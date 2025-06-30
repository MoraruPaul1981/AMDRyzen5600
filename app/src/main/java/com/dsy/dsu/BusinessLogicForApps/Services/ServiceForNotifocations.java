package com.dsy.dsu.BusinessLogicForApps.Services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossContext;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.ChatsApp.MainActivity_List_Chats;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


public class ServiceForNotifocations extends Service {////Service

    //TODO
    String ИмяСлужбыУведомленияДляЧата;
    HashMap<String, String> hashMapХэшДляЗапоминиялUUID = new HashMap();
    // TODO: 07.02.2022
    JbossContext Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = null;
    // TODO: 12.10.2021  Ссылка Менеджер Потоков
    Long UUIDДляЗапускСогласованияПришедшегоЗАДАНИЕ;

    // TODO: 07.02.2022
    Integer ПередаемСтатусзадачи;

    // TODO: 07.02.2022
    // TODO: 24.03.2022
    String ПримечаниеВыполнилКлиентИлиНетЗадачуПришлиВСлужбу;
    ////////
    private String PROCESS_ID;

    // TODO: 26.03.2022


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(getApplicationContext().getClass().getName(), " onCreate СЛУЖБА ServiceForNotifocations  "
                + " время: "
                + new Date());

    }
// TODO: 16.11.2021

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {

        try {

            Log.d(getApplicationContext().getClass().getName(), " onStartCommand СЛУЖБА ServiceForNotifocations  "
                    + " время: "
                    + new Date() + "intent.getAction().toString() " + intent.getAction());


            if (intent.getAction()!=null) {
                // TODO: 08.06.2022  входим когда не NULL
                if (intent.getAction().equals("ЗакрываемУведомленияЧата")) {
    
    
                    // TODO: 17.11.2021
                    /////todo NOtofication
                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
    
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v2.vibrate(200);
                    }
                    // TODO: 17.11.2021
                    ///
    
    
                    NotificationManager notificationManager = (NotificationManager)
                            getSystemService(NOTIFICATION_SERVICE);
    
    
                    // TODO: 26.03.2022
    
    
                    // TODO: 26.03.2022
                    PROCESS_ID = intent.getStringExtra("PROCESS_ID_Чата");
    
                    // TODO: 26.03.2022
                    ИмяСлужбыУведомленияДляЧата = intent.getStringExtra("НазваниеСлужбыВСлужбуЧата");
    
                    // TODO: 26.03.2022
                    Log.d(getApplicationContext().getClass().getName(), " onStartCommand СЛУЖБА ServiceForNotifocations  "
                            + " время: "
                            + new Date() + "intent.getAction().toString() " + intent.getAction().toString() + "bundleЧата " + ИмяСлужбыУведомленияДляЧата);
    

                    notificationManager.cancel(Integer.parseInt(PROCESS_ID));
    
                    stopForeground(true);
    
    
                    // TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
                    WorkInfo ИнформацияОЗапущенойСлужбе = null;
                    try {
                        ИнформацияОЗапущенойСлужбе = WorkManager.getInstance(getApplicationContext().getApplicationContext()).getWorkInfosByTag(ИмяСлужбыУведомленияДляЧата.toString()).get().get(0);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // TODO: 13.11.2021  ПОКАЗЫВАЕМ СТАТУС ПОСЛЕ ОТРАБОТАНГНЙО WORK MANAGER  ПРИ Уведомления для Чата         // TODO: 13.11.2021  ПОКАЗЫВАЕМ СТАТУС ПОСЛЕ ОТРАБОТАНГНЙО WORK MANAGER  ПРИ Уведомления для Чата
    
                    Log.w(getApplicationContext().getClass().getName(), " После НАЖАТИЕ НА КНОПКУ ЗАКРЫТЬ  Выкючение в ServiceForNotifocations Внутри СЛУЖБЫ " +
                            " MyWork_NotifocationsForTasks " + ИмяСлужбыУведомленияДляЧата + "\n"
                            + " getState  " +
                            ИнформацияОЗапущенойСлужбе.getState().name() + "\n" +
                            " isFinished  " +
                            ИнформацияОЗапущенойСлужбе.getState().isFinished() + "\n" +
                            "getTags " +
                            ИнформацияОЗапущенойСлужбе.getTags() + "\n" +
                            "getRunAttemptCount " +
                            ИнформацияОЗапущенойСлужбе.getRunAttemptCount() + "\n" +
                            "getProgress " +
                            ИнформацияОЗапущенойСлужбе.getProgress().toString() + "\n" +
                            " время : " + new Date());
    
    
    // TODO: 13.11.2021  ПОКАЗЫВАЕМ СТАТУС ПОСЛЕ ОТРАБОТАНГНЙО WORK MANAGER  ПРИ Уведомления для Чата         // TODO: 13.11.2021  ПОКАЗЫВАЕМ СТАТУС ПОСЛЕ ОТРАБОТАНГНЙО WORK MANAGER  ПРИ Уведомления для Чата
    
    
                    Log.i(getApplicationContext().getClass().getName(), " Закрываем   внутри служы ПОЛЬЗОВАТЛЬ НАДАЛ НАКПОКУ ЗАКРЫТЬ" +
                            "ServiceForNotifocations (intent.getAction()   СЛУЖБА" + (intent.getAction().toString()) + " время запуска  " + new Date());
    
    
                    // TODO: 07.02.2022 задание КЛИВАЕМ НА СООБЩЕНИЕ ПЕРЕРХОДИМ НА ЧАТ
    
    
                }


                // TODO: 07.02.2022 задание КЛИВАЕМ НА СООБЩЕНИЕ ПЕРЕРХОДИМ НА ЧАТ


                Log.d(getApplicationContext().getClass().getName(), " onStartCommand СЛУЖБА ServiceForNotifocations  "
                        + " время: "
                        + new Date() + "intent.getAction().toString() " + intent.getAction().toString());


                if (intent.getAction().equals("ПереходимИзУведомленияВЧасЧат")) {
    
    
                    Log.i(getApplicationContext().getClass().getName(), " ЗапускИзУведомления ........ СНАРУЖИ Broadcatrecever (intent.getAction()   СЛУЖБА"
                            + (intent.getAction().toString()) + " время запуска  " + new Date());
    
                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
    // Vibrate for 500 milliseconds
    
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v2.vibrate(150);
                    }
    
                    // TODO: 17.11.2021
                    Intent intentЗапускаемИзУведомленияСамЧат = new Intent(getApplicationContext(), MainActivity_List_Chats.class);
    
                    // TODO: 15.11.2021
                    intentЗапускаемИзУведомленияСамЧат.setAction(Intent.ACTION_SCREEN_ON);
    
                    intentЗапускаемИзУведомленияСамЧат.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    
                    intentЗапускаемИзУведомленияСамЧат.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    
    
                    getApplicationContext().startActivity(intentЗапускаемИзУведомленияСамЧат);
                    // TODO: 15.11.2021
    
                    Log.i(getApplicationContext().getClass().getName(), " ЗапускИзУведомления ........ СНАРУЖИ Broadcatrecever (intent.getAction()   СЛУЖБА"
                            + (intent.getAction().toString()) + " время запуска  " + new Date());
    
    
                    // TODO: 07.02.2022 задание НА СОГЛАСОВАНИЕ ЧТО ПОЛЬЗОВАТЕЛЬ ОЗНАКОМЛЕН С ЗАДАНИЕМ
                }
            }



            stopSelf();
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошиюбка СЛУЖБА СЛУЖБАService_Notifications_ДЛЯ ЧАТА  ДЛЯ ЧАТА onHandleWork Exception  PROCESS_ID   " + PROCESS_ID);

        }


        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        try {

            Log.i(getApplicationContext().getClass().getName(), "Стоп Стоп  Стоп !!!!!!!!!!! СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время " + new Date());

// TODO: 20.04.2021 повторыный запуск широковещательного приемника

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            Log.e(getApplicationContext().getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время " + new Date());

        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.i(getApplicationContext().getClass().getName(), "Стоп Стоп  Стоп !!!!!!!!!!! СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время " + new Date());

        return null;
    }

}