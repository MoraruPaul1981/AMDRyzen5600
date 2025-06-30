package com.dsy.dsu.BusinessLogicForApps.SynsProccessor;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicForApps.Jakson.GeneratorBinarySONSerializer;
import com.dsy.dsu.BusinessLogicForApps.Jakson.GeneratorJSONSerializer;
import com.dsy.dsu.BusinessLogicForApps.SharedPreferences.GetSharedPreferences;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.SynsProccessor.PrograsBarAsync.GetPrograssbarChangeIndicator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sous.backasync.launch.ModuleQuety;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.IntPredicate;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

import javax.net.ssl.SSLSocketFactory;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProccesorparallelSynch   {



   protected Context context;
    protected  ObjectMapper jsonGenerator;
    protected   SSLSocketFactory getsslSocketFactory2;


    protected Integer PublicID;

    protected  @NonNull LinkedHashMap<Integer,String> getHiltPortJboss;

    private SharedPreferences preferences;

    private  String  РежимЗапускаСинхронизации;

    private  CopyOnWriteArrayList<ConcurrentHashMap<String, String>> getBufferFromJbossServerAllTables;


    public ProccesorparallelSynch(@NonNull Context context,
                                  @NonNull ObjectMapper jsonGenerator,
                                  @NonNull SSLSocketFactory getsslSocketFactory2,
                                  @NonNull    CopyOnWriteArrayList<ConcurrentHashMap<String, String>> getBufferFromJbossServerAllTables,
                                  @NonNull Integer PublicID,
                                  @NonNull  LinkedHashMap<Integer,String> getHiltPortJboss) {



        this. context=context;
        this.   jsonGenerator=jsonGenerator;
        this.   getsslSocketFactory2=getsslSocketFactory2;
        this.  PublicID=PublicID;
        this.  getBufferFromJbossServerAllTables=getBufferFromJbossServerAllTables;
        this.  getHiltPortJboss=getHiltPortJboss;
    }

    public Long startingAsyncParallels() {
        final AtomicLong getstartingAsyncParallels=new AtomicLong(0l);
        try{
            // TODO: 30.09.2024
            preferences =context. getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
             РежимЗапускаСинхронизации = preferences.getString("РежимЗапускаСинхронизации" ,null);
            // TODO: 20.01.2025 сама синхрониаиця
            switch (РежимЗапускаСинхронизации){
// TODO: 20.01.2025 сама синхрониаиця
            case  "СамыйПервыйЗапускСинхронизации":
                    Flowable.fromIterable(getBufferFromJbossServerAllTables)
                            .parallel().runOn(Schedulers.from(Executors.newFixedThreadPool(2)))
                            .doOnNext(new Consumer<ConcurrentHashMap<String, String>>() {
                                @Override
                                public void accept(ConcurrentHashMap<String, String> operationMulti) throws Throwable {
                                    // TODO: 28.12.2024
                                    // TODO: 06.12.2023  запуск синхризуции по таблице конктерной
                                    getstartingAsyncParallels.addAndGet(getLooTablesPOSTANDGET(operationMulti));
                                    // TODO: 30.09.2024
                                    // TODO: 15.09.2023
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " getBufferFromJbossServerAllTables.size() " + getBufferFromJbossServerAllTables.size()
                                            +"\n" +" POOL NAMES "+Thread.currentThread().getName()+"\n"+
                                            " concurrentSkipListSetCompleteTable.get() "
                                            +getstartingAsyncParallels.get()
                                            +"\n" +" POOL NAMES "+Thread.currentThread().getName());
                                }
                            }).doOnError(new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Throwable {
                                    throwable.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(context).recordnewerror(throwable.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber()  );
                                }
                            }).doOnComplete(()->{
                                // TODO: 03.04.2025
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        +"\n" + "POOL NAME  " +Thread.currentThread().getName());

                            }).sequentialDelayError()
                            .doOnComplete(new Action() {
                                @Override
                                public void run() throws Throwable {
                                    // TODO: 29.04.2025
                                    // TODO: 29.04.2025
                                    if (getstartingAsyncParallels.get()>0) {
                                        new GetSharedPreferences(context).writinganewvaluePreferences();
                                    }
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            +"\n" + "POOL NAME  " +Thread.currentThread().getName()+"\n"+
                                            " concurrentSkipListSetCompleteTable.get() " +getstartingAsyncParallels.get()+"\n");
                                }
                            }) .blockingSubscribe();
                    // TODO: 15.09.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " getBufferFromJbossServerAllTables.size() " + getBufferFromJbossServerAllTables.size()
                            +"\n");
                    break;

                // TODO: 20.01.2025 сама синхрониаиця
              case "ПовторныйЗапускСинхронизации":
// TODO: 20.01.2025 сама синхрониаиця
                    Flowable.fromIterable(getBufferFromJbossServerAllTables)
                            .onBackpressureBuffer(1)
                            .doOnNext(new Consumer<ConcurrentHashMap<String, String>>() {
                                @Override
                                public void accept(ConcurrentHashMap<String, String> operationSingle) throws Throwable {
                                    // TODO: 28.12.2024
                                    // TODO: 06.12.2023  запуск синхризуции по таблице конктерной
                                    // TODO: 06.12.2023  запуск синхризуции по таблице конктерной
                                    getstartingAsyncParallels.addAndGet(getLooTablesPOSTANDGET(operationSingle));
                                    // TODO: 30.09.2024
                                    // TODO: 15.09.2023
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " getBufferFromJbossServerAllTables.size() " + getBufferFromJbossServerAllTables.size()
                                            +"\n" +" POOL NAMES "+Thread.currentThread().getName()+"\n"+
                                            " concurrentSkipListSetCompleteTable.get() " +getstartingAsyncParallels.get()+"\n"
                                            +"\n" +" POOL NAMES "+Thread.currentThread().getName());
                                }
                            }).doOnError(new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Throwable {
                                    throwable.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :"
                                            + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(context).recordnewerror(throwable.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber()  );
                                }
                            }).doOnComplete(()->{
                                // TODO: 29.04.2025
                                if (getstartingAsyncParallels.get()>0) {
                                    new GetSharedPreferences(context).writinganewvaluePreferences();
                                }
                                // TODO: 03.04.2025
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        +"\n" + "POOL NAME  " +Thread.currentThread().getName()+"\n"+
                                        " concurrentSkipListSetCompleteTable.get() " +getstartingAsyncParallels.get()+"\n");

                            })
                            .blockingSubscribe();
                    // TODO: 15.09.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " getBufferFromJbossServerAllTables.size() " + getBufferFromJbossServerAllTables.size()
                            +"\n"+"\n" + "РежимЗапускаСинхронизации " +РежимЗапускаСинхронизации);
                    break;
            }


            // TODO: 15.09.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " getBufferFromJbossServerAllTables.size() " + getBufferFromJbossServerAllTables.size()
                    +"\n");



            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "РежимЗапускаСинхронизации  " +РежимЗапускаСинхронизации);
      } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
        + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
        Thread.currentThread().getStackTrace()[2].getLineNumber()  );
        }
        return getstartingAsyncParallels.get();
        }

// TODO: 07.04.2024




public Long getLooTablesPOSTANDGET(@NonNull ConcurrentHashMap<String, String> stringStringMapRowSingle) {
        Long   РезультатТаблицыОбмена=0l;
        try{
        // TODO: 21.08.2023 Запуск Синхронизации после получение Версии
final   String getId= stringStringMapRowSingle.entrySet().stream().filter(e->e.getKey().equalsIgnoreCase("id")).map(Map.Entry::getValue).findFirst().get();
final  String getNameTable= stringStringMapRowSingle.entrySet().stream().filter(e->e.getKey().equalsIgnoreCase("name")).map(Map.Entry::getValue).findFirst().get();
final   String getVersionserver= stringStringMapRowSingle.entrySet().stream().filter(e->e.getKey().equalsIgnoreCase("versionserver")).map(Map.Entry::getValue).findFirst().get();
final   Long getVersionserverversion= Long.valueOf(stringStringMapRowSingle.entrySet().stream().filter(e->e.getKey().equalsIgnoreCase("versionserverversion")).map(Map.Entry::getValue).findFirst().get());

        // TODO: 27.12.2024 get Date parser
        DateFormat dateFormat =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",new Locale("ru"));
        Date  getParserVersionserver= dateFormat.parse(getVersionserver);

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
        " getVersionserver " +getVersionserver+ " getNameTable "
        + getNameTable);





        /////////////TODO ИДЕМ ПО ШАГАМ К ЗАПУСКИ СИНХРОГНИАЗЦИИ
        РезультатТаблицыОбмена= twoOfaKindGetAndPostJboss(getNameTable, getVersionserverversion, PublicID,getParserVersionserver);
        // TODO: 12.07.2023

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
        " getNameTable " +getNameTable+ " getVersionserver "
        + getVersionserver +" getVersionserverversion " +getVersionserverversion+
        "   РезультатТаблицыОбмена " + РезультатТаблицыОбмена);



        ///   TODO: 08.04.2024 Показываем пользовалю ПРоценты{
        // TODO: 24.12.2024
        new GetPrograssbarChangeIndicator(context).setAsyncrograssbarMap( getNameTable );


        Log.d(this.getClass().getName(), "\n"
        + " время: " + new Date() + "\n+" +
        " Класс в процессе... " + this.getClass().getName() + "\n" +
        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
        "  +  РезультатТаблицыОбмена " + РезультатТаблицыОбмена);



        Log.d(this.getClass().getName(), "\n"
        + " время: " + new Date() + "\n+" +
        " Класс в процессе... " + this.getClass().getName() + "\n" +
        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
        "  +  РезультатТаблицыОбмена " + РезультатТаблицыОбмена);

        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  РезультатТаблицыОбмена;
        }

// TODO: 07.04.2024



@SuppressLint("Range")
    Long twoOfaKindGetAndPostJboss(@NonNull String ИмяТаблицы,
                                   @NonNull  Long ВерсияДанныхсSqlServer,
                                   @NonNull  Integer PublicID,
                                   @NonNull Date     ВремяОтSqlServer) throws  Exception{

    AtomicLong getTwoOfaKindGetAndPostJboss=new AtomicLong(0);
        try  {
                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                        +" ИмяТаблицы " +ИмяТаблицы
                        + "\n"
                        +" ВерсияДанныхсSqlServer " +ВерсияДанныхсSqlServer
                        + "\n"
                        +" PublicID " +PublicID
                        + "\n"
                        +" ВремяОтSqlServer " +ВремяОтSqlServer);


            Single.fromCallable(()->{

// TODO: 24.09.2024 Запускаем Отправление и или ПОлучение данных  сервера JBoss
                // TODO: 08.04.2024 SEND SERVERR JBOSS POST
                Long getSendingToJboss =startSendingDatatoTheServerOnjboss(ИмяТаблицы,
                        ВерсияДанныхсSqlServer,
                        PublicID,
                        ВремяОтSqlServer);


                // TODO: 24.09.2024
                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                        + " getSendingToJboss "+getSendingToJboss);


                if (getSendingToJboss>0) {
                    getTwoOfaKindGetAndPostJboss.addAndGet(getSendingToJboss);
                }

                // TODO: 17.03.2025 Если Положительный ответ POST
                if (getSendingToJboss>0) {
                    // TODO: 13.02.2025 ПОСЛЕ ПОВЫШАЕМ ВЕРИСЮ ДАННЫХ ТОЛЬКО ДЛЯ POST после всей синхрониахции
                    workerUpVersionDataOnlyPOSTAsyncBack(   getSendingToJboss, ИмяТаблицы);
                }
                // TODO: 24.09.2024
                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        "  + getSendingToJboss) " + getSendingToJboss);




                return getSendingToJboss;

            }).doOnSuccess(result->{
                // TODO: 08.04.2024 через Retry Obsever множественое ображение  к серверу GET
                Long getsettingOtJboss =completedInsertorUpdateOperationsForEachWhile(ИмяТаблицы,
                        ВерсияДанныхсSqlServer,
                        PublicID,
                        ВремяОтSqlServer);

                // TODO: 24.09.2024
                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        "  + " + getsettingOtJboss +"\n"
                        + " getsettingOtJboss "+getsettingOtJboss);

                // TODO: 17.03.2025 Если Положительный ответ GET
                if (getsettingOtJboss>0) {
                    getTwoOfaKindGetAndPostJboss.addAndGet(getsettingOtJboss);
                }

                // TODO: 17.03.2025
                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "\n" + " getsettingOtJboss " +getsettingOtJboss);
            }).blockingSubscribe();



            // TODO: 17.03.2025
            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "getTwoOfaKindGetAndPostJboss " +getTwoOfaKindGetAndPostJboss.get());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  getTwoOfaKindGetAndPostJboss.get();
    }





 void workerUpVersionDataOnlyPOSTAsyncBack(@NonNull Long  getSendingDatatoTheServerOnjboss,
                                           @NonNull String ИмяТаблицы){



try{


    // TODO: 13.02.2025 только для POST  После всей сихронихации
    // TODO: 01.07.2023 После Успешно Посылании Данных На Сервер Повышаем Верисю Данных
    if (getSendingDatatoTheServerOnjboss>0) {
        
        методПослеУспешногоПолученияПовышаемВерсиюPOST(ИмяТаблицы ,getSendingDatatoTheServerOnjboss );
        // TODO: 13.02.2025
        // TODO: 13.02.2025
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                " getSendingDatatoTheServerOnjboss "+getSendingDatatoTheServerOnjboss);
    }
} catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

}











    // TODO: 07.04.2024

    @SuppressLint("SuspiciousIndentation")
    private     Long completedInsertorUpdateOperationsForEachWhile(@NonNull String ИмяТаблицы,
                                                                 @NonNull Long ВерсияДанныхсSqlServer,
                                                                 @NonNull  Integer  PublicID,
                                                                 @NonNull Date   ВремяОтSqlServer) {

// TODO: 07.04.2025

    final     AtomicLong getCompleteInsertsUpdatesOperations=new AtomicLong(0);
        try{
            final        AtomicLong getInsertsUpdatesCurrentOperation=new AtomicLong(0);
            // TODO: 02.11.2023  ПРИНИМАЕМ ДАННЫЕ ОТ СЕРВЕРА ПО ЧАСТЯМ
            IntStream.range(0,Integer.MAX_VALUE).noneMatch(new IntPredicate() {
                @Override
                public boolean test(int value) {

                    // TODO: 06.04.2025  
                    // TODO: 08.04.2024 выполения операции  GET ()
                    // TODO: 13.02.2025  сабираем все ответы при GET
                    getInsertsUpdatesCurrentOperation.getAndSet( getCursorWithVersionGET(ИмяТаблицы, ВерсияДанныхсSqlServer, PublicID, ВремяОтSqlServer));
                    // TODO: 06.04.2025
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            "getInsertsUpdatesCurrentOperation.get() " + getInsertsUpdatesCurrentOperation.get() + "\n" );
                    // TODO: 06.04.2025 EXIT  
                    if (getInsertsUpdatesCurrentOperation.get()>0) {
                        // TODO: 29.04.2025
                        // TODO: 13.02.2025  Повышаем версию данных только для GET
                        workerUpVersionDataOnlyGETAsyncBack(ИмяТаблицы);
                        // TODO: 29.04.2025
                        LongBinaryOperator ibo = (x, y) -> (x + y);
                        getCompleteInsertsUpdatesOperations.accumulateAndGet(getInsertsUpdatesCurrentOperation.get(),  ibo);
                        return false;
                    } else {
                        return true;
                    }
                }
            });


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "atomicLongInsertsUpdatesOperations.get() "
                    + getInsertsUpdatesCurrentOperation.get() +"\n"
                    + "atomicLongInsertsUpdatesOperations.get()"
                    +getInsertsUpdatesCurrentOperation.get()) ;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getCompleteInsertsUpdatesOperations.get();
    }


    void workerUpVersionDataOnlyGETAsyncBack(@NonNull String ИмяТаблицы){
        try{
            // TODO: 13.02.2025 только для POST  После всей сихронихации
                // TODO: 01.07.2023 После Успешно Посылании Данных На Сервер Повышаем Верисю Данных
                методПослеУспешногоПолученияПовышаемВерсиюGET(ИмяТаблицы  );
                // TODO: 09.10.202
                // TODO: 13.02.2025
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"ИмяТаблицы"+ИмяТаблицы);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



//todo  оправляем  даннеы на СЕРВЕР POST
    @SuppressLint("SuspiciousIndentation")
    private   Long startSendingDatatoTheServerOnjboss(@NonNull String ИмяТаблицы,
                                                      @NonNull Long ВерсияДанныхсSqlServer,
                                                      @NonNull  Integer  PublicID,
                                                      @NonNull Date   ВремяОтSqlServer) {


      Long startSendingDatatoTheServerOnjboss=0l;
        try{

                        // TODO: 08.04.2024 выполения операции  POST ()
            startSendingDatatoTheServerOnjboss=getCursorWithVersionPOST( ИмяТаблицы, ВерсияДанныхсSqlServer, PublicID, ВремяОтSqlServer);


                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                " cstartSendingDatatoTheServerOnjboss " + startSendingDatatoTheServerOnjboss);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return    startSendingDatatoTheServerOnjboss;
    }


































    @SuppressLint("Range")
    private  synchronized Long getCursorWithVersionPOST(@NonNull String ИмяТаблицы,
                                      @NonNull Long ВерсияДанныхсSqlServer,
                                      @NonNull  Integer  PublicID,
                                      @NonNull Date   ВремяОтSqlServer) {
        // TODO: 08.04.2024 get and post
        AtomicLong ResultatSendPOST=new AtomicLong(0l);
        // TODO: 13.02.2025
     try (Cursor КурсорДляАнализаВерсииДанныхАндройда = new VersionCurentTable(context).getVersionMODIFITATION_ClientTable(ИмяТаблицы); ){
        // TODO: 07.04.2024  получаем данные локалные лдля сравенния

        // TODO: 05.04.2024  получаем верисю данных андройд версия всехданныхс
        if (КурсорДляАнализаВерсииДанныхАндройда.getCount() > 0) {
            КурсорДляАнализаВерсииДанныхАндройда.moveToFirst();


            Long ВерсииНаАндройдеЛокальная =
                    КурсорДляАнализаВерсииДанныхАндройда.getLong(КурсорДляАнализаВерсииДанныхАндройда
                            .getColumnIndex("localversionandroid_version"));

            Long ВерсииНаАндройдеСерверная = КурсорДляАнализаВерсииДанныхАндройда.getLong(
                    КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("versionserveraandroid_version"));

            String ВремяДанныхSQliteНаАндройде = КурсорДляАнализаВерсииДанныхАндройда.getString(
                    КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("versionserveraandroid"));

            // TODO: 09.08.2023  даты заполяем таблиц с серверар
           Date ВремяДанныхНаАндройде = new FormattingVersionDastaSqlserver(context).formattingDateOnVersionSqlServerMirror(ВремяДанныхSQliteНаАндройде);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "ВерсииНаАндройдеЛокальная  " + ВерсииНаАндройдеЛокальная
                    + "\n" +
                    "ВерсииНаАндройдеСерверная  " + ВерсииНаАндройдеСерверная
                    + "\n" +
                    "ВремяДанныхSQliteНаАндройде  " + ВремяДанныхSQliteНаАндройде
                    + "\n" +
                    "ВремяДанныхНаАндройде  " + ВремяДанныхНаАндройде);


            //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР  POST  #1
            ResultatSendPOST.getAndSet(workerAsyncBackPOST(
                    ИмяТаблицы,
                    ВерсияДанныхсSqlServer,
                    PublicID,
                    ВерсииНаАндройдеЛокальная,
                    ВерсииНаАндройдеСерверная,
                    ВремяДанныхНаАндройде,
                    ВремяОтSqlServer));
            // TODO: 07.04.2024
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    "ResultatAndGETANDPOST " + ResultatSendPOST.get());


        }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
       return  ResultatSendPOST.get();
    }
// TODO: 07.04.2024
















    @SuppressLint("Range")
    private Long getCursorWithVersionGET(@NonNull String ИмяТаблицы,
                                          @NonNull Long ВерсияДанныхсSqlServer,
                                          @NonNull  Integer  PublicID,
                                          @NonNull Date   ВремяОтSqlServer) {
        // TODO: 08.04.2024 get and post
        Long  ResultatAndGET=0l;
        // TODO: 13.02.2025
        try (Cursor КурсорДляАнализаВерсииДанныхАндройда = new VersionCurentTable(context).getVersionMODIFITATION_ClientTable(ИмяТаблицы); ){
            // TODO: 07.04.2024  получаем данные локалные лдля сравенния

            // TODO: 05.04.2024  получаем верисю данных андройд версия всехданныхс
            if (КурсорДляАнализаВерсииДанныхАндройда.getCount() > 0) {
                КурсорДляАнализаВерсииДанныхАндройда.moveToFirst();


                Long ВерсииНаАндройдеЛокальная =
                        КурсорДляАнализаВерсииДанныхАндройда.getLong(КурсорДляАнализаВерсииДанныхАндройда
                                .getColumnIndex("localversionandroid_version"));

                Long ВерсииНаАндройдеСерверная = КурсорДляАнализаВерсииДанныхАндройда.getLong(
                        КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("versionserveraandroid_version"));

                String ВремяДанныхSQliteНаАндройде = КурсорДляАнализаВерсииДанныхАндройда.getString(
                        КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("versionserveraandroid"));

                // TODO: 09.08.2023  даты заполяем таблиц с серверар
                Date ВремяДанныхНаАндройде = new FormattingVersionDastaSqlserver(context).formattingDateOnVersionSqlServerMirror(ВремяДанныхSQliteНаАндройде);

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        "ВерсииНаАндройдеЛокальная  " + ВерсииНаАндройдеЛокальная
                        + "\n" +
                        "ВерсииНаАндройдеСерверная  " + ВерсииНаАндройдеСерверная
                        + "\n" +
                        "ВремяДанныхSQliteНаАндройде  " + ВремяДанныхSQliteНаАндройде
                        + "\n" +
                        "ВремяДанныхНаАндройде  " + ВремяДанныхНаАндройде);


                //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР  GET  #2
                ResultatAndGET= workerAsyncBackGET(
                        ИмяТаблицы,
                        ВерсияДанныхсSqlServer,
                        PublicID,
                        ВерсииНаАндройдеЛокальная,
                        ВерсииНаАндройдеСерверная,
                        ВремяДанныхНаАндройде,
                        ВремяОтSqlServer);
                // TODO: 07.04.2024
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        "ResultatAndGET " + ResultatAndGET+
                        "\n"+" ResultatAndGET " +ResultatAndGET);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ResultatAndGET;
    }
// TODO: 07.04.2024


















// TODO: 07.04.2024



    //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ   ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР




    Long workerAsyncBackPOST(@NonNull String ИмяТаблицы,
                             @NonNull Long ВерсияДанныхсSqlServer,
                             @NonNull  Integer  PublicID,
                             @NonNull Long ВерсииНаАндройдеЛокальная,
                             @NonNull Long  ВерсииНаАндройдеСерверная,
                             @NonNull Date  ВремяДанныхНаАндройде,
                             @NonNull Date   ВремяОтSqlServer) {
        //TODO
       Long workerAsyncBackPOST=0l;
        try {

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                    +" ИмяТаблицы " +ИмяТаблицы
                    + "\n"
                    +" ВерсияДанныхсSqlServer " +ВерсияДанныхсSqlServer
                    + "\n"
                    +" PublicID " +PublicID
                    + "\n"
                    +" ВерсииНаАндройдеЛокальная " +ВерсииНаАндройдеЛокальная+ "\n"
                    +" ВерсииНаАндройдеСерверная " +ВерсииНаАндройдеСерверная
                    + "\n"
                    +" ВремяДанныхНаАндройде " +ВремяДанныхНаАндройде
                    + "\n"
                    +" ВремяОтSqlServer " +ВремяОтSqlServer+"\n");



            // TODO: 08.04.2024 launch  post
                // TODO: 05.04.2024 post() sending
                // TODO: 05.10.2021  POST()-->
                if (ВерсииНаАндройдеЛокальная > ВерсииНаАндройдеСерверная) {
                    // TODO: 05.04.2024  отправлем только определенные таблицы
                    if (! ИмяТаблицы.equalsIgnoreCase("view_onesignal") &&
                            ! ИмяТаблицы.equalsIgnoreCase("chat_users") &&
                            ! ИмяТаблицы.equalsIgnoreCase("view_onesignal") &&
                            ! ИмяТаблицы.equalsIgnoreCase("track") &&
                            ! ИмяТаблицы.equalsIgnoreCase("cfo") ) {

                        Log.d(this.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                                +" ИмяТаблицы " +ИмяТаблицы
                                + "\n"
                                +" ВерсияДанныхсSqlServer " +ВерсияДанныхсSqlServer
                                + "\n"
                                +" PublicID " +PublicID
                                + "\n"
                                +" ВерсииНаАндройдеЛокальная " +ВерсииНаАндройдеЛокальная+ "\n"
                                +" ВерсииНаАндройдеСерверная " +ВерсииНаАндройдеСерверная
                                + "\n"
                                +" ВремяДанныхНаАндройде " +ВремяДанныхНаАндройде
                                + "\n"
                                +" ВремяОтSqlServer " +ВремяОтSqlServer+"\n");

                        ////// todo МЕТОД POST() в фоне    ////// todo МЕТОД POST
                        workerAsyncBackPOST=   МетодПосылаемДанныеНаСервервФоне(ИмяТаблицы, ВерсииНаАндройдеСерверная);

                        // TODO: 11.02.2025
                    }
                    // TODO: 05.04.2024  метод GET()   ПОЛУЧАЕМ ДАННЫЕ !!!!!
                    // TODO: 05.04.2024  метод GET()   ПОЛУЧАЕМ ДАННЫЕ !!!!!
                }



            // TODO: 08.04.2024 launch get


            // TODO: 05.04.2024  после обработки обоих методов post and get
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return workerAsyncBackPOST;
    }







    Long workerAsyncBackGET(@NonNull String ИмяТаблицы,
                                                  @NonNull Long ВерсияДанныхсSqlServer,
                                                  @NonNull  Integer  PublicID,
                                                  @NonNull Long ВерсииНаАндройдеЛокальная,
                                                  @NonNull Long  ВерсииНаАндройдеСерверная,
                                                  @NonNull Date  ВремяДанныхНаАндройде,
                                                  @NonNull Date   ВремяОтSqlServer) {
        //TODO
       Long workerAsyncBackGET=0l;
        try {

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                    +" ИмяТаблицы " +ИмяТаблицы
                    + "\n"
                    +" ВерсияДанныхсSqlServer " +ВерсияДанныхсSqlServer
                    + "\n"
                    +" PublicID " +PublicID
                    + "\n"
                    +" ВерсииНаАндройдеЛокальная " +ВерсииНаАндройдеЛокальная+ "\n"
                    +" ВерсииНаАндройдеСерверная " +ВерсииНаАндройдеСерверная
                    + "\n"
                    +" ВремяДанныхНаАндройде " +ВремяДанныхНаАндройде
                    + "\n"
                    +" ВремяОтSqlServer " +ВремяОтSqlServer+"\n");
            // TODO: 08.04.2024 launch get
                // TODO: 19.10.2021   GET()->
                if (ВерсияДанныхсSqlServer > ВерсииНаАндройдеСерверная ) {
                    // TODO: 05.04.2024
                        // TODO: 05.04.2024
                        if (!ИмяТаблицы.trim().equalsIgnoreCase("errordsu1")
                                && !ИмяТаблицы.trim().equalsIgnoreCase("settings_tabels")) {

                            ////// todo МЕТОД GET() в фоне    ////// todo МЕТОД GET
                            workerAsyncBackGET = МетодДанныеПолучаемНаСервервФоне(ИмяТаблицы, ВерсииНаАндройдеСерверная, PublicID);

                            // TODO: 12.02.2025
                            // TODO: 11.02.2025
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " workerAsyncBackGET  "
                                    + workerAsyncBackGET +
                                    "\n" + " ВремяДанныхНаАндройде " + ВремяДанныхНаАндройде + " ВремяДанныхНаАндройде " + ВремяДанныхНаАндройде);
                        }
                }

            // TODO: 08.04.2024 launch get


            // TODO: 05.04.2024  после обработки обоих методов post and get
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return workerAsyncBackGET;
    }































// TODO: 07.04.2024

    ///----------- ТУТ КОД УЖЕ ПОСЫЛАНИЕ ДАННЫХ НА СЕРВЕР МЕТОДУ POST (данные андройда посылаються на сервер)


    /////todo POST МЕТОД КОГДА НА АНДРОЙДЕ ВЕРСИЯ ДАННЫХ ВЫШЕ ЧЕМ НА СЕРВРЕР И МЫ  JSON ФАЙЛ ТУДА МЕТОД POST
    Long МетодПосылаемДанныеНаСервервФоне(@NonNull String ИмяТаблицы,
                                          @NonNull Long ВерсииНаАндройдеСерверная) {

        Long РезультатСинхронизации=0l;
        try {
            // TODO: 15.02.2022  ДАННЫЕ ДЛЯ ОТПРАВКИ НА СЕРВЕР
            Cursor cursorForSendServer= методГлавныйGetDataForAsync(ИмяТаблицы ,ВерсииНаАндройдеСерверная ,PublicID);
            /////TODO результаты   количество отправляемой информации на сервера
            if (cursorForSendServer!=null && cursorForSendServer.getCount() > 0) {
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " cursorForSendServer "+cursorForSendServer.getCount() );

                //////// todo упаковываем в  json ПЕРЕХОДИМ НА СЛЕДУЩИМ МЕТОД для отрправки на сервер метод POST() POST() POST() POST() POST() POST()POST()
                РезультатСинхронизации = МетодГенерацииJSON(cursorForSendServer, ИмяТаблицы );
                // TODO: 04.08.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " РезультатСинхронизации "+РезультатСинхронизации );

            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " РезультатСинхронизации "+РезультатСинхронизации );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  РезультатСинхронизации;
    }


// TODO: 07.04.2024

    //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА
    Long МетодДанныеПолучаемНаСервервФоне(@NonNull String ИмяТаблицы,
                                          @NonNull Long  ВерсииНаАндройдеСерверная,
                                          @NonNull Integer PublicID) {
        // TODO: 05.04.2024 get ()
        Long         ДанныесСервера = 0l;
        try {
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ИмяТаблицы  "+ИмяТаблицы
                    + "\n"+" ВерсииНаАндройдеСерверная  "+ВерсииНаАндройдеСерверная
                    + "\n"+" PublicID  "+PublicID+"\n");


            // TODO: 05.04.2024   GET()-> получаем данные с сервера
            ДанныесСервера = МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер(ВерсииНаАндройдеСерверная, ИмяТаблицы, PublicID);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ДанныесСервера  "+ДанныесСервера+ "\n"+" ИмяТаблицы  "+ИмяТаблицы
                    +"\n"+" ВерсииНаАндройдеСерверная " +ВерсииНаАндройдеСерверная);




            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ИмяТаблицы  "+ИмяТаблицы);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ДанныесСервера;
    }
// TODO: 07.04.2024


    // TODO: 07.09.2023 После Успешного ПОлучение и Успешной Отправки Выравниваем Версию  Данных AFTER

    private void методПослеУспешногоПолученияПовышаемВерсиюPOST(@NonNull String ИмяТаблицыОтАндройда_Локальноая, @NotNull   Long VersionFromSqlServer) {
        try{
            // TODO: 19.11.2022 ПОДНИМАЕМ ВЕРИСЮ ДАННЫХ
            Integer РезультатПовышенииВерсииДанных =
                    new VersionCurentTable(context).writingDataVersionAfterPost(ИмяТаблицыОтАндройда_Локальноая,  VersionFromSqlServer);


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ВерсияДанныхсСамогоSqlServer  "+
                    " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    // TODO: 07.09.2023 После Успешного ПОлучение и Успешной Отправки Выравниваем Версию  Данных AFTER

    private void методПослеУспешногоПолученияПовышаемВерсиюGET(@NonNull String ИмяТаблицыОтАндройда_Локальноая) {
        try{
            // TODO: 19.11.2022 ПОДНИМАЕМ ВЕРИСЮ ДАННЫХ
            Integer РезультатПовышенииВерсииДанных =
                    new VersionCurentTable(context).writingDataVersionAfterGet(ИмяТаблицыОтАндройда_Локальноая);


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ВерсияДанныхсСамогоSqlServer  "+
                    " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





// TODO: 07.04.2024

    @NonNull
    private Long МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер(@NonNull  Long ВерсииНаАндройдеСерверная,
                                                                  @NonNull String ИмяТаблицы,
                                                                  @NonNull  Integer ID) {
        Long  РезультатДанныесСервера=0l;
        try{
            Log.d(this.getClass().getName(), " ВерсииНаАндройдеСерверная" + ВерсииНаАндройдеСерверная+" ID "   + ID + "ИмяТаблицы"  + ИмяТаблицы);
            //////////TODO МЕТОД get
            РезультатДанныесСервера =
                    МетодПолучаемДаннныесСервера(ИмяТаблицы,
                            ID,
                            ВерсииНаАндройдеСерверная );

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" РезультатДанныесСервера  "+РезультатДанныесСервера);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатДанныесСервера;
    }
// TODO: 07.04.2024






    // TODO: 19.08.2021   КОнец Класс ВЫЧИСЛЯЕТ ЕЩЕ НЕ ОТРРВЛЕННЫЕ СООБЩЕНИЯ НА СЕРВЕР ИЗ ЧАТА
    /////МЕТОД КОГДА НА СЕРВЕРЕ ВЕРСИЯ ДАННЫХ ВЫШЕ И МЫ ПОЛУЧАЕМ ДАННЫЕ С СЕРВРА
    @SuppressLint("SuspiciousIndentation")
    Long МетодПолучаемДаннныесСервера( @NonNull String ИмяТаблицы,
                                       @NonNull Integer ID
            , @NonNull Long  ВерсииНаАндройдеСерверная) {

        Long РезультатФоновнойСинхронизации=0l;
        try {
            // TODO: 02.04.2024  Адресс и Порт Сервера Jboss
            // TODO: 02.04.2024  Адресс и Порт Сервера Jboss
            String   ИмяСерверИзХранилица = getHiltPortJboss.values().stream().map(m->String.valueOf(m)).findFirst().get();
            Integer    ПортСерверИзХранилица = getHiltPortJboss.keySet().stream().mapToInt(m->m).findFirst().getAsInt();


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ИмяСерверИзХранилица " + ИмяСерверИзХранилица+
                    " ПортСерверИзХранилица " +ПортСерверИзХранилица+"\n"+ " ВерсииНаАндройдеСерверная " +ВерсииНаАндройдеСерверная );
            // TODO: 10.11.2022  Получение JSON-потока
            byte[] BufferGetData =new CoreBinessLogics(context). методGetByteFromServerAsync(
                    ИмяТаблицы,
                    "application/gzip",
                    "Хотим Получить  JSON"
                    ,ВерсииНаАндройдеСерверная,
                    ID,
                    ИмяСерверИзХранилица
                    ,ПортСерверИзХранилица,getsslSocketFactory2);
            // TODO: 01.12.2023

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " BufferGetData " +BufferGetData );

            if (BufferGetData!=null) {
                //////TODO запускаем метод распарстивая JSON
                РезультатФоновнойСинхронизации=        МетодПарсингJSONФайлаОтСервреравФоне(BufferGetData, ИмяТаблицы);
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" РезультатФоновнойСинхронизации "+РезультатФоновнойСинхронизации+
                    " BufferGetData " +BufferGetData);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ////// начало запись в файл
        }
        return РезультатФоновнойСинхронизации;
    }

// TODO: 07.04.2024

    /////// TODO МЕТОД ПАСРИНГА ПРИШЕДШЕГО  С СЕРВЕРА ВНУТРИ ASYNSTASK В ФОНЕ
    @SuppressLint("SuspiciousIndentation")
    Long МетодПарсингJSONФайлаОтСервреравФоне(@NonNull  byte[] БуферGetByteJson,
                                              @NonNull  String имяТаблицаAsync) throws InterruptedException, JSONException {
        // TODO: 05.07.2023 result suync
        Long  РезультСинхрониазции=0l;
        try {
            Log.d(this.getClass().getName(), " имяТаблицаAsync " + имяТаблицаAsync + " БуферGetByteJson " +БуферGetByteJson.length );
            //TODO БУфер JSON от Сервера
            //  ObjectMapper jsonGenerator = new JbossContext(context).getGeneratorJackson();

            final JsonParser jsonParser= jsonGenerator.createParser(БуферGetByteJson,0,БуферGetByteJson.length);
            JsonNode jsonNodeParentMAP= jsonParser.readValueAsTree();


            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " jsonNodeParentMAP " +jsonNodeParentMAP );


            if (jsonNodeParentMAP!=null && jsonNodeParentMAP.size()>0) {
                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " jsonNodeParentMAP.size() " +jsonNodeParentMAP.size() );

                // TODO: 03.10.2023 все кроме байт
              РезультСинхрониазции=   методRowJsonRow(jsonNodeParentMAP,имяТаблицаAsync);

                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " БуферGetByteJson " +БуферGetByteJson );


            }
// TODO: 14.09.2023 exit
            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " РезультСинхрониазции " + РезультСинхрониазции);
            // TODO: 01.05.2023 clear
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return   РезультСинхрониазции ;
    }

// TODO: 07.04.2024


    // TODO: 13.09.2023   ROW
    Long методRowJsonRow(@NonNull  JsonNode jsonNodeParentMAP,
                         @NonNull String имяТаблицаAsync){
        Long РезультСинхрониазции=0l;
        try{
    
            int Проценты = 0;
            if (jsonNodeParentMAP.size()>0) {
                // TODO: 11.10.2022 callback
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasemirrorbinary/" + имяТаблицаAsync + "");
                ContentResolver resolver = context.getContentResolver();
                Bundle bundle=new Bundle();
                bundle.putSerializable("jsonNodeParentMAP", (Serializable) jsonNodeParentMAP);
                bundle.putString("nametable",имяТаблицаAsync);

                // TODO: 08.04.2024  оправляем на выполения  полученый от сервера json

                Bundle bundleРезультатОбновлениеМассовой =resolver.call(uri,имяТаблицаAsync,new StringBuffer(имяТаблицаAsync).toString(),bundle);
                РезультСинхрониазции=bundleРезультатОбновлениеМассовой.getLong("completeasync",0l)   ;
            }
            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  РезультСинхрониазции;
    }




    // TODO: 07.04.2024



    ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
    Long МетодГенерацииJSON(@NonNull  Cursor КурсорДляОтправкиДанныхНаСерверОтАндройда,
                            @NonNull String Таблицы) {
        Long ResultatSendingJsonJboss = 0l;
        try {
            if (КурсорДляОтправкиДанныхНаСерверОтАндройда!=null) {
                if (КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount()>0) {
                    КурсорДляОтправкиДанныхНаСерверОтАндройда.moveToFirst();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " КурсорДляОтправкиДанныхНаСерверОтАндройда "+КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount() );


                    //   ObjectMapper jsonGenerator = new JbossContext(context).getGeneratorJackson();
                    SimpleModule module = new SimpleModule();
                    // TODO: 11.09.2023  какая текущапя таблица
                    if (Таблицы.equalsIgnoreCase("materials_databinary")
                            || Таблицы.equalsIgnoreCase("data_chat") ) {
                        module.addSerializer(Cursor.class, new GeneratorBinarySONSerializer(context));
                    } else {
                        module.addSerializer(Cursor.class, new GeneratorJSONSerializer(context));
                    }
                    jsonGenerator.registerModule(module);

             /*       StringWriter stringWriterJSONAndroid=    new StringWriter();
                    jsonGenerator.getFactory().createGenerator( stringWriterJSONAndroid ).useDefaultPrettyPrinter();*/
                    // TODO: 17.03.2025 byte send
                  //  byte[] BufferJsonForSendServer=  jsonGenerator.writeValueAsBytes(КурсорДляОтправкиДанныхНаСерверОтАндройда);
                    // TODO: 17.03.2025 string send
                    //String  JsonForSendServer = jsonGenerator.writeValueAsString(КурсорДляОтправкиДанныхНаСерверОтАндройда);


                    ByteArrayOutputStream baos = new ByteArrayOutputStream(2028);
                    SequenceWriter seqWriter = jsonGenerator.writerWithDefaultPrettyPrinter().writeValues(baos);
                    seqWriter.write(КурсорДляОтправкиДанныхНаСерверОтАндройда);
                    seqWriter.flush();
                    seqWriter.close();
                    byte[] BufferJsonForSendServer=     baos.toByteArray();

                    // TODO: 23.03.2023 ID ПРОФЕСИИ
                    КурсорДляОтправкиДанныхНаСерверОтАндройда.close();
                  Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " BufferJsonForSendServer"+BufferJsonForSendServer );



                   // TODO: 14.03.2023 ПОСЫЛАЕМ ДАННЫЕ СГЕНЕРИРОНГО JSON НА СЕРВЕР ---->SERVER
                    ResultatSendingJsonJboss = new SendJsonCompliteToJboss().sendingJsonCompliteToJboss(context,BufferJsonForSendServer,
                            Таблицы,getHiltPortJboss,PublicID,getsslSocketFactory2 );

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления " +ResultatSendingJsonJboss );
                }else{
                    Log.d(this.getClass().getName(), " НЕ т данных  "+"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления " +ResultatSendingJsonJboss +
                            " КурсорДляОтправкиДанныхНаСерверОтАндройда " +КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ResultatSendingJsonJboss;
    }
// TODO: 07.04.2024






    // TODO: 15.02.2022 синхрогниазции таблиц
    @NonNull
    private Cursor методГлавныйGetDataForAsync( @NonNull  String Таблица,
                                                @NonNull Long ВерсияДанныхДляСравения,
                                                @NonNull Integer PublicId) {
        Cursor  cursorSendJboss=null;
        try{
            ModuleQuety moduleQuety=new ModuleQuety(context);
            switch (Таблица.trim()) {
                // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID
                case "settings_tabels":
                    cursorSendJboss   =moduleQuety.getModuleQuery(Таблица,
                            "  SELECT DISTINCT  * FROM "+Таблица+"   as gett  " +
                                    " WHERE   gett.current_table >   '"+ВерсияДанныхДляСравения+"' " +
                                    " AND gett.user_update = '"+PublicId+"';" , null);

                    Log.d(this.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+" Таблица "+Таблица);

                    break;
                // TODO: 27.05.2025
                case "data_notification":
                    cursorSendJboss   =moduleQuety.getModuleQuery(Таблица,
                            " SELECT DISTINCT  * FROM " +Таблица+" as gett" +
                                    " WHERE   gett.current_table >  '"+ВерсияДанныхДляСравения+"' ;"  , null);


                    Log.d(this.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+" Таблица "+Таблица);
                    break;
                // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID   // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID
                // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID
                default:
                    cursorSendJboss   =moduleQuety.getModuleQuery(Таблица,
                            " SELECT DISTINCT  * FROM " +Таблица+" as gett" +
                                    " WHERE   gett.current_table >  '"+ВерсияДанныхДляСравения+"'" +
                                    " AND gett.user_update = '"+PublicId + "' ;" , null);
                    Log.d(this.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+" Таблица "+Таблица);
                    break;
            }

            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    "cursorSendJboss   " + cursorSendJboss );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return cursorSendJboss;
    }

// TODO: 07.04.2024





}
