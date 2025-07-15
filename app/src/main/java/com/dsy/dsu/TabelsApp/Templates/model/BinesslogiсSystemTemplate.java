package com.dsy.dsu.TabelsApp.Templates.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.TabelsApp.Peoples.MainActivityListPeoples;
import com.dsy.dsu.TabelsApp.Templates.view.MainActivityTemplatesFindPeoples;

public class BinesslogiсSystemTemplate  implements  SystemTemplateIntarface {
  private Context context;
  private Activity activity;

  public BinesslogiсSystemTemplate(Context context, Activity activity) {
      this.context = context;
      this.activity = activity;
  }

    /**
     * @param bundlebackActivitylistPeoples
     */
    @Override
    public void backToActivityListTabels(@NonNull Bundle bundlebackActivitylistPeoples) {
        try{
            Intent ИнтентBackToActivityListTabels = new Intent(activity, MainActivityListPeoples.class);
            ИнтентBackToActivityListTabels.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ИнтентBackToActivityListTabels.putExtras(bundlebackActivitylistPeoples);
            activity. startActivity( ИнтентBackToActivityListTabels);
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].
                    getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2]
                            .getLineNumber());
        }
    }

    /**
     * launchToActivityFindTempales
     */
    @Override
    public void launchToActivityFindTempales(@NonNull Bundle bundleFindTemplate,  @NonNull Handler handlerAfterAddingTemplate) {
        try{
            Intent  intentActivityFindTemplate = new Intent(activity, MainActivityTemplatesFindPeoples.class);
            intentActivityFindTemplate.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentActivityFindTemplate.putExtras(bundleFindTemplate);
            activity. startActivity( intentActivityFindTemplate);
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].
                    getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].
                            getLineNumber());
        }
    }









    //TODO END CLASS
}
