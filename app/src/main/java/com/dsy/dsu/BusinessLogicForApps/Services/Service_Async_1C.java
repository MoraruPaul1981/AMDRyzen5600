package com.dsy.dsu.BusinessLogicForApps.Services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */



public class Service_Async_1C extends IntentService {
    public LocalBinderGET1С binder = new LocalBinderGET1С();
    private Context context;

    public Service_Async_1C() {
        super("Service_Async_1C");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+  newConfig.locale);
      ;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
     //   return super.onBind(intent);
      return   binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.dump(fd, writer, args);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  newBase.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context=newBase;
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context=getApplicationContext();
        МетодЗапускаGET1С(getApplicationContext(),intent);
// TODO: 30.06.2022 сама не постредствено запуск метода
    }
    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinderGET1С extends Binder {
        public Service_Async_1C getService() {
            // Return this instance of LocalService so clients can call public methods
            return Service_Async_1C.this;
        }
    }

    public String МетодЗапускаGET1С(@NonNull Context context, @NonNull Intent intent) {
        final String[] БуферGET1С = {null};
        try{
            this.context=context;
            Bundle bundleПришлиДанные=   intent.getExtras();
            String  АдресСервера1С =      bundleПришлиДанные.getString("АдресСервера1С","");
            Integer  ПубличныйID =      bundleПришлиДанные.getInt("PublicIDfromClient",0);
            String  ТекущаяТаблица=      bundleПришлиДанные.getString("TableForProcessing","");
            String  ЗаданиеДля1ССервера=      bundleПришлиДанные.getString("JobForServer","");
            String  Login=      bundleПришлиДанные.getString("Login","");
            String  Password=      bundleПришлиДанные.getString("Password","");
            Long  CurrentVersionTable=      bundleПришлиДанные.getLong("CurrentVersionTable",0l);

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " АдресСервера1С " +АдресСервера1С+  "ПубличныйID " +ПубличныйID+
                    "  ТекущаяТаблица " +ТекущаяТаблица);
                    // MediaType JSON = MediaType.parse("application/json; charset=utf-8");
              OkHttpClient      okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                                @Override
                                public okhttp3.Response intercept(Chain chain) throws IOException {
                                    Request originalRequest = chain.request();
                                    Request.Builder builder = originalRequest.newBuilder()
                                            .header("PublicIDfromClient", String.valueOf(ПубличныйID))
                                            .header("TableForProcessing", ТекущаяТаблица)
                                            .header("JobForServer", ЗаданиеДля1ССервера)
                                            .header("Login", Login)
                                            .header("Password",Password )
                                            .header("CurrentVersionTable",String.valueOf( CurrentVersionTable) )
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
                    Request requestGET = new Request.Builder().get().url(АдресСервера1С).build();
                    Log.d(this.getClass().getName(), "  request  " + requestGET);
                    // TODO  Call callGET = client.newCall(requestGET);
                    okHttpClient.newCall(requestGET).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());

                            // TODO: 28.12.2024 closeting
                            //TODO закрываем п отоки
                            okHttpClient.dispatcher().executorService().shutdown();
                        }
                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String  ПришедшегоПотока =    response.header("stream_size");
                                ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                                Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                                Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                                Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));

                                БуферGET1С[0] =response.body().string();
                                ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                                Log.d(this.getClass().getName(), "  БуферGET1С  " + БуферGET1С[0].toString() + "  responseGet.code()" + response.code());
                            }
                            // TODO: 28.12.2024 closeting
                            response.close();

                            //TODO закрываем п отоки
                            okHttpClient.dispatcher().executorService().shutdown();
                        }
                    });
                    // TODO: 31.05.2022
                    okHttpClient.dispatcher().executorService().awaitTermination(1,TimeUnit.DAYS);

                    // TODO: 06.07.2022
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
        return БуферGET1С[0];
    }






}