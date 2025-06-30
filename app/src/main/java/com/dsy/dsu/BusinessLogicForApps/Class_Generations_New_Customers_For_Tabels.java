package com.dsy.dsu.BusinessLogicForApps;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.sous.backasync.launch.ModuleQuety;

import java.util.Date;


public class Class_Generations_New_Customers_For_Tabels {

    Context context;
    ///


    public Class_Generations_New_Customers_For_Tabels(Context context) {
        this.context =context;
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  );
    }


    // TODO: 22.09.2021   ---launch   fio


    // TODO: 26.03.2021 финальная вствка данных новго сотружника

    public Integer МетодЗаписиСозданогоСотрудникаВБазуПоТаблицы_ФИО(ContentValues АдаптерДляСозданиеНовогоСотрудаТАблицаФИО,
                                                                    Activity activity) {
        ///todo САМА ВСТВКА ТАБЛИЦА ФИО
        Integer getcreatingAnewEmployee = 0;
        try {
            String ТекущаяТаблицаОбработки = "fio";
            // TODO: 25.03.2021 вставка фио
            getcreatingAnewEmployee = new CoreBinessLogics(activity).
                    ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНовогоСотрудникаУниверсальная(ТекущаяТаблицаОбработки,
                            АдаптерДляСозданиеНовогоСотрудаТАблицаФИО  );

            // TODO: 30.01.2022 Сообщеам Observer что изменилибьс данные в адаптере AdapterCursor
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getcreatingAnewEmployee"
                    +getcreatingAnewEmployee);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(activity).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  getcreatingAnewEmployee;
    }
























    // TODO: 22.09.2021  -----data_tabels




// TODO: 26.03.2021 финальная вствка данных новго сотружника

    public Long МетодЗаписиСозданогоСотрудникаВБазуПоТаблицы_Дата_Табеля(
           @NonNull ContentValues АдаптерДляСозданиеНовогоСотрудаТАблицаТабель
            , @NonNull Activity activity,
           @NonNull int ГодПриВставкеНовогоСотрудника
            , @NonNull int МЕсяцПриВставкеНовогоСотрудника,
           @NonNull  Long UUIDgenetarForData_tabels) {
        // TODO: 15.05.2025
        long РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель = 0;
        try{
            String ТекущаяОбрабатываемаяТаблица="data_tabels";
            String РезультатКакойРежимЗаписанвБазеВЫходныеДни=null;
            // TODO: 25.03.2021 вставка табель
            РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель = new CoreBinessLogics(activity).
                    ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНовогоСотрудникаУниверсальная(ТекущаяОбрабатываемаяТаблица,
                            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель  );

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель " +РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель);

            if (РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель>0) {
           // TODO: 14.05.2025
                String Текущаятаблицы="SuccessLogin";
                ModuleQuety moduleQuety=new ModuleQuety(context);
                Cursor КурсорУзнаемСохраненыйРежимРаботыССетью= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT D.mode_weekend FROM "+Текущаятаблицы+" AS D" ,null);
                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " КурсорУзнаемСохраненыйРежимРаботыССетью " +КурсорУзнаемСохраненыйРежимРаботыССетью);

                if (КурсорУзнаемСохраненыйРежимРаботыССетью.getCount() > 0) {
                    КурсорУзнаемСохраненыйРежимРаботыССетью.moveToFirst();
                    РезультатКакойРежимЗаписанвБазеВЫходныеДни = КурсорУзнаемСохраненыйРежимРаботыССетью.getString(0);
                    Log.d(activity.getClass().getName(), " РезультатКакойРежимЗаписанвБазеВЫходныеДни  " + "--" +РезультатКакойРежимЗаписанвБазеВЫходныеДни);/////
                // TODO: 24.05.2021 КОД ДЛЯ АВТОМАТИЧЕСКОГО ВЫСТАВЛЕНИЯ ВЫХОДНЫХ ДНЕЙ В ТАБЕЛЬ
                if (РезультатКакойРежимЗаписанвБазеВЫходныеДни.contentEquals("Включить")) {
                    ContentValues КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные= new CoreBinessLogics(activity). МетодВычисляемВыходныеДниПриСозданииНовогоТабеляАвтоРЕжим(activity,
                            ГодПриВставкеНовогоСотрудника,МЕсяцПриВставкеНовогоСотрудника);
                    Log.w(activity.getClass().getName(), " КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные  " + "--"
                            +КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные);/////
                        // TODO: 25.03.2021 вставка табель
                    Integer          РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель  = new CoreBinessLogics(activity)
                            .ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(ТекущаяОбрабатываемаяТаблица,
                                КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные,
                                UUIDgenetarForData_tabels,
                                "uuid");

                    Log.d(this.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + " РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель " +РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель);

                }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(activity).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель;
    }

}//TODO END CLASS
