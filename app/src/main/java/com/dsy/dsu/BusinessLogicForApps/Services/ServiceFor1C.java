package com.dsy.dsu.BusinessLogicForApps.Services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import com.dsy.dsu.BusinessLogicForApps.Class_Get_Json_1C;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Date;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceFor1C extends IntentService {

    // TODO: Rename actions, choose action names that describe tasks that this


    // Binder given to clients
    private LocalBinderДляПолучениеДанных1С binder = new LocalBinderДляПолучениеДанных1С();

    private Context context;

    public ServiceFor1C() {
        super("ServiceFor1C");
    }




    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
     //   return super.onBind(intent);
      return   binder;

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.dump(fd, writer, args);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  newBase.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context=newBase;
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
// TODO: 30.06.2022 сама не постредствено запуск метода
    }


    // TODO: 30.06.2022  пользовательский код


    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinderДляПолучениеДанных1С extends Binder {
        public ServiceFor1C getService() {
            // Return this instance of LocalService so clients can call public methods
            return ServiceFor1C.this;
        }
    }

    public StringBuffer МетодЗапускаПолучениеДанных1СВторойЭтапЛимитМарериалов(@NonNull Context context, @NonNull Intent intent) {
        StringBuffer БуферДанныхОт1СВторойЭтапЛимитаМатериалов=new StringBuffer();
        try{
            this.context=context;
            // TODO: 04.07.2022 получение данных от 1с
            Bundle bundleПришлиДанныеДляЗапускаСлужбыСинхрониазхцииНЕДляСебя=   intent.getExtras();
            // TODO: 04.07.2022
            String  АдресСерераДляПолучениеДанных1С=      bundleПришлиДанныеДляЗапускаСлужбыСинхрониазхцииНЕДляСебя.getString("адрессерверадляотгрускиматериалов","");
            Integer  IDПубличныйНеМойАСкемБылаПереписака=      bundleПришлиДанныеДляЗапускаСлужбыСинхрониазхцииНЕДляСебя.getInt("публичныйidдляотгрускиматериалов",0);
            String  ТаблицыДляОбработки1С=      bundleПришлиДанныеДляЗапускаСлужбыСинхрониазхцииНЕДляСебя.getString("ТаблицыДляОбработки1С","");
            Integer  ЦФОIDДляПотему1СПрисылаетДанныеВОтвет=      bundleПришлиДанныеДляЗапускаСлужбыСинхрониазхцииНЕДляСебя.getInt("цфовозвратматериалы",0);
            // TODO: 07.07.2022
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " АдресСерераДляПолучениеДанных1С " +АдресСерераДляПолучениеДанных1С+  "IDПубличныйНеМойАСкемБылаПереписака " +IDПубличныйНеМойАСкемБылаПереписака+
                    "  ТаблицыДляОбработки1С " +ТаблицыДляОбработки1С+
                    "  ЦФОIDДляПотему1СПрисылаетДанныеВОтвет " +ЦФОIDДляПотему1СПрисылаетДанныеВОтвет);
            /// ПубличныйIDДляФрагмента=8;
            //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С
            БуферДанныхОт1СВторойЭтапЛимитаМатериалов=
                    new Class_Get_Json_1C(context,АдресСерераДляПолучениеДанных1С.trim())//todo old debug "http://192.168.254.145/dsutest/hs/jsonto1c/listofdocuments"
                            .МетодПолучемJSONОт1СДляЛимитаМатериаловВторойЭтап(IDПубличныйНеМойАСкемБылаПереписака,ТаблицыДляОбработки1С,ЦФОIDДляПотему1СПрисылаетДанныеВОтвет);//
            // TODO: 21.06.2022  как пример 125  -это камиль//  /"sog"
                //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С --old
            
    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
        return БуферДанныхОт1СВторойЭтапЛимитаМатериалов;
    }


    // TODO: 07.07.2022 первый этап для лимита материалов


    public StringBuffer МетодЗапускаПолучениеДанных1СПервыйЭтапДЛяЛимитаМатериалов(@NonNull Context context, @NonNull Intent intent) {
        StringBuffer БуферДанныхОт1СПервыйЭтап=new StringBuffer();
        try{
            this.context=context;
            // TODO: 04.07.2022 получение данных от 1с
            Bundle bundleПришлиДанныеДляЗапускаСлужбыСинхрониазхцииНЕДляСебя=   intent.getExtras();
            // TODO: 04.07.2022
            String  АдресСерераДляПолучениеДанных1С=      bundleПришлиДанныеДляЗапускаСлужбыСинхрониазхцииНЕДляСебя.getString("адрессерверадляотгрускиматериалов","");
            Integer  IDПубличныйНеМойАСкемБылаПереписака=      bundleПришлиДанныеДляЗапускаСлужбыСинхрониазхцииНЕДляСебя.getInt("публичныйidдляотгрускиматериалов",0);
            String  ТаблицыДляОбработки1С=      bundleПришлиДанныеДляЗапускаСлужбыСинхрониазхцииНЕДляСебя.getString("ТаблицыДляОбработки1С","");

            // TODO: 07.07.2022
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " АдресСерераДляПолучениеДанных1С " +АдресСерераДляПолучениеДанных1С+  "IDПубличныйНеМойАСкемБылаПереписака " +IDПубличныйНеМойАСкемБылаПереписака+
                    "  ТаблицыДляОбработки1С " +ТаблицыДляОбработки1С);
            /// ПубличныйIDДляФрагмента=8;
            //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С
            БуферДанныхОт1СПервыйЭтап=
                    new Class_Get_Json_1C(context,АдресСерераДляПолучениеДанных1С.trim())//todo old debug "http://192.168.254.145/dsutest/hs/jsonto1c/listofdocuments"
                            .МетодПолучемJSONОт1СДляЛимитаМатериаловЭтапПервый(IDПубличныйНеМойАСкемБылаПереписака,ТаблицыДляОбработки1С);//
            // TODO: 21.06.2022  как пример 125  -это камиль//  /"sog"
            //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С --old

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return БуферДанныхОт1СПервыйЭтап;
    }





}