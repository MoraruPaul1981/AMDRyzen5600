package com.dsy.dsu.BusinessLogicForApps.BroadcastRecievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.WorkManagers.binesslogic.CreatePublicWorkManager;


import java.util.Date;


public class BroadCastMyReceiverPublic extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try{
        // TODO: This method is called when the BroadcastReceiver is receiving

            Log.d(context.getClass().getName(), "\n"
                    + " BroadCastMyReceiverPublic Starting.... sous .... bremy: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " intent.getAction() " +intent.getAction());
            PendingResult pendingResult = goAsync();

          //  Toast.makeText(context, "background sous-avtodor !!! ", Toast.LENGTH_LONG).show();

/*
         Integer PublicIDWorkMangerPubluc= EntryPoints.get(context, HiltInterfacesPublicID.class).getPublicIDAllApp();*/


            // Toast.makeText(context, "ООО Союз-Автодор work Background !!! "+"\n"+new Date().toLocaleString().toString(), Toast.LENGTH_LONG).show();

             // TODO: 14.12.2023 REPLACE
             new CreatePublicWorkManager(context).getcreatePublicWorkManager(context, "BootService" );


            Log.d(context.getClass().getName(), "\n"
                    + " Ending.... время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " intent.getAction() " +intent.getAction() +"background sous-avtodor !!! "
                    +new Date().toLocaleString() + " pendingResult.getResultData() "+pendingResult.getResultData());
            // TODO: 07.10.2023
            pendingResult.finish();

            Log.d(context.getClass().getName(), "\n"
                    + " END ...время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
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