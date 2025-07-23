package com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.DownloadsJBOSS.BunessLogicDownloadBufferReader.DownloadReader;
import com.dsy.dsu.BusinessLogicForApps.DownloadsJBOSS.BunessLogicDownloadBufferReader.GetBinessLogicDownloadReader;
import com.dsy.dsu.BusinessLogicForApps.DownloadsJBOSS.BunessLogicDownloadByte.DownloadByte;
import com.dsy.dsu.BusinessLogicForApps.DownloadsJBOSS.BunessLogicDownloadByte.GetBinessLogicDownloadByteBuffer;
import com.dsy.dsu.BusinessLogicForApps.DownloadsJBOSS.BunessLogicDownloadFiles.DownloadFiles;
import com.dsy.dsu.BusinessLogicForApps.DownloadsJBOSS.BunessLogicDownloadFiles.GetBinessLogicDownloadFiles;
import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;

import com.dsy.dsu.BusinessLogicForApps.DeviceName.ModulegetDeviceName;
import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossContext;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossHilt.intarfaces.getHiltPortJbossInterface;
import com.dsy.dsu.BusinessLogicForApps.Hilt.OkhhtpBuilder.GetAsyncOkHttpClientBuilder;
import com.sous.backasync.launch.ModuleDeleting;
import com.sous.backasync.launch.ModuleInserting;
import com.sous.backasync.launch.ModuleQuety;
import com.sous.backasync.launch.ModuleUpdating;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPOutputStream;

import javax.inject.Inject;
import javax.inject.Named;
import javax.net.ssl.SSLSocketFactory;

import dagger.Module;
import dagger.hilt.EntryPoints;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

///////todo Универсальный Код Логики



@Module
@InstallIn(SingletonComponent.class)
@Named
 public class CoreBinessLogics {
    private     Context context;
    private JbossContext jbossContext =null;

    private String ПубличноеЛогин =      new String();
    private  String ПубличноеПароль =   new String();
    private SharedPreferences preferencesJboss;


    public  @Inject CoreBinessLogics(@ApplicationContext Context context) {
       this. context=context;
       try{
        //TODO контроль потоков
           jbossContext =new JbossContext(context);
        // TODO: 16.04.2025
           preferencesJboss = context.getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);


        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  );
        // TODO: 06.10.2024
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }


    //todo #GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET
    public StringBuffer МетодУниверсальныйСервернаяВерсияДанныхДанныесСервера(String ИмяТаблицы,
                                                                              String Тип,
                                                                              String JobForServer,
                                                                              Long Версия,
                                                                              Integer ID,
                                                                              String ИмяСервера,
                                                                              Integer ИмяПорта,
                                                                              SSLSocketFactory getsslSocketFactory2) {

        AtomicReference<StringBuffer>  БуферСамиДанныеОтСервера = new AtomicReference<>(new StringBuffer());
        try {
            String enableSSl = preferencesJboss.getString("enablesll","http");
            String СтрокаСвязиСсервером =enableSSl+"://"+ИмяСервера+":"+ИмяПорта+"/"+new JbossContext(context).getСсылкаНаРежимСервераТабель();;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            Log.d(this.getClass().getName(), "   СтрокаСвязиСсервером "+  СтрокаСвязиСсервером);
            String Params = "?" + "NameTable= " + ИмяТаблицы.trim() +
                    "&" + "JobForServer=" + JobForServer.trim() + ""
                    + "&" + "IdUser=" + ID + ""
                    + "&" + "VersionData=" + Версия + "";
            СтрокаСвязиСсервером=   СтрокаСвязиСсервером + Params;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            URL Adress = new URL(СтрокаСвязиСсервером);
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " + СтрокаСвязиСсервером);
            // TODO: 14.05.2025
            OkHttpClient.Builder builderokhtttp=   new GetAsyncOkHttpClientBuilder(context,getsslSocketFactory2).GetAsyncOkHttpClientBuilder(enableSSl);
            OkHttpClient okHttpClientДанныеОтСервера = builderokhtttp.addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                              // TODO: 14.05.2025
                            String Текущаятаблицы="successlogin";
                            // TODO: 14.05.2025  получение данных
                            ModuleQuety moduleQuety=new ModuleQuety(context);
                            Cursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО   =moduleQuety.getModuleQuery(Текущаятаблицы,
                                    "  SELECT success_users,success_login  FROM "+Текущаятаблицы+"  ORDER BY date_update DESC " , null);

                            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                ПубличноеЛогин =         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                ПубличноеПароль =           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();

                            }
                            // TODO: 14.05.2025
                            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.close();
                            Log.d(this.getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " ПубличноеЛогин " +ПубличноеЛогин + " ПубличноеПароль " +ПубличноеПароль);

                            // TODO: 18.02.2025 get name Device
                            String ANDROID_ID =new ModulegetDeviceName().getDeviceName(context);
                            
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            Log.d(this.getClass().getName(), "  ПубличноеЛогин " + ПубличноеЛогин + " ПубличноеПароль " + ПубличноеПароль+" ANDROID_ID "+ANDROID_ID);
                            
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("Content-Type", Тип + " ;charset=UTF-8")
                                    .header("Accept-Encoding", "gzip,*,compress,identity,deflate,zstd,br")
                                    .header("Connection", "Keep-Alive")
                                    .header("Accept-Language", "ru-RU")
                                    .header("identifier", ПубличноеЛогин)
                                    .header("p_identifier", ПубличноеПароль)
                                    .header("id_device_androis", ANDROID_ID);
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .build();
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(Adress).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            // TODO  Call callGET = client.newCall(requestGET);
            Dispatcher dispatcherДанныеОтСервера = okHttpClientДанныеОтСервера.dispatcher();
            okHttpClientДанныеОтСервера.newCall(requestGET).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                    Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                    new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 28.12.2024
                    // TODO: 31.05.2022
                    dispatcherДанныеОтСервера.executorService().shutdown();
                    //TODO закрываем п отоки
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try{
                    if (response.isSuccessful()) {
                        String  ПришедшегоПотока =    response.header("stream_size");
                        ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                        Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                        // TODO: 29.09.2023
                        Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                        Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));
                        if (РазмерПришедшегоПотока>0l) {
                            // TODO: 07.10.2023  gzip
                            // TODO: 07.10.2023  gzip

                            // TODO: 07.04.2025  получаем STEAM  от сервера и обрабоатываем его для READER
                            DownloadReader downloadReader=new DownloadReader();
                            // TODO: 07.04.2025 обрабоатываем пршедщий файл
                            // TODO: 07.04.2025 обрабоатываем пршедщий файл
                            БуферСамиДанныеОтСервера.getAndSet(downloadReader.downloadReader(context, new GetBinessLogicDownloadReader(), response.body().bytes())) ;
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "     БуферСамиДанныеОтСервера.get()" +   БуферСамиДанныеОтСервера.get()
                                    +  " РазмерПришедшегоПотока " + РазмерПришедшегоПотока);

                        }

                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "     БуферСамиДанныеОтСервера.get()" +   БуферСамиДанныеОтСервера.get()
                                +  " РазмерПришедшегоПотока " + РазмерПришедшегоПотока);

                        // TODO: 28.12.2024 closeting
                        response.close();
                        // TODO: 31.05.2022
                        dispatcherДанныеОтСервера.executorService().shutdown();
                    }
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
            dispatcherДанныеОтСервера.executorService().awaitTermination(1, TimeUnit.DAYS);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            String ОшибкаТекущегоМетода = e.toString();
            if (!ОшибкаТекущегоМетода.toString().trim().trim().matches("(.*)java.io.EOFException(.*)") &&
                    !ОшибкаТекущегоМетода.toString().trim().trim().matches("(.*)java.net.sockettimeoutexception(.*)")
                    &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)SocketTimeout(.*)")) {
                Log.e(CoreBinessLogics.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        //// todo get ASYNtASK
        return БуферСамиДанныеОтСервера.get();

    }

    // TODO: 06.09.2023  пришли данные байтовые от сервера
    ///МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА
    public   byte[]     методGetByteFromServerAsync(String ИмяТаблицы,
                                                                              String Тип,
                                                                              String JobForServer,
                                                                              Long Версия,
                                                                              Integer ID,
                                                                              String ИмяСервера,
                                                                              Integer ИмяПорта,
                                                         SSLSocketFactory getsslSocketFactory2) {

        AtomicReference<byte[]>  inputStreamJaksonByte = new AtomicReference();
        try {
            String enableSSl = preferencesJboss.getString("enablesll","http");
            String СтрокаСвязиСсервером =enableSSl+"://"+ИмяСервера+":"+ИмяПорта+"/"+new JbossContext(context).getСсылкаНаРежимСервераТабель();;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            Log.d(this.getClass().getName(), "   СтрокаСвязиСсервером "+  СтрокаСвязиСсервером);
            String Params = "?" + "NameTable= " + ИмяТаблицы.trim() +
                    "&" + "JobForServer=" + JobForServer.trim() + ""
                    + "&" + "IdUser=" + ID + ""
                    + "&" + "VersionData=" + Версия + "";
            СтрокаСвязиСсервером=   СтрокаСвязиСсервером + Params;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            URL Adress = new URL(СтрокаСвязиСсервером);
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " + СтрокаСвязиСсервером);
            // TODO: 14.05.2025
            
            OkHttpClient.Builder builderokhtttp=   new GetAsyncOkHttpClientBuilder(context,getsslSocketFactory2).GetAsyncOkHttpClientBuilder(enableSSl);
            OkHttpClient okHttpClientДанныеОтСервера = builderokhtttp.addInterceptor(new Interceptor() {// " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;"
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            // TODO: 14.05.2025


                            // TODO: 14.05.2025
                            String Текущаятаблицы="successlogin";
                            // TODO: 14.05.2025  получение данных
                            ModuleQuety moduleQuety=new ModuleQuety(context);
                            Cursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО   =moduleQuety.getModuleQuery(Текущаятаблицы,
                                    "  SELECT D.success_users,D.success_login  FROM "+Текущаятаблицы+" AS D ORDER BY D.date_update DESC " , null);

                            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                ПубличноеЛогин =         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                ПубличноеПароль =           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();

                            }
                            // TODO: 14.05.2025
                            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.close();
                            Log.d(this.getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " ПубличноеЛогин " +ПубличноеЛогин + " ПубличноеПароль " +ПубличноеПароль);
                            // TODO: 18.02.2025 get name Device
                            String ANDROID_ID =new ModulegetDeviceName().getDeviceName(context);
                            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                            Log.d(this.getClass().getName(), "  ПубличноеЛогин " + ПубличноеЛогин + " ПубличноеПароль " + ПубличноеПароль+" ANDROID_ID "+ANDROID_ID);
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("Content-Type", Тип + " ;charset=UTF-8")
                                    .header("Accept-Encoding", "gzip,*,compress,identity,deflate,zstd,br")
                                    .header("Connection", "Keep-Alive")
                                    .header("Accept-Language", "ru-RU")
                                    .header("identifier", ПубличноеЛогин)
                                    .header("p_identifier", ПубличноеПароль)
                                    .header("id_device_androis", ANDROID_ID);
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .build();
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(Adress).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            // TODO  Call callGET = client.newCall(requestGET);
            Dispatcher dispatcherДанныеОтСервера = okHttpClientДанныеОтСервера.dispatcher();
            okHttpClientДанныеОтСервера.newCall(requestGET).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                    Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                    new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 31.05.2022
                    dispatcherДанныеОтСервера.executorService().shutdown();
                    //TODO закрываем п отоки
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try{
                        if (response.isSuccessful()) {
                            String  ПришедшегоПотока =    response.header("stream_size");
                            ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                            Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                            // TODO: 29.09.2023
                            Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                            Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));
                            if (РазмерПришедшегоПотока>0l) {
                                // TODO: 07.10.2023  gzip
                                // TODO: 07.04.2025  получаем STEAM  от сервера и обрабоатываем его для READER
                                DownloadByte downloadByte=new DownloadByte();
                                // TODO: 07.04.2025 обрабоатываем пршедщий файл
                                inputStreamJaksonByte.getAndSet(downloadByte.downloadByte(context, new GetBinessLogicDownloadByteBuffer(), response.body().bytes())); ;

                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+" inputStreamJaksonByte.get() " +inputStreamJaksonByte.get());
                            }
                            // TODO: 28.12.2024 closeting
                            response.close();
                            // TODO: 31.05.2022
                            dispatcherДанныеОтСервера.executorService().shutdown();
                        }
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
            dispatcherДанныеОтСервера.executorService().awaitTermination(1, TimeUnit.DAYS);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            String ОшибкаТекущегоМетода = e.toString();
            if (!ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.io.EOFException(.*)") &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.net.sockettimeoutexception(.*)")
                    &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)SocketTimeout(.*)")) {
                Log.e(CoreBinessLogics.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        //// todo get ASYNtASK
        return   inputStreamJaksonByte.get();

    }















    //todo #GET     //#GET  только для ПИНГА     //#GET  только для ПИНГА  //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА
    ///МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА
    @SuppressLint("Range")
    public Long МетодУниверсальногоПинга(String NameTable,
                                            String Тип ,
                                            String JobForServer,
                                            Long VersionData,
                                            Integer IdUser,
                                            String ИмяСервера,
                                            Integer ИмяПорта,
                                         @NotNull SSLSocketFactory getsslSocketFactory2)  {
        // TODO: 14.05.2025
       AtomicLong РазмерПришедшегоПотока = new AtomicLong(0l);
        try {
            String enableSSl = preferencesJboss.getString("enablesll","http");
            String СтрокаСвязиСсервером = enableSSl+"://" + ИмяСервера + ":" + ИмяПорта + "/"+ new JbossContext(context).getСсылкаНаРежимСервераRuntime();
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            Log.d(this.getClass().getName(), "   СтрокаСвязиСсервером " + СтрокаСвязиСсервером);
            String Params  = "?" + "NameTable= " + NameTable.trim() +
                    "&" + "JobForServer=" + JobForServer.trim() + ""+
                    "&" + "IdUser=" + IdUser + ""
                    + "&" + "VersionData=" + VersionData + "";
            Log.d(this.getClass().getName(), " Params" + Params);
            СтрокаСвязиСсервером = СтрокаСвязиСсервером + Params;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            URL Adress = new URL(СтрокаСвязиСсервером);
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " + СтрокаСвязиСсервером);
            // TODO: 11.03.2023 новый тест коде
            // TODO: 25.12.2024 Какой тип okhhtp подключить
            OkHttpClient.Builder builderokhtttp=   new GetAsyncOkHttpClientBuilder(context,getsslSocketFactory2).GetAsyncOkHttpClientBuilder(enableSSl);

            // TODO: 15.12.2023 end test
            OkHttpClient okHttpClientПинг = builderokhtttp.addInterceptor(new Interceptor() {///" SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;"
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            // TODO: 14.05.2025

                            // TODO: 14.05.2025
                            String Текущаятаблицы="successlogin";
                            // TODO: 14.05.2025  получение данных
                            ModuleQuety moduleQuety=new ModuleQuety(context);
                            Cursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО   =moduleQuety.getModuleQuery(Текущаятаблицы,
                                    "  SELECT D.success_users,D.success_login  FROM "+Текущаятаблицы+" AS D  ORDER BY D.date_update DESC " , null);

                            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                ПубличноеЛогин =         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                ПубличноеПароль =           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();

                            }
                            // TODO: 14.05.2025
                            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.close();
                            Log.d(this.getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " ПубличноеЛогин " +ПубличноеЛогин + " ПубличноеПароль " +ПубличноеПароль);
                            // TODO: 18.02.2025 get name Device
                            String ANDROID_ID =new ModulegetDeviceName().getDeviceName(context);
                            Log.d(this.getClass().getName(), "  JbossContext.ПубличноеИмяПользовательДлСервлета  " + ПубличноеЛогин +
                                    " JbossContext.ПубличноеПарольДлСервлета " + ПубличноеПароль);
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("Content-Type", Тип + " ;charset=UTF-8")
                                    .header("Accept-Encoding", "gzip,*,compress,identity,deflate,zstd,br")
                                    .header("Connection", "Keep-Alive")
                                    .header("Accept-Language", "ru-RU")
                                    .header("identifier", ПубличноеЛогин)
                                    .header("p_identifier", ПубличноеПароль)
                                    .header("id_device_androis", ANDROID_ID);
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(2,TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .build();
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(Adress).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            // TODO  Call callGET = client.newCall(requestGET);
            Dispatcher  dispatcherПинг = okHttpClientПинг.dispatcher();
            okHttpClientПинг.newCall(requestGET).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                    Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                    new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 31.05.2022
                    dispatcherПинг.executorService().shutdown();
                    //TODO закрываем п отоки
                }
                @SuppressLint("SuspiciousIndentation")
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try{
                    if (response.isSuccessful()) {
                        String  ПришедшегоПотока =    response.header("stream_size");
                        ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                        // TODO: 14.05.2025
                        РазмерПришедшегоПотока.getAndSet(Long.parseLong(ПришедшегоПотока  ));
                        // TODO: 29.09.2023
                        Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                        Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));
                        StringBuffer БуферРезультатПингасСервером = null;
                        if (РазмерПришедшегоПотока.get() >0l) {
                            // TODO: 07.04.2025  получаем STEAM  от сервера и обрабоатываем его для READER
                            DownloadReader downloadReader=new DownloadReader();
                            // TODO: 07.04.2025 обрабоатываем пршедщий файл
                            StringBuffer   РидерОтСервераМетодаGET=downloadReader.downloadReader(context, new GetBinessLogicDownloadReader(),
                                    response.body().bytes()) ;
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " РидерОтСервераМетодаGET " +РидерОтСервераМетодаGET
                                    +  " РазмерПришедшегоПотока.get() " +РазмерПришедшегоПотока.get());
                        }
                        Log.d(this.getClass().getName(), "БуферРезультатПингасСервером " + БуферРезультатПингасСервером +  " РазмерПришедшегоПотока.get()" + РазмерПришедшегоПотока.get());
                    }
                        // TODO: 28.12.2024 closeting
                        response.close();
                        // TODO: 31.05.2022
                        dispatcherПинг.executorService().shutdown();
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
            dispatcherПинг.executorService().awaitTermination(1,TimeUnit.DAYS);
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            String ОшибкаТекущегоМетода = ex.toString();
            if (!ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.io.EOFException(.*)") &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.net.sockettimeoutexception(.*)")
                    &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)SocketTimeout(.*)")) {

                Log.e(CoreBinessLogics.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                new RecordNewErros(context).recordnewerror(ex.toString(), CoreBinessLogics.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        return РазмерПришедшегоПотока.get();
    }

///todo #POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST


    ///////метод ОТПРАВКИ ДАННЫХ НА СЕРВЕР
    public StringBuffer методSendByteToAsync(@NonNull    byte[] ГенерацияJSONОтAndroid,
                                             @NonNull Integer ID,
                                             @NonNull String Таблица,
                                             @NonNull  String JobForServer,
                                             @NonNull   String ИмяСервера,
                                             @NonNull  Integer ИмяПорта,
                                             @NonNull SSLSocketFactory getsslSocketFactory2)  {

        AtomicReference<StringBuffer>  БуферCallsBackОтСеврера = new AtomicReference<>(new StringBuffer());
                try {
                    String enableSSl = preferencesJboss.getString("enablesll","http");
                    // TODO: 12.03.2023  метод POST()
                    String СтрокаСвязиСсервером =enableSSl+"://"+ИмяСервера+":"+ИмяПорта+"/"+new JbossContext(context).getСсылкаНаРежимСервераТабель();
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
                    String Params = "?" + "NameTable=" + Таблица.trim() + "&"
                            + "IdUser=" + ID +
                            "&" + "JobForServer=" + JobForServer.trim() + "";
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером + Params;
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
                    URL Adress = new URL(СтрокаСвязиСсервером);
                    Log.d(this.getClass().getName(), " Adress  " + Adress);


                    OkHttpClient.Builder builderokhtttp=   new GetAsyncOkHttpClientBuilder(context,getsslSocketFactory2).GetAsyncOkHttpClientBuilder(enableSSl);

                    OkHttpClient okHttpClientОтправкиДанныхНаСервер =builderokhtttp.addInterceptor(new Interceptor() {//" SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;"
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    // TODO: 14.05.2025
                                    String Текущаятаблицы="successlogin";
                                    // TODO: 14.05.2025  получение данных
                                    ModuleQuety moduleQuety=new ModuleQuety(context);
                                    Cursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО   =moduleQuety.getModuleQuery(Текущаятаблицы,
                                            "  SELECT D.success_users,D.success_login  FROM "+Текущаятаблицы+" AS D  ORDER BY D.date_update DESC " , null);

                                    if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                        ПубличноеЛогин =         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                        ПубличноеПароль =           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();

                                    }
                                    // TODO: 14.05.2025
                                    Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.close();
                                    Log.d(this.getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " ПубличноеЛогин " +ПубличноеЛогин + " ПубличноеПароль " +ПубличноеПароль);
                                    // TODO: 18.02.2025 get name Device
                                    String ANDROID_ID =new ModulegetDeviceName().getDeviceName(context);
                                    Log.d(this.getClass().getName(), "  JbossContext.ПубличноеИмяПользовательДлСервлета  " + ПубличноеЛогин +
                                            " JbossContext.ПубличноеПарольДлСервлета " + ПубличноеПароль);
                                    Request originalRequest = chain.request();
                                    Request.Builder builder = originalRequest.newBuilder()
                                            .header("Content-Type", "application/octet-stream ;charset=UTF-8")
                                            .header("Accept-Encoding", "gzip,*,compress,identity,deflate,zstd,br")
                                            .header("Connection", "Keep-Alive")
                                            .header("Accept-Language", "ru-RU")
                                            .header("identifier", ПубличноеЛогин)
                                            .header("p_identifier", ПубличноеПароль)
                                            .header("id_device_androis", ANDROID_ID);
                                    Request newRequest = builder.build();
                                    return chain.proceed(newRequest);
                                }
                            }).connectTimeout(5, TimeUnit.SECONDS)
                            .writeTimeout(2, TimeUnit.MINUTES)
                            .readTimeout(2, TimeUnit.MINUTES)
                            .build();
                    ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");

                    Log.i(context.getClass().getName(), "ГенерацияJSONОтAndroid.toString()" + ГенерацияJSONОтAndroid.toString());
                    // MediaType JSON = MediaType.parse("application/json; charset=utf-16");
                      MediaType JSON = MediaType.parse("application/octet-stream; charset=utf-8");
                   // RequestBody body = RequestBody.create(JSON, СгенерированыйФайлJSONДляОтправкиНаСервер.toString());
                    RequestBody requestBody = new RequestBody() {
                        @Override
                        public MediaType contentType() {
                            try{
                                Log.d(this.getClass().getName(), " requestBody ");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                            return JSON;
                        }

                        @Override
                        public void writeTo(BufferedSink sink) throws IOException {
                            // TODO: 21.09.2023 SEND BITY FROM SERVEER
                            try (GZIPOutputStream gzipOutputStream =       new GZIPOutputStream(sink.outputStream(),2048,true );){///4096
                                // TODO: 07.10.2023  wreting to server..
                                gzipOutputStream.write(ГенерацияJSONОтAndroid);
                                gzipOutputStream.finish();
                                gzipOutputStream.close();
                                Log.d(this.getClass().getName(), " requestBody  ГенерацияJSONОтAndroid.toString() "+ГенерацияJSONОтAndroid.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        }
                    };
                    Request requestPost = new Request.Builder().post(requestBody).url(Adress).build();
                    Log.d(this.getClass().getName(), "  requestPost  " + requestPost);
                    // TODO  Call callGET = client.newCall(requestGET);
                    Dispatcher  dispatcherCallsBackСервера = okHttpClientОтправкиДанныхНаСервер.dispatcher();
                    okHttpClientОтправкиДанныхНаСервер.newCall(requestPost).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                            Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                            new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 31.05.2022
                            dispatcherCallsBackСервера.executorService().shutdown();
                            //TODO закрываем п отоки
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
                                if (РазмерПришедшегоПотока>0l) {
                                    // TODO: 07.10.2023
                                    // TODO: 07.04.2025  получаем STEAM  от сервера и обрабоатываем его для READER
                                    DownloadReader downloadReader=new DownloadReader();
                                    // TODO: 07.04.2025 обрабоатываем пршедщий файл
                                    БуферCallsBackОтСеврера.getAndSet(downloadReader.downloadReader(context, new GetBinessLogicDownloadReader(), response.body().bytes()) );
                                    // TODO: 14.05.2025
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " БуферCallsBackОтСеврера.get()  " +БуферCallsBackОтСеврера.get()
                                            +  " РазмерПришедшегоПотока" + РазмерПришедшегоПотока);

                                        Log.d(this.getClass().getName(), "БуферCallsBackОтСеврера.get() " + БуферCallsBackОтСеврера.get() +
                                                " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);
                                }

                                Log.d(this.getClass().getName(), " БуферCallsBackОтСеврера.get()" +  БуферCallsBackОтСеврера.get() +  " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);

                                // TODO: 28.12.2024 closeting
                                response.close();
                                // TODO: 31.05.2022
                                dispatcherCallsBackСервера.executorService().shutdown();
                            }
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
                    dispatcherCallsBackСервера.executorService().awaitTermination(1, TimeUnit.DAYS);
                    // TODO: 12.03.2023  тест код конец
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                    String ОшибкаТекущегоМетода=new String();
                    if (!ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.io.EOFException(.*)") &&
                            !ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.net.sockettimeoutexception(.*)") &&
                            !ОшибкаТекущегоМетода.toString().trim().matches("(.*)SocketTimeout(.*)")) {
                        Log.e(CoreBinessLogics.class.getName(), "Ошибка " + ex + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(ex.toString(), CoreBinessLogics.class.getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
        return БуферCallsBackОтСеврера.get();
    }

    ////--- TODO ТУТ НАХОДЯТЬСЯ КОНТЕРЙНЕРЫ ДЛЯ ВСТАВКИ И ОБНОВЛЕНИИ ДАННЫХ  ЧЕРЕЗ КОНТЕЙНЕРЫ







    ///////// TODO УНИВЕРСАЛЬНЫЙ МЕТОД ВСТАВКИ ДАННЫХ
    protected Integer  ВставкаДанныхЧерезКонтейнерУниверсальная(String ТаблицаКудаВставляем,
                                                            ContentValues КонтейнерДляВставки){
        Integer Результат_ВставкиДанных = 0;
        try {
            // TODO: 14.05.2025
            ModuleInserting moduleInserting=new ModuleInserting(context);
           // TODO: 14.05.2025
            Результат_ВставкиДанных =    moduleInserting.getModuleInsert(ТаблицаКудаВставляем,КонтейнерДляВставки);


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Результат_ВставкиДанных "+Результат_ВставкиДанных );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Результат_ВставкиДанных;
    }



    /////////TODO  ОБНОВЛЕНИЕ КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    protected Integer ОбновлениеДанныхЧерезКонтейнерУниверсальная(String ТаблицаКудаОбновляем,
                                                                  ContentValues КонтейнерДляОбновления,
                                                                  String UUIDДляСостыковПриОбновления,
                                                                  String ИндификаторЧерезЧегоОбнолвяемсяUUIDИлиID) {
        Integer Результат_ОбновлениеДанных = 0;

        try {

            ModuleUpdating moduleUpdating = new ModuleUpdating(context);
            // TODO: 03.02.2025 update new back
            Результат_ОбновлениеДанных=   moduleUpdating.getModuleUpdate(ТаблицаКудаОбновляем,КонтейнерДляОбновления,ИндификаторЧерезЧегоОбнолвяемсяUUIDИлиID+"=?", new String[] {UUIDДляСостыковПриОбновления});

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Результат_ОбновлениеДанных "+Результат_ОбновлениеДанных );



        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Результат_ОбновлениеДанных;
    }



    /////////TODO КОНТЕЙНЕР ЛОКАЛЬНОГО ОБНОВЛЕНИЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(String ТаблицаКудаОбновляем,
                                                                        @NonNull ContentValues КонтейнерДляЛокальногоОбновления,
                                                                         Long UUIDДляСостыковПриОбновления,
                                                                         String ФлагЧерезЧегоОбновляетьсяIDилиUUID)  {
        //////////////////////////////////////////////////
        Integer Результат_ЛокальногоОбновлениеДанных = 0;
            try {
                ModuleUpdating moduleUpdating = new ModuleUpdating(context);
                // TODO: 03.02.2025 update new back
                Результат_ЛокальногоОбновлениеДанных=   moduleUpdating.getModuleUpdate(ТаблицаКудаОбновляем,КонтейнерДляЛокальногоОбновления,ФлагЧерезЧегоОбновляетьсяIDилиUUID+"=?", new String[] {String.valueOf(UUIDДляСостыковПриОбновления)});

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Результат_ЛокальногоОбновлениеДанных "+Результат_ЛокальногоОбновлениеДанных );

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
        return Результат_ЛокальногоОбновлениеДанных;
    }




    public Integer ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНовогоСотрудникаУниверсальная(@NonNull  String ТаблицаКудаВставляем,
                                                                                          @NonNull  ContentValues КонтейнерДляВставкиНовогоСотрудника)  {
        Integer getcreatingAnewEmployee = 0;
            try {
                ModuleInserting moduleInserting=new ModuleInserting(context);
                getcreatingAnewEmployee =    moduleInserting.getModuleInsert(ТаблицаКудаВставляем,КонтейнерДляВставкиНовогоСотрудника);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getcreatingAnewEmployee "+getcreatingAnewEmployee );

            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        return getcreatingAnewEmployee;
    }



















  public Integer ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНСообщенияДЛЯЧата(@NonNull  String ТаблицаКудаВставляем, @NonNull ContentValues КонтейнерДляВставкиДляЧата)  {

      Integer Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата = 0;
            try {
                    // TODO: 14.05.2025
                    ModuleInserting moduleInserting=new ModuleInserting(context);
                    // TODO: 14.05.2025
                    Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата =    moduleInserting.getModuleInsert(ТаблицаКудаВставляем,КонтейнерДляВставкиДляЧата);
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата "+Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        return Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата;
    }













    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer laumchReadPublicIDAfterSettingOrSuccessTables(
            @NonNull String ИмяТаблицы,
            @NonNull   ContentValues КонтейнерДляВставкиПубличныйID,
            @NonNull String КолонкаПосикаpublicID ) {
        // TODO: 08.10.2024
        Integer   getUpdatingPublicID=0;
        try {
// TODO: 08.10.2024 Update PUBLIC ID AFTER SYNnc

            // TODO: 14.05.2025
            ModuleQuety moduleQuety=new ModuleQuety(context);
            Cursor getCursorSuccessLoginOrSettingsTabels=   moduleQuety.getModuleQuery(ИмяТаблицы,"   SELECT D."+КолонкаПосикаpublicID+" " +
                    " FROM   "+ИмяТаблицы+" AS D ORDER BY D.date_update  LIMIT   1  " ,null);

            // TODO: 12.04.2023 UPDATER PUBLIC ID
          if(getCursorSuccessLoginOrSettingsTabels.getCount()>0 ){
              // TODO: 12.04.2023 UPDATER PUBLIC ID
              ModuleUpdating moduleUpdating = new ModuleUpdating(context);
              // TODO: 03.02.2025 update new back
              getUpdatingPublicID=   moduleUpdating.getModuleUpdate(ИмяТаблицы,КонтейнерДляВставкиПубличныйID);
              Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  getUpdatingPublicID " +getUpdatingPublicID);


              // TODO: 08.10.2024 Insert PUBLIC ID
          }else {
              // TODO: 12.04.2023 INSERT PUBLIC ID
              // TODO: 14.05.2025
              ModuleInserting moduleInserting=new ModuleInserting(context);
              // TODO: 14.05.2025
              getUpdatingPublicID =    moduleInserting.getModuleInsert(ИмяТаблицы,КонтейнерДляВставкиПубличныйID);

              Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  getUpdatingPublicID " +getUpdatingPublicID);

          }
          //TODO
            getCursorSuccessLoginOrSettingsTabels.close();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " getCursorSuccessLoginOrSettingsTabels " +getCursorSuccessLoginOrSettingsTabels.getCount());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getUpdatingPublicID;
    }







    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer wewillsetupanewPublicidaftersuccessfulsynchronizationSuccessLogin(
            @NonNull String ИмяТаблицы,
            @NonNull   ContentValues КонтейнерДляВставкиПубличныйID,
            @NonNull Integer PublicID )
            throws ExecutionException,
            InterruptedException, TimeoutException {
        // TODO: 08.10.2024
        Integer   UpdatingPublicID=0;
        try {
// TODO: 08.10.2024 Update PUBLIC ID AFTER SYNnc
// TODO: 08.10.2024 Update PUBLIC ID AFTER SYNnc
            Uri uri = Uri.parse("content://com.dsy.dsu.providerforsystemtables/" + ИмяТаблицы + "");
            // TODO: 08.10.2024 Дополнительное добавление данных
            КонтейнерДляВставкиПубличныйID.put("publicid",PublicID);
            // TODO: 08.10.2024 Находим если такой  Пользователь
            Integer getFindPublicIdInApp=  new GetttingPublicID().getttingPublicID(context);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " getFindPublicIdInApp " +getFindPublicIdInApp);


            // TODO: 12.04.2023 UPDATER PUBLIC ID
            if(getFindPublicIdInApp>0 ){
                // TODO: 12.04.2023 UPDATER PUBLIC ID
                ModuleUpdating moduleUpdating = new ModuleUpdating(context);
                // TODO: 03.02.2025 update new back
                UpdatingPublicID=   moduleUpdating.getModuleUpdate(ИмяТаблицы,КонтейнерДляВставкиПубличныйID);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  UpdatingPublicID " +UpdatingPublicID);


                // TODO: 08.10.2024 Insert PUBLIC ID
            }else {
                // TODO: 12.04.2023 INSERT PUBLIC ID
                // TODO: 14.05.2025
                ModuleInserting moduleInserting=new ModuleInserting(context);
                // TODO: 14.05.2025
                UpdatingPublicID =    moduleInserting.getModuleInsert(ИмяТаблицы,КонтейнерДляВставкиПубличныйID);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  UpdatingPublicID " +UpdatingPublicID);

            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " UpdatingPublicID " +UpdatingPublicID);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return UpdatingPublicID;
    }


    /////////TODO  ОБНОВЛЕНИЕ КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    Long ОбновлениеДанныхЧерезКонтейнерТолькоПриСозданииСУниверсальная(String ТаблицаКудаОбновляем,
                                                                                      ContentValues КонтейнерДляОбновленияСозданииНовогоСотрудника,
                                                                                      String UUIDДляСостыковПриОбновления) {

        long Результат_ОбновлениеДанныхОбновлениеСозданииНового = 0;

            try {
                ModuleUpdating moduleUpdating = new ModuleUpdating(context);
                // TODO: 03.02.2025 update new back
                Результат_ОбновлениеДанныхОбновлениеСозданииНового=   moduleUpdating.getModuleUpdate(ТаблицаКудаОбновляем,КонтейнерДляОбновленияСозданииНовогоСотрудника,
                        "uuid"+"=?", new String[] {String.valueOf(UUIDДляСостыковПриОбновления)});

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Результат_ОбновлениеДанныхОбновлениеСозданииНового "+Результат_ОбновлениеДанныхОбновлениеСозданииНового );

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            ////
        return Результат_ОбновлениеДанныхОбновлениеСозданииНового;
    }



    ///////// todo КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ ТОЛЬКО ДЛЯ ЗАПИСИ ОШИБКИ
    Long ВставкаДанныхЧерезКонтейнерУниверсальнаяТолькоДляЗаписиОшибки(String ТаблицаКудаВставляем,
                                                                       ContentValues КонтейнерДляВставкиЗаписиОшибки) {
        long Результат_ВставкиДанныхДляЗаписиОшибки = 0;
            try {
// TODO: 14.05.2025
                ModuleInserting moduleInserting=new ModuleInserting(context);
                // TODO: 14.05.2025
                Результат_ВставкиДанныхДляЗаписиОшибки =    moduleInserting.getModuleInsert(ТаблицаКудаВставляем,КонтейнерДляВставкиЗаписиОшибки);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Результат_ВставкиДанныхДляЗаписиОшибки "+Результат_ВставкиДанныхДляЗаписиОшибки );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        return Результат_ВставкиДанныхДляЗаписиОшибки;
    }


    /////////TODO КОНТЕЙНЕР УДАЛЕНИЕ СОТРУДНИКА ИЗ ТАБЕЛЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer УдалениеДанныхЧерезКонтейнерУниверсальная(String ТаблицаОткудаУдлаяемЗапись,
                                                             String ЧерезКакоеПолеУдлаяемФлаг,
                                                             Long UUIDДляСостыковПриОбновления,
                                                             String ПолеКудаИзменятьСтатус,
                                                             String ЗапоЗначенияУсменыСтатуса) {
        Integer Результат_УдалениеДанных = 0;
            try {
                ContentValues contentValuesDelete=new ContentValues();
                contentValuesDelete.put(ПолеКудаИзменятьСтатус,ЗапоЗначенияУсменыСтатуса);
                // TODO: 14.05.2025
                ModuleUpdating moduleUpdating = new ModuleUpdating(context);
                // TODO: 03.02.2025 update new back
                Результат_УдалениеДанных=   moduleUpdating.getModuleUpdate(ТаблицаОткудаУдлаяемЗапись,contentValuesDelete,ЧерезКакоеПолеУдлаяемФлаг+"=?", new String[] {String.valueOf(UUIDДляСостыковПриОбновления)});
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Результат_УдалениеДанных "+Результат_УдалениеДанных );
                } catch (Exception e) {///////ошибки
                    e.printStackTrace();
                    Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
        return Результат_УдалениеДанных;
    }

    public Long МетодЛокальноеОбновлениеВТабеле(ContentValues КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                                                String ПолучениеЗначениеСтолбикUUID,
                                                Context КонтексДляЛокальногоОбновления,
                                                String таблицаДляЛокальногоОбонвления) throws InterruptedException, ExecutionException, TimeoutException {
        Integer результатОбновлениеЧерезКонтрейнер = 0;
        try {
            ///////TODO САМ ВЫЗОВ МЕТОДА ОБНОВЛЕНИЕ ЛОКАЛЬНОГО обновление uuid
            результатОбновлениеЧерезКонтрейнер = ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(таблицаДляЛокальногоОбонвления,
                    КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                    Long.parseLong(ПолучениеЗначениеСтолбикUUID), "uuid");
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " результатОбновлениеЧерезКонтрейнер "+результатОбновлениеЧерезКонтрейнер );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Long.parseLong(String.valueOf(результатОбновлениеЧерезКонтрейнер));//5,TimeUnit.SECONDS

    }


    public Integer launchDeletingDataTabels(String ТаблицаОткудаУдлаяемЗапись,
                                            String ЧерезКакоеПолеУдлаяемФлаг,
                                            Long UUIDДляСостыковПриОбновления) {
        Integer Результат_УдалениеДанных = 0;
        // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕ
            try {
                // TODO: 14.05.2025
                ModuleDeleting moduleDeleting = new ModuleDeleting(context);
                // TODO: 03.02.2025 update new back
                Результат_УдалениеДанных=    moduleDeleting.getModuleDelete(ТаблицаОткудаУдлаяемЗапись,
                        ЧерезКакоеПолеУдлаяемФлаг,new String[]{UUIDДляСостыковПриОбновления.toString()});
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Результат_УдалениеДанных "+Результат_УдалениеДанных );

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e.toString() + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        return Результат_УдалениеДанных;
    }







    public Integer launchUpdateData(String Таблица,
                                    String ЧерезКакоеПолеУдлаяемФлаг,
                                    Long UUIDUpdate) {
        Integer resultUpdate= 0;
        // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕ
        try {
            // TODO: 14.05.2025
            ContentValues contentValuesUpsdateData=new ContentValues();
            contentValuesUpsdateData = new ContentValues();
            contentValuesUpsdateData.put("status_send", "Удаленная");
            ////TODO ДАТА
            String СгенерированованныйДатаДляДаннойОперации=     new GetMainDateForApp(context).getMainDateForApp();
            contentValuesUpsdateData.put("date_update", СгенерированованныйДатаДляДаннойОперации);
            Long getUpversionData =
                    new VersionCurentTable(context).upVersionCurentTable(    Таблица);
            contentValuesUpsdateData.put("current_table", getUpversionData);

            ModuleUpdating moduleUpdating = new ModuleUpdating(context);
            // TODO: 03.02.2025 update new back
            resultUpdate=   moduleUpdating.getModuleUpdate(Таблица,contentValuesUpsdateData,ЧерезКакоеПолеУдлаяемФлаг+"=?", new String[] {UUIDUpdate.toString()});
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " resultUpdate "+resultUpdate );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e.toString() + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return resultUpdate;
    }


























    @SuppressLint("SuspiciousIndentation")
    public Integer МетодКоторыйЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobile(String ПередаваемыйРежимИнтрентета,
                                                                               Context context,
                                                                               String Таблица,
                                                                               String Поля) {
/////todo КОД ЗАПОЛЕНЕИЯ ДАННЫМИ В СПИНЕР ЦФО ДЕПАРТАМЕНТ МЕСЯЦ
        Integer РезультатОбновлениеЧерезКонтрейнер = 0;
        long PublicId=0l;
                try {
                    ContentValues ВставляемВБАзуВыбранныйРежимИнтренета=new ContentValues();
                   ВставляемВБАзуВыбранныйРежимИнтренета = new ContentValues();
                    ВставляемВБАзуВыбранныйРежимИнтренета.put("id", PublicId);
                    ВставляемВБАзуВыбранныйРежимИнтренета.put(Поля, ПередаваемыйРежимИнтрентета);
                    ////TODO ДАТА
                    String СгенерированованныйДатаДляДаннойОперации=     new GetMainDateForApp(context).getMainDateForApp();
                    ВставляемВБАзуВыбранныйРежимИнтренета.put("date_update", СгенерированованныйДатаДляДаннойОперации);

                    ModuleUpdating moduleUpdating = new ModuleUpdating(context);
                    // TODO: 03.02.2025 update new back
                    РезультатОбновлениеЧерезКонтрейнер=   moduleUpdating.getModuleUpdate(Таблица,ВставляемВБАзуВыбранныйРежимИнтренета);

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " РезультатОбновлениеЧерезКонтрейнер "+РезультатОбновлениеЧерезКонтрейнер );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
        return РезультатОбновлениеЧерезКонтрейнер;
    }


    public String МетодПолучениеИмяСистемыДляСменыПользователя(Context context) {
        //
        String getSuccess_Users = new String();
        try {
            // TODO: 14.05.2025
            String Текущаятаблицы="successlogin";
            // TODO: 14.05.2025  получение данных
            ModuleQuety moduleQuety=new ModuleQuety(context);
            Cursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО   =moduleQuety.getModuleQuery(Текущаятаблицы,
                    "  SELECT D.success_users,D.success_login  FROM "+Текущаятаблицы+" AS D  ORDER BY D.date_update DESC " , null);

            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                getSuccess_Users =           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();

            }
            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.close();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getSuccess_Users "+getSuccess_Users );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
      return  getSuccess_Users;

    }

    // TODO: 09.04.2021 Метод Обновление Получение ПО с Сервера
    // TODO: 09.04.2021 Метод Обновление Получение ПО с Сервера
    public synchronized File МетодЗагрузкиОбновлениеПОсСервера(@NonNull String АдресЗагрузки,
                                                  @NonNull Context context,
                                                  @NonNull String ИмяСервера,
                                                  @NonNull Integer ИмяПорта,
                                                  @NonNull String ЗаданиеЗагрузки,
                                                  @NonNull String ИмяФайлаЗагрузки ,
                                                  @NonNull String ВозвращяемыйТип,
                                                  @NonNull SSLSocketFactory getsslSocketFactory2) {
        // TODO: 24.09.2024
         AtomicReference<File>  getFileAPKandJson=new AtomicReference<>();
        try {
            String enableSSl = preferencesJboss.getString("enablesll","http");
            String СтрокаСвязиСсервером =enableSSl+"://"+ИмяСервера+":"+ИмяПорта+"/";;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            СтрокаСвязиСсервером = СтрокаСвязиСсервером + АдресЗагрузки; /////"dsu1.glassfish/update_android_dsu1/output-metadata.json";
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            URL    Adress = new URL(СтрокаСвязиСсервером);


            OkHttpClient.Builder builderokhtttp=   new GetAsyncOkHttpClientBuilder(context,getsslSocketFactory2).GetAsyncOkHttpClientBuilder(enableSSl);

            OkHttpClient okHttpClientЗагрузкаНовогоПО = builderokhtttp.addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            // TODO: 14.05.2025
                            String Текущаятаблицы="successlogin";
                            // TODO: 14.05.2025  получение данных
                            ModuleQuety moduleQuety=new ModuleQuety(context);
                            Cursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО   =moduleQuety.getModuleQuery(Текущаятаблицы,
                                    "  SELECT D.success_users,D.success_login  FROM "+Текущаятаблицы+" AS D" +
                                            "   ORDER BY D.date_update DESC " , null);

                            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                ПубличноеЛогин =         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                ПубличноеПароль =           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();

                            }
                            // TODO: 14.05.2025
                            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.close();
                            // TODO: 18.02.2025 get name Device
                            String ANDROID_ID =new ModulegetDeviceName().getDeviceName(context);
                            Log.d(this.getClass().getName(), "  JbossContext.ПубличноеИмяПользовательДлСервлета  " + ПубличноеЛогин +
                                    " JbossContext.ПубличноеПарольДлСервлета " + ПубличноеПароль);
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("Content-Type", ВозвращяемыйТип)
                                    .header("Accept-Encoding", "gzip,*,compress,identity,deflate,zstd,br")
                                    .header("Connection", "Keep-Alive")
                                    .header("Accept-Language", "ru-RU")
                                    .header("identifier", ПубличноеЛогин)
                                    .header("p_identifier", ПубличноеПароль)
                                    .header("task_downlonupdatepo", ЗаданиеЗагрузки)
                                    .header("id_device_androis", ANDROID_ID);
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .build();
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(Adress).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            Dispatcher  dispatcherЗагрузкаПО = okHttpClientЗагрузкаНовогоПО.dispatcher();
            okHttpClientЗагрузкаНовогоПО.newCall(requestGET).enqueue(new Callback() {

                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    try{
                        Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                        Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                        new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 31.05.2022
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + ex + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(ex.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    dispatcherЗагрузкаПО.executorService().shutdown();
                    //TODO закрываем п отоки
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

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ФлагgZIPOutputStream " +ФлагgZIPOutputStream);

                            // TODO: 06.05.2023  если ПОТОК ЕСТЬ СОДЕРЖИВАЕМ ПАРСИМ
                            if(РазмерПришедшегоПотока>0){


                             final   InputStream getByteStream= response.body().byteStream();
                                // TODO: 07.04.2025  получаем STEAM  от сервера и обрабоатываем его для получение файла
                                DownloadFiles getBinessLogicDwonloadFiles=new DownloadFiles();
                                // TODO: 07.04.2025 обрабоатываем пршедщий файл
                                getFileAPKandJson.set(getBinessLogicDwonloadFiles.downloadFiles(context, new GetBinessLogicDownloadFiles(),getByteStream,ИмяФайлаЗагрузки)) ;

                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() +" getFileAPKandJson.get() " +getFileAPKandJson.get());

                                // TODO: 20.03.2023 ответ от сервреа если нет цифры значит не и файла
                            }
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }

                        // TODO: 28.12.2024 closeting
                        response.close();
                        // TODO: 06.05.2023 exit
                        dispatcherЗагрузкаПО.executorService().shutdown();
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
            dispatcherЗагрузкаПО.executorService().awaitTermination(1,TimeUnit.DAYS);
            // TODO: 07.04.2025  
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() +" getFileAPKandJson.get() " +getFileAPKandJson.get());
            // TODO: 13.03.2023  конец загрузки файла по новому FILE
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            String  ОшибкаТекущегоМетода = ex.toString();
            if (!ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.io.EOFException(.*)") &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.net.sockettimeoutexception(.*)")
                    &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)SocketTimeout(.*)") &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.net.ConnectException(.*)")
                    && !ОшибкаТекущегоМетода.toString().trim().matches("(.*)FileNotFoundException(.*)"))  {
                Log.e(CoreBinessLogics.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                new RecordNewErros(this.context).recordnewerror(ex.toString(), CoreBinessLogics.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
        }
        return   getFileAPKandJson.get();

    }

    public ContentValues МетодВычисляемВыходныеДниПриСозданииНовогоТабеляАвтоРЕжим(@NonNull Context КонтекстДляРежимаИнтрента,
                                                                                   @NonNull Integer Месяц ,
                                                                                    @NonNull Integer Год ) {

        ContentValues РезультатВычисленияВыходныхДней = new ContentValues();
        try {
            Log.d(КонтекстДляРежимаИнтрента.getClass().getName(), " Год  " + "--" + Год + " Месяц " + Месяц);/////

            Calendar calendar1 = Calendar.getInstance(new Locale("ru"));
            YearMonth yearMonthObject = YearMonth.of(Год, Месяц);
            int daysInMonth = yearMonthObject.lengthOfMonth()+1; //28
            SimpleDateFormat СозданияВычисляемВыходные=null;
            // TODO: 29.04.2022  int ИндексДней
            int ИндексДней;
            ///////TODO сам цикл который заполняет месяцами
            for ( ИндексДней=1;ИндексДней<daysInMonth;ИндексДней++) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    СозданияВычисляемВыходные = new SimpleDateFormat("yyyy-MM-dd", new Locale("rus"));
                } else {
                    СозданияВычисляемВыходные = new java.text.SimpleDateFormat("yyyy-MM-dd", new Locale("rus"));

                }
                Date   ДатаПосикаВыходныеДней       = СозданияВычисляемВыходные.parse (Год +"-"+Месяц+"-"+ИндексДней );
                // Then get the day of week from the Date based on specific locale.
            // TODO: 29.01.2022  отдельно только название дня
            String РезультатДатыДляКонктетногоТабеляТолькоЗанвание = new SimpleDateFormat("EEE", new Locale("ru")).format(ДатаПосикаВыходныеДней );
                if (РезультатДатыДляКонктетногоТабеляТолькоЗанвание.equalsIgnoreCase("сб")  ||
                        РезультатДатыДляКонктетногоТабеляТолькоЗанвание.equalsIgnoreCase("вс")) {
                    System.out.println("выходные дни при созадни тбалея полльзователь разрешмил авторежим" + ИндексДней);
                    String ОбьединяемДеньсЦифровдЛЯвСТАВКИ = "d" + String.valueOf(ИндексДней);
                    РезультатВычисленияВыходныхДней.put(ОбьединяемДеньсЦифровдЛЯвСТАВКИ, "В");
                }
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "   РезультатВычисленияВыходныхДней  " + РезультатВычисленияВыходныхДней.valueSet() + " daysInMonth " + daysInMonth);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатВычисленияВыходныхДней;
    }

    ////TODO САМ МЕТОД АУНТИФИКАЦИИ С СЕРВЕРОМ
    public Integer методАвторизацииЛогинИПаполь(@NonNull String ПубличноеЛогин,
                                                     @NonNull String ПубличноеПароль,
                                                @NotNull SSLSocketFactory getsslSocketFactory2) {


       AtomicInteger БуферПубличныйIDОтСервера = new AtomicInteger(0);
        try {
            String enableSSl = preferencesJboss.getString("enablesll","http");

            LinkedHashMap<Integer,String> getHiltPortJboss=   EntryPoints.get(context, getHiltPortJbossInterface.class).getHiltPortJboss();

            // TODO: 02.04.2024  Адресс и Порт Сервера Jboss
            String   ИмяСерверИзХранилица = getHiltPortJboss.values().stream().map(m->String.valueOf(m)).findFirst().get();
            Integer    ПортСерверИзХранилица = getHiltPortJboss.keySet().stream().mapToInt(m->m).findFirst().getAsInt();



            String ИмменоКакойСерверПодкючения =enableSSl+"://"+ИмяСерверИзХранилица+":"+ПортСерверИзХранилица+"/";
          String  СтрокаСвязиСсервером = ИмменоКакойСерверПодкючения +new JbossContext(context).getСсылкаНаРежимСервераТабель()+ "?"
                     + "JobForServer=" + "Хотим Получить ID для Генерации  UUID" + ""+
                  "&" + "IdUser=" + ПубличноеЛогин + "";
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " +СтрокаСвязиСсервером);
            URL Adress = new URL(СтрокаСвязиСсервером); //
            Log.d(this.getClass().getName(),  "СтрокаСвязиСсервером "+СтрокаСвязиСсервером+
                    "ПубличноеПароль "+ПубличноеПароль+
                    "СтрокаСвязиСсервером "+СтрокаСвязиСсервером
                    + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName());

            // TODO: 11.03.2023  текст код
            if (ПубличноеЛогин.length()>0 && ПубличноеПароль.length()>0 && СтрокаСвязиСсервером.length()>0) {

                OkHttpClient.Builder builderokhtttp=   new GetAsyncOkHttpClientBuilder(context,getsslSocketFactory2).GetAsyncOkHttpClientBuilder(enableSSl);
                // TODO: 05.03.2025
                OkHttpClient okHttpClientИмяиПароль =builderokhtttp.addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                // TODO: 18.02.2025 get name Device
                                String ANDROID_ID =new ModulegetDeviceName().getDeviceName(context);
                                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                                Log.d(this.getClass().getName(), "  ПубличноеЛогин " + ПубличноеЛогин + " ПубличноеПароль " + ПубличноеПароль);
                                Request originalRequest = chain.request();
                                Request.Builder builder = originalRequest.newBuilder()
                                        .header("Content-Type", "application/octet-stream ;charset=UTF-8")
                                        .header("Accept-Encoding", "gzip,*,compress,identity,deflate,zstd,br")
                                        .header("Connection", "Keep-Alive")
                                        .header("Accept-Language", "ru-RU")
                                        .header("identifier", ПубличноеЛогин)
                                        .header("p_identifier", ПубличноеПароль)
                                        .header("id_device_androis", ANDROID_ID);
                                Request newRequest = builder.build();
                                return chain.proceed(newRequest);
                            }
                        }).connectTimeout(5, TimeUnit.SECONDS)
                        .writeTimeout(2, TimeUnit.MINUTES)
                        .readTimeout(2, TimeUnit.MINUTES)
                        .build();
                ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
                Request requestGET = new Request.Builder().get().url(Adress).build();
                Log.d(this.getClass().getName(), "  request  " + requestGET);
                // TODO  Call callGET = client.newCall(requestGET);
                Dispatcher dispatcherПроверкаЛогиниПароль = okHttpClientИмяиПароль.dispatcher();
                okHttpClientИмяиПароль.newCall(requestGET).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                        Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                        new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 31.05.2022
                        dispatcherПроверкаЛогиниПароль.executorService().shutdown();

                        //TODO закрываем п отоки
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
                                if (РазмерПришедшегоПотока>0) {
                                    // TODO: 07.10.2023  gzip
                                    // TODO: 07.04.2025  получаем STEAM  от сервера и обрабоатываем его для READER
                                    DownloadReader downloadReader=new DownloadReader();
                                    // TODO: 07.04.2025 обрабоатываем пршедщий файл
                                    // TODO: 07.04.2025 обрабоатываем пршедщий файл
                                    StringBuffer   БуферПубличныйID=downloadReader.downloadReader(context, new GetBinessLogicDownloadReader(),
                                            response.body().bytes()) ;
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " БуферПубличныйID " +БуферПубличныйID
                                            +  " РазмерПришедшегоПотока " + РазмерПришедшегоПотока);
                                    // TODO: 31.05.2022
                                    String  БуферПубличный = БуферПубличныйID.toString().trim();

                                        if(     БуферПубличный.trim().matches("(.*)Server Running...... Don't Login(.*)")==true){
                                            БуферПубличный="-1";
                                            БуферПубличныйIDОтСервера.getAndSet(Integer.parseInt(БуферПубличный)) ;
                                        }else {
                                            БуферПубличный=   БуферПубличный .replaceAll("[^0-9]", "")
                                                    .replaceAll("]","")
                                                    .replaceAll("","");

                                            boolean isNumeric = БуферПубличный.chars().allMatch( Character::isDigit );
                                            if (isNumeric) {
                                                БуферПубличныйIDОтСервера.getAndSet(Integer.parseInt(БуферПубличный));
                                            }

                                        }
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " БуферПубличныйIDОтСервера.get() "+БуферПубличныйIDОтСервера.get() );
                                    // TODO: 31.05.2022
                                }

                            }
                            // TODO: 28.12.2024 closeting
                            response.close();
                            // TODO: 28.12.2024
                            dispatcherПроверкаЛогиниПароль.executorService().shutdown();
                            Log.i(this.getClass().getName(),  " java.security.cert.X509Certificate  "+
                                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    " время " +new Date().toLocaleString() );
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });
                dispatcherПроверкаЛогиниПароль.executorService().awaitTermination(1,TimeUnit.DAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return БуферПубличныйIDОтСервера.get();
    }



}//TODO END  CLASS















