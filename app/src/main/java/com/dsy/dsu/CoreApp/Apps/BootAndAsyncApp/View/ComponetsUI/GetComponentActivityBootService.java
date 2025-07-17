package com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.View.ComponetsUI;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;


import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.EventsBus.MessageEvensBusNetworkStatuses;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.Service.bl_service_boot.StartServiceBootAndAsync;


import com.dsy.dsu.BusinessLogicForApps.CallNavigarlaout.CallNavigarlaout;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_fragmenterrorlautch.LaunchFragmenrError;
import com.dsy.dsu.R;
import com.dsy.dsu.CoreApp.Apps.SettingsCoreApp.View.MainActivity_Settings;
import com.google.android.material.navigation.NavigationView;

import java.util.Date;

import javax.net.ssl.SSLSocketFactory;

// TODO: 19.01.2024  внутренаяя бизнес логика Активтив BootAndAsync
public class GetComponentActivityBootService {

    private LifecycleOwner lifecycleOwner  ;
    private SSLSocketFactory getsslSocketFactory2;
    private ProgressBar progressbarbootandasync;
    private Activity activity;
    private DrawerLayout drawerLayoutAsync;
    private NavigationView navigationViewAsyncApp;
    
    private  Context context;


    public static final int ALL_PERSSION_CODE=1;



    ImageView imageView_faceapp_settings;
    // TODO: 27.12.2024 первый КОНСТРУКТОР



    public GetComponentActivityBootService(@NonNull SSLSocketFactory getsslSocketFactory2,
                                           @NonNull Activity activity,
                                           @NonNull Context context,
                                           @NonNull LifecycleOwner lifecycleOwner) {

        this.getsslSocketFactory2 = getsslSocketFactory2;
        this.activity = activity;
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        // TODO: 24.01.2024
    }








    // TODO: 27.12.2024 ВТОРОЙ КОНТСРУКТОР
    public GetComponentActivityBootService(@NonNull SSLSocketFactory getsslSocketFactory2,
                                           @NonNull ProgressBar progressbarbootandasync,
                                           @NonNull Activity activity,
                                           @NonNull DrawerLayout drawerLayoutAsync,
                                           @NonNull  NavigationView navigationViewAsyncApp,
                                           @NonNull Context context,
                                           @NonNull LifecycleOwner lifecycleOwner,
                                           @NonNull ImageView imageView_faceapp_settings) {

        this.lifecycleOwner = lifecycleOwner;
        this.getsslSocketFactory2 = getsslSocketFactory2;
        this.progressbarbootandasync = progressbarbootandasync;
        this.activity = activity;
        this.drawerLayoutAsync = drawerLayoutAsync;
        this.navigationViewAsyncApp = navigationViewAsyncApp;
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        this.imageView_faceapp_settings = imageView_faceapp_settings;
        // TODO: 24.01.2024
    }










































    // TODO: 19.01.2024


    // TODO: 20.03.2025  статусы сети
    public void getEventBusNetworkStatuses(@NonNull MessageEvensBusNetworkStatuses messageEvensBusNetworkStatuses){
        try{
            Bundle bundleGetOtServicePrograssBar =(Bundle)         messageEvensBusNetworkStatuses.mess.getExtras();
            String Статус=   bundleGetOtServicePrograssBar.getString("Статус");
            Integer СервернаяВерсия=   bundleGetOtServicePrograssBar.getInt("СервернаяВерсия");

            if(Статус.contains(    "ServerJbosOff")){
                Toast.makeText(context,     "Сервер выкл.!!!"    , Toast.LENGTH_LONG).show();

            }

            if(Статус.contains(    "LastVersionUpdatePO")){
                Toast.makeText(context,     "Последняя версия ПО !!! ("+СервернаяВерсия+")"    , Toast.LENGTH_LONG).show();

            }

            // TODO: 26.12.2022  конец основгого кода
            Log.d(context.getClass().getName(), "\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Статус " +Статус);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }










    public void МетодБоковаяПанельОткрытьЗАкрыть() {
        try {
            if (drawerLayoutAsync.isDrawerOpen(Gravity.LEFT)) {
                drawerLayoutAsync.closeDrawer(Gravity.LEFT);
            }

            Log.d(context.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    private void МетодДополнительнойНастрокиАвтоЗапуска() {
        try {
            final Intent[] AUTO_START_INTENTS = {
                    new Intent().setComponent(new ComponentName("com.samsung.android.lool",
                            "com.samsung.android.sm.ui.battery.BatteryActivity")),
                    new Intent("miui.intent.action.OP_AUTO_START").addCategory(Intent.CATEGORY_DEFAULT),
                    new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
                    new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
                    new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
                    new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
                    new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
                    new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
                    new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
                    new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
                    new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
                    new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.entry.FunctionActivity")).setData(
                            Uri.parse("mobilemanager://function/entry/AutoStart"))
            };
            for (Intent intent : AUTO_START_INTENTS) {
                if (context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                    activity.startActivity(intent);
                    // break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



















    // TODO: 04.10.2023
    public void listerNavigationViewAsyncApp() {
        // TODO: 06.04.2022
        try {
            drawerLayoutAsync.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                @Override
                public void onDrawerOpened(View drawerView) {
                    try {


                    navigationViewAsyncApp.setVisibility(View.VISIBLE);

                    super.onDrawerOpened(drawerView);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                @Override
                public void onDrawerClosed(View drawerView) {
                    try {
                    Drawable drawable =context. getResources().getDrawable(R.drawable.icon_dsu1_async_asynprograssbar);///

                    navigationViewAsyncApp.setVisibility(View.GONE);

                    super.onDrawerClosed(drawerView);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }

            });
            // TODO: 25.03.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }



    }

// TODO: 10.01.2025

    // TODO: 04.10.2023
    public void  workerNavigationViewAsyncApp(@NonNull FragmentManager fragmentManager) {
        // TODO: 06.04.2022
        try {
            navigationViewAsyncApp.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    try{
                        switch (item.getItemId()) {
                            // TODO: 06.04.2022 Запускаем ОШибки
                            case R.id.one:
                                try {
                                    item.setChecked(true);

                                    // TODO: 04.04.2025
                                    // TODO Запусукаем  Фрагмент Главный Экран
                                  LaunchFragmenrError launchFragmenrError=new LaunchFragmenrError( fragmentManager,context);
                                    // TODO: 27.03.2024 в зависомсти кто вызвает
                                    launchFragmenrError.     launchErrorFragment() ;

                                    Log.d(context.getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                            " intent.getAction() "  );
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                                            + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(context).recordnewerror(e.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }

                                break;
                            // TODO: 06.04.2022 ЗапускаемОбновление ПО
                            case R.id.item_async_updatepo:
                                item.setChecked(true);
                                try {
// TODO: 10.07.2023  запуск обновление ПО
                                    new StartServiceBootAndAsync(context).startServiceBootAndAsync("lanchUpdatePO");

                                    Log.d(context.getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                            " intent.getAction() "  );
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(context)
                                            .recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                                break;

                            // TODO: 06.04.2022 ЗапускаемОбновление ПО
                            case R.id.item_settings:
                                item.setChecked(true);
                                try {
                                    Intent Интент_Меню = new Intent(activity, MainActivity_Settings.class);
                                    Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  );
                                    activity.startActivity(Интент_Меню);

                                    Log.d(context.getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                            " intent.getAction() "  );
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(context)
                                            .recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                                break;





                        }

                        if (drawerLayoutAsync.isDrawerOpen(Gravity.LEFT)) {
                            drawerLayoutAsync.closeDrawer(Gravity.LEFT);
                        }
                        // TODO: 25.03.2023
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }



    }

    // TODO: 04.10.2023
    public void  workerImageViewsettings() {
        // TODO: 06.04.2022
        try {
            imageView_faceapp_settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO: 25.03.2025
                    new CallNavigarlaout(drawerLayoutAsync,new Handler(),context).методНастройкиБоковойпанели();



                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

                }
            });
                        // TODO: 25.03.2023
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }



    }




    // TODO: 04.04.2022  END CLASS
}


