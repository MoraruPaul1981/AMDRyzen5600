package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.SaveBitmapToFile.ClassSaveBitmapToFile;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

public class Materials_databinaryJsonDeserializer extends JsonMainDeseirialzer {
    private Context context;

    // TODO: 04.07.2023  Главный метод Парсин таблицы ОРганизация
    public Integer методMaterilaBinaryJsonDeserializer(@NonNull  JsonNode jsonNodeParentMAP,
                                                       @NonNull Context context,
                                                       @NonNull  String  имяТаблицаAsync ,
                                                       @NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite
                                                  , @NonNull  String ФлагКакойСинхронизацияПерваяИлиНет  ) {
        CopyOnWriteArrayList<Integer> РезультатОперацииBurkUPDATE=new CopyOnWriteArrayList<>();
try{
this.context=context;
        // TODO: 04.07.2023 ЗАпускаем ТРАНЗАКЦИЮ
        if (!Create_Database_СамаБАзаSQLite.inTransaction()) {
            Create_Database_СамаБАзаSQLite.beginTransaction();
        }
    Flowable.fromIterable(jsonNodeParentMAP)
            .onBackpressureBuffer()
            .doOnNext(new Consumer<JsonNode>() {
                @Override
                public void accept(JsonNode jsonNodeBinaryMaretial) throws Throwable {
                    // TODO: 31.10.2023
                    if (ФлагКакойСинхронизацияПерваяИлиНет.equalsIgnoreCase("ПовторныйЗапускСинхронизации") ) {
                        // TODO: 04.07.2023  Обновление
                        // TODO: 04.07.2023  Вставка  ПОСЛЕ ОБНОВЛЕНИЯ ЕСЛИ ОНО НЕ ПРОШЛО
                        long ЕслиИлиНЕтUUID=        new  FindEmptyUUID().методПосикаUUIDDeseializer(context, имяТаблицаAsync, Create_Database_СамаБАзаSQLite,jsonNodeBinaryMaretial);
                        if (ЕслиИлиНЕтUUID>0) {
                            // TODO: 04.07.2023  Обновление
                            Integer  ОперацияUpdate = ОбновлениеДанных(context, имяТаблицаAsync, Create_Database_СамаБАзаSQLite, jsonNodeBinaryMaretial);
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
                            Long     ОперацияInsert = ВставкаДанных(context, имяТаблицаAsync, Create_Database_СамаБАзаSQLite, jsonNodeBinaryMaretial);
                            if (ОперацияInsert>0) {
                                РезультатОперацииBurkUPDATE.add(ОперацияInsert.intValue());
                            }
                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + имяТаблицаAsync   + " ОперацияInsert " +ОперацияInsert  );

                        }


// TODO: 04.07.2023 ТОЛЬКО ВСТАВКА   // TODO: 04.07.2023 ТОЛЬКО ВСТАВКА  // TODO: 04.07.2023 ТОЛЬКО ВСТАВКА  // TODO: 04.07.2023 ТОЛЬКО ВСТАВКА  // TODO: 04.07.2023 ТОЛЬКО ВСТАВКА
                    }else {

                        // TODO: 04.07.2023  ТОЛЬКО ВСТАВКА
                        Long ОперацияInsert = ВставкаДанных(context, имяТаблицаAsync, Create_Database_СамаБАзаSQLite, jsonNodeBinaryMaretial);
                        if (ОперацияInsert>0) {
                            РезультатОперацииBurkUPDATE.add(ОперацияInsert.intValue());
                        }
                        Log.d(this.getClass().getName(), "\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + имяТаблицаAsync   + " ОперацияInsert " +ОперацияInsert  );


                    }
                }
            }).doAfterNext(new Consumer<JsonNode>() {
                @Override
                public void accept(JsonNode jsonNode) throws Throwable {
                    // TODO: 04.07.2023  ОПЕРАЦИИ ПОСЛЕ УСПЕШНОЙ ОБРАБОТКИ 500 ЗАписей
                    if ( РезультатОперацииBurkUPDATE.size()>0) {
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
            }).doOnComplete(new Action() {
                @Override
                public void run() throws Throwable {
                    // TODO: 14.09.2023 операций insert and update
                    if ( РезультатОперацииBurkUPDATE.size()>0) {
                        // TODO: 04.07.2023 После Успешной Операции Повышаем Версию ДАнных Для Данной Тваблицы
                        Integer РезультатПовышенииВерсииДанных =
                                new VersionCurentTable(context).writingDataVersionAfterLocalInsertOrUpdate(имяТаблицаAsync);
                        Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);

                    }}
            }).blockingSubscribe();



        Log.d(this.getClass().getName(), "\n" + " class " +
                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNodeParentMAP " +jsonNodeParentMAP);


        Log.d(this.getClass().getName(), "\n" + " class " +
                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                + имяТаблицаAsync    + " РезультатОперацииBurkUPDATE " +РезультатОперацииBurkUPDATE);


    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context.getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
 return  РезультатОперацииBurkUPDATE.size();
    }
    
    
    
    
    
    
    
    
    
    // TODO: 04.07.2023 ВСТАВКА
    @SuppressLint("SuspiciousIndentation")
    Long ВставкаДанных(@NonNull Context context,
                       @NonNull String имяТаблицаAsync,
                       @NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite,
                       @NonNull JsonNode jsonNodeParentMAP) {
        // TODO: 26.04.2023 Insert
        Long ОперацияInsert=0l;
        try{
            this.context=context;

                // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
                String  SQlOperInsert=  " REPLACE INTO "+имяТаблицаAsync+" " +
                        " (     image ,files,date_update, uuid ,parent_uuid ,user_update ,current_table   )  " +
                        " VALUES ( ?,?,?,?,?,?,? ) ;";

            SQLiteStatement sqLiteStatementInsert = методGetSqliteStatementForInsert(Create_Database_СамаБАзаSQLite, SQlOperInsert,jsonNodeParentMAP);

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
                             @NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite,
                             @NonNull JsonNode jsonNodeParentMAP) {
        Integer ОперацияUpdate=0;
        try{
            this.context=context;
            // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
            String  SQlOperUpdate=  " UPDATE "+имяТаблицаAsync+" SET  " +
                    " image =?,files=?,date_update=?, uuid=? ,parent_uuid=? ,user_update=? ,current_table=?   "+
                    "   WHERE  uuid=?  ;";

            SQLiteStatement sqLiteStatementInsert = методGetSqliteStatementForUpdate( SQlOperUpdate,jsonNodeParentMAP,Create_Database_СамаБАзаSQLite);

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
    private SQLiteStatement методGetSqliteStatementForInsert(@NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite,
                                                    String SQlOperInsert,
                                                    @NonNull JsonNode jsonNodeParentMAP) {
        SQLiteStatement  sqLiteStatementInsert=null;
        try{
           sqLiteStatementInsert = Create_Database_СамаБАзаSQLite.compileStatement(SQlOperInsert);
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

    private SQLiteStatement методЗаполенияMaterialBinarySQLiteStatement( @NonNull SQLiteStatement sqLiteStatementInsert,
                                                                        @NonNull JsonNode jsonNodeParentMAP) throws IOException {
        try{

            // TODO: 16.07.2023  decode Binary Image
            ClassDecodePhotos classDecodePhotos=new ClassDecodePhotos();
// TODO: 09.10.2023 save bitmap to file
            ClassSaveBitmapToFile classSaveBitmapToFile=new ClassSaveBitmapToFile();

            if (  jsonNodeParentMAP.has("image") ) {
            if (!jsonNodeParentMAP.get("image").isNull()  ) {
                byte[] Photo = jsonNodeParentMAP.get("image").binaryValue();
                // TODO: 26.10.2023 вставляем в базу данных
                byte[] ByteFOrBlobImage = classDecodePhotos.методDecodePhotos(Photo);
                sqLiteStatementInsert.bindBlob(1, ByteFOrBlobImage);//"date_update"

                // TODO: 11.09.2023  ЗАПИСЬ НА ДИСК image
                classSaveBitmapToFile.методSavePhotoFromServer(context, jsonNodeParentMAP.get("uuid").longValue(), Photo);
                // TODO: 25.09.2024
            }else{
                    sqLiteStatementInsert.bindNull(1);
                }
            }else{
                sqLiteStatementInsert.bindNull(1);
            }
           if (  jsonNodeParentMAP.has("files") ) {
            if (!jsonNodeParentMAP.get("files").isNull()  ) {
                sqLiteStatementInsert.bindBlob(2, jsonNodeParentMAP.get("files").binaryValue());//"date_update"
            }else{
                    sqLiteStatementInsert.bindNull(2);
                }
            }else{
                sqLiteStatementInsert.bindNull(2);
            }
            if (jsonNodeParentMAP.has("date_update")) {
                sqLiteStatementInsert.bindString(3, jsonNodeParentMAP.get("date_update").asText().trim());//"date_update"
            }
            if (jsonNodeParentMAP.has("uuid")) {
                sqLiteStatementInsert.bindLong(4, jsonNodeParentMAP.get("uuid").longValue());//"uuid"
            }
            if (jsonNodeParentMAP.has("parent_uuid")) {
                sqLiteStatementInsert.bindLong(5, jsonNodeParentMAP.get("parent_uuid").longValue());//"uuid"
            }
            if (jsonNodeParentMAP.has("user_update")) {
                sqLiteStatementInsert.bindLong(6, jsonNodeParentMAP.get("user_update").intValue());//"uuid"
            }
            if (jsonNodeParentMAP.has("current_table")) {
                sqLiteStatementInsert.bindLong(7, jsonNodeParentMAP.get("current_table").longValue());//"current_table"
            }
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
    private SQLiteStatement методGetSqliteStatementForUpdate(@NonNull String SQlOperInsert,
                                                             @NonNull JsonNode jsonNodeParentMAP,
                                                             @NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite) {
        SQLiteStatement sqLiteStatementInsert = null;
        try{
            sqLiteStatementInsert =          Create_Database_СамаБАзаSQLite.compileStatement(SQlOperInsert);
            sqLiteStatementInsert=    методЗаполенияMaterialBinarySQLiteStatement(sqLiteStatementInsert, jsonNodeParentMAP );
            // TODO: 05.07.2023  Для Состыковки
            if (jsonNodeParentMAP.has("uuid")) {
                sqLiteStatementInsert.bindLong(9,jsonNodeParentMAP.get("uuid").longValue());//"uuid уже для UUID"
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
        return sqLiteStatementInsert;
    }


    // TODO: 11.09.2023 class Преобразование Входящеих Bibary Фалов
    class ClassDecodePhotos{
        // TODO: 11.09.2023 уменьшщение размера файлд для записи в базу
        @NonNull
        private byte[] методDecodePhotos( @NonNull  byte[] Photo) throws IOException {
            byte[] ByteDecodePhoto=null;
            try{
                Bitmap bitmapNewImage = BitmapFactory.decodeByteArray(Photo, 0, Photo.length);
                ByteArrayOutputStream bytedataNewImage = new ByteArrayOutputStream(2048);//4096
                bitmapNewImage.compress(Bitmap.CompressFormat.JPEG, 3, bytedataNewImage);
                ByteDecodePhoto = bytedataNewImage.toByteArray();
                bytedataNewImage.flush();
                bytedataNewImage.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return ByteDecodePhoto;
        }

    }
}
