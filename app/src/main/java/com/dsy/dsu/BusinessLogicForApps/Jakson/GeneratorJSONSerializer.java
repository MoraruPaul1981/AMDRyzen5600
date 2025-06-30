package com.dsy.dsu.BusinessLogicForApps.Jakson;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Optional;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

public class GeneratorJSONSerializer extends JsonSerializer<Cursor> {
    Context context;
    public GeneratorJSONSerializer(Context context) {
        this.context=context;
    }

    @Override
    @SuppressLint("Range")
    public void serialize(Cursor КурсорДляОтправкиДанныхНаСерверОтАндройда, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
//////////20.15
        try{
            jsonGenerator.writeStartObject();///      .concatMap(i -> Observable.just(i).delay(150, TimeUnit.MILLISECONDS))
            // TODO: 20.07.2023 Строки 
            Flowable.range(0,КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount())
                    .onBackpressureBuffer(1)
                    .doOnNext(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Throwable {

                    Long Key= КурсорДляОтправкиДанныхНаСерверОтАндройда.getLong(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex("uuid"));
                            jsonGenerator.writeFieldId(Key);
                            jsonGenerator.writeStartObject();

                            // TODO: 20.07.2023 Колонки
                            Flowable.range(0,КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnCount())
                                    .onBackpressureBuffer()
                                    .blockingIterable().forEach(new java.util.function.Consumer<Integer>() {
                                        @Override
                                        public void accept(Integer ИндексСтолбикаJson) {
                                            try{

                                            String НазваниеСтолбикаJson = КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                    .getColumnName(ИндексСтолбикаJson).trim();// TODO: 14.03.2023 Название как текст столбика в JSON  NAme
                                            switch (НазваниеСтолбикаJson.trim()){
                                                case "_id":
                                                case "id":
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson);
                                                    break;
                                                case "uuid":
                                                case "current_table":
                                                case "uuid_tabel":
                                                case "parent_uuid":
                                                case "fio":
                                                    Long UUIDandCurrenttableValue= КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                            .getLong(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                                                    UUIDandCurrenttableValue=Optional.ofNullable(UUIDandCurrenttableValue).orElse(0l);
                                                    // TODO: 14.03.2023  Само Полученое содеожимое столбика Value
                                                    serializers.defaultSerializeField(НазваниеСтолбикаJson,UUIDandCurrenttableValue, jsonGenerator);
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +
                                                            " UUIDandCurrenttableValue " +UUIDandCurrenttableValue);
                                                    break;
                                                case "user_update":
                                                    Integer Getuser_update= КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                            .getInt(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                                                    // Само Полученое содеожимое столбика Value
                                                    if (Getuser_update>0) {
                                                        serializers.defaultSerializeField(НазваниеСтолбикаJson,Getuser_update, jsonGenerator);
                                                    } else {
                                                        serializers.defaultSerializeField(НазваниеСтолбикаJson,null, jsonGenerator);
                                                    }
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +
                                                            " Getuser_update " +Getuser_update);
                                                    break;

                                                case "prof":
                                                  Integer Getprof= КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                            .getInt(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                                                    //TODO Само Полученое содеожимое столбика Value
                                                    if (Getprof>0) {
                                                        serializers.defaultSerializeField(НазваниеСтолбикаJson,Getprof, jsonGenerator);
                                                    } else {
                                                        serializers.defaultSerializeField(НазваниеСтолбикаJson,null, jsonGenerator);
                                                    }
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +
                                                            " Getprof " +Getprof);
                                                    break;

                                                case    "current_organization":
                                                    Integer GetcurrentOrganization= КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                            .getInt(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                                                    //TODO Само Полученое содеожимое столбика Value
                                                    if (GetcurrentOrganization>0) {
                                                        serializers.defaultSerializeField(НазваниеСтолбикаJson,GetcurrentOrganization, jsonGenerator);
                                                    } else {
                                                        serializers.defaultSerializeField(НазваниеСтолбикаJson,null, jsonGenerator);
                                                    }
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +
                                                            " GetcurrentOrganization " +GetcurrentOrganization);
                                                    break;

                                                case "image":
                                                    byte[] GetBlobImage= КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                            .getBlob(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                                                    // Само Полученое содеожимое столбика IMAGE
                                                    if (GetBlobImage==null) {
                                                        serializers.defaultSerializeField(НазваниеСтолбикаJson,null, jsonGenerator);
                                                        //serializers.defaultSerializeNull(jsonGenerator);
                                                    } else {
                                                        serializers.defaultSerializeField(НазваниеСтолбикаJson,GetBlobImage, jsonGenerator);
                                                    }
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +
                                                            " GetBlobImage " +GetBlobImage);
                                                    break;

                                                case "files":
                                                    byte[] GetBlobFile= КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                            .getBlob(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                                                    // Само Полученое содеожимое столбика FILE
                                                    if (GetBlobFile==null) {
                                                        serializers.defaultSerializeField(НазваниеСтолбикаJson,null, jsonGenerator);
                                                    } else {
                                                        serializers.defaultSerializeField(НазваниеСтолбикаJson,GetBlobFile, jsonGenerator);
                                                    }
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +
                                                            " GetBlobFile " +GetBlobFile);
                                                    break;
                                                case "image_chat":
                                                    byte[] GetBlobImageChat= КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                            .getBlob(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                                                    // Само Полученое содеожимое столбика FILE
                                                    if (GetBlobImageChat==null) {
                                                        serializers.defaultSerializeField(НазваниеСтолбикаJson,null, jsonGenerator);
                                                    } else {
                                                        serializers.defaultSerializeField(НазваниеСтолбикаJson,GetBlobImageChat, jsonGenerator);
                                                    }
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +
                                                            " GetBlobImageChat " +GetBlobImageChat);
                                                    break;



                                                default:
                                                    String СодержимоеСтолбикаJson= КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                            .getString(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                                                    // TODO: 14.03.2023  Само Полученое содеожимое столбика Value
                                                    СодержимоеСтолбикаJson=Optional.ofNullable(СодержимоеСтолбикаJson).orElse("");
                                                    // TODO: 17.05.2023
                                                    serializers.defaultSerializeField(НазваниеСтолбикаJson,СодержимоеСтолбикаJson, jsonGenerator);
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +
                                                            " СодержимоеСтолбикаJson " +СодержимоеСтолбикаJson);
                                                    break;
                                            }

                                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                    + " getColumnCount " +КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnCount() +
                                                    "ИндексСтолбикаJson" + ИндексСтолбикаJson);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        }
                                        }
                                    });

                        }
                    })
                    .doAfterNext(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Throwable {

                            jsonGenerator.writeEndObject();
                            Log.d(this.getClass().getName(), " jsonObjectWriter.toString()   " + jsonGenerator.toString());

                            КурсорДляОтправкиДанныхНаСерверОтАндройда.moveToNext();
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " getPosition " +КурсорДляОтправкиДанныхНаСерверОтАндройда.getPosition());
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            jsonGenerator.writeEndObject();
                            jsonGenerator.flush();
                            jsonGenerator.close();
                            КурсорДляОтправкиДанныхНаСерверОтАндройда.close();
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            КурсорДляОтправкиДанныхНаСерверОтАндройда.close();
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    })
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {

                            КурсорДляОтправкиДанныхНаСерверОтАндройда.close();
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .blockingSubscribe();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }
}
