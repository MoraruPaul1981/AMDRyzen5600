package com.dsy.dsu.BusinessLogicForApps.Hilt.OkhhtpBuilder;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;

public class GetOkhhtpBuilderSSL implements  InGetOkhhtpBuilder {
    private  Context context;
    private SSLSocketFactory getsslSocketFactory2;
    public GetOkhhtpBuilderSSL(@NotNull Context context,@NotNull SSLSocketFactory getsslSocketFactory2) {
        this.context = context;
        this.getsslSocketFactory2 = getsslSocketFactory2;
    }

    @Override
    public OkHttpClient.Builder getOkhhtpBuilder() {
        OkHttpClient.Builder builderSSL=null;
        try{
            // TODO: 06.10.2024 3 вариат
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            // TODO: 25.12.2024
                            Log.i(this.getClass().getName(),  " chain  "+
                                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    " время " +new Date().toLocaleString() + " chain "  +chain);
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            // TODO: 25.12.2024
                            Log.i(this.getClass().getName(),  " chain  "+
                                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    " время " +new Date().toLocaleString()+ " chain "  +chain );
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            // TODO: 25.12.2024
                            // TODO: 25.12.2024
                            X509Certificate[] getX509Certificate=    new X509Certificate[1];
                            try {
                                KeyStore ksTrust = KeyStore.getInstance("BKS");
                                InputStream instream = context.getResources().openRawResource(R.raw.androidserver);
                                ksTrust.load(instream, "password".toCharArray());
                                X509Certificate certificate= (X509Certificate) ksTrust.getCertificate("server");
                                // TODO: 05.03.2025
                                getX509Certificate[0]=certificate;

                                Log.i(this.getClass().getName(),  " java.security.cert.X509Certificate  "+
                                        Thread.currentThread().getStackTrace()[2].getMethodName()+
                                        " время " +new Date().toLocaleString() + " certificate  "+certificate);
                            } catch ( Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            Log.i(this.getClass().getName(),  " java.security.cert.X509Certificate  "+
                                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    " время " +new Date().toLocaleString() );
                            return getX509Certificate;
                        }
                    }
            };


            builderSSL=     new OkHttpClient().newBuilder();
            builderSSL.connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT,ConnectionSpec.MODERN_TLS,ConnectionSpec.COMPATIBLE_TLS ,ConnectionSpec.RESTRICTED_TLS ));
            builderSSL.sslSocketFactory(getsslSocketFactory2, (X509TrustManager)trustAllCerts[0]);
      builderSSL.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    // TODO: 25.12.2024
                   switch (hostname){
                       case "base.dsu1.ru":
                       return true;
                   }
                    Log.i(this.getClass().getName(),  " hostname  "+
                            Thread.currentThread().getStackTrace()[2].getMethodName()+
                            " время " +new Date().toLocaleString()  + " hostname " +hostname +" session " +session);
                    return false;
                }

            });

            Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString() );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return builderSSL;
    }
    }

