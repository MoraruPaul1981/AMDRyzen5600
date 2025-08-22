
package com.dsy.dsu.BusinessLogicForApps.Hilt.Sqlitehilt;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
@Named
@QualifiertEnd
public class DataModuleSqlite {
    @Singleton
    @Provides
public SQLiteDatabase metodHiltSqlite (@ApplicationContext Context context){
        // TODO: 23.04.2025
        SQLiteDatabase getSQLites=null;
        try{
          File fileDatabeseOpenParametrs = new File("/data/user/0/com.dsy.dsu/databases", "Database DSU-1.db");

  if(fileDatabeseOpenParametrs.exists()){
      // TODO: 23.04.2025  GET()
        getSQLites = getOpenDatabase( fileDatabeseOpenParametrs,context);
      // TODO: 16.04.2025
      if (!getSQLites.isOpen()) {
          GetSQLiteDatabase getSQLiteDatabase=new GetSQLiteDatabase(context);
          getSQLiteDatabase.getDatabaseName();
          // TODO: 23.04.2025  GET()
          getSQLites = getOpenDatabase( fileDatabeseOpenParametrs,context);
      }

  }else {
         GetSQLiteDatabase getSQLiteDatabase=new GetSQLiteDatabase(context);
      getSQLiteDatabase.getDatabaseName();
      // TODO: 23.04.2025  GET()
        getSQLites = getOpenDatabase( fileDatabeseOpenParametrs,context);
  }

            // TODO: 17.04.2023
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                " getSQLites.get().isOpen() " +getSQLites.isOpen());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context.getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return getSQLites;
    }

    private   SQLiteDatabase getOpenDatabase(  File fileDatabeseOpenParametrs, @NonNull Context context) {
        AtomicReference<SQLiteDatabase>  getSQLites=new AtomicReference<>();
        try{
        //getSQLites =  SQLiteDatabase.openDatabase("/data/user/0/com.dsy.dsu/databases/Database DSU-1.db",null, SQLiteDatabase.CREATE_IF_NECESSARY);

            //TODO Проверка на ВЕРСИЮ вЫШЕ
            launchgetSQLiteDatabaseChekUpVersion(context);


            //TODO OPEN
            launchSQLiteDatabaseOpenDatabase(fileDatabeseOpenParametrs, getSQLites);
            // TODO: 17.04.2023
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                " getSQLites.get().isOpen() " +getSQLites.get().isOpen());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context.getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  getSQLites.get();
    }

    private   void launchSQLiteDatabaseOpenDatabase(File fileDatabeseOpenParametrs, AtomicReference<SQLiteDatabase> getSQLites) throws  Exception {
        getSQLites.getAndSet( SQLiteDatabase.openDatabase(fileDatabeseOpenParametrs.getAbsolutePath(),null, SQLiteDatabase.OPEN_READWRITE));
    }

    private   void launchgetSQLiteDatabaseChekUpVersion(@NonNull Context context) throws  Exception {
        GetSQLiteDatabase getSQLiteDatabaseChekUpVersion=new GetSQLiteDatabase(context);
        getSQLiteDatabaseChekUpVersion.close();
    }


}
