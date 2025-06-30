package com.dsy.dsu.BusinessLogicForApps.Services;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.sous.backasync.launch.ModuleQuety;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class ServiceForChatten extends IntentService {
    // TODO: 22.05.2025
    public LocalBinderДляЧата binderЧАТ = new LocalBinderДляЧата();
    // Random number generator
    private Context context;
    private CoreBinessLogics modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного ;
    private RecordNewErros recordNewErros;
    public ServiceForChatten() {
        super("ServiceForChatten");
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinderДляЧата extends Binder {
        public ServiceForChatten getService() {
            // Return this instance of LocalService so clients can call public methods
            return ServiceForChatten.this;
        }
    }


    @Override
    public IBinder onBind(@NonNull Intent intent) {
        Log.i(getApplicationContext().getClass().getName(), "     public IBinder onBind(@NonNull Intent intent) { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());//todo super.onBind(intent)
        return  binderЧАТ ;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("     public static void enqueueWork(Context context, Intent work) {" + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
    }


   @Override
    public void onCreate() {
        super.onCreate();
       // TODO: 16.04.2025
       Log.d(context.getClass().getName(), "\n"
               + " время: " + new Date() + "\n+" +
               " Класс в процессе... " + this.getClass().getName() + "\n" +
               " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        Log.i(getApplicationContext().getClass().getName(), " public class ServiceForChatten extends JobIntentService { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
    }



    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(getApplicationContext().getClass().getName(), "    public boolean onUnbind(Intent intent) { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {

    try{

    Log.i(getApplicationContext().getClass().getName(), "  ServiceForChatten   public void onDestroy() { " + new Date()+"\n"+
            " Thread.currentThread().getName()  " +Thread.currentThread().getName());

    super.onDestroy();

    stopSelf();
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(getApplicationContext().getClass().getName(), "     public void onRebind(Intent intent) { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Log.i(getApplicationContext().getClass().getName(), "  attachBaseContext      protected void attachBaseContext(Context newBase) {  " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
        this.context=newBase;
    }

// TODO: 15.07.2022  пользовательский код
    /** method for clients */
    public Integer МетодВнутриБиндингСлужбыСменаСтатусаВЧатеПрочитаноИлиНет(@NonNull Intent intent,@NonNull Context context) {
// TODO: 18.06.2022
        Integer результатСменыСтатусаВЧатеПрочитаноИлиНет = 0;
        try{
            this.context=context;

            Bundle bundleДАнныеПришлиСЧатаДляСменыСтатуса= intent.getExtras();
            // TODO: 18.06.2022
            String ЗамоЗначениеТекущегоСообщения=   bundleДАнныеПришлиСЧатаДляСменыСтатуса.getString("ЗамоЗначениеТекущегоСообщения");

            Long РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно=
                    bundleДАнныеПришлиСЧатаДляСменыСтатуса.getLong("РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно");


            Log.d(this.getClass().getName(), "  bundleДАнныеПришлиСЧатаДляСменыСтатуса " + bundleДАнныеПришлиСЧатаДляСменыСтатуса + "" +
                    " РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно "+РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно);
            // TODO: 18.06.2022  сама смена статуса через службы биндинга

            результатСменыСтатусаВЧатеПрочитаноИлиНет =
                    МетодЗаписиСтатусаДляТекущегоПользователя(ЗамоЗначениеТекущегоСообщения ,
                            РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно,context);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        Log.i(context.getClass().getName(), "        public Boolean МетодВнутриБиндингСлужбыСменаСтатуса() {" + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName()+ " intent " +intent);
        return результатСменыСтатусаВЧатеПрочитаноИлиНет;
    }
    

    // TODO: Rename actions, choose action names that describe tasks that this
    // TODO: 18.06.2022  сама перенесенный метод для смены статуча

    @SuppressLint("SuspiciousIndentation")
    Integer МетодЗаписиСтатусаДляТекущегоПользователя(
          @NonNull String СамоЗначенияИндифкатора,
           @NonNull Long ПолученныйUUIDТекущейСтрочкиКоторуюПрочитали,
           @NonNull Context context) {

        Integer РезультатОбновленияСтатусЧатаКакПрочитанный = 0;
        Long РезультатУвеличинаяВерсияДАныхЧата = 0L;
        String ТаблицаОбработкиВнутриЧтатаПриУвеличсенииВерсииДаннвъКоглаПрочинаноСообещния = "data_chat";

        try {
         modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного = new CoreBinessLogics(context);
            recordNewErros =new RecordNewErros(context);

            // TODO: 15.07.2022

            ContentValues contentValuesОбновленниВТАблицеКакПрочитанныйМеняемСтатусЗаписисВчатеПостлеПросмотра = new ContentValues();
            contentValuesОбновленниВТАблицеКакПрочитанныйМеняемСтатусЗаписисВчатеПостлеПросмотра.put("status_write", 1);

            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
          РезультатУвеличинаяВерсияДАныхЧата =
                    new VersionCurentTable(context).upVersionCurentTable(    ТаблицаОбработкиВнутриЧтатаПриУвеличсенииВерсииДаннвъКоглаПрочинаноСообещния);
            Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияДАныхЧата  " + РезультатУвеличинаяВерсияДАныхЧата);

            contentValuesОбновленниВТАблицеКакПрочитанныйМеняемСтатусЗаписисВчатеПостлеПросмотра.put("current_table", РезультатУвеличинаяВерсияДАныхЧата);
            РезультатОбновленияСтатусЧатаКакПрочитанный = 0;
            РезультатОбновленияСтатусЧатаКакПрочитанный = modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного.
                    ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(ТаблицаОбработкиВнутриЧтатаПриУвеличсенииВерсииДаннвъКоглаПрочинаноСообещния,
                            contentValuesОбновленниВТАблицеКакПрочитанныйМеняемСтатусЗаписисВчатеПостлеПросмотра,
                            ПолученныйUUIDТекущейСтрочкиКоторуюПрочитали, СамоЗначенияИндифкатора);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатОбновленияСтатусЧатаКакПрочитанный;   // TODO: 05.07.2021 вставка новго сообщения в деве таблоицы Code_For_Chats_КодДля_Чата and DATA_Chat
    }


    // TODO: 15.07.2022  ПОЛУЧЕНИЕ ДАННЫХ ДЛЯ ЧАТА


  public    Cursor МетодГенерацияКурсораДляЧата(@NonNull Long ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата,
                                                     @NonNull Context context) {
         Cursor          КурсорДанныеДлязаписиичтнияЧата=null;
        try {
            this.context=context;

            modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного = new CoreBinessLogics(context);
            recordNewErros =new RecordNewErros(context);

            Log.i(context.getClass().getName(),
                    " ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата    " +ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата);//todo super.onBind(intent)

            if (ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата > 0) {
                // TODO: 15.05.202
                String Текущаятаблицы="data_chat";
                ModuleQuety moduleQuety=new ModuleQuety(getApplicationContext());
                КурсорДанныеДлязаписиичтнияЧата= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT   * FROM '"+Текущаятаблицы+"'  AS D "+
                        " WHERE  D.chat_uuid  ='" + ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата +"'"+
                        "                                        \"  AND   D.message IS NOT NULL    \" +\n" +
                        "                                        \" ORDER BY   D.date_update     ASC, D.id   ASC " ,null);

                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " КурсорДанныеДлязаписиичтнияЧата " +КурсорДанныеДлязаписиичтнияЧата);




                if (КурсорДанныеДлязаписиичтнияЧата != null) {
                    if (КурсорДанныеДлязаписиичтнияЧата.getCount() > 0) {
                        КурсорДанныеДлязаписиичтнияЧата.moveToFirst();
                    }

                    Log.d(this.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + " КурсорДанныеДлязаписиичтнияЧата " +КурсорДанныеДлязаписиичтнияЧата);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    context.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  КурсорДанныеДлязаписиичтнияЧата;
    }






    // TODO: 15.07.2022  метод который  ВЫЧИСЛЯЕТ КЕМ БЫЛ НАПИСАНОЕ ТЕКУЩЕЕ СООБЩЕНИЕ
    String МетодКемБЫлоНАписаноСообщение(@NonNull  Integer ПолученноеФИОКемБылоНаписаноСообщение,@NonNull Context context) {
        String КтопанисалСообщениеФИО = new String();

        try {
            this.context=context;
            modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного = new CoreBinessLogics(context);
            recordNewErros =new RecordNewErros(context);
            // TODO: 15.07.2022 ПОЛУЧАЕМ ФИО
                    String Текущаятаблицы="fio";
            ModuleQuety moduleQuety=new ModuleQuety(getApplicationContext());
            Cursor   Курсор_соЗначениемФИО = moduleQuety.getModuleQuery(Текущаятаблицы," SELECT   D.name FROM '"+Текущаятаблицы+"'  AS D "+
                    " WHERE   D.user_update = '"+ПолученноеФИОКемБылоНаписаноСообщение+"'" ,null);

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Курсор_соЗначениемФИО " +Курсор_соЗначениемФИО);


            if (Курсор_соЗначениемФИО.getCount() > 0) {
                Курсор_соЗначениемФИО.moveToFirst();
                КтопанисалСообщениеФИО = Курсор_соЗначениемФИО.getString(0).trim();
            }

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " КтопанисалСообщениеФИО " +КтопанисалСообщениеФИО);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return КтопанисалСообщениеФИО;
    }


} //TODO END CLASS

