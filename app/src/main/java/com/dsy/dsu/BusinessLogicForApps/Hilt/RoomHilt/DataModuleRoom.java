
package com.dsy.dsu.BusinessLogicForApps.Hilt.RoomHilt;


import android.content.Context;
import android.util.Log;

import com.dsy.dsu.BusinessLogicForApps.AllDatabases.ROOM.CreateROOM;
import com.dsy.dsu.BusinessLogicForApps.AllDatabases.ROOM.ROOMDatabase;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
@QualifiertRoom
@Module
@InstallIn(SingletonComponent.class)

public class DataModuleRoom {

    @Singleton
    @Provides

    public ROOMDatabase metodHiltRoom (@ApplicationContext Context context) {
        ROOMDatabase ROOM = null;
        try{
         ROOM = new CreateROOM(context).метоInizROOM();
       // ROOMDatabase ROOM = null;
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                " sqlite " +ROOM);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context.getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return ROOM;
    }


}
