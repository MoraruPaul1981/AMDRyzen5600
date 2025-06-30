package com.dsy.dsu.OrdersTransportsApp.Background;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;
import com.google.firebase.annotations.concurrent.Background;
import com.sous.backasync.launch.ModuleQuety;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Random;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceOrserTransportService extends IntentService {

    private GetCursorOrderTransport getCursorOrderTransport;
    private SubClassDeleteУдаланиеRow subClassDeleteУдаланиеRow;

   protected LocalBinderOrderTransport localBinderOrderTransport= new LocalBinderOrderTransport();
    public ServiceOrserTransportService() {

        super(ServiceOrserTransportService.class.getClass().getName());

    }

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 16.04.2025
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() );
        getCursorOrderTransport =new GetCursorOrderTransport();
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        try{
            LocalBinderOrderTransport.setCallingWorkSourceUid(new Random().nextInt());
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
        return localBinderOrderTransport;

    }



    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  newBase.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try{
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }
    public class LocalBinderOrderTransport extends Binder {
        public ServiceOrserTransportService getService() {
            try {
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
            return ServiceOrserTransportService.this;
        }


        @Background
        @BinderThread
        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
           try{
               Log.d(getApplicationContext().getClass().getName(), "\n"
                       + " время: " + new Date() + "\n+" +
                       " Класс в процессе... " + this.getClass().getName() + "\n" +
                       " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  " mapBoundService " +
                       " reply " +reply  + " data " +data );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
            return    true;
        }




        // TODO: 04.05.2023 Главный метод Службы Заказы Транспота
        @BinderThread
        @Background
        public  Integer методВиндингУдалениеЗаказа(@NonNull  Long UUIDДляУдалениеRow  ){
            Integer РезультатаУдалениеRow=0;
            try{
                SubClassDeleteУдаланиеRow subClassDeleteУдаланиеRow=new SubClassDeleteУдаланиеRow();
                 РезультатаУдалениеRow=         subClassDeleteУдаланиеRow.методУдалениеВыбранойRow(UUIDДляУдалениеRow);
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  " mapBoundService " +
                        " mapRetry " +РезультатаУдалениеRow + " Thread 1   "
                        + Thread.currentThread().getName()+ " Thread 2"
                        + Thread.getAllStackTraces().values().toString() );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return  РезультатаУдалениеRow;
        }






        // TODO: 04.05.2023 Главный метод Службы Заказы Транспота LIKE


        // TODO: 01.06.2023  метод получение трех значений день месяц год
        public LinkedHashMap<String,String> методGetТриЗначениеГодМесяцДень() {
            LinkedHashMap<String,String> linkedHashMapДеньМесяцГод=new LinkedHashMap<>();
            try{
                Calendar myCalмесяц = new GregorianCalendar();
                Calendar myCalдень = new GregorianCalendar();
                SimpleDateFormat simpleDateFormatмесяц=    new SimpleDateFormat("MM", new Locale("ru"));
                SimpleDateFormat simpleDateFormatдень=    new SimpleDateFormat("dd", new Locale("ru"));
               String Dateмесяц=      simpleDateFormatмесяц.format(myCalмесяц.getTime());
               String Dateдень=       simpleDateFormatдень.format(myCalдень.getTime());
                Integer Год = myCalмесяц.get(Calendar.YEAR);
                // TODO: 01.06.2023  Заполяем
                linkedHashMapДеньМесяцГод.put("День", Dateдень);
                linkedHashMapДеньМесяцГод.put("Месяц",  Dateмесяц);
                linkedHashMapДеньМесяцГод.put("Год",Год.toString());
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " linkedHashMapДеньМесяцГод " +linkedHashMapДеньМесяцГод);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  linkedHashMapДеньМесяцГод;
        }
    }








































    // TODO: 25.04.2023  КЛАСС РАБОТЫ ЗАКАЗА ТРАНСПОРТА
   protected class GetCursorOrderTransport {
        // TODO: 03.05.2023 GEt Cursor
        Cursor getCursorOrderTransport(@NonNull String selection, @NonNull String Текущаятаблицы){
            Cursor cursor = null;
            try{
                // TODO: 14.05.2025
                ModuleQuety moduleQuety = new ModuleQuety(getApplicationContext());
                cursor = moduleQuety.getModuleQuery(Текущаятаблицы, selection, null);
                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " cursor " + cursor);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return  cursor;
        }
        // TODO: 25.04.2023 END CLASS   GetCursorOrderTransport  // TODO: 25.04.2023 END CLASS   GetCursorOrderTransport  // TODO: 25.04.2023 END CLASS   GetCursorOrderTransport  // TODO: 25.04.2023 END CLASS   GetCursorOrderTransport
        // TODO: 25.04.2023 END CLASS   GetCursorOrderTransport   // TODO: 25.04.2023 END CLASS   GetCursorOrderTransport  // TODO: 25.04.2023 END CLASS   GetCursorOrderTransport  // TODO: 25.04.2023 END CLASS   GetCursorOrderTransport
    }



    // TODO: 15.06.2023  Удаление Выбраного СТрочки ЗАказа
    protected  class SubClassDeleteУдаланиеRow{
        Integer методУдалениеВыбранойRow(@NonNull  Long UUIDДляУдалениеRow){
            Integer РезультатаУдалениеRow=0;
            try{
                String ТаблицаОбработки="order_tc";
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" +ТаблицаОбработки + "");
                ContentValues contentValuesУданиеЗаказаТраспорта=new ContentValues();
                contentValuesУданиеЗаказаТраспорта.put("status",5);

                String Дата =     new GetMainDateForApp(getApplicationContext()).getMainDateForApp();
                contentValuesУданиеЗаказаТраспорта.put("date_update", Дата);


                Long Версия = new VersionCurentTable(getApplicationContext()).upVersionCurentTable(    ТаблицаОбработки);
                contentValuesУданиеЗаказаТраспорта.put("current_table", Версия);
                // TODO: 12.04.2023 удаление ЗАказа Траспрта
                ContentResolver contentResolver=getApplicationContext().getContentResolver();
                РезультатаУдалениеRow= contentResolver.update(uri, contentValuesУданиеЗаказаТраспорта,
                        "uuid=?",new String[]{String.valueOf(UUIDДляУдалениеRow)});
                // TODO: 15.06.2023
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " UUIDДляУдалениеRow" +UUIDДляУдалениеRow+ "\n"+" РЕЗУЛЬТАТ РезультатаУдалениеRow  " +  РезультатаУдалениеRow);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультатаУдалениеRow;

        }
    }


}