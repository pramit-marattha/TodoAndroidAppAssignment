package com.np.pramitmarattha.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.np.pramitmarattha.database.RegisteredUserAuthentication;
import com.np.pramitmarattha.database.TodoList;
import com.np.pramitmarattha.repository.TasksItemsRepository;
import com.np.pramitmarattha.repository.TasksListRepository;
import com.np.pramitmarattha.liveupdatestuff.SnackMessageSubUpdate;

import java.io.File;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TasksListViewModel extends ParentExtendViewModel {

    private MutableLiveData<List<TodoList>> onListUpdated = new MutableLiveData<>();
    private SnackMessageSubUpdate<Boolean> onTodoListTempDeleted = new SnackMessageSubUpdate<>();
    private SnackMessageSubUpdate<Boolean> onTodoListEmpty = new SnackMessageSubUpdate<>();
    private SnackMessageSubUpdate<File> onFileCreated = new SnackMessageSubUpdate<>();
    public LiveData<List<TodoList>> onListUpdated() {
        return onListUpdated;
    }
    private TasksListRepository todoListRepository;
    private TasksItemsRepository todoItemRepository;
    private final RegisteredUserAuthentication auth;
    public TasksListViewModel(@NonNull Application application) {
        super(application);
        auth = RegisteredUserAuthentication.getInstance(application);
        todoListRepository = TasksListRepository.getInstance(application);
        todoItemRepository = TasksItemsRepository.getInstance(application);
        getTodoListsByUserId(auth.currentUserId(application));
    }

    public void createTodoList(TodoList todoList) {
        todoList.setUserId(auth.currentUserId(getApplication()));
        toDispose(
                todoListRepository.create(todoList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(todoListId -> {

                        }, error -> {
                        })
        );
    }

    public void updateTodoList(TodoList todoList, boolean isTempDeleted) {
        toDispose(
                todoListRepository.update(todoList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(updatedRow -> {
                                    if (isTempDeleted)
                                        onTodoListTempDeleted.setValue(true);
                                },
                                this::onQueryError)
        );
    }
    public void tempDeleteTodoList(TodoList todoList, boolean setDeleted) {
        todoList.setDeleted(setDeleted);
        updateTodoList(todoList, setDeleted);
    }
    public void deleteTodoList(TodoList todoList) {
        toDispose(
                todoListRepository.delete(todoList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(deletedRow -> {
                        }, this::onQueryError)
        );
    }
    public void getTodoListsByUserId(long userId) {
        toDispose(
                todoListRepository.findByUserId(userId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(userAndTodoLists -> onListUpdated.postValue(userAndTodoLists), this::onQueryError)
        );
    }
    public void logout() {
        auth.clearAuthData(getApplication());
    }
    public SnackMessageSubUpdate<Boolean> onTodoListTempDeleted() {
        return onTodoListTempDeleted;
    }
    public SnackMessageSubUpdate<File> onFileCreated() {
        return onFileCreated;
    }
    public SnackMessageSubUpdate<Boolean> onTodoListEmpty() {
        return onTodoListEmpty;
    }
}