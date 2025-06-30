package com.dsy.dsu.BusinessLogicForApps.Jakson;


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
import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class GeneratorJSON1CPayCommitSerializer extends JsonSerializer<Bundle> {
    Context context;
    public GeneratorJSON1CPayCommitSerializer(Context context) {
        this.context=context;
    }


    @Override
    public void serialize( Bundle  value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
try {
        // TODO: 10.11.2023  генерайия JSON for 1c
     // GeneratorJson1cPaernt generatorJson1cJsonGenerator=new GeneratorJson1cJsonGenerator(context,value,gen,serializers);

       GeneratorJson1cPaernt generatorJson1cJsonGenerator=new GeneratorJson1cJsonGenerator(context,value,gen,serializers);

    // TODO: 12.11.2023 выполенение ГЕнерации JSON
        generatorJson1cJsonGenerator.generatorJson();

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







// TODO: 10.11.2023 class Бизнес ЛОгика для Генерации JSon  1c Согласование
class GeneratorJson1cPaernt {
    Context context;
    Bundle  value;
    JsonGenerator gen;
    SerializerProvider serializers;
    Observable observable;

    public GeneratorJson1cPaernt(Context context, Bundle  value, JsonGenerator gen, SerializerProvider serializers) {
        this.context = context;
        this.value = value;
        this.gen = gen;
        this.serializers = serializers;
    }

   void generatorJson(){
        try{
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
}///TODO END  class GeneratorJson1cPaernt










// TODO: 10.11.2023 class Бизнес ЛОгика для Генерации JSon  1c Согласование
class GeneratorJson1cJsonGenerator extends  GeneratorJson1cPaernt {
    // TODO: 10.11.2023 метод генерации JSON
    public GeneratorJson1cJsonGenerator(Context context, Bundle  value, JsonGenerator gen, SerializerProvider serializers) {
        super(context, value, gen, serializers);
    }

    @Override
   protected void generatorJson() {
        try{
            LinkedHashMap<String, String> linkedHashMapОтпавркаНа1с = (LinkedHashMap<String, String>) value.getSerializable("ser");

            gen.writeStartArray();
            gen.writeStartObject();
            // TODO: 20.07.2023 Строки
            Flowable.fromIterable(linkedHashMapОтпавркаНа1с.entrySet())
                    .onBackpressureBuffer()
                    .blockingIterable()
                    .forEach(new java.util.function.Consumer<Map.Entry<String, String>>() {
                        @Override
                        public void accept(Map.Entry<String, String> stringStringEntry) {
                            try{

                                if (stringStringEntry.getKey()==null) {
                                    serializers.defaultSerializeField(stringStringEntry.getKey(),null, gen);
                                } else {
                                    if(stringStringEntry.getKey().equalsIgnoreCase("status")){
                                        serializers.getGenerator().writeNumberField(stringStringEntry.getKey(),Long.parseLong(stringStringEntry.getValue()));
                                    }else {
                                        serializers.defaultSerializeField(stringStringEntry.getKey(),stringStringEntry.getValue(), gen);
                                    }

                                }

                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + " s " +stringStringEntry.getKey()+" d" +stringStringEntry.getValue());

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


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }

}///TODO class GeneratorJson1cJsonGenerator






// TODO: 10.11.2023 class Бизнес ЛОгика для Генерации JSon  1c Согласование
class GeneratorJson1cVariantTokenBuffer extends  GeneratorJson1cPaernt {
    // TODO: 10.11.2023 метод генерации JSON
    public GeneratorJson1cVariantTokenBuffer(Context context, Bundle value, JsonGenerator gen, SerializerProvider serializers) {
        super(context, value, gen, serializers);
    }

    @Override
    void generatorJson() {
        try {

            LinkedHashMap<String, String> linkedHashMapОтпавркаНа1с = (LinkedHashMap<String, String>) value.getSerializable("ser");

            gen.writeStartObject();///      .concatMap(i -> Observable.just(i).delay(150, TimeUnit.MILLISECONDS))
            // TODO: 20.07.2023 Строки
            Flowable.fromIterable(linkedHashMapОтпавркаНа1с.entrySet())
                    .onBackpressureBuffer()
                    .blockingIterable()
                    .forEach(new java.util.function.Consumer<Map.Entry<String, String>>() {
                        @Override
                        public void accept(Map.Entry<String, String> stringStringEntry) {
                            try{
                                Long      Key= ThreadLocalRandom.current().nextLong(10,10000);

                                gen.writeFieldId(Key);
                                gen.writeStartObject();

                                if (stringStringEntry.getKey()==null) {
                                    serializers.defaultSerializeField(stringStringEntry.getKey(),null, gen);
                                } else {
                                    serializers.defaultSerializeField(stringStringEntry.getKey(),stringStringEntry.getValue(), gen);
                                }
                                gen.writeEndObject();

                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + " s " +stringStringEntry.getKey()+" d" +stringStringEntry.getValue());

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
            gen.flush();
            gen.close();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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

    }
    // TODO: 12.11.2023 TWO CLASS Serlizay

}//TODO END class GeneratorJson1cVariantTokenBuffer