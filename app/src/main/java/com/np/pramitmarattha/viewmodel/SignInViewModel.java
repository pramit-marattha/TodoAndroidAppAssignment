package com.np.pramitmarattha.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.np.pramitmarattha.R;
import com.np.pramitmarattha.database.EntireUserDatabase;
import com.np.pramitmarattha.database.RegisteredUserAuthentication;
import com.np.pramitmarattha.repository.UsersRepository;
import com.np.pramitmarattha.liveupdatestuff.SnackMessageSubUpdate;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignInViewModel extends ParentExtendViewModel {
    private UsersRepository usersRepository;
    private RegisteredUserAuthentication auth;
    private SnackMessageSubUpdate<EntireUserDatabase> onLoggedIn = new SnackMessageSubUpdate<>();
    public SignInViewModel(@NonNull Application application) {
        super(application);
        auth = RegisteredUserAuthentication.getInstance(application);
        usersRepository = UsersRepository.getInstance(application);
    }
    public void signIn(EntireUserDatabase user) {
        Disposable disposable = usersRepository.findByUsername(user.getUserName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user1 -> {
                    if (user1.getPassword().equals(user.getPassword())) {
                        auth.setCurrentUser(user1.getId(), getApplication());
                        onLoggedIn.postValue(user1);
                    } else {
                        postError(R.string.PASSWORD_WRONG);
                    }
                }, error -> postError(R.string.PASSWORD_WRONG));

        toDispose(disposable);
    }
    public SnackMessageSubUpdate<EntireUserDatabase> onLoggedIn() {
        return onLoggedIn;
    }
    public SnackMessageSubUpdate<Error> onError() {
        return onError;
    }
}
