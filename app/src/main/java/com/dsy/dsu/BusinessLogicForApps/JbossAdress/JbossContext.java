package com.dsy.dsu.BusinessLogicForApps.JbossAdress;
import android.content.Context;
import android.util.Log;


import java.util.Date;

public  class JbossContext   {
    private  Context context;
    public JbossContext(Context context) {
        this.context=context;
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  );
        // TODO: 06.10.2024
    }

    private   String  СсылкаНаРежимСервера;
    // TODO: 19.02.2024
    private   String  СсылкаСервераТип="jboss-1.0-SNAPSHOT";


    public String getСсылкаНаРежимСервераТабель() {
        СсылкаНаРежимСервера=СсылкаСервераТип+"/sous.jboss.tabel";//TODO РЕЛИЗ
        return СсылкаНаРежимСервера.trim();
    }
    private   String  СсылкаНаРежимСервераОбновлениеПО;
    public String getСсылкаНаРежимСервераОбновлениеПО() {
        СсылкаНаРежимСервераОбновлениеПО= СсылкаСервераТип+"/sous.jboss.download";//TODO РЕЛИЗ
        return СсылкаНаРежимСервераОбновлениеПО.trim();
    }

    private   String  СсылкаНаРежимСервераRuntime;
    public String getСсылкаНаРежимСервераRuntime() {
        СсылкаНаРежимСервераRuntime=СсылкаСервераТип+"/sous.jboss.runtimejboss";//TODO РЕЛИЗ
        return СсылкаНаРежимСервераRuntime.trim();
    }

}//TODO END CLASS





