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
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
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
       AtomicReference<ProgressDialog>  atomicProgressDialog=new AtomicReference<>();
        try{

            // TODO: 08.07.2025
            ProgressDialog progressDialog=new ProgressDialog(activity);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Новый шаблон");
            progressDialog.setMessage("Добавление...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(0);
            progressDialog.setMax(1);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            atomicProgressDialog.getAndSet(progressDialog);

            //todo гененируем если есть публичный id
               Single.fromCallable(()->{
                // TODO: 08.07.2025  
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
              Integer   newTamplate =   operationsNewTemplate(contentValuesNewTamplate,НазваниеТаблицы);
                
                // TODO: 02.05.2021
                Log.d(context.getClass().getName(), "\n"
                        + " время: " + new Date()+"\n+" +
                        " Класс в процессе... " +  this.getClass().getName()+"\n"+
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " result "+newTamplate);
                return  newTamplate;
            }).subscribeOn(AndroidSchedulers.mainThread())
                       .doOnSuccess(new Consumer<Integer>() {
                @Override
                public void accept(Integer getnewTamplate) throws Throwable {
                    // TODO: 08.07.2025
                    if (getnewTamplate>0) {
                        ProgressDialog    progressDialog = atomicProgressDialog.get();
                        progressDialog.setIndeterminate(false);
                        progressDialog.setProgress(1);
                        progressDialog.setMessage("Успешно");

                    }else {
                        Snackbar snackbar=      Snackbar.make(v, "Нет создан шаблона !!!",Snackbar.LENGTH_LONG)
                                .setAction("Action",null);
                        snackbar.show();
                    }
                    // TODO: 08.07.2025  
                    progressDialog.dismiss();
                    progressDialog.cancel();
                    // TODO: 08.07.2025  
                    Log.d(context.getClass().getName(), "\n"
                            + " время: " + new Date()+"\n+" +
                            " Класс в процессе... " +  this.getClass().getName()+"\n"+
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + " getnewTamplate " +getnewTamplate);
                    
                }
            }).subscribeOn(Schedulers.single())
                       .subscribe();

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
                  ModuleInserting moduleQuety=new ModuleInserting(context);
                  resultatOperation   =moduleQuety.getModuleInsert(getNameTable,contentvaluesTemplateOperationsAdding);
                  // TODO: 02.05.2021
                  Log.d(context.getClass().getName(), "\n"
                          + " время: " + new Date()+"\n+" +
                          " Класс в процессе... " +  this.getClass().getName()+"\n"+
                          " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                          + " resresultatOperationult "+resultatOperation);
              // TODO: 02.05.2021
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
