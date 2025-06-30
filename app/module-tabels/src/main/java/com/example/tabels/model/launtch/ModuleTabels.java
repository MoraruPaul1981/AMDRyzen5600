package com.example.tabels.model.launtch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tabels.model.hilts.ModuleTabelsSQLlite;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.EntryPoints;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class ModuleTabels {
    Context context;

    SQLiteDatabase getModuleTabelsSQLlite;

    public @Inject ModuleTabels(@ApplicationContext Context context) {
        this.context=context;
        getModuleTabelsSQLlite = EntryPoints.get(context, ModuleTabelsSQLlite.class).getModuleTabelsSQLlite();
        // TODO: 17.01.2025
        Log.d(this.getClass().getName(), "\n" + " class "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");//
    }





    @SuppressLint("NewApi")
    public Integer getModuleDeleteTabes(@NonNull String Таблица ) {
        Integer getDeletingBack=0;
        try{

           // SubClassUpdatesCELL subClassUpdateSingletabel = new SubClassUpdatesCELL(context,getModuleTabelsSQLlite);

            Log.d(this.getClass().getName(),"\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + "\n" + "getDeletingBack " +getDeletingBack  );

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка "
                    + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  getDeletingBack;
    }

    // TODO: 28.01.2025   END CLASS
}