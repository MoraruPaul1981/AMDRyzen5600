package com.dsy.dsu.TabelsApp.Tabel.Single.model;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

public class BunesslogicSingleTabel {

    Context context;

    public BunesslogicSingleTabel(Context context) {
        this.context = context;
    }


    public Integer getemployeeHoursCounting(Cursor курсор_ЗагружаемТабеляСозданный) {
        Integer СуммаЧасов = 0;
        try{
            if (курсор_ЗагружаемТабеляСозданный.getCount()>0) {
                for (int ИндексДляИзмененияДней = 1; ИндексДляИзмененияДней < 32; ИндексДляИзмененияДней++) {
                    int ИндексЧассыСотрудника = курсор_ЗагружаемТабеляСозданный.getColumnIndex("d" + ИндексДляИзмененияДней);
                    if (  курсор_ЗагружаемТабеляСозданный.getType(ИндексЧассыСотрудника)==Cursor.FIELD_TYPE_INTEGER) {
                        int ЧассыСотрудника = курсор_ЗагружаемТабеляСозданный.getInt(ИндексЧассыСотрудника);
                        СуммаЧасов = СуммаЧасов + ЧассыСотрудника;
                        Log.d(this.getClass().getName(), "    СуммаЧасов " + СуммаЧасов);
                    }
                }
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " СуммаЧасов "+СуммаЧасов );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return СуммаЧасов;
    }




}
