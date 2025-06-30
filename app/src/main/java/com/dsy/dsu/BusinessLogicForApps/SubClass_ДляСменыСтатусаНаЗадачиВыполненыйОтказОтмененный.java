package com.dsy.dsu.BusinessLogicForApps;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;


import java.util.Date;

public class SubClass_ДляСменыСтатусаНаЗадачиВыполненыйОтказОтмененный {
    // TODO: 07.02.2022
    public Boolean МетодСменыСтатусаНаОзкомленныйЗадениеСамимПользователем(
            @NonNull Context context,
   @NonNull Long UUID_ПоКоторомуМыИИщменимСтатусОзнакомлнныйВТаблицыУведомления,
   @NonNull Integer ПередаемСтатусзадачи
, String ПримечанияОтКлинетаВыполнилИлиНетЗадачу) {
        // TODO: 07.02.2022
        Boolean РезультатСменыСтатусаНАОзнакомленый = false;
        try {
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() );

            // TODO: 07.02.2022
            String НазваниеТаблицыобработки = "data_notification";////notifications
            ContentValues contentValuesДляОбновленияСтатусаОзнакомлненый = new ContentValues();
            // TODO: 07.02.2022  увеличиваем верисю данных
            Long РезультатУвеличинаяВерсияДАныхЧата =
                    new VersionCurentTable(context).upVersionCurentTable(    НазваниеТаблицыобработки);
            contentValuesДляОбновленияСтатусаОзнакомлненый.put("current_table", РезультатУвеличинаяВерсияДАныхЧата);
            String СгенерированованныйДатаДляВставки = new GetMainDateForApp(context).getMainDateForApp();
            contentValuesДляОбновленияСтатусаОзнакомлненый.put("date_update", СгенерированованныйДатаДляВставки);
              // TODO: 07.02.2022  само зполение смены статуса
            contentValuesДляОбновленияСтатусаОзнакомлненый.put("status_write", ПередаемСтатусзадачи);
            contentValuesДляОбновленияСтатусаОзнакомлненый.put("callsback_note_task", ПримечанияОтКлинетаВыполнилИлиНетЗадачу);


            ///TODO ТОЛЬКО ЛОКАЛЬНОЕ ОБНОВЛЕНИЕ НА ТАБЕЛЕ В АКТИВИТИ
            Long РезультатЛокальногоОбновления_ОбновлениеСтатусОЗНАКОМЛЕННЫЙ = new CoreBinessLogics(context).
                    МетодЛокальноеОбновлениеВТабеле(contentValuesДляОбновленияСтатусаОзнакомлненый,
                            String.valueOf(UUID_ПоКоторомуМыИИщменимСтатусОзнакомлнныйВТаблицыУведомления),
                            context, НазваниеТаблицыобработки);
            Log.d(this.getClass().getName(), "  РезультатЛокальногоОбновления_ОбновлениеСтатусОЗНАКОМЛЕННЫЙ " + РезультатЛокальногоОбновления_ОбновлениеСтатусОЗНАКОМЛЕННЫЙ);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время " + new Date());

        }
        return РезультатСменыСтатусаНАОзнакомленый;
    }
}
