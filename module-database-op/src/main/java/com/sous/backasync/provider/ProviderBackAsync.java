package com.sous.backasync.provider;


import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.sous.backasync.businesslogic.errors.RecordNewErroBack;
import com.sous.backasync.businesslogic.hill.HiltWorkerTableBarckAync;

import com.sous.backasync.businesslogic.hill.ModuleBackAsyncSQLlite;


import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

import dagger.hilt.EntryPoints;

public class ProviderBackAsync extends ContentProvider  {
    private   UriMatcher uriMatcherДЛяПровайдераКонтентБазаДанных;

    private Integer ТекущаяСтрокаПриДОбавлениииURL=0;

 private  final String getNameProvider="com.sous.backasync.provider";

    private SQLiteDatabase  sqliteBAck;

    public ProviderBackAsync() throws InterruptedException {
        try{
            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  );
            // TODO: 04.10.2022
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }
    @Override
    public boolean onCreate() {
        try{
            // TODO: 13.05.2025 ПРОВАЙДЕР
             sqliteBAck = EntryPoints.get(getContext(), ModuleBackAsyncSQLlite.class).getModuleBackAsyncSQLlite();
            // TODO: 17.01.2025
            // TODO: 17.01.2025
            CopyOnWriteArrayList<String> getWorkerTablesALl=     EntryPoints.get(getContext(), HiltWorkerTableBarckAync.class).getWorkerTablesALl();


            uriMatcherДЛяПровайдераКонтентБазаДанных=new UriMatcher(getWorkerTablesALl.size());
            getWorkerTablesALl.forEach(new Consumer<String>() {
                @Override
                public void accept(String ЭлементТаблица) {
                    uriMatcherДЛяПровайдераКонтентБазаДанных.addURI(getNameProvider,ЭлементТаблица.toString(),ТекущаяСтрокаПриДОбавлениииURL);

                    Log.d(this.getClass().getName(), " ЭлементТаблица "+ЭлементТаблица + " ТекущаяСтрокаПриДОбавлениииURL " +ТекущаяСтрокаПриДОбавлениииURL);

                    ТекущаяСтрокаПриДОбавлениииURL++;
                }
            });
            if (sqliteBAck!=null) {
                Log.d(this.getClass().getName(),"\n"
                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " sqliteBAck "  +sqliteBAck  +
                        " sqliteBAck " +sqliteBAck);
                return  true;

            }
            // TODO: 15.01.2025
            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " sqliteBAck "  +sqliteBAck  +
                    " sqliteBAck " +sqliteBAck);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(getContext()).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  false;
    }




    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        try {
            Log.d(this.getClass().getName(), " uri"+uri  + "selection "+selection );
            String table = МетодОпределяемТаблицу(uri);

            cursor=   sqliteBAck.rawQuery( selection,  selectionArgs);
            Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " cursor " + cursor);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(getContext()).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return cursor;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable Bundle queryArgs, @Nullable CancellationSignal cancellationSignal) {
        try{
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErroBack(getContext()).recordnewerrorBack(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return super.query(uri, projection, queryArgs, cancellationSignal);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Integer РезультатУдаления=0;
        try{
                    if (!sqliteBAck.inTransaction()) {
                        sqliteBAck.beginTransaction();
                    }
                    Log.d(this.getClass().getName(), " uri"+uri );
                    // TODO: 14.10.2022 метод определения текущней таблицы
                    String table = МетодОпределяемТаблицу(uri);
                    if (table!=null) {
                        РезультатУдаления  = sqliteBAck.delete(table, selection+"=?", selectionArgs);
                        // TODO: 30.10.2021
                        Log.w(getContext().getClass().getName(), " РезультатУдаления  " + РезультатУдаления);/////
                        Uri ОтветВставкиДанных  = Uri.parse("content://"+РезультатУдаления.toString());
                        String ответОперцииВставки=    Optional.ofNullable(ОтветВставкиДанных).map(Emmeter->Emmeter.toString().replace("content://","")).get();
                        Integer   РезультатУдалениеСтатуса= Integer.parseInt(ответОперцииВставки);
                        if (РезультатУдаления> 0) {
                            getContext().getContentResolver().notifyChange(uri, null);
                        }
                    }else {
                        Log.w(getContext().getClass().getName(), " table  " + table);/////
                    }
                    if (sqliteBAck.inTransaction()) {
                        sqliteBAck.setTransactionSuccessful();
                    }
                    if (sqliteBAck.inTransaction()) {
                        sqliteBAck.endTransaction();
                    }
            Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " РезультатУдаления " + РезультатУдаления);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(getContext()).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатУдаления;
    }











    @NonNull
    private String МетодОпределяемТаблицу(Uri uri) {
        String table = new String();
        try{
            Log.d(this.getClass().getName(), " uri"+ uri);
            table=    Optional.ofNullable(uri).map(Emmeter->Emmeter.toString().replace("content://"+getNameProvider+"/","")).get();
            Log.w(getContext().getClass().getName(),
                    " defaluit table  " + table  + " uri " + uri);/////
            Log.d(this.getClass().getName(), " table"+ table);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(getContext()).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return table;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
          Uri ОтветВставкиДанных = null;
        try {
                    if (!sqliteBAck.inTransaction()) {
                        sqliteBAck.beginTransaction();
                    }
                    Log.d(this.getClass().getName(), " uri"+uri );
                    // TODO: 14.10.2022 метод определения текущней таблицы
                    String table = МетодОпределяемТаблицу(uri);
                    Long   РезультатВставкиДанных  = sqliteBAck.insert(table, null, values);
                    // TODO: 30.10.2021
                    Log.w(getContext().getClass().getName(), " РезультатВставкиДанных  " + РезультатВставкиДанных);/////

                    ОтветВставкиДанных = Uri.parse("content://"+РезультатВставкиДанных.toString());
                    if (РезультатВставкиДанных> 0) {

                        if (sqliteBAck.inTransaction()) {
                            sqliteBAck.setTransactionSuccessful();
                            // TODO: 22.09.2022 увеличивает версию данных
                        }
                    }
                    if (sqliteBAck.inTransaction()) {
                        sqliteBAck.endTransaction();
                    }
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(getContext()).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ОтветВставкиДанных;
    }

    // TODO: 22.11.2022 INSERT
    @SuppressLint("SuspiciousIndentation")
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        Integer РезультатМассовогоВсатвкиДанныхФинал=0;
        ArrayList<Integer> РезультатВнутренаяbulk = new ArrayList<>();
        try {
            if (!sqliteBAck.inTransaction()) {
                sqliteBAck.beginTransaction();
            }
            Log.d(this.getClass().getName(), " uri"+uri );
            String table = МетодОпределяемТаблицу(uri);
            Stream.of(values)
                    .filter(emme->emme!=null)
                    .forEachOrdered(new Consumer<ContentValues>() {
                        @Override
                        public void accept(ContentValues ТекущаяСтрочкаИзМассо) {
                            Log.w(this.getClass().getName(), " Вставка массовая через burkInsert   ТекущаяСтрочкаИзМассо" +  ТекущаяСтрочкаИзМассо);
                            try{
                                Long     id  = 0l;
                                if (ТекущаяСтрочкаИзМассо.size()>0 ) {
                                    id = sqliteBAck.insert(table, null, ТекущаяСтрочкаИзМассо);
                                }
                                Log.w(this.getClass().getName(), " Вставка массовая через burkInsert   id " +  id);
                                if (0 < id) РезультатВнутренаяbulk.add( Integer.parseInt(id.toString()) );
                                Log.w(this.getClass().getName(), "count  bulkInsert  РезультатВнутренаяbulk.size() "
                                        + РезультатВнутренаяbulk.size()+"\n"+"bulkPOTOK "+Thread.currentThread().getName()+"\n"+
                                        " FUTURE FUTURE SIZE  EntityMaterialBinary "+"\n"+
                                        "  isParallel isParallel isParallel" );
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                        Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    });
            // TODO: 09.11.2022 закрывает ТРАНЗАКЦИИ ВНУТРИ
            if (sqliteBAck.inTransaction()) {

                sqliteBAck.setTransactionSuccessful();
            }
            if (sqliteBAck.inTransaction()) {
                sqliteBAck.endTransaction();
            }
            РезультатМассовогоВсатвкиДанныхФинал=РезультатВнутренаяbulk.size();
            // TODO: 09.11.2022  получаем результаты
            Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " РезультатМассовогоВсатвкиДанныхФинал " + РезультатМассовогоВсатвкиДанныхФинал);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(getContext()).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return    РезультатМассовогоВсатвкиДанныхФинал;
    }








    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                " operations " + operations);
        return super.applyBatch(operations);
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull String authority, @NonNull ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                " operations " + operations);
        return super.applyBatch(authority, operations);
    }


    @Override
    public void shutdown() {
        super.shutdown();
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String table, @Nullable Bundle bundleCall) {
       // return super.call(method, arg, extras);
            // TODO: 17.05.2025
            try{
                if (!sqliteBAck.inTransaction()) {
                    sqliteBAck.beginTransaction();
                }
                String getxecSQL=     bundleCall.getString("getxecSQL").trim();
                if (table!=null) {
                    // TODO: 17.05.2025
                    SQLiteStatement sqLiteStatementgetxecSQL= sqliteBAck.compileStatement(getxecSQL);
                    sqLiteStatementgetxecSQL.execute();

                    Log.d(this.getClass().getName(),"\n" + " class FaceAPp "
                            + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                            " getxecSQL " + getxecSQL);
                }else {
                    Log.w(getContext().getClass().getName(), " table  " + table);/////
                }
                if (sqliteBAck.inTransaction()) {
                    sqliteBAck.setTransactionSuccessful();
                }
                if (sqliteBAck.inTransaction()) {
                    sqliteBAck.endTransaction();
                }
                Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " bundleCall " + bundleCall);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErroBack(getContext()).recordnewerrorBack(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return bundleCall;

        }






    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Integer  UpdateBAck=0;
        try{
                            if (!sqliteBAck.inTransaction()) {
                                sqliteBAck.beginTransaction();
                            }

                            Log.d(this.getClass().getName(), " uri"+uri );
                            // TODO: 14.10.2022 метод определения текущней таблицы
                            String table = МетодОпределяемТаблицу(uri);
                            if (table!=null) {
                                Integer РезультатУдаления  = sqliteBAck.update(table,values, selection, selectionArgs);
                                // TODO: 30.10.2021
                                Log.w(getContext().getClass().getName(), " РезультатУдаления  " + РезультатУдаления);/////
                                Uri ОтветВставкиДанных  = Uri.parse("content://"+РезультатУдаления.toString());
                                String ответОперцииВставки=    Optional.ofNullable(ОтветВставкиДанных).map(Emmeter->Emmeter
                                        .toString().replace("content://","")).get();
                                UpdateBAck= Integer.parseInt(ответОперцииВставки);
                                if (РезультатУдаления> 0) {
                                    getContext().getContentResolver().notifyChange(uri, null);
                                }
                            }else {
                                Log.w(getContext().getClass().getName(), " table  " + table);/////
                            }
                            if (sqliteBAck.inTransaction()) {

                                sqliteBAck.setTransactionSuccessful();
                            }
                            if (sqliteBAck.inTransaction()) {
                                sqliteBAck.endTransaction();
                            }
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " UpdateBAck " +UpdateBAck);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(getContext()).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return UpdateBAck;
    }





    //todo end class
}


