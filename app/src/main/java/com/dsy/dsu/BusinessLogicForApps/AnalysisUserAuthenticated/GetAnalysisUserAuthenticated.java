package com.dsy.dsu.BusinessLogicForApps.AnalysisUserAuthenticated;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class GetAnalysisUserAuthenticated {

    Context context;

    public GetAnalysisUserAuthenticated(Context context) {
        this.context = context;
    }


    @SuppressLint("Range")
    public Boolean  analysisUserAuthenticated(  @NonNull Integer permissibledaysofwork) {
        Boolean getanalysisUserAuthenticated=false;
        try {

            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + "successlogin" + "");
            ContentResolver contentResolver=context. getContentResolver();
            try(  Cursor cursoranalysisUserAuthenticated = contentResolver.query(uri,new String[]{},
                    new String(" SELECT *  FROM    successlogin   ORDER BY id  LIMIT   1  "),
                    new String[]{},null);) {
                if (cursoranalysisUserAuthenticated.getCount() > 0) {/////ПРОВЕРЯЕМ ЕСЛИ ПО ДАННОМУ ID UUID ЗАПОЛНЕ ЛИ ОН
                    cursoranalysisUserAuthenticated.moveToFirst();
              String     success_users =
                            cursoranalysisUserAuthenticated.getString(cursoranalysisUserAuthenticated.getColumnIndex("success_users")).trim();
                    String     success_login =
                            cursoranalysisUserAuthenticated.getString(cursoranalysisUserAuthenticated.getColumnIndex("success_login")).trim();
                    String      date_update =
                            cursoranalysisUserAuthenticated.getString(cursoranalysisUserAuthenticated.getColumnIndex("date_update")).trim();

                    Integer   ПолученныйПубличныйID= cursoranalysisUserAuthenticated.getInt(cursoranalysisUserAuthenticated.getColumnIndex("id"));

                    Log.d(context.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+
                            "  success_users  " + success_users + "  " +
                            "    success_login  " + success_login + " date_update " + date_update);

                    // TODO: 13.08.2023 дата из табции
                    Date ДатаSucceslogin =
                            new android.icu.text.SimpleDateFormat("yyyy-MM-dd",
                                    new Locale("ru")).parse(date_update);//TODO "2023-08-01 19:00:59.781"

                    Log.d(this.getClass().getName(), "  ДатаSucceslogin  " + ДатаSucceslogin);


                    // TODO: 13.08.2023 Дата NOW !!!!!
                    Date ДатаNOW = Calendar.getInstance().getTime();
                    DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));//"yyyy-MM-dd'T'HH:mm:ss'Z'
                    String ДатСегодняДатаNOW = dateFormat.format(ДатаNOW);
                    ДатаNOW = dateFormat.parse(ДатСегодняДатаNOW);
                    Log.d(this.getClass().getName(), "  ДатаNOW  " + ДатаNOW);


                    ////TODO само сравнивание дат на 7 дней назад
                    long РазницаМеждуДатамиNowИДатыИзБазы =
                            ДатаNOW.getTime()
                                    - ДатаSucceslogin.getTime(); //локальное сравнение дата из базы андройда и дат сегодня
                    ///////////
                    Integer    ФиналПолучаемРазницуМеждуДатами = Integer.parseInt("" + (TimeUnit.DAYS.convert(РазницаМеждуДатамиNowИДатыИзБазы, TimeUnit.MILLISECONDS)));

                    Log.d(this.getClass().getName(), "  ФиналПолучаемРазницуМеждуДатами  " + ФиналПолучаемРазницуМеждуДатами);
                    // TODO: 23.01.2024
                    if (      date_update != null && success_users != null && success_login != null
                            && ФиналПолучаемРазницуМеждуДатами < permissibledaysofwork  ) {
                        // TODO: 28.03.2025
                        getanalysisUserAuthenticated=true;
                    }
                }}
            // TODO: 28.04.2023 НЕт Анутифтикации Пароль
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+
                    "  GetAnalysisUserAuthenticated  " +getanalysisUserAuthenticated);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
        return  getanalysisUserAuthenticated;
    }






    // TODO: 31.03.2025 END CLASS
}
