package com.dsy.dsu.BusinessLogicForApps.WorkManagers.binesslogic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.WorkManagers.MyWork_Async_Public;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class CreatePublicWorkManager {
    Context context;
    private    String ИмяСлужбыСинхронизации="WorkManager Synchronizasiy_Data";
    public CreatePublicWorkManager(Context context) {
        this.context =context;
    }
    // TODO: 08.10.2023 вызов из БроадКста для Синхрониазции ТОлько


    @SuppressLint("NewApi")
    public void getcreatePublicWorkManager( @NonNull String getWhoLaunched) {

        try{
            Integer PublicId =  new GetttingPublicID().getttingPublicID(context);

            Data myDataPublicWorker = new Data.Builder()
                    .putInt("ПубличныйID", PublicId)
                    .putBoolean("StartPublicWorker", true)
                    .putString("getWhoLaunched",   getWhoLaunched)
                    .build();
            Constraints constraintsСинхронизация= new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(false)
                    .setRequiresStorageNotLow(false)
                    .build();
            PeriodicWorkRequest   periodicWorkRequestСинхронизация = new PeriodicWorkRequest.Builder(MyWork_Async_Public.class,
                    PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)//MIN_PERIODIC_FLEX_MILLIS////
                    // PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS
                    .addTag(ИмяСлужбыСинхронизации)
                    .setInputData(myDataPublicWorker)
                    .setConstraints(constraintsСинхронизация)
                    .build();


            List<WorkInfo> workInfo = WorkManager.getInstance(context).getWorkInfosByTag(ИмяСлужбыСинхронизации).get();

        Integer RunWorkInfo=  Optional.ofNullable(workInfo).stream().mapToInt(e->e.size()).findAny().orElse(0);


            if (RunWorkInfo==0) {
                // TODO: 20.01.2025  ЗАпускаем Work Manger
                WorkManager.getInstance(context).enqueueUniquePeriodicWork(ИмяСлужбыСинхронизации,
                        ExistingPeriodicWorkPolicy.UPDATE, periodicWorkRequestСинхронизация);
                // TODO: 26.07.2024
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + "\n" + " workInfo.get(0).getState() " +RunWorkInfo);
            }else {

                // TODO: 20.01.2025  ЗАпускаем Work Manger
                WorkManager.getInstance(context).enqueueUniquePeriodicWork(ИмяСлужбыСинхронизации,
                        ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequestСинхронизация);
                // TODO: 26.07.2024
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + "\n" + " workInfo.get(0).getState() " +RunWorkInfo);
            }

                Log.d(context.getClass().getName(), "\n"
                        + " время: " + new Date()+"\n+" +
                        " Класс в процессе... " +  this.getClass().getName()+"\n"+
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+ " callbackRunnable "
                        + "\n" + " workInfo.get(0).getState() " +RunWorkInfo);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context.getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

}
