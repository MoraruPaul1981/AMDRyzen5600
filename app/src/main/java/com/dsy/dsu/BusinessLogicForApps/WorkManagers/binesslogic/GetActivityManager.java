package com.dsy.dsu.BusinessLogicForApps.WorkManagers.binesslogic;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.util.List;

public class GetActivityManager {


    Context context;

    public GetActivityManager(Context context) {
        this.context = context;
    }

    public Boolean getActivityTasks(){
        Boolean getActivityTasks=false;
        try{
            ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
            //ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
            if (am!=null) {
                List<ActivityManager.AppTask> coTaskActivity = am.getAppTasks();
                if(coTaskActivity.get(0).getTaskInfo().isRunning){
                    getActivityTasks=true;
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " getActivityTasks " +getActivityTasks );
                }
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                + " getActivityTasks " +getActivityTasks );

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  getActivityTasks;

    }



}
