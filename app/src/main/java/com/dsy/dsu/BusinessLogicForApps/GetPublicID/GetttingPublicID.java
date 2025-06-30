package com.dsy.dsu.BusinessLogicForApps.GetPublicID;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;


import java.util.Date;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;



@Module
@InstallIn(SingletonComponent.class)
@Named
public class GetttingPublicID {





    @Provides
    @QualifierPublicID
    public Integer getttingPublicID(@ApplicationContext Context context  ) {//
        ///TODO --первая вставка

        Integer PublicID = 0;
        try{
            if (context !=null) {
                //TODO SELECT getpublic id
                Uri uri = Uri.parse("content://com.dsy.dsu.providerforsystemtables/" + "successLogin" + "");
                ContentResolver contentResolverPublicID = context.getContentResolver();
                Cursor getCursorPublicID = contentResolverPublicID.query(uri, new String[]{},
                        new String(" SELECT publicid FROM successLogin ORDER BY date_update DESC LIMIT 1  "),
                        new String[]{}, null);///   "  //// SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?

                if (getCursorPublicID.getCount() > 0) {
                    getCursorPublicID.moveToFirst();

                    PublicID = getCursorPublicID.getInt(0);
                }
                getCursorPublicID.close();



            }
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " PublicID " +PublicID);
            // TODO: 12.04.2024
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  PublicID;
    }


    // TODO: 08.10.2024 end CLASS
}