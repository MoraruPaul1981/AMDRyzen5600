package com.dsy.dsu.TasksApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForTasks;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.sous.backasync.launch.ModuleQuety;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;


public class Fragment4_Now_Views_Task_For_Complete extends Fragment {
    // TODO: 15.03.2022
    private RecyclerView recyclerView;
    private   AccessibilityNodeInfo accessibilityNodeInfoДополнительныеДанные;
    private  AccessibilityNodeInfo accessibilityNodeInfoДляПередачиДанных;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент4 subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент4ЗаполенияЗадачиДляСозданияНовойЗадачи;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент4.MyRecycleViewAdapter myRecycleViewAdapter;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент4.MyViewHolder myViewHolder;
    private   Bundle bundleПередачаДанныхЧерезФрагменты;
    private WorkInfo WorkInfoИнформацияОЗапущенойСлужбеОдноразовая;
    private String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";
    // TODO: 01.03.2022--перпменные для переноса в другие ФОАГМЕНТЫ1,2,3,4,5
    private View viewДляПервойКнопкиHome_Задания = null;
    private Bundle BungleДанныеДляViewCard;
    private Bundle BungleДанныеДляViewCardBungle;
    private Bundle BungleДанныеДляViewCardBungleID;
    private Bundle BungleДанныеДляViewCardДляпередачиCallsBaskПримечание;
    private  Cursor Курсор_ГлавныйКурсорДляЗадач;
    private  Cursor Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе = null;
    private Handler handlerTaskFragment4;
    private ProgressBar progressBarTaskFragment4;
    
    private FragmentManager fragmentManagerДляЗадачи;
    private FragmentTransaction fragmentTransactionляЗадачи;
    private Fragment fragment_ТекущийФрагмент;

    // TODO: 27.06.2022
    private TextView textViewТекущаяЗадача;
    @SuppressLint("RestrictedApi")
    private BottomNavigationView bottomNavigationViewДляTasks;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationКонкретноКнопкаДобавить;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи;
    private  Integer ПубличныйIDДляФрагмента;
    private LinearLayout linearLayou;
    private  Context context;
    private  Integer СкемИдётПереписка;
    // TODO: 28.06.2022
    private ServiceForTasks service_for_tasks;
    @SuppressLint("RestrictedApi")
   private BottomNavigationItemView bottomNavigationПринудительныйОбмен;
    // TODO: 05.07.2022



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            // TODO: 16.04.2025
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {

            context=getContext();
            linearLayou = (LinearLayout) getActivity().findViewById(R.id.activity_main_fisrt_for_tasks);

            fragmentManagerДляЗадачи = getActivity().getSupportFragmentManager();

              recyclerView= (RecyclerView) view.findViewById(R.id.recycleviewActiviTask);
            // TODO: 14.03.2022 ССЫЛКА НА РОДИТЕЛЬСКОЕ ФРАГМЕНТ/

            textViewТекущаяЗадача = (TextView) view.findViewById(R.id.activy_task_fragment1_tasksnameеtextview);

            textViewТекущаяЗадача.setText("Описание задачи".toUpperCase());
            // TODO: 14.03.202


            handlerTaskFragment4=new Handler(Looper.getMainLooper());

            progressBarTaskFragment4 = (ProgressBar) view.findViewById(R.id.prograessbartaskfragmen1down); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА



           BungleДанныеДляViewCard=new Bundle();
             BungleДанныеДляViewCardBungle=new Bundle();
          BungleДанныеДляViewCardBungleID=new Bundle();
             BungleДанныеДляViewCardДляпередачиCallsBaskПримечание=new Bundle();



            // TODO: 27.06.2022
            bottomNavigationViewДляTasks = (BottomNavigationView) view.findViewById(R.id.bottomnavigationActiviTask8);

            // TODO: 14.03.2022  тут обьявляем три кнопки доьавить контроль и новая задача

            bottomNavigationКонкретноКнопкаДобавить = bottomNavigationViewДляTasks.findViewById(R.id.id_taskCreateNewTasks);

            // TODO: 14.03.2022  тут обьявляем три кнопки доьавить контроль и новая задача

            bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи = bottomNavigationViewДляTasks.findViewById(R.id.id_taskHome);


            bottomNavigationКонкретноКнопкаДобавить.setVisibility(View.GONE);
            // TODO: 16.03.2022
            bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи .setVisibility(View.VISIBLE);
            // TODO: 16.03.2022
            bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи .setTitle("Контроль");

            bottomNavigationПринудительныйОбмен = bottomNavigationViewДляTasks.findViewById(R.id.id_taskAsyns);


            bottomNavigationПринудительныйОбмен.setVisibility(View.GONE);


            // TODO: 15.03.2022 ПРИШЛИ ПРАМЕНТЫ ОТ ДРУГОВА ФРАГМЕНТА
            bundleПередачаДанныхЧерезФрагменты = this.getArguments();
            // TODO: 16.03.2022
            Log.d(this.getClass().getName(), "  Fragment2_Create_Tasks  viewДляПервойКнопкиHome_Задания ---/" + viewДляПервойКнопкиHome_Задания + " recyclerViewДляСозданиеНовойЗадачи" +
                    " bundleПередачаДанныхЧерезФрагменты " + bundleПередачаДанныхЧерезФрагменты);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            Log.d(this.getClass().getName(), "  Fragment3_Create_Tasks  viewДляПервойКнопкиHome_Задания " + viewДляПервойКнопкиHome_Задания);
            // TODO: 14.03.2022
            Log.d(this.getClass().getName(), " onCreateView  viewДляПервойКнопкиHome_Задания  Fragment1_One_Tasks  onCreateView ");

            // TODO: 14.03.2022
            viewДляПервойКнопкиHome_Задания = inflater.inflate(R.layout.activity_main_fragment1_for_tasks, container, false);

            // TODO: 12.03.2022
            Log.d(this.getClass().getName(), " onCreateView  viewДляПервойКнопкиHome_Задания  Fragment1_One_Tasks  onCreateView " +
                    "" + viewДляПервойКнопкиHome_Задания);

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///
        }
        return viewДляПервойКнопкиHome_Задания;//  super.onCreateView(inflater, container, savedInstanceState)            todo  super.onCreateView(inflater, container, savedInstanceState);
    }

    // TODO: 12.03.2022
    @Override
    public void onDestroy() {
        super.onDestroy();
        try{

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
    public void onResume() {
        super.onResume();
        try {
            // TODO: 16.03.2022

            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент4ЗаполенияЗадачиДляСозданияНовойЗадачи = new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент4(getContext(), getActivity());




            Log.d(this.getClass().getName(), " отработоатл  subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент3ЗаполенияЗадачиДляРедактирования " +
                    "" + subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент4ЗаполенияЗадачиДляСозданияНовойЗадачи);

            // TODO: 02.03.2022
            ПубличныйIDДляФрагмента = new GetttingPublicID().getttingPublicID(getContext());


            // TODO: 15.03.2022
            Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента);

            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент4ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодПолученимТОлькоКоличествоЗадач(ПубличныйIDДляФрагмента);

            Log.d(this.getClass().getName(), "Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);


            // TODO: 02.03.2022 получения курсора
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент4ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодПолучаемГлавныеДанныеДляЗадач(ПубличныйIDДляФрагмента);

            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK  " + Курсор_ГлавныйКурсорДляЗадач);


            //todo метод  ИНИЦИАЛИЗАЦИИ RECYCLEVIEW ДЛЯ АКТИВТИ ЗАДАЧИ

            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент4ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодИнициализацииRecycleViewДляЗадач(viewДляПервойКнопкиHome_Задания);

            // TODO: 05.03.2022  ДЛЯ ИНИЗАЛИЗАЦИИ НИЖНИХ КНОПОК
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент4ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодСозданиеНавигаторКнопок();

            // TODO: 17.03.2022

            // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент4ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);



            Log.d(this.getClass().getName(), " нет данных для отображения " +
                    "отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK  МетодКпопкаСоЗачкомКраснымДополнительныйСтатус  " + Курсор_ГлавныйКурсорДляЗадач +
                    " Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);


            // TODO: 22.03.2022 obsrver


            // TODO: 13.03.2022
          bottomNavigationViewДляTasks .requestLayout();
            // TODO: 13.03.202
            recyclerView.requestLayout();
            recyclerView.forceLayout();
            // TODO: 14.03.2022
           linearLayou.requestLayout();//


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


    // TODO: 16.03.2022 бизнес логика для третьего фрагмента созданеи нового задачи    // TODO: 16.03.2022 бизнес логика для третьего фрагмента созданеи нового задачи
    // TODO: 16.03.2022 бизнес логика для третьего фрагмента созданеи нового задачи   // TODO: 16.03.2022 бизнес логика для третьего фрагмента созданеи нового задачи

   private class SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент4  {
        // TODO: 28.02.2022
        public SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент4(Context context, Activity activity) {
            // TODO: 03.03.2022
            Log.d(this.getClass().getName(), "SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент4  " + context.getClass().getName());
        }

        // TODO: 28.02.2022 Под Класс порлучение данных для активти
        Cursor МетодПолучаемГлавныеДанныеДляЗадач(Integer ПубличноеIDПолученныйИзСервлетаДляUUID) {
            try {
                // TODO: 15.05.202
                String Текущаятаблицы="view_tasks";
                ModuleQuety moduleQuety=new ModuleQuety(getContext());
                Курсор_ГлавныйКурсорДляЗадач= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT *  FROM "+Текущаятаблицы+" AS D" +
                        "  WHERE D.user_update='"+ПубличноеIDПолученныйИзСервлетаДляUUID+"' AND  D.message IS NOT NULL "+
                        "   ORDER BY D.status_write, D.date_update DESC   " ,null);

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




        // TODO: 04.03.2022 прозвомжность инициализации RecycleView

        void МетодИнициализацииRecycleViewДляЗадач(@NonNull View viewДляПервойКнопкиHome_Задания) {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK  " + Курсор_ГлавныйКурсорДляЗадач);

                if (viewДляПервойКнопкиHome_Задания!=null) {
                    Log.d(this.getClass().getName(), " есть данные для отображения " +
                            "отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ГлавныйКурсорДляЗадач  " + Курсор_ГлавныйКурсорДляЗадач);
                    // TODO: 28.02.2022
                    recyclerView = (RecyclerView) viewДляПервойКнопкиHome_Задания.findViewById(R.id.recycleviewActiviTask);
                    // TODO: 14.03.2022
                    recyclerView.setVisibility(View.VISIBLE);
                    // TODO: 14.03.202
                /*    GridLayoutManager gridLayoutManager;
                    // TODO: 14.03.202
                    gridLayoutManager = new GridLayoutManager(getActivity(), 1);*/

                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    // TODO: 28.02.2022 создаем наш первый RecyclerView
                    // TODO: 28.02.2022 создаем наш первый RecyclerView
                    recyclerView.setLayoutManager(linearLayoutManager);//TODO new LinearLayoutManager(getContext()) // TODO: 28.02.2022 создаем наш первый RecyclerView recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                    // TODO: 04.03.2022  В ДАННЫЙ КОД ЗАХОДИМ КОГДА РЕЖИМ РЕДАКТИРОВАНИЯ И МЫ УЖЕ СОЗДАНУЮ АЗАДЧЦ РЕДАКТИРУЕМ

                    // TODO: 04.03.2022  В ДАННЫЙ КОД ЗАХОДИМ КОГДА МЫ СОЗДАЕМ НОВУЮ ЗАДАЧУ

                    LinkedBlockingQueue ОчередьДаныеДляСозданиеНовойЗадачи = new LinkedBlockingQueue();

                    // TODO: 16.03.2022

                    ОчередьДаныеДляСозданиеНовойЗадачи.offer("СоздатьНовуюЗадачу");

                    // TODO: 28.02.2022 переходим в создание новой задачи
                    myRecycleViewAdapter = new MyRecycleViewAdapter(ОчередьДаныеДляСозданиеНовойЗадачи);

                    // TODO: 04.03.2022  В ДАННЫЙ КОД ЗАХОДИМ КОГДА МЫ СОЗДАЕМ НОВУЮ ЗАДАЧУ

                    recyclerView.setAdapter(myRecycleViewAdapter);
                    // TODO: 22.03.2022
                    // TODO: 04.03.2022
                    recyclerView.getAdapter().notifyDataSetChanged();
                    // TODO: 04.03.2022
                    recyclerView.getAdapter().notifyItemChanged(0);

                    Log.d(this.getClass().getName(), " есть данные для отображения " +
                            "отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ГлавныйКурсорДляЗадач  " + Курсор_ГлавныйКурсорДляЗадач + " myRecycleViewAdapterНоваяЗадача " + myRecycleViewAdapter);


                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 recyclerView   " + recyclerView);

                    // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ когнданет записей  МетодИнициализацииRecycleViewДляЗадачМетодИнициализацииRecycleViewДляЗадач

                    // TODO: 14.03.2022
                    Log.d(this.getClass().getName(), "onItemRangeMoved  Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе +
                            " Курсор_ДляПолученияДАнныхДляЗАДАЧTASK " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);

                    // TODO: 04.03.2022 создаем слушатель    третий класс создаем класс слушаителй  ДАННЫЙ КОД ЗАПУСКАЕТЬСЯ ПОСЛЕ СОЗДАЕНИЯ И УСТАНОВКИ АДАПТЕРА RECYCLEVIEW

                }


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

        // TODO: 05.03.2022 метод создание кнопок снизу навигатор
        void МетодСозданиеНавигаторКнопок() {
            try {
                // TODO: 05.03.2022
         bottomNavigationViewДляTasks .setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        // TODO: 09.03.2022
                        try {
                            fragmentManagerДляЗадачи = getActivity().getSupportFragmentManager();
                            // TODO: 09.03.2022
                            fragmentTransactionляЗадачи = fragmentManagerДляЗадачи.beginTransaction();
                            // TODO: 11.03.2022
                            // TODO: 11.03.2022
                            Log.d(this.getClass().getName(), "  bottomNavigationViewДляTasks " +  bottomNavigationViewДляTasks  + " fragment_ТекущийФрагмент " + fragment_ТекущийФрагмент);
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
                                    fragment_ТекущийФрагмент = new Fragment1_One_Tasks();
                                    // TODO: 11.03.2022
                                    fragmentTransactionляЗадачи.replace(R.id.activity_main_fisrt_for_tasks, fragment_ТекущийФрагмент).commit();//.layout.activity_for_fragemtb_history_tasks
                                    // TODO: 10.03.2022
                                    fragmentTransactionляЗадачи.show(fragment_ТекущийФрагмент);
                                    // TODO: 10.03.2022
                                    Log.d(this.getClass().getName(), " fragmentTransactionляЗадачи " + fragmentTransactionляЗадачи);
                                    // TODO: 10.03.2022
                                    // TODO: 15.03.2022
                                    // TODO: 09.03.2022
                                    bottomNavigationViewДляTasks .requestLayout();
                                    // TODO: 14.03.2022
                                   linearLayou.requestLayout();
                                    Log.d(this.getClass().getName(), " bottomNavigationViewДляTasks.getChildCount() " +      bottomNavigationViewДляTasks .getChildCount());
                                    break;

                                // TODO: 09.03.2022////

                                case R.id.id_taskCreateNewTasks:
                                    // TODO: 09.03.2022
                                    Log.d(this.getClass().getName(), " R.id.id_taskCreateNewTasks  item.getItemId() " + item.getItemId());
                                    // TODO: 10.03.2022
                                    item.setChecked(true);
                                    // TODO: 09.03.2022
                                    fragment_ТекущийФрагмент = new Fragment2_Create_Tasks();
                                    // TODO: 11.03.2022
                                    fragmentTransactionляЗадачи.replace(R.id.activity_main_fisrt_for_tasks, fragment_ТекущийФрагмент).commit();//.layout.activity_for_fragemtb_history_tasks
                                    // TODO: 10.03.2022
                                    fragmentTransactionляЗадачи.show(fragment_ТекущийФрагмент);
                                    // TODO: 10.03.2022
                                    Log.d(this.getClass().getName(), " fragmentTransactionляЗадачи " + fragmentTransactionляЗадачи);
                                    // TODO: 10.03.2022
                                    // TODO: 14.03.2022
                             bottomNavigationViewДляTasks .requestLayout();
                                    // TODO: 14.03.2022
                                   linearLayou.requestLayout();
                                    Log.d(this.getClass().getName(), " bottomNavigationViewДляTasks.getChildCount() " +      bottomNavigationViewДляTasks.getChildCount());

                                    // TODO: 14.03.2022  дополнительно визуализируем


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

        // TODO: 14.03.2022
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
             bottomNavigationViewДляTasks .getOrCreateBadge(R.id.id_taskCreateNewTasks).setVisible(true);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true); R.id.id_taskHome todo R.id.id_taskCreateNewTasks
                    // TODO: 06.03.2022
             bottomNavigationViewДляTasks .getOrCreateBadge(R.id.id_taskCreateNewTasks).setNumber(Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    // TODO: 09.03.2022
             bottomNavigationViewДляTasks .getOrCreateBadge(R.id.id_taskCreateNewTasks).setBackgroundColor(Color.parseColor("#8B0000"));
                    // TODO: 06.03.2022
                    Log.d(this.getClass().getName(), "   bottomNavigationViewДляTasks " +  bottomNavigationViewДляTasks  +
                            "  Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount()   " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount());
                    // TODO: 05.03.2022
                } else {
                    // TODO: 06.03.2022
             bottomNavigationViewДляTasks .getOrCreateBadge(R.id.id_taskCreateNewTasks).setVisible(false);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    // TODO: 09.03.2022
             bottomNavigationViewДляTasks .getOrCreateBadge(R.id.id_taskCreateNewTasks).setNumber(Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount());
                    // TODO: 10.03.2022
             bottomNavigationViewДляTasks .getOrCreateBadge(R.id.id_taskCreateNewTasks).setBackgroundColor(Color.BLACK);
                }
                Log.d(this.getClass().getName(), "   bottomNavigationViewДляTasks" +  bottomNavigationViewДляTasks  +
                        "  Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount()   " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
                // TODO: 13.03.2022
         bottomNavigationViewДляTasks .requestLayout();
                // TODO: 13.03.2022
         bottomNavigationViewДляTasks .requestApplyInsets();
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

        // TODO: 23.03.2022  метод получения сколько созданных
        protected Cursor МетодПолученимТОлькоКоличествоЗадач(Integer ПубличноеIDПолученныйИзСервлетаДляUUID) {
            // TODO: 02.03.2022
            try {
                // TODO: 15.05.202
                String Текущаятаблицы="view_tasks";
                ModuleQuety moduleQuety=new ModuleQuety(getContext());
                Курсор_ГлавныйКурсорДляЗадач= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT *  FROM "+Текущаятаблицы+" AS D" +
                        "  WHERE D.user_update='"+ПубличноеIDПолученныйИзСервлетаДляUUID+"' AND  D.status_write!='Удаленная' "+
                        "   ORDER BY D.status_write, D.date_update DESC   " ,null);

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
            return Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе;
        }


        // TODO: 15.03.2022  перенесееный код
        // TODO: 28.02.2022 начало  MyViewHolderДляЧата
        protected class MyViewHolder extends RecyclerView.ViewHolder {// TODO: 28.02.2022 начало  MyViewHolderДляЧата
            // TODO: 28.02.2022
         private    TextInputEditText   textView5, textView10ПримечанияОтКлиентаCallsBalck ,textInputEditTextСтатус, textViewСамаЗадача,textView3Описание,textView4Откого,textInputEditTextДАтаСоздания,textInputEditTextТипЗадачи; ;
            // TODO: 17.03.2022
            private     MaterialCardView materialCardView;
            // TODO: 16.03.202
            private     MaterialButton buttonДляПоложительныйРезультатВыполенойЗадачи, buttonДляРезультатНеВыполенаЗадачи;
            // TODO: 02.03.2022
           // Spinner spinnerДляСозданиеНовойЗадачи;
            // TODO: 18.03.2022
           // SeekBar seekBarДляВЫбораКакаяЗадачаВыполяется;
            // TODO: 18.03.2022
           // TextView textView7;
            // TODO: 21.03.2022
            private      Bundle bundleЗначенияДляНовойЗадачи;
            // TODO: 21.03.2022


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
                 //   textView1 = (TextInputEditText) itemView.findViewById(R.id.text1_innercardview);
                    // TODO: 02.3.2022  дополнительный
                    textViewСамаЗадача = (TextInputEditText) itemView.findViewById(R.id.text2_innercardviewtwo);

                    ///TODO описание
                    textView3Описание= (TextInputEditText) itemView.findViewById(R.id.text2_value_fragment4Opisanie);

                    ///TODO от кого

                            textView4Откого= (TextInputEditText) itemView.findViewById(R.id.text2_value_fragment4Otkogo   );

                     textInputEditTextСтатус = (TextInputEditText) itemView.findViewById(R.id.text8_innercardviewtextinutstatus_taskcreatingdown);

                    // TODO: 28.02.2022

                    textInputEditTextДАтаСоздания= (TextInputEditText) itemView.findViewById(R.id.text2_value_fragment4ValueDatecreating);

                    // TODO: 17.06.2022 ТТИП ЗАДАЧИ

                    textInputEditTextТипЗадачи= (TextInputEditText) itemView.findViewById(R.id.text2_value_fragment5TypeTask);


                    // textView3 = (TextInputEditText) itemView.findViewById(R.id.text3_innercardviewtree);
                    // TODO: 28.02.2022
                    //spinnerДляСозданиеНовойЗадачи = (Spinner) itemView.findViewById(R.id.Spinner_innercardviewfour);
                    // TODO: 28.02.2022
                    // TODO: 13.03.2022
                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 materialCardView  textView2  "+ textView3Описание );


                    // TODO: 01.03.2022
                    materialCardView = (MaterialCardView) itemView.findViewById(R.id.cardviewmatirealtask);


                    // TODO: 13.03.2022 положительный резуолат выполения задачи
                    buttonДляПоложительныйРезультатВыполенойЗадачи = (MaterialButton) itemView.findViewById(R.id.BottomFor_task_complete_result_task);

                    // TODO: 16.03.2022
                    buttonДляПоложительныйРезультатВыполенойЗадачи.setBackgroundResource(R.drawable.style_for_task_new_create_save);

                    // TODO: 13.03.2022  НЕ ВЫПОЛНЕНИЯ ЗАДАЧИ
                    buttonДляРезультатНеВыполенаЗадачи = (MaterialButton) itemView.findViewById(R.id.BottomFor_task_deseble_result_task);

                    // TODO: 16.03.2022
                    buttonДляРезультатНеВыполенаЗадачи.setBackgroundResource(R.drawable.style_for_task_new_create_save);


               Integer СтатусИзДруговоФрагментаОдинслиСтатусНАТекущейЗадаче=     bundleПередачаДанныхЧерезФрагменты.getInt("ПоказыватьКнопкиИлиНетЕслиУжеБыИзмененСтатус",0);

                    Log.d(this.getClass().getName(), " СтатусИзДруговоФрагментаОдинслиСтатусНАТекущейЗадаче" +СтатусИзДруговоФрагментаОдинслиСтатусНАТекущейЗадаче );


                    // TODO: 20.06.2022 ДЕЛАЕМ КОНОПКИ НЕ АКТИВНМИ КОГДА УЖЕ ОТ ОТРАБОТАЛИ ОТКАЗ ВЫПОЛЕНО ИЛИ ОТМЕНЕННАЯ ЗАДАЧА 

                    if(СтатусИзДруговоФрагментаОдинслиСтатусНАТекущейЗадаче>0 ){
                        //TODO
                        buttonДляПоложительныйРезультатВыполенойЗадачи.setEnabled(false);
                        //todo
                        buttonДляПоложительныйРезультатВыполенойЗадачи.setVisibility(View.GONE);


                        //TODO
                        buttonДляРезультатНеВыполенаЗадачи.setEnabled(false);
                        //todo
                        buttonДляРезультатНеВыполенаЗадачи.setVisibility(View.GONE);

                    }
                    
                    
       


                    

                    // TODO: 18.03.2022

                ///    seekBarДляВЫбораКакаяЗадачаВыполяется = (SeekBar) itemView.findViewById(R.id.seekBar_for_tasks_create_new);
                    // TODO: 18.03.2022
                  //  textView7 = (TextView) itemView.findViewById(R.id.text2_innercardviewtwoSettasksType);
                    // TODO: 21.03.2022
                    bundleЗначенияДляНовойЗадачи = new Bundle();

                    // TODO: 21.03.2022
                    accessibilityNodeInfoДляПередачиДанных = materialCardView.createAccessibilityNodeInfo();
                    // TODO: 24.03.2022
                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 materialCardView   " + materialCardView +
                            " accessibilityNodeInfoДляПередачиДанных " + accessibilityNodeInfoДляПередачиДанных);

                    // TODO: 24.03.2022

                    textView10ПримечанияОтКлиентаCallsBalck = (TextInputEditText) itemView.findViewById(R.id.text2_callsBack_current_task_fragment4);

                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 textView10ПримечанияОтКлиентаCallsBalck   " + textView10ПримечанияОтКлиентаCallsBalck);


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


        // TODO: 16.03.2022


        class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
            // TODO: 04.03.2022
            LinkedBlockingQueue ОчередьДаныеДляСозданиеНовойЗадачиВнутри;

            // TODO: 15.03.2022 первыЙ КЛАССС ДЛЯ АДАПТЕРА С ДАННЫМИ ПОДНИМАЕМ ДАННЫЕ ДЛЯ РЕДАКТИРОВАНИЯ
            public MyRecycleViewAdapter(@NotNull LinkedBlockingQueue ОчередьДаныеДляСозданиеНовойЗадачи) {
                // super();
                // TODO: 04.03.2022
                this.ОчередьДаныеДляСозданиеНовойЗадачиВнутри = ОчередьДаныеДляСозданиеНовойЗадачи;

                Log.i(this.getClass().getName(), "     getItemId holder.position " + "  ОчередьДаныеДляСозданиеНовойЗадачиВнутри " + ОчередьДаныеДляСозданиеНовойЗадачиВнутри);
            }


            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // TODO: 10.03.2022
                View viewГлавныйВидДляRecyclleViewДляЗаданий = null;
                try {
                    // TODO: 28.02.2022
                    viewГлавныйВидДляRecyclleViewДляЗаданий = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_apply_takst_cardview4, parent, false);//todo old simple_for_new_takst_cardview3       R.layout.simple_for_takst_cardview1
                    // TODO: 05.03.2022
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляЗаданий" + viewГлавныйВидДляRecyclleViewДляЗаданий);
                    // TODO: 28.02.2022

                    // TODO: 22.03.2022
                    myViewHolder = new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент4.MyViewHolder(viewГлавныйВидДляRecyclleViewДляЗаданий);

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


            // TODO: 16.03.2022  перегруженный метод ДЛЯ СОЗДАНЕИ НОВОЙ ЗАДАЧИ И ВСЕ !!!!! 
            // TODO: 16.03.2022  перегруженный метод ДЛЯ СОЗДАНЕИ НОВОЙ ЗАДАЧИ И ВСЕ !!!!! 
            // TODO: 16.03.2022  перегруженный метод ДЛЯ СОЗДАНЕИ НОВОЙ ЗАДАЧИ И ВСЕ !!!!! 

            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                // TODO: 28.02.2022 привазяваем данные из колекции пряме на наш recycreview
                try {
                    // TODO: 14.03.2022

                    // TODO: 02.03.2022 тут РАЗДАЕМ ДАННЫЕ RECYCLERBIEW
                    ;
                    // TODO: 04.03.2022 p==osion
                    Log.i(this.getClass().getName(), "   ОчередьДаныеДляСозданиеНовойЗадачиВнутри.peek() " + ОчередьДаныеДляСозданиеНовойЗадачиВнутри.peek());



                    // TODO: 14.03.2022  метод создания номер задания
                    МетодБиндингаНомерЗадания(holder);

                    // TODO: 24.03.2022
                    // TODO: 14.03.2022  проверяем статус Примеания Отработано она или нет
                    МетодБиндингаПримечанияЗадания(holder);




                    // TODO: 14.03.2022  метод создания spinnerДляСозданиеНовойЗадачи
                    МетодБиндингаДатаЗадания(holder);


                    // TODO: 14.03.2022  метод создания ФИО задания
                    МетодБиндингаФИОДляЗадания(holder);





                    // TODO: 13.03.2022 СЛУШАТЕЛЬ СРАБАТЫВАЕТ КОГДА КОМАДА TOGGER И МЕНЯЕМ СТАТУСТ ЧЕК ОЗНАКОМЛЕНЫЙ ЛИ ИНЕТ

                    МетодБиндингаСлушательisChered(holder);


                    // TODO: 16.03.2022  метод для слушателя для создания данных 
                    МетодБиндингаСлушательДляКнопкиСоздатьНовуюЗадачу(holder);





                    // TODO: 02.03.2022#5  получаем ТИП ЗАДАЧИ
                    МетодБиндингПолучаемТипЗадания(holder);



////TODO ДВА НОВЫХ МЕТОДА

//TODO   МЕТОД КОТО НАПИСАЛ ТЕКУЩЕЕ ЗАДАНИЕ .  ОТ КОГО
                    МетодБиндингаКтоНписалТекущуюЗадачу(holder);


//TODO   МЕТОД КОТО текущий СТАТУС ЗАДАЧИ ДЛЯ МЕНЯ
                    МетодБиндингаСтатусЗадачиДляменя(holder);


                    //TODO   МЕТОД КОТО текущий Дата создания  ЗАДАЧИ ДЛЯ МЕНЯ
                    МетодБиндингаДатаСозданияТекущейзадачи(holder);



//TODO   МЕТОД КОТО ОПИАНИЕ ЗАДАЧИ
                    МетодБиндингаОписаниеЗадачи(holder);








//TODO   МЕТОД КОТОРЫЙ ОПИСЫВАЕТ ТИП  ЗАДАЧИ
                    МетодБиндингаТипЗадачиСозданияТекущейзадачи(holder);




                    // TODO: 13.03.2022 настройки для carview КОНЕЦ ВЫЗЫВАЕМЫХ МЕТОДОВ вызывает событие клика на самой cardview 

                    holder.materialCardView.toggle();

                    // TODO: 13.03.2022
                    holder.materialCardView.setChecked(true);
                    // TODO: 15.03.2022
                    // TODO: 15.03.2022
              holder.materialCardView.setCardBackgroundColor(Color.parseColor("#F0FFF0"));

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


                    Log.i(this.getClass().getName(), "  BungleДанныеДляViewCard   " + BungleДанныеДляViewCard.getBundle(String.valueOf(holder.materialCardView.getId())));

                    // TODO: 13.03.2022  передаем статус задачи


                    // TODO: 03.03.2022 передаем помер позиции position
                    holder.materialCardView.setTag(holder.materialCardView.getId(), СамСтатусПрочтенияИлиНет);

                    // TODO: 24.03.2022

                    // TODO: 01.03.2022*/
                    Bundle bundle = accessibilityNodeInfoДляПередачиДанных.getExtras();
                    // TODO: 24.03.2022  bundlebundle
                    bundle.putInt("accessibilityNodeInfoДляПередачиДанных", СамСтатусПрочтенияИлиНет);

                    Log.i(this.getClass().getName(), "  bundle   " + bundle)


                    ;
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
                    if (holder.textView5!=null) {
                        ///todo
                        holder.textView5.setText("тип: ");
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



            private void МетодБиндингаФИОДляЗадания(@NonNull MyViewHolder holder) throws ExecutionException, InterruptedException {
                try {
                    // TODO: 02.03.2022#4  // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4
                    Integer ПозицияДанныхВSpinner = 0;
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  КтоНаписалСообщениеФИОдЛПосика ");
                    // TODO: 02.03.2022
                    String ФИОКотоНаписал = new String();
                    // TODO: 13.03.2022
                       Cursor sqLiteCursorПолученимНАстоящийФИО = МетодПолучениеДанныхФИОаОснованииID(1);//КтоНаписалСообщениеФИОдЛПосика
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
            public int getItemViewType(int position) {
                return super.getItemViewType(position);
            }

            // TODO: 17.03.2022  получаем данные для спинера
            private void МетодБиндингаДатаЗадания(@NonNull MyViewHolder holder) throws ParseException {
                try {
                    // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3  // TODO: 02.03.2022#3



                   Cursor sqLiteCursorПолученныйФИОДЛЯSpinnerДляНовойЗадачи =
                            new КлассАдаптерДляСпинера(getContext()).МетодПолучаемГлавныеДанныеДляSpinnerКомуЗадачаФИО();

///TODO ГЛАВНЫЙ АДАПТЕР чата
                    SimpleCursorAdapter АдаптерДляФИОПриСозданииНовойЗадачи = new SimpleCursorAdapter(getContext(), android.R.layout.simple_spinner_item, sqLiteCursorПолученныйФИОДЛЯSpinnerДляНовойЗадачи,
                            new String[]{"name"},
                            new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

                    // TODO: 17.03.2022
                    SimpleCursorAdapter.ViewBinder БиндингДляSpinnerФИОСозданиеНовойЗадачи = new SimpleCursorAdapter.ViewBinder() {
                        @Override
                        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {

                            // TODO: 18.03.2022  получаем ФИО людей для кого задание

                            String ФИОДляПОиска = null;
                            if (columnIndex == cursor.getColumnIndex("name")) {
                                // TODO: 21.03.2022
                                int ИндексФИО = cursor.getColumnIndex("name");

                                ФИОДляПОиска = cursor.getString(ИндексФИО);
                                ////
                                Log.d(this.getClass().getName(), " ФИОДляПОиска  " + ФИОДляПОиска);
                            }

                            // TODO: 18.03.2022  получаем ПУБЛИЧНЫЙ ID длявыбранных ФИО

                            int ИндексПубличныIdДляВыбранныхФИО = cursor.getColumnIndex("_id");

                            Long ПубличныIdДляВыбранныхФИО = cursor.getLong(ИндексПубличныIdДляВыбранныхФИО);
                            ////
                            Log.d(this.getClass().getName(), " ПубличныIdДляВыбранныхФИО  " + ПубличныIdДляВыбранныхФИО);

                            Drawable icon = null;
                            //

                            icon = getResources().getDrawable(R.drawable.icon_dsu1_create_peplefio_tasks);///

                            icon.setBounds(1, 1, 60, 60);

                            ((TextView) view).setPadding(0, 0, 0, 0);

                            ((TextView) view).setCompoundDrawables(icon, null, null, null);

                            Log.d(this.getClass().getName(), " ФИОДляПОиска  " + ФИОДляПОиска +
                                    "    ((TextView) view).getHint() " + ((TextView) view).getHint());
                            // TODO: 18.03.2022  сам слушатель


                            ((TextView) view).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);



                            // TODO: 18.03.2022
                            return true;
                        }

                        // TODO: 18.03.2022  перенессыц код


                    };
                    // TODO: 17.03.2022
                    АдаптерДляФИОПриСозданииНовойЗадачи.setViewBinder(БиндингДляSpinnerФИОСозданиеНовойЗадачи);
                    // TODO: 28.02.2022


                    // TODO: 17.03.2022
                    Log.e(this.getClass().getName(), "АдаптерДляФИОПриСозданииНовойЗадачи  ");

                    // TODO: 17.03.2022
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


            // TODO: 18.03.2022  метод делаем слушатель на spinner


            // TODO: 18.03.2022  метод СОДАЕМ СЛУШАТЕЛЬ ДЛЯ ВЫБОРА ПЕРИОДА ЗАДАЧА SEELBAR



            private void МетодБиндингаНомерЗадания(@NonNull MyViewHolder holder) {
                try {
                    // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2
                    String Самазадача = bundleПередачаДанныхЧерезФрагменты.getString("СамаЗадачаДляПередачи", "");
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  Самазадача " + Самазадача);
                    // TODO: 17.03.2022

                        // TODO: 28.02.2022
                        holder.textViewСамаЗадача.setText( Самазадача);//IDЗадачиТекущей

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

            // TODO: 24.03.2022  для примечания


            private void МетодБиндингаПримечанияЗадания(@NonNull MyViewHolder holder) {
                try {

                    // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2
                    String СтатусЗадачиДляСтатусаПримечания = bundleПередачаДанныхЧерезФрагменты.getString("СтатусПримечаниеCallsBaks", "");
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  СтатусЗадачиДляСтатусаПримечания " + СтатусЗадачиДляСтатусаПримечания);

                    //TODO узнаем статус задачи
                    Integer СтатусЗадачиУжеОтработаноИлинетВыполенияИлиОтказ = bundleПередачаДанныхЧерезФрагменты.getInt("ПоказыватьКнопкиИлиНетЕслиУжеБыИзмененСтатус", 0);
                    //TODO
                    Log.i(this.getClass().getName(), "  СтатусЗадачиУжеОтработаноИлинетВыполенияИлиОтказ " + СтатусЗадачиУжеОтработаноИлинетВыполенияИлиОтказ);

                    // TODO: 17.03.2022

                    if (!СтатусЗадачиДляСтатусаПримечания.equalsIgnoreCase("Примечания нет")) {

                        holder.textView10ПримечанияОтКлиентаCallsBalck.setText(СтатусЗадачиДляСтатусаПримечания);

                        holder.textView10ПримечанияОтКлиентаCallsBalck.setHighlightColor(Color.BLACK);

                    }else{
                        holder.textView10ПримечанияОтКлиентаCallsBalck.setText("");

                        if (СтатусЗадачиУжеОтработаноИлинетВыполенияИлиОтказ==0) {

                            holder.textView10ПримечанияОтКлиентаCallsBalck.setHint("Напишите примечание");

                        } else {

                            holder.textView10ПримечанияОтКлиентаCallsBalck.setHint("Примечания нет");

                        }

                        holder.textView10ПримечанияОтКлиентаCallsBalck.setHighlightColor(Color.GRAY);

                    }



                    if (СтатусЗадачиУжеОтработаноИлинетВыполенияИлиОтказ>0) {
                        holder.textView10ПримечанияОтКлиентаCallsBalck.setClickable(false);
                        holder.textView10ПримечанияОтКлиентаCallsBalck.setFocusable(false);
                        holder.textView10ПримечанияОтКлиентаCallsBalck.setEnabled(false);
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


            private void МетодБиндингаОписаниеЗадачи(@NonNull MyViewHolder holder) {
                try {

                    // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2
                    String ЗадачиДляПередачи = bundleПередачаДанныхЧерезФрагменты.getString("ОписаниеЗадачиШабка", "");
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  ЗадачиДляПередачи " + ЗадачиДляПередачи);
                    // TODO: 17.03.2022
                        //TODO
                        holder.textView3Описание  .setText(ЗадачиДляПередачи);

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


            // TODO: 13.03.2022

            private void МетодБиндингаКтоНписалТекущуюЗадачу(@NonNull MyViewHolder holder) {
                try {

                    // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2
                    String СтатусЗадачиКтоНаписалЗадачуТекущую = bundleПередачаДанныхЧерезФрагменты.getString("СтатусКтоНаписалДЛяПереадачиВДругойФоргмент", "");
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  СтатусЗадачиКтоНаписалЗадачуТекущую " + СтатусЗадачиКтоНаписалЗадачуТекущую);
                    // TODO: 17.03.2022

                    if (holder.textView4Откого!=null) {
                        //TODO
                        holder.textView4Откого.setHint("Уже имеет статус (Выполнена) ");//IDЗадачиТекущей
                        // TODO: 25.03.2022
                        holder.textView4Откого.setSelected(false);
                        // TODO: 25.03.2022
                        holder.textView4Откого.setFocusable(false);
                        // TODO: 25.03.2022
                        holder.textView4Откого.setClickable(false);
                        //TODO
                        holder.textView4Откого.setText(СтатусЗадачиКтоНаписалЗадачуТекущую);
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

            // TODO: 13.03.2022

            private void МетодБиндингаСтатусЗадачиДляменя(@NonNull MyViewHolder holder) {
                try {

                    // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2
                    String СтатусЗадачиКтоНаписалЗадачуТекущую = bundleПередачаДанныхЧерезФрагменты.getString("СтатусзадачиДляПередачиВоТОрокойФрагмент2", "");
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  СтатусЗадачиКтоНаписалЗадачуТекущую " + СтатусЗадачиКтоНаписалЗадачуТекущую);
                    // TODO: 17.03.2022

                    if (holder.textInputEditTextСтатус!=null) {
                        //TODO
                        // TODO: 25.03.2022
                        holder.textInputEditTextСтатус.setSelected(false);
                        // TODO: 25.03.2022
                        holder.textInputEditTextСтатус.setFocusable(false);
                        // TODO: 25.03.2022
                        holder.textInputEditTextСтатус.setClickable(false);
                        // TODO: 25.03.2022
                        holder.textInputEditTextСтатус.setTextColor(Color.BLACK);
                        //TODO
                        holder.textInputEditTextСтатус.setText(СтатусЗадачиКтоНаписалЗадачуТекущую);
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

            // TODO: 15.03.2022


            private void МетодБиндингаДатаСозданияТекущейзадачи(@NonNull MyViewHolder holder) {
                try {

                    // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2
                    String ДатаЗадачиКтоНаписалЗадачуТекущую = bundleПередачаДанныхЧерезФрагменты.getString("ДатаСоздаенияЗадачиФрагмент2", "");
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  ДатаЗадачиКтоНаписалЗадачуТекущую " + ДатаЗадачиКтоНаписалЗадачуТекущую);
                    // TODO: 17.03.2022

                    if (holder.textInputEditTextДАтаСоздания!=null) {
                        //TODO
                        // TODO: 25.03.2022
                        holder.textInputEditTextДАтаСоздания.setSelected(false);
                        // TODO: 25.03.2022
                        holder.textInputEditTextДАтаСоздания.setFocusable(false);
                        // TODO: 25.03.2022
                        holder.textInputEditTextДАтаСоздания.setClickable(false);
                        //TODO
                        holder.textInputEditTextДАтаСоздания.setText(ДатаЗадачиКтоНаписалЗадачуТекущую);
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


            // TODO: 1.03.2022  ТИП ЗАДАЧИ


            private void МетодБиндингаТипЗадачиСозданияТекущейзадачи(@NonNull MyViewHolder holder) {
                try {

                    // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2
                    String ТипЗадачиКтоНаписалЗадачуТекущую = bundleПередачаДанныхЧерезФрагменты.getString("ТипЗадачиОдноразоваяИлиПоРАсписанию", "");
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  ТипЗадачиКтоНаписалЗадачуТекущую " + ТипЗадачиКтоНаписалЗадачуТекущую);
                    // TODO: 17.03.2022

                        //TODO
                        holder.textInputEditTextТипЗадачи.setText(ТипЗадачиКтоНаписалЗадачуТекущую);



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




            // TODO: 13.03.2022
            private void МетодБиндингаСлушательisChered(MyViewHolder holder) {
                // TODO: 14.03.2022

                try {
                    holder.materialCardView.setOnCheckedChangeListener(new MaterialCardView.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(MaterialCardView card, boolean isChecked) {
                            // TODO: 13.03.2022
                            /*int ИндексдляНепрочитанный = R.drawable.icon_dsu1_fortasks_cardview_color_geeeey;
                            // TODO: 13.03.2022

                            // TODO: 13.03.2022
                            Drawable drawableИндексдляНепрочитанный
                                    = getContext().getDrawable(ИндексдляНепрочитанный);*/
                           /* // TODO: 16.03.2022
                            int ИндексПпрочитанные = R.drawable.icon_dsu1_fortasks_cardview_color_geen;*/
                            // TODO: 13.03.2022
                            // TODO: 16.03.2022
                            int ИндексПпрочитанные; //R.drawable.icon_dsu1_create_new_tasks;
                            // TODO: 16.03.2022  
                            Drawable drawableПпрочитанные;

                            // TODO: 13.03.2022
                            Log.d(this.getClass().getName(), " card  " + card +
                                    "  holder.getAdapterPosition() " + holder.getAdapterPosition() + " isChecked " + isChecked);

                            // TODO: 13.03.2022

                            // TODO: 02.03.2022#5


                            // TODO: 13.03.2022
                            if (isChecked) {
                                // TODO: 16.03.2022
                                ИндексПпрочитанные = R.drawable.icon_dsu1_create_new_tasks; //R.drawable.icon_dsu1_create_new_tasks;
                                drawableПпрочитанные
                                        = getContext().getDrawable(ИндексПпрочитанные);

                                // TODO: 13.03.2022
                                card.setCheckedIcon(drawableПпрочитанные);
                                // TODO: 13.03.2022
                                card.setCheckedIconResource(ИндексПпрочитанные);
                                // TODO: 16.03.2022
                                card.setCardBackgroundColor(Color.RED);
                                // TODO: 16.03.2022
                                card.setCardBackgroundColor(Color.RED);

                                // TODO: 13.03.2022
                                // TODO: 13.03.2022
                                card.setSelected(true);
                                // TODO: 13.03.2022
                                Log.d(this.getClass().getName(), "  holder.materialCardView.setOnCheckedChangeListener  isChecked   " + isChecked);


                            } else {
                                // TODO: 14.03.2022
                                ИндексПпрочитанные = R.drawable.icon_dsu1_message_add_contact; //R.drawable.icon_dsu1_create_new_tasks;
                                drawableПпрочитанные
                                        = getContext().getDrawable(ИндексПпрочитанные);

                                // TODO: 13.03.2022
                                card.setCheckedIcon(drawableПпрочитанные);
                                // TODO: 13.03.2022
                                card.setCheckedIconResource(ИндексПпрочитанные);
                                // TODO: 16.03.2022
                                card.setCardBackgroundColor(Color.RED);
                                // TODO: 16.03.2022
                                card.setCardBackgroundColor(Color.RED);

                                // TODO: 13.03.2022
                                // TODO: 13.03.2022
                                card.setSelected(true);
                                // TODO: 13.03.2022
                                Log.d(this.getClass().getName(), "   holder.materialCardView.setOnCheckedChangeListener  isChecked    " + isChecked);
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

            // TODO: 04.03.2022 четвертый дополнительный метод СОЗДАНИЕ СЛУШАТЕЛЯ ДЛЯ СОЗДАНИЕ НОВОЙ ЗАДАЧИ
            private void МетодБиндингаСлушательДляКнопкиСоздатьНовуюЗадачу(MyViewHolder holder) {
                // TODO: 14.03.2022

                try {
                    // TODO: 24.03.2022  ПОЛОЖИТЕЛЬНЫЙ РЕЗУЛЬТАТ
                    holder.buttonДляПоложительныйРезультатВыполенойЗадачи.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.buttonДляПоложительныйРезультатВыполенойЗадачи.animate().rotationX(20l);//.rotationXBy(4l);
                            // TODO: 01.08.2022
                            handlerTaskFragment4.postDelayed(()->{
                            // TODO: 24.03.2022
                         final   Integer ВставляемСтатусКогдаВыВЫполнилиЗадание = 2;//todo один это выполнил успешно
                            handlerTaskFragment4.post(()->{
                                progressBarTaskFragment4.setVisibility(View.VISIBLE);
                            });
                            // TODO: 24.03.2022   класс изменеия статуса клинета Завершил Или Отказался
                            try {
                                if (!WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().isEmpty()) {
                                    WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                            WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                                    while (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING) == 0) {
                                        WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                                WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                                        // TODO: 27.06.2022
                                        handlerTaskFragment4.post(() -> {
                                            progressBarTaskFragment4.setVisibility(View.VISIBLE);
                                        });
                                    }
                                }
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            // TODO: 28.06.2022 смены статуса выполенения статуса в задачах
                            МетодСменыСтатусаЗадачиЗавершенияИлиОтмена(v, ВставляемСтатусКогдаВыВЫполнилиЗадание, holder);
                            // TODO: 13.03.2022
                            Log.d(this.getClass().getName(), "   МетодСменыСтатусаЗадачиЗавершенияИлиОтмена(v);    ВставляемСтатусКогдаВыВЫполнилиЗадание "
                                    + ВставляемСтатусКогдаВыВЫполнилиЗадание);
                                // TODO: 01.08.2022  
                            },500);
                        }
                    });
                    // TODO: 24.03.2022


                    // TODO: 24.03.2022  ПОЛОЖИТЕЛЬНЫЙ РЕЗУЛЬТАТ
                    holder.buttonДляРезультатНеВыполенаЗадачи.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO: 16.03.2022
                            Log.d(this.getClass().getName(), "  holder.buttonДляРезультатНеВыполенаЗадачи.setOnClickListener   МетодБиндингаСлушательДляКнопкиСоздатьНовуюЗадачу    " +
                                    " holder.bundleЗначенияДляНовойЗадачи " + holder.buttonДляРезультатНеВыполенаЗадачи.toString());
                            holder.buttonДляРезультатНеВыполенаЗадачи.animate().rotationX(20l);//.rotationXBy(4l);
                            // TODO: 01.08.2022
                            handlerTaskFragment4.postDelayed(()->{
                            // TODO: 24.03.2022
                            handlerTaskFragment4.post(()->{
                                progressBarTaskFragment4.setVisibility(View.VISIBLE);
                            });
                            try {
                                WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                        WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                            while (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING)==0) {
                                WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                        WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                                // TODO: 27.06.2022
                                handlerTaskFragment4.post(()->{
                                    progressBarTaskFragment4.setVisibility(View.VISIBLE);
                                });
                            }
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // TODO: 24.03.2022
                        final   Integer ВставляемСтатусКогдаВыВЫполнилиЗадание = 1;//todo два  это Отказ

                            // TODO: 24.03.2022   класс изменеия статуса клинета Завершил Или Отказался

                            МетодСменыСтатусаЗадачиЗавершенияИлиОтмена(v, ВставляемСтатусКогдаВыВЫполнилиЗадание, holder);

                            // TODO: 13.03.2022
                            Log.d(this.getClass().getName(), "   МетодСменыСтатусаЗадачиЗавершенияИлиОтмена(v);  buttonДляРезультатНеВыполенаЗадачи   ВставляемСтатусКогдаВыВЫполнилиЗадание "
                                    + ВставляемСтатусКогдаВыВЫполнилиЗадание);

                            },500);
                        }
                    });
                    // TODO: 24.03.2022
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


















            @Override
            public long getItemId(int position) {
                // TODO: 04.03.2022

                Log.i(this.getClass().getName(), "     getItemId holder.position  новая задача " + position);

                return super.getItemId(position);

            }

            protected void МетодСменыСтатусаЗадачиЗавершенияИлиОтмена(View v, Integer ВставляемСтатусКогдаВыВЫполнилиЗадание, MyViewHolder holder) {
                ///
                Boolean РезультатСменыСтатусаНаОзнакомленный = null;
                try {
                    String СаМоПричечаниеЗдялЗаписВБАзу;

                    // TODO: 16.03.2022
                    Log.d(this.getClass().getName(), "  holder.buttonДляПоложительныйРезультатВыполенойЗадачи.setOnClickListener   МетодБиндингаСлушательДляКнопкиСоздатьНовуюЗадачу    " +
                            " holder.bundleЗначенияДляНовойЗадачи " + holder.buttonДляПоложительныйРезультатВыполенойЗадачи.toString());
                    // TODO: 13.03.2022  статус прочтения ли уде или нет адани
                    Integer СтатусПрочтеаУжеЗадачаИлиНет = bundleПередачаДанныхЧерезФрагменты.getInt("СтатусПрочтеаУжеЗадачаИлиНет");//TODO holder.materialCardView.getId()
                    // TODO: 13.03.2022
                    Log.d(this.getClass().getName(), "  СтатусПрочтеаУжеЗадачаИлиНет " + СтатусПрочтеаУжеЗадачаИлиНет);
                    // TODO: 13.03.2022
                    Long ПолучаемUUIDТекущйПозицииВRecyreView = bundleПередачаДанныхЧерезФрагменты.getLong("ПередаемВЧетвертыйФрагмендляСменыСтатуса", 0l);
                    Log.i(this.getClass().getName(), "  ПолучаемUUIDТекущйПозицииВRecyreView   " + ПолучаемUUIDТекущйПозицииВRecyreView);
                    // TODO: 24.03.2022
                    СаМоПричечаниеЗдялЗаписВБАзу = holder.textView10ПримечанияОтКлиентаCallsBalck.getText().toString().trim();
                    // TODO: 24.03.2022
                    СкемИдётПереписка= bundleПередачаДанныхЧерезФрагменты.getInt("СкемИдётПереписка", 0);
                    Log.i(this.getClass().getName(),
                            " ПолучаемUUIDТекущйПозицииВRecyreView " + ПолучаемUUIDТекущйПозицииВRecyreView +
                            " СтатусПрочтеаУжеЗадачаИлиНет " + СтатусПрочтеаУжеЗадачаИлиНет +
                            "  ПримечанниееОтКлиентаПоЗадаTasks " + СаМоПричечаниеЗдялЗаписВБАзу+ "СкемИдётПереписка " +СкемИдётПереписка);
                    // TODO: 03.03.2022  запускам сменты статуса
                    if (Integer.parseInt(String.valueOf(СтатусПрочтеаУжеЗадачаИлиНет)) == 0) {

                            ///////TODO запускаем смены стануса задачи черезе PendingIntent
                            Log.d(getContext().getClass().getName(),  " СтатусПрочтеаУжеЗадачаИлиНет "
                                    + СтатусПрочтеаУжеЗадачаИлиНет+  " ВставляемСтатусКогдаВыВЫполнилиЗадание "+ ВставляемСтатусКогдаВыВЫполнилиЗадание);

                            Intent intentДляЗапускаСлужбыСменыСменыСтатусаВсСлужбе=new Intent();
                        Bundle bundleДляПередачиВСлужбыСменыСтатуса=new Bundle();

                        bundleДляПередачиВСлужбыСменыСтатуса.putString("PROCESS_ID_Задачи","23");
                        bundleДляПередачиВСлужбыСменыСтатуса.putLong("UUIDПолучениейЗадачи",ПолучаемUUIDТекущйПозицииВRecyreView);
                        bundleДляПередачиВСлужбыСменыСтатуса.putInt("ДляЗадачиПередаемФлагВыполненаЗадчаИлиОтказ",ВставляемСтатусКогдаВыВЫполнилиЗадание);
                        bundleДляПередачиВСлужбыСменыСтатуса.putString("СамоПримечание",СаМоПричечаниеЗдялЗаписВБАзу);
                        bundleДляПередачиВСлужбыСменыСтатуса.putString("ИмяСлужбыУведомленияДляЧата_Задачи","WorkManager NOtofocationForTasksOnlyDissenOrCompliete");
                        intentДляЗапускаСлужбыСменыСменыСтатусаВсСлужбе.setAction("ЗапускаемИзмененияСатусазадачиВыполнил");
                        intentДляЗапускаСлужбыСменыСменыСтатусаВсСлужбе.putExtras(bundleДляПередачиВСлужбыСменыСтатуса);
// TODO: 05.07.2022 меняет статус
                      РезультатСменыСтатусаНаОзнакомленный=        service_for_tasks.МетодpЗапускСлужбыДляЗадач(getContext(),intentДляЗапускаСлужбыСменыСменыСтатусаВсСлужбе);

                        Log.i(this.getClass().getName(),
                                " ПолучаемUUIDТекущйПозицииВRecyreView " + ПолучаемUUIDТекущйПозицииВRecyreView +
                                        " service_for_tasks " + service_for_tasks +" РезультатСменыСтатусаНаОзнакомленный " +РезультатСменыСтатусаНаОзнакомленный);
                        
                        if (РезультатСменыСтатусаНаОзнакомленный==true) {
                            // TODO: 09.03.2022
                            fragmentTransactionляЗадачи = fragmentManagerДляЗадачи.beginTransaction();
                            // TODO: 23.03.2022  переходим на фрагмент для редактирования Fragment4_Now_Views_Task_For_Complete
                            fragment_ТекущийФрагмент = new Fragment1_One_Tasks();
                            Bundle bundleПередаемОбратноДанныеВоФрагмент1=new Bundle();
                            bundleПередаемОбратноДанныеВоФрагмент1.putInt("СкемИдётПереписка",СкемИдётПереписка);
                            bundleПередаемОбратноДанныеВоФрагмент1.putBoolean("РезультатИзменияСтатуса",РезультатСменыСтатусаНаОзнакомленный);
                         /*   class__oneSingnalGenerator.
                                    МетодЗапускаетОДНОРАЗОВУЮСинхронизациюПослкеУспешнойПроведеннойОперации(СкемИдётПереписка, getContext());*/
                            // TODO: 14.01.2022
                            Message message = new Message();
                            message.setData(bundleПередаемОбратноДанныеВоФрагмент1);
                            handlerTaskFragment4.sendMessage(message);

                        }else{
                            ///////TODO запускаем смены стануса задачи черезе PendingIntent
                            Log.i(getContext().getClass().getName(), "СтатусПрочтеаУжеЗадачаИлиНет Задание не сменила статус " + СтатусПрочтеаУжеЗадачаИлиНет);
                            Snackbar.make(v, " Задание не сменила статус !!! ", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }

                    } else {
                        ///////TODO запускаем смены стануса задачи черезе PendingIntent
                        Log.i(getContext().getClass().getName(), "СтатусПрочтеаУжеЗадачаИлиНет Статус Уже Изменен на 1  " + СтатусПрочтеаУжеЗадачаИлиНет);
                        Snackbar.make(v, " У задачи уже есть статус !!! ", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        /*        Toast.makeText(getActivity(), " Статус уже изменём !!! (задачи #)" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();*/
                    }

                    // TODO: 24.03.2022 конец кода смена статуса

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

























            // TODO: 24.03.2022

            private Cursor МетодПолучениеДанныхФИОаОснованииID(Integer КтоНаписалСообщениеФИОдЛПосика) throws ExecutionException, InterruptedException {
                // TODO: 16.11.2021 find FIO
                Cursor Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал = null;
                try {
                    // TODO: 15.05.202
                    String Текущаятаблицы="chat_users";
                    ModuleQuety moduleQuety=new ModuleQuety(getContext());
                    Курсор_ГлавныйКурсорДляЗадач= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT D.name  FROM "+Текущаятаблицы+" AS D" +
                            "  WHERE D._id='"+КтоНаписалСообщениеФИОдЛПосика+"'   AND  D.name IS NOT NULL "  ,null);

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

                // TODO: 02.03.2022
                ////////

                Log.d(this.getClass().getName(), "ОчередьДаныеДляСозданиеНовойЗадачиВнутри " + ОчередьДаныеДляСозданиеНовойЗадачиВнутри);
                // TODO: 28.02.2022
                return ОчередьДаныеДляСозданиеНовойЗадачиВнутри.size();
            }

            @Override
            public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

                recyclerView.removeAllViews();

                recyclerView.getRecycledViewPool().clear();
                super.onAttachedToRecyclerView(recyclerView);
            }
        }//TODO  конец два


        // TODO: 17.03.2022  КЛАСС ДЛЯ СПИНЕРА


        private class КлассАдаптерДляСпинера {
            // TODO: 17.03.2022
            Context context;
            // TODO: 17.03.2022
             Cursor sqLiteCursorКурсорПолучаемВсеФИоДляНовойЗадачиКому;

            public КлассАдаптерДляСпинера(Context context) throws ExecutionException, InterruptedException {
                // TODO: 17.03.2022
                this.context = context;

                // TODO: 02.03.2022
                Log.i(this.getClass().getName(), "  sqLiteCursorКурсорПолучаемВсеФИоДляНовойЗадачиКому " + sqLiteCursorКурсорПолучаемВсеФИоДляНовойЗадачиКому);
            }

            // TODO: 17.03.2022  ПЕРЕНЕСЕНЫЙ МЕТОД ДЛЯ КУРСОР ДЛЯ ФИО ДЛЯ СПИНЕРА

            // TODO: 28.02.2022 Под Класс порлучение данных для активти
           Cursor МетодПолучаемГлавныеДанныеДляSpinnerКомуЗадачаФИО()   {
                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                 Cursor sqLiteCursorКурсорВсеФИОДЛяSpinneraДляКогоЗадание = null;
                try {
                    // TODO: 14.05.2025
                    String Текущаятаблицы = "chat_users";
                    ModuleQuety moduleQuety = new ModuleQuety(context);
                    sqLiteCursorКурсорВсеФИОДЛяSpinneraДляКогоЗадание = moduleQuety.getModuleQuery(Текущаятаблицы,
                            " SELECT *  FROM " + Текущаятаблицы + " AS D WHERE  D._id<>= '"+ПубличныйIDДляФрагмента+"'  ORDER BY D._id ",
                            null);
                    Log.d(this.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " sqLiteCursorКурсорВсеФИОДЛяSpinneraДляКогоЗадание " + sqLiteCursorКурсорВсеФИОДЛяSpinneraДляКогоЗадание);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return sqLiteCursorКурсорВсеФИОДЛяSpinneraДляКогоЗадание;
            }


/////////////////////////////////////////////
        }


        // TODO: 21.03.2022  класс создаения класс после получения данных из  Bungle

        private class SubClass_CreateNewTasks_КлассДляСозданияНовойЗадачи {
            // TODO: 21.03.2022
            Bundle bundleПолученныйеДанныеДляСозданияЗадачи;

            // TODO: 21.03.2022
            public SubClass_CreateNewTasks_КлассДляСозданияНовойЗадачи(@NonNull Context context, @NonNull Bundle bundleПолученныйеДанныеДляСозданияЗадачи) {

                try {
                    // TODO: 21.03.2022
                    this.bundleПолученныйеДанныеДляСозданияЗадачи = bundleПолученныйеДанныеДляСозданияЗадачи;
                    ////////
                    Log.d(this.getClass().getName(), "bundleПолученныйеДанныеДляСозданияЗадачи " + bundleПолученныйеДанныеДляСозданияЗадачи);


                    // TODO: 21.03.2022
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




        // TODO: 24.03.2022 Класс лучшее создать смены статуса


       //TODO метод делает callback с ответом на экран
       private  void  МетодИнициализациHandlerCallBack(){

           try{

               Handler.Callback callback = new Handler.Callback() {
                   @Override
                   public boolean handleMessage(@NonNull android.os.Message msg) {
                       Log.d(this.getClass().getName(), " " +
                               " SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1   ПозицияЭлментаVIewCardДополнительно  СтатусПрочтеаУжеЗадачаИлиНет " +
                               msg + " msg.getWhen() " + msg.what);

                       // TODO: 26.06.2022
                       Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                               " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                               " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                               + " ПубличныйIDДляФрагмента "+ПубличныйIDДляФрагмента );

              

                   Bundle bundleПередаемОбратноДанныеВоФрагмент1=    msg.getData();
                       Log.d(this.getClass().getName(), " " +
                               " bundleПередаемОбратноДанныеВоФрагмент1 " +bundleПередаемОбратноДанныеВоФрагмент1);
                       // TODO: 15.03.2022
                       msg.getTarget().postDelayed(()->{

                           progressBarTaskFragment4.setVisibility(View.GONE);

                           // TODO: 14.06.2022
                           fragment_ТекущийФрагмент.setArguments(bundleПередаемОбратноДанныеВоФрагмент1);
                           // TODO: 11.03.2022
                           fragmentTransactionляЗадачи.replace(R.id.activity_main_fisrt_for_tasks, fragment_ТекущийФрагмент).commit();//.layout.activity_for_fragemtb_history_tasks
                           // TODO: 10.03.2022
                           fragmentTransactionляЗадачи.show(fragment_ТекущийФрагмент);


                           // TODO: 10.03.2022
                           Log.d(this.getClass().getName(), " fragmentTransactionляЗадачи " + fragmentTransactionляЗадачи +
                                   " fragment_ТекущийФрагмент " + fragment_ТекущийФрагмент);

                           // TODO: 13.03.2022
                           /// notifyDataSetChanged();

                           recyclerView.requestLayout();

                           recyclerView.forceLayout();
                           // TODO: 29.03.2022


                       },1000);



                       Bundle bundle=      msg.getData();

                       String ОперацияДанныВЧате=bundle.getString("ОперациЯПрошлаЧат","");

                       msg.getTarget().removeMessages(1);

                       return true;
                   }
               };
               handlerTaskFragment4= new Handler(callback);

           } catch (Exception e) {
               e.printStackTrace();
               Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                       " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
               new RecordNewErros(getActivity()).recordnewerror(e.toString(),
                       this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                       Thread.currentThread().getStackTrace()[2].getLineNumber());
           }
       }

       public void МетодЗапускаетБиндингСлужбыДляЗапускаОдноразовойСинхронизаци() {

           try {
               //TODO launch broad caset receiver
               service_for_tasks =new ServiceForTasks();
               Intent intentЗапускСлужыСменаСтатусаБиндинг = new Intent(getContext(), ServiceForTasks.class);
               // TODO: 26.06.2022 созадем биндинг службыы
                 getContext(). bindService(intentЗапускСлужыСменаСтатусаБиндинг, connectionДляСменыСтатусаЗадач, Context.BIND_AUTO_CREATE);
               // context.startService(intentЗапускСлужюыыСинхрониазцииЧерезСлужбу);
               // TODO: 27.06.2022  запуск
           } catch (Exception e) {
               //  Block of code to handle errors
               e.printStackTrace();
               ///метод запись ошибок в таблицу
               Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                       + Thread.currentThread().getStackTrace()[2].getLineNumber());
               new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                       Thread.currentThread().getStackTrace()[2].getLineNumber());

               // TODO: 11.05.2021 запись ошибок


           }

       }
// TODO: 05.07.2022 коннект к биндингу служьы смена статуса задачи



        protected ServiceConnection connectionДляСменыСтатусаЗадач = new ServiceConnection() {
           @Override
           public void onServiceConnected(ComponentName name, IBinder service) {
               try{
                   // We've bound to LocalService, cast the IBinder and get LocalService instance
                   ServiceForTasks.LocalBinderДляСлужбыСменаСтатуса binder = (ServiceForTasks.LocalBinderДляСлужбыСменаСтатуса) service;

                   service_for_tasks = binder.getService();

                   Log.d(context.getClass().getName(), "\n"
                           + " время: " + new Date()+"\n+" +
                           " Класс в процессе... " +  this.getClass().getName()+"\n"+
                           " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                           +"    onServiceDisconnected  service_for_tasks" + service_for_tasks);

               } catch (Exception e) {
                   //  Block of code to handle errors
                   e.printStackTrace();
                   ///метод запись ошибок в таблицу
                   Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                           + Thread.currentThread().getStackTrace()[2].getLineNumber());
                   new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                           Thread.currentThread().getStackTrace()[2].getLineNumber());

                   // TODO: 11.05.2021 запись ошибок


               }
           }

           @Override
           public void onServiceDisconnected(ComponentName name) {
               try{
                   Log.d(context.getClass().getName(), "\n"
                           + " время: " + new Date()+"\n+" +
                           " Класс в процессе... " +  this.getClass().getName()+"\n"+
                           " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                           +"    onServiceDisconnected  service_for_tasks" + service_for_tasks);
               } catch (Exception e) {
                   //  Block of code to handle errors
                   e.printStackTrace();
                   ///метод запись ошибок в таблицу
                   Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                           + Thread.currentThread().getStackTrace()[2].getLineNumber());
                   new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                           Thread.currentThread().getStackTrace()[2].getLineNumber());

                   // TODO: 11.05.2021 запись ошибок


               }
           }
       };


    }   // TODO: 28.02.2022 конец класса бизнес логики   // TODO: 28.02.2022 конец класса бизнес логики

    // TODO: 02.03.2022


    // TODO: 28.02.2022 бизнес -логика    для активти

 }
}






























