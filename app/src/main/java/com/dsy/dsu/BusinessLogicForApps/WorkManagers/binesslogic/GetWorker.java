package com.dsy.dsu.BusinessLogicForApps.WorkManagers.binesslogic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;


import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.Service.IntentServiceBoot;
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


    public void startingSingleWorkerManger(@NotNull  IntentServiceBoot.LocalBinderBootSerice          getlocalBinderBootSerice ,@NotNull String getAnalysisPublicWorkManger ,@NotNull String getWhoLaunched) {
        try{
            LinkedHashMap<Integer,String> getHiltPortJboss=   EntryPoints.get(context, getHiltPortJbossInterface.class).getHiltPortJboss();

            ////Boolean isPublicWorkManagerRunning=  new FindActivityRunning(context).isWorkManagerRunning(getAnalysisPublicWorkManger);

            // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
            boolean ВыбранныйРежимСети =
                    new GetConnectivityManagerAndroid(context).сonnectivityManageruserselection();
            Intent intentSingleWorker=new Intent();
            //TODO Запущеные Activity
            GetActivityManager getActivityManagerPublicWorker=new GetActivityManager(context);
            Boolean getActivityTasks=   getActivityManagerPublicWorker.getActivityTasks();



                if (ВыбранныйРежимСети) {
                    // TODO: 30.06.2025
                    if (  getActivityTasks) {
                        // TODO: 30.06.2025
                            intentSingleWorker.setAction(getWhoLaunched);
                            intentSingleWorker.setData(Uri.parse(getWhoLaunched));
// TODO: 30.06.2025 Запускаем Саму Службу Single Workmanager
                            getlocalBinderBootSerice.getService().startingServiceSingleWorkManger(intentSingleWorker, getHiltPortJboss);
                    }
                } else {
// TODO: 30.06.2025 Запускаем Саму Службу Single Workmanager
                    String exitSingleWorker = "ExitBootService";
                    intentSingleWorker.setAction(exitSingleWorker);
                    intentSingleWorker.setData(Uri.parse(exitSingleWorker));
// TODO: 30.06.2025 EXIT Запускаем Саму Службу Single Workmanager
                    getlocalBinderBootSerice.getService().startingServiceSingleWorkManger(intentSingleWorker, getHiltPortJboss);

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + "  getActivityTasks "+getActivityTasks );


                }

            // TODO: 04.04.2025////

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
                                          @NotNull String    getAnalysisSingleWorkManger  ,@NotNull String getWhoLaunched) {
        try{
            LinkedHashMap<Integer,String> getHiltPortJboss=   EntryPoints.get(context, getHiltPortJbossInterface.class).getHiltPortJboss();

            Boolean isSingleWorkManagerRunning=  new FindActivityRunning(context).isWorkManagerRunning(getAnalysisSingleWorkManger);

            // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
            boolean ВыбранныйРежимСети =
                    new GetConnectivityManagerAndroid(context).сonnectivityManageruserselection();
            Intent intentSingleWorker=new Intent();


            Boolean isWorkActivityRunning=
                    new FindActivityRunning(context).
                            launchFindActivityRunning("com.dsy.dsu.CoreApp.Apps.View.BootAndAsync.MainActivityBootAndAsync");


                if(isSingleWorkManagerRunning == false && ВыбранныйРежимСети && isWorkActivityRunning==false ) {
                    // TODO: 30.06.2025
                            intentSingleWorker.setAction(getWhoLaunched);
                            intentSingleWorker.setData(Uri.parse(getWhoLaunched));
// TODO: 06.06.2025  запускам public work managers
                            getlocalBinderBootSerice.getService().startingServicePublicWorkManger(intentSingleWorker, getHiltPortJboss);



            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " PUBLIC PUBLIC PUBLIC isSingleWorkManagerRunning " +isSingleWorkManagerRunning     );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


    }
