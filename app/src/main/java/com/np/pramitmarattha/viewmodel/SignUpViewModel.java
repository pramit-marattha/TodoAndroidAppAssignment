package com.np.pramitmarattha.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import com.np.pramitmarattha.R;
import com.np.pramitmarattha.database.EntireUserDatabase;
import com.np.pramitmarattha.repository.UsersRepository;
import com.np.pramitmarattha.liveupdatestuff.SnackMessageSubUpdate;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignUpViewModel extends ParentExtendViewModel {
    private UsersRepository usersRepository;
    private SnackMessageSubUpdate<Long> onUserCreated = new SnackMessageSubUpdate<>();
    public SignUpViewModel(@NonNull Application application) {
        super(application);
        usersRepository = UsersRepository.getInstance(application);
    }
    public void createUser(EntireUserDatabase user) {
        Disposable disposable = usersRepository.create(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        id -> onUserCreated.postValue(id),
                        error -> postError(R.string.COULDNT_CREATE_USER)
                );
        toDispose(disposable);
    }
    public SnackMessageSubUpdate<Long> onUserCreated() {
        return onUserCreated;
    }
}