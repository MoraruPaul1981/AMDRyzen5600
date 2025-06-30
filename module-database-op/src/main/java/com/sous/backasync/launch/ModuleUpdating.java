package com.sous.backasync.launch;


import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.sous.backasync.businesslogic.errors.RecordNewErroBack;
import com.sous.backasync.launch.interfaces.ModuleUpdatetingBackAsyncInterface;

import java.util.Optional;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class ModuleUpdating implements ModuleUpdatetingBackAsyncInterface {
     private    final  String getNameProvider="com.sous.backasync.provider";
     private    final  String getNameProviderSystem="com.dsy.dsu.providerforsystemtables";
    private  Context context;
    public @Inject ModuleUpdating(@ApplicationContext Context context) {
        this.context=context;
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }


    @SuppressLint("NewApi")
    @Override
    public Integer getModuleUpdate(@NonNull String Таблица, @NonNull  ContentValues contentValuesModuleBack ,
                                   @NonNull String selection,  @NonNull String[] selectionArgs) {
        Integer UpdatingBack=0;
        try{
            if (contentValuesModuleBack!=null) {
                Uri uri = Uri.parse("content://"+getNameProvider+"/" + Таблица + "");
                // TODO: 28.01.2025
                ContentResolver contentProviderInsert=context.getContentResolver();
                  UpdatingBack= contentProviderInsert.acquireContentProviderClient(uri).update(uri,contentValuesModuleBack,selection,selectionArgs);


                UpdatingBack=
                        Optional.ofNullable(UpdatingBack)
                                .stream()
                                .filter(f->f!=null).mapToInt(Integer::new).findAny().orElse(0);


                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "UpdatingBack " +UpdatingBack  );
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "UpdatingBack " +UpdatingBack  );

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return  UpdatingBack;
    }


    @SuppressLint("NewApi")
    @Override
    public Integer getModuleUpdate(@NonNull String Таблица, @NonNull ContentValues contentValuesModuleBack) {
        int UpdatingBack = 0;
        try{
            if (contentValuesModuleBack!=null) {
                Uri uri = Uri.parse("content://"+getNameProvider+"/" + Таблица + "");
                // TODO: 28.01.2025
                ContentResolver contentProviderInsert=context.getContentResolver();
                UpdatingBack= contentProviderInsert.acquireContentProviderClient(uri).update(uri,contentValuesModuleBack,null);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  +
                        " UpdatingBack "+UpdatingBack);
            }


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n" + "UpdatingBack " +UpdatingBack +"\n");

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return  UpdatingBack;
    }





    @SuppressLint("NewApi")
    @Override
    public Integer getModuleSystemUpdate(@NonNull String Таблица, @NonNull ContentValues contentValuesModuleBack,
                                         @NonNull  String selection, @NonNull String[] selectionArgs) {
        int UpdatingBack = 0;
        try{
            if (contentValuesModuleBack!=null) {
                Uri uri = Uri.parse("content://"+getNameProviderSystem+"/" + Таблица + "");
                // TODO: 28.01.2025
                ContentResolver contentProviderUpdate=context.getContentResolver();
                UpdatingBack=
                        contentProviderUpdate.acquireContentProviderClient(uri).update(uri,contentValuesModuleBack,selection,selectionArgs);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  +
                        " UpdatingBack "+UpdatingBack);
            }


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n" + "UpdatingBack " +UpdatingBack +"\n");

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return  UpdatingBack;
    }


    @SuppressLint("NewApi")
    @Override
    public Integer getModuleSystemUpdate(@NonNull String Таблица, @NonNull ContentValues contentValuesModuleBack ) {
        int UpdatingBack = 0;
        try{
            if (contentValuesModuleBack!=null) {
                Uri uri = Uri.parse("content://"+getNameProviderSystem+"/" + Таблица + "");
                // TODO: 28.01.2025
                ContentResolver contentProviderUpdate=context.getContentResolver();
                UpdatingBack=
                        contentProviderUpdate.acquireContentProviderClient(uri).update(uri,contentValuesModuleBack,null,null);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  +
                        " UpdatingBack "+UpdatingBack);
            }


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n" + "UpdatingBack " +UpdatingBack +"\n");

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return  UpdatingBack;
    }



    // TODO: 28.01.2025   END CLASS
    }

// TODO: 28.01.2025   END CLASS




