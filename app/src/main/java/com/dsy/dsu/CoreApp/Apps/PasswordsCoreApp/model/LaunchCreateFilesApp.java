package com.dsy.dsu.CoreApp.Apps.PasswordsCoreApp.model;

import android.content.Context;
import android.util.Log;


import com.dsy.dsu.BusinessLogicForApps.BootAndAsync.Model.DowloadUpdatePO.ClassCreateFolderUpdatePO;
import com.dsy.dsu.BusinessLogicForApps.CreateFolderBinatySave.ClassCreateFolderBinatyMatrilal;
import com.dsy.dsu.BusinessLogicForApps.CreateFolderBinatySave.ClassCreateFolderCommitPays1C;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_creatingfileerrors.GetCreateFileForErrors;

public class LaunchCreateFilesApp {

    private Context context;


    public LaunchCreateFilesApp(Context context) {
        this.context = context;
    }


      public      void launchCreateFilesApp(){
        try{
          // TODO: 14.08.2023 создаем папку для BinaryFile Save
            ClassCreateFolderBinatyMatrilal classCreateFolderBinatyMatrilal=
                    new ClassCreateFolderBinatyMatrilal(context) ;
            classCreateFolderBinatyMatrilal.МетодCreateFoldersBinaty();

          // TODO: 14.08.2023 создаем папку для BinaryFile CommitPay1C Соласования
            ClassCreateFolderCommitPays1C classCreateFolderCommitPays1C=
                    new ClassCreateFolderCommitPays1C(context) ;
            classCreateFolderCommitPays1C.МетодCreateFoldersBinaty();

          // TODO: 14.08.2023 создаем папку для Обновления ПО
            ClassCreateFolderUpdatePO classCreateFolderUpdatePO=
                    new ClassCreateFolderUpdatePO(context);
            classCreateFolderUpdatePO.МетодCreateFoldersBinaty();

            // TODO: 07.10.2023  create file for ERROR
            GetCreateFileForErrors getWorkerErros =new GetCreateFileForErrors(context);
            getWorkerErros.creatingFileForErrors();


          Log.d(this.getClass().getName(),"\n" + " class CoreApp    " + Thread.currentThread().getStackTrace()[2].getClassName()
                  + "\n" +
                  " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                  " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

      } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

      }

}
