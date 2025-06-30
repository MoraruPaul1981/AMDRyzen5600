package com.dsy.dsu.BusinessLogicForApps.DownloadsJBOSS.BunessLogicDownloadByte;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.google.common.io.ByteStreams;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;

public class GetBinessLogicDownloadByteBuffer implements  GetBinessLogicDwonloadByteInterface {
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

              ByteArrayOutputStream outbuffer = new ByteArrayOutputStream(2048);
              ByteStreams.copy(gzipper , outbuffer);

              gzipper.close();
              outbuffer.flush();
              outbuffer.close();
// TODO: 28.04.2025 finif
              getNewByte=outbuffer.toByteArray();



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
