package com.dsy.dsu.CommitPricesApp.Model.GeneratorJsons;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GeneratorJsonFor1cCommitingPrices extends JsonSerializer<Bundle> {

    Context context;

    public GeneratorJsonFor1cCommitingPrices(Context context) {
        this.context = context;
    }


    @Override
    public void serialize(Bundle value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
 try{

     LinkedHashMap<String, String> linkedHashMapCommintPrecis = (LinkedHashMap<String, String>) value.getSerializable("genetarjsonsend1ccommitprices");

     gen.writeStartArray();
     gen.writeStartObject();
     // TODO: 20.07.2023 Строки
     Flowable.fromIterable(linkedHashMapCommintPrecis.entrySet())
             .onBackpressureBuffer()
             .subscribeOn(Schedulers.single())
             .blockingIterable()
             .forEach(new Consumer<Map.Entry<String, String>>() {
                 @Override
                 public void accept(Map.Entry<String, String> stringLongEntry) {
                     try{

                         if (stringLongEntry.getKey()==null) {
                             serializers.defaultSerializeField(stringLongEntry.getKey(),null, gen);
                         } else {
                                 serializers.defaultSerializeField(stringLongEntry.getKey(),stringLongEntry.getValue(), gen);
                         }

                         Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                 " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                 " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                 + " s " +stringLongEntry.getKey()+" d" +stringLongEntry.getValue());

                     } catch (Exception e) {
                         e.printStackTrace();
                         Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                 " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                         new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                 Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                     }
                 }
             });
     gen.writeEndObject();
     gen.writeEndArray();
     gen.flush();
     gen.close();
     Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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
