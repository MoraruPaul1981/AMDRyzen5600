package com.dsy.dsu.BusinessLogicForApps.BootAndAsync.Model.BinesslogicActivityBoot;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dsy.dsu.BusinessLogicForApps.BootAndAsync.View.BootFragment;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceUpdatesPO;

import org.jetbrains.annotations.NotNull;

public class LaunchActivityFragmentBoot {

  private   FragmentManager fragmentManager;
    private  Context context;

    public LaunchActivityFragmentBoot(  FragmentManager fragmentManager, Context context) {
        this.fragmentManager = fragmentManager;
        this.context = context;
    }


    public void launchBootFragment() {
        try {
            // TODO Запусукаем Фргамент DdshBoard
            BootFragment bootFragment = BootFragment.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle data = new Bundle();
            bootFragment.setArguments(data);
            fragmentTransaction.addToBackStack(null);
            bootFragment.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
                    | FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            bootFragment.show(fragmentManager, "BootFragment");
            // TODO: 17.04.2025
            fragmentTransaction.commit();
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

    public   void launchBootFragment(     @NotNull ServiceUpdatesPO.localBinderОбновлениеПО localBinderОбновлениеПО) {
        try {
            // TODO Запусукаем Фргамент DdshBoard
            BootFragment bootFragment = BootFragment.newInstance();
            Bundle data = new Bundle();
            FragmentTransaction   fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentManager.popBackStack();
            bootFragment.setArguments(data);
            fragmentTransaction.setPrimaryNavigationFragment(bootFragment);
            bootFragment.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            bootFragment.show(fragmentManager, "BootFragment");
            fragmentTransaction.commit();

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
