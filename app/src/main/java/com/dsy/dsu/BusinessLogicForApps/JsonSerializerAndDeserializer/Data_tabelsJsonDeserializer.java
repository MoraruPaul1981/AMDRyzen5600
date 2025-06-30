package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

public class Data_tabelsJsonDeserializer extends JsonMainDeseirialzer {
    private Context context;

    // TODO: 04.07.2023  Главный метод Парсин таблицы ОРганизация
    public Integer методOrganizationJsonDeserializer(@NonNull  JsonNode jsonNodeParentMAP,
                                                  @NonNull Context context,
                                                  @NonNull  String  имяТаблицаAsync ,
                                                  @NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite
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
                                     if (!Create_Database_СамаБАзаSQLite.inTransaction()) {
                                         Create_Database_СамаБАзаSQLite.beginTransaction();
                                     }
                                     Flowable.fromIterable(jsonNodes)
                                             .onBackpressureBuffer()
                                             .blockingIterable()
                                             .forEach(new java.util.function.Consumer<JsonNode>() {
                                         @Override
                                         public void accept(JsonNode jsonNode) {
                                             // TODO: 26.04.2023 Insert
                                             Integer ОперацияUpdate=0;

                                             if (ФлагКакойСинхронизацияПерваяИлиНет.equalsIgnoreCase("ПовторныйЗапускСинхронизации") ) {

                     // TODO: 04.07.2023  Вставка  ПОСЛЕ ОБНОВЛЕНИЯ ЕСЛИ ОНО НЕ ПРОШЛО
                         // TODO: 04.07.2023  Вставка  ПОСЛЕ ОБНОВЛЕНИЯ ЕСЛИ ОНО НЕ ПРОШЛО
                         long ЕслиИлиНЕтUUID=        new  FindEmptyUUID().методПосикаUUIDDeseializer(context, имяТаблицаAsync, Create_Database_СамаБАзаSQLite,jsonNode);
                         if (ЕслиИлиНЕтUUID>0) {
                             // TODO: 04.07.2023  Обновление
                             ОперацияUpdate = ОбновлениеДанных(context, имяТаблицаAsync, Create_Database_СамаБАзаSQLite, jsonNode);
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
                             Long     ОперацияInsert = ВставкаДанных(context, имяТаблицаAsync, Create_Database_СамаБАзаSQLite, jsonNode);
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
                                                     Long ОперацияInsert = ВставкаДанных(context, имяТаблицаAsync, Create_Database_СамаБАзаSQLite, jsonNode);
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
                         // TODO: 04.07.2023 ЗАВЕРШАЕТ ТРНЗАКЦИЮ НА 50 СТРОЧКЕ
                         Create_Database_СамаБАзаSQLite.setTransactionSuccessful();
                         if (Create_Database_СамаБАзаSQLite.inTransaction()) {
                             Create_Database_СамаБАзаSQLite.endTransaction();
                         }
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

                     // TODO: 27.05.2025
                     if ( РезультатОперацииBurkUPDATE.size()>0) {
                         // TODO: 04.07.2023 После Успешной Операции Повышаем Версию ДАнных Для Данной Тваблицы
                         Integer РезультатПовышенииВерсииДанных =
                                 new VersionCurentTable(context).writingDataVersionAfterLocalInsertOrUpdate(имяТаблицаAsync);
                         Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
                     }

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
                     if (Create_Database_СамаБАзаSQLite.inTransaction()) {
                         Create_Database_СамаБАзаSQLite.endTransaction();
                     }
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
                     if (Create_Database_СамаБАзаSQLite.inTransaction()) {
                         Create_Database_СамаБАзаSQLite.endTransaction();
                     }
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
                       @NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite,
                       @NonNull JsonNode jsonNodeParentMAP) {
        // TODO: 26.04.2023 Insert
        Long ОперацияInsert=0l;
        try{
            this.context=context;
                // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
                String  SQlOperInsert=  "REPLACE INTO "+имяТаблицаAsync+" " +
                        "  (   fio," +
                        " d1 ,d2, d3 , d4 ,d5 ,d6 , d7 , d8 ,d9, d10 , " +
                        "  d11 , d12 , d13,d14 ,d15 , d16 , d17 ,d18 , d19 , " +
                        "  d20 ,d21, d22 , d23 ,d24,d25 , d26, d27 ,d28 , d29 ," +
                        "   d30 , d31  ,  " +
                        "  date_update,  uuid_tabel,current_table,  uuid, " +
                        "         user_update ,status_send,status_carried_out,prof  )   " +
                        " VALUES(" +
                        " ?,?,?,?,?,?,?,?,?," +
                        " ?,?,?,?,?,?,?,?,?,?," +
                        " ?,?,?,?,?,?,?,?,?,? ," +
                        " ?,?,?,?,?,?,?,?,?,? ," +
                        "? );";

            SQLiteStatement sqLiteStatementInsert = методGetSqliteStatementForInsert(Create_Database_СамаБАзаSQLite, SQlOperInsert,jsonNodeParentMAP);

            // TODO: 04.07.2023  INSERT  Organization
             ОперацияInsert=      sqLiteStatementInsert.executeInsert();
            // TODO: 06.07.2023 clear
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
                             @NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite,
                             @NonNull JsonNode jsonNodeParentMAP) {
        Integer ОперацияUpdate=0;
        try{
            this.context=context;
            // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
            String  SQlOperUpdate=  " UPDATE "+имяТаблицаAsync+" SET    fio=?," +
                    " d1 =?,d2=?, d3=? , d4=? ,d5=? ,d6=? , d7=? , d8=? ,d9=? , d10=? ,  " +
                    " d11 =?, d12=? , d13=? ,d14=? ,d15=? , d16=? , d17=? ,d18=? , d19=? ,  " +
                    " d20 =?,d21=?, d22=? , d23=? ,d24=? ,d25=? , d26=? , d27=? ,d28=? , d29=? ,  " +
                    " d30 =?, d31 =? ,  " +
                    " date_update=?,  uuid_tabel=?,current_table=?,  uuid=?,  " +
                    "  user_update=? ,status_send=?,status_carried_out=?,prof=?    WHERE  uuid=?  ;";

            SQLiteStatement sqLiteStatementInsert = методGetSqliteStatementForUpdate(Create_Database_СамаБАзаSQLite, SQlOperUpdate,jsonNodeParentMAP);

            // TODO: 04.07.2023  UPDARE Organization
            ОперацияUpdate=      sqLiteStatementInsert.executeUpdateDelete();
            // TODO: 06.07.2023 clear
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
    private SQLiteStatement методGetSqliteStatementForInsert(@NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite,
                                                    String SQlOperInsert,
                                                    @NonNull JsonNode jsonNodeParentMAP) {
        SQLiteStatement sqLiteStatementInsert = null;
        try{
            sqLiteStatementInsert = Create_Database_СамаБАзаSQLite.compileStatement(SQlOperInsert);
            sqLiteStatementInsert.clearBindings();
            // TODO: 04.07.2023 цикл данных
            // TODO: 04.07.2023 цикл данных Заполение ДАнными ДЛя Обновления
            методЗаполенияДнейТабеляДляInsert(jsonNodeParentMAP, sqLiteStatementInsert);

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
    @NonNull
    private SQLiteStatement методGetSqliteStatementForUpdate(@NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite,
                                                             String SQlOperInsert,
                                                             @NonNull JsonNode jsonNodeParentMAP) {
        SQLiteStatement sqLiteStatementInsert = null;
        try{
            sqLiteStatementInsert= Create_Database_СамаБАзаSQLite.compileStatement(SQlOperInsert);
            sqLiteStatementInsert.clearBindings();
            // TODO: 04.07.2023 цикл данных Заполение ДАнными ДЛя Обновления
            методЗаполенияДнейТабеляДляUpdate(jsonNodeParentMAP, sqLiteStatementInsert);



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

    private void методЗаполенияДнейТабеляДляUpdate(@NonNull JsonNode jsonNodeParentMAP, SQLiteStatement sqLiteStatementInsert) {
        try{

          //  sqLiteStatementInsert.bindLong(1, jsonNodeParentMAP.get("_id").longValue());//"current_table"

            sqLiteStatementInsert.bindLong(1, jsonNodeParentMAP.get("fio").longValue());//"current_table"
        // TODO: 05.07.2023  дни
        sqLiteStatementInsert.bindString(2, jsonNodeParentMAP.get("d1").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(3, jsonNodeParentMAP.get("d2").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(4, jsonNodeParentMAP.get("d3").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(5, jsonNodeParentMAP.get("d4").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(6, jsonNodeParentMAP.get("d5").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(7, jsonNodeParentMAP.get("d6").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(8, jsonNodeParentMAP.get("d7").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(9, jsonNodeParentMAP.get("d8").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(10, jsonNodeParentMAP.get("d9").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(11, jsonNodeParentMAP.get("d10").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(12, jsonNodeParentMAP.get("d11").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(13, jsonNodeParentMAP.get("d12").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(14, jsonNodeParentMAP.get("d13").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(15, jsonNodeParentMAP.get("d14").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(16, jsonNodeParentMAP.get("d15").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(17, jsonNodeParentMAP.get("d16").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(18, jsonNodeParentMAP.get("d17").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(19, jsonNodeParentMAP.get("d18").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(20, jsonNodeParentMAP.get("d19").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(21, jsonNodeParentMAP.get("d20").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(22, jsonNodeParentMAP.get("d21").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(23, jsonNodeParentMAP.get("d22").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(24, jsonNodeParentMAP.get("d23").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(25, jsonNodeParentMAP.get("d24").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(26, jsonNodeParentMAP.get("d25").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(27, jsonNodeParentMAP.get("d26").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(28, jsonNodeParentMAP.get("d27").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(29, jsonNodeParentMAP.get("d28").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(30, jsonNodeParentMAP.get("d29").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(31, jsonNodeParentMAP.get("d30").asText().trim());//"id1""
        sqLiteStatementInsert.bindString(32, jsonNodeParentMAP.get("d31").asText().trim());//"id1""
        // TODO: 05.07.2023   конец дни

        sqLiteStatementInsert.bindString(33, jsonNodeParentMAP.get("date_update").asText().trim());//"date_update"
        sqLiteStatementInsert.bindLong(34, jsonNodeParentMAP.get("uuid_tabel").longValue());//"current_table"
        sqLiteStatementInsert.bindLong(35, jsonNodeParentMAP.get("current_table").longValue());//"current_table"
        sqLiteStatementInsert.bindLong(36, jsonNodeParentMAP.get("uuid").longValue());//"uuid"
        sqLiteStatementInsert.bindLong(37, jsonNodeParentMAP.get("user_update").intValue());//"uuid"



            if (jsonNodeParentMAP.has("status_send")) {
                if (!jsonNodeParentMAP.get("status_send").isNull()) {
                    sqLiteStatementInsert.bindString(38, jsonNodeParentMAP.get("status_send").asText().trim());//"user_update"
                }else {
                    sqLiteStatementInsert.bindNull(38);
                }
            }else {
                sqLiteStatementInsert.bindNull(38);
            }




            sqLiteStatementInsert.bindLong(39, jsonNodeParentMAP.get("status_carried_out").intValue());//"id""


            // TODO: 25.09.2024
            // TODO: 25.09.2024
            if (  jsonNodeParentMAP.has("prof") ) {
                if (!jsonNodeParentMAP.get("prof").isNull()  ) {
                    sqLiteStatementInsert.bindLong(40, jsonNodeParentMAP.get("prof").longValue());//"id""
                }else {
                    sqLiteStatementInsert.bindNull(40);
                }
            }else{
                sqLiteStatementInsert.bindNull(40);
            }

            // TODO: 05.07.2023  Для Состыковки
            sqLiteStatementInsert.bindLong(41,jsonNodeParentMAP.get("uuid").longValue());//"uuid уже для UUID"

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
    }





    private void методЗаполенияДнейТабеляДляInsert(@NonNull JsonNode jsonNodeParentMAP, SQLiteStatement sqLiteStatementInsert) {
        try{
            sqLiteStatementInsert.bindLong(1, jsonNodeParentMAP.get("fio").longValue());//"current_table"
            // TODO: 05.07.2023  дни
            sqLiteStatementInsert.bindString(2, jsonNodeParentMAP.get("d1").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(3, jsonNodeParentMAP.get("d2").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(4, jsonNodeParentMAP.get("d3").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(5, jsonNodeParentMAP.get("d4").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(6, jsonNodeParentMAP.get("d5").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(7, jsonNodeParentMAP.get("d6").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(8, jsonNodeParentMAP.get("d7").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(9, jsonNodeParentMAP.get("d8").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(10, jsonNodeParentMAP.get("d9").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(11, jsonNodeParentMAP.get("d10").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(12, jsonNodeParentMAP.get("d11").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(13, jsonNodeParentMAP.get("d12").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(14, jsonNodeParentMAP.get("d13").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(15, jsonNodeParentMAP.get("d14").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(16, jsonNodeParentMAP.get("d15").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(17, jsonNodeParentMAP.get("d16").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(18, jsonNodeParentMAP.get("d17").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(19, jsonNodeParentMAP.get("d18").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(20, jsonNodeParentMAP.get("d19").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(21, jsonNodeParentMAP.get("d20").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(22, jsonNodeParentMAP.get("d21").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(23, jsonNodeParentMAP.get("d22").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(24, jsonNodeParentMAP.get("d23").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(25, jsonNodeParentMAP.get("d24").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(26, jsonNodeParentMAP.get("d25").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(27, jsonNodeParentMAP.get("d26").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(28, jsonNodeParentMAP.get("d27").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(29, jsonNodeParentMAP.get("d28").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(30, jsonNodeParentMAP.get("d29").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(31, jsonNodeParentMAP.get("d30").asText().trim());//"id1""
            sqLiteStatementInsert.bindString(32, jsonNodeParentMAP.get("d31").asText().trim());//"id1""
            // TODO: 05.07.2023   конец дни

            sqLiteStatementInsert.bindString(33, jsonNodeParentMAP.get("date_update").asText().trim());//"date_update"
            sqLiteStatementInsert.bindLong(34, jsonNodeParentMAP.get("uuid_tabel").longValue());//"current_table"
            sqLiteStatementInsert.bindLong(35, jsonNodeParentMAP.get("current_table").longValue());//"current_table"
            sqLiteStatementInsert.bindLong(36, jsonNodeParentMAP.get("uuid").longValue());//"uuid"
            sqLiteStatementInsert.bindLong(37, jsonNodeParentMAP.get("user_update").intValue());//"uuid"

            if (jsonNodeParentMAP.has("status_send")) {
                if (!jsonNodeParentMAP.get("status_send").isNull()) {
                    sqLiteStatementInsert.bindString(38, jsonNodeParentMAP.get("status_send").asText().trim());//"user_update"
                }else {
                    sqLiteStatementInsert.bindNull(38);
                }
            }else {
                sqLiteStatementInsert.bindNull(38);
            }






            sqLiteStatementInsert.bindLong(39, jsonNodeParentMAP.get("status_carried_out").intValue());//"id""


            // TODO: 25.09.2024
            // TODO: 25.09.2024
            if (  jsonNodeParentMAP.has("prof") ) {
                if (!jsonNodeParentMAP.get("prof").isNull()  ) {
                    sqLiteStatementInsert.bindLong(40, jsonNodeParentMAP.get("prof").longValue());//"id""
                }else {
                    sqLiteStatementInsert.bindNull(40);
                }
            }else{
                sqLiteStatementInsert.bindNull(40);
            }
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
    }

}











