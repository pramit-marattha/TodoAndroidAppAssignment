package com.np.pramitmarattha.MainTasks;

import android.app.Application;

import com.np.pramitmarattha.database.TaskEntry;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends AndroidViewModel {
        private LiveData<List<TaskEntry>> CustTasks;
        Repository CustRepository;
        private MutableLiveData<Boolean> _showSnackBarEvent = new MutableLiveData<>();
        public LiveData<Boolean> showSnackBarEvent(){
                return _showSnackBarEvent;
        }
        public MainViewModel(@NonNull Application application) {
                super(application);
                CustRepository = new Repository(application);
                CustTasks = CustRepository.getAllWords();
        }
        public LiveData<List<TaskEntry>> getCustomTasks(){
                return CustTasks;
        }
        public void insert(TaskEntry taskEntry) { CustRepository.insert(taskEntry); }
        public void delete(TaskEntry taskEntry) { CustRepository.delete(taskEntry);
                _showSnackBarEvent.setValue(true);
        }
        public void update(TaskEntry taskEntry) { CustRepository.update(taskEntry); }
}