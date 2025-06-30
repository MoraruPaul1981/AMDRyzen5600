package com.dsy.dsu.BusinessLogicForApps.Providers;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;


import com.dsy.dsu.BusinessLogicForApps.WorkerTables.hilt.HiltWorkerTableCoreApp;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossContext;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.BusinessLogicForApps.Hilt.Sqlitehilt.AppModuleSQLlite;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import dagger.hilt.EntryPoints;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Predicate;

public class ContentProviderSynsInsertOnlyAsync extends ContentProvider {
    private UriMatcher uriMatcherДЛяПровайдераКонтентБазаДанных;
    private JbossContext jbossContextМенеджерПотоковМассвойОперацииВставки;
    private AsyncTaskLoader<?> asyncTaskLoader;
    private Handler handler;
    private Integer ТекущаяСтрокаПриДОбавлениииURL = 0;

     SQLiteDatabase sqlite;


    public ContentProviderSynsInsertOnlyAsync() throws InterruptedException {
        try {

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " sqlite " +sqlite);
            // TODO: 04.10.2022
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    @Override
    public boolean onCreate() {
        try {
            // TODO: 13.05.2025 ПРОВАЙДЕР
            sqlite = EntryPoints.get(getContext(), AppModuleSQLlite.class).getAppModuleSQLlite();

            CopyOnWriteArrayList<String> getWorkerTablesALl=     EntryPoints.get(getContext(), HiltWorkerTableCoreApp.class).getWorkerTablesALl();
            Log.d(this.getClass().getName(), " getWorkerTablesALl " + getWorkerTablesALl);
            uriMatcherДЛяПровайдераКонтентБазаДанных = new UriMatcher(getWorkerTablesALl.size());
            getWorkerTablesALl.forEach(new Consumer<String>() {
                @Override
                public void accept(String ЭлементТаблица) {
                    uriMatcherДЛяПровайдераКонтентБазаДанных.addURI("com.dsy.dsu.providerdatabaseonlyasync", ЭлементТаблица.toString(), ТекущаяСтрокаПриДОбавлениииURL);
                    Log.d(this.getClass().getName(), " ЭлементТаблица " + ЭлементТаблица + " ТекущаяСтрокаПриДОбавлениииURL " + ТекущаяСтрокаПриДОбавлениииURL);
                    ТекущаяСтрокаПриДОбавлениииURL++;
                }
            });
            if (sqlite!=null) {
                Log.d(this.getClass().getName(),"\n"
                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  +
                        " sqlite " +sqlite);
                return  true;

            }
            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), " uriMatcherДЛяПровайдераКонтентБазаДанных" + uriMatcherДЛяПровайдераКонтентБазаДанных);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return false;

    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Integer РезультатУдалениеСтатуса = 0;
        try {
            //   Create_Database_СамаБАзаSQLite=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазуORM();
            Log.d(this.getClass().getName(), " uri" + uri);
            // TODO: 14.10.2022 метод определения текущней таблицы
            String table = МетодОпределяемТаблицу(uri);
            if (table != null) {
                Integer РезультатУдаления =   sqlite.delete(table, selection, selectionArgs);
                // TODO: 30.10.2021
                Log.w(getContext().getClass().getName(), " РезультатУдаления  " + РезультатУдаления);/////
                Uri ОтветВставкиДанных = Uri.parse("content://" + РезультатУдаления.toString());
                String ответОперцииВставки = Optional.ofNullable(ОтветВставкиДанных).map(Emmeter -> Emmeter.toString().replace("content://", "")).get();
                РезультатУдалениеСтатуса = Integer.parseInt(ответОперцииВставки);
                if (РезультатУдаления > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
            } else {
                Log.w(getContext().getClass().getName(), " table  " + table);/////
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатУдалениеСтатуса;
    }


    @NonNull
    private String МетодОпределяемТаблицу(Uri uri) {
        String table = new String();
        try {
            Log.d(this.getClass().getName(), " uri" + uri);
            table = Optional.ofNullable(uri).map(Emmeter -> Emmeter.toString().replace("content://com.dsy.dsu.providerdatabaseonlyasync/", "")).get();
            Log.w(getContext().getClass().getName(),
                    " defaluit table  " + table + " uri " + uri);/////
            Log.d(this.getClass().getName(), " table" + table);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
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
            Log.d(this.getClass().getName(), " uri" + uri);
            // TODO: 14.10.2022 метод определения текущней таблицы
            String table = МетодОпределяемТаблицу(uri);


            Long РезультатВставкиДанных =   sqlite.insert(table, null, values);
            // TODO: 30.10.2021
            Log.w(getContext().getClass().getName(), " РезультатВставкиДанных  " + РезультатВставкиДанных);/////

            ОтветВставкиДанных = Uri.parse("content://" + РезультатВставкиДанных.toString());
            if (РезультатВставкиДанных > 0) {
                getContext().getContentResolver().notifyChange(uri, null);
            }

            // TODO: 30.10.2021
            getContext().getContentResolver().notifyChange(uri, null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ОтветВставкиДанных;
    }

    // TODO: 22.11.2022 INSERT
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        Integer РезультатМассовогоВсатвкиДанныхФинал = 0;
        ArrayList<Integer> РезультатОперацииBulkInsert = new ArrayList<>();
        try {
            Log.d(this.getClass().getName(), " uri" + uri);
            String table = МетодОпределяемТаблицу(uri);
            // TODO: 07.12.2022 КонтентПровайдер INSERT
            Flowable.fromArray(values)
                    .onBackpressureBuffer()
                    .filter(filter -> filter.size() > 0)
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).
                                    recordnewerror(throwable.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .doOnNext(new io.reactivex.rxjava3.functions.Consumer<ContentValues>() {
                        @Override
                        public void accept(ContentValues contentValuesUpdates) throws Throwable {
                            try {
                                Long id = 0l;
                                if (contentValuesUpdates != null && contentValuesUpdates.size() > 0) {
                                    id =   sqlite.insertOrThrow(table, null, contentValuesUpdates);
                                }
                                Log.w(this.getClass().getName(), " Вставка массовая через burkInsert   id " + id);
                                if (0 < id)
                                    РезультатОперацииBulkInsert.add(Integer.parseInt(id.toString()));
                                Log.w(this.getClass().getName(), "count  bulkInsert  РезультатВнутренаяbulk.size() "
                                        + РезультатОперацииBulkInsert.size() + "\n" + "bulkPOTOK " + Thread.currentThread().getName() + "\n" +
                                        " FUTURE FUTURE SIZE  EntityMaterialBinary " + "\n" +
                                        "  isParallel isParallel isParallel");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                        Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            // TODO: 19.11.2022 ПОДНИМАЕМ ВЕРИСЮ ДАННЫХ
                            Integer РезультатПовышенииВерсииДанных = 0;
                            if (РезультатОперацииBulkInsert.size() > 0) {
                                РезультатПовышенииВерсииДанных =
                                        new VersionCurentTable(getContext()).writingDataVersionAfterLocalInsertOrUpdate(table  );
                                Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
                            }

                            // TODO: 09.11.2022 закрывает ТРАНЗАКЦИИ ВНУТРИ
                                // TODO: 09.11.2022 закрывает ТРАНЗАКЦИИ ВНУТРИ
                                if (РезультатПовышенииВерсииДанных > 0) {
                                    getContext().getContentResolver().notifyChange(uri, null);
                                }
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                    " table  " + table + "  РезультатОперацииBulkInsert.size() " + РезультатОперацииBulkInsert.size());
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    })
                    .blockingSubscribe();
            // TODO: 09.11.2022  получаем результаты
            Log.w(this.getClass().getName(), "count bulkInsert РезультатМассовогоВсатвкиДанныхФинал " + РезультатМассовогоВсатвкиДанныхФинал);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатОперацииBulkInsert.size();
    }





    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable Bundle queryArgs, @Nullable CancellationSignal cancellationSignal) {
        Cursor cursor = null;
        try {
            String table = МетодОпределяемТаблицу(uri);
            String query = queryArgs.getString("query");
            cursor =    sqlite.rawQuery(query, null);
            Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " + cursor);/////
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return cursor;
    }




    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        try{
            SQLiteQueryBuilder          SQLBuilder_Для_GRUD_Операций =new SQLiteQueryBuilder();
            String table = МетодОпределяемТаблицу(uri);
            SQLBuilder_Для_GRUD_Операций.setTables(table);
            SQLBuilder_Для_GRUD_Операций.appendWhere(selection);
/*                 cursor=     SQLBuilder_Для_GRUD_Операций.query(Create_Database_СамаБАзаSQLite,new String[]{"*"},
                            selection,selectionArgs,null, null, "date_update DESC",null);*/
            cursor=     SQLBuilder_Для_GRUD_Операций.query( sqlite,projection, selection,selectionArgs,sortOrder, null, sortOrder,null);
            // TODO: 04.08.2023
            Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " + cursor);/////
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return cursor;
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " );/////
        return super.applyBatch(operations);
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull String authority, @NonNull ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " );/////
        return super.applyBatch(authority, operations);
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        return super.call(method, arg, extras);
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Integer РезультатСменыСтатусаВыбраногоМатериала=0;
        try{
          //  Create_Database_СамаБАзаSQLite=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазуORM();
            Log.d(this.getClass().getName(), " uri"+uri );
            // TODO: 14.10.2022 метод определения текущней таблицы
            String table = МетодОпределяемТаблицу(uri);
            if (table!=null) {
                Integer РезультатУдаления  =  sqlite.update(table,values, selection, selectionArgs);
                // TODO: 30.10.2021
                Log.w(getContext().getClass().getName(), " РезультатУдаления  " + РезультатУдаления);/////
                Uri ОтветВставкиДанных  = Uri.parse("content://"+РезультатУдаления.toString());
                String ответОперцииВставки=    Optional.ofNullable(ОтветВставкиДанных).map(Emmeter->Emmeter.toString().replace("content://","")).get();
                РезультатСменыСтатусаВыбраногоМатериала= Integer.parseInt(ответОперцииВставки);
                if (РезультатУдаления> 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
            }else {
                Log.w(getContext().getClass().getName(), " table  " + table);/////
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатСменыСтатусаВыбраногоМатериала;
    }
    /*@Override
    public int delete(@NonNull Uri uri, @Nullable Bundle extras) {
        Integer РезультатУдалениеСтатуса=0;
        try{
            Create_Database_СамаБАзаSQLite=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазуORM();
            if (!Create_Database_СамаБАзаSQLite.inTransaction()) {
                Create_Database_СамаБАзаSQLite.beginTransaction();
            }
      String selection =      extras.getString("selection");
      String[] selectionArgs =      extras.getStringArray("selectionArgs");
            Log.d(this.getClass().getName(), " uri"+uri );
            // TODO: 14.10.2022 метод определения текущней таблицы
            String table = МетодОпределяемТаблицу(uri);
            if (table!=null) {
                Integer РезультатУдаления  = Create_Database_СамаБАзаSQLite.delete(table, selection, selectionArgs);
                // TODO: 30.10.2021
                Log.w(getContext().getClass().getName(), " РезультатУдаления  " + РезультатУдаления);/////
                Uri ОтветВставкиДанных  = Uri.parse("content://"+РезультатУдаления.toString());
                String ответОперцииВставки=    Optional.ofNullable(ОтветВставкиДанных).map(Emmeter->Emmeter.toString().replace("content://","")).get();
                РезультатУдалениеСтатуса= Integer.parseInt(ответОперцииВставки);
                    if (РезультатУдалениеСтатуса>0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                    }
            }else {
                Log.w(getContext().getClass().getName(), " table  " + table);/////
            }
            if (Create_Database_СамаБАзаSQLite.inTransaction()) {
                Create_Database_СамаБАзаSQLite.setTransactionSuccessful();
                Create_Database_СамаБАзаSQLite.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Create_Database_СамаБАзаSQLite.endTransaction();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewBackErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатУдалениеСтатуса;
    }*/
}

