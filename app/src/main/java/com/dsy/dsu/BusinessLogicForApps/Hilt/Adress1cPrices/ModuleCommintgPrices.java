
package com.dsy.dsu.BusinessLogicForApps.Hilt.Adress1cPrices;


import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;




@Module
@InstallIn(SingletonComponent.class)
public class ModuleCommintgPrices {




    @Provides
    @QualifierCommintgPrices
    public String  getHiltCommintgPrices(@ApplicationContext Context context) {
        String  АдресСеврера1сДляgetFilePrices=new String() ;
        try {
            // TODO: 18.03.2024  релиз   Prices
       АдресСеврера1сДляgetFilePrices="http://uat.dsu1.ru:55080/dds/hs/jsonto1ccena/listofdocuments".trim();// TODO: 18.01.2024  RELEZ

     // TODO: 18.03.2024  дебаг  Prices
   // АдресСеврера1сДляgetFilePrices="http://192.168.3.10/dds_copy/hs/jsonto1ccena/listofdocuments".trim();// TODO: 18.01.2024  DEBUG

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " АдресСеврера1сДляgetFilePrices " + АдресСеврера1сДляgetFilePrices);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return АдресСеврера1сДляgetFilePrices;


    }
}
