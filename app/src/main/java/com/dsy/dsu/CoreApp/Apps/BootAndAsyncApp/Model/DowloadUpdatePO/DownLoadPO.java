package com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.DowloadUpdatePO;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;


import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.DowloadUpdatePO.DeletingFiles.GetDeletingFilesJsonAndApk;
import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossContext;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.net.ssl.SSLSocketFactory;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DownLoadPO {
private  Activity activity;
    private Context context;

    private      AlertDialog alertDialogАнализВерсииПО =null;
    private     Integer СервернаяВерсияПОВнутри =null;

   private SSLSocketFactory getsslSocketFactory2;
    private SharedPreferences preferences;



    public DownLoadPO(@NonNull Activity activity,@NonNull  Context context,
                      @NonNull Integer СервернаяВерсияПОВнутри,
                      @NonNull SSLSocketFactory getsslSocketFactory2) {
        this.activity = activity;
        this.context = context;
        this.СервернаяВерсияПОВнутри = СервернаяВерсияПОВнутри;
        this.getsslSocketFactory2 = getsslSocketFactory2;
    }

    @SuppressLint("NewApi")
    @UiThread
public     void МетодСообщениеАнализПО( @NonNull LinkedHashMap<Integer,String> getHiltPortJboss) {

        try {
            preferences = context.getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            View promptsViewАнализПО=   методЗагрузкиСвоегоВидаДлAliadDialod(R.layout.activity_insertdata);
            ProgressBar PrograssBarЗагрузкиПО=promptsViewАнализПО.findViewById(R.id.prograssbardownload);
            MaterialButton КнопкаЗАгрузкиПО=promptsViewАнализПО.findViewById(R.id.bottom_alaliz_and_dwonloadupdatepo);
            // TODO: 19.09.2025
                PrograssBarЗагрузкиПО.setIndeterminate(false);
                PrograssBarЗагрузкиПО.setVisibility(View.GONE);
                PrograssBarЗагрузкиПО.forceLayout();
                PrograssBarЗагрузкиПО.refreshDrawableState();

            КнопкаЗАгрузкиПО.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 18.02.2023 Загрузка Нового файла APK
                    try {
                        // TODO: 11.04.2025 Загружаем файл
                        Maybe.fromCallable(()->{
                                    // TODO: 11.04.2025
                                    // TODO: 18.02.2023 удаление Файлов перед Обновление ПО или Анализм Версии ПО
                                    new GetDeletingFilesJsonAndApk(context).startingDeletingFileApk();



                                    // TODO: 29.07.2023 ЗАгрузки  ПО
                             final    File    getFileAPK =    МетодЗагрузкиAPK(getHiltPortJboss,  getsslSocketFactory2) ;

                                    Log.d(context.getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " getFileAPK " +getFileAPK);
                            return getFileAPK;
                                }).doOnSuccess(getFileAPK->{
                                    // TODO: 11.04.2025


                            КнопкаЗАгрузкиПО.getHandler().post( new Runnable() {
                                @Override
                                public void run() {
                                    if (getFileAPK!=null) {
                                        методПослеАнализаПО(getFileAPK);
                                    }

                                    Log.d(context.getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " getFileAPK " +getFileAPK);
                                }
                            });
                                    Log.d(context.getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " getFileAPK " +getFileAPK);
                                }).doOnError(e->{
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(context).recordnewerror(e.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }).doOnSubscribe(s->{
                                    // TODO: 11.04.2025
                            КнопкаЗАгрузкиПО.getHandler().post( new Runnable() {
                                        @Override
                                        public void run() {

                                            КнопкаЗАгрузкиПО.setEnabled(false);
                                            КнопкаЗАгрузкиПО.setBackgroundColor(Color.GRAY);
                                            КнопкаЗАгрузкиПО.requestLayout();
                                            КнопкаЗАгрузкиПО.refreshDrawableState();
                                            // TODO: 06.05.2023 делаем кнопки не активныйе
                                            Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                                            v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));

                                            PrograssBarЗагрузкиПО.setVisibility(View.VISIBLE);
                                            PrograssBarЗагрузкиПО.setIndeterminate(true);
                                            PrograssBarЗагрузкиПО.requestLayout();
                                            PrograssBarЗагрузкиПО.refreshDrawableState();
                                            Log.d(context.getClass().getName(), "\n"
                                                    + " время: " + new Date() + "\n+" +
                                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() );
                                        }
                                    });
                                    
                                    
                                    
                                }).subscribeOn(Schedulers.single())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe();
                        Log.d(context.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }

                }

                private void методПослеАнализаПО(@NonNull File getFileAPK) {
                    try{
                        if (getFileAPK != null && getFileAPK.length() > 0) {
                            alertDialogАнализВерсииПО.dismiss();
                            alertDialogАнализВерсииПО.cancel();

                            // TODO: 29.07.2023 ЗапускаемАнализ ВЕРСИИ ПО
                            МетодУстановкиНовойВерсииПО(СервернаяВерсияПОВнутри, getFileAPK);

                            Log.d(context.getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + " СервернаяВерсияПОВнутри " +СервернаяВерсияПОВнутри);

                        } else {

                            Log.w(context.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName() + " СервернаяВерсияПОВнутри  "
                                    + СервернаяВерсияПОВнутри + " POOLS");
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

            // TODO: 28.07.2023
            MaterialAlertDialogBuilder materialAlertDialogBuilderАнализПО
                    = (MaterialAlertDialogBuilder) new MaterialAlertDialogBuilder(activity)///       final AlertDialog alertDialog =new AlertDialog.Builder( MainActivity_Face_App_OLd.КонтекстFaceApp)
                    .setTitle("Обновление")
                    .setCancelable(false)
                    .setView(promptsViewАнализПО)
                    .setMessage("Обновление ПО"
                            + "\n" + "ООО Союз-Автодор"
                            + "\n"  +"версия. " + СервернаяВерсияПОВнутри)
                    .setIcon(R.drawable.icon_dsu1_update_success);
            // TODO: 29.07.2023 Запускем Анали ПО ДИалог
            if (alertDialogАнализВерсииПО==null) {
                alertDialogАнализВерсииПО = materialAlertDialogBuilderАнализПО.show();
            }else {
                if (!alertDialogАнализВерсииПО.isShowing()) {
                    alertDialogАнализВерсииПО = materialAlertDialogBuilderАнализПО.show();
                }
            }
            // TODO: 29.07.2023  переопределем расмер диалога
            методДизайнРазмераAliarDialog(alertDialogАнализВерсииПО);
            // TODO: 29.07.2023 запускам анализ версии ПО Диалог
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }
    void методДизайнРазмераAliarDialog(@NonNull AlertDialog alertDialog){

        try{
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(   alertDialog.getWindow().getAttributes());
            layoutParams.width =WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height =  WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.gravity = Gravity.CENTER;
            alertDialog.getWindow().setAttributes(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @UiThread
    private void МетодУстановкиНовойВерсииПО(@NonNull Integer СервернаяВерсияПОВнутри,
                                             @NonNull File alreadydownloadedUpdateFile){
        try {
            View  promptsViewУстановкаПО=   методЗагрузкиСвоегоВидаДлAliadDialod(R.layout.simple_download_newversii_po);
            MaterialButton bottom_install_and_dwonloadupdatepo=promptsViewУстановкаПО.findViewById(R.id.bottom_install_and_dwonloadupdatepo);
            promptsViewУстановкаПО.forceLayout();
            promptsViewУстановкаПО.refreshDrawableState();
            bottom_install_and_dwonloadupdatepo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        bottom_install_and_dwonloadupdatepo.setEnabled(false);
                        bottom_install_and_dwonloadupdatepo.setBackgroundColor(Color.GRAY);
                        Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));

                        Log.i(this.getClass().getName(),  "Установка Обновления .APK СЛУЖБА "
                                + Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                        
                        Uri UrlalreadydownloadedUpdateFile = FileProvider.getUriForFile(activity,
                                context.getPackageName() + ".provider", alreadydownloadedUpdateFile);
                     
                        Intent intentОбновлениеПО = new Intent(Intent.ACTION_INSTALL_PACKAGE);
                        intentОбновлениеПО.setDataAndType(UrlalreadydownloadedUpdateFile,
                                "application/vnd.android.package-archive");
                        intentОбновлениеПО.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                                Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
                                | Intent.FLAG_GRANT_PREFIX_URI_PERMISSION
                                | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intentОбновлениеПО.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
                        intentОбновлениеПО.putExtra(Intent.EXTRA_STREAM, UrlalreadydownloadedUpdateFile);
                        PackageManager МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт = context.getPackageManager();
                        
                        if (intentОбновлениеПО.resolveActivity(МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт) != null) {
                            // TODO: 26.12.2024 запускаем файл
                        activity.startActivity(intentОбновлениеПО);
                            activity.finishAndRemoveTask();

                        } else {

                            Log.i(this.getClass().getName(),  " "+ Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            });

            // TODO: 29.07.2023 Установка ПО  ДИАЛОГ
            MaterialAlertDialogBuilder materialAlertDialogBuilderУстановкаПО     = new MaterialAlertDialogBuilder(activity)///       final AlertDialog alertDialog =new AlertDialog.Builder( MainActivity_Face_App_OLd.КонтекстFaceApp)
                    .setTitle("Установщик")
                    .setCancelable(false)
                    .setView(promptsViewУстановкаПО)
                    .setMessage("Обновление ПО"
                            + "\n" + "ООО Союз-Автодор"
                            + "\n"  +"версия. " + СервернаяВерсияПОВнутри)
                    .setIcon(R.drawable.icon_dowload_new_packagepo);

            // TODO: 29.07.2023 Запускем Анали ПО ДИалог
            if (alertDialogАнализВерсииПО==null) {
                alertDialogАнализВерсииПО = materialAlertDialogBuilderУстановкаПО.show();
            }else {
                if (!alertDialogАнализВерсииПО.isShowing()) {
                    alertDialogАнализВерсииПО = materialAlertDialogBuilderУстановкаПО.show();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }











    private File МетодЗагрузкиAPK(@NonNull LinkedHashMap<Integer,String> getHiltPortJboss,@NonNull SSLSocketFactory getsslSocketFactory2)  {
        // TODO: 03.06.2025
        File   getFileAPK=null;
        try {
            Log.d(this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName()+"Загружаем Файл APK."+new Date());


            // TODO: 02.04.2024  Адресс и Порт Сервера Jboss
            String   ИмяСерверИзХранилица = getHiltPortJboss.values().stream().map(m->String.valueOf(m)).findFirst().get();
            Integer    ПортСерверИзХранилица = getHiltPortJboss.keySet().stream().mapToInt(m->m).findFirst().getAsInt();


            // TODO: 08.01.2022 Полученм JSON File  для анализа

             getFileAPK = new CoreBinessLogics(context).
                    МетодЗагрузкиОбновлениеПОсСервера(new JbossContext(context).getСсылкаНаРежимСервераОбновлениеПО(),
                           context, ИмяСерверИзХранилица ,ПортСерверИзХранилица,
                            "FileAPKUpdatePO","update_dsu1.apk",
                            "application/octet-stream",getsslSocketFactory2);

            Log.w(context.getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()
                    + Thread.currentThread().getName()+" getFileAPK" + getFileAPK);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getFileAPK;
    }




    // TODO: 03.10.2023
    View методЗагрузкиСвоегоВидаДлAliadDialod(@NonNull Integer Вид)  {
        View promptsView=null;
        try{
            LayoutInflater li = LayoutInflater.from(context);
            promptsView = li.inflate(Вид, null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  promptsView;
    }

}
