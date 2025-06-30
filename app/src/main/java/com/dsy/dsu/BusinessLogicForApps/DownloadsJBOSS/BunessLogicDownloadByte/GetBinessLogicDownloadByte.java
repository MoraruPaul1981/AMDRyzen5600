package com.dsy.dsu.BusinessLogicForApps.DownloadsJBOSS.BunessLogicDownloadByte;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;

public class GetBinessLogicDownloadByte implements  GetBinessLogicDwonloadByteInterface {
    /**
     * @param context
     * @param
     * @return
     */
    @Override
    public byte[] getBinessLogicDwonloadByte(@NotNull Context context, @NotNull byte[] getbytejboss ) {
        // TODO: 07.04.2025
        byte[]  getNewByte=null;
  try{
          try (ByteArrayInputStream bin = new ByteArrayInputStream(getbytejboss);
               GZIPInputStream gzipper = new GZIPInputStream(bin))
          {
              // Not sure where to go here

              byte[] buffer = new byte[2048];
              ByteArrayOutputStream out = new ByteArrayOutputStream();
              int len;
              while ((len = gzipper.read(buffer)) > 0) {
                  out.write(buffer, 0, len);
              }

              gzipper.close();
              out.flush();
              out.close();
              getNewByte=out.toByteArray();

              Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " getNewByte " +getNewByte);
          }

      Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
              " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
              " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " getNewByte " +getNewByte);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        return getNewByte;
    }
}
