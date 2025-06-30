package com.dsy.dsu.OrdersTransportsApp.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceUpdatesPO;

import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivityOrdersTransports extends AppCompatActivity {
    // TODO: 25.04.2023 Переменные
    private Activity activity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment_СозданиеЗаказаТранспорта;
    private LinearLayout linearLayout_root_activity_main;

    private ServiceUpdatesPO.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ
    private   SubClassStartingFragmentOrderTran subClassStartingFragmentOrderTran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_main_order_transport);
        // TODO: 25.04.2023  ЗАКАЗ Транспорта
            activity=this;
            fragmentManager = getSupportFragmentManager();
            getSupportActionBar().hide(); ///скрывать тул бар
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            linearLayout_root_activity_main =  (LinearLayout) findViewById(R.id.linearLayout_root_activity_main);
            ViewGroup.LayoutParams params = linearLayout_root_activity_main.getLayoutParams();
            params.height= ViewGroup.LayoutParams.WRAP_CONTENT;
            linearLayout_root_activity_main.setLayoutParams(params);
            // ani = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row);
            // TODO: 26.04.2023 Запускаем Ордер Транпорта
            subClassStartingFragmentOrderTran=new SubClassStartingFragmentOrderTran();
            subClassStartingFragmentOrderTran.методЗапускаФрагментаОрдерТранспорта();
            subClassStartingFragmentOrderTran.  методСлушательCallsBackFragmentManagers();
            // TODO: 11.05.2023 Горизотнтальная Прокрутка
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }






    // TODO: 26.04.2023  класс для запуска
    class  SubClassStartingFragmentOrderTran{


    // TODO: 26.04.2023  класс для ЗАПУСКА ЗАКАЗАТЬ НОВЫЙ ТРАНСПОРТ

    protected void методЗапускаФрагментаОрдерТранспорта() {
        try{
            fragmentManager.clearBackStack(null);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right);
            fragment_СозданиеЗаказаТранспорта = new FragmentOrderTransportOneChane();
            fragmentTransaction.add(R.id.linearLayout_root_activity_main,
                            fragment_СозданиеЗаказаТранспорта)
                    .setPrimaryNavigationFragment(fragment_СозданиеЗаказаТранспорта)
                    .setReorderingAllowed(true).commit();//.layout.activity_for_fragemtb_history_tasks
            fragmentTransaction.show(fragment_СозданиеЗаказаТранспорта);
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   fragment_СозданиеЗаказаТранспорта " + fragment_СозданиеЗаказаТранспорта);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).
                    recordnewerror(e.toString(),
                            this.getClass().getName().toString(),
                            Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

        private void методСлушательCallsBackFragmentManagers() {
        try{
            fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                @Override
                public void onFragmentPreAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
                    super.onFragmentPreAttached(fm, f, context);
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "   fragment_СозданиеЗаказаТранспорта " + fragment_СозданиеЗаказаТранспорта);
                }

                @Override
                public void onFragmentStopped(@NonNull FragmentManager fm, @NonNull Fragment f) {
                    super.onFragmentStopped(fm, f);
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "   fragment_СозданиеЗаказаТранспорта " + fragment_СозданиеЗаказаТранспорта);
                }

                @Override
                public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                    super.onFragmentViewDestroyed(fm, f);
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "   fragment_СозданиеЗаказаТранспорта " + fragment_СозданиеЗаказаТранспорта);
                }

                @Override
                public void onFragmentDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                    super.onFragmentDestroyed(fm, f);
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "   fragment_СозданиеЗаказаТранспорта " + fragment_СозданиеЗаказаТранспорта);
                }

                @Override
                public void onFragmentDetached(@NonNull FragmentManager fm, @NonNull Fragment f) {
                    super.onFragmentDetached(fm, f);
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "   fragment_СозданиеЗаказаТранспорта " + fragment_СозданиеЗаказаТранспорта);
                }
            },true);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).
                    recordnewerror(e.toString(),
                            this.getClass().getName().toString(),
                            Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }




}



}