package com.np.pramitmarattha.liveupdatestuff;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;
public class SnackMessageSubUpdate<T> extends MutableLiveData<T> {
    private static final String TAG = "SnackMessageSubUpdate";
    private final AtomicBoolean mPending = new AtomicBoolean(false);
    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer<? super T> observer) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Yoo!!! Multiple Observer");
        }
        // Observe the internal MutableLiveData
        super.observe(owner, t -> {
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t);
            }
        });
    }
    @MainThread
    public void setValue(@Nullable T t) {
        mPending.set(true);
        super.setValue(t);
    }

    /**
     * Used for cases whre T is Void, to make calls simple and eficient.
     */
    @MainThread
    public void call() {
        setValue(null);
    }
}