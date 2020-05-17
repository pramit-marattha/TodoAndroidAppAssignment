package com.np.pramitmarattha.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public  interface TaskDao {

    @Query("select * from tasks order by priority")
    LiveData<List<TaskEntry>> getAllTasks();

    @Insert
    void insertTodo(TaskEntry todo);

    @Delete
    void deleteTodo(TaskEntry todo);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(TaskEntry todo);

    @Query("select * from tasks where id = :id")
    LiveData<TaskEntry> loadTaskById(int id);

}
