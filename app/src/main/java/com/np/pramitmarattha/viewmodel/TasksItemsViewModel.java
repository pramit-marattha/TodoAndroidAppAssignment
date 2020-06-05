package com.np.pramitmarattha.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.np.pramitmarattha.database.TodoItem;
import com.np.pramitmarattha.repository.TasksItemsRepository;
import com.np.pramitmarattha.repository.TasksItemSearch;
import com.np.pramitmarattha.liveupdatestuff.SnackMessageSubUpdate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.np.pramitmarattha.repository.TasksItemSearch.CompletionState.Complete;
import static com.np.pramitmarattha.repository.TasksItemSearch.CompletionState.Incomplete;
import static com.np.pramitmarattha.repository.TasksItemSearch.CompletionState.NotSpecified;


public class TasksItemsViewModel extends ParentExtendViewModel {
    private final TasksItemsRepository repository;
    private MediatorLiveData<List<TodoItem>> onListUpdated = new MediatorLiveData<>();
    private SnackMessageSubUpdate<Boolean> onItemTempDeleted = new SnackMessageSubUpdate<>();
    private SnackMessageSubUpdate<Long> onItemCreated = new SnackMessageSubUpdate<>();
    private TasksItemSearch criteria = new TasksItemSearch();
    private List<TasksItemSearch.CompletionState> statusValues = Arrays.asList(
            NotSpecified,
            Complete,
            Incomplete
    );
    private SnackMessageSubUpdate<TodoItem> onTodoItemFound = new SnackMessageSubUpdate<>();
    private SnackMessageSubUpdate<Integer> onItemUpdated = new SnackMessageSubUpdate<>();
    private long todoListId;
    public TasksItemsViewModel(@NonNull Application application) {
        super(application);
        repository = TasksItemsRepository.getInstance(application);
    }
    public void setTodoListId(long todoListId) {
        criteria.todoListId = todoListId;
        reloadData();
    }
    public void setSearchKeyword(String query) {
        criteria.searchKeyword = query;
        reloadData();
    }
    public void setStatus(int index) {
        criteria.completionState = statusValues.get(index);

        reloadData();
    }
    public void clearFilters() {
        criteria = new TasksItemSearch();

        onListUpdated.postValue(new ArrayList<>());
    }
    public int getStatus() {
        return statusValues.indexOf(criteria.completionState);
    }
    public void update(TodoItem item, boolean isTempDeleted) {
        toDispose(
                repository.update(item)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(affectedRows -> {
                            if (isTempDeleted)
                                onItemTempDeleted.setValue(true);
                            else {
                                onItemUpdated.postValue(affectedRows);
                            }

                        }, this::onQueryError)
        );
    }
    public void findById(long itemId) {
        toDispose(
                repository.findById(itemId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(todoItem -> {
                            onTodoItemFound.postValue(todoItem);
                        }, this::onQueryError));
    }
    public void updateStatus(TodoItem item, boolean isComplete) {
        item.setComplete(isComplete);
        toDispose(
                repository.update(item)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(affectedRows -> {
                        }, this::onQueryError)
        );
    }
    public void create(TodoItem item) {
        item.setCreatedAt(new Date(System.currentTimeMillis()));
        toDispose(
                repository.create(item)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(id -> onItemCreated.postValue(id), this::onQueryError)
        );
    }
    public void delete(TodoItem item) {
        toDispose(
                repository.delete(item)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(affectedRows -> {
                        }, this::onQueryError)
        );
    }
    public void tempDelete(TodoItem item, boolean setDeleted) {
        item.setDeleted(setDeleted);
        update(item, setDeleted);
    }
    public LiveData<List<TodoItem>> onListUpdated() {
        return onListUpdated;
    }
    public SnackMessageSubUpdate<Boolean> onItemTempDeleted() {
        return onItemTempDeleted;
    }
    public SnackMessageSubUpdate<Long> onItemCreated() {
        return onItemCreated;
    }
    public SnackMessageSubUpdate<Integer> onItemUpdated() {
        return onItemUpdated;
    }
    public SnackMessageSubUpdate<TodoItem> onTodoItemFound() {
        return onTodoItemFound;
    }
    private LiveData<List<TodoItem>> currentDataSource = null;
    private void reloadData() {
        if (currentDataSource != null) {
            onListUpdated.removeSource(currentDataSource);
        }
        currentDataSource = repository.getTodoItems(criteria);
        onListUpdated.addSource(currentDataSource, todoItems -> onListUpdated.postValue(todoItems));
    }
}