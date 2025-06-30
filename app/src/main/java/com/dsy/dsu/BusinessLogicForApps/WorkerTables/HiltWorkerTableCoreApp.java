package com.dsy.dsu.BusinessLogicForApps.WorkerTables;

import java.util.concurrent.CopyOnWriteArrayList;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltWorkerTableCoreApp {
    // TODO: 13.05.2025
    CopyOnWriteArrayList<String> getWorkerTablesALl();
}
