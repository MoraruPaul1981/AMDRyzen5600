package com.dsy.dsu.BusinessLogicForApps.BroadcastRecievers.Bl;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;


import com.dsy.dsu.BusinessLogicForApps.WorkManagers.binesslogic.CreatePublicWorkManager;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.util.Date;

public class getStartingWorkmanagerPublic {


    public void metodRegistraBroadCastFroPublicAsyns(@NonNull Context context) {
        try {


            // TODO: 14.12.2023 REPLACE
           new CreatePublicWorkManager(context).getcreatePublicWorkManager(context , "lanchOnlyAsync");

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context.getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }

}
