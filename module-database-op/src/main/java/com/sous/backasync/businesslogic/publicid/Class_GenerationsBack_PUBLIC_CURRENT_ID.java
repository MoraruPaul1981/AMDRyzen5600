package com.sous.backasync.businesslogic.publicid;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Date;

public class Class_GenerationsBack_PUBLIC_CURRENT_ID {
    Context context;

    public Class_GenerationsBack_PUBLIC_CURRENT_ID( ) {
        //sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
    }

    //todo функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    public Integer getPublicIDAllApp(@NonNull Context context) {
        ///TODO --первая вставка
        this.context =context;
        Integer PublicID = 0;
        try{
            if (this.context !=null) {
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
        /*    new RecordNewErros(this.context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());*/
        }
        return  PublicID;
    }


    public Long gettingSettingTableVersion(@NonNull  Context context,@NonNull String getSql,@NonNull String ИмяТаблицы) {
        ///TODO --первая вставка
        this.context =context;
        Long getsettingTableVersion = 0l;
        try{
            if (this.context !=null) {
                //TODO SELECT getpublic id
                Uri uri = Uri.parse("content://com.dsy.dsu.providerforsystemtables/" + ИмяТаблицы + "");
                ContentResolver contentResolverPublicID = context.getContentResolver();
                Cursor getCursorPublicID = contentResolverPublicID.query(uri, new String[]{},
                        new String(getSql),//TODO " SELECT id FROM successLogin ORDER BY date_update DESC LIMIT 1  "
                        new String[]{}, null);///   "  //// SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?

                if (getCursorPublicID.getCount() > 0) {
                    getCursorPublicID.moveToFirst();

                    getsettingTableVersion = getCursorPublicID.getLong(0);
                }
                getCursorPublicID.close();



            }
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " getsettingTableVersion " +getsettingTableVersion);
            // TODO: 12.04.2024
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
           /* new RecordNewErros(this.context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());*/
        }
        return  getsettingTableVersion;
    }


}
