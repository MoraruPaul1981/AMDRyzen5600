package com.dsy.dsu.BusinessLogicForApps.DownloadsJBOSS.BunessLogicDownloadBufferReader;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

public class GetBinessLogicDownloadReader implements GetBinessLogicDwonloadReaderInterface {
    /**
     * @param context
     * @param
     * @return
     */
    @Override
    public StringBuffer getBinessLogicDwonloadReader(@NotNull Context context, @NotNull byte[] getbytejboss ) {
        // TODO: 07.04.2025
        StringBuffer   buffer =null;
  try{
          try (ByteArrayInputStream bin = new ByteArrayInputStream(getbytejboss);
               GZIPInputStream gzipper = new GZIPInputStream(bin))
          {
              // Not sure where to go here
              InputStreamReader reader=new InputStreamReader(gzipper, StandardCharsets.UTF_8);
              BufferedReader   getNewReader=new BufferedReader(reader);
              // TODO: 08.04.2025

               buffer = getNewReader.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                      StringBuffer::append);

              gzipper.close();
              reader.close();

              Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " buffer " +buffer);
          }

      Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
              " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
              " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " buffer " +buffer);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        return buffer;
    }
}
