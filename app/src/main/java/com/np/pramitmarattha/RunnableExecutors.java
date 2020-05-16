package com.np.pramitmarattha;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import androidx.annotation.NonNull;

public class RunnableExecutors {
    // Singleton instantiation
    private static final Object LOCK = new Object();
    private static RunnableExecutors RunnableExecutorsInstance;
    private final Executor InputDiskThread;
    private final Executor mainThread;
    private final Executor InputNetworkThread;
    private RunnableExecutors(Executor InputDiskThread, Executor InputNetworkThread, Executor mainThread) {
        this.InputDiskThread = InputDiskThread;
        this.InputNetworkThread = InputNetworkThread;
        this.mainThread = mainThread;
    }
    public static RunnableExecutors getInstance() {
        if (RunnableExecutorsInstance == null) {
            synchronized (LOCK) {
                RunnableExecutorsInstance = new RunnableExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return RunnableExecutorsInstance;
    }
    public Executor DiskThread() {
        return InputDiskThread;
    }
    public Executor mainThread() {
        return mainThread;
    }
    public Executor NetworkThread() {
        return InputNetworkThread;
    }
    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
