package com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_getdatafragmenterror;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.interfaces.GettingExistingErrorsInterface;
import com.sous.backasync.launch.ModuleQuety;

final public class GetDataFragmentError {

    private Context context;
    private  ModuleQuety moduleQuety;

    
    
    
    public GetDataFragmentError(@NonNull  Context context,
                                @NonNull  ModuleQuety moduleQuety) {
        this.moduleQuety = moduleQuety;
        this.context = context;
        // TODO: 17.04.2023
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

    }


    // TODO: 07.04.2025 launch Get ERRORS
  public StringBuffer getDataFragmentError (@NonNull GettingExistingErrorsInterface gettingExistingErrorsInterface){
       StringBuffer getDataFragmentError=new StringBuffer();
       try{

       getDataFragmentError=  gettingExistingErrorsInterface.gettingExistingErrors(context,  moduleQuety);


       // TODO: 17.04.2023
       Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
               " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
               " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getDataFragmentError " +getDataFragmentError);
   } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

      return  getDataFragmentError;
   }




    // TODO: 24.03.2025 end class
}

