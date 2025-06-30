package com.dsy.dsu.Documents1CApp.ViewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ModelFactoryCommitDocuments implements ViewModelProvider.Factory {

    private final long id;

    private Context context;

    public ModelFactoryCommitDocuments(long id, Context context) {
        super();
        this.id = id;
        this.context=context;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass == ViewModelCommitDocuments.class) {
            return (T) new ViewModelCommitDocuments(id,context);
        }else {
            return ViewModelProvider.Factory.super.create(modelClass);
        }

    }
}