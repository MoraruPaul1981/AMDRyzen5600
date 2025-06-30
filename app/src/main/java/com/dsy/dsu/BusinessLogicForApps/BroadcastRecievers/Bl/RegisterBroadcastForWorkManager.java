package com.dsy.dsu.BusinessLogicForApps.BroadcastRecievers.Bl;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@QualifierInRegisterBoradcastWork
@Module
@InstallIn(SingletonComponent.class)
@Named
public class RegisterBroadcastForWorkManager {

Context context;
    public  @Inject RegisterBroadcastForWorkManager() {
        // TODO: 22.03.202
        this.context=context;
    }

    // TODO: 22.03.2024 запускам work manager 


 public void statingPublicWorkMAnager(@NotNull Context context ) {
        try{
                // TODO: 22.03.2024  регистрация work manager
                new getStartingWorkmanagerPublic().metodRegistraBroadCastFroPublicAsyns(context);

            Log.d(this.getClass().getName(),"\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
}
