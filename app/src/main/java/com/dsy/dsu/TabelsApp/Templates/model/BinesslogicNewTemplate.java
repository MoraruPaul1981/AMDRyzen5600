package com.dsy.dsu.TabelsApp.Templates.model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;
import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.BusinessLogicForApps.GreatUuidGenerations.GreatUuidGeneration;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.sous.backasync.launch.ModuleInserting;

import java.util.Date;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BinesslogicNewTemplate  extends NewTemplateIntarface {
    private Context context;
    private Activity activity;

    public BinesslogicNewTemplate(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }
    ////todo добавления новый Шаблон
    @Override
    public Integer newTamplate(@NonNull View v, @NonNull String namenewtemplate) {
        Integer         getnewTamplate=0;
        try{
            activity.runOnUiThread(()->{
            ProgressDialog progressDialog= new ProgressDialog(activity);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Новый шаблон");
            progressDialog.setMessage("Добавление...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(0);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            });
            // TODO: 27.06.2025  Создание
            String НазваниеТаблицы = "templates";
            ContentValues contentValuesNewTamplate = new ContentValues();
            // TODO: 09.10.2024 Public ID
            Integer getPublicID = new GetttingPublicID().getttingPublicID(context);
            contentValuesNewTamplate.put("user_update",getPublicID);
            Long getUUIDGenerator = (Long) new GreatUuidGeneration(context).greatUuidGeneration();
            contentValuesNewTamplate.put("uuid",getUUIDGenerator);
            contentValuesNewTamplate.put("name_templates", namenewtemplate);

            String getNewDateCurrent = new GetMainDateForApp(context).getMainDateForApp();
            contentValuesNewTamplate.put("date_update", getNewDateCurrent);
            contentValuesNewTamplate.put("status_send", " ");

            // TODO: 14.05.2025 Создание Нового шаблона
            getnewTamplate =   operationsNewTemplate(contentValuesNewTamplate,НазваниеТаблицы);

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " getnewTamplate " +getnewTamplate);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  getnewTamplate;
    }












      /**
       * @param contentvaluesTemplateOperationsAdding
       * @param getNameTable
       * @return
       */
      @Override
      public Integer operationsNewTemplate(@NonNull ContentValues contentvaluesTemplateOperationsAdding, @NonNull String getNameTable) {
          // TODO: 26.06.2025
          Integer resultatOperation = 0;
          try {
              //todo гененируем если есть публичный id
              Single<Integer> singleOperation =       Single.fromCallable(()->{
                  ModuleInserting moduleQuety=new ModuleInserting(context);
               Integer   result   =moduleQuety.getModuleInsert(getNameTable,contentvaluesTemplateOperationsAdding);
                  // TODO: 02.05.2021
                  Log.d(context.getClass().getName(), "\n"
                          + " время: " + new Date()+"\n+" +
                          " Класс в процессе... " +  this.getClass().getName()+"\n"+
                          " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                          + " result "+result);
                return  result;
              });
              resultatOperation=   singleOperation.subscribeOn(Schedulers.single()).blockingGet();

              // TODO: 02.05.2021
              Log.d(context.getClass().getName(), "\n"
                      + " время: " + new Date()+"\n+" +
                      " Класс в процессе... " +  this.getClass().getName()+"\n"+
                      " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " resultatOperation "+resultatOperation);

          } catch (Exception e) {
              e.fillInStackTrace();
              Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                      " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
              new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                      Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
          }
          return resultatOperation;
      }


  } //TODO END CLASS
