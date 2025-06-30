package com.dsy.dsu.BusinessLogicForApps.Hilt.Jakson;


import android.database.sqlite.SQLiteDatabase;

import com.fasterxml.jackson.databind.ObjectMapper;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfaceJakson {


    ObjectMapper getHiltJaksonInterface( );
}


