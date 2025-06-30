package com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_get_error_from_files;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.interfaces.GettingExistingErrorsInterface;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.sous.backasync.launch.ModuleQuety;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class GettingExistingErrorFromFile  implements GettingExistingErrorsInterface {
    private String fileNameFull = "Sous-Avtodor-ERROR";

    private   String patchFileName="SousAvtoFile";

    /**
     * @return
     */
    @SuppressLint("SuspiciousIndentation")
    @Override
    public StringBuffer gettingExistingErrors(@NonNull Context context,ModuleQuety moduleQuety ) {
        // TODO: 17.04.2023
        StringBuffer stringBuffergetFileError=new StringBuffer();
        try {
            // TODO: 07.04.2025
            // TODO: 07.04.2025  get erro from file
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                        File.separator + patchFileName + File.separator + fileNameFull + ".txt");
                if (file.exists()) {

                        Uri uriGetErrors=     FileProvider.getUriForFile(context.getApplicationContext(), "com.dsy.dsu.provider" ,file );
                        ContentResolver contentResolverGetErrors = context.getContentResolver();

                    try (              InputStream inputStream = contentResolverGetErrors.openInputStream(uriGetErrors);
                                       // TODO: 15.01.2025
                                       BufferedReader    BufferedReaderError = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));){
                        // TODO: 14.04.2025
                        stringBuffergetFileError= BufferedReaderError.lines().map(m->m+"\n").collect(StringBuffer::new, (sb, i) -> sb.append(i), StringBuffer::append);
                        // TODO: 10.04.2025
                        BufferedReaderError.close();

                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  +   " stringBuffergetFileError"+stringBuffergetFileError);

                    }



                }

                // TODO: 07.04.2025
                Log.d(this.getClass().getName(), "\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch(Exception e){
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new RecordNewErros(context).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return stringBuffergetFileError;

            // TODO: 24.03.2025 end class


        }
// TODO: 10.04.2025  end class
}