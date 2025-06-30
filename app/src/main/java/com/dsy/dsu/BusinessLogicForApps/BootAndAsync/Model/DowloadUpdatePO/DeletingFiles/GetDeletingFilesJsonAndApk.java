package com.dsy.dsu.BusinessLogicForApps.BootAndAsync.Model.DowloadUpdatePO.DeletingFiles;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;


import java.io.File;

public class GetDeletingFilesJsonAndApk   implements InDeletingFile {

Context context;

    public GetDeletingFilesJsonAndApk(Context context) {
        this.context = context;
    }

    @Override
    public void startingDeletingFileJson() {
        try {
            String PatchDeleteJsonAnalitic="SousAvtoFile/UpdatePO";
/////TODO  УДАЛЕНИЕ .JSON ФАЙЛА
            File ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии;
            if (Build.VERSION.SDK_INT >= 30) {
                // TODO: 10.04.2022
                ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии =
                        context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic);
            } else {
                ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic);
            }

            // TODO: 10.04.2022
            File[] Files = ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии.listFiles();

            if (Files!=null) {
                for (int i = 0; i < Files.length; i++) {
                    String ИмяФайла = Files[i].getName();
                    // TODO: 10.04.2022//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    boolean ФайлУдаленияJson = ИмяФайла.trim().matches("(.*)update_dsu1(.*)") &&
                            ИмяФайла.trim().matches("(.*)json(.*)") ;//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    // TODO: 15.04.2024 deleting file
                    if(ФайлУдаленияJson==true){
                        Files[i].delete();
                        Log.d(this.getClass().getName(), "ФайлУдаленияJson" + ФайлУдаленияJson );
                    }
                }
            }
            Log.d(this.getClass().getName(), "Files" + Files);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), " ошибка  faceapp из меню МетодДополнительногоУдалениеФайлов Обновление ПО ");
        }
    }

    @Override
    public void startingDeletingFileApk() {
        try {
            String PatchDeleteJsonAnalitic="SousAvtoFile/UpdatePO";
/////TODO  УДАЛЕНИЕ .JSON ФАЙЛА
            File ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии;
            if (Build.VERSION.SDK_INT >= 30) {
                // TODO: 10.04.2022
                ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии =
                        context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic);
            } else {
                ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic);
            }

            // TODO: 10.04.2022
            File[] Files = ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии.listFiles();

            if (Files!=null) {
                for (int i = 0; i < Files.length; i++) {
                    String ИмяФайла = Files[i].getName();
                    // TODO: 10.04.2022//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    boolean ФайлУдаленияJson = ИмяФайла.trim().matches("(.*)apk(.*)") &&
                            ИмяФайла.trim().matches("(.*)update_dsu1(.*)") ;//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    // TODO: 15.04.2024 deleting file
                    if(ФайлУдаленияJson==true){
                        Files[i].delete();
                        Log.d(this.getClass().getName(), "ФайлУдаленияJson" + ФайлУдаленияJson );
                    }
                }
            }
            Log.d(this.getClass().getName(), "Files" + Files);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), " ошибка  faceapp из меню МетодДополнительногоУдалениеФайлов Обновление ПО ");
        }
    }
}
