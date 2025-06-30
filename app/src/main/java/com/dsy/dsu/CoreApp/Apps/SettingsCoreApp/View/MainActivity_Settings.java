package com.dsy.dsu.CoreApp.Apps.SettingsCoreApp.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossContext;
import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.LaunchActivityDiaologSettings;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.dsy.dsu.CoreApp.Apps.SettingsCoreApp.Model.ChangeSSLForSettings;
import com.google.android.material.button.MaterialButton;
import com.sous.backasync.launch.ModuleQuety;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import dagger.hilt.android.AndroidEntryPoint;


//вывод данных на Автивити
@AndroidEntryPoint
public class MainActivity_Settings extends AppCompatActivity {

    private Map<String, String> ХэшДанныеИзБазыДляЗАполенияСпинеровыОрганизация = Collections.synchronizedMap(new LinkedHashMap<String, String>());


    private Spinner СпинерВыборОрганизации;
    private Cursor Курсор_СамиДанные_Logins = null;
    private int ЕстьСтроки;
    private MaterialButton imageViewСтрелкаВнутриНастроек, КнопкаСохранениеОрганизации;

    private Switch СвичДляWIFI, switchАвтоЗаполенияВТАбелеВыходных, switchСкрытыеПоляПолучениеМатериалов, switchJbossЗащищеный;
    private Context context;
    private TextView textViewИмяПрограммы;
    private TextView textViewВерсияПрограммы;
    private TextView textViewТекущийПользователь;
    private TextView textViewВремяПоследнееСинхронизации;
    private int ПубличныйIDДляорганизацции = 0;
    private String ДатаДляОбновлениеОргназации;
    private SharedPreferences preferences;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    // TODO: 12.10.2021  Ссылка Менеджер Потоков
    JbossContext jbossContext = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Log.d(this.getClass().getName(), "Запущен.... метод  onCreate в классе MainActivity_Settings  ; ");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_settings);
            getSupportActionBar().hide(); ///скрывать тул бар

            preferences = getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();


            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            jbossContext = new JbossContext(getApplicationContext());
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            getSupportActionBar().hide(); ///скрывать тул бар
            context = this;
            // TODO: 16.04.2025
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());


            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            imageViewСтрелкаВнутриНастроек = (MaterialButton) findViewById(R.id.imageViewСтрелкаВнутриНастроек);
            textViewВерсияПрограммы = (TextView) findViewById(R.id.textViewВерсияПрограммы);
            Log.d(this.getClass().getName(), "  textViewВерсияПрограммы " + textViewВерсияПрограммы.getText());
            textViewТекущийПользователь = (TextView) findViewById(R.id.textViewТекущийПользователь);
            String ПолученыйТекущееИмяПользователя = new CoreBinessLogics(getApplicationContext()).МетодПолучениеИмяСистемыДляСменыПользователя(getApplicationContext());
            Log.d(this.getClass().getName(), "  ПолученыйТекущееИмяПользователя  " + ПолученыйТекущееИмяПользователя);
            textViewТекущийПользователь.setText("Пользователь: " + ПолученыйТекущееИмяПользователя.toUpperCase());
            textViewВремяПоследнееСинхронизации = (TextView) findViewById(R.id.textViewВремяПоследнееСинхронизации);
            СвичДляWIFI = (Switch) findViewById(R.id.switchWIFI);
            switchАвтоЗаполенияВТАбелеВыходных = (Switch) findViewById(R.id.switchАвтоЗаполенияВТАбелеВыходных);
            switchСкрытыеПоляПолучениеМатериалов = (Switch) findViewById(R.id.switchСкрытыеПоляПолучениеМатериалов);
            switchJbossЗащищеный = (Switch) findViewById(R.id.switchsslcomunications);


            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            МетодСозданиеКодBACK();
            МетодОбработкиСвичаДляWIFI();
            МетодОбработкиСвичаАвтоматическогоДобавлениямМеткуВыходныхДней();
            методВычисляетПоследнуюДатуСинхронищацииССервром();
            МетодОбработкиСкрытыхматериалов();
            settheCurrentVersionoftheProgramVersion();

            // TODO: 08.10.2024 SSL
            ChangeSSLForSettings sslForSettings = new ChangeSSLForSettings(switchJbossЗащищеный, getApplicationContext());

            sslForSettings.changeSwitcSllSimple();
            sslForSettings.changeSwitcSllSimpleLister();


            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодОбработкиСкрытыхматериалов() {
        try {
            Boolean ФлагДляСкрытыхМатериалов = preferences.getBoolean("ФлагДляСкрытыхМатериалов", false);
            //////////TODO КАК РЕЖИМ РАБОТЫ ИНТРЕНТА И  ПЕРЕОПРЕДЕЛЯЕМ ВИЗУАЛЬНО
            if (ФлагДляСкрытыхМатериалов) {
                switchСкрытыеПоляПолучениеМатериалов.setChecked(true);
                switchСкрытыеПоляПолучениеМатериалов.setText("Материалы (Открытые)");///Оба Mobile/Wifi
            } else {
                switchСкрытыеПоляПолучениеМатериалов.setChecked(false);
                switchСкрытыеПоляПолучениеМатериалов.setText("Материалы (Скрытые)");

            }

            switchСкрытыеПоляПолучениеМатериалов.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SharedPreferences.Editor editor = preferences.edit();
                    if (isChecked) {
                        MainActivity_Settings.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switchСкрытыеПоляПолучениеМатериалов.setText("Материалы (Открытые)");
                                editor.putBoolean("ФлагДляСкрытыхМатериалов", true);
                            }
                        });
                    } else {
                        MainActivity_Settings.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switchСкрытыеПоляПолучениеМатериалов.setText("Материалы (Скрытые)");
                                editor.putBoolean("ФлагДляСкрытыхМатериалов", false);
                            }
                        });
                    }
                    editor.commit();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


// TODO: 02.06.2021 метод которыу вычислет и заполянет ДАТУПОСЛЕДНЕЙ СИНХРОНИЗАЦИИ С СЕРВЕРОМ

    @SuppressLint("Range")
    protected void методВычисляетПоследнуюДатуСинхронищацииССервром() {
        // TODO: 15.05.2025
        Cursor CursorlastDateAsync = null;
        try {
            String ПоследнаяДата = null;
            // TODO: 14.05.2025
            String Текущаятаблицы = "MODIFITATION_Client";
            ModuleQuety moduleQuety = new ModuleQuety(context);
            CursorlastDateAsync = moduleQuety.getModuleQuery(Текущаятаблицы,
                    " SELECT MAX ( D.versionserveraandroid  ) " +
                            "AS MAX_R  FROM " +  Текущаятаблицы.trim()+" AS D" , null);
            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " CursorlastDateAsync " + CursorlastDateAsync);
            ////TODO   результат
            if (CursorlastDateAsync.getCount() > 0) {
                CursorlastDateAsync.moveToFirst();////
                ПоследнаяДата = CursorlastDateAsync.getString(CursorlastDateAsync.getColumnIndex("MAX_R"));
                Log.d(this.getClass().getName(), "ПоследнаяДата" + ПоследнаяДата);
            }
            if (ПоследнаяДата != null) {
                textViewВремяПоследнееСинхронизации.setText("Успешный обмен : Дата " + ПоследнаяДата);
            }
            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " ПоследнаяДата " + ПоследнаяДата);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодОбработкиСвичаДляWIFI() throws InterruptedException {
        try {
            String РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile = null;
            // TODO: 14.05.2025
            String Текущаятаблицы = "SuccessLogin";
            ModuleQuety moduleQuety = new ModuleQuety(context);
            Cursor КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile = moduleQuety.getModuleQuery(Текущаятаблицы, " SELECT D.mode_connection  FROM " + Текущаятаблицы + " AS D", null);
            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile " + КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile);


            if (КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile.getCount() > 0) {
                КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile.moveToFirst();
                РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile = КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile.getString(0);
                Log.d(getApplicationContext().getClass().getName(), " РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile  " + "--" + РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile);/////
            }
            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile " + РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile);
            final Toast[] aa = new Toast[1];
            final ImageView[] cc = new ImageView[1];
            MainActivity_Settings.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    aa[0] = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_SHORT);
                    cc[0] = new ImageView(getBaseContext());
                }
            });
            //////////TODO КАК РЕЖИМ РАБОТЫ ИНТРЕНТА И  ПЕРЕОПРЕДЕЛЯЕМ ВИЗУАЛЬНО
            switch (РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile.trim()) {
                case "Mobile":
                    this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            СвичДляWIFI.setChecked(true);
                            СвичДляWIFI.setText("Mobile/Wifi (Сеть)");///Оба Mobile/Wifi
                            //  cc[0].setImageResource(R.drawable.icon_dsu1_for_dont_wifi);
                            aa[0].setView(cc[0]);
                            aa[0].show();
                        }
                    });
                    break;
                ///TODO WIFI ТОЛЬКО
                case "WIFI":
                    this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            СвичДляWIFI.setChecked(false);
                            СвичДляWIFI.setText("WIFI");
                            //   cc[0].setImageResource(R.drawable.icon_dsu1_for__wifi);
                            aa[0].setView(cc[0]);
                            aa[0].show();
                        }
                    });
                    break;
            }
            СвичДляWIFI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //////TODO ЕСЛИ РЕЖИМ TRUE  MOBILE ВПИСЫВАЕМ КАК В БАЗУ MOBILE
                    if (isChecked) {
                        // The toggle is enabled mobile
                        Integer РезультатЗаписиНовогоРЕжима = new CoreBinessLogics(getApplicationContext())
                                .МетодКоторыйЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobile("Mobile", getApplicationContext()
                                        , "SuccessLogin", "mode_connection");
                        Log.d(this.getClass().getName(), "РезультатЗаписиНовогоРЕжима " + РезультатЗаписиНовогоРЕжима);
                        MainActivity_Settings.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                СвичДляWIFI.setText("Mobile/Wifi (Сеть)");
                                Toast aa = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_SHORT);
                                ImageView cc = new ImageView(getBaseContext());
                                cc.setImageResource(R.drawable.icon_dsu1_for_dont_wifi);
                                aa.setView(cc);
                                aa.show();
                            }
                        });
                    } else {
                        // The toggle is disabled
                        Integer РезультатЗаписиНовогоРЕжима = new CoreBinessLogics(getApplicationContext()).МетодКоторыйЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobile("WIFI", getApplicationContext()
                                , "SuccessLogin", "mode_connection");
                        Log.d(this.getClass().getName(), "РезультатЗаписиНовогоРЕжима " + РезультатЗаписиНовогоРЕжима);
                        ///TODO принудительно устанвливаем редим работы синхронизации
                        MainActivity_Settings.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                СвичДляWIFI.setText("Wifi");
                                Toast aa = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_SHORT);
                                ImageView cc = new ImageView(getBaseContext());
                                cc.setImageResource(R.drawable.icon_dsu1_for__wifi);
                                aa.setView(cc);
                                aa.show();
                            }
                        });
                    }
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

    private void МетодОбработкиСвичаАвтоматическогоДобавлениямМеткуВыходныхДней() throws InterruptedException {
        try {
            String РезультатКакойРежимЗаписанвБазеВЫходныеДни = new String();
            String РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile = null;
            // TODO: 14.05.2025
            String Текущаятаблицы = "SuccessLogin";
            ModuleQuety moduleQuety = new ModuleQuety(context);
            Cursor КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile = moduleQuety.getModuleQuery(Текущаятаблицы, " SELECT D.mode_connection  FROM " + Текущаятаблицы + " AS D", null);
            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile " + КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile);

            if (КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile.getCount() > 0) {
                КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile.moveToFirst();
                РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile = КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile.getString(0);
                Log.d(getApplicationContext().getClass().getName(), " РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile  " + "--" + РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile);/////
            }
            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile " + РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile);


            Log.d(this.getClass().getName(), " РезультатКакойРежимЗаписанвБазеВЫходныеДни : " + РезультатКакойРежимЗаписанвБазеВЫходныеДни);
            ////
            final Toast[] aa = new Toast[1];
            final ImageView[] cc = new ImageView[1];
            MainActivity_Settings.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    aa[0] = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_SHORT);
                    cc[0] = new ImageView(getBaseContext());
                }
            });


            //////////TODO КАК РЕЖИМ РАБОТЫ ИНТРЕНТА И  ПЕРЕОПРЕДЕЛЯЕМ ВИЗУАЛЬНО
            switch (РезультатКакойРежимЗаписанвБазеВЫходныеДни) {
                case "Включить":
                    this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switchАвтоЗаполенияВТАбелеВыходных.setChecked(true);
                            switchАвтоЗаполенияВТАбелеВыходных.setText("Вкл (Выходные)");
                            //  cc[0].setImageResource(R.drawable.icon_dsu1_for_dont_wifi);
                            aa[0].setView(cc[0]);
                            aa[0].show();

                        }
                    });
                    break;


                ///TODO WIFI ТОЛЬКО
                case "Выключить":
                    this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switchАвтоЗаполенияВТАбелеВыходных.setChecked(false);
                            switchАвтоЗаполенияВТАбелеВыходных.setText("Выкл (Выходные)");
                            //   cc[0].setImageResource(R.drawable.icon_dsu1_for__wifi);
                            aa[0].setView(cc[0]);
                            aa[0].show();
                        }
                    });
                    break;

            }


//////todo при нНАЖАТИИ  НА КНОПКУ СОХРАНИТЬ НАСТРОЙКИ ЗАПИСЫВАЕТ ОРГАНИЗАЦИЮ В БАЗУ

            switchАвтоЗаполенияВТАбелеВыходных.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //////TODO ЕСЛИ РЕЖИМ TRUE  MOBILE ВПИСЫВАЕМ КАК В БАЗУ MOBILE
                    if (isChecked) {
                        // The toggle is enabled mobile
                        new CoreBinessLogics(getApplicationContext()).МетодКоторыйЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobile("Включить", getApplicationContext()
                                , "SuccessLogin", "mode_weekend");

                        ///TODO принудительно устанвливаем редим работы синхронизации
                        MainActivity_Settings.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switchАвтоЗаполенияВТАбелеВыходных.setText("Вкл (Выходные)");
                                Toast aa = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_SHORT);
                                ImageView cc = new ImageView(getBaseContext());
                                cc.setImageResource(R.drawable.icon_dsu1_add_organisazio_success);
                                aa.setView(cc);
                                aa.show();
                            }
                        });


                        //////TODO ЕСЛИ РЕЖИМ TRUE  MOBILE ВПИСЫВАЕМ КАК В БАЗУ ТОЛЬКО WIFI
                    } else {
                        // The toggle is disabled
                        new CoreBinessLogics(getApplicationContext()).МетодКоторыйЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobile("Выключить", getApplicationContext()
                                , "SuccessLogin", "mode_weekend");

                        ///TODO принудительно устанвливаем редим работы синхронизации
                        MainActivity_Settings.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switchАвтоЗаполенияВТАбелеВыходных.setText("Выкл (Выходные)");
                                Toast aa = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_SHORT);
                                ImageView cc = new ImageView(getBaseContext());
                                cc.setImageResource(R.drawable.icon_dsu1_off_swihc_bolyny_error);
                                aa.setView(cc);
                                aa.show();
                            }
                        });
                    }
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

    /////todo метод создание BACK
    private void МетодСозданиеКодBACK() {
        imageViewСтрелкаВнутриНастроек.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Запусукаем Фргамент НАстройки  dashbord
                new LaunchActivityDiaologSettings(fragmentManager, context).launchADashboardSettings();

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            }
        });
    }

    // TODO: 15.05.2025
    protected void settheCurrentVersionoftheProgramVersion() {
        try {
            // TODO: 24.09.2024   Локальная Версия Программернр Обеспечения табель
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String version = pInfo.versionName;//Version Name
            Integer ЛокальнаяВерсияПО = pInfo.versionCode;
            textViewВерсияПрограммы.setText("Версия ПО" + ": " + ЛокальнаяВерсияПО.toString());
            // TODO: 03.10.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " ЛокальнаяВерсияПО " + ЛокальнаяВерсияПО);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }
    }
    //TODO END CLASS

}
