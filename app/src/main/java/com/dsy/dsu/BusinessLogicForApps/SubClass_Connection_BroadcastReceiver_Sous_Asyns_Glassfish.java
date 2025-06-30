package com.dsy.dsu.BusinessLogicForApps;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

public class SubClass_Connection_BroadcastReceiver_Sous_Asyns_Glassfish  {
    @NonNull
    public Integer МетодПолучениеяПубличногоID(Context context) {
        // TODO: 27.02.2022
        Integer ПубличныйIDДляФрагмента = 0;

        try {
            // TODO: 30.09.2021 МЕТОД ЗАПУСКА СИНХРОНИЗАЦИИ ЧАТА ПО РАСПИСАНИЮ , НЕ ВЗАВИСИМОСТИ ОТ СОЗДАВАЛ ЛИ СООБЩЕНИЕ ИЛИ НЕТ
            ПубличныйIDДляФрагмента = new GetttingPublicID().getttingPublicID(context);
            Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента);
            if (ПубличныйIDДляФрагмента == null) {
                // TODO: 03.02.2022
                ПубличныйIDДляФрагмента = 0;
            }
            // TODO: 27.02.2022
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context.getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            ;
            Log.e(context.getClass().getName(),
                    " ОШИБКА В public class SubClass_Connection_BroadcastReceiver_Sous_Asyns_Glassfish extends BroadcastReceiver_Sous_Asyns_Glassfish { " + " ОШИБКА ::" + e.toString());

        }
        /////
        return ПубличныйIDДляФрагмента;
    }
}
