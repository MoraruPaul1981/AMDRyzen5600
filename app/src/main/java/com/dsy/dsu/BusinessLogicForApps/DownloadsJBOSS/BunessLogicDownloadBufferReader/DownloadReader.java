package com.dsy.dsu.BusinessLogicForApps.DownloadsJBOSS.BunessLogicDownloadBufferReader;

import android.content.Context;
import android.util.Log;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import org.jetbrains.annotations.NotNull;

final public class DownloadReader {



   public   StringBuffer downloadReader (@NotNull Context context, @NotNull GetBinessLogicDwonloadReaderInterface getBinessLogicDwonloadByteInterface,
                                                      @NotNull   byte[] getbytejboss ){
        // TODO: 07.04.2025
       StringBuffer getDownloadReader=  null;

        try{
            // TODO: 07.04.2025 start
            getDownloadReader=    getBinessLogicDwonloadByteInterface.getBinessLogicDwonloadReader(context, getbytejboss );


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " getDownloadReader " +getDownloadReader);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    return  getDownloadReader;
    }

}
