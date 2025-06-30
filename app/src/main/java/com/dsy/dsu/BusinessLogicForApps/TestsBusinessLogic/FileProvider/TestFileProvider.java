package com.dsy.dsu.BusinessLogicForApps.TestsBusinessLogic.FileProvider;


// TODO: 29.08.2023  класс для любого тестирования

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestFileProvider {



    protected    class TestServer  {
        protected OkHttpClient httpClient = new OkHttpClient();
        protected MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        protected Request request;
        protected URL urltest;
        protected Response response;
        protected  Context context;
        protected HttpURLConnection connection ;

        {
            try {
                //  urltest = new URL("http://185.136.77.98:8888");
                // urltest = new URL("http://185.136.77.98:8888/jboss-1.0-SNAPSHOT/");
                urltest = new URL("http://192.168.254.40:8080/jboss-1.0-SNAPSHOT/");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        public TestServer(Context context) {
            this.context = context;
        }


        // TODO: 25.10.2023 metod connectiong public
        public   void metodTest( ){
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
        }
    }






    // TODO: 08.09.2023  клас ФайлПровайдера

    public class ClassGetFileProvider{
        protected  Context context;

        public ClassGetFileProvider( ) {
            throw  new RuntimeException();
        }

        // TODO: 08.09.2023
        public  void      методGetFileProvider(){
            try{

                File fileProvider=new File(context.getFilesDir(),"image11.jpg");
                Uri ResultFileProvider=    FileProvider.getUriForFile(context,"com.dsy.dsu.provider",fileProvider);


/*
           // Assume block needs to be inside a Try/Catch block.
           String path = Environment.getExternalStorageDirectory().toString();
           OutputStream fOut = null;
           Integer counter = 0;
           File file = new File(path, "FitnessGirl"+counter+".jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
           fOut = new FileOutputStream(file);*/

                ///Bitmap pictureBitmap = getImageBitmap(myurl); // obtaining the Bitmap
                //  pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
       /*    fOut.flush(); // Not really required
           fOut.close(); // do not forget to close the stream*/



                Log.d(this.getClass().getName(), "\n" + " class "
                        + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }




    }// TODO: 25.10.2023 end   classGetFileProvider

    // TODO: 15.11.2023







//TODO end public class AllTestsBusinessLogic //TODO end public class AllTestsBusinessLogic
}//TODO end public class AllTestsBusinessLogic //TODO end public class AllTestsBusinessLogic
