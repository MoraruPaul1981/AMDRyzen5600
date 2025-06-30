package com.example.tabels.model.hilts;

import android.database.sqlite.SQLiteDatabase;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@EntryPoint
@InstallIn(SingletonComponent.class)

public interface ModuleTabelsSQLlite {


    SQLiteDatabase getModuleTabelsSQLlite( );
}
