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
import com.sous.backasync.launch.interfaces.ModuleInsertingBackAsyncInterface;

import java.util.Optional;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class ModuleInserting implements ModuleInsertingBackAsyncInterface {
    private  Context context;
    private  final String getNameProvider="com.sous.backasync.provider";
    private    final  String getNameProviderSystem="com.dsy.dsu.providerforsystemtables";
    public @Inject ModuleInserting(@ApplicationContext Context context) {
        this.context=context;
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }

    // TODO: 28.01.2025   Querty



@Override
public Integer getModuleInsert(@NonNull Bundle bundleModuleBack ){
    Integer getInsert=0;
        try{
            if (bundleModuleBack!=null) {
                String[] УсловияВыборки=      bundleModuleBack.getStringArray("УсловияВыборки");
                String  СамЗапрос=      bundleModuleBack.getString("СамЗапрос");
                String  Таблица=      bundleModuleBack.getString("Таблица");
                Uri uri = Uri.parse("content://"+getNameProvider+"/" + Таблица + "");
                // TODO: 28.01.2025
                ContentResolver contentProviderInsert=context.getContentResolver();
                ContentValues contentValuesModuleBackAsync=new ContentValues();
                Uri InsertingBack =     contentProviderInsert.acquireContentProviderClient(uri).insert(uri,contentValuesModuleBackAsync);
                String ответОперцииВставки=    Optional.ofNullable(InsertingBack).map(Emmeter->Emmeter.toString().replace("content://","")).get();
                getInsert= Integer.parseInt(ответОперцииВставки);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "getInsert " +getInsert  );
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "getInsert " +getInsert  );

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  getInsert;
    }

    @SuppressLint("NewApi")
    @Override
    public Integer getModuleInsert(@NonNull String Таблица, @NonNull ContentValues contentValuesModuleBackAsync ){
        Integer getInsertingBack = null;
        try{
            if (contentValuesModuleBackAsync!=null) {
                Uri uri = Uri.parse("content://"+getNameProvider+"/" + Таблица + "");
                ContentResolver contentProviderInsert=context.getContentResolver();

         Uri InsertingBackUri=contentProviderInsert.acquireContentProviderClient(uri).insert(uri,contentValuesModuleBackAsync);

                getInsertingBack=
                         Optional.ofNullable(InsertingBackUri)
                                 .stream()
                                 .filter(f->f!=null)
                                 .filter(f->f.getHost()!=null)
                                 .filter(f->f.getHost().chars().allMatch( Character::isDigit ))
                                 .mapToInt(tran-> Integer.parseInt(tran.getHost())).findAny().orElse(0);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "getInsertingBack " +getInsertingBack +"\n"+
                        " InsertingBackUri "+InsertingBackUri);
            }


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n" + "getInsertingBack " +getInsertingBack +"\n");

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return  getInsertingBack;
    }


    @SuppressLint("NewApi")
    @Override
    public Integer getModuleSystemInsert(@NonNull String Таблица, @NonNull ContentValues contentValuesModuleBackAsync ){
        Integer getInsertingBack = null;
        try{
            if (contentValuesModuleBackAsync!=null) {
                Uri uri = Uri.parse("content://"+getNameProviderSystem+"/" + Таблица + "");
                ContentResolver contentProviderInsert=context.getContentResolver();

                Uri InsertingBackUri=contentProviderInsert.acquireContentProviderClient(uri).insert(uri,contentValuesModuleBackAsync);
                // TODO: 29.05.2025
                getInsertingBack=
                        Optional.ofNullable(InsertingBackUri)
                                .stream()
                                .filter(f->f!=null)
                                .filter(f->f.getHost()!=null)
                                .filter(f->f.getHost().chars().allMatch( Character::isDigit ))
                                .mapToInt(tran-> Integer.parseInt(tran.getHost())).findAny().orElse(0);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "getInsertingBack " +getInsertingBack +"\n"+
                        " InsertingBackUri "+InsertingBackUri);
            }


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n" + "getInsertingBack " +getInsertingBack +"\n");

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return  getInsertingBack;
    }
}
