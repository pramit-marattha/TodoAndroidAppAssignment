package com.np.pramitmarattha.repository;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQueryBuilder;
import com.np.pramitmarattha.database.AppDatabase;
import com.np.pramitmarattha.database.dao.TasksDao;
import com.np.pramitmarattha.database.TodoItem;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class TasksItemsRepository {
    private TasksDao todoItemDAO;
    private static TasksItemsRepository instance;
    public static TasksItemsRepository getInstance(Application application) {
        if (instance == null) {
            instance = new TasksItemsRepository(application);
        }
        return instance;
    }
    private TasksItemsRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        todoItemDAO = appDatabase.todoItemDAO();
    }
    public Single<Long> create(TodoItem todoItem) {
        return todoItemDAO.create(todoItem);
    }
    public Single<Integer> update(TodoItem todoItem) {
        return todoItemDAO.update(todoItem);
    }
    public Single<Integer> delete(TodoItem todoItem) {
        return todoItemDAO.delete(todoItem);
    }
    public Flowable<List<TodoItem>> findByTodoListId(long todoListId) {
        return todoItemDAO.findByTodoListId(todoListId);
    }
    public LiveData<List<TodoItem>> getTodoItems(TasksItemSearch criteria) {
        return todoItemDAO.getTodoItems(
                buildQuery(criteria)
        );
    }
    public Flowable<TodoItem> findById(long todoItemId) {
        return todoItemDAO.findById(todoItemId);
    }
    private SupportSQLiteQuery buildQuery(TasksItemSearch criteria) {
        SupportSQLiteQueryBuilder builder = SupportSQLiteQueryBuilder.builder("todo_items");
        ArrayList<Object> args = new ArrayList<>();
        ArrayList<String> clauses = new ArrayList<>();
        if (criteria.todoListId != -1) {
            clauses.add("todo_list_id = ?");
            args.add(criteria.todoListId);
        }

        if (criteria.searchKeyword != null && !criteria.searchKeyword.isEmpty()) {
            clauses.add("name LIKE ?");
            args.add("%" + criteria.searchKeyword + "%");
        }
        switch (criteria.completionState) {
            case Complete:
                clauses.add("is_complete = 1");
                break;
            case Incomplete:
                clauses.add("is_complete = 0");
                break;
        }
        clauses.add("is_deleted = 0");
        builder.selection(TextUtils.join(" AND ", clauses), args.toArray());
        Log.d("QUERY", builder.create().getSql());
        return builder.create();
    }
}

