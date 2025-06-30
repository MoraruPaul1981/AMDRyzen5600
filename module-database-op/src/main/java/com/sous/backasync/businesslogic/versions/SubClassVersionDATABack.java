package com.sous.backasync.businesslogic.versions;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.sous.backasync.businesslogic.errors.RecordNewErroBack;
import com.sous.backasync.businesslogic.dates.Class_GenerationBack_Data;
import com.sous.backasync.launch.ModuleQuety;
import com.sous.backasync.launch.ModuleUpdating;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicLong;

public class SubClassVersionDATABack {
    // TODO: 03.02.2025
    private  Context context;


    public SubClassVersionDATABack( @NotNull Context context ) {
        this.context=context;
    }

    // TODO: 19.11.2022  МЕТОДД ЗАПИСЫВАЕМ ВЕРСИЮ ДАННЫХ В СИСТЕМНЦЮ ТАБЛИЦ ИЗ ТЕКУЩЕЙ ТАБЛИЦЫ
    public Integer upVersionMODIFITATION_ClientRemote(@NotNull String Таблица, @NotNull Context context ) {
        Integer Результат_ПовышенаяВерсия = 0;
        try {
            ModuleUpdating moduleUpdating = new ModuleUpdating(context);

            String ТаблицаСистемная = "MODIFITATION_Client";
            Long ВерсияДанныхПослеСинхрониазацииДляЗаписи = МетодАнализаВерсииCurrentTable(Таблица, context );
            // TODO: 22.11.2021  ПОСЛЕ УСПЕШНОЙ ОПЕРАЦИИ ПОДТВЕРЖДАЕМ ТРАНЗАУЙИЮ
            String СгенерированованныйДата = new Class_GenerationBack_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
            ContentValues contentValuesДляПоднятияВерсии = new ContentValues();


            if (ВерсияДанныхПослеСинхрониазацииДляЗаписи>0) {
                // TODO: 01.07.2023  после выравниванию ДЛЯ СЕРВЕРА
                contentValuesДляПоднятияВерсии.put("versionserveraandroid", СгенерированованныйДата);
                contentValuesДляПоднятияВерсии.put("versionserveraandroid_version", ВерсияДанныхПослеСинхрониазацииДляЗаписи);

                // TODO: 03.02.2025 update new back
                moduleUpdating.getModuleUpdate(ТаблицаСистемная,contentValuesДляПоднятияВерсии);

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        "  Результат_ПовышенаяВерсия   " + Результат_ПовышенаяВерсия);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
          /*  new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());*/
        }

        return Результат_ПовышенаяВерсия;

    }

    // TODO: 19.11.2022  МЕТОД ЗАПИСЫВАЕМ ВЕРСИЮ ДАННЫХ В СИСТЕМНУЮ ТАБЛИЦУ ВЕРСИЕ КОТОРАЯ ПРИШЛА ОТ СЕРВЕРА
    public Integer МетодVesrionFromSqlServerUPMODIFITATION_Client(@NotNull String Таблица,@NotNull Context context
            ,@NotNull Long ВерсияДанныхПослеСинхронизацииОтСервера) {
        Integer Результат_ПовышенаяВерсия = 0;
        try {
            ModuleUpdating moduleUpdating = new ModuleUpdating(context);

            String ТаблицаСистемная = "MODIFITATION_Client";
            String СгенерированованныйДата = new Class_GenerationBack_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();

            ContentValues contentValuesДляПоднятияВерсии = new ContentValues();
            if (ВерсияДанныхПослеСинхронизацииОтСервера>0) {
                // TODO: 01.07.2023  после выравниванию ДЛЯ СЕРВЕРА
                contentValuesДляПоднятияВерсии.put("versionserveraandroid", СгенерированованныйДата);
                contentValuesДляПоднятияВерсии.put("versionserveraandroid_version", ВерсияДанныхПослеСинхронизацииОтСервера);

                // TODO: 01.07.2023  после выравниванию ДЛЯ ЛОКАЛЬНАЯ
                contentValuesДляПоднятияВерсии.put("localversionandroid_version", ВерсияДанныхПослеСинхронизацииОтСервера);

                contentValuesДляПоднятияВерсии.put("localversionandroid", СгенерированованныйДата);

                // TODO: 03.02.2025 update new back

                moduleUpdating.getModuleUpdate(ТаблицаСистемная,contentValuesДляПоднятияВерсии);


                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        "  Результат_ПовышенаяВерсия   " + Результат_ПовышенаяВерсия);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
/*            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());*/
        }

        return Результат_ПовышенаяВерсия;

    }


    // TODO:MAX cURRENT table
    public Long МетодАнализаВерсииCurrentTable( @NotNull String Текущаятаблицы,
                                                @NotNull Context context) {
        Long  АнализВерсииMAXCurrentTable=0l;
        try  {
            ModuleQuety  moduleQuety=new ModuleQuety(context);
            Cursor getbackasyncQueryVersionTable   =moduleQuety.getModuleQuery(Текущаятаблицы,
                    " SELECT MAX ( current_table  ) " +
                            "AS MAX_R  FROM " +  Текущаятаблицы.trim()+"" , null);

            if(getbackasyncQueryVersionTable.getCount()>0){
                getbackasyncQueryVersionTable.moveToFirst();
                Integer  ИндексГдеСтолбикМах=getbackasyncQueryVersionTable.getColumnIndex("MAX_R");
                АнализВерсииMAXCurrentTable=getbackasyncQueryVersionTable.getLong(ИндексГдеСтолбикМах);
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " АнализВерсииMAXCurrentTable  " + АнализВерсииMAXCurrentTable + "  Текущаятаблицы" + Текущаятаблицы);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  АнализВерсииMAXCurrentTable;
    }



    // TODO:MAX cURRENT table
    @SuppressLint("SuspiciousIndentation")
    public Long upVersionCurentTable(@NotNull String Текущаятаблицы,
                                     @NotNull Context context) {

        AtomicLong ПовышенняВерсия=new AtomicLong(0l);
        try     {
            ModuleQuety  moduleQuety=new ModuleQuety(context);
            Cursor getbackasyncQueryUPVersionTable   =moduleQuety.getModuleQuery(Текущаятаблицы,
                    " SELECT *  FROM " +
                            "  MODIFITATION_Client  WHERE  name = '"+Текущаятаблицы+"' " , null);


            if(getbackasyncQueryUPVersionTable.getCount()>0 && getbackasyncQueryUPVersionTable!=null){
                getbackasyncQueryUPVersionTable.moveToFirst();
                String  ПовышенняВерсияMAXCurrddentTable=getbackasyncQueryUPVersionTable.getString(0);
                Long ПовышенняВерсияMODIFITATION_Client=(Long) getbackasyncQueryUPVersionTable.getLong(3);

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " ПовышенняВерсияMAXCurrddentTable  " + ПовышенняВерсияMAXCurrddentTable);

                // TODO: 16.11.2023 еще обна проверка
                Long ПовышенняВерсияMAXCurrentTable=   МетодАнализаВерсииCurrentTable(Текущаятаблицы,context);

                if(ПовышенняВерсияMODIFITATION_Client>ПовышенняВерсияMAXCurrentTable){

                    ПовышенняВерсия.set(ПовышенняВерсияMODIFITATION_Client.longValue());
                    ПовышенняВерсия.incrementAndGet();
                }else {

                    ПовышенняВерсия.set(ПовышенняВерсияMAXCurrentTable.longValue());
                    ПовышенняВерсия.incrementAndGet();
                }

            }else {
                ПовышенняВерсия.set(1);
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ПовышенняВерсия  " + ПовышенняВерсия + "  Текущаятаблицы" + Текущаятаблицы);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ПовышенняВерсия.get();
    }

}
