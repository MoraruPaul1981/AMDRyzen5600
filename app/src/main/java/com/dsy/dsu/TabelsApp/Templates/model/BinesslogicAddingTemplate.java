package com.dsy.dsu.TabelsApp.Templates.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
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
import com.google.common.util.concurrent.AtomicDouble;
import com.sous.backasync.launch.ModuleInserting;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BinesslogicAddingTemplate extends AddingTemplateIntarface {
  private Context context;
  private Activity activity;

  public BinesslogicAddingTemplate(Context context, Activity activity) {
      this.context = context;
      this.activity = activity;
  }


  // TODO: 26.06.2025

  @Override
  @SuppressLint("Range")
  ////todo добавления новый Шаблон
  public Long addingTemplateInTabel(@NonNull  View v, @NonNull Bundle bundleItemCompletetemplate ) {
      long         addingTemplateForTabel=0;
      AtomicReference<ProgressDialog> atomicReferenceAddingTemplateAddTabel=new AtomicReference();
      try{
          // TODO: 02.07.2025
                  ProgressDialog progressDialogAddTemplaesInTabel= new ProgressDialog(activity);
                  progressDialogAddTemplaesInTabel.setIndeterminate(true);
                  progressDialogAddTemplaesInTabel.setCancelable(false);
                  progressDialogAddTemplaesInTabel.setTitle("Из Шаблона");
                  progressDialogAddTemplaesInTabel.setMessage("Добавление в табель...");
                  progressDialogAddTemplaesInTabel.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                  progressDialogAddTemplaesInTabel.setMax(1);
                  progressDialogAddTemplaesInTabel.setProgress(0);
                  progressDialogAddTemplaesInTabel.setCanceledOnTouchOutside(false);
                  progressDialogAddTemplaesInTabel.show();
                 // TODO: 08.07.2025
                atomicReferenceAddingTemplateAddTabel.getAndSet(progressDialogAddTemplaesInTabel);

          // TODO: 02.07.2025  ADDING
          Single.fromCallable(()->{
                      // TODO: 08.07.2025
                      // TODO: 27.06.2025  Создание
                      String НазваниеТаблицы = "data_tabels";
                     CopyOnWriteArrayList<Integer>    addingInTabelTamplate=new CopyOnWriteArrayList<>();

                      Long getFindUUID=   bundleItemCompletetemplate.getLong("getFindUUID");
                      Cursor getCursorfioUuid=  new BinesslogiсGetCursorTemplate(context).getInseiderRowTemplate(getFindUUID);


                      // TODO: 11.07.2025 вставка из шаблона в табель
                      Flowable.range(0,getCursorfioUuid.getCount())
                              .onBackpressureBuffer()
                              .blockingForEach(new Consumer<Integer>() {
                          @Override
                          public void accept(Integer  getRow) throws Throwable {
                              //TODO move
                              // TODO: 02.05.2021
                              getCursorfioUuid.moveToPosition(getRow);
                              // TODO: 11.07.2025 get FIO
                            Long getFio_template = getCursorfioUuid.getLong(getCursorfioUuid.getColumnIndex("fio_uuid"));
                              // TODO: 10.07.2025  ROW
                              ContentValues contentValuesNewTamplate = new ContentValues();
                              // TODO: 09.10.2024 Public ID
                              Integer getPublicID = new GetttingPublicID().getttingPublicID(context);
                              contentValuesNewTamplate.put("user_update",getPublicID);
                              Long getUUIDGenerator = (Long) new GreatUuidGeneration(context).greatUuidGeneration();
                              contentValuesNewTamplate.put("uuid",getUUIDGenerator);
                              // TODO: 10.07.2025
                              String getNewDateCurrent = new GetMainDateForApp(context).getMainDateForApp();
                              contentValuesNewTamplate.put("date_update", getNewDateCurrent);
                              contentValuesNewTamplate.put("status_send", " ");
                              Long getMainParentUUID=   bundleItemCompletetemplate.getLong("MainParentUUID");
                              contentValuesNewTamplate.put("uuid_tabel", getMainParentUUID);//MainParentUUID

                              contentValuesNewTamplate.put("fio", getFio_template);

                              // TODO: 14.05.2025 Создание Нового шаблона
                            Integer inTabelTamplate=  operationsCompleteAfterTemplate(contentValuesNewTamplate,НазваниеТаблицы);
                              if (inTabelTamplate>0) {
                                  addingInTabelTamplate.add(inTabelTamplate );
                              }
                              Log.d(context.getClass().getName(), "\n"
                                      + " время: " + new Date()+"\n+" +
                                      " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                      " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                      " getRow " +getRow +
                                      " getFio_template " +getFio_template + " addingInTabelTamplate " +addingInTabelTamplate.size());
                          }
                      });



                      // TODO: 02.05.2021
                      Log.d(context.getClass().getName(), "\n"
                              + " время: " + new Date()+"\n+" +
                              " Класс в процессе... " +  this.getClass().getName()+"\n"+
                              " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                              " addingInTabelTamplate " +addingInTabelTamplate);
                      return addingInTabelTamplate;
                  }).subscribeOn(AndroidSchedulers.mainThread())
                  .doOnSuccess(new Consumer<CopyOnWriteArrayList<Integer>>() {
                      @Override
                      public void accept(CopyOnWriteArrayList<Integer> integersInTabelTamplate) throws Throwable {
                          // TODO: 11.07.2025
                          // TODO: 08.07.2025
                          ProgressDialog    progressDialogAddingInTabel = atomicReferenceAddingTemplateAddTabel.get();
                          Snackbar snackbar=null;
                          if (integersInTabelTamplate.size()>0) {
                              progressDialogAddingInTabel.setIndeterminate(false);
                              progressDialogAddingInTabel.setProgress(1);
                              progressDialogAddingInTabel.setMessage("Успешно");
                              snackbar=      Snackbar.make(v, "Успешно !!!",Snackbar.LENGTH_LONG)
                                      .setAction("Action",null);
                          }else {
                              // TODO: 11.07.2025
                              progressDialogAddingInTabel.setMessage("Нет");
                                snackbar=      Snackbar.make(v, "Нет добавления !!!",Snackbar.LENGTH_LONG)
                                      .setAction("Action",null);

                          }
                          // TODO: 08.07.2025
                          progressDialogAddingInTabel.dismiss();
                          progressDialogAddingInTabel.cancel();

                          snackbar.show();
                          // TODO: 08.07.2025
                          Log.d(context.getClass().getName(), "\n"
                                  + " время: " + new Date()+"\n+" +
                                  " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                  " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                  + " integersInTabelTamplate.size() " +integersInTabelTamplate.size());
                      }
                  }).subscribeOn(Schedulers.single())
                  .subscribe();
          Log.d(context.getClass().getName(), "\n"
                  + " время: " + new Date()+"\n+" +
                  " Класс в процессе... " +  this.getClass().getName()+"\n"+
                  " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  );

          Log.d(context.getClass().getName(), "\n"
                  + " время: " + new Date()+"\n+" +
                  " Класс в процессе... " +  this.getClass().getName()+"\n"+
                  " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " addingTemplateForTabel " +addingTemplateForTabel);
      } catch (Exception e) {
          e.printStackTrace();
          Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                  " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
          new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                  Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
      }
      return addingTemplateForTabel;
  }






    // TODO: 07.09.2021  Терминальный метод вСтавки данных шаблон создание  и вставка Готовго Шаблона
    @Override
    public Integer operationsCompleteAfterTemplate(@NonNull ContentValues contentvaluesTemplateOperationsAdding,
                                                   @NonNull String getNameTable) {
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








  //TODO END CLASS
}
