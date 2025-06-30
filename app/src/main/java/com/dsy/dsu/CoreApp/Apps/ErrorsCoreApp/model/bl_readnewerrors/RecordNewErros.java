package com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;


import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.BusinessLogicForApps.GreatUuidGenerations.GreatUuidGeneration;
import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;
import com.dsy.dsu.BusinessLogicForApps.DeviceName.ModulegetDeviceName;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_writer_errors_app.WewriteallApplicationErrorstoaFile;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.interfaces.RecordNewErrorsInterface;
import com.sous.backasync.launch.ModuleInserting;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class RecordNewErros  implements RecordNewErrorsInterface {

    private Context context;
      ModuleInserting moduleInserting;


    public @Inject RecordNewErros(@ApplicationContext Context context) {

        this.context=context;

        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }


    @Override
    public void recordnewerror(@NonNull String ТекстОшибки,
                                              @NonNull String КлассГнерацииОшибки,
                                              @NonNull String МетодаОшибки,
                                              @NonNull Integer ЛинияОшибки) {

        try {
            if (context != null) {
                ///TODO Записываем ошибки только определного сорта
                if (!ТекстОшибки.trim().matches("(.*)UnknownHostException(.*)")
                        && !ТекстОшибки.trim().matches("(.*)SocketTimeoutException(.*)")
                        && !ТекстОшибки.trim().matches("(.*)ConnectException(.*)")) {


                    // TODO: 30.01.2025
                    getWriteNewError(  ТекстОшибки, КлассГнерацииОшибки,МетодаОшибки, ЛинияОшибки );

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n" +" ТекстОшибки " +ТекстОшибки);;




                    // TODO: 20.12.2022  дополнительный механизм записи ошибкок
                    getWriteNewErrorNotePad(ТекстОшибки, КлассГнерацииОшибки, МетодаОшибки, ЛинияОшибки);

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n" +" ТекстОшибки " +ТекстОшибки);;
                    // TODO: 21.12.2022  главная  файл ErrorDSU1 в ТАблицу




                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "\n" +" ТекстОшибки " +ТекстОшибки);

            } else {
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "\n" +" ТекстОшибки " +ТекстОшибки);
            }
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "\n" +" ТекстОшибки " +ТекстОшибки);
            // TODO: 09.07.2023 clear
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("  Ошибка в самом классе записи ошибок нет КОНТЕКСТА RecordNewBackErros");
            Log.e(context.getClass().getName(), "Ошибка в самом классе создание ОШИБКИ (записи новой ошибки) ERROR  inse ERROR" + e
                    + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }



    @Override
      public void getWriteNewErrorNotePad(@NonNull String ТекстОшибки,
                                          @NonNull String КлассГнерацииОшибки,
                                          @NonNull String МетодаОшибки,
                                          @NonNull Integer ЛинияОшибки) {
        try{
        ArrayList<String> arrayListОшибкиДляЗаписивФайл = new ArrayList();
        arrayListОшибкиДляЗаписивФайл.add(ТекстОшибки);
        arrayListОшибкиДляЗаписивФайл.add(КлассГнерацииОшибки);
        arrayListОшибкиДляЗаписивФайл.add(МетодаОшибки);
        arrayListОшибкиДляЗаписивФайл.add(String.valueOf(ЛинияОшибки));

        // TODO: 09.07.2023 запись ошибки в файл  .txt

         writeDownAnewErrorFile(arrayListОшибкиДляЗаписивФайл);

            Log.d(this.getClass().getName(),"\n" + " class CoreApp    " + Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " arrayListОшибкиДляЗаписивФайл " + arrayListОшибкиДляЗаписивФайл);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(context.getClass().getName(), "Ошибка в самом классе создание ОШИБКИ (записи новой ошибки) ERROR  inse ERROR" + e
                + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }




    @Override
     public Integer getWriteNewError(@NonNull String ТекстОшибки,
                                  @NonNull String КлассГнерацииОшибки,
                                  @NonNull String МетодаОшибки,
                                  @NonNull Integer ЛинияОшибки ) {
        // TODO: 30.01.2025
        Integer InsertingNewErorr = null;
        try{

           moduleInserting=new ModuleInserting(context);
            // TODO: 30.01.2025
            ContentValues contentValuesNewError=new ContentValues();

            Long getVersionForError  = new VersionCurentTable(context).upVersionCurentTable("errordsu1");
            Long UUIDForError = (Long)
                    new GreatUuidGeneration(context).greatUuidGeneration();
     

            Integer getPublicIdForError =    new GetttingPublicID().getttingPublicID(context);


            String getNewDateForError = new GetMainDateForApp(context).getMainDateForApp();

            Integer getDeviceVersion    =new ModulegetDeviceName().getDeviceVersion(context);

            String ANDROID_ID    =new ModulegetDeviceName().getDeviceName(context);
            // TODO: 30.01.2025 ВСТАВКА  новой ошибки

            contentValuesNewError.put("Error", ТекстОшибки.toLowerCase() +"\n"+" :::Current Device::: "+ANDROID_ID +"\n");
            contentValuesNewError.put("Klass", КлассГнерацииОшибки.toUpperCase());
            contentValuesNewError.put("Metod", МетодаОшибки.toUpperCase());
            contentValuesNewError.put("LineError", ЛинияОшибки);
            contentValuesNewError.put("user_update", getPublicIdForError.toString());
            contentValuesNewError.put("UUID", UUIDForError.toString());
            contentValuesNewError.put("current_table", getVersionForError);
            contentValuesNewError.put("whose_error", getDeviceVersion);
            contentValuesNewError.put("date_update", getNewDateForError);

              InsertingNewErorr =    moduleInserting.getModuleInsert("errordsu1",contentValuesNewError);


            Log.d(this.getClass().getName(),"\n" + " class CoreApp    " + Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" InsertingNewErorr " +InsertingNewErorr);
    } catch (Exception e) {
        e.printStackTrace();
        System.err.println("  Ошибка в самом классе записи ошибок нет КОНТЕКСТА RecordNewBackErros");
        Log.e(context.getClass().getName(), "Ошибка в самом классе создание ОШИБКИ (записи новой ошибки) ERROR  inse ERROR" + e
                + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  InsertingNewErorr;
    }





    // TODO: 20.12.2022  дополнительный клас Заппси ОШИБКИВ ФАЙЛ

    @Override
       public void writeDownAnewErrorFile(@NonNull ArrayList<String> linkedBlockingQueueВскеОшибкиДляЗаписи) {
            try {
                WewriteallApplicationErrorstoaFile wewriteallApplicationErrorstoaFile=new WewriteallApplicationErrorstoaFile(context);

                wewriteallApplicationErrorstoaFile.launtchWewriteallApplicationErrorstoaFile(linkedBlockingQueueВскеОшибкиДляЗаписи);


                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("  Ошибка в    дополнительном модуле записи ошиьки в файл SubClassWriteErrorFile ");
                Log.e(context.getClass().getName(), "Ошибка в самом классе создание ОШИБКИ (записи новой ошибки) ERROR  inse ERROR" + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }




}

// TODO: 07.10.2023  class Create FILE for Error


