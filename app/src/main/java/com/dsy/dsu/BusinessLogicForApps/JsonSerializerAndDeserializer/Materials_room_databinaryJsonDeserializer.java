package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.dsy.dsu.BusinessLogicForApps.AllDatabases.ROOM.ROOMDatabase;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

public class Materials_room_databinaryJsonDeserializer extends JsonMainDeseirialzer {
    private Context context;

    // TODO: 04.07.2023  Главный метод Парсин таблицы ОРганизация
    public Integer методRoomJsonDeserializer(@NonNull  JsonNode jsonNodeParentMAP,
                                                  @NonNull Context context,
                                                  @NonNull  String  имяТаблицаAsync ,
                                                  @NonNull ROOMDatabase GetROOM
                                                  , @NonNull  String ФлагКакойСинхронизацияПерваяИлиНет  ) {
        CopyOnWriteArrayList<Integer> РезультатОперацииBurkUPDATE=new CopyOnWriteArrayList<>();

this.context=context;
     Flowable.fromIterable(jsonNodeParentMAP)
             .onBackpressureBuffer()
             .buffer(100)
                             .doOnNext(new Consumer<List<JsonNode>>() {
                                 @Override
                                 public void accept(List<JsonNode> jsonNodes) throws Throwable {
                                     // TODO: 04.07.2023 ЗАпускаем ТРАНЗАКЦИЮ
                                     Flowable.fromIterable(jsonNodes)
                                             .onBackpressureBuffer()
                                             .blockingIterable()
                                             .forEach(new java.util.function.Consumer<JsonNode>() {
                                         @Override
                                         public void accept(JsonNode jsonNode) {
                                             // TODO: 26.04.2023 Insert
                                             Integer ОперацияUpdate=0;

                                             if (ФлагКакойСинхронизацияПерваяИлиНет.equalsIgnoreCase("ПовторныйЗапускСинхронизации") ) {
                                                 // TODO: 04.07.2023  Обновление

                                 // TODO: 04.07.2023  Вставка  ПОСЛЕ ОБНОВЛЕНИЯ ЕСЛИ ОНО НЕ ПРОШЛО
                                 long ЕслиИлиНЕтUUID=        new  FindEmptyUUID().методПосикаROOMUUIDDeseializer(context, имяТаблицаAsync, GetROOM,jsonNode);
                                 if (ЕслиИлиНЕтUUID>0) {
                                     // TODO: 04.07.2023  Обновление
                                     ОперацияUpdate = ОбновлениеДанных(context, имяТаблицаAsync, GetROOM, jsonNode);
                                     if (ОперацияUpdate>0) {
                                         РезультатОперацииBurkUPDATE.add(ОперацияUpdate);
                                     }
                                     Log.d(this.getClass().getName(), "\n" + " class " +
                                             Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                             + имяТаблицаAsync  + " ОперацияUpdate " +ОперацияUpdate + " ОперацияUpdate " +ОперацияUpdate  );
                                 }else{
                                     // TODO: 04.07.2023  Вставка  ПОСЛЕ ОБНОВЛЕНИЯ ЕСЛИ ОНО НЕ ПРОШЛО
                                     Long     ОперацияInsert = ВставкаДанных(context, имяТаблицаAsync, GetROOM, jsonNode);
                                     if (ОперацияInsert>0) {
                                         РезультатОперацииBurkUPDATE.add(ОперацияInsert.intValue());
                                     }
                                     Log.d(this.getClass().getName(), "\n" + " class " +
                                             Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                             + имяТаблицаAsync  + " ОперацияUpdate " +ОперацияUpdate + " ОперацияInsert " +ОперацияInsert  );

                                 }


// TODO: 04.07.2023 ТОЛЬКО ВСТАВКА   // TODO: 04.07.2023 ТОЛЬКО ВСТАВКА  // TODO: 04.07.2023 ТОЛЬКО ВСТАВКА  // TODO: 04.07.2023 ТОЛЬКО ВСТАВКА  // TODO: 04.07.2023 ТОЛЬКО ВСТАВКА
                                             }else {

                                                     // TODO: 04.07.2023  ТОЛЬКО ВСТАВКА
                                                     Long ОперацияInsert = ВставкаДанных(context, имяТаблицаAsync, GetROOM, jsonNode);
                                                     if (ОперацияInsert>0) {
                                                         РезультатОперацииBurkUPDATE.add(ОперацияInsert.intValue());
                                                     }
                                                     Log.d(this.getClass().getName(), "\n" + " class " +
                                                             Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                             + имяТаблицаAsync  + " ОперацияUpdate " +ОперацияUpdate + " ОперацияInsert " +ОперацияInsert  );


                                             }
                                             Log.d(this.getClass().getName(), "\n" + " class " +
                                                     Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                     " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                     " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                     + имяТаблицаAsync  + " ОперацияUpdate " +ОперацияUpdate  + " РезультатОперацииBurkUPDATE " +РезультатОперацииBurkUPDATE);
                                         }
                                     });

                                 }
                             })
             .doAfterNext(new Consumer<List<JsonNode>>() {
                 @Override
                 public void accept(List<JsonNode> jsonNodes) throws Throwable {
                     // TODO: 04.07.2023  ОПЕРАЦИИ ПОСЛЕ УСПЕШНОЙ ОБРАБОТКИ 500 ЗАписей
                     if ( РезультатОперацииBurkUPDATE.size()>0) {
                         // TODO: 04.07.2023 После Успешной Операции Повышаем Версию ДАнных Для Данной Тваблицы  
                         Integer РезультатПовышенииВерсииДанных =
                                 new VersionCurentTable(context).writingDataVersionAfterLocalInsertOrUpdate(имяТаблицаAsync);
                         Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
                         // TODO: 04.07.2023 ЗАВЕРШАЕТ ТРНЗАКЦИЮ НА 50 СТРОЧКЕ
                     }
                     Log.d(this.getClass().getName(), "\n" + " class " +
                             Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNodeParentMAP " +jsonNodeParentMAP);
                 }
             })
             .doOnComplete(new Action() {
                 @Override
                 public void run() throws Throwable {
                     Log.d(this.getClass().getName(), "doOnComplete "  + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                             " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                 }
             })
             .onErrorComplete(new Predicate<Throwable>() {
                 @Override
                 public boolean test(Throwable throwable) throws Throwable {
                     throwable.printStackTrace();
                     Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                             " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                     new RecordNewErros(context).recordnewerror(throwable.toString(), this.getClass().getName(),
                             Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                     return false;
                 }
             })
             .doOnError(new Consumer<Throwable>() {
                 @Override
                 public void accept(Throwable throwable) throws Throwable {
                     throwable.printStackTrace();
                     Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                             " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                     new RecordNewErros(context).recordnewerror(throwable.toString(), this.getClass().getName(),
                             Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

                 }
             })
             .blockingSubscribe();

        Log.d(this.getClass().getName(), "\n" + " class " +
                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNodeParentMAP " +jsonNodeParentMAP);

 return  РезультатОперацииBurkUPDATE.size();
    }
    
    
    
    
    
    
    
    
    
    // TODO: 04.07.2023 ВСТАВКА
    Long ВставкаДанных(@NonNull Context context,
                       @NonNull String имяТаблицаAsync,
                       @NonNull ROOMDatabase GetROOM,
                       @NonNull JsonNode jsonNodeParentMAP) {
        // TODO: 26.04.2023 Insert
        Long ОперацияInsert=0l;
        try{
            this.context=context;

                // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
                String  SQlOperInsert=  " REPLACE INTO "+имяТаблицаAsync+" " +
                        " (    image ,files,date_update, uuid ,parent_uuid ,user_update ,current_table   )  " +
                        " VALUES ( ?,?,?,?,?,?,? ) ;";

            SupportSQLiteStatement sqLiteStatementInsert = методGetSqliteStatementForInsert(GetROOM, SQlOperInsert,jsonNodeParentMAP);

            // TODO: 04.07.2023  INSERT  Organization
             ОперацияInsert=      sqLiteStatementInsert.executeInsert();
                // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);

                    // TODO: 27.04.2023  повышаем верисю данных
                    Log.d(this.getClass().getName(), "\n" + " class " +
                            Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + имяТаблицаAsync  +
                            " ОперацияInsert " +ОперацияInsert);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ОперацияInsert;
    }
    // TODO: 04.07.2023 ОБНОВЛЕНИЕ
    Integer ОбновлениеДанных(@NonNull Context context,
                             @NonNull String имяТаблицаAsync,
                             @NonNull ROOMDatabase GetROOM,
                             @NonNull JsonNode jsonNodeParentMAP) {
        Integer ОперацияUpdate=0;
        try{
            this.context=context;
            // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
            String  SQlOperUpdate=  " UPDATE "+имяТаблицаAsync+" SET   " +
                    " image =?,files=?,date_update=?, uuid=? ,parent_uuid=? ,user_update=? ,current_table=?   "+
                    "   WHERE  uuid=?  ;";

            SupportSQLiteStatement sqLiteStatementInsert = методGetSqliteStatementForUpdate( SQlOperUpdate,jsonNodeParentMAP,GetROOM);

            // TODO: 04.07.2023  UPDARE Organization
            ОперацияUpdate=      sqLiteStatementInsert.executeUpdateDelete();
            // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
            if (ОперацияUpdate>0) {
                // TODO: 27.04.2023  повышаем верисю данных
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + имяТаблицаAsync  + " ОперацияUpdate " +ОперацияUpdate  +"  sqLiteStatementInsert " +sqLiteStatementInsert);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ОперацияUpdate;
    }



    @NonNull
    private SupportSQLiteStatement методGetSqliteStatementForInsert(@NonNull ROOMDatabase GetROOM,
                                                    String SQlOperInsert,
                                                    @NonNull JsonNode jsonNodeParentMAP) {
        SupportSQLiteStatement  sqLiteStatementInsert=null;
        try{
           sqLiteStatementInsert = GetROOM.compileStatement(SQlOperInsert);
            sqLiteStatementInsert.clearBindings();
            // TODO: 04.07.2023 цикл данных
            sqLiteStatementInsert=    методЗаполенияMaterialBinarySQLiteStatement(sqLiteStatementInsert, jsonNodeParentMAP );

            Log.d(this.getClass().getName(), "\n" + " class " +
                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                + sqLiteStatementInsert  + "sqLiteStatementInsert");
} catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return sqLiteStatementInsert;
    }

    private SupportSQLiteStatement методЗаполенияMaterialBinarySQLiteStatement( @NonNull SupportSQLiteStatement sqLiteStatementInsert,
                                                                        @NonNull JsonNode jsonNodeParentMAP) throws IOException {
        try{
            // TODO: 16.07.2023 Binary Image

            if (!jsonNodeParentMAP.get("image").isNull() && jsonNodeParentMAP.has("image") ) {
                sqLiteStatementInsert.bindBlob(1, jsonNodeParentMAP.get("image").binaryValue());//"date_update"

            }else{
                sqLiteStatementInsert.bindNull(1);
            }
            if (!jsonNodeParentMAP.get("files").isNull() && jsonNodeParentMAP.has("files")  ) {
                sqLiteStatementInsert.bindBlob(2, jsonNodeParentMAP.get("files").binaryValue());//"date_update"
            }else{
                sqLiteStatementInsert.bindNull(2);
            }
        sqLiteStatementInsert.bindString(3, jsonNodeParentMAP.get("date_update").asText().trim());//"date_update"
        sqLiteStatementInsert.bindLong(4, jsonNodeParentMAP.get("uuid").longValue());//"uuid"
        sqLiteStatementInsert.bindLong(5, jsonNodeParentMAP.get("parent_uuid").longValue());//"uuid"
        sqLiteStatementInsert.bindLong(6, jsonNodeParentMAP.get("user_update").intValue());//"uuid"
        sqLiteStatementInsert.bindLong(7, jsonNodeParentMAP.get("current_table").longValue());//"current_table"

        // TODO: 27.04.2023  повышаем верисю данных
        Log.d(this.getClass().getName(), "\n" + " class " +
                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  sqLiteStatementInsert;
    }

    @NonNull
    private SupportSQLiteStatement методGetSqliteStatementForUpdate(@NonNull String SQlOperInsert,
                                                             @NonNull JsonNode jsonNodeParentMAP,
                                                             @NonNull ROOMDatabase GetROOM) {
        SupportSQLiteStatement sqLiteStatementInsert = null;
        try{
            sqLiteStatementInsert =          GetROOM.compileStatement(SQlOperInsert);
            sqLiteStatementInsert=    методЗаполенияMaterialBinarySQLiteStatement(sqLiteStatementInsert, jsonNodeParentMAP );
            // TODO: 05.07.2023  Для Состыковки
            sqLiteStatementInsert.bindLong(9,jsonNodeParentMAP.get("uuid").longValue());//"uuid уже для UUID"
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + sqLiteStatementInsert  + "sqLiteStatementInsert");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return sqLiteStatementInsert;
    }

}
