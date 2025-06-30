package com.dsy.dsu.BusinessLogicForApps.CallNavigarlaout;

// TODO: 17.08.2023  Класс Для Компонента Для  Боковой Панели

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

public class CallNavigarlaout{
   private DrawerLayout navigator;

    private  android.os.Handler handlerDashBord;

    private Context context;

    public CallNavigarlaout(DrawerLayout navigator, Handler handlerDashBord, Context context) {
        this.navigator = navigator;
        this.handlerDashBord = handlerDashBord;
        this.context = context;
    }


    public void  методНастройкиБоковойпанели() {
        try{

            handlerDashBord.postDelayed(()->{
                navigator.openDrawer(Gravity.LEFT | Gravity.LEFT,true);

            },500);

            handlerDashBord.postDelayed(()->{
                if (  navigator.isDrawerOpen(GravityCompat.END | Gravity.LEFT)) {
                    navigator.closeDrawer(GravityCompat.END | Gravity.LEFT,true);
                }

            },1500);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }








}