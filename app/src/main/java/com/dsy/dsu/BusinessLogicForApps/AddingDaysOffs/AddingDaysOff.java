package com.dsy.dsu.BusinessLogicForApps.AddingDaysOffs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.sous.backasync.launch.ModuleQuety;

import java.util.Date;


public class AddingDaysOff {
    Context context;
    public AddingDaysOff(Context context) {
        this.context =context;
        // TODO: 16.04.2025
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() );
    }


    /////////////////////////////
    // TODO: 24.05.2021 метод автоматической вставки выходных дней
    public Integer addingDaysOff(Long UUIDGeneratorINset, int ГодПриВставкеНовогоСотрудника, int МЕсяцПриВставкеНовогоСотрудника) {

        Integer РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель = 0;
        String  РезультатКакойРежимЗаписанвБазеВЫходныеДни=null;
        try {
            // TODO: 14.05.2025
            String Текущаятаблицы="SuccessLogin";
            ModuleQuety moduleQuety=new ModuleQuety(context);
            Cursor КурсорУзнаемСохраненыйРежимРаботыССетью= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT  D.mode_weekend FROM "+Текущаятаблицы+" AS D " ,null);
            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " КурсорУзнаемСохраненыйРежимРаботыССетью " +КурсорУзнаемСохраненыйРежимРаботыССетью);
            if (КурсорУзнаемСохраненыйРежимРаботыССетью.getCount() > 0) {
                КурсорУзнаемСохраненыйРежимРаботыССетью.moveToFirst();
                 РезультатКакойРежимЗаписанвБазеВЫходныеДни = КурсорУзнаемСохраненыйРежимРаботыССетью.getString(0);
                ///
                Log.d(context.getClass().getName(), " РезультатКакойРежимЗаписанвБазеВЫходныеДни  " + "--" +РезультатКакойРежимЗаписанвБазеВЫходныеДни);/////
            }

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " КурсорУзнаемСохраненыйРежимРаботыССетью " +КурсорУзнаемСохраненыйРежимРаботыССетью);

            // TODO: 24.05.2021 КОД ДЛЯ АВТОМАТИЧЕСКОГО ВЫСТАВЛЕНИЯ ВЫХОДНЫХ ДНЕЙ В ТАБЕЛЬ
            if (РезультатКакойРежимЗаписанвБазеВЫходныеДни.contentEquals("Включить")) {
                // TODO: 24.05.2021 вычисляем дни
                ContentValues КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные = new CoreBinessLogics(context)
                        .МетодВычисляемВыходныеДниПриСозданииНовогоТабеляАвтоРЕжим(context,
                                МЕсяцПриВставкеНовогоСотрудника , ГодПриВставкеНовогоСотрудника);
                // TODO: 24.05.2021 сама вставка  выходних дней
                Log.d(this.getClass().getName()," КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные " + КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные);

                // TODO: 25.03.2021 вставка табель
                РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель = new CoreBinessLogics(context)
                        .ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная("data_tabels",
                                КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные,
                                UUIDGeneratorINset,
                                "uuid");
                Log.d(this.getClass().getName()," РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель " + РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель;
    }
}
