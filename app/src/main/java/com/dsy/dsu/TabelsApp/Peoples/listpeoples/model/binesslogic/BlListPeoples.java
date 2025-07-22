package com.dsy.dsu.TabelsApp.Peoples.listpeoples.model.binesslogic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.dsy.dsu.TabelsApp.Tabel.Single.view.MainActivity_Tabel_Single_PeopleOneSwipe;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;
import com.sous.backasync.businesslogic.dates.Class_GenerationBack_Data;
import com.sous.backasync.launch.ModuleDeleting;
import com.sous.backasync.launch.ModuleQuety;
import com.sous.backasync.launch.ModuleUpdating;

import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;


@Named
public class BlListPeoples {
    private Context context;
    private Activity activity;
    private  ListView listActivityListPeoples;

    public @Inject BlListPeoples(@ApplicationContext Context context , @NonNull Activity activity,@NotNull ListView listActivityListPeoples) {
        this.context = context;
        this.activity = activity;
        this.listActivityListPeoples = listActivityListPeoples;
    }



    public  void launchItemClickAndLongClick(@NotNull Animation animationv3){

        listActivityListPeoples.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 07.05.2025

                LinearLayout linearLayoutItemPeople=    (LinearLayout)       parent.getAdapter().getView(position, view,parent );
                MaterialTextView getMaterialTextViewPeople=(MaterialTextView)     linearLayoutItemPeople.findViewById(R.id.MaterialTextViewPeoples);

                // TODO: 22.07.2025
                getMaterialTextViewPeople.setBackgroundColor(Color.GRAY);

                // TODO: 07.05.2025
                Bundle bundlePepolesУдаление= (Bundle) getMaterialTextViewPeople.getTag();
                if (bundlePepolesУдаление!=null) {

                    методПереходаИзList_People(bundlePepolesУдаление);


                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


                }

                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            }
        });


        // TODO: 22.07.2025 LONG CLICK
        listActivityListPeoples.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 22.07.2025
                View linearLayoutItemPeople=          parent.getAdapter().getView(position, view,parent );
                MaterialTextView getMaterialTextViewPeople=(MaterialTextView)     linearLayoutItemPeople.findViewById(R.id.MaterialTextViewPeoples);
                // TODO: 07.05.2025
                Bundle bundlePepolesУдаление= (Bundle) getMaterialTextViewPeople.getTag();
                if (bundlePepolesУдаление!=null) {
                    getMaterialTextViewPeople.setBackgroundColor(Color.GRAY);
                    getMaterialTextViewPeople.startAnimation(animationv3);
                    // TODO: 09.04.2023 set TABEL    MAinActivbity_List_Tabels
                    getMaterialTextViewPeople.setBackgroundColor(Color.WHITE);
                    //TODO: 09.04.2023 Удаление ВыбраногоСОтурдника
                    МетодСообщенииУдалениеСотрудника(bundlePepolesУдаление);

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


                }






                Log.d(this.getClass().getName(),"\n" + " class " +
                        Log.d(this.getClass().getName(),"\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"));
                return false;
            }
        });

    }



    // TODO: 09.04.2023  метод удаление сотрудника
    void МетодСообщенииУдалениеСотрудника(@NonNull Bundle bundlePepolesУдаление ) {
        try {

            String ФИО=   bundlePepolesУдаление.getString("ФИО", "");
            // TODO: 03.09.2021 поле
            СообщениеВыборУдлаляниИлиНЕтСотрудникаИзБазы("Удаление сотрудника",
                    "Удалить сотрудника ?: "+ФИО+"\n",
                    bundlePepolesУдаление)  ;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    ///todo сообщение
    @UiThread
    protected void СообщениеВыборУдлаляниИлиНЕтСотрудникаИзБазы(@NonNull String ШабкаДиалога,
                                                                @NonNull String СообщениеДиалога,
                                                                @NonNull  Bundle  bundlePepolesУдаление  ) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        try {
//////сам вид
            final AlertDialog alertDialog = new MaterialAlertDialogBuilder(activity)
                    .setTitle(ШабкаДиалога)
                    .setMessage(СообщениеДиалога)
                    .setPositiveButton("Да", null)
                    .setNegativeButton("Нет", null)
                    .setIcon(R.drawable.icon_dsu1_delete_customer)
                    .show();
/////////кнопка
            final Button MessageBoxУдалениеСотрудникаИзТабеля = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxУдалениеСотрудникаИзТабеля .setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    try{
                        alertDialog.dismiss();

                        // TODO: 04.04.2025
                        МетодУдалениеСотрудникаИзТабеля(    bundlePepolesУдаление );

                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }

                }
            });

            /////////кнопка
            final Button MessageBoxУдалениеСотрудникаИзТабеляОтмена = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxУдалениеСотрудникаИзТабеляОтмена.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }




    //todo метод удаление сотрудника из табеля
    private void МетодУдалениеСотрудникаИзТабеля(@NonNull Bundle     bundlePepolesУдаление    ) {
        // TODO: 04.04.2025
        AtomicInteger результатУдалениеСотрудникаИзТабеля=new AtomicInteger(0);
        try{
            String ТаблицыДляОбработкиУдалние="data_tabels";
            ProgressDialog progressDialogДляудалениеОдногоотрудника=new ProgressDialog(activity);
            progressDialogДляудалениеОдногоотрудника.setIndeterminate(true);
            progressDialogДляудалениеОдногоотрудника.setTitle("Удаление сотрудника");
            progressDialogДляудалениеОдногоотрудника.setMessage("Удаление...");
            progressDialogДляудалениеОдногоотрудника.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialogДляудалениеОдногоотрудника.setCanceledOnTouchOutside(false);
            progressDialogДляудалениеОдногоотрудника.setCancelable(false);
            progressDialogДляудалениеОдногоотрудника.show();
            // TODO: 12.10.2021  Ссылка Менеджер Потоков
            Completable completableУдалениеСотрудникаИзЛистаТАбедя=Completable
                    .fromAction(new Action() {
                        @SuppressLint("Range")
                        @Override
                        public void run() throws Throwable {
                            // TODO: 10.08.202


                            Long MainParentUUID=   bundlePepolesУдаление.getLong("MainParentUUID", 0l);
                            Long CurrenrsSelectFio=   bundlePepolesУдаление.getLong("CurrenrsSelectFio", 0l);
                            /////
                            ModuleQuety moduleQuety=new ModuleQuety(context);
                            Cursor cursorGetStatusSend= moduleQuety.getModuleQuery("data_tabels"," SELECT *  FROM data_tabels AS D" +
                                    "  WHERE D.uuid_tabel= '"+MainParentUUID+"'   AND D.fio= '"+CurrenrsSelectFio+"'  AND D.status_send='Удаленная'    " ,null);

                            ModuleUpdating moduleUpdating = new ModuleUpdating(context);
                            String ТаблицаСистемная = "data_tabels";

                            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                            Long РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника =
                                    new VersionCurentTable(context).upVersionCurentTable(   ТаблицыДляОбработкиУдалние  );

                            // TODO: 22.11.2021  ПОСЛЕ УСПЕШНОЙ ОПЕРАЦИИ ПОДТВЕРЖДАЕМ ТРАНЗАУЙИЮ
                            String СгенерированованныйДата = new Class_GenerationBack_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
                            ContentValues contentValuesДляПоднятияВерсии = new ContentValues();


                            Integer getCurrentID=0;
                            // TODO: 01.07.2023  после выравниванию ДЛЯ СЕРВЕРА
                            if (cursorGetStatusSend.getCount()==0) {
                                // TODO: 04.04.2025
                                // TODO: 04.04.2025 UPDATE
                                getCurrentID=   bundlePepolesУдаление.getInt("getCurrentID", 0);
                                contentValuesДляПоднятияВерсии.put("status_send", "Удаленная");
                                contentValuesДляПоднятияВерсии.put("date_update", СгенерированованныйДата);
                                contentValuesДляПоднятияВерсии.put("current_table", РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);
                                // TODO: 03.02.2025 update new back
                                результатУдалениеСотрудникаИзТабеля.set(moduleUpdating.getModuleUpdate(ТаблицаСистемная,contentValuesДляПоднятияВерсии,"_id=?", new String[] {getCurrentID.toString()} ));


                                // TODO: 04.04.2025 delete
                            } else {
                                // TODO: 04.04.2025
                                // TODO: 03.02.2025 delete
                                if (cursorGetStatusSend.getCount()>0) {
                                    ModuleDeleting moduleDeleting=new ModuleDeleting(context);
                                    contentValuesДляПоднятияВерсии.put("date_update", СгенерированованныйДата);
                                    contentValuesДляПоднятияВерсии.put("current_table", РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);
                                    getCurrentID=   cursorGetStatusSend.getInt(cursorGetStatusSend.getColumnIndex("_id"));
                                    // TODO: 03.02.2025 delete
                                    результатУдалениеСотрудникаИзТабеля.set(moduleDeleting.getModuleDelete(ТаблицаСистемная,"_id=?", new String[] {getCurrentID.toString()} ));


                                    // TODO: 04.04.2025 UPDATE
                                    getCurrentID=   bundlePepolesУдаление.getInt("getCurrentID", 0);
                                    contentValuesДляПоднятияВерсии.put("status_send", "Удаленная");
                                    contentValuesДляПоднятияВерсии.put("date_update", СгенерированованныйДата);
                                    contentValuesДляПоднятияВерсии.put("current_table", РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);
                                    // TODO: 03.02.2025 update new back
                                    результатУдалениеСотрудникаИзТабеля.set(moduleUpdating.getModuleUpdate(ТаблицаСистемная,contentValuesДляПоднятияВерсии,"_id=?", new String[] {getCurrentID.toString()} ));





                                }
                            }
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " результатУдалениеСотрудникаИзТабеля.get() "+результатУдалениеСотрудникаИзТабеля.get()+
                                    " cursorGetStatusSend.getCount() " +cursorGetStatusSend.getCount());


                            if (результатУдалениеСотрудникаИзТабеля.get() > 0) {
                                ///todo ДАННЫЙ КОД ИЗМЕНЯЕТ ВЕРИСЮ ДАННЫХ
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialogДляудалениеОдногоотрудника.setMessage("Удаление...");
                                    }
                                });

                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
                            }

                        }
                    }).doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            //TODO ЗАПУСКАЕМ ФУТУРЕ

                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // TODO: 30.01.2022 Сообщеам Observer что изменилибьс данные в адаптере AdapterCursor


                                    if (результатУдалениеСотрудникаИзТабеля.get() >0){


                                         Intent intentPeopls=new Intent();
                                        intentPeopls.setAction("BloadcastRecieverListPeoples");
                                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentPeopls);


                                    }else{
                                        СообщениеПослеУдаленияСотрудникаИзТабеля("Оповещение",  "Операция удаление сотрудника не прошла ",false);
                                    }
                                    progressDialogДляудалениеОдногоотрудника.dismiss();
                                    progressDialogДляудалениеОдногоотрудника.cancel();

                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
                                    ///TODO СООБЩЕНИЕ О РЕЗУЛЬТАТОВ
                                }
                            });
                        }
                    }).doOnError(e->{
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

                    });
            completableУдалениеСотрудникаИзЛистаТАбедя.subscribe();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " результатУдалениеСотрудникаИзТабеля.get() "
                    +результатУдалениеСотрудникаИзТабеля.get());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    ///todo  конец метода удаления третий обработчки нажатия
    ///todo сообщение
    @UiThread
    protected void СообщениеПослеУдаленияСотрудникаИзТабеля(String ШабкаДиалога,  String СообщениеДиалога,boolean Статус) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
//////сам вид
        int Значек;
        if (Статус){
            Значек  =R.drawable.icon_dsu1_tabel_info;
        }else{
            Значек  =R.drawable.icon_dsu1_delete_customer;
        }
        final AlertDialog alertDialog = new MaterialAlertDialogBuilder(activity)
                .setTitle(ШабкаДиалога)
                .setMessage(СообщениеДиалога)
                .setPositiveButton("ОК", null)
                .setIcon(Значек)
                .show();
/////////кнопка
        final Button MessageBoxUpdateСоздатьТабель = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        MessageBoxUpdateСоздатьТабель.setOnClickListener(new View.OnClickListener() {
            ///MessageBoxUpdate метод CLICK для DIALOBOX

            @Override
            public void onClick(View v) {
                //удаляем с экрана Диалог
                alertDialog.dismiss();
                Log.d(this.getClass().getName(), "  ФИНАЛ после удалание сотрудуника ");
                //TODO  второе действие заполенние контентом  в табеля в TableLyзаполения табеля из базы через элемент TableLauy
                ///// МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля( МесяцТабеляФинал);
                ///TODO запускаем возврат на предыдущее активывти после успешного удаление сотрудника
                ///МетодЗапускаетСотрудниковПослеУспешногоУдалениеСотрудника();


                //todo
            }


        });
    }
    // TODO: 08.04.2023 ПЕРЕХОДИТ ИЗ PEOLES
    void методПереходаИзList_People(@NonNull Bundle bundleПереходИзList_People){
        try {
            Intent intentПереходИзList_People= new Intent();
            intentПереходИзList_People.setClass(context, MainActivity_Tabel_Single_PeopleOneSwipe.class);//TODO тест
            intentПереходИзList_People.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentПереходИзList_People.putExtras(bundleПереходИзList_People);
          activity. startActivity(intentПереходИзList_People);
            activity.    finishAfterTransition();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " bundleПереходИзList_People "+bundleПереходИзList_People );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    // TODO: 22.07.2025  END CLASS

}
