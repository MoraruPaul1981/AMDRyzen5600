package com.dsy.dsu.BusinessLogicForApps;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForApprove;


public class SubClass_Starting_ЗапускДЛяСогласования {
    Context context;

    // TODO: 24.03.2022
    Bundle bundleДляПередачиВСлужбуСогласования;

    public SubClass_Starting_ЗапускДЛяСогласования(Context context) {
        // TODO: 03.03.2022
        this.context = context;
        // TODO: 26.03.2022
        bundleДляПередачиВСлужбуСогласования = new Bundle();

    }


    // TODO: 03.03.2022



// TODO: 26.03.2022 код для ЗАДАНИЯ КОТОРЫЙ СРАБАТЫВАЕТ КОГДАНА САМО УВЕДОМЛЕНИЯ ЗАДАНИЕ НАЖАЛИ
    // TODO: 03.03.2022

    public PendingIntent МетодЗапускаСменыСтатусаОтказИилиСогласованияЧерезPendingIntent(
            String PROCESS_IDСогласования,
            String ПолученныйНомерДокументаСогласования,
            Integer ПередаемСтатусСогласования) {
        PendingIntent ЗапускКОдаЧтоПОльзовательЗадачаВыполнилОтказ = null;
        try {
            ///////TODO запускаем смены стануса задачи черезе PendingIntent
            Log.d(context.getClass().getName(), "PROCESS_IDСогласования " + PROCESS_IDСогласования +
                    " ПолученныйНомерДокументаСогласования " + ПолученныйНомерДокументаСогласования
                    + "  ПередаемСтатусСогласования " + ПередаемСтатусСогласования );
            PackageManager pm = context.getPackageManager();
// TODO: 17.11.2021 БЛОК КОДА РЕАЛИЗАЦИЯ БУДУШЕГО ЗАПУСКА ПРИ НАЖАТИИ НА УВЕДОСЛЕНИЕ ИЛИ НА КНОПКИ ЗАПУСКАЕТ С УВЕДОМЛЕНИЯ РАЗЛИЧНЫЕ ДЕЙСТВИЯ
            Intent notificationIntentДляЗадачиОтказ;
            notificationIntentДляЗадачиОтказ = new Intent(context, ServiceForApprove.class);
            notificationIntentДляЗадачиОтказ.setAction( "ЗапускаемСогласованиеОтказИлилУспешное");
            notificationIntentДляЗадачиОтказ.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            bundleДляПередачиВСлужбуСогласования.putInt("PROCESS_IDСогласования", Integer.parseInt(PROCESS_IDСогласования));
            bundleДляПередачиВСлужбуСогласования.putString("ПолученныйНомерДокументаСогласования", ПолученныйНомерДокументаСогласования);
            bundleДляПередачиВСлужбуСогласования.putInt("ПередаемСтатусСогласования", ПередаемСтатусСогласования);
            notificationIntentДляЗадачиОтказ.putExtras(bundleДляПередачиВСлужбуСогласования);
            Log.d(context.getClass().getName(), "PROCESS_IDСогласования "
                    + PROCESS_IDСогласования + "  ПолученныйНомерДокументаСогласования " + ПолученныйНомерДокументаСогласования+
                     " ПередаемСтатусСогласования " +ПередаемСтатусСогласования);
            if (notificationIntentДляЗадачиОтказ.resolveActivity(pm) != null) {
                ЗапускКОдаЧтоПОльзовательЗадачаВыполнилОтказ = PendingIntent.getService(context,
                        7, notificationIntentДляЗадачиОтказ,
                        PendingIntent.FLAG_MUTABLE | PendingIntent.FLAG_UPDATE_CURRENT); //PendingIntent.FLAG_UPDATE_CURRENT
                // TODO: 17.11.2021
                /// ЗапускКОдаЧтоПОльзовательОзнаомленсЗаданием.send();
                bundleДляПередачиВСлужбуСогласования.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(context.getClass().getName(), " Стоп СЛУЖБА СЛУЖБАService_Notifications ДЛЯ ЧАТА  onDestroy() Exception ");

        }
        return ЗапускКОдаЧтоПОльзовательЗадачаВыполнилОтказ;
    }





}
