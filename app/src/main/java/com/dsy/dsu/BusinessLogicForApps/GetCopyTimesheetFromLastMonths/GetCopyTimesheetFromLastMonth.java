package com.dsy.dsu.BusinessLogicForApps.GetCopyTimesheetFromLastMonths;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.dsy.dsu.BusinessLogicForApps.AddingDaysOffs.AddingDaysOff;
import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;
import com.dsy.dsu.BusinessLogicForApps.GreatUuidGenerations.GreatUuidGeneration;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.TabelsApp.Tabel.ListTabels.MainActivity_List_Tabels;
import com.sous.backasync.launch.ModuleInserting;
import com.sous.backasync.launch.ModuleQuety;
import org.reactivestreams.Subscription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.inject.Named;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;




@Named
public class GetCopyTimesheetFromLastMonth {
    private Context context;
    private   Integer  ГодТабелейИзТабеля;
    private   Integer   МЕсяцТабелейИзТабеля;
    private      Long     MainParentUUID;
    private   Integer   DigitalNameCFO;

    public @Inject GetCopyTimesheetFromLastMonth(@ApplicationContext Context context) {
        this.context = context;
    }
    // TODO: 09.06.2025

    public void addPeopleLastMonth(  @NonNull Intent intentLastMonth, @NonNull ProgressDialog progressDialog) {
        try {
            final AtomicInteger atomicIntegerBeforeMothCopyTabel = new AtomicInteger(0);
            // TODO: 22.09.2025
            String getCurrentTabel="viewtabel";
            //TODO ВЫЧИСЛЯЕМ ДАННЫЕ КОТОРЫЕ НА ВСТАВИТЬ
            // TODO: 24.02.2025 внтрений
            AtomicReference<Cursor>    Курсор_ВытаскиваемПоследнийМесяцТабеля =new AtomicReference<>();
            Flowable.range(1,12)
                    .filter(f->f.intValue()<МЕсяцТабелейИзТабеля)
                    //.filter(f->f.intValue()<9)
                    .sorted(Collections.reverseOrder()).delay(1000, TimeUnit.MILLISECONDS)
                    .onBackpressureBuffer()
                    .doOnNext(new io.reactivex.rxjava3.functions.Consumer<Integer>() {
                        @Override
                        public void accept(Integer getmonthagofordatasearch) throws Throwable {
                            // TODO: 15.05.202
                            ModuleQuety moduleQuety=new ModuleQuety(context);
                            Курсор_ВытаскиваемПоследнийМесяцТабеля.getAndSet( moduleQuety.getModuleQuery(getCurrentTabel," SELECT *  FROM "+getCurrentTabel+" AS D" +
                                    "  WHERE D.year_tabels= '"+ГодТабелейИзТабеля +"'" +
                                    " AND D.month_tabels='"+getmonthagofordatasearch +"'" +
                                    " AND D.cfo='"+DigitalNameCFO+ "'" +
                                    "   AND D.status_send!='Удаленная'" +
                                    "   ORDER BY D.date_update DESC  " ,null));

                            Log.d(this.getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + " Курсор_ВытаскиваемПоследнийМесяцТабеля " +Курсор_ВытаскиваемПоследнийМесяцТабеля.get());
                        }
                    })
                    .takeWhile(new Predicate<Integer>() {
                        @Override
                        public boolean test(Integer integer) throws Throwable {
                            // TODO: 24.02.2025
                            Integer  getmonthagofordatasearchtakeWhile=0;
                            if (Курсор_ВытаскиваемПоследнийМесяцТабеля.get()!=null) {
                                getmonthagofordatasearchtakeWhile=  Курсор_ВытаскиваемПоследнийМесяцТабеля.get().getCount();
                            }
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " getmonthagofordatasearchtakeWhile  " +getmonthagofordatasearchtakeWhile);
                            if (getmonthagofordatasearchtakeWhile>0) {
                                return false; //TODO false  это продолжение обработуки
                            } else {
                                return true;
                            }
                        }
                    }).doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            if (Курсор_ВытаскиваемПоследнийМесяцТабеля.get()!=null) {
                                if (Курсор_ВытаскиваемПоследнийМесяцТабеля.get().getCount()>0) {
                                    // TODO: 16.02.2023 сама вставка
                                    atomicIntegerBeforeMothCopyTabel.set( currentAddFromLastTabel(context, ГодТабелейИзТабеля, МЕсяцТабелейИзТабеля,
                                            Курсор_ВытаскиваемПоследнийМесяцТабеля.get(),
                                            progressDialog,MainParentUUID));
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " atomicIntegerBeforeMothCopyTabel  " + atomicIntegerBeforeMothCopyTabel.get());


                                }
                            }


// TODO: 24.02.2025

                            // TODO: 21.09.2023
                            if (             atomicIntegerBeforeMothCopyTabel.get() >0) {
                                // TODO: 21.04.2023 после операции возврящемся на Activity List Peoples
                                switchToActivity_List_Peoples(intentLastMonth);

                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + "  atomicIntegerBeforeMothCopyTabel.get() " +            atomicIntegerBeforeMothCopyTabel.get() );
                            }else{
                                // TODO: 24.02.2025
                                context.getMainExecutor().execute(()->{
                                    Toast.makeText(context, "Табель не скопирован!!!", Toast.LENGTH_SHORT).show();
                                });

                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + "  atomicIntegerBeforeMothCopyTabel.get() " + atomicIntegerBeforeMothCopyTabel.get());
                            }

                            progressDialog.dismiss();
                            progressDialog.cancel();

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }
                    })
                    .doOnSubscribe(new Consumer<Subscription>() {
                        @Override
                        public void accept(Subscription subscription) throws Throwable {
                            // TODO: 24.02.2025
                            Bundle bundleПолучаемДанных =(Bundle)  intentLastMonth.getExtras();
                            MainParentUUID=    bundleПолучаемДанных.getLong("MainParentUUID", 0l);
                            DigitalNameCFO=   bundleПолучаемДанных.getInt("DigitalNameCFO", 0);
//                                            ГодТабелейИзТабеля= getYear();
//                                            МЕсяцТабелейИзТабеля=     getMoth();
                            ГодТабелейИзТабеля=  bundleПолучаемДанных.getInt("ГодТабелей", 0);
                            МЕсяцТабелейИзТабеля=  bundleПолучаемДанных.getInt("МЕсяцТабелей",0);


                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " DigitalNameCFO " +DigitalNameCFO
                                    + "МЕсяцТабелейИзТабеля " +МЕсяцТабелейИзТабеля +" ГодТабелейИзТабеля " +ГодТабелейИзТабеля);
                        }
                    }).subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
            //todo  конец
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    /////TODO метод запуска кода при однократорм нажатии просто загузка сотрудников табель
    private void switchToActivity_List_Peoples(@NonNull Intent intentLastMonth ) {
        try{
            Intent    ИнтентпереходВMainActivityList_Peoples=new Intent(context, MainActivity_List_Tabels.class);
            ИнтентпереходВMainActivityList_Peoples      .putExtras(intentLastMonth.getExtras());
            ИнтентпереходВMainActivityList_Peoples.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(ИнтентпереходВMainActivityList_Peoples);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " intentLastMonth " +intentLastMonth.getExtras());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    private Integer currentAddFromLastTabel(@NonNull Context context,
                                            @NonNull  Integer ГодНазадДляЗаполнени,
                                            @NonNull   Integer МесяцИзПрошлогоМесяца,
                                            @NonNull    Cursor Курсор_ВытаскиваемПоследнийМесяцТабеля,
                                            @NonNull ProgressDialog progressDialog,
                                            @NonNull Long MainParentUUID) {
        ArrayList<Integer> integerArrayListВствавкаИзПрошлогоМесяц = new ArrayList<>();;
        final Disposable[] disposable1 = new Disposable[1];
        try {
            // TODO: 21.09.2023  Сама ыВсатвка
            Observable.range(0,Курсор_ВытаскиваемПоследнийМесяцТабеля.getCount())
                    .concatMap(i -> Observable.just(i).delay(250, TimeUnit.MILLISECONDS))
                    .blockingSubscribe(new Observer<Integer>() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                            Курсор_ВытаскиваемПоследнийМесяцТабеля.moveToLast();
                            context.getMainExecutor().execute(() -> {
                                progressDialog.setMax(Курсор_ВытаскиваемПоследнийМесяцТабеля.getCount());
                            });
                            disposable1[0] =d;
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }

                        @Override
                        public void onNext(@io.reactivex.rxjava3.annotations.NonNull Integer integer) {
                            // TODO: 23.09.2022 ВСТАВЛЯЕМ ДАННЫЕ ВО ВТОРУЮ ТАБЛИЦЫ ДАТА_ТАБЕЛЬ
                            Integer РезультатВставкиВНИжнуюТаюблицу = addingNewRowFromLarTabel(context,
                                    Курсор_ВытаскиваемПоследнийМесяцТабеля, MainParentUUID, ГодНазадДляЗаполнени, МесяцИзПрошлогоМесяца);

                            // TODO: 21.09.2023
                            Курсор_ВытаскиваемПоследнийМесяцТабеля.moveToPrevious();
                            Log.d(this.getClass().getName(), " Вторая Таблиуа Из Прошлого МЕссяца РезультатВставкиВНИжнуюТаюблицу"
                                    + РезультатВставкиВНИжнуюТаюблицу);
                            if (РезультатВставкиВНИжнуюТаюблицу > 0) {
                                integerArrayListВствавкаИзПрошлогоМесяц.add(РезультатВставкиВНИжнуюТаюблицу);
                                context.getMainExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.setProgress(integerArrayListВствавкаИзПрошлогоМесяц.size());
                                    }
                                });


                            }
                        }


                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                        }

                        @Override
                        public void onComplete() {
                           context.getMainExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    progressDialog.cancel();

                                }
                            });
                            // TODO: 21.09.2023  cliser
                            Курсор_ВытаскиваемПоследнийМесяцТабеля.close();
                            disposable1[0].dispose();
                        }
                    });
            // TODO: 23.09.2022 ВСТАВЛЯЕМ ДАННЫЕ ВО ВТОРУЮ ТАБЛИЦЫ ДАТА_ТАБЕЛЬ

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return  integerArrayListВствавкаИзПрошлогоМесяц.size();
    }



    @SuppressLint("Range")
    private Integer addingNewRowFromLarTabel(@NonNull Context context,
                                             @NonNull Cursor Курсор_ВытаскиваемПоследнийМесяцТабеля,
                                             @NonNull Long ParentUUID,
                                             @NonNull Integer ПолученаяДатаТолькоГод,
                                             @NonNull Integer  МесяцИзПрошлогоМесяца) {
        Integer ответОперцииВставки = 0;
        try {
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ПолученаяДатаТолькоГод  " + ПолученаяДатаТолькоГод + "  МесяцИзПрошлогоМесяца " + МесяцИзПрошлогоМесяца +
                    " Курсор_ВытаскиваемПоследнийМесяцТабеля " +Курсор_ВытаскиваемПоследнийМесяцТабеля);
            // TODO: 15.05.2025
            String НазваниеОбрабоатываемойТаблицы = "data_tabels";
            ContentValues contentValuesДляДатаТабель = new ContentValues();
            int ИндексСтолбикаДляЗаполненияФИО = Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("fio");
            contentValuesДляДатаТабель.put("fio", Курсор_ВытаскиваемПоследнийМесяцТабеля.getLong(ИндексСтолбикаДляЗаполненияФИО));
            int ИндексFIOuser_update = Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("user_update");
            contentValuesДляДатаТабель.put("user_update", Курсор_ВытаскиваемПоследнийМесяцТабеля.getInt(ИндексFIOuser_update));

            // TODO: 09.06.2025
            Integer Профессия     = Курсор_ВытаскиваемПоследнийМесяцТабеля.getInt(Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("dt_prof"));
            if (Профессия==0) {
                Профессия =  Курсор_ВытаскиваемПоследнийМесяцТабеля.getInt(Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("fio_prof"));
            }
            contentValuesДляДатаТабель.put("prof", Профессия);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ответОперцииВставки "+ответОперцииВставки );


            String СгенерированованныйДатаДляДаннойОперации = new GetMainDateForApp(context).getMainDateForApp();
            contentValuesДляДатаТабель.put("date_update", СгенерированованныйДатаДляДаннойОперации);

            // TODO: 23.09.2022 сама вставка в таблиц ТАБЕЛЬ  #1
            Long ДляНовойЗаписиUUID = (Long) new GreatUuidGeneration(context).greatUuidGeneration();
            contentValuesДляДатаТабель.put("uuid", ДляНовойЗаписиUUID);;
            contentValuesДляДатаТабель.put("uuid_tabel", ParentUUID);
            contentValuesДляДатаТабель.put("status_send", " ");
            contentValuesДляДатаТабель.put("status_carried_out", "False");
            // contentValuesДляДатаТабель.putNull("_id");
            // TODO: 22.09.2022 дополнительные параменты ДатаТабель
            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
            Long РезультатУвеличиваемВерсияДатаТАбель = new VersionCurentTable(context).upVersionCurentTable(    НазваниеОбрабоатываемойТаблицы );
            contentValuesДляДатаТабель.put("current_table", РезультатУвеличиваемВерсияДатаТАбель);


            // TODO: 14.05.2025
            ModuleInserting moduleInserting=new ModuleInserting(context);
            // TODO: 14.05.2025
            ответОперцииВставки =    moduleInserting.getModuleInsert(НазваниеОбрабоатываемойТаблицы,contentValuesДляДатаТабель);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ответОперцииВставки "+ответОперцииВставки );
            if (     ответОперцииВставки>0) {
                Integer РезультатВставкаВыходныхДНей= new AddingDaysOff(context).addingDaysOff(ДляНовойЗаписиUUID,ПолученаяДатаТолькоГод,МесяцИзПрошлогоМесяца );
                Log.d(this.getClass().getName(), "   РезультатВставкаВыходныхДНей  "+  РезультатВставкаВыходныхДНей);
                // TODO: 21.09.2023
            }

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " ответОперцииВставки " +ответОперцииВставки);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return ответОперцииВставки;
    }


}//TODO END CLASS
