package com.dsy.dsu.BusinessLogicForApps.AllDatabases.bl_SettingandSucceesLogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

public class SettingAndLoginBinesslogicSuccessLogin {

    Context context;
    Long version;

    SQLiteDatabase sqliteManager;


    public SettingAndLoginBinesslogicSuccessLogin(Context context, SQLiteDatabase sqliteManager) {
        this.context = context;
        this.version = version;
        this.sqliteManager = sqliteManager;
    }



    public SQLiteStatement sqLiteStatementInsertSuccessLogin(@NonNull String table, @NonNull ContentValues values) {
        // TODO: 08.10.2024
        SQLiteStatement sqLiteStatementInsertSuccessLogin=null;
        try{

///todo insert successlogin
            String  SQlOperInsert=  "REPLACE INTO "+table+"  " +
                    " ( publicid,success_users,success_login,date_update,mode_weekend, mode_connection ) " +
                    "   VALUES (?,?,?  ,?,?,?)";

            sqLiteStatementInsertSuccessLogin = sqliteManager.compileStatement(SQlOperInsert);
            sqLiteStatementInsertSuccessLogin.clearBindings();
            // TODO: 04.07.2023 цикл данных
            sqLiteStatementInsertSuccessLogin.bindLong(1, values.getAsInteger("publicid"));//"date_update"
            sqLiteStatementInsertSuccessLogin.bindString(2, values.getAsString("success_users"));//"date_update"
            sqLiteStatementInsertSuccessLogin.bindString(3, values.getAsString("success_login"));//"date_update"
            sqLiteStatementInsertSuccessLogin.bindString(4, values.getAsString("date_update"));//"date_update"

            // TODO: 08.10.2024
            if (values.getAsString("mode_weekend")!=null) {
                sqLiteStatementInsertSuccessLogin.bindString(5, values.getAsString("mode_weekend"));//"uuid"
            }else {
                sqLiteStatementInsertSuccessLogin.bindString(5, "Включить");//"uuid"
            }

            if (values.getAsString("mode_connection")!=null) {
                sqLiteStatementInsertSuccessLogin.bindString(6, values.getAsString("mode_connection"));//"date_update"
            }else {
                sqLiteStatementInsertSuccessLogin.bindString(6, "Mobile");//"date_update"
            }


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  sqLiteStatementInsertSuccessLogin;
    }





    public   SQLiteStatement getsqLiteStatementUpdateSuccessLogin(@NonNull String table , @NonNull ContentValues values) {
        // TODO: 08.10.2024
        SQLiteStatement sqLiteStatementUpdateSuccessLogin=null;

        try{

///todo insert successlogin
            String SQlOperUpdateSystemTable = " UPDATE " + table + " SET   " +
                    " success_login =?,success_users=?,publicid=?, date_update=? ,mode_weekend=?,mode_connection=? " +
                    "   WHERE  publicid=?  ";
            sqLiteStatementUpdateSuccessLogin = sqliteManager.compileStatement(SQlOperUpdateSystemTable);

            sqLiteStatementUpdateSuccessLogin.clearBindings();
            // TODO: 04.07.2023 цикл данных
            sqLiteStatementUpdateSuccessLogin.bindString(1, values.getAsString("success_login"));//"date_update"
            sqLiteStatementUpdateSuccessLogin.bindString(2, values.getAsString("success_users"));//"date_update"
            sqLiteStatementUpdateSuccessLogin.bindLong(3, values.getAsInteger("publicid"));//"date_update"
            sqLiteStatementUpdateSuccessLogin.bindString(4, values.getAsString("date_update"));//"uuid"

            if (values.getAsString("mode_weekend")!=null) {
                sqLiteStatementUpdateSuccessLogin.bindString(5, values.getAsString("mode_weekend"));//"uuid"
            }else {
                sqLiteStatementUpdateSuccessLogin.bindString(5, "Включить");//"uuid"
            }

            if (values.getAsString("mode_connection")!=null) {
                sqLiteStatementUpdateSuccessLogin.bindString(6, values.getAsString("mode_connection"));//"date_update"
            }else {
                sqLiteStatementUpdateSuccessLogin.bindString(6, "Mobile");//"date_update"
            }


            // TODO: 08.10.2024  UUIDСommunication  set
            sqLiteStatementUpdateSuccessLogin.bindLong(7, values.getAsInteger("publicid"));//"current_table"


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  sqLiteStatementUpdateSuccessLogin;
    }





    public SQLiteStatement getsqLiteStatementChangeSSLUpdateSuccessLogin(@NonNull String table , @NonNull ContentValues values ) {
        // TODO: 08.10.2024
        SQLiteStatement sqLiteStatementUpdateSuccessLogin=null;

        try{

///todo insert successlogin
            String SQlOperUpdateSystemTable = " UPDATE " + table + " SET   " +
                    " mode_ssl =?  " +
                    "   WHERE  publicid=?  ";
            sqLiteStatementUpdateSuccessLogin = sqliteManager.compileStatement(SQlOperUpdateSystemTable);

            sqLiteStatementUpdateSuccessLogin.clearBindings();
            // TODO: 04.07.2023 цикл данных
            sqLiteStatementUpdateSuccessLogin.bindString(1, values.getAsString("mode_ssl"));//"date_update"


            // TODO: 08.10.2024  UUIDСommunication  set
            sqLiteStatementUpdateSuccessLogin.bindLong(2, values.getAsInteger("publicid"));//"current_table"


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  sqLiteStatementUpdateSuccessLogin;
    }









}
