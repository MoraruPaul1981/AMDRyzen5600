package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

public class Data_chatJsonDeserializer extends JsonMainDeseirialzer {
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
                                                 // TODO: 04.07.2023  Обновление
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
                        "  (     uuid, message,image_chat , status_write,  chat_uuid,user_update,  date_update,    current_table  ,  alreadyshownnotifications  ) " +
                        " VALUES(?,?,?,?,?,?,?,?,?);";

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
                    " uuid=?, message =?,image_chat=? ,  " +
                    " status_write=?,  chat_uuid=?,user_update=?,  date_update=?,    current_table=? ,  alreadyshownnotifications=?   WHERE  uuid=?  ;";

            SQLiteStatement sqLiteStatementInsert = методGetSqliteStatementForUpdate(Create_Database_СамаБАзаSQLite, SQlOperUpdate,jsonNodeParentMAP);

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
        SQLiteStatement sqLiteStatementInsert = null;
        try{
            sqLiteStatementInsert = Create_Database_СамаБАзаSQLite.compileStatement(SQlOperInsert);
            sqLiteStatementInsert.clearBindings();
            // TODO: 04.07.2023 цикл данных
            sqLiteStatementInsert.bindLong(1, jsonNodeParentMAP.get("uuid").longValue());//"uuid"
            sqLiteStatementInsert.bindString(2, jsonNodeParentMAP.get("message").asText().trim());//"date_update"
            if (jsonNodeParentMAP.has("image_chat")  ) {
            if (!jsonNodeParentMAP.get("image_chat").isNull()  ) {
                sqLiteStatementInsert.bindBlob(3, jsonNodeParentMAP.get("image_chat").binaryValue());//"date_update"
            }else {
                sqLiteStatementInsert.bindNull(3);
            }
            }else{
                sqLiteStatementInsert.bindNull(3);
            }
            sqLiteStatementInsert.bindLong(4, jsonNodeParentMAP.get("status_write").intValue());//"id""
            sqLiteStatementInsert.bindLong(5, jsonNodeParentMAP.get("chat_uuid").longValue());//"current_table"
            sqLiteStatementInsert.bindLong(6, jsonNodeParentMAP.get("user_update").intValue());//"uuid"
            sqLiteStatementInsert.bindString(7, jsonNodeParentMAP.get("date_update").asText().trim());//"date_update"
            sqLiteStatementInsert.bindLong(8, jsonNodeParentMAP.get("current_table").longValue());//"current_table"
            sqLiteStatementInsert.bindLong(9, jsonNodeParentMAP.get("alreadyshownnotifications").intValue());//"uuid"

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
            ClassDecodePhotos decodePhotos=new ClassDecodePhotos();
            sqLiteStatementInsert= Create_Database_СамаБАзаSQLite.compileStatement(SQlOperInsert);
            sqLiteStatementInsert.clearBindings();
            // TODO: 04.07.2023 цикл данных
            sqLiteStatementInsert.bindLong(1, jsonNodeParentMAP.get("uuid").longValue());//"uuid"
            sqLiteStatementInsert.bindString(2, jsonNodeParentMAP.get("message").asText().trim());//"date_update"


            if ( jsonNodeParentMAP.has("image_chat") ) {
                byte[] ByteFOrBlobImage =decodePhotos. методDecodePhotos(jsonNodeParentMAP,"image_chat");
                // TODO: 06.10.2024  
                sqLiteStatementInsert.bindBlob(3,ByteFOrBlobImage );//"date_update"
                // TODO: 11.09.2023  ЗАПИСЬ НА ДИСК
                decodePhotos.  методSavePhotoFromServer(jsonNodeParentMAP,"image_chat",jsonNodeParentMAP.get("uuid").longValue());

            }else{
                sqLiteStatementInsert.bindNull(3);
            }

            sqLiteStatementInsert.bindLong(4, jsonNodeParentMAP.get("status_write").intValue());//"id""
            sqLiteStatementInsert.bindLong(5, jsonNodeParentMAP.get("chat_uuid").longValue());//"current_table"
            sqLiteStatementInsert.bindLong(6, jsonNodeParentMAP.get("user_update").intValue());//"uuid"
            sqLiteStatementInsert.bindString(7, jsonNodeParentMAP.get("date_update").asText().trim());//"date_update"
            sqLiteStatementInsert.bindLong(8, jsonNodeParentMAP.get("current_table").longValue());//"current_table"
            sqLiteStatementInsert.bindLong(9, jsonNodeParentMAP.get("alreadyshownnotifications").intValue());//"uuid"

            // TODO: 05.07.2023  Для Состыковки
            sqLiteStatementInsert.bindLong(10,jsonNodeParentMAP.get("uuid").longValue());//"uuid уже для UUID"

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
        private byte[] методDecodePhotos(@NonNull JsonNode jsonNodeParentMAP,@NonNull String Столбик) throws IOException {
            byte[] ByteDecodePhoto=null;
            try{
                byte[] Photo= jsonNodeParentMAP.get(Столбик).binaryValue();
                Bitmap bitmapNewImage = BitmapFactory.decodeByteArray(Photo, 0, Photo.length);
                ByteArrayOutputStream bytedataNewImage = new ByteArrayOutputStream(2048);
                bitmapNewImage.compress(Bitmap.CompressFormat.JPEG, 5, bytedataNewImage);
                ByteDecodePhoto = bytedataNewImage.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return ByteDecodePhoto;
        }


        @NonNull
        private byte[] методSavePhotoFromServer(@NonNull JsonNode jsonNodeParentMAP,@NonNull String Столбик, @NonNull Long UUIDPhoto) throws IOException {
            byte[] ByteDecodePhoto=null;
            try{
                byte[] Photo= jsonNodeParentMAP.get(Столбик).binaryValue();

                String patchFileName="SousAvtoFile/AppMaterial/Photos";
                String NameNewPhotosCamerax=UUIDPhoto.toString()+".jpg";
                File fileNewPhotoFromCameraX = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        +File.separator+patchFileName + File.separator+NameNewPhotosCamerax);

                File fileNewPhotoDirectory= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        +File.separator+patchFileName  );

                if ( fileNewPhotoDirectory.isDirectory() && !fileNewPhotoFromCameraX.exists()) {
                    Uri address=     FileProvider.getUriForFile(context.getApplicationContext(), "com.dsy.dsu.provider" ,fileNewPhotoFromCameraX );
                    ContentResolver contentResolver = context.getContentResolver();
                    OutputStream out = contentResolver.openOutputStream(address);
                    out.write(Photo);
                    out.flush();
                    out.close();
                }
                // TODO: 17.07.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " fileNewPhotoFromCameraX.exists() " +fileNewPhotoFromCameraX.exists());

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
