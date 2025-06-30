package com.dsy.dsu.BusinessLogicForApps.Hilt.RoomHilt;


import com.dsy.dsu.BusinessLogicForApps.AllDatabases.ROOM.ROOMDatabase;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfaceRoom {


    ROOMDatabase getHiltRoom( );
}


