package com.dsy.dsu.TabelsApp.Tabel.ListTabels;

import static java.util.Locale.setDefault;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;

import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.bl_launchFragmentSettingsandDashbord.LaunchActivityDashboard;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import com.dsy.dsu.R;
import com.dsy.dsu.TabelsApp.Peoples.listpeoples.view.MainActivityListPeoples;
import com.dsy.dsu.TabelsApp.Tabel.New.MainActivityNewTabels;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.sous.backasync.launch.ModuleQuety;


import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;




@AndroidEntryPoint
public class MainActivity_List_Tabels extends AppCompatActivity  {
    private  Spinner СпинерВыборДату;/////спинеры для создание табеляСпинерТабельДепратамент
    private  GridView gridViewAllTabes;
    private   ProgressDialog progressDialogДляУдаления;
    private    Configuration config;
    private   Context context;
    private   Button backFaceApp;
    private  TextView textViewКоличествоТабелей;
    private  FloatingActionButton КруглаяКнопкаСамТабель;
    private  Activity activity;
    private Long MainParentUUIDFromTabel =0l;
    private SharedPreferences sharedPreferencesХранилище;
    private  Animation     animation;
    private  Animation     animationvibr1;
    private   int getMonth;
    private  int getYear;
    private   int getDigitalNameCFO;
    private  String getDateOnlySpinnerDown;
    private String FullNameCFO;
    private  LinkedList< String> МассивДляВыбораВСпинерДатаArray=new  LinkedList< String>();
    private  LinkedList< Long> МассивДляВыбораВСпинореMainUUID=new  LinkedList< Long>();
    private  Message message;
    private    DatePickerDialog ДатаДляКалендаря;
    private FragmentManager fragmentManager;
    private    SimpleCursorAdapter simpleCursorAdapterAllTAbels;
    private   Cursor getMainCursorListTabels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
        super.onCreate(savedInstanceState);
           // setContentView(R.layout.activity_main__historytabely);
            setContentView(R.layout.activity_main__historytabely_materialview);
            activity=this;
            context =this;
            getSupportActionBar().hide(); ///скрывать тул бар
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);


            gridViewAllTabes = (GridView) findViewById(R.id.gridViewAllTabes); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            fragmentManager =   getSupportFragmentManager();
        //todo кнопка назад
        backFaceApp = findViewById(R.id.КонопкаНазадСтрелкаВсеТабеля);
        textViewКоличествоТабелей= findViewById(R.id.textViewКоличествоТабелей);
        СпинерВыборДату=(Spinner) findViewById(R.id.СпинерТабельМесяцИсториииТабелей);

            animationvibr1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_singletable2);
               animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_row_tabellist);
         КруглаяКнопкаСамТабель = findViewById(R.id.КруглаяКнопкаСамТабель);//////КНОПКА СОЗДАНИЕ НОВГО ТАБЕЛЯ ИЗ ИСТОРИИ ВТОРОЙ ШАГ СОЗДАНИЯ ТАБЕЛЯ СНАЧАЛА ИСТРОИЯ ПОТОМ НА БАЗЕ ЕГО СОЗЗДАНИЕ


            //TODO Code Пользваотельский
            // TODO: 06.11.2022 методы после создание
            getsharedPreferencesХранилище();
            //TODO МЕТОД ПОЛУЧЕНИЕ ДАННЫХ ДЛЯ ДАННОГО АКВТИВИ
            МетодBackПеременныеFromListPeoples();
            МетодКруглаяКнопка();
            МетодНазадBACKНААктивти();
            МетодMessage();

            getMainCursorListTabels =    методGetDataSimpleCursorAdapter(); /////МЕТОД ЗАГРУЗКИ СОЗДАННЫХ ТАБЕЛЕЙ ИЗ БАЗж

            методЗаполенениеДатаСпинер( );
            ////todo заполение спинера
            МетодДанныеСпинераДаты( );
            // TODO: 09.04.2023  set Позиция после инициализации Scinner
            методМассивДляВыбораВСпинерДата();


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " Курсор_ДанныеСпиннера" + getMainCursorListTabels);
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }}




    @Override
    protected void onStart() {
        super.onStart();
        try{

            gridViewAllTabes.refreshDrawableState();
            gridViewAllTabes.requestLayout();
            gridViewAllTabes.startAnimation(animation);
            СпинерВыборДату.startAnimation(animation);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }











    private void МетодКруглаяКнопка() {
        КруглаяКнопкаСамТабель.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO СОЗДАНИЕ НОВОГО ТАБЕЛЯ
                try{
                    ///TODO создание нового ТАБЕЛЯ
                    МетодСозданиеДиалогаКалендаряДаты();////ЗПАСУКАЕМ МЕТОД КОГДА НАДО ВЫБРВТЬ ДАТУ С КАЛЕНДАРКА
                    // TODO: 18.03.2024  создание клика по Календарю
                    getCalendarClick();
                    // TODO: 18.03.2024  Клик закрыть Календарь
                    getCalendarCloseClick();

                    Log.d(this.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }}
        });
    }

    private void МетодНазадBACKНААктивти() {
        backFaceApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Log.d(this.getClass().getName(), " кликнем для созданни новго сотрдника при нажатии  ");
                    ///todo код которыц возврящет предыдущий актвитики кнопка back

                    LaunchActivityDashboard launchActivityDashboard=new LaunchActivityDashboard( fragmentManager,getApplicationContext());
                    // TODO: 27.03.2024 в зависомсти кто вызвает
                    launchActivityDashboard.     launchADashboardFragment();


                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


        });
    }

    // TODO: 09.04.2023  получем данные для ТАбелей
    void МетодBackПеременныеFromListPeoples() {
        try{
        Intent Интент_Back_MAinActivity_List_peole = getIntent();
            Bundle bundleДЛяListTabels=Интент_Back_MAinActivity_List_peole.getExtras();
            if (bundleДЛяListTabels!=null) {
                MainParentUUIDFromTabel =      bundleДЛяListTabels.getLong("MainParentUUID", 0l);
                getYear =  bundleДЛяListTabels.getInt("ГодТабелей", 0);
                getMonth =  bundleДЛяListTabels.getInt("МЕсяцТабелей",0);
                getDigitalNameCFO = bundleДЛяListTabels.getInt("DigitalNameCFO", 0);
                FullNameCFO=    bundleДЛяListTabels.getString("FullNameCFO", "" );
                getDateOnlySpinnerDown = bundleДЛяListTabels.getString("ИмесяцвИГодСразу", "" );
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " bundleДЛяListTabels " +bundleДЛяListTabels  + " MainParentUUIDFromTabel  " +MainParentUUIDFromTabel);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
        // TODO: 01.09.2021 метод вызова
       this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }





    private void методКоличествоТабелей(@NonNull  Cursor    getCursorItemSelected) {
        try{
        if (   СпинерВыборДату.getCount()>0) {
            textViewКоличествоТабелей.setText(" ("+СпинерВыборДату.getCount()+")"+" ("+getCursorItemSelected.getCount()+")");
        } else {
            textViewКоличествоТабелей.setText("("+"0"+")");
        }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    ///todo  конец метода удаления третий обработчки нажатия
    ///////МЕТОД СОЗДАННИЕ СПИНЕРА

    ///todo сообщение
    @SuppressLint("SuspiciousIndentation")
    @UiThread


    private Cursor launchCursorForDelete(@NonNull Context context , @NonNull Long СамоЗначениеUUID) {
        Cursor cursorForDelete=null;
        try{
            // TODO: 14.05.2025
            String Текущаятаблицы="data_tabels";
            ModuleQuety moduleQuety=new ModuleQuety(context);
            cursorForDelete= moduleQuety.getModuleQuery(Текущаятаблицы,"  SELECT D.uuid FROM  "+Текущаятаблицы+" AS D " +
                    " WHERE D.uuid_tabel='"+String.valueOf(СамоЗначениеUUID)+"'     AND D.status_send!='Удаленная' " ,null);
            // TODO: 09.06.2025  
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return cursorForDelete;
    }

    private void методМассивДляВыбораВСпинерДата() {
        try {
            getDateOnlySpinnerDown = СпинерВыборДату.getSelectedItem().toString();
                if (getDateOnlySpinnerDown !=null) {
                 Integer ИндексНахождение=   МассивДляВыбораВСпинерДатаArray.indexOf(getDateOnlySpinnerDown);
                    Log.d(  getApplicationContext().getClass().getName(), " ИндексНахождение "+ИндексНахождение);
                    if (ИндексНахождение>=0) {
                        Collections.swap(МассивДляВыбораВСпинерДатаArray,0,ИндексНахождение);
                    }
                }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " МассивДляВыбораВСпинерДатаArray " +МассивДляВыбораВСпинерДатаArray+"  FullNameCFO  " +FullNameCFO);
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
       this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        /////МЕТОД ЗАГРУКЗИ КОЛИЧЕСТВО ТАБЕЛЕЙ ИЗ БАЗЫ\




    }

    private void МетодДанныеСпинераДаты(  ) {
        try{
            ArrayAdapter<String>           АдаптерДляСпинераДата = new ArrayAdapter<String>(this,
                    R.layout.simple_for_create_new_assintionmaterila_spinner_main, МассивДляВыбораВСпинерДатаArray);
            АдаптерДляСпинераДата.setDropDownViewResource(R.layout.simple_for_create_new_assintionmaterila_spinner);
        СпинерВыборДату.setAdapter(АдаптерДляСпинераДата);
        СпинерВыборДату.setSelected(true);
        СпинерВыборДату.setSaveEnabled(true);
        СпинерВыборДату.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view!=null) {
                    if ((TextView) parent.getChildAt(0) != null) {
                        getDateOnlySpinnerDown = Optional.ofNullable(String.valueOf(((TextView)
                             parent.getChildAt(0)).getText())).map(String::new).orElse(" "); /////ОПРЕДЕЛЯЕМ ТЕКУЩЕЕ ЗНАЧЕНИЕ ВНУТИРИ СПЕНИРА
                        //////TODO линия снизу самих табелей ЦВЕТ
                        if (! getDateOnlySpinnerDown.equalsIgnoreCase("Не создано") ) {

                            ((TextView) parent.getChildAt(0)).setTextSize(16);
                            ((TextView) parent.getChildAt(0)).startAnimation(animation);
                            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                            ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                            ((TextView) parent.getChildAt(0)).setTypeface(((TextView) parent.getChildAt(0)).getTypeface(), Typeface.BOLD);//////ВЫДЕЛЕМ ЖИРНЫМ ЦВЕТОМ ДАТЫ
                            ((TextView) parent.getChildAt(0)).setText(getDateOnlySpinnerDown);//// ЗАПИСЫВАЕМ ЗНАЧЕНИЕ В СПИПЕР
                            СпинерВыборДату.startAnimation(animation);
                            TextView textViewspiner=(TextView)   СпинерВыборДату.getSelectedView();
                                textViewspiner.setTextColor(Color.BLACK);

                        Long    getMainParentUUIDFromTabel =(Long)       МассивДляВыбораВСпинореMainUUID.get(position);
                            Log.d(this.getClass().getName(), " КакойКонтекст" + getDateOnlySpinnerDown +
                                    " ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре " + getDateOnlySpinnerDown +
                                    " МассивДляВыбораВСпинерДатаArray " +МассивДляВыбораВСпинерДатаArray  +
                                    " МассивДляВыбораВСпинореMainUUID " + МассивДляВыбораВСпинореMainUUID+
                                    "  getMainParentUUIDFromTabel " +getMainParentUUIDFromTabel );


                            Bundle bundleДЛяСпинераДаты=new Bundle();
                            bundleДЛяСпинераДаты.putLong("MainParentUUID", getMainParentUUIDFromTabel);
                            bundleДЛяСпинераДаты.putInt("Position", position);
                            bundleДЛяСпинераДаты.putString("ИмесяцвИГодСразу", getDateOnlySpinnerDown.trim());
                            bundleДЛяСпинераДаты.putInt("ГодТабелей", getYear);
                            bundleДЛяСпинераДаты.putInt("МЕсяцТабелей", getMonth);
                            bundleДЛяСпинераДаты.putInt("DigitalNameCFO", getDigitalNameCFO);
                            // TODO: 19.04.2023  add bungle
                            textViewspiner.setTag(bundleДЛяСпинераДаты);


                            Cursor getCursorItemSelected = launchCursorItemSelectedListtabels(getMainParentUUIDFromTabel);


                            if ( getCursorItemSelected.getCount()>0 ) {

                                // TODO: 23.08.2023  ГЛАВНЫЙ МЕТОД ЗАПОЛЕНИЯ ЭКРАНА SIMPLECURSOR  ДАННЫМИ
                                методзаполненияSimplrCursor(getCursorItemSelected);
                                // TODO: 19.04.2023  показываем количемтво табеленй
                              методКоличествоТабелей(getCursorItemSelected );

                            } else {
                                // TODO: 19.04.2023  Когда ДАННЫХ НЕТ
                                методDontGetData();
                            }



                        }else {
                            // TODO: 19.04.2023  Когда ДАННЫХ НЕТ
                            методDontGetData( );
                        }

                    }
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(this.getClass().getName(), "  FullNameCFO  " + FullNameCFO);
            }
        });
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @SuppressLint({"Range", "SuspiciousIndentation"})
    private Cursor launchCursorItemSelectedListtabels(@NonNull Long getMainParentUUIDFromTabel) {
        Cursor    getCursorListtabels=null;
        try {
        Cursor getCursorMainParentUUID=    launchItemSelectTabelWithMainParentUUID(getMainParentUUIDFromTabel);
       //TODO

        getMonth =getCursorMainParentUUID.getInt(getCursorMainParentUUID.getColumnIndex("month_tabels"));
        getYear =getCursorMainParentUUID.getInt(getCursorMainParentUUID.getColumnIndex("year_tabels"));
        getDigitalNameCFO =getCursorMainParentUUID.getInt(getCursorMainParentUUID.getColumnIndex("cfo"));
        // TODO: 19.04.2023 даннные
            getCursorMainParentUUID.close();


        // TODO: 09.04.2023  Главный Треитий Последние Получение Данных Для Конктерного Месяца И Года
            getCursorListtabels=    launchItemSelectTabelWithgetMonthAndgetYear(getMonth, getYear );
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  +
                     "  getMainParentUUIDFromTabel " +getMainParentUUIDFromTabel);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return getCursorListtabels;
    }


    void методDontGetData(  ){
        try{
                TextView textViewСпинерДАты = (TextView) СпинерВыборДату.getSelectedView();
                СпинерВыборДату.setSelection(0, true);
                СпинерВыборДату.refreshDrawableState();
                СпинерВыборДату.requestLayout();
                // TODO: 19.04.2023  когад нет данныхх
                методDontCursorSimplrCursor();


            Log.d(this.getClass().getName(), " КакойКонтекст" + getDateOnlySpinnerDown +
                    " ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре " + getDateOnlySpinnerDown +
                    " МассивДляВыбораВСпинерДатаArray " +МассивДляВыбораВСпинерДатаArray  +
                    " МассивДляВыбораВСпинореMainUUID " + МассивДляВыбораВСпинореMainUUID+
                    "  ((TextView) parent.getChildAt(0)) "+ " MainParentUUID " + MainParentUUIDFromTabel);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }




    private Cursor методGetDataSimpleCursorAdapter()  {
        Cursor getMainCursorListTabels = null;
        try{
            // TODO: 09.04.2023  курсор самим создаваемых табеляПОСИК ДАННЫХ ЧЕРЕЗ UUID
            String Текущаятаблицы="tabel";
            ModuleQuety moduleQuety=new ModuleQuety(getApplicationContext());
            getMainCursorListTabels=   moduleQuety.getModuleQuery(Текущаятаблицы,"   SELECT D.month_tabels, D.year_tabels , D.uuid ,COUNT(D.year_tabels ) \n" +
                    "                     FROM  "+Текущаятаблицы+"  AS D\n" +
                    "                      WHERE D.status_send!='Удаленная' \n" +
                    "\t\t\t\t\t  AND D.month_tabels IS NOT NULL\n" +
                    "\t\t\t\t\t  AND D.year_tabels IS NOT NULL \n" +
                    "\t\t\t\t\t  \n" +
                    "\t\t\t\t\t  \n" +
                    "                    GROUP BY D.month_tabels, D.year_tabels \n" +
                    "                                    HAVING count(D.year_tabels )>=1\n" +
                    "                     ORDER BY D.year_tabels DESC, D.month_tabels DESC LIMIT 6  ",null);
            // TODO: 09.06.2025
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                   new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return getMainCursorListTabels;
    }




    private Cursor launchItemSelectTabelWithMainParentUUID(@NonNull Long  MainParentUUID )  {
        Cursor getItemSelectTabelWithMainParentUUID = null;
        try{
            // TODO: 09.04.2023  курсор самим создаваемых табеляПОСИК ДАННЫХ ЧЕРЕЗ UUID
            String Текущаятаблицы="tabel";
            ModuleQuety moduleQuety=new ModuleQuety(getApplicationContext());
            getItemSelectTabelWithMainParentUUID=   moduleQuety.getModuleQuery(Текущаятаблицы,"   SELECT * FROM  "+Текущаятаблицы+" AS D  " +
                    " WHERE D.status_send!='Удаленная'" +
                    " AND D.uuid='"+String.valueOf(MainParentUUID)+"'   " +
                    "  AND D.month_tabels IS NOT NULL   AND D.year_tabels IS NOT NULL " ,null);
            // TODO: 09.06.2025
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +  " getItemSelectTabelWithMainParentUUID " +getItemSelectTabelWithMainParentUUID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return getItemSelectTabelWithMainParentUUID;
    }




    private Cursor launchItemSelectTabelWithgetMonthAndgetYear(@NonNull Integer  getMonth, @NonNull Integer getYear )  {
        Cursor getItemSelectTabelWithgetMonthAndgetYear = null;
        try{
            // TODO: 09.04.2023  курсор самим создаваемых табеляПОСИК ДАННЫХ ЧЕРЕЗ UUID
            String Текущаятаблицы="tabel";
            ModuleQuety moduleQuety=new ModuleQuety(getApplicationContext());
            getItemSelectTabelWithgetMonthAndgetYear=   moduleQuety.getModuleQuery(Текущаятаблицы,"   SELECT * FROM "+Текущаятаблицы + "  AS D " +
                    "  WHERE D.status_send!='Удаленная' " +
                    " AND D.month_tabels ='"+getMonth+"'  " +
                    "AND D.year_tabels ='"+getYear+"' ",null);

            // TODO: 09.06.2025
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getItemSelectTabelWithgetMonthAndgetYear " +getItemSelectTabelWithgetMonthAndgetYear);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return getItemSelectTabelWithgetMonthAndgetYear;
    }



    @SuppressLint("Range")
    private void методзаполненияSimplrCursor(Cursor getCursorItemSelected) {
        try {
            AtomicInteger atomicIntegerItemTabel=new AtomicInteger(0);
            simpleCursorAdapterAllTAbels =
                    new SimpleCursorAdapter(getApplicationContext(), R.layout.list_item_all_customer_tabel3,
                            getCursorItemSelected, new String[]{"_id"}, new int[]{android.R.id.text1 },
                          0);  ///name
            SimpleCursorAdapter.ViewBinder binding = new SimpleCursorAdapter.ViewBinder() {

                @Override
                public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                    try{
                        // TODO: 29.07.2025
                        switch (view.getId()) {
                            case android.R.id.text1:
                                // TODO: 29.07.2025
                                 LinearLayout linearLayoutItemTabel=(LinearLayout) view ;
                                // TODO: 29.07.2025
                                setMaterialTextViewItemTabel(linearLayoutItemTabel, cursor);
                                setImageViewIemTabel(linearLayoutItemTabel);
                                setMaterialTextViewtabelsCount(linearLayoutItemTabel, cursor);
                                
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " MainParentUUID "+ MainParentUUIDFromTabel);
                                return true;

                        }

                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " MainParentUUID "+ MainParentUUIDFromTabel);
                        return false;
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return false;
                }

                private void setMaterialTextViewItemTabel(LinearLayout  linearLayoutItemTabel, Cursor cursor) {
                    // TODO: 29.07.2025  
                  MaterialTextView getMaterialTextViewItemTabel=  linearLayoutItemTabel.findViewById(R.id.MaterialTextViewItemTabel);
                    
                    Long   getSimpleCursorMainParentUUIDFromTabel = cursor.getLong(cursor.getColumnIndex("uuid")); //TODO ЗАПРОС К ТАБЛИЦЕ TABEL
                    Integer  getSimpleCursorgetDigitalNameCFO = cursor.getInt(cursor.getColumnIndex("cfo"));//TODO ЗАПРОС К ТАБЛИЦЕ TABEL

                    FullNameCFO = getingNameCurrentZFOWithUUID();
                    if( FullNameCFO.equalsIgnoreCase("Нет ЦФО !!!")  )  {
                        FullNameCFO = getingNameCurrentZFOWithID();
                    }

                    // TODO: 19.06.2023 close

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                            " DigitalNameCFO " + getDigitalNameCFO + " FullNameCFO "+FullNameCFO);


                    getMonth = cursor.getInt(cursor.getColumnIndex("month_tabels"));
                    getYear = cursor.getInt(cursor.getColumnIndex("year_tabels"));
                    // TODO: 15.12.2022  Давные Bundle
                    Bundle bundleДЛяListTabels=new Bundle();
                    bundleДЛяListTabels.putLong("MainParentUUID", getSimpleCursorMainParentUUIDFromTabel);
                    bundleДЛяListTabels.putInt("Position", cursor.getPosition());
                    bundleДЛяListTabels.putInt("ГодТабелей", getYear);
                    bundleДЛяListTabels.putInt("МЕсяцТабелей", getMonth);
                    bundleДЛяListTabels.putInt("DigitalNameCFO", getSimpleCursorgetDigitalNameCFO);
                    bundleДЛяListTabels.putString("FullNameCFO", FullNameCFO.trim());
                    bundleДЛяListTabels.putString("ИмесяцвИГодСразу", getDateOnlySpinnerDown.trim());

                    // TODO: 09.04.2023  ВставлЯем Данные
                    getMaterialTextViewItemTabel.setTag(bundleДЛяListTabels);
                    if (FullNameCFO!=null && FullNameCFO.length()>0) {
                        getMaterialTextViewItemTabel.setText(FullNameCFO.trim());
                    }else{
                        getMaterialTextViewItemTabel.setText("Нет ЦФО !!!");
                    }
                    getMaterialTextViewItemTabel.setTextSize(15l);
                    getMaterialTextViewItemTabel.startAnimation(animationvibr1);
                    // TODO: 18.04.2023  Внешниц вид


                 
                }

                private   void setMaterialTextViewtabelsCount(LinearLayout linearLayout, Cursor cursor) {
                    // TODO: 29.07.2025 количество
                    MaterialTextView getMaterialTextViewCount=       linearLayout.findViewById(  R.id.MaterialTextViewCount);
                    //Drawable icon2 = getResources().getDrawable(   R.drawable.icon_alltabels1);
                    // TODO: 09.04.2023  ВставлЯем Данные
                  Integer getItem=  atomicIntegerItemTabel.getAndIncrement();
                    getItem=getItem-1;
                    getMaterialTextViewCount.setText(getItem.toString());
                }

                private   void setImageViewIemTabel(LinearLayout linearLayout) {
                    ImageView imageView=       linearLayout.findViewById(  R.id.ImageViewIemTabel);
                    //Drawable icon2 = getResources().getDrawable(   R.drawable.icon_alltabels1);
                    Drawable icon2 = getResources().getDrawable(   R.drawable.icon_newlisttabel);
                    imageView.setImageDrawable(icon2);
                    imageView.setImageResource(R.drawable.icon_newlisttabel);
                }


                
                
                
            };
            simpleCursorAdapterAllTAbels.setViewBinder(binding);
            gridViewAllTabes.setAdapter(simpleCursorAdapterAllTAbels);
            simpleCursorAdapterAllTAbels.notifyDataSetChanged();
            gridViewAllTabes.refreshDrawableState();
            gridViewAllTabes.requestLayout();



            // TODO: 19.04.2023 слушаелти
            // TODO: 18.04.2023 Слушаиель Клика
          clickItemGridView( );
            // TODO: 18.04.2023 Слушатель Удалание
            методУдалениеТабеля( );

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " gridViewAllTabes.isActivated() " +gridViewAllTabes.isActivated() );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    @SuppressLint("Range")
    @NonNull
    private String getingNameCurrentZFOWithUUID() {
        try{
        // TODO: 18.04.2023 Название ЦФО
            String Текущаятаблицы="cfo";
            ModuleQuety moduleQuety=new ModuleQuety(getApplicationContext());
            Cursor КурсорПОискНазваниеЦФО =   moduleQuety.getModuleQuery(Текущаятаблицы,"   SELECT *  FROM   "+Текущаятаблицы+" AS D " +
                    " WHERE D.uuid='"+String.valueOf(getDigitalNameCFO)+"'"+";",null);
            // TODO: 09.06.2025
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        // TODO: 09.10.2024  Получаем Название ЦФО  для отображения
        if (КурсорПОискНазваниеЦФО.getCount()>0) {
            FullNameCFO=КурсорПОискНазваниеЦФО.getString(КурсорПОискНазваниеЦФО.getColumnIndex("name")).trim();
            // TODO: 07.10.2024
            if ( FullNameCFO.equalsIgnoreCase("Тестовое")) {
                FullNameCFO="Нет ЦФО !!!";
            }
        }else {
            FullNameCFO="Нет ЦФО !!!";
        }
        КурсорПОискНазваниеЦФО.close();
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }


        return FullNameCFO;
    }


    @SuppressLint("Range")
    @NonNull
    private String getingNameCurrentZFOWithID( ) {
        try{
            // TODO: 18.04.2023 Название ЦФО
            // TODO: 07.06.2023 вытаскиваем названеи ЦФО
            String Текущаятаблицы="cfo";
            ModuleQuety moduleQuety=new ModuleQuety(getApplicationContext());
            Cursor КурсорПОискНазваниеЦФО=   moduleQuety.getModuleQuery(Текущаятаблицы,"  SELECT * FROM  "+Текущаятаблицы+ " AS D " +
                    " WHERE    D._id= '"+ String.valueOf(getDigitalNameCFO)+"' ",null);
            // TODO: 09.06.2025
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


            // TODO: 09.10.2024  Получаем Название ЦФО  для отображения
            if (КурсорПОискНазваниеЦФО.getCount()>0) {
                FullNameCFO=КурсорПОискНазваниеЦФО.getString(КурсорПОискНазваниеЦФО.getColumnIndex("name")).trim();
                // TODO: 07.10.2024
                if ( FullNameCFO.equalsIgnoreCase("Тестовое")) {
                    FullNameCFO="Нет ЦФО !!!";
                }
            }else {
                FullNameCFO="Нет ЦФО !!!";
            }
            КурсорПОискНазваниеЦФО.close();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return FullNameCFO;
    }




    private void методУдалениеТабеля( ) {
        gridViewAllTabes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    LinearLayout linearLayoutClickDelete=    (LinearLayout)       parent.getAdapter().getView(position, view,parent );
                    MaterialTextView materialTextViewClickDelete=(MaterialTextView)        linearLayoutClickDelete.findViewById(R.id.MaterialTextViewItemTabel);
                    materialTextViewClickDelete.setBackgroundColor(Color.GRAY);

                    message.getTarget().postDelayed(()->{
                        Bundle bundleДЛяListTabels=(Bundle)           materialTextViewClickDelete.getTag();
                        Long    MainParentUUID=      bundleДЛяListTabels.getLong("MainParentUUID");
                        String    FullNameCFO=      bundleДЛяListTabels.getString("FullNameCFO");
                        ///todo Удаление
                        МетодУдалениеТАбеляСообщениеПередЭтим(MainParentUUID, FullNameCFO,view);
                            },200);

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return true;
            }
        });

    }

    private void методDontCursorSimplrCursor( ) {
        try {
            ArrayList<HashMap<String, Object>> ЛистНетданных= new ArrayList<HashMap<String, Object>> ();
            HashMap<String, Object> map = new HashMap<>();
            map.put("allimage", " dont");
            ЛистНетданных.add(map);
            SimpleAdapter АдаптерКогдаНетданных = new SimpleAdapter(getApplicationContext(),
                    ЛистНетданных,
                    R.layout.list_item_all_customer_tabel4dont,
                    new String[]{ "allimage"},
                    new int[]{android.R.id.text1});

            SimpleAdapter.ViewBinder БиндингКогдаНетДАнных = new SimpleAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Object data, String textRepresentation) {
                        try{
                            switch (view.getId()) {
                                case android.R.id.text1:
                                    // TODO: 09.04.2023  ВставлЯем Данные

                                    RelativeLayout relativeLayout=view.findViewById(android.R.id.text1);
                                    CheckedTextView checkedTextView=(CheckedTextView) relativeLayout.findViewById(R.id.textView3SapolnitFrrombackmezaz);
                                    checkedTextView.setHeight(1300);
                                    checkedTextView.setTextSize(18l);
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " MainParentUUID " + MainParentUUIDFromTabel);
                                    return true;
                            }
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " MainParentUUID "+ MainParentUUIDFromTabel);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return false;
                    }
                };
                АдаптерКогдаНетданных.setViewBinder(БиндингКогдаНетДАнных);
                gridViewAllTabes.setAdapter(АдаптерКогдаНетданных);
                АдаптерКогдаНетданных.notifyDataSetChanged();
                gridViewAllTabes.refreshDrawableState();
                gridViewAllTabes.requestLayout();
                // TODO: 19.04.2023 слушаелти

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    private void clickItemGridView( ) {
        gridViewAllTabes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    LinearLayout linearLayoutClick=    (LinearLayout)       parent.getAdapter().getView(position, view,parent );
                    MaterialTextView materialTextViewClick=(MaterialTextView)        linearLayoutClick.findViewById(R.id.MaterialTextViewItemTabel);


                    materialTextViewClick.setBackgroundColor(Color.GRAY);
                    message.getTarget().postDelayed(()->{
                        // TODO: 09.04.2023  перехеод после клика Items
                        МетодПереходMainActivity_List_Peoples(materialTextViewClick);
                    },100);

/////TODO одинатрный клик для загрузки в этот табель всех сотрудников
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
        });
    }
    /////TODO метод запуска кода при однократорм нажатии просто загузка сотрудников табель
    private void МетодПереходMainActivity_List_Peoples(@NonNull  MaterialTextView textView) {
        try{
            Intent    ИнтентпереходВMainActivityList_Peoples=new Intent(getApplicationContext(), MainActivityListPeoples.class);
            Bundle bundleИзMAinActivbity_List_Tabels=(Bundle) textView.getTag();
            ИнтентпереходВMainActivityList_Peoples.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ИнтентпереходВMainActivityList_Peoples      .putExtras(bundleИзMAinActivbity_List_Tabels);
            startActivity(ИнтентпереходВMainActivityList_Peoples);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " bundleИзMAinActivbity_List_Tabels "+bundleИзMAinActivbity_List_Tabels);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    //////TODO вычисляем максимальную дату для СПИНЕРА ДЛЯ ВАДАПТЕРА AAARYADAPTER


    private void МетодСозданиеДиалогаКалендаряДаты() {///////метод создание календяря даты
/////TODO тут визуализикуеться КАЛЕНДАРЬ
        try {
            Calendar newDate = Calendar.getInstance();
            class  getDatePicker extends DatePickerDialog{
                public getDatePicker(@NonNull Context context, int themeResId) {
                    super(context, themeResId);
                    setButton(BUTTON_POSITIVE, ("Ok"), this);
                    setButton(BUTTON_NEGATIVE, ("Закрыть"), this);
                }
                public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    newDate.set(year, monthOfYear, dayOfMonth);
                }

            }
            ДатаДляКалендаря=new getDatePicker(this,  android.R.style.Widget_Material_DatePicker);//TODO : android.R.style.Widget_Material_DatePicker
            ДатаДляКалендаря.setTitle("Календарь");
            ДатаДляКалендаря.setCancelable(false);
            ДатаДляКалендаря.setCanceledOnTouchOutside(false);


            WindowManager.LayoutParams params = ДатаДляКалендаря.getWindow().getAttributes();
            ДатаДляКалендаря.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            ДатаДляКалендаря.getWindow().setGravity(Gravity.CENTER);
            ДатаДляКалендаря.getWindow().setAttributes(params);


         /*   Drawable drawabledown=context.getDrawable(R.drawable.style_for_calendar1);
            ДатаДляКалендаря.getWindow().setBackgroundDrawable(drawabledown);*/
            //todo:
            if (!ДатаДляКалендаря.isShowing()) {
                ДатаДляКалендаря.show();
            }




            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " ИмесяцвИГодСразу " + getDateOnlySpinnerDown);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }



    private void getCalendarCloseClick() {
        ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setVisibility(View.VISIBLE);
        ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);//Color.parseColor("#03DAC6")
        ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setText("Закрыть");

        ДатаДляКалендаря.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                        ДатаДляКалендаря.dismiss();
                        ДатаДляКалендаря.cancel();
                        // TODO: 17.04.2023
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                " DayOfMonth " +ДатаДляКалендаря.getDatePicker().getYear() );

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                });
    }

    private void getCalendarClick() {
        ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setVisibility(View.VISIBLE);
        ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);//Color.parseColor("#03DAC6")
        ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setText("Ok");

        ДатаДляКалендаря.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                        int DayOfMonth=    ДатаДляКалендаря.getDatePicker().getDayOfMonth();
                        int Month=  ДатаДляКалендаря.getDatePicker().getMonth()+1;
                        int Year=     ДатаДляКалендаря.getDatePicker().getYear();

                            Log.d(this.getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Month " + Month+ " Year " +Year);

                            if (Month>0  && Year>0 && DayOfMonth>0) {
                                // TODO: 03.10.2024 create new Tabel with Calendary
                                launchNewTabelWithCalendate(  Month,   Year,DayOfMonth);

                                ДатаДляКалендаря.dismiss();
                                ДатаДляКалендаря.cancel();

                                Log.d(this.getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Month " + Month+ " Year " +Year);
                                }  else {
                                Toast.makeText(getApplicationContext(), " Нет месяца для создание Табеля !!! ", Toast.LENGTH_LONG).show();
                                // TODO: 18.03.2024
                            }

                        // TODO: 17.04.2023
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                " DayOfMonth " +ДатаДляКалендаря.getDatePicker().getYear()  );
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                });
    }







    ////TODO СОЗДАНИЯ КАЛЕНДАРЯ С ПОЛУЧЕННЫМИ УЖЕ ДАННЫМИ
    private void launchNewTabelWithCalendate(@NonNull  Integer Month, @NonNull  Integer Year , @NonNull Integer DayOfMonth )   {
        try{
            ///TODO  ПОСЛЕ ВСТАКИ ПЕРЕХОДИМ НА АКТИВТИ С ВЫБОРО И СОЗДАНИЕМ САМОГО ТАБЕЛЯ НОВОГО
            Intent intentNewTabelWithCalendate = new Intent(getApplicationContext(), MainActivityNewTabels.class);
            Bundle     bundleСозданиеНовогоТабеля=new Bundle();
                bundleСозданиеНовогоТабеля.putInt("getNewYear", Year);
                bundleСозданиеНовогоТабеля.putInt("getNewMonth", Month);
                bundleСозданиеНовогоТабеля.putInt("getNewDayOfMonth", DayOfMonth);
            intentNewTabelWithCalendate.putExtras(bundleСозданиеНовогоТабеля);
            intentNewTabelWithCalendate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentNewTabelWithCalendate);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " Month " +Month+ " Year " +Year);
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        }






    //TODO метод получени месяа для записи в одну колонку

    private int  МетодПолучениниеМесяцДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( " " + ДатаКоторуюНадоПеревестиИзТекставЦифру + " " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        int month=0;
        try{
        SimpleDateFormat formatмесяц = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatмесяц .parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        Calendar calendar2 = new GregorianCalendar();
        calendar.setTime(date );
        month = calendar.get(Calendar.MONTH) + 1;



    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///


    }


        return   month;
    }

    //TODO метод получени месяа для записи в одну колонку

    private int  МетодПолучениниеГОдДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( "ДатаКоторуюНадоПеревестиИзТекставЦифру " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        int year=0;
        try{
        SimpleDateFormat formatгод = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
            year  = calendar.get(Calendar.YEAR);


    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///


    }
        return   year ;
    }

////TODO МЕТОД ТОЛЬКО ДЛЯ ВСТВКИ НОВОГО МЕСЯЦА и ГодТабелей НОВЫЙ


































    @SuppressLint("Range")
    private void методЗаполенениеДатаСпинер( )
            {
        try {
            МассивДляВыбораВСпинерДатаArray.clear();
            МассивДляВыбораВСпинореMainUUID.clear();
            // TODO: 19.04.2023 close cursor
            if (getMainCursorListTabels !=null && getMainCursorListTabels.getCount()>0) {
                do{
                  Integer      Месяц = getMainCursorListTabels.getInt(getMainCursorListTabels.getColumnIndex("month_tabels")) ;
                    Integer     Год = getMainCursorListTabels.getInt(getMainCursorListTabels.getColumnIndex(	"year_tabels")) ;
                    Long     ЗнаениеИзБазыНовыеТабеляUUID = getMainCursorListTabels.getLong(getMainCursorListTabels.getColumnIndex("uuid"));

                    ////todo ПРЕОБРАЗОВАЫВЕМ ЦИФРВЫ В ДАТУ ВВИДЕТ ТЕКСТА ИБЛЬ АВГУСТ 2020 2021
                    SimpleDateFormat ПереводимЦифруВТЕкстМЕсяца = new SimpleDateFormat("mm", new Locale("ru") );
                    Date ДатаДляПолученияМесяцаСловом = ПереводимЦифруВТЕкстМЕсяца.parse(Месяц.toString());
                    String ПреобразованоеИмяМесяца= ПереводимЦифруВТЕкстМЕсяца.format( ДатаДляПолученияМесяцаСловом );
                    Log.d(this.getClass().getName()," ПреобразованоеИмяМесяца " +ПреобразованоеИмяМесяца);
                    SimpleDateFormat formatмесяц = new SimpleDateFormat("MMyyyy", new Locale("ru"));
                    Date date = formatмесяц.parse(ПреобразованоеИмяМесяца+Год);
                    Calendar calendar = Calendar.getInstance(new Locale("ru"));
                    calendar.setTime(date);
                    System.out.println(calendar.get(Calendar.YEAR));
                    System.out.println(calendar.get(Calendar.MONTH)+1);
                    System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
                    System.out.println(new SimpleDateFormat("LLLL").format(calendar.getTime()));
                    ПреобразованоеИмяМесяца=new SimpleDateFormat("LLLL").format(calendar.getTime());
                    StringBuffer stringBuffer=new StringBuffer(ПреобразованоеИмяМесяца);
                    ПреобразованоеИмяМесяца=stringBuffer.substring(0,1).toUpperCase()+stringBuffer.substring(1,stringBuffer.length()).toLowerCase();
                    String ФиналВставкаМЕсяцаИгода = "";
                    ФиналВставкаМЕсяцаИгода=ПреобразованоеИмяМесяца+ "  "+Год;
                    Log.d(this.getClass().getName()," ФиналВставкаМЕсяцаИгода "+ФиналВставкаМЕсяцаИгода);
                    ///todo заполяем Название СФО
                 МассивДляВыбораВСпинерДатаArray.add(ФиналВставкаМЕсяцаИгода.trim());
                    ///todo заполяем Название UUID
                    МассивДляВыбораВСпинореMainUUID.add(ЗнаениеИзБазыНовыеТабеляUUID);


                }while (getMainCursorListTabels.moveToNext());
// TODO: 23.08.2023
                getMainCursorListTabels.moveToFirst();
            }else {
                     МассивДляВыбораВСпинерДатаArray.add("Не создано");

            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " МассивДляВыбораВСпинерДатаArray " +МассивДляВыбораВСпинерДатаArray +
                    " МассивДляВыбораВСпинореMainUUID " +МассивДляВыбораВСпинореMainUUID);

        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }













































































    void МетодУдалениеТАбеляСообщениеПередЭтим(  @NonNull Long СамUUIDТабеля,
                                               @NonNull String НазваниеУдаляемогоТАбеля,
                                                 @NonNull  View v) {

        try{


                СообщениеВыборУдлалянияТабеляИзБазы(НазваниеУдаляемогоТАбеля,СамUUIDТабеля) ;

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }
//todo  конеч сообщение предупреждения удлаения табеля





















    ///todo сообщение
    @UiThread
    protected void СообщениеВыборУдлалянияТабеляИзБазы(String Сообщение, Long СамоЗначениеUUID) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        try {
//////сам вид
            final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                    .setTitle("Удаление Табеля...")
                    .setMessage(Сообщение)
                    .setPositiveButton("Да", null)
                    .setNegativeButton("Нет", null)
                    .setIcon(R.drawable.icon_dsu1_delete_customer)
                    .show();
/////////кнопка
            final Button MessageBoxУдалениеСотрудникаИзТабеля = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxУдалениеСотрудникаИзТабеля .setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                    Log.d(this.getClass().getName(), "  ФИНАЛ создание нового сотрудника " + "ИндификаторUUID " + " СамоЗначениеUUID " + СамоЗначениеUUID+"  "+ FullNameCFO);
                    if (СамоЗначениеUUID>0) {
                        // TODO: 15.02.2023 получаем даннеы для удаления
                        Cursor cursorДляУдалениея=    launchCursorForDelete(getApplicationContext(),СамоЗначениеUUID);


                        // TODO: 15.02.2023  само удаление по двум таблицам
                        deletingTabel(СамоЗначениеUUID,"data_tabels",cursorДляУдалениея);

                        Log.d(context.getClass().getName(), "\n"
                                + " время: " + new Date()+"\n+" +
                                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    }


                }
            });
            /////////кнопка
            final Button MessageBoxУдалениеСотрудникаИзТабеляОтмена = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxУдалениеСотрудникаИзТабеляОтмена.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                    Log.d(context.getClass().getName(), "\n"
                            + " время: " + new Date()+"\n+" +
                            " Класс в процессе... " +  this.getClass().getName()+"\n"+
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }







    //todo метод удаление сотрудника из табеля
    private void deletingTabel(@NonNull Long СамоЗначениеUUID,
                               @NonNull  String ИзКакойТаблицыУдалять,
                               @NonNull Cursor cursor) {
        ArrayList<Integer> УдалениеintegerArrayList=new ArrayList<>();
        try{
            Log.d(this.getClass().getName()," ДляУдалениеUUID " +ИзКакойТаблицыУдалять);
            progressDialogДляУдаления = new ProgressDialog(activity);
            progressDialogДляУдаления.setTitle("Удаление Табеля");
            progressDialogДляУдаления.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialogДляУдаления.setProgress(0);
            progressDialogДляУдаления.setCanceledOnTouchOutside(false);
            progressDialogДляУдаления.setMessage("Удаление...");
            progressDialogДляУдаления.show();
                               //TODO
                        Observable.range(0,cursor.getCount())
                                    .subscribeOn(Schedulers.single())
                                    .zipWith(Observable.interval(150, TimeUnit.MILLISECONDS), new BiFunction<Object, Long, Object>() {
                                        @Override
                                        public Object apply(Object o, Long aLong) throws Throwable {
                                            Log.d(this.getClass().getName(), " o " + o+ " aLong " +aLong);
                                            return aLong;
                                        }
                                    }).doOnNext(new Consumer<Object>() {
                                    @Override
                                    public void accept(Object o) throws Throwable {
                                        // TODO: 22.11.2022  первая часть
                                    Long    ДляУдалениеUUID=     cursor.getLong(0);
                                   Integer     Удаление = new CoreBinessLogics(getApplicationContext()).launchUpdateData(ИзКакойТаблицыУдалять,
                                                    "uuid", ДляУдалениеUUID);

                                        if (Удаление>0) {
                                            УдалениеintegerArrayList.add(Удаление);
                                        }

                                        Log.d(context.getClass().getName(), "\n"
                                                + " время: " + new Date()+"\n+" +
                                                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                + " УдалениеintegerArrayList.size()) " +УдалениеintegerArrayList.size());


                                    }
                                })
                                .doAfterNext(new Consumer<Object>() {
                                    @Override
                                    public void accept(Object o) throws Throwable {
                                        cursor.moveToNext();
                                        if (Integer.parseInt(o.toString())>0) {
                                            context.getMainExecutor().execute(()->{
                                                progressDialogДляУдаления.setMessage("Удалание..."+УдалениеintegerArrayList.size()+"("+Integer.parseInt(o.toString())+")");
                                            });
                                        }
                                    }
                                }).doOnComplete(new Action() {
                                    @Override
                                    public void run() throws Throwable {
                                        // TODO: 14.07.2025  
                                            deletingTabel(СамоЗначениеUUID,"tabel");
                                        

                                        Log.d(this.getClass().getName(), "\n"
                                                + " время: " + new Date() + "\n+" +
                                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                                    }
                                }).subscribe();;

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        };
    }




    //todo ВТОРОЙ МЕТОД УДАЛЕНИЕ ДЛЯ ВЕРХНЕНЙ ТАБЛИЦЫ ТАБЕЛЬ
    private void deletingTabel(@NotNull  Long ДляУдалениеUUID,
                               @NonNull  String ИзКакойТаблицыУдалять) {

        try{
       Single.fromCallable(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    // TODO: 22.11.2022  первая часть
                    Integer     deleteTabel = new CoreBinessLogics(getApplicationContext()).launchUpdateData(ИзКакойТаблицыУдалять,
                            "uuid", ДляУдалениеUUID);
                    Log.d(context.getClass().getName(), "\n"
                            + " время: " + new Date()+"\n+" +
                            " Класс в процессе... " +  this.getClass().getName()+"\n"+
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + " deleteTabel " +deleteTabel);
                    return deleteTabel;
                }
            }).subscribeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Throwable {
                            // TODO: 14.07.2025
                            if (integer>0) {
                                context.getMainExecutor().execute(()->{
                                    progressDialogДляУдаления.setMessage("Удалание..."+integer +")");
                                });
                            }
                            progressDialogДляУдаления.dismiss();
                            progressDialogДляУдаления.cancel();;

                            // TODO: 24.08.2023  метод перегрузки reeboot данных
                            методRebootGataTabel();

                            onStart();

                        }
                    }).doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getApplicationContext()).recordnewerror(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .subscribeOn(Schedulers.single()).subscribe();
            // TODO: 14.07.2025



        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        };
    }






    private void методRebootGataTabel() {
        try{
        if (simpleCursorAdapterAllTAbels!=null) {
            /*Cursor Курсор_MainСамиДанныеТекущегоТАбеля  =     simpleCursorAdapterAllTAbels.getCursor();
           Курсор_MainСамиДанныеТекущегоТАбеля .requery();*/

            // TODO: 24.08.2023

            getMainCursorListTabels =    методGetDataSimpleCursorAdapter(); /////МЕТОД ЗАГРУЗКИ СОЗДАННЫХ ТАБЕЛЕЙ ИЗ БАЗж
          //  Курсор_ДанныеСпиннера.requery(); /////МЕТОД ЗАГРУЗКИ СОЗДАННЫХ ТАБЕЛЕЙ ИЗ БАЗж

            методЗаполенениеДатаСпинер( );
            ////todo заполение спинера
            МетодДанныеСпинераДаты( );

            // TODO: 09.04.2023  set Позиция после инициализации Scinner
            методМассивДляВыбораВСпинерДата();


            // TODO: 09.06.2025
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    };
    }




    private void МетодMessage() {
        try {
                message=Message.obtain(new Handler(Looper.myLooper()),()->{
                    Bundle bundle=   message.getData();
                    Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                            Thread.currentThread().getStackTrace()[2].getMethodName()+
                            " время " +new Date().toLocaleString() + " message " +message );
                    Log.i(this.getClass().getName(), "bundle " +bundle);
                    //message.recycle();
                });
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " message " +message);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }

    }
    private void getsharedPreferencesХранилище() {
        // TODO: 14.10.2022 настйрока хранилища
        sharedPreferencesХранилище=   getApplicationContext().getSharedPreferences("sharedPreferencesХранилище",
                Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferencesХранилище.edit();
        editor.putString( "sharedPreferencesХранилищеkey", "sharedPreferencesХранилищеvalue" );
        editor.commit();
    }
}