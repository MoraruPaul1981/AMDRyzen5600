package com.dsy.dsu.CommitPricesApp.Model.GetDataOt1C;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
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

public class GetJsonOt1cComminhgPrices extends  GetJsonOt1cComminhgPricesParent {

    public GetJsonOt1cComminhgPrices() {
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    }


    @Override
    public  byte[] getByteComminhgPrices(@NonNull Context context,
                                         @NonNull String adress,
                                         @NonNull Integer PublicId,
                                         @NonNull ObjectMapper objectMapper) {
        // TODO: 26.12.2023
        final byte[][] getbyteComminhgPrices = {null};
        try{
        OkHttpClient okHttpClient1cСогласованиеЦенbyte = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder()
                                .header("user", String.valueOf(PublicId))//TODO old ПубличныйIDДляФрагмента   или 8
                                .header("uuid", String.valueOf(0))//TODO old ПубличныйIDДляФрагмента   или 8
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
        Dispatcher dispatcher=  okHttpClient1cСогласованиеЦенbyte.dispatcher();
        Request requestet1cСогласованииЦен = new Request.Builder().get().url(adress).build();
        // TODO  Call callGET = client.newCall(requestGET);
        okHttpClient1cСогласованиеЦенbyte.newCall(requestet1cСогласованииЦен).enqueue(new Callback() {
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
                        getbyteComminhgPrices[0] =    response.body().source().readByteArray();

                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                + "      getbyteComminhgPrices[0]  " + getbyteComminhgPrices[0] .length);

                  /*      // TODO: 07.10.2023 end

                     //inputStream1c[0] = new ByteArrayInputStream( asByteBuffer);
                      ///  getInputStreamComminhgPrices[0] = ByteSource.wrap(asByteBuffer).openBufferedStream();
                        BufferedReader       РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(getInputStreamComminhgPrices[0], StandardCharsets.UTF_8));
                        StringBuffer БуферСамиДанныеОтСервера= РидерОтСервераМетодаGET.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                                StringBuffer::append);
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                + "     getInputStreamComminhgPrices " + getInputStreamComminhgPrices[0].available());*/
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
        return getbyteComminhgPrices[0];
    }





}
