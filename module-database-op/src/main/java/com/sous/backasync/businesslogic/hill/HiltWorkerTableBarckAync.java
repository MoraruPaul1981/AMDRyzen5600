package com.sous.backasync.businesslogic.hill;


import java.util.concurrent.CopyOnWriteArrayList;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltWorkerTableBarckAync {
    CopyOnWriteArrayList<String> getWorkerTablesALl();
}
