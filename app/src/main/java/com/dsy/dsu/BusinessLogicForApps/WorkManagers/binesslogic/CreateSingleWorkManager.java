package com.dsy.dsu.BusinessLogicForApps.WorkManagers.binesslogic;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;


import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.BusinessLogicForApps.WorkManagers.MyWork_AsyncSingle;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.util.Date;

public class CreateSingleWorkManager {
    Context context;
    private  String ИмяСлужбыSingleWorkManger ="WorkManager Synchronizasiy_Data Disposable";
    public CreateSingleWorkManager(Context context) {
        this.context =context;
    }
    
    public void getcreateSingleWorkManager( @NonNull String getWhoLaunched) {

        try{
            // TODO: 08.10.2023
            Integer PublicId =  new GetttingPublicID().getttingPublicID(context);

            Data myDataSingleWorker = new Data.Builder()
                    .putInt("ПубличныйID", PublicId)
                    .putBoolean("StartSingleWorker", true)
                    .putString("getWhoLaunched",   getWhoLaunched)
                    .build();

            Constraints constraintsЗапускСинхОдноразоваяСлужба = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                    .setRequiresBatteryNotLow(false)
                    .setRequiresStorageNotLow(false)
                    .build();
            OneTimeWorkRequest oneTimeWorkRequest =
                    new OneTimeWorkRequest.Builder(MyWork_AsyncSingle.class)
                            .setConstraints(constraintsЗапускСинхОдноразоваяСлужба)
                            .setInputData(myDataSingleWorker)
                            .addTag(ИмяСлужбыSingleWorkManger)
                            .build();//      .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)

            WorkManager.getInstance(context).enqueueUniqueWork(ИмяСлужбыSingleWorkManger,
                    ExistingWorkPolicy.KEEP , oneTimeWorkRequest);


            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context.getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 08.10.2023
}
