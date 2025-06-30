package com.dsy.dsu.BusinessLogicForApps.AllDatabases.bl_SettingandSucceesLogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

public class SettingAndLoginBinesslogicSettingsTabels {

    Context context;
    Long version;

    SQLiteDatabase sqliteManager;


    public SettingAndLoginBinesslogicSettingsTabels(Context context, SQLiteDatabase sqliteManager) {
        this.context = context;
        this.version = version;
        this.sqliteManager = sqliteManager;
    }






    public SQLiteStatement sqLiteStatementInsertSettingsTabels(@NonNull String table, @NonNull ContentValues values) {
        // TODO: 08.10.2024
        SQLiteStatement sqLiteStatementInsertSettingsTabels=null;
        try{
///todo insert setting table
            String  SQlOperInsert=  "REPLACE INTO "+table+"  " +
                    " (  onesignal,current_table,version_dsu1,user_update,date_update,uuid) " +
                    "   VALUES (?,?,?  ,?,?,?)";

            sqLiteStatementInsertSettingsTabels = sqliteManager.compileStatement(SQlOperInsert);
            sqLiteStatementInsertSettingsTabels.clearBindings();
            // TODO: 04.07.2023 цикл данных
            if (values.getAsString("onesignal")!=null) {
                sqLiteStatementInsertSettingsTabels.bindString(1, values.getAsString("onesignal"));//"date_update"
            } else {
                sqLiteStatementInsertSettingsTabels.bindNull(1);
            }
            sqLiteStatementInsertSettingsTabels.bindLong(2, values.getAsLong("current_table"));//"date_update"
            sqLiteStatementInsertSettingsTabels.bindLong(3, values.getAsInteger("version_dsu1"));//"date_update"
            sqLiteStatementInsertSettingsTabels.bindLong(4, values.getAsInteger("user_update"));//"uuid"
            sqLiteStatementInsertSettingsTabels.bindString(5, values.getAsString("date_update"));//"date_update"
            sqLiteStatementInsertSettingsTabels.bindString(6, values.getAsString("uuid"));//"date_update"


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " values " +values);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  sqLiteStatementInsertSettingsTabels;
    }




    public   SQLiteStatement sqLiteStatementUpdateSettingsTabels (@NonNull String table , @NonNull ContentValues values) {
        // TODO: 08.10.2024
        SQLiteStatement sqLiteStatementUpdateSettingsTabels=null;
        try{
///todo insert setting table
            String SQlOperUpdateSystemTable = " UPDATE " + table + " SET   " +
                    " onesignal =?,current_table=?,version_dsu1=?, user_update=?  ,uuid=? ,date_update=? " +
                    "   WHERE  user_update=?  ;";
            sqLiteStatementUpdateSettingsTabels = sqliteManager.compileStatement(SQlOperUpdateSystemTable);

            sqLiteStatementUpdateSettingsTabels.clearBindings();
            // TODO: 04.07.2023 цикл данных
            if (values.getAsString("onesignal")!=null) {
                sqLiteStatementUpdateSettingsTabels.bindString(1, values.getAsString("onesignal"));//"date_update"
            } else {
                sqLiteStatementUpdateSettingsTabels.bindNull(1);
            }
            sqLiteStatementUpdateSettingsTabels.bindLong(2, values.getAsLong("current_table"));//"date_update"
            sqLiteStatementUpdateSettingsTabels.bindLong(3, values.getAsInteger("version_dsu1"));//"date_update"
            sqLiteStatementUpdateSettingsTabels.bindLong(4, values.getAsInteger("user_update"));//"uuid"
            sqLiteStatementUpdateSettingsTabels.bindString(5, values.getAsString("date_update"));//"date_update"
            sqLiteStatementUpdateSettingsTabels.bindString(6, values.getAsString("uuid"));//"date_update"

            // TODO: 08.10.2024  UUIDСommunication  set
            sqLiteStatementUpdateSettingsTabels.bindLong(7, values.getAsInteger("publicid"));//"current_table"



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

        return  sqLiteStatementUpdateSettingsTabels;
    }




}
