package com.sous.backasync.businesslogic.hill;


import android.database.sqlite.SQLiteDatabase;




import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;



@EntryPoint
@InstallIn(SingletonComponent.class)
public interface ModuleBackAsyncSQLlite {


    SQLiteDatabase getModuleBackAsyncSQLlite( );
}
