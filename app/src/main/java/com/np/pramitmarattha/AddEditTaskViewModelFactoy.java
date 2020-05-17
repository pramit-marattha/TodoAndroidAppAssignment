package com.np.pramitmarattha;

import android.app.Application;

import com.np.pramitmarattha.database.AppDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AddEditTaskViewModelFactoy extends ViewModelProvider.NewInstanceFactory {
    private final AppDatabase ApplicationDataBase;
    private final int CustomTaskId;

    public AddEditTaskViewModelFactoy(AppDatabase mdb, int mTodoId) {
        this.ApplicationDataBase = mdb;
        this.CustomTaskId = mTodoId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddEditTaskViewModel(ApplicationDataBase, CustomTaskId);
    }
}
