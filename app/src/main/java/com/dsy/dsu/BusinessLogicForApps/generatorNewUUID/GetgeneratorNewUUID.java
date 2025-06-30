package com.dsy.dsu.BusinessLogicForApps.generatorNewUUID;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class GetgeneratorNewUUID {

    Context context;

    public GetgeneratorNewUUID(Context context) {
        this.context = context;
    }

    public Long  generatorNewUUID( ) {
        Long NewUUID = 0l;
        try{

            LocalDateTime futureDate = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH.mm.ss.SSS");
            String uuid=   dtf.format(futureDate) ;
            String finaluuid=   uuid.replaceAll("[^0-9]","");
            // uuid = uuid.replaceAll("^[a-zA-Z]", "");
            //uuid= CharMatcher.any().replaceFrom("[A-Za-z0-9]", "");
            BigInteger result = new BigInteger(finaluuid);
            NewUUID = result.longValue();
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " uuid " + uuid);

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " ФинальныйРезультатAsyncBackgroud " +NewUUID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return NewUUID;
    }

}
