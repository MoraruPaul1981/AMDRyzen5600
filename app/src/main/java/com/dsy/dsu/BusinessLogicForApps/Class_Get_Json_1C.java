package com.dsy.dsu.BusinessLogicForApps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicForApps.Jakson.GeneratorJSON1CPayCommitSerializer;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.io.ByteSource;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.crypto.NoSuchPaddingException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


///////////--------------------------TODO ЭТО ТРЕТИЙ  КОНТРОЛЛЕР ТОЛЬКО ДЛЯ ПОЛУЧЕНИЯ  ТОЛЬКО JSON ПОЛЕЙ  И СКОЛЬКО ТАБЛИЦ НУЖНО БЕЗ  СИНХРОНИЗАЦИИИ---

public class Class_Get_Json_1C extends CoreBinessLogics {
  private   Context context;
    private  String АдресСервера;
    public Class_Get_Json_1C(Context context, String АдресСервера) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        super(context);
        // super(context);
       this. context = context;
        this.АдресСервера = АдресСервера.trim();
        ////// конец  TODO созданеи шифрование
    }


////// TODO для согласования
public InputStream   МетодПолучемJSONОт1СДляСогласования(@NonNull  Integer ПубличныйIDДляФрагмента, @NonNull String ТаблицыДляОбработки1С)
        throws InterruptedException, ExecutionException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
    // TODO: 09.11.2023 Ответ От 1с
    final InputStream[] inputStream1c = {null};
    try {
        ////todo ТОЛЬКО КОГДА НЕ ОТЛАДКА
        Log.w(this.getClass().getName(), "   ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента
                + " ТаблицыДляОбработки1С " +ТаблицыДляОбработки1С);
        // MediaType JSON = MediaType.parse("application/json; charset=utf-8");



    OkHttpClient okHttpClientПолучаемДанныеОт1С = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder()
                                .header("dsu1table", ТаблицыДляОбработки1С)
                                .header("dsu1user", String.valueOf(ПубличныйIDДляФрагмента))//TODO old ПубличныйIDДляФрагмента   или 8
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
        Dispatcher dispatcher=  okHttpClientПолучаемДанныеОт1С.dispatcher();
        //
        ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
        Request requestGET = new Request.Builder().get().url(АдресСервера).build();
        Log.d(this.getClass().getName(), "  request  " + requestGET);
        // TODO  Call callGET = client.newCall(requestGET);
        okHttpClientПолучаемДанныеОт1С.newCall(requestGET).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // TODO: 12.01.2024
                Log.i(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());

                //TODO
                dispatcher.executorService().shutdown();
                //TODO закрываем п отоки
            }
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try{
                if (response.isSuccessful()) {
                    String  ПришедшегоПотока =    response.header("stream_size");
                    ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                    Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                    Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                    Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));

            /* ByteString strings=   response.body().source().readByteString();

                byte[]  asByteBuffer= strings.toByteArray();*/




                    byte[] asByteBuffer=    response.body().source().readByteArray();
                    //inputStream1c[0] = new ByteArrayInputStream( asByteBuffer);
                    inputStream1c[0] = ByteSource.wrap(asByteBuffer).openBufferedStream();

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "     inputStream1c " + inputStream1c[0]);
                }
                    // TODO: 09.11.2023  close
                    // TODO: 28.12.2024 closeting
                    response.close();
                    //TODO
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

        // TODO: 01.12.2023
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    return   inputStream1c[0];
}


    // TODO: 07.07.2022  для лимита остатков
    public StringBuffer МетодПолучемJSONОт1СДляЛимитаМатериаловВторойЭтап(@NonNull  Integer ПубличныйIDДляФрагмента,
                                                                          @NonNull String ТаблицыДляОбработки1С,
                                                                          Integer СФООтправляемОпятьНа1сДляПолучениеДАнныхОбратно)
            throws InterruptedException, ExecutionException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
        StringBuffer stringBufferВторайШагПолучаемСамиДанныеНАОснованииЦФО = new StringBuffer();
        try {
            ////todo ТОЛЬКО КОГДА НЕ ОТЛАДКА
            Log.w(this.getClass().getName(), "   ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента
                    + " ТаблицыДляОбработки1С " +ТаблицыДляОбработки1С+  " СФООтправляемОпятьНа1сДляПолучениеДАнныхОбратно "+СФООтправляемОпятьНа1сДляПолучениеДАнныхОбратно);
            // MediaType JSON = MediaType.parse("application/json; charset=utf-8");
      OkHttpClient      okHttpClientДляЛимитаМатериалов = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("dsu1table", ТаблицыДляОбработки1С)
                                    .header("dsu1user", String.valueOf(ПубличныйIDДляФрагмента))
                                    .header("dsu1cfo",String.valueOf(СФООтправляемОпятьНа1сДляПолучениеДАнныхОбратно) )//ПубличныйIDДляФрагмента
                                    .header("Authorization",
                                            Credentials.basic("dsu1Admin", "dsu1Admin"));
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(5, TimeUnit.SECONDS)
              .writeTimeout(2, TimeUnit.MINUTES)
              .readTimeout(2, TimeUnit.MINUTES)
              .build();
            //
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(АдресСервера).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            // TODO  Call callGET = client.newCall(requestGET);
            okHttpClientДляЛимитаМатериалов.newCall(requestGET).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                    ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                    //TODO закрываем п отоки
                    okHttpClientДляЛимитаМатериалов.dispatcher().executorService().shutdown();
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try{
                    if (response.isSuccessful()) {
                        String  ПришедшегоПотока =    response.header("stream_size");
                        ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                        Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                        Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                        Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));

                        stringBufferВторайШагПолучаемСамиДанныеНАОснованииЦФО.append(response.body().string());
                        ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                        Log.d(this.getClass().getName(), "  stringBuffer  " + stringBufferВторайШагПолучаемСамиДанныеНАОснованииЦФО.toString() + "  responseGet.code()" + response.code());
                        //todo метод после получение данных запускам локальный бродкастер
                      //  new SubClass_CommitPayAfter_Для_СогласованияПосле().     МетодПослеСменыСтаусаСогласованЧерезЛокльныйБродКастерОтправимИзмениниянаАктивти(stringBuffer,contextGetClassNumberAllRowsJSON);
                        Log.i(context.getClass().getName(), "stringBuffer" + stringBufferВторайШагПолучаемСамиДанныеНАОснованииЦФО);
                    }
                        // TODO: 28.12.2024
                        // TODO: 28.12.2024 closeting
                        response.close();

                    //TODO закрываем п отоки
                    okHttpClientДляЛимитаМатериалов.dispatcher().executorService().shutdown();
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
             //TODO
            Log.i(context.getClass().getName(), "stringBuffer" + stringBufferВторайШагПолучаемСамиДанныеНАОснованииЦФО);
            okHttpClientДляЛимитаМатериалов.dispatcher().executorService().awaitTermination(1,TimeUnit.DAYS);
            // TODO: 06.07.2022
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
//TODO
        return stringBufferВторайШагПолучаемСамиДанныеНАОснованииЦФО;
    }



    ////// TODO ПЕРВЫЙ МЕТОД ОБМЕНА ДАННЫМИ С СЕРВЕРОМ МЕТОД GET JSON только когда иы хотим узнать все строки json  по всем строкам мы запускаем этот код И ВСЕ !!!!

    public StringBuffer МетодПолучемJSONОт1СДляЛимитаМатериаловЭтапПервый(@NonNull  Integer ПубличныйIDДляФрагмента, @NonNull String ТаблицыДляОбработки1С)
            throws InterruptedException, ExecutionException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
        StringBuffer stringBufferМатериаловЭтапПервый = new StringBuffer();
        try {
            ////todo ТОЛЬКО КОГДА НЕ ОТЛАДКА
            Log.w(this.getClass().getName(), "   ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента
                    + " ТаблицыДляОбработки1С " +ТаблицыДляОбработки1С);
            // MediaType JSON = MediaType.parse("application/json; charset=utf-8");
         OkHttpClient   okHttpClientЛиммитМатериаловЭтаппервый = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("dsu1table", ТаблицыДляОбработки1С)
                                    .header("dsu1user", String.valueOf(113))///TODO old      113           .header("dsu1user", String.valueOf(ПубличныйIDДляФрагмента))
                                    .header("Authorization",
                                            Credentials.basic("dsu1Admin", "dsu1Admin"));
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(5, TimeUnit.SECONDS)
                 .writeTimeout(2, TimeUnit.MINUTES)
                 .readTimeout(2, TimeUnit.MINUTES)
                 .build();
            //
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(АдресСервера).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            // TODO  Call callGET = client.newCall(requestGET);
            okHttpClientЛиммитМатериаловЭтаппервый.newCall(requestGET).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                    ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                    //TODO закрываем п отоки
                    okHttpClientЛиммитМатериаловЭтаппервый.dispatcher().executorService().shutdown();
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try{
                    if (response.isSuccessful()) {
                        String  ПришедшегоПотока =    response.header("stream_size");
                        ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                        Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                        Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                        Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));
                        stringBufferМатериаловЭтапПервый.append(response.body().string());
                        ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                        Log.d(this.getClass().getName(), "  stringBufferМатериаловЭтапПервый  " + stringBufferМатериаловЭтапПервый.toString() + "  responseGet.code()" + response.code());
                        //todo метод после получение данных запускам локальный бродкастер
                        //  new SubClass_CommitPayAfter_Для_СогласованияПосле().     МетодПослеСменыСтаусаСогласованЧерезЛокльныйБродКастерОтправимИзмениниянаАктивти(stringBuffer,contextGetClassNumberAllRowsJSON);
                        Log.i(context.getClass().getName(), "stringBufferМатериаловЭтапПервый" + stringBufferМатериаловЭтапПервый);
                    }
                        // TODO: 28.12.2024
                        // TODO: 28.12.2024 closeting
                        response.close();
                    //TODO закрываем п отоки
                    okHttpClientЛиммитМатериаловЭтаппервый.dispatcher().executorService().shutdown();
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
            //TODO
            // TODO: 31.05.2022
            while (!okHttpClientЛиммитМатериаловЭтаппервый.dispatcher().executorService().isShutdown());
            //TODO
            Log.i(context.getClass().getName(), "stringBufferМатериаловЭтапПервый" + stringBufferМатериаловЭтапПервый);
            // okHttpClient.dispatcher().executorService().awaitTermination(1,TimeUnit.MINUTES);
            okHttpClientЛиммитМатериаловЭтаппервый.dispatcher().executorService().awaitTermination(1,TimeUnit.DAYS);
            // TODO: 06.07.2022

            ///
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
//TODO
        return stringBufferМатериаловЭтапПервый;
    }

























//TODO ВТОРОЙ МЕТОД ОТПРАВЛЯЕМ ДАННЫЕ  НА 1С POST()
    ////// TODO ПЕРВЫЙ МЕТОД ОБМЕНА ДАННЫМИ С СЕРВЕРОМ МЕТОД GET JSON только когда иы хотим узнать все строки json  по всем строкам мы запускаем этот код И ВСЕ !!!!
    public StringBuffer МетодОтправляемSimpleJSONОт1С(@NotNull Context context,
                                                      @NotNull  String     NumberDoc,
                                                      @NotNull   String DataDoc,
                                                      @NotNull   Integer  StatusDoc,
                                                      @NotNull String ТаблицыДляPOST,
                                                      @NotNull Integer ПубличныйIDДляCогласования,
                                                      @NonNull  ObjectMapper jsonGenerator)

    {
        //TODO
        StringBuffer stringBufferотправкаНа1С = new StringBuffer();
        try {
            ////todo ТОЛЬКО КОГДА НЕ ОТЛАДКА
         OkHttpClient   okHttpClientОтправкаСоглоавания = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();

                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("dsu1table", ТаблицыДляPOST)
                                    .header("dsu1user", String.valueOf(ПубличныйIDДляCогласования))
                                    .header("returnstatus", String.valueOf(StatusDoc))
                                    .header("dsu1number", NumberDoc)
                                    .header("dsu1date", DataDoc)
                                    .header("Authorization",
                                            Credentials.basic("dsu1Admin", "dsu1Admin"));

                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(5, TimeUnit.SECONDS)
                 .writeTimeout(2, TimeUnit.MINUTES)
                 .readTimeout(2, TimeUnit.MINUTES)
                 .build();

            Dispatcher      dispatcher= okHttpClientОтправкаСоглоавания.dispatcher();

            //TODO POST () Генерируем JSON на отправку 1c Cjukfjdfyb
            byte[] dataforsend1cCommitPay= metodGeneraotrSimpleFor1cPayCommit(NumberDoc, StatusDoc,NumberDoc,jsonGenerator);

            RequestBody bodyДляОтправки1cСогласования =
                    RequestBody.create(MediaType.parse("application/octet-stream; charset=utf-8"),dataforsend1cCommitPay);
          //  RequestBody bodyДляОтправки1cСогласования = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),stringBufferСгенерированыйJSONДляОтпрвкиНа1С.toString());

            Request requestPOST = new Request.Builder()
                    .post(bodyДляОтправки1cСогласования)
                    .url(АдресСервера).build();
            okHttpClientОтправкаСоглоавания.newCall(requestPOST).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    try{
                    Log.d(this.getClass().getName(), "  post call  " + call + "  e" + e.toString());
                    ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                        dispatcher.executorService().shutdown();

                } catch (Exception ex) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    //TODO
                    try{
                    if (response.isSuccessful()) {
                        //TODO
                        String  ПришедшегоПотока =    response.header("stream_size");
                        ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                        Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                        Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                        Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));
                        stringBufferотправкаНа1С.append(response.body().string());
                        Log.d(this.getClass().getName(), "   stringBufferотправкаНа1С  " + stringBufferотправкаНа1С.toString() +
                                "  responsePOST.code()" + response.code());
                        ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                        Log.i(context.getClass().getName(), "stringBuffer" + stringBufferотправкаНа1С);
                    }

                        // TODO: 28.12.2024 closeting
                        response.close();

                        // TODO: 28.12.2024
                        dispatcher.executorService().shutdown();

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }}
            });
            dispatcher.executorService().awaitTermination(1,TimeUnit.DAYS);

            Log.i(context.getClass().getName(), "stringBuffer" + stringBufferотправкаНа1С);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
//TODO
        return stringBufferотправкаНа1С;
    }


    public byte[] МетодПолучениеФайлаBinatyJSONОт1С(@NotNull Context context,
                                                String backfile
            , Integer ПередаемСтатусСогласования
            , String ТаблицыДляPOST,
                                                Integer ПубличныйIDДляCогласования,
                                                    @NonNull String НомерТекущегоДокумента,
                                                    @NotNull ObjectMapper jsonGenerator)
            throws InterruptedException, ExecutionException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
        final byte[][] newFileBinaty1c = {null};
        try {
            ////todo ТОЛЬКО КОГДА НЕ ОТЛАДКА
            // MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient   okHttpClientОтправкаСоглоавания = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();

                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("dsu1table",  String.valueOf(ТаблицыДляPOST))
                                    .header("dsu1user", String.valueOf(ПубличныйIDДляCогласования))
                                    .header("Authorization",
                                            Credentials.basic("dsu1Admin", "dsu1Admin"));

                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .build();

            Dispatcher      dispatcher= okHttpClientОтправкаСоглоавания.dispatcher();

            //TODO POST () Генерируем JSON на отправку 1c Cjukfjdfyb
            byte[] dataforsend1cCommitPay= metodGeneraotrSimpleFor1cPayCommit(backfile, ПередаемСтатусСогласования,НомерТекущегоДокумента,jsonGenerator);

            if (dataforsend1cCommitPay!=null) {
                RequestBody bodyДляОтправки1cСогласования =
                        RequestBody.create(MediaType.parse("application/octet-stream; charset=utf-8"),dataforsend1cCommitPay);
                //  RequestBody bodyДляОтправки1cСогласования = RequestBody.create(MediaType.parse("application/json; charset=utf-8")
                //  ,stringBufferСгенерированыйJSONДляОтпрвкиНа1С.toString());

                Request requestPOST = new Request.Builder()
                        .post(bodyДляОтправки1cСогласования)
                        .url(АдресСервера).build();
                okHttpClientОтправкаСоглоавания.newCall(requestPOST).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        try{
                            Log.d(this.getClass().getName(), "  post call  " + call + "  e" + e.toString());
                            ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                            dispatcher.executorService().shutdown();

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                    + "\n" + "   e " +   e.getMessage().toString());

                        } catch (Exception ex) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @SuppressLint("SuspiciousIndentation")
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        //TODO
                        try{
                            if (response.isSuccessful()) {
                                //TODO
                                String  ПришедшегоПотока =    response.header("stream_size");
                                ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                                Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                                Integer КакаяКодировка = Integer.parseInt(
                                        Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                                Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (
                                        Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));

                            newFileBinaty1c[0] =   response.body().source().readByteArray();

                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                        + "\n" + "  newFileBinaty1c[0] " +   newFileBinaty1c[0].length+" response.code() " + response.code());

                            }

                            // TODO: 28.12.2024 closeting
                            response.close();
                            // TODO: 10.11.2023 exit
                            dispatcher.executorService().shutdown();

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }}
                });
                dispatcher.executorService().awaitTermination(1,TimeUnit.DAYS);

            }
            Log.i(context.getClass().getName(), "newFileBinaty1c" + newFileBinaty1c[0]);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
//TODO
        return newFileBinaty1c[0];
    }












    // TODO: 07.07.2022  получения данных тольо для согласования
    public JsonNode МетодПингаИПОлучениеДанныхОт1сДляСогласования(@NonNull Context context,
                                                                  @NonNull Integer ПубличныйIDДляФрагмента,
                                                                  @NonNull  ObjectMapper jsonGenerator) throws JSONException {
        // TODO: 09.11.2023
        JsonNode jsonNode1сСогласования=null;
        //TODO данные от 1С согласования
        try{
            //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С
           InputStream  inputStream1cСогласования  =
                    new Class_Get_Json_1C(context,АдресСервера)//TODO
                            .МетодПолучемJSONОт1СДляСогласования(ПубличныйIDДляФрагмента,"sog");//
            //TODO БУфер JSON от Сервера
          //  ObjectMapper jsonGenerator = new JbossContext(context).getGeneratorJackson();

            if (  inputStream1cСогласования !=null) {
                if (inputStream1cСогласования.available()>0 ) {
                    jsonNode1сСогласования= jsonGenerator.readTree(inputStream1cСогласования);

                    inputStream1cСогласования.close();
                }
            }


            // TODO: 26.05.2022
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " jsonNode1сСогласования " +jsonNode1сСогласования);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return jsonNode1сСогласования;
    }



    // TODO: 10.11.2023 метод получение JSOIN  для отправки на 1С Согласовании
  byte[] metodGeneraotrSimpleFor1cPayCommit(@NotNull String ПолученныйНомерДокументаСогласования,
                                              @NotNull Integer  ПередаемСтатусСогласования,
                                            @NotNull String НомерТекущегоДокумента,
                                            @NotNull @NonNull  ObjectMapper jsonGenerator){
      byte[] JsonByte1cPayCommit=null;
      try{
          LinkedHashMap<String,String>linkedHashMapОтпавркаНа1с=new LinkedHashMap<>();
          linkedHashMapОтпавркаНа1с.put("backfile",ПолученныйНомерДокументаСогласования);
          linkedHashMapОтпавркаНа1с.put("status",ПередаемСтатусСогласования.toString());
          linkedHashMapОтпавркаНа1с.put("dsu1number",НомерТекущегоДокумента);
          // TODO: 10.11.2023 starting Jakson JSON
          StringWriter stringWriterJSONAndroid=    new StringWriter();
          //ObjectMapper jsonGenerator = new JbossContext(context).getGeneratorJackson();
          SimpleModule module = new SimpleModule();
          // TODO: 11.09.2023  какая текущап таблица

          Bundle bundle=new Bundle();
          bundle.putSerializable("ser",(Serializable) linkedHashMapОтпавркаНа1с);


          module.addSerializer(Bundle.class, new GeneratorJSON1CPayCommitSerializer(context));
          jsonGenerator.registerModule(module);
          jsonGenerator.getFactory().createGenerator( stringWriterJSONAndroid ).useDefaultPrettyPrinter();

          JsonByte1cPayCommit=  jsonGenerator.writeValueAsBytes(bundle);
          // TODO: 13.11.2023 TETST !!!!
       //  String JsonByte1cPayCommit2=  jsonGenerator.writeValueAsString(bundle);


      Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
              " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
              " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
              + " JsonByte1cPayCommit " +JsonByte1cPayCommit );
  } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
      return JsonByte1cPayCommit;
  }

}