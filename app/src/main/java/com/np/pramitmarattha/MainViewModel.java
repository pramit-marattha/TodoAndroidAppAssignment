package com.np.pramitmarattha;


import android.app.Application;
import android.util.Log;
import com.np.pramitmarattha.database.AppDatabase;
import com.np.pramitmarattha.database.TaskEntry;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends AndroidViewModel {

        Repository repository;

        private  LiveData<List<TaskEntry>> tasks;

        private MutableLiveData<Boolean> _showSnackBarEvent = new MutableLiveData<>();

        public LiveData<Boolean> showSnackBarEvent(){
                return _showSnackBarEvent;
        }


        public  MainViewModel(Application application){
                super(application);
                AppDatabase database = AppDatabase.getInstance(application);
                repository = new Repository(database);
                tasks = repository.getTasks();
        }

        public LiveData<List<TaskEntry>> getTasks(){
                return tasks;
        }

        public void deleteTask(TaskEntry task){
                repository.deleteTask(task);

                _showSnackBarEvent.setValue(true);
        }

}
