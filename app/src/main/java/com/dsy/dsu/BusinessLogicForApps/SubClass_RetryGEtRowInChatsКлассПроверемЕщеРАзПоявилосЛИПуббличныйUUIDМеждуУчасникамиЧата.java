package com.dsy.dsu.BusinessLogicForApps;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.sous.backasync.launch.ModuleQuety;

import java.util.Date;

import javax.annotation.Nonnull;

// TODO: 10.02.2022  данный по д класс ПОВТОРНО ПРОВЕРЯЕТ НЕ ПОЯВИЛЬСЯ ЛИ МЕЖДУ УЧАСНИКАМИ ЧАТА ПУБЛИЧНЫЙ UUID ПРОЦЕСЕ ПЕРЕПИСКИ
public class SubClass_RetryGEtRowInChatsКлассПроверемЕщеРАзПоявилосЛИПуббличныйUUIDМеждуУчасникамиЧата {


    public Long МетодПовторноПроверетНеПовилосьЛиМеждеУчаникамиперепискиПубличныйUUID(
            @NonNull Context context,
            @Nonnull Long ПолученыйIDДляЧата,
            @Nonnull Integer ПубличныйIDДляФрагмента) {
        // TODO: 20.05.2025
        Long РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки = 0l;
        try {
            // TODO: 15.05.202
            String Текущаятаблицы = "view_tasks";
            ModuleQuety moduleQuety = new ModuleQuety(context);
            Cursor КурсорДанныеИщемНЕПоявильсяЛиПубличныйUUIDМеджуУчасникамиПереписки = moduleQuety.getModuleQuery(Текущаятаблицы,
                    " SELECT uuid_parent FROM chats   WHERE id_user= '" + ПубличныйIDДляФрагмента + "' " +
                    " AND  user_update='" + ПубличныйIDДляФрагмента + "'  ORDER BY  date_update  ASC ; ", null);

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " КурсорДанныеИщемНЕПоявильсяЛиПубличныйUUIDМеджуУчасникамиПереписки " + КурсорДанныеИщемНЕПоявильсяЛиПубличныйUUIDМеджуУчасникамиПереписки);
            if (КурсорДанныеИщемНЕПоявильсяЛиПубличныйUUIDМеджуУчасникамиПереписки.getCount() > 0) {
                КурсорДанныеИщемНЕПоявильсяЛиПубличныйUUIDМеджуУчасникамиПереписки.moveToFirst();
                РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки = КурсорДанныеИщемНЕПоявильсяЛиПубличныйUUIDМеджуУчасникамиПереписки.getLong(0);
            }
            КурсорДанныеИщемНЕПоявильсяЛиПубличныйUUIDМеджуУчасникамиПереписки.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки;
    }
}
    // TODO: 21.03.2022  для задачи  проверяем если межуд участиника переписка
