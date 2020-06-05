package com.np.pramitmarattha.repository;

import android.app.Application;
import com.np.pramitmarattha.database.AppDatabase;
import com.np.pramitmarattha.database.dao.TasksListDao;
import com.np.pramitmarattha.database.dao.UserDao;
import com.np.pramitmarattha.database.TodoList;
import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class TasksListRepository {
    private TasksListDao todoListDAO;
    private UserDao userDAO;
    private static TasksListRepository instance;
    public static TasksListRepository getInstance(Application application) {
        if (instance == null) {
            instance = new TasksListRepository(application);
        }
        return instance;
    }
    private TasksListRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        todoListDAO = appDatabase.todoListDAO();
        userDAO = appDatabase.userDAO();
    }
    public Single<Long> create(TodoList todoList) {
        return todoListDAO.create(todoList);
    }
    public Single<Integer> update(TodoList todoList) {
        return todoListDAO.update(todoList);
    }
    public Single<Integer> delete(TodoList todoList) {
        return todoListDAO.delete(todoList);
    }
    public Flowable<TodoList> getTodoListById(long todoListId) {
        return todoListDAO.getTodoListById(todoListId);
    }
    public Flowable<List<TodoList>> findByUserId(long userId) {
        return todoListDAO.findByUserId(userId);
    }
}