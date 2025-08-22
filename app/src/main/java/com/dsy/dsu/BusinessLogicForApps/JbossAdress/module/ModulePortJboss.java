package com.dsy.dsu.BusinessLogicForApps.JbossAdress.module;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossLinktoDebug;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossHilt.intarfaces.HiltJbossBinessLogicIntarface;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossHilt.intarfaces.QualifierPortJboss;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossLinktoRelease;
import com.dsy.dsu.CoreApp.Apps.SettingsCoreApp.Model.SLLBenessLogicMode;

import java.util.LinkedHashMap;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;



@Module
@InstallIn(SingletonComponent.class)
@SuppressLint("SuspiciousIndentation")
@Named
public class ModulePortJboss {

    @Provides
    @QualifierPortJboss
    public  LinkedHashMap<Integer,String> getHiltPortJboss(@ApplicationContext Context context) {
        LinkedHashMap<Integer,String> getJbossPort= new LinkedHashMap();
        try {
            SharedPreferences     preferencesJboss = context.getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
         String   getModeTrasport=new SLLBenessLogicMode(context).getModeSLL();


            // TODO: 28.05.2025 Release Trasport
         switch (getModeTrasport){
             case "http":
                 // TODO: 28.05.2025 release
                 //HiltJbossBinessLogicIntarface     hiltJbossBinessLogicIntarfaceRelease=new JbossLinktoRelease();
                 HiltJbossBinessLogicIntarface     hiltJbossBinessLogicIntarfaceRelease=new JbossLinktoDebug();
                 // TODO: 06.10.2024 ответ  сам адрес с чем подкбчаться
                 //getJbossPort=   hiltJbossBinessLogicIntarfaceRelease.selectingLinkJbossAdress(preferencesJboss,context,getModeTrasport,8888); //TODO РЕЛИЗ  на Обычный СЕРВЕР JBOSS
                 getJbossPort=   hiltJbossBinessLogicIntarfaceRelease.selectingLinkJbossAdress(preferencesJboss,context,getModeTrasport,8080); //TODO РЕЛИЗ  на Обычный СЕРВЕР JBOSS DEBUG
                 break;
             case "https":
                 // TODO: 28.05.2025 release
               //  HiltJbossBinessLogicIntarface     hiltJbossBinessLogicIntarfaceReleaseSSL=new JbossLinktoRelease();
                 HiltJbossBinessLogicIntarface     hiltJbossBinessLogicIntarfaceReleaseSSL=new JbossLinktoDebug(); //TODO DEBUG
                 // TODO: 06.10.2024 ответ  сам адрес с чем подкбчаться
                 getJbossPort=   hiltJbossBinessLogicIntarfaceReleaseSSL.selectingLinkJbossAdress(preferencesJboss,context,getModeTrasport,8890); //TODO РЕЛИЗ на SSL СЕРВЕР JBOSS
                 break;
         }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " getJbossPort " + getJbossPort+ "getModeTrasport " +getModeTrasport);


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " getJbossPort " + getJbossPort+ "getModeTrasport " +getModeTrasport);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getJbossPort;

    }






}


