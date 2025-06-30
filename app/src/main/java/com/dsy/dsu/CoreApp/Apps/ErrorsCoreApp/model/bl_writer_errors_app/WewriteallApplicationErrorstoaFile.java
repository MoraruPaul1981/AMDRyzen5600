package com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_writer_errors_app;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WewriteallApplicationErrorstoaFile {

    private String fileNameFull = "Sous-Avtodor-ERROR";

    private   String patchFileName="SousAvtoFile";


    private Context context;

    public WewriteallApplicationErrorstoaFile(Context context) {
        this.context = context;
    }


   public void    launtchWewriteallApplicationErrorstoaFile(@NonNull ArrayList<String> linkedBlockingQueueВскеОшибкиДляЗаписи) {
        try {

            String GeneratorNewDate = new GetMainDateForApp(context).getMainDateForApp();
            String СамаОшибка = null;
            boolean ДлинаСтрокивСпиноре = linkedBlockingQueueВскеОшибкиДляЗаписи.get(0).toString().length() > 40;
            if (ДлинаСтрокивСпиноре) {
                StringBuffer sb = new StringBuffer(linkedBlockingQueueВскеОшибкиДляЗаписи.get(0).toString());
                sb.insert(40, System.lineSeparator());
                СамаОшибка = sb.toString();
            } else {
                СамаОшибка = linkedBlockingQueueВскеОшибкиДляЗаписи.get(0).toString();
            }

            File fileWriteError = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    File.separator + patchFileName +File.separator+fileNameFull+".txt" );
            if (  fileWriteError.exists()) {



                try(FileOutputStream outputStream = new FileOutputStream(fileWriteError.getAbsoluteFile(), true);
                        BufferedWriter  bufferedWriterErrors = new BufferedWriter(new OutputStreamWriter(outputStream,
                                StandardCharsets.UTF_8));) {


                    bufferedWriterErrors.newLine();
                    bufferedWriterErrors.write("********ОШИБКА********");
                    bufferedWriterErrors.newLine();
                    bufferedWriterErrors.write("Время");
                    bufferedWriterErrors.newLine();
                    bufferedWriterErrors.write(GeneratorNewDate);
                    bufferedWriterErrors.newLine();
                    bufferedWriterErrors.write("Ошибка");
                    bufferedWriterErrors.newLine();
                    bufferedWriterErrors.write(СамаОшибка);
                    bufferedWriterErrors.newLine();
                    bufferedWriterErrors.write("Класс");
                    bufferedWriterErrors.newLine();
                    bufferedWriterErrors.write(linkedBlockingQueueВскеОшибкиДляЗаписи.get(1).toString());
                    bufferedWriterErrors.newLine();
                    bufferedWriterErrors.write("Метод");
                    bufferedWriterErrors.newLine();
                    bufferedWriterErrors.write(linkedBlockingQueueВскеОшибкиДляЗаписи.get(2).toString());
                    bufferedWriterErrors.newLine();
                    bufferedWriterErrors.write("Линия");
                    bufferedWriterErrors.newLine();
                    bufferedWriterErrors.write(linkedBlockingQueueВскеОшибкиДляЗаписи.get(3).toString());
                    // TODO: 14.04.2025
                    bufferedWriterErrors.flush();
                    outputStream.flush();

                   }

                // TODO: 13.04.2025
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(context.getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


}
