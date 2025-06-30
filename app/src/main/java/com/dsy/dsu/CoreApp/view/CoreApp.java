package com.dsy.dsu.CoreApp.view;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;

import java.util.Date;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;


@HiltAndroidApp
public class CoreApp extends Application {

/*  @Inject
  public   SQLiteDatabase getSqlLiteCoreApp;*/
  /*@Inject
  public BinessLogicIntentServiceBoot binessLogicIntentServiceBoot;*/

/*  @Inject
  public   SQLiteDatabase getSqlLiteCoreApp;
  @Inject
  @QualifiergetDeviceName
  public  String getDeviceName;
  @Inject
  @QualifiergetDeviceVersionBack
  public  Integer getDeviceVersion;
  @Inject
  CopyOnWriteArrayList<String> getWorkerTablesALl;
  @Inject
  ModuleQuety moduleQuety;
  @Inject
  ModuleInserting moduleInserting;
  @Inject
  ModuleUpdating moduleUpdating;
  @Inject
  RecordNewErros recordNewErros;
  @Inject
  RecordNewErroBack recordNewErroBack;
  @Inject
  ModuleDeleting moduleDeleting;
  @Inject
  ModulegetDeviceNameBack modulegetDeviceNameBack;




  @Inject
   @QualifierPortJboss
   public LinkedHashMap<Integer,String> getHiltPortJboss;



  @Inject
  @QualifierPublicID
  Integer getHiltPublicId;*/


/*  @Inject
  ModuleTabels moduleTabels;*/


  @Inject
  protected SQLiteDatabase coreAppSqLiteDatabase ;


/*   @Inject
  protected CoreBinessLogics coreBinessLogics;


  @Inject
  ModuleСalled moduleСalled;*/


  @Override
  public void onCreate() {
    super.onCreate();
    try{
      // TODO: 04.10.2023 разрешения для всего
      // TODO   запускам бизнес логику CoreApp
      setTheme(R.style.Theme_AppCompat_DayNight_DarkActionBar);
      Log.d(this.getClass().getName(),"\n" + " class CoreApp    " + Thread.currentThread().getStackTrace()[2].getClassName()
              + "\n" +
              " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
              " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
              " \n" +
              " STARTED    COREAPP    " +" BREMY " +new Date().toLocaleString());

     // new LaunchCreateFilesApp(getApplicationContext()).getBunessLogicCoreApp();


      ///  getBinderAsync = EntryPoints.get(getApplicationContext(), HiltInterfaceBinderAsync.class).metodBinderAsync();
      // TODO: 02.09.2023  CREATE get SQLITE
      //    SQLiteDatabase getSQLites =   new GetSqlite().методGetSqlite(getApplicationContext());

      // TODO: 29.08.2023  CREATE ROOM
      /// new CreateROOM(getApplicationContext()).метоInizROOM();

// Reference to the application graph that is used across the whole app

      // Cursor c= sqlite.rawQuery("select * from fio",null);


      // TODO: 13.01.2025  Запускаем
      //  startingBackAsync.startingBackAsync(getApplicationContext(), getSqlLiteCoreApp);

      // TODO: 17.01.2025
     /*       CopyOnWriteArrayList<String> getWorkerTablesALl=     EntryPoints.get(getApplicationContext(), HiltWorkerTable.class).getWorkerTablesALl();
            CopyOnWriteArrayList<String> getWorkerTablesALlBarckAync=     EntryPoints.get(getApplicationContext(), HiltWorkerTableBarckAync.class).getWorkerTablesALl();
*/

            /*Cursor getbackasyncQueryandWhere=   moduleQuety.backasyncQueryandWhere("errordsu1",
                   " SELECT  *   FROM errordsu1 AS er  WHERE er.ERROR IS  NOT NULL  ORDER BY er.id DESC  "
                   ,null);*/


// TODO: 13.01.2025  Запускаем  
      //   startingBackAsync.backasyncQueryandWhere("errordsu1"," SELECT  *   FROM errordsu1  AS er WHERE er.Error=?  ORDER BY id DESC" ,new String[]{"IS  NOT NULL"});
      //startingBackAsync.backasyncQueryandWhere("fio"," SELECT  *   FROM fio   " ,null);
      //   startingBackAsync.backasyncQueryandWhere("errordsu1"," SELECT  *   FROM errordsu1 WHERE  id=? AND ERROR IS  NOT NULL  ORDER BY id DESC  " ,new String[]{"3"});
      /* startingBackAsync.backasyncQueryandWhere("errordsu1"," SELECT  *   FROM errordsu1  WHERE ERROR IS  NOT NULL  ORDER BY id DESC  " ,null);*/
      //  startingBackAsync.backasyncQueryandWhere("errordsu1"," SELECT  *   FROM errordsu1 AS er  WHERE er.ERROR IS  NOT NULL  ORDER BY er.id DESC  " ,null);
      //moduleQuety.backasyncQueryandWhere("errordsu1"," SELECT  *   FROM errordsu1 AS er  WHERE er.ERROR IS  NOT NULL  ORDER BY er.id DESC  " ,null);
      // TODO: 17.04.2023

      // TODO: 29.01.2025 insert

      // moduleInserting.getModuleInsert("error",new ContentValues());

      /*      String s=null;
            s.length();*/


      ///    recordNewErros.hashCode();

             /*    String s=null;
            s.length();
*/
      //Cursor cursor= moduleQuety.getModuleQueryForceLoad("errordsu1"," SELECT  *   FROM errordsu1 AS er  WHERE er.ERROR IS  NOT NULL  ORDER BY er.id DESC  " ,null);
      // TODO: 17.04.2023
         /* String getDeviceNameBack=  modulegetDeviceNameBack.getDeviceNameBack();
          Integer getDeviceVersionBack=  modulegetDeviceNameBack.getDeviceVersionBack();*/


      // Integer getde= moduleDeleting.getModuleDelete("errordsu1"," SELECT  *   FROM errordsu1 AS er  WHERE er.ERROR IS  NOT NULL  ORDER BY er.id DESC  " ,null);

      //LinkedHashMap<Integer,String> getHiltPortJboss=   EntryPoints.get(getApplicationContext(), getHiltPortJbossInterface.class).getHiltPortJboss();


      // TODO: 04.10.2023 разрешения для всего
      // TODO: 24.03.2025 test

    } catch (Exception e) {
      e.printStackTrace();
      Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
              Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
              + Thread.currentThread().getStackTrace()[2].getLineNumber());
      new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
              this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
              Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
  }



}




// TODO: 29.08.2023  КЛАСС   БИЗНЕС ЛОГИКУ





