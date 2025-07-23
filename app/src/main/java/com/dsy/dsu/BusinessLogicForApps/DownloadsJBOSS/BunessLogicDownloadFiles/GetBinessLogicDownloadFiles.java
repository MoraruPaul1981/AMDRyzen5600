package com.dsy.dsu.BusinessLogicForApps.DownloadsJBOSS.BunessLogicDownloadFiles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.google.common.io.ByteStreams;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class GetBinessLogicDownloadFiles implements  GetBinessLogicDwonloadFilesInterface {
    /**
     * @param context
     * @param
     * @return
     */
    @Override
    public File getttingFilesJboss(@NotNull Context context, @NotNull InputStream getbytejboss, @NotNull String ИмяФайлаЗагрузки) {
        // TODO: 07.04.2025
        File  getNewFileJsonApk=null;
  try{
    final  String PatchDeleteJsonAnalitic="SousAvtoFile/UpdatePO";

      File ПутькФайлу = null;
      if (Build.VERSION.SDK_INT >= 30) {
          ПутькФайлу = context.getExternalFilesDir( Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic);
      } else {
          ПутькФайлу = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic);
      }

      if (!ПутькФайлу.isDirectory()) {
          ПутькФайлу.mkdirs();
          ПутькФайлу.getParentFile().mkdirs();
      }
      // TODO: 12.02.2025 удаление
      // TODO: 12.02.2025  второе удаление файла самого


      if (Build.VERSION.SDK_INT >= 30) {
          getNewFileJsonApk = context.getExternalFilesDir( Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic+File.separator + ИмяФайлаЗагрузки);
      } else {
          getNewFileJsonApk = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic+File.separator + ИмяФайлаЗагрузки);
      }

      if (getNewFileJsonApk.exists()) {
          getNewFileJsonApk.delete();
      }
      Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
              " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
              " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());




      getNewFileJsonApk.setReadable(true,false);
      getNewFileJsonApk.setWritable(true,false);
      getNewFileJsonApk.setExecutable(true,false);
      getNewFileJsonApk.getParentFile().mkdirs();

      // TODO: 24.09.2024
      Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
              " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
              " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " getNewFileJsonApk " +  getNewFileJsonApk);

      // TODO: 20.03.2023 само создание файла
      if ( getNewFileJsonApk.createNewFile()) {

          try (@SuppressLint("NewApi") ByteArrayInputStream bin = new ByteArrayInputStream(getbytejboss.readAllBytes());
               GZIPInputStream gzipper = new GZIPInputStream(bin,2048)) {
              ByteArrayOutputStream out = new ByteArrayOutputStream();
             ByteStreams.copy(gzipper , out);
              // TODO: 03.06.2025  close
              gzipper.close();
                   FileOutputStream outputStream = new FileOutputStream(getNewFileJsonApk);
                  outputStream.write(out.toByteArray());
              // TODO: 03.06.2025  close
                      out.flush();
                     out.close();
              Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " getNewFileJsonApk.length() " +getNewFileJsonApk.length());
          }

      } else {
          Log.d(this.getClass().getName(), "\n" + " class ERROR File  " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                  " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                  " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " getNewFileJsonApk.length() " +getNewFileJsonApk.length());
      }

      Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
              " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
              " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " getNewFileJsonApk " +getNewFileJsonApk);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        return getNewFileJsonApk;
    }
}
