package com.dsy.dsu.BusinessLogicForApps.AllDatabases.ROOM;


import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

@Dao
public interface DaoRoom {




    @RawQuery
    Maybe<List<Task> >getRawMatbin(SupportSQLiteQuery query);

    @RawQuery
    Maybe<List<EntityMaterialBinary> >getRawBinaty(SupportSQLiteQuery query);


       // TODO: 29.08.2023  запросы для всех таблиц GET ALL////////
    @Query("SELECT * FROM materials_databinary ")
    Maybe<List<EntityMaterialBinary>> getAllModBinaty();



/*    // TODO: 29.08.2023  запросы для всех таблиц GET ALL
    @Query("SELECT * FROM materials_databinary as mod")
    Maybe<List<EntityMaterialBinary>> getAllMod();

    @Query("SELECT * FROM MODIFITATION_Client_ROOM as mamtbin")
    Maybe<List<Entitymodifversions>> getAllMatrBi();*/


    /*// TODO: 29.08.2023 Прямые запросы SQL injects

    @RawQuery
    Maybe<List<Entitymodifversions> >getRawMod(SupportSQLiteQuery query);

    @RawQuery
    Maybe<List<EntityMaterialBinary> >getRawMatbin(SupportSQLiteQuery query);


    // TODO: 29.08.2023  вставка данных и обновленние   Binary

    @Insert
    void insertBinary(EntityMaterialBinary entityMaterialBinary);

    @Delete
    void deleteBinary(EntityMaterialBinary entityMaterialBinary);

    @Update
    void updateBinary(EntityMaterialBinary entityMaterialBinary);

    // TODO: 29.08.2023  вставка данных и обновленние  для всех таблиц Version

    @Insert
    void insertversions(Entitymodifversions entitymodifversions);

    @Delete
    void deleteversions(Entitymodifversions entitymodifversions);

    @Update
    void updateversions(Entitymodifversions entitymodifversions);*/

}
