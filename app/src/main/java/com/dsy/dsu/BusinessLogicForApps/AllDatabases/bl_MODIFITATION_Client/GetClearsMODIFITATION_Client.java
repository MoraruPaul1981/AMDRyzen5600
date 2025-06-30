package com.dsy.dsu.BusinessLogicForApps.AllDatabases.bl_MODIFITATION_Client;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

public class GetClearsMODIFITATION_Client {

  private   Context context;
    private   Long version;

    private   SQLiteDatabase sqliteManager;


    public GetClearsMODIFITATION_Client(Context context, SQLiteDatabase sqliteManager) {
        this.context = context;
        this.version = version;
        this.sqliteManager = sqliteManager;
    }



    public SQLiteStatement sqLiteStatementUpdateMODIFITATION_Client (@NonNull String table , @NonNull ContentValues values) {
        // TODO: 08.10.2024
        SQLiteStatement sqLiteStatementUpdateMODIFITATION_Client=null;
        try{
///todo insert setting table
            String SQlOperUpdateSystemTable = " UPDATE " + table + " SET   " +
                    " localversionandroid =?,versionserveraandroid=?,localversionandroid_version=?, versionserveraandroid_version=?   " +
                    "   WHERE  name=?  ;";
            sqLiteStatementUpdateMODIFITATION_Client = sqliteManager.compileStatement(SQlOperUpdateSystemTable);

            sqLiteStatementUpdateMODIFITATION_Client.clearBindings();
            // TODO: 04.07.2023 цикл данных
            sqLiteStatementUpdateMODIFITATION_Client.bindString(1, values.getAsString("localversionandroid"));//"date_update"
            sqLiteStatementUpdateMODIFITATION_Client.bindString(2, values.getAsString("versionserveraandroid"));//"date_update"
            sqLiteStatementUpdateMODIFITATION_Client.bindLong(3, values.getAsInteger("localversionandroid_version"));//"date_update"
            sqLiteStatementUpdateMODIFITATION_Client.bindLong(4, values.getAsInteger("versionserveraandroid_version"));//"uuid"

            // TODO: 08.10.2024  UUIDСommunication  set
            sqLiteStatementUpdateMODIFITATION_Client.bindString(5, values.getAsString("name"));//"current_table"



            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  sqLiteStatementUpdateMODIFITATION_Client;
    }



}
