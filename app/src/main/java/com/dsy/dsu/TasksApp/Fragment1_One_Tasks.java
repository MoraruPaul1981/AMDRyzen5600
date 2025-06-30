package com.dsy.dsu.TasksApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;


import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;


import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

import com.google.android.material.textview.MaterialTextView;
import com.sous.backasync.launch.ModuleQuety;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


public class Fragment1_One_Tasks extends Fragment {
    // TODO: 01.03.2022
    private RecyclerView recyclerView;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1.MyRecycleViewAdapter myRecycleViewAdapter;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1.MyViewHolder myViewHolder;
    private View viewДляПервойКнопкиHome_Задания;
    private  Cursor Курсор_ГлавныйКурсорДляЗадач;
    private  Cursor Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе = null;

    private Bundle BungleДанныеДляViewCard;
    private Bundle BungleДанныеДляViewCardBungle;
    private Bundle BungleДанныеДляViewCardBungleID;
    private Bundle BungleДанныеДляViewCardBungleСКемПереписка;
    private Bundle BungleДанныеДляViewCardДляпередачиCallsBaskПримечание;
    private Bundle bundleНаФрагменте5IDДляСкемПереписка;
    final private String ИмяСлужбыWorkmanagerОдноразовая = "WorkManager Synchronizasiy_Data Disposable";
    final private String ИмяСлужбыWorkmanagerОБЩАЯ = "WorkManager Synchronizasiy_Data";

    // TODO: 01.03.2022--перпменные для переноса в другие ФОАГМЕНТЫ1,2,3,4,5
    private BottomNavigationView bottomNavigationViewДляTasks;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationКонкретноКнопкаДобавить;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationПринудительныйОбмен;
    private TextView textViewТекущаяЗадача;
    private Integer ПубличныйIDДляФрагмента;
    private LinearLayout linearLayou;
    protected   Integer СкемИдётПереписка;
    private FragmentManager fragmentManagerДляЗадачи;
    private FragmentTransaction fragmentTransactionляЗадачи;
    private Fragment fragment_ТекущийФрагмент;
    private WorkInfo WorkInfoИнформацияОЗапущенойСлужбеОдноразовая;
    private    Integer IDДляСкемПереписка=0;
    private Handler handlerTaskFragment1;
    private ProgressBar progressBarTaskFragment1;




    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            Log.d(this.getClass().getName(), "  Fragment1_One_Tasks  viewДляПервойКнопкиHome_Задания " + viewДляПервойКнопкиHome_Задания);

            recyclerView = (RecyclerView) viewДляПервойКнопкиHome_Задания.findViewById(R.id.recycleviewActiviTask);
            recyclerView.setVisibility(View.VISIBLE);

            progressBarTaskFragment1  = (ProgressBar) view.findViewById(R.id.prograessbartaskfragmen1down); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            recyclerView= (RecyclerView) view.findViewById(R.id.recycleviewActiviTask);
            textViewТекущаяЗадача = (TextView) view.findViewById(R.id.activy_task_fragment1_tasksnameеtextview);
            Log.d(this.getClass().getName(), "  Fragment1_One_Tasks  textViewТекущаяЗадача " + textViewТекущаяЗадача + " view " + view);
            textViewТекущаяЗадача.setText("Задачи на выполнение".toUpperCase());
            fragmentManagerДляЗадачи = getActivity().getSupportFragmentManager();
            linearLayou = (LinearLayout) getActivity().findViewById(R.id.activity_main_fisrt_for_tasks);
            bundleНаФрагменте5IDДляСкемПереписка=   getArguments();
            bottomNavigationViewДляTasks = (BottomNavigationView) view.findViewById(R.id.bottomnavigationActiviTask8);
            bottomNavigationКонкретноКнопкаДобавить = bottomNavigationViewДляTasks.findViewById(R.id.id_taskCreateNewTasks);
            bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи = bottomNavigationViewДляTasks.findViewById(R.id.id_taskHome);
            bottomNavigationПринудительныйОбмен = bottomNavigationViewДляTasks.findViewById(R.id.id_taskAsyns);
            //  bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи.performLongClick();
            bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи.setSelected(true);
            bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи.setTitle("Контроль");
            bottomNavigationКонкретноКнопкаДобавить.setVisibility(View.GONE);
            BungleДанныеДляViewCard=new Bundle();
            BungleДанныеДляViewCardBungle=new Bundle();
            BungleДанныеДляViewCardBungleID=new Bundle();
            BungleДанныеДляViewCardДляпередачиCallsBaskПримечание=new Bundle();
            BungleДанныеДляViewCardBungleСКемПереписка=new Bundle();
            // TODO: 02.08.2022 инициализация recyreview

            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1.МетодИнициализацииRecycleViewДляЗадач(viewДляПервойКнопкиHome_Задания);

            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 imageView  onViewCreated ");
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     try{
         // TODO: 16.04.2025
         Log.d(getContext().getClass().getName(), "\n"
                 + " время: " + new Date() + "\n+" +
                 " Класс в процессе... " + this.getClass().getName() + "\n" +
                 " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());


         // TODO: 04.03.2022 инициализацуия ссылок на кассы
         subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1 = new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1(getContext(), getActivity());

        // TODO: 02.08.2022  иницциализирован два work manager
        subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1.МетодСоздаенияСлушателяДляЧатаWorkMAnager();
        // TODO: 04.03.2022 создаем слушатель    третий класс создаем ЗАПУСКАЕМ СЛУШАТЕЛЬ КУРСОРРА туту запускам два слушателя дялнаших work manager
        subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1.МетодСоздаенияСлушателяДляЧатаWorkMAnagerОбщщийДополнительный();
         // TODO: 02.08.2022  инициализация Колл бэка главного потока
// TODO: 02.08.2022  инициализация recuycrevVIew
         subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1.МетодИнициализациHandlerCallBack();
// TODO: 04.03.2022
         Log.d(this.getClass().getName(), "  subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1 " +
                 "" + subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    // TODO: 12.03.2022
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try{
// TODO: 14.03.2022
            viewДляПервойКнопкиHome_Задания = inflater.inflate(R.layout.activity_main_fragment1_for_tasks, container, false);
            Log.d(this.getClass().getName(), " onCreateView  viewДляПервойКнопкиHome_Задания  Fragment1_One_Tasks  onCreateView " + "" + viewДляПервойКнопкиHome_Задания);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return viewДляПервойКнопкиHome_Задания;
    }


    @Override
    public void onStart() {
        super.onStart();
        try{
               // TODO: 09.10.2024 Public ID
                    Integer getPublicID = new GetttingPublicID().getttingPublicID(getContext());
            Курсор_ГлавныйКурсорДляЗадач=        subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1.МетодПолучаемГлавныеДанныеДляЗадач(ПубличныйIDДляФрагмента);
            Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента+ " Курсор_ГлавныйКурсорДляЗадач " +Курсор_ГлавныйКурсорДляЗадач);
            Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе=    subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1.МетодПолученимТОлькоКоличествоЗадач(ПубличныйIDДляФрагмента);
            Log.d(this.getClass().getName(), "Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 12.03.2022  метод с бизнес логикой
    @Override
    public void onResume() {
        super.onResume();
        try {
            Log.d(this.getClass().getName(), "Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе+ "   + Курсор_ГлавныйКурсорДляЗадач "
                    + Курсор_ГлавныйКурсорДляЗадач);
            // TODO: 05.03.2022  ДЛЯ ИНИЗАЛИЗАЦИИ НИЖНИХ КНОПОК
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1.МетодСозданиеНавигаторКнопок();
            // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1.МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
            //todo метод  ИНИЦИАЛИЗАЦИИ RECYCLEVIEW ДЛЯ АКТИВТИ ЗАДАЧИ
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1.МетодЗаполенияRecycleViewДляЗадач(viewДляПервойКнопкиHome_Задания);

            // TODO: инициализация слушателя recyreView
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1.МетодСлушательObserverДляRecycleView();


        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///


        }
    }

    // TODO: 10.03.2022 БИЗНЕС-КОД ПЕРЕНЕСЕН ИЗ АКТИВТИ


    @Override
    public void onStop() {
        super.onStop();
        try {
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 onDestroyView  " );
            handlerTaskFragment1.removeCallbacksAndMessages(null);
            WorkManager.getInstance(getContext()).getWorkInfosForUniqueWorkLiveData(ИмяСлужбыWorkmanagerОдноразовая).removeObserver(null);
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  ИмяСлужбыСинхронизацииДляЗадачиИзЧата  " +
                    "" + ИмяСлужбыWorkmanagerОдноразовая);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 02.03.2022

    // TODO: 28.02.2022 бизнес -логика    для активти

    private class SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 {
        // TODO: 28.02.2022
        Context context;
        // TODO: 14.03.2022
        Activity activity;

        public SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1(Context context, Activity activity) {
            // TODO: 14.03.2022
            this.context = context;
            // TODO: 14.03.2022
            this.activity = activity;
            Log.d(this.getClass().getName(), "  public SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1(Context context, Activity activity)   " + context);
        }

        // TODO: 14.03.2022

        protected  Cursor МетодПолученимТОлькоКоличествоЗадач(@NonNull  Integer ПубличноеIDПолученныйИзСервлетаДляUUID) {
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе = null;
            // TODO: 02.03.2022
            try {
                if (context!=null) {
                    // TODO: 15.05.2025
                    String getCurrentTabel="view_tasks";
                    ModuleQuety moduleQuety=new ModuleQuety(context);
                    Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе= moduleQuety.getModuleQuery(getCurrentTabel," SELECT *  FROM "+getCurrentTabel+" AS D" +
                            "  WHERE D.id_user="+ПубличноеIDПолученныйИзСервлетаДляUUID+" AND  D.status_write="+0+" "+
                            "   ORDER BY  D.status_write,  D.date_update DESC   " ,null);

                    Log.d(this.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + " Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " +Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
                    // TODO: 02.03.2022
                    if (Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount() > 0) {
                        // TODO: 03.03.2022
                        Log.d(this.getClass().getName(), "Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
                        // TODO: 03.03.2022
                        Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.moveToFirst();
                    }
                }
                // TODO: 14.03.2022
                Log.d(this.getClass().getName(), "Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }
            return Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе;
        }














        // TODO: 28.02.2022 Под Класс порлучение данных для активти
        Cursor МетодПолучаемГлавныеДанныеДляЗадач(Integer ПубличноеIDПолученныйИзСервлетаДляUUID) {
            try {
                // TODO: 15.05.202
                String Текущаятаблицы="view_tasks";
                ModuleQuety moduleQuety=new ModuleQuety(getContext());
                Курсор_ГлавныйКурсорДляЗадач= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT *  FROM "+Текущаятаблицы+" AS D" +
                        "  WHERE D.id_user='"+ПубличноеIDПолученныйИзСервлетаДляUUID+"' AND  D.message IS NOT NULL "+
                        "   ORDER BY D.status_write, D.date_update DESC ,D.id DESC " ,null);

                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " Курсор_ГлавныйКурсорДляЗадач " +Курсор_ГлавныйКурсорДляЗадач);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return Курсор_ГлавныйКурсорДляЗадач;
        }




        // TODO: 04.03.2022  класс в котором находяться слушатели


        void МетодСлушательObserverДляRecycleView() {
            // TODO: 04.03.2022
            try {
                // TODO: 23.03.2022
                if (myRecycleViewAdapter!=null) {
                    myRecycleViewAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                        @Override
                        public void onChanged() {
                            super.onChanged();
                            // TODO: 02.03.2022
                            Log.d(this.getClass().getName(), "onChanged ");
                            // TODO: 13.03.2022
                            try {
                        /*    onStart();

                            onResume();*/
                                Log.d(this.getClass().getName(), "onChanged adapterDataObserverObserverСлушатель = new RecyclerView.AdapterDataObserver() { " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
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
                        @Override
                        public void onItemRangeChanged(int positionStart, int itemCount) {
                            super.onItemRangeChanged(positionStart, itemCount);
                            // TODO: 02.03.202 2
                            try{
                                onStart();
                                onResume();
                                Log.d(this.getClass().getName(), "   public void onItemRangeChanged(int positionStart, int itemCount) {  " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
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

                            Log.d(this.getClass().getName(), "onItemRangeMoved  Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
                        }
                    });
                }

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

        // TODO: 04.03.2022 второй метод слушатель для курсора

        // TODO: 18.10.2021  СИНХРОНИАЗЦИЯ ЧАТА ПО РАСПИСАНИЮ ЧАТ
        void МетодСоздаенияСлушателяДляЧатаWorkMAnager() throws ExecutionException, InterruptedException {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            try {
                WorkManager.getInstance(getContext())
                        .getWorkInfosForUniqueWorkLiveData(ИмяСлужбыWorkmanagerОдноразовая)
                        .observe(getActivity().getSupportFragmentManager().getFragments().get(0), new Observer<List<WorkInfo>>() {
                            @Override
                            public void onChanged(List<WorkInfo> workInfosОдноразовая) {
                                // TODO: 03.08.2022 методы работы work manager
                                МетодРаботаWorkManagerНачалась(workInfosОдноразовая);
                                МетодРаботаWorkManagerКонец(workInfosОдноразовая);
                                МетодУспешнойРаботыWorkManager(workInfosОдноразовая);
                            }
                        });
                // TODO: 29.09.2021  конец синхрониазции по раписанию
                Log.d(this.getClass().getName(), "  WorkManager.getInstance(getContext())\n" +
                        "                        .getWorkInfosForUniqueWorkLiveData(ИмяСлужбыWorkmanagerОдноразовая).observe(getActivity().getSupportFragmentManager().getFragments().get(0)," +
                        " " + " ИмяСлужбыСинхронизацииДляЗадачиИзЧата " + ИмяСлужбыWorkmanagerОдноразовая);
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 18.10.2021  СИНХРОНИАЗЦИЯ ЧАТА ПО РАСПИСАНИЮ ЧАТ
        void МетодСоздаенияСлушателяДляЧатаWorkMAnagerОбщщийДополнительный() throws ExecutionException, InterruptedException {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            try {
                // TODO: 16.12.2021  --ОДНОРАЗОВАЯ СИНХРОНИАЗЦИЯ СЛУШАТЕЛЬ
                WorkManager.getInstance(getContext())
                        .getWorkInfosForUniqueWorkLiveData(ИмяСлужбыWorkmanagerОБЩАЯ).observe(getActivity().getSupportFragmentManager().getFragments().get(0), new Observer<List<WorkInfo>>() {
                            @Override
                            public void onChanged(List<WorkInfo> workInfoДополнительнаОБЩАЯ) {
                                // TODO: 23.12.2021  методы работы  со статусом work manager ОБЩЕГО
                                МетодРаботаWorkManagerНачалась(workInfoДополнительнаОБЩАЯ);
                                МетодРаботаWorkManagerКонец(workInfoДополнительнаОБЩАЯ);
                                МетодУспешнойРаботыОБЩЕГОWorkManager(workInfoДополнительнаОБЩАЯ);
                            }
                        });
                // TODO: 29.09.2021  конец синхрониазции по раписанию
                Log.d(this.getClass().getName(), "       WorkManager.getInstance(getContext())\n" +
                        "                        .getWorkInfosForUniqueWorkLiveData(ИмяСлужбыWorkmanagerОБЩАЯ).observe(getActivity().getSupportFragmentManager().getFragments().get(0), new Observer<List<WorkInfo>>() {" +
                        "  СтастусWorkMangerЧата " + " ИмяСлужбыСинхронизацииДляЗадачиИзЧата " + ИмяСлужбыWorkmanagerОдноразовая);
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



        // TODO: 03.08.2022  метод успешной работы work amanger
        private void МетодУспешнойРаботыWorkManager(List<WorkInfo> workInfos) {
            // TODO: 03.08.2022 успешный ответ
            workInfos.stream().filter(СтастусWorkMangerДляФрагментаЧитатьИПисать ->
                     СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0)
             .forEachOrdered((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                 // TODO: 18.02.2022
                     Long  CallBaskОтWorkManagerОдноразового = СтастусWorkMangerДляФрагментаЧитатьИПисать
                             .getOutputData().getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая",0l);
                     if (CallBaskОтWorkManagerОдноразового>0) {
                         // TODO: 14.01.2022
                         Message message = new Message();
                         Bundle bundle=new Bundle();
                         bundle.putString("ОперациЯПрошлаЧат","Успешный обмен данными !!!");
                         message.setData(bundle);
                         handlerTaskFragment1.sendMessage(message);
                     }
             });
        }
        // TODO: 03.08.2022  метод успешной работы  ОБЩЕЙ work amanger
        private void МетодУспешнойРаботыОБЩЕГОWorkManager(List<WorkInfo> workInfos) {
            // TODO: 03.08.2022 успешный ответ
            workInfos.stream().filter(СтастусWorkMangerДляФрагментаЧитатьИПисать ->
                            СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0
                    ||       СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.ENQUEUED) == 0)
                    .forEachOrdered((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                        // TODO: 18.02.2022
                        Integer  CallBaskОтWorkManagerОБЩАЯ= СтастусWorkMangerДляФрагментаЧитатьИПисать
                                .getOutputData().getInt("ReturnPublicAsyncWorkMananger",0);
                        // TODO: 14.01.2022
                        Message message = new Message();
                        Bundle bundle=new Bundle();
                        bundle.putString("ОперациЯПрошлаЧат","Успешный обмен данными !!!");
                        message.setData(bundle);
                        handlerTaskFragment1.sendMessage(message);
                    });
        }
        // TODO: 03.08.2022  метод начала работы WorkManger
        private void МетодРаботаWorkManagerНачалась(List<WorkInfo> workInfos) {
            // TODO: 03.08.2022 успешный ответ
            workInfos.stream().filter(СтастусWorkMangerДляФрагментаЧитатьИПисать ->
                            СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.RUNNING) == 0)
                    .forEachOrdered((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                        // TODO: 18.02.2022
                        handlerTaskFragment1.post(()->{
                            progressBarTaskFragment1.setVisibility(View.VISIBLE);
                        });
                    });
        }

        // TODO: 03.08.2022  метод конец работы WorkManger
        private void МетодРаботаWorkManagerКонец(List<WorkInfo> workInfos) {
            // TODO: 03.08.2022 успешный ответ
            workInfos.stream().filter(СтастусWorkMangerДляФрагментаЧитатьИПисать ->
                            СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.RUNNING) != 0)
                    .forEachOrdered((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                        // TODO: 18.02.2022
                        handlerTaskFragment1.post(()->{
                            progressBarTaskFragment1.setVisibility(View.INVISIBLE);
                        });
                    });
        }



















        // TODO: 04.03.2022 прозвомжность инициализации RecycleView
        void МетодИнициализацииRecycleViewДляЗадач(@NonNull  View viewДляПервойКнопкиHome_Задания) {
            try {

                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK  " + Курсор_ГлавныйКурсорДляЗадач);
                Log.d(this.getClass().getName(), " есть данные для отображения " +
                        "отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ГлавныйКурсорДляЗадач  " + Курсор_ГлавныйКурсорДляЗадач);
                // TODO: 14.03.2022
                LinearLayoutManager    linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                // TODO: 28.02.2022 создаем наш первый RecyclerView
                DividerItemDecoration    dividerItemDecoration = new DividerItemDecoration(
                        recyclerView.getContext(),
                        DividerItemDecoration.HORIZONTAL);
                recyclerView.removeItemDecoration(dividerItemDecoration);
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setLayoutManager(linearLayoutManager);//TODO new LinearLayoutManager(getContext()) // TODO: 28.02.2022 создаем наш первый RecyclerView recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                // TODO: 03.03.2022
                Log.d(this.getClass().getName(), " есть данные для отображения " +
                        "отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ГлавныйКурсорДляЗадач  " + Курсор_ГлавныйКурсорДляЗадач);
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }




        // TODO: 04.03.2022 прозвомжность инициализации RecycleView
        void МетодЗаполенияRecycleViewДляЗадач(@NonNull  View viewДляПервойКнопкиHome_Задания) {
            try {

                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK  " + Курсор_ГлавныйКурсорДляЗадач);
                Log.d(this.getClass().getName(), " есть данные для отображения " +
                        "отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ГлавныйКурсорДляЗадач  " + Курсор_ГлавныйКурсорДляЗадач);

                myRecycleViewAdapter = new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1. MyRecycleViewAdapter(Курсор_ГлавныйКурсорДляЗадач);

                recyclerView.setAdapter(myRecycleViewAdapter);

                Log.d(this.getClass().getName(), "onItemRangeMoved  Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе +
                        " Курсор_ДляПолученияДАнныхДляЗАДАЧTASK " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
                // TODO: 14.03.2022
                if (recyclerView!=null) {
                    recyclerView.requestLayout();
                    recyclerView.forceLayout();
                }
                Log.d(this.getClass().getName(), "onItemRangeMoved  Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
                if (bottomNavigationViewДляTasks!=null) {
                    bottomNavigationViewДляTasks.requestLayout();
                    bottomNavigationViewДляTasks.forceLayout();
                }
                if (linearLayou!=null) {
                    linearLayou.requestLayout();
                    linearLayou.forceLayout();
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







        // TODO: 05.03.2022 метод создание кнопок снизу навигатор
        void МетодСозданиеНавигаторКнопок() {
            try {
                // TODO: 05.03.2022
                bottomNavigationViewДляTasks.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        // TODO: 09.03.2022
                        try {

                            // TODO: 09.03.2022
                            fragmentTransactionляЗадачи = fragmentManagerДляЗадачи.beginTransaction();
                            // TODO: 11.03.2022
                            // TODO: 11.03.2022
                            Log.d(this.getClass().getName(), "  bottomNavigationViewДляTasks " + bottomNavigationViewДляTasks + " fragment_ТекущийФрагмент " + fragment_ТекущийФрагмент);
                            // TODO: 09.03.2022 вешаем слушатель на конткеноую кнопку
                            Log.d(this.getClass().getName(), "  item.getItemId() " + item.getItemId());
                            // TODO: 09.03.2022
                            switch (item.getItemId()) {
                                // TODO: 14.03.2022
                                case R.id.id_taskHome:
                                    // TODO: 22.12.2021  запускам втнутерий класс по созданию бизнес логики для даннго активти
                                    Log.d(this.getClass().getName(), " R.id.id_taskHome отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  fragmentTransactionляЗадачи  "
                                            + fragmentTransactionляЗадачи + " R.id.id_taskHome  item.getItemId() " + item.getItemId());
                                    // TODO: 10.03.2022
                                    item.setChecked(true);
                                    fragment_ТекущийФрагмент = new Fragment2_Create_Tasks();
                                    fragmentTransactionляЗадачи.replace(R.id.activity_main_fisrt_for_tasks, fragment_ТекущийФрагмент);//.layout.activity_for_fragemtb_history_tasks
                                    //fragmentTransactionляЗадачи.addToBackStack(null);
                                    fragmentTransactionляЗадачи.commit();
                                    fragmentManagerДляЗадачи.executePendingTransactions();
                                    fragmentTransactionляЗадачи.show(fragment_ТекущийФрагмент);
                                    Log.d(this.getClass().getName(), " fragmentTransactionляЗадачи " + fragmentTransactionляЗадачи);
                                    //Fragment f = fragmentManagerДляЗадачи.findFragmentById(R.id.activity_fragment1_for_tasks);
                                   /* Fragment f = new Fragment1_One_Tasks();
                                    // TODO: 02.08.2022 test code
                                    fragmentTransactionляЗадачи.remove(f).commit();*/
                                    // TODO: 10.03.2022
                                    Log.d(this.getClass().getName(), " bottomNavigationViewДляTasks.getChildCount() " + bottomNavigationViewДляTasks.getChildCount());
                                    return false;
                                // TODO: 09.03.2022////
                                case R.id.id_taskAsyns :
                                    // TODO: 19.02.2022
                                    // TODO: 10.03.2022
                                    item.setChecked(true);
                                    if (!        WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыWorkmanagerОдноразовая ).get().isEmpty()) {
                                        WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                                WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыWorkmanagerОдноразовая ).get().get(0);
                                        if (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING)!=0) {

                                            // TODO: 01.08.2022

                                            Log.d(this.getClass().getName(), " ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента);

                                        }
                                    }
                                    return false;
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

        // TODO: 14.03.2022

        private void МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(@NonNull Cursor cursorДЛяОпределенияНужноПоказыватьЗначеиЛИнЕТ)
                throws ExecutionException, InterruptedException {
            // TODO: 02.03.2022
            try {
                // TODO: 02.03.2022
                Log.d(this.getClass().getName(), "  cursorДЛяОпределенияНужноПоказыватьЗначеиЛИнЕТ " + cursorДЛяОпределенияНужноПоказыватьЗначеиЛИнЕТ);
                // TODO: 09.03.2022
                if (cursorДЛяОпределенияНужноПоказыватьЗначеиЛИнЕТ.getCount() > 0) {
                    // TODO: 06.03.2022
                    bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskHome).setVisible(true);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    // TODO: 06.03.2022
                    bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskHome).setNumber(Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    // TODO: 09.03.2022
                    bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskHome).setBackgroundColor(Color.RED);
                    // TODO: 06.03.2022
                    Log.d(this.getClass().getName(), "  bottomNavigationViewДляTasks " + bottomNavigationViewДляTasks +
                            "  Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount()   " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount());
                    // TODO: 05.03.2022
                } else {
                    // TODO: 06.03.2022
                    bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskHome).setVisible(false);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    // TODO: 09.03.2022
                    bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskHome).setNumber(Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount());
                    // TODO: 10.03.2022
                    bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskHome).setBackgroundColor(Color.BLACK);
                }
                Log.d(this.getClass().getName(), "  bottomNavigationViewДляTasks " + bottomNavigationViewДляTasks +
                        "  Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount()   " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
                // TODO: 13.03.2022
                bottomNavigationViewДляTasks.requestLayout();
                // TODO: 13.03.2022
                bottomNavigationViewДляTasks.forceLayout();
                // TODO: 14.03.2022
                linearLayou.requestLayout();
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


        // TODO: 15.03.2022  перенесееный код
        // TODO: 28.02.2022 начало  MyViewHolderДляЧата
        protected class MyViewHolder extends RecyclerView.ViewHolder {// TODO: 28.02.2022 начало  MyViewHolderДляЧата
            // TODO: 28.02.2022
            private      MaterialTextView textView1ОписаниеЗадачи,  textView3, textView4СамаДата, textView5, textView6, textView7СамаЗадача,textView10;
            // TODO: 13.03.2022
            private      MaterialCardView materialCardView;
            // TODO: 29.03.2022
            // TODO: 13.03.2022



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
                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);
                    // TODO: 02.03.2022
                    textView1ОписаниеЗадачи = (MaterialTextView) itemView.findViewById(R.id.text1_innercardview);
                    // TODO: 02.3.2022  дополнительный
                    ///  textView2 = (TextView) itemView.findViewById(R.id.text2_innercardviewtwo);
                    // TODO: 28.02.2022
                    textView3 = (MaterialTextView) itemView.findViewById(R.id.text3_innercardviewtree);
                    // TODO: 28.02.2022
                    textView4СамаДата = (MaterialTextView) itemView.findViewById(R.id.text4_innercardviewfour);
                    // TODO: 28.02.2022
                    textView5 = (MaterialTextView) itemView.findViewById(R.id.text5_innercardviewtype_tasks);
                    // TODO: 13.03.2022
                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 materialCardView  textView2 " + textView4СамаДата);
                    // TODO: 01.03.2022
                    materialCardView = (MaterialCardView) itemView.findViewById(R.id.cardviewmatirealtask);
                    // TODO: 13.03.2022
                    textView6 = (MaterialTextView) itemView.findViewById(R.id.text9_innercardview_callsbaks);
                    // TODO: 30.03.2022

                    // TODO: 31.03.2022
                    textView7СамаЗадача = (MaterialTextView) itemView.findViewById(R.id.text0_innercardview_headmessage);
                    // TODO: 31.03.2022

// TODO: 07.06.2022 статус задачи
                    // TODO: 31.03.2022
                    textView10 = (MaterialTextView) itemView.findViewById(R.id.text2_inner_staus_value_runnigmy);


                    // TODO: 30.03.2022
                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 materialCardView   " + materialCardView);
                    // TODO: 01.03.2022*/
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
        // TODO: 28.02.2022 конец  MyViewHolderДляЧата


        // TODO: 28.02.2022 ViewHolder


        class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
            // TODO: 04.03.2022
            Cursor Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри;

            // TODO: 15.03.2022

            public MyRecycleViewAdapter(@NotNull  Cursor Курсор_ДляПолученияДАнныхДляЗАДАЧTASK) {
                // super();

                // TODO: 04.03.2022
                this.Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри = Курсор_ДляПолученияДАнныхДляЗАДАЧTASK;

                // TODO: 29.03.2022
                if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASK.getCount() > 0) {
                    // TODO: 04.03.2022
                    Log.i(this.getClass().getName(), "   myRecycleViewAdapter   Курсор_ДляПолученияДАнныхДляЗАДАЧTASK.getCount()>" + Курсор_ДляПолученияДАнныхДляЗАДАЧTASK.getCount());
                }
                Log.i(this.getClass().getName(), "     getItemId holder.position ");
            }


            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
                // TODO: 30.03.2022
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position);
                // TODO: 02.03.2022 тут РАЗДАЕМ ДАННЫЕ RECYCLERBIEW

                try {


                    if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount() > 0) {
                        // TODO: 30.03.2022
                        Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.moveToFirst();




     /*  recyclerView.removeAllViews();

       recyclerView.getRecycledViewPool().clear();*/
                        // TODO: 30.03.2022
                        //   Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.moveToNext();
                        Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.moveToPosition(position);

                    }


                    // TODO: 30.03.2022

                    recyclerView.requestLayout();
                    // TODO: 30.03.2022
                    recyclerView.requestFocus();



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
                View viewГлавныйВидДляRecyclleViewДляЗаданий = null;
                try {

                    if (Курсор_ГлавныйКурсорДляЗадач.getCount()>0) {
                        // TODO: 28.02.2022
                        viewГлавныйВидДляRecyclleViewДляЗаданий = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_takst_cardview1, parent, false);//todo old simple_for_takst_cardview1
                        // TODO: 05.03.2022
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляЗаданий" + viewГлавныйВидДляRecyclleViewДляЗаданий);
                        // TODO: 28.02.2022
                    }else{

                        // TODO: 28.02.2022
                        viewГлавныйВидДляRecyclleViewДляЗаданий = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_takst_cardview31_isempty, parent, false);//todo old simple_for_takst_cardview1
                        // TODO: 05.03.2022
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляЗаданий" + viewГлавныйВидДляRecyclleViewДляЗаданий);
                        // TODO: 28.02.2022

                        viewГлавныйВидДляRecyclleViewДляЗаданий.setClickable(false);
                        viewГлавныйВидДляRecyclleViewДляЗаданий.setEnabled(false);
                    }


                    // TODO: 22.03.2022
                    myViewHolder = new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1.MyViewHolder(viewГлавныйВидДляRecyclleViewДляЗаданий);

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
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }
                return myViewHolder;

            }

            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                // TODO: 28.02.2022 привазяваем данные из колекции пряме на наш recycreview
                try {
                    // TODO: 14.03.2022 метод офорленя recycreview

                      /*  // TODO: 02.03.2022 тут РАЗДАЕМ ДАННЫЕ RECYCLERBIEW


                        Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.moveToNext();*/

                    /*   Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.move(position);//todo old  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK.move(position);*/
                    // TODO: 04.03.2022 p==osion
                    Log.i(this.getClass().getName(), "  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK.getPosition() " + Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getPosition());


                    // TODO: 14.03.2022  метод создания само сообщения
                    МетодБиндингаСозданиеСамоСообщения(holder);


                    // TODO: 14.03.2022  метод создания номер задания
                    МетодБиндингаНомерЗадания(holder);


                    // TODO: 14.03.2022  метод создания дата задания
                    МетодБиндингаДатаЗадания(holder);


                    // TODO: 14.03.2022  метод создания ФИО задания
                    МетодБиндингаФИОДляЗадания(holder);


                    // TODO: 13.03.2022 СЛУШАТЕЛЬ СРАБАТЫВАЕТ КОГДА КОМАДА TOGGER И МЕНЯЕМ СТАТУСТ ЧЕК ОЗНАКОМЛЕНЫЙ ЛИ ИНЕТ

                    МетодБиндингаСлушательisChered(holder);


                    // TODO: 13.03.2022 СЛУШАТЕЛЬ ТРЕТИТЬ ПРОСТОЙ СЛУШАТИЕЛЬ ПРОСТО КЛИК ПО CARD VIEW

                    МетодБиндингаСлушателейТретьийСлушательПростоКликДляCard(holder);


                    // TODO: 03.03.2022 ПОЛУЧАЕМ СТАТУС ЗАДАНИЯ ПРОЧИТАН ИЛИ НЕТ

                    Integer СамСтатусПрочтенияИлиНет = МетодБиндингаПолученияСтатусаЗадачи(holder);


                    // TODO: 02.03.2022#5  получаем ТИП ЗАДАЧИ
                    МетодБиндингПолучаемТипЗадания(holder);


                    // TODO: 02.03.2022#5  заполем ДАННЫМИ BUNGLE САМОЙ ЗАДАЧИ//

                    МетодБиндингаЗаполненияДаннымиBungle(holder, СамСтатусПрочтенияИлиНет);

                    Log.i(this.getClass().getName(), "      holder.textView1  accessibilityNodeInfo " + " СамСтатусПрочтенияИлиНет " + СамСтатусПрочтенияИлиНет);



                    // TODO: 02.03.2022#5 создание Для заполнения Примечание

                    МетодБиндингаПримечаниеСамогоСообщенияCallBask(holder);

                    // TODO: 31.03.2022  метод для создание Шабка Задачи

                    МетодБиндингаШабкаЗадачи(holder);


                    // TODO: 07.06.2022  статус задачи которую надо выполнить
                    МетодБиндингаСтатусЗадачиКоторуНадоВЫполнить(holder);


// TODO: 29.07.2022  анимайия для ecyrview

                    МетодАнимацииRecycreView(holder);





                    // TODO: 13.03.2022 настройки для carview

                    holder.materialCardView.toggle();

                    // TODO: 13.03.2022
                    holder.materialCardView.setChecked(true);

                    // TODO: 30.03.2022


// TODO: 28.02.2022

                    // TODO: 09.03.2022

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


            private void МетодБиндингаЗаполненияДаннымиBungle(@NonNull MyViewHolder holder, Integer СамСтатусПрочтенияИлиНет) {
                // TODO: 03.03.2022
                try {

                    if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0) {
                        Integer ИндексUUIDДЛяЗАДАНИЯКотореВыбрали = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("uuid");///"uuid_notifications"
                        // TODO: 02.03.2022 получет UUID строчки

                        if (ИндексUUIDДЛяЗАДАНИЯКотореВыбрали!=null) {
                            //TODO

                            Long UUIDДЛяЗАДАНИЯКотореВыбрали = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getLong(ИндексUUIDДЛяЗАДАНИЯКотореВыбрали);

                            Log.i(this.getClass().getName(), "  UUIDДЛяЗАДАНИЯКотореВыбрали " + UUIDДЛяЗАДАНИЯКотореВыбрали);

                            // TODO: 13.03.2022


                            Log.i(this.getClass().getName(), "  holder.getAdapterPosition() " + holder.getAdapterPosition());

                            // TODO: 14.03.2022  заполем данными для получение  UUID вышке
                            BungleДанныеДляViewCard.putLong(String.valueOf(holder.getAdapterPosition()), UUIDДЛяЗАДАНИЯКотореВыбрали);


                            Log.i(this.getClass().getName(), "  BungleДанныеДляViewCard   " + BungleДанныеДляViewCard.getBundle(String.valueOf(holder.materialCardView.getId())));

                            // TODO: 13.03.2022  передаем статус задачи


                            // TODO: 03.03.2022 передаем помер позиции position
                            holder.materialCardView.setTag(holder.materialCardView.getId(), СамСтатусПрочтенияИлиНет);


                            // TODO: 01.04.2022  дополнительнео создани  данных по текущей строчке

                            Integer ИндексСамогоПримечаниезадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("callsback_note_task");
                            // TODO: 02.03.2022
                            String СамогоПримечанияЗАДАНИЯ = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getString(ИндексСамогоПримечаниезадачи);


                            // TODO: 01.04.2022 дополнительные данные сощдаем на строчке
                            // TODO: 01.04.2022

                            BungleДанныеДляViewCardBungle.putString(String.valueOf(holder.getLayoutPosition()), СамогоПримечанияЗАДАНИЯ);

                            Log.i(this.getClass().getName(),  holder.getLayoutPosition()+ " + holder.getLayoutPosition()");


                            // TODO: 01.04.2022  допОЛНИТЕЛЬНЫЙ BUNGLE  ДЛЯ  ID

                            // TODO: 01.04.2022
                            Integer ИндексСамогоПримечаниезадачиID = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("status_write");
                            // TODO: 02.03.2022
                            Integer СамогоПримечанияЗАДАНИЯID = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getInt(ИндексСамогоПримечаниезадачиID);


                            BungleДанныеДляViewCardBungleID.putInt(String.valueOf(holder.getLayoutPosition()), СамогоПримечанияЗАДАНИЯID);

                            Log.i(this.getClass().getName(), "  accessibilityNodeInfoBundle   " + BungleДанныеДляViewCardBungleID + " holder.getLayoutPosition() " + holder.getLayoutPosition());



                            //TODO  ВЫТАСКИВАЕМ ПРИМЕЧАНИЕ ДЛЯ ПЕРЕДАЧИ В ДРУГОЙ ФОАГМЕНТ

                            Integer ИндекСамогоПрисечаниеCallBaks = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("callsback_note_task");///"uuid_notifications"
                            //TODO

                            String СамогоПрисечаниеCallBaks = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getString(ИндекСамогоПрисечаниеCallBaks);///"uuid_notifications"

                            Log.i(this.getClass().getName(), "  ИндекСамогоПрисечаниеCallBaks   " + ИндекСамогоПрисечаниеCallBaks);

                            BungleДанныеДляViewCardДляпередачиCallsBaskПримечание.putString(String.valueOf(holder.getLayoutPosition()), СамогоПрисечаниеCallBaks);



                            //TODO  ВЫТАСКИВАЕМ с кем переписка

                            Integer ИндекСамогСкемПепеписка = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("user_update");///"uuid_notifications"
                            //TODO

                            String СамогоIDОтКогоЗАдача = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getString(ИндекСамогСкемПепеписка);///"uuid_notifications"

                            Log.i(this.getClass().getName(), "  СамогоIDОтКогоЗАдача   " + СамогоIDОтКогоЗАдача);

                            BungleДанныеДляViewCardBungleСКемПереписка.putInt(String.valueOf(holder.getLayoutPosition()), Integer.parseInt(СамогоIDОтКогоЗАдача));











                        }
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
                // TODO: 03.03.2022 передаем помер позиции position
            }

            private void МетодБиндингПолучаемТипЗадания(@NonNull MyViewHolder holder) {

                try {
                    if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0) {
                        Integer ИндексСтатусТипаЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("type_tasks");
                        // TODO: 02.03.2022

                        if (ИндексСтатусТипаЗадачи!=null) {
                            //TODO
                            String СамТипЗадания = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getString(ИндексСтатусТипаЗадачи);

                            Log.i(this.getClass().getName(), "  СамТипЗадания " + СамТипЗадания);

                            holder.textView5.setText( СамТипЗадания);
                            // holder.textView5.setText("тип: " + СамТипЗадания);

                            // TODO: 16.06.2022  слушатель
                            View.OnClickListener clickListenerДляВсехЭлементовКартВью=         МетодСоздаетСлушаткльДляВсехЭлментовКартВьюФрагмент1(holder);


                            holder.textView5.setOnClickListener(clickListenerДляВсехЭлементовКартВью);

                        }
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

            @NonNull
            private Integer МетодБиндингаПолученияСтатусаЗадачи(@NonNull MyViewHolder holder) {
                // TODO: 02.03.2022#5
                Integer СамСтатусПрочтенияИлиНет = 0;
                try {

                    Integer ИндексСтатусПрочтенияИлиНЕт = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("status_write");
                    // TODO: 02.03.2022

                    if (ИндексСтатусПрочтенияИлиНЕт!=null) {
                        //TODO
                        СамСтатусПрочтенияИлиНет = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getInt(ИндексСтатусПрочтенияИлиНЕт);
                        // TODO: 03.03.2022

                        holder.textView1ОписаниеЗадачи.setTag(СамСтатусПрочтенияИлиНет);
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
                // TODO: 01.03.2022 уставнока дополнительный данныых
                return СамСтатусПрочтенияИлиНет;
            }


            private void МетодБиндингаФИОДляЗадания(@NonNull MyViewHolder holder) throws ExecutionException, InterruptedException {
                try {
                    // TODO: 02.03.2022#4  // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4
                    if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0) {
                        Integer ИндексКтоНаписалСообщение = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("user_update");
                        // TODO: 02.03.2022
                        if (ИндексКтоНаписалСообщение!=null) {
                            //TODO
                            Integer КтоНаписалСообщениеФИОдЛПосика = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getInt(ИндексКтоНаписалСообщение);
                            // TODO: 02.03.2022
                            Log.i(this.getClass().getName(), "  КтоНаписалСообщениеФИОдЛПосика " + КтоНаписалСообщениеФИОдЛПосика);
                            // TODO: 02.03.2022
                            String ФИОКотоНаписал = new String();
                            // TODO: 13.03.2022
                          Cursor sqLiteCursorПолученимНАстоящийФИО = МетодПолучениеДанныхФИОаОснованииID(КтоНаписалСообщениеФИОдЛПосика);
                            // TODO: 02.03.2022
                            Log.i(this.getClass().getName(), "  sqLiteCursorПолученимНАстоящийФИО " + sqLiteCursorПолученимНАстоящийФИО);
                            // TODO: 02.03.2022
                            if (sqLiteCursorПолученимНАстоящийФИО.getCount() > 0) {
                                // TODO: 02.03.2022
                                sqLiteCursorПолученимНАстоящийФИО.moveToFirst();
                                // TODO: 02.03.2022
                                Integer ИндексПолученогоФИО = sqLiteCursorПолученимНАстоящийФИО.getColumnIndex("name");
                                // TODO: 13.03.2022
                                ФИОКотоНаписал = sqLiteCursorПолученимНАстоящийФИО.getString(ИндексПолученогоФИО);
                                // TODO: 13.03.2022
                                // TODO: 02.03.2022
                                Log.i(this.getClass().getName(), "  ФИОКотоНаписал " + ФИОКотоНаписал);
                            }
                            // TODO: 09.03.2022
                            sqLiteCursorПолученимНАстоящийФИО.close();
                            // TODO: 02.03.2022
                            Log.i(this.getClass().getName(), "  ФИОКотоНаписал " + ФИОКотоНаписал);
                            // TODO: 28.02.2022

                            // TODO: 28.02.2022
                            // holder.textView3.setText("от: " + ФИОКотоНаписал.trim());
                            holder.textView3.setText(ФИОКотоНаписал.trim());

                            holder.textView3.setTag(КтоНаписалСообщениеФИОдЛПосика);

                            View.OnClickListener clickListenerДляВсехЭлементовКартВью=         МетодСоздаетСлушаткльДляВсехЭлментовКартВьюФрагмент1(holder);


                            holder.textView3.setOnClickListener(clickListenerДляВсехЭлементовКартВью);



                        }
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

            private void МетодБиндингаДатаЗадания(@NonNull MyViewHolder holder) throws ParseException {
                try {
                    // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3
                    if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0) {
                        Integer ИндексПолучаемДатыЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("date_update");


                        if (ИндексПолучаемДатыЗадачи!=null) {
                            // TODO: 02.03.2022
                            String СамаДАтаЗадачиТекущей = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getString(ИндексПолучаемДатыЗадачи);
                            // TODO: 03.03.2022 парсинг даты
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));
                            // TODO: 13.03.2022
                            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("ru"));
                            // TODO: 13.03.2022
                            Date date = dateFormat.parse(СамаДАтаЗадачиТекущей);
                            // TODO: 13.03.2022
                            SimpleDateFormat simpleDateFormatДва = new SimpleDateFormat("dd MMMM yyyy HH:mm", new Locale("ru"));
                            // TODO: 13.03.2022
                            //   simpleDateFormatДва.applyPattern("dd-MM-yyyy HH:mm");//dd-MM-yyyy//// EEEE yyyy HH:mm  /////  dd MMMM yyyy HH:mm
                            СамаДАтаЗадачиТекущей = simpleDateFormatДва.format(date);
                            // TODO: 13.03.2022
                            Log.i(this.getClass().getName(), "  СамаДАтаЗадачиТекущей " + СамаДАтаЗадачиТекущей);
                            // TODO: 28.02.2022
                            holder.textView4СамаДата.setText( СамаДАтаЗадачиТекущей);  /// holder.textView4.setText("дата: " + СамаДАтаЗадачиТекущей);


                            // TODO: 16.06.2022  слушатель
                            View.OnClickListener clickListenerДляВсехЭлементовКартВью=         МетодСоздаетСлушаткльДляВсехЭлментовКартВьюФрагмент1(holder);


                            holder.textView4СамаДата.setOnClickListener(clickListenerДляВсехЭлементовКартВью);
                        }
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

            private void МетодБиндингаНомерЗадания(@NonNull MyViewHolder holder) {
                try {
                    // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2
                    if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0) {
                        Integer ИндексПолучаемIDЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("id");
                        // TODO: 02.03.2022
                        if (ИндексПолучаемIDЗадачи!=null) {
                            //TODO
                            Integer IDЗадачиТекущей = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getInt(ИндексПолучаемIDЗадачи);
                            // TODO: 02.03.2022
                            Log.i(this.getClass().getName(), "  IDЗадачиТекущей " + IDЗадачиТекущей);


                            // TODO: 02.03.2022#5

                            Integer ИндексСтатусПрочтенияИлиНЕт = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("status_write");
                            // TODO: 02.03.2022

                            Integer СамСтатусПрочтенияИлиНет = 0;

                            СамСтатусПрочтенияИлиНет = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getInt(ИндексСтатусПрочтенияИлиНЕт);

                            Log.i(this.getClass().getName(), "  СамСтатусПрочтенияИлиНет " + СамСтатусПрочтенияИлиНет);

                            // TODO: 29.03.2022


                        }
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

            private void МетодБиндингаСозданиеСамоСообщения(@NonNull MyViewHolder holder) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

                    if(Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0){



                        Integer ИндексСамогоСообщенияЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("head_message");//TODO old  "message")
                        //TDOO
                        if (ИндексСамогоСообщенияЗадачи!=null) {
                            // TODO: 02.03.2022
                            String СамогоСообщенияЗадачиДляПользователя = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getString(ИндексСамогоСообщенияЗадачи);
                            // TODO: 02.03.2022
                            Log.i(this.getClass().getName(), "  СамогоСообщенияЗадачиДляПользователя " + СамогоСообщенияЗадачиДляПользователя);
                            // TODO: 28.02.2022
                            holder.textView1ОписаниеЗадачи.setText(СамогоСообщенияЗадачиДляПользователя);

                            View.OnClickListener clickListenerДляВсехЭлементовКартВью=         МетодСоздаетСлушаткльДляВсехЭлментовКартВьюФрагмент1(holder);


                            holder.textView1ОписаниеЗадачи.setOnClickListener(clickListenerДляВсехЭлементовКартВью);


                        }

                    }
                    // TODO: 28.02.2022
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

            private void МетодБиндингаПримечаниеСамогоСообщенияCallBask(@NonNull MyViewHolder holder) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0) {
                        Integer ИндексСамогоПримечаниезадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("callsback_note_task");


                        if (ИндексСамогоПримечаниезадачи!= null) {
                            // TODO: 02.03.2022
                            String СамогоПримечанияЗАДАНИЯ = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getString(ИндексСамогоПримечаниезадачи);
                            // TODO: 02.03.2022
                            Log.i(this.getClass().getName(), "  СамогоСообщенияЗадачиДляПользователя " + СамогоПримечанияЗАДАНИЯ);

                            СамогоПримечанияЗАДАНИЯ=Optional.ofNullable(СамогоПримечанияЗАДАНИЯ).orElse("");
                            // TODO: 30.03.2022

                            // TODO: 28.02.2022
                            if (СамогоПримечанияЗАДАНИЯ.length()>0 ) {
                                //TODO
                                holder.textView6.setText(СамогоПримечанияЗАДАНИЯ);
                            }else {
                                //TODO
                                // TODO: 30.03.2022
                                holder.textView6.setText( "");
                                holder.textView6.setHintTextColor(Color.GRAY);
                                holder.textView6.setHint( "Нет примечания");

                            }


                            // TODO: 16.06.2022  слушатель
                            View.OnClickListener clickListenerДляВсехЭлементовКартВью=         МетодСоздаетСлушаткльДляВсехЭлментовКартВьюФрагмент1(holder);


                            holder.textView6.setOnClickListener(clickListenerДляВсехЭлементовКартВью);



                        }
                    }
                    // TODO: 28.02.2022
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

            // TODO: 31.03.2022  метод  примечаение задачи
            private void МетодБиндингаШабкаЗадачи(@NonNull MyViewHolder holder) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0) {
                        Integer ИндексСамогоШабкаЗАдачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("message");


                        // TODO: 02.03.2022
                        if (ИндексСамогоШабкаЗАдачи!=null) {
                            //TODO
                            String СамаЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getString(ИндексСамогоШабкаЗАдачи);
                            // TODO: 02.03.2022
                            Log.i(this.getClass().getName(), "  МетодБиндингаШабкаЗадачи  СамогоШабкаЗадачи " + СамаЗадачи);
                            // TODO: 28.02.2022
                            //holder.textView7.setText("описание: " + Optional.ofNullable(СамогоШабкаЗадачи).orElse(""));
                            holder.textView7СамаЗадача.setText(СамаЗадачи);


                        }
                        // TODO: 16.06.2022  слушатель
                        View.OnClickListener clickListenerДляВсехЭлементовКартВью=         МетодСоздаетСлушаткльДляВсехЭлментовКартВьюФрагмент1(holder);


                        holder.textView7СамаЗадача.setOnClickListener(clickListenerДляВсехЭлементовКартВью);
                    }

                    // TODO: 28.02.2022
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

            // TODO: 31.03.2022  метод  примечаение задачи
            private void МетодБиндингаСтатусЗадачиКоторуНадоВЫполнить(@NonNull MyViewHolder holder) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0) {
                        Integer ИндексСамогоШабкаЗАдачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("head_message");


                        Integer ИндексПолучаемIDЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("status_write");
                        // TODO: 02.03.2022
                        Integer IDЗадачиТекущей = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getInt(ИндексПолучаемIDЗадачи);
                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), "  IDЗадачиТекущей " + IDЗадачиТекущей);

                        switch (IDЗадачиТекущей){
                            case 0:
                                // TODO: 07.06.2022
                                // TODO: 28.02.2022
                                //holder.textView2.setText("#" + String.valueOf(IDЗадачиТекущей));
                                holder.textView10.setText("В процессе...");
                                holder.textView10.setTextColor(Color.BLACK);

                                break;
                            case 2:
                                // TODO: 07.06.2022
                                // TODO: 28.02.2022
                                //holder.textView2.setText("#" + String.valueOf(IDЗадачиТекущей));
                                holder.  textView10.setText("Выполнена");
                                break;
                            case 1:
                                // TODO: 07.06.2022
                                // TODO: 28.02.2022
                                //holder.textView2.setText("#" + String.valueOf(IDЗадачиТекущей));
                                holder.  textView10.setText("Отказ" );
                                break;

                            case 3:
                                // TODO: 07.06.2022
                                // TODO: 28.02.2022
                                //holder.textView2.setText("#" + String.valueOf(IDЗадачиТекущей));
                                holder.  textView10.setText("Отмененная");
                                break;


                        }
                        // TODO: 16.06.2022  слушатель
                        View.OnClickListener clickListenerДляВсехЭлементовКартВью=         МетодСоздаетСлушаткльДляВсехЭлментовКартВьюФрагмент1(holder);


                        holder.textView10.setOnClickListener(clickListenerДляВсехЭлементовКартВью);
                    }

                    // TODO: 28.02.2022
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

// TODO: 29.07.2022 метод анимации



            private void МетодАнимацииRecycreView(@NonNull MyViewHolder holder) {
                try{

         /*  //    Animation animationДляСогласования= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row);
                        Animation animationДляСогласования= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_materialcard);
                        holder.materialCardView.startAnimation(animationДляСогласования);
                        Log.d(this.getClass().getName(), "   private void МетодАнимацииRecycreView(@NonNull MyViewHolder holder) { " );*/
                    LayoutAnimationController layoutAnimationController=AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_animal_commit);
                    //  Animation animationДляСогласования= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row);
                    holder.materialCardView.setLayoutAnimation(layoutAnimationController);
                    holder.materialCardView.startLayoutAnimation();

                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }






            // TODO: 15.03.2022  третий слушатель просто клик

            private void МетодБиндингаСлушателейТретьийСлушательПростоКликДляCard(MyViewHolder holder) {
                // TODO: 01.03.2022 слушатели

                try {


                    View.OnClickListener clickListenerДляВсехЭлементовКартВью=         МетодСоздаетСлушаткльДляВсехЭлментовКартВьюФрагмент1(holder);


                    holder.materialCardView.setOnClickListener(clickListenerДляВсехЭлементовКартВью);

                    // TODO: 13.03.2022
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

            private       View.OnClickListener МетодСоздаетСлушаткльДляВсехЭлментовКартВьюФрагмент1(MyViewHolder holder) {

                View.OnClickListener clickListener=new  View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 01.03.2022
                        try{
                            ////// ВЫКЛЮЧАЕМ WORK MANAGER  ПРИ ПЕРЕХОДЕ НА ФРАГМЕНТЫ ОТКАЗА ВЫПОЛГНИТЬ
                            if (Курсор_ГлавныйКурсорДляЗадач.getCount()>0) {
                                // TODO: 01.04.2022  дополнительный Bungle для ID
                                holder.itemView.animate().rotationX(10l);//.rotationXBy(4l);
                                // TODO: 28.07.022 ам переход
                                handlerTaskFragment1.postDelayed(()->{

                                    Integer СамогоПримечанияЗАДАНИЯ_ID = BungleДанныеДляViewCardBungleID.getInt(String.valueOf(holder.getLayoutPosition()));
                                    // TODO: 01.04.2022
                                    СамогоПримечанияЗАДАНИЯ_ID = Optional.ofNullable(СамогоПримечанияЗАДАНИЯ_ID).orElse(0);
                                    СкемИдётПереписка= BungleДанныеДляViewCardBungleСКемПереписка.getInt(String.valueOf(holder.getLayoutPosition()));
                                    // TODO: 13.03.2022
                                    Log.d(this.getClass().getName(), "  СамогоПримечанияЗАДАНИЯ " + СамогоПримечанияЗАДАНИЯ_ID + " holder.getLayoutPosition() " + holder.getLayoutPosition() +
                                            " BungleДанныеДляViewCardBungleID " + BungleДанныеДляViewCardBungleID+
                                            "СкемИдётПереписка "+СкемИдётПереписка);
                                    // TODO: 30.03.2022 ЗАПУСКАЕМ ЕЩЕ НЕТ СТАТУСА ЗАДАЧИ
                                    Bundle bundleПередачаПараметровФрагментов = new Bundle();
                                    // TODO: 13.03.2022
                                    Log.d(this.getClass().getName(), "   Fragment4_Now_Views_Task_For_Complete SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1   ПозицияЭлментаVIewCardДополнительно  " +
                                            " holder.getAdapterPosition() " + holder.getAdapterPosition() + " v.getTag() " + v.getTag(holder.materialCardView.getId()));
                                    // TODO: 09.03.2022
                                    fragmentTransactionляЗадачи = fragmentManagerДляЗадачи.beginTransaction();
                                    // TODO: 23.03.2022  переходим на фрагмент для редактирования Fragment4_Now_Views_Task_For_Complet
                                    fragment_ТекущийФрагмент = new Fragment4_Now_Views_Task_For_Complete();
                                    // TODO: 11.03.2022
                                    fragmentTransactionляЗадачи.replace(R.id.activity_main_fisrt_for_tasks, fragment_ТекущийФрагмент).commit();//.layout.activity_for_fragemtb_history_tasks
                                    // TODO: 10.03.2022
                                    fragmentTransactionляЗадачи.show(fragment_ТекущийФрагмент);
                                    // TODO: 10.03.2022
                                    Log.d(this.getClass().getName(), " fragmentTransactionляЗадачи " + fragmentTransactionляЗадачи +
                                            " bundleПередачаПараметровФрагментов " + bundleПередачаПараметровФрагментов);
                                    // TODO: 13.03.2022  ПЕРЕДАЕМ ДРУГОМУ ФРАГМЕНТ
                                    // TODO: 13.03.2022
                                    Long ПолучаемUUIDТекущйПозицииВRecyreViewДляПередачиВЧетвртыйФрагмент
                                            = BungleДанныеДляViewCard.getLong((String.valueOf(holder.getAdapterPosition())), 0l);
                                    // TODO: 24.03.2022
                                    bundleПередачаПараметровФрагментов.putLong("ПередаемВЧетвертыйФрагмендляСменыСтатуса", ПолучаемUUIDТекущйПозицииВRecyreViewДляПередачиВЧетвртыйФрагмент);
                                    // TODO: 24.03.2022
                                    String ОписаниеЗадачиШабка = holder.textView1ОписаниеЗадачи.getText().toString().trim();
                                    // TODO: 24.03.2022
                                    bundleПередачаПараметровФрагментов.putString("ОписаниеЗадачиШабка", ОписаниеЗадачиШабка);
                                    // TODO: 13.03.2022  перердаем задание другому фрагменту
                                    String СамаЗадачаДляПередачи = holder.textView7СамаЗадача.getText().toString();//TODO holder.materialCardView.getId()
                                    //TODO
                                    СамаЗадачаДляПередачи=СамаЗадачаДляПередачи.replace("описание:","").trim();
                                    // TODO: 24.03.2022
                                    Log.d(this.getClass().getName(), "СамаЗадачаДляПередачи " + СамаЗадачаДляПередачи);
                                    // TODO: 24.03.2022
                                    bundleПередачаПараметровФрагментов.putString("СамаЗадачаДляПередачи", СамаЗадачаДляПередачи);
                                    // TODO: 24.03.2022
                                    String СтатусКтоНаписалДЛяПереадачиВДругойФоргмент = holder.textView3.getText().toString().trim();
                                    // TODO: 24.03.2022
                                    bundleПередачаПараметровФрагментов.putString("СтатусКтоНаписалДЛяПереадачиВДругойФоргмент", СтатусКтоНаписалДЛяПереадачиВДругойФоргмент);
                                    // TODO: 24.03.2022
                                    String СтатусПримечаниеCallsBaks = BungleДанныеДляViewCardДляпередачиCallsBaskПримечание.getString(String.valueOf(holder.getAdapterPosition()));

                                    //TODO
                                    if (СтатусПримечаниеCallsBaks.isEmpty()){
                                        //TODO
                                        СтатусПримечаниеCallsBaks="Примечания нет";
                                    }

                                    // TODO: 24.03.2022
                                    bundleПередачаПараметровФрагментов.putString("СтатусПримечаниеCallsBaks", СтатусПримечаниеCallsBaks);
                                    //TODO
                                    bundleПередачаПараметровФрагментов.putInt("ПоказыватьКнопкиИлиНетЕслиУжеБыИзмененСтатус", СамогоПримечанияЗАДАНИЯ_ID);
                                    // TODO: 10.03.2022
                                    Log.d(this.getClass().getName(), " bundleПередачаПараметровФрагментов " + bundleПередачаПараметровФрагментов +
                                            "   ПолучаемUUIDТекущйПозицииВRecyreViewДляПередачиВЧетвртыйФрагмент " + ПолучаемUUIDТекущйПозицииВRecyreViewДляПередачиВЧетвртыйФрагмент );
                                    // TODO: 13.03.2022   КОНЕЦ ПЕРЕДАЕМ ДРУГОМУ ФРАГМЕНТУ
                                    // TODO: 24.03.2022
                                    String СтатусзадачиДляПередачиВоТОрокойФрагмент2 = holder.textView10.getText().toString().trim();
                                    // TODO: 24.03.2022
                                    bundleПередачаПараметровФрагментов.putString("СтатусзадачиДляПередачиВоТОрокойФрагмент2", СтатусзадачиДляПередачиВоТОрокойФрагмент2);
                                    // TODO: 24.03.2022 ПЕРЕДАЕМ ДАТУ
                                    String ДатаСоздаенияЗадачиФрагмент2 = holder.textView4СамаДата.getText().toString().trim();
                                    // TODO: 24.03.2022
                                    bundleПередачаПараметровФрагментов.putString("ДатаСоздаенияЗадачиФрагмент2", ДатаСоздаенияЗадачиФрагмент2);
                                    // TODO: 24.03.2022
                                    bundleПередачаПараметровФрагментов.putInt("СкемИдётПереписка", Integer.parseInt(СкемИдётПереписка.toString()));
                                    // TODO: 24.03.2022  передаем тип задачи
                                    bundleПередачаПараметровФрагментов.putString("ТипЗадачиОдноразоваяИлиПоРАсписанию", holder.textView5.getText().toString());
                                    Log.i(this.getClass().getName(), "  bundleПередачаПараметровФрагментов  для перердачи в фрагмен т5  " + bundleПередачаПараметровФрагментов);
                                    // TODO: 24.03.2022   передча данных друговвова фрагмента
                                    fragment_ТекущийФрагмент.setArguments(bundleПередачаПараметровФрагментов);
                                    bottomNavigationViewДляTasks.requestLayout();
                                    recyclerView.requestLayout();
                                    recyclerView.forceLayout();
                                    recyclerView.requestFocus();
                                    linearLayou.requestLayout();

                                },500);
                            }

                            // TODO: 03.03.2022 update screewn
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
                };

                return clickListener;
            }


            // TODO: 29.03.2022  слутаеть смены статуса





            private void МетодБиндингаСлушательisChered(MyViewHolder holder) {
                // TODO: 14.03.2022

                try {
                    holder.materialCardView.setOnCheckedChangeListener(new MaterialCardView.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(MaterialCardView card, boolean isChecked) {




                            // TODO: 13.03.2022
                            int ИндексдляНепрочитанный = 0;
                            // TODO: 13.03.2022
                            // TODO: 13.03.2022
                            Drawable drawableИндексдляНепрочитанный;
                            // TODO: 13.03.2022
                            Log.d(this.getClass().getName(), " card  " + card +
                                    "  holder.getAdapterPosition() " + holder.getAdapterPosition() + " isChecked " + isChecked);

                            // TODO: 02.03.2022#5

                            if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0 ) {
                                Integer ИндексСтатусПрочтенияИлиНЕт = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("status_write");
                                // TODO: 02.03.2022
                                if (ИндексСтатусПрочтенияИлиНЕт!=null ) {
                                    //Todo
                                    Integer СамСтатусПрочтенияИлиНет = 0;


                                    СамСтатусПрочтенияИлиНет = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getInt(ИндексСтатусПрочтенияИлиНЕт);

                                    // TODO: 30.03.2022

                                    Log.d(this.getClass().getName(), " card  " + card +
                                            "  holder.getAdapterPosition() " + holder.getAdapterPosition() + " СамСтатусПрочтенияИлиНет " + СамСтатусПрочтенияИлиНет);

                                    // TODO: 30.03.2022 делаем дизай про значению какойе прочтиатное или отказаноо
                                    switch (СамСтатусПрочтенияИлиНет) {
                                        // TODO: 30.03.2022
                                        case 0:
                                            // TODO: 30.03.2022
                                            ИндексдляНепрочитанный = R.drawable.icon_dsu1_for_tasks_new1;// TODO: 30.03.2022   old 1
                                            // TODO: 30.03.2022
                                            card.setCheckedIconTint(ColorStateList.valueOf(Color.BLACK));
                                            break;

                                        case 1:

                                            // TODO: 25.03.2022
                                            ИндексдляНепрочитанный = R.drawable.icon_dsu1_fragment1_deseble_tasks;  // TODO: 30.03.2022  for 1  ЭТО ОТКАЗ  ОТ ВЫПОЛЕНИЯ

                                            // TODO: 30.03.2022
                                            card.setCheckedIconTint(ColorStateList.valueOf(Color.RED));

                                            // TODO: 30.03.2022
                                            break;

                                        case 2:
                                            // TODO: 30.03.2022
                                            ИндексдляНепрочитанный = R.drawable.icon_dsu1_fortasks_cardview_color_geeeey;// TODO: 30.03.2022   old 2 ЭТО УСПЕШНОЕ ВЫПОЛЕНИЕ
                                            // TODO: 30.03.2022
                                            // TODO: 30.03.2022
                                            card.setCheckedIconTint(ColorStateList.valueOf(Color.parseColor("#00CED1")));
                                            break;

                                        // TODO: 30.03.2022

                                        case 3:

                                            // TODO: 25.03.2022
                                            ИндексдляНепрочитанный = R.drawable.icon_dsu1_for_task_creating;// TODO: 30.03.2022   3 ЗАДАЧА БЫЛА ОТМЕНЕНА САМИМ СОЗДАТЕЛЕМ
                                            // TODO: 30.03.2022
                                            card.setCheckedIconTint(ColorStateList.valueOf(Color.parseColor("#DC143C")));
                                            break;



                                        default:
                                            // TODO: 30.03.2022

                                            Log.d(this.getClass().getName(), " card  " + card +
                                                    "  holder.getAdapterPosition() " + holder.getAdapterPosition() + " isChecked " + isChecked);

                                            break;

                                    }
                                    // TODO: 30.03.2022
                                    drawableИндексдляНепрочитанный
                                            = getContext().getDrawable(ИндексдляНепрочитанный);
                                    // TODO: 13.03.2022
                                    card.setCheckedIcon(drawableИндексдляНепрочитанный);
                                    // TODO: 13.03.2022
                                    card.setCheckedIconResource(ИндексдляНепрочитанный);

                                    // TODO: 13.03.2022
                                    card.setSelected(true);
                                    // TODO: 30.03.2022
                                    card.requestFocus();
                                    // TODO: 30.03.2022
                                    card.requestLayout();
                                    // TODO: 30.03.2022
                                    card.invalidate();
                                    // TODO: 30.03.2022
                                    // TODO: 30.03.2022
                                    recyclerView.requestLayout();
                                    // TODO: 30.03.2022
                                    recyclerView.invalidate();
                                }
                            }

                            // TODO: 13.03.2022





                        }
                    });






                    // TODO: 13.03.2022
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
            // TODO: 04.03.2022


            @Override
            public long getItemId(int position) {
                // TODO: 04.03.2022

                Log.i(this.getClass().getName(), "     getItemId holder.position " + position);

                return super.getItemId(position);

            }

            private Cursor МетодПолучениеДанныхФИОаОснованииID(Integer КтоНаписалСообщениеФИОдЛПосика){
                // TODO: 16.11.2021 find FIO
                Cursor Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал = null;
                try {
                    // TODO: 15.05.202
                    String Текущаятаблицы="chat_users";
                    ModuleQuety moduleQuety=new ModuleQuety(getContext());
                    Курсор_ГлавныйКурсорДляЗадач= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT D.name  FROM "+Текущаятаблицы+" AS D" +
                            "  WHERE D._id='"+КтоНаписалСообщениеФИОдЛПосика+"'   AND D.name IS NOT NULL " ,null);

                    Log.d(this.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + " Курсор_ГлавныйКурсорДляЗадач " +Курсор_ГлавныйКурсорДляЗадач);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                // TODO: 02.03.2022
                return Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал;
            }



            @Override
            public int getItemCount() {
                Integer КоличествоСтрокВЗАдачах;
                if (  Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0) {
                    КоличествоСтрокВЗАдачах=   Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount();
                }else {
                    КоличествоСтрокВЗАдачах=1;
                }
                Log.d(this.getClass().getName(), "КоличествоСтрокВЗАдачах " + КоличествоСтрокВЗАдачах);
                // TODO: 28.02.2022
                return КоличествоСтрокВЗАдачах;
            }
        }//TODO  конец два
        // TODO: 31.03.2022  метод  примечаение задачи


        // TODO: 29.03.2022  метод регмстарцмии локального брод кастера доля смен задачи

        //TODO метод делает callback с ответом на экран
        private  void  МетодИнициализациHandlerCallBack(){
            try{
                Handler callback = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        Log.d(this.getClass().getName(), " " +
                                " SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1   ПозицияЭлментаVIewCardДополнительно  СтатусПрочтеаУжеЗадачаИлиНет " +
                                msg + " msg.getWhen() " + msg.what);
                        // TODO: 19.02.2022
                        //recyclerView.getAdapter().notifyDataSetChanged();
                        recyclerView.getAdapter().notifyItemRangeChanged(0,Курсор_ГлавныйКурсорДляЗадач.getCount());
                        //TODO
                        recyclerView.requestLayout();
                        recyclerView.forceLayout();
                        bottomNavigationViewДляTasks.forceLayout();
                        linearLayou.requestLayout();
                        // TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК ВОРК МЕНЕДЖЕР
                        Bundle bundle=      msg.getData();
                        String ОперацияДанныВЧате=bundle.getString("ОперациЯПрошлаЧат","");
                        msg.getTarget().removeMessages(1);
                    }
                    @Override
                    public void dispatchMessage(@NonNull Message msg) {
                        super.dispatchMessage(msg);
                    }

                    @NonNull
                    @Override
                    public String getMessageName(@NonNull Message message) {
                        return super.getMessageName(message);
                    }
                    @Override
                    public boolean sendMessageAtTime(@NonNull Message msg, long uptimeMillis) {
                        return super.sendMessageAtTime(msg, uptimeMillis);
                    }
                };
                handlerTaskFragment1=  callback;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 02.08.2022  metod launch bing async
    }   // TODO: 28.02.2022 конец класса бизнес логики   // TODO: 28.02.2022 конец класса бизнес логики

    // TODO: 02.03.2022














    // TODO: 12.06.2022 set and get

    // TODO: 28.02.2022 бизнес -логика    для активти

    public String getИмяСлужбыWorkmanagerОдноразовая() {
        return ИмяСлужбыWorkmanagerОдноразовая;
    }

    public String getИмяСлужбыWorkmanagerДляВсехЗадачОбщаяДопольнительная() {
        return ИмяСлужбыWorkmanagerОдноразовая ;
    }





    public BottomNavigationView getBottomNavigationViewДляTasks() {
        return bottomNavigationViewДляTasks;
    }

    @SuppressLint("RestrictedApi")
    public BottomNavigationItemView getBottomNavigationКонкретноКнопкаДобавить() {
        return bottomNavigationКонкретноКнопкаДобавить;
    }

    @SuppressLint("RestrictedApi")
    public BottomNavigationItemView getBottomNavigationКонкретноКнопкаКонтролируемыеЗадачи() {
        return bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи;
    }

    public TextView getTextViewТекущаяЗадача() {
        return textViewТекущаяЗадача;
    }

    public Integer getПубличныйIDДляФрагмента() {
        return ПубличныйIDДляФрагмента;
    }

    public LinearLayout getLinearLayou() {
        return linearLayou;
    }







}    // TODO: 28.02.2022 конец класса бизнес логики для фрагмента 1






























