package com.dsy.dsu.BusinessLogicForApps.Hilt.JbossAdrress.reliesadress;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.Hilt.JbossAdrress.intarfaces.HiltJbossBinessLogicIntarface;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

public class HiltJbossBinessLogic implements HiltJbossBinessLogicIntarface {


    /**
     *
     */
    @Override
    public LinkedHashMap<Integer,String>   selectenableforSslrequests(@NotNull SharedPreferences preferencesJboss, @NotNull Context context) {
        LinkedHashMap<Integer,String> getJbossPort= new LinkedHashMap();
        try{
            SharedPreferences.Editor editor = preferencesJboss.edit();


            // TODO: 18.03.2023 московский сервер
            getJbossPort.putIfAbsent(8888,"base.dsu1.ru");// TODO: <без SSL
            //getJbossPort.putIfAbsent(8888,"80.70.108.165");// TODO: <без SSL

            // TODO: 18.03.2023 debug сервер
        ///   getJbossPort.putIfAbsent( 8080,"192.168.3.4");//
            editor.putString("enablesll","http");
            editor.commit();
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  getJbossPort;
    }
}
