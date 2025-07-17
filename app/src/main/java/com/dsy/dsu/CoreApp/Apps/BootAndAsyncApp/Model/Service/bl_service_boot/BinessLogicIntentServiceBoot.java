package com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.Service.bl_service_boot;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;


import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.EventsBus.MessageEvensBusAppAfterSyncing;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.EventsBus.MessageEvensBusNetworkStatuses;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.EventsBus.MessageEvensBusUpdatePO;
import com.dsy.dsu.BusinessLogicForApps.AnalysisUserAuthenticated.GetAnalysisUserAuthenticated;

import com.dsy.dsu.BusinessLogicForApps.GetPingServers.GetPingServerJbossNotActivity;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForRemoteAsyncBinary;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceUpdatesPO;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;
import javax.net.ssl.SSLSocketFactory;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;

@Module
@InstallIn(SingletonComponent.class)
@SuppressLint("Range")
public class BinessLogicIntentServiceBoot {

    public   ServiceForRemoteAsyncBinary.LocalBinderAsync localBinderAsync;//TODO нова\
    public    ServiceUpdatesPO.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO нова
    private  Context context;

 Subject<  ServiceUpdatesPO.localBinderОбновлениеПО> publishSubjectlocalBinderОбновлениеПО= ReplaySubject.create();
 Subject< ServiceForRemoteAsyncBinary.LocalBinderAsync> publishSubjectLocalBinderAsync= ReplaySubject.create();


    public  @Inject BinessLogicIntentServiceBoot(@ApplicationContext Context contextBounding) {
        //TODO сомо имя json
        try{
// TODO: 30.04.2025  слуушатели на всякий случай
            publishSubjectlocalBinderОбновлениеПО.subscribe();
            // TODO: 30.04.2025
            publishSubjectLocalBinderAsync.subscribe();

            // TODO: 28.04.2025
            this.context=contextBounding;
        // TODO: 14.08.2023 методЗапукска Синхрониазйиии
           МетодБиндингаОбновлениеПО(  contextBounding);
            // TODO: 14.08.2023 методЗапукска Синхрониазйиии
            МетодБиндингаRemoteAsync(contextBounding);
        Log.d(contextBounding.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(contextBounding).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }





    public void lanchUpdatePO(@NonNull LinkedHashMap<Integer,String> getHiltPortJboss,
                              @NonNull Context context) {
        try {
            // TODO: 14.08.2023 вызов кода ПОльзовательский

            // TODO: 14.08.2023 Проверяем версси ПО с серврной и локальной
        Maybe maybelanchUpdatePO=    Maybe.fromCallable(()->{
                        // TODO: 22.01.2024 true запускаем Анализ По
                        Integer    versionServicePO=        getVersionServicePO(getHiltPortJboss,context);
                        Integer    versionLocalPO=        getVersionLocalPO(getHiltPortJboss,context);
                        Log.d(context.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + "\n"+"versionServicePO"+versionServicePO + " versionLocalPO "+versionLocalPO);
                        if (versionServicePO>versionLocalPO) {
                            return versionServicePO;
                        } else {
                            return null;
                        }
                    }).doOnSuccess(versionServicePO->{
                        // TODO: 30.03.2025 Запускаем ПО
                        // TODO: 22.01.2024 запускаю обновление ПО
                        startingUpdatePOComplete(versionServicePO,context );

                        // TODO: 03.10.2023
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                    }).doOnComplete(()->{
                        // TODO: 30.03.2025 Сообщаем ЧТо версия Уже есть
            Integer    versionServicePO=        getVersionServicePO(getHiltPortJboss,context);

                        getLastVersionUpdatePO(context,versionServicePO);

                        Log.d(context.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");

                    });

            maybelanchUpdatePO .blockingSubscribe();


            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @SuppressLint("SuspiciousIndentation")
    public Long lanchAsync(@NonNull Context context) {
        Long  getcompleteAsync=0l;
        try {
            // TODO: 14.08.2023 вызов кода ПОльзовательский
               getcompleteAsync =   completeAsync(   context);

            // TODO: 03.10.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"   + " getcompleteAsync " +getcompleteAsync);

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return   getcompleteAsync;
    }

    public void lanchUpdatePOAndAsync(@NonNull LinkedHashMap<Integer,String> getHiltPortJboss,
                                      @NonNull Context context) {
        try {
            // TODO: 14.08.2023 Проверяем версси ПО с серврной и локальной
       Maybe maybelanchUpdatePOAndAsync=    Maybe.fromCallable(()->{
               // TODO: 22.01.2024 true запускаем Анализ По
               Integer    versionServicePO=        getVersionServicePO(getHiltPortJboss,context);
               Integer    versionLocalPO=        getVersionLocalPO(getHiltPortJboss,context);
               Log.d(context.getClass().getName(), "\n"
                       + " время: " + new Date() + "\n+" +
                       " Класс в процессе... " + this.getClass().getName() + "\n" +
                       " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                       + "\n"+"versionServicePO"+versionServicePO + " versionLocalPO "+versionLocalPO);
               if (versionServicePO>versionLocalPO) {
                   return versionServicePO;
               } else {
                   return null;
               }
            }).doOnSuccess(versionServicePO->{
               // TODO: 30.03.2025 Запускаем ПО
               // TODO: 22.01.2024 запускаю обновление ПО
               startingUpdatePOComplete(versionServicePO,context );

               // TODO: 03.10.2023
               Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
            }).doOnComplete(()->{
               // TODO: 30.03.2025 Запускаем СИНХРОНИЗАЦИЮ
               Long getcompleteAsync =   lanchAsync(   context  );
                   // TODO: 31.03.2025

                   // TODO: 31.03.2025 нет логина  и пароля переводим программу на Активити Password
                   afterUpdatePOandAsynclaunchActivity(context);

               // TODO: 03.10.2023
               Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " getcompleteAsync " +getcompleteAsync);

               Log.d(context.getClass().getName(), "\n"
                       + " время: " + new Date() + "\n+" +
                       " Класс в процессе... " + this.getClass().getName() + "\n" +
                       " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");

            });
            // TODO: 31.03.2025
            maybelanchUpdatePOAndAsync.blockingSubscribe();


            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");//
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    // TODO: 31.03.2025  после обновлени и /или синхрониазци запцскаем нужную активти
    public void afterUpdatePOandAsynclaunchActivity(@NonNull Context context) {
        try {
     Boolean UserAuthenticated=       new GetAnalysisUserAuthenticated(context).analysisUserAuthenticated(240);
          Intent intentLaunchMainAppAfterSyncing=new Intent();
            intentLaunchMainAppAfterSyncing.setAction("LaunchMainAppAfterSyncing");
            Bundle setbundleLaunchMainAppAfterSyncing=new Bundle();
            setbundleLaunchMainAppAfterSyncing.putBoolean("launchMainAppAfterSyncing",  UserAuthenticated);///"В процесс"
            intentLaunchMainAppAfterSyncing.putExtras(setbundleLaunchMainAppAfterSyncing);
// TODO: 11.04.2025  Запускаем Основное Приложение после как прошда Синхронизация
            EventBus.getDefault().post(new MessageEvensBusAppAfterSyncing(intentLaunchMainAppAfterSyncing));

            // TODO: 28.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " userHasReceivedAccesstoDashBordorPasswordisNeeded " +UserAuthenticated);
    } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }

    }


    public void getDontNetwork(@NonNull Context context) {
        try{
            Intent intentComunicationsBusAyns=new Intent();
            Bundle bundleComunications=new Bundle();


                intentComunicationsBusAyns.setAction("EventBusAnsyc");
                bundleComunications.putString("Статус",  "ServerJbosOff");///"В процесс"
                intentComunicationsBusAyns.putExtras(bundleComunications);

                EventBus.getDefault().post(new MessageEvensBusNetworkStatuses(intentComunicationsBusAyns));
                // TODO: 22.01.2024 просто сеть рабоатет  переделаем програсс бару

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " localBinderAsync "+ "\n" );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    public void getLastVersionUpdatePO(@NonNull Context context,@NonNull  Integer    СервернаяВерсия) {
        try{
            Intent intentComunicationsBusAyns=new Intent();
            Bundle bundleComunications=new Bundle();


            intentComunicationsBusAyns.setAction("EventBusAnsyc");
            bundleComunications.putString("Статус",  "LastVersionUpdatePO");///"В процесс"
            bundleComunications.putInt("СервернаяВерсия",  СервернаяВерсия);///"В процесс"
            intentComunicationsBusAyns.putExtras(bundleComunications);

            EventBus.getDefault().post(new MessageEvensBusNetworkStatuses(intentComunicationsBusAyns));
            // TODO: 22.01.2024 просто сеть рабоатет  переделаем програсс бару

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " localBinderAsync "+ "\n" );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }









































        private void startingUpdatePOComplete(@NonNull Integer СервернаяВерсия, @NonNull Context context) {
                     try{
                         Intent intentComunicationsUpdatePO=new Intent();
                         Bundle bundleComunications=new Bundle();

                         intentComunicationsUpdatePO.setAction("EventBusUpdatePO");
                         bundleComunications.putString("Статус",  "UpdateProcessorPO");///"В процесс"
                         bundleComunications.putInt("СервернаяВерсия",  СервернаяВерсия);///"В процесс"
                         intentComunicationsUpdatePO.putExtras(bundleComunications);

                         EventBus.getDefault().post(new MessageEvensBusUpdatePO(intentComunicationsUpdatePO));

                         Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                 " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                 " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                 " localBinderAsync "+ "\n" );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
        }









    // TODO: 19.01.2024   /////// МЕТОД КОГДА ЗАХОДИЛ ПОСЛЬДНИЙ РАЗ ПОЛЬЗОВАТЛЬ




    ///////todo ФИНАЛЬНЫЙ МЕТОД КТО ВХОДИЛ ДО 7 ДНЕЙ ИЛИ ПОСЫЛАЕМ НА АУНТИФИКАЦИЮ
    Integer getVersionServicePO(@NonNull LinkedHashMap<Integer,String> getHiltPortJboss, @NonNull Context context) {
        AtomicInteger СервернаяВерсия=new AtomicInteger(0);
        try {
            if (localBinderОбновлениеПО!=null) {
                СервернаяВерсия.getAndSet(localBinderОбновлениеПО.getService().МетодГлавныйОбновленияПОДоAsync(true,
                        context,getHiltPortJboss ));

                Log.d(context.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + "СервернаяВерсия " +СервернаяВерсия);
            } else {



            publishSubjectlocalBinderОбновлениеПО.doOnNext(new Consumer<  ServiceUpdatesPO.localBinderОбновлениеПО>() {
                @Override
                public void accept(  ServiceUpdatesPO.localBinderОбновлениеПО ser) throws Throwable {
                    // TODO: 29.04.2025
                    СервернаяВерсия.getAndSet( ser.getService().МетодГлавныйОбновленияПОДоAsync(true,
                            context,getHiltPortJboss ));


                    Log.d(context.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+ "\n" + "СервернаяВерсия " +СервернаяВерсия);

                }
            }).subscribe();
            }


            Log.i(this.getClass().getName(), " Атоманически установкаОбновление ПО " +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
            Log.i(this.getClass().getName(), "R.id.item_async_updatepo  "
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString() +
                    "СервернаяВерсия " + СервернаяВерсия);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  СервернаяВерсия.get();
    }

    Integer getVersionLocalPO(@NonNull LinkedHashMap<Integer,String> getHiltPortJboss, @NonNull Context context) {
        Integer ЛокальнаяВерсияПО=0;
        try {
            // TODO: 24.09.2024   Локальная Версия Программернр Обеспечения табель
            PackageInfo    pInfo = context. getPackageManager().getPackageInfo(context. getPackageName(), 0);
            String version = pInfo.versionName;//Version Name
              ЛокальнаяВерсияПО = pInfo.versionCode;
            Log.i(this.getClass().getName(), "R.id.item_async_updatepo  "
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString() +
                    "СервернаяВерсия " + ЛокальнаяВерсияПО);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  ЛокальнаяВерсияПО;
    }




    Long completeAsync( @NonNull Context context  ){
        // TODO: 28.03.2025
        AtomicLong completeAsync=new AtomicLong(0l);
        try{
            // TODO: 03.10.2023

            if (localBinderAsync!=null) {
                completeAsync.getAndSet(localBinderAsync.getService().metodStartingSync(context)) ;

                Log.d(context.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+ "\n" + "completeAsync " +completeAsync);
            } else {
                // TODO: 30.04.2025

                publishSubjectLocalBinderAsync.doOnNext(new Consumer<   ServiceForRemoteAsyncBinary.LocalBinderAsync>() {
                    @Override
                    public void accept(  ServiceForRemoteAsyncBinary.LocalBinderAsync asy) throws Throwable {
                        // TODO: 29.04.2025

                        completeAsync.getAndSet(  localBinderAsync.getService().metodStartingSync(context));

                        Log.d(context.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+ "\n" + "completeAsync " +completeAsync);

                    }
                }).subscribe();

            }



            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " completeAsync " +completeAsync );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  completeAsync.get();
    }




























    @SuppressLint("SuspiciousIndentation")
    public Boolean МетодПингаКСереруЗапущенЛиСерерИлиНет( @NonNull SSLSocketFactory getsslSocketFactory2, @NonNull Context context) {
        Boolean СтатусРаботыСервера =false;
        try {
            // TODO: 16.12.2021 НЕПОСРЕДСТВЕННЫЙ ПИНГ СИСТЕНМ ИНТРЕНАТ НА НАЛИЧЕНИ СВАЗИ С БАЗОЙ SQL SERVER
              СтатусРаботыСервера = new GetPingServerJbossNotActivity(context). pingServerJbossSuccessfulOrNot(getsslSocketFactory2);

   Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  "  СтатусРаботыСервера " +СтатусРаботыСервера);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return СтатусРаботыСервера;
    }


























































    @SuppressLint("NewApi")
    public void МетодБиндингаRemoteAsync(  @NonNull  Context contextBounding  ) {
        try {
            // TODO: 28.04.2023  запускаем Гланвную Синхрониазцию
                Intent intentAsync = new Intent(contextBounding, ServiceForRemoteAsyncBinary.class);
                intentAsync.setAction("com.StartingAsyncMainBackgroud");
                ServiceConnection connectionAsync = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            if (service.isBinderAlive()) {
                                // TODO: 29.09.2023
                                 localBinderAsync = (ServiceForRemoteAsyncBinary.LocalBinderAsync) service;

                                // TODO: 30.04.2025

                                publishSubjectLocalBinderAsync.onNext(localBinderAsync);
                                // TODO: 30.04.2025
                                publishSubjectLocalBinderAsync.onComplete();

                                // TODO: 28.04.2025
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                        + " localBinderAsync " +localBinderAsync);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(contextBounding).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }


                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        localBinderAsync = null;
                        Log.d(contextBounding.getClass().getName().toString(), "\n"
                                + "onServiceConnected  одноразовая  messengerActivity  ");
                    }};

            if (localBinderAsync==null) {
                //contextBounding. bindService(intentAsync ,connectionAsync ,Context.BIND_AUTO_CREATE);
                contextBounding. bindService(intentAsync,Context.BIND_AUTO_CREATE , Executors.newSingleThreadExecutor(),connectionAsync );
            }


            // TODO: 28.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(contextBounding).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }

    }





    @SuppressLint("NewApi")
    public void МетодБиндингаОбновлениеПО( @NonNull  Context contextBounding ) {
        try {
            ServiceConnection   connectionОбновлениеПО = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                        if (service.isBinderAlive()) {

                            // TODO: 28.07.2023  Update
                            localBinderОбновлениеПО = (ServiceUpdatesPO.localBinderОбновлениеПО) service;
                            // TODO: 30.04.2025
                            publishSubjectlocalBinderОбновлениеПО.onNext(localBinderОбновлениеПО);
                            publishSubjectlocalBinderОбновлениеПО.onComplete();

                            // TODO: 28.04.2025
                            // TODO: 25.03.2023
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

                        }

                        Log.d(contextBounding.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                                + "localBinderОбновлениеПО " + localBinderОбновлениеПО);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(contextBounding).recordnewerror(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }


                @Override
                public void onServiceDisconnected(ComponentName name) {
                    try {
                        localBinderОбновлениеПО = null;
                        Log.i(contextBounding.getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(contextBounding).recordnewerror(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            };
            Intent intentЗапускСлужбыОбновлениеПО = new Intent(contextBounding, ServiceUpdatesPO.class);
            intentЗапускСлужбыОбновлениеПО.setAction("com.ServiceUpdatesPO");

            if (localBinderОбновлениеПО==null) {
                //contextBounding. bindService(intentЗапускСлужбыОбновлениеПО,connectionОбновлениеПО,Context.BIND_AUTO_CREATE  );
                contextBounding. bindService(intentЗапускСлужбыОбновлениеПО,Context.BIND_AUTO_CREATE , Executors.newSingleThreadExecutor(),connectionОбновлениеПО );
            }
            // TODO: 28.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(contextBounding).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }

    }


    // TODO: 19.01.2024 END CLASS
}