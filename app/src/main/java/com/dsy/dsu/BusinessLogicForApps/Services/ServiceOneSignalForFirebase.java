package com.dsy.dsu.BusinessLogicForApps.Services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.dsy.dsu.BusinessLogicForApps.GetPublicID.QualifierPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.FirebaseAndOneSignal.OneSignal.registOnesignal.ClassOneSingnalGenerator;
import com.dsy.dsu.BusinessLogicForApps.Hilt.OneSignalHils.QualifierOneSignal;


import java.util.Date;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */

@AndroidEntryPoint
public class ServiceOneSignalForFirebase extends IntentService {



    @Inject
    @QualifierOneSignal
    String  KeyHiltOneSignal ;





    @Inject
    @QualifierPublicID
    Integer getHiltPublicId;

    public ServiceOneSignalForFirebase() {
        super("ServiceOneSignalForFirebase");
    }





    @Override
    protected void onHandleIntent(Intent intent) {
        try{
            if (intent.getAction().equalsIgnoreCase("com.registariionesignal.net")) {

                new ClassOneSingnalGenerator(getApplicationContext()).getGetRegistaziyNewKeyForOnoSignal(KeyHiltOneSignal);

            }
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
    }

}






