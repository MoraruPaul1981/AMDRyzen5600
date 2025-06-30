package com.dsy.dsu.BusinessLogicForApps.FirebaseAndOneSignal.Firebase;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.onesignal.OneSignal;

import java.util.Date;
import java.util.Map;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {

    public MyFirebaseInstanceIDService() {
        super();
        try{
            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " НовыйКлючОтOneSingnal "+
                    "   OneSignal.getTriggerValueForKey(\"GT_PLAYER_ID\"); " + OneSignal.getTriggers());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplication()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
    }

   @Override
    public void handleIntent(@NonNull Intent intent) {
       super.handleIntent(intent);
       Log.d(this.getClass().getName(), " handleIntent ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL !!!!!!!!!!!!  " +
               " MyFirebaseInstanceIDService  protected Intent public void handleIntent");
    }
    @NonNull
    @Override
    protected Intent getStartCommandIntent(@NonNull Intent intent) {
        Log.d(this.getClass().getName(), " getStartCommandIntent ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL !!!!!!!!!!!!  " +
                " MyFirebaseInstanceIDService  protected Intent getStartCommandIntent");
         return super.getStartCommandIntent(intent);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
      super.onMessageReceived(remoteMessage);
        try{
        Log.d(this.getClass().getName(), " onMessageReceived ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL !!!!!!!!!!!!  " +
                " MyFirebaseInstanceIDService   metod onNewToken "+remoteMessage.getMessageId()+"\n"+
              "  remoteMessage.getMessageType() "+  remoteMessage.getMessageType()+"\n"+
                "  remoteMessage.getRawData() "+remoteMessage.getRawData()+"\n"+
                 " remoteMessage.getSenderId()  " +remoteMessage.getSenderId());
        // TODO: 02.12.2021  ПРИШЛИ ДАННЫЕ ОТ FIREBASE CLOUD  НОВО СООБЕЩЕНИ И ИХ АНАЛИЗИРУЕМ И ЕСЛИ СООБЩЕНИЕ НЕ ПУСТОЕ МЫ УДАЛЯЕМ НОВТИВИКАЦИО ВИСЕВШЕЕ И СОЩЗДАЕНМ НВОЕ ЕЛСИ НЕСТЬ СООБЩЕНИ ТЕСТ СООБЩЕНИЯ
     Map<String, String> Уведоелмение= remoteMessage.getData();
        for (Map.Entry<String, String> entry: Уведоелмение.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();
            Log.d(this.getClass().getName(), " key "+key+"\n"+
                    " value "+value+"\n" );
            Log.d(this.getClass().getName(),  "ПРИШЛО СООБЩЕНИЕ ОТ FIREBASE CLOUD И СМОТРИМ СОДЕРЖИМОЕ "+"\n"
                    +value+"\n"+ " key " +key+"\n"+
                    " entry.getValue() " +entry.getValue());
            // TODO: 21.12.2022  запускас ONESINGLE FIREBASE
         if (entry.getValue().trim().matches("(.*)android(.*)")) {
                    Log.d(this.getClass().getName(),  " value ЕСЛИ ЕСТЬ СООБЩЕНИ НАПИСАННО ДРУГИМ ПОЛЬОВАТЛЕМ ТО УДАЛЯЕМ УВЕДОМЛЕНИ И СОЗАДЕМ НОВЫЙ СЛУЖЮА BRODCAST"+value+"\n"+ " key " +key);
             // TODO: 01.02.2022 заПУСКАЕМ сИНХРОНИАЗАЦИЮ С ВСЕХ ЛИСТ ТАБЕЛЕЙ


           /*  Integer  ПубличныйIDДляАсих=   new Class_GenerationsBack_PUBLIC_CURRENT_ID().getPublicIDAllApp(getApplicationContext());

             Data myDataSingleWorker = new Data.Builder()
                     .putInt("ПубличныйID", ПубличныйIDДляАсих)
                     .putBoolean("StartSingleWorker", true)
                     .build();
             // TODO: 02.08.2022
             // TODO: 02.08.2022
             new Class_Generator_One_WORK_MANAGER(getApplicationContext()).МетодОдноразовыйЗапускВоерМенеджера(getApplicationContext(),myDataSingleWorker);*/
             // TODO: 26.06.2022
             Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                     " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                     " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                     + " entry.getValue() "+entry.getValue() );
                    break;
           }
        }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplication()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        ////
        Log.d(this.getClass().getName(), " onDeletedMessages ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL !!!!!!!!!!!!   MyFirebaseInstanceIDService   metod onNewToken " );
    }

    @Override
    public void onMessageSent(@NonNull String s) {
        super.onMessageSent(s);

        try{
        /////

/*        String КлючДляFirebaseNotification="2a1819db-60c8-4ca3-a752-1b6cd9cadfa1";

   FirebaseMessaging.getInstance().subscribeToTopic("adroid");

            RemoteMessage rm = new RemoteMessage.Builder(КлючДляFirebaseNotification)
                    .setMessageId("myApp_" + + System.currentTimeMillis())
                    .addData("action", "chat")
                    .addData("destinataire", "ee")
                    .addData("emetteur","ee")
                    .setTtl(0)
                    .build();
            FirebaseMessaging.getInstance().send(rm);*/
        ////
        Log.d(this.getClass().getName(), " onMessageSent ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL !!!!!!!!!!!!   MyFirebaseInstanceIDService   metod onNewToken "+s.toString() );





    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplication()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

        // TODO: 11.05.2021 запись ошибок


    }
    }


    @Override
    public void onSendError(@NonNull String s, @NonNull Exception e) {
        super.onSendError(s, e);
        Log.e(this.getClass().getName(), " onSendError  ОШИБКА  ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL " +
                "!!!!!!!!!!!!  MyFirebaseInstanceIDService   metod onNewToken e  "+e.toString()+"\n"+
                " s " +s.toString());
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d(this.getClass().getName(), " onNewToken  ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL !!!!!!!!!!!!   MyFirebaseInstanceIDService   metod onNewToken "+s.toString());

    }


}
