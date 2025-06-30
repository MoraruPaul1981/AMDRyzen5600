package com.dsy.dsu.BusinessLogicForApps;



import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.sous.backasync.launch.ModuleQuety;
import com.sous.backasync.launch.ModuleUpdating;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class VersionCurentTable {
    Context context;

    public VersionCurentTable(@NonNull Context context) {

        // TODO: 11.02.2025
        try {
            this.context = context;
                // TODO: 16.04.
                Log.d(context.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

            // TODO: 11.02.2025
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    //////////TODO SET Version


    // TODO: 19.11.2022  Записываем нову версию данных в текущем операции Runtime
    public Integer writingDataVersionAfterLocalInsertOrUpdate(@NotNull String Таблица ) {//versionserver
        Integer Результат_ПовышенаяВерсия = 0;
            try {
                String ТаблицаСистемная = "MODIFITATION_Client";
                Long VersionFromSqlServerGet = getVersionCurentTable(Таблица, context );
                String СгенерированованныйДата = new GetMainDateForApp(context).getMainDateForApp();
                ContentValues contentValuesДляПоднятияВерсии = new ContentValues();
                if (VersionFromSqlServerGet>0) {
                    // TODO: 01.07.2023  после выравниванию ДЛЯ СЕРВЕРА
                    // TODO: 01.07.2023  после выравниванию ДЛЯ КЛИЕНТА
                    contentValuesДляПоднятияВерсии.put("localversionandroid", СгенерированованныйДата);
                    contentValuesДляПоднятияВерсии.put("localversionandroid_version", VersionFromSqlServerGet);

                    // TODO: 12.04.2023 UPDATER PUBLIC ID
                    ModuleUpdating moduleUpdating = new ModuleUpdating(context);
                    // TODO: 03.02.2025 update new back
                    Результат_ПовышенаяВерсия=   moduleUpdating.getModuleUpdate(ТаблицаСистемная,contentValuesДляПоднятияВерсии,"name=?", new String[] {Таблица.toLowerCase()});

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  Результат_ПовышенаяВерсия " +Результат_ПовышенаяВерсия);

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            "  Результат_ПовышенаяВерсия   " + Результат_ПовышенаяВерсия);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        return Результат_ПовышенаяВерсия;
    }






    public Integer writingDataVersionAfterPost(@NotNull String Таблица, @NotNull   Long VersionFromSqlServerPost) {//versionserver
        Integer Результат_ПовышенаяВерсия = 0;
        try {
            String ТаблицаСистемная = "MODIFITATION_Client";
            String СгенерированованныйДата = new GetMainDateForApp(context).getMainDateForApp();
            ContentValues contentValuesДляПоднятияВерсии = new ContentValues();
            if (VersionFromSqlServerPost>0) {
                // TODO: 01.07.2023  после выравниванию ДЛЯ СЕРВЕРА
                contentValuesДляПоднятияВерсии.put("versionserveraandroid", СгенерированованныйДата);
                contentValuesДляПоднятияВерсии.put("versionserveraandroid_version", VersionFromSqlServerPost);

                // TODO: 01.07.2023  после выравниванию ДЛЯ КЛИЕНТА
               contentValuesДляПоднятияВерсии.put("localversionandroid", СгенерированованныйДата);
                contentValuesДляПоднятияВерсии.put("localversionandroid_version", VersionFromSqlServerPost);

                // TODO: 12.04.2023 UPDATER PUBLIC ID
                ModuleUpdating moduleUpdating = new ModuleUpdating(context);
                // TODO: 03.02.2025 update new back
                Результат_ПовышенаяВерсия=   moduleUpdating.getModuleUpdate(ТаблицаСистемная,contentValuesДляПоднятияВерсии,"name=?", new String[] {Таблица.toLowerCase()});

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        "  Результат_ПовышенаяВерсия   " + Результат_ПовышенаяВерсия);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Результат_ПовышенаяВерсия;
    }



    public Integer writingDataVersionAfterGet(@NotNull String Таблица ) {//versionserver
        Integer Результат_ПовышенаяВерсия = 0;
        try {
            String ТаблицаСистемная = "MODIFITATION_Client";
            SQLiteQueryBuilder      SQLBuilderВерсияДанныхСистемнаяТАблицы = new SQLiteQueryBuilder();
            // TODO: 22.11.2021  ПОСЛЕ УСПЕШНОЙ ОПЕРАЦИИ ПОДТВЕРЖДАЕМ ТРАНЗАУЙИЮ
            Long VersionFromSqlServerGet = getVersionCurentTable(Таблица, context );
            // TODO: 22.11.2021  ПОСЛЕ УСПЕШНОЙ ОПЕРАЦИИ ПОДТВЕРЖДАЕМ ТРАНЗАУЙИЮ
            Log.d(this.getClass().getName(), "  VersionFromSqlServerGet   " + VersionFromSqlServerGet + " Таблица " + Таблица);
            String СгенерированованныйДата = new GetMainDateForApp(context).getMainDateForApp();
            ContentValues contentValuesДляПоднятияВерсии = new ContentValues();
            if (VersionFromSqlServerGet>0) {
                // TODO: 01.07.2023  после выравниванию ДЛЯ СЕРВЕРА
                contentValuesДляПоднятияВерсии.put("versionserveraandroid", СгенерированованныйДата);
                contentValuesДляПоднятияВерсии.put("versionserveraandroid_version", VersionFromSqlServerGet);
                // TODO: 01.07.2023  после выравниванию ДЛЯ КЛИЕНТА
        /*        contentValuesДляПоднятияВерсии.put("localversionandroid", СгенерированованныйДата);
                contentValuesДляПоднятияВерсии.put("localversionandroid_version", VersionFromSqlServerGet);*/

                // TODO: 12.04.2023 UPDATER PUBLIC ID
                ModuleUpdating moduleUpdating = new ModuleUpdating(context);
                // TODO: 03.02.2025 update new back
                Результат_ПовышенаяВерсия=   moduleUpdating.getModuleUpdate(ТаблицаСистемная,contentValuesДляПоднятияВерсии,"name=?", new String[] {Таблица.toLowerCase()});
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        "  Результат_ПовышенаяВерсия   " + Результат_ПовышенаяВерсия);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return Результат_ПовышенаяВерсия;

    }













    //////////TODO GET Version

    // TODO:MAX cURRENT table
    public Long getVersionCurentTable(@NotNull String Текущаятаблицы,
                                      @NotNull Context context) {
        Long  АнализВерсииMAXCurrentTable=0l;
        ModuleQuety moduleQuety=new ModuleQuety(context);
            try   (Cursor КурсоАнализVersionCurrentTable   =moduleQuety.getModuleQuery(Текущаятаблицы,
                           " SELECT MAX( current_table  ) " +
                                   "AS MAX_R  FROM    " +  Текущаятаблицы.trim()+";" , null);) {
                if (КурсоАнализVersionCurrentTable!=null) {
                    if(КурсоАнализVersionCurrentTable.getCount()>0){
                        КурсоАнализVersionCurrentTable.moveToFirst();
                        Integer  ИндексГдеСтолбикМах=КурсоАнализVersionCurrentTable.getColumnIndex("MAX_R");
                        АнализВерсииMAXCurrentTable=КурсоАнализVersionCurrentTable.getLong(ИндексГдеСтолбикМах);
                    }
                }
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " АнализВерсииMAXCurrentTable  " + АнализВерсииMAXCurrentTable + "  Текущаятаблицы" + Текущаятаблицы);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        return  АнализВерсииMAXCurrentTable;
    }



    // TODO:MAX cURRENT table
    @SuppressLint("SuspiciousIndentation")
    public Long upVersionCurentTable(@NotNull String Текущаятаблицы) {

        Long  ПовышенняВерсия=0l;

        ModuleQuety moduleQuety=new ModuleQuety(context);
        try   (Cursor Курсор_АнализMODIFITATION_Client   =moduleQuety.getModuleQuery(Текущаятаблицы,
                " SELECT *  FROM " +
                        "  MODIFITATION_Client  WHERE  name = '"+Текущаятаблицы+"' ", null);) {
                if (Курсор_АнализMODIFITATION_Client!=null) {
                    if(Курсор_АнализMODIFITATION_Client.getCount()>0 ){
                        Курсор_АнализMODIFITATION_Client.moveToFirst();
                       int getNameindex=Курсор_АнализMODIFITATION_Client.getColumnIndex("localversionandroid_version");
                        ПовышенняВерсия=(Long) Курсор_АнализMODIFITATION_Client.getLong(getNameindex);

                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"localversionandroid_version");

                        // TODO: 16.11.2023 еще обна проверка
                        if(ПовышенняВерсия>0){
                            ПовышенняВерсия++;
                        }else {
                            ПовышенняВерсия=1l;
                        }

                    }
                }
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " ПовышенняВерсия  " + ПовышенняВерсия + "  Текущаятаблицы" + Текущаятаблицы);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        return  ПовышенняВерсия;
    }



    // TODO: 05.04.2024 курсор получчаем весрию всех жанных на андройде для дальншего сопоствалвения
    public Cursor getVersionMODIFITATION_ClientTable(@NonNull String ИмяТаблицыОтАндройда_Локальноая) throws ExecutionException, InterruptedException {
        Cursor AllVersionAndroidLocalSQlite = null;
        try{
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + "MODIFITATION_Client" + "");
            ContentResolver contentResolver=context. getContentResolver();
            // TODO: 05.04.2024 get Curcour all  version local Android Sqlite
            AllVersionAndroidLocalSQlite =      contentResolver.query(uri,new String[]{},
                    new String(" SELECT *  FROM    MODIFITATION_Client   where name = ?    "),
                    new String[]{String.valueOf(ИмяТаблицыОтАндройда_Локальноая)},null);///   "  //// SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?
            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+
                    " AllVersionAndroidLocalSQlite " +AllVersionAndroidLocalSQlite);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return AllVersionAndroidLocalSQlite;
    }

}
