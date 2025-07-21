package com.dsy.dsu.BusinessLogicForApps.WorkManagers;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.Service.IntentServiceBoot;

import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.BusinessLogicForApps.WorkManagers.binesslogic.GetWorker;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.util.Date;

public class MyWork_Async_Public extends Worker {
    /*    private String ИмяСлужбыWorkManger ="WorkManager Synchronizasiy_Data";*/
    private  String getAnalysisSingleWorkManger ="WorkManager Synchronizasiy_Data Disposable";
    private   ServiceConnection serviceConnectionPublic;

    /*  protected String ИмяСлужбыWorkManger ="WorkManager Synchronizasiy_Data";
      protected  String ИмяСлужбыSingleWorkManger ="WorkManager Synchronizasiy_Data Disposable";*/
    private  IntentServiceBoot.LocalBinderBootSerice          getlocalBinderBootSerice;
    private String  getWhoLaunched;
    // TODO: 28.09.2022
    @SuppressLint("RestrictedApi")
    public MyWork_Async_Public(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        try{
            // TODO: 22.12.2022
            getInParamentesWork();
            // TODO: 02.04.2024 Bl
            getLiveBindibngServiceBoot();

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    @NonNull
    @Override
    public Result doWork() {
        try {

            Integer PublicIDWorkMangerPubluc=   new GetttingPublicID().getttingPublicID(getApplicationContext());

            // TODO: 18.03.2025
            GetWorker getWorker=new GetWorker(getApplicationContext());
            // TODO: 07.04.2025 start
            getWorker.startingPublicWorkManager(getlocalBinderBootSerice,getAnalysisSingleWorkManger,getWhoLaunched);
            // TODO: 07.04.2025 close
            getApplicationContext().unbindService(serviceConnectionPublic);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " getlocalBinderBootSerice " +getlocalBinderBootSerice );

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " PublicIDWorkMangerPubluc " +PublicIDWorkMangerPubluc);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Result.failure();
        }
        return      Result.success();
    }




    @Override
    public void onStopped() {
        super.onStopped();
        try{
            Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                    + "onStopped  onStopped");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }







    public void getLiveBindibngServiceBoot() {
        try{
            if (getlocalBinderBootSerice==null) {
                Intent intentstartServiceOneSignal=new Intent(getApplicationContext(), IntentServiceBoot.class);
                // TODO: 19.07.2025
                serviceConnectionPublic=      new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        if (service.isBinderAlive()) {
                            getlocalBinderBootSerice = (IntentServiceBoot.LocalBinderBootSerice) service;
                            // TODO: 03.03.2025
                            // TODO: 03.03.2025

                            Log.d(getApplicationContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " onServiceConnected  метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    "  + getlocalBinderBootService.isBinderAlive()"+
                                    getlocalBinderBootSerice.isBinderAlive());

                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.d(getApplicationContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " onServiceConnected  метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    }
                };
                // TODO: 24.01.2024
                getApplicationContext().bindService(intentstartServiceOneSignal, serviceConnectionPublic, Context.BIND_AUTO_CREATE);
            }

            Log.d(getApplicationContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getApplicationContext().getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext().getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    private void getInParamentesWork() {
        try{
            Data data = getInputData();
            getWhoLaunched = data.getString("getWhoLaunched");
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }



    // TODO: 03.10.2024 end class

}





























