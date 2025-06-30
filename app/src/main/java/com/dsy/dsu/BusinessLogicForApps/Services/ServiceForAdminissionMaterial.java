package com.dsy.dsu.BusinessLogicForApps.Services;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.loader.content.AsyncTaskLoader;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.GreatUuidGenerations.GreatUuidGeneration;
import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;
import com.sous.backasync.launch.ModuleInserting;


import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */

@AndroidEntryPoint
public class ServiceForAdminissionMaterial extends IntentService {
    public LocalBinderДляПолучениеМатериалов binder = new LocalBinderДляПолучениеМатериалов();
    private Context context;
    private String ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре;



    public ServiceForAdminissionMaterial() {
        super(
                "ServiceForAdminissionMaterial");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 16.04.2025
       // sqLiteDatabase = EntryPoints.get(context, HiltInterfacesqlite.class).getHiltSqlite();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + newConfig.locale);
        ;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.dump(fd, writer, args);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + newBase.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context = newBase;
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context = getApplicationContext();
        // TODO: 28.09.2022  запускаем службу табелей
        МетодCлужбыПолучениеМатериалов(getApplicationContext(), intent);
// TODO: 30.06.2022 сама не постредствено запуск метода
    }

    ////МЕТОД ЗАПОЛЕНИЯ ДАННЫМИ ИЗ БАЗЫ В СПИНЕР
    public LinkedHashMap<String, Object> МетодЗаполенинияДаннымиСпинеровДляНовогоМатериалаСФильром(
            @NonNull Context context ,@NonNull Intent intent, @NonNull Cursor cursorДляОдногоМатериалаБывВесовых) {
        Log.d(this.getClass().getName(), " МетодЗаполениеяСпинераДаннымиИзБазы() ");

        AsyncTaskLoader< LinkedHashMap<String, Object>> asyncTaskLoader=new AsyncTaskLoader(context) {
            @Nullable
            @Override
            public  LinkedHashMap<String, Object> loadInBackground() {
                LinkedHashMap<String, Object> ХэшиАрайЛистДляСпиноровФинал = null;
                try {
                    Bundle data = intent.getExtras();
                    ArrayList<String> АрайЛистДляСпинеров = new ArrayList<String>();
                    LinkedHashMap< String,Integer> ХэшДляСпиноров = new LinkedHashMap<>();
                    АрайЛистДляСпинеров.add("Выберете " + data.getString("ТаблицаОбработкиСпинера"));
                    String ПолеВторогоПоля= data.getString("ФильтрКолонок");
                    Integer ФильтрВесовых= data.getInt("ФильтрВесовых");
                    if (cursorДляОдногоМатериалаБывВесовых.getCount()>0) {
                        cursorДляОдногоМатериалаБывВесовых.moveToFirst();
                    }
                    ХэшиАрайЛистДляСпиноровФинал = new LinkedHashMap<>();
                    // TODO: 20.10.2022
                    ХэшиАрайЛистДляСпиноровФинал.putIfAbsent("АрайЛистДляСпинеров", АрайЛистДляСпинеров);
                    // TODO: 20.10.2022
                    ХэшиАрайЛистДляСпиноровФинал.putIfAbsent("ХэшДляСпиноров", ХэшДляСпиноров);
                    ХэшиАрайЛистДляСпиноровФинал.putIfAbsent("ЧистыйКурсорДляСпиноров", cursorДляОдногоМатериалаБывВесовых);
                    Log.d(this.getClass().getName(), " ХэшиАрайЛистДляСпиноровФинал " + ХэшиАрайЛистДляСпиноровФинал);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return ХэшиАрайЛистДляСпиноровФинал;
            }
        };
        return asyncTaskLoader.loadInBackground() ;
    }
    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinderДляПолучениеМатериалов extends Binder {
        public LocalBinderДляПолучениеМатериалов() {

            super();
        }








        @Override
        public void attachInterface(@Nullable IInterface owner, @Nullable String descriptor) {
            super.attachInterface(owner, descriptor);
        }

        @Nullable
        @Override
        public String getInterfaceDescriptor() {
            return super.getInterfaceDescriptor();
        }

        @Override
        public boolean pingBinder() {

            return super.pingBinder();
        }

        @Override
        public boolean isBinderAlive() {
            return super.isBinderAlive();
        }

        @Nullable
        @Override
        public IInterface queryLocalInterface(@NonNull String descriptor) {
            return super.queryLocalInterface(descriptor);
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public void linkToDeath(@NonNull DeathRecipient recipient, int flags) {
            super.linkToDeath(recipient, flags);
        }

        @Override
        public boolean unlinkToDeath(@NonNull DeathRecipient recipient, int flags) {
            return super.unlinkToDeath(recipient, flags);
        }

        public ServiceForAdminissionMaterial getService() {
            // Return this instance of LocalService so clients can call public methods
            return ServiceForAdminissionMaterial.this;
        }

        public void linkToDeath(DeathRecipient deathRecipient) {
            Log.d(this.getClass().getName(), "deathRecipient " + deathRecipient);
        }
    }

    public Cursor МетодCлужбыПолучениеМатериалов(@NonNull Context context, @NonNull Intent intent) {
        Cursor cursor = null;
        try {
            switch (intent.getAction().trim()) {
                case "ПолучениеЦФО":
                    cursor=       new SubClassGetDataAdmissionMaterial_ПолучаемЦФО().МетодПолучениеДанныхAsyncQueryhandler(intent,context);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;
                case "ПолучениеСгрупированныеСамиДанные":
                case "ПолучениеНомерМатериала":
                    cursor=       new SubClassGetDataAdmissionMaterial_ПолучаемМатериалы().МетодПолучениеДанныхAsyncQueryhandler(intent,context);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;
                // TODO: 10.11.2022  детализация
                case "ПолучениеНомерМатериалаДетализация":
                    cursor=       new SubClassGetDataAdmissionMaterial_Групировка_Детализация().МетодПолучениеДанныхAsyncQueryhandler(intent,context);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;
                // TODO: 03.11.2022  new создание МАТЕРИАЛОВ  --СОЗДАНИЕ
                case "ПолучениеМатериалоСозданиеНового":
                    cursor=       new    SubClassGetDataAdmissionMaterial_ПриСозданииНовогоМатериала().МетодПолучениеДанныхAsyncQueryhandler(intent,context);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;

                // TODO: 10.11.2022  новые два класс для новых двух полей АВТОМОБИЛИ И КОНТРАГЕНТЫ
                case "ПолучениеАвтомобильДляСозданиеНовгоМатерила":
                    cursor=       new SubClassGetDataAdmissionMaterial_Автомобили().МетодПолучениеДанныхAsyncQueryhandler(intent,context);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;
                // TODO: 03.11.2022  new создание МАТЕРИАЛОВ  --СОЗДАНИЕ
                case "ПолучениеКонтрагентовДляСозданиеНовгоМатерила":
                    cursor=       new    SubClassGetDataAdmissionMaterial_Контрагенты().МетодПолучениеДанныхAsyncQueryhandler(intent,context);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;







                // TODO: 03.11.2022  new создание МАТЕРИАЛОВ  --СОЗДАНИЕ
                case "ПолучениеМатериалоИзНовгоПоиска":
                    cursor=       new    SubClassGetDataAdmissionMaterial_Данные_ДляНовогоПоиска().МетодПолучениеДанныхДля_FilterQueryProviderAsyncQueryhandler(intent,context);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;


                // TODO: 03.11.2022  new создание МАТЕРИАЛОВ  --УДАЛЕНИЕ
                case "УдалениеВыбранныеМатериалыДетализации":
                    cursor=       new    SubClassGetDataAdmissionMaterial_ПриСозданииНовогоМатериала().МетодПолучениеДанныхAsyncQueryhandler(intent,context);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;



                default:
                    break;
            }

            Log.d(this.getClass().getName(), "   intent.getAction().trim() " + intent.getAction().trim()   );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return cursor;
    }

    public Integer МетодCлужбыУдалениеМатериалов(@NonNull Context context, @NonNull Intent intent) {
        Integer РезультатУдаленияВыбраногоМатериала = 0;
        try {
            switch (intent.getAction().trim()) {
                // TODO: 03.11.2022  new создание МАТЕРИАЛОВ  --УДАЛЕНИЕ
                case "УдалениеВыбранныеМатериалыДетализации":
                    РезультатУдаленияВыбраногоМатериала=       new    SubClassDeleteSelectAssiMaterial().МетодУдалениеВыбраногоМатериала(context,intent);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return РезультатУдаленияВыбраногоМатериала;
    }

    // TODO: 20.10.2022  метод получение СПРАВОЧНИКОВ ДЛЯ СОЗДАНИЯ НОВОГО МАТЕРИАЛА
    public Long МетодCлужбыСозданиеНовогоМатериала(
            @NonNull Context context, @NonNull Intent intent) {
        Long ХэшиАрайЛистДляСпиноровФинал =0l;
        try {
            switch (intent.getAction()) {
                case "СамоСозданиеНовогоМатериала":
                    ХэшиАрайЛистДляСпиноровФинал= new    SubClassGetDataAdmissionMaterial_ПриСозданииНовогоМатериала().
                            МетодСозданиеНовогоМатериалов(context,intent);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;
                default:
                    break;
            }
            Log.w(this.getClass().getName(), "   ХэшиАрайЛистДляСпиноровФинал " + ХэшиАрайЛистДляСпиноровФинал);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return ХэшиАрайЛистДляСпиноровФинал;
    }





    // TODO: 28.09.2022  класс заполнения из прошлых месяцев

    class SibClassApplyFromBackPeriodof_ЗаполененияТабеляИзПрошлогоМесяца {
        private Integer МетодВставкивТаблицуДата_Табель(@NonNull Context context,
                                                        @NonNull Long ГенерироватьUUIDРОДИТЕЛЬСКАЯ,
                                                        @NonNull SQLiteCursor Курсор_ВытаскиваемПоследнийМесяцТабеля) {
            Integer   getответОперцииВставки=0;
            try {
                String НазваниеОбрабоатываемойТаблицы = "data_tabels";
                ContentValues contentValuesДляДатаТабель = new ContentValues();
                int ИндексСтолбикаДляЗаполненияФИО = Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("fio");
                contentValuesДляДатаТабель.put("fio", Курсор_ВытаскиваемПоследнийМесяцТабеля.getLong(ИндексСтолбикаДляЗаполненияФИО));
                int ИндексFIOuser_update = Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("user_update");
                contentValuesДляДатаТабель.put("user_update", Курсор_ВытаскиваемПоследнийМесяцТабеля.getInt(ИндексFIOuser_update));
                String СгенерированованныйДатаДляДаннойОперации = new GetMainDateForApp(getApplicationContext()).getMainDateForApp();
                contentValuesДляДатаТабель.put("date_update", СгенерированованныйДатаДляДаннойОперации);
                contentValuesДляДатаТабель.put("uuid_tabel", ГенерироватьUUIDРОДИТЕЛЬСКАЯ);
                contentValuesДляДатаТабель.put("status_send", " ");
                contentValuesДляДатаТабель.put("status_carried_out", "False");


                // TODO: 22.09.2022 ПОЛУЧЕМ ПОВЫШЕНУЮ ВЕРИСЮ ДАННЫХ
                Long РезультатУвеличиваемВерсияДатаТАбель =
                        new VersionCurentTable(context).upVersionCurentTable(НазваниеОбрабоатываемойТаблицы);
                Log.d(this.getClass().getName(), " РезультатУвеличиваемВерсияДатаТАбель  " + РезультатУвеличиваемВерсияДатаТАбель);
                // TODO: 18.11.2022
                contentValuesДляДатаТабель.put("current_table", РезультатУвеличиваемВерсияДатаТАбель);
                // TODO: 22.09.2022
                Long ГенерироватьUUIDДляТаблицыДАТАТАБЕЛЬ = (Long) new GreatUuidGeneration(getApplicationContext()).greatUuidGeneration();
                contentValuesДляДатаТабель.put("uuid", ГенерироватьUUIDДляТаблицыДАТАТАБЕЛЬ);

                // TODO: 14.05.2025
                ModuleInserting moduleInserting=new ModuleInserting(context);
                // TODO: 14.05.2025
                 getответОперцииВставки =    moduleInserting.getModuleInsert(НазваниеОбрабоатываемойТаблицы,contentValuesДляДатаТабель);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getответОперцииВставки "+getответОперцииВставки );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return getответОперцииВставки;
        }






        // TODO: 25.11.2022  визуальнае отображение добавление сотрудников из проглого месяца
        private void МетодОтображениеОперации(@NonNull Context context, String Статус, Integer Значение) {
            try {
                Intent intentЗапускаемИзСлужбыДляЛистТАбеля = new Intent();
                intentЗапускаемИзСлужбыДляЛистТАбеля.setAction("LocalBroadcastManagerListBackListtabel");
                Bundle bundleЗапускемBackДанные = new Bundle();
                bundleЗапускемBackДанные.putInt("Значения", Значение);
                bundleЗапускемBackДанные.putString("Статус", Статус);///"В процесс"
                bundleЗапускемBackДанные.putString("ДепартаментТабеляПослеПодбораBACK", ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);///"В процесс"
                intentЗапускаемИзСлужбыДляЛистТАбеля.putExtras(bundleЗапускемBackДанные);
                Log.w(this.getClass().getName(), "   bundleЗапускемBackДанные  " + bundleЗапускемBackДанные);
                LocalBroadcastManager localBroadcastManagerИзСлужбыServiceForAllTabel = LocalBroadcastManager.getInstance(context);
                localBroadcastManagerИзСлужбыServiceForAllTabel.sendBroadcast(intentЗапускаемИзСлужбыДляЛистТАбеля);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
        }


    }




    // TODO получение данных для получение материалов
    private class SubClassGetDataAdmissionMaterial_ПолучаемЦФО {
        private    Cursor курсор;
        private    Context context;
        public SubClassGetDataAdmissionMaterial_ПолучаемЦФО() {
        }
        @WorkerThread
        Cursor МетодПолучениеДанныхAsyncQueryhandler(@NonNull Intent intent,@NonNull Context context){
            try{
                String Таблица=intent.getStringExtra("Таблица");
                this.context=context;
                Log.w(this.getClass().getName(), "   Таблица  " +Таблица);
                Bundle data=intent.getExtras();
                Integer  ПубличныйIDДляФрагмента=data.getInt("ПубличныйIDДляФрагмента",0);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица.trim() + "");
                ContentResolver resolver = context.getContentResolver();
                // TODO Курсор
                data.putString("selection","user_update=? AND status_send!=?  AND name_cfo IS NOT NULL ");
                data.putStringArray("selectionArgs",new String[]{String.valueOf(ПубличныйIDДляФрагмента),"Удаленная"});
                data.putString("groupby","name_cfo");
                // курсор = resolver.query(uri,new String[]{"*"},"user_update=? AND status_send!=?",new String[]{String.valueOf(ПубличныйIDДляФрагмента),"Удаленная"},"name_cfo");// TODO: 13.10.2022 ,"Удаленная"
                курсор = resolver.query(uri,new String[]{"*"},data,null);// TODO: 13.10.2022 ,"Удаленная"
                Log.d(this.getClass().getName(), "курсор   " + курсор);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return  курсор;
        }
    }
    private class SubClassGetDataAdmissionMaterial_Групировка_Детализация {
        private    Cursor курсор;
        private    Context context;
        private Intent intent;
        Cursor МетодПолучениеДанныхAsyncQueryhandler(@NonNull Intent intent, @NonNull Context context) {
            try{
                this.context=context;
                this.intent=intent;
                String Таблица=intent.getStringExtra("Таблица");
                Bundle data=intent.getExtras();
                Integer  ПубличныйIDДляФрагмента=data.getInt("ПубличныйIDДляФрагмента",0);
                Integer  ТекущаяЦФО=data.getInt("ТекущаяЦФО",0);
                Integer  НомерВыбраногоМатериала=data.getInt("НомерВыбраногоМатериала",0);
                Log.w(this.getClass().getName(), "   Таблица  " +Таблица);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица.trim() + "");
                ContentResolver resolver = context.getContentResolver();
                data.putString("selection"," cfo=? AND nomen_vesov=? AND status_send!=?");
                data.putStringArray("selectionArgs",new String[]{String.valueOf(ТекущаяЦФО), String.valueOf(НомерВыбраногоМатериала),"Удаленная"});
/*        курсор     = resolver.query(uri,new String[]{"*"},"user_update=? AND cfo=? AND nomen_vesov=? AND status_send!=?",
                new String[]{String.valueOf(ПубличныйIDДляФрагмента), String.valueOf(ТекущаяЦФО), String.valueOf(ТекущаяНомерМатериала),"Удаленная"},"");*/
                курсор = resolver.query(uri,new String[]{"*"},data,null);// TODO: 13.10.2022 ,"Удаленная"
                Log.d(this.getClass().getName(), "курсор   " + курсор);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return    курсор;
        }
    }

    // TODO: 26.12.2022  новая два класса Автомобиле и Контрагенты
    private class SubClassGetDataAdmissionMaterial_Автомобили {
        private    Cursor курсор;
        private    Context context;
        private Intent intent;
        Cursor МетодПолучениеДанныхAsyncQueryhandler(@NonNull Intent intent, @NonNull Context context) {
            try{
                this.context=context;
                this.intent=intent;
                String Таблица=intent.getStringExtra("Таблица");
                Bundle data=intent.getExtras();
                String  ТекущийПосикАвтомобиль=data.getString("ФильтрДляПоискаАвтомобили","");
                Log.w(this.getClass().getName(), "   Таблица  " +Таблица);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица.trim() + "");
                ContentResolver resolver = context.getContentResolver();
                data.putString("selection"," fullname  LIKE  ? AND fullname!=? ");
                data.putStringArray("selectionArgs",new String[]{"%"+ТекущийПосикАвтомобиль+"%",""});

                курсор = resolver.query(uri,new String[]{"*"},data,null);// TODO: 13.10.2022 ,"Удаленная"
                Log.d(this.getClass().getName(), "курсор   " + курсор);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return    курсор;
        }
    }
    private class SubClassGetDataAdmissionMaterial_Контрагенты {
        private    Cursor курсор;
        private    Context context;
        private Intent intent;
        Cursor МетодПолучениеДанныхAsyncQueryhandler(@NonNull Intent intent, @NonNull Context context) {
            try{
                this.context=context;
                this.intent=intent;
                String Таблица=intent.getStringExtra("Таблица");
                Bundle data=intent.getExtras();
                String  ТекущийПосикКонтрагента=data.getString("ФильтрДляПоискаКонтрагенты","");
                Log.w(this.getClass().getName(), "   Таблица  " +Таблица);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица.trim() + "");
                ContentResolver resolver = context.getContentResolver();
                // TODO: 26.12.2022 посик значения в контрагенте для фильтра
                if (ТекущийПосикКонтрагента.length()>0) {
                    if(Character.isDigit(ТекущийПосикКонтрагента.charAt(0)) ){
                        data.putString("selection"," inn  LIKE  ? AND fullname!=?  ");
                        data.putStringArray("selectionArgs",new String[]{"%"+  ТекущийПосикКонтрагента+"%",""});
                    }else{
                        data.putString("selection"," name   LIKE  ? AND fullname!=?  ");
                        data.putStringArray("selectionArgs",new String[]{"%"+  ТекущийПосикКонтрагента+"%",""});
                    }
                }else{
                    data.putString("selection"," name   LIKE  ?  AND fullname!=?  ");
                    data.putStringArray("selectionArgs",new String[]{"%"+  ТекущийПосикКонтрагента+"%",""});
                }


                курсор = resolver.query(uri,new String[]{"*"},data,null);// TODO: 13.10.2022 ,"Удаленная"
                Log.d(this.getClass().getName(), "курсор   " + курсор);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return    курсор;
        }
    }





    class SubClassGetDataAdmissionMaterial_ПолучаемМатериалы {
        private    Cursor курсор;
        private    Context context;
        private Intent intent;
        Cursor МетодПолучениеДанныхAsyncQueryhandler(@NonNull Intent intent, @NonNull Context context) {
            try{
                this.context=context;
                this.intent=intent;
                String Таблица=intent.getStringExtra("Таблица");
                Bundle data=intent.getExtras();
                data.putString("Таблица",Таблица);
                Log.w(this.getClass().getName(), "   Таблица  " +Таблица);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdataadminissionmaterial/" + Таблица.trim() + "");
                ContentResolver resolver = context.getContentResolver();
                /*курсор     = resolver.query(uri,new String[]{"*"},"user_update=? AND cfo=? AND status_send!=?",
                        new String[]{String.valueOf(ПубличныйIDДляФрагмента), String.valueOf(ТекущаяЦФО),"Удаленная"},"");*/
                курсор     = resolver.query(uri,new String[]{"*"},data,null);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return    курсор;
        }
    }

    // TODO: 19.10.2022  ПОЛУЧЕНИЕ ДАННЫХ ДЛЯ НОВОГО МАТЕРИАЛА
    private class SubClassGetDataAdmissionMaterial_ПриСозданииНовогоМатериала {
        private    Cursor курсор;
        private    Context context;
        private  Intent intent;
        public SubClassGetDataAdmissionMaterial_ПриСозданииНовогоМатериала() {
        }
        @WorkerThread
        Cursor МетодПолучениеДанныхAsyncQueryhandler(@NonNull Intent intent,@NonNull Context context){
            try{
                String Таблица=intent.getStringExtra("Таблица");
                this.context=context;
                this.intent=intent;
                Log.w(this.getClass().getName(), "   Таблица  " +Таблица);
                Bundle data=intent.getExtras();
                Integer  ПубличныйIDДляФрагмента=data.getInt("ПубличныйIDДляФрагмента",0);
                String  ФильтрДляПоиска=data.getString("ФильтрДляПоиска","");
                Long  ФильтрДляПоискаДляОдногоМатериалаВесовые=data.getLong("ФильтрДляПоискаДляОдногоМатериалаВесовые",0);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица.trim() + "");
                ContentResolver resolver = context.getContentResolver();
                switch (Таблица.trim()){
                    case "cfo":
                        data.putString("selection"," closed!=?");
                        data.putStringArray("selectionArgs",new String[]{"True"});
                        data.putString("sortOrder","name");
                        break;
                    case "type_materials":
                        data.putString("sortOrder","name");
                        break;
                    case "nomen_vesov":
                        data.putString("selection"," type_material =? ");//type_material =?
                        data.putStringArray("selectionArgs",new String[]{String.valueOf(ФильтрДляПоискаДляОдногоМатериалаВесовые)});
                        data.putString("sortOrder","name");
                        break;
                }
                // TODO: 16.12.2022 ПОЛУЧАЕМ ДАННЫЕ
                курсор = resolver.query(uri,new String[]{"*"},data,null);// TODO: 13.10.2022 ,"Удаленная"
                Log.d(this.getClass().getName(), "курсор   " + курсор);
                курсор = resolver.query(uri,new String[]{"*"},data,null);// TODO: 13.10.2022 ,"Удаленная"
                Log.d(context.getClass().getName(), "\n"
                        + "   .... bremy: " + new Date()+"\n+" +
                        " Класс в процессе... " +  this.getClass().getName()+"\n"+
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " intent.getAction() " +intent.getAction()+"курсор   " + курсор);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return  курсор;
        }


        // TODO: 21.10.2022 Само Создание Нового Материала
        public  Long МетодСозданиеНовогоМатериалов(@NonNull Context context,@NonNull Intent intent){
            AsyncTaskLoader<Integer> asyncTaskLoader=null;
            Integer РезультатСозданиеНовгоМатериала=0;
            final Long[] ГенерироватьUUIDДляНовойЗадачи = {0l};
            try{
                asyncTaskLoader=new AsyncTaskLoader<Integer>(context) {
                    @Nullable
                    @Override
                    public Integer loadInBackground() {
                        Integer ХэшРезультатСозданиеНовогоМатериала=0;
                        Bundle data=intent.getExtras();
                        Log.d(context.getClass().getName(), " НазваниеЦФО "+data);
                        String ответОперцииВставки = null;
                        String НазваниеОбрабоатываемойТаблицы = "get_materials_data";
                        // TODO: 21.10.2022 заполняем ДАННЫМИ ДЛЯ ТАБЛИЦЫ НОВОГО МАТЕРИАЛА
                        ContentValues contentValuesСозданиеНовогоМатериала = new ContentValues();
                        contentValuesСозданиеНовогоМатериала.put("cfo", data.getInt("cfo"));
                        contentValuesСозданиеНовогоМатериала.put("type_material", data.getInt("type_material"));
                        contentValuesСозданиеНовогоМатериала.put("nomen_vesov", data.getInt("nomen_vesov"));
                        contentValuesСозданиеНовогоМатериала.put("count", data.getLong("count"));
                        contentValuesСозданиеНовогоМатериала.put("ttn", data.getString("ttn"));
                        contentValuesСозданиеНовогоМатериала.put("datattn", data.getString("datattn"));
                        Integer  ПубличныйIDДляФрагмента =new GetttingPublicID().getttingPublicID(getContext());

                        contentValuesСозданиеНовогоМатериала.put("user_update", ПубличныйIDДляФрагмента);
                        String СгенерированованныйДатаДляДаннойОперации = new GetMainDateForApp(getApplicationContext()).
                                getMainDateForApp();
                        contentValuesСозданиеНовогоМатериала.put("date_update", СгенерированованныйДатаДляДаннойОперации);
                        //    contentValuesСозданиеНовогоМатериала.putNull("id");
                        contentValuesСозданиеНовогоМатериала.put("status_send"," ");

                        // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                        Long РезультатУвеличиваемВерсияПолучениеНовогоМатериала =
                                new VersionCurentTable(context).upVersionCurentTable(НазваниеОбрабоатываемойТаблицы );
                        Log.d(this.getClass().getName(), " РезультатУвеличиваемВерсияПолучениеНовогоМатериала  " + РезультатУвеличиваемВерсияПолучениеНовогоМатериала);

                        contentValuesСозданиеНовогоМатериала.put("current_table", РезультатУвеличиваемВерсияПолучениеНовогоМатериала);
                        // TODO: 22.09.2022
                        ГенерироватьUUIDДляНовойЗадачи[0] = (Long) new GreatUuidGeneration(getApplicationContext()).greatUuidGeneration();
                        contentValuesСозданиеНовогоМатериала.put("uuid", ГенерироватьUUIDДляНовойЗадачи[0]);
                        // TODO: 27.12.2022  для новых двух полей Автомобили и Котрагенеты
                        contentValuesСозданиеНовогоМатериала.put("tracks", data.getInt("tracks"));
                        contentValuesСозданиеНовогоМатериала.put("companys", data.getInt("companys"));

                        // TODO: 21.10.2022 САМА ВСТАВКА
                        Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + НазваниеОбрабоатываемойТаблицы + "");
                        //  Uri uri = Uri.parse("content://MyContentProviderDatabase/" +НазваниеОбрабоатываемойТаблицы + "");
                        ContentResolver resolver = context.getContentResolver();
                        // TODO: 22.09.2022 Само выполенение
                        Uri insertData = resolver.insert(uri, contentValuesСозданиеНовогоМатериала);
                        ответОперцииВставки = Optional.ofNullable(insertData).map(Emmeter -> Emmeter.toString().replace("content://", "")).get();
                        Log.d(this.getClass().getName(), "insertData   " + insertData + "  ответОперцииВставки " + ответОперцииВставки);
                        ХэшРезультатСозданиеНовогоМатериала=Integer.parseInt(ответОперцииВставки);
                        Log.d(this.getClass().getName(), " ХэшРезультатСозданиеНовогоМатериала " +ХэшРезультатСозданиеНовогоМатериала);

                        return    ХэшРезультатСозданиеНовогоМатериала;
                    }
                    @Override
                    public void commitContentChanged() {
                        super.commitContentChanged();
                        Log.d(this.getClass().getName(), " РезультатСозданиеНовгоМатериала " );
                    }
                };
                asyncTaskLoader.startLoading();
                РезультатСозданиеНовгоМатериала=     asyncTaskLoader.loadInBackground().intValue();
                // TODO: 31.07.2023
                if(РезультатСозданиеНовгоМатериала<1){
                    ГенерироватьUUIDДляНовойЗадачи[0]=0l;
                }

                Log.d(this.getClass().getName(), " РезультатСозданиеНовгоМатериала " + РезультатСозданиеНовгоМатериала   +"   ГенерироватьUUIDДляНовойЗадачи[0] " + ГенерироватьUUIDДляНовойЗадачи[0]);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return ГенерироватьUUIDДляНовойЗадачи[0]  ;
        }

    }

    // TODO: 31.07.2023  создание втограйий
    public  Long МетодВставкаНовойИлиВыбранойФотографииImageUpAndCreate(
            @NonNull CopyOnWriteArrayList<LinkedHashMap<String, Bitmap>> copyOnWriteArrayListSuccessAddImages
            ,@NonNull Long Parent_Uuid){
        ArrayList<Long> arrayListРезультатНовойImageandUp=new ArrayList<>();
        try{


            Flowable.fromIterable(copyOnWriteArrayListSuccessAddImages)
                    .onBackpressureBuffer()
                    .subscribeOn(Schedulers.single())
                    .filter(image->!image.values().isEmpty())
                    .doOnNext(new Consumer<LinkedHashMap<String, Bitmap>>() {
                        @Override
                        public void accept(LinkedHashMap<String, Bitmap> stringBitmapLinkedHashMap) throws Throwable {
                            try{

                                Long ImageРАзмерФотографии =
                                        stringBitmapLinkedHashMap .values().stream()
                                                .filter(s -> s.getRowBytes()>0).collect(Collectors.counting());

                                if (ImageРАзмерФотографии>0) {
                                    // TODO: 04.08.2023 начинаем ставляем ФОТОГРАФИЮ
                                    String НазваниеОбрабоатываемойТаблицы = "materials_databinary";

                                    // TODO: 21.10.2022 Записываем Новые ФОтографии Image CAmera
                                    ContentValues contentValuesСозданиеНовогоМатериала = new ContentValues();

                                    Integer  ПубличныйIDДляФрагмента = new GetttingPublicID().getttingPublicID(context);
                                    contentValuesСозданиеНовогоМатериала.put("user_update", ПубличныйIDДляФрагмента);

                                    String СгенерированованныйДатаДляДаннойОперации = new GetMainDateForApp(getApplicationContext()).getMainDateForApp();
                                    contentValuesСозданиеНовогоМатериала.put("date_update", СгенерированованныйДатаДляДаннойОперации);


                                    // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                                    Long РезультатУвеличиваемВерсияПолучениеНовогоМатериала =
                                            new VersionCurentTable(context)
                                                    .upVersionCurentTable(НазваниеОбрабоатываемойТаблицы );
                                    Log.d(this.getClass().getName(), " РезультатУвеличиваемВерсияПолучениеНовогоМатериала  " + РезультатУвеличиваемВерсияПолучениеНовогоМатериала);

                                    contentValuesСозданиеНовогоМатериала.put("current_table", РезультатУвеличиваемВерсияПолучениеНовогоМатериала);



                                    // TODO: 22.09.2022  UUID Новый Для Новой Записи
                                    //    Long  UUIDNewImage =    integerBitmapLinkedHashMap.value().keySet().stream().findFirst().get();
                                    Long UUIDForUpImage = (Long)
                                            new GreatUuidGeneration(getApplicationContext()).greatUuidGeneration();

                                    contentValuesСозданиеНовогоМатериала.put("uuid", UUIDForUpImage);
                                    // TODO: 27.12.2022  для новых двух полей Автомобили и Котрагенеты
                                    contentValuesСозданиеНовогоМатериала.put("parent_uuid", Parent_Uuid);


                             String НазваниеФотографии=       stringBitmapLinkedHashMap.keySet().stream().findFirst().get();
                                contentValuesСозданиеНовогоМатериала.put("files",НазваниеФотографии);


                                    // TODO: 07.08.2023 метод Декопресии и Вставки Фтографии Новой
                                    contentValuesСозданиеНовогоМатериала = методВставкиИДекопресииНовойФотографии(stringBitmapLinkedHashMap, contentValuesСозданиеНовогоМатериала);


                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " contentValuesСозданиеНовогоМатериала " +contentValuesСозданиеНовогоМатериала );




                                    // TODO: 21.10.2022 САМА ВСТАВКА
                                    Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + НазваниеОбрабоатываемойТаблицы + "");
                                    //  Uri uri = Uri.parse("content://MyContentProviderDatabase/" +НазваниеОбрабоатываемойТаблицы + "");
                                    ContentResolver resolver = context.getContentResolver();
                                    // TODO: 22.09.2022 Само выполенение
                                    Uri insertData = resolver.insert(uri, contentValuesСозданиеНовогоМатериала);
                                    String    ответОперцииВставкиНовойФотографии = Optional.ofNullable(insertData).map(Emmeter -> Emmeter.toString().replace("content://", "")).get();
                                    Log.d(this.getClass().getName(), "insertData   " + insertData + "  ответОперцииВставкиНовойФотографии " + ответОперцииВставкиНовойФотографии);


                                    arrayListРезультатНовойImageandUp.add(Long.parseLong(ответОперцииВставкиНовойФотографии));
                                    Log.d(this.getClass().getName(), " ответОперцииВставкиНовойФотографии " +ответОперцииВставкиНовойФотографии  +
                                            "   arrayListРезультатНовойImageandUp.size() "  +arrayListРезультатНовойImageandUp.size());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(context).recordnewerror(e.toString(),
                                        this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    })
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return true;
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " copyOnWriteArrayListSuccessAddImages " +copyOnWriteArrayListSuccessAddImages );
                        }
                    }).blockingSubscribe();


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
   return   arrayListРезультатНовойImageandUp.stream().mapToLong(v -> v).max().getAsLong();
    }




    private ContentValues методВставкиИДекопресииНовойФотографии(
           @NonNull LinkedHashMap<String,
            Bitmap> integerBitmapLinkedHashMap,
           @NonNull   ContentValues contentValuesСозданиеНовогоМатериала) {
        try{
        // TODO: 31.07.2023  вставка самой НОВОЙ ФОТОГРАФИИ
        Bitmap bitmapNewImage=    integerBitmapLinkedHashMap.values().stream().findFirst().get();

        ByteArrayOutputStream bytedataNewImage = new ByteArrayOutputStream(2048);///4096
      //  bitmapNewImage.compress(Bitmap.CompressFormat.JPEG, 100, bytedataNewImage);
        bitmapNewImage.compress(Bitmap.CompressFormat.JPEG, 3, bytedataNewImage);
        byte[] ByteFOrBlobImage = bytedataNewImage.toByteArray();

            // TODO: 07.08.2023 Находим РАзмер фотографии
            long РазмерФотографии = ByteFOrBlobImage.length;
            long fileSizeInMB = РазмерФотографии /   (1024 * 1024);
            // TODO: 07.08.2023  Записыаем Новую ФОтграфию
            contentValuesСозданиеНовогоМатериала.put("image",ByteFOrBlobImage );
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " fileSizeInMB " +fileSizeInMB   +  " ByteFOrBlobImage " +ByteFOrBlobImage);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return contentValuesСозданиеНовогоМатериала;
    }
























    // TODO: 19.10.2022  ПОЛУЧЕНИЕ ДАННЫХ ДЛЯ НОВОГО МАТЕРИАЛА
    public class SubClassGetDataAdmissionMaterial_Данные_ДляНовогоПоиска {
        private Cursor курсорДляНовогоПосика;
        private Context context;
        private Intent intent;

        public SubClassGetDataAdmissionMaterial_Данные_ДляНовогоПоиска() {
        }

        @WorkerThread
        Cursor МетодПолучениеДанныхДля_FilterQueryProviderAsyncQueryhandler(@NonNull Intent intent, @NonNull Context context) {
            try {
                String Таблица = intent.getStringExtra("Таблица");
                this.context = context;
                this.intent = intent;
                Log.w(this.getClass().getName(), "   Таблица  " + Таблица);
                Bundle data = intent.getExtras();
                Integer ПубличныйIDДляФрагмента = data.getInt("ПубличныйIDДляФрагмента", 0);
                String ФильтрДляПоиска = data.getString("ФильтрДляПоиска", "");
                Integer ФильтрДляПоискаДляОдногоМатериалаВесовые = data.getInt("ЗначениеГруппыМатериаловДляПосика", 0);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица.trim() + "");
                ContentResolver resolver = context.getContentResolver();
                switch (Таблица.trim()) {
                    case "cfo":
                        if (ФильтрДляПоиска.length() > 0) {
                            data.putString("selection", " name LIKE ? AND closed!=?");
                            data.putStringArray("selectionArgs", new String[]{"%" + ФильтрДляПоиска.toUpperCase() + "%","True"});
                        }
                        break;
                    case "type_materials":
                        if (ФильтрДляПоиска.length() > 0) {
                            data.putString("selection", " name LIKE ? ");
                            data.putStringArray("selectionArgs", new String[]{"%" + ФильтрДляПоиска.toUpperCase() + "%"});
                        }
                        break;
                    case "nomen_vesov":
                        if (ФильтрДляПоиска.length() > 0 && ФильтрДляПоискаДляОдногоМатериалаВесовые>0) {
                            data.putString("selection", " name LIKE ? AND type_material  =?  ");
                            data.putStringArray("selectionArgs", new String[]{"%" + ФильтрДляПоиска+ "%", String.valueOf(ФильтрДляПоискаДляОдногоМатериалаВесовые)});
                            data.putString("sortOrder", "name");
                        }
                        break;
                    case "company":
                        if (ФильтрДляПоиска.length() > 0 ) {
                            if(Character.isDigit(ФильтрДляПоиска.charAt(0)) ){
                                data.putString("selection"," inn  LIKE  ? AND fullname!=?  ");
                                data.putStringArray("selectionArgs",new String[]{"%"+  ФильтрДляПоиска+"%",""});
                            }else{
                                data.putString("selection"," name   LIKE  ? AND fullname!=?  ");
                                data.putStringArray("selectionArgs",new String[]{"%"+  ФильтрДляПоиска+"%",""});
                            }
                        }
                        break;
                    case "track":
                        if (ФильтрДляПоиска.length() > 0 ) {
                            data.putString("selection"," fullname  LIKE  ? AND fullname!=? ");
                            data.putStringArray("selectionArgs",new String[]{"%"+ФильтрДляПоиска+"%",""});
                        }
                        break;
                    case "prof":
                        if (ФильтрДляПоиска.length() > 0 ) {
                            data.putString("selection"," name  LIKE  ?  ");
                            data.putStringArray("selectionArgs",new String[]{"%"+ФильтрДляПоиска+"%",""});
                        }
                        break;
                }
                // TODO: 16.12.2022 ПОЛУЧАЕМ ДАННЫЕ
                курсорДляНовогоПосика = resolver.query(uri, new String[]{"*"}, data, null);// TODO: 13.10.2022 ,"Удаленная"
                Log.d(this.getClass().getName(), "курсорДляНовогоПосика   " + курсорДляНовогоПосика);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return курсорДляНовогоПосика;
        }
        // TODO: 17.07.2023 Image

        @WorkerThread
        public Cursor МетодПолучениеДанныхForImage( @NonNull Context context,@NonNull Intent intent) {
            Cursor cursorGetImage = null;
            try {
                String Таблица = intent.getStringExtra("Таблица");
                this.context = context;
                this.intent = intent;
                Log.w(this.getClass().getName(), "   Таблица  " + Таблица);
                Bundle data = intent.getExtras();
                Long Paren_Image_UUID = data.getLong("Paren_Image_UUID", 0l);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица.trim() + "");
                ContentResolver resolver = context.getContentResolver();
                // TODO: 18.04.2023  Simple Adapter Кдик по Элементы
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Таблица " +Таблица +
                        " Paren_Image_UUID " +Paren_Image_UUID);


                switch (Таблица.trim()) {
                    case "materials_databinary":
                        data.putString("selection","  parent_uuid=? AND image IS NOT NULL");
                        data.putStringArray("projection",new String[]{"  _id  ,image,files  ,date_update   ,uuid  ,parent_uuid   ,user_update  , current_table " }  );
                        data.putStringArray("selectionArgs",new String[]{Paren_Image_UUID.toString()});
                }
                // TODO: 16.12.2022 ПОЛУЧАЕМ ДАННЫЕ
                cursorGetImage = resolver.query(uri, new String[]{"*"}, data, null);// TODO: 13.10.2022 ,"Удаленная"
                Log.d(this.getClass().getName(), "cursorGetImage   " + cursorGetImage);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return cursorGetImage;
        }
    }





    class SubClassDeleteSelectAssiMaterial {
        public SubClassDeleteSelectAssiMaterial() {
        }

        // TODO: 21.10.2022 Само Создание Нового Материала
        public  Integer МетодУдалениеВыбраногоМатериала(@NonNull Context context,@NonNull Intent intent){
            AsyncTaskLoader<Integer> asyncTaskLoader=null;
            Integer РезультатУдалениеНовгоМатериала=0;
            try{
                asyncTaskLoader=new AsyncTaskLoader<Integer>(context) {
                    @Nullable
                    @Override
                    public Integer loadInBackground() {
                        Integer РезультатУдалениеНовгоМатериала=0;
                        Bundle data=intent.getExtras();
                        Log.d(context.getClass().getName(), " НазваниеЦФО "+data);
                        String НазваниеОбрабоатываемойТаблицы = "get_materials_data";
                        // TODO: 21.10.2022 заполняем ДАННЫМИ ДЛЯ ТАБЛИЦЫ НОВОГО МАТЕРИАЛА
                        String        selection= data.getString("selection");
                        Long      selectionArgs= data.getLong("UUIDВыбраныйМатериал");
                        ContentValues contentValuesСменыСтатусаВыбраногоМатериала=new ContentValues();
                        contentValuesСменыСтатусаВыбраногоМатериала.put("status_send","Удаленная");

                        // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                        Long РезультатУвеличиваемВерсияПолучениеНовогоМатериала =
                                new VersionCurentTable(context).upVersionCurentTable(НазваниеОбрабоатываемойТаблицы );
                        Log.d(this.getClass().getName(), " РезультатУвеличиваемВерсияПолучениеНовогоМатериала  " + РезультатУвеличиваемВерсияПолучениеНовогоМатериала);

                        // TODO: 18.11.2022
                        contentValuesСменыСтатусаВыбраногоМатериала.put("current_table",РезультатУвеличиваемВерсияПолучениеНовогоМатериала);


                        String Дата  = new GetMainDateForApp(context).getMainDateForApp();
                        contentValuesСменыСтатусаВыбраногоМатериала.put("date_update", Дата);

                        // TODO: 21.10.2022 САМА ВСТАВКА
                        Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + НазваниеОбрабоатываемойТаблицы + "");
                        //  Uri uri = Uri.parse("content://MyContentProviderDatabase/" +НазваниеОбрабоатываемойТаблицы + "");
                        ContentResolver resolver = context.getContentResolver();
                        // TODO: 22.09.2022 Само выполенение
                        РезультатУдалениеНовгоМатериала = resolver.update(uri,contentValuesСменыСтатусаВыбраногоМатериала,selection,new String[]{selectionArgs.toString()});
                        РезультатУдалениеНовгоМатериала = Optional.ofNullable(РезультатУдалениеНовгоМатериала).map(Integer::new).orElse(0);
                        Log.d(this.getClass().getName(), "РезультатУдалениеНовгоМатериала   " + РезультатУдалениеНовгоМатериала);

                        Log.d(this.getClass().getName(), " РезультатУдалениеНовгоМатериала " +РезультатУдалениеНовгоМатериала);
                        return    РезультатУдалениеНовгоМатериала;
                    }
                    @Override
                    public void commitContentChanged() {
                        super.commitContentChanged();
                        Log.d(this.getClass().getName(), " РезультатУдалениеНовгоМатериала " );
                    }
                };
                asyncTaskLoader.startLoading();
                Log.d(this.getClass().getName(), " intent " + intent);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }finally {
                РезультатУдалениеНовгоМатериала=     asyncTaskLoader.loadInBackground().intValue();
                asyncTaskLoader.commitContentChanged();
                if (РезультатУдалениеНовгоМатериала>0) {

                    // TODO: 26.06.2022
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }
            }
            return РезультатУдалениеНовгоМатериала  ;
        }
    }





    // TODO: 19.07.2023  Класс  Для Создании и Сохранение новой Image  // TODO: 19.07.2023  Класс  Для Создании и Сохранение новой Image  // TODO: 19.07.2023  Класс  Для Создании и Сохранение новой Image
    public class SubClassCreateNewImageForMateril{
        // TODO: 19.07.2023  первый класс создание нового изображения
        public     class  SubClassImagesNews{
            // TODO: 19.07.2023 класс  создание нового Image
            public     void методCreateNewImages(){
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
                    Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                }
            }

        }//todo END SubClassImagesNews




    }//todo END SubClassCreateNewImage
}