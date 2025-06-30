package com.dsy.dsu.BusinessLogicForApps;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.dsy.dsu.BusinessLogicForApps.WorkerTables.GetWorkerAndSystemTables;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.sous.backasync.launch.ModuleDeleting;
import com.sous.backasync.launch.ModuleUpdating;


import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetClearDataUserAnCnahgeData {
    private Context context;

    // TODO: 24.02.2022
    public GetClearDataUserAnCnahgeData(Context context) {
        this.context = context;
        // TODO: 16.04.2025
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }


    // TODO: 24.04.2023 Метод Семны ДАнных Пользователя
    public Integer clearTableWorkerUsers(Context context,
                                         Activity activity,
                                         ProgressDialog progressDialogДляУдалениеТаблиц) {
        ArrayList<Integer>   РезультатWorkerСменыДанных=new ArrayList<>();
              try {
                  CopyOnWriteArrayList<String> copyOnWriteArrayListSystemtables=    new GetWorkerAndSystemTables().getSystemTablesALl(context);

                  Observable.fromIterable(copyOnWriteArrayListSystemtables)
                          .subscribeOn(Schedulers.single())
                          .observeOn(AndroidSchedulers.mainThread())
                          .filter(e->!e.equalsIgnoreCase("MODIFITATION_Client"))
                          .concatMap(i -> Observable.just(i).delay(50, TimeUnit.MILLISECONDS))
                          .doOnNext(new Consumer<String>() {
                              @Override
                              public void accept(String текущаяТаблицаДляУдваления) throws Throwable {

                                  // TODO: 29.05.2025  Очистка Рабочих Таблиц
                                  Integer РезультатУдалениеДанных=clearWorkerTablesDeletes( текущаяТаблицаДляУдваления.toString().trim(), context);

                                  РезультатWorkerСменыДанных.add(РезультатУдалениеДанных);

                                  Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                          " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                          " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                          + " РезультатWorkerСменыДанных " +РезультатWorkerСменыДанных);

                              }
                          }).doAfterNext(new Consumer<String>() {
                              @Override
                              public void accept(String string) throws Throwable {
                                  // TODO: 24.04.2023 Конец Цикла
                                  activity.runOnUiThread(()->{
                                      progressDialogДляУдалениеТаблиц.setMessage("Удаление таблиц ..."+РезультатWorkerСменыДанных.size() + " ("+copyOnWriteArrayListSystemtables.size()+")");
                                  });

                                  Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                          " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                          " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                              }
                          }).doOnComplete(new Action() {
                              @Override
                              public void run() throws Throwable {
                                  // TODO: 19.02.2025
                                  activity.runOnUiThread(()->{
                                      progressDialogДляУдалениеТаблиц.dismiss();
                                      progressDialogДляУдалениеТаблиц.cancel();
                                  });



                                  Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                          " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                          " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                              }
                          }).subscribe();

          } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new RecordNewErros(context).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
        return   РезультатWorkerСменыДанных.size();
    }










































    // TODO: 24.04.2023 Метод Семны ДАнных Пользователя
    public Integer clearTableSystemUsers(Context context) {
        ArrayList<Integer>   clearSystemTables=new ArrayList<>();
        try {
            CopyOnWriteArrayList<String> ИменаТаблицыSystem=    new GetWorkerAndSystemTables().getSystemTablesALl(context);

            Observable.fromIterable(ИменаТаблицыSystem)
                    .concatMap(i -> Observable.just(i).delay(50, TimeUnit.MILLISECONDS))
                    .filter(e->!e.equalsIgnoreCase("MODIFITATION_Client"))
                    .doOnNext(new Consumer<String>() {
                        @Override
                        public void accept(String текущаяСистемнаяТаблица) throws Throwable {
                            // TODO: 09.09.2021 DELETE УДАЛЕНИЕ ТАБЛИЦ ПЕРЕД УМЕНЫ ПОЛЬЗОВАТЕЛЯ
                            Integer clearSystemTablesUpdates= clearSystemTablesUpdates( текущаяСистемнаяТаблица, context);

                            Log.d(this.getClass().getName(), "clearSystemTablesUpdates " + clearSystemTablesUpdates+ " текущаяСистемнаяТаблица "
                                    +текущаяСистемнаяТаблица);
                            // TODO: 09.09.2021  действие второе добалянеим дату
                            if (clearSystemTablesUpdates>0) {
                                clearSystemTables.add(clearSystemTablesUpdates);
                            }

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " clearSystemTables " +clearSystemTables.size());
                        }
                    }).doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 01.09.2021 метод вызова
                            new RecordNewErros(context).recordnewerror(throwable.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }).blockingSubscribe();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
        return   clearSystemTables.size();
    }






    // TODO: 09.09.2021 delete data for tabels
    protected Integer clearWorkerTablesDeletes(String ИмяТаблицы, Context context) {
//
        Integer СменаДанных = 0;
        try {
            Log.d(this.getClass().getName(), "  ИмяТаблицы "+ИмяТаблицы+"" );
            // TODO: 14.05.2025
            ModuleDeleting moduleDeleting = new ModuleDeleting(context);
            // TODO: 03.02.2025 update new back
            СменаДанных=    moduleDeleting.getModuleSystemDelete(ИмяТаблицы );
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    ///
                    Log.d(context.getClass().getName(), " РезультатУдалениеОчисткиТаблиц" + "--" + СменаДанных));/////
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return СменаДанных;
    }




    // TODO: 09.09.2021 delete data for tabels
    protected Integer clearSystemTablesUpdates(String ТекущееИмяТаблицы, Context context) {
//
        Integer РезультатОбновлениеОчисткиТаблиц = 0;
        try {

            String getTableRoot= "MODIFITATION_Client";
            ModuleUpdating moduleUpdating = new ModuleUpdating(context);
            ContentValues contentValuesСменаДанных=new ContentValues();
            contentValuesСменаДанных.put("localversionandroid", "2000-01-10 00:00:00");
            contentValuesСменаДанных.put("versionserveraandroid", "2000-01-10 00:00:00");

            contentValuesСменаДанных.put("localversionandroid_version", 0);
            contentValuesСменаДанных.put("versionserveraandroid_version", 0);
            contentValuesСменаДанных.put("name", ТекущееИмяТаблицы);

            // TODO: 03.02.2025 update new back
            РезультатОбновлениеОчисткиТаблиц=
                    moduleUpdating.getModuleSystemUpdate(getTableRoot,contentValuesСменаДанных,"name=?",new String[]{ТекущееИмяТаблицы});
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " РезультатОбновлениеОчисткиТаблиц "+РезультатОбновлениеОчисткиТаблиц );
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатОбновлениеОчисткиТаблиц;
    }



}