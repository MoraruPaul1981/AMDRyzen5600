package com.dsy.dsu.MaterialsShipmentApp;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.bl_launchFragmentSettingsandDashbord.LaunchActivityDashboard;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.Gsons.SubClass_JSON_B_P_GET_1C_shipment_of_materials;
import com.dsy.dsu.R;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceFor1C;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;




public class Fragment1_List_Shipment_of_Materials extends Fragment    {
    // TODO: 10.03.2022  согласования фрагмент
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.MyRecycleViewAdapter myRecycleViewAdapter;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.MyViewHolder myViewHolder;
    private HashMap<Integer ,String> ХэшСпискаЦФО =new HashMap<Integer ,String> ();
    private FragmentManager fragmentManagerДляотгрузкаМатериалов;
    private FragmentTransaction fragmentTransactionляЗадачи;
    private TextView textViewТекущаяотгрузкаМатериалов;
    private Integer ПубличныйIDДляФрагмента;
    private LinearLayout linearLayou;
    private Fragment fragment_ТекущийФрагментСогласованиеСписок;
    private BottomNavigationView bottomNavigationViewДляОтгрузкеМатериалов;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationКонкретноКнопкаКонтролируемыеНазад;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationКонкретноКнопкаКонтролируемыеПринудительныйОбмен;
    private JSONObject БуферПолучениеДанныхОт1сСтрочкаОдна;
    private View viewДляПервойКнопкиОтказалМатерилов = null;
    private RecyclerView recyclerView;
    private ProgressBar progressBarshipment_of_materials;
    private  Handler handlerЛимитОстатков;
    private  Boolean  РезультатИзмененияСтатусаСогласованияОтказаИлиУспешноеСогласования;
    private    StringBuffer БуферОт1СсамиДанныеJSON =new StringBuffer();
    private    StringBuffer БуферОт1ССписокЦФО =new StringBuffer();
    private JSONArray ARRAYJSONОт1СсамиДанные =new JSONArray();
    // TODO: 28.06.2022

    private ServiceFor1C service_for1C;

    private  ArrayList<HashMap<String, Object>> ЛистВнутриХэшМапДляЗаполненияСпинераМатериаловЛеммита;

    private  Spinner spinner_Shipment_of_materials;
    // TODO: 14.07.2022
    private   Bundle bundleЗаполяетсяДаннымиПослеПоворотаЭкран;
      private SubInterface_forОтргузкаМатериалов subInterface_forОтргузкаМатериалов;
    // TODO: 19.07.2022
    private Boolean  ФлагПрошлаЛиХотябыОднаПопыткаПолученияДАнных=false;
    // TODO: 30.07.2022
    private Boolean  ФлагЧтоЭтоНеПервыйСтартСпинераДляВыбораЦФО=false;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            Log.d(this.getClass().getName(), "  onViewCreated  Fragment1_One_Tasks view   " + view);
            Log.d(this.getClass().getName(), "  Fragment1_One_Tasks  viewДляПервойКнопкиHome_Задания " + viewДляПервойКнопкиОтказалМатерилов);
            textViewТекущаяотгрузкаМатериалов = (TextView) view.findViewById(R.id.activy_shipment_of_materials_fragment1_tasksnameеtextview);
            Log.d(this.getClass().getName(), "  Fragment1_List_Shipment_of_Materials  textViewТекущаяЗадача " + textViewТекущаяотгрузкаМатериалов + " view " + view);
            textViewТекущаяотгрузкаМатериалов.setText("Лимит  материалов".toUpperCase());
            fragmentManagerДляотгрузкаМатериалов = getActivity().getSupportFragmentManager();
            linearLayou = (LinearLayout) getActivity().findViewById(R.id.activity_main_fisrt_shipment_of_materials);
            bottomNavigationViewДляОтгрузкеМатериалов = (BottomNavigationView) view.findViewById(R.id.bottomnavigationActivicommit);
            bottomNavigationViewДляОтгрузкеМатериалов.setItemHorizontalTranslationEnabled(true);
            bottomNavigationКонкретноКнопкаКонтролируемыеНазад = bottomNavigationViewДляОтгрузкеМатериалов.findViewById(R.id.id_commiback);
            bottomNavigationКонкретноКнопкаКонтролируемыеНазад.setTitle("Выйти");
            bottomNavigationКонкретноКнопкаКонтролируемыеНазад.setSelected(true);
            bottomNavigationКонкретноКнопкаКонтролируемыеПринудительныйОбмен= bottomNavigationViewДляОтгрузкеМатериалов.findViewById(R.id.id_commitasync);
            bottomNavigationКонкретноКнопкаКонтролируемыеПринудительныйОбмен.setTitle("Обновить");
            progressBarshipment_of_materials = (ProgressBar) view.findViewById(R.id.prograessbarprograessbar_shipment_of_materials); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            spinner_Shipment_of_materials  = (Spinner) view.findViewById(R.id.Spinner_shipment_of_materials);
//TODO  запскаем будушем Headler.CallBack
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///

        }
    }

    // TODO: 12.03.2022

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try{
            switch (getResources().getConfiguration().orientation){
                case  Configuration.ORIENTATION_PORTRAIT:
                    // TODO: 28.02.2022
                    // TODO: 14.03.2022
                    viewДляПервойКнопкиОтказалМатерилов = inflater.inflate(R.layout.activity_main_fragment1_for_shipment_of_materials, container, false);
                    // TODO: 12.03.2022
                    Log.d(this.getClass().getName(), " onCreateView  viewДляПервойКнопкиHome_Задания  Fragment1_One_Tasks  onCreateView " +
                            "" + viewДляПервойКнопкиОтказалМатерилов);
                    break;
                case  Configuration.ORIENTATION_LANDSCAPE:
                    // TODO: 28.02.2022
                    // TODO: 14.03.2022
                    viewДляПервойКнопкиОтказалМатерилов = inflater.inflate(R.layout.activity_main_fragment1_for_shipment_of_materialsposlepovorota, container, false);
                    // TODO: 12.03.2022
                    Log.d(this.getClass().getName(), " onCreateView  viewДляПервойКнопкиHome_Задания  Fragment1_One_Tasks  onCreateView " +
                            "" + viewДляПервойКнопкиОтказалМатерилов);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return viewДляПервойКнопкиОтказалМатерилов;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity(). getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        subInterface_forОтргузкаМатериалов= (SubInterface_forОтргузкаМатериалов) context;
    }

    @Override
    public void onStop() {
        super.onStop();
        // TODO: 18.06.2022

    }

    @Override
    public void onStart() {
        super.onStart();
        try{
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 imageView  Fragment1_One_Tasks  onStart");
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов = new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов();
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.МетодПодключенияКСлужбеБиндингом();
            ПубличныйIDДляФрагмента = new GetttingPublicID().getttingPublicID(getContext());

            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.МетодИнициализациHandlerCallBack();
            БуферОт1ССписокЦФО = subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.МетодОбобщенныйДляПервогоЭтапаПолучаемСпискЦфоОт1С();
            Log.d(this.getClass().getName(), " нет данных  БуферРезультатПолучениеДанныхОт1СПервыйЭтап  " + БуферОт1ССписокЦФО);

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодСрабатываетКогдаМыПовернулиЭкран() throws JSONException, ParseException {
        // TODO: 29.07.2022
        try {
        if (bundleЗаполяетсяДаннымиПослеПоворотаЭкран!=null) {
            Log.i(getContext().getClass().getName(), "bundleЗаполяетсяДаннымиПослеПоворотаЭкран  "+bundleЗаполяетсяДаннымиПослеПоворотаЭкран);
            Integer    ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов =
                    Optional.ofNullable(bundleЗаполяетсяДаннымиПослеПоворотаЭкран
                            .getInt("ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов", 0)).map(Integer::new).orElse(0);
            Log.i(getContext().getClass().getName(), "ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов "+ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);
            // TODO: 11.07.2022 запускаем загрузку данных на экран
            if (ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов>0) {
                БуферОт1СсамиДанныеJSON =   (StringBuffer)
                        Optional.ofNullable(   bundleЗаполяетсяДаннымиПослеПоворотаЭкран.getSerializable("БуферРезультатПолучениеДанныхОт1СВторойЭтап"))
                                .orElse(new StringBuffer());
                Log.d(this.getClass().getName(), "bundleЗаполяетсяДаннымиПослеПоворотаЭкран " + bundleЗаполяетсяДаннымиПослеПоворотаЭкран.toString());
                Log.d(this.getClass().getName(), " нет данных  ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов  " + ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);
                if (!БуферОт1СсамиДанныеJSON.toString().trim().matches("(.*)не соответствует базе данных!(.*)")
                        && БуферОт1СсамиДанныеJSON.length()>20) {
                    ARRAYJSONОт1СсамиДанные =
                            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.МетодПарсингаПолучениеДанныхОт1с(БуферОт1СсамиДанныеJSON);//ПубличныйIDДляФрагмента
                    // TODO: 18.07.2022 передаем данные в интреейс
                    ((SubInterface_forОтргузкаМатериалов) getActivity()).МеханизмПолучениеданныхИзФрагмента(БуферОт1СсамиДанныеJSON);
                    Log.i(getContext().getClass().getName(), "БуферРезультатПолучениеДанныхОт1СВторойЭтап "+ БуферОт1СсамиДанныеJSON);
                }}

            // TODO: 21.07.2022  первый запуск без поворота
        }

    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    // TODO: 12.03.2022  метод с бизнес логикой
    @Override
    public void onResume() {
        super.onResume();
        //TODO
        try {
            //todo метод  ИНИЦИАЛИЗАЦИИ RECYCLEVIEW ДЛЯ АКТИВТИ ЗАДАЧИ
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.МетодИнициализацииRecycleViewДляЗадач();
            // TODO: 05.03.2022  ДЛЯ ИНИЗАЛИЗАЦИИ НИЖНИХ КНОПОК
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.МетодСозданиеНавигаторКнопок();
            // TODO: 04.03.2022 создаем слушатель    третий класс создаем класс слушаителй  ДАННЫЙ КОД ЗАПУСКАЕТЬСЯ ПОСЛЕ СОЗДАЕНИЯ И УСТАНОВКИ АДАПТЕРА RECYCLEVIEW
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.МетодКпопкаВозвращениеНазадИзСогласованиии();
            // TODO: 22.03.2022  принудительный обмен с кнопки
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.МетодКпопкаПринидительноОбмена();
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(ХэшСпискаЦФО);
            // TODO: 04.03.2022 создаем слушатель    третий класс создаем ЗАПУСКАЕМ  второай слушатель только количество данных СЛУШАТЕЛЬ КУРСОРРА
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.МетодСлушательObserverДляRecycleView();

            // TODO: 29.07.2022 reselt screen
           bottomNavigationViewДляОтгрузкеМатериалов.requestLayout();
            bottomNavigationViewДляОтгрузкеМатериалов.forceLayout();
            recyclerView.requestLayout();
            recyclerView.forceLayout();
            linearLayou.requestLayout();
            linearLayou.forceLayout();
            //todo перерерисовка экран
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    // TODO: 10.03.2022 БИЗНЕС-КОД ПЕРЕНЕСЕН ИЗ АКТИВТИ


    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            /////////////

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 28.02.2022 бизнес -логика    для активти


    private  class SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов {
        private ServiceConnection connectionДляПолучениеДанных1сОтрзукаМатерилатов = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                try{
                    ServiceFor1C.LocalBinderДляПолучениеДанных1С binder = ( ServiceFor1C.LocalBinderДляПолучениеДанных1С) service;
                    service_for1C = binder.getService();
                    Log.i(getContext().getClass().getName(), "    onServiceConnected  service_for1C" + service_for1C);
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
                try{
                    Log.i(getContext().getClass().getName(), "    onServiceDisconnected  service_for1C" + service_for1C);
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

        // TODO: 14.03.2022


        // TODO: 04.03.2022  класс в котором находяться слушатели

        void МетодСлушательObserverДляRecycleView() {
            // TODO: 04.03.2022
            try {
                // TODO: 02.03.2022
                RecyclerView.AdapterDataObserver     adapterDataObserverObserverСлушатель = new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        // TODO: 02.03.2022
                        Log.d(this.getClass().getName(), "onChanged ");
                        // TODO: 02.03.2022

                        onStart();
                       /// onResume();
                    }

                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount) {
                        super.onItemRangeChanged(positionStart, itemCount);
                        // TODO: 02.03.2022
                        Log.d(this.getClass().getName(), "onItemRangeChanged ");
                    }

                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                        super.onItemRangeChanged(positionStart, itemCount, payload);
                        // TODO: 02.03.2022
                        // вибратор.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        Log.d(this.getClass().getName(), "onItemRangeChanged ");
                    }
                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        super.onItemRangeInserted(positionStart, itemCount);
                        // TODO: 02.03.2022
                        //   вибратор.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        Log.d(this.getClass().getName(), "onItemRangeInserted ");
                    }

                    @Override
                    public void onItemRangeRemoved(int positionStart, int itemCount) {
                        super.onItemRangeRemoved(positionStart, itemCount);
                        // TODO: 02.03.2022
                        // вибратор.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        Log.d(this.getClass().getName(), "onItemRangeRemoved ");
                    }

                    @Override
                    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                        // TODO: 02.03.2022

                    }
                };
                // TODO: 04.03.2022 запускаем слушатель;
                // TODO: 23.03.2022
                myRecycleViewAdapter.registerAdapterDataObserver(adapterDataObserverObserverСлушатель);

            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///
            }
        }

        // TODO: 02.03.2022  принудительный обмен с 1с

        private  void МетодКпопкаВозвращениеНазадИзСогласованиии()
                throws ExecutionException, InterruptedException {
            // TODO: 02.03.2022
            try {
                // TODO: 02.03.2022
                Log.d(this.getClass().getName(), "  выходим из задания МетодКпопкаВозвращениеНазадИзСогласованиии" );
                // TODO: 09.03.2022
                bottomNavigationКонкретноКнопкаКонтролируемыеНазад.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LaunchActivityDashboard launchActivityDashboard=new LaunchActivityDashboard( fragmentManager,getContext());
                        // TODO: 27.03.2024 в зависомсти кто вызвает
                        launchActivityDashboard.     launchADashboardFragment();


                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private  void МетодКпопкаПринидительноОбмена()
                throws ExecutionException, InterruptedException {
            Log.d(this.getClass().getName(), "  принудительный обмен  МетодКпопкаВозвращениеНазадИзСогласованиии" );
            // TODO: 09.03.2022
            bottomNavigationКонкретноКнопкаКонтролируемыеПринудительныйОбмен.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Integer IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов= 0;
                        Log.d(this.getClass().getName(), "  принудительный обмен  МетодКпопкаВозвращениеНазадИзСогласованиии" );
                        progressBarshipment_of_materials.setVisibility(View.VISIBLE);
                        Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                        TextView  materialTextView= (TextView)    spinner_Shipment_of_materials.getSelectedView();
                        if (materialTextView!=null) {
                            Object materialTextViewЕслиTag=materialTextView.getTag();
                            String ВЫтаскиваемTAgВлимитеМатериалов = null;
                            if (materialTextViewЕслиTag!=null) {
                                ВЫтаскиваемTAgВлимитеМатериалов = Optional.ofNullable(materialTextViewЕслиTag.toString()).orElse("");
                            }
                            IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов =
                                    Optional.ofNullable(ВЫтаскиваемTAgВлимитеМатериалов).map(Integer::new).orElse(0);
                        }
                        Log.d(this.getClass().getName(), " IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов " +IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);

                        // TODO: 30.07.2022  ХОЛОСТОЙ ХОД
                        МетодСинхронизацияПриНажатииНаКнопкуПринудительнойЛимитМатериалов(IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);


                    } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }


            });


            /////////////

        }

        // TODO: 30.07.2022 Метод  Синхронизации ПриНАжатии на Кнпоку
      private  void МетодСинхронизацияПриНажатииНаКнопкуПринудительнойЛимитМатериалов(Integer IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов) {
            try{
                Log.d(this.getClass().getName()
                        , "      private  void МетодСинхронизацияПриНажатииНаКнопкуПринудительнойЛимитМатериалов(Integer IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов) {  IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов " + IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);
                if(   IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов >0
                        && !БуферОт1СсамиДанныеJSON.toString().trim().matches("(.*)Нет записей по этому id(.*)")){
                    Log.d(this.getClass().getName(), " IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов " + IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);
                    // TODO: 11.07.2022 запускаем загрузку данных на экран
                    БуферОт1СсамиДанныеJSON =
                            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.
                                    МетодОбодщенныйДляВторогоЭтапаПолучениеУжеСамогоJsonОтС(IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);
                    Log.d(this.getClass().getName(), " private  void БуферРезультатПолучениеДанныхОт1СВторойЭтап()  "
                            + БуферОт1СсамиДанныеJSON);
                }else {
                    Log.d(this.getClass().getName(), " private  void IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов()  "
                            + IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);

                    onStart();
                }
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 30.07.202 Метод Холостой Минхронизациии
        private  void МетодХолостаяСинхронизацияЛимитМатериалов() {
            try{
                Log.d(this.getClass().getName(), "  private  void МетодХолостаяСинхронизацияЛимитМатериалов() { ");

                    Log.d(this.getClass().getName(), " private  void БуферРезультатПолучениеДанныхОт1СВторойЭтап()  "
                            + БуферОт1СсамиДанныеJSON);
                ХэшСпискаЦФО =new HashMap<>();

                ARRAYJSONОт1СсамиДанные=new JSONArray();

                    onStart();
                   // onResume();

            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }




        // TODO: 13.07.2022 Второй Синхронизация Долгое НАажтаие Синхронизиорва с начала
        private  void МетодКпопкаПринидительноОбменаПриДолгомНажатии()
                throws ExecutionException, InterruptedException {
            Log.d(this.getClass().getName(), "  принудительный обмен  МетодКпопкаВозвращениеНазадИзСогласованиии" );
            // TODO: 09.03.2022
            bottomNavigationКонкретноКнопкаКонтролируемыеПринудительныйОбмен.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    try {
                        Log.d(this.getClass().getName(), "  принудительный обмен  МетодКпопкаВозвращениеНазадИзСогласованиии" );

                        progressBarshipment_of_materials.setVisibility(View.VISIBLE);
                        Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));

                      /*  ARRAYJSONОт1СсамиДанные =new JSONArray();
                        БуферОт1ССписокЦФО =new StringBuffer();
                        БуферОт1ССписокЦФО =            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.        МетодОбобщенныйДляПервогоЭтапаПолучаемСпискЦфоОт1С();
*/

                        // TODO: 29.07.2022
                        recyclerView.getAdapter().notifyDataSetChanged();
                        // TODO: 04.03.2022
                        recyclerView.getAdapter().notifyItemChanged(0);

                        Log.d(this.getClass().getName(), " private  void МетодКпопкаПринидительноОбменаПриДолгомНажатии() " + БуферОт1ССписокЦФО);

                    } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }

                    return true;
                }
            });


                    /////////////

        }
        // TODO: 04.03.2022 прозвомжность инициализации RecycleView
        void МетодИнициализацииRecycleViewДляЗадач() {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1       ARRAYJSONПолучениеДанныхОТ1СВторойШаг  "
                        + ARRAYJSONОт1СсамиДанные);
                recyclerView = (RecyclerView) viewДляПервойКнопкиОтказалМатерилов.findViewById(R.id.recycleview_shipment_of_materials);
                recyclerView.setVisibility(View.VISIBLE);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                DividerItemDecoration    dividerItemDecoration = new DividerItemDecoration(
                        recyclerView.getContext(),
                        DividerItemDecoration.HORIZONTAL);
                recyclerView.removeItemDecoration(dividerItemDecoration);
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setLayoutManager(linearLayoutManager);//TODO new LinearLayoutManager(getContext())
                // TODO: 28.02.2022 создаем наш первый RecyclerView recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                myRecycleViewAdapter = new MyRecycleViewAdapter(ARRAYJSONОт1СсамиДанные);
                recyclerView.setAdapter(myRecycleViewAdapter);
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 recyclerView   " + recyclerView+
                        "       ARRAYJSONПолучениеДанныхОТ1СВторойШаг "+ ARRAYJSONОт1СсамиДанные);
                /////////////
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

        // TODO: 14.03.2022

        // TODO: 05.03.2022 метод создание кнопок снизу навигатор
        void МетодСозданиеНавигаторКнопок() {
            try {
                // TODO: 05.03.2022
                bottomNavigationViewДляОтгрузкеМатериалов.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        // TODO: 09.03.2022
                        try {

                            // TODO: 09.03.2022
                            fragmentTransactionляЗадачи = fragmentManagerДляотгрузкаМатериалов.beginTransaction();
                            // TODO: 11.03.2022
                            // TODO: 11.03.2022
                            Log.d(this.getClass().getName(), "  bottomNavigationViewДляTasks " + bottomNavigationViewДляОтгрузкеМатериалов + " fragment_ТекущийФрагмент " + fragment_ТекущийФрагментСогласованиеСписок);
                            // TODO: 09.03.2022 вешаем слушатель на конткеноую кнопку
                            Log.d(this.getClass().getName(), "  item.getItemId() " + item.getItemId());
                            // TODO: 09.03.2022
                            switch (item.getItemId()) {
                                // TODO: 14.03.2022
                                case R.id.id_taskHome:
                                    // TODO: 22.12.2021  запускам втнутерий класс по созданию бизнес логики для даннго активти
                                    Log.d(this.getClass().getName(), " R.id.id_taskHome отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  fragmentTransactionляЗадачи  "
                                            + fragmentTransactionляЗадачи + " R.id.id_taskHome  item.getItemId() " + item.getItemId());
                                    ///
                                    // TODO: 10.03.2022
                                    item.setChecked(true);
                                    // TODO: 09.03.2022
                                       /* fragment_ТекущийФрагмент = new Fragment2_Create_CommitPay();
                                        // TODO: 11.03.2022
                                        fragmentTransactionляЗадачи.replace(R.id.activity_main_fisrt_for_tasks, fragment_ТекущийФрагмент).commit();//.layout.activity_for_fragemtb_history_tasks
                                        // TODO: 10.03.2022
                                        fragmentTransactionляЗадачи.show(fragment_ТекущийФрагмент);
                                        // TODO: 10.03.2022
                                        Log.d(this.getClass().getName(), " fragmentTransactionляЗадачи " + fragmentTransactionляЗадачи);
                                        // TODO: 10.03.2022

                                        // TODO: 15.03.2022
                                        // TODO: 14.03.2022
                                        //bottomNavigationКонкретноКнопкаСоздатьСейчас.setVisibility(View.GONE);
                                        // TODO: 09.03.2022
                                        bottomNavigationViewДляTasks.requestLayout();*/
                                    // TODO: 14.03.2022
                                    linearLayou.requestLayout();
                                    Log.d(this.getClass().getName(), " bottomNavigationViewДляTasks.getChildCount() " + bottomNavigationViewДляОтгрузкеМатериалов.getChildCount());
                                    break;

                                // TODO: 09.03.2022////

                                default:
                                    // TODO: 09.03.2022
                                    // TODO: 09.03.2022
                                    Log.d(this.getClass().getName(), "  никакой не выбрали  item.getItemId() ");
                                    return false;
                            }
                            // TODO: 09.03.2022
                        } catch (Exception e) {
                            e.printStackTrace();
                            ///метод запись ошибок в таблицу
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            ///
                        }
                        return true;
                    }
                });
                /////////////
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(@NonNull HashMap<Integer,String> БуферПолучениеДанныхОт1сПриПервомШаге)
                throws ExecutionException, InterruptedException {
            // TODO: 02.03.2022
            try {
                // TODO: 02.03.2022
                Log.d(this.getClass().getName(), "  БуферПолучениеДанныхОт1с.length() " + БуферПолучениеДанныхОт1сПриПервомШаге.size());
                // TODO: 09.03.2022
                if (БуферПолучениеДанныхОт1сПриПервомШаге.size()==0) {
                    // TODO: 06.03.2022
                    bottomNavigationViewДляОтгрузкеМатериалов.getOrCreateBadge(R.id.id_commitasync).setVisible(true);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    // TODO: 06.03.2022
                    bottomNavigationViewДляОтгрузкеМатериалов.getOrCreateBadge(R.id.id_commitasync).setNumber(БуферПолучениеДанныхОт1сПриПервомШаге.size());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    // TODO: 09.03.2022
                    bottomNavigationViewДляОтгрузкеМатериалов.getOrCreateBadge(R.id.id_commitasync).setBackgroundColor(Color.RED);
                    // TODO: 06.03.2022

                    // TODO: 05.03.2022
                }else{

                    bottomNavigationViewДляОтгрузкеМатериалов.getOrCreateBadge(R.id.id_commitasync).setVisible(true);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    // TODO: 06.03.2022
                    bottomNavigationViewДляОтгрузкеМатериалов.getOrCreateBadge(R.id.id_commitasync).setNumber(БуферПолучениеДанныхОт1сПриПервомШаге.size());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    // TODO: 09.03.2022
                    bottomNavigationViewДляОтгрузкеМатериалов.getOrCreateBadge(R.id.id_commiback).setBackgroundColor(Color.parseColor("#0A524B"));
                    // TODO: 06.03.2022
                }



                // TODO: 13.03.2022
                bottomNavigationViewДляОтгрузкеМатериалов.requestLayout();
                // TODO: 13.03.2022
                bottomNavigationViewДляОтгрузкеМатериалов.forceLayout();
                // TODO: 14.03.2022
                linearLayou.requestLayout();

                linearLayou.forceLayout();
                /////////////
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 28.02.2022 конец  MyViewHolderДляЧата


        // TODO: 28.02.2022 ViewHolder

        StringBuffer МетодПолучениеСамихДанныхДляЛимитаМатириаловВтторойЭтап(@NonNull Integer ЦФОДляОтпарвкиЕгоНаСервер1С) throws JSONException {
            //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С
            StringBuffer БуферРезультатПолучениеДанныхОт1СВторойЭтап   = new StringBuffer();
            try{
                Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента);
                Intent intentДляПолучениеДАнныхот1СДЛяОтгрузкиМатериалов=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("адрессерверадляотгрускиматериалов","http://192.168.254.137/dsutest/hs/jsonto1c/Inertmaterials");//TODO "http://192.168.254.137/dsutest/hs/jsonto1c/Inertmaterials"
                bundle.putInt("публичныйidдляотгрускиматериалов",ПубличныйIDДляФрагмента);
                bundle.putInt("цфовозвратматериалы",ЦФОДляОтпарвкиЕгоНаСервер1С);
                bundle.putString("ТаблицыДляОбработки1С","dsu1cfo");
                intentДляПолучениеДАнныхот1СДЛяОтгрузкиМатериалов.putExtras(bundle);
                БуферРезультатПолучениеДанныхОт1СВторойЭтап =
                        service_for1C.МетодЗапускаПолучениеДанных1СВторойЭтапЛимитМарериалов(getContext(),intentДляПолучениеДАнныхот1СДЛяОтгрузкиМатериалов);
                //todo generator json*/
                Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанныхДляGson "+
                        " БуферРезультатПолучениеДанныхОт1СВторойЭтап " + БуферРезультатПолучениеДанныхОт1СВторойЭтап+ " ЦФОДляОтпарвкиЕгоНаСервер1С " +ЦФОДляОтпарвкиЕгоНаСервер1С);


            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }
            return БуферРезультатПолучениеДанныхОт1СВторойЭтап;
        }


        ////TODO получение даннеы от 1С ВТОРОЙ ЭТАП

        StringBuffer МетодПолучениеСамихДанныхДляЛимитаМатириаловЭтапПервый() throws JSONException {
            //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С
            StringBuffer stringBufferОтветПриПервомЭтапе   = new StringBuffer();
            try{
                Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента);
                Intent intentДляПолучениеДАнныхот1СДЛяОтгрузкиМатериалов=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("адрессерверадляотгрускиматериалов","http://192.168.254.137/dsutest/hs/jsonto1c/cfop");//TODO "http://192.168.254.137/dsutest/hs/jsonto1c/Inertmaterials"
                bundle.putInt("публичныйidдляотгрускиматериалов",ПубличныйIDДляФрагмента);
                bundle.putString("ТаблицыДляОбработки1С","dsu1cfo");
                intentДляПолучениеДАнныхот1СДЛяОтгрузкиМатериалов.putExtras(bundle);
                // TODO: 07.07.2022
                stringBufferОтветПриПервомЭтапе =
                        service_for1C.МетодЗапускаПолучениеДанных1СПервыйЭтапДЛяЛимитаМатериалов(getContext(),intentДляПолучениеДАнныхот1СДЛяОтгрузкиМатериалов);
                //todo generator json*/
                Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанныхДляGson "+
                        " БуферРезультатПолучениеДанныхОт1СВторойЭтап " + БуферОт1ССписокЦФО);

                if ( stringBufferОтветПриПервомЭтапе.length()==0) {
                    Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанныхДляGson "+
                            " stringBufferОтветПриПервомЭтапе " + stringBufferОтветПриПервомЭтапе);
                }
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }
            return stringBufferОтветПриПервомЭтапе;
        }


        ////TODO получение даннеы от 1С ПЕРВЫЙ ЭТАП

        JSONArray МетодПарсингаПолучениеДанныхОт1с(@NonNull StringBuffer БуферРезультатаПингаИПолучениеДанных) throws JSONException {
            //TODO
            JSONArray ФинальныйРезультатаПингаИПолучениеДанных=new JSONArray();
            // TODO: 21.05.2022
            //TODO
            LinkedBlockingDeque<SubClass_JSON_B_P_GET_1C_shipment_of_materials> subClass_json_b_pGET =new LinkedBlockingDeque<SubClass_JSON_B_P_GET_1C_shipment_of_materials>();
            //TODO

            try{

                Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанных " + БуферРезультатаПингаИПолучениеДанных);

                //TODO
                if (БуферРезультатаПингаИПолучениеДанных.length()>0 &&  !  БуферРезультатаПингаИПолучениеДанных.toString().trim().matches("(.*)Нет записей(.*)")) {
                    Gson gson = new GsonBuilder()
                            .setPrettyPrinting()
                            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                            .create();

                    //TODO

                    БуферРезультатаПингаИПолучениеДанных.append(gson.toJson(БуферРезультатаПингаИПолучениеДанных)) ;

                    Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанных  " + БуферРезультатаПингаИПолучениеДанных.toString());


                    //todo
                    ФинальныйРезультатаПингаИПолучениеДанных=new JSONArray(БуферРезультатаПингаИПолучениеДанных.toString());
                    //TODO


                    Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанных "
                            + БуферРезультатаПингаИПолучениеДанных+
                            " ФинальныйРезультатаПингаИПолучениеДанных " +ФинальныйРезультатаПингаИПолучениеДанных);
                }


                //todo generator json*/

                Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанныхДляGson "+
                        " ФинальныйРезультатаПингаИПолучениеДанных " +ФинальныйРезультатаПингаИПолучениеДанных);



            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }


            return  ФинальныйРезультатаПингаИПолучениеДанных;
        }








        ////TODO пинг 1с

        HashMap<Integer,String> МетодПарсингаДляПервогоЗапросаСписокЦИО(@NonNull StringBuffer БуферРезультатаПингаИПолучениеДанных) throws JSONException {
            //TODO
            HashMap<Integer,String> ХэшДляПервогоЗапросаТОлькоСписокЦфо =new   HashMap<Integer,String>();
            //TODO

            try{
                if (БуферРезультатаПингаИПолучениеДанных!=null) {
                    JSONArray      arrayСписокЦфоОт1с=new JSONArray(БуферРезультатаПингаИПолучениеДанных.toString());
                    for (int i = 0; i < arrayСписокЦфоОт1с.length(); i++) {
                        Object ПолученийЦФО;
                        Object ПолученийID;
                        Object ObjectСтрокаJSONСписокЦфо=arrayСписокЦфоОт1с.get(i);
                        JSONObject jsonObjectСтрокаJSONСписокЦфо=new JSONObject(ObjectСтрокаJSONСписокЦфо.toString());
                        Iterator iteratorСтрокаJSONСписокЦфо =jsonObjectСтрокаJSONСписокЦфо.keys();
                        iteratorСтрокаJSONСписокЦфо.forEachRemaining(new Consumer() {
                            @Override
                            public void accept(Object o) {
                                try{
                                    Object ПолученийЦФО=    jsonObjectСтрокаJSONСписокЦфо.get("name");
                                    Object ПолученийID=    jsonObjectСтрокаJSONСписокЦфо.get("cfoid");

                                    if (ПолученийID!=null && ПолученийЦФО!=null ) {
                                        ХэшДляПервогоЗапросаТОлькоСписокЦфо.putIfAbsent(Integer.parseInt(ПолученийID.toString()),ПолученийЦФО.toString());
                                        // TODO: 11.07.2022
                                        ПолученийID=null;
                                        ПолученийЦФО=null;
                                    }
                                    Log.d(this.getClass().getName(), "ХэшДляПервогоЗапросаТОлькоСписокЦфо  " + ХэшДляПервогоЗапросаТОлькоСписокЦфо);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    ///метод запись ошибок в таблицу
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        });
                    }
                    Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанных  " + БуферРезультатаПингаИПолучениеДанных.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }
            return  ХэшДляПервогоЗапросаТОлькоСписокЦфо;
        }

        ////TODO пинг 1с  для второго запроса

        //TODO метод делает callback с ответом на экран
        private  void  МетодИнициализациHandlerCallBack(){
                Handler.Callback callback = new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        try{
                            Log.d(this.getClass().getName(), " " +
                                    " Handler.CallBack onCreateView  viewДляПервойКнопкиHome_Задания  Fragment1_One_Tasks  onCreateView  " +
                                    msg + " msg.getWhen() " + msg.what);

                            msg.getTarget().post(()->{
                                progressBarshipment_of_materials.setVisibility(View.VISIBLE);
                            });
                            Bundle bundle=      msg.getData();
                            String ОперацияДанныВЧате=bundle.getString("ОперациЯПрошлСогласование","");

                            // TODO: 29.07.2022
                            recyclerView.getAdapter().notifyDataSetChanged();
                            // TODO: 04.03.2022
                            recyclerView.getAdapter().notifyItemChanged(0);
                            msg.getTarget().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBarshipment_of_materials.setVisibility(View.INVISIBLE);
                                }
                            },1000);
                            msg.getTarget().removeMessages(1);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getActivity()).recordnewerror(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return true;
                    }
                };
                handlerЛимитОстатков = new Handler(callback);

        }

        // TODO: 28.06.2022 слущатель call back  для мсоглдасования
        private StringBuffer МетодОбобщенныйДляПервогоЭтапаПолучаемСпискЦфоОт1С() throws JSONException, ParseException {
            //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С
            try{
               handlerЛимитОстатков.post(()->{
                    progressBarshipment_of_materials.setVisibility(View.VISIBLE);
                   TextView  materialTextView= (TextView)    spinner_Shipment_of_materials.getSelectedView();
                   if (materialTextView!=null) {
                       materialTextView.setTextColor(Color.GRAY);
                   }
               });
                // TODO: 12.07.2022 ПЕРВЫЙ ШАГ ПОЛУЧЕНИЕ ОТ 1С СПИСОК ЦФО
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Throwable {
                        // TODO: 12.07.2022  код для ПЕРВОЙ СТУПЕНИ ПОЛУЧЕНИЕ ЦФО
                      БуферОт1ССписокЦФО =
                                subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.МетодПолучениеСамихДанныхДляЛимитаМатириаловЭтапПервый();//ПубличныйIDДляФрагмента

                        Log.d(this.getClass().getName(), " ХэшBlockingQueueJSONПолучениеДанныхОТ1СПЕрвыйШаг  " + ХэшСпискаЦФО +
                                " БуферРезультатПолучениеДанныхОт1СПервыйЭтап " + БуферОт1ССписокЦФО);
                        // TODO: 12.07.2022 old code test kode
                     if (БуферОт1ССписокЦФО.length()==0) {
                            БуферОт1ССписокЦФО.append("[{\"cfoid\":221,\"name\":\"Дома в д. Домнино\"}]");
                        }

                    }
                }).subscribeOn(Schedulers.newThread())
                        .onErrorComplete(new Predicate<Throwable>() {
                    @Override
                    public boolean test(Throwable throwable) throws Throwable {
                        Log.e(this.getClass().getName(), "Ошибка " + throwable.getMessage().toString()
                                + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(throwable.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        return false;
                    }
                })  .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete(new Action() {
                    @Override
                    public void run() throws Throwable {

                            try{
                                if (БуферОт1ССписокЦФО.length()>0) {
                                    ХэшСпискаЦФО =new HashMap<Integer,String>();
                                    if ( ! БуферОт1ССписокЦФО.toString().trim().matches("(.*)не найден(.*)")) {
                                        ХэшСпискаЦФО =
                                                subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.МетодПарсингаДляПервогоЗапросаСписокЦИО(БуферОт1ССписокЦФО);//ПубличныйIDДляФрагмента
                                        // TODO: 06.07.2022 метод генерация данных только для спинера
                                    }
                                    ФлагПрошлаЛиХотябыОднаПопыткаПолученияДАнных=true;
                                    progressBarshipment_of_materials.setVisibility(View.GONE);
                                    subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов
                                            .МетодГенерацииДанныхДляСпинераЛимитматериалов(ХэшСпискаЦФО);
                                    Log.i(getContext().getClass().getName(), "БуферРезультатПолучениеДанныхОт1СВторойЭтап "+ БуферОт1СсамиДанныеJSON);
                                    // TODO: 09.03.2022 код для Инициализации по Spinner  ЛИМИТ материалоов
                                    subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.
                                            МетодБиндингаСпинераЛимитыОстатки( ЛистВнутриХэшМапДляЗаполненияСпинераМатериаловЛеммита);

                                }

                                handlerЛимитОстатков.post(()->{
                                    TextView  materialTextView= (TextView)    spinner_Shipment_of_materials.getSelectedView();
                                    if (materialTextView!=null) {
                                        materialTextView.setTextColor(Color.BLACK);
                                    }
                                });


                        // TODO: 14.07.2022  ПОЛУЧАЕМ ДАННЫЕ ЕСЛИ ПРИИЗОШОЛ ПОВОРОТ ЭКРАНА
                                bundleЗаполяетсяДаннымиПослеПоворотаЭкран=   getArguments();
                                // TODO: 21.07.2022  получение данных с поворотом экрана или без
                                МетодСрабатываетКогдаМыПовернулиЭкран();



                                Log.i(getContext().getClass().getName(), "ХэшBlockingQueueЗаполняемЦФО "+ ХэшСпискаЦФО);
                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                    }
                }).doFinally(new Action() {
                            @Override
                            public void run() throws Throwable {
                                try{
                                Log.d(this.getClass().getName(), "DisposableCompletableObserver  " );

                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            }
                        }).doAfterTerminate(new Action() {
                            @Override
                            public void run() throws Throwable {
                                try {
                                    Log.d(this.getClass().getName(), "DisposableCompletableObserver  " );

                                    // TODO: 29.07.2022
                                    onResume();

                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            }
                        }).subscribe();

            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return БуферОт1ССписокЦФО;
        }

        private    void   МетодПодключенияКСлужбеБиндингом(){
            try {
                // TODO: 16.06.2022  тест код
                service_for1C =new ServiceFor1C();

                Intent intentЗапускДжоп = new Intent(getContext(), ServiceFor1C.class);
                //  ServiceForChatten.enqueueWork(getContext(), intentЗапускДжоп);
                getContext(). bindService(intentЗапускДжоп, connectionДляПолучениеДанных1сОтрзукаМатерилатов, Context.BIND_AUTO_CREATE);
                // int num = service_smenaStatusMessageChat.getRandomNumber();

                Log.i(getContext().getClass().getName(), "    protected void onHandleWork(@NonNull Intent intent) { " + new Date()+ " intentЗапускДжоп " +intentЗапускДжоп+"\n"+
                        " Thread.currentThread().getName()  " +Thread.currentThread().getName()+ " service_for1C " + service_for1C);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

        // TODO: 29.03.2022  метод регмстарцмии локального брод кастера доля смен задачи

        private     ArrayList<HashMap<String, Object>> МетодГенерацииДанныхДляСпинераЛимитматериалов(
                @NonNull  HashMap<Integer,String> ХэшBlockingQueueJSONПолучениеДанныхОТ1СПЕрвыйШаг) {
            // TODO: 06.07.2022
            ЛистВнутриХэшМапДляЗаполненияСпинераМатериаловЛеммита =  new ArrayList<HashMap<String, Object>> ();
            final HashMap<String, Object>[] map = new HashMap[]{new HashMap<>()};
            // TODO: 06.07.2022 все остальныеданные
            try {
                Log.d(this.getClass().getName(), "  ХэшBlockingQueueJSONПолучениеДанныхОТ1СПЕрвыйШаг  " + ХэшBlockingQueueJSONПолучениеДанныхОТ1СПЕрвыйШаг);
                if (ХэшBlockingQueueJSONПолучениеДанныхОТ1СПЕрвыйШаг.size()>0) {
                    map[0].putIfAbsent("cfo", "Выберете цфо");
                } else {
                    map[0].putIfAbsent("cfo", "Данных нет");
                }
                // TODO: 06.07.2022  заполяем сфо
                ЛистВнутриХэшМапДляЗаполненияСпинераМатериаловЛеммита.add(map[0]);

                ХэшBlockingQueueJSONПолучениеДанныхОТ1СПЕрвыйШаг.forEach(new BiConsumer<Integer, String>() {
                    @Override
                    public void accept(Integer integer, String s) {
                        map[0] =   new  HashMap<String, Object>();;

                        map[0].put("cfo", s.toString().trim());

                        // TODO: 06.07.2022  заполяем сфо
                        ЛистВнутриХэшМапДляЗаполненияСпинераМатериаловЛеммита.add(map[0]);
                    }
                });


                if(ЛистВнутриХэшМапДляЗаполненияСпинераМатериаловЛеммита.size()==0){
                    map[0].put("cfo","Данных нет !!!");
                    ЛистВнутриХэшМапДляЗаполненияСпинераМатериаловЛеммита.add(map[0]);
                }
                // TODO: 02.03.2022
                Log.i(this.getClass().getName(), " ЛистВнутриХэшМапДляЗаполненияСпинераМатериаловЛеммита " + ЛистВнутриХэшМапДляЗаполненияСпинераМатериаловЛеммита+
                        " ХэшBlockingQueueJSONПолучениеДанныхОТ1СПЕрвыйШаг " +ХэшBlockingQueueJSONПолучениеДанныхОТ1СПЕрвыйШаг);
            } catch ( Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  ЛистВнутриХэшМапДляЗаполненияСпинераМатериаловЛеммита;
        }

        private void МетодБиндингаСпинераЛимитыОстатки(@NonNull ArrayList<HashMap<String, Object>> arrayListРезультатДляЗаполенияСпинера) throws ParseException {
            try {
                // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3
                Log.d(this.getClass().getName(), " arrayListРезультатДляЗаполенияСпинера " + arrayListРезультатДляЗаполенияСпинера);
                if (arrayListРезультатДляЗаполенияСпинера!=null) {
///TODO ГЛАВНЫЙ АДАПТЕР чата
                    SimpleAdapter АдаптерДляФИОПриСозданииНовойЗадачи = new SimpleAdapter(getContext(),
                            arrayListРезультатДляЗаполенияСпинера,
                            android.R.layout.simple_spinner_dropdown_item,
                            new String[]{"cfo"},
                            new int[]{android.R.id.text1});

                    // TODO: 17.03.2022
                    SimpleAdapter.ViewBinder БиндингДляSpinnerФИОСозданиеНовойЗадачи = new SimpleAdapter.ViewBinder() {
                        @Override
                        public boolean setViewValue(View view, Object data, String textRepresentation) {

                            Log.d(this.getClass().getName(), " view  " + view +
                                    "  data" +data  +"textRepresentation "+textRepresentation
                                    + " ЛистВнутриХэшМапДляЗаполненияСпинераМатериаловЛеммита " +ЛистВнутриХэшМапДляЗаполненияСпинераМатериаловЛеммита.size());


                            // TODO: 21.03.2022
                            String ПолучениеСтрочкаДляСпинераСФО= data.toString();
                            // TODO: 29.07.2022
                            Log.d(this.getClass().getName(), " ПолучениеСтрочкаДляСпинераСФО  " + ПолучениеСтрочкаДляСпинераСФО);

                            // TODO: 29.07.2022 выбираем кто делать
                            switch (ПолучениеСтрочкаДляСпинераСФО.trim()){
                                case "Выберете цфо":
                                    ((TextView) view).setText("  "+ "Выберете цфо");
                                    ((TextView) view).setTextColor(Color.GRAY);
                                    ((TextView) view).setTextSize(15l);
                                    new   SubClass_StyleSpinner_СтильСпинера().МетодВизуализацииВидаСпинераНулеваяПозиция((TextView) view,getContext());
                                    break;
                                case "Данных нет":
                                    ((TextView) view).setText("");
                                    ((TextView) view).setTextColor(Color.GRAY);
                                    ((TextView) view).setTextSize(15l);
                                    break;
                                default:
                                    ((TextView) view).setText("  " +ПолучениеСтрочкаДляСпинераСФО.trim());
                                    ((TextView) view).setTextColor(Color.BLACK);
                                    ((TextView) view).setTextSize(15l);
                                    Integer ПолучаениТекущийIdЛимтаМатериалов=   ХэшСпискаЦФО.entrySet().stream()
                                            .filter(e->e.getValue().equalsIgnoreCase(ПолучениеСтрочкаДляСпинераСФО.trim())).
                                            filter(e->!e.getValue().trim().matches("(.*)Выбер(.*)")).
                                            filter(e->!e.getValue().trim().matches("(.*)Данных(.*)")).
                                            map(Map.Entry::getKey).findFirst().orElse(0).intValue();
                                    // TODO: 11.07.2022
                                    Log.d(this.getClass().getName(), " view  " + view +
                                            "  data" +data  +"ПолучениеСтрочкаДляСпинераСФО "+ПолучениеСтрочкаДляСпинераСФО
                                            + " ПолучаениТекущийIdЛимтаМатериалов " +ПолучаениТекущийIdЛимтаМатериалов);

                                    ((TextView) view).setTag(String.valueOf(ПолучаениТекущийIdЛимтаМатериалов));

                                    // TODO: 08.07.2022
                                    new   SubClass_StyleSpinner_СтильСпинера().МетодВизуализацииВидаСпинера((TextView) view,getContext());
                                    break;
                            }

                            // TODO: 06.07.2022
                            return true;
                        }
                        // TODO: 18.03.2022  перенессыц код
                    };
                    // TODO: 17.03.2022
                    АдаптерДляФИОПриСозданииНовойЗадачи.setViewBinder(БиндингДляSpinnerФИОСозданиеНовойЗадачи);
                    // TODO: 28.02.2022
                    spinner_Shipment_of_materials.setAdapter(АдаптерДляФИОПриСозданииНовойЗадачи);
                    // TODO: 07.06.2022 ДОПОЛНИТЕЛЬНАЯ НАСТОЙКА СПИНЕРА  ЛИМИТ МАТЕРИЛОВ
                }
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }
        }

// TODO: 08.07.2022 МЕТОД ЗАПОЛЕНИЯ ДАННЫМИ СПИНЕРА

        private StringBuffer МетодОбодщенныйДляВторогоЭтапаПолучениеУжеСамогоJsonОтС(@NonNull Integer IdВЫбарнныйДляЦФОДляЛимитаМатериреалов) throws JSONException {
            try{
                handlerЛимитОстатков.post(()->{
                    TextView  materialTextView= (TextView)    spinner_Shipment_of_materials.getSelectedView();
                    if (materialTextView!=null) {
                        materialTextView.setTextColor(Color.GRAY);
                    }
                });
                // TODO: 11.07.2022  получаем файл уже через rxjava
             Completable.fromAction(new Action() {
                            @Override
                            public void run() throws Throwable {
                                try{
                                    // TODO: 18.07.2022  само получение данных второй шаг
                                   БуферОт1СсамиДанныеJSON =
                                            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.
                                                    МетодПолучениеСамихДанныхДляЛимитаМатириаловВтторойЭтап(IdВЫбарнныйДляЦФОДляЛимитаМатериреалов);//ПубличныйIDДляФрагмента
                                    Log.d(this.getClass().getName(), " ID ЦФО пришли   БуферРезультатПолучениеДанныхОт1СПервыйЭтап  " + БуферОт1ССписокЦФО +
                                            "\n"+ "  БуферРезультатПолучениеДанныхОт1СВторойЭтап " + БуферОт1СсамиДанныеJSON +
                                            "   ARRAYJSONПолучениеДанныхОТ1СВторойШаг " + ARRAYJSONОт1СсамиДанные);

                                 if ( БуферОт1СсамиДанныеJSON.length()==0) {
                                        БуферОт1СсамиДанныеJSON = new StringBuffer("[\n" +
                                                "{\n" +
                                                "\"cfo\": \"Гарантийный ремонт улиц г.Иваново                                                                   \",\n" +
                                                "\"cfoid\": 213,\n" +
                                                "\"type_material\": \"АБСмеси\",\n" +
                                                "\"amount\": 0,\n" +
                                                "\"nomenclatura\": \"А/б смесь тип А 5 ВЛ\",\n" +
                                                "\"PurchasedPermits\": 0,\n" +
                                                "\"BroughtPermits\": 0,\n" +
                                                "\"Weighted\": 38,\n" +
                                                "\"Remainder\": -38\n" +
                                                "},\n" +
                                                "{\n" +
                                                "\"cfo\": \"Гарантийный ремонт улиц г.Иваново                                                                   \",\n" +
                                                "\"cfoid\": 113,\n" +
                                                "\"type_material\": \"АБСмеси\",\n" +
                                                "\"amount\": 0,\n" +
                                                "\"nomenclatura\": \"А/б смесь ЩМА 11\",\n" +
                                                "\"PurchasedPermits\": 0,\n" +
                                                "\"BroughtPermits\": 0,\n" +
                                                "\"Weighted\": 77.1,\n" +
                                                "\"Remainder\": -77.1\n" +
                                                "},\n" +
                                                "{\n" +
                                                "\"cfo\": \"Гарантийный ремонт улиц г.Иваново                                                                   \",\n" +
                                                "\"cfoid\": 415,\n" +
                                                "\"type_material\": \"АБСмеси\",\n" +
                                                "\"amount\": 0,\n" +
                                                "\"nomenclatura\": \"А/б смесь ЩМА 8\",\n" +
                                                "\"PurchasedPermits\": 0,\n" +
                                                "\"BroughtPermits\": 0,\n" +
                                                "\"Weighted\": 175.8,\n" +
                                                "\"Remainder\": -175.8\n" +
                                                "},\n" +
                                                "{\n" +
                                                "\"cfo\": \"Гарантийный ремонт улиц г.Иваново                                                                   \",\n" +
                                                "\"cfoid\": 213,\n" +
                                                "\"type_material\": \"Эмульсия\",\n" +
                                                "\"amount\": 0,\n" +
                                                "\"nomenclatura\": \"битумная эмульсия\",\n" +
                                                "\"PurchasedPermits\": 0,\n" +
                                                "\"BroughtPermits\": 0,\n" +
                                                "\"Weighted\": 16.35,\n" +
                                                "\"Remainder\": -16.35\n" +
                                                "},\n" +
                                                "{\n" +
                                                "\"cfo\": \"Гарантийный ремонт улиц г.Иваново                                                                   \",\n" +
                                                "\"cfoid\": 213,\n" +
                                                "\"type_material\": \"Щебень\",\n" +
                                                "\"amount\": 0,\n" +
                                                "\"nomenclatura\": \"щебень фр 4/8 ГБР\",\n" +
                                                "\"PurchasedPermits\": 0,\n" +
                                                "\"BroughtPermits\": 0,\n" +
                                                "\"Weighted\": 27.97,\n" +
                                                "\"Remainder\": -27.97\n" +
                                                "}\n" +
                                                "]");
                                    }
                                 
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    ///метод запись ошибок в таблицу
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        }) .observeOn(AndroidSchedulers.mainThread())
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                Log.e(this.getClass().getName(), "Ошибка " + throwable.getMessage().toString() + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(throwable.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                return false;
                            }
                        })
                        .subscribeOn(Schedulers.newThread())
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Throwable {
                                try{
                                    if (!БуферОт1СсамиДанныеJSON.toString().trim().matches("(.*)не соответствует базе данных!(.*)")
                                            && БуферОт1СсамиДанныеJSON.length()>20) {
                                        // TODO: 29.07.2022  заполение финальными данными
                                        ARRAYJSONОт1СсамиДанные =
                                                subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.
                                                        МетодПарсингаПолучениеДанныхОт1с(БуферОт1СсамиДанныеJSON);//ПубличныйIDДляФрагмента
                                        // TODO: 18.07.2022 передаем данные в интреейс
                                        ((SubInterface_forОтргузкаМатериалов) getActivity())
                                                .МеханизмПолучениеданныхИзФрагмента(БуферОт1СсамиДанныеJSON);

                                        Log.i(getContext().getClass().getName(),
                                                "БуферРезультатПолучениеДанныхОт1СВторойЭтап "+ БуферОт1СсамиДанныеJSON);
                                    }

                                    handlerЛимитОстатков.post(()->{
                                        progressBarshipment_of_materials.setVisibility(View.INVISIBLE);
                                        TextView  materialTextView= (TextView)    spinner_Shipment_of_materials.getSelectedView();
                                        if (materialTextView!=null) {
                                            materialTextView.setTextColor(Color.BLACK);
                                        }

                                    });

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    ///метод запись ошибок в таблицу
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        }).doAfterTerminate(new Action() {
                            @Override
                            public void run() throws Throwable {
                                try{
                                onResume();
                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            }
                        }).subscribe();
                // TODO: 11.07.2022
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return БуферОт1СсамиДанныеJSON;
        }



        // TODO: 05.07.2022  Метод Инициализация Спинера

        // TODO: 15.03.2022  перенесееный код
        // TODO: 28.02.2022 начало  MyViewHolderДляЧата
        protected class MyViewHolder extends RecyclerView.ViewHolder {// TODO: 28.02.2022 начало  MyViewHolderДляЧата

            // TODO: 28.02.2022 ТОЛЬКО LINE
            MaterialTextView textView1ТипМатериаловЛиния, textView2ЛимитЛиния, textView3НоменклатураЛиния, textView4ЗакупленоРазрешенияЛиния, textView5ПривезеноРазрешенияЛиния, textView6ВесоваяЛиния;
            // TODO: 28.02.2022 ТОЛЬКО KEY
            MaterialTextView textView1ТипМатериаловКлюч, textView2ЛимитКлюч, textView3НоменклатураКлюч, textView4ЗакупленоРазрешенияКлюч, textView5ПривезеноРазрешенияКлюч, textView6ВесоваяКлюч, textView7ОстатокКлюч;
            // TODO: 28.02.2022 ТОЛЬКО VALUE
            MaterialTextView textView1ТипМатериалов, textView2Лимит, textView3Номенклатура, textView4ЗакупленоРазрешения, textView5ПривезеноРазрешения, textView6Весовая, textView7Остаток;
            // TODO: 13.03.2022
            MaterialCardView materialCardView_Shipment_of_materials;
            // TODO: 05.07.2022
            LinearLayout LinearLayout_Shipment_of_materials;
            TableLayout tableLayoutДляЛимитаМатериала;

            // TODO: 02.03.2022
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                // TODO: 02.03.2022
                МетодИнициализацииКомпонетовЗаданияCardView(itemView);
                // TODO: 01.03.2022
                Log.d(this.getClass().getName(), "  private class MyViewHolderДляЧата extends RecyclerView.ViewHolder  itemView   " + itemView);
            }

            // TODO: 14.03.2022
            private void МетодИнициализацииКомпонетовЗаданияCardView(@NonNull View itemView) {
                // TODO: 28.02.2022
                try {
                    // TODO: 01.03.2022 Инициализации компонтов
                    Log.d(this.getClass().getName(),
                            " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);

                    // TODO: 02.03.2022  ТОЛЬКО САМИ ЛИНИЯ ВНУТРИ CARDVIEW
                    textView1ТипМатериаловЛиния= (MaterialTextView) itemView.findViewById(R.id.bertlinekey01);
                    textView2ЛимитЛиния= (MaterialTextView) itemView.findViewById(R.id.bertlinekey02);
                    textView3НоменклатураЛиния= (MaterialTextView) itemView.findViewById(R.id.bertlinekey03);
                    textView4ЗакупленоРазрешенияЛиния= (MaterialTextView) itemView.findViewById(R.id.bertlinekey04);
                    textView5ПривезеноРазрешенияЛиния= (MaterialTextView) itemView.findViewById(R.id.bertlinekey05);
                    textView6ВесоваяЛиния= (MaterialTextView) itemView.findViewById(R.id.bertlinekey06);



                    // TODO: 02.03.2022  ТОЛЬКО САМИ КЛЮЧИ ВНУТРИ CARDVIEW
                    textView1ТипМатериаловКлюч= (MaterialTextView) itemView.findViewById(R.id.key1);
                    textView2ЛимитКлюч= (MaterialTextView) itemView.findViewById(R.id.key2);
                    textView3НоменклатураКлюч= (MaterialTextView) itemView.findViewById(R.id.key3);
                    textView4ЗакупленоРазрешенияКлюч= (MaterialTextView) itemView.findViewById(R.id.key4);
                    textView5ПривезеноРазрешенияКлюч= (MaterialTextView) itemView.findViewById(R.id.key5);
                    textView6ВесоваяКлюч= (MaterialTextView) itemView.findViewById(R.id.key6);
                    textView7ОстатокКлюч= (MaterialTextView) itemView.findViewById(R.id.key7);


                    // TODO: 02.03.2022  ТОЛЬКО САМИ ДАННЫЕ ВНУТРИ CARDVIEW
                    textView1ТипМатериалов = (MaterialTextView) itemView.findViewById(R.id.value1);
                    textView2Лимит = (MaterialTextView) itemView.findViewById(R.id.value2);
                    textView3Номенклатура = (MaterialTextView) itemView.findViewById(R.id.value3);
                    textView4ЗакупленоРазрешения = (MaterialTextView) itemView.findViewById(R.id.value4);
                    textView5ПривезеноРазрешения = (MaterialTextView) itemView.findViewById(R.id.value5);
                    textView6Весовая = (MaterialTextView) itemView.findViewById(R.id.value6);
                    textView7Остаток= (MaterialTextView) itemView.findViewById(R.id.value7);
                    materialCardView_Shipment_of_materials = (MaterialCardView) itemView.findViewById(R.id.cardvie_shipment_of_materials);
                    LinearLayout_Shipment_of_materials    = (LinearLayout) itemView.findViewById(R.id.simple_shipment_of_materials);
                    tableLayoutДляЛимитаМатериала = (TableLayout) itemView.findViewById(R.id.gridLayout_shipment_of_materials);

                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 materialCardView   " + materialCardView_Shipment_of_materials+
                            " gridLayoutДляЛимитаМатериала " + tableLayoutДляЛимитаМатериала);
                    ///////
                } catch (Exception e) {
                    //  Block of code to handle errors
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                /////
            }
        } // TODO: 28.02.2022 конец  MyViewHolderДляЧата


// TODO: 08.07.2022 МЕТОД ВТОРОЙ ОБОБЩЕННЫЙ ДЛЯ УЖЕ ПОЛУЧЕНИЕ САМОГО JSON  ОТ 1С

        class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
            // TODO: 04.03.2022
            JSONArray МассивДанных;
            // TODO: 15.03.2022
            public MyRecycleViewAdapter(@NotNull JSONArray ARRAYJSONПолучениеДанныхОТ1СВторойШагRecycleViewAdapter) {
                // TODO: 04.03.2022
                this.МассивДанных = ARRAYJSONПолучениеДанныхОТ1СВторойШагRecycleViewAdapter;
                // TODO: 29.03.2022

            }


            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
                // TODO: 30.03.2022
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position);
                // TODO: 02.03.2022 тут РАЗДАЕМ ДАННЫЕ RECYCLERBIEW
                try {
                    recyclerView.getRecycledViewPool().clear();
                    // TODO: 30.03.2022
                    if (МассивДанных.length()>0) {
                        //TODO
                        Object   СодержимоеИзПришедшихТаблицДляКлиента = ARRAYJSONОт1СсамиДанные.getString(position); // Here's
                        БуферПолучениеДанныхОт1сСтрочкаОдна = new JSONObject(СодержимоеИзПришедшихТаблицДляКлиента.toString());
                        Log.d(this.getClass().getName(), "БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView "
                                + БуферПолучениеДанныхОт1сСтрочкаОдна +
                                " ARRAYJSONПолучениеДанныхОТ1СВторойШаг " + ARRAYJSONОт1СсамиДанные);
                        Log.d(this.getClass().getName(), " ARRAYJSONПолучениеДанныхОТ1СВторойШаг   " + ARRAYJSONОт1СсамиДанные +
                                " БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView " + БуферПолучениеДанныхОт1сСтрочкаОдна  + " position " +position);
                    }
                    // TODO: 30.03.2022
                    recyclerView.requestLayout();
                    // TODO: 30.03.2022
                    recyclerView.forceLayout();
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }
                super.onBindViewHolder(holder, position, payloads);
            }

            @Override
            public void setHasStableIds(boolean hasStableIds) {
                super.setHasStableIds(hasStableIds);
            }

            @Override
            public void onViewRecycled(@NonNull MyViewHolder holder) {
                super.onViewRecycled(holder);
            }

            @Override
            public boolean onFailedToRecycleView(@NonNull MyViewHolder holder) {
                return super.onFailedToRecycleView(holder);
            }

            @Override
            public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
                super.onViewAttachedToWindow(holder);
            }

            @Override
            public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
                super.onViewDetachedFromWindow(holder);
            }

            @Override
            public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

                recyclerView.removeAllViews();

                recyclerView.getRecycledViewPool().clear();
                super.onAttachedToRecyclerView(recyclerView);
            }

            @Override
            public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
                super.onDetachedFromRecyclerView(recyclerView);
            }

            @Override
            public int getItemViewType(int position) {
                Log.i(this.getClass().getName(), "      holder.textView1  position " + position);
                try {
                    // TODO: 30.03.2022
                    Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
                    // TODO: 30.03.2022

                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }
                return super.getItemViewType(position);
            }


            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // TODO: 10.03.2022
                View viewГлавныйВидДляRecyclleViewДляОтгрузкиМатерилов = null;
                try {
                    TextView materialTextViewСпинерВытаскиваемЗначениеТекущее = (TextView) spinner_Shipment_of_materials.getSelectedView();
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewГлавныйВидДляRecyclleViewДляОтгрузкиМатерилов+
                            "  ХэшBlockingQueueJSONПолучениеДанныхОТ1СПЕрвыйШаг " + ХэшСпискаЦФО+ " МассивДанных " +МассивДанных);
                    // todo данные загрузились  на экран
                    switch (МассивДанных.length()){
                        case 0:
                            // TODO: СПИСОК загрузилься на экран
                            if(  ХэшСпискаЦФО.size()>0) {
                                viewГлавныйВидДляRecyclleViewДляОтгрузкиМатерилов =
                                        LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_shipment_of_materials_arrajsononestenenable, parent, false);//todo old simple_for_takst_cardview1
                            }
                            // TODO: КОГДА СПИСКА ЦФО ЕЩЕ НЕТ ИЛИ ЕЩЕ НЕ ЗАГРУЖЕНО
                            if(  ХэшСпискаЦФО.size()==0){
                                if (ФлагПрошлаЛиХотябыОднаПопыткаПолученияДАнных==true) {
                                    viewГлавныйВидДляRecyclleViewДляОтгрузкиМатерилов =
                                            LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_shipment_of_materials_empty, parent, false);//todo old simple_for_takst_cardview1
                                    // TODO: 20.07.2022  ШАГ ПЕРВЫЙ ПОКАЗЫВАЕТ ПОЛЬЗАТЕЛЮ ЭКРАН ЗАГРУЗКИ --- ИДЕТ ЗАГРУЗКА ДАННЫХ
                                }else{
                                    viewГлавныйВидДляRecyclleViewДляОтгрузкиМатерилов =
                                            LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_shipment_of_materials_arrajsononestenenable_isprosessing, parent, false);//todo old simple_for_takst_cardview1
                                }}
                            // TODO: 05.03.2022
                            Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewГлавныйВидДляRecyclleViewДляОтгрузкиМатерилов+
                                    "  ХэшBlockingQueueJSONПолучениеДанныхОТ1СПЕрвыйШаг " + ХэшСпискаЦФО+ " ФлагПрошлаЛиХотябыОднаПопыткаПолученияДАнных " +ФлагПрошлаЛиХотябыОднаПопыткаПолученияДАнных);
                            break;

                        default:
                            viewГлавныйВидДляRecyclleViewДляОтгрузкиМатерилов =
                                    LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_shipment_of_materials_cardview1_isdebug, parent, false);//todo old simple_for_takst_cardview1
                            Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewГлавныйВидДляRecyclleViewДляОтгрузкиМатерилов +
                                    " ARRAYJSONПолучениеДанныхОТ1СВторойШагRecycleViewAdapter "
                                    + МассивДанных +" myViewHolder.getLayoutPosition() " +myViewHolder.getLayoutPosition());
                            // TODO: 31.07.2022  когда данные есть но для выбраного ЦФО данных
                            if (materialTextViewСпинерВытаскиваемЗначениеТекущее != null) {
                                if (!materialTextViewСпинерВытаскиваемЗначениеТекущее.getText().toString().trim().equalsIgnoreCase("Выберете цфо")) {
                                    // TODO: 31.07.2022 уогда по выбраному цфо нет данных
                                    if (БуферОт1СсамиДанныеJSON.length() == 0) {
                                        viewГлавныйВидДляRecyclleViewДляОтгрузкиМатерилов =
                                                LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_shipment_of_materials_empty_po_bibranomycfo, parent, false);//todo old simple_for_takst_cardview1*/
                                    }
                                }
                            }
                            // TODO: 05.03.2022
                            Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewГлавныйВидДляRecyclleViewДляОтгрузкиМатерилов+
                                    "  ХэшBlockingQueueJSONПолучениеДанныхОТ1СПЕрвыйШаг " + ХэшСпискаЦФО+ " МассивДанных " +МассивДанных);
                            break;
                    }

                    // TODO: 22.03.2022
                    myViewHolder = new MyViewHolder(viewГлавныйВидДляRecyclleViewДляОтгрузкиМатерилов);
                    // TODO: 01.03.2022
                    Log.i(this.getClass().getName(), "   myViewHolder" + myViewHolder);
// TODO: 01.03.2022
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return myViewHolder;

            }

            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                // TODO: 28.02.2022 привазяваем данные из колекции пряме на наш recycreview
                try {
                    // TODO: 01.03.2022
                    Log.i(this.getClass().getName(), "   создание согласования"
                            + myViewHolder
                            + " БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView " + БуферПолучениеДанныхОт1сСтрочкаОдна);
                    // TODO: 14.03.2022  метод первый номер документ #1
                    МетодБиндингаТипМатериала(holder, БуферПолучениеДанныхОт1сСтрочкаОдна);
                    // TODO:14.03.2022  метод первый номер документ #2
                    МетодБиндингаПривезеноРазрешения(holder, БуферПолучениеДанныхОт1сСтрочкаОдна);
                    // TODO: 14.03.2022  метод первый номер документ #4
                    МетодБиндингаЗакупленоРазрешения(holder, БуферПолучениеДанныхОт1сСтрочкаОдна);
                    // TODO: 14.03.2022  метод первый номер документ #5
                    МетодБиндингаСозданиеЛимита(holder, БуферПолучениеДанныхОт1сСтрочкаОдна);
                    // TODO: 14.03.2022  метод создание номелклутры #3
                    МетодБиндингаСозданиеНоменклатура(holder, БуферПолучениеДанныхОт1сСтрочкаОдна);
                    // TODO: 09.03.2022 код для клика по Spinner  ЛИМИТ материалоов
                    МетодКликСпиннераВыборЦфо_ЛимитМатериалов(holder);
                    // TODO: 14.03.2022  метод первый номер документ #7
                    МетодБиндингаСозданиеНомелклатура(holder, БуферПолучениеДанныхОт1сСтрочкаОдна);
                    // TODO: 14.03.2022  метод первый номер документ #3
                    МетодБиндингаСозданиеОстаток(holder, БуферПолучениеДанныхОт1сСтрочкаОдна);
                    // TODO: 07.07.2022 код скрывем внешний вид ключ внутри Cardview
                    МетодБиндингаСозданиеВыключаетКлючиCardView(holder, БуферПолучениеДанныхОт1сСтрочкаОдна);

                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }
            }











            ///todo первый метод #1

            private void МетодБиндингаТипМатериала(@NonNull MyViewHolder holder, @NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {
                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null && holder.textView1ТипМатериалов!=null ) {
                        //TODO
                        String ТипМатериала = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getString("type_material").trim();
                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), "  Ndoc  ПерваяСтрочкаЗнТипМатериалаачения " + ТипМатериала);
                        // TODO: 28.02.2022
                        holder.textView1ТипМатериалов.setText(ТипМатериала);

                        // TODO: 20.07.2022    тескт код
                        Log.i(this.getClass().getName(), "  Ndoc  ПерваяСтрочкаЗнТипМатериалаачения " + myViewHolder.getAdapterPosition());



                    }
                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }

            ///todo первый метод #2

            private void МетодБиндингаСозданиеЛимита(@NonNull MyViewHolder holder, @NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {
                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null &&  holder.  textView2Лимит!=null ) {
                        Integer Лимит = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getInt("amount");
                        // TODO: 02.03.2022

                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), "  amount Лимит " + Лимит);
                        // TODO: 28.02.2022
                        holder.  textView2Лимит.setText(String.valueOf(Лимит));
                    }
                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }

            ///todo первый метод #3

            private void МетодБиндингаСозданиеНоменклатура(@NonNull MyViewHolder holder, @NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {
                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null &&  holder.    textView3Номенклатура!=null ) {
                        String Номенклатура = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getString("nomenclatura");
                        // TODO: 02.03.2022

                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), "  nomenclatura Номенклатура " + Номенклатура);
                        // TODO: 28.02.2022
                        holder.    textView3Номенклатура.setText(Номенклатура);
                    }
                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }


            ///todo первый метод #4

            private void МетодБиндингаЗакупленоРазрешения(@NonNull MyViewHolder holder, @NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {
                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null &&  holder.textView4ЗакупленоРазрешения!=null ) {
                        //TODO
                        Integer ЗакупленоеРазрешения = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getInt("PurchasedPermits");
                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), " ЗакупленоеРазрешения" + ЗакупленоеРазрешения);
                        // TODO: 28.02.2022
                        holder.textView4ЗакупленоРазрешения.setText(String.valueOf(ЗакупленоеРазрешения));
                    }
                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }




            ///todo первый метод #4

            private void МетодБиндингаПривезеноРазрешения(@NonNull MyViewHolder holder, @NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {
                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (holder. textView5ПривезеноРазрешения!=null && БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null ) {
                        //todo
                        Integer ПривезеноРазрешения = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getInt("BroughtPermits");
                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), " ПривезеноРазрешения " + ПривезеноРазрешения);
                        // TODO: 28.02.2022
                        holder. textView5ПривезеноРазрешения.setText(String.valueOf(ПривезеноРазрешения));
                    }
                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }


            ///todo первый метод #7

            private void МетодБиндингаСозданиеНомелклатура(@NonNull MyViewHolder holder,@NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {
                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null && holder.    textView6Весовая !=null ) {
                        Double ПерваяСтрочкаЗначения = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getDouble("Weighted");
                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), "  nomenclature ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                        // TODO: 28.02.2022
                        holder.    textView6Весовая .setText(String.valueOf(ПерваяСтрочкаЗначения));
                    }
                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }
            // TODO: 14.03.2022  Успешное Согласования


//TODO вторая кнопка

            // TODO: 14.03.2022  отказа Согласования

            private void МетодКликСпиннераВыборЦфо_ЛимитМатериалов(@NonNull MyViewHolder holder)
                    throws ExecutionException, InterruptedException {
                // TODO: 02.03.2022
                try {
                    // TODO: 02.03.2022
                    Log.d(this.getClass().getName(), "  КнопкаСогласованиеОтказ    отказ 1  " );
                    spinner_Shipment_of_materials.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            try{
                                Log.w(this.getClass().getName(), " private void МетодКликСпиннераВыборЦфо_ЛимитМатериалов(@NonNull MyViewHolderДляЧата holder) parent   "
                                        + parent+
                                        " position " +position+  "id   "+id);
                                Integer IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов = 0;
                                // TODO: 11.07.2022
                                String ЦФОВыбраногоЦФОДляЗагрузкиЛимитаМатерилов;
                                switch (position){
                                    case  0:
                                        // TODO: 30.07.2022 код после поворота экрана
                                        if (bundleЗаполяетсяДаннымиПослеПоворотаЭкран!=null) {
                                            Integer    ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов =
                                                    Optional.ofNullable(bundleЗаполяетсяДаннымиПослеПоворотаЭкран.
                                                            getInt("ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов", 0))
                                                            .map(Integer::new).orElse(0);
                                            if (ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов>0) {
                                                ((TextView) parent.getChildAt(0)).setTag(ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);
                                            }
                                            String    СамоНазваниЦФОПослеRestart =
                                                    Optional.ofNullable(bundleЗаполяетсяДаннымиПослеПоворотаЭкран.
                                                            getString("ПослеRestartСамоНазваниЦФОПослеRestart", "")).map(String::new).orElse("");
                                            if (СамоНазваниЦФОПослеRestart.length()>0) {
                                                ((TextView) parent.getChildAt(0)).setText("  "+СамоНазваниЦФОПослеRestart);
                                            }
                                            new   SubClass_StyleSpinner_СтильСпинера().МетодВизуализацииВидаСпинера((TextView) view,getContext());
                                            // TODO: 29.07.2022 когда в спиноре позиция 0
                                        }else {
                                            // TODO: 30.07.2022
                                                  if(ФлагЧтоЭтоНеПервыйСтартСпинераДляВыбораЦФО==true){
                                                      new   SubClass_StyleSpinner_СтильСпинера().МетодВизуализацииВидаСпинераНулеваяПозиция((TextView) view,getContext());
                                                      // TODO: 29.07.2022
                                                      String ЧтоНаходитьсяНАПозицииНоль=   ((TextView) parent.getChildAt(0)).getText().toString();
                                                      Log.d(this.getClass().getName(), " ЧтоНаходитьсяНАПозицииНоль  " + ЧтоНаходитьсяНАПозицииНоль);
                                                      if (ЧтоНаходитьсяНАПозицииНоль.trim().equalsIgnoreCase("Выберете цфо")){
                                                          Log.d(this.getClass().getName(), " ЧтоНаходитьсяНАПозицииНоль " + ЧтоНаходитьсяНАПозицииНоль);
                                                          // TODO: 30.07.2022  ХОЛОСТОЙ ХОД
                                                          // МетодХолостаяСинхронизацияЛимитМатериалов(IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);

                                                          ФлагЧтоЭтоНеПервыйСтартСпинераДляВыбораЦФО=false;
                                                          // TODO: 30.07.2022  ХОЛОСТОЙ ХОД
                                                          МетодХолостаяСинхронизацияЛимитМатериалов();
                                                          Log.d(this.getClass().getName(), " ЧтоНаходитьсяНАПозицииНоль " + ЧтоНаходитьсяНАПозицииНоль);
                                                      }

                                                  }else{

                                                      ФлагЧтоЭтоНеПервыйСтартСпинераДляВыбораЦФО=true;
                                                  }
                                        }
// TODO: 30.07.2022

                                        Log.d(this.getClass().getName(), " нет данных  БуферРезультатПолучениеДанныхОт1СПервыйЭтап  " + БуферОт1ССписокЦФО+
                                                 " ФлагЧтоЭтоНеПервыйСтартСпинераДляВыбораЦФО "+ФлагЧтоЭтоНеПервыйСтартСпинераДляВыбораЦФО);
                                        break;



// TODO: 30.07.2022 загружаем данные на сонвоани выбраного цфо
                                    default:
                                        МетодЗагрузкиДанныхПослеВыборавСпинореЦФО(parent, (TextView) view, position);
                                        Log.d(this.getClass().getName(), " ЦФОВыбраногоЦФОДляЗагрузкиЛимитаМатерилов загрузка данных при выборе цфо ");
                                        break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }







                        private void МетодЗагрузкиДанныхПослеВыборавСпинореЦФО(AdapterView<?> parent, TextView view, int position) {
                            String ЦФОВыбраногоЦФОДляЗагрузкиЛимитаМатерилов;
                            Integer IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов;
                            try {
                            TextView materialTextView = (TextView) spinner_Shipment_of_materials.getSelectedView();
                            if (materialTextView!=null) {
                                ((TextView) parent.getChildAt(0)).setText(materialTextView.getText().toString());
                                ((TextView) parent.getChildAt(0)).setTag(materialTextView.getTag());
                            }
                            // TODO: 29.07.2022
                            new   SubClass_StyleSpinner_СтильСпинера().МетодВизуализацииВидаСпинера(view,getContext());

                            IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов=Integer.parseInt(((TextView) parent.getChildAt(0)).getTag().toString());
                            ЦФОВыбраногоЦФОДляЗагрузкиЛимитаМатерилов=((TextView) parent.getChildAt(0)).getText().toString();
                            Log.d(this.getClass().getName(), " ЦФОВыбраногоЦФОДляЗагрузкиЛимитаМатерилов  "
                                    + ЦФОВыбраногоЦФОДляЗагрузкиЛимитаМатерилов+ " IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов "
                                    +IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);
                            // TODO: 11.07.2022 запускаем загрузку данных на экран
                            if (IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов>0) {
                                subClassBuccessLogin_главныйКлассБизнесЛогикиФрагментОтгрузкаМатериалов.
                                        МетодОбодщенныйДляВторогоЭтапаПолучениеУжеСамогоJsonОтС(IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);
                                Log.d(this.getClass().getName(), " нет данных  БуферРезультатПолучениеДанныхОт1СПервыйЭтап  "
                                        + БуферОт1ССписокЦФО + " position "+ position);
                            }else{
                                Log.d(this.getClass().getName(), " нет данных  БуферРезультатПолучениеДанныхОт1СПервыйЭтап  "
                                        + БуферОт1ССписокЦФО + " position "+ position);
                            }

                    } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                        }


                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            Log.w(this.getClass().getName(), "АдаптерДляФИОПриСозданииНовойЗадачи parent  " + parent);
                        }
                    });
                    /////////////
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


            private void МетодБиндингаСозданиеОстаток(@NonNull MyViewHolder holder, @NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {
                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null &&   holder. textView7Остаток!=null ) {
                        //TODO
                        Double Остаток = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getDouble("Remainder");
                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), " Остаток " + Остаток);
                        // TODO: 28.02.2022
                        holder. textView7Остаток.setText(String.valueOf(Остаток));
                    }
                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }

            @Override
            public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
                super.registerAdapterDataObserver(observer);
                Log.w(this.getClass().getName(), "     public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {" );

            }

            ///todo первый метод #5
            private void МетодБиндингаСозданиеВыключаетКлючиCardView(@NonNull MyViewHolder holder, @NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {
                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null  ) {
                        //TODO
                        Log.i(this.getClass().getName(), " Скрываем Внешний вид ключей в CardView  holder.getAdapterPosition() "  +holder.getAdapterPosition()+"\n"+
                                  " holder.getLayoutPosition() " +holder.getLayoutPosition()  + " gridLayoutДляЛимитаМатериала  " +holder.tableLayoutДляЛимитаМатериала);
                    /*    LinearLayout.LayoutParams layoutParams=null;
                        if (holder. materialCardView_Shipment_of_materials!=null) {
                            // TODO: 13.07.2022
                         layoutParams = (LinearLayout.LayoutParams) holder.materialCardView_Shipment_of_materials.getLayoutParams();
                        }*/

           /*                     if (holder.getAdapterPosition()>0){
                                    // TODO: 13.07.2022 hide key
                                    holder.textView1ТипМатериаловКлюч.setVisibility(View.GONE);
                                    holder. textView2ЛимитКлюч.setVisibility(View.GONE);
                                    holder.textView3НоменклатураКлюч.setVisibility(View.GONE);
                                    holder.    textView4ЗакупленоРазрешенияКлюч.setVisibility(View.GONE);
                                    holder. textView5ПривезеноРазрешенияКлюч.setVisibility(View.GONE);
                                    holder. textView6ВесоваяКлюч.setVisibility(View.GONE);
                                    holder.  textView7ОстатокКлюч.setVisibility(View.GONE);
                                    // TODO: 13.07.2022 hide line 
                                    holder. textView1ТипМатериаловЛиния.setVisibility(View.GONE);
                                    holder. textView2ЛимитЛиния.setVisibility(View.GONE);
                                    holder. textView3НоменклатураЛиния.setVisibility(View.GONE);
                                    holder. textView4ЗакупленоРазрешенияЛиния.setVisibility(View.GONE);
                                    holder.textView5ПривезеноРазрешенияЛиния.setVisibility(View.GONE);
                                    holder. textView6ВесоваяЛиния.setVisibility(View.GONE);
                                    // TODO: 13.07.2022 hide matiealcardview
                               *//*    ViewGroup.LayoutParams layoutParams =(ViewGroup.LayoutParams)  holder. materialCardView_Shipment_of_materials.getLayoutParams();
                                    layoutParams.height = 220;*//*
                                    layoutParams.topMargin=20;
                                    layoutParams.height = 150;


                                }else{

                                    Log.i(this.getClass().getName(), " Скрываем Внешний вид ключей в CardView  holder.getAdapterPosition() "  +holder.getAdapterPosition()+"\n"+
                                            " holder.getLayoutPosition() " +holder.getLayoutPosition() );
                                }*/
                   /*     if (holder. materialCardView_Shipment_of_materials!=null) {
                            holder. materialCardView_Shipment_of_materials.setLayoutParams(layoutParams);
                        }*/



                        // TODO: 21.07.2022
                        if (holder.tableLayoutДляЛимитаМатериала !=null ){
                                    Log.i(this.getClass().getName(), " Скрываем Внешний вид ключей в CardView  holder.getAdapterPosition() "  +holder.getAdapterPosition()+"\n"+
                                            " holder.getLayoutPosition() " +holder.getLayoutPosition() );
                                    // TODO: 25.07.2022  нулевое  удалие данных перед вставкой
             /*                       // TODO: 25.07.2022 удаление
                                    TableRow УдалениеСтрокИзTableluyot = (TableRow) holder.tableLayoutДляЛимитаМатериала.getChildAt(0);

                                    holder.tableLayoutДляЛимитаМатериала.removeView(УдалениеСтрокИзTableluyot);

                                    УдалениеСтрокИзTableluyot = (TableRow) holder.tableLayoutДляЛимитаМатериала.getChildAt(2);

                                    holder.tableLayoutДляЛимитаМатериала.removeView(УдалениеСтрокИзTableluyot);

*/

                            // TODO: СПИСОК ЦФО КОГДА ДАННЫХ НЕТ ПО ВЫБРВАНОМУ ЦФО
                            holder.tableLayoutДляЛимитаМатериала.setStretchAllColumns(true);

                                    TableRow.LayoutParams НАстройкаЛиния = new TableRow.LayoutParams(  android.widget.TableRow.LayoutParams.WRAP_CONTENT,
                                            android.widget.TableRow.LayoutParams.WRAP_CONTENT);
                            TableRow.LayoutParams НАстройкаДанные = new TableRow.LayoutParams(  android.widget.TableRow.LayoutParams.WRAP_CONTENT,
                                    android.widget.TableRow.LayoutParams.WRAP_CONTENT);


                                    // TODO: 25.07.2022  первое действие добавялем шабку
                                    // TODO: 25.07.2022  первое действие добавялем шабку
                                    // TODO: 25.07.2022  первое действие добавялем шабку
                                    TableRow       tableRow = new TableRow(getContext());
                                    View Шабка =
                                            (TableRow)   getActivity().getLayoutInflater().inflate(R.layout.simple_for_shipment_of_materials_for_tablelout_only_up_header,
                                                    null,false);
                                    // TODO: 25.07.2022
                                    MaterialTextView materialTextView=Шабка.findViewById(R.id.key1);
                                    Шабка.setLayoutParams(НАстройкаДанные);
                                    Log.i(this.getClass().getName(), "     materialTextView " + materialTextView);
                                    tableRow.addView(Шабка);
                                    // TODO: 25.07.2022
                                    holder.tableLayoutДляЛимитаМатериала.addView(tableRow);





                                    // TODO: 25.07.2022  третье линия снизу
                                    // TODO: 25.07.2022  третье линия снизу
                                    // TODO: 25.07.2022  третье линия снизу
                            tableRow = new TableRow(getContext());
                            View       ЛинияШабки =
                                    (TableRow)   getActivity().getLayoutInflater().inflate(R.layout.simple_for_shipment_of_materials_for_tablelout_only_for_header_down_row,
                                            null,false);
                            Log.i(this.getClass().getName(), "     materialTextView " + materialTextView);
                            ЛинияШабки.setBackgroundColor(Color.MAGENTA);
                            ЛинияШабки.setLayoutParams(НАстройкаЛиния);
                            tableRow.addView(ЛинияШабки);
                            // TODO: 25.07.2022
                            holder.tableLayoutДляЛимитаМатериала.addView(tableRow);






                                    // TODO: 25.07.2022  второе сими данные
                                    // TODO: 25.07.2022  второе сими данные
                                    // TODO: 25.07.2022  второе сими данные
                                    for (int i = 0; i < 2; i++) {
                                      tableRow = new TableRow(getContext());
                                    View      Данные =
                                            (TableRow) getActivity().getLayoutInflater()
                                                    .inflate(R.layout.simple_for_shipment_of_materials_for_tablelout_only_data,        null,false);
                                        // TODO: 25.07.2022
                                        materialTextView=Данные.findViewById(R.id.value1);
                                        Данные.setLayoutParams(НАстройкаДанные);
                                        tableRow.addView(Данные);
                                        // TODO: 25.07.2022
                                        holder.tableLayoutДляЛимитаМатериала.addView(tableRow);
                                        // TODO: 25.07.2022  третье линия снизу
                                        // TODO: 25.07.2022  третье линия снизу
                                        // TODO: 25.07.2022  третье линия снизу
                                        // TODO: 25.07.2022  третье линия снизу
                                        // TODO: 25.07.2022  третье линия снизу
                                             tableRow = new TableRow(getContext());
                                    View  ЛинияДанных =
                                                (TableRow)   getActivity().getLayoutInflater().inflate(R.layout.simple_for_shipment_of_materials_for_tablelout_only_data_for_down_row,
                                                        null,false);
                                        // TODO: 25.07.2022
                                       //materialTextView=ПерваяСтрокаШабка.findViewById(R.id.key1);
                                        Log.i(this.getClass().getName(), "     materialTextView " + materialTextView);
                                        ЛинияДанных.setLayoutParams(НАстройкаЛиния);
                                        tableRow.addView(ЛинияДанных);
                                        // TODO: 25.07.2022
                                        holder.tableLayoutДляЛимитаМатериала.addView(tableRow);


                                        // TODO: 25.07.2022  третье линия снизу

                                   }




                                    // TODO: 25.07.2022 после генериации TableRow  обновлем экран
                                    holder.tableLayoutДляЛимитаМатериала.requestLayout();
                                    holder.materialCardView_Shipment_of_materials.requestLayout();
                                    holder.tableLayoutДляЛимитаМатериала.forceLayout();
                                    holder.materialCardView_Shipment_of_materials.forceLayout();

                             }}



                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }

            // TODO: 29.03.2022  слутаеть смены статуса







            @Override
            public long getItemId(int position) {
                // TODO: 04.03.2022

                Log.i(this.getClass().getName(), "     getItemId holder.position " + position);

                return super.getItemId(position);

            }




            @Override
            public int getItemCount() {
                // TODO: 02.03.2022
                int КоличесвоСтрок=1;
               /* if (ARRAYJSONПолучениеДанныхОТ1СВторойШагRecycleViewAdapter.length()>0) {
                    КоличесвоСтрок = ARRAYJSONПолучениеДанныхОТ1СВторойШагRecycleViewAdapter.length();
                    Log.d(this.getClass().getName(), "ARRAYJSONПолучениеДанныхОТ1СВторойШагRecycleViewAdapter " + ARRAYJSONПолучениеДанныхОТ1СВторойШагRecycleViewAdapter
                            + " КоличесвоСтрок " +КоличесвоСтрок);
                } else {
                    КоличесвоСтрок=1;
                    Log.d(this.getClass().getName(), "ARRAYJSONПолучениеДанныхОТ1СВторойШагRecycleViewAdapter " + ARRAYJSONПолучениеДанныхОТ1СВторойШагRecycleViewAdapter
                            + " холостой ход КоличесвоСтрок " +КоличесвоСтрок);
                }*/
                Log.i(this.getClass().getName(), "     public int getItemCount()   КоличесвоСтрок" + КоличесвоСтрок);
                // TODO: 28.02.2022
                return КоличесвоСтрок ;
            }
        }//TODO  конец два
    }

}    // TODO: 28.02.2022 конец класса бизнес логики для фрагмента 1


// TODO: 11.07.2022 класс только с визуализацие пинера  
class SubClass_StyleSpinner_СтильСпинера{

    void МетодВизуализацииВидаСпинера(@NonNull TextView view, @Nullable Context context) {
        try {
            Drawable icon = context.getResources().getDrawable(R.drawable.icon_dsu1_for_limit_adapter);///TODO .icon_dsu1_create_message_for_tasks
            //icon.setBounds(0, 0, 0, 50);
            icon.setBounds(0, 0, 60, 60);
            ((TextView) view).setCompoundDrawables(icon, null, null, null);
            ((TextView) view).setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
            ((TextView) view).setTextColor(Color.BLACK);
            ((TextView) view).setGravity(Gravity.CENTER);
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            ((TextView) view).setPadding(20, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }
    }

    void МетодВизуализацииВидаСпинераНулеваяПозиция(@NonNull TextView view, @Nullable Context context) {
        try {
            Drawable icon = context.getResources().getDrawable(R.drawable.icon_dsu1_for_limit_maretialod_onepostion);///TODO .icon_dsu1_create_message_for_tasks
            //icon.setBounds(0, 0, 0, 50);
            icon.setBounds(0, 0, 60, 60);
            ((TextView) view).setCompoundDrawables(icon, null, null, null);
            ((TextView) view).setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
            ((TextView) view).setTextColor(Color.GRAY);
            ((TextView) view).setGravity(Gravity.CENTER);
            ((TextView) view).setHintTextColor(Color.GRAY);
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            ((TextView) view).setPadding(20, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }
    }
}





























