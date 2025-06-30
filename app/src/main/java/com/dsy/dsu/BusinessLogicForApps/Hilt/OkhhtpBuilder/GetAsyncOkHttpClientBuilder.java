package com.dsy.dsu.BusinessLogicForApps.Hilt.OkhhtpBuilder;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;


final public class GetAsyncOkHttpClientBuilder {
    // TODO: 05.03.2025
     private  Context context;
    private SSLSocketFactory getsslSocketFactory2;


    public GetAsyncOkHttpClientBuilder(Context context, SSLSocketFactory getsslSocketFactory2) {
        this.context = context;
        this.getsslSocketFactory2 = getsslSocketFactory2;

    }

    // TODO: 05.03.2025 Получаем для Работы с сетью
   public OkHttpClient.Builder  GetAsyncOkHttpClientBuilder(@NonNull String enableSSl){
        // TODO: 05.03.2025
        OkHttpClient.Builder getAsyncOkHttpClientBuilder=null;
        try{
            InGetOkhhtpBuilder inGetOkhhtpBuilder = null;
            switch (enableSSl){
                case "https":
                    // TODO: 05.03.2025
                      inGetOkhhtpBuilder=new GetOkhhtpBuilderSSL(context,getsslSocketFactory2);
                    // TODO: 20.03.2025
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"getAsyncOkHttpClientBuilder " +getAsyncOkHttpClientBuilder+ "\n" +
                            " enableSSl " +enableSSl);
                    break;

                case "http":
                    // TODO: 05.03.2025
                    inGetOkhhtpBuilder=new GetOkhhtpBuilder(context);
                    // TODO: 20.03.2025
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"getAsyncOkHttpClientBuilder " +getAsyncOkHttpClientBuilder+ "\n" +
                              " enableSSl " +enableSSl);
                    break;
            }
            // TODO: 05.03.2025
            getAsyncOkHttpClientBuilder=    inGetOkhhtpBuilder.getOkhhtpBuilder();


            // TODO: 20.03.2025
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"getAsyncOkHttpClientBuilder " +getAsyncOkHttpClientBuilder);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  getAsyncOkHttpClientBuilder;
    }


}
