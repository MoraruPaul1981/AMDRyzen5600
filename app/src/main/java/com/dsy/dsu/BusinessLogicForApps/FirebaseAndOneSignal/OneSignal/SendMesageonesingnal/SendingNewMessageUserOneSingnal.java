package com.dsy.dsu.BusinessLogicForApps.FirebaseAndOneSignal.OneSignal.SendMesageonesingnal;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.util.Date;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class SendingNewMessageUserOneSingnal {
 private    Context context;

    private JSONObject responseReturn;
    public SendingNewMessageUserOneSingnal( @NonNull Context context) {
        this.context = context;
    }

    JSONObject startSendingNewMessageUserOneSingnal(@NonNull Integer IdКомуСаообщениеOneSignal){
        try{
        SQLiteCursor CursorLomySendingMessages = null;

        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                +" IdКомуСаообщениеOneSignal" +IdКомуСаообщениеOneSignal);


        // TODO: 15.12.2021
        if(CursorLomySendingMessages.getCount()>0) {
            // TODO: 15.12.2021
            CursorLomySendingMessages.moveToFirst();
            String KeYfireBaseKomyNewMessage = CursorLomySendingMessages.getString(0).trim();

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " KeYfireBaseKomyNewMessage " + KeYfireBaseKomyNewMessage);

     Single.just( onesignalSendingNewMessage(KeYfireBaseKomyNewMessage))
                    .subscribeOn(Schedulers.single())
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :"
                                    + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
             .doOnSuccess(new Consumer<JSONObject>() {
                        @Override
                        public void accept(JSONObject jsonObject) throws Throwable {

                            responseReturn=jsonObject;
                            Log.d(context.getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " jsonObject " + jsonObject);
                        }
                    }).blockingSubscribe();
            // TODO: 19.03.2024
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " KeYfireBaseKomyNewMessage " + KeYfireBaseKomyNewMessage+
                    " responseReturn " +responseReturn);

        }
    } catch (Exception e ) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
   return responseReturn;

    }

    public JSONObject  onesignalSendingNewMessage(@NonNull  String KeYfireBaseKomyNewMessage) {
        final JSONObject[] responseReturn = new JSONObject[1];

    /*    new JSONObject("{" +
                " 'include_player_ids': ['" + KeYfireBaseKomyNewMessage + "']," +
                " 'app_id': '2a1819db-60c8-4ca3-a752-1b6cd9cadfa1'," +
                " 'android_background_data': 'true'," +
                " 'content_available': 'true'," +
                " 'data': {'grp_msg': 'android'} } "),*/
        try {

            OneSignal.postNotification(new JSONObject("{" +
                            " 'include_player_ids': ['" + KeYfireBaseKomyNewMessage + "']," +
                            " 'app_id': '2a1819db-60c8-4ca3-a752-1b6cd9cadfa1'," +
                            " 'android_background_data': 'true'," +
                            " 'content_available': 'true'," +
                            " 'data': {'grp_msg': 'android'} } "),

                            /*    OneSignal.postNotification(new JSONObject("{" +
                                                " 'include_player_ids': ['" + ТекущйUUIDКомуНужноСообщение + "']," +
                                                " 'app_id': '2a1819db-60c8-4ca3-a752-1b6cd9cadfa1'," +
                                                " 'android_background_data': 'true'," +
                                                " 'content_available': 'true'," +
                                                " 'data': {'grp_msg': 'android'} } "),*/
                    new OneSignal.PostNotificationResponseHandler() {
                        @Override
                        public void onSuccess(JSONObject response) {
                            // TODO: 13.01.2022  успешний пинг с пользователями которым нужноотпавить сообщения
                            responseReturn[0] = response;
                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName()
                                    + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " onSuccess  KeYfireBaseKomyNewMessage " + KeYfireBaseKomyNewMessage);
                        }

                        @Override
                        public void onFailure(JSONObject response) {
                            // TODO: 13.01.2022  НЕт пинга КЛЮЧ ИЛИ НЕТУ ИЛИ УСТАРЕЛЛ  пинг с пользователями которым нужноотпавить сообщения
                            responseReturn[0] = response;

                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName()
                                    + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " onSuccess  KeYfireBaseKomyNewMessage " + KeYfireBaseKomyNewMessage);
                        }
                    });

            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " onSuccess  KeYfireBaseKomyNewMessage " + KeYfireBaseKomyNewMessage);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return responseReturn[0];
    }

}
