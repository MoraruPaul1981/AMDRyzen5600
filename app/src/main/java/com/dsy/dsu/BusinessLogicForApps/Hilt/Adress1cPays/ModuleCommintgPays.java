
package com.dsy.dsu.BusinessLogicForApps.Hilt.Adress1cPays;


import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class ModuleCommintgPays {


    @Provides
    @QualifierCommintgPays
    public StringBuilder  getHiltCommintgPays(@ApplicationContext Context context ) {
        String АдресСеврера1сДляgetFile=new String();
        try {

            // TODO: 18.03.2024 Дебаг
      //  АдресСеврера1сДляgetFile="http://192.168.3.10/dds_copy/hs/jsonto1c/listofdocuments".trim();// TODO: 18.01.2024 DEBUG

            // TODO: 18.03.2024  релиз
        АдресСеврера1сДляgetFile="http://uat.dsu1.ru:55080/dds/hs/jsonto1c/listofdocuments".trim();// TODO: 18.01.2024  REliz


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " АдресСеврера1сДляgetFile " + АдресСеврера1сДляgetFile);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  new StringBuilder(АдресСеврера1сДляgetFile) ;


    }




    @Provides
    @Singleton
    @QualifierCommintgPays2
    public StringBuilder  getHiltCommintgPays3(@ApplicationContext Context context ) {
        String АдресСеврера1сДляgetFile=new String();
        return  new StringBuilder(АдресСеврера1сДляgetFile) ;


    }


    @Provides
    @Singleton
    @QualifierCommintgPays4
    public StringBuilder  getHiltCommintgPays4(@ApplicationContext Context context ) {
        String АдресСеврера1сДляgetFile=new String();
        return  new StringBuilder(АдресСеврера1сДляgetFile) ;


    }

}
