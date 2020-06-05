package com.np.pramitmarattha.database;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.np.pramitmarattha.repository.UsersRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class RegisteredUserAuthentication {
    private static final String USER_AUTH = "USER_AUTH";
    private static final String USER_ID = "USER_ID";
    private static RegisteredUserAuthentication registeredUserAuthInstance = null;
    public static RegisteredUserAuthentication getInstance(Application application) {
        if (registeredUserAuthInstance == null) {
            registeredUserAuthInstance = new RegisteredUserAuthentication(application);
        }
        return registeredUserAuthInstance;
    }
    public final Subject<AuthStatus> status = BehaviorSubject.create();
    private Disposable disposable;
    private UsersRepository users;
    private RegisteredUserAuthentication(Application application) {
        users = UsersRepository.getInstance(application);
        loadCurrentUser(application);
    }
    private void loadCurrentUser(Application application) {
        long userId = currentUserId(application);
        loadCurrentUser(userId, true);
    }
    private void loadCurrentUser(long userId, boolean isInitialLoad) {
        if (userId != -1) {
            dispose();
            disposable = users.getUser(userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(user -> {
                        AuthStatus.State state = isInitialLoad
                                ? AuthStatus.State.LoggedIn
                                : AuthStatus.State.JustLoggedIn;
                        status.onNext(new AuthStatus(user, state));
                    }, t -> {
                        AuthStatus.State state = isInitialLoad
                                ? AuthStatus.State.LoggedOut
                                : AuthStatus.State.JustLoggedOut;

                        status.onNext(new AuthStatus(state));
                    });
        } else {
            status.onNext(
                    new AuthStatus(
                            isInitialLoad
                                    ? AuthStatus.State.LoggedOut
                                    : AuthStatus.State.JustLoggedOut
                    )
            );
        }
    }
    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
    public void setCurrentUser(long userId, Application application) {
        SharedPreferences.Editor edit = application.getSharedPreferences(USER_AUTH, Context.MODE_PRIVATE).edit();
        edit.putLong(USER_ID, userId);
        edit.apply();
        loadCurrentUser(userId, false);
    }
    public long currentUserId(Application application) {
        return application.getSharedPreferences(USER_AUTH, Context.MODE_PRIVATE)
                .getLong(USER_ID, -1);
    }
    public void clearAuthData(Application application) {
        SharedPreferences.Editor edit = application.getSharedPreferences(USER_AUTH, Context.MODE_PRIVATE).edit();
        edit.clear();
        edit.apply();
        loadCurrentUser(-1, false);
    }
    public static class AuthStatus {
        public final EntireUserDatabase currentUser;
        public final State state;

        AuthStatus(EntireUserDatabase currentUser, State state) {
            this.currentUser = currentUser;
            this.state = state;
        }
        AuthStatus(State state) {
            this.currentUser = null;
            this.state = state;
        }
        public enum State {
            LoggedIn,
            LoggedOut,
            JustLoggedIn,
            JustLoggedOut
        }
    }
}