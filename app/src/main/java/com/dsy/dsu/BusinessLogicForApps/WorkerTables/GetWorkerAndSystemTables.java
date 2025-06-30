package com.dsy.dsu.BusinessLogicForApps.WorkerTables;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.BusinessLogicForApps.WorkerTables.hilt.QualifierSystemTable;
import com.dsy.dsu.BusinessLogicForApps.WorkerTables.hilt.QualifierWorkerTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
@QualifierWorkerTable
public class GetWorkerAndSystemTables {



    @Provides
    @Singleton
    public CopyOnWriteArrayList<String> getWorkerTablesALl(@ApplicationContext Context context )  {
        CopyOnWriteArrayList<String> getMainListTable = new CopyOnWriteArrayList();
        try {
            getMainListTable.addIfAbsent("errordsu1");
            getMainListTable.addIfAbsent("depatment");
            getMainListTable.addIfAbsent("fio");
            getMainListTable.addIfAbsent("region");
            getMainListTable.addIfAbsent("cfo");
            getMainListTable.addIfAbsent("settings_tabels");
            getMainListTable.addIfAbsent("notifications");
            getMainListTable.addIfAbsent("templates");
            getMainListTable.addIfAbsent("fio_template");
            getMainListTable.addIfAbsent("chat_users");
            getMainListTable.addIfAbsent("chats");
            getMainListTable.addIfAbsent("data_chat");
            getMainListTable.addIfAbsent("tabel");
            getMainListTable.addIfAbsent("data_tabels");
            getMainListTable.addIfAbsent("view_onesignal");
            getMainListTable.addIfAbsent("data_notification");
            getMainListTable.addIfAbsent("nomen_vesov");
            getMainListTable.addIfAbsent("type_materials");
            getMainListTable.addIfAbsent("get_materials_data");
            getMainListTable.addIfAbsent("company");
            getMainListTable.addIfAbsent("track");
            getMainListTable.addIfAbsent("prof");
            getMainListTable.addIfAbsent("order_tc");
            getMainListTable.addIfAbsent("vid_tc");
            getMainListTable.addIfAbsent("materials_databinary");
            getMainListTable.addIfAbsent("organization");

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"  getMainListTable" + getMainListTable);

            ///todo публикум название таблицы или цифру его
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getMainListTable;
    }



    @Provides
    @Singleton
    @QualifierSystemTable
    public CopyOnWriteArrayList<String> getSystemTablesALl(@ApplicationContext Context context )  {
        CopyOnWriteArrayList<String> getSystemTablesALl = new CopyOnWriteArrayList();
        try {
            getSystemTablesALl.add("MODIFITATION_Client");
            getSystemTablesALl.addIfAbsent("settings_tabels");
            getSystemTablesALl.addIfAbsent("successlogin");
            // TODO: 06.06.2025 еще добавил
            getSystemTablesALl.addIfAbsent("errordsu1");
            getSystemTablesALl.addIfAbsent("notifications");
            getSystemTablesALl.addIfAbsent("templates");
            getSystemTablesALl.addIfAbsent("fio_template");
            getSystemTablesALl.addIfAbsent("chat_users");
            getSystemTablesALl.addIfAbsent("chats");
            getSystemTablesALl.addIfAbsent("data_chat");
            getSystemTablesALl.addIfAbsent("tabel");
            getSystemTablesALl.addIfAbsent("data_tabels");
            getSystemTablesALl.addIfAbsent("data_notification");
            getSystemTablesALl.addIfAbsent("get_materials_data");
            getSystemTablesALl.addIfAbsent("order_tc");
            getSystemTablesALl.addIfAbsent("vid_tc");
            getSystemTablesALl.addIfAbsent("materials_databinary");

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"  getSystemTablesALl" + getSystemTablesALl);

            ///todo публикум название таблицы или цифру его
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getSystemTablesALl;
    }


}
