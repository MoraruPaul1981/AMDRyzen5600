package com.dsy.dsu.BusinessLogicForApps.BootAndAsync.ViewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class ViewModel extends AndroidViewModel {

    private  Context context;
    private MutableLiveData mutableLiveData;


    public  @Inject ViewModel(@NonNull Application application) {
        super(application);
        // TODO: 03.03.2025
        context=getApplication().getApplicationContext();
        // TODO: 03.03.2025
        Log.d(context.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

    }



}
