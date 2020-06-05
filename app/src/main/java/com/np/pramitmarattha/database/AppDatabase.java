package com.np.pramitmarattha.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.np.pramitmarattha.database.dao.TasksListDao;
import com.np.pramitmarattha.database.dao.TasksDao;
import com.np.pramitmarattha.database.dao.UserDao;

@Database(entities = {EntireUserDatabase.class, TodoList.class, TodoItem.class}, version = 6, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "pramitTodo_database";
    private static AppDatabase instance;
    public abstract UserDao userDAO();
    public abstract TasksListDao todoListDAO();
    public abstract TasksDao todoItemDAO();
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().addCallback(callback).build();
        }
        return instance;
    }
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}