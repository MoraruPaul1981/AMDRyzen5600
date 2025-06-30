package com.dsy.dsu.BusinessLogicForApps;

import android.content.Context;
import android.net.NetworkInfo;
import android.util.Log;

import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

public class GetConnectivityManagerAndroid {
    Context context ;

    public GetConnectivityManagerAndroid(Context context) {
        this.context=context;
        //
    }


    public Boolean сonnectivityManageruserselection() {
        // TODO: 13.01.2025
        Boolean КакойТипПодключения = false;
        try {
            // TODO: 02.09.2021  проверяем какое подключение
            android.net.ConnectivityManager connManager = (android.net.ConnectivityManager)
                    context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(android.net.ConnectivityManager.TYPE_WIFI);
            if (mWifi.isConnected()) {
                // Do whatever
                // TODO: 13.01.2025
                КакойТипПодключения=true;
                Log.d(CoreBinessLogics.class.getName()," КакойТипПодключения"+ КакойТипПодключения);
            }else{
                // TODO: 29.09.2021
                NetworkInfo mMOBILE = connManager.getNetworkInfo(android.net.ConnectivityManager.TYPE_MOBILE);

                if (mMOBILE.isConnected()) {
                    // TODO: 13.01.2025
                    КакойТипПодключения=true;
                    Log.d(CoreBinessLogics.class.getName()," КакойТипПодключения"+ КакойТипПодключения);
                }
            }
            Log.d(CoreBinessLogics.class.getName()," КакойТипПодключения"+ КакойТипПодключения);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return КакойТипПодключения;
    }


}
