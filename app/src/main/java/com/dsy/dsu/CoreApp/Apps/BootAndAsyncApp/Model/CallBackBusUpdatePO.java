package com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;


import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.EventsBus.MessageEvensBusUpdatePO;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

public class CallBackBusUpdatePO {
    protected  Context context;
    public CallBackBusUpdatePO(Context context) {
        this.context = context;
    }
    public void callbackEvensBusUpdatePO(@NonNull    Intent intentComunications) {
        try{
            EventBus.getDefault().post(new MessageEvensBusUpdatePO(intentComunications));
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


}
