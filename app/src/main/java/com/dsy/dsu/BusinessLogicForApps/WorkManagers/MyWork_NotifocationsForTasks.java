package com.dsy.dsu.BusinessLogicForApps.WorkManagers;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.dsy.dsu.BusinessLogicForApps.SubClass_Starting_Tasks_ЗапускДЛяЗадач;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.sous.backasync.launch.ModuleUpdating;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MyWork_NotifocationsForTasks extends Worker {
    private  Context context;
    private  String ИмяСлужбыУведомленияДля_Задачи = "WorkManager NOtofocationForTasks";
    private  WorkerParameters workerParams;
    private   NotificationManager mNotificationManagerДляЧАТА = null;
    private   WorkInfo ИнформацияОЗапущенойСлужбе_Уведомления_Одноразовая;
    private  Integer ОбщееКоличествоНЕпрочитанныхСтрок = 0;
    private NotificationCompat.Builder builder_Для_Задачи = null;
    private  SimpleDateFormat ФоорматДат ;
    private    int     ID_ТаблицаУвендомлений;
    private   Intent ИнтентДляЗапускаСлужбыПолсеАнализа;
    private   Boolean РезультатНужноЗапускатьУведомленияИлиНет=false;
    private String PROCESS_ID_УведомленияПлановая="12";

    private  ArrayList БуферСамиУведомленияЛинкСамиУведомления;
    private  NotificationCompat.MessagingStyle messagingStyleДля_ОбщихУведомлений;
    private Person.Builder person;
    private SubClass_Starting_Tasks_ЗапускДЛяЗадач subClassStartingTasksЗапускДЛяЗадач;
    private  Long UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный=0l;
    private     Data myDataОтветОбщегоУведомления;

    public MyWork_NotifocationsForTasks(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
        messagingStyleДля_ОбщихУведомлений = new NotificationCompat.MessagingStyle(getApplicationContext().getResources().getString(R.string.action_settings)).setConversationTitle("Задачи");
        Log.i(this.context.getClass().getName(),
                " messagingStyleДля_ОбщихУведомлений " + "\n" + messagingStyleДля_ОбщихУведомлений.getMessages());
        subClassStartingTasksЗапускДЛяЗадач = new SubClass_Starting_Tasks_ЗапускДЛяЗадач(getApplicationContext());
    }

    @SuppressLint("RestrictedApi")
    @NonNull
    @Override
    public Executor getBackgroundExecutor() {
        Log.i(context.getClass().getName(),
                "public Executor getBackgroundExecutor() {");
        return  Executors.newSingleThreadExecutor();
    }
    @Override
    public void onStopped() {
        super.onStopped();
        Log.i(this.getClass().getName(), " onStopped ()  ");
    }
    // TODO: 17.11.2021  ГЛАВНЫЙ МЕТОД КЛАССА WORK MANEGER  ДЛЯ УВЕЛОДОМЛЕНИЯ ТОЛЬКО ДЛЯ ЧАТА
    @NonNull
    @Override
    public Result doWork() {
        Boolean ФинальныйФлагЛюбогоЗапущеногоАктивти = false;
        try {
            // TODO: 16.04.2025
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            ActivityManager ЗапущенныйПроуессыДляУведомленийЧата = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
            if (ЗапущенныйПроуессыДляУведомленийЧата!=null) {
                List<ActivityManager.AppTask> КоличествоЗапущенныйПроуессыДляЧата = ЗапущенныйПроуессыДляУведомленийЧата.getAppTasks();
            if (КоличествоЗапущенныйПроуессыДляЧата.size() > 0) {
                Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         " +
                        "public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER  КоличествоЗапущенныйПроуессыДляЧата " + "\n"
                        + КоличествоЗапущенныйПроуессыДляЧата.size());
                for (ActivityManager.AppTask ТекущаяАктивти : КоличествоЗапущенныйПроуессыДляЧата) {
                    String АктивностьЕслиЕстьTOPДляЧата = null;
                    if (ТекущаяАктивти!=null) {
                        Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         " +
                                "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                                + ТекущаяАктивти.getTaskInfo().numActivities);
                        // TODO: 20.02.2022
                        if (ТекущаяАктивти.getTaskInfo().numActivities>0) {
                            // TODO: 20.02.2022
                            АктивностьЕслиЕстьTOPДляЧата = ТекущаяАктивти.getTaskInfo().topActivity.getClassName().toString();
                        }
                        Log.i(context.getClass().getName(), "ТекущаяАктивти " + ТекущаяАктивти +
                                " АктивностьЕслиЕстьTOPДляЧата  " + АктивностьЕслиЕстьTOPДляЧата +
                                "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                                + ТекущаяАктивти.getTaskInfo().numActivities);
                    }
                    if (АктивностьЕслиЕстьTOPДляЧата!=null) {
                        // TODO: 06.12.2021
                        switch (АктивностьЕслиЕстьTOPДляЧата) {
                            case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Visible_Async":
                            case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_Start":
                            case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivityPasswords":
                                break;
                            // TODO: 01.12.2021 САМ ЗАПУСК WORK MANAGER  СИНХРОНИАЗЦИИ ПРИ ВКЛЮЧЕННОЙ АКТИВТИ
                            default:
                                Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА ВНУТРИ startService   Вещятеля BroadcastReceiver  Service_Notificatios_Уведомления_ОбновлениеПО  ДЛЯ ЧАТА " + new Date() +
                                        "\n" + " Build.BRAND " + Build.BRAND.toString() + "\n");
                                ///////todo код запуска уведомлений для чата
                                МетодЗапускаСлужбыУведомленияДляЧата();
                                ///////todo код запуска уведомлений для чата
                                Log.i(context.getClass().getName(), "Метод ВНУТРИ РАБОТА... С АКТИВТИ ДЕЙСТВУЩИМ ЧАТА ОТРАБОТАЛ ВНУТРИ метода ЗАПУСКАЕМ БЕЗ activity      " +
                                        "   public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER "
                                        + new Date() +
                                        " WorkManager Synchronizasiy_Data  " + " РАБОТАЮЩИЙ ПРОЦЕСС  КоличествоЗапущенныйПроуессыДляЧата.size()"
                                        + КоличествоЗапущенныйПроуессыДляЧата.size() + "\n" +
                                        "   действуещее TOP активти АктивностьЕслиЕстьTOPДляЧата " + АктивностьЕслиЕстьTOPДляЧата);
                                ///////todo  КОНЕЦ  код запуска уведомлений для чата
                                break;
                            // TODO: 24.11.2021
                        }
                    }else{
                        // TODO: 03.12.2021 ПРОСТО ФОНОВАЯ ЗАДАЧА  РЕЗКО НЕ СТАЛО АКТИВТИ
                        МетодЗапускаетУведомленияКогдаВообщенетНиОдногоАктивтиNULL( );
                        Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER Зарускает когда Активти РАВНО )" + "\n"
                                + КоличествоЗапущенныйПроуессыДляЧата);
                    }
                }
            } else {
                МетодЗапускаетУведомленияКогдаВообщенетНиОдногоАктивтиNULL( );
                Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER Зарускает когда Активти РАВНО )" + "\n"
                        + КоличествоЗапущенныйПроуессыДляЧата);
            }
        }else{
            // TODO: 03.12.2021 ПРОСТО ФОНОВАЯ ЗАДАЧА
            МетодЗапускаетУведомленияКогдаВообщенетНиОдногоАктивтиNULL( );
                Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER Зарускает когда Активти воОБЩЕ НЕТ null" );
        }
            // TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            Log.i(context.getClass().getName(), "ОбщееКоличествоНЕпрочитанныхСтрок" + ОбщееКоличествоНЕпрочитанныхСтрок + " РезультатНужноЗапускатьУведомленияИлиНет "+ РезультатНужноЗапускатьУведомленияИлиНет);
            // TODO: 25.02.2022 send datas
            myDataОтветОбщегоУведомления = new Data.Builder()
                    .putBoolean("ОтветПослеВыполения_MyWork_Notifocations_Уведомления_Общая",
                            РезультатНужноЗапускатьУведомленияИлиНет)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
            return Result.retry();
            // TODO: 03.01.2022
    }

















    // TODO: 16.12.2021 Метод ЗАпускает в фоне Уведомелния когда вообще нет нет ни одного Активти NULL

    private void МетодЗапускаетУведомленияКогдаВообщенетНиОдногоАктивтиNULL() {
        try{
                          Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА ВНУТРИ startService   Вещятеля BroadcastReceiver  Service_Notificatios_Уведомления_ОбновлениеПО  ДЛЯ ЧАТА " + new Date() +
                                  "\n" + " Build.BRAND " + Build.BRAND.toString() + "\n" );

                          МетодЗапускаСлужбыУведомленияДляЧата();

                          Log.i(context.getClass().getName(), "Метод ВНУТРИ ЧИСТО ФОНОВАЯ ЗАДАЧА  ЧАТА ОТРАБОТАЛ ВНУТРИ метода ЗАПУСКАЕМ БЕЗ activity      " +
                                  "   public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER "
                                  + new Date() +
                                  " WorkManager Synchronizasiy_Data  " );

                      } catch (Exception e) {
                      e.printStackTrace();
                      Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                              " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                      // TODO: 01.09.2021 метод вызова
                      new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                              this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                              Thread.currentThread().getStackTrace()[2].getLineNumber());
                  }
    }


    // TODO: 17.11.2021










    private void МетодЗапускаСлужбыУведомленияДляЧата() {
        try{
            Log.i(getApplicationContext().getClass().getName(), "Запуск метода МетодЗапускаСлужбыУведомления СЛУЖБА СЛУЖБАService_Notifications ДЛЯ ЧАТА ДЛЯ ЧАТА  "+new Date());
            try {
                ФоорматДат = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));//"yyyy-MM-dd HH:mm:ss.SSS"//"yyyy-MM-dd'T'HH:mm:ss'Z'"
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: 02.08.2021
                ФоорматДат = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("ru"));//"yyyy-MM-dd HH:mm:ss.SSS"//"yyyy-MM-dd'T'HH:mm:ss'Z'"
            }
            ФоорматДат.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));

                    /////////TODO запуск нновую нотификашенс устанолвка
                    МетодЗарускаСозданиеУведомлений();

                    Log.d(getApplicationContext().getClass().getName(), " Запуск по Расписанию СЛУЖБА  Информирования  BroadcastReceiver или  FaceApp " + "  --" + new Date());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }

    }


    /////////////////

    private void МетодЗарускаСозданиеУведомлений() {
        try{
                РезультатНужноЗапускатьУведомленияИлиНет=false;

                Log.d(getApplicationContext().getClass().getName(), " Внутри МетодЗарускаСозданиеУведомлений"
                        + "--" + РезультатНужноЗапускатьУведомленияИлиНет);/////
                // TODO: 17.11.2021  ВЫЧИСЛЯЕМ ВООБЩЕ ЕСТЬ СТРОЧКИ В ЧАТЕ НЕ ПРОЧИТАННЫЕ КОТОРЫЕ НАЖДО ПРОЧИТАТЬ -- ВЕРНЁТ TRUE


                Log.d(getApplicationContext().getClass().getName(), " Внутри Future Результат НужноЗапускать Уведомления Или Нет  СЛУЖБА РезультатНужноЗапускатьУведомленияИлиНет"
                        + "--" + РезультатНужноЗапускатьУведомленияИлиНет);/////
                String ФлагПолучаемИзНутриПрограммы = null;//=// notificationIntentДляУведомлений .getStringExtra("Флаг");
                if(ФлагПолучаемИзНутриПрограммы==null){
                    ФлагПолучаемИзНутриПрограммы=new String();
                }
                Log.d(getApplicationContext().getClass().getName(), " Определили Результат НужноЗапускать Уведомления Или Нет  СЛУЖБА"
                        + "--" + РезультатНужноЗапускатьУведомленияИлиНет+  " ФлагПолучаемИзНутриПрограммы " +ФлагПолучаемИзНутриПрограммы+ "\n"
                        + "ОбщееКоличествоНЕпрочитанныхСтрок   "+ОбщееКоличествоНЕпрочитанныхСтрок);/////

// TODO: 17.11.2021  ЕСЛИ TRUE ТО НАЧИНАЕМ ЗАПУСКАЕМ УВЕДОМЛЕНИЯ
                if (РезультатНужноЗапускатьУведомленияИлиНет==true ) {
                    МетодКоторыйЗапускаетУвеломленияПослеАнализа(ИнтентДляЗапускаСлужбыПолсеАнализа, РезультатНужноЗапускатьУведомленияИлиНет, ФлагПолучаемИзНутриПрограммы);//  //ФлагКтоЗапустилСлужбу
                    Log.d(this.getClass().getName(), "ЗАПУСК ПОСЛЕ АНАЛИЗА ДАТ ЗАПУСКАЕМ УВЕДОМЛЕНИЯ  СЛУЖБА  Синхронизация   " + " ВРЕМЯ " + new Date()
                            + "\n" + " РезультатНужноЗапускатьУведомленияИлиНет " + РезультатНужноЗапускатьУведомленияИлиНет);
                }
                Log.d(getApplicationContext().getClass().getName(), " Определили Результат НужноЗапускать Уведомления Или Нет  СЛУЖБА"
                        + "--" + РезультатНужноЗапускатьУведомленияИлиНет+  " ФлагПолучаемИзНутриПрограммы " +ФлагПолучаемИзНутриПрограммы);/////
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }





    private void МетодКоторыйЗапускаетУвеломленияПослеАнализа(Intent intent, boolean результатНужноЗапускатьУведомленияИлиНет, String ФлагКтоЗапустилСлужбу) {
        Log.d(this.getClass().getName(), "Результат Нужно Запускать Уведомления Или Нет СЛУЖБА  true and false :: " +
                результатНужноЗапускатьУведомленияИлиНет);
        Log.d(getApplicationContext().getClass().getName(), " ФлагКтоЗапустилСлужбу " + ФлагКтоЗапустилСлужбу);
            МетодНотификайшенДЛяОбщейСлужбыУведомления(ФлагКтоЗапустилСлужбу);
        Log.d(getApplicationContext().getClass().getName(), " ФлагКтоЗапустилСлужбу " + ФлагКтоЗапустилСлужбу);
    }

/////////TODO запуск нновую нотификашенс устанолвка
    private void МетодНотификайшенДЛяОбщейСлужбыУведомления(String ФлагКтоЗапустилСлужбу) {
        try {
            Log.d(getApplicationContext().getClass().getName(), " Создание Уведомлеения СЛУЖБА СЛУЖБА Service_Notificatios_Уведомления_ОбновлениеПО ");
            builder_Для_Задачи = null;


            // TODO: 03.03.2022 определяем кода для отложеного запуска службы смены статсу условия задачи выполнить
            PendingIntent ЗапускКОдаЧтоПОльзовательВыполнить = subClassStartingTasksЗапускДЛяЗадач.
                    МетодЗапускаСменыСтатусаВыполнилСлужбыЧерезPendingIntent(PROCESS_ID_УведомленияПлановая, ИмяСлужбыУведомленияДля_Задачи,
                            person.build().getUri(),
                            2, "Выполнил 100 %", "ЗапускаемИзмененияСатусазадачиВыполнил");

            // TODO: 03.03.2022 определяем кода для отложеного запуска службы смены статсу условия задачи  отказ
            PendingIntent ЗапускКОдаЧтоПОльзовательОтказЗаданием = subClassStartingTasksЗапускДЛяЗадач.
                    МетодЗапускаСменыСтатусаОтказСлужбыЧерезPendingIntent(PROCESS_ID_УведомленияПлановая, ИмяСлужбыУведомленияДля_Задачи,
                            person.build().getUri(),
                            1, "Отказ", "ЗапсукаемОтказИзмененияСтатусаВзадаче");


            // TODO: 03.03.2022 ВЬТОРОЙ МЕТОД ДЛЯ ЗАДАНИЕ ПЕРЕХОД ИЗ УВЕДОМЛЕНИЯ В ЗАДАНИЕ
            PendingIntent ЗапускПриКликеКодаИзЗаданияКогдаНадоПерейтисУведомленияНаЗАдачние = subClassStartingTasksЗапускДЛяЗадач.
                    МетодПриКликеЗапускаЗаданияИзСамогоУведомленияПереход(PROCESS_ID_УведомленияПлановая, ИмяСлужбыУведомленияДля_Задачи,
                            person.build().getUri(),
                            0, "", "ИзУведомленияЗадачаПереходимВАктивтиЗадача");


            ///////TODO запускаем смены стануса задачи черезе PendingIntent
            Log.d(getApplicationContext().getClass().getName(), "PROCESS_ID_УведомленияПлановая  ПЕРЕЙТИ " + PROCESS_ID_УведомленияПлановая +
                    " ИмяСлужбыУведомленияДляЧата " + ИмяСлужбыУведомленияДля_Задачи + " person.build().getUri() " + person.build().getUri());


            ///////TODO запускаем смены стануса задачи черезе PendingIntent
            Log.d(getApplicationContext().getClass().getName(), "PROCESS_ID_УведомленияПлановая  ПЕРЕЙТИ " + PROCESS_ID_УведомленияПлановая +
                    " ИмяСлужбыУведомленияДляЧата " + ИмяСлужбыУведомленияДля_Задачи + " person.build().getUri() " + person.build().getUri());


            // TODO: 27.03.2022 ДЛЯ ЗАДАЧА
            NotificationManager notificationManager = (NotificationManager)
                    getApplicationContext().getSystemService(NOTIFICATION_SERVICE);





            // TODO: 21.12.2021 В КОДЕ НИЖЕ МЫ ОПРЕДЕЛЯЕМ ЗАПУСКАТЬ НАМ КОД ИЛИ НЕТ

            Boolean СтатустУведомленияДляУведомленияЧАТА=false;


            StatusBarNotification[] statusBarNotificationУведомленияЧата=      notificationManager.getActiveNotifications();
            // TODO: 21.12.2021
            for(StatusBarNotification statusBarNotification1: statusBarNotificationУведомленияЧата) {

                // TODO: 21.12.2021

                if (statusBarNotification1.getId() == Integer.parseInt(PROCESS_ID_УведомленияПлановая)) {

                    // TODO: 21.12.2021
                    СтатустУведомленияДляУведомленияЧАТА = statusBarNotification1.isClearable();
                    // TODO: 21.12.2021

                    Log.d(this.getClass().getName(), " СтатустУведомленияДляУведомленияЧАТА" + СтатустУведомленияДляУведомленияЧАТА);


                }


            }


            Log.d(this.getClass().getName(), "СтатустУведомленияДляУведомленияЧАТА " +СтатустУведомленияДляУведомленияЧАТА+
                    " БуферСамиУведомленияЛинкСамиУведомления " +БуферСамиУведомленияЛинкСамиУведомления );


            // TODO: 21.11.2021 НЕПОСТРЕДСТВЕННО СОЗДАНИЕ УВЕДОМЛЕНИЯ ДЛЯ ЧАТА СОЗДАНИЕ И ЗАПОЛЕНИЕ


               // if (БуферСамиУведомленияЛинкСамиУведомления.size()>0  && СтатустУведомленияДляУведомленияЧАТА==false ) {/// && СтатустУведомленияДляУведомленияЧАТА==false
                if (БуферСамиУведомленияЛинкСамиУведомления.size()>0 ) {/// && СтатустУведомленияДляУведомленияЧАТА==false


                    Log.d(this.getClass().getName(), "bigText " +БуферСамиУведомленияЛинкСамиУведомления+ " БуферСамиУведомленияЛинкСамиУведомления.toString() "
                            +БуферСамиУведомленияЛинкСамиУведомления.toString()+"\n"+
                            "ЗАПУСК......НОВОЕ СООБЩЕНИЕ   bigText " +БуферСамиУведомленияЛинкСамиУведомления+"\n"+
                            "  время ::" + new Date());




                    //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ


                    notificationManager.cancel(Integer.parseInt(PROCESS_ID_УведомленияПлановая));


                    ///notificationManager.cancelAll();



                    onStopped();


                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v2.vibrate(200);
                    }




                    PendingIntent ЗапускКОдаЧтоПОльзовательОзнаомленсЗаданиемss = null;


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        ///"@mipmap/icon_main_tabel_four" ////.setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        builder_Для_Задачи = new NotificationCompat.Builder(getApplicationContext(), PROCESS_ID_УведомленияПлановая)
                                /////
                                .setContentText(БуферСамиУведомленияЛинкСамиУведомления.toString())                 // .setContentText("http://developer.alexanderklimov.ru/android/")
                                .setSmallIcon(R.drawable.ic_notifications_black_24dp)////builder.setSmallIcon(R.drawable.ic_launcher_background);//R.mipmap.ic_launcher   ///R.drawable.ic_notifications_black_24dp
                                .setPriority(NotificationCompat.PRIORITY_MAX)
                                .setColor(Color.BLUE)
                                .setColorized(true)
                                //.setContentTitle("Задание на выполнение")
                                .setSmallIcon(R.drawable.icon_dsu1_for_fragment1_chat2)
                                .setGroup("SousAndroid")
                                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                        R.drawable.ic_notifications_black_24dp)) // большая картинка
                                //.setTicker("Последнее китайское предупреждение!") // до Lollipop
                                .setVibrate(new long[]{0, 250, 100, 250})
                                .setShowWhen(true)
                                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                .setCategory(Notification.CATEGORY_MESSAGE)
                                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.FLAG_AUTO_CANCEL)
                                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                                .setStyle(new NotificationCompat.BigTextStyle().bigText(БуферСамиУведомленияЛинкСамиУведомления
                                        .toString())).setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                                .setStyle(messagingStyleДля_ОбщихУведомлений).setColor(Color.parseColor(("#FAEBD" + new Random().nextInt(1))))
                                .setGroupSummary(true)
                                .setColor(Color.GREEN)
                                .addAction(android.R.drawable.ic_btn_speak_now, "Выполнить", ЗапускКОдаЧтоПОльзовательВыполнить)
                                .addAction(android.R.drawable.ic_delete, "Отказ", ЗапускКОдаЧтоПОльзовательОтказЗаданием)
                                .setAutoCancel(false)
                                .setWhen(System.currentTimeMillis()) // автоматически закрыть уведомление после нажатия////.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText) ).setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                                .setContentIntent(ЗапускПриКликеКодаИзЗаданияКогдаНадоПерейтисУведомленияНаЗАдачние);
                        ////TODO три кнопки действия PUSH-сообщений
                        /// .setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)  .addAction(android.R.drawable.ic_delete, "Выполнил/на", ЗапускКОдаЧтоПОльзовательОзнаомленсЗаданиемss)


                    } else {
                        builder_Для_Задачи =
                                new NotificationCompat.Builder(getApplicationContext(), PROCESS_ID_УведомленияПлановая)
                                        //
                                        .setContentText(БуферСамиУведомленияЛинкСамиУведомления.toString())                 // .setContentText("http://developer.alexanderklimov.ru/android/")
                                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)////builder.setSmallIcon(R.drawable.ic_launcher_background);//R.mipmap.ic_launcher   ///R.drawable.ic_notifications_black_24dp
                                        .setPriority(NotificationCompat.PRIORITY_MAX)
                                        .setColor(Color.BLUE)
                                        .setColorized(true)
                                        //  .setContentTitle("Задание на выполнение")
                                        .setSmallIcon(R.drawable.icon_dsu1_for_fragment1_chat2)
                                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                                R.drawable.ic_notifications_black_24dp)) // большая картинка
                                        //.setTicker("Последнее китайское предупреждение!") // до Lollipop
                                        .setVibrate(new long[]{0, 250, 100, 250})
                                        .setShowWhen(true)
                                        .setGroup("SousAndroid")
                                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                        .setCategory(Notification.CATEGORY_MESSAGE)
                                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.FLAG_AUTO_CANCEL)
                                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                                        /* .setStyle(new NotificationCompat.BigTextStyle().bigText(БуферСамиУведомленияЛинкСамиУведомления.toString())
                                         ).setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)*/
                                        .setStyle(messagingStyleДля_ОбщихУведомлений).setColor(Color.parseColor(("#FAEBD" + new Random().nextInt(1))))
                                        .setGroupSummary(true)
                                        .setColor(Color.GREEN)
                                        .addAction(android.R.drawable.ic_btn_speak_now, "Выполнить", ЗапускКОдаЧтоПОльзовательВыполнить)
                                        .addAction(android.R.drawable.ic_delete, "Отказ", ЗапускКОдаЧтоПОльзовательОтказЗаданием)
                                        .setAutoCancel(false)
                                        .setWhen(System.currentTimeMillis()) // автоматически закрыть уведомление после нажатия////.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText) ).setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                                        .setContentIntent(ЗапускПриКликеКодаИзЗаданияКогдаНадоПерейтисУведомленияНаЗАдачние);// TODO: 27.03.2022    .addAction(android.R.drawable.ic_delete, "Выполнил/на", ЗапускКОдаЧтоПОльзовательОзнаомленсЗаданиемss)

                        // автоматически закрыть уведомление после нажатия
                        // .setContentIntent(ЗапускЗакрытия);
                        ////TODO три кнопки действия PUSH-сообщений



                    }



            // TODO: 27.11.2021 САМ ЗАПУСК УВЕДОМЛЕНИЯ

            mNotificationManagerДляЧАТА = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

            // TODO: 17.11.2021  launch
            // === Removed some obsoletes
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(
                        PROCESS_ID_УведомленияПлановая,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_HIGH);
                mNotificationManagerДляЧАТА.createNotificationChannel(channel);
                builder_Для_Задачи.setChannelId(String.valueOf(PROCESS_ID_УведомленияПлановая));
                channel.setDescription("Увеомление для версии выше API 25");
                // TODO: 18.11.2021  дополнительые настройки

                builder_Для_Задачи.build().flags |= Notification.FLAG_FOREGROUND_SERVICE;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |= Notification.FLAG_AUTO_CANCEL;
                // startForeground(Integer.parseInt(PROCESS_ID_УведомленияПлановая),builder.build());//builder.build()
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |= Notification.FLAG_SHOW_LIGHTS;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |= Notification.FLAG_INSISTENT;

                // TODO: 02.12.2021
                builder_Для_Задачи.setNumber(БуферСамиУведомленияЛинкСамиУведомления.size());


                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |=Intent.FLAG_ACTIVITY_CLEAR_TASK;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |=  Intent.FLAG_ACTIVITY_NEW_TASK;


                // TODO: 20.06.2022 световая индикация\

                builder_Для_Задачи.build().ledARGB = Color.RED;
                builder_Для_Задачи.build().ledOffMS = 0;
                builder_Для_Задачи.build().ledOnMS = 1;
                builder_Для_Задачи.build().flags =  Notification.FLAG_SHOW_LIGHTS;
                builder_Для_Задачи.build().flags = Notification.FLAG_ONGOING_EVENT;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    mNotificationManagerДляЧАТА.getBubblePreference();
                }

                ///TODO Запускаем увидомления
                // TODO: 02.12.2021  сам запуск уведомления
                mNotificationManagerДляЧАТА.notify(Integer.parseInt(PROCESS_ID_УведомленияПлановая), builder_Для_Задачи.build());////   mNotificationManagerДляЧАТА.notify(Integer.parseInt(PROCESS_ID_УведомленияПлановая), builder.build());
                ///TODO закрытие увидомления

            }else{
                ///TODO Запускаем увидомления

                // TODO: 27.11.2021 САМ ЗАПУСК УВЕДОМЛЕНИЯ

                //mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                builder_Для_Задачи.build().flags |= Notification.FLAG_FOREGROUND_SERVICE;
                ///TODO Запускаем увидомления
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |= Notification.FLAG_AUTO_CANCEL;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |= Notification.FLAG_SHOW_LIGHTS;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |= Notification.FLAG_INSISTENT;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |=Intent.FLAG_ACTIVITY_CLEAR_TASK;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |=  Intent.FLAG_ACTIVITY_NEW_TASK;
                // TODO: 02.12.2021
                builder_Для_Задачи.setNumber(БуферСамиУведомленияЛинкСамиУведомления.size());
                // TODO: 20.06.2022 световая индикация\

                builder_Для_Задачи.build().ledARGB = Color.RED;
                builder_Для_Задачи.build().ledOffMS = 0;
                builder_Для_Задачи.build().ledOnMS = 1;
                builder_Для_Задачи.build().flags = Notification.FLAG_SHOW_LIGHTS;
                builder_Для_Задачи.build().flags = Notification.FLAG_ONGOING_EVENT;


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    mNotificationManagerДляЧАТА.getBubblePreference();
                }

                // TODO: 02.12.2021  сам запуск уведомления

                mNotificationManagerДляЧАТА.notify(Integer.parseInt(PROCESS_ID_УведомленияПлановая), builder_Для_Задачи.build());////   mNotificationManagerДляЧАТА.notify(Integer.parseInt(PROCESS_ID_УведомленияПлановая), builder.build());
                ///TODO закрытие увидомления


            }


// TODO: 20.06.2022  метод смены статуса уведосления что его показывали

                    МетодСтатусаЗадачиЧтоУведомлениеУжеПоказывали();

                    Log.i(this.getClass().getName(), " смена статуса уведомления за " );




                }else{
                    Log.i(this.getClass().getName(), " ОБЩЕЕ УВЕДОМЛЕНИЯ НЕТ ДАГЫХ ЧТО ЗАПОЛНИТЬ УСЛОВИЯ ПУСТОЙ СЛУЖБЫ БуферСамиУведомленияЛинкСамиУведомления  " +БуферСамиУведомленияЛинкСамиУведомления.toString());
                }



            //////



        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ
            Log.d(getApplicationContext().getClass().getName(), " Стоп СЛУЖБА СЛУЖБАService_Notifications ДЛЯ ЧАТА  onDestroy() Exception ");

        }







    }

    private void МетодСтатусаЗадачиЧтоУведомлениеУжеПоказывали() {
        try {
            String ТаблицаОбрабокаиПриСменсатусаУведомленияЧтоЕгоУжекПоказхывали = "data_notification";
            ContentValues contentValuesСменаСтатусаЗадачиЧтоЕЕЕжуПоказывали = new ContentValues();
            // TODO: 20.05.2025
            contentValuesСменаСтатусаЗадачиЧтоЕЕЕжуПоказывали.put("alreadyshownnotifications", 1);
            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
            Long РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудникаПервая =
                    new VersionCurentTable(getApplicationContext()).upVersionCurentTable(ТаблицаОбрабокаиПриСменсатусаУведомленияЧтоЕгоУжекПоказхывали);
            contentValuesСменаСтатусаЗадачиЧтоЕЕЕжуПоказывали.put("current_table", РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудникаПервая);

            // TODO: 14.05.2025
            ModuleUpdating moduleUpdating = new ModuleUpdating(context);
            // TODO: 03.02.2025 update new back
            Integer Результат_ОбновлениеДанных = moduleUpdating.getModuleUpdate(ТаблицаОбрабокаиПриСменсатусаУведомленияЧтоЕгоУжекПоказхывали,
                    contentValuesСменаСтатусаЗадачиЧтоЕЕЕжуПоказывали, "uuid =?", new String[]{UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный.toString()});

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Результат_ОбновлениеДанных " + Результат_ОбновлениеДанных);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }







    ///TODO метод определяем стоит запускать и создвать службу напоминаний или нет


    // TODO: 02.03.2022


    // TODO: 20.05.2021  продолжение уведомления определяем даты после того как получили права на сотрудника



    // TODO: 17.11.2021  end classs worl manager

    }






























