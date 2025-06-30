package com.dsy.dsu.CommitPricesApp.Model.SendDataTo1C;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CommitPricesApp.Model.GeneratorJsons.GeneratorJsonFor1cCommitingPrices;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.io.ByteSource;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommintPricesSendJsonTo1C extends  SendDataParent {


    // TODO: 10.01.2024  генерируем код на отправку
    @Override
    public byte[] GeneratorJsonForPostComminhgPrices(@NonNull Context context,
                                                     @NonNull LinkedHashMap<String, String> linkedHashMapОтпавркаНа1с,
                                                     @NonNull  ObjectMapper objectMapper) {
        byte[] ByteFor1CCommintPrices=null;
        try{
            //TODO POST () Генерируем JSON
            // TODO: 10.11.2023 starting Jakson JSON
            StringWriter stringWriterJSONAndroid=    new StringWriter();
            SimpleModule module = new SimpleModule();
            // TODO: 11.09.2023  какая текущап таблица
            Bundle bundle=new Bundle();
            bundle.putSerializable("genetarjsonsend1ccommitprices",(Serializable) linkedHashMapОтпавркаНа1с);
            module.addSerializer(Bundle.class, new GeneratorJsonFor1cCommitingPrices(context));
            objectMapper.registerModule(module);
            objectMapper.getFactory().createGenerator( stringWriterJSONAndroid ).useDefaultPrettyPrinter();

            ByteFor1CCommintPrices=    objectMapper.writeValueAsBytes(bundle);
             // String  dataforsend1cCommitPay2=    objectMapper.writeValueAsString(bundle);

            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + " ByteFor1CCommintPrices " +ByteFor1CCommintPrices);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ByteFor1CCommintPrices;
    }


    // TODO: 10.01.2024  посылаем данные на POST 1c Согласование Цены
    
    @Override
    public  StringBuffer SendJsonForPostComminhgPrices(@NonNull Context context,
                                                @NonNull byte[] ByteОтпавркаНа1с,
                                                @NotNull Integer PublicId,
                                                @NotNull String adress,
                                                @NonNull String uuid ) {
        // TODO: 26.12.2023
        final StringBuffer[] OtvetPostComminhgPrices = {new StringBuffer()};
        try{
            OkHttpClient okHttpClient1cPOSTСогласованиеЦенbyte = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("user", String.valueOf(PublicId))//TODO old ПубличныйIDДляФрагмента   или 8
                                    .header("uuid", uuid)//TODO old ПубличныйIDДляФрагмента   или 8
                                    .header("Authorization",
                                            Credentials.basic("dsu1Admin", "dsu1Admin"));
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .build();
            // TODO: 25.10.2022 Диспечер
            Dispatcher dispatcher=  okHttpClient1cPOSTСогласованиеЦенbyte.dispatcher();
            Request requestet1cСогласованииЦен = new Request.Builder().get().url(adress).build();
            // TODO  Call callGET = client.newCall(requestGET);
            okHttpClient1cPOSTСогласованиеЦенbyte.newCall(requestet1cСогласованииЦен).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());

                    //TODO
                    dispatcher.executorService().shutdown();
                    //TODO закрываем п отоки
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try{
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                + "   response.isSuccessful() " +  response.isSuccessful());

                        if (response.isSuccessful()) {
                            String  ПришедшегоПотока =    response.header("stream_size");
                            ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                            Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                            Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets"))
                                    .map(String::new).orElse("0"));
                            Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream"))
                                    .map(String::new).orElse("false"));


                            // TODO: 26.12.2023
                            // TODO: 26.12.2023
                            byte[]    getbytepostComminhgPrices =    response.body().source().readByteArray();
                            InputStream   inputStreamОтПинга  = ByteSource.wrap(getbytepostComminhgPrices).openBufferedStream();

                            // TODO: 07.10.2023 end
                            BufferedReader РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(inputStreamОтПинга, StandardCharsets.UTF_8));

                            OtvetPostComminhgPrices[0] = РидерОтСервераМетодаGET.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                                    StringBuffer::append);
                            // TODO: 10.01.2024
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    +  " OtvetPostComminhgPrices " + OtvetPostComminhgPrices[0]);
                        }

                        // TODO: 28.12.2024 closeting
                        response.close();
                        // TODO: 28.12.2024
                        dispatcher.executorService().shutdown();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            });
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

            dispatcher.executorService().awaitTermination(1,TimeUnit.DAYS);


            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  OtvetPostComminhgPrices[0]  ;
    }

    
    
    
    
    @Override
    public String GetDeserializerJson1cComminhgPrices(@NonNull Context context, @NonNull InputStream inputStream, ObjectMapper objectMapper) {
        return null;
    }

    @Override
    public void motingHideRecyreVIewNested(@NonNull Context context, @NonNull InputStream inputStream) {

    }
}
