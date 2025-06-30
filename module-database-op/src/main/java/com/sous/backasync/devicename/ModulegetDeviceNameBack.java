package com.sous.backasync.devicename;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.content.pm.PackageInfoCompat;

import com.sous.backasync.businesslogic.errors.RecordNewErroBack;
import com.sous.backasync.devicename.hilt.QualifiergetDeviceNameBack;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
@QualifiergetDeviceNameBack
@Named
public class ModulegetDeviceNameBack {

    Context context;
    public  @Inject ModulegetDeviceNameBack(@ApplicationContext  Context context ) {
        this.context = context;
    }

    // TODO: 18.02.2025



    public String getDeviceNameBack() {
        // TODO: 18.02.2025
        String getDeviceName = new String();
        try {
            getDeviceName = Build.MANUFACTURER
                    + " " + Build.MODEL + " " + Build.VERSION.RELEASE
                    + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            Log.d(this.getClass().getName(), " getDeviceName   " + getDeviceName);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getDeviceName;

    }


    public Integer getDeviceVersionBack( ) {
        // TODO: 18.02.2025
        Integer getDeviceVersion = 0;
        try {
            //getDeviceVersion = BuildConfig.VERSION_CODE;

            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            // getDeviceVersion    = Integer.valueOf(info.versionName);
            getDeviceVersion = (int) PackageInfoCompat.getLongVersionCode(info);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " getDeviceVersion   " + getDeviceVersion);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getDeviceVersion;

    }
}


