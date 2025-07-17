package com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.BinesslogicActivityBoot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;


import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.EventsBus.MessageEvensBusPrograssBar;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;


import java.util.Optional;

public class GetComponentPrograssbar {
    // TODO: 25.09.2024
  private ProgressBar getlinearProgressIndicator;

    private   Context context;

    public GetComponentPrograssbar(ProgressBar getlinearProgressIndicator, Context context) {
        this.getlinearProgressIndicator = getlinearProgressIndicator;
        this.context = context;
    }


    public void getEventBusPrograssBar(@NonNull MessageEvensBusPrograssBar messageEvensBusPrograssBar){
        try{
            if (getlinearProgressIndicator.isAttachedToWindow()) {
                Bundle bundleGetOtServicePrograssBar = (Bundle) messageEvensBusPrograssBar.mess.getExtras();
                String Статус = bundleGetOtServicePrograssBar.getString("Статус");
                if (Статус.contains("PrograssBarOn")) {
                    Integer getПроценны = bundleGetOtServicePrograssBar.getInt("Проценны", 0);


                            // TODO: 20.03.2025 есть обработка таблиц
                            МетодВизуализацииСинхронизации(bundleGetOtServicePrograssBar, getПроценны);


                            // TODO: 26.12.2022  конец основгого кода
                            Log.d(context.getClass().getName(), "\n" + " class "
                                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                    // TODO: 26.12.2022  конец основгого кода
                    Log.d(context.getClass().getName(), "\n" + " class "
                            + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }
            }
            Log.d(context.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    @SuppressLint("NewApi")
    @MainThread
    private void МетодВизуализацииСинхронизации(@NonNull Bundle bundleCallsBackAsynsService,@NonNull  Integer getПроценны) {
        try {
            // TODO: 25.09.2024

                // TODO: 10.01.2025
                try{
                // TODO: 25.09.2024
                Drawable progressDrawable = getlinearProgressIndicator.getProgressDrawable().mutate();
                progressDrawable.setColorFilter(Color.parseColor("#000000"), android.graphics.PorterDuff.Mode.SRC_IN);
                getlinearProgressIndicator.setProgressDrawable(progressDrawable);
                    // TODO: 25.09.2024
                    String currentEventTable = null;
                    if (getПроценны>0) {
                        Integer MaxКоличествоСТрочеек = bundleCallsBackAsynsService.getInt("maxtables", 0);
                        currentEventTable = bundleCallsBackAsynsService.getString("имятаблицы", "");
                        getlinearProgressIndicator.setMax(MaxКоличествоСТрочеек);
                        // TODO: 25.09.2024
                        if (!currentEventTable.isEmpty()  ) {
                            CharSequence currentEventTableAnSync = Optional.ofNullable(getlinearProgressIndicator.getTooltipText()).orElse("");
                            if (!currentEventTable.equalsIgnoreCase(currentEventTableAnSync.toString())) {
                                Integer getProgress = getlinearProgressIndicator.getProgress() + 1;
                                Integer SecondaryProgress = getProgress + 2;
                                // TODO: 25.09.2024
                                if (getProgress > getlinearProgressIndicator.getProgress()) {
                                    getlinearProgressIndicator.setIndeterminate(false);
                                    getlinearProgressIndicator.setProgress(getProgress, true);
                                    // TODO: 25.09.2024
                                    getlinearProgressIndicator.setSecondaryProgress(SecondaryProgress);
                                    getlinearProgressIndicator.setSecondaryProgressTintList(ColorStateList.valueOf(Color.GRAY));
                                }
                                // TODO: 30.09.2024
                            }

                        }
                        getlinearProgressIndicator.setTooltipText(currentEventTable);
                    } else {
                        getlinearProgressIndicator.setIndeterminate(true);
                    }
                    getlinearProgressIndicator.requestLayout();
                    getlinearProgressIndicator.refreshDrawableState() ;


                    Log.d(this.getClass().getName(), "\n" + " class " +
                            Thread.currentThread().getStackTrace()[2].getClassName()
                            + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " getlinearProgressIndicator" + getlinearProgressIndicator.getProgress());
                // TODO: 10.01.2025
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }


            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " getlinearProgressIndicator" + getlinearProgressIndicator.getProgress());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }





    // TODO: 25.09.2024  END CLASS
}
