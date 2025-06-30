package com.dsy.dsu.BusinessLogicForApps;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Class_Visible_Processing_Async {

    Context context;



    public Class_Visible_Processing_Async(Context context) {

        this.context =context;

    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    // TODO: 05.07.2021



    // TODO: 07.09.2021  метод визуальной синхронизации отображения прогресс бара////

    public int  ГенерируемПРОЦЕНТЫДляAsync(Integer Результат_Обновление_ИлиВставкиДанных,
                                             Integer ОбщееКоличествоСтрокВJSON) {
        int Проценты = 0;
        try{
                Log.d(CoreBinessLogics.class.getName(),
                        " Результат_Обновление_ИлиВставкиДанных  " + Результат_Обновление_ИлиВставкиДанных);
                Log.d(this.getClass().getName(), " ПроцентыДляВизуализацииИхПриСинхронизации" + Результат_Обновление_ИлиВставкиДанных + " ОбщееКоличествоСтрокВJSON " + ОбщееКоличествоСтрокВJSON);
                String ФиналПроцентыДляВизуализацииИхПриСинхронизации = null;
                //TODO считаем проценты от цифры
                Integer   ФинальныеПроценты=0;
                    Float ОбщееКоличествоСтрокВJSONfloat = Float.parseFloat(String.valueOf(ОбщееКоличествоСтрокВJSON));
                    Float Результат_Обновление_ИлиВставкиДанныхfloat = Float.parseFloat(String.valueOf(Результат_Обновление_ИлиВставкиДанных));
                    Float ФиналПроценты = (Результат_Обновление_ИлиВставкиДанныхfloat / ОбщееКоличествоСтрокВJSONfloat) * 100;     ///        ФиналПроценты = ( ОбщееКоличествоСтрокВJSONfloat *Результат_Обновление_ИлиВставкиДанныхfloat ) / 100;
                    Log.d(this.getClass().getName(), "  ФиналПроценты  нЕ ОБРАБОТАННЫЕ " + ФиналПроценты + "  Результат_Обновление_ИлиВставкиДанных " + Результат_Обновление_ИлиВставкиДанных);
                    if (ФиналПроценты < 100) {
                        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
                        DecimalFormatSymbols ЗонаПроцентовфОрмат = new DecimalFormatSymbols(Locale.US);//new Locale("ru")
                        DecimalFormat ФОрматированиеВидаПроцентов = new DecimalFormat("##", ЗонаПроцентовфОрмат);//#0.0"
                        Log.d(this.getClass().getName(), "  ФиналПроценты" + ФиналПроценты);
                        final String ФиналПроцентыВременно = ФОрматированиеВидаПроцентов.format(Math.ceil(ФиналПроценты));
                        Log.d(this.getClass().getName(), "  ФиналПроцентыВременно" + ФиналПроцентыВременно);
                        ФинальныеПроценты = 0;
                        ФинальныеПроценты = Integer.parseInt(ФиналПроцентыВременно);
                        Log.d(this.getClass().getName(), "  ФиналПроценты" + ФиналПроценты);
                    } else {
                        DecimalFormatSymbols ЗонаПроцентовфОрмат = new DecimalFormatSymbols(Locale.US);///new Locale("ru")
                        DecimalFormat ФОрматированиеВидаПроцентов = new DecimalFormat("###", ЗонаПроцентовфОрмат);
                        ///TODO переропределяем проценты
                        Log.d(this.getClass().getName(), "  ФиналПроценты" + ФиналПроценты);
                        // TODO: 14.10.2021 ЗамоПреобразование
                        final String ФиналПроцентыВременно = ФОрматированиеВидаПроцентов.format(ФиналПроценты);
                        Log.d(this.getClass().getName(), "  ФиналПроцентыВременно" + ФиналПроцентыВременно);
                        ФинальныеПроценты = 0;
                        ФинальныеПроценты = Integer.parseInt(ФиналПроцентыВременно);
                        Log.d(this.getClass().getName(), "  ФиналПроценты" + ФиналПроценты);
                        Log.d(this.getClass().getName(), "  ФинальныеПроценты" + ФинальныеПроценты);
                        if (ФинальныеПроценты >= 100 || ФинальныеПроценты >= 100.0) {
                            ////todo если процентов больше 100 %  уменьваем их принудтельно
                            ФиналПроценты = Float.parseFloat(String.valueOf(100));
                            Log.d(this.getClass().getName(), " ФиналПроценты" + ФиналПроценты);
                        }

                    }
                    Проценты = ФинальныеПроценты  ;
                    Log.d(this.getClass().getName(), " Проценты " + Проценты);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), CoreBinessLogics.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
        return  Проценты;
    }






}
