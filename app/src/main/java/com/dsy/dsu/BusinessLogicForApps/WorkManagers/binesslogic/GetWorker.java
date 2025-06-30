package com.dsy.dsu.BusinessLogicForApps.WorkManagers.binesslogic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;


import com.dsy.dsu.BusinessLogicForApps.BootAndAsync.Model.Service.IntentServiceBoot;
import com.dsy.dsu.BusinessLogicForApps.GetConnectivityManagerAndroid;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossHilt.intarfaces.getHiltPortJbossInterface;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

import dagger.hilt.EntryPoints;

public class GetWorker {


    Context context;

    public GetWorker(Context context) {
        this.context = context;
    }


    public void startingSingleWorkerManger(@NotNull  IntentServiceBoot.LocalBinderBootSerice          getlocalBinderBootSerice ,@NotNull String getAnalysisPublicWorkManger) {
        try{
            LinkedHashMap<Integer,String> getHiltPortJboss=   EntryPoints.get(context, getHiltPortJbossInterface.class).getHiltPortJboss();

            Boolean isWorkManagerRunning=  new FindRunnigServiceBeforeWorkManager(context).isWorkManagerRunning(getAnalysisPublicWorkManger);

            // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
            boolean ВыбранныйРежимСети =
                    new GetConnectivityManagerAndroid(context).сonnectivityManageruserselection();
            Intent intentSingleWorker=new Intent();

            if(getlocalBinderBootSerice!=null) {
                if (ВыбранныйРежимСети) {
                    if (getlocalBinderBootSerice.isBinderAlive()  ) {
                        // TODO: 30.06.2025
                        if (isWorkManagerRunning==false) {
                            String actionSingleWorker =  "lanchUpdatePOAndAsync" ;
                            intentSingleWorker.setAction(actionSingleWorker);
                            intentSingleWorker.setData(Uri.parse(actionSingleWorker));
// TODO: 30.06.2025 Запускаем Саму Службу Single Workmanager
                            getlocalBinderBootSerice.getService().startingServiceSingleWorkManger(intentSingleWorker, getHiltPortJboss);
                        }

                    }
                } else {
// TODO: 30.06.2025 Запускаем Саму Службу Single Workmanager
                    String exitSingleWorker = "ExitBootService";
                    intentSingleWorker.setAction(exitSingleWorker);
                    intentSingleWorker.setData(Uri.parse(exitSingleWorker));

                    getlocalBinderBootSerice.getService().startingServiceSingleWorkManger(intentSingleWorker, getHiltPortJboss);

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " SINGLE SINGLE SINGLE  isWorkManagerRunning " );


                }
            }
            // TODO: 04.04.2025//

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }



    public void startingPublicWorkManager(@NotNull  IntentServiceBoot.LocalBinderBootSerice          getlocalBinderBootSerice,
                                          @NotNull String    getAnalysisSingleWorkManger  ) {
        try{
            LinkedHashMap<Integer,String> getHiltPortJboss=   EntryPoints.get(context, getHiltPortJbossInterface.class).getHiltPortJboss();

            Boolean isWorkManagerRunning=  new FindRunnigServiceBeforeWorkManager(context).isWorkManagerRunning(getAnalysisSingleWorkManger);

            // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
            boolean ВыбранныйРежимСети =
                    new GetConnectivityManagerAndroid(context).сonnectivityManageruserselection();
            Intent intentSingleWorker=new Intent();

            //TODO Запущеные Activity
            GetActivityManager getActivityManagerPublicWorker=new GetActivityManager(context);
            Boolean getActivityTasks=   getActivityManagerPublicWorker.getActivityTasks();


            if (getActivityTasks==false) {
                if(getlocalBinderBootSerice!=null) {
                    if (ВыбранныйРежимСети) {

                        if (getlocalBinderBootSerice.isBinderAlive() && isWorkManagerRunning == false) {
                            String actionSingleWorker =  "lanchAsync" ;
                            intentSingleWorker.setAction(actionSingleWorker);
                            intentSingleWorker.setData(Uri.parse(actionSingleWorker));
// TODO: 06.06.2025  запускам public work managers
                            getlocalBinderBootSerice.getService().startingServicePublicWorkManger(intentSingleWorker, getHiltPortJboss);

                        }
                    }
                }
            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " PUBLIC PUBLIC PUBLIC isWorkManagerRunning " +isWorkManagerRunning  + " getActivityTasks " +getActivityTasks  );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


    public void closingSinglePublicWorkManager(@NotNull  IntentServiceBoot.LocalBinderBootSerice          getlocalBinderBootSerice) {
        try{
            getlocalBinderBootSerice.getService().stopSelf();
            getlocalBinderBootSerice.getService().onDestroy();
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


}