package com.dsy.dsu.CommitPricesApp.ViewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ModelFactory implements ViewModelProvider.Factory {

    private final long id;

    private Context context;

    public ModelFactory(long id,  Context context) {
        super();
        this.id = id;
        this.context=context;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass == ModelComminingPrisesString.class) {
            return (T) new ModelComminingPrisesString(id,context);
        }else {
            return ViewModelProvider.Factory.super.create(modelClass);
        }

    }
}
