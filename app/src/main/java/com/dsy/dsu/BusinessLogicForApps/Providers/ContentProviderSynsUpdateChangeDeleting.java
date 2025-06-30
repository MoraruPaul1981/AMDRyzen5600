package com.dsy.dsu.BusinessLogicForApps.Providers;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
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
import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.BusinessLogicForApps.Hilt.Sqlitehilt.AppModuleSQLlite;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

import dagger.hilt.EntryPoints;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

public class ContentProviderSynsUpdateChangeDeleting extends ContentProvider {
  private   UriMatcher uriMatcherДЛяПровайдераКонтентБазаДанных;
    @Inject
     SQLiteDatabase sqlite;
    private JbossContext jbossContextМенеджерПотоковМассвойОперацииВставки;
    private AsyncTaskLoader<?> asyncTaskLoader;
    private Handler handler;
    private Integer ТекущаяСтрокаПриДОбавлениииURL=0;
    private SharedPreferences preferences;
    public ContentProviderSynsUpdateChangeDeleting() throws InterruptedException {
        try{

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

            preferences =getContext(). getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            // TODO: 13.05.2025
            // TODO: 17.01.2025
            CopyOnWriteArrayList<String> getWorkerTablesALl=     EntryPoints.get(getContext(), HiltWorkerTableCoreApp.class).getWorkerTablesALl();
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " sqlite " +sqlite);

            preferences =getContext(). getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);

            Log.d(this.getClass().getName(), " getWorkerTablesALl "+getWorkerTablesALl );
            uriMatcherДЛяПровайдераКонтентБазаДанных=new UriMatcher(getWorkerTablesALl.size());
            getWorkerTablesALl.forEach(new java.util.function.Consumer<String>() {
                @Override
                public void accept(String ЭлементТаблица) {
                    uriMatcherДЛяПровайдераКонтентБазаДанных.addURI("com.dsy.dsu.providerdatachangedeleting",ЭлементТаблица.toString(),ТекущаяСтрокаПриДОбавлениииURL);
                    Log.d(this.getClass().getName(), " ЭлементТаблица "+ЭлементТаблица + " ТекущаяСтрокаПриДОбавлениииURL " +ТекущаяСтрокаПриДОбавлениииURL);
                    ТекущаяСтрокаПриДОбавлениииURL++;
                }
            });
            Log.d(this.getClass().getName(),  " uriMatcherДЛяПровайдераКонтентБазаДанных" +uriMatcherДЛяПровайдераКонтентБазаДанных );
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
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  false;
    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Integer РезультатУдалениеСтатуса=0;
        try{
        Log.d(this.getClass().getName(), " uri"+uri );
            // TODO: 14.10.2022 метод определения текущней таблицы
            String table = МетодОпределяемТаблицу(uri);
            if (table!=null) {
                Integer РезультатУдаления  = sqlite.delete(table, selection, selectionArgs);
                // TODO: 30.10.2021
                Log.w(getContext().getClass().getName(), " РезультатУдаления  " + РезультатУдаления);/////
                Uri ОтветВставкиДанных  = Uri.parse("content://"+РезультатУдаления.toString());
               String ответОперцииВставки=    Optional.ofNullable(ОтветВставкиДанных).map(Emmeter->Emmeter.toString().replace("content://","")).get();
                РезультатУдалениеСтатуса= Integer.parseInt(ответОперцииВставки);
                // TODO: 06.09.2023
                if (РезультатУдаления> 0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                        // TODO: 22.09.2022 увеличивает версию данных
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
      return РезультатУдалениеСтатуса;
    }

    @NonNull
    private String МетодОпределяемТаблицу(Uri uri) {
        String table = new String();
        try{
        Log.d(this.getClass().getName(), " uri"+ uri);
            table=    Optional.ofNullable(uri).map(Emmeter->Emmeter.toString().replace("content://com.dsy.dsu.providerdatachangedeleting/","")).get();
            Log.w(getContext().getClass().getName(),
                    " defaluit table  " + table  + " uri " + uri);/////
            Log.d(this.getClass().getName(), " table"+ table);
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
        Uri  ОтветВставкиДанных = null;
        try {
            Log.d(this.getClass().getName(), " uri"+uri );
            // TODO: 14.10.2022 метод определения текущней таблицы
            String table = МетодОпределяемТаблицу(uri);
            Long   РезультатВставкиДанных  =   sqlite.insertOrThrow(table, null, values);
            // TODO: 30.10.2021
            Log.w(getContext().getClass().getName(), " РезультатВставкиДанных  " + РезультатВставкиДанных);/////
            ОтветВставкиДанных  = Uri.parse("content://"+РезультатВставкиДанных.toString());
            if (РезультатВставкиДанных> 0) {
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






    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable Bundle queryArgs, @Nullable CancellationSignal cancellationSignal) {
        Cursor cursor=null;
       try{
           asyncTaskLoader=new AsyncTaskLoader<Object>(getContext()) {
               @Nullable
               @Override
               public Object loadInBackground() {
                   Cursor cursor=null;
        Integer  ПубличныйIDДляФрагмента=queryArgs.getInt("ПубличныйIDДляФрагмента",0);
        Integer  ТекущаяЦФО=queryArgs.getInt("ТекущаяЦФО",0);
        Integer  ТекущаяНомерМатериала=queryArgs.getInt("ТекущаяНомерМатериала",0);
        String  Таблица=queryArgs.getString("Таблица","");
        String  ФлагКакиеДанныеНужныПолучениеМатериалов=queryArgs.getString("ФлагКакиеДанныеНужныПолучениеМатериалов","");
           SQLiteQueryBuilder          SQLBuilder_Для_GRUD_Операций =new SQLiteQueryBuilder();
           SQLBuilder_Для_GRUD_Операций.setTables(Таблица);
           // TODO: 07.11.2022  данные курсор для групировки
           switch (ФлагКакиеДанныеНужныПолучениеМатериалов.trim()) {
               case "ПолучениеНомерМатериала":
               cursor=     SQLBuilder_Для_GRUD_Операций.query(  sqlite,new String[]{"*"},
                       null,null
                       ,"nomenvesov_zifra, nomenvesov, moneys, kolichstvo, cfo", "cfo="+ТекущаяЦФО, null,null);
               break;
               case "ПолучениеСгрупированныеСамиДанные":
                   cursor=     SQLBuilder_Для_GRUD_Операций.query(  sqlite,new String[]{"*"},
                           null,null
                           ,"nomenvesov_zifra, nomenvesov, moneys, kolichstvo, cfo", "cfo="+ТекущаяЦФО+" AND nomenvesov_zifra="+ТекущаяНомерМатериала , null,null);
                   break;
           }
           Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " + cursor);/////
           commitContentChanged();
                   return cursor;
               }
           };
           asyncTaskLoader.startLoading();
           cursor= (Cursor) asyncTaskLoader.loadInBackground();
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
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
    public Bundle call(@NonNull String authority, @NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        return super.call(authority, method, arg, extras);
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        /*String table = МетодОпределяемТаблицу(uri);
        Create_Database_СамаБАзаSQLite=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазуORM();*/
        Log.w(this.getClass().getName(), "  КОНТЕНТ ПРОВАЙДЕР update  uri " +uri + " getContext()) " +getContext());

        return Integer.parseInt("1");
    }

    // TODO: 22.11.2022  UPDATE
// TODO: 22.11.2022  UPDATE
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        ArrayList<Integer> РезультатОперацииBurkUPDATEСменаСтатусуУдаланиеСервера = new ArrayList<>();
        try {

            String     table = МетодОпределяемТаблицу(uri);

            final Long[] ПовышенаяВерсияДанных = {new VersionCurentTable(getContext()).upVersionCurentTable(table )};
            Log.d(this.getClass().getName(), " ПовышенаяВерсияДанных  " + ПовышенаяВерсияДанных[0]);

            String ФлагКакойСинхронизацияПерваяИлиНет=         preferences.getString("РежимЗапускаСинхронизации", "");
            if (ФлагКакойСинхронизацияПерваяИлиНет.equalsIgnoreCase("ПовторныйЗапускСинхронизации") ||
                    table.equalsIgnoreCase("settings_tabels") ||  table.equalsIgnoreCase("view_onesignal") ) {

                Log.w(this.getClass().getName(), "count  bulkInsert  values.length "
                        + values.length+"\n"+"bulkPOTOK "+Thread.currentThread().getName()+"\n"+
                        " FUTURE FUTURE SIZE  EntityMaterialBinary "+"\n"+
                        "  isParallel isParallel isParallel" );
                // TODO: 01.12.2022 тест код UPDATE
                // TODO: 08.12.2022 UPDATE
                Flowable.fromArray(values)
                        .onBackpressureBuffer( )
                        .filter(filter->filter.size()>0)
                        .doOnError(new Consumer<Throwable>() {
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
                        .doOnNext(new Consumer<ContentValues>() {
                            @Override
                            public void accept(ContentValues contentValuesInsert) throws Throwable {
                                try{
                                     String СтолбикСравнения="uuid";
                                    Integer     ОперацияUPDATEСменыСтатусаУдаление=0;
                                    Object UUID= contentValuesInsert.get(СтолбикСравнения);
                                    System.out.println("  ИзменяемыйСтлобикСравенения  " + СтолбикСравнения);

                                    ContentValues contentValuesСменыСтатусаУдаленногоСервера=new ContentValues();
                                    contentValuesСменыСтатусаУдаленногоСервера.put("status_send","Удаленная");
                                    // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                                    contentValuesСменыСтатусаУдаленногоСервера.put("current_table", ПовышенаяВерсияДанных[0]);
                                    String ДатаДляТекущеОперации=     new GetMainDateForApp(getContext()).getMainDateForApp();
                                    contentValuesСменыСтатусаУдаленногоСервера.put("date_update", ДатаДляТекущеОперации);
                                    String table = МетодОпределяемТаблицу(uri);
                                    ОперацияUPDATEСменыСтатусаУдаление  =
                                            sqlite.update(table,contentValuesСменыСтатусаУдаленногоСервера,"status_send=? AND uuid=?",
                                                    new String[]{"УдалитьФлагСервера",UUID.toString()});
                                 /*   ОперацияUPDATEСменыСтатусаУдаление  = update(uri,contentValuesСменыСтатусаУдаленногоСервера,"status_send=?, uuid=?", new String[]{"УдалитьФлагСервера",UUID.toString()});;*/
                                    Log.w(this.getClass().getName(), " Вставка массовая через contentValuesInsert  burkInsert   ОперацияUPDATEСменыСтатусаУдаление " +  ОперацияUPDATEСменыСтатусаУдаление);
                                    if (ОперацияUPDATEСменыСтатусаУдаление>0) {
                                        РезультатОперацииBurkUPDATEСменаСтатусуУдаланиеСервера.add(Integer.parseInt(ОперацияUPDATEСменыСтатусаУдаление.toString()));
                                        // TODO: 20.03.2023 МЕняем Статуст УдалелитьсСервера на Удаленный
                                        ПовышенаяВерсияДанных[0]++;
                                    }
                                    Log.w(this.getClass().getName(), "count  bulkInsert  РезультатОперацииBurkUPDATEСменаСтатусуУдаланиеСервера.size() "
                                            + РезультатОперацииBurkUPDATEСменаСтатусуУдаланиеСервера.size()+"\n"+"bulkPOTOK "+Thread.currentThread().getName()+"\n"+
                                            " FUTURE FUTURE SIZE  EntityMaterialBinary "+"\n"+
                                            "  isParallel isParallel isParallel" + " ДанныеДляВторогоЭтапаBulkINSERT ДанныеДляВторогоЭтапаBulkINSERT.size()  ");
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
                                    // TODO: 09.11.2022 закрывает ТРАНЗАКЦИИ ВНУТРИ
                                    if ( РезультатОперацииBurkUPDATEСменаСтатусуУдаланиеСервера.size()>0) {
                                        getContext().getContentResolver().notifyChange(uri, null);
                                    }
                                // TODO: 20.03.2023 Смена Статуса С Сервера  По СТАТУСУ УДАЛАЛИТЬ НА УДАЛЕННЫЙ
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        " table  " + table + "  РезультатОперацииBurkUPDATEСменаСтатусуУдаланиеСервера.size() " + РезультатОперацииBurkUPDATEСменаСтатусуУдаланиеСервера.size());

                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(throwable.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                return false;
                            }
                        })
                        .blockingSubscribe();
            }
            // TODO: 10.11.2022 получаем ответ данные
            Log.w(this.getClass().getName(), " BULK insert updaet РезультатОперацииBurkUPDATEСменаСтатусуУдаланиеСервера.size()" + РезультатОперацииBurkUPDATEСменаСтатусуУдаланиеСервера.size());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return    РезультатОперацииBurkUPDATEСменаСтатусуУдаланиеСервера.stream().reduce(0, (a, b) -> a + b);
    }

}

