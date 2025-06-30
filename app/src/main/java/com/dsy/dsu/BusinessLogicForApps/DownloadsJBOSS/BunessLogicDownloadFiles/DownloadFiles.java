package com.dsy.dsu.BusinessLogicForApps.DownloadsJBOSS.BunessLogicDownloadFiles;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import org.jetbrains.annotations.NotNull;

import java.io.File;

final public class DownloadFiles {



  public   File downloadFiles  (@NotNull Context context,@NotNull GetBinessLogicDwonloadFilesInterface getBinessLogicDwonloadFilesInterface,
                                           @NotNull   byte[] getbytejboss,@NotNull String ИмяФайлаЗагрузки){
        // TODO: 07.04.2025
        File downloadFile=null;

        try{
            // TODO: 07.04.2025 start
            downloadFile=    getBinessLogicDwonloadFilesInterface.getttingFilesJboss(context, getbytejboss, ИмяФайлаЗагрузки);


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " downloadFile " +downloadFile);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    return  downloadFile;
    }

}
