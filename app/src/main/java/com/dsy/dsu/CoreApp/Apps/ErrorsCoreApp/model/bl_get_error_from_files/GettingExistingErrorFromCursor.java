package com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_get_error_from_files;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.interfaces.GettingExistingErrorsInterface;
import com.sous.backasync.launch.ModuleQuety;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GettingExistingErrorFromCursor    implements GettingExistingErrorsInterface {

    /**
     * @param context
     * @return
     */
    @Override
    public StringBuffer gettingExistingErrors(@NonNull Context context,ModuleQuety moduleQuety ) {
        // TODO: 17.04.2023
        StringBuffer gettingExistingErrors=new StringBuffer();
        try {


            Cursor getbackasyncQueryandWhere=   moduleQuety.getModuleQuery("errordsu1",
                    " SELECT  *   FROM errordsu1 AS er  WHERE er.ERROR IS  NOT NULL  ORDER BY er.id DESC  " ,
                    null);

            gettingExistingErrors=     rowAppendBufferErrors(context,getbackasyncQueryandWhere);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "gettingExistingErrors " +gettingExistingErrors );




            Log.d(this.getClass().getName(), "\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return gettingExistingErrors;
    }
    @SuppressLint("Range")
    public StringBuffer rowAppendBufferErrors(@NonNull Context context,@NonNull Cursor getbackasyncQueryandWhere) {
        // TODO: 17.01.2025
        StringBuffer БуерДляОшибок=new StringBuffer();
        try {
            Flowable.range(0, getbackasyncQueryandWhere.getCount())
                    .filter(kol-> getbackasyncQueryandWhere!=null)
                    .filter(kol-> getbackasyncQueryandWhere.getCount()>0)
                    .onBackpressureBuffer(1)
                    .doOnNext(procucer->{
                        // TODO: 07.04.2025
                        String date_update=     getbackasyncQueryandWhere.getString(getbackasyncQueryandWhere.getColumnIndex("date_update"));
                        // TODO: 17.01.2025
                        БуерДляОшибок
                                .append("\n")
                                .append("\n")
                                .append("************ Ошибка ************")
                                .append("\n")
                                .append("\n").append(date_update);
                        String getErrorRow=     getbackasyncQueryandWhere.getString(getbackasyncQueryandWhere.getColumnIndex("Error"));
                        // TODO: 17.01.2025
                        БуерДляОшибок
                                .append("\n")
                                .append("\n")
                                .append("\n").append(getErrorRow);
                        String Klass=     getbackasyncQueryandWhere.getString(getbackasyncQueryandWhere.getColumnIndex("Klass"));
                        // TODO: 17.01.2025
                        БуерДляОшибок
                                .append("\n")
                                .append("\n")
                                .append("\n").append(Klass);
                        String Metod=     getbackasyncQueryandWhere.getString(getbackasyncQueryandWhere.getColumnIndex("Metod"));
                        // TODO: 17.01.2025
                        БуерДляОшибок
                                .append("\n")
                                .append("\n")
                                .append("\n").append(Metod);
                        String LineError=     getbackasyncQueryandWhere.getString(getbackasyncQueryandWhere.getColumnIndex("LineError"));
                        // TODO: 17.01.2025
                        БуерДляОшибок.append("\n").append(LineError);

                        // TODO: 17.01.2025   СТЕП
                        getbackasyncQueryandWhere.moveToNext();
                        // TODO: 17.01.2025

                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "БуерДляОшибок " + БуерДляОшибок);

                    }).subscribeOn(Schedulers.single()).doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            // TODO: 07.04.2025
                            getbackasyncQueryandWhere.close();;
                        }
                    }).doOnError(e->{
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 01.09.2021 метод вызова
                        new RecordNewErros(context).recordnewerror(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }).blockingSubscribe();


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "БуерДляОшибок " +БуерДляОшибок );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  БуерДляОшибок;
    }


    // TODO: 24.03.2025 end class
}

