package com.sous.backasync.businesslogic.dates;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Class_GenerationBack_Data {
    Context contextДляКлассаВремени;
    DateFormat dateFormat = null;//  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));//"yyyy-MM-dd HH:mm:ss.SSS"//"yyyy-MM-dd'T'HH:mm:ss'Z'"
    public Class_GenerationBack_Data(Context context) {
        contextДляКлассаВремени=context;
        dateFormat =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    public String ГлавнаяДатаИВремяОперацийСБазойДанных() {
        Date Дата = null;
        try {
            Дата = Calendar.getInstance().getTime();
            dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            Log.d(this.getClass().getName(), " ГЛАВНАЯ ДАТА ПРОГРАММЫ ДСУ-1 : " + dateFormat.format(Дата));
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            /*new RecordNewErros(contextДляКлассаВремени).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());*/
        }
        return dateFormat.format(Дата);
    }

    public String ГлавнаяДатаИВремяОперацийСБазойДанныхДОП() {
        Date Дата = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));//"yyyy-MM-dd HH:mm:ss.SSS"//"yyyy-MM-dd'T'HH:mm:ss'Z'"
        //  dateFormat.setTimeZone(TimeZone.getTimeZone("UTC-03:00"));
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        Log.d(this.getClass().getName(), " ГЛАВНАЯ ДАТА ПРОГРАММЫ ДСУ-1 : " + dateFormat.format(Дата));
        return dateFormat.format(Дата);
    }
}
