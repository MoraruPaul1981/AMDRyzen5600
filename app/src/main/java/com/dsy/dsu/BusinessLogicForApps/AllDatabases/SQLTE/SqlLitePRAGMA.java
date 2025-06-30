package com.dsy.dsu.BusinessLogicForApps.AllDatabases.SQLTE;



import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

public class SqlLitePRAGMA {

    private Context context;

    public SqlLitePRAGMA(@NonNull Context context) {
        this.context = context;
    }

    @SuppressLint("SuspiciousIndentation")
    protected void launchsqlLitePRAGMA(@NonNull  SQLiteDatabase ССылкаНаСозданнуюБазу ) {
        // TODO: 13.01.2025
        try{

                    ССылкаНаСозданнуюБазу.compileStatement("PRAGMA synchronous = FULL");//
            // TODO: 17.04.2023
                    ССылкаНаСозданнуюБазу.rawQuery("PRAGMA journal_mode =  MEMORY",null);//DELETE | TRUNCATE | PERSIST | MEMORY | WAL | OFF


            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class FaceAPp "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }



}

