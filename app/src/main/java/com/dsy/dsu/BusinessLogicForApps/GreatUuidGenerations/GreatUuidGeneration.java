package com.dsy.dsu.BusinessLogicForApps.GreatUuidGenerations;


import static java.util.Calendar.getInstance;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.sous.backasync.launch.ModuleQuety;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.Nonnull;

public class GreatUuidGeneration {
 private    Context context;

    public GreatUuidGeneration(@Nonnull  Context context) {
        this.context = context;
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    public Long greatUuidGeneration() {
        Long getUUID = 0l;
        try {
            if (context!=null) {
                // TODO ГЕНЕРАЦИЯ UUID ВВИДЕ ЦИФРЫ
                //"yyyyMMddHHmmssSSS" //"EEEEE MMMMM yyyy HH:mm:ss.SSSSSSZ"
                Date Дата = getInstance().getTime();
                // DateFormat dateFormat = new SimpleDateFormat("yyyyddMMHHmmssSS", new Locale("ru"));
                DateFormat dateFormat = new SimpleDateFormat("yyddMMHHmmssS", new Locale("ru"));
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
                String СгенерированоДатаДляUUIDШагПервый = dateFormat.format(Дата);
                Long СгенерированоДатаВТипеLong = Long.parseLong(СгенерированоДатаДляUUIDШагПервый);

                // TODO: 14.05.2025
                String Текущаятаблицы="successlogin";
                // TODO: 14.05.2025  получение данных
                ModuleQuety moduleQuety=new ModuleQuety(context);
                Cursor Курсор_Получаемsuccesslogin   =moduleQuety.getModuleQuery(Текущаятаблицы,
                        "  SELECT D.publicid  FROM "+Текущаятаблицы+" AS D  ORDER BY D.date_update DESC ; " , null);

                if(Курсор_Получаемsuccesslogin.getCount()>0){
                    Курсор_Получаемsuccesslogin.moveToFirst();
                    Integer   ПубличныйID =         Курсор_Получаемsuccesslogin.getInt(0);
                    Log.d(this.getClass().getName(), " ID  " + ПубличныйID);
                    // TODO: 28.06.2025
                    // TODO: 24.03.2021 ЕслиВубличногоНЕтТоНАходим ЕГо
                    if ( ПубличныйID >0) {
                        String  ПубличноеIDПолученныйИзСервлетаДляUUIDПромежуточный=String.valueOf(ПубличныйID);
                        String  СгенерированоДатаВТипеLongПромежуточный=String.valueOf(СгенерированоДатаВТипеLong);

                        // TODO: 02.08.2023  генерируем  UUID
                        String UUIDПромкжеточный = ПубличноеIDПолученныйИзСервлетаДляUUIDПромежуточный+СгенерированоДатаВТипеLongПромежуточный;
                        BigInteger UUIDПромежуточныйBig= new BigInteger(UUIDПромкжеточный);
                        getUUID=        UUIDПромежуточныйBig.longValue();
                        // TODO: 28.06.2025
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " getUUID " +getUUID);
                    }
                }
                Курсор_Получаемsuccesslogin.close();
            }
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " getUUID " +getUUID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // TODO: 06.09.2021  новый UUID
        return getUUID;
    }

}
