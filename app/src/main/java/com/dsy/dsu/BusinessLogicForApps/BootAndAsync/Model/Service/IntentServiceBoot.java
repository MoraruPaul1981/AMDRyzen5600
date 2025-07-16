package com.dsy.dsu.BusinessLogicForApps.BootAndAsync.Model.Service;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.ServiceCompat;


import com.dsy.dsu.BusinessLogicForApps.BootAndAsync.Model.Service.bl_service_boot.BinessLogicIntentServiceBoot;
import com.dsy.dsu.BusinessLogicForApps.AnalysisUserAuthenticated.GetAnalysisUserAuthenticated;


import com.dsy.dsu.BusinessLogicForApps.GetPublicID.QualifierPublicID;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.module.ModulePortJboss;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossHilt.intarfaces.QualifierPortJboss;

import com.dsy.dsu.BusinessLogicForApps.Hilt.getSSLSocketFactory2.QualifiergetsslSocketFactory2;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Random;

import javax.inject.Inject;
import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.core.Maybe;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 *
 *
 */

@AndroidEntryPoint
public class IntentServiceBoot extends IntentService {
    // TODO: 03.03.2025
    public  LocalBinderBootSerice getlocalBinderBootSerice = new  LocalBinderBootSerice();
    @Inject
    BinessLogicIntentServiceBoot binessLogicIntentServiceBoot;
    @Inject
    @QualifiergetsslSocketFactory2
    SSLSocketFactory getsslSocketFactory2;
    @Inject
    @QualifierPublicID
    Integer getHiltPublicId;
   private Notification notification;
    @Inject
    @QualifierPortJboss
    public  LinkedHashMap<Integer,String> getHiltPortJboss;


    public IntentServiceBoot() {

        super("IntentServiceBoot");
    }


    @Override
    public ContentResolver getContentResolver() {
        return super.getContentResolver();
    }

    @SuppressLint("ForegroundServiceType")
    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 24.09.2024
        try{
            startingNotificationService();
            Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

}




    @Nullable
    @Override
    public ComponentName startForegroundService(Intent service) {
        return super.startForegroundService(service);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            // TODO: 25.12.2024
            // TODO: 10.10.2024 записываем статус службы ка в менякем статус как отработал
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
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
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        try{
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
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
    protected void onHandleIntent(Intent intent) {
        try {


           getHiltPortJboss=new ModulePortJboss().getHiltPortJboss(getApplicationContext());


            startingServiceBoot(intent,   getHiltPortJboss);



            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " intent.getAction() " +intent.getAction());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    private void getCloseService() {
        try{
            stopForeground(false);
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    private void startingNotificationService() {
        try{
            String CHANNEL_ID = this.getClass().getName();
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_ID,
                    NotificationManager.IMPORTANCE_NONE);

            ((NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            notification = new NotificationCompat.Builder(this, CHANNEL_ID).build();
            ServiceCompat.startForeground(this,new Random().nextInt(2),notification,ServiceCompat.STOP_FOREGROUND_REMOVE);
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    public class LocalBinderBootSerice extends Binder {
        public IntentServiceBoot getService() {
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            return IntentServiceBoot.this;
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        return getlocalBinderBootSerice;
    }





    // TODO: 30.03.2025 ONLY BOOT service
private      void startingServiceBoot(@NotNull Intent intent ,@NonNull  LinkedHashMap<Integer,String> getHiltPortJboss){
        try{

            String getTypeTaskForBoot=  intent.getAction();
            Maybe.fromCallable(()->{
                        Boolean UserAuthenticated=       new GetAnalysisUserAuthenticated(getApplicationContext()).analysisUserAuthenticated(240);
                        // TODO: 26.12.2024 выди запуска
                        Log.d(getApplicationContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                " UserAuthenticated " +UserAuthenticated + "  getHiltPortJboss " +getHiltPortJboss.values().toString());
                        if (UserAuthenticated) {
                            return UserAuthenticated;
                        } else {return null;
                        }
                    }).doOnSuccess(s->{
                        // TODO: 31.03.2025
                        switch (getTypeTaskForBoot.trim()) {
                            // TODO: 26.12.2024 Синхрониазция
                            case "lanchAsync":
                                // TODO: 19.01.2024  запуск класса бизнес логики службы Синхроиазции и Обновление ПО
                                binessLogicIntentServiceBoot.lanchAsync(getApplicationContext());
                                Log.d(getApplicationContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " intent.getAction() " + intent.getAction());
                                break;
                            // TODO: 26.12.2024 Только Обновление ПО
                            case "lanchUpdatePO":
                                // TODO: 19.01.2024  запуск класса бизнес логики службы Синхроиазции и Обновление ПО
                                binessLogicIntentServiceBoot.lanchUpdatePO( getHiltPortJboss,getApplicationContext());
                                Log.d(getApplicationContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                        " intent.getAction() " +intent.getAction());
                                break;

                            // TODO: 26.12.2024 EXIT
                            case "ExitBootService":
                                // TODO: 19.01.2024  запуск класса бизнес логики службы Синхроиазции и Обновление ПО
                                binessLogicIntentServiceBoot.getDontNetwork(getApplicationContext());
                                // TODO: 31.03.2025 нет логина  и пароля переводим программу на Активити Password
                                binessLogicIntentServiceBoot.afterUpdatePOandAsynclaunchActivity(getApplicationContext());
                                Log.d(getApplicationContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " intent.getAction() " + intent.getAction());
                                break;
                        }


                        Log.d(getApplicationContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " getHiltPortJboss " + getHiltPortJboss+
                                " getTypeTaskForBoot " +getTypeTaskForBoot);

                    }).doOnError(e->{
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }).blockingSubscribe();
            Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " getHiltPortJboss " +getHiltPortJboss);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }











    // TODO: 30.03.2025 ONLY WORK Manager 
    public       void startingServiceSingleWorkManger(@NotNull Intent intent ,@NonNull  LinkedHashMap<Integer,String> getHiltPortJboss) {
        try {
            String getTypeTaskWorkManager = intent.getAction();
            Maybe.fromCallable(()->{
                // TODO: 04.04.2025
                Boolean UserAuthenticated=       new GetAnalysisUserAuthenticated(getApplicationContext()).analysisUserAuthenticated(240);
                // TODO: 26.12.2024 выди запуска
                        Log.d(getApplicationContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                " UserAuthenticated " +UserAuthenticated + "  getTypeTaskWorkManager " +getTypeTaskWorkManager);
                if (UserAuthenticated) {
                    return UserAuthenticated;
                } else {return null;
                        }
            }).doOnSuccess(s->{
                        // TODO: 31.03.2025
                        switch (getTypeTaskWorkManager.trim()) {
                            // TODO: 26.12.2024 И Обновление и Синхронизация
                            case "lanchUpdatePOAndAsync" :
                                // TODO: 19.01.2024  запуск класса бизнес логики службы Синхроиазции и Обновление ПО
                                binessLogicIntentServiceBoot.lanchUpdatePOAndAsync(getHiltPortJboss,getApplicationContext());
                                // TODO: 31.03.2025
                                Log.d(getApplicationContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                        " intent.getAction() " +intent.getAction());
                                break;
                            // TODO: 26.12.2024 EXIT
                            case "ExitBootService":
                                // TODO: 19.01.2024  запуск класса бизнес логики службы Синхроиазции и Обновление ПО
                                binessLogicIntentServiceBoot.getDontNetwork(getApplicationContext());
                                // TODO: 31.03.2025 нет логина  и пароля переводим программу на Активити Password
                                binessLogicIntentServiceBoot.afterUpdatePOandAsynclaunchActivity(getApplicationContext());
                                Log.d(getApplicationContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " intent.getAction() " + intent.getAction());
                                break;
                        }


                        Log.d(getApplicationContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " getHiltPortJboss " + getHiltPortJboss+
                                " getTypeTaskWorkManager " +getTypeTaskWorkManager);

                    }).doOnComplete(()->{
                        switch (getTypeTaskWorkManager.trim()) {
                            // TODO: 31.03.2025
                            // TODO: 26.12.2024 И Обновление и Синхронизация
                            case "lanchUpdatePOAndAsync" :
                            case "ExitBootService" :
                                // TODO: 31.03.2025 нет логина  и пароля переводим программу на Активити Password
                                binessLogicIntentServiceBoot.afterUpdatePOandAsynclaunchActivity(getApplicationContext());

                                break;
                        }

            }).doOnError(e->{
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    })
                    .blockingSubscribe();
                // TODO: 31.03.2025  КОгда первый запук и нет не имени не пароля
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " getHiltPortJboss " + getHiltPortJboss);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 31.03.2025 PUBLIC WORK MAMAGER
    // TODO: 30.03.2025 ONLY WORK Manager
    public       void startingServicePublicWorkManger(@NotNull Intent intent ,@NonNull  LinkedHashMap<Integer,String> getHiltPortJboss) {
        try {
            String getTypeTaskWorkManager = intent.getAction();
            Maybe.fromCallable(()->{
                        Boolean UserAuthenticated=       new GetAnalysisUserAuthenticated(getApplicationContext()).analysisUserAuthenticated(240);
                        // TODO: 26.12.2024 выди запуска
                        Log.d(getApplicationContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                " UserAuthenticated " +UserAuthenticated + "  getTypeTaskWorkManager " +getTypeTaskWorkManager);
                        if (UserAuthenticated) {
                            return UserAuthenticated;
                        } else {return null;
                        }
                    }).doOnSuccess(s->{
                        // TODO: 31.03.2025
                        switch (getTypeTaskWorkManager.trim()) {
                            // TODO: 26.12.2024 Синхрониазция
                            case "lanchAsync":
                                // TODO: 19.01.2024  запуск класса бизнес логики службы Синхроиазции и Обновление ПО
                                binessLogicIntentServiceBoot.lanchAsync(getApplicationContext());
                                Log.d(getApplicationContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " intent.getAction() " + intent.getAction());
                                break;
                            // TODO: 26.12.2024 EXIT
                            case "ExitBootService":
                                // TODO: 19.01.2024  запуск класса бизнес логики службы Синхроиазции и Обновление ПО
                                binessLogicIntentServiceBoot.getDontNetwork(getApplicationContext());
                                // TODO: 31.03.2025 нет логина  и пароля переводим программу на Активити Password
                                binessLogicIntentServiceBoot.afterUpdatePOandAsynclaunchActivity(getApplicationContext());
                                Log.d(getApplicationContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " intent.getAction() " + intent.getAction());
                                break;
                        }


                        Log.d(getApplicationContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " getHiltPortJboss " + getHiltPortJboss+
                                " getTypeTaskWorkManager " +getTypeTaskWorkManager);

                    }).doOnComplete(()->{
                        switch (getTypeTaskWorkManager.trim()) {
                            // TODO: 31.03.2025
                            // TODO: 26.12.2024 И Обновление и Синхронизация
                            case "lanchUpdatePOAndAsync" :
                                // TODO: 31.03.2025 нет логина  и пароля переводим программу на Активити Password
                                binessLogicIntentServiceBoot.afterUpdatePOandAsynclaunchActivity(getApplicationContext());

                                break;
                        }

                    }).doOnError(e->{
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }) .blockingSubscribe();
            // TODO: 31.03.2025  КОгда первый запук и нет не имени не пароля
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " getHiltPortJboss " + getHiltPortJboss);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    // TODO: 10.10.2024 end class
}
