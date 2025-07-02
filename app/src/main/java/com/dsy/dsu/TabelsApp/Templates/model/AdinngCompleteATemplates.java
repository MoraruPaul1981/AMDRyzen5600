package com.dsy.dsu.TabelsApp.Templates.model;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

class AdinngATemplatesForTabel implements  AdinngATemplatesInterface {
   private Context context;


    public AdinngATemplatesForTabel(Context context) {
        this.context = context;
    }

    /**
     * @param bundleAdding
     * @return
     */
    @Override
    public Long launchAdinng(@NotNull Bundle bundleAdding) {
        // TODO: 02.07.2025
        Long launchAdinng=0l;
    try{






        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " launchAdinng " +launchAdinng);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        return launchAdinng;
    }


    //TODO END CLASS
}















class AdinngInsideTemplate implements  AdinngATemplatesInterface {
    private Context context;


    public AdinngInsideTemplate(Context context) {
        this.context = context;
    }

    @Override
    public Long launchAdinng(@NotNull Bundle bundleAdding) {
        return null;
    }


    //TODO END CLASS
}












    interface   AdinngATemplatesInterface{

    Long launchAdinng(@NotNull Bundle  bundleAdding);

    }


