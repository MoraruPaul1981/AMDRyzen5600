package com.sous.backasync.launch;


import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.CursorLoader;

import com.sous.backasync.businesslogic.errors.RecordNewErroBack;
import com.sous.backasync.launch.interfaces.ModuleQueryBackAsyncInterface;

import java.util.Optional;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class ModuleQuety implements ModuleQueryBackAsyncInterface {
    private    final  String getNameProvider="com.sous.backasync.provider";
    private    final  String getNameProviderSystem="com.dsy.dsu.providerforsystemtables";
    private  Context context;
    public @Inject ModuleQuety(@ApplicationContext Context context) {
        this.context=context;
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }

    // TODO: 28.01.2025   Querty



@Override
public Cursor getModuleQueryForceLoad(@NonNull Bundle bundleModuleBack){
        Cursor cursor=null;
        try{
            if (bundleModuleBack!=null) {
                CursorLoader  cursorLoader=new CursorLoader(context);
                String[] УсловияВыборки=      bundleModuleBack.getStringArray("УсловияВыборки");
                String  СамЗапрос=      bundleModuleBack.getString("СамЗапрос");
                String  Таблица=      bundleModuleBack.getString("Таблица");
                Uri uri = Uri.parse("content://"+getNameProvider+"/" + Таблица + "");
                cursorLoader.setUri(uri);
                cursorLoader.setSelection(СамЗапрос);
                cursorLoader.setSelectionArgs(УсловияВыборки);//МесяцПростоАнализа
                cursorLoader.forceLoad();
                cursor=    cursorLoader.loadInBackground();
                if (cursor.getCount() > 0 && cursor!=null) {
                    cursor.moveToFirst();
                    Log.d(this.getClass().getName(), "cursor.getCount() "
                            + cursor.getCount());
                }
                cursorLoader.commitContentChanged();
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "cursor "
                    +cursor + " bundleModuleBack " +bundleModuleBack );

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return  cursor;
    }


    @Override
    public Cursor getModuleQueryForceLoad(@NonNull String Таблица, @NonNull String СамЗапрос, @NonNull String []УсловияВыборки){
        Cursor cursor=null;
        try{
            if (СамЗапрос!=null) {
                CursorLoader  cursorLoader=new CursorLoader(context);
                Uri uri = Uri.parse("content://"+getNameProvider+"/" + Таблица + "");
                cursorLoader.setUri(uri);
                cursorLoader.setSelection(СамЗапрос);
                cursorLoader.setSelectionArgs(УсловияВыборки);//МесяцПростоАнализа
                cursorLoader.forceLoad();
                cursor=    cursorLoader.loadInBackground();
                if (cursor!=null) {
                    if (cursor.getCount() > 0  ) {
                        cursor.moveToFirst();
                        Log.d(this.getClass().getName(), "cursor.getCount() "
                                + cursor.getCount());
                    }
                }
                cursorLoader.commitContentChanged();
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "cursor "
                    +cursor + " СамЗапрос " +СамЗапрос );

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return  cursor;
    }

    @Override
    public Cursor getModuleQuery(@NonNull String Таблица, @NonNull String СамЗапрос, @NonNull String []УсловияВыборки) {
        Cursor cursor = null;
        try {
            if (СамЗапрос != null) {

                Uri uri = Uri.parse("content://" + getNameProvider + "/" + Таблица + "");
                ContentResolver contentProviderInsert=context.getContentResolver();
                cursor= contentProviderInsert.acquireContentProviderClient(uri).query(uri,null,СамЗапрос,УсловияВыборки,null);

                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        Log.d(this.getClass().getName(), "cursor.getCount() "
                                + cursor.getCount());
                        // TODO: 23.05.2025
                    }
                }
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "cursor "
                    + cursor + " СамЗапрос " + СамЗапрос);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return cursor;
    }

}
