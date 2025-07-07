package com.dsy.dsu.TabelsApp.Templates.model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sous.backasync.launch.ModuleInserting;

import java.util.Date;

public class BinesslogicAddingTemplate extends AddingTemplateIntarface {
  private Context context;
  private Activity activity;

  public BinesslogicAddingTemplate(Context context, Activity activity) {
      this.context = context;
      this.activity = activity;
  }


  // TODO: 26.06.2025

  @Override
  ////todo добавления новый Шаблон
  public Long addingTemplateInTabel(@NonNull Bundle bundleItemCompletetemplate ) {
      long         addingTemplateForTabel=0;
      try{
          // TODO: 02.07.2025
          activity.runOnUiThread(()->{
              ProgressDialog progressDialog= new ProgressDialog(activity);
              progressDialog.setIndeterminate(true);
              progressDialog.setCancelable(false);
              progressDialog.setTitle("Шаблон");
              progressDialog.setMessage("Добавление в табель...");
              progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
              progressDialog.setMax(1);
              progressDialog.setProgress(0);
              progressDialog.setCanceledOnTouchOutside(false);
              progressDialog.show();
          });

          Log.d(context.getClass().getName(), "\n"
                  + " время: " + new Date()+"\n+" +
                  " Класс в процессе... " +  this.getClass().getName()+"\n"+
                  " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                  + " bundleItemCompletetemplate " +bundleItemCompletetemplate);


          // TODO: 02.07.2025  вставка из Готового Шаблона в Табель




          Log.d(context.getClass().getName(), "\n"
                  + " время: " + new Date()+"\n+" +
                  " Класс в процессе... " +  this.getClass().getName()+"\n"+
                  " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  );


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






    // TODO: 07.09.2021  Терминальный метод вСтавки данных шаблон создание  и вставка Готовго Шаблона
    @Override
    public Integer operationsCompleteAfterTemplate(@NonNull ContentValues contentvaluesTemplateOperationsAdding, @NonNull String getNameTable) {
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
