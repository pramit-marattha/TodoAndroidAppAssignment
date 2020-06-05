package com.np.pramitmarattha.repository;

import android.app.Application;

import com.np.pramitmarattha.database.AppDatabase;
import com.np.pramitmarattha.database.EntireUserDatabase;
import com.np.pramitmarattha.database.dao.UserDao;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class UsersRepository {
    private static UsersRepository sInstance;
    public static UsersRepository getInstance(Application application) {
        if (sInstance == null) {
            sInstance = new UsersRepository(application);
        }
        return sInstance;
    }
    private UserDao userDAO;

    private UsersRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        userDAO = appDatabase.userDAO();
    }

    public Maybe<Long> create(EntireUserDatabase user) {
        return userDAO.create(user);
    }
    public Completable update(EntireUserDatabase user) {
        return userDAO.update(user);
    }
    public Completable delete(EntireUserDatabase user) {
        return userDAO.delete(user);
    }
    public Single<EntireUserDatabase> getUser(long userId) {
        return userDAO.getUser(userId);
    }
    public Single<EntireUserDatabase> findByUsername(String userName) {
        return userDAO.findByUsername(userName);
    }
}
