package com.np.pramitmarattha.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.np.pramitmarattha.database.EntireUserDatabase;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface UserDao {
    @Insert()
    Maybe<Long> create(EntireUserDatabase entireUserDatabase);
    @Query("SELECT * FROM users WHERE user_name==:userName")
    Single<EntireUserDatabase> findByUsername(String userName);
    @Update
    Completable update(EntireUserDatabase user);
    @Delete
    Completable delete(EntireUserDatabase user);
    @Query("SELECT * FROM users WHERE id==:userId")
    Single<EntireUserDatabase> getUser(long userId);
    @Query("SELECT * FROM users")
    LiveData<List<EntireUserDatabase>> getUsers();
}
