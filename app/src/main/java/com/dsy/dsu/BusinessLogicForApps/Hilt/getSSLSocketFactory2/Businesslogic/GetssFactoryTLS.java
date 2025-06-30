package com.dsy.dsu.BusinessLogicForApps.Hilt.getSSLSocketFactory2.Businesslogic;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.Date;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class GetssFactoryTLS extends  BissennsLogica {
    @Override
    public SSLSocketFactory getSocketFactorySSL(@NonNull Context context) {
        SSLSocketFactory sslSocketFactory3 = null;
        try {
            KeyStore ksTrust = KeyStore.getInstance("BKS");
            InputStream instream = context.getResources().openRawResource(R.raw.androidserver);
            ksTrust.load(instream, "password".toCharArray());

            // TrustManager decides which certificate authorities to use.
            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ksTrust);
            SSLContext    sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            // Create an ssl socket factory with our all-trusting manager
            sslSocketFactory3 = sslContext.getSocketFactory();
            // TODO: 25.12.2023  clear
            instream.close();

            Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString()+ " sslContext " +sslContext +  "   certificate.getPublicKey() "
                    +   ksTrust.getKey("locahost", "password".toCharArray()));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return sslSocketFactory3;
    }


}
