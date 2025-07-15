package com.dsy.dsu.TabelsApp.Templates.model;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.sous.backasync.launch.ModuleQuety;

public class BinesslogiсGetCursorTemplate implements  GetCursorTemplateIntarface {
    private Context context;
    public BinesslogiсGetCursorTemplate(Context context) {
        this.context = context;
    }


    @Override
        //todo метод получаем список всех созданных Шаблонов
   public Cursor getCompletesTemplates( ) {
        // TODO: 26.06.2025
        Cursor getaListofAllCreatedTemplates = null;
        try {
            // TODO: 14.05.2025
            String Текущаятаблицы = "templates";
            ModuleQuety moduleQuety = new ModuleQuety(context);
            getaListofAllCreatedTemplates = moduleQuety.getModuleQuery(Текущаятаблицы,
                    "   SELECT *  FROM   " + Текущаятаблицы + " AS D " +
                            "ORDER BY D.name_templates   ", null);
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " getaListofAllCreatedTemplates " + getaListofAllCreatedTemplates);
            // TODO: 09.07.2025
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getaListofAllCreatedTemplates;
    }

    /**
     * @return
     */
    @Override
    public Cursor getInseiderRowTemplate(@NonNull Long findUUID) {
        // TODO: 26.06.2025
        Cursor getaInseiderRowTemplates = null;
        try {
            // TODO: 14.05.2025
            String Текущаятаблицы = "fio_template";
            ModuleQuety moduleQuety = new ModuleQuety(context);
            getaInseiderRowTemplates = moduleQuety.getModuleQuery(Текущаятаблицы,
                    "   SELECT D.fio_uuid  FROM   " + Текущаятаблицы + " AS D " +
                            " WHERE  D.fio_template = '"+findUUID.toString()+"'   ORDER BY D.date_update  ", null);
            // TODO: 17.04.2023
            getaInseiderRowTemplates.moveToFirst();
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " getaInseiderRowTemplates " + getaInseiderRowTemplates);
            // TODO: 09.07.2025
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getaInseiderRowTemplates;
    }


    @Override
    public Cursor getInseiderRowFindProf(@NonNull Long findUUID) {
        // TODO: 26.06.2025
        Cursor getaInseiderRowTemplates = null;
        try {
            // TODO: 14.05.2025
            String Текущаятаблицы = "fio";
            ModuleQuety moduleQuety = new ModuleQuety(context);
            getaInseiderRowTemplates = moduleQuety.getModuleQuery(Текущаятаблицы,
                    "   SELECT D.prof  FROM   " + Текущаятаблицы + " AS D " +
                            " WHERE  D.uuid = '"+findUUID.toString()+"'   ORDER BY D.date_update  ", null);
            // TODO: 17.04.2023
            getaInseiderRowTemplates.moveToFirst();

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " getaInseiderRowTemplates " + getaInseiderRowTemplates);
            // TODO: 09.07.2025
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getaInseiderRowTemplates;
    }





    //TODO END CLASS
}
