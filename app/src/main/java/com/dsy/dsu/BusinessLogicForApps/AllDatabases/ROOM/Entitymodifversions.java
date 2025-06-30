package com.dsy.dsu.BusinessLogicForApps.AllDatabases.ROOM;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.math.BigInteger;

@Entity(tableName = "MODIFITATION_Client_ROOM")

public class Entitymodifversions implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer _id;

    @ColumnInfo(name = "name" )
    @NonNull
    private String name="materials_databinary";

    @ColumnInfo(name = "localversionandroid",defaultValue ="1901-01-10 00:00:00")
    private String localversionandroid;

    @ColumnInfo(name = "versionserveraandroid",defaultValue ="1901-01-10 00:00:00")
    private String versionserveraandroid;

    @ColumnInfo(name = "localversionandroid_version",defaultValue ="0")
    private Long localversionandroid_version;

    @ColumnInfo(name = "versionserveraandroid_version",defaultValue ="0")
    private Long versionserveraandroid_version;


    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getLocalversionandroid() {
        return localversionandroid;
    }

    public void setLocalversionandroid(String localversionandroid) {
        this.localversionandroid = localversionandroid;
    }

    public String getVersionserveraandroid() {
        return versionserveraandroid;
    }

    public void setVersionserveraandroid(String versionserveraandroid) {
        this.versionserveraandroid = versionserveraandroid;
    }

    public Long getLocalversionandroid_version() {
        return localversionandroid_version;
    }

    public void setLocalversionandroid_version(Long localversionandroid_version) {
        this.localversionandroid_version = localversionandroid_version;
    }

    public Long getVersionserveraandroid_version() {
        return versionserveraandroid_version;
    }

    public void setVersionserveraandroid_version(Long versionserveraandroid_version) {
        this.versionserveraandroid_version = versionserveraandroid_version;
    }
}