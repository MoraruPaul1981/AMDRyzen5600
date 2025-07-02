package com.dsy.dsu.OrdersTransportsApp.Window;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FilterQueryProvider;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.GreatUuidGenerations.GreatUuidGeneration;

import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;

import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.OrdersTransportsApp.Background.ServiceOrserTransportService;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.sous.backasync.launch.ModuleQuety;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Optional;


// TODO: 29.09.2022 фрагмент для получение материалов
public class FragmentNewOrderTransport extends Fragment {
    private Integer ПубличныйID;
    private BottomNavigationView BottomNavigationOrderTransport;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemViewвыход;

    private ProgressBar progressBarСканирование;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragmentBackListOrderTransport;
    private  TextView TextViewHadler;
    long start;
    long startДляОбноразвовной;
    private  Message message;
    private     SubClassNewOrderTransport subClassNewOrderTransport;
    private  Animation animationvibr1;
    private  ServiceOrserTransportService.  LocalBinderOrderTransport localBinderNewOrderTransport;
    private    TextView       textViewHadler;
    private  Cursor cursorCfo;
    private  Cursor cursorTypeTS;
    private  Cursor cursorGosNomer;
    private AlertDialog alertDialogNewOrderTranport;
    private   SubClassSetAllSprabochnik subClassSetAllSprabochnik;
    private LinearLayout linearLayout_new_create_order_transport;
    private   RelativeLayout relativeLayout_pool_inseder;
    private  ScrollView scrollview_pool_new_order_trasport;
    private     View   родительскийCardView;

   private SharedPreferences preferencesМатериалы;
   private Animation animation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            // TODO: 27.04.2023  Запускаем  Новый Заказ Транпорта
            subClassNewOrderTransport    =new SubClassNewOrderTransport(getActivity());
            subClassSetAllSprabochnik=           new SubClassSetAllSprabochnik();
            ПубличныйID = new GetttingPublicID().getttingPublicID(getContext());
            localBinderNewOrderTransport =  (ServiceOrserTransportService.  LocalBinderOrderTransport) getArguments().getBinder("binder");
            animationvibr1 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_singletable2);//
            preferencesМатериалы = getContext().getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  +
                     "  localBinderNewOrderTransport " +localBinderNewOrderTransport  + " ПубличныйID " +ПубличныйID);
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

    @SuppressLint({"RestrictedApi", "WrongConstant"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View    view=null;
        try {
            view= inflater.inflate(R.layout.simple_for_new_create_order_transport, container, false);
          // view= inflater.inflate(R.layout.list_item_progressing_ordertransport, container, false);
           /// view= inflater.inflate(R.layout.list_item_progressing_ordertransport, container, false);
            linearLayout_new_create_order_transport =(LinearLayout)  view.findViewById(R.id.linearLayout_new_create_order_transport);
            relativeLayout_pool_inseder =(RelativeLayout)  linearLayout_new_create_order_transport.findViewById(R.id.relativeLayout_pool_inseder);
            relativeLayout_pool_inseder.startAnimation(animationvibr1);
            scrollview_pool_new_order_trasport =(ScrollView)  linearLayout_new_create_order_transport.findViewById(R.id.scrollview_pool_new_order_trasport);
            // TODO: 01.05.2023  Кнопки
            BottomNavigationOrderTransport=  (BottomNavigationView) view. findViewById(R.id.BottomNavigationOrderTransport);
            bottomNavigationItemViewвыход = BottomNavigationOrderTransport.findViewById(R.id.id_lback);
            BottomNavigationOrderTransport.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationItemViewвыход.setIconSize(50);
            bottomNavigationItemViewвыход.setVisibility(View.VISIBLE);
            BottomNavigationOrderTransport.refreshDrawableState();
            BottomNavigationOrderTransport.forceLayout();
            progressBarСканирование=  (ProgressBar)  view. findViewById(R.id.ProgressBar);
            progressBarСканирование.setVisibility(View.VISIBLE);
            TextViewHadler = (TextView) view.findViewById(R.id.TextViewHadler);
            textViewHadler= (TextView) view.findViewById(R.id.TextViewHadler);
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_newscanner1);
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " view " +view + " container " +container) ;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);
            fragmentManager = getActivity().getSupportFragmentManager();
            start=     Calendar.getInstance().getTimeInMillis();
            startДляОбноразвовной=     Calendar.getInstance().getTimeInMillis();
            // TODO: 04.05.2023 Procsseing// TODO: 04.05.2023 Procsseing// TODO: 04.05.2023 Procsseing// TODO: 04.05.2023 Procsseing// TODO: 04.05.2023 Procsseing
            //todo запуск методов в фрагменте
            subClassNewOrderTransport.   МетодHandlerCallBack();
            subClassNewOrderTransport.   МетодВыходНаAppBack();
            // TODO: 14.05.2023  методы получение ДАнных ЦФО
            cursorCfo=     subClassNewOrderTransport .методGetAll("cfo","name");
            // TODO: 14.05.2023  методы получение ДАнных Виды ТС
            cursorTypeTS=     subClassNewOrderTransport .методGetAll("vid_tc","name");
            // TODO: 14.05.2023  методы получение ДАнных ЦФО
            cursorGosNomer=   subClassNewOrderTransport .методGetAll("track","fullname");
            // TODO: 11.05.2023 горизонтальеный Сколлл
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
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
    public void onStart() {
        super.onStart();
        try{
            if( cursorCfo ==null) {
                родительскийCardView = LayoutInflater.from(getContext()).
                        inflate(R.layout.list_item_progressing_newordertransport, relativeLayout_pool_inseder, false);

            }else {
                if( cursorCfo.getCount()>0) {
                    родительскийCardView = LayoutInflater.from(getContext()).
                            inflate(R.layout.fragment_ordertransport2, relativeLayout_pool_inseder, false);
                    // TODO: 17.05.2023 Клики По Данными New order Transport
                    subClassSetAllSprabochnik.методСправочникЦФО(родительскийCardView);
                    // TODO: 15.05.2023  Вид ТС
                    subClassSetAllSprabochnik.   методСправочникВидТС(родительскийCardView);
                    // TODO: 15.05.2023  ГОС Номер
                    subClassSetAllSprabochnik.    методСправочникГосНомер(родительскийCardView);
                    // TODO: 15.05.2023  Дата Будущего Времени
                    subClassSetAllSprabochnik.      методСправочникДатаБудущая(родительскийCardView);
                    // TODO: 15.05.2023  Кнопка Создания
                    subClassSetAllSprabochnik.      методСправочникКнопкаСоздания(родительскийCardView);

                    // TODO: 18.04.2023  Simple Adapter Кдик по Элементы
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                }else{
                    родительскийCardView = LayoutInflater.from(getContext()).
                            inflate(R.layout.list_item_isnull_newordertransport, relativeLayout_pool_inseder, false);
                }
                // TODO: 04.05.2023 Получаем Данные что обработка данных закончена
                subClassNewOrderTransport.    МетодДизайнПрограссБара();
            }

            relativeLayout_pool_inseder.removeView(родительскийCardView);
            relativeLayout_pool_inseder.addView(родительскийCardView);
            relativeLayout_pool_inseder.requestLayout();
            relativeLayout_pool_inseder.refreshDrawableState();
            scrollview_pool_new_order_trasport.pageScroll(ScrollView.FOCUS_UP);
            linearLayout_new_create_order_transport.requestLayout();



            // TODO: 19.04.2023 слушаелти
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " cursorCfo " + cursorCfo);
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

    }

    // TODO: 27.04.2023  новый код Заказ Транспорта
    class   SubClassNewOrderTransport{
        private Activity activity;
        public SubClassNewOrderTransport(Activity activity) {
            this.activity = activity;
        }
        // TODO: 28.04.2023
        void МетодHandlerCallBack() {
            try{
            message=Message.obtain(new Handler(Looper.myLooper()),()->{
                Bundle bundle=   message.getData();
                Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                        Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " время " +new Date().toLocaleString() + " message " +message );
            });
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
        // TODO: 28.04.2023

        private void МетодВыходНаAppBack() {
            try {
                bottomNavigationItemViewвыход.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            v.animate().rotationX(-40l);
                            message.getTarget() .postDelayed(()->{
                                v.animate().rotationX(0);
                                // TODO: 17.05.2023
                                методBackOrdersTransport();
                            },200);
                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName()
                                    + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " cursorCfo " + cursorCfo);
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
                });
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

        // TODO: 28.04.2023
        protected void методBackOrdersTransport() {
            try{
                fragmentManager.clearBackStack(null);
                fragmentTransaction = fragmentManager.beginTransaction();
              //  fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                fragmentBackListOrderTransport = new FragmentOrderTransportOneChane();
                Bundle bundleBackOrdersTransport=new Bundle();
                bundleBackOrdersTransport.putInt("isalive",2);
                fragmentBackListOrderTransport.setArguments(bundleBackOrdersTransport);
                fragmentTransaction.remove(fragmentManager.getFragments().get(0));
                fragmentTransaction.replace(R.id.linearLayout_root_activity_main, fragmentBackListOrderTransport).setReorderingAllowed(true).commit();//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.show(fragmentBackListOrderTransport);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " localBinderNewOrderTransport " +localBinderNewOrderTransport);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                        + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).
                        recordnewerror(e.toString(),
                                this.getClass().getName().toString(),
                                Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 19.05.2023
      void   методЗаписьВыбраногоЦФО(@NonNull Integer IDДляВставкиЦФО,@NonNull String НазваниеВыбраногоЦФО){
            try{
                // TODO: 17.11.2022 запоманаем выбраное цфо
                SharedPreferences.Editor editor = preferencesМатериалы.edit();
                editor.putBoolean("ДляСпинераУжеВибиралЦФО",true);
                // TODO: 09.12.2022 цфо
                editor.putInt("ПозицияВыбраногоЦФО",IDДляВставкиЦФО);
                editor.putString("НазваниеВыбраногоЦФО",НазваниеВыбраногоЦФО);
                editor.commit();
          Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                  " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                  " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                  + " НазваниеВыбраногоЦФО " +НазваниеВыбраногоЦФО + " IDДляВставкиЦФО " +IDДляВставкиЦФО);
      } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).
                    recordnewerror(e.toString(),
                            this.getClass().getName().toString(),
                            Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }

        // TODO: 28.04.2023


        // TODO: 02.08.2022



        private void МетодДизайнПрограссБара() {
            progressBarСканирование.postDelayed(()->{
                progressBarСканирование.setVisibility(View.INVISIBLE);
                progressBarСканирование.setIndeterminate(true);
            },250);
        }




        @SuppressLint("SuspiciousIndentation")
        @NonNull
        private String методПарсингаДатыЗаказа(String DateOrder) throws ParseException {
            try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
            Date breamy= simpleDateFormat.parse(DateOrder);
            DateOrder = simpleDateFormat.format(breamy);
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "  DateOrder " + DateOrder  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
            return DateOrder;
        }



        // TODO: 15.05.2023
        private Cursor методGetAll(@NonNull String ТекущеаяТаблица,@NonNull String ТекущейСтолбик) {
            Cursor cursor = null;
            try{
                // TODO: 04.05.2023  получаем первоночальыне Данные  #1
                ModuleQuety moduleQuety = new ModuleQuety(getContext());
                cursor = moduleQuety.getModuleQuery(ТекущеаяТаблица,
                        " SELECT *  FROM " + ТекущеаяТаблица + "  WHERE "+ТекущейСтолбик+" IS NOT NULL  AND _id >?  ORDER BY _id  ",
                        null);
                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " cursor " + cursor);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  cursor;
        }

        private Cursor методGetAllLike(@NonNull String ТекущеаяТаблица,
                                       @NonNull String ТекущейСтолбик,@NonNull String ValueForLike) {
            Cursor cursor = null;
            try{

                // TODO: 05.05.2023  ПОЛУЧАЕМ ДАННЫЕ
                ModuleQuety moduleQuety = new ModuleQuety(getContext());
                cursor = moduleQuety.getModuleQuery(ТекущеаяТаблица,
                        " SELECT *  FROM " + ТекущеаяТаблица + "  WHERE "+ТекущейСтолбик+"  LIKE= '"+ValueForLike+"' ",
                        null);
                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " cursor " + cursor);

                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  +
                        "  cursorCfo " +cursorCfo);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  cursor;
        }
        @SuppressLint("Range")
        private String методGetNumberNewIDOrderTranport() {
            String number_order = null;
            try{
                String Текущаятаблицы = "view_ordertransport";
                ModuleQuety moduleQuety = new ModuleQuety(getContext());
             Cursor   CursorlastDateAsync = moduleQuety.getModuleQuery(Текущаятаблицы,
                        " SELECT MAX ( D.versionserveraandroid  ) " +
                                "AS MAX_R  FROM " +  Текущаятаблицы.trim()+" AS D" , null);

                Integer  получаемGetId=CursorlastDateAsync.getInt(CursorlastDateAsync.getColumnIndex("MAX_R"));
                if(получаемGetId!=null){
                    получаемGetId=получаемGetId+1;
                }else {
                    получаемGetId=1;
                }
                number_order="зт"+"#" +ПубличныйID.toString()+  получаемGetId;
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                // TODO: 18.05.2023
                if(CursorlastDateAsync!=null) {
                    CursorlastDateAsync.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                        Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros( getContext())
                        .recordnewerror(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return number_order.trim();
        }

// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport           //// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport   //// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport


    }
    // TODO: 14.05.2023 НАЧАЛО КЛАСС ПОСИКА SEARCHVIEW
    class SubClassSetAllSprabochnik {

        public SubClassSetAllSprabochnik() {
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  +
                    "  cursorCfo " +cursorCfo);
        }
        private void методСправочникЦФО(@NonNull View родительскийCardView) {
            try{
                MaterialTextView           materialTextCFO = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuecfo);//ВИД CFO
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера==false){
                    методЗаполенияСправочника(   materialTextCFO,cursorCfo,"выберете цфо");
                }else {
                    методВытаскивваемGetCFOИзSharePre(materialTextCFO);
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ФлагВыбиралУжеЦФОИзСпинера " +ФлагВыбиралУжеЦФОИзСпинера );
                MaterialButton            materialButtonExitOrSave = (MaterialButton)   родительскийCardView.findViewById(R.id.bottomnewordertransport);
                // TODO: 12.05.2023
                materialTextCFO.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.animate().rotationX(+40l);
                        message.getTarget() .postDelayed(()->{
                            v.animate().rotationX(0);
                            // TODO: 16.05.2023 запуск SEARCHVIEW CFO
                            new SearchViewForNewOT().методПоискаНовыйЗаказТранспорта(  cursorCfo,"name",
                                    "cfo","цфо",materialTextCFO ,materialButtonExitOrSave);
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        },200);
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void методВытаскивваемGetCFOИзSharePre(@NonNull MaterialTextView           materialTextCFO ) {
            try{
                // TODO: 17.11.2022  если пользователь уже выбирал
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера  ){
                    Integer ПозицияВыбраногоЦФО=            preferencesМатериалы.getInt("ПозицияВыбраногоЦФО",0);
                    String НазваниеВыбраногоЦФО=            preferencesМатериалы.getString("НазваниеВыбраногоЦФО","");
                    Bundle bundle=new Bundle();
                    bundle.putInt("getId",ПозицияВыбраногоЦФО);
                    bundle.putString("getName",НазваниеВыбраногоЦФО);
                    materialTextCFO.setTag(bundle);
                    materialTextCFO.setText(НазваниеВыбраногоЦФО);
                    materialTextCFO.forceLayout();
                    materialTextCFO.refreshDrawableState();
                }
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void методСправочникВидТС(@NonNull  View родительскийCardView) {
            try{
                MaterialTextView    materialTextTypeTS = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuetypetc);//ВИД CFO
                    методЗаполенияСправочника(   materialTextTypeTS,cursorTypeTS,"выберете вид тс");
               MaterialButton    materialButtonExitOrSave = (MaterialButton)   родительскийCardView.findViewById(R.id.bottomnewordertransport);
                // TODO: 12.05.2023
                materialTextTypeTS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.animate().rotationX(+40l);
                        message.getTarget() .postDelayed(()->{
                            v.animate().rotationX(0);
                          new SearchViewForNewOT().методПоискаНовыйЗаказТранспорта( cursorTypeTS,"name","vid_tc","вид тс",materialTextTypeTS,materialButtonExitOrSave);
                            // TODO: 17.05.2023
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        },200);
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void методСправочникГосНомер(@NonNull  View родительскийCardView) {
            try{
                MaterialTextView       materialTextGosNomer = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuegosnomer);
                методЗаполенияСправочника(   materialTextGosNomer,cursorGosNomer,"выберете гос.номер");
            MaterialButton    materialButtonExitOrSave = (MaterialButton)   родительскийCardView.findViewById(R.id.bottomnewordertransport);
                // TODO: 12.05.2023
                materialTextGosNomer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.animate().rotationX(+40l);
                        message.getTarget() .postDelayed(()->{
                            v.animate().rotationX(0);
                          //  new SearchViewForNewOT().методПоискаНовыйЗаказТранспорта( (MaterialTextView) v,cursorGosNomer,"fullname","track","гос.номер",materialButtonExitOrSave);
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        },200);
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void методСправочникДатаБудущая(@NonNull  View родительскийCardView) {
            try{
            MaterialTextView        materialTextDate = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuedate);//ВИД CFO
                методЗаполенияСправочникДата(   materialTextDate,"выберете дату");
                MaterialButton    materialButtonExitOrSave = (MaterialButton)   родительскийCardView.findViewById(R.id.bottomnewordertransport);
                // TODO: 16.05.2023 Первоночальная Запись даты
                СlassGetDateForNewOrderTranport classGetDateForNewOrderTranport=new СlassGetDateForNewOrderTranport();
                classGetDateForNewOrderTranport.   методGetFirstDateForNewOrder(materialTextDate);
                // TODO: 12.05.2023
                materialTextDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            v.animate().rotationX(+40l);
                            message.getTarget() .postDelayed(()-> {
                                v.animate().rotationX(0);
                                // TODO: 16.05.2023 создание Даты
                                СlassGetDateForNewOrderTranport classGetDateForNewOrderTranport = new СlassGetDateForNewOrderTranport();
                                classGetDateForNewOrderTranport.методGetDateForNewOrder((MaterialTextView) v);
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            },200);
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }

                    }
                });
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void методСправочникКнопкаСоздания(@NonNull  View родительскийCardView) {
            try{
                MaterialButton     materialButtonExitOrSave = (MaterialButton)   родительскийCardView.findViewById(R.id.bottomnewordertransport);
                // TODO: 12.05.2023
                materialButtonExitOrSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 15.05.2023 закрывает или сохраняем
                            // TODO: 16.05.2023  CLICK SAVE or EXIT
                        MaterialTextView materialTextcfo
                                = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuecfo);//ВИД CFO
                        MaterialTextView materialTexttypetc
                                = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuetypetc);//ВИД type tc
                        MaterialTextView materialTextdate
                                = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuedate);//ВИД Dates

                        Snackbar snackbar=      Snackbar.make(v, "Вы не заполнили заказ !!! ",Snackbar.LENGTH_LONG).setAction("Action",null);
                        // TODO: 15.05.2023
                        if(materialTextcfo.getText().length()>1
                        &&  materialTexttypetc.getText().length()>1
                        &&  materialTextdate.getText().length()>1) {

                            v.animate().rotationX(+40l);
                            message.getTarget() .postDelayed(()-> {
                                        v.animate().rotationX(0);
                                        // TODO: 16.05.2023 Вставляем Новый Заказ Транпорта
                                        String table = "order_tc";
                                        Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + table + "");
                                        ContentValues valuesNewOrderTransport = new ContentValues();
                                        valuesNewOrderTransport.put("user_update", ПубличныйID);
                                        Long ВерсияДанныхUp = new VersionCurentTable(getContext()).upVersionCurentTable(table );
                                        valuesNewOrderTransport.put("current_table", ВерсияДанныхUp);
                                        String ДатаОбновления = new GetMainDateForApp(getContext()).getMainDateForApp();
                                        valuesNewOrderTransport.put("date_update", ДатаОбновления);
                                      Long   UUIDGenetetorNewCustoner= (Long) new GreatUuidGeneration(getContext()).greatUuidGeneration();
                                      valuesNewOrderTransport.put("uuid", UUIDGenetetorNewCustoner);
                                         Bundle bundlegetCfo=(Bundle)       materialTextcfo.getTag();
                                valuesNewOrderTransport.put("cfo", bundlegetCfo.getInt("getId"));
                                         Bundle bundlegetTypeTC=(Bundle)       materialTexttypetc.getTag();
                                valuesNewOrderTransport.put("vid_trasport", bundlegetTypeTC.getInt("getId"));
                                         Bundle bundlegetDateOrder=(Bundle)       materialTextdate.getTag();


                                // TODO: 09.06.2023 Дата Время Для Вставки нового Заказа
                       String ДатаДляСозданияЗаказа=         bundlegetDateOrder.getString("GetDateOrder");
                                valuesNewOrderTransport.put("dateorders", ДатаДляСозданияЗаказа);
                                // TODO: 09.06.2023  сама ВСТАВКА НОВОГО ЗАКАЗА ТРАСПОРТА
                                
                                        ContentResolver contentResolver = getContext().getContentResolver();
                                        // TODO: 16.05.2023 Сама Вставка
                                       Uri urlРезультатNewOrderTranport=  contentResolver.insert(uri,  valuesNewOrderTransport);

                                String ответОперцииВставки=    Optional.ofNullable(urlРезультатNewOrderTranport).map(Emmeter->Emmeter.toString().replace("content://","")).get();
                          Integer      РезультатУдалениеСтатуса= Integer.parseInt(ответОперцииВставки);

                                Snackbar snackbarОперацияДобавления=      Snackbar.make(v, "Ошибка заказ не добавлен !!! ",Snackbar.LENGTH_LONG).setAction("Action",null);
                                if(РезультатУдалениеСтатуса<1){
                                    snackbarОперацияДобавления.show();
                                }else{
                                    // TODO: 19.05.2023 Если Успешно Записала Новай заказ Трспорта
                                    методПослеУспешногоСозданогоЗаказа(bundlegetCfo);

                                }
                                // TODO: 15.05.2023  Сохранчем Выбраные Данные
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + " materialTextcfo.getText().length() " +materialTextcfo.getText().length()
                                        + " materialTexttypetc.getText().length() " +materialTexttypetc.getText().length()
                                        + " materialTextdate.getText().length() "+ materialTextdate.getText().length()+
                                        " РезультатУдалениеСтатуса " +РезультатУдалениеСтатуса);

                                    },150);
                            // TODO: 15.05.2023  Сохранчем Выбраные Данные
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " materialTextcfo.getText().length() " +materialTextcfo.getText().length()
                             + " materialTexttypetc.getText().length() " +materialTexttypetc.getText().length()
                                    + " materialTextdate.getText().length() "+ materialTextdate.getText().length());
                        }else{
                            // TODO: 15.05.2023
                            snackbar.show();
                         ///   subClassNewOrderTransport.   методBackOrdersTransport();

         /*                   // TODO: 16.11.2022  ПОСЛЕ УСТАНОВКИ РАБОТАЕТ ОДИН РАЗ ПРИ СТАРТЕ ЗАРУСК ОБЩЕГО WORK MANAGER
                            new ClassOneSingnalGenerator(getContext()).МетодЗапускаетОБЩУЮСинхронизацию();
*/

                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        }
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void методПослеУспешногоСозданогоЗаказа(Bundle bundlegetCfo) {
            try{
                subClassNewOrderTransport.   методBackOrdersTransport();
                // TODO: 19.05.2023  И далее Записали  Выбранный ЦФо в PreShare
                Integer getId=     bundlegetCfo.getInt("getId");
                String getName=     bundlegetCfo.getString("getName");
                subClassNewOrderTransport.     методЗаписьВыбраногоЦФО(getId,getName);
                // TODO: 06.06.2023 запускаем сихорнизацию одноразовую
                Integer ПубличныйIDДляФрагмента =new GetttingPublicID().getttingPublicID(getContext());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 02.08.2022




















        private void методЗаполенияСправочника(@NonNull MaterialTextView values,
                                               @NonNull Cursor cursor,
                                               @NonNull String ФлагНаличияСпра) {
            try{
                values.startAnimation(animationvibr1);
                Bundle bundle=new Bundle();
                if (cursor==null || cursor.getCount()==0) {
                    values.setHint( "нет справочника");
                    bundle.putString("ФлагНаличияСпра","нет справочника");
                    values.setTag(bundle);
                }else {
                    bundle.putInt("cursor",cursor.getCount());
                    bundle.putString("ФлагНаличияСпра",ФлагНаличияСпра);
                    values.setTag(bundle);
                    values.setHint(ФлагНаличияСпра);
                }
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                        "cursor " +cursor);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void методЗаполенияСправочникДата(@NonNull MaterialTextView values, @NonNull String ФлагНаличияСпра) {
            try{
                Bundle bundle=new Bundle();
                values.setTag(bundle);
                values.setHint(ФлагНаличияСпра);
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void МетодЗапускаАнимацииAllvalues(View v) {
            v.animate().rotationX(+40l);
            message.getTarget() .postDelayed(()->{
                v.animate().rotationX(0);
            },200);
        }
}//TODO END SubClassGETVALUE


    //TODO SubClass SearchView
    class SearchViewForNewOT {
        private    ListView ListViewForSearchView;
        private  String Столбик;
        private String ТаблицаТекущая;
        private   androidx.appcompat.widget.SearchView searchView;
        private  Cursor cursor;
        private  MaterialTextView materialTextViewParent;

        @SuppressLint("Range")
        void методПоискаНовыйЗаказТранспорта(@NonNull Cursor cursor,
                                             @NonNull String Столбик,
                                             @NonNull String ТаблицаТекущая,
                                             @NonNull String Спровочник,
                                             @NonNull MaterialTextView materialTextViewParent,
                                             @NonNull  MaterialButton            materialButtonExitParent){
            this.cursor=cursor;
            this.Столбик=Столбик;
            this.ТаблицаТекущая=ТаблицаТекущая;
            this.materialTextViewParent=materialTextViewParent;
            alertDialogNewOrderTranport = new MaterialAlertDialogBuilder(getContext()){
                private     MaterialButton ButtonFilterЗакрытьДиалог =null;
                @NonNull
                @Override
                public MaterialAlertDialogBuilder setView(View view) {
                    try{
                        ButtonFilterЗакрытьДиалог =    (MaterialButton) view.findViewById(R.id.bottom_newscanner1);
                        ListViewForSearchView =    (ListView) view.findViewById(R.id.ListViewForNewOrderTransport);
                        searchView =    (androidx.appcompat.widget.SearchView) view.findViewById(R.id.searchview_newordertransport);
                        searchView.setQueryHint("Поиск "+Спровочник);
                        ListViewForSearchView.setTextFilterEnabled(true);
                        searchView.setSubmitButtonEnabled(true);




                        TextView textViewСтрокаПосика = searchView.findViewById(com.google.android.material.R.id.search_src_text);




                        textViewСтрокаПосика.setTextColor(Color.rgb(0,102,102));
                        textViewСтрокаПосика.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        searchView.refreshDrawableState();

                        ///TODO ГЛАВНЫЙ АДАПТЕР чата
                        SimpleCursorAdapter          simpleCursorForSearchView =
                                new SimpleCursorAdapter(getContext(),
                                        R.layout.simple_newspinner_dwonload_newfiltersearch, cursor, new String[]{ Столбик,"_id"},
                                        new int[]{android.R.id.text1,android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                        SimpleCursorAdapter.ViewBinder БиндингДляПоиск = new SimpleCursorAdapter.ViewBinder(){

                            @Override
                            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                                switch (view.getId()) {
                                    case android.R.id.text1:
                                        Log.d(this.getClass().getName()," position");
                                        if (cursor.getCount()>0) {
                                            try{
                                                MaterialTextView materialTextViewЭлементСписка=(MaterialTextView) view;
                                                // TODO: 13.12.2022  производим состыковку
                                              Integer   getId = cursor.getInt(cursor.getColumnIndex("_id"));
                                                if (getId>0) {
                                                    Long UUIDGetFilter = cursor.getLong(cursor.getColumnIndex("uuid"));
                                                    String  getName = cursor.getString(cursor.getColumnIndex(Столбик)).trim();
                                                    Bundle bundle=new Bundle();
                                                    bundle.putInt("getId",getId);
                                                    bundle.putString("getName",getName);
                                                    bundle.putLong("getUUID",UUIDGetFilter);
                                                    // TODO: 16.05.2023 Элемент Заполяем данными  TAG
                                                    materialTextViewЭлементСписка.setTag(bundle);
                                                // TODO: 20.01.2022
                                                Log.d(this.getClass().getName()," getName "+getName + " getId " +getId  + " UUIDGetFilter " +UUIDGetFilter+ " bundle "+bundle);
                                                boolean ДлинаСтрокивСпиноре = getName.length() >40;
                                                if (ДлинаСтрокивСпиноре==true) {
                                                    StringBuffer sb = new StringBuffer(getName);
                                                    sb.insert(40, System.lineSeparator());
                                                    getName = sb.toString();
                                                    Log.d(getContext().getClass().getName(), " getName " + "--" + getName);/////
                                                }
                                                // TODO: 16.05.2023 Элемент Заполяем данными
                                                    materialTextViewЭлементСписка.setText(getName);
                                                    materialTextViewЭлементСписка.startAnimation(animation);
                                                }
                                                Log.d(getContext().getClass().getName(), "\n"
                                                        + " время: " + new Date() + "\n+" +
                                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        "  ((MaterialTextView)view) " +  materialTextViewЭлементСписка);
                                                // TODO: 13.12.2022 филь
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                new RecordNewErros( getContext()).recordnewerror(e.toString(),
                                                        this.getClass().getName(),
                                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            }
                                            return true;
                                        } else {
                                            Log.d(this.getClass().getName()," position");
                                            return false;
                                        }
                                }
                                return false;
                            }
                        };
                        simpleCursorForSearchView.setViewBinder(БиндингДляПоиск);
                        simpleCursorForSearchView.notifyDataSetChanged();
                        ListViewForSearchView.setAdapter(simpleCursorForSearchView);
                        ListViewForSearchView.setSelection(0);
                        ListViewForSearchView.startAnimation(animation);
                        ListViewForSearchView.refreshDrawableState();
                        ListViewForSearchView.requestLayout();

                        // TODO: 13.12.2022  Поиск и его слушель
                        МетодПоискаФильтр(  ТаблицаТекущая,             simpleCursorForSearchView );

                        // TODO: 15.05.2023 Слушатель Действия Кнопки Сохранить
                        // TODO: 16.05.2023  КЛИК СЛУШАТЕЛЬ ПО ЕЛЕМЕНТУ
                        методКликПоЗаказуOrder(  );

                        методКликДейсвиеКнопкиСохранить(ButtonFilterЗакрытьДиалог);

                        Log.d(this.getContext().getClass().getName(), "\n"
                                + " время: " + new Date()+"\n+" +
                                " Класс в процессе... " +
                                this.getContext().getClass().getName()+"\n"+
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(this.getContext())
                                .recordnewerror(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }

                    return super.setView(view);
                    // TODO: 20.12.2022  тут конец выбеленого
                }
                // TODO: 16.05.2023  КЛИК ПО ЕЛЕМЕНТУ
                private void методКликПоЗаказуOrder( ) {
                    try{
                    ListViewForSearchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try{
                            MaterialTextView materialTextViewGetElement=(MaterialTextView)       parent.getAdapter().getView(position, view,parent );
                            if(materialTextViewGetElement!=null){
                                Bundle bundlePepoles= (Bundle) materialTextViewGetElement.getTag();
                                materialTextViewGetElement.startAnimation(animationvibr1);
                                // TODO: 16.05.2023 Из Выбраного Элемента Получаеним ДАнные
                                Integer getId=      bundlePepoles.getInt("getId",0);
                                String getName=   bundlePepoles.getString("getName","").trim();
                                Long getUUID =   bundlePepoles.getLong("getUUID",0l);
                                materialTextViewParent.setTag(bundlePepoles);
                                materialTextViewParent.setText(getName);
                                    // TODO: 15.05.2023 ЗАПОЛЕНИЕ ДАННЫМИ КЛИК
                                materialTextViewParent.startAnimation(animation);
                                        // TODO: 15.05.2023  ЗАКРЫВАЕТ
                                        alertDialogNewOrderTranport.cancel();
                                        alertDialogNewOrderTranport.dismiss();
                                    Log.d(getContext().getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +

                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date()+"\n+" +
                                    " Класс в процессе... " +
                                   getContext().getClass().getName()+"\n"+
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()));
                                    }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros( getContext())
                                    .recordnewerror(e.toString(),
                                            this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                            Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros( getContext())
                            .recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }


            }
                    .setTitle(Спровочник)
                    .setCancelable(false)
                    .setIcon( R.drawable.icon_dsu1_new_tamples)
                    .setView(getLayoutInflater().inflate( R.layout.simple_for_new_spinner_searchview_newordertransport2, null )).show();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(    alertDialogNewOrderTranport.getWindow().getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height =WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.gravity = Gravity.CENTER;
            alertDialogNewOrderTranport.getWindow().setAttributes(layoutParams);
            // TODO: 13.12.2022 ВТОРОЙ СЛУШАТЕЛЬ НА КНОПКУ
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        }






        private void МетодПоискаФильтр(@NonNull String ТаблицаДляФильтра,@NonNull     SimpleCursorAdapter          simpleCursorForSearchView ) {
            try{
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        try{
                            Log.d(this.getClass().getName()," position");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros( getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        try{
                            Log.d(this.getClass().getName()," position");
                            if (newText.length() > 0) {
                                FilterQueryProvider filter = simpleCursorForSearchView.getFilterQueryProvider();
                                filter.runQuery(newText);
                                return true;
                            }else {
                                // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР
                                методПерезаполенияПоискаИзФилтра(cursor,             simpleCursorForSearchView );
                                Log.d(getContext() .getClass().getName(), "\n"
                                        + " время: " + new Date()+"\n+" +
                                        " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " cursor "+ cursor);
                                return true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext() ).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return false;
                    }
                });
                simpleCursorForSearchView.setFilterQueryProvider(new FilterQueryProvider() {
                    @Override
                    public Cursor runQuery(CharSequence constraint) {
                        final Cursor[] cursorFilter = {null};
                        try{
                            message.getTarget().post(()->{
                            ///   cursorFilter[0] =    simpleCursorForSearchView.getCursor();
                                cursorFilter[0] =          subClassNewOrderTransport
                                    .методGetAllLike(ТаблицаТекущая,Столбик,constraint.toString());

                                // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР
                                методПерезаполенияПоискаИзФилтра(cursorFilter[0],          simpleCursorForSearchView );
                                // TODO: 16.05.2023
                                Log.d(getContext() .getClass().getName(), "\n"
                                        + " время: " + new Date()+"\n+" +
                                        " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " cursorFilter "+ cursorFilter[0]);

                            });
                            Log.d(getContext() .getClass().getName(), "\n"
                                    + " время: " + new Date()+"\n+" +
                                    " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " cursorFilter "+ cursorFilter[0]);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros( getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return cursorFilter[0];
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros( getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР
        private void методПерезаполенияПоискаИзФилтра(Cursor cursorFilter ,@NonNull     SimpleCursorAdapter          simpleCursorForSearchView ) {
            try {
            simpleCursorForSearchView.swapCursor(cursorFilter);
            // TODO: 16.05.2023 reboot disain
            simpleCursorForSearchView.notifyDataSetChanged();
            // TODO: 16.05.2023 Если данные Естьв Фильтре
            if(cursorFilter !=null && cursorFilter.getCount()>0) {
                ListViewForSearchView.setSelection(0);
                View filter=       ListViewForSearchView.getChildAt(0);
                if(filter!=null){
                    ((MaterialTextView)filter).startAnimation(animationvibr1);
                }
            }else{
                searchView.setBackgroundColor(Color.RED);
                message.getTarget().postDelayed(() -> {
                    searchView.setBackgroundColor(Color.parseColor("#F2F5F5"));
                }, 500);
            }
            // TODO: 16.05.2023
            ListViewForSearchView.refreshDrawableState();
            ListViewForSearchView.forceLayout();
                Log.d(getContext() .getClass().getName(), "\n"
                        + " время: " + new Date()+"\n+" +
                        " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " cursorFilter "+ cursorFilter);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros( getContext()).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }

        private void методКликДейсвиеКнопкиСохранить( @NonNull MaterialButton materialButtonFilterЗакрытьДиалог) {
            try{
                materialButtonFilterЗакрытьДиалог.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialogNewOrderTranport.dismiss();
                        alertDialogNewOrderTranport.cancel();

                        Log.d(materialButtonFilterЗакрытьДиалог.getContext().getClass().getName(), "\n"
                                + " время: " + new Date()+"\n+" +
                                " Класс в процессе... " +  materialButtonFilterЗакрытьДиалог.getContext().getClass().getName()+"\n"+
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(materialButtonFilterЗакрытьДиалог.getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 15.05.2023 КОНЕЦ НОВОГО ПОСИКА
    }
    // TODO: 16.05.2023 Класс Для Порлучение Даты Для Новго Заказа
    class  СlassGetDateForNewOrderTranport{
        void  методGetDateForNewOrder( @NonNull      MaterialTextView materialTextViewКликДата){
            try{
                final String[] FullNameCFO = new String[1];
                final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
                Calendar newDate = Calendar.getInstance();
                //TODODATA
                DatePickerDialog ДатаДляКалендаря =
                        new DatePickerDialog(getContext(), android.R.style.Theme_Holo_InputMethod , new DatePickerDialog.OnDateSetListener() {////Theme_Holo_Dialog_MinWidth  //Theme_Holo_Panel
                            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                newDate.set(year, monthOfYear, dayOfMonth);
                                try {
                                  //  String ДатаДляНовогоЗаказаТраспорта= DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(newDate.getTime());
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd,MMM yyyy" , new Locale("ru"));
                                    SimpleDateFormat simpleDateFormatBungle = new SimpleDateFormat("yyyy-MM-dd" , new Locale("ru"));
                                    Date ДатаВыбранаяПользователь= newDate.getTime();
                                    // TODO: 09.06.2023
                                    Calendar myCal = new GregorianCalendar();
                                    Date ДатаСейчасСитемная= myCal.getTime();
                                    // TODO: 09.06.2023  проверяем что выбрана дата старше чем сейчас
                                    if (        ДатаВыбранаяПользователь.after(ДатаСейчасСитемная)) {
                                        String    ДатаДляНовогоЗаказаТраспорта = simpleDateFormat.format(ДатаВыбранаяПользователь);
                                        String    ДатаДляНовогоЗаказаТраспортаBungle = simpleDateFormatBungle.format(ДатаВыбранаяПользователь);
                                        // TODO: 16.05.2023 дата
                                        методЗаписиНовуюДату(ДатаДляНовогоЗаказаТраспорта, materialTextViewКликДата,ДатаДляНовогоЗаказаТраспортаBungle);
                                        Log.d(getContext() .getClass().getName(), "\n"
                                                + " время: " + new Date()+"\n+" +
                                                " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+" ДатаДляНовогоЗаказаТраспорта " +ДатаДляНовогоЗаказаТраспорта);
                                    }

                                    Log.d(getContext() .getClass().getName(), "\n"
                                            + " время: " + new Date()+"\n+" +
                                            " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }

                        }, newDate.get(Calendar.YEAR), newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH) +1);
                ДатаДляКалендаря.setTitle("Календарь");
                ДатаДляКалендаря.setButton(DatePickerDialog.BUTTON_POSITIVE, "Создать", ДатаДляКалендаря);
                ДатаДляКалендаря.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Закрыть", ДатаДляКалендаря);
                if (!ДатаДляКалендаря.isShowing()) {
                    ДатаДляКалендаря .show();
                }
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setBackgroundColor(Color.WHITE);
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setBackgroundColor(Color.WHITE);
                ////
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

        private void методЗаписиНовуюДату(String ДатаДляНовогоЗаказаТраспорта,
                                          @NonNull MaterialTextView materialTextViewКликДата,
                                          @NonNull  String ДатаДляНовогоЗаказаТраспортаBungle) {
            try{
                Bundle bundle=new Bundle();
                bundle.putString("GetDateOrder",ДатаДляНовогоЗаказаТраспортаBungle.trim());
                        materialTextViewКликДата.setTag(bundle);
            materialTextViewКликДата.setText(ДатаДляНовогоЗаказаТраспорта.toString());
            materialTextViewКликДата.refreshDrawableState();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }

        void  методGetFirstDateForNewOrder(@NonNull MaterialTextView        materialTextDate){
            try{
                // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy", new Locale("ru"));
                Calendar myCal = new GregorianCalendar();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd,MMM yyyy" , new Locale("ru"));
                          SimpleDateFormat simpleDateFormatBungle = new SimpleDateFormat("yyyy-MM-dd" , new Locale("ru"));
                myCal.add(Calendar.DAY_OF_MONTH, 1);
                Date breamy= myCal.getTime();

            String    ДатаДляНовогоЗаказаТраспорта=        simpleDateFormat .format(breamy);
            String    ДатаДляНовогоЗаказаТраспортаBungle=       simpleDateFormatBungle.format(breamy);

                // TODO: 16.05.2023  Записи Новой Даты 
                методЗаписиНовуюДату(ДатаДляНовогоЗаказаТраспорта, materialTextDate,ДатаДляНовогоЗаказаТраспортаBungle);
                Log.d(getContext() .getClass().getName(), "\n"
                        + " время: " + new Date()+"\n+" +
                        " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " ДатаДляНовогоЗаказаТраспорта "+ ДатаДляНовогоЗаказаТраспорта);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

        // TODO: 17.05.2023  Метод Ищем Значение для Вставки Из Строки В Значниея
    }

    // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne
}