
package com.dsy.dsu.BusinessLogicForApps.Hilt.getSSLSocketFactory2;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.dsy.dsu.BusinessLogicForApps.Hilt.getSSLSocketFactory2.Businesslogic.TLSSocketFactoryTLS;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.util.Date;

import javax.inject.Singleton;
import javax.net.ssl.SSLSocketFactory;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;



@Module
@InstallIn(SingletonComponent.class)
public class ModulegetsslSocketFactory2 {
    @SuppressLint("SuspiciousIndentation")
    @QualifiergetsslSocketFactory2
    @Provides
    public SSLSocketFactory getsslSocketFactory2(@ApplicationContext Context context) {
        SSLSocketFactory sslSocketFactory2=null;
       try{
         sslSocketFactory2 = new TLSSocketFactoryTLS().TLSSocketFactoryTLS(context);

           Log.i(this.getClass().getName(), " Атоманически установкаОбновление ПО " +
                   Thread.currentThread().getStackTrace()[2].getMethodName() +
                   " время " + new Date().toLocaleString() + " sslSocketFactory2 " + sslSocketFactory2);

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return sslSocketFactory2;

    }

}

