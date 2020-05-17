package com.np.pramitmarattha.MainTasks;

import android.app.Application;

import com.np.pramitmarattha.MainTasks.RunnableExecutors;
import com.np.pramitmarattha.database.AppDatabase;
import com.np.pramitmarattha.database.TaskDao;
import com.np.pramitmarattha.database.TaskEntry;
import androidx.lifecycle.LiveData;
import java.util.List;

public class Repository {
    private TaskDao taskDao;
    private LiveData<List<TaskEntry>> AllTheTasks;

    Repository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        taskDao = db.taskDao();
        AllTheTasks = taskDao.getAllTasks();
    }

    LiveData<List<TaskEntry>> getAllWords() {
        return AllTheTasks;
    }
    public void insert (final TaskEntry taskEntry) {
        RunnableExecutors.getInstance().DiskThread().execute(new Runnable() {
            @Override
            public void run() {
                taskDao.insertTodo(taskEntry);
            }
        });
    }

    public void delete(final TaskEntry taskEntry)  {
        RunnableExecutors.getInstance().DiskThread().execute(new Runnable() {
            @Override
            public void run() {
                taskDao.deleteTodo(taskEntry);
            }
        });
    }
    public void update(final TaskEntry taskEntry)  {
        RunnableExecutors.getInstance().DiskThread().execute(new Runnable() {
            @Override
            public void run() {
                taskDao.update(taskEntry);
            }
        });
    }
}
