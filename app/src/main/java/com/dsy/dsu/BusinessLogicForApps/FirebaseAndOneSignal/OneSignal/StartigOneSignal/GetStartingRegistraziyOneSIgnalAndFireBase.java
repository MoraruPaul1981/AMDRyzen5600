package com.dsy.dsu.BusinessLogicForApps.FirebaseAndOneSignal.OneSignal.StartigOneSignal;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceOneSignalForFirebase;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@QualifierInRegisterRegistraziyOneSignal
@Module
@InstallIn(SingletonComponent.class)
public class GetStartingRegistraziyOneSIgnalAndFireBase {

    Context context;
    public  @Inject GetStartingRegistraziyOneSIgnalAndFireBase(@ApplicationContext Context context) {
        this.context = context;
    }

   public void getStartingRegistraziyOneSignalFirebase( ){

try {
    Intent intentstartServiceOneSignal=new Intent(context, ServiceOneSignalForFirebase.class);
    intentstartServiceOneSignal.setAction("com.registariionesignal.net");
    // TODO: 28.03.2024
    context.  startService(intentstartServiceOneSignal);


    } catch (Exception e) {
    e.printStackTrace();
    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
    new RecordNewErros(context).recordnewerror(e.toString(),
            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
            Thread.currentThread().getStackTrace()[2].getLineNumber());
}
    }
}
