package com.dsy.dsu.CoreApp.Apps.SettingsCoreApp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.sous.backasync.businesslogic.dates.Class_GenerationBack_Data;
import com.sous.backasync.launch.ModuleUpdating;

import java.util.Date;

public class ChangeSSLForSettings {

    Switch  switchsslcomunications;

    Context context;

    public ChangeSSLForSettings(Switch switchsslcomunications, Context context) {
        this.switchsslcomunications = switchsslcomunications;
        this.context = context;
    }


    public  void changeSwitcSllSimple(){
  try {
      String getMode_ssl=new String();
      //SELECT

      getMode_ssl=new SLLBenessLogicMode(context).getModeSLL();

      Log.d(context.getClass().getName(), "\n"
              + " время: " + new Date()+"\n+" +
              " Класс в процессе... " +  this.getClass().getName()+"\n"+
              " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " getMode_ssl " +getMode_ssl);

          if(getMode_ssl.equalsIgnoreCase("http")) {
              switchsslcomunications.setChecked(false);
              switchsslcomunications.setText("Обычный (Сервер)");
             }else {
              // TODO: 09.10.2024
              if(getMode_ssl.equalsIgnoreCase("https")) {
                  switchsslcomunications.setChecked(true);
                  switchsslcomunications.setText("Защищенный (Сервер)");
              }
            }
      // TODO: 09.10.2024
      switchsslcomunications.refreshDrawableState();
      switchsslcomunications.requestLayout();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+" getMode_ssl "+getMode_ssl);

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }



    public  void changeSwitcSllSimpleLister(){
        try {

            switchsslcomunications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                   if (switchsslcomunications.isChecked()) {

                       recordingShiftdSSLconnectionMode("https");

                       Log.d(context.getClass().getName(), "\n"
                               + " время: " + new Date()+"\n+" +
                               " Класс в процессе... " +  this.getClass().getName()+"\n"+
                               " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    }else {

                       recordingShiftdSSLconnectionMode("http");

                       Log.d(context.getClass().getName(), "\n"
                               + " время: " + new Date()+"\n+" +
                               " Класс в процессе... " +  this.getClass().getName()+"\n"+
                               " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                   }



                    // TODO: 08.10.2024 после выполения   переопределяем внешний ВИД Активити
                    changeSwitcSllSimple();

                    Log.d(context.getClass().getName(), "\n"
                            + " время: " + new Date()+"\n+" +
                            " Класс в процессе... " +  this.getClass().getName()+"\n"+
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                }
            });


            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }







      Integer recordingShiftdSSLconnectionMode(@NonNull String getchangeMode){
          // TODO: 09.10.2024
          Integer recordingShiftdSSLconnectionMode=0;
          try{
              String ИмяТаблицы= "successlogin";
              ModuleUpdating moduleUpdating = new ModuleUpdating(context);
              ContentValues contentValuesChangeModeSLL=new ContentValues();
              // TODO: 08.10.2024 Находим если такой  Пользователь
              Integer getPublicID = new GetttingPublicID().getttingPublicID(context);
              String getNewDateForError = new Class_GenerationBack_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
              // TODO: 12.04.2023 UPDATER PUBLIC ID
              if(getPublicID>0 ){

                  // TODO: 09.10.2024
                  contentValuesChangeModeSLL.put("publicid",getPublicID);
                  contentValuesChangeModeSLL.put("mode_ssl",getchangeMode);
                  contentValuesChangeModeSLL.put("date_update",getNewDateForError);
                  // TODO: 03.02.2025 update new back
                  recordingShiftdSSLconnectionMode=        moduleUpdating.getModuleSystemUpdate(ИмяТаблицы,contentValuesChangeModeSLL);
                  Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                          " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                          " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " recordingShiftdSSLconnectionMode "+recordingShiftdSSLconnectionMode );

              }
              Log.d(context.getClass().getName(), "\n"
                  + " время: " + new Date()+"\n+" +
                  " Класс в процессе... " +  this.getClass().getName()+"\n"+
                  " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

      } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  recordingShiftdSSLconnectionMode;
      }




    // TODO: 09.10.2024


}
