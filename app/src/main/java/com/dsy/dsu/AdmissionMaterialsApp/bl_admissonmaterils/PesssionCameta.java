package com.dsy.dsu.AdmissionMaterialsApp.bl_admissonmaterils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

public class PesssionCameta {

 Activity activity;

    public PesssionCameta(Activity activity) {
        this.activity = activity;
    }
    private static final int REQUEST_EXTERNAL_STORAGECamera = 1;
    private static final int REQUEST_EXTERNAL_STORAGEFile = 2;

    private static String[] PERMISSIONS_STORAGECamera = {
            Manifest.permission.CAMERA,
    };
    private static String[] PERMISSIONS_STORAGEFile = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
      public   Boolean checkPesssionCameta() {
          Boolean ФлагЕслиРАзрешенияКамераИлиНет = false;
          try {
              int permissionStatus = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
              Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                      " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                      " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName() + " permissionStatus " + permissionStatus);


              if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                      // We don't have permission so prompt the user
                      ActivityCompat.requestPermissions(
                              activity,
                              PERMISSIONS_STORAGECamera,
                              permissionStatus
                      );
                  }

                  if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                      // TODO: 07.10.2023 \
                      ФлагЕслиРАзрешенияКамераИлиНет = true;
                      Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                              " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                              " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName() + " ЗаписьВSuccesLogin ");
                  }
              } catch(Exception e){
                  Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                          " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                  new RecordNewErros(activity).recordnewerror(e.toString(), this.getClass().getName(),
                          Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
              }
              return ФлагЕслиРАзрешенияКамераИлиНет;
          }



    public   Boolean checkPesssionFile() {
        Boolean ФлагЕслиРАзрешенияКамераИлиНет = false;
        try {
            int permissionStatus = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                    " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName() + " permissionStatus " + permissionStatus);


            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        activity,
                        PERMISSIONS_STORAGEFile,
                        permissionStatus
                );
            }

            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                // TODO: 07.10.2023 \
                ФлагЕслиРАзрешенияКамераИлиНет = true;
                Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                        " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName() + " ЗаписьВSuccesLogin ");
            }
        } catch(Exception e){
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(activity).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ФлагЕслиРАзрешенияКамераИлиНет;
    }


    //TODO end Class
}
