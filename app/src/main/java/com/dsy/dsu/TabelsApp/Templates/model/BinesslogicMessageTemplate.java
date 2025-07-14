package com.dsy.dsu.TabelsApp.Templates.model;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

public class BinesslogicMessageTemplate extends MessageTemplateIntarface {
  private Context context;
  private Activity activity;

  public BinesslogicMessageTemplate(Context context, Activity activity) {
      this.context = context;
      this.activity = activity;
  }

    /**
     * @param СообщениеДиалога
     * @param namenewtemplate
     * @param bundleFromActivityListPeoples
     */
    @Override
    public void messageNewTemplate(@NonNull String СообщениеДиалога,
                                   @NonNull String namenewtemplate,
                                   @NonNull Bundle bundleFromActivityListPeoples,
                                   @NonNull Handler getHandler) {
        try {
            int ФлагЗнака = R.drawable.icon_for_tastk_fragment_create_tastks_dostavleno;//icon_dsu1_new_customer7
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View templatesView = layoutInflater.inflate(R.layout.simpletemplates6, null);
/////////TODO создание Шаблона
            final MaterialAlertDialogBuilder materialAlertDialogBuilderNewTemplate = new MaterialAlertDialogBuilder(activity)
                    .setTitle("Шаблоны")
                    .setIcon(ФлагЗнака)
                    .setCancelable(false)
                    .setIcon( R.drawable.icon_add)
                    .setView(templatesView );
            final AlertDialog   alertDialogNewTemplate= materialAlertDialogBuilderNewTemplate.create();
            // TODO: 28.06.2025 Кнопки


            // TODO: 02.07.2025 финальные настройки Message Box
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(   alertDialogNewTemplate.getWindow().getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.gravity = Gravity.CENTER;
            alertDialogNewTemplate.getWindow().setAttributes(layoutParams);
            alertDialogNewTemplate.show();




            final Button buttonNewTempleate =     alertDialogNewTemplate.findViewById(R.id.buttonnewtemplates);
            buttonNewTempleate.setOnClickListener(     new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) throws  NullPointerException {
                    // TODO: 09.07.2025
                    alertDialogNewTemplate .dismiss();
                    alertDialogNewTemplate.cancel();

                    // TODO: 28.06.2025 Создание нового Шаблона
                    new BinesslogicNewTemplate(context,activity).newTamplate(v,namenewtemplate,    getHandler);
                    // TODO: 17.04.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }


            });



            // TODO: 28.06.2025 Кнопки
            final Button buttonExitNewTempleate =     alertDialogNewTemplate.findViewById(R.id.buttonexitemplates);
            buttonExitNewTempleate.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) throws  NullPointerException {
                    // TODO: 02.07.2025 Закрыть создание Шаблона
                    alertDialogNewTemplate.dismiss();
                    alertDialogNewTemplate.cancel();
                    // TODO: 17.04.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " addingTamplateForTabel ");
                }
            });
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




















    /**
     * @param СообщениеДиалога
     * @param namenewtemplate
     * @param bundleFromActivityListPeoples
     */
    @Override
    public void messageCompleteTemplate(@NonNull String СообщениеДиалога,
                                        @NonNull String namenewtemplate,
                                        @NonNull Bundle bundleFromActivityListPeoples,
                                        @NonNull Handler handlerAfterAddingTemplate) {
        try {
            int ФлагЗнака = R.drawable.icon_for_tastk_fragment_create_tastks_dostavleno;//icon_dsu1_new_customer7
            LayoutInflater layoutInflater = LayoutInflater.from(activity);
            View templatesView = layoutInflater.inflate(R.layout.simpletemplates5, null);
/////////TODO создание Шаблона
            final MaterialAlertDialogBuilder materialAlertDialogBuilderCompleteTemplate = new MaterialAlertDialogBuilder(activity)
                    .setTitle("Шаблон")
                    .setIcon(ФлагЗнака)
                    .setCancelable(false)
                    .setIcon( R.drawable.icon_add)
                    .setView(templatesView );
            final AlertDialog   alertDialogCompleteTemplate= materialAlertDialogBuilderCompleteTemplate.create();
            // TODO: 28.06.2025 Кнопки


            // TODO: 02.07.2025 финальные настройки Message Box
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(   alertDialogCompleteTemplate.getWindow().getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.gravity = Gravity.CENTER;
            alertDialogCompleteTemplate.getWindow().setAttributes(layoutParams);
            alertDialogCompleteTemplate.show();




            final Button buttonAddTemplateIntabel=     alertDialogCompleteTemplate.findViewById(R.id.buttonaddtabell);
            buttonAddTemplateIntabel.setOnClickListener(     new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) throws  NullPointerException {
                    // TODO: 28.06.2025 Шаблон добавить в  Табель
                    //TODO   в Табель добаляем созданный ранее Шаблон или в Табель
                        new BinesslogicAddingTemplate(context,activity).addingTemplateInTabel(v,bundleFromActivityListPeoples,handlerAfterAddingTemplate);

                    alertDialogCompleteTemplate .dismiss();
                    alertDialogCompleteTemplate.cancel();
                    // TODO: 17.04.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }
            });

            // TODO: 28.06.2025 Кнопки
            final Button buttonFindTemplate =    alertDialogCompleteTemplate.findViewById(R.id.buttoncompletetemplates);
            buttonFindTemplate.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) throws  NullPointerException {
                    // TODO: 26.06.2025
                    // TODO: 07.07.2025
                    new BinesslogiсSystemTemplate(context,activity).launchToActivityFindTempales(bundleFromActivityListPeoples,handlerAfterAddingTemplate);


                    alertDialogCompleteTemplate.dismiss();
                    alertDialogCompleteTemplate.cancel();
                    // TODO: 17.04.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                }
            });

            // TODO: 28.06.2025 Кнопки
            final Button buttonExitMessage =     alertDialogCompleteTemplate.findViewById(R.id.buttonexitemplates);
            buttonExitMessage.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) throws  NullPointerException {
                    // TODO: 02.07.2025
                    alertDialogCompleteTemplate.dismiss();
                    alertDialogCompleteTemplate.cancel();
                    // TODO: 17.04.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " addingTamplateForTabel ");
                }
            });
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");




            // TODO: 17.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    //TODO END CLASS
}
