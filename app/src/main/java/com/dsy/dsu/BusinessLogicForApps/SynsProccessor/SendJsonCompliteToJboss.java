package com.dsy.dsu.BusinessLogicForApps.SynsProccessor;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

import javax.net.ssl.SSLSocketFactory;

public class SendJsonCompliteToJboss {

    //////todo МЕТОД НЕПОСТРЕДСТВЕННО ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР МЕТОД POST
    Long sendingJsonCompliteToJboss(@NonNull Context context,
                                    @NonNull byte[] ГенерацияJSONОтAndroid,
                                    @NonNull String Таблицы,
                                    @NonNull    LinkedHashMap<Integer,String> getHiltJbossDebug,
                                    @NonNull Integer PublicID,
                                    @NotNull SSLSocketFactory getsslSocketFactory2) {
        Long РезультатCallBacksОтСервера = 0l;
        try {
            Log.d(this.getClass().getName(), "  МЕТОД НЕПОСТРЕДСТВЕННО ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР МЕТОД POST ");
            // TODO: 15.06.2021 проверяем если таблица табель то еси в нутри потока отпралеемого хоть один день d1,d2,d3 защита от пустого траыфика\
            Log.d(this.getClass().getName(), " ГенерацияJSONОтAndroida.toString() "
                    + ГенерацияJSONОтAndroid.toString() +
                    " ГенерацияJSONОтAndroida.toString().toCharArray().length  "
                    + ГенерацияJSONОтAndroid.toString().toCharArray().length +
                    " Таблицы " + Таблицы);
            // TODO: 02.04.2024  Адресс и Порт Сервера Jboss
            // TODO: 02.04.2024  Адресс и Порт Сервера Jboss
            String   ИмяСерверИзХранилица = getHiltJbossDebug.values().stream().map(m->String.valueOf(m)).findFirst().get();
            Integer    ПортСерверИзХранилица = getHiltJbossDebug.keySet().stream().mapToInt(m->m).findFirst().getAsInt();


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ИмяСерверИзХранилица " + ИмяСерверИзХранилица+
                    " ПортСерверИзХранилица " +ПортСерверИзХранилица );

            // TODO: 21.09.2022 ОТПРАВЯЛЕТ ДАННЫЕ НА СЕРВЕР
            StringBuffer    BufferSendDataServer =new CoreBinessLogics(context). методSendByteToAsync(
                    ГенерацияJSONОтAndroid,
                    PublicID,
                    Таблицы,
                    "Получение JSON файла от Андройда",
                    ИмяСерверИзХранилица ,ПортСерверИзХранилица,getsslSocketFactory2);

            ///БУФЕР ОТПРАВКИ ДАННЫХ НА СЕРВЕР  //TODO original "tabel.dsu1.ru", 8888        //TODO "192.168.254.40", 8080
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" BufferSendDataServer "+BufferSendDataServer.toString());
            if (BufferSendDataServer.length() > 0) {
                if (!BufferSendDataServer.toString().trim().matches("(.*)[Don't Login and Password](.*)")){
                    РезультатCallBacksОтСервера=Long.parseLong(BufferSendDataServer.toString());
                }
            }
            Log.d(this.getClass().getName(), "BufferSendDataServer.length() " + BufferSendDataServer.length()+
                    " РезультатCallBacksОтСервера " +РезультатCallBacksОтСервера);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатCallBacksОтСервера;
    }




}
