package com.dsy.dsu.BusinessLogicForApps;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.sous.backasync.launch.ModuleQuety;

import java.util.Date;


public class SubClassGET_FIO {
    Context context;
    public SubClassGET_FIO(Context context) {

        this.context = context;

        Log.d(context.getClass().getName(), "context " + context);
    }

    public String МетодПолучениеФИОНАОснованииIDВыбранногоСотрудника(int ПуличныйIdДляВычисленияКтоНаписал) {
        // TODO: 16.05.2025
        StringBuffer ПолученыйФИОIDДляЧата = new StringBuffer();
        try {
            // TODO: 14.05.2025
            String Текущаятаблицы="Chat_Users";
            ModuleQuety moduleQuety=new ModuleQuety(context);
            Cursor КурсорДанныеДляКонтактовФИОЧата     = moduleQuety.getModuleQuery(Текущаятаблицы," SELECT D.name  FROM "+Текущаятаблицы+" AS D" +
                    "  WHERE D._id =   '"+ПуличныйIdДляВычисленияКтоНаписал+"'  " ,null);

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " КурсорДанныеДляКонтактовФИОЧата " +КурсорДанныеДляКонтактовФИОЧата);

            //////TODO resultat
            if (КурсорДанныеДляКонтактовФИОЧата.getCount() > 0) {
                КурсорДанныеДляКонтактовФИОЧата.moveToFirst();
                int ИндексФИо = КурсорДанныеДляКонтактовФИОЧата.getColumnIndex("name");
                // TODO: 08.09.2021  цикл
                do {
                    ПолученыйФИОIDДляЧата.append(КурсорДанныеДляКонтактовФИОЧата.getString(ИндексФИо).trim()).append(",").append("\n");
                    Log.d(context.getClass().getName(), "ПолученыйФИОIDДляЧата " + ПолученыйФИОIDДляЧата);
                    // TODO: 30.06.2021 выход
                    if (ПолученыйФИОIDДляЧата.length() > 0) {
                        Log.d(context.getClass().getName(), "ПолученыйФИОIDДляЧата " + ПолученыйФИОIDДляЧата);
                        break;
                    }
                } while (КурсорДанныеДляКонтактовФИОЧата.moveToNext());
                // TODO: 22.07.2021  заполнили всеми фио
                ПолученыйФИОIDДляЧата.setLength(ПолученыйФИОIDДляЧата.length() - 2);
            }
            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " КурсорДанныеДляКонтактовФИОЧата " +КурсорДанныеДляКонтактовФИОЧата);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), context.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return ПолученыйФИОIDДляЧата.toString();
    }
}
