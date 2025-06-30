package com.dsy.dsu.BusinessLogicForApps;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.util.Date;

public class SubClassWriterPUBLICIDtoDatabase {

    Context context;

    public SubClassWriterPUBLICIDtoDatabase() {
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    public Integer recordaftersyncSuccesLogin(@NonNull  Context context,
                                              @NonNull  Integer ПолученинныйПубличныйIDДлчЗаписиВБАзу,
                                              @NonNull  String ПубличноеИмяПользовательДлСервлета,
                                              @NonNull  String ПубличноеПарольДлСервлета) {


        Integer результатЗаписиНовогоПароляПользователявБазцуsuccesslogin = 0;
        try{
       ContentValues setNewPublicWitnSussecLogin=new ContentValues();
            setNewPublicWitnSussecLogin.put("publicid", ПолученинныйПубличныйIDДлчЗаписиВБАзу);
            setNewPublicWitnSussecLogin.put("success_users", ПубличноеИмяПользовательДлСервлета);
       ///
            setNewPublicWitnSussecLogin.put("success_login",ПубличноеПарольДлСервлета);
       Log.d(this.getClass().getName(), " ПубличноеИмяПользовательДлСервлета "
               + ПолученинныйПубличныйIDДлчЗаписиВБАзу +
               " ПубличноеПарольДлСервлета" + ПолученинныйПубличныйIDДлчЗаписиВБАзу);
       ////TODO ДАТ
       String ДатаДЛяОчисткиИВстсвкиИмениИПароль=     new GetMainDateForApp(context).getMainDateForApp();
            setNewPublicWitnSussecLogin.put("date_update", ДатаДЛяОчисткиИВстсвкиИмениИПароль);

            // TODO: 08.10.2024 Update or Insert  In table SuccessLogin PUBLIC ID

            //////todo САМА НЕ ПОСТРЕДВСТВЕНА ЗАПИС ДАННЫХ В ТАБЛИЦУ НАСТЙКИ СИТЕМЫ
            результатЗаписиНовогоПароляПользователявБазцуsuccesslogin =
                    new CoreBinessLogics(context).
                            wewillsetupanewPublicidaftersuccessfulsynchronizationSuccessLogin("successlogin",
                                    setNewPublicWitnSussecLogin,ПолученинныйПубличныйIDДлчЗаписиВБАзу);




            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    "результатЗаписиНовогоПароляПользователявБазцуsuccesslogin " + результатЗаписиНовогоПароляПользователявБазцуsuccesslogin+
                    " ПолученинныйПубличныйIDДлчЗаписиВБАзу " +ПолученинныйПубличныйIDДлчЗаписиВБАзу);
            // TODO: 08.10.2024  
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return результатЗаписиНовогоПароляПользователявБазцуsuccesslogin;
    }
}
