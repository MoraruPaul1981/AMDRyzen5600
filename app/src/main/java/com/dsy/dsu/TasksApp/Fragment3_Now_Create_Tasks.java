package com.dsy.dsu.TasksApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
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



import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;
import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.GreatUuidGenerations.GreatUuidGeneration;

import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.sous.backasync.launch.ModuleInserting;
import com.sous.backasync.launch.ModuleQuety;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class Fragment3_Now_Create_Tasks extends Fragment {
    // TODO: 15.03.2022
    private RecyclerView recyclerView;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент3 subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент3ЗаполенияЗадачиДляСозданияНовойЗадачи;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент3.MyRecycleViewAdapter myRecycleViewAdapter;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент3.MyViewHolder myViewHolder;
    private  AccessibilityNodeInfo accessibilityNodeInfoДополнительныеДанные;
    // TODO: 01.03.2022--перпменные для переноса в другие ФОАГМЕНТЫ1,2,3,4,5
    private View viewДляПервойКнопкиHome_Задания = null;
    private Bundle BungleДанныеДляViewCard;
    private Bundle BungleДанныеДляViewCardBungle;
    private Bundle BungleДанныеДляViewCardBungleID;
    private Bundle BungleДанныеДляViewCardДляпередачиCallsBaskПримечание;
    private Cursor Курсор_ГлавныйКурсорДляЗадач;
    private SQLiteCursor Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе = null;

    private Integer ПубличныйIDДляЗаданияКомуПисать;
    private FragmentManager fragmentManagerДляЗадачи;
    private FragmentTransaction fragmentTransactionляЗадачи;
    private Fragment fragment_ТекущийФрагмент;

    private WorkInfo WorkInfoИнформацияОЗапущенойСлужбеОдноразовая;
    private String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";

/*    private WorkInfo WorkInfoИнформацияОЗапущенойСлужбеОБЩАЯ;
    final private String ИмяСлужбыWorkmanagerДляВсехЗадачОБЩАЯ = "WorkManager Synchronizasiy_Data";*/
    private Handler handlerTaskFragment3;
    private ProgressBar progressBarTaskFragment3;

    private Integer ПубличныйIDДляФрагмента;
    private BottomNavigationView bottomNavigationViewДляTasks;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationКонкретноКнопкаДобавить;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи;
    private TextView textViewТекущаяЗадача;
    private LinearLayout linearLayou;

    // TODO: 04.07.2022
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationПринудительныйОбмен;





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            // TODO: 16.04.2025
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());



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

            fragmentManagerДляЗадачи = getActivity().getSupportFragmentManager();

            recyclerView= (RecyclerView) view.findViewById(R.id.recycleviewActiviTask);

            // TODO: 06.03.2022
            bottomNavigationViewДляTasks = (BottomNavigationView) view.findViewById(R.id.bottomnavigationActiviTask8);

            // TODO: 14.03.2022  тут обьявляем три кнопки доьавить контроль и новая задача

            bottomNavigationКонкретноКнопкаДобавить = bottomNavigationViewДляTasks.findViewById(R.id.id_taskCreateNewTasks);

            // TODO: 14.03.2022  тут обьявляем три кнопки доьавить контроль и новая задача

            bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи = bottomNavigationViewДляTasks.findViewById(R.id.id_taskHome);


            textViewТекущаяЗадача = (TextView) view.findViewById(R.id.activy_task_fragment1_tasksnameеtextview);

            Log.d(this.getClass().getName(), "  Fragment1_One_Tasks  textViewТекущаяЗадача " + textViewТекущаяЗадача + " view " + view);
            // TODO: 14.03.2022
            
            // TODO: 14.03.2022 ССЫЛКА НА РОДИТЕЛЬСКОЕ ФРАГМЕНТ/
            textViewТекущаяЗадача.setText("Новая задача".toUpperCase());
            // TODO: 14.03.202


            bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи.setVisibility(View.GONE);
            // TODO: 16.03.2022
            bottomNavigationКонкретноКнопкаДобавить.setVisibility(View.VISIBLE);
            // TODO: 16.03.2022
            bottomNavigationКонкретноКнопкаДобавить.setTitle("Созданные");

            // TODO: 15.03.2022 НЕ ПОКАЗЫВАЕМ
            /*   bottomNavigationКонкретноКнопкаДобавить.setVisibility(View.GONE);*/
            handlerTaskFragment3=new Handler(Looper.getMainLooper());

            progressBarTaskFragment3 = (ProgressBar) view.findViewById(R.id.prograessbartaskfragmen1down); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА


            // TODO: 14.03.2022

            linearLayou = (LinearLayout) getActivity().findViewById(R.id.activity_main_fisrt_for_tasks);

            bottomNavigationПринудительныйОбмен = bottomNavigationViewДляTasks.findViewById(R.id.id_taskAsyns);


            bottomNavigationПринудительныйОбмен.setVisibility(View.GONE);




           BungleДанныеДляViewCard=new Bundle();

            BungleДанныеДляViewCardBungle=new Bundle()
            ;
           BungleДанныеДляViewCardBungleID=new Bundle();

             BungleДанныеДляViewCardДляпередачиCallsBaskПримечание=new Bundle();

            // TODO: 16.03.2022

            Log.d(this.getClass().getName(), "  Fragment2_Create_Tasks  viewДляПервойКнопкиHome_Задания ---/" + viewДляПервойКнопкиHome_Задания + " recyclerViewДляСозданиеНовойЗадачи");

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
    public void onStop() {
        super.onStop();

        try {
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 onDestroyView  " );

            /////////////
          //  WorkManager.getInstance(getContext()).cancelUniqueWork(ИмяСлужбыОдноразоваяСинхронизацииДляЗадачиИзЧата);

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
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент3ЗаполенияЗадачиДляСозданияНовойЗадачи = new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент3(getContext(), getActivity());

            // TODO: 12.03.2022
            Log.d(this.getClass().getName(), " отработоатл  subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент3ЗаполенияЗадачиДляРедактирования " +
                    "" + subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент3ЗаполенияЗадачиДляСозданияНовойЗадачи);



// TODO: 02.03.2022
            ПубличныйIDДляФрагмента = new GetttingPublicID().getttingPublicID(getContext());
            // TODO: 15.03.202
            Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента " +     ПубличныйIDДляФрагмента );

            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент3ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодПолученимТОлькоКоличествоЗадач(    ПубличныйIDДляФрагмента );

            Log.d(this.getClass().getName(), "Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);


            // TODO: 02.03.2022 получения курсора
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент3ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодПолучаемГлавныеДанныеДляЗадач(    ПубличныйIDДляФрагмента );

            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK  " + Курсор_ГлавныйКурсорДляЗадач);


            //todo метод  ИНИЦИАЛИЗАЦИИ RECYCLEVIEW ДЛЯ АКТИВТИ ЗАДАЧИ

            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент3ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодИнициализацииRecycleViewДляЗадач(viewДляПервойКнопкиHome_Задания);

            // TODO: 05.03.2022  ДЛЯ ИНИЗАЛИЗАЦИИ НИЖНИХ КНОПОК
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент3ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодСозданиеНавигаторКнопок();

            // TODO: 17.03.2022

            // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
            subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент3ЗаполенияЗадачиДляСозданияНовойЗадачи.МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);


            Log.d(this.getClass().getName(), " нет данных для отображения " +
                    "отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK  МетодКпопкаСоЗачкомКраснымДополнительныйСтатус  " + Курсор_ГлавныйКурсорДляЗадач +
                    " Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);


            // TODO: 22.03.2022 obsrver

            // TODO: 13.03.2022
           bottomNavigationViewДляTasks.requestLayout();
            // TODO: 13.03.202
            recyclerView.requestLayout();
            recyclerView.forceLayout();
            // TODO: 14.03.2022
            linearLayou.requestLayout();//



         /*   Operation lifecycleOwner=     WorkManager.getInstance(getContext()).cancelUniqueWork(ИмяСлужбыСинхронизацииОдноразовая);

            while(!lifecycleOwner.getResult().isDone()) ;

            Log.d(this.getClass().getName(), "  lifecycleOwner.getResult().get() " + lifecycleOwner.getResult().get());*/

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

   private class SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент3 {
        // TODO: 28.02.2022
        Context context;
       
       
        public SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент3(Context context, Activity activity) {
            // TODO: 03.03.2022
            this.context=context;
            Log.d(this.getClass().getName(), "SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент4  " + context.getClass().getName());
        }

        // TODO: 28.02.2022 Под Класс порлучение данных для активти

        Cursor МетодПолучаемГлавныеДанныеДляЗадач(Integer ПубличноеIDПолученныйИзСервлетаДляUUID) {
            Cursor Курсор_ГлавныйКурсорДляЗадач=null;
            try {
                // TODO: 15.05.202
                String Текущаятаблицы="view_tasks";
                ModuleQuety moduleQuety=new ModuleQuety(getContext());
                Курсор_ГлавныйКурсорДляЗадач= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT *  FROM "+Текущаятаблицы+" AS D" +
                        "  WHERE D.user_update'"+ПубличноеIDПолученныйИзСервлетаДляUUID+"' AND D.message IS NOT NULL "+
                        "   ORDER BY D.status_write, D.date_update DESC " ,null);

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
// TODO: 17.03.2022 ЕЩЕ КУРСОР ДЛЯ СПИНЕРА фио ДЛЯ КОГО СОЗДАНО ЗАДАНИЕ


        // TODO: 04.03.2022  класс в котором находяться слушатели





        // TODO: 04.03.2022 прозвомжность инициализации RecycleView

        void МетодИнициализацииRecycleViewДляЗадач(@NonNull View viewДляПервойКнопкиHome_Задания) {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK  " + Курсор_ГлавныйКурсорДляЗадач);

                if (   viewДляПервойКнопкиHome_Задания!=null ) {
                    Log.d(this.getClass().getName(), " есть данные для отображения " +
                            "отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  Курсор_ГлавныйКурсорДляЗадач  " + Курсор_ГлавныйКурсорДляЗадач);
                    // TODO: 28.02.2022
                    recyclerView = (RecyclerView) viewДляПервойКнопкиHome_Задания.findViewById(R.id.recycleviewActiviTask);
                    // TODO: 14.03.2022
                    recyclerView.setVisibility(View.VISIBLE);
                    // TODO: 14.03.202
         /*           GridLayoutManager gridLayoutManager;
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
                                    Log.d(this.getClass().getName(), " bottomNavigationViewДляTasks.getChildCount() " + bottomNavigationViewДляTasks.getChildCount());
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
                                    Log.d(this.getClass().getName(), " bottomNavigationViewДляTasks.getChildCount() " + bottomNavigationViewДляTasks.getChildCount());

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
                    Log.d(this.getClass().getName(), "  bottomNavigationViewДляTasks " + bottomNavigationViewДляTasks +
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
                Log.d(this.getClass().getName(), "  bottomNavigationViewДляTasks " + bottomNavigationViewДляTasks +
                        "  Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount()   " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
                // TODO: 13.03.2022
                bottomNavigationViewДляTasks.requestLayout();
                // TODO: 13.03.2022
                bottomNavigationViewДляTasks.requestApplyInsets();
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

        // TODO: 14.03.2022
        protected Cursor МетодПолученимТОлькоКоличествоЗадач(Integer ПубличноеIDПолученныйИзСервлетаДляUUID) throws ExecutionException, InterruptedException {
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
          Cursor  getКурсор_ДляПолученияДАнныхТОлькоДляЗадачВработе = null;
            try {
                // TODO: 15.05.202
                String Текущаятаблицы="view_tasks";
                ModuleQuety moduleQuety=new ModuleQuety(getContext());
                getКурсор_ДляПолученияДАнныхТОлькоДляЗадачВработе= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT *  FROM "+Текущаятаблицы+" AS D" +
                        "  WHERE D.user_update='"+ПубличноеIDПолученныйИзСервлетаДляUUID+"' AND D.status_write<>'"+ПубличноеIDПолученныйИзСервлетаДляUUID+"' AND D.message IS NOT NULL "+
                        "   ORDER BY D.status_write, D.date_update DESC " ,null);

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
            return getКурсор_ДляПолученияДАнныхТОлькоДляЗадачВработе;
        }


        // TODO: 15.03.2022  перенесееный код
        // TODO: 28.02.2022 начало  MyViewHolderДляЧата
        protected class MyViewHolder extends RecyclerView.ViewHolder {// TODO: 28.02.2022 начало  MyViewHolderДляЧата
            // TODO: 28.02.2022
            TextInputEditText textView1, textView3, textView5;
            // TODO: 17.03.2022
            // TODO: 28.02.2022
            TextView textView2;
            // TODO: 13.03.2022
            MaterialCardView materialCardView;
            // TODO: 16.03.202
            Button buttonДляСозданиеНовогоЗадания;
            // TODO: 02.03.2022
            Spinner spinnerДляСозданиеНовойЗадачи;
            // TODO: 18.03.2022
            SeekBar seekBarДляВЫбораКакаяЗадачаВыполяется;
            // TODO: 18.03.2022
            TextView textView7;
            // TODO: 21.03.2022
            Bundle bundleЗначенияДляНовойЗадачи;
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
                    // TODO: 02.3.2022  дополнительный
                    textView2 = (TextView) itemView.findViewById(R.id.text2_innercardviewtwo);
                    // TODO: 28.02.2022
                    textView3 = (TextInputEditText) itemView.findViewById(R.id.text3_innercardviewtree);
                    // TODO: 28.02.2022
                    spinnerДляСозданиеНовойЗадачи = (Spinner) itemView.findViewById(R.id.Spinner_innercardviewfour);

                     //TODO: 13.03.2022
                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 materialCardView  textView2 " + spinnerДляСозданиеНовойЗадачи);
                    // TODO: 01.03.2022
                    materialCardView = (MaterialCardView) itemView.findViewById(R.id.cardviewmatirealtask);
                    // TODO: 13.03.2022
                    buttonДляСозданиеНовогоЗадания = (Button) itemView.findViewById(R.id.BottomFor_New_Create_Task);

                    // TODO: 16.03.2022
                    buttonДляСозданиеНовогоЗадания.setBackgroundResource(R.drawable.style_for_task_new_create_save);
                    // TODO: 18.03.2022

                    seekBarДляВЫбораКакаяЗадачаВыполяется = (SeekBar) itemView.findViewById(R.id.seekBar_for_tasks_create_new);
                    // TODO: 18.03.2022
                    textView7 = (TextView) itemView.findViewById(R.id.text2_innercardviewtwoSettasksType);
                    // TODO: 21.03.2022
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
                    viewГлавныйВидДляRecyclleViewДляЗаданий = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_new_takst_cardview3, parent, false);//todo old simple_for_new_takst_cardview3       R.layout.simple_for_takst_cardview1
                    // TODO: 05.03.2022
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляЗаданий" + viewГлавныйВидДляRecyclleViewДляЗаданий);
                    // TODO: 28.02.2022

                    // TODO: 22.03.2022
                        myViewHolder = new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент3.MyViewHolder(viewГлавныйВидДляRecyclleViewДляЗаданий);

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
                    МетодБиндингаСозданиеСамоСообщения(holder);


                    // TODO: 14.03.2022  метод создания номер задания
                    МетодБиндингаНомерЗадания(holder);


                    // TODO: 14.03.2022  метод создания spinnerДляСозданиеНовойЗадачи
                    МетодБиндингаДелаемСлушательДляSpinnerЗадания(holder);

                    // TODO: 14.03.2022  метод создания spinnerДляСозданиеНовойЗадачи
                    МетодБиндингаДатаЗадания(holder);


                    // TODO: 14.03.2022  метод создания ФИО задания
                    МетодБиндингаФИОДляЗадания(holder);


                    // TODO: 13.03.2022 СЛУШАТЕЛЬ для ДОЛГОВО НАЖАТИЯ СМЕНЫ СТАТУСА

                    МетодБиндингаСлушателейДляViewCard(holder);


                    // TODO: 13.03.2022 СЛУШАТЕЛЬ СРАБАТЫВАЕТ КОГДА КОМАДА TOGGER И МЕНЯЕМ СТАТУСТ ЧЕК ОЗНАКОМЛЕНЫЙ ЛИ ИНЕТ

                    МетодБиндингаСлушательisChered(holder);


                    // TODO: 16.03.2022  метод для слушателя для создания данных 
                    МетодБиндингаСлушательДляКнопкиСоздатьНовуюЗадачу(holder);


                    // TODO: 03.03.2022 ПОЛУЧАЕМ СТАТУС ЗАДАНИЯ ПРОЧИТАН ИЛИ НЕТ

                    Integer СамСтатусПрочтенияИлиНет = МетодБиндингаПолученияСтатусаЗадачи(holder);


                    // TODO: 02.03.2022#5  получаем ТИП ЗАДАЧИ
                    //МетодБиндингПолучаемТипЗадания(holder);


                    // TODO: 02.03.2022#5  заполем ДАННЫМИ BUNGLE САМОЙ ЗАДАЧИ//

                    МетодБиндингаЗаполненияДаннымиBungle(holder, СамСтатусПрочтенияИлиНет);

                    Log.i(this.getClass().getName(), "      holder.textView1  accessibilityNodeInfo " + BungleДанныеДляViewCard + " СамСтатусПрочтенияИлиНет " + СамСтатусПрочтенияИлиНет);


                    МетодБиндингаДелаемСлушательДляSeedBars(holder);

                    Log.i(this.getClass().getName(), "      МетодБиндингаДелаемСлушательДляSeedBars  holder.seekBarДляВЫбораКакаяЗадачаВыполяется  " + holder.seekBarДляВЫбораКакаяЗадачаВыполяется);


                    // TODO: 13.03.2022 настройки для carview КОНЕЦ ВЫЗЫВАЕМЫХ МЕТОДОВ вызывает событие клика на самой cardview 

                    holder.materialCardView.toggle();

                    // TODO: 13.03.2022
                    holder.materialCardView.setChecked(true);
                    // TODO: 15.03.2022
                    // TODO: 15.03.2022
                    holder.materialCardView.setCardBackgroundColor(Color.parseColor("#FFFAFA"));

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



            @NonNull

            private Integer МетодБиндингаПолученияСтатусаЗадачи(@NonNull MyViewHolder holder) {
                // TODO: 02.03.2022#5
                Integer СамСтатусПрочтенияИлиНет = 0;
                try {
                    // TODO: 03.03.2022

                    holder.textView1.setTag(СамСтатусПрочтенияИлиНет);


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
                    Integer ПозицияДанныхВSpinner = 0;
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  КтоНаписалСообщениеФИОдЛПосика ");
                    // TODO: 02.03.2022
                    String ФИОКотоНаписал = new String();
                    // TODO: 13.03.2022
                    Cursor sqLiteCursorПолученимНАстоящийФИО = МетодПолучениеДанныхФИОаОснованииID(1);//КтоНаписалСообщениеФИОдЛПосика
                    // TODO: 02.03.2022
                    Log.d(this.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + " sqLiteCursorПолученимНАстоящийФИО " +sqLiteCursorПолученимНАстоящийФИО);

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
                    holder.textView3.setText("");
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
                    // TODO: 17.03.2022
                    Cursor   sqLiteCursorПолученныйФИОДЛЯSpinnerДляНовойЗадачи = new КлассАдаптерДляСпинера(getContext()).МетодПолучаемГлавныеДанныеДляSpinnerКомуЗадачаФИО();
///TODO ГЛАВНЫЙ АДАПТЕР чата
                    SimpleCursorAdapter АдаптерДляФИОПриСозданииНовойЗадачи = new SimpleCursorAdapter(getContext(), android.R.layout.simple_spinner_item,
                            sqLiteCursorПолученныйФИОДЛЯSpinnerДляНовойЗадачи,
                            new String[]{"name"},
                            new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

                    // TODO: 17.03.2022
                    SimpleCursorAdapter.ViewBinder БиндингДляSpinnerФИОСозданиеНовойЗадачи = new SimpleCursorAdapter.ViewBinder() {
                        @Override
                        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                            // TODO: 18.03.2022  получаем ФИО людей для кого задание
                            String ФИОДляПОиска = null;

                            if (cursor.getCount()>0) {

                                    // TODO: 21.03.2022
                                    int ИндексФИО = cursor.getColumnIndex("name");

                                    ФИОДляПОиска = cursor.getString(ИндексФИО);
                                    ////
                                    Log.d(this.getClass().getName(), " ФИОДляПОиска  " + ФИОДляПОиска);


                                // TODO: 18.03.2022  получаем ПУБЛИЧНЫЙ ID длявыбранных ФИО

                                int ИндексПубличныIdДляВыбранныхФИО = cursor.getColumnIndex("_id");

                                Long ПубличныIdДляВыбранныхФИО = cursor.getLong(ИндексПубличныIdДляВыбранныхФИО);
                                ////
                                Log.d(this.getClass().getName(), " ПубличныIdДляВыбранныхФИО  " + ПубличныIdДляВыбранныхФИО);

                                Drawable icon = null;
                                //

                                if (ФИОДляПОиска.equalsIgnoreCase("Выбор ФИО ?")) {

                                    icon = getResources().getDrawable(R.drawable.icon_dsu1_create_message_for_tasks);///
                                    ((TextView) view).setTextColor(Color.GRAY);
                                }else {
                                    icon = getResources().getDrawable(R.drawable.icon_dsu1_create_peplefio_tasks);///
                                    ((TextView) view).setTextColor(Color.BLACK);
                                }


                                icon.setBounds(0, 0, 50, 50);

                                ((TextView) view).setPadding(0, 0, 0, 0);

                                ((TextView) view).setCompoundDrawables(icon, null, null, null);

                                Log.d(this.getClass().getName(), " ФИОДляПОиска  " + ФИОДляПОиска +
                                        "    ((TextView) view).getHint() " + ((TextView) view).getHint());
                                // TODO: 18.03.2022  сам слушатель


                                ((TextView) view).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

                                    ((TextView) view).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                                    // TODO: 17.03.2022 ЗАПОЯЕМ ЗАДАЧУ фио и id на каждого
                                    ((TextView) view).setText(ФИОДляПОиска.trim());
                                    // TODO: 20.04.2022
                                    ((TextView) view).setHint(ФИОДляПОиска.trim());
                                    // TODO: 17.03.2022
                                    ((TextView) view).setTextSize(15f);
                                   ((TextView) view).setTypeface( ((TextView) view).getTypeface(), Typeface.NORMAL);
                                    // TODO: 20.04.2022
                                    ((TextView) view).setPadding(0,25,0,25);
                                    // TODO: 18.03.2022
                                    ((TextView) view).setTag(ПубличныIdДляВыбранныхФИО);
                                    // TODO: 18.03.2022
                                    accessibilityNodeInfoДополнительныеДанные = view.createAccessibilityNodeInfo();

                                    // TODO: 21.03.2022

                                    // TODO: 18.03.2022
                                    accessibilityNodeInfoДополнительныеДанные.setContentDescription(String.valueOf(ПубличныIdДляВыбранныхФИО));
                                    // TODO: 18.03.2022
                                    Log.d(this.getClass().getName(), " ФИОДляПОиска  " + ФИОДляПОиска +
                                            " accessibilityNodeInfoДополнительныеДанные " + accessibilityNodeInfoДополнительныеДанные);

                                return true;

                            } else {

                                return false;
                            }

                        }

                        // TODO: 18.03.2022  перенессыц код


                    };
                    // TODO: 17.03.2022
                    АдаптерДляФИОПриСозданииНовойЗадачи.setViewBinder(БиндингДляSpinnerФИОСозданиеНовойЗадачи);
                    // TODO: 28.02.2022
                    holder.spinnerДляСозданиеНовойЗадачи.setAdapter(АдаптерДляФИОПриСозданииНовойЗадачи);
                    // TODO: 07.06.2022
                    Log.e(this.getClass().getName(), "АдаптерДляФИОПриСозданииНовойЗадачи  ");
                  //  holder.spinnerДляСозданиеНовойЗадачи.setSelection(-1,true);
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

            void МетодБиндингаДелаемСлушательДляSpinnerЗадания(@NonNull MyViewHolder holder) throws ParseException {
                try {
                    // TODO: 18.03.2022
                    Log.e(this.getClass().getName(), "МетодБиндингаДелаемСлушательДляSpinnerЗадания  ");
                    //
                    holder.spinnerДляСозданиеНовойЗадачи.setHorizontalScrollBarEnabled(true);
                    // TODO: 18.03.2022  сам слушатель
                    holder.spinnerДляСозданиеНовойЗадачи.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            // TODO: 18.03.2022
                            Log.d(this.getClass().getName(), " position  " + position);

                            if (((TextView) parent.getChildAt(0)).getText().toString().equalsIgnoreCase("Выбор ФИО ?"))
                            {
                                //do nothing.
                                ((TextView) parent.getChildAt(0)).setText("");
                                ((TextView) parent.getChildAt(0)).setHint("Выбор ФИО ?");
                                ((TextView) parent.getChildAt(0)).setHintTextColor(Color.GRAY);
                            }
                            else
                            {
                                // write code on what you want to do with the item selection
                                //  int position = parent.getSelectedItemPosition();
                                ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                                // TODO: 18.03.2022


                                String text = parent.getSelectedItem().toString();


                                Object item = parent.getItemAtPosition(position);

                                // TODO: 18.03.2022
                                ((TextView) parent.getChildAt(0)).setTag(id);

                                Log.w(this.getClass().getName(), "АдаптерДляФИОПриСозданииНовойЗадачи position  " + position + " id " + id + " view " + view);

                                TextView ПубличныйIDДляФрагментаTextView = (TextView) parent.getChildAt(0);

                                ПубличныйIDДляЗаданияКомуПисать = Integer.parseInt(ПубличныйIDДляФрагментаTextView.getTag().toString());

                                Log.w(this.getClass().getName(), "АдаптерДляФИОПриСозданииНовойЗадачи position  " + position + " id " + id + " view " + view +
                                        " ПубличныйIDДляФрагмента " +     ПубличныйIDДляФрагмента );

                                ((TextView) parent.getChildAt(0)).setTextSize(15f);
                                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                            }



                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            Log.w(this.getClass().getName(), "АдаптерДляФИОПриСозданииНовойЗадачи  ");
                        }
                    });
                    // TODO: 18.03.2022
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
            // TODO: 18.03.2022  метод СОДАЕМ СЛУШАТЕЛЬ ДЛЯ ВЫБОРА ПЕРИОДА ЗАДАЧА SEELBAR

            void МетодБиндингаДелаемСлушательДляSeedBars(@NonNull MyViewHolder holder) throws ParseException {
                try {
                    // TODO: 18.03.2022
                    Log.e(this.getClass().getName(), "МетодБиндингаДелаемСлушательДляSeedBars  ");
                    //

                    // TODO: 18.03.2022  сам слушатель  SeekBar

                    holder.seekBarДляВЫбораКакаяЗадачаВыполяется.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            Log.w(this.getClass().getName(), "setOnSeekBarChangeListener  ");
                            // TODO: 18.03.2022
                            // TODO: 10.03.2022

                            // TODO: 18.03.2022
                            seekBar.incrementSecondaryProgressBy(progress);
                            // TODO: 18.03.2022
                            seekBar.setIndeterminate(true);

                            String[] ЗадачиДЛяВЫбора = new String[]{"Одноразовая", "Раз в час", "Раз в сутки", "Раз в неделю", "Раз в месяц"};

                            // TODO: 18.03.2022

                            switch (progress) {
                                // TODO: 18.03.2022
                /*                case 0:
                                    // TODO: 18.03.2022
                                    holder.textView7.setText("выберете тип задачи");
                                    // TODO: 18.03.2022
                                    Log.w(this.getClass().getName(), "string[0]   " + ЗадачиДЛяВЫбора[0].trim());
// TODO: 18.03.2022
                                    seekBar.setIndeterminate(false);*/
                                   // break;
                                case 0:
                                    // TODO: 18.03.2022
                                    holder.textView7.setText(ЗадачиДЛяВЫбора[0]);
                                    // TODO: 18.03.2022
                                    Log.w(this.getClass().getName(), "string[0]   " + ЗадачиДЛяВЫбора[0].trim());
// TODO: 18.03.2022
                                    handlerTaskFragment3.postDelayed(()->{
                                        seekBar.setIndeterminate(false);
                                    },1000);


                                    break;
                                case 1:
                                    // TODO: 18.03.2022
                                    holder.textView7.setText(ЗадачиДЛяВЫбора[1]);
                                    // TODO: 18.03.2022
                                    Log.w(this.getClass().getName(), "string[1]   " + ЗадачиДЛяВЫбора[1].trim());
                                    // TODO: 18.03.2022
                                    handlerTaskFragment3.postDelayed(()->{
                                        seekBar.setIndeterminate(false);
                                    },1000);
                                    break;
                                case 2:
                                    // TODO: 18.03.2022
                                    holder.textView7.setText(ЗадачиДЛяВЫбора[2]);
                                    // TODO: 18.03.2022
                                    Log.w(this.getClass().getName(), "string[2]   " + ЗадачиДЛяВЫбора[2].trim());
                                    // TODO: 18.03.2022
                                    handlerTaskFragment3.postDelayed(()->{
                                        seekBar.setIndeterminate(false);
                                    },1000);
                                    break;
                                case 3:
                                    // TODO: 18.03.2022
                                    holder.textView7.setText(ЗадачиДЛяВЫбора[3]);
                                    // TODO: 18.03.2022
                                    Log.w(this.getClass().getName(), "string[3]   " + ЗадачиДЛяВЫбора[3].trim());
                                    // TODO: 18.03.2022
                                    handlerTaskFragment3.postDelayed(()->{
                                        seekBar.setIndeterminate(false);
                                    },1000);
                                    break;
                                case 4:
                                    // TODO: 18.03.2022
                                    holder.textView7.setText(ЗадачиДЛяВЫбора[4]);
                                    // TODO: 18.03.2022
                                    Log.w(this.getClass().getName(), "string[4]   " + ЗадачиДЛяВЫбора[4].trim());
                                    // TODO: 18.03.2022
                                    handlerTaskFragment3.postDelayed(()->{
                                        seekBar.setIndeterminate(false);
                                    },1000);
                                    break;
                                // TODO: 18.03.2022
                                default:
                                    Log.w(this.getClass().getName(), "progress  " + progress);

                                    handlerTaskFragment3.postDelayed(()->{
                                        seekBar.setIndeterminate(false);
                                    },1000);

                                    break;
                            }


                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            Log.w(this.getClass().getName(), "onStartTrackingTouch  ");
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            Log.w(this.getClass().getName(), "onStopTrackingTouch  ");
                        }
                    });
                    // TODO: 18.03.2022  второй слушатель

                    holder.seekBarДляВЫбораКакаяЗадачаВыполяется.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                        @Override
                        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                            Log.w(this.getClass().getName(), "onScrollChange  ");

                        }
                    });


                    // TODO: 18.03.2022
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

            private void МетодБиндингаНомерЗадания(@NonNull MyViewHolder holder) {
                try {
                    // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2 // TODO: 02.03.2022#2

                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  IDЗадачиТекущей ");
                    // TODO: 17.03.2022
                    if (Курсор_ГлавныйКурсорДляЗадач.getCount() > 0) {
                        int результатСколькоЗадачЯУжеСоздал = Курсор_ГлавныйКурсорДляЗадач.getCount();
                        // TODO: 17.03.2022
                        результатСколькоЗадачЯУжеСоздал = результатСколькоЗадачЯУжеСоздал + 1;
                        // TODO: 28.02.2022
                        holder.textView2.setText("#" + результатСколькоЗадачЯУжеСоздал++);//IDЗадачиТекущей
                    } else {
                        // TODO: 28.02.2022
                        holder.textView2.setText("#" + 1);//IDЗадачиТекущей
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
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  СамогоСообщенияЗадачиДляПользователя ");
                    // TODO: 28.02.2022
                    holder.textView1.setText("");//СамогоСообщенияЗадачиДляПользователя
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


            private void МетодБиндингаСлушателейДляViewCard(MyViewHolder holder) {
                // TODO: 01.03.2022 слушатели

                try {

// TODO: 01.03.2022 слушатели

                    holder.materialCardView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            // TODO: 01.03.2022
                            // TODO: 13.03.2022
                            Log.d(this.getClass().getName(), "  SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1   ПозицияЭлментаVIewCardДополнительно  " +
                                    " holder.getAdapterPosition() " + holder.getAdapterPosition() + " v.getTag() " + v.getTag(holder.materialCardView.getId()));

                            // TODO: 13.03.2022  статус прочтения ли уде или нет адание

                            Object СтатусПрочтеаУжеЗадачаИлиНет = v.getTag(holder.materialCardView.getId());//TODO holder.materialCardView.getId()

                            // TODO: 13.03.2022
                            Log.d(this.getClass().getName(), "  SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1   ПозицияЭлментаVIewCardДополнительно  СтатусПрочтеаУжеЗадачаИлиНет " + СтатусПрочтеаУжеЗадачаИлиНет);
                            // TODO: 04.03.2022  ПОЛУЧЕНИЕ НАЗВАНЕИ ЗАДАЧИ
                     /*   Long ПолучаемUUIDТекущйПозицииВRecyreView = AccessibilityNodeInfoДанныеДляViewCard.getAvailableExtraData().stream().map(Long::new)
                                .distinct() .sorted(Collections.reverseOrder()).collect(Collectors.toList()).get(holder.getAdapterPosition()).longValue();*/

                            // TODO: 13.03.2022
                            Long ПолучаемUUIDТекущйПозицииВRecyreView = BungleДанныеДляViewCard.getLong((String.valueOf(holder.getAdapterPosition())), 0l);


                            Log.i(this.getClass().getName(), "  BungleДанныеДляViewCard   " + BungleДанныеДляViewCard.getLong((String.valueOf(holder.getAdapterPosition())), 0l) +
                                    " ПолучаемUUIDТекущйПозицииВRecyreView " + ПолучаемUUIDТекущйПозицииВRecyreView);


// TODO: 13.03.2022
                            Log.d(this.getClass().getName(), "  СтатусПрочтеаУжеЗадачаИлиНет " + СтатусПрочтеаУжеЗадачаИлиНет
                                    + " ПолучаемUUIDТекущйПозицииВRecyreView " + ПолучаемUUIDТекущйПозицииВRecyreView);


                            // TODO: 03.03.2022  запускам сменты статуса

                         /*   if (Integer.parseInt(String.valueOf(СтатусПрочтеаУжеЗадачаИлиНет)) == 0 && ПолучаемUUIDТекущйПозицииВRecyreView != null) {

                                ///
                                String ИмяСлужбыУведомленияДляЧата = "WorkManager NOtofocationForTasks";

                                String PROCESS_ID_УведомленияПлановая = "12";

                                // TODO: 03.03.2022

                                SubClass_Starting_chahge_status_public_task_Класс_ДляЗадач subClass_starting_chahge_status_public_notificaton =
                                        new SubClass_Starting_chahge_status_public_task_Класс_ДляЗадач(getContext());

                                // TODO: 03.03.2022 определяем кода для отложеного запуска службы смены статсу условия задачи
                                PendingIntent ЗапускКОдаЧтоПОльзовательОзнаомленсЗаданием = subClass_starting_chahge_status_public_notificaton.
                                        МетодЗапускаСменыСтатусаСлужбыЧерезPendingIntent(PROCESS_ID_УведомленияПлановая, ИмяСлужбыУведомленияДляЧата, String.valueOf(ПолучаемUUIDТекущйПозицииВRecyreView));


                                try {

                                    // TODO: 03.03.2022  запускаем службу смены статуса
                                    ЗапускКОдаЧтоПОльзовательОзнаомленсЗаданием.send();


                                } catch (PendingIntent.CanceledException e) {
                                    e.printStackTrace();
                                }
                                ///////TODO запускаем смены стануса задачи черезе PendingIntent
                                Log.d(getContext().getClass().getName(), "PROCESS_ID_УведомленияПлановая " + PROCESS_ID_УведомленияПлановая +
                                        " ИмяСлужбыУведомленияДляЧата " + ИмяСлужбыУведомленияДляЧата + " СтатусПрочтеаУжеЗадачаИлиНет " + СтатусПрочтеаУжеЗадачаИлиНет);


                                // TODO: 03.03.2022 update screewn
                                Handler handlerЗапускаемОтсрочнуюСменуСтатуса = new Handler();
                                // TODO: 04.03.2022
                                handlerЗапускаемОтсрочнуюСменуСтатуса.postDelayed(() -> {
                                    // TODO: 04.03.2022

                                    Курсор_ДляПолученияДАнныхДляЗАДАЧTASK.deactivate();
                                    // TODO: 03.03.2022

                                    Курсор_ДляПолученияДАнныхДляЗАДАЧTASK.requery();
                                    // TODO: 13.03.2022

                                    Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.deactivate();
                                    // TODO: 14.03.2022
                                    Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.requery();

                                    // TODO: 13.03.2022
                                    notifyDataSetChanged();

                           *//*     Log.i(getContext().getClass().getName(), "СтатусПрочтеаУжеЗадачаИлиНет Статус Уже Изменен на 0 " + СтатусПрочтеаУжеЗадачаИлиНет);

                                Toast.makeText(getActivity(), " Статус сменили на ознакомленный  #" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();*//*

                                }, 2500);

                            } else {

                                ///////TODO запускаем смены стануса задачи черезе PendingIntent
                                Log.i(getContext().getClass().getName(), "СтатусПрочтеаУжеЗадачаИлиНет Статус Уже Изменен на 1  " + СтатусПрочтеаУжеЗадачаИлиНет);

                                ///   Toast.makeText(getActivity(), " Статус ознакомлена !!!   #" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                            }*/

                            // TODO: 03.03.2022 update screewn

                            // TODO: 13.03.2022
                            Log.d(this.getClass().getName(), "  SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1" +
                                    "   ПолучаемUUIDТекущйПозицииВRecyreView " + ПолучаемUUIDТекущйПозицииВRecyreView +
                                    " holder.getAdapterPosition() " + holder.getAdapterPosition());

                            // TODO: 13.03.2022
                            // notifyDataSetChanged();

                            return true;

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


            // TODO: 15.03.2022


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
                    holder.buttonДляСозданиеНовогоЗадания.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO: 16.03.2022
                            try{
                        // TODO: 10.03.2022
              /*              Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));*/
                            // TODO: 21.03.2022 аписываем данные первую строчки шабка задачи
                            if (!holder.textView1.getText().toString().isEmpty()) {
                                // TODO: 21.03.2022
                                holder.bundleЗначенияДляНовойЗадачи.putString("ШабкаНовойЗадачи", holder.textView1.getText().toString());
                                // TODO: 21.03.2022  ПЕРЕНЕСЕННЫЙ КОД ДЛЯ ЗАПИСИ ЗАДАЧИ В БАЗУ
                                if (!holder.textView3.getText().toString().isEmpty()) {
                                    holder.bundleЗначенияДляНовойЗадачи.putString("СообщениеНовойЗадачи", holder.textView3.getText().toString());
                             TextView textViewДЛяСпинераФИО=(TextView)       holder.spinnerДляСозданиеНовойЗадачи.getChildAt(0);///Выбор ФИО ?
                             String ЗначениеИзtextViewДЛяСпинераФИО=       textViewДЛяСпинераФИО.getText().toString();
                                    // TODO: 21.03.2022 аписываем данные третья  ФИО
                                    if (!ЗначениеИзtextViewДЛяСпинераФИО.isEmpty() && !ЗначениеИзtextViewДЛяСпинераФИО.equalsIgnoreCase("Выбор ФИО ?")) {
                                        TextView ПолучаемФИОКомуЗадачаПредназначена = (TextView) holder.spinnerДляСозданиеНовойЗадачи.getChildAt(0);
                                        if (!ПолучаемФИОКомуЗадачаПредназначена.getText().toString().isEmpty()) {
                                            // TODO: 21.03.2022
                                            Log.d(this.getClass().getName(), "  holder.spinnerДляСозданиеНовойЗадачи.getSelectedItemPosition() " + holder.spinnerДляСозданиеНовойЗадачи.getSelectedItemPosition() +
                                                    "  " + holder.spinnerДляСозданиеНовойЗадачи.getSelectedItem().toString() +
                                                    "  holder.spinnerДляСозданиеНовойЗадачи " + holder.spinnerДляСозданиеНовойЗадачи.getChildAt(0).toString() +
                                                    " holder.spinnerДляСозданиеНовойЗадачи " + holder.spinnerДляСозданиеНовойЗадачи.getItemAtPosition(0) +
                                                    " textView " + ПолучаемФИОКомуЗадачаПредназначена.getText().toString());
                                            holder.bundleЗначенияДляНовойЗадачи.putString("КомуСообщениеФИО", ПолучаемФИОКомуЗадачаПредназначена.getText().toString());
                                            // TODO: 21.03.2022 аписываем данные  четвертая Выбор какая Задача Одноразовая Или По расписания
                                            if (!holder.textView7.getText().toString().isEmpty()) {
                                                holder.bundleЗначенияДляНовойЗадачи.putString("ЗадачиКакаяЗадачиОдноразоваяИлиНет", holder.textView7.getText().toString());
                                                // TODO: 21.03.2022  запускаем создаени задачи только если пользователь выбрал задачу
                                                if (!holder.textView7.getText().toString().equalsIgnoreCase("выберете тип задачи")) {
                                                    // TODO: 21.03.2022  Класс для Создание Нового Задича ПОСЛЕН ПОЛУЧЕНИЯ ДАННЫХ ЧЕРЕЗ bUNGLE
                                                    handlerTaskFragment3.post(()->{
                                                        progressBarTaskFragment3.setVisibility(View.VISIBLE);
                                                    });
                                                    Log.d(this.getClass().getName(), " accessibilityNodeInfoДополнительныеДанные.getContentDescription() "
                                                            + accessibilityNodeInfoДополнительныеДанные.getContentDescription());
                                                    SubClass_CreateNewTasks_КлассДляСозданияНовойЗадачи subClass_createNewTasksКлассДляСозданияНовойЗадачи
                                                            = new SubClass_CreateNewTasks_КлассДляСозданияНовойЗадачи(getContext(), holder.bundleЗначенияДляНовойЗадачи);
                                                    // TODO: 21.03.2022

                                                    if (!WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().isEmpty()) {
                                                        try {
                                                            WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                                                    WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);


                                                            while (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING)==0) {
                                                                // TODO: 23.06.2022
                                                                try {
                                                                    WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                                                            WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                                                                    handlerTaskFragment3.post(()->{
                                                                        progressBarTaskFragment3.setVisibility(View.VISIBLE);
                                                                    });
                                                                } catch (ExecutionException e) {
                                                                    e.printStackTrace();
                                                                } catch (InterruptedException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }


                                                        } catch (ExecutionException e) {
                                                            e.printStackTrace();
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                    holder.buttonДляСозданиеНовогоЗадания.animate().rotationX(20l);//.rotationXBy(4l);
                                                    //     holder.itemView.animate().rotationXBy(4l);



                                                    // TODO: 24.06.2022  создаем новую задачу
                                                    Integer ОперациСозданияНовойЗадания
                                                            = subClass_createNewTasksКлассДляСозданияНовойЗадачи.МетодЗаписиНовойЗадачи();
                                                    Log.d(this.getClass().getName(), "  ОперациСозданияНовойЗадания" + ОперациСозданияНовойЗадания);
                                                    if (ОперациСозданияНовойЗадания>0) {


                                                        // TODO: 26.06.2022
                                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                + " ПубличныйIDДляФрагмента "+ПубличныйIDДляФрагмента );

                                                        Log.d(this.getClass().getName(), "  ОперациСозданияНовойЗадания" + ОперациСозданияНовойЗадания+
                                                                "ПубличныйIDДляЗаданияКомуПисать " +ПубличныйIDДляЗаданияКомуПисать);
                                                    }

                                                    handlerTaskFragment3.postDelayed(()->{
                                                        if (ОперациСозданияНовойЗадания >0) {
                                                            // TODO: 22.03.2022
                                                            Log.w(this.getClass().getName(), "   Успешное Создаение Задачи !!!" + ОперациСозданияНовойЗадания);
                                                        }else{
                                                            Snackbar.make(v, " Ошибка новая задача не создалась !!! ", Snackbar.LENGTH_LONG).show();
                                                            Log.e(this.getClass().getName(), "  Ошибка новая задача не создалась !!!" + ОперациСозданияНовойЗадания);
                                                        }
                                                            // TODO: 22.03.2022
                                                            // TODO: 09.03.2022
                                                            fragmentTransactionляЗадачи = fragmentManagerДляЗадачи.beginTransaction();
                                                            Log.d(this.getClass().getName(), "  fragmentTransactionляЗадачи" + fragmentTransactionляЗадачи);
                                                            fragment_ТекущийФрагмент = new Fragment2_Create_Tasks();
                                                            fragmentTransactionляЗадачи.replace(R.id.activity_main_fisrt_for_tasks, fragment_ТекущийФрагмент).commit();//.layout.activity_for_fragemtb_history_tasks
                                                            fragmentTransactionляЗадачи.show(fragment_ТекущийФрагмент);
                                                            Log.d(this.getClass().getName(), " fragmentTransactionляЗадачи " + fragmentTransactionляЗадачи);
                                                            bottomNavigationViewДляTasks.requestLayout();
                                                            recyclerView.requestLayout();
                                                           recyclerView.forceLayout();
                                                            linearLayou.requestLayout();
                                                            // TODO: 22.03.2022  ПОСЛЕ УСПЕШНОГО СОЗДАНЕИ ОВГО ЗАДАНИЯ ЗАПУСКАЕМ СИНХРОНИАЗЦИЮ
                                                            Log.d(this.getClass().getName(), " ПубличныйIDДляФрагмента " +     ПубличныйIDДляФрагмента +
                                                                    " holder.spinnerДляСозданиеНовойЗадачи " +holder.spinnerДляСозданиеНовойЗадачи.getTag());
                                                            Log.d(this.getClass().getName(), "после СОЗДАНИЕЯ НОВОЙ ЗАДАЧИ ..... ПОСЛЕ ЗУПАСУКА СИНХРОНИАЗЦИИ" +
                                                                    "  ПубличныйIDДляФрагмента " +     ПубличныйIDДляФрагмента );
                                                        progressBarTaskFragment3.setVisibility(View.GONE);
                                                    },500);
                                                    Log.d(this.getClass().getName(), "  ОперациСозданияНовойЗадания" + ОперациСозданияНовойЗадания);

                                                } else {
                                                    // TODO: 21.03.2022 ЗАДАЧА НЕ ВЫЬББРАНА
                                                    Snackbar.make(v, "Вы не выбрали тип задачи !!!  ", Snackbar.LENGTH_LONG).show();
                                                }
                                            } else {
                                                // TODO: 21.03.2022 ЗАДАЧА НЕ ВЫЬББРАНА
                                                Snackbar.make(v, "Вы не выбрали тип задачи !!!  ", Snackbar.LENGTH_LONG).show();
                                            }
                                        } else {
                                            // TODO: 21.03.2022 ЗАДАЧА НЕ ВЫЬББРАНА
                                            Snackbar.make(v, "Вы не выбрали ФИО  !!!  ", Snackbar.LENGTH_LONG).show();
                                        }
                                        // TODO: 21.03.2022  запускапм класс с данными BUngle
                                        Log.d(this.getClass().getName(), "  holder.buttonДляСозданиеНовогоЗадания.setOnClickListener   МетодБиндингаСлушательДляКнопкиСоздатьНовуюЗадачу    " +
                                                " holder.bundleЗначенияДляНовойЗадачи " + holder.bundleЗначенияДляНовойЗадачи.toString());
                                    } else {
                                        // TODO: 21.03.2022 ЗАДАЧА НЕ ВЫЬББРАНА
                                        Snackbar.make(v, "Не заполнено   ФИО   !!!  ", Snackbar.LENGTH_LONG).show();
                                    }
                                } else {
                                    // TODO: 21.03.2022 ЗАДАЧА НЕ ВЫЬББРАНА
                                    Snackbar.make(v, "Нет самой задачи !!!  ", Snackbar.LENGTH_LONG).show();
                                }
                                // TODO: 21.03.2022  перенсенный код из задачи
                            } else {
                                // TODO: 21.03.2022 ЗАДАЧА НЕ ВЫЬББРАНА
                                Snackbar.make(v, "Нет загаловка задачи !!!  ", Snackbar.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }

                            Log.d(this.getClass().getName(), "  holder.buttonДляСозданиеНовогоЗадания.setOnClickListener   МетодБиндингаСлушательДляКнопкиСоздатьНовуюЗадачу    " +
                                    " holder.bundleЗначенияДляНовойЗадачи " + holder.bundleЗначенияДляНовойЗадачи.toString());

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


            private  Cursor МетодПолучениеДанныхФИОаОснованииID(Integer КтоНаписалСообщениеФИОдЛПосика) throws ExecutionException, InterruptedException {
                // TODO: 16.11.2021 find FIO
              Cursor Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал = null;
                try {
                    // TODO: 15.05.202
                    String Текущаятаблицы="chat_users";
                    ModuleQuety moduleQuety=new ModuleQuety(getContext());
                    Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT D.name  FROM "+Текущаятаблицы+" AS D" +
                            "  WHERE D._id='"+КтоНаписалСообщениеФИОдЛПосика+"'   AND D.name IS NOT NULL  " ,null);

                    Log.d(this.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + " Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал " +Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал;
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
        }//TODO  конец два


        // TODO: 17.03.2022  КЛАСС ДЛЯ СПИНЕРА


        private class КлассАдаптерДляСпинера {
            // TODO: 17.03.2022
            Context context;
            // TODO: 17.03.2022
            SQLiteCursor sqLiteCursorКурсорПолучаемВсеФИоДляНовойЗадачиКому;

            public КлассАдаптерДляСпинера(Context context) throws ExecutionException, InterruptedException {
                // TODO: 17.03.2022
                this.context = context;

                // TODO: 02.03.2022
                Log.i(this.getClass().getName(), "  sqLiteCursorКурсорПолучаемВсеФИоДляНовойЗадачиКому " + sqLiteCursorКурсорПолучаемВсеФИоДляНовойЗадачиКому);
            }

            // TODO: 17.03.2022  ПЕРЕНЕСЕНЫЙ МЕТОД ДЛЯ КУРСОР ДЛЯ ФИО ДЛЯ СПИНЕРА
        Cursor МетодПолучаемГлавныеДанныеДляSpinnerКомуЗадачаФИО() throws ExecutionException, InterruptedException {
                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                 Cursor sqLiteCursorКурсорВсеФИОДЛяSpinneraДляКогоЗадание = null;
                try {
                    // TODO: 15.05.202
                    String Текущаятаблицы="chat_users";
                    ModuleQuety moduleQuety=new ModuleQuety(getContext());
                    sqLiteCursorКурсорВсеФИОДЛяSpinneraДляКогоЗадание= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT *  FROM "+Текущаятаблицы+" AS D" +
                            "  WHERE D._id<>? ='"+ПубличныйIDДляФрагмента+"' "+
                            "   ORDER BY D._id " ,null);

                    Log.d(this.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + " sqLiteCursorКурсорВсеФИОДЛяSpinneraДляКогоЗадание " +sqLiteCursorКурсорВсеФИОДЛяSpinneraДляКогоЗадание);
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

            // TODO: 21.03.2022  --метод записи новой задачи
            Integer МетодЗаписиНовойЗадачи() {
                // TODO: 21.03.2022
            AtomicInteger Результат_ВставкиДанныхПриСозданииНовойЗадачи=new AtomicInteger(0);
                try {
                    // TODO: 21.03.2022
                    LinkedBlockingQueue<String> linkedBlockingQueueДвеТаблицыСозданиеНовойЗадачи = new LinkedBlockingQueue();
                        linkedBlockingQueueДвеТаблицыСозданиеНовойЗадачи.offer("notifications");
                        linkedBlockingQueueДвеТаблицыСозданиеНовойЗадачи.offer("data_notification");
                    Long ЛокальныйUUIDДляОбоихТаблиц = (Long) new GreatUuidGeneration(getContext()).greatUuidGeneration();
                    // TODO: 21.03.2022
                    ContentValues contentValuesДляСозданияНовойЗадачиДляДвухТаблиц = new ContentValues();
                    // TODO: 21.03.2022 ЦИКЛ СОЗДАНЕИ НВОЙ ЗАДАЧИ
                    linkedBlockingQueueДвеТаблицыСозданиеНовойЗадачи.forEach((ТаблицаОбработки) -> {
                        // TODO: 21.03.2022
                        try {
                            // TODO: 21.03.2022 #1 для первой таблицы
                            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                            Long РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудникаПервая=
                                    new VersionCurentTable(getContext()).upVersionCurentTable(    ТаблицаОбработки );
                            // TODO: 08.10.2021 повышаем версию
                            contentValuesДляСозданияНовойЗадачиДляДвухТаблиц.put("current_table", РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудникаПервая);
                            // TODO: 21.03.2022
                            String СгенерированованныйДатаДляДаннойОперации = new GetMainDateForApp(getContext()).getMainDateForApp();
                            contentValuesДляСозданияНовойЗадачиДляДвухТаблиц.put("date_update", СгенерированованныйДатаДляДаннойОперации);
                            // TODO: 16.05.2025
                            switch (ТаблицаОбработки) {
                                // TODO: 21.03.2022
                                case "notifications":
                                    // TODO: 21.03.2022
                                    ///////// вставляем ПУБЛИЧНЫЙ ID текущего пользователя
                                    contentValuesДляСозданияНовойЗадачиДляДвухТаблиц.put("user_update",    ПубличныйIDДляФрагмента   );
                                    Log.d(this.getClass().getName(), "   ПубличныйIDДляФрагмента " +     ПубличныйIDДляФрагмента );
                                    ///////// вставляем id_user
                                    contentValuesДляСозданияНовойЗадачиДляДвухТаблиц.put("id_user",ПубличныйIDДляЗаданияКомуПисать);
                                    Log.d(this.getClass().getName(), "   ПубличныйIDДляЗаданияКомуПисать " + ПубличныйIDДляЗаданияКомуПисать);
                                    ////todo # 1 первой таблицы
                                    contentValuesДляСозданияНовойЗадачиДляДвухТаблиц.put("uuid", ЛокальныйUUIDДляОбоихТаблиц);
                                    // TODO: 21.03.2022
                                    Log.d(this.getClass().getName(),
                                            " ТаблицаОбработки " + ТаблицаОбработки + " bundleПолученныйеДанныеДляСозданияЗадачи " + bundleПолученныйеДанныеДляСозданияЗадачи +
                                                    "  ПолученыйUUIDУУжеЕслиСуществуетЗаданияТекущегоПользователясКомуПишем  +ПолученыйUUIDУУжеЕслиСуществуетЗаданияТекущегоПользователясКомуПишем" +
                                                    " ЛокальныйUUIDДляОбоихТаблиц " + ЛокальныйUUIDДляОбоихТаблиц);
                                    break;
// TODO: 16.05.2025
                                case "data_notification":
                                    // TODO: 21.03.2022
                                    ////todo # 2 первой таблицы
                                        // TODO: 21.03.2022
                                        contentValuesДляСозданияНовойЗадачиДляДвухТаблиц.put("uuid_notifications", ЛокальныйUUIDДляОбоихТаблиц);
                                    // TODO: 22.03.2022
                                    Long ЛокальныйUUIDДляТолькоДляВторойТаблицы = (Long) new GreatUuidGeneration(getContext()).greatUuidGeneration() + 1;
                                    ////todo # 2 первой таблицы
                                    contentValuesДляСозданияНовойЗадачиДляДвухТаблиц.put("uuid", ЛокальныйUUIDДляТолькоДляВторойТаблицы);
                                    // TODO: 21.03.2022
                                    Log.d(this.getClass().getName(),
                                            " ТаблицаОбработки " + ТаблицаОбработки + " ПолученыйUUIDУУжеЕслиСуществуетЗаданияТекущегоПользователясКомуПишем" +
                                                    " " + ЛокальныйUUIDДляОбоихТаблиц
                                                    + " bundleПолученныйеДанныеДляСозданияЗадачи " + bundleПолученныйеДанныеДляСозданияЗадачи +
                                                    " bundleПолученныйеДанныеДляСозданияЗадачи.getString(\"ШабкаНовойЗадачи\") " + bundleПолученныйеДанныеДляСозданияЗадачи.getString("ШабкаНовойЗадачи"));


// TODO: 21.03.2022   add headr new task
                                    contentValuesДляСозданияНовойЗадачиДляДвухТаблиц.put("head_message", bundleПолученныйеДанныеДляСозданияЗадачи.getString("ШабкаНовойЗадачи"));
// TODO: 21.03.2022   add message  new task
                                    contentValuesДляСозданияНовойЗадачиДляДвухТаблиц.put("message", bundleПолученныйеДанныеДляСозданияЗадачи.getString("СообщениеНовойЗадачи"));
// TODO: 21.03.2022   type  message  new task
                                    contentValuesДляСозданияНовойЗадачиДляДвухТаблиц.put("type_tasks", bundleПолученныйеДанныеДляСозданияЗадачи.getString("ЗадачиКакаяЗадачиОдноразоваяИлиНет"));


                                    // TODO: 21.03.2022
                                    Log.d(this.getClass().getName(),
                                            " ТаблицаОбработки " + ТаблицаОбработки + " contentValuesДляСозданияНовойЗадачиДляДвухТаблиц " + contentValuesДляСозданияНовойЗадачиДляДвухТаблиц +
                                                    " ТаблицаОбработки " + ТаблицаОбработки);

                                    ////


                                    // TODO: 12.10.2021  Ссылка Менеджер Потоков
                                    // TODO: 21.03.2022
                                    break;


                                default:
                                    // TODO: 21.03.2022
                                    break;
                            }


                            // TODO: 14.05.2025  После заполенеие Вставка Данных
                            ModuleInserting moduleInserting=new ModuleInserting(context);
                            // TODO: 14.05.2025
                            Результат_ВставкиДанныхПриСозданииНовойЗадачи.getAndSet(moduleInserting.getModuleInsert(ТаблицаОбработки,contentValuesДляСозданияНовойЗадачиДляДвухТаблиц));

                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                    "     Результат_ВставкиДанныхПриСозданииНовойЗадачи.get()  "+    Результат_ВставкиДанныхПриСозданииНовойЗадачи.get()  );


                            // TODO: 21.03.2022
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }


                        // TODO: 21.03.2022

                        Object objectТекущаяТалицаДляНовойЗадачи = linkedBlockingQueueДвеТаблицыСозданиеНовойЗадачи.peek();
                        // TODO: 21.03.2022

                        Log.d(this.getClass().getName(), " linkedBlockingQueueДвеТаблицыСозданиеНовойЗадачи " + linkedBlockingQueueДвеТаблицыСозданиеНовойЗадачи +
                                " objectТекущаяТалицаДляНовойЗадачи " + objectТекущаяТалицаДляНовойЗадачи);

                    });

                    Log.d(this.getClass().getName(), " linkedBlockingQueueДвеТаблицыСозданиеНовойЗадачи " + linkedBlockingQueueДвеТаблицыСозданиеНовойЗадачи);

                    //TODO прегружаем внешний код после вставки нвой задачи
                    bottomNavigationViewДляTasks.requestLayout();
                    // TODO: 22.03.2022
                    recyclerView.requestLayout();
                    // TODO: 06.06.2022
                    recyclerView.forceLayout();
                    // TODO: 14.03.2022
                    linearLayou.requestLayout();



                    // TODO: 21.03.2022
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                // TODO: 21.03.2022
                return Результат_ВставкиДанныхПриСозданииНовойЗадачи.get();
            }



        }

    }   // TODO: 28.02.2022 конец класса бизнес логики   // TODO: 28.02.2022 конец класса бизнес логики

    // TODO: 02.03.2022


}    // TODO: 28.02.2022 конец класса






























