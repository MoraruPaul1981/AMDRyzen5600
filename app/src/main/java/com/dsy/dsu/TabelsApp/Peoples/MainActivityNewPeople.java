package com.dsy.dsu.TabelsApp.Peoples;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;
import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.GreatUuidGenerations.GreatUuidGeneration;
import com.dsy.dsu.BusinessLogicForApps.Class_Generations_New_Customers_For_Tabels;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossContext;
import com.dsy.dsu.R;
import com.dsy.dsu.TabelsApp.Peoples.listpeoples.view.MainActivityListPeoples;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.sous.backasync.launch.ModuleQuety;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


@AndroidEntryPoint
public class MainActivityNewPeople extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {
    ////todo переменные для новго сотрдуника при создание на  активтик
    private Button butttonNewSaveCustomer;
    private EditText ЗначениеФИОСозданиеСотрудника,  ЗначениеСНИЛССозданиеСотрудника;
    private TextView  ЗначениеДеньРожденияСозданиеСотрудника;
    private   String FullNameCFO;
    private  Long MainParentUUID;

    private  Long CurrenrsСhildUUID;
    private  Integer Position;
    private  Integer ГодТабелей;

    private  Integer МЕсяцТабелей;
    private String ИмесяцвИГодСразу;

    private  Configuration config;



    private Button КнопкаНазад;
    private   int DigitalNameCFO;
    private  LinkedHashMap<String,Integer> ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи;
    private   Activity activity;


    private Context КонтекстДляАктивтиСозданиеНовогоСотрудника;
    private  Spinner СпинерВыборОрганизацииПриСозданииНовогоСотрудника;/////спинеры для создание табеля
    private    String ПолученноеТекущееЗначениеСпинераОрганизация;



    private JbossContext Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private   ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_main_create_new_customers2);
        //TODO  ОЧИЩАЕМ ПАМТЬ
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() );

        activity=this;
        ////
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new JbossContext(getApplicationContext());
        /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
     //   getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  );
        getSupportActionBar().hide(); ///скрывать тул бар
        /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
        КонтекстДляАктивтиСозданиеНовогоСотрудника=this;
        butttonNewSaveCustomer = findViewById(R.id.КнопкаСозданиеНовогоТабеля);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        /////
        Log.d(this.getClass().getName(), "   ");
        // Locale locale = Locale.ROOT;
        Locale locale = new Locale("rus");
        Locale.setDefault(locale);
        config =
                getBaseContext().getResources().getConfiguration();
        config.setLocale(locale);
        createConfigurationContext(config);
        ///TODO разное

////TODO ИНИЗАЛИЗАУМЯ ОБЬЕКТОВ НА АКТИВИТИ
        ЗначениеФИОСозданиеСотрудника = findViewById(R.id.ЗначениеЦФОПриСозданииНовогоТабеля);
        ЗначениеДеньРожденияСозданиеСотрудника = findViewById(R.id.ЗначениеДепартаментаПриСоздаенииНовогоТабеля);
        ЗначениеСНИЛССозданиеСотрудника = findViewById(R.id.ЗначениеДатаСоздаваемогоТабеля);
        ЗначениеСНИЛССозданиеСотрудника = findViewById(R.id.ЗначениеДатаСоздаваемогоТабеля);
        //todo кнопка назад
        КнопкаНазад= findViewById(R.id.imageViewСтрелкаНазадНовыйСотрудник);
                СпинерВыборОрганизацииПриСозданииНовогоСотрудника= findViewById(R.id.значениеИзСпинераОрганизацияДляНовогоСотрудника);
            // TODO: 17.04.2023 Переменные из других Активти
            //todo пришили данные из преедедущего активти с названием табеля сСАМО ИМЯ И ЕГО UUID
            методGetVaeribaleCustomers();
// TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
//todo настройки
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///////
    }

    }

    @Override
    protected void onStart() {
        super.onStart();
        try{
            МетодСозданиеСпинеровОрганизации();
            МетодЗапускаКодаПоСозданиюНовогоСотрудникаДляДвухТаблицФиоиДатаТабеля();
            МетодПолучениеДатыРожденияЧерезКалендарь();
            МетодВозврещениеНаПредыдущуюАктивтиBACK();
            ///
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }
    }













    protected void МетодСозданиеСпинеровОрганизации() {
        // TODO: 15.05.2025  
        try{
            // TODO: 24.03.2021 ЕслиВубличногоНЕтТоНАходим ЕГо
            ArrayList<String> ЛистДляАдаптераСпинерОрганизация = new ArrayList<>();
            ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи = new LinkedHashMap<>();
            // TODO: 14.05.2025
            String Текущаятаблицы="organization";
            ModuleQuety moduleQuety=new ModuleQuety(getApplicationContext());
            Cursor   Курсор_ИщемВсеОрганизации= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT *  FROM "+Текущаятаблицы+" AS D" +
                    "  WHERE D.name IS NOT NULL  " ,null);

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " Курсор_ИщемВсеОрганизации " +Курсор_ИщемВсеОрганизации);
            // TODO: 07.09.2021  полученный результат

                if(Курсор_ИщемВсеОрганизации.getCount()>0){
                    Курсор_ИщемВсеОрганизации.moveToFirst();
                    ЛистДляАдаптераСпинерОрганизация=new ArrayList<>();
                 ЛистДляАдаптераСпинерОрганизация.add("") ;
// TODO: 07.09.2021 _old по данным
                    do{
                        Log.d(this.getClass().getName(), " Курсор_ИщемПУбличныйIDКогдаегоНетВстатике " + Курсор_ИщемВсеОрганизации.getCount());
                        int ПолощениеСамаОрганизация=Курсор_ИщемВсеОрганизации.getColumnIndex("name");
                        String          СамаОрганизация =Курсор_ИщемВсеОрганизации.getString(ПолощениеСамаОрганизация);
                        Log.d(this.getClass().getName(), "  СамаОрганизация" +  СамаОрганизация);
                        ЛистДляАдаптераСпинерОрганизация.add(СамаОрганизация) ;
                        // TODO: 02.11.2021   ВтораяЧасть ПолученияID ДЛЯВставка
                        int ПолощениеСамаОрганизацияIDДЛяЗаписи=Курсор_ИщемВсеОрганизации.getColumnIndex("id");
                        Integer         СамаОрганизацияIDЗаписи =Курсор_ИщемВсеОрганизации.getInt(ПолощениеСамаОрганизацияIDДЛяЗаписи);
                        Log.d(this.getClass().getName(), "  СамаОрганизацияIDЗаписи" +  СамаОрганизацияIDЗаписи);
                        ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи.put(СамаОрганизация,СамаОрганизацияIDЗаписи);
                        // TODO: 15.05.2025  
                        Log.d(this.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи.values() " +ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи.values());
                        
                    } while (Курсор_ИщемВсеОрганизации.moveToNext());
                }
            // TODO: 07.09.2021 exit
            Курсор_ИщемВсеОрганизации.close();

// TODO: 15.05.2025  
        ArrayAdapter<String> АдаптерДляСпинераОрганизация = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,
                ЛистДляАдаптераСпинерОрганизация);
        // Определяем разметку для использования при выборе элемента
        АдаптерДляСпинераОрганизация.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        СпинерВыборОрганизацииПриСозданииНовогоСотрудника.setAdapter(АдаптерДляСпинераОрганизация);

        //
        СпинерВыборОрганизацииПриСозданииНовогоСотрудника.setHorizontalScrollBarEnabled(true);
        ////что быврали
        СпинерВыборОрганизацииПриСозданииНовогоСотрудника.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //////СПИНЕР ДЕПАРТАМЕНТ
                if (position>0) {///ставим ограничкния если выбрано не 0 позиция то запонимаеним

                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    ((TextView) parent.getChildAt(0)).setTextSize(12);
                    ((TextView) parent.getChildAt(0)).setTextSize(12);
                    ((TextView) parent.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
                    ((TextView) parent.getChildAt(0)).setHint("Выберете Организацию".toUpperCase(Locale.ROOT));
                    ((TextView) parent.getChildAt(0)).setHintTextColor(Color.parseColor("#675757"));
                    ((TextView) parent.getChildAt(0)).setBackgroundResource(R.drawable.textlinesgrey);
                    ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

                    ПолученноеТекущееЗначениеСпинераОрганизация = parent.getItemAtPosition(position).toString();

                    Log.d(this.getClass().getName(), "ПолученноеТекущееЗначениеСпинераОрганизация " + ПолученноеТекущееЗначениеСпинераОрганизация);
                        /*Toast toast = Toast.makeText(getApplicationContext(),
                                "Ваш выбор Раздел : " + ПолученноеЗначениеИзСпинераРаздел + " " + position, Toast.LENGTH_SHORT);
                        toast.show();*/

                }else if (position==0){
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    ((TextView) parent.getChildAt(0)).setBackgroundResource(R.drawable.textlinesgrey);
                    ((TextView) parent.getChildAt(0)).setTextSize(12);
                    ((TextView) parent.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
                    ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    ((TextView) parent.getChildAt(0)).setHint("Выберете Организацию".toUpperCase(Locale.ROOT));
                    ((TextView) parent.getChildAt(0)).setHintTextColor(Color.parseColor("#675757"));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

            ///////
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }















    @Override
    protected void onDestroy() {
        super.onDestroy();

        //////TODO  данный код срабатывает когда произошда ошивка в базе

    }






    @Override
    protected void onPause() {
        super.onPause();
        try {

          ///  МетодЗапускаЛокальнойСинхронизации();
            ///////
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                   new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    protected void методGetVaeribaleCustomers() {
        try{
            Intent ИнтентActivityNewCustomer = getIntent();
           Bundle bundleActivityNewCustomer =      ИнтентActivityNewCustomer.getExtras();
            if (bundleActivityNewCustomer!=null) {
                MainParentUUID=    bundleActivityNewCustomer.getLong("MainParentUUID", 0);
                Position=    bundleActivityNewCustomer.getInt("Position", 0);
                ГодТабелей=  bundleActivityNewCustomer.getInt("ГодТабелей", 0);
                МЕсяцТабелей=  bundleActivityNewCustomer.getInt("МЕсяцТабелей",0);
                DigitalNameCFO=   bundleActivityNewCustomer.getInt("DigitalNameCFO", 0);
                FullNameCFO=  bundleActivityNewCustomer.getString("FullNameCFO", "");
                ИмесяцвИГодСразу= bundleActivityNewCustomer.getString("ИмесяцвИГодСразу", "");
                CurrenrsСhildUUID= bundleActivityNewCustomer.getLong("CurrenrsСhildUUID", 0l);
            }
// TODO: 17.04.2023  //////////20.15
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " bundleActivityNewCustomer " +bundleActivityNewCustomer);
        } catch (Exception e) {
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }









///todo метод который возврящаем с текущего активити на предыдущий
///todo метод получение ДАТЫ РОЖДЕНИЯ ИЗ  КАЛЕНЛАРЯ
private void МетодВозврещениеНаПредыдущуюАктивтиBACK() {
    КнопкаНазад.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(this.getClass().getName(), " кликнем для созданни новго сотрдника при нажатии  ");
            ///todo код которыц возврящет предыдущий актвитики кнопка back
            //TODO ПОСЛЕ УСПЕШНОЙ СОЗДАНИЕ НОВОГО СОТРУДНИКА ПЕРЕХОДИМ В ТАБЕЛЯ

            методBackActivityListPeoples();
            //////

        }
    });
}





///todo метод получение ДАТЫ РОЖДЕНИЯ ИЗ  КАЛЕНЛАРЯ
    private void МетодПолучениеДатыРожденияЧерезКалендарь() {
        ЗначениеДеньРожденияСозданиеСотрудника.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(this.getClass().getName(), " кликнем для созданни новго сотрдника при нажатии  ");
                МетодВытаскиваемИзКалендаряДиалогаКалендаряДаты();
            }
        });
    }
    ///поймать ошибку
    private void МетодВытаскиваемИзКалендаряДиалогаКалендаряДаты() {///////метод создание календяря даты
/////TODO тут визуализикуеться КАЛЕНДАРЬ
        DatePickerDialog ДатаДляКалендаря=new DatePickerDialog(this, (DatePickerDialog.OnDateSetListener) this,
                GregorianCalendar.getInstance().get(Calendar.YEAR),
                GregorianCalendar.getInstance().get(Calendar.MONTH),
                GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
        ДатаДляКалендаря.setIcon(R.drawable.icon_dsu1_new_customer7 );
        ДатаДляКалендаря.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuffer БуферПолученаяДатаРожденияСотрудника=new StringBuffer();
        //todo make offset
        switch (month){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                month=month+1;
                break;
                /////todo яныварь
            case 0:
                month=month+1;
                break;
        }
//todo выравниваем длину месяца
        String МесяцФинал=String.valueOf(month);
        if (МесяцФинал.length()==1) {
            МесяцФинал = "0" + МесяцФинал;
        }
        ////todo  выравниваем длину день
        String ДеньФинал=String.valueOf(dayOfMonth);
        if (ДеньФинал.length()==1) {
            ДеньФинал = "0" + ДеньФинал;
        }
        БуферПолученаяДатаРожденияСотрудника.append(ДеньФинал).append(".").append(МесяцФинал).append(".").append(year);
        Log.d(this.getClass().getName(), " stringBuffer  "+ БуферПолученаяДатаРожденияСотрудника.toString());
        //TODO ЗАПОЛНЯЕМ
        ЗначениеДеньРожденияСозданиеСотрудника.setText(БуферПолученаяДатаРожденияСотрудника.toString());
    }














    ///todo данный метод начальный для создание нового сотрудника с кнопки
       void МетодЗапускаКодаПоСозданиюНовогоСотрудникаДляДвухТаблицФиоиДатаТабеля()  throws  InterruptedException{
           final AtomicLong РезультатВставкиDataTabels = new AtomicLong(0l);
           try{
            butttonNewSaveCustomer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), " ЗначениеФИОСозданиеСотрудника  "+ ЗначениеФИОСозданиеСотрудника+
                            " ЗначениеДеньРожденияСозданиеСотрудника  " + ЗначениеДеньРожденияСозданиеСотрудника +
                            " ЗначениеСНИЛССозданиеСотрудника  " +ЗначениеСНИЛССозданиеСотрудника);
                    // TODO: 09.08.2022 создаем новго сотрудника
                    int ТекущаяПозиция=СпинерВыборОрганизацииПриСозданииНовогоСотрудника.getSelectedItemPosition();
                    Integer getPublicID=  new GetttingPublicID().getttingPublicID(getApplicationContext());
                    ПолученноеТекущееЗначениеСпинераОрганизация=( СпинерВыборОрганизацииПриСозданииНовогоСотрудника.getItemAtPosition(ТекущаяПозиция).toString());
                    Log.d(this.getClass().getName(), " ПолученноеТекущееЗначениеСпинераОрганизация  "+ ПолученноеТекущееЗначениеСпинераОрганизация);


                    if (ЗначениеФИОСозданиеСотрудника.length() > 0
                            && ЗначениеДеньРожденияСозданиеСотрудника.length() > 0
                            && ЗначениеСНИЛССозданиеСотрудника.length()>0 &&
                            ТекущаяПозиция!=0 &&
                            СпинерВыборОрганизацииПриСозданииНовогоСотрудника.getItemAtPosition(ТекущаяПозиция).toString()!=null &&
                            СпинерВыборОрганизацииПриСозданииНовогоСотрудника.getItemAtPosition(ТекущаяПозиция).toString()!="") {
                        // TODO: 17.04.2023 наинаем встаавку новаого сотрудинка Single

                        Completable.fromAction(new Action() {
                                    @Override
                                    public void run() throws Throwable {
                                        Integer РезультатВставкивТаблицуФИО = 0;
                                        final Long  РезультатВставкивТаблицуDataTabels = 0l;
/////TODO перед созданием определяем не пустые ли значения
                                        //TODO внешний вид
                                        activity.runOnUiThread(()->{
                                            progressDialog=new ProgressDialog(activity  );
                                            progressDialog.setCancelable(false);
                                            progressDialog.setTitle("Создание сотрудника");
                                            progressDialog.setMessage("в процессе...");
                                            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                            progressDialog.setMax(1);
                                            progressDialog.setProgress(0);
                                            progressDialog.setIndeterminate(true);
                                            progressDialog.show();
                                        });


                                        Long   UUIDGenetetorNewCustoner= (Long) new GreatUuidGeneration(getApplicationContext()).greatUuidGeneration();
                                        // TODO: 23.09.2021  получение из даты месяц и год
                                        Log.d(this.getClass().getName(), " ИмесяцвИГодСразу  " + ИмесяцвИГодСразу);
                                        // TODO: 22.09.2021 обработка ТАБЛИЦА ФИО

                                        РезультатВставкивТаблицуФИО = new Class_Generator_New_Customer_In_Table_Fio()
                                                .методВставкиВТАблицуФИО(ТекущаяПозиция,UUIDGenetetorNewCustoner,getPublicID);
                                        // TODO: 22.09.2021 ПОСЛЕ ДВУХ ОБРАБОТКАХ  ФИО И ДАТА_ТАБЕЛЬ ПЕРЕРХОДИМ НА ДРГОЕ АКТИВТИ
                                        Log.d(this.getClass().getName(), " РезультатВставкивТаблицуФИО  " + РезультатВставкивТаблицуФИО);



                                        if (РезультатВставкивТаблицуФИО >0) {
                                            // TODO: 22.09.2021  ТАБЛИЦА ДАТА_ТАБЕЛЯ
                                            РезультатВставкиDataTabels.addAndGet( new Class_Generator_New_Customer_In_Table_Data_Tables().
                                                    методСозданиеНовогоСотрудникаDataTabels(UUIDGenetetorNewCustoner,
                                                            МЕсяцТабелей,
                                                            ГодТабелей,getPublicID));

                                            Log.d(this.getClass().getName(),"\n" + " class " +
                                                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                                     " РезультатВставкиDataTabels " +РезультатВставкиDataTabels.get());

                                        }



                                    }
                                })
                                .subscribeOn(Schedulers.single())
                                .observeOn(AndroidSchedulers.mainThread()).
                                doOnComplete(new Action() {
                                    @Override
                                    public void run() throws Throwable {
                                        progressDialog.setProgress(1);

                                        if (РезультатВставкиDataTabels.get()>0) {
                                            // TODO: 17.04.2023 переходим на обратно в активити выбор сотрудников
                                            if (progressDialog!=null) {
                                                progressDialog.setIndeterminate(false);
                                                progressDialog.dismiss();
                                            }

                                            методBackActivityListPeoples();


                                            Log.d(this.getClass().getName(),"\n" + " class " +
                                                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                                    " РезультатВставкиDataTabels " +РезультатВставкиDataTabels.get());
                                        }else {

                                            ЗначениеФИОСозданиеСотрудника.setError("ошибка!!!");

                                           Snackbar.make(v, "Сотрудник не создан !!!",Snackbar.LENGTH_LONG).setAction("Action",null).show();


                                            Log.d(this.getClass().getName(),"\n" + " class " +
                                                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                                    " РезультатВставкиDataTabels " +РезультатВставкиDataTabels.get());

                                        }
                                        // TODO: 17.04.2023  //////////20.15
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                    }
                                })
                                .doOnError(new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Throwable {
                                        ///метод запись ошибок в таблицу
                                        Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new RecordNewErros(getApplicationContext()).recordnewerror(throwable.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                }).subscribe();

                    } else {

                            Snackbar.make(v, "Данные не заполнены !!!", Snackbar.LENGTH_LONG).show();
                            Log.i(this.getClass().getName(), " Не все поля заполены (снилс от 10 знаков) ");

                        Toast.makeText(getApplicationContext(),     "Данные не заполнены !!!"    , Toast.LENGTH_LONG).show();
                    }
                }
            });
       } catch (Exception e) {
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

































    //TODO ПОСЛЕ УСПЕШНОЙ СОЗДАНИЕ НОВОГО СОТРУДНИКА ВОЗВРАЩАЕМСЯ ОБРАТНО В ТАБЕЛЬ КУДА ДОБАВЛЯЛИ СОТРУЛНИКА


    private void методBackActivityListPeoples() {
        try{
            Intent ИнтентBackActivityListPeoples = new Intent();
            ИнтентBackActivityListPeoples.setClass(this, MainActivityListPeoples.class);
            Bundle dataBackActivityListPeoples=new Bundle();
            dataBackActivityListPeoples.putLong("MainParentUUID", MainParentUUID);
            dataBackActivityListPeoples.putInt("Position",    Position);
            dataBackActivityListPeoples.putInt("ГодТабелей",     ГодТабелей);
            dataBackActivityListPeoples.putInt("МЕсяцТабелей", МЕсяцТабелей);
            dataBackActivityListPeoples.putInt("DigitalNameCFO",  DigitalNameCFO);
            dataBackActivityListPeoples.putString("FullNameCFO", FullNameCFO);
            dataBackActivityListPeoples.putString("ИмесяцвИГодСразу",    ИмесяцвИГодСразу);
            dataBackActivityListPeoples.putLong("CurrenrsСhildUUID",  CurrenrsСhildUUID);
            ИнтентBackActivityListPeoples.putExtras(dataBackActivityListPeoples);
            startActivity(ИнтентBackActivityListPeoples);
        // TODO: 17.04.2023  //////////20.15
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





    ///todo сообщение на активти создание новго сотрудника спрашиваем нужно ли создать
    @UiThread
    protected void СообщениеСообщаетОСоздаенииНовогоСотрудника(String ШабкаДиалога, final String СообщениеДиалога, boolean статус) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        int ФлагЗнака;
        if (статус) {
            ФлагЗнака = R.drawable.icon_dsu1_new_customer_success;
        } else {
            ФлагЗнака = R.drawable.icon_dsu1_new_customer_error;
        }

        try {
//////сам вид
            final AlertDialog alertDialog = new MaterialAlertDialogBuilder(КонтекстДляАктивтиСозданиеНовогоСотрудника)
                    .setTitle(ШабкаДиалога)
                    .setMessage(СообщениеДиалога)
                    .setPositiveButton("ОК", null)
                    .setIcon(ФлагЗнака)
                    .show();
/////////кнопка
            final Button MessageBoxUpdateСоздатьТабель = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxUpdateСоздатьТабель.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                    Log.d(this.getClass().getName(), "  ФИНАЛ создание нового сотрудника ");

                    if (статус) {
                        ///todo после как мы либо создали новогосо остружника или обновли его в табел то обнуляем



                        //todo обнуляем ПОСЛЕ ВСТАВКИ НОВГО СОТРУДНИКА

                        ЗначениеФИОСозданиеСотрудника=null;

                        ЗначениеДеньРожденияСозданиеСотрудника=null;

                        ЗначениеСНИЛССозданиеСотрудника=null;

///TODO метод запуска формы после вставки
                        //TODO ПОСЛЕ УСПЕШНОЙ СОЗДАНИЕ НОВОГО СОТРУДНИКА ПЕРЕХОДИМ В ТАБЕЛЯ



                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    /////////todo проверика подключение к wi fi












































    // TODO: 22.09.2021   KLASS GENERATOR NEW CUSTOMERS  IN TABLE FIO


    class  Class_Generator_New_Customer_In_Table_Fio   {
        /////////
        public Class_Generator_New_Customer_In_Table_Fio() {


        }

        // TODO: 22.09.2021   обработка ТАБЛИЦЫ ФИО



        // TODO: 22.09.2021  ЗАПИСЬ НОВОГО СОТДУНИКА ПЕРВОЕ  ДЕЙСТИЕ ЗАПИСЬВ ТАБЛИЦУ ФИО



      @SuppressLint("SuspiciousIndentation")
      protected Integer методВставкиВТАблицуФИО(@NotNull  int ТекущееЗначение,
                                                @NotNull Long   UUIDGenetetorNewCustoner
                                             , @NotNull Integer ПубличноеID) throws InterruptedException {

            Integer РезультаВставкиВТАблицуФИО=0;
            try {
                ContentValues АдаптерДляСозданиеНовогоСотрудаТАблицаФИО = new ContentValues();////контрейнер для нового табеля
                String НазваниеФИО=ЗначениеФИОСозданиеСотрудника.getText().toString();
                String ЗначениеДеньРождения=ЗначениеДеньРожденияСозданиеСотрудника.getText().toString();
                Object  ПолученныйСНИЛСНовогоСотрудникаПереход=ЗначениеСНИЛССозданиеСотрудника.getText().toString().replaceAll("[^0-9]","").trim();
                Long ПолученныйСНИЛСНовогоСотрудника=Long.parseLong(ПолученныйСНИЛСНовогоСотрудникаПереход.toString());


                // TODO: 08.05.2025 ВСавка новго сотржника
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("name",НазваниеФИО);
                Flowable.just(НазваниеФИО).map(new Function<String, Object>() {
                    @SuppressLint("NewApi")
                    @Override
                    public Object apply(String s) throws Throwable {
                        String[] words = s.split(" ");
                        // TODO: 08.05.2025
                        List<String> stringsNameFio = Arrays.asList(words);
                        AtomicInteger atomicInteger=new AtomicInteger(0);
                        stringsNameFio.forEach(new java.util.function.Consumer<String>() {
                            @Override
                            public void accept(String splitfio) {
                                // TODO: 08.05.2025
                                if (atomicInteger.get()==0) {
                                    АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("f",splitfio);
                                }
                                if (atomicInteger.get()==1) {
                                    АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("n",splitfio);
                                }
                                if (atomicInteger.get()==2) {
                                    АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("o",splitfio);
                                }
                                // TODO: 08.05.2025
                                atomicInteger.incrementAndGet();

                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + " atomicInteger.get()"
                                        +atomicInteger.get());
                            }
                        });

                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " words"
                                +words);


                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            return words;
                    }
                }).blockingSubscribe();

                Log.d(this.getClass().getName(), "  АдаптерДляСозданиеНовогоСотрудаТАблицаФИО " + АдаптерДляСозданиеНовогоСотрудаТАблицаФИО);
                Long РезультатВычисляемВреисюДанных =
                        new VersionCurentTable(getApplicationContext()).upVersionCurentTable(    "fio"  );
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("current_table",РезультатВычисляемВреисюДанных);
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("BirthDate",ЗначениеДеньРождения);
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("snils",ПолученныйСНИЛСНовогоСотрудника);

                // TODO: 14.05.2025 поиск Снилса
                String Текущаятаблицы="fio";
                ModuleQuety moduleQuety=new ModuleQuety(getApplicationContext());
                Cursor    Курсор_ИщемЕслиТАкойСнилсУже= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT D.snils  FROM "+Текущаятаблицы+" AS D" +
                        "  WHERE   D.snils  ='"+ПолученныйСНИЛСНовогоСотрудника+"' ORDER BY   D.date_update DESC  LIMIT 1  " ,null);

                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " Курсор_ИщемЕслиТАкойСнилсУже " +Курсор_ИщемЕслиТАкойСнилсУже);
                // TODO: 01.11.2021
                if( Курсор_ИщемЕслиТАкойСнилсУже.getCount()==0){
                    // TODO: 08.05.2025
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("uuid",UUIDGenetetorNewCustoner);
                String ДатаПРиСозданииНовогоСотрудника=null;
                String СгенерированованныйДатаДляДаннойОперации=     new GetMainDateForApp(getApplicationContext()).getMainDateForApp();
                ДатаПРиСозданииНовогоСотрудника = СгенерированованныйДатаДляДаннойОперации;
                Log.d(this.getClass().getName(), " ДатаПРиСозданииНовогоСотрудника" + ДатаПРиСозданииНовогоСотрудника);
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("date_update",ДатаПРиСозданииНовогоСотрудника);
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("user_update", ПубличноеID);


                // TODO: 23.09.2021  повышаем верисю таблицы фио
                // TODO: 08.092021  метод после заполения данными
                РезультаВставкиВТАблицуФИО=       new Class_Generations_New_Customers_For_Tabels(getApplicationContext()).
                        МетодЗаписиСозданогоСотрудникаВБазуПоТаблицы_ФИО(АдаптерДляСозданиеНовогоСотрудаТАблицаФИО,activity);

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультаВставкиВТАблицуФИО"
                            +РезультаВставкиВТАблицуФИО);

                }else{
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Такой  СНИЛС уже есть !!!"
                                    +ПолученныйСНИЛСНовогоСотрудника , Toast.LENGTH_LONG).show();

                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }
                    });


                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
//TODO ОКОНЧИАЕМ ВСТАВКУ ДАННЫХ
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультаВставкиВТАблицуФИО;
        }
    }

    // TODO: 22.09.2021   KLASS GENERATOR NEW CUSTOMERS  IN TABLE data_tabels
    // TODO: 22.09.2021   KLASS GENERATOR NEW CUSTOMERS  IN TABLE data_tabels
    // TODO: 22.09.2021   KLASS GENERATOR NEW CUSTOMERS  IN TABLE data_tabels
    class   Class_Generator_New_Customer_In_Table_Data_Tables   {
    // TODO: 22.09.2021  ТАБЛИЦА ДАТА_ТАБЕЛЬ
    // TODO: 22.09.2021  ЗАПИСЬ НОВОГО СОТДУНИКА ВТОРОЕ ДЕЙСТИЕ ЗАПИСЬВ ТАБЛИЦУ ДАТА_ТАБЕЛЯ
   protected Long методСозданиеНовогоСотрудникаDataTabels(@NotNull  Long UUIDGenetetorNewCustoner,
                                                          int ГодПриВставкеНовогоСотрудника,
                                                          int  МЕсяцПриВставкеНовогоСотрудника,
                                                          @NotNull Integer ПубличноеID) throws InterruptedException {


        Long РезультатВставкиВтаблицу_Дата_табеля=0l;
        try {
            ContentValues  АдаптерДляСозданиеНовогоСотрудаТАблицаТабель=new ContentValues();
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("fio",UUIDGenetetorNewCustoner);
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("uuid_tabel",MainParentUUID);
        //    АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.putNull("_id");
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("status_carried_out", "False");
            Long РезультатПовышаемВреисюДанныхТаблицы_Дата_Табеля =
                    new VersionCurentTable(getApplicationContext()).upVersionCurentTable(    "data_tabels" );
            // TODO: 23.09.2021  повышаем верисю таблицы фио
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("current_table",РезультатПовышаемВреисюДанныхТаблицы_Дата_Табеля);


            String СгенерированованныйДатаДляДаннойОперации=     new GetMainDateForApp(getApplicationContext()).getMainDateForApp();
            String      ДатаПРиСозданииНовогоСотрудника = СгенерированованныйДатаДляДаннойОперации;
            Log.d(this.getClass().getName(), " ДатаПРиСозданииНовогоСотрудника" + ДатаПРиСозданииНовогоСотрудника);
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("date_update",ДатаПРиСозданииНовогоСотрудника);
            // TODO: 23.09.2021
            Log.d(this.getClass().getName(), " ДатаПРиСозданииНовогоСотрудника" + ДатаПРиСозданииНовогоСотрудника);

          Long UUidGeneratorDataTabels =  (Long)  new GreatUuidGeneration(getApplicationContext()).greatUuidGeneration();
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("uuid",UUidGeneratorDataTabels);
                // TODO: 24.03.2021 ЕслиВубличногоНЕтТоНАходим ЕГо

                final int[] ТекущуюОрганизацию = {0};
                final Cursor[] Курсор_ИщемТекущуюОрганизациюКоторуюВыбраСОтрудник = {null};
            // TODO: 23.09.2021  повышаем верисю таблицы фио
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("status_send", " ");
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("user_update", ПубличноеID);
            Log.d(this.getClass().getName(),"ТекущуюОрганизацию[0] " + ТекущуюОрганизацию[0] );
            // TODO: 08.09.2021  метод после заполения данными
            РезультатВставкиВтаблицу_Дата_табеля=   new Class_Generations_New_Customers_For_Tabels(getApplicationContext()).
                    МетодЗаписиСозданогоСотрудникаВБазуПоТаблицы_Дата_Табеля(
                    АдаптерДляСозданиеНовогоСотрудаТАблицаТабель
                    ,activity,
                    ГодПриВставкеНовогоСотрудника,
                    МЕсяцПриВставкеНовогоСотрудника, UUidGeneratorDataTabels);/////TODO НОВЫЙ НУЖЕН НЕ ДЛЯ ВСТАВКИ А ДЛЯ ВТОРОГО ДЕЙСТВИЯ ЗАПОЛЕНИЯ ВЫХОДНЫМИ ЕСЛИ НВ НАСТРОЙКАХ ЕСТЬ КАКАЯ ФУЕНКЦИЯ И ОНА ВКЛЮЧЕН

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " РезультатВставкиВтаблицу_Дата_табеля " +РезультатВставкиВтаблицу_Дата_табеля);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  РезультатВставкиВтаблицу_Дата_табеля;

    }

    // TODO: 22.09.2021   метод записи дата_табельс



    }

}