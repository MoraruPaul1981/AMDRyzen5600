package com.dsy.dsu.TabelsApp.Templates.model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
import com.dsy.dsu.TabelsApp.Peoples.MainActivityListPeoples;
import com.dsy.dsu.TabelsApp.Templates.model.abstractes.TemplateGenerator;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.sous.backasync.launch.ModuleInserting;

import java.util.Date;

public class InsertingAndCreatingaTemplate extends TemplateGenerator {
    private Context context;
    private Activity activity;

    public InsertingAndCreatingaTemplate(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }


    // TODO: 26.06.2025

    @Override
    ////todo добавления новый Шаблон
    public Long addingTemplateForTabel(@NonNull Bundle bundleItemCompletetemplate ) {
        long         addingTemplateForTabel=0;
        try{
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



         /*   ContentValues contentValuesaddingTemplateForTabel = new ContentValues();////контрейнер для нового табеля
            String СгенерированованныйДатаДляДаннойОперации = new GetMainDateForApp(context).getMainDateForApp();
            contentValuesaddingTemplateForTabel.put("date_update", СгенерированованныйДатаДляДаннойОперации);

            // TODO: 09.04.2023  курсор самим создаваемых табеляПОСИК ДАННЫХ ЧЕРЕЗ UUID
            contentValuesaddingTemplateForTabel.put("user_update", ПубличноеID);
            contentValuesaddingTemplateForTabel.put("status_carried_out", "False");
            //  АдаптерВставкиDATAtABELS.putNull("_id");
            contentValuesaddingTemplateForTabel.put("fio", CurrentFIOUUID);
            contentValuesaddingTemplateForTabel.put("uuid", UUIDGeneratorINset);
            contentValuesaddingTemplateForTabel.put("status_send", " ");
            contentValuesaddingTemplateForTabel.put("uuid_tabel", MainParentUUID);//MainParentUUID
            contentValuesaddingTemplateForTabel.put("prof", Prof);//MainParentUUID



// TODO: 08.10.2021 повышаем версию
            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
            Long getVersionCurrent = new VersionCurentTable(context).upVersionCurentTable("data_tabels"  );

            contentValuesaddingTemplateForTabel.put("current_table", getVersionCurrent);
            // TODO: 09.10.2024 Public ID
            Integer getPublicID = new GetttingPublicID().getttingPublicID(context);
            Long UUIDGeneratorForFindFio = (Long) new GreatUuidGeneration(context).greatUuidGeneration( );


            // TODO: 14.03.2025  Вставка Уже готового Шаблона в Табель
                      addingTemplateForTabel = addTemplateOperationsFinal(contentValuesaddingTemplateForTabel,"templates");

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " addingTemplateForTabel " +addingTemplateForTabel);

            // TODO: 03.10.2021  второя событие   вставка ВС,СУББ Понедельник
            if ( addingTemplateForTabel>0) {
                // TODO: 14.03.2025
                Integer resultInsertDaysOff=
                        new AddingDaysOff(context).addingDaysOff( UUIDGeneratorForFindFio,ГодТабелей,МЕсяцТабелей );
                Log.d(context.getClass().getName(), "\n"
                        + " время: " + new Date()+"\n+" +
                        " Класс в процессе... " +  this.getClass().getName()+"\n"+
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " resultInsertDaysOff "+resultInsertDaysOff);

            }*/

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



    ////todo добавления новый Шаблон
    @Override
    public Integer proccesingNewTamplate(@NonNull View v, @NonNull String namenewtemplate) {
        Integer         setproccesingNewTamplate=0;
        try{
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

            // TODO: 14.05.2025
            ModuleInserting moduleInserting=new ModuleInserting(context);
            // TODO: 14.05.2025
            setproccesingNewTamplate =    moduleInserting.getModuleInsert(НазваниеТаблицы,contentValuesNewTamplate);

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  setproccesingNewTamplate;
    }










// TODO: 07.09.2021  Терминальный метод вСтавки данных шаблон создание  и вставка Готовго Шаблона
   @Override
    public    Integer addTemplateOperationsFinal( @NonNull  ContentValues  contentvaluesTemplateOperationsAdding,@NonNull String getNameTable) {
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



    ///todo сообщение
    public void messageForCreatingNewTemplates(  @NonNull String Создание ,
                                               @NonNull  String СообщениеДиалога,
                                               @NonNull String namenewtemplate,
                                               @NonNull Bundle bundleFromActivityListPeoples ) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        try {
            int ФлагЗнака = R.drawable.icon_documentscommitings1;//icon_dsu1_new_customer7
/////////TODO создание Шаблона
            final AlertDialog alertDialogNewTemplates = new MaterialAlertDialogBuilder(activity)
                    .setMessage(СообщениеДиалога)
                    .setNegativeButton("Выйти", null)
                    .setPositiveButton("Создать", null)
                    .setTitle(Создание)
                    .setMessage(СообщениеДиалога)
                    .setIcon(ФлагЗнака).show();
            // TODO: 28.06.2025 Кнопки
            final Button buttonNegative = alertDialogNewTemplates.getButton(AlertDialog.BUTTON_NEGATIVE);
            buttonNegative.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) throws  NullPointerException {
                    // TODO: 28.06.2025
                    alertDialogNewTemplates.dismiss();
                    alertDialogNewTemplates.cancel();
                    // TODO: 17.04.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }
            });

            // TODO: 28.06.2025 Кнопки
            final Button buttonPositive = alertDialogNewTemplates.getButton(AlertDialog.BUTTON_POSITIVE);
            buttonPositive.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) throws  NullPointerException {
                    // TODO: 28.06.2025
                    Integer setproccesingNewTamplate = new InsertingAndCreatingaTemplate(context, activity).
                            proccesingNewTamplate(v, namenewtemplate);

                    // TODO: 26.06.2025
                    alertDialogNewTemplates.dismiss();
                    alertDialogNewTemplates.cancel();

                    // TODO: 26.06.2025  полче создани выходим из ДИалога  ипееходим на актвти назад
                    if (setproccesingNewTamplate>0) {
                        backToActivityListTabels(bundleFromActivityListPeoples);
                    }else {
                        Snackbar snackbar=      Snackbar.make(v, "Не создан шаблон !!! ",Snackbar.LENGTH_LONG).setAction("Action",null);
                        snackbar.show();
                    }

                    // TODO: 17.04.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "setproccesingNewTamplate " + setproccesingNewTamplate);
                }
            });
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    
    
    
    
    ///todo финалный метод еотровый другому активти посылает данные
    @Override
    public void backToActivityListTabels(@NonNull Bundle bundlebackActivitylistPeoples) {
        try{
            Intent ИнтентBackToActivityListTabels = new Intent(activity, MainActivityListPeoples.class);
            ИнтентBackToActivityListTabels.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ИнтентBackToActivityListTabels.putExtras(bundlebackActivitylistPeoples);
            activity. startActivity( ИнтентBackToActivityListTabels);
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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


    ///todo сообщение Выбор Додавить  в Табель или Наполнить ФИО текущие Шаблон
    @Override
    public void choiceMessageCompleteAddtoTimesheetOrFillFio(@NonNull Bundle bundleFromActivityListPeoples ) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        try {
            int ФлагЗнака = R.drawable.icon_documentscommitings1;//icon_dsu1_new_customer7
/////////TODO создание Шаблона
            final AlertDialog alertDialogChoiceTemplates = new MaterialAlertDialogBuilder(activity)
                    .setMessage("Заполнить")
                    .setNegativeButton("Добавить фио", null)
                    .setPositiveButton("В табель", null)
                    .setTitle("Действия Шаблона")
                    .setIcon(ФлагЗнака).show();
            // TODO: 28.06.2025 Кнопки
            final Button buttonNegativeChoice = alertDialogChoiceTemplates .getButton(AlertDialog.BUTTON_NEGATIVE);
            buttonNegativeChoice.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) throws  NullPointerException {
                    // TODO: 28.06.2025 Заполняем ФИо уже готовый Шаблон

                    alertDialogChoiceTemplates .dismiss();
                    alertDialogChoiceTemplates.cancel();
                    // TODO: 17.04.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }
            });

            // TODO: 28.06.2025 Кнопки
            final Button buttonPositiveChoice = alertDialogChoiceTemplates.getButton(AlertDialog.BUTTON_POSITIVE);
            buttonPositiveChoice.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) throws  NullPointerException {
                    // TODO: 28.06.2025 Добавляем  В табель
                    Long addingTamplateForTabel=          addingTemplateForTabel( bundleFromActivityListPeoples);
                    // TODO: 26.06.2025
                    alertDialogChoiceTemplates.dismiss();
                    alertDialogChoiceTemplates.cancel();
                    // TODO: 17.04.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " addingTamplateForTabel " +addingTamplateForTabel);
                }
            });
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }










    //TODO END CLASS
}
