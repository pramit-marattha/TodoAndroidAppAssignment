package com.np.pramitmarattha;

import android.app.Application;

import com.np.pramitmarattha.database.AppDatabase;
import com.np.pramitmarattha.database.TaskEntry;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

class AddEditTaskViewModel extends AndroidViewModel {

    public LiveData<TaskEntry> getTask() {
        return task;
    }
    LiveData<TaskEntry> task;
    public AddEditTaskViewModel(Application application, int taskId) {

        super(application);

        AppDatabase database = AppDatabase.getInstance(application);
        task = database.taskDao().loadTaskById(taskId);


    }
}
