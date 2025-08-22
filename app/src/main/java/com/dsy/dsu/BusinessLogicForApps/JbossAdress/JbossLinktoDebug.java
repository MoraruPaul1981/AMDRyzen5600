package com.dsy.dsu.BusinessLogicForApps.JbossAdress;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossHilt.intarfaces.HiltJbossBinessLogicIntarface;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

public class JbossLinktoDebug implements HiltJbossBinessLogicIntarface {


    /**
     *
     */
    @Override
    public LinkedHashMap<Integer,String> selectingLinkJbossAdress(@NotNull SharedPreferences preferencesJboss,
                                                                  @NotNull Context context ,
                                                                  @NotNull  String   getModeTrasport,
                                                                  @NotNull  Integer  getPost) {
        LinkedHashMap<Integer,String> getJbossPort= new LinkedHashMap();
   try{
       SharedPreferences.Editor editor = preferencesJboss.edit();
       // TODO: 18.03.2023 debug сервер
      getJbossPort.putIfAbsent( getPost,"192.168.50.92");//
       editor.putString("enablesll",getModeTrasport);
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
