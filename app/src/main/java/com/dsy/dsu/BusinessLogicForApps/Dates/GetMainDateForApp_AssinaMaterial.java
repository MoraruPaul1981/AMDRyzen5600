package com.dsy.dsu.BusinessLogicForApps.Dates;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GetMainDateForApp_AssinaMaterial implements  GetMainDateForAppInterface {
    Context context;
    String ВходящаяДатаМатериалы;

    public GetMainDateForApp_AssinaMaterial(Context context, String ВходящаяДатаМатериалы) {
        this.ВходящаяДатаМатериалы=ВходящаяДатаМатериалы;
    }
    @Override
    public String getMainDateForApp() {
        String ФиналДата = null;
        try {
            Date    Дата = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", new Locale("ru"));
            dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            ФиналДата= dateFormat.format(Дата);
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " ФиналДата " +ФиналДата );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(context.getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return ФиналДата;
    }

}
