package com.dsy.dsu.BusinessLogicForApps.WorkManagers.binesslogic;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class FindRunnigServiceBeforeWorkManager {


    Context context;

    public FindRunnigServiceBeforeWorkManager(Context context) {
        this.context = context;
    }

    public boolean isMyServiceRunningWithNameActivity( ) {
        // TODO: 14.01.2025
        Boolean isMyActivityRunning=false;
        try{
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {



            if (service.service.getClassName().equalsIgnoreCase("MainActivityBootAndAsync")) {
                // TODO: 10.10.2024

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " service.service.getClassName() " + service.service.getClassName()+" service.activeSince " +service.activeSince + " service.foreground " +service.foreground );

                return true;
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "service.service.getClassName()" + service.service.getClassName());
        }
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
        return isMyActivityRunning;

    }
    @SuppressLint({"SuspiciousIndentation", "NewApi"})
    public boolean isGetMyActivityRunning() {
        // TODO: 14.01.2025
         Boolean  isMyActivityRunning = false;
        try{
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> tasks = null;
            if (activityManager!=null) {
                tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
                // TODO: 14.01.2025
                List<ActivityManager.RunningTaskInfo> services = activityManager.getRunningTasks(Integer.MAX_VALUE);
                isMyActivityRunning=    tasks.stream().anyMatch(new Predicate<ActivityManager.RunningTaskInfo>() {
                    @Override
                    public boolean test(ActivityManager.RunningTaskInfo runningTaskInfo) {
                        // TODO: 14.01.2025
                        Boolean  isMyActivityRunning = false;
                        ComponentName currentPackageName =   runningTaskInfo.topActivity;

                        if(currentPackageName.getClassName().length()>20){
                            if (runningTaskInfo.isRunning) {
                                isMyActivityRunning =true;
                            }
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" isMyActivityRunning "+ isMyActivityRunning);
                        }

                        return isMyActivityRunning;
                    }
                });
            }
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
        return isMyActivityRunning;
    }




    @SuppressLint({"SuspiciousIndentation", "NewApi"})
    public boolean isWorkManagerRunning(@NotNull String getNameWorkMager) {
        // TODO: 14.01.2025
        Boolean  isWorkManagerRunning = false;
        try{
            if (! WorkManager.getInstance(context).getWorkInfosByTag(getNameWorkMager).get().isEmpty()) {
                WorkInfo workInfo = WorkManager.getInstance(context).getWorkInfosByTag(getNameWorkMager).get().get(0);
                if (workInfo.getState().compareTo(WorkInfo.State.RUNNING) == 0) {

// TODO: 18.03.2025
                    isWorkManagerRunning = true;
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            " isWorkManagerRunning " +isWorkManagerRunning);
                }
            }
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
        return isWorkManagerRunning;
    }






}
