package com.dsy.dsu.BusinessLogicForApps.CreateFolderBinatySave;


import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.io.File;

import io.reactivex.rxjava3.annotations.NonNull;

class ClassDeleteFiles {

 protected   Context context;
      String fileName;
      String patchFileName;
   public ClassDeleteFiles(@NonNull Context context) {
       this.context=context;
   }

   public void МетодDeleteFolders() {
       try{
           File patchFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), File.separator + patchFileName);

           if (patchFile.exists()) {
               File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                       File.separator + patchFileName + File.separator + fileName);
               if (file.isFile()) {

                   file.delete();

                   Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                           " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                           " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  +"  file " + file.isFile());

               }

           }
           // TODO: 25.03.2023
           Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                   " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                   " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  +"  file " + fileName.isEmpty());
       } catch (Exception e) {
           e.printStackTrace();
           Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                   + Thread.currentThread().getStackTrace()[2].getLineNumber());
           new RecordNewErros(context).recordnewerror(e.toString(),
                   this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                   Thread.currentThread().getStackTrace()[2].getLineNumber());
       }
   }




}
