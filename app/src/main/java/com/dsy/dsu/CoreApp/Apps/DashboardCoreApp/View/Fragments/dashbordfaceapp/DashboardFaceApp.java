package com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.View.Fragments.dashbordfaceapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.viewpager2.widget.ViewPager2;

import com.dsy.dsu.AdmissionMaterialsApp.Window.MainActivity_AdmissionMaterials;
import com.dsy.dsu.BusinessLogicForApps.BroadcastRecievers.Bl.RegistrationPublicWorkmanager;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.BinesslogicActivityBoot.GetFinishAffinityFragment;
import com.dsy.dsu.BusinessLogicForApps.GetPublicID.QualifierPublicID;
import com.dsy.dsu.CommitPricesApp.View.Window.MainActivityCommitingPrices;

import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.LaunchActivityDiaologSettings;
import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.bl_viewpager2.BunesslogicViewPager2;
import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.changeaccessrights.DashboardGRANTorREVOKE;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import com.dsy.dsu.OrdersTransportsApp.Window.MainActivityOrdersTransports;
import com.dsy.dsu.PaysCommitApp.View.Window.MainActivity_CommitPay;
import com.dsy.dsu.R;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceUpdatesPO;
import com.dsy.dsu.TabelsApp.Tabel.ListTabels.MainActivity_List_Tabels;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.Date;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardFaceApp extends  DialogFragment  {
    // TODO: Rename parameter arguments, choose names that match

    private  BuniccessLogicFra4gmentDashboard buniccessLogicFra4gmentDashboard;
    private MaterialCardView materialcardview_dashboard=null;
    private FragmentManager fragmentManager;



    private   TableLayout tableLayout_dashboard;

    private Animation animation1,animation2,animation3,animation4,animation5;

    private MaterialButton КнопкаТабель, КнопкаСогласование ,
            КнопкаПоступлениеМатериалов,КнопкаЗаявкаНаТранспорт,
            КнопкаСогласЦен;


    private LifecycleOwner lifecycleOwner;

    private ServiceUpdatesPO.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ

    private MaterialButton imageview_to_settings ;


    @Inject
    DashboardGRANTorREVOKE dashboardGRANTorREVOKE;

    @Inject
    @QualifierPublicID
        Integer getHiltPublicId;

    @Inject
    RegistrationPublicWorkmanager registrationPublicWorkmanager;


  public    ViewPager2 pagerdachbord;
    protected LifecycleOwner getlifecycleOwner  ;


    // TODO: Rename and change types and number of parameters
    // TODO: Rename and change types and number of parameters
    public static DashboardFaceApp newInstance( ) {
        DashboardFaceApp fragment = new DashboardFaceApp();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            fragmentManager = getActivity(). getSupportFragmentManager();
            buniccessLogicFra4gmentDashboard=new BuniccessLogicFra4gmentDashboard();
            // TODO: 22.08.2023  анимауия
            animation5 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row_tabel);
            // TODO: 21.08.2023 насйтироки Разые дизайна Фрагмента
            /*setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_Dialog_Alert);//Theme_Dialog*/
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
            // setStyle(   DialogFragment.STYLE_NO_FRAME ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan);//Theme_Dialog
            //setCancelable(false);

            //setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Material_Dialog);
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Dialog_MinWidth);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_TITLE ,android.R.style.Theme_Material_Light);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_TITLE ,android.R.style.Theme_Material_Light_NoActionBar_Overscan);//Theme_Dialog
            //setStyle(   DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_Light_NoActionBar_Overscan);//Theme_Dialog

            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog  Без Часов
            //    setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_DarkActionBar);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge_NoActionBar);//Theme_Dialog   с часами сверху
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog  Без Часов
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Black_NoTitleBar );
            //  setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Light_Dialog_Presentation);
            setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Light_NoActionBar );
           // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
          // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen );
            setShowsDialog(true);
            // TODO: 15.04.2025
            getlifecycleOwner=this;
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
          //  view= inflater.inflate(R.layout.simple_dashbord_fragment_scroll, container, false);
            view= inflater.inflate(R.layout.simple_dashbord_fragment_coreapp, container, false);
           // view= inflater.inflate(R.layout.simple_dashbord_fragment_blue_deleting_before, container, false);
                   /*     ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);*/
            // ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            // ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_blue, container, false);
            //view= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            // view= inflater.inflate(R.layout.simple_dashbord_fragment_green, container, false);

            getDialog().getWindow().setStatusBarColor(Color.BLACK);
            getDialog().setCancelable(false);
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
            // TODO: 03.04.2025
            tableLayout_dashboard         = (TableLayout) view.findViewById(R.id.tableLayout_dashboard); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаЗаявкаНаТранспорт   = (MaterialButton) tableLayout_dashboard.findViewById(R.id.КнопкаЗаявкаНаТранспорт); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаСогласование         = (MaterialButton) tableLayout_dashboard.findViewById(R.id.КнопкаСогласование); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаСогласЦен         = (MaterialButton) tableLayout_dashboard.findViewById(R.id.КнопкаСогласЦен); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаПоступлениеМатериалов         = (MaterialButton) tableLayout_dashboard.findViewById(R.id.КнопкаПоступлениеМатериалов); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаТабель          = (MaterialButton) tableLayout_dashboard.findViewById(R.id.КнопкаТабель); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            imageview_to_settings      = (MaterialButton) tableLayout_dashboard.findViewById(R.id.imageview_to_settings); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            pagerdachbord = (ViewPager2) tableLayout_dashboard.findViewById(R.id.pagerdachbord);


            // TODO: 17.03.2025 Старрт Бизнес ЛОгики
            
            // TODO: 16.03.2025 ViewPager2
            new BunesslogicViewPager2(pagerdachbord,getContext(),this).getBunesslogicViewPager2();
            // TODO: 10.03.2025  биндинг обновление ПО
            new  BindingSoftwareUpdatePO().getbindingSoftwareUpdatePO();

            // TODO: 11.01.2024  ПОЛУЧАЕМ Права
            Integer getGrantRemote=    dashboardGRANTorREVOKE.getGrantRemote(getContext() ,getHiltPublicId);
            //TODO
            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " getGrantRemote " +getGrantRemote);

            dashboardGRANTorREVOKE.setGrantRemote(getContext(),
                    КнопкаЗаявкаНаТранспорт,
                    КнопкаСогласование,КнопкаСогласЦен
                    ,КнопкаПоступлениеМатериалов,
                    КнопкаТабель,getGrantRemote);

// TODO: 06.06.2025 workmanager Регистация  Public
            registrationPublicWorkmanager.registrationPublicWorkmanager();



            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
    public void onStop() {
        super.onStop();
        try{



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

    // TODO: 07.10.2023  метод первой запуска Work Manager Public и его перввая регистарция


    @Override
    public void onStart() {
        super.onStart();
        try{


            buniccessLogicFra4gmentDashboard.new ClassButtonsApp().методStartingAllButtonApp();

            buniccessLogicFra4gmentDashboard.new ClassAnimatilBackButton().методToSettingsFragment();


            new GetFinishAffinityFragment(getContext()).  finishAffinityFragment(this,fragmentManager);



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




























    // TODO: 15.08.2023  Бизнес ЛОгика Bunecees Logic DashboardCoreApp Fragment
    class BuniccessLogicFra4gmentDashboard{





        // TODO: 17.08.2023  Класс Для Компонента Табель








        // TODO: 21.08.2023 Класс с бизнес логикой Кнопки

        class ClassButtonsApp {
            void методStartingAllButtonApp() {


                // TODO: 14.04.2023 Запускаем Заявка НА Транспорт
                КнопкаЗаявкаНаТранспорт.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Log.d(this.getClass().getName(), "Запускает Согласния   ");
                            Intent ИнтентЗаявкаНаТранспорт = new Intent();
                            Bundle data = new Bundle();
                            ИнтентЗаявкаНаТранспорт.putExtras(data);
                            ИнтентЗаявкаНаТранспорт.setClass(getContext(), MainActivityOrdersTransports.class);//рабочий
                            ИнтентЗаявкаНаТранспорт.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(ИнтентЗаявкаНаТранспорт);
                            // TODO: 10.03.2025

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }


                    }
                });
// TODO: 21.08.2023 tretiy button
                КнопкаСогласование.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Log.d(this.getClass().getName(), "Запускает Согласния   ");
                            Intent intentЗапускСогласования1C = new Intent();
                            Bundle data = new Bundle();
                            intentЗапускСогласования1C.putExtras(data);
                            intentЗапускСогласования1C.setClass(getContext(), MainActivity_CommitPay.class);//рабочий
                            intentЗапускСогласования1C.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intentЗапускСогласования1C);
                            // TODO: 10.03.2025

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }


                });
                // TODO: 21.08.2023 hetbertay button

                // TODO: 14.04.2023 Запускаем Заявка НА Транспорт
                КнопкаПоступлениеМатериалов.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Log.d(this.getClass().getName(), "Запускает Согласния   ");
                            Intent ИнтентЗаявкаНаТранспорт = new Intent();
                            Bundle data = new Bundle();
                            ИнтентЗаявкаНаТранспорт.putExtras(data);
                            ИнтентЗаявкаНаТранспорт.setClass(getContext(), MainActivity_AdmissionMaterials.class);//рабочий
                            ИнтентЗаявкаНаТранспорт.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(ИнтентЗаявкаНаТранспорт);
                            // TODO: 10.03.2025

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }


                    }
                });

                // TODO: 21.08.2023 pyty abutton
                КнопкаТабель.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent Интент_ЗапускТабельногоУчётаПервыйШаг = new Intent();
                            Bundle data = new Bundle();
                            Интент_ЗапускТабельногоУчётаПервыйШаг.putExtras(data);
                            Интент_ЗапускТабельногоУчётаПервыйШаг.setClass(getContext(), MainActivity_List_Tabels.class); //  ТЕСТ КОД КОТОРЫЙ ЗАПУСКАЕТ ACTIVITY VIEWDATA  ПРОВЕРИТЬ ОБМЕН
                            Интент_ЗапускТабельногоУчётаПервыйШаг.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  );
                            Log.d(this.getClass().getName(), "" + "    КнопкаТабельныйУчёт.setOnClickListener(new View.OnClickListener() {");
                            startActivity(Интент_ЗапускТабельногоУчётаПервыйШаг);
                            // TODO: 10.03.2025
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });

// TODO: 20.12.2023  новая кнопка согласование цен
                КнопкаСогласование.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intentЗапускСогласования = new Intent();
                            Bundle data = new Bundle();
                            intentЗапускСогласования.putExtras(data);
                            intentЗапускСогласования.setClass(getContext(), MainActivity_CommitPay.class);//рабочий
                            intentЗапускСогласования.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intentЗапускСогласования);
                            // TODO: 10.03.2025

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }


                });
// TODO: 20.12.2023  новая кнопка согласование цен
                КнопкаСогласЦен.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intentЗапускСогласованиеЦены1С = new Intent();
                            Bundle data = new Bundle();
                            intentЗапускСогласованиеЦены1С.putExtras(data);
                            intentЗапускСогласованиеЦены1С.setClass(getContext(), MainActivityCommitingPrices.class);//рабочий
                            intentЗапускСогласованиеЦены1С.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intentЗапускСогласованиеЦены1С);
                            // TODO: 10.03.2025

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }


                });

                // TODO: 21.08.2023  teo button




            }
        }



        // TODO: 22.08.2023  классс который отвечает за Переход на Фрагмент НАстройки
        class ClassAnimatilBackButton{
            void методToSettingsFragment(){
                imageview_to_settings.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            // TODO Запусукаем Фргамент НАстройки  dashbord
                            new LaunchActivityDiaologSettings(fragmentManager,getContext()).launchADashboardSettings(localBinderОбновлениеПО);
                                // TODO: 01.08.2023
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


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
                });

            }
        }
        // TODO: 22.08.2023 МЕТОД ПОЛУЧЕНИЕ ДАННЫХ binder
        //TODO end Buniceess Lofic for Activity//TODO end Buniceess Lofic for Activity//TODO end Buniceess Lofic for Activity//TODO end Buniceess Lofic for Activity//TODO end Buniceess Lofic for Activity
    }//TODO end Buniceess Lofic for Activity


    class BindingSoftwareUpdatePO{


        // TODO: 03.10.2023  метод когда не биндинга
        void getbindingSoftwareUpdatePO(         ) {
            try {
                Boolean asBoolenОбновлениеПО = null;
                ServiceConnection    connectionОбновлениеПО = new ServiceConnection() {
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
/*    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        return     new MaterialAlertDialogBuilder(getContext(),android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen){
            @NonNull
            @Override
            public MaterialAlertDialogBuilder setView(View view) {
                try {
                    // TODO: 15.08.2023
                    Log.d(this.getClass().getName(),"\n" + " class "
                            + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return super.setView(view);
            }

        }
                .setTitle("ddd")
                .setCancelable(false)
                .setIcon( R.drawable.icon_newscannertwo)
                .setView(getLayoutInflater().inflate( R.layout.simple_dashbord_fragment_scroll, null )).create();




    }*/
    // TODO: 10.03.2025 end class
}