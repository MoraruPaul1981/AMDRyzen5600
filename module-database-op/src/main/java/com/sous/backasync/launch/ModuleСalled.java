package com.sous.backasync.launch;


import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sous.backasync.businesslogic.errors.RecordNewErroBack;
import com.sous.backasync.launch.interfaces.ModuleDeletingBackAsyncInterface;
import com.sous.backasync.launch.interfaces.ModuleСalledBackAsyncInterface;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Named
@Module
@InstallIn(SingletonComponent.class)
public class ModuleСalled implements ModuleСalledBackAsyncInterface {
  private   Context context;
    private    final  String getNameProvider="com.sous.backasync.provider";
    private    final  String getNameProviderSystem="com.dsy.dsu.providerforsystemtables";
    public @Inject ModuleСalled(@ApplicationContext Context context) {
        this.context = context;
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");//
    }

    @Override
    public Bundle getModuleСalled(@NonNull String method, @Nullable String table, @Nullable Bundle extras) {
        Bundle gatCall =null;
        try{
            if (extras!=null) {
                Uri uri = Uri.parse("content://"+getNameProvider+"/" + table + "");
                // TODO: 28.01.2025
                ContentResolver contentProviderCall=context.getContentResolver();
                gatCall= contentProviderCall.acquireContentProviderClient(uri).call(method,table,extras);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  +
                        " gatCall "+gatCall);
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n" + "gatCall " +gatCall +"\n");

        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return  gatCall;
}
}
// TODO: 28.01.2025   END CLASS




