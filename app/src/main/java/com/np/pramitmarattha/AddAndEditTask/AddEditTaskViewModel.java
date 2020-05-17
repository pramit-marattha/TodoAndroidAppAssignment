package com.np.pramitmarattha.AddAndEditTask;

import android.app.Application;
import com.np.pramitmarattha.database.AppDatabase;
import com.np.pramitmarattha.database.TaskEntry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class AddEditTaskViewModel extends ViewModel {
    private LiveData<TaskEntry> task;
    public AddEditTaskViewModel(AppDatabase database, int todoId){
        task = database.taskDao().loadTaskById(todoId);
    }

    public LiveData<TaskEntry> getTasks() {
        return task;
    }
}