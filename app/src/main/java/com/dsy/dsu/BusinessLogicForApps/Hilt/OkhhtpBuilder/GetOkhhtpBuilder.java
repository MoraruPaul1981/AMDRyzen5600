package com.dsy.dsu.BusinessLogicForApps.Hilt.OkhhtpBuilder;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Date;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;

public class GetOkhhtpBuilder implements  InGetOkhhtpBuilder {
    private Context context;


    public GetOkhhtpBuilder(@NotNull Context context) {
        this.context = context;
     }

    @Override
    public OkHttpClient.Builder getOkhhtpBuilder() {
        OkHttpClient.Builder builderDefault=null;
        try{
            // TODO: 05.03.2025
            builderDefault=     new OkHttpClient().newBuilder();
            // TODO: 09.10.2024
            builderDefault.connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT,ConnectionSpec.MODERN_TLS,ConnectionSpec.COMPATIBLE_TLS ,ConnectionSpec.RESTRICTED_TLS ));
            Log.i(this.getClass().getName(),  " java.security.cert.X509Certificate  "+
                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString() + "builderDefault " +builderDefault);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return builderDefault;
    }
}