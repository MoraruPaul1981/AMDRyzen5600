package com.dsy.dsu.BusinessLogicForApps.CalculateChasov;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.sous.backasync.launch.ModuleQuety;

public class SubClasssCuclulatesHours {

private Context context;

    public SubClasssCuclulatesHours(Context context) {
        this.context = context;
    }

    //TODO Метод ПОДЧСЧЕТА ЧАСОВ ПО ВСЕМ ТАБЕЛЯМ СРАЗУ
    private Integer  методПодстчетЧасовФИО(Long finalПолученныйUUID) {
        Integer     ЧасыСотрудникаТекущегоТабеля=0; //TODO РЕзультат
        try {
            // TODO: 14.05.2025
            String Текущаятаблицы="vietabel";
            ModuleQuety moduleQuety=new ModuleQuety(context);
            Cursor КурсорПосчётЧасов=   moduleQuety.getModuleQuery(Текущаятаблицы,"   SELECT *  FROM   "+Текущаятаблицы+" AS D " +
                    "  WHERE   D.uuid='"+String.valueOf(finalПолученныйUUID)+"'  AND D.fio IS NOT NULL   ORDER BY D.fio  LIMIT   1  " ,null);
            // TODO: 09.06.2025
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


            //TODO Считаем Сумму часов по всем табелям
            ЧасыСотрудникаТекущегоТабеля = МетодПосчётаЧасовПоСотрудникуДляЗагрузкиСотрудников(КурсорПосчётЧасов);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ЧасыСотрудникаТекущегоТабеля " +ЧасыСотрудникаТекущегоТабеля);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ЧасыСотрудникаТекущегоТабеля;

    }



    @SuppressLint("Range")
    protected int МетодПосчётаЧасовПоСотрудникуДляЗагрузкиСотрудников(Cursor курсор_ЗагружаемТабеляСозданный) {
        int СуммаЧасов = 0;
        try{
            do {
                for (int ИндексДляИзмененияДней = 1; ИндексДляИзмененияДней < 32; ИндексДляИзмененияДней++) {
               int ЧассыСотрудника = курсор_ЗагружаемТабеляСозданный.getInt(курсор_ЗагружаемТабеляСозданный.getColumnIndex("d" + ИндексДляИзмененияДней));
                    СуммаЧасов = СуммаЧасов + ЧассыСотрудника;
                    Log.d(this.getClass().getName(), "    СуммаЧасов " + СуммаЧасов);
                }///TODO END FOR  ПО СТОЛБЦАМ БЕЖИМ
            } while (курсор_ЗагружаемТабеляСозданный.moveToNext());
            // TODO: 09.06.2025
            курсор_ЗагружаемТабеляСозданный.close();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " курсор_ЗагружаемТабеляСозданный " +курсор_ЗагружаемТабеляСозданный);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return СуммаЧасов;
    }


}
