package com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.View.Fragments.fragmentsettings;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.multidex.BuildConfig;

import com.dsy.dsu.BusinessLogicForApps.WorkManagers.binesslogic.CreateSingleWorkManager;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.EventsBus.MessageEvensBusNetworkStatuses;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.EventsBus.MessageEvensBusUpdatePO;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.BinesslogicActivityBoot.GetComponentActivityBootService;


import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicForApps.GetClearDataUserAnCnahgeData;
import com.dsy.dsu.BusinessLogicForApps.GetPingServers.GetPingServerJboss;
import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.bl_launchFragmentSettingsandDashbord.LaunchActivityDashboard;
import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.endingasynsdashboard.LaunchMainAppAfterSyncing;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_fragmenterrorlautch.LaunchFragmenrError;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossHilt.intarfaces.QualifierPortJboss;
import com.dsy.dsu.BusinessLogicForApps.Hilt.getSSLSocketFactory2.QualifiergetsslSocketFactory2;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceUpdatesPO;
import com.dsy.dsu.CoreApp.Apps.SettingsCoreApp.View.MainActivity_Settings;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.rxbinding4.view.RxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;
import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.subjects.Subject;
import kotlin.Unit;

/**
 * AHilt simple {@link Fragment} subclass.
 * Use the {@link DashboardFragmentSettings#newInstance} factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
public class DashboardFragmentSettings extends  DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    private  ClassBiznesLogikaSettings classBiznesLogikaSettings;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private AlertDialog DialogBox=null;
    private  Handler handlerAsync;
    private MaterialButton КнопкаоСистеме, КнопкаСменыПользователи,  КнопкаОбменДанными, КнопкаОбновлениеПО,КнопкаОшибки;
    private Animation  animation6;
    private TextView TextViewLogo;
    private LifecycleOwner lifecycleOwner;
    private MaterialButton bottonBack;
    private ServiceUpdatesPO.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ
    private Subject<ServiceUpdatesPO.localBinderОбновлениеПО> UpdatePublish;
    private   ServiceConnection  connectionОбновлениеПО;
    private   GetComponentActivityBootService blInnerMainActivityBootAndAsync;

    private SharedPreferences preferences;

    @Inject
    @QualifiergetsslSocketFactory2
    protected   SSLSocketFactory getsslSocketFactory2;

    @Inject
    @QualifierPortJboss
    protected LinkedHashMap<Integer,String> getHiltPortJboss;




    // TODO: Rename and change types and number of parameters
    public static DashboardFragmentSettings newInstance( ) {
        DashboardFragmentSettings fragment = new DashboardFragmentSettings();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            lifecycleOwner=getActivity();

            fragmentManager = getActivity(). getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            // TODO: 22.08.2023  настйроки анимацуии
            animation6 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row9);

            classBiznesLogikaSettings=new ClassBiznesLogikaSettings();
            // TODO: 17.08.2023 inizial message
            classBiznesLogikaSettings.  МетодИнициализацияHandler();

            preferences = getContext().getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            // TODO: 27.12.2024

// TODO: 27.12.2024 Инициализирукм Конструктор Класса для запуска Обновление ПО
            blInnerMainActivityBootAndAsync=new GetComponentActivityBootService(getsslSocketFactory2,getActivity(),getContext() ,lifecycleOwner);

            //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_Dialog_Alert);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Dialog_Alert);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_Dialog
            //setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Panel);//Theme_Dialog
            //  setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_InputMethod);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Panel);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Panel);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Settings);//Theme_Dialog
            //setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_FRAME | DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            //  setStyle(  DialogFragment.STYLE_NO_FRAME | DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            //    setStyle(   DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            //  setStyle(   DialogFragment.STYLE_NO_INPUT ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_FRAME ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            //setCancelable(false);
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog  Без Часов
            //    setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_DarkActionBar);//Theme_Dialog
            //  setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge_NoActionBar);//Theme_Dialog   с часами сверху
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog  Без Часов
          /*    setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge_NoActionBar);//Theme_Dialog   с часами сверху

            setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Black_NoTitleBar );*/
            setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Light_NoActionBar );
            /*          setCancelable(false);*/
            setShowsDialog(true);
            // TODO: 15.08.2023
            Log.d(this.getClass().getName(),"\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=null;
        try{
       /*     ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);*/
            // ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            // ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_blue, container, false);
            //  view= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            view= inflater.inflate(R.layout.simple_dashbord_fragment_grey_materialdisign_4, container, false);
            // TODO: 19.02.2025
            MaterialCardView  materialcardview_settings         = (MaterialCardView) view.findViewById(R.id.materialcardview_settings); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаоСистеме   = (MaterialButton) materialcardview_settings.findViewById(R.id.КнопкаоСистеме); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаСменыПользователи = (MaterialButton) materialcardview_settings.findViewById(R.id.КнопкаПользователи); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаОбменДанными         = (MaterialButton) materialcardview_settings.findViewById(R.id.КнопкаОбменДанными); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаОбновлениеПО = (MaterialButton) materialcardview_settings.findViewById(R.id.Кнопкаобновление); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаОшибки          = (MaterialButton) materialcardview_settings.findViewById(R.id.КнопкаОшибки); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            TextViewLogo      = (TextView) materialcardview_settings.findViewById(R.id.TextViewLogo); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            bottonBack = (MaterialButton) materialcardview_settings.findViewById(R.id.imageButton_back_in_settings); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА

            getDialog().getWindow().setStatusBarColor(Color.BLACK);
            // TODO: 21.06.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  view; //TODO inflater.inflate(R.layout.activity_main__tabel_four_colums, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);

            // TODO: 22.08.2023 код после ИНИЦИАЛИЗАЦИИ КНОПОК
            classBiznesLogikaSettings.методНастройкиВнешнегоВида();

            // TODO: 22.08.2023  дейстия кнопок TASK
            classBiznesLogikaSettings.new ClassAllTaskButtons().new SubClassoSystem().методТаскоСистемы();
            classBiznesLogikaSettings.new ClassAllTaskButtons().new SubClassAsyncVisual().ЗапускСинхрониазцииИзФрагмента() ;
            classBiznesLogikaSettings.new ClassAllTaskButtons().new SubClassChangeDataUsers().методСменыДанныхПользователя(); ;
            classBiznesLogikaSettings.new ClassAllTaskButtons().new ClassUpdatePO().методОбновлениеПО(); ;
            classBiznesLogikaSettings.new ClassAllTaskButtons().new ClassTaskError( ).методtaskError() ;
            // TODO: 10.03.2025  биндинг обновление ПО
            new BindingSoftwareUpdatePO().getbindingSoftwareUpdatePO();

            registeEventBusFirst(this);

            // TODO: 17.08.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }





    @Override
    public void onStart() {
        super.onStart();
        try{
            classBiznesLogikaSettings.new ClassAnimatilBackButton().методToSettingsFragment();
            // TODO: 20.07.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " localBinderОбновлениеПО " +localBinderОбновлениеПО  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    @Override
    public void onStop() {
        super.onStop();
        try{
            try{
                unregisterEventBusFirst(this);
                // TODO: 17.08.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
            }
            // TODO: 17.08.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }
    }





    // TODO: 23.01.2024 EventBus for Update PO
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventMessageEvensBusUpdatePO(MessageEvensBusUpdatePO messageEvensBusUpdatePO){
        try{


            blInnerMainActivityBootAndAsync   .getEventBusUpdatePo(messageEvensBusUpdatePO,getHiltPortJboss);

            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   starting... onRestart" + " starting... onRestart");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }


    // TODO: 15.08.2023  Бизнес Логика Фрагмета Настройки
    class ClassBiznesLogikaSettings{
        // TODO: 15.08.2023 тест код для 1С
        private void методНастройкиВнешнегоВида() {
            try{

                // TODO: 21.08.2023  ЛОГОТИП
                TextViewLogo.startAnimation(animation6);
                TextViewLogo.refreshDrawableState();
                // TODO: 20.07.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }







        private void МетодИнициализацияHandler() {
            try{
                handlerAsync=new Handler(Looper.getMainLooper()){


                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                    }

                    @Override
                    public void dispatchMessage(@NonNull Message msg) {
                        super.dispatchMessage(msg);
                        try {
                            Bundle bundleCallsBackAsynsService=msg.getData();
                            switch (msg.what){
                                case 20:// процеессе

                                    final Object ТекущаяВерсияПрограммы = BuildConfig.VERSION_CODE;
                                    Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());

                                    Toast toast = Toast.makeText(getContext(), "Последняя версия ПО !!! " +"("+ЛокальнаяВерсияПОСравнение.toString()+")", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM |Gravity.CENTER_HORIZONTAL, 50, 0);
                                    toast.show();



                                    Log.d(this.getClass().getName(), "\n" + " class " +
                                            Thread.currentThread().getStackTrace()[2].getClassName()
                                            + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                    break;

                                case 34:// процеессе

                                    Toast toast3 = Toast.makeText(getContext(), "Нет связи c Cервер ПО !!!", Toast.LENGTH_LONG);
                                    toast3.setGravity(Gravity.BOTTOM, 0, 40);
                                    toast3.show();

                                    Log.d(this.getClass().getName(), "\n" + " class " +
                                            Thread.currentThread().getStackTrace()[2].getClassName()
                                            + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                    break;
                            }


                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName()
                                    + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }


                    }
                };

                Log.i(getContext().getClass().getName(),  " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }









        // TODO: 22.08.2023  классс который отвечает за Переход на Фрагмент НАстройки
        class ClassAnimatilBackButton{
            void методToSettingsFragment(){
                bottonBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            // TODO Запусукаем  Фрагмент Главный Экран
                            LaunchActivityDashboard launchActivityDashboard=new LaunchActivityDashboard( fragmentManager,getContext());
                            // TODO: 27.03.2024 в зависомсти кто вызвает
                            launchActivityDashboard.     launchADashboardFragment(localBinderОбновлениеПО);

                            // TODO: 01.08.2023
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });


            }


        }


        // TODO: 22.08.2023 Класс для всех Тask Кнопок     // TODO: 22.08.2023 Класс для всех Тask Кнопок
        // TODO: 22.08.2023 Класс для всех Тask Кнопок    // TODO: 22.08.2023 Класс для всех Тask Кнопок
        class ClassAllTaskButtons{
            // TODO: 22.08.2023  класс для Таск О Системы
            class SubClassoSystem {
                void методТаскоСистемы ( ){
                    КнопкаоСистеме.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try{
                                Intent Интент_Меню = new Intent(getContext(), MainActivity_Settings.class);
                                Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  );
                                startActivity(Интент_Меню);

                                Log.i(getContext().getClass().getName(),  " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    });


                }

            }














            // TODO: 22.08.2023  Сласс для Запуска Синхрониазциии  из Фрагнмета Настройки


            class SubClassAsyncVisual{
                private void ЗапускСинхрониазцииИзФрагмента() {


                    RxView.clicks(  КнопкаОбменДанными)
                            .throttleFirst(3, TimeUnit.SECONDS)
                            .filter(s -> !s.toString().isEmpty())
                            .map(new Function<Unit, Object>() {
                                @Override
                                public Object apply(Unit unit) throws Throwable {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                                    return    КнопкаОбменДанными;
                                }
                            })
                            .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Throwable {
                                    throwable.printStackTrace();
                                    Log.e(getContext().getClass().getName(),
                                            "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            })
                            .onErrorComplete(new Predicate<Throwable>() {
                                @Override
                                public boolean test(Throwable throwable) throws Throwable {
                                    throwable.printStackTrace();
                                    Log.e(getContext().getClass().getName(),
                                            "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    return false;
                                }
                            })
                            .subscribe( bottomSync-> {

                                Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                                // TODO: 24.01.2024  запуска синхрониахции из фрагмента настройкит
                                startingPoUpdateFromFragmentSettings();

                            });


                }

                private void startingPoUpdateFromFragmentSettings() {
                    try{
                        ///todo revboot
                         final     AtomicReference<ProgressDialog>   progressDialogДляСинхронизации = new AtomicReference<>();
                        progressDialogДляСинхронизации.getAndSet(new ProgressDialog(getActivity()));

                        // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
                        // TODO: 16.12.2021 НЕПОСРЕДСТВЕННЫЙ ПИНГ СИСТЕНМ ИНТРЕНАТ НА НАЛИЧЕНИ СВАЗИ С БАЗОЙ SQL SERVER
                        Boolean   СтатусРаботыСервера =
                                new GetPingServerJboss(getContext()).
                                        pingServerJbossSuccessfulOrNot( getsslSocketFactory2,getHiltPortJboss);
                        if (СтатусРаботыСервера) {
                            handlerAsync.post(() -> {
                                progressDialogДляСинхронизации.get().setTitle("Обмен данными");
                                progressDialogДляСинхронизации.get().setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                progressDialogДляСинхронизации.get().setProgress(0);
                                progressDialogДляСинхронизации.get().setCanceledOnTouchOutside(false);
                                progressDialogДляСинхронизации.get().setMessage("В процессе ....");
                                progressDialogДляСинхронизации.get().setCancelable(false);
                                progressDialogДляСинхронизации.get().show();

                            });
                            handlerAsync.postDelayed(() -> {
                                progressDialogДляСинхронизации.get().dismiss();
                                progressDialogДляСинхронизации.get().cancel();
                            }, 3000);

                            // TODO: 10.07.2023  запуск Синхрониаию
                            // TODO: 03.03.2025 Запускем Синхрнонизацию через Singlw Work manager
                            new CreateSingleWorkManager(getContext()).getcreateSingleWorkManager( "lanchOnlyAsync");
                            // TODO: 26.06.2022
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                            // TODO: 14.12.2023


                        }else {
                            Snackbar.make(КнопкаОбменДанными, "Нет сети !!!",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

                    }
                }


                //TODO end SubClassAsyncVisual
            }//TODO end SubClassAsyncVisual //TODO end SubClassAsyncVisual


            // TODO: 22.08.2023  Класс для смены Данных Пользователя на Другие
            class SubClassChangeDataUsers{
                @UiThread
                protected void методСменыДанныхПользователя() {
                    КнопкаСменыПользователи.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                // TODO: 16.12.2021 НЕПОСРЕДСТВЕННЫЙ ПИНГ СИСТЕНМ ИНТРЕНАТ НА НАЛИЧЕНИ СВАЗИ С БАЗОЙ SQL SERVER
                                Boolean   СтатусРаботыСервера =
                                        new GetPingServerJboss(getContext()).
                                                pingServerJbossSuccessfulOrNot( getsslSocketFactory2, getHiltPortJboss);

                                if (СтатусРаботыСервера == true) {
                                    String ПолученыйТекущееИмяПользователя = new CoreBinessLogics(getContext())
                                            .МетодПолучениеИмяСистемыДляСменыПользователя(getActivity());

                                    MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(getActivity());
                                    materialAlertDialogBuilder.setTitle("Смена данных");
                                    materialAlertDialogBuilder.setMessage("Данные будут удалены" + "\n"
                                            + " (текущий пользователь : ) " + ПолученыйТекущееИмяПользователя.toUpperCase());
                                    materialAlertDialogBuilder.setIcon(R.drawable.icon_dsu1_web_success);
                                    materialAlertDialogBuilder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            try {
                                                // TODO: 24.04.2023  запуск смены Пользоватедя Данные
                                                ProgressDialog prograssbarСменаДанныхПользователя = new ProgressDialog(getActivity());
                                                prograssbarСменаДанныхПользователя.setTitle("Смена данных");
                                                prograssbarСменаДанныхПользователя.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                                prograssbarСменаДанныхПользователя.setProgress(0);
                                                prograssbarСменаДанныхПользователя.setCanceledOnTouchOutside(false);
                                                prograssbarСменаДанныхПользователя.setMessage("в процессе...");
                                                prograssbarСменаДанныхПользователя.show();


                                                // TODO: 17.05.2025  Запускае Очистка таблиц
                                                GetClearDataUserAnCnahgeData clearDataUserAnCnahgeDataWorkerTable      =      new GetClearDataUserAnCnahgeData(getActivity());
                                                clearDataUserAnCnahgeDataWorkerTable .clearTableWorkerUsers(getContext(),getActivity(),prograssbarСменаДанныхПользователя );


                                                // TODO: 29.05.2025  Очистка Системных Таблиц
                                                GetClearDataUserAnCnahgeData clearDataUserAnCnahgeDataТаблицыSystem = new GetClearDataUserAnCnahgeData(getActivity());
                                                clearDataUserAnCnahgeDataТаблицыSystem.clearTableSystemUsers(getContext());


                                                // TODO: 05.06.2025  После Очисты Данных ЗАпускаем Активити Password
                                                LaunchMainAppAfterSyncing launchMainAppaftersyncing = new LaunchMainAppAfterSyncing(getActivity());
                                                launchMainAppaftersyncing.appAfterSyncingActivityPassword(getActivity(), "ПовторныйЗапускСинхронизации");


                                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                                                Log.d(this.getClass().getName(), "\n" + " class " +
                                                        Thread.currentThread().getStackTrace()[2].getClassName()
                                                        + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                        + " Линия  :"
                                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            }

                                        }
                                    });
                                    materialAlertDialogBuilder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            dialog.cancel();
                                            dialog.dismiss();
                                            Log.d(this.getClass().getName(), "\n" + " class " +
                                                    Thread.currentThread().getStackTrace()[2].getClassName()
                                                    + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                        }
                                    });
                                    materialAlertDialogBuilder.setIcon(R.drawable.icon_change_user1);

// TODO: 22.08.2023
                                    if(    DialogBox==null ){
                                        DialogBox=  materialAlertDialogBuilder.show();
                                    }else {
                                        if(!DialogBox.isShowing()){
                                            DialogBox=  materialAlertDialogBuilder.show();
                                        }
                                    }

                                } else {
                                    Toast.makeText(getContext(), "Для смены данных, нужно подключение к серверу !!! "
                                            + "\n", Toast.LENGTH_LONG).show();
                                }

                                Log.d(this.getClass().getName(), "\n" + " class " +
                                        Thread.currentThread().getStackTrace()[2].getClassName()
                                        + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }

                        }
                    });


                }



//TODO END CLASS SubClassChangeDataUsers//TODO END CLASS SubClassChangeDataUsers//TODO END CLASS SubClassChangeDataUsers
            }//TODO END CLASS SubClassChangeDataUsers//TODO END CLASS SubClassChangeDataUsers


            // TODO: 23.08.2023 \ Class EntityMaterialBinary Обновление ПО
            class  ClassUpdatePO{
                void методОбновлениеПО(){
                    try{
                        RxView.clicks(КнопкаОбновлениеПО)
                                .throttleFirst(3, TimeUnit.SECONDS)
                                .filter(s -> !s.toString().isEmpty())
                                .map(new Function<Unit, Object>() {
                                    @Override
                                    public Object apply(Unit unit) throws Throwable {
                                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                                        return КнопкаОбновлениеПО;
                                    }
                                })
                                .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Throwable {
                                        throwable.printStackTrace();
                                        Log.e(getContext().getClass().getName(),
                                                "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                })
                                .onErrorComplete(new Predicate<Throwable>() {
                                    @Override
                                    public boolean test(Throwable throwable) throws Throwable {
                                        throwable.printStackTrace();
                                        Log.e(getContext().getClass().getName(),
                                                "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        return false;
                                    }
                                })
                                .subscribe( buttonUpdatePo-> {

                                    Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                    v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));

// TODO: 10.07.2023  запуск обновление ПО
                                    // TODO: 16.12.2021 НЕПОСРЕДСТВЕННЫЙ ПИНГ СИСТЕНМ ИНТРЕНАТ НА НАЛИЧЕНИ СВАЗИ С БАЗОЙ SQL SERVER
                                    Boolean   СтатусРаботыСервера =
                                            new GetPingServerJboss(getContext()).
                                                    pingServerJbossSuccessfulOrNot( getsslSocketFactory2,getHiltPortJboss);
// TODO: 10.07.2023  запуск обновление ПО
                                    if (СтатусРаботыСервера) {
                                        // TODO: 03.03.2025 Запускем Синхрнонизацию через Singlw Work manager
                                        new CreateSingleWorkManager(getContext()).getcreateSingleWorkManager( "lanchOnlyUpdatePO");//"lanchUpdatePO"  BootService    // TODO: 03.03.2025 Запускем Синхрнонизацию через Singlw Work manager
                                       // new CreateSingleWorkManager(context).getcreateSingleWorkManager("BootService");//"lanchUpdatePO"
                                    } else {
                                        Snackbar.make(КнопкаОбновлениеПО, "Нет сети !!!",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                                    }

                                    Log.i(this.getClass().getName(), " Из меню установкаОбновление ПО "
                                            + Thread.currentThread().getStackTrace()[2].getMethodName()
                                            + " время " + new Date().toLocaleString());


                                });


                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }


                }
                //TODO END   class  ClassUpdatePO
            }//TODO END   class  ClassUpdatePO


            // TODO: 23.08.2023  КЛАСС EntityMaterialBinary Ошибки
            class ClassTaskError{
                // TODO: 23.08.2023  metod error
                void  методtaskError(){
                    КнопкаОшибки.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {

                                // TODO Запусукаем  Фрагмент Главный Экран
                                LaunchFragmenrError launchFragmenrError =new LaunchFragmenrError( fragmentManager,getContext());
                                // TODO: 27.03.2024 в зависомсти кто вызвает
                                launchFragmenrError.     launchErrorFragment();

                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    });


                }
//TODO END  class ClassTaskError
            }//TODO END  class ClassTaskError






// TODO: 04.10.2023  binder class
            //TODO end class ClassAllTaskButtons
        }//TODO end class ClassAllTaskButtons

    }//TODO end Buniceess Lofic for Activity




    class BindingSoftwareUpdatePO{


        // TODO: 03.10.2023  метод когда не биндинга
        void getbindingSoftwareUpdatePO(         ) {
            try {
                Boolean asBoolenОбновлениеПО = null;
                connectionОбновлениеПО = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            if (service.isBinderAlive()) {
                                // TODO: 28.07.2023  Update
                                localBinderОбновлениеПО = (ServiceUpdatesPO.localBinderОбновлениеПО) service;
                            }

                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                                    + "localBinderОбновлениеПО " + localBinderОбновлениеПО);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }


                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        try {
                            localBinderОбновлениеПО = null;
                            Log.i(getContext().getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                };
                Intent intentЗапускСлужбыОбновлениеПО = new Intent(getContext(), ServiceUpdatesPO.class);
                intentЗапускСлужбыОбновлениеПО.setAction("com.ServiceUpdatesPO");
                asBoolenОбновлениеПО = getContext().bindService(intentЗапускСлужбыОбновлениеПО, connectionОбновлениеПО, Context.BIND_AUTO_CREATE);
                // TODO: 28.04.2023
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " asBoolenОбновлениеПО " + asBoolenОбновлениеПО);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
            }

        }




    }


    // TODO: 23.01.2024 EventBus for Status
    @Subscribe (threadMode = ThreadMode.MAIN_ORDERED)
    public void EventMessageEvensBusAyns(MessageEvensBusNetworkStatuses messageEvensBusNetworkStatuses){
        try{

            blInnerMainActivityBootAndAsync .getEventBusNetworkStatuses(messageEvensBusNetworkStatuses);

            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   starting... onRestart" + " starting... onRestart");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    public void registeEventBusFirst(@NonNull DashboardFragmentSettings dashboardFragmentSettings) throws Exception {

        if (  !EventBus.getDefault().isRegistered(dashboardFragmentSettings)) {
            EventBus.getDefault().register(dashboardFragmentSettings);
        }
        Log.d(dashboardFragmentSettings.getContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                " registeEventBusFirst bootFragment  " +dashboardFragmentSettings);
    }

    public void unregisterEventBusFirst(@NonNull DashboardFragmentSettings dashboardFragmentSettings) throws Exception {
        if (  EventBus.getDefault().isRegistered(dashboardFragmentSettings)) {
            EventBus.getDefault().unregister(dashboardFragmentSettings);
        }
        Log.d(dashboardFragmentSettings.getContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                + "   unregisterEventBusFirst bootFragment "+dashboardFragmentSettings);
    }

// TODO: 10.03.2025  END
}