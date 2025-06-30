package com.dsy.dsu.BusinessLogicForApps.SaveBitmapToFile;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class ClassSaveBitmapToFile {
    private  Context context;

    public byte[] методSavePhotoFromServer(@NonNull Context context, @NonNull Long UUIDPhoto,@NonNull  byte[] Photo ) throws IOException {
        byte[] ByteDecodePhoto=null;
        try{
            String patchFileName="SousAvtoFile/AppMaterial/Photos";
            String NameNewPhotosCamerax=UUIDPhoto.toString()+".jpg";
            File fileNewPhotoDirectory= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    +File.separator+patchFileName  );
            File fileNewPhotoFromCameraX = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    +File.separator+patchFileName + File.separator+NameNewPhotosCamerax);

            if ( fileNewPhotoDirectory.isDirectory()  && !fileNewPhotoFromCameraX.exists()) {
                Uri address=     FileProvider.getUriForFile(context.getApplicationContext(), "com.dsy.dsu.provider" ,fileNewPhotoFromCameraX );
                ContentResolver contentResolver = context.getContentResolver();
                OutputStream out = contentResolver.openOutputStream(address);
                BufferedOutputStream bufferedOutputStream =   new BufferedOutputStream(out,2048);//4096//2048
                bufferedOutputStream.write(Photo);
                // TODO: 03.10.2023
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                out.flush();
                out.close();
            }
            // TODO: 17.07.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " fileNewPhotoFromCameraX.exists() " +fileNewPhotoFromCameraX.exists());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ByteDecodePhoto;
    }





}
