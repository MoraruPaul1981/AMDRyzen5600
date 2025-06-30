package com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.endingasynsdashboard;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.multidex.BuildConfig;

import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.bl_launchFragmentSettingsandDashbord.LaunchActivityDashboard;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.CoreApp.Apps.PasswordsCoreApp.view.MainActivityPasswords;


import java.util.Date;

public class LaunchMainAppAfterSyncing {

private Activity activity;

    public LaunchMainAppAfterSyncing(Activity activity) {
        this.activity = activity;
    }

    public void appAfterSyncingDashboard( @NonNull FragmentManager fragmentManager ){
        try {
          LaunchActivityDashboard launchActivityDashboard=new LaunchActivityDashboard( fragmentManager,activity);
            // TODO: 27.03.2024 в зависомсти кто вызвает
            launchActivityDashboard.     launchADashboardFragment();


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(activity.getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    public void appAfterSyncingActivityPassword( @NonNull Activity activity,@NonNull String stepAsync) {
        try{
            /////TODO ЗАПУСКАМ ОБНОЛВЕНИЕ ДАННЫХ С СЕРВЕРА ПЕРЕРД ЗАПУСКОМ ПРИЛОЖЕНИЯ ВСЕ ПРИЛОЖЕНИЯ ДСУ-1
            Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
            Intent Интент_ЗапускаетPasswords = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri);
            Интент_ЗапускаетPasswords.setClass(activity, MainActivityPasswords.class);
            Интент_ЗапускаетPasswords.setFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Bundle bundle=new Bundle();
            Интент_ЗапускаетPasswords.putExtras(bundle);
            Интент_ЗапускаетPasswords.  addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Интент_ЗапускаетPasswords.  addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Интент_ЗапускаетPasswords.setAction("MainActivityPasswords.class");
            Интент_ЗапускаетPasswords.putExtra("stepAsync",stepAsync);
            // TODO: 05.06.2025
            activity.startActivity(Интент_ЗапускаетPasswords);//tso
            // TODO: 11.04.2025 exit
            activity.finishAffinity();

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " localBinderAsync "+ "\n" );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(activity.getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 25.09.2024  end class
}
