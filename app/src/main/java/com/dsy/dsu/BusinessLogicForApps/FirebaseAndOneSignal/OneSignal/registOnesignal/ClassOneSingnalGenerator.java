package com.dsy.dsu.BusinessLogicForApps.FirebaseAndOneSignal.OneSignal.registOnesignal;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;


import com.dsy.dsu.BusinessLogicForApps.FirebaseAndOneSignal.Firebase.MyFirebaseInstanceIDService;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossContext;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.onesignal.OneSignal;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ClassOneSingnalGenerator {
     private Context context;

    public ClassOneSingnalGenerator(@NonNull  Context context) {
        try{
            this.context=context;
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
    }



    // TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК ВОРК МЕНЕДЖЕР
    @SuppressLint("SuspiciousIndentation")
    public void getGetRegistaziyNewKeyForOnoSignal(@NonNull String КлючДляFirebaseNotification) {

        try {
            JbossContext public_contentменеджер=new JbossContext(context);
            // TODO: 23.12.2021 ЧЕТЫРЕ ПОПЫТКИ ПОДКЛЮЧЕНИЕ В СЕВРЕРУONESIGNAL
            Observable.interval(0, 20, TimeUnit.SECONDS, Schedulers.single())
                    .timeInterval()
                    .take(1,TimeUnit.MINUTES)
                  .doOnError(new Consumer<Throwable>() {
                      @Override
                      public void accept(Throwable throwable) throws Throwable {
                          // TODO: 06.01.2022
                          throwable.printStackTrace();
                          Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                  + Thread.currentThread().getStackTrace()[2].getLineNumber());
                          new RecordNewErros(context).recordnewerror(throwable.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                  Thread.currentThread().getStackTrace()[2].getLineNumber());
                      }
                  })
                  .takeWhile(new Predicate<Object>() {
                      @Override
                      public boolean test(Object o) throws Throwable {
                          // TODO: 28.03.2024
                     String     НовыйКлючОтOneSingnal =   getNewKeyTokernFronOneSignal(КлючДляFirebaseNotification );

                          // TODO: 26.12.2021
                          if (   НовыйКлючОтOneSingnal !=null) {

                              // TODO: 06.01.2022
                              методЗаписиNewKeyOneSignal( НовыйКлючОтOneSingnal );


                              Log.d(this.getClass().getName(),"\n"
                                      + " bremy: " + new Date()+"\n+"
                                      + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                      " УРА УРА !!!! НовыйКлючОтOneSingnal " +НовыйКлючОтOneSingnal);
                              // TODO: 04.01.2022
                              return false;
                          }else {
                              Log.d(this.getClass().getName(),"\n"
                                      + " bremy: " + new Date()+"\n+"
                                      + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                      " НЕТ ПОКА НовыйКлючОтOneSingnal  " +НовыйКлючОтOneSingnal);
                              return true;
                          }
                      }
                  })
                  .doOnComplete(new Action() {
                      @Override
                      public void run() throws Throwable {
                          // TODO: 06.01.2022
                          Log.w(context.getClass().getName(), "  onComplete МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal"  +"\n"+
                                  " Thread.currentThread().getName() " +Thread.currentThread().getName()); 
                      }
                  })
                    .toFlowable(BackpressureStrategy.BUFFER).subscribe();

        } catch (Exception e ) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }



    private String getNewKeyTokernFronOneSignal(@NonNull String КлючДляFirebaseNotification) {
        String НовыйКлючОтOneSingnal = null;
        try{

            String СтарыйКлючОтOneSignal=    getFindOldKeyOneSingal();


            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " СтарыйКлючОтOneSignal " +СтарыйКлючОтOneSignal + " НовыйКлючОтOneSingnal " +НовыйКлючОтOneSingnal);

        // TODO: 05.01.2022
         НовыйКлючОтOneSingnal =     МетодПолучениеКлючаОтСервераONESIGNALЕслиОЕстьКОнечноВНЕСКОЛЬКОПОпыток(КлючДляFirebaseNotification,СтарыйКлючОтOneSignal);
            // TODO: 12.04.2024

        Log.d(this.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+"  " +
                "   НовыйКлючОтOneSingnal[0] " +    НовыйКлючОтOneSingnal);

    } catch (Exception e ) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
        return НовыйКлючОтOneSingnal;
    }


    private void методЗаписиNewKeyOneSignal(@NonNull String НовыйКлючОтOneSingnal) {
        try{

            setWriterNewKeyInSqlLite(НовыйКлючОтOneSingnal);

            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " НовыйКлючОтOneSingnal " +НовыйКлючОтOneSingnal);
        } catch (Exception e ) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

     private void setWriterNewKeyInSqlLite(@NonNull String НовыйКлючОтOneSingnal ) {
        try{
         // TODO: 10.12.2022 проверка условия сстоит записывать ключновый или нет от SINGONE FIREBASE
         if (НовыйКлючОтOneSingnal !=null   ) {
                 // TODO: 04.01.2022  ПРИШЕЛ НОВЫЙ КЛЮЧ И ЕГО НАДО ЗАПИСАТЬ ДЛЯ ONESINGNAL
                 new WriterNewKeyOneSignal( context ,НовыйКлючОтOneSingnal ).writeingNewOneSingle();

                 Log.d(this.getClass().getName(),"\n"
                         + " bremy: " + new Date()+"\n+"
                         + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                         " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                         " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                         + " НовыйКлючОтOneSingnal " +НовыйКлючОтOneSingnal);

         }
     } catch (Exception e ) {
         e.printStackTrace();
         Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                 + Thread.currentThread().getStackTrace()[2].getLineNumber());
         new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                 Thread.currentThread().getStackTrace()[2].getMethodName(),
                 Thread.currentThread().getStackTrace()[2].getLineNumber());
     }
     }




     // TODO: 24.12.2021  МетодПолучение Статуса Ключа От Сервера OneSignal

    private String МетодПолучениеКлючаОтСервераONESIGNALЕслиОЕстьКОнечноВНЕСКОЛЬКОПОпыток(@NonNull String PublicKeyOneSignal,
                                                                                          @NonNull String СтарыйКлючОтOneSignal) {
        String  НовыйКлючОтOneSingnal = null;
        try{
            // todo OneSignal Initialization


            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
            // todo OneSignal Initialization
            OneSignal.initWithContext(context);
            OneSignal.setAppId(PublicKeyOneSignal);
            OneSignal.disablePush(false);
            // TODO: 28.03.2024 создаени сообщения
            FirebaseMessagingService firebaseMessagingService =new MyFirebaseInstanceIDService();
            firebaseMessagingService.onNewToken("Сообщения от Firebase Cloud Google ");

            /// firebaseMessagingService.onMessageSent("sgsggsgsgsg");
            ///   "+79158111806" "sousautodor@gmail.com"
            // TODO: 28.03.2024 полылаем ключ ОБЩИЙ
            Map<String, String> params = new HashMap<String, String>();
            OneSignal.sendTag("Authorization", "Basic "+ СтарыйКлючОтOneSignal +"" );//TODO "db29ca6e-455f-474c-a2cd-30f910bc9d1f"
            OneSignal.sendTag("Content-type", "application/json");
            OneSignal.sendTag("grp_msg", "android");
            OneSignal.sendTag("android_background_data", "true");
            OneSignal.sendTag("content_available", "true");

            //TODO srating......  oneSignal
            String ПушТОкен=    OneSignal.getDeviceState().getPushToken();
            НовыйКлючОтOneSingnal = OneSignal.getDeviceState().getUserId();

           /// firebaseMessagingService.onMessageSent("sgsggsgsgsg");

            // TODO: 15.12.2021
            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " НовыйКлючОтOneSingnal "
                    + НовыйКлючОтOneSingnal + "\n"+ПушТОкен+" BREMY  " +new Date().toLocaleString()+
                    "   OneSignal.getTriggerValueForKey(\"GT_PLAYER_ID\"); " );

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

        // TODO: 11.05.2021 запись ошибок

    }
        return НовыйКлючОтOneSingnal;
    }

// TODO: 12.04.2024






    @SuppressLint("Range")
    private String getFindOldKeyOneSingal() {
        String getOldkeyOneSignal = null;
        try{
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + "settings_tabels" + "");
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursorOldOneSignal = contentResolver.query(uri, new String[]{},
                    new String(" SELECT onesignal  FROM    settings_tabels      ORDER BY id  LIMIT   1  "),
                    new String[]{}, null);///   "  //// SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?

            if (cursorOldOneSignal.getCount() > 0) {
                cursorOldOneSignal.moveToFirst();
                // TODO: 11.01.2024
                getOldkeyOneSignal = cursorOldOneSignal.getString(cursorOldOneSignal.getColumnIndex("onesignal"));

            }

            // TODO: 12.04.2024 close Cursor
            if(cursorOldOneSignal!=null && ! cursorOldOneSignal.isClosed()){
                cursorOldOneSignal.close();
            }
            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                     " getOldkeyOneSignal "+getOldkeyOneSignal);
        } catch (Exception e ) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getOldkeyOneSignal;
    }



}


































// TODO: 15.12.2021 КЛАСС ЗАПИСЫВАЕТ ЕСЛИ ИЗМЕННЕНОЕ ЗНАЧЕНИЕ НА СЕРВЕРА ДЛЯ ТЕКУЩЕГО ПОЛЬЗВОАТЕЛЯ ИЗМЕНИЛОСЬ НА ONESIGNAL

