package com.np.pramitmarattha.viewmodel;

import android.app.Application;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.np.pramitmarattha.database.EntireUserDatabase;
import com.np.pramitmarattha.database.RegisteredUserAuthentication;
import com.np.pramitmarattha.liveupdatestuff.SnackMessageSubUpdate;

public class authHandleHomeViewModel extends ParentExtendViewModel {
    private SnackMessageSubUpdate<EntireUserDatabase> authenticaionStatus = new SnackMessageSubUpdate<>();
    private RegisteredUserAuthentication auth;
    private final Handler handler = new Handler();
    private final Runnable listenToAuth = () ->
            toDispose(
                    auth.status.subscribe(
                            status -> authenticaionStatus.postValue(status.currentUser),
                            error -> authenticaionStatus.postValue(null)
                    )
            );
    public authHandleHomeViewModel(@NonNull Application application) {
        super(application);
        auth = RegisteredUserAuthentication.getInstance(application);
        handler.postDelayed(listenToAuth, 1000);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        handler.removeCallbacks(listenToAuth);
        auth.dispose();
    }
    public LiveData<EntireUserDatabase> onAuthStatusChanged() {
        return authenticaionStatus;
    }
}