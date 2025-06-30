package com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_fragment_errors;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;

import com.dsy.dsu.BusinessLogicForApps.AnalysisUserAuthenticated.GetAnalysisUserAuthenticated;
import com.dsy.dsu.BusinessLogicForApps.Class_Sendiing_Errors;
import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.LaunchActivityDiaologSettings;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.IOUtils;
import com.sous.backasync.launch.ModuleQuety;
import com.sous.backasync.launch.ModuleСalled;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class BinessLogicFragmentError {

    private Context context;

    private String fileName = "Sous-Avtodor-ERROR.txt";
    private   String patchFileName="SousAvtoFile";

    private  ModuleQuety moduleQuety;

    public BinessLogicFragmentError(@NonNull  Context context,  @NonNull  ModuleQuety moduleQuety) {
        this.moduleQuety = moduleQuety;
        this.context = context;
        // TODO: 17.01.2025


        // TODO: 17.04.2023
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

    }



    public void CallBackkFragemtSettings(FragmentManager fragmentManager, Context context ) {
        try{
            // TODO Запусукаем Фргамент НАстройки  dashbord
            Boolean UserAuthenticated=       new GetAnalysisUserAuthenticated(context).analysisUserAuthenticated(240);

            if (UserAuthenticated) {
                // TODO: 01.04.2024 Все в порядке ЗАпускам Саму Программу DashBord
                // TODO Запусукаем Фргамент НАстройки  dashbord
                new LaunchActivityDiaologSettings(fragmentManager,context).launchADashboardSettings();
                // TODO: 01.08.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                    // TODO: 01.08.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    protected void sendingErrorsbyMail(@NonNull StringBuffer БуерДляОшибок, @NonNull Activity activity, @NonNull SharedPreferences preferences) {
        try{
            Integer   ПубличноеID  = preferences.getInt("ПубличноеID",0);
            БуерДляОшибок.append("\n")
                    .append(" текущий пользователь : ").append("\n")
                    .append(ПубличноеID).append("\n")
                    .append(" время отправки: ").append("\n")
                    .append(new Date())
                    .append("\n");
            // TODO: 06.07.2023  оправлем ощибки на ПОЧТУ
            // TODO: 06.07.2023  оправлем ощибки на ПРЧТУ
            new Class_Sendiing_Errors(context)
                    .МетодПослываемОшибкиАдминистаторуПо(БуерДляОшибок,activity,ПубличноеID );

            Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }
      public void BackFragmentSettings(@NonNull MaterialButton imageViewBack, @NonNull  FragmentManager fragmentManager ) {
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    // TODO: 22.09.2023  exit error fragment
                    // TODO: 22.09.2023  exit error fragment
                   CallBackkFragemtSettings( fragmentManager,context );

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                            + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new RecordNewErros(context).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
        });

    }

    // TODO: 12.12.2023 метод Посылаешь данные на Почту
    public void metodSendErrorsToMail(@NonNull MaterialButton materialButtonОтправка,
                                      @NonNull StringBuffer БуерДляОшибок,
                                      @NonNull Activity activity,@NonNull SharedPreferences sharedPreferences) {
        materialButtonОтправка.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //TODO полывоаем ошибки на почту
                    Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v2.vibrate(50);
                    }
                    // TODO: 06.07.2023  оправлем ощибку на почту
                    sendingErrorsbyMail(БуерДляОшибок,activity,sharedPreferences);



                    // TODO: 07.04.2025 Удаление ОШИБОК
                    v.getHandler().postDelayed(()->{
                        // TODO: 17.05.2025
                              removingErrorsinFile();
                        // TODO: 17.05.2025
                                clearingTableError();
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
                    },1000);

                    Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                            Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
///////
                }
            }
        });



    }

    public void metodScreenDontErrorForUsers(@NonNull TextView    textViewAllError,@NonNull Animation animationv3) {
        try{
            textViewAllError.startAnimation(animationv3);
            textViewAllError.setText("Ошибок нет !!!");
            textViewAllError.requestLayout();
            textViewAllError.refreshDrawableState();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }










    public void metodScreenErrorForUsers(@NotNull TextView textViewAllError, @NonNull StringBuffer bufferError, @NonNull Animation animationv3) {
        try{
            textViewAllError.setText(bufferError.toString());

            textViewAllError.startAnimation(animationv3);
            textViewAllError.requestLayout();
            textViewAllError.refreshDrawableState();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    public void metodButtonEnables(@NonNull  MaterialButton materialButtonОтправка) {
        try{
            materialButtonОтправка.setClickable(true);
            materialButtonОтправка.setFocusable(true);
            materialButtonОтправка.requestLayout();
            materialButtonОтправка.refreshDrawableState();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    public void metodButtonDisable(@NonNull   MaterialButton materialButtonОтправка) {
        try{
            materialButtonОтправка.setClickable(false);
            materialButtonОтправка.setFocusable(false);
            materialButtonОтправка.setVisibility(View.INVISIBLE);
            materialButtonОтправка.requestLayout();
            materialButtonОтправка.refreshDrawableState();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }












    // TODO: 28.06.2023 Запись Ошибков
    public void removingErrorsinFile()   {
        try {
            // TODO: 14.05.2025
            ModuleСalled moduleСalled = new ModuleСalled(context);
            // TODO: 03.02.2025 update new back
            Bundle bundleCall=new Bundle();
            bundleCall.putString("getxecSQL", "DELETE FROM errordsu1");

            Bundle gatCall=  moduleСalled.getModuleСalled(Thread.currentThread().getStackTrace()[2].getMethodName(),"errordsu1",bundleCall );
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " gatCall "+gatCall );
        } catch (Exception e) {
            e.printStackTrace();
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    public void clearingTableError() {
        try    {
            File fileDelete = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+
                    File.separator+patchFileName +File.separator+ fileName);
            if ( fileDelete.exists()) {
                Uri address = FileProvider.getUriForFile(context, "com.dsy.dsu.provider", fileDelete);
                try( final InputStream getfileStream = context.getContentResolver().openInputStream(address);){

                    FileOutputStream outputStream = new FileOutputStream(fileDelete);
                    IOUtils.copy(getfileStream, outputStream);
                    fileDelete.deleteOnExit();
                    fileDelete.delete();
                    outputStream.close();
                }

            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );


        } catch (IOException e) {
            e.printStackTrace();
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 24.03.2025 end class
}

