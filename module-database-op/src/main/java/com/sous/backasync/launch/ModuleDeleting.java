package com.sous.backasync.launch;


import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.sous.backasync.businesslogic.errors.RecordNewErroBack;
import com.sous.backasync.launch.interfaces.ModuleDeletingBackAsyncInterface;
import com.sous.backasync.launch.interfaces.ModuleUpdatetingBackAsyncInterface;

import java.util.Optional;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class ModuleDeleting implements ModuleDeletingBackAsyncInterface {
    private final String getNameProvider="com.sous.backasync.provider";
    private    final  String getNameProviderSystem="com.dsy.dsu.providerforsystemtables";
  private   Context context;
    public @Inject ModuleDeleting(@ApplicationContext Context context) {
        this.context=context;
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");//
    }


    @SuppressLint("NewApi")
    @Override
    public Integer getModuleDelete(@NonNull String Таблица, @NonNull String selection,  @NonNull String[] selectionArgs) {
        Integer getDeletingBack=0;
        try{
            if (selection!=null) {
                Uri uri = Uri.parse("content://"+getNameProvider+"/" + Таблица + "");
                // TODO: 28.01.2025
                ContentResolver contentProviderInsert=context.getContentResolver();
                getDeletingBack= contentProviderInsert.acquireContentProviderClient(uri).delete(uri,selection,selectionArgs);


                getDeletingBack=
                        Optional.ofNullable(getDeletingBack)
                                .stream()
                                .filter(f->f!=null).mapToInt(Integer::new).findAny().orElse(0);


                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "getDeletingBack " +getDeletingBack  );
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "getDeletingBack " +getDeletingBack  );

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return  getDeletingBack;
    }








    @SuppressLint("NewApi")
    @Override
    public Integer getModuleDelete(@NonNull String Таблица, @NonNull Bundle bungleModuleBack) {
        int getDeletingBack = 0;
        try{
            if (bungleModuleBack!=null) {
                Uri uri = Uri.parse("content://"+getNameProvider+"/" + Таблица + "");
                // TODO: 28.01.2025
                ContentResolver contentProviderInsert=context.getContentResolver();
                getDeletingBack= contentProviderInsert.acquireContentProviderClient(uri).delete(uri,bungleModuleBack);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  +
                        " getDeletingBack "+getDeletingBack);
            }


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n" + "getDeletingBack " +getDeletingBack +"\n");

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return  getDeletingBack;
    }


    @SuppressLint("NewApi")
    @Override
    public Integer getModuleSystemDelete(@NonNull String Таблица ) {
        Integer getDeletingBack=0;
        try{
            if (Таблица!=null) {
                Uri uri = Uri.parse("content://"+getNameProviderSystem+"/" + Таблица + "");
                // TODO: 28.01.2025
                ContentResolver contentProviderInsert=context.getContentResolver();
                getDeletingBack= contentProviderInsert.acquireContentProviderClient(uri).delete(uri,"DELETE FROM " + Таблица+"",null);


                getDeletingBack=
                        Optional.ofNullable(getDeletingBack)
                                .stream()
                                .filter(f->f!=null).mapToInt(Integer::new).findAny().orElse(0);


                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "getDeletingBack " +getDeletingBack  );
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "getDeletingBack " +getDeletingBack  );

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  getDeletingBack;
    }


    // TODO: 28.01.2025   END CLASS
    }

// TODO: 28.01.2025   END CLASS




