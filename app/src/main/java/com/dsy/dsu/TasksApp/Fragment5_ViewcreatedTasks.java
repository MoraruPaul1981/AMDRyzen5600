package com.dsy.dsu.TasksApp;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForTasks;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.sous.backasync.launch.ModuleQuety;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;


public class Fragment5_ViewcreatedTasks extends Fragment {
    // TODO: 15.03.2022
    private RecyclerView recyclerView;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент5 subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент5ЗаполенияЗадачиДляСозданияНовойЗадачи;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент5.myRecycleViewAdapter myRecycleViewAdapter;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент5.MyViewHolder myViewHolder;
    private AccessibilityNodeInfo accessibilityNodeInfoДополнительныеДанные;
    private Bundle bundleНаФрагменте5;

    // TODO: 01.03.2022--перпменные для переноса в другие ФОАГМЕНТЫ1,2,3,4,5
    private View viewДляПервойКнопкиHome_Задания = null;
    private Bundle BungleДанныеДляViewCard;
    private Bundle BungleДанныеДляViewCardBungle;
    private Bundle BungleДанныеДляViewCardBungleID;
    private Bundle BungleДанныеДляViewCardДляпередачиCallsBaskПримечание;
    private  Cursor Курсор_ГлавныйКурсорДляЗадач;
    private  Cursor Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе = null;
    
    private FragmentManager fragmentManagerДляЗадачи;
    private FragmentTransaction fragmentTransactionляЗадачи;
    private Fragment fragment_ТекущийФрагмент;
    // TODO: 17.06.2022
    private Handler handlerTaskFragment5;
    private ProgressBar progressBarTaskFragment5;
    private WorkInfo WorkInfoИнформацияОЗапущенойСлужбеОдноразовая;
    // TODO: 27.06.2022
    private TextView textViewТекущаяЗадача;
    private BottomNavigationView bottomNavigationViewДляTasks;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationКонкретноКнопкаДобавить;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи;
    private  Integer ПубличныйIDДляФрагмента;
    private LinearLayout linearLayou;
    private  Context context;
    private  Integer СкемИдётПереписка;
    final private String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";
    // TODO: 28.06.2022


    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationПринудительныйОбмен;
    // TODO: 06.07.2022

    private ServiceForTasks service_for_tasks;
    // TODO: 14.07.2022



    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            // TODO: 16.04.2025
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());



            context=getContext();

            fragmentManagerДляЗадачи = getActivity().getSupportFragmentManager();

            BungleДанныеДляViewCard = new Bundle();
            BungleДанныеДляViewCardBungle = new Bundle();
            BungleДанныеДляViewCardBungleID = new Bundle();
            BungleДанныеДляViewCardДляпередачиCallsBaskПримечание = new Bundle();


            linearLayou = (LinearLayout) getActivity().findViewById(R.id.activity_main_fisrt_for_tasks);

            progressBarTaskFragment5 = (ProgressBar) view.findViewById(R.id.prograessbartaskfragmen1down); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА

            recyclerView = (RecyclerView) view.findViewById(R.id.recycleviewActiviTask);


            textViewТекущаяЗадача = (TextView) view.findViewById(R.id.activy_task_fragment1_tasksnameеtextview);
            // TODO: 14.03.2022 ССЫЛКА НА РОДИТЕЛЬСКОЕ ФРАГМЕНТ/
            textViewТекущаяЗадача.setText("Созданная Задача".toUpperCase());
            // TODO: 14.03.202
            // TODO: 27.06.2022
            bottomNavigationViewДляTasks = (BottomNavigationView) view.findViewById(R.id.bottomnavigationActiviTask8);

            // TODO: 14.03.2022  тут обьявляем три кнопки доьавить контроль и новая задача

            bottomNavigationКонкретноКнопкаДобавить = bottomNavigationViewДляTasks.findViewById(R.id.id_taskCreateNewTasks);

            // TODO: 14.03.2022  тут обьявляем три кнопки доьавить контроль и новая задача

            bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи = bottomNavigationViewДляTasks.findViewById(R.id.id_taskHome);

            bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи.setVisibility(View.GONE);
            // TODO: 16.03.2022
            bottomNavigationКонкретноКнопкаДобавить.setVisibility(View.VISIBLE);
            // TODO: 16.03.2022
            bottomNavigationКонкретноКнопкаДобавить.setTitle("Созданные");

            bottomNavigationПринудительныйОбмен = bottomNavigationViewДляTasks.findViewById(R.id.id_taskAsyns);


            bottomNavigationПринудительныйОбмен.setVisibility(View.GONE);

            // TODO: 15.03.2022 НЕ ПОКАЗЫВАЕМ
            bundleНаФрагменте5 = getArguments();
            /*   bottomNavigationКонкретноКнопкаДобавить.setVisibility(View.GONE);*/
            // TODO: 16.03.2022

            Log.d(this.getClass().getName(), "  Fragment2_Create_Tasks  viewДляПервойКнопкиHome_Задания ---/"
                    + viewДляПервойКнопкиHome_Задания + " recyclerViewДляСозданиеНовойЗадачи" +
                    "  bundleНаФрагменте5 " + bundleНаФрагменте5);

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


    // TODO: 12.03.2022
    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
            Log.d(this.getClass().getName(), " отработоатл new subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент5ЗаполенияЗадачиДляСозданияНовойЗадачиonDestroyView  " );
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
    public void onStop() {
        super.onStop();

        try {
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 onDestroyView  ");


            // WorkManager.getInstance(getContext()).cancelUniqueWork(ИмяСлужбыОдноразоваяСинхронизацииДляЗадачиИзЧата);

// TODO: 04.03.2022
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  ИмяСлужбыСинхронизацииДляЗадачиИзЧата  ");

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
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент5ЗаполенияЗадачиДляСозданияНовойЗадачи
                    = new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент5(getContext());
            Log.d(this.getClass().getName(), " отработоатл  subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент3ЗаполенияЗадачиДляРедактирования " +
                    "" + subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент5ЗаполенияЗадачиДляСозданияНовойЗадачи);

            // TODO: 02.03.2022
            ПубличныйIDДляФрагмента = new GetttingPublicID().getttingPublicID(getContext());
            

            // TODO: 15.03.2022
            Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента " +      ПубличныйIDДляФрагмента);

            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент5ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодПолученимТОлькоКоличествоЗадач(     ПубличныйIDДляФрагмента);

            Log.d(this.getClass().getName(), "Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);


            // TODO: 02.03.2022 получения курсора
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент5ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодПолучаемГлавныеДанныеДляЗадач(     ПубличныйIDДляФрагмента);

            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK  " + Курсор_ГлавныйКурсорДляЗадач);



            //todo метод  ИНИЦИАЛИЗАЦИИ RECYCLEVIEW ДЛЯ АКТИВТИ ЗАДАЧИ

            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент5ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодИнициализацииRecycleViewДляЗадач(viewДляПервойКнопкиHome_Задания);

            // TODO: 05.03.2022  ДЛЯ ИНИЗАЛИЗАЦИИ НИЖНИХ КНОПОК
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент5ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодСозданиеНавигаторКнопок();
            // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент5ЗаполенияЗадачиДляСозданияНовойЗадачи.
                    МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);

            Log.d(this.getClass().getName(), " нет данных для отображения " +
                    "отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK  МетодКпопкаСоЗачкомКраснымДополнительныйСтатус  " + Курсор_ГлавныйКурсорДляЗадач +
                    " Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);


            // TODO: 13.03.2022
          bottomNavigationViewДляTasks.requestLayout();
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

    private class SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент5  {
        // TODO: 28.02.2022
      private   Context context;
        public SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент5(Context context) {
            // TODO: 03.03.2022
        }

        // TODO: 28.02.2022 Под Класс порлучение данных для активти

     Cursor МетодПолучаемГлавныеДанныеДляЗадач(Integer ПубличноеIDПолученныйИзСервлетаДляUUID) {
            try {
                // TODO: 14.05.2025
                String Текущаятаблицы = "view_tasks";
                ModuleQuety moduleQuety = new ModuleQuety(context);
                Курсор_ГлавныйКурсорДляЗадач = moduleQuety.getModuleQuery(Текущаятаблицы,
                        " SELECT *  FROM " + Текущаятаблицы + " AS D" +
                                " WHERE   D.user_update='"+ПубличноеIDПолученныйИзСервлетаДляUUID+"'  AND D.message IS NOT NULL"
                                +"'  ORDER BY  D.status_write, D.date_update DESC ",
                        null);
                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Курсор_ГлавныйКурсорДляЗадач " + Курсор_ГлавныйКурсорДляЗадач+
                        " ПубличноеIDПолученныйИзСервлетаДляUUID " +ПубличноеIDПолученныйИзСервлетаДляUUID);
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

                if (viewДляПервойКнопкиHome_Задания != null) {
                    Log.d(this.getClass().getName(), " есть данные для отображения " +
                            "отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ГлавныйКурсорДляЗадач  " + Курсор_ГлавныйКурсорДляЗадач);
                    // TODO: 28.02.2022
                    recyclerView = (RecyclerView) viewДляПервойКнопкиHome_Задания.findViewById(R.id.recycleviewActiviTask);
                    // TODO: 14.03.2022
                    recyclerView.setVisibility(View.VISIBLE);
                    // TODO: 14.03.202
                  /*  GridLayoutManager gridLayoutManager;
                    // TODO: 14.03.202
                    gridLayoutManager = new GridLayoutManager(getActivity(), 1);*/
                    // TODO: 05.07.2022

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
                    myRecycleViewAdapter = new myRecycleViewAdapter(ОчередьДаныеДляСозданиеНовойЗадачи);

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
              bottomNavigationViewДляTasks.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        // TODO: 09.03.2022
                        try {

                            // TODO: 09.03.2022
                            fragmentTransactionляЗадачи = fragmentManagerДляЗадачи.beginTransaction();
                            // TODO: 11.03.2022
                            // TODO: 11.03.2022
                            Log.d(this.getClass().getName(), "  bottomNavigationViewДляTasks " +  bottomNavigationViewДляTasks + " fragment_ТекущийФрагмент " + fragment_ТекущийФрагмент);
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
                                  bottomNavigationViewДляTasks.requestLayout();
                                    // TODO: 14.03.2022
                                   linearLayou.requestLayout();
                                    Log.d(this.getClass().getName(), " bottomNavigationViewДляTasks.getChildCount() " +  bottomNavigationViewДляTasks.getChildCount());
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
                                  bottomNavigationViewДляTasks.requestLayout();
                                    // TODO: 14.03.2022
                                   linearLayou.requestLayout();
                                    Log.d(this.getClass().getName(), " bottomNavigationViewДляTasks.getChildCount() " +  bottomNavigationViewДляTasks.getChildCount());

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
                  bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskCreateNewTasks).setVisible(true);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true); R.id.id_taskHome todo R.id.id_taskCreateNewTasks
                    // TODO: 06.03.2022
                  bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskCreateNewTasks).setNumber(Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    // TODO: 09.03.2022
                  bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskCreateNewTasks).setBackgroundColor(Color.parseColor("#8B0000"));
                    // TODO: 06.03.2022
                    Log.d(this.getClass().getName(), "  bottomNavigationViewДляTasks " +  bottomNavigationViewДляTasks +
                            "  Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount()   " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount());
                    // TODO: 05.03.2022
                } else {
                    // TODO: 06.03.2022
                  bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskCreateNewTasks).setVisible(false);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    // TODO: 09.03.2022
                  bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskCreateNewTasks).setNumber(Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount());
                    // TODO: 10.03.2022
                  bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskCreateNewTasks).setBackgroundColor(Color.BLACK);
                }
                Log.d(this.getClass().getName(), "  bottomNavigationViewДляTasks " +  bottomNavigationViewДляTasks +
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
        protected Cursor МетодПолученимТОлькоКоличествоЗадач(Integer ПубличноеIDПолученныйИзСервлетаДляUUID) {
            try {
                // TODO: 14.05.2025
                String Текущаятаблицы = "view_tasks";
                ModuleQuety moduleQuety = new ModuleQuety(context);
                Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе = moduleQuety.getModuleQuery(Текущаятаблицы,
                        " SELECT *  FROM " + Текущаятаблицы + " AS D" +
                                " WHERE   D.user_update='"+ПубличноеIDПолученныйИзСервлетаДляUUID+"' AND D.status_write<> 'Удаленная' " +
                                " ORDER BY D.status_write,  D.date_update DESC ",
                        null);
                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
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
            private TextInputEditText textView1, textView2, textView3, textView5, textView6, textView8, textView7, textView11, textView9;
            // TODO: 17.03.2022
            // TODO: 13.03.2022
            private MaterialCardView materialCardView;
            // TODO: 16.03.202
            private MaterialButton materialButtonОтменаЗадачи;
            // TODO: 21.03.2022
            private Bundle bundleЗначенияДляНовойЗадачи;
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
                    textView1 = (TextInputEditText) itemView.findViewById(R.id.text1_innercardview);
                    // TODO: 06.06.2022
                    // TODO: 02.3.2022  дополнительный
                    textView2 = (TextInputEditText) itemView.findViewById(R.id.text3_innercardviewgretingtaskdown);
                    // TODO: 28.02.2022
                    textView8 = (TextInputEditText) itemView.findViewById(R.id.text9_innercardviewgretingtasfio);
                    // TODO: 28.02.2022
                    // TODO: 13.03.2022
                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 materialCardView  textView8 " + textView8);
                    // TODO: 01.03.2022
                    materialCardView = (MaterialCardView) itemView.findViewById(R.id.cardviewmatirealtask);
                    // TODO: 06.06.2022
                    materialCardView.setBackgroundColor(Color.parseColor("#DCDCDC"));
                    // TODO: 06.06.2022
                    materialCardView.setStrokeColor(Color.parseColor("#E0FFFF"));
                    // TODO: 06.06.2022
                    materialCardView.setEnabled(false);
                    // TODO: 18.03.2022
                    textView7 = (TextInputEditText) itemView.findViewById(R.id.text2_innercardviewtwoSettasksType);


                    // TODO: 28.02.2022--СТАТУС ЗАДАЧИ
                    textView6 = (TextInputEditText) itemView.findViewById(R.id.text8_innercardviewtextinutstatus_taskcreatingdown);


                    // TODO: 15.06.2022 ПРИМЕЧАЕИ ЗАДАЧИ
                    textView9 = (TextInputEditText) itemView.findViewById(R.id.text12_innercardviewtextinutstatus_samoprimechanie);

                    // TODO: 15.06.2022 дата уже созданной задачи ЗАДАЧИ
                    textView11 = (TextInputEditText) itemView.findViewById(R.id.text11_innercardviewgretingta_valuedate);

// TODO: 17.06.2022  кнопка отмены задачи созданной мной

                    materialButtonОтменаЗадачи = (MaterialButton) itemView.findViewById(R.id.BottomFor_task_cancel_creating_task);


                    // TODO: 28.02.2022
                    bundleЗначенияДляНовойЗадачи = new Bundle();

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


        // TODO: 16.03.2022


        class myRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
            // TODO: 04.03.2022
            LinkedBlockingQueue ОчередьДаныеДляСозданиеНовойЗадачиВнутри;

            // TODO: 15.03.2022 первыЙ КЛАССС ДЛЯ АДАПТЕРА С ДАННЫМИ ПОДНИМАЕМ ДАННЫЕ ДЛЯ РЕДАКТИРОВАНИЯ
            public myRecycleViewAdapter(@NotNull LinkedBlockingQueue ОчередьДаныеДляСозданиеНовойЗадачи) {
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
                    viewГлавныйВидДляRecyclleViewДляЗаданий = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_viewcreatedtasks, parent, false);//todo old simple_for_new_takst_cardview3       R.layout.simple_for_takst_cardview1
                    // TODO: 05.03.2022
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляЗаданий" + viewГлавныйВидДляRecyclleViewДляЗаданий);
                    // TODO: 28.02.2022

                    // TODO: 22.03.2022
                    myViewHolder = new MyViewHolder(viewГлавныйВидДляRecyclleViewДляЗаданий);

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


                    // TODO: 14.03.2022  метод создания само сообщения
                    МетодБиндингаСозданоеЗадания(holder);


                    // TODO: 14.03.2022  метод создания номер задания
                    МетодБиндингаСамаСозданаяМнойЗадания(holder);


                    // TODO: 14.03.2022  метод создания spinnerДляСозданиеНовойЗадачи
                    МетодБиндингаФИОСозданогоЗаданияМной(holder);


                    // TODO: 13.03.2022 СЛУШАТЕЛЬ для ДОЛГОВО НАЖАТИЯ СМЕНЫ СТАТУСА


                    МетодБиндингСтатусЗадачиСозданойМной(holder);


                    // TODO: 13.03.2022  метод инизадихзации примечания задачи

                    МетодБиндингПримечаениеСозданнойЗадачи(holder);


                    // TODO: 13.03.2022  метод инизадихзации ДАТЫ уже созданой задачи

                    МетодБиндингДатаСозданнойЗадачи(holder);


                    // TODO: 13.03.2022  метод инизадихзации ТИП уже созданой задачи

                    МетодБиндингТипСозданнойЗадачи(holder);


                    // TODO: 13.03.2022 метод котрый может отменить созданую задачу созданой задачи

                    МетодБиндингКнопкаОтменыСозданойЗадачиСозданнойЗадачи(holder);

                    // TODO: 13.03.2022 настройки для carview КОНЕЦ ВЫЗЫВАЕМЫХ МЕТОДОВ вызывает событие клика на самой cardview 


                    // TODO: 17.06.2022  ПОлучаем UUID для текущей созданной задачи на фрагменте5
                    МетодБиндингаЗаполненияДаннымиBungle(holder);
                    

                  /*  holder.materialCardView.toggle();
()
                    // TODO: 13.03.2022
                    holder.materialCardView.setChecked(true);*/
                    // TODO: 15.03.2022
                    // TODO: 15.03.2022
                    //  holder.materialCardView.setCardBackgroundColor(Color.parseColor("#FFFAFA"));

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


            private void МетодБиндингаЗаполненияДаннымиBungle(@NonNull MyViewHolder holder) {
                // TODO: 03.03.2022
                try {


                    Log.i(this.getClass().getName(), "  BungleДанныеДляViewCard   " + bundleНаФрагменте5);

                    // TODO: 13.03.2022  передаем статус задачи
                    Long ПолучаемыйUUIDСозданнойТекущейЗадачи = bundleНаФрагменте5.getLong("ДляФрагмента5_UUIDЗадачиСозданной");

                    // TODO: 03.03.2022 передаем помер позиции position
                    holder.materialCardView.setTag(holder.materialCardView.getId(), ПолучаемыйUUIDСозданнойТекущейЗадачи);


                    // TODO: 17.06.2022  получаем ID скем мы вообезм перрпииываемся
                    // TODO: 13.03.2022  передаем статус задачи
                    СкемИдётПереписка = bundleНаФрагменте5.getInt("СкемИдётПерепискаФрагмент5");
                    Log.i(this.getClass().getName(), "  СкемИдётПереписка   " + СкемИдётПереписка + " ПолучаемыйUUIDСозданнойТекущейЗадачи " + ПолучаемыйUUIDСозданнойТекущейЗадачи);


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
                    holder.textView5.setText("тип: ");
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


            private void МетодБиндингСтатусЗадачиСозданойМной(@NonNull MyViewHolder holder) throws ExecutionException, InterruptedException {
                try {
                    // TODO: 02.03.2022#4  // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4

                    Log.i(this.getClass().getName(), "  bundleНаФрагменте5 " + bundleНаФрагменте5);
                    // TODO: 28.02.2022
                    String ТипСозданойЗадачиПериод = bundleНаФрагменте5.getString("ДляФрагмента5_СтатусЗадачиСозданной", "");

                    // TODO: 06.06.2022
                    if (ТипСозданойЗадачиПериод.length() > 0) {

                        holder.textView6.setText(ТипСозданойЗадачиПериод);

                    } else {
                        holder.textView6.setText("");
                        holder.textView6.setHint("В процессе...");
                        holder.textView6.setHighlightColor(Color.GRAY);
                    }
// TODO: 28.02.2022
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
            }


            private void МетодБиндингПримечаениеСозданнойЗадачи(@NonNull MyViewHolder holder) throws ExecutionException, InterruptedException {
                try {
                    // TODO: 02.03.2022#4  // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4

                    Log.i(this.getClass().getName(), "  bundleНаФрагменте5 " + bundleНаФрагменте5);
                    // TODO: 28.02.2022
                    String ТипСозданойЗадачиПериод = bundleНаФрагменте5.getString("ДляФрагмента5_ПримечаниеОтказИлиВыполнили", "");

                    if (ТипСозданойЗадачиПериод.length() > 0) {
                        holder.textView9.setText(ТипСозданойЗадачиПериод);
                    } else {
                        holder.textView9.setText("");
                        holder.textView9.setHint("Примечание нет");
                        holder.textView9.setHighlightColor(Color.GRAY);
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

            private void МетодБиндингДатаСозданнойЗадачи(@NonNull MyViewHolder holder) throws ExecutionException, InterruptedException {
                try {
                    // TODO: 02.03.2022#4  // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4

                    Log.i(this.getClass().getName(), "  bundleНаФрагменте5 " + bundleНаФрагменте5);
                    // TODO: 28.02.2022
                    String ДатаСозданойУжеЗадачи = bundleНаФрагменте5.getString("ДляФрагмента5_ДатаОтказИлиВыполнили", "");

                    holder.textView11.setText(ДатаСозданойУжеЗадачи);

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

            private void МетодБиндингКнопкаОтменыСозданойЗадачиСозданнойЗадачи(@NonNull MyViewHolder holder) throws ExecutionException, InterruptedException {
                try {
                    // TODO: 02.03.2022#4  // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4

                    Log.i(this.getClass().getName(), "  bundleНаФрагменте5 " + bundleНаФрагменте5);
                    // TODO: 28.02.2022
                    // TODO: 28.02.2022
                    String ТипСозданойЗадачиПериод = bundleНаФрагменте5.getString("ДляФрагмента5_СтатусЗадачиСозданной", "");


                    switch (ТипСозданойЗадачиПериод) {
                        // TODO: 17.06.2022
                        case "Отмененная":
                        case "Отказ":
                        case "Выполнена":
                            holder.materialButtonОтменаЗадачи.setEnabled(false);
                            // TODO: 20.06.2022
                            holder.materialButtonОтменаЗадачи.setVisibility(View.GONE);
                            Log.i(this.getClass().getName(), "          holder. materialButtonОтменаЗадачи.setEnabled(true); " + bundleНаФрагменте5);
                            break;
                        // TODO: 17.06.2022  
                        default: {
                            holder.materialButtonОтменаЗадачи.setEnabled(true);
                            // TODO: 20.06.2022
                            holder.materialButtonОтменаЗадачи.setVisibility(View.VISIBLE);
                            Log.i(this.getClass().getName(), "  holder. materialButtonОтменаЗадачи.setEnabled(false); " + bundleНаФрагменте5);
                            break;
                        }


                    }

                    // TODO: 17.06.2022  отмена зазадчи созданной  мной
                    holder.materialButtonОтменаЗадачи.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Log.i(this.getClass().getName(), "  кНОПКА ОТМЕНЫ ЗАДАЧИ КОТОРУ СОЗДАЛ Я    holder.materialButtonОтменаЗадачи " +
                                        "" +   holder.materialButtonОтменаЗадачи);
                       /*         Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                // Vibrate for 500 millisecond
                                v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.EFFECT_DOUBLE_CLICK));*/
                                // TODO: 17.06.2022 смена статуса отмена уже созданной мной задачи
                                holder.materialButtonОтменаЗадачи.animate().rotationX(20l);//.rotationXBy(4l);

                                handlerTaskFragment5.postDelayed(()->{
                                // TODO: 24.06.2022
                                handlerTaskFragment5.post(() -> {
                                    progressBarTaskFragment5.setVisibility(View.VISIBLE);
                                });
                                // TODO: 24.03.2022
                            final     Integer ВставляемСтатусКогдаПрименялирешениеотменыЗадачи = 3;//todo два  это Отмена уже Созданой ЗАДАЧИ

                                Object ПолученныйUUIDДляТекущегоФрагмента5 = holder.materialCardView.getTag(holder.materialCardView.getId());

                                Log.i(this.getClass().getName(), "  кНОПКА ОТМЕНЫ ЗАДАЧИ КОТОРУ СОЗДАЛ Я  ПолученныйUUIDДляТекущегоФрагмента5" + ПолученныйUUIDДляТекущегоФрагмента5);

                                    try {
                                        if (!WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().isEmpty()) {
                                            WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                                    WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                                            while (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING) == 0) {
                                                WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                                        WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                                                handlerTaskFragment5.post(() -> {
                                                    progressBarTaskFragment5.setVisibility(View.VISIBLE);
                                                });
                                            }
                                        }
                                    } catch (ExecutionException e) {
                                        e.printStackTrace();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                    // TODO: 17.06.2022  метод отмены созданнйо мной задачи  ОТМЕНА ЗАДАЧИ
                                МетодОтменыСозданнойМнойЗадачиФрагмент2(ВставляемСтатусКогдаПрименялирешениеотменыЗадачи,
                                        Long.parseLong(ПолученныйUUIDДляТекущегоФрагмента5.toString())
                                        , holder);
                                    Log.i(this.getClass().getName(), "  holder.materialButtonОтменаЗадачи " + holder.materialButtonОтменаЗадачи);

                                },500);

                                // TODO: 17.06.2022
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
                    });


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


            // TODO: 17.06.2022  метод отмены созданнйо мной задачи
            private void МетодОтменыСозданнойМнойЗадачиФрагмент2(@NonNull Integer ВставляемСтатусКогдаПрименялирешениеотменыЗадачи,
                                                                 @NonNull Long ПолучаемUUIDТекущйПозицииВRecyreView
                    , MyViewHolder holder) {

                Boolean РезультатСменыСтатусаНаОзнакомленный = false;
                try {


                    Intent intentДляЗапускаСлужбыСменыСменыСтатусаВсСлужбе=new Intent();
                    Bundle bundleДляПередачиВСлужбыСменыСтатуса=new Bundle();

                    bundleДляПередачиВСлужбыСменыСтатуса.putString("PROCESS_ID_Задачи","23");
                    bundleДляПередачиВСлужбыСменыСтатуса.putLong("UUIDПолучениейЗадачи",ПолучаемUUIDТекущйПозицииВRecyreView);
                    bundleДляПередачиВСлужбыСменыСтатуса.putInt("ДляЗадачиПередаемФлагВыполненаЗадчаИлиОтказ",ВставляемСтатусКогдаПрименялирешениеотменыЗадачи);
                    bundleДляПередачиВСлужбыСменыСтатуса.putString("СамоПримечание","");
                    bundleДляПередачиВСлужбыСменыСтатуса.putString("ИмяСлужбыУведомленияДляЧата_Задачи","WorkManager NOtofocationForTasksOnlyDissenOrCompliete");
                    intentДляЗапускаСлужбыСменыСменыСтатусаВсСлужбе.setAction("ЗапускаемИзмененияСатусазадачиВыполнил");
                    intentДляЗапускаСлужбыСменыСменыСтатусаВсСлужбе.putExtras(bundleДляПередачиВСлужбыСменыСтатуса);
// TODO: 05.07.2022 меняет статус
                    РезультатСменыСтатусаНаОзнакомленный=        service_for_tasks.МетодpЗапускСлужбыДляЗадач(getContext(),intentДляЗапускаСлужбыСменыСменыСтатусаВсСлужбе);

                    if (РезультатСменыСтатусаНаОзнакомленный == true) {
                        // TODO: 09.03.2022
                        Log.w(context.getClass().getName(), " Вставлен Отменная РезультатСменыСтатусаНаОзнакомленный " +
                                РезультатСменыСтатусаНаОзнакомленный);
                        // TODO: 15.03.2022
                        holder.materialButtonОтменаЗадачи.setEnabled(false);

                        // TODO: 14.01.2022
                        Message message = new Message();
                        Bundle bundle=new Bundle();
                        bundle.putString("ОперациЯПрошлаЧат","Успешный обмен данными !!!");
                        message.setData(bundle);
                        handlerTaskFragment5.sendMessage(message);


                    } else {
                        Log.w(context.getClass().getName(), " Вставлен Отменная РезультатСменыСтатусаНаОзнакомленный " +
                                РезультатСменыСтатусаНаОзнакомленный);

                        Toast.makeText(getContext(), "Смена статуса на Отмененная не прошла !!! "
                                + "\n", Toast.LENGTH_LONG).show();
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


            private void МетодБиндингТипСозданнойЗадачи(@NonNull MyViewHolder holder) throws ExecutionException, InterruptedException {
                try {
                    // TODO: 02.03.2022#4  // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4 // TODO: 02.03.2022#4

                    Log.i(this.getClass().getName(), "  bundleНаФрагменте5 " + bundleНаФрагменте5);
                    // TODO: 28.02.2022
                    String ТипСозданойУжеЗадачи = bundleНаФрагменте5.getString("ДляФрагмента5_ПериодЗадачиСозданной", "");

                    holder.textView7.setText(ТипСозданойУжеЗадачи);

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


            // TODO: 18.03.2022  метод делаем слушатель на spinner

            void МетодБиндингаФИОСозданогоЗаданияМной(@NonNull MyViewHolder holder) throws ParseException {
                try {
                    // TODO: 18.03.2022
                    Log.e(this.getClass().getName(), "МетодБиндингаДелаемСлушательДляSpinnerЗадания  bundleНаФрагменте5  " + bundleНаФрагменте5);
                    //
                    // TODO: 18.03.2022
                    String ФИОДляКогоСозданаяЗадача = bundleНаФрагменте5.getString("ДляФрагмента5_ФИОДЛяКогоСозданаЗадача");


                    holder.textView8.setText(ФИОДляКогоСозданаяЗадача.replace("кому:", "").replace("кому:", ""));

                    // TODO: 17.03.2022


                    // TODO: 18.03.2022
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
            // TODO: 18.03.2022  метод СОДАЕМ СЛУШАТЕЛЬ ДЛЯ ВЫБОРА ПЕРИОДА ЗАДАЧА SEELBAR


            private void МетодБиндингаСамаСозданаяМнойЗадания(@NonNull MyViewHolder holder) {
                try {
                    // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2

                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  bundleНаФрагменте5 " + bundleНаФрагменте5);
                    // TODO: 17.03.2022

                    String СамаСозданаМномЗадния = bundleНаФрагменте5.getString("ДляФрагмента5_СамаСозданнаяМнойЗадача");

                    holder.textView2.setText(СамаСозданаМномЗадния.trim());//IDЗадачиТекущей


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

            private void МетодБиндингаСозданоеЗадания(@NonNull MyViewHolder holder) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  СамогоСообщенияЗадачиДляПользователя bundleНаФрагменте5" + bundleНаФрагменте5);

                    String НазваниеСоздаваниемЗадачи = bundleНаФрагменте5.getString("ДляФрагмента5_НазваниеСозданойМнейЗадача");
                    // TODO: 28.02.2022
                    holder.textView1.setText(НазваниеСоздаваниемЗадачи.trim());//СамогоСообщенияЗадачиДляПользователя

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


            // TODO: 13.03.2022


            private void МетодБиндингаОдинКликПоСозданомуЗаданию(MyViewHolder holder) {
                // TODO: 01.03.2022 слушатели

                try {

// TODO: 01.03.2022 слушатели

                    holder.materialCardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            // TODO: 13.03.2022
                            Log.d(this.getClass().getName(), "   private void МетодБиндингаОдинКликПоСозданомуЗаданию(MyViewHolderДляЧата holder) {" +
                                    "  один клик  v" + v);


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


            @Override
            public long getItemId(int position) {
                // TODO: 04.03.2022

                Log.i(this.getClass().getName(), "     getItemId holder.position  новая задача " + position);

                return super.getItemId(position);

            }




            @Override
            public int getItemCount() {

                // TODO: 02.03.2022
                ////////

                Log.d(this.getClass().getName(), "Курсор_ДляПолученияДАнныхДляЗАДАЧTASK " + ОчередьДаныеДляСозданиеНовойЗадачиВнутри);
                // TODO: 28.02.2022
                return ОчередьДаныеДляСозданиеНовойЗадачиВнутри.size();
            }

            @Override
            public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

                recyclerView.removeAllViews();

                recyclerView.getRecycledViewPool().clear();
                super.onAttachedToRecyclerView(recyclerView);
            }
        }
        }


        // TODO: 21.03.2022  класс создаения класс после получения данных из  Bungle





        private ServiceConnection connectionДляСменыСтатусаЗадач = new ServiceConnection() {
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





























