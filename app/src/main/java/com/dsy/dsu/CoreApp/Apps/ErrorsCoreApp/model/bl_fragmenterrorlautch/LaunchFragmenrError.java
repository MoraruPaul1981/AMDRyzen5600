package com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_fragmenterrorlautch;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.view.FragmentError;

import javax.annotation.Nonnull;

public class LaunchFragmenrError {

   protected   FragmentManager fragmentManager;
    protected   Context context;

    public LaunchFragmenrError(@Nonnull  FragmentManager fragmentManager,@Nonnull   Context context) {
        this.fragmentManager = fragmentManager;
        this.context = context;
    }


    public   void launchErrorFragment() {
        try {
            // TODO Запусукаем Фргамент DdshBoard
            FragmentError fragmentError = FragmentError.newInstance();
            Bundle data = new Bundle();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentManager.popBackStack();
            fragmentError.setArguments(data);
            fragmentError.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragmentError.show(fragmentManager, "FragmentError");


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }






}
