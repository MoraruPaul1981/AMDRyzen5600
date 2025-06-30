package com.dsy.dsu.BusinessLogicForApps.DownloadsJBOSS.BunessLogicDownloadByte;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import org.jetbrains.annotations.NotNull;

final public class DownloadByte {



   public    byte[]  downloadByte  (@NotNull Context context,@NotNull GetBinessLogicDwonloadByteInterface getBinessLogicDwonloadByteInterface,
                                           @NotNull   byte[] getbytejboss ){
        // TODO: 07.04.2025
      byte[]  getDownloadByte=  null;

        try{
            // TODO: 07.04.2025 start
            getDownloadByte=    getBinessLogicDwonloadByteInterface.getBinessLogicDwonloadByte(context, getbytejboss );


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " getDownloadByte " +getDownloadByte);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    return  getDownloadByte;
    }

}
