package com.dsy.dsu.TasksApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.accessibility.AccessibilityNodeInfo;
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
import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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


public class Fragment2_Create_Tasks extends Fragment {
    // TODO: 15.03.2022
    private RecyclerView recyclerView;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2 БизнесЛогикиФрагмент2;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2.MyRecycleViewAdapter myRecycleViewAdapter;
    private SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2.MyViewHolder myViewHolder;
    private View viewДляПервойКнопкиHome_Задания ;
    private  Cursor Курсор_ГлавныйКурсорДляЗадач;
    private  Cursor Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе ;
    private AccessibilityNodeInfo accessibilityNodeInfoBundle;

    private    Bundle   BungleДанныеДляViewCard;
    private   Bundle BungleДанныеДляViewCardBungle;
    private    Bundle   BungleДанныеДляViewCardBungleID;
    private    Bundle   BungleДанныеДляViewCardДляпередачиCallsBaskПримечание;
    private FragmentManager fragmentManagerДляЗадачи;
    private FragmentTransaction fragmentTransactionляЗадачи;
    private Fragment fragment_ТекущийФрагмент;
    // TODO: 01.03.2022 -- конец перенесенные переменные из ФРАГМЕНТА1
    private WorkInfo WorkInfoИнформацияОЗапущенойСлужбеОдноразовая;
    final private String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";
    final private String ИмяСлужбыWorkmanagerОБЩАЯ = "WorkManager Synchronizasiy_Data";

    // TODO: 15.06.2022
    private Handler handlerTaskFragment2;
    private ProgressBar progressBarTaskFragment2;
    private       Integer   КтоНаписалСообщениеФИОдЛПосика ;
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
    // TODO: 28.06.2022
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationПринудительныйОбмен;


    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            // TODO: 14.03.2022 ССЫЛКА НА РОДИТЕЛЬСКОЕ ФРАГМЕНТ
            context=getContext();
            recyclerView = (RecyclerView) viewДляПервойКнопкиHome_Задания.findViewById(R.id.recycleviewActiviTask);
            recyclerView.setVisibility(View.VISIBLE);
            linearLayou = (LinearLayout) getActivity().findViewById(R.id.activity_main_fisrt_for_tasks);
            fragmentManagerДляЗадачи = getActivity().getSupportFragmentManager();
            progressBarTaskFragment2  = (ProgressBar) view.findViewById(R.id.prograessbartaskfragmen1down); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            recyclerView= (RecyclerView) view.findViewById(R.id.recycleviewActiviTask);
            textViewТекущаяЗадача = (TextView) view.findViewById(R.id.activy_task_fragment1_tasksnameеtextview);
            textViewТекущаяЗадача.setText("Созданные задачи".toUpperCase());
            // TODO: 14.03.2022
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
            //  bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи.performLongClick();
            bottomNavigationКонкретноКнопкаДобавить.setSelected(true);
            bottomNavigationКонкретноКнопкаДобавить.setVisibility(View.VISIBLE);
            bottomNavigationКонкретноКнопкаКонтролируемыеЗадачи.setTitle("Задачи");
            bottomNavigationПринудительныйОбмен = bottomNavigationViewДляTasks.findViewById(R.id.id_taskAsyns);
            Log.d(this.getClass().getName(), "  Fragment2_Create_Tasks  viewДляПервойКнопкиHome_Задания ---/" + viewДляПервойКнопкиHome_Задания +
                    " subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент2 " + БизнесЛогикиФрагмент2);
// TODO: 02.08.2022  иницаализация  recycreView
            БизнесЛогикиФрагмент2.МетодИнициализацииRecycleViewДляЗадач(viewДляПервойКнопкиHome_Задания);

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 try{
     // TODO: 16.04.2025
     Log.d(context.getClass().getName(), "\n"
             + " время: " + new Date() + "\n+" +
             " Класс в процессе... " + this.getClass().getName() + "\n" +
             " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() );


        // TODO: 02.08.2022 инициализация классовдля работы
        БизнесЛогикиФрагмент2 = new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2(getContext(), getActivity());

        // TODO: 02.08.2022  инициализация двух слуушатель для двух work manager
        БизнесЛогикиФрагмент2.МетодСоздаенияСлушателяДляЧатаWorkMAnagerФрагмент2();
        БизнесЛогикиФрагмент2.МетодСоздаенияСлушателяДляЧатаWorkMAnagerОбщщийДополнительныйФрагмент2();
        БизнесЛогикиФрагмент2.МетодИнициализациHandlerCallBack();
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try{
            Log.d(this.getClass().getName(), "  Fragment2_Create_Tasks  viewДляПервойКнопкиHome_Задания " + viewДляПервойКнопкиHome_Задания);
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
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return viewДляПервойКнопкиHome_Задания;//super.onCreateView(inflater, container, savedInstanceState)
    }


    @Override
    public void onStart() {
        super.onStart();
        // TODO: 20.07.2022
        try{
            // TODO: 14.03.2022
            ПубличныйIDДляФрагмента =  new GetttingPublicID().getttingPublicID(getContext());
            БизнесЛогикиФрагмент2.
                    МетодПолучаемГлавныеДанныеДляЗадач(ПубличныйIDДляФрагмента);

            БизнесЛогикиФрагмент2.МетодПолученимТОлькоКоличествоЗадач(ПубличныйIDДляФрагмента);
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

    @Override
    public void onResume() {
        super.onResume();
        try {
            Log.d(this.getClass().getName(), "Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе+ " + Курсор_ГлавныйКурсорДляЗадач " + Курсор_ГлавныйКурсорДляЗадач);
            БизнесЛогикиФрагмент2.МетодСозданиеНавигаторКнопок();
            БизнесЛогикиФрагмент2.МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
            БизнесЛогикиФрагмент2.МетодЗаполенияRecycleViewДляЗадач(viewДляПервойКнопкиHome_Задания);
            БизнесЛогикиФрагмент2.МетодСлушательObserverДляRecycleView();

            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2 ИмяСлужбыСинхронизацииДляЗадачиИзЧата   Fragment2_Create_Tasks " +
                    "" + ИмяСлужбыСинхронизацииОдноразовая +
                    " subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент2 " + БизнесЛогикиФрагмент2);
            // TODO: 04.03.2022 создаем слушатель    третий класс создаем ЗАПУСКАЕМ  второай слушатель только количество данных СЛУШАТЕЛЬ КУРСОРРА
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
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2 onDestroyView  ");
            // TODO: 04.03.2022
            //WorkManager.getInstance(getContext()).getWorkInfosForUniqueWorkLiveData(ИмяСлужбыСинхронизацииОдноразовая).removeObserver(observerWorkManagerОДНОРАЗОВАЯ);
           //// WorkManager.getInstance(getContext()).cancelUniqueWork(ИмяСлужбыСинхронизацииОдноразовая);
            handlerTaskFragment2.removeCallbacksAndMessages(null);
            WorkManager.getInstance(getContext()).getWorkInfosForUniqueWorkLiveData(ИмяСлужбыСинхронизацииОдноразовая).removeObserver(null);
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2  ИмяСлужбыСинхронизацииДляЗадачиИзЧата  " +
                    "" + ИмяСлужбыСинхронизацииОдноразовая);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    // TODO: 15.03.2022 КЛАСС ДЛЯ БИЗНЕС ЛОГИКИ  ДЛЯ ВТОРОГО ФРАГМЕНТА 2


    private  class SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2  {
        // TODO: 28.02.2022
        public SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2(Context context, Activity activity) {

            // TODO: 03.03.2022
            Log.d(this.getClass().getName(), "SsubClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент2this.context.getClass().getName()  " + context.getClass().getName());
        }

        // TODO: 14.03.2022

        protected  Cursor МетодПолученимТОлькоКоличествоЗадач(Integer ПубличноеIDПолученныйИзСервлетаДляUUID) {
            try {
                // TODO: 14.03.2022
                // TODO: 15.05.202
                String Текущаятаблицы="view_tasks";
                ModuleQuety moduleQuety=new ModuleQuety(getContext());
                Курсор_ГлавныйКурсорДляЗадач= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT *  FROM "+Текущаятаблицы+" AS D" +
                        "  WHERE D.user_update='"+ПубличноеIDПолученныйИзСервлетаДляUUID+"' AND D.status_write <> '"+ПубличноеIDПолученныйИзСервлетаДляUUID+"' AND D.message IS NOT NULL  "+
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
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }
            return Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе;
        }

        // TODO: 28.02.2022 Под Класс порлучение данных для активти

         Cursor МетодПолучаемГлавныеДанныеДляЗадач(Integer ПубличноеIDПолученныйИзСервлетаДляUUID){
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


        // TODO: 04.03.2022  класс в котором находяться слушатели


        void МетодСлушательObserverДляRecycleView() {
            // TODO: 04.03.2022
            try {
                // TODO: 04.03.2022 запускаем слушатель;
                if (myRecycleViewAdapter!=null) {
                    myRecycleViewAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                        @Override
                        public void onChanged() {
                            super.onChanged();
                            // TODO: 02.03.2022
                            try {
                                Log.d(this.getClass().getName(), "onChanged ");
                                // TODO: 05.03.2022  ДЛЯ ИНИЗАЛИЗАЦИИ НИЖНИХ КНОПОК
                           /* onStart();
                            onResume();*/
                                Log.d(this.getClass().getName(), "onChanged    adapterDataObserverObserverСлушатель = new RecyclerView.AdapterDataObserver() { " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
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
                            // TODO: 02.03.2022
                            try{
                                Log.d(this.getClass().getName(), "onItemRangeChanged ");
                                onStart();
                                onResume();
                                /////////////
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

                            Log.d(this.getClass().getName(), "onItemRangeMoved ");
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
            }
        }
// TODO: 04.03.2022


        // TODO: 18.10.2021  СИНХРОНИАЗЦИЯ ЧАТА ПО РАСПИСАНИЮ ЧАТ

        void МетодСоздаенияСлушателяДляЧатаWorkMAnagerФрагмент2() throws ExecutionException, InterruptedException {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            try {
                // TODO: 16.12.2021  --ОДНОРАЗОВАЯ СИНХРОНИАЗЦИЯ СЛУШАТЕЛЬ
                WorkManager.getInstance(getContext()).getWorkInfosForUniqueWorkLiveData(ИмяСлужбыСинхронизацииОдноразовая)
                        .observe(getActivity().getSupportFragmentManager().getFragments().get(0), new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfosОдноразовая) {
                        // TODO: 23.12.2021
                        // TODO: 03.08.2022 методы работы work manager
                        МетодРаботаWorkManagerНачалась(workInfosОдноразовая);
                        МетодРаботаWorkManagerКонец(workInfosОдноразовая);
                        МетодУспешнойРаботыWorkManager(workInfosОдноразовая);
                    }
                });
                // TODO: 29.09.2021  конец синхрониазции по раписанию
                Log.d(this.getClass().getName(), " WorkInfoИнформацияОЗапущенойСлужбеОдноразовая  СтастусWorkMangerЧата " + " ИмяСлужбыСинхронизацииДляЗадачиИзЧата " + ИмяСлужбыСинхронизацииОдноразовая);
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 10.06.2022  дополниткльный слушатель Цщкльфтфпук j,otuj
        // TODO: 18.10.2021  СИНХРОНИАЗЦИЯ ЧАТА ПО РАСПИСАНИЮ ЧАТ
        void МетодСоздаенияСлушателяДляЧатаWorkMAnagerОбщщийДополнительныйФрагмент2() throws ExecutionException, InterruptedException {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            try {
                WorkManager.getInstance(getContext()).getWorkInfosForUniqueWorkLiveData(ИмяСлужбыWorkmanagerОБЩАЯ)
                        .observe(getActivity().getSupportFragmentManager().getFragments().get(0), new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfoДополнительнаОБЩАЯ) {
                        // TODO: 23.12.2021  методы работы  со статусом work manager ОБЩЕГО
                        МетодРаботаWorkManagerНачалась(workInfoДополнительнаОБЩАЯ);
                        МетодРаботаWorkManagerКонец(workInfoДополнительнаОБЩАЯ);
                        МетодУспешнойРаботыОБЩЕГОWorkManager(workInfoДополнительнаОБЩАЯ);
                    }
                });
                // TODO: 29.09.2021  конец синхрониазции по раписанию
                Log.d(this.getClass().getName(), " WorkInfoИнформацияОЗапущенойСлужбеОдноразовая  СтастусWorkMangerЧата " + " ИмяСлужбыСинхронизацииОдноразовая " + ИмяСлужбыСинхронизацииОдноразовая);
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
                            handlerTaskFragment2.sendMessage(message);
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
                        // TODO: 14.01.2022
                        Integer  CallBaskОтWorkManagerОБЩАЯ= СтастусWorkMangerДляФрагментаЧитатьИПисать
                                .getOutputData().getInt("ReturnPublicAsyncWorkMananger",0);
                            // TODO: 14.01.2022
                            Message message = new Message();
                            Bundle bundle=new Bundle();
                            bundle.putString("ОперациЯПрошлаЧат","Успешный обмен данными !!!");
                            message.setData(bundle);
                            handlerTaskFragment2.sendMessage(message);

                    });
        }
        // TODO: 03.08.2022  метод начала работы WorkManger
        private void МетодРаботаWorkManagerНачалась(List<WorkInfo> workInfos) {
            // TODO: 03.08.2022 успешный ответ
            workInfos.stream().filter(СтастусWorkMangerДляФрагментаЧитатьИПисать ->
                            СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.RUNNING) == 0)
                    .forEachOrdered((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                        // TODO: 18.02.2022
                        handlerTaskFragment2.post(()->{
                            progressBarTaskFragment2.setVisibility(View.VISIBLE);
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
                        handlerTaskFragment2.post(()->{
                            progressBarTaskFragment2.setVisibility(View.INVISIBLE);
                        });
                    });
        }



        // TODO: 04.03.2022 прозвомжность инициализации RecycleView

        void МетодЗаполенияRecycleViewДляЗадач(@NonNull View viewДляПервойКнопкиHome_Задания) {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK  " + Курсор_ГлавныйКурсорДляЗадач);
                // TODO: 04.03.2022  В ДАННОМ КОДЕ МЫ ОПЕРДЕЛЯЕМ КАКОЙ ЭКОРАН БУДЕМ ЗАГРУЖАТЬ В ЗАВПИСИМОСТИ ЕСЛИ ЛИ ДАННЫЫЕ ЗАДАЧИ
                myRecycleViewAdapter = new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2.MyRecycleViewAdapter(Курсор_ГлавныйКурсорДляЗадач);
                recyclerView.setAdapter(myRecycleViewAdapter);
                Log.d(this.getClass().getName(), "onItemRangeMoved  Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе +
                        " Курсор_ДляПолученияДАнныхДляЗАДАЧTASK " + Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе);
                // TODO: 14.03.2022
                Log.d(this.getClass().getName(), "      subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент2.МетодСлушательObserverДляRecycleView()  МетодИнициализацииRecycleViewДляЗадач()  ");
                // TODO: 22.03.2022
                if (recyclerView!=null) {
                    // TODO: 04.03.2022
                    recyclerView.forceLayout();
                    recyclerView.requestLayout();
                }
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2 recyclerView   " + recyclerView);
                if (bottomNavigationViewДляTasks!=null) {
                    // TODO: 13.03.2022
                    bottomNavigationViewДляTasks.requestLayout();
                }
                // TODO: 04.03.2022
                if (linearLayou!=null) {
                    linearLayou.requestLayout();
                    linearLayou.forceLayout();
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

        // TODO: 04.03.2022 прозвомжность инициализации RecycleView

        void МетодИнициализацииRecycleViewДляЗадач(@NonNull View viewДляПервойКнопкиHome_Задания) {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK  " + Курсор_ГлавныйКурсорДляЗадач);
                // TODO: 14.03.2022
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                // TODO: 28.02.2022 создаем наш первый RecyclerView
                DividerItemDecoration    dividerItemDecoration = new DividerItemDecoration(
                        recyclerView.getContext(),
                        DividerItemDecoration.HORIZONTAL);
                recyclerView.removeItemDecoration(dividerItemDecoration);
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setLayoutManager(linearLayoutManager);//TODO new LinearLayoutManager(getContext()) // TODO: 28.02.2022 создаем наш первый RecyclerView recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
                                    Log.d(this.getClass().getName(), " R.id.id_taskHome отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2  fragmentTransactionляЗадачи  "
                                            + fragmentTransactionляЗадачи + " R.id.id_taskHome  item.getItemId() " + item.getItemId());
                                    ///
                                    item.setChecked(true);
                                    fragment_ТекущийФрагмент = new Fragment1_One_Tasks();
                                    fragmentTransactionляЗадачи.replace(R.id.activity_main_fisrt_for_tasks, fragment_ТекущийФрагмент);//.layout.activity_for_fragemtb_history_tasks
                                   // fragmentTransactionляЗадачи.addToBackStack(null);
                                    fragmentTransactionляЗадачи.commit();
                                    fragmentManagerДляЗадачи.executePendingTransactions();
                                    fragmentTransactionляЗадачи.show(fragment_ТекущийФрагмент);
                                    Log.d(this.getClass().getName(), " bottomNavigationViewДляTasks.getChildCount() " + bottomNavigationViewДляTasks.getChildCount());
                                    return false;

                                // TODO: 09.03.2022////
                                case R.id.id_taskCreateNewTasks:
                                    // TODO: 09.03.2022
                                    ///////////// перед переходом на новую задачи выключем WORK MANAGE
                                    Log.d(this.getClass().getName(), " R.id.id_taskCreateNewTasks  item.getItemId() " + item.getItemId());
                                    item.setChecked(true);
                                    fragment_ТекущийФрагмент = new Fragment3_Now_Create_Tasks();
                                    fragmentTransactionляЗадачи.replace(R.id.activity_main_fisrt_for_tasks, fragment_ТекущийФрагмент);//.layout.activity_for_fragemtb_history_tasks
                                   // fragmentTransactionляЗадачи.addToBackStack(null);
                                    fragmentTransactionляЗадачи.commit();
                                    fragmentManagerДляЗадачи.executePendingTransactions();
                                    fragmentTransactionляЗадачи.show(fragment_ТекущийФрагмент);
                                    Log.d(this.getClass().getName(), " fragmentTransactionляЗадачи " + fragmentTransactionляЗадачи);
                                    Log.d(this.getClass().getName(), " bottomNavigationViewДляTasks.getChildCount() " + bottomNavigationViewДляTasks.getChildCount());
                                    return false;
                                // TODO: 09.03.2022////
                                // TODO: 14.03.2022 кнопка принудиьельной сихронизации
                                case R.id.id_taskAsyns :
                                    // TODO: 19.02.2022
                                    handlerTaskFragment2.post(()->{
                                        progressBarTaskFragment2.setVisibility(View.VISIBLE);
                                    });
                                    // TODO: 10.03.2022
                                    item.setChecked(true);
                                    if (! WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().isEmpty()) {
                                        WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                                WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                                        if (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING)!=0) {
                                  /*          // TODO: 01.08.2022 запускаем синхронищзцию
                                            class_generation_sendBroadcastReceiver_and_firebase_oneSignal.
                                                    МетодЗапускаетОДНОРАЗОВУЮСинхронизациюПослкеУспешнойПроведеннойОперации(ПубличныйIDДляФрагмента, getContext());*/

                                            // TODO: 01.08.2022  
                                            handlerTaskFragment2.postDelayed(()->{
                                        /*        Message message = new Message();
                                                Bundle bundle=new Bundle();
                                                bundle.putString("ОперациЯПрошлаЧат","Успешный обмен данными !!!");
                                                message.setData(bundle);
                                                handlerTaskFragment2.sendMessage(message);*/
                                                progressBarTaskFragment2.setVisibility(View.GONE);
                                            },2000);


                                        }}
                                    // TODO: 09.03.2022
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

        private void МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(@NonNull Cursor cursorДЛяОпределенияНужноПоказыватьЗначеиЛИнЕТ)
                throws ExecutionException, InterruptedException {
            // TODO: 02.03.2022
            try {
                // TODO: 02.03.2022
                Log.d(this.getClass().getName(), "  cursorДЛяОпределенияНужноПоказыватьЗначеиЛИнЕТ " + cursorДЛяОпределенияНужноПоказыватьЗначеиЛИнЕТ);


                // TODO: 06.03.2022
                bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskCreateNewTasks).setVisible(false);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                // TODO: 09.03.2022
                bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskCreateNewTasks).setNumber(Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount());
                // TODO: 10.03.2022
                bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskCreateNewTasks).setBackgroundColor(Color.BLACK);


                // TODO: 09.03.2022
                if (cursorДЛяОпределенияНужноПоказыватьЗначеиЛИнЕТ.getCount() > 0) {
                    // TODO: 06.03.2022
                    bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskHome).setVisible(true);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true); R.id.id_taskHome todo R.id.id_taskCreateNewTasks
                    // TODO: 06.03.2022
                    bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskHome).setNumber(Курсор_ДляПолученияДАнныхТОлькоДляЗадачВработе.getCount());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    // TODO: 09.03.2022
                    bottomNavigationViewДляTasks.getOrCreateBadge(R.id.id_taskHome).setBackgroundColor(Color.parseColor("#8B0000"));
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
                recyclerView.requestLayout();
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
            private MaterialTextView textView1ОписаниеЗадачиФрагмент2, textView2, textView3, textView4, textView5, textView6, textView7;
            // TODO: 13.03.2022
            private   MaterialCardView materialCardView;

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
                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2 itemView   " + itemView);
                    // TODO: 02.03.2022
                    textView1ОписаниеЗадачиФрагмент2 = (MaterialTextView) itemView.findViewById(R.id.text1_innercardview);
                    // TODO: 02.3.2022  дополнительный
                    textView2 = (MaterialTextView) itemView.findViewById(R.id.text2_innercardviewtwo);
                    // TODO: 28.02.2022
                    textView3 = (MaterialTextView) itemView.findViewById(R.id.text3_innercardviewtree);
                    // TODO: 28.02.2022
                    textView4 = (MaterialTextView) itemView.findViewById(R.id.text4_innercardviewfour);
                    // TODO: 28.02.2022
                    textView5 = (MaterialTextView) itemView.findViewById(R.id.text5_innercardviewtype_tasks);
                    // TODO: 13.03.2022
                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2 materialCardView  textView2 " + textView4);
                    // TODO: 30.03.2022
                    // TODO: 13.03.2022
                    textView6 = (MaterialTextView) itemView.findViewById(R.id.text9_innercardview_callsbaks);
                    // TODO: 30.03.202

                    // TODO: 31.03.2022
                    textView7 = (MaterialTextView) itemView.findViewById(R.id.text0_innercardview_headmessage_create_task);
                    // TODO: 31.03.2022
                    // TODO: 30.03.2022
                    // TODO: 01.03.2022
                    materialCardView = (MaterialCardView) itemView.findViewById(R.id.cardviewmatirealtask);
                    // TODO: 13.03.2022
                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2 materialCardView   " + materialCardView);
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

                Log.i(this.getClass().getName(), "     getItemId holder.position  Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри " + Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри);
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // TODO: 10.03.2022
                View viewГлавныйВидДляRecyclleViewДляЗаданий = null;
                try {
                    // TODO: 28.02.2022
                    if (Курсор_ГлавныйКурсорДляЗадач.getCount()>0) {
                        // TODO: 22.03.2022
                        viewГлавныйВидДляRecyclleViewДляЗаданий = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_takst_cardview2, parent, false);//todo old R.layout.simple_for_takst_cardview1

                        // TODO: 05.03.2022
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляЗаданий" + viewГлавныйВидДляRecyclleViewДляЗаданий);
                        // TODO: 28.02.2022
                    } else {

                        // TODO: 22.03.2022
                        viewГлавныйВидДляRecyclleViewДляЗаданий = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_takst_cardview22_isempty_task2, parent, false);//todo old R.layout.simple_for_takst_cardview1

                        // TODO: 05.03.2022
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляЗаданий" + viewГлавныйВидДляRecyclleViewДляЗаданий);
                        // TODO: 28.02.2022
                        viewГлавныйВидДляRecyclleViewДляЗаданий.setFocusable(false);
                        viewГлавныйВидДляRecyclleViewДляЗаданий.setClickable(false);
                        // TODO: 22.03.2022


                    }

                    // TODO: 22.03.2022

                    myViewHolder = new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2.MyViewHolder(viewГлавныйВидДляRecyclleViewДляЗаданий);


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
                    // TODO: 14.03.2022


                    // TODO: 16.03.2022
                      /*  int   ИндексПпрочитанные = R.drawable.icon_dsu1_create_new_tasks; //R.drawable.icon_dsu1_create_new_tasks;
                        Drawable  drawableПпрочитанные
                                = getContext().getDrawable(ИндексПпрочитанные);
                        // TODO: 15.03.2022
                        holder.materialCardView.setBackgroundDrawable(drawableПпрочитанные);*/

                    // TODO: 02.03.2022 тут РАЗДАЕМ ДАННЫЕ RECYCLERBIEW


                    // TODO: 22.03.2022
                    /* Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.moveToPosition(position);//todo old  Курсор_ДляПолученияДАнныхДляЗАДАЧTASK.move(position);*/

                    // TODO: 04.03.2022 p==osion
                    Log.i(this.getClass().getName(), "  Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getPosition() " + Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getPosition());


                    // TODO: 14.03.2022  метод создания само сообщения
                    МетодБиндингаСозданиеСамоСообщения(holder);


                    // TODO: 14.03.2022  метод создания номер задания
                    МетодБиндингаНомерЗадания(holder);


                    // TODO: 14.03.2022  метод создания дата задания
                    МетодБиндингаДатаЗадания(holder);


                    // TODO: 14.03.2022  метод создания ФИО задания
                    МетодБиндингаФИОДляЗадания(holder);


                    // TODO: 13.03.2022 СЛУШАТЕЛЬ для ДОЛГОВО НАЖАТИЯ СМЕНЫ СТАТУСА

                    МетодБиндингаСлушателейДляViewCard(holder);


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


                    // TODO: 02.03.2022#5 создание Для заполнения Примечание

                    МетодБиндингаПримечаниеСамогоСообщенияCallBask(holder);

                    Log.i(this.getClass().getName(), "      holder.textView1  accessibilityNodeInfo "+ " СамСтатусПрочтенияИлиНет " + СамСтатусПрочтенияИлиНет);


                    // TODO: 31.03.2022  метод для создание Шабка Задачи

                    МетодБиндингаШабкаЗадачи(holder);


                    // TODO: 29.07.2022 метод анимации для recyreview задачи  2

                    МетодАнимацииRecycreView(holder);





                    // TODO: 13.03.2022 настройки для carview

                    holder.materialCardView.toggle();

                    // TODO: 13.03.2022
                    holder.materialCardView.setChecked(true);
                    // TODO: 15.03.2022

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

            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {


                if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount() > 0) {
                    // TODO: 30.03.2022
                    Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.moveToFirst();

     /*  recyclerView.removeAllViews();
       recyclerView.getRecycledViewPool().clear();*/
                    // TODO: 30.03.2022
                    //   Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.moveToNext();
                    Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.moveToPosition(position);

                }


                recyclerView.requestLayout();

                // TODO: 30.03.2022
                recyclerView.forceLayout();





                super.onBindViewHolder(holder, position, payloads);
            }

            @Override
            public int getItemViewType(int position) {
                return super.getItemViewType(position);
            }

            @Override
            public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {


                recyclerView.removeAllViews();

                recyclerView.getRecycledViewPool().clear();
                super.onAttachedToRecyclerView(recyclerView);
            }


            private void МетодБиндингаЗаполненияДаннымиBungle(@NonNull MyViewHolder holder, Integer СамСтатусПрочтенияИлиНет) {
                // TODO: 03.03.2022
                try {

                    Integer ИндексUUIDДЛяЗАДАНИЯКотореВыбрали = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("uuid");///"uuid_notifications"
                    // TODO: 02.03.2022 получет UUID строчки

                    Long UUIDДЛяЗАДАНИЯКотореВыбрали = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getLong(ИндексUUIDДЛяЗАДАНИЯКотореВыбрали);

                    Log.i(this.getClass().getName(), "  UUIDДЛяЗАДАНИЯКотореВыбрали " + UUIDДЛяЗАДАНИЯКотореВыбрали);

                    // TODO: 13.03.2022

                    Integer позиция = holder.getAdapterPosition();

                    Log.i(this.getClass().getName(), "  позиция " + позиция);

                    // TODO: 14.03.2022  заполем данными для получение  UUID вышке
                    BungleДанныеДляViewCard.putLong(String.valueOf(позиция), UUIDДЛяЗАДАНИЯКотореВыбрали);


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

            // TODO: 30.03.2022

            private void МетодБиндингПолучаемТипЗадания(@NonNull MyViewHolder holder) {

                try {
                    Integer ИндексСтатусТипаЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("type_tasks");
                    // TODO: 02.03.2022

                    String СамТипЗадания = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getString(ИндексСтатусТипаЗадачи);

                    Log.i(this.getClass().getName(), "  СамТипЗадания " + СамТипЗадания);

                    holder.textView5.setText( СамТипЗадания);

                    // TODO: 01.03.2022 слушатели
                    View.OnClickListener clickListener=     МетодСозданиеСлушателяДляФрагмента2НаВсеКомпоненты(holder);

                    holder.textView5.setOnClickListener(clickListener);

                    // holder.textView5.setText("тип: " + СамТипЗадания);
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

            // TODO: 30.03.2022

            private void МетодБиндингаПримечаниеСамогоСообщенияCallBask(@NonNull SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2.MyViewHolder holder) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    Integer ИндексСамогоПримечаниезадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("callsback_note_task");
                    // TODO: 02.03.2022
                    String СамогоПримечанияЗАДАНИЯ = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getString(ИндексСамогоПримечаниезадачи);
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  СамогоСообщенияЗадачиДляПользователя " + СамогоПримечанияЗАДАНИЯ);

                    СамогоПримечанияЗАДАНИЯ=Optional.ofNullable(СамогоПримечанияЗАДАНИЯ).orElse("");
                    // TODO: 28.02.2022
                    if (СамогоПримечанияЗАДАНИЯ.length()>0) {

                        // TODO: 30.03.2022
                        holder.textView6.setText( СамогоПримечанияЗАДАНИЯ);

                    }else {
                        // TODO: 30.03.2022
                        holder.textView6.setText( "");
                        holder.textView6.setHintTextColor(Color.GRAY);
                        holder.textView6.setHint( "Нет примечания");

                    }

                    // TODO: 01.03.2022 слушатели
                    View.OnClickListener clickListener=     МетодСозданиеСлушателяДляФрагмента2НаВсеКомпоненты(holder);

                    holder.textView6.setOnClickListener(clickListener);


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
                    Integer ИндексСамогоШабкаЗАдачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("message");
                    // TODO: 02.03.2022
                    String СамогоШабкаЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getString(ИндексСамогоШабкаЗАдачи);
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  МетодБиндингаШабкаЗадачи  СамогоШабкаЗадачи " + СамогоШабкаЗадачи);

                    // TODO: 30.03.2022

                    holder.textView7.setText( СамогоШабкаЗадачи);


                    // TODO: 01.03.2022 слушатели
                    View.OnClickListener clickListener=     МетодСозданиеСлушателяДляФрагмента2НаВсеКомпоненты(holder);

                    holder.textView7.setOnClickListener(clickListener);


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
            // TODO: 29.07.2022  метод анимации для recycrevuiew




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




            @NonNull

            private Integer МетодБиндингаПолученияСтатусаЗадачи(@NonNull MyViewHolder holder) {
                // TODO: 02.03.2022#5
                Integer СамСтатусПрочтенияИлиНет = 0;
                try {

                    Integer ИндексСтатусПрочтенияИлиНЕт = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("status_write");
                    // TODO: 02.03.2022

                    СамСтатусПрочтенияИлиНет = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getInt(ИндексСтатусПрочтенияИлиНЕт);
                    // TODO: 03.03.2022

                    holder.textView1ОписаниеЗадачиФрагмент2.setTag(СамСтатусПрочтенияИлиНет);


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
                    Integer ИндексКтоНаписалСообщение = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("id_user");// TODO: 15.03.2022 user_update
                    // TODO: 02.03.2022
                    КтоНаписалСообщениеФИОдЛПосика = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getInt(ИндексКтоНаписалСообщение);
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
                    holder.textView3.setText( ФИОКотоНаписал.trim());

                    // TODO: 17.06.2022 Получаеним UUID созданной задачи и передаем ее фоагменту5
                    Integer ИндексПолучаемUUIDУжеСозданойЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("uuid");
                    // TODO: 02.03.2022
                    Long UUIDЗадачиТекущей = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getLong(ИндексПолучаемUUIDУжеСозданойЗадачи);
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  UUIDЗадачиТекущей " + UUIDЗадачиТекущей);

                    holder.textView3.setTag(UUIDЗадачиТекущей);

                    // TODO: 01.03.2022 слушатели
                    View.OnClickListener clickListener=     МетодСозданиеСлушателяДляФрагмента2НаВсеКомпоненты(holder);

                    holder.textView3.setOnClickListener(clickListener);

                    // holder.textView3.setText("кому: " + ФИОКотоНаписал.trim());
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
                        holder.textView4.setText( СамаДАтаЗадачиТекущей);


                        // TODO: 01.03.2022 слушатели
                        View.OnClickListener clickListener=     МетодСозданиеСлушателяДляФрагмента2НаВсеКомпоненты(holder);

                        holder.textView4.setOnClickListener(clickListener);
                    }


                    // holder.textView4.setText("дата: " + СамаДАтаЗадачиТекущей);
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
                    //   Integer ИндексПолучаемIDЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("id");
                    if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0) {
                        Integer ИндексПолучаемIDЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("status_write");
                        // TODO: 02.03.2022
                        Integer IDЗадачиТекущей = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getInt(ИндексПолучаемIDЗадачи);
                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), "  IDЗадачиТекущей " + IDЗадачиТекущей);

                        holder.textView2.setTextColor(Color.BLACK);

                        switch (IDЗадачиТекущей){
                            case 0:
                                // TODO: 07.06.2022
                                // TODO: 28.02.2022
                                //holder.textView2.setText("#" + String.valueOf(IDЗадачиТекущей));
                                // TODO: 30.03.2022
                                holder.textView2.setText( "В процессе...");
                                break;

                            case 1:
                                // TODO: 07.06.2022
                                // TODO: 28.02.2022
                                //holder.textView2.setText("#" + String.valueOf(IDЗадачиТекущей));

                                holder.textView2.setText("Отказ" );
                                break;


                            case 2:
                                // TODO: 07.06.2022
                                // TODO: 28.02.2022
                                //holder.textView2.setText("#" + String.valueOf(IDЗадачиТекущей));
                                holder.textView2.setText("Выполнена");
                                break;



                            case 3:
                                // TODO: 07.06.2022
                                // TODO: 28.02.2022
                                //holder.textView2.setText("#" + String.valueOf(IDЗадачиТекущей));
                                holder.textView2.setText("Отмененная");
                                break;


                        }


                        // TODO: 01.03.2022 слушатели
                        View.OnClickListener clickListener=     МетодСозданиеСлушателяДляФрагмента2НаВсеКомпоненты(holder);

                        holder.textView2.setOnClickListener(clickListener);
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
                    if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0) {
                        // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                        Integer ИндексСамогоСообщенияЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("head_message");//TODO old "message"
                        // TODO: 02.03.2022
                        String СамогоСообщенияЗадачиДляПользователя = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getString(ИндексСамогоСообщенияЗадачи);
                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), "  СамогоСообщенияЗадачиДляПользователя " + СамогоСообщенияЗадачиДляПользователя);
                        // TODO: 28.02.2022
                        holder.textView1ОписаниеЗадачиФрагмент2.setText(СамогоСообщенияЗадачиДляПользователя);

                        // TODO: 23.03.2022  если отказ генерирем для текущего значния ошибку

                        // TODO: 01.03.2022 слушатели
                        View.OnClickListener clickListener=     МетодСозданиеСлушателяДляФрагмента2НаВсеКомпоненты(holder);

                        holder.textView1ОписаниеЗадачиФрагмент2.setOnClickListener(clickListener);
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


            // TODO: 13.03.2022


            private void МетодБиндингаСлушателейДляViewCard(MyViewHolder holder) {
                // TODO: 01.03.2022 слушатели

                try {
// TODO: 25.03.2022

                    // TODO: 01.03.2022 слушатели УДАЛЯЕМ ВЫБРАНУЮ ЗАДЧУ СОЗДАНУЮ МНОЙ

                    holder.materialCardView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {


                            // TODO: 25.03.2022
                            int ФлагЗнака = R.drawable.icon_dsu1_new_customer2;

                            MaterialAlertDialogBuilder alertDialogУдалениеЗадачи = new MaterialAlertDialogBuilder(getActivity());

                            // TODO: 29.03.2022

                            alertDialogУдалениеЗадачи
                                    .setTitle("Удаление")
                                    .setMessage("Удалить созданую задачу" + "\n" + "(" + holder.textView1ОписаниеЗадачиФрагмент2.getText().toString() + ")" + ".")
                                    .setPositiveButton("Да", null)
                                    .setNegativeButton("Нет", null)
                                    .setIcon(ФлагЗнака);
/////////кнопка
                            MaterialAlertDialogBuilder MessageBoxUpdateУдалаениеЗаданияДА = alertDialogУдалениеЗадачи.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO: 29.03.2022
                                    Log.d(this.getClass().getName(), "  ФИНАЛ создание нового сотрудника ");
                                    // TODO: 29.03.2022
                                    try {
                                        // TODO: 29.03.2022


                                        // TODO: 29.03.2022

                                        ProgressDialog progressDialogДляУдаленияСвоегоЗадание;
                                        // TODO: 26.10.2021
                                        // TODO: 26.10.2021
                                        progressDialogДляУдаленияСвоегоЗадание = new ProgressDialog(getActivity());
                                        // TODO: 25.03.2022
                                        progressDialogДляУдаленияСвоегоЗадание.setTitle("Задача");
                                        progressDialogДляУдаленияСвоегоЗадание.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                        progressDialogДляУдаленияСвоегоЗадание.setProgress(0);
                                        progressDialogДляУдаленияСвоегоЗадание.setCanceledOnTouchOutside(false);
                                        progressDialogДляУдаленияСвоегоЗадание.setMessage("удаление ....");
                                        progressDialogДляУдаленияСвоегоЗадание.show();




                                                    // TODO: 25.03.2022
                                                    progressDialogДляУдаленияСвоегоЗадание.dismiss();
                                                    ////
                                                    progressDialogДляУдаленияСвоегоЗадание.cancel();
                                                    // TODO: 25.03.2022

                                                    // TODO: 13.01.2022  ЗАПУСК СИХРОНИЗВАЦИИ В ХОЛОСТУЮ ХОД

                                                    WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                                            WorkManager.getInstance(getContext().getApplicationContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                                                    if (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING)!=0) {
                                                     /*   class_generation_sendBroadcastReceiver_and_firebase_oneSignal.
                                                                МетодЗапускаетОДНОРАЗОВУЮСинхронизациюПослкеУспешнойПроведеннойОперации(ПубличныйIDДляФрагмента, getContext());*/

                                                    }



                                        // TODO: 13.03.2022
                                        Log.d(this.getClass().getName(), "  SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2  ПозицияЭлментаVIewCardДополнительно  СтатусПрочтеаУжеЗадачаИлиНет ");
                                        // TODO: 04.03.2022  ПОЛУЧЕНИЕ НАЗВАНЕИ ЗАДАЧИ


                                        // TODO: 13.03.2022
                                        Long ПолучаемUUIDТекущйПозицииВRecyreView = BungleДанныеДляViewCard.getLong((String.valueOf(holder.getAdapterPosition())), 0l);


                                        Integer РезультатУдаленияСозданныйЗадач = null;
                                        try {
                                            РезультатУдаленияСозданныйЗадач = new CoreBinessLogics(getContext())
                                                    .УдалениеДанныхЧерезКонтейнерУниверсальная("data_notification",
                                                            "uuid", ПолучаемUUIDТекущйПозицииВRecyreView, "status_write", "5");

                                            // TODO: 25.03.2022
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

                                        /////////TODO КОНТЕЙНЕР УДАЛЕНИЕ СОТРУДНИКА ИЗ ТАБЕЛЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ

                                        // TODO: 13.03.2022
                                        Log.d(this.getClass().getName(), " РезультатУдаленияСозданныйЗадач МетодБиндингаСлушателейДляViewCard   " + РезультатУдаленияСозданныйЗадач);


                                        // TODO: 29.03.2022  конец переносимого кода

                                        dialog.dismiss();
                                        // TODO: 25.03.2022 \
                                        dialog.cancel();


                                        if (РезультатУдаленияСозданныйЗадач > 0) {


                                            // TODO: 04.03.2022

                                            Message message = new Message();
                                            Bundle bundle = new Bundle();
                                            bundle.putString("ОперациЯПрошлаЧат", "Успешный обмен данными !!!");
                                            message.setData(bundle);
                                            handlerTaskFragment2.sendMessage(message);

                                        }
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
                                }
                            });


                            /////////кнопка
                            MaterialAlertDialogBuilder MessageBoxUpdateУдалаениеЗаданияНЕТ = alertDialogУдалениеЗадачи.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO: 29.03.2022
                                    try {
                                        // TODO: 29.03.2022
                                        Log.d(this.getClass().getName(), " MessageBoxUpdateУдалаениеЗаданияНЕТ");

                                        dialog.dismiss();
                                        // TODO: 25.03.2022 \
                                        dialog.cancel();

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
// TODO: 29.03.2022
                            // TODO: 29.03.2022
                            Log.d(this.getClass().getName(), " MessageBoxUpdateУдалаениеЗаданияНЕТ");

                            alertDialogУдалениеЗадачи.show();

                            // TODO: 29.03.2022 defalult
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


            // TODO: 15.03.2022  третий слушатель просто клик
            private void МетодБиндингаСлушателейТретьийСлушательПростоКликДляCard(MyViewHolder holder) {
                // TODO: 01.03.2022 слушатели

                try {
// TODO: 01.03.2022 слушатели
                    View.OnClickListener clickListener=     МетодСозданиеСлушателяДляФрагмента2НаВсеКомпоненты(holder);

                    holder.materialCardView.setOnClickListener(clickListener);

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

            private View.OnClickListener МетодСозданиеСлушателяДляФрагмента2НаВсеКомпоненты(MyViewHolder holder) {


                View.OnClickListener clickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 01.03.2022
                        try {
                            // TODO: 13.03.2022
                            if (Курсор_ГлавныйКурсорДляЗадач.getCount()>0) {
                                Log.d(this.getClass().getName(), "  SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент2  ПозицияЭлментаVIewCardДополнительно  " +
                                        " holder.getAdapterPosition() " + holder.getAdapterPosition() + " v.getTag() " + v.getTag(holder.materialCardView.getId()));
                                holder.itemView.animate().rotationX(10l);//.rotationXBy(4l);
                                // TODO: 28.07.022 ам переход
                                handlerTaskFragment2.postDelayed(()-> {
                                    // TODO: 03.03.2022 ПЕРЕХОДИМ НА ДРУГОЙ Е АКТВИТИ ПРОСТО СОЗДАНОЙ МНОЙ ЗАДАЧЕЙ
                                    // TODO: 30.03.2022 ЗАПУСКАЕМ ЕЩЕ НЕТ СТАТУСА ЗАДАЧИ
                                    Bundle bundleПередачаПараметровФрагментовСозданойМноюЗадачей = МетодЗаполенияДаннымиДляПердеачиВДругуюФрагментСозданойМнойюЗадачи(holder);
                                    // TODO: 13.03.2022
                                    Log.d(this.getClass().getName(), "   Fragment4_Now_Views_Task_For_Complete SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1   ПозицияЭлментаVIewCardДополнительно  " +
                                            " holder.getAdapterPosition() " + holder.getAdapterPosition() + " v.getTag() " + v.getTag(holder.materialCardView.getId())+
                                            "  bundleПередачаПараметровФрагментовСозданойМноюЗадачей " +bundleПередачаПараметровФрагментовСозданойМноюЗадачей);
                                    // TODO: 09.03.2022
                                    fragmentTransactionляЗадачи = fragmentManagerДляЗадачи.beginTransaction();
                                    // TODO: 23.03.2022  переходим на фрагмент для редактирования Fragment4_Now_Views_Task_For_Complete
                                    fragment_ТекущийФрагмент = new Fragment5_ViewcreatedTasks();
                                    // TODO: 06.06.2022
                                    fragment_ТекущийФрагмент.setArguments(bundleПередачаПараметровФрагментовСозданойМноюЗадачей);
                                    // TODO: 11.03.2022
                                    fragmentTransactionляЗадачи.replace(R.id.activity_main_fisrt_for_tasks, fragment_ТекущийФрагмент).commit();//.layout.activity_for_fragemtb_history_tasks
                                    // TODO: 10.03.2022
                                    fragmentTransactionляЗадачи.show(fragment_ТекущийФрагмент);
                                    bottomNavigationViewДляTasks.forceLayout();
                                    recyclerView.requestLayout();
                                    recyclerView.forceLayout();
                                    linearLayou.requestLayout();

                                },500);
                            }
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
                };

// TODO: 16.06.2022
                return  clickListener;
            }


            @NonNull
            private Bundle МетодЗаполенияДаннымиДляПердеачиВДругуюФрагментСозданойМнойюЗадачи(MyViewHolder holder) {
                //TODO
                Bundle bundleПередачаПараметровФрагментовСозданойМноюЗадачей = new Bundle();
                try{
                    Log.d(this.getClass().getName(), "  bundleПередачаПараметровФрагментовСозданойМноюЗадачей holder  "+
                            holder.textView1ОписаниеЗадачиФрагмент2.getText().toString());

                    //TODO
                    bundleПередачаПараметровФрагментовСозданойМноюЗадачей.putString("ДляФрагмента5_НазваниеСозданойМнейЗадача",holder.textView7.getText().toString());
                    //TODO
                    //TODO
                    bundleПередачаПараметровФрагментовСозданойМноюЗадачей.putString("ДляФрагмента5_СамаСозданнаяМнойЗадача",holder.textView1ОписаниеЗадачиФрагмент2.getText().toString());
                    //TODO
                    //TODO
                    bundleПередачаПараметровФрагментовСозданойМноюЗадачей.putString("ДляФрагмента5_ФИОДЛяКогоСозданаЗадача",  holder.textView3.getText().toString());
                    //TODO
                    //TODO
                    bundleПередачаПараметровФрагментовСозданойМноюЗадачей.putString("ДляФрагмента5_ПериодЗадачиСозданной",holder.textView5.getText().toString());
                    //TODO
                    //TODO
                    bundleПередачаПараметровФрагментовСозданойМноюЗадачей.putString("ДляФрагмента5_ПримечаниеОтказИлиВыполнили",holder.textView6.getText().toString());

                    //TODO еще два дата и  статус

                    //TODO
                    bundleПередачаПараметровФрагментовСозданойМноюЗадачей.putString("ДляФрагмента5_ДатаОтказИлиВыполнили",holder.textView4.getText().toString());
                    //TODO
                    bundleПередачаПараметровФрагментовСозданойМноюЗадачей.putString("ДляФрагмента5_СтатусЗадачиСозданной",holder.textView2.getText().toString());
                    //TODO


                    // TODO: 17.06.2022 Получаеним UUID созданной задачи и передаем ее фоагменту5

                    // TODO: 02.03.2022
                    Long UUIDЗадачиТекущей = Long.parseLong(holder.textView3.getTag().toString());
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  UUIDЗадачиТекущей " + UUIDЗадачиТекущей);

                    bundleПередачаПараметровФрагментовСозданойМноюЗадачей.putLong("ДляФрагмента5_UUIDЗадачиСозданной",UUIDЗадачиТекущей);
                    //TODO
                    // TODO: 13.03.2022
                    Log.d(this.getClass().getName(), "  bundleПередачаПараметровФрагментовСозданойМноюЗадачей "
                            + bundleПередачаПараметровФрагментовСозданойМноюЗадачей);


                    // TODO: 17.06.2022  вытаскиваем и переадаем ID c кем мы переписываемся


                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  КтоНаписалСообщениеФИОдЛПосика " + КтоНаписалСообщениеФИОдЛПосика);

                    bundleПередачаПараметровФрагментовСозданойМноюЗадачей.putInt("СкемИдётПерепискаФрагмент5",КтоНаписалСообщениеФИОдЛПосика);
                    //TODO
                    // TODO: 13.03.2022
                    Log.d(this.getClass().getName(), "  bundleПередачаПараметровФрагментовСозданойМноюЗадачей "
                            + bundleПередачаПараметровФрагментовСозданойМноюЗадачей);


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
                return bundleПередачаПараметровФрагментовСозданойМноюЗадачей;
            }























            private void МетодБиндингаСлушательisChered(MyViewHolder holder) {
                // TODO: 14.03.2022

                try {
                    holder.materialCardView.setOnCheckedChangeListener(new MaterialCardView.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(MaterialCardView card, boolean isChecked) {
                            // TODO: 13.03.2022
                            int ИндексПпрочитанные = 0;
                            // TODO: 13.03.2022
                            Drawable drawableПпрочитанные;
                            // TODO: 13.03.2022
                            Log.d(this.getClass().getName(), " card  " + card +
                                    "  holder.getAdapterPosition() " + holder.getAdapterPosition() + " isChecked " + isChecked);

                            // TODO: 13.03.2022

                            if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0) {
                                Integer ИндексСтатусПрочтенияИлиНЕт = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("status_write");
                                // TODO: 02.03.2022

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
                                        ИндексПпрочитанные = R.drawable.icon_dsu1_create_new_tasks;//TODO 0 НЕТ СТАТУСА ЗАДАЧИ ТОЛЬКО СОЗДАНА
                                        // TODO: 30.03.2022


                                        // TODO: 28.06.2022   зависисмости если есть ID значит задача ушла успешно на сервер если нет то серым цветом значсек
                                        // TODO: 13.03.2022

                                        Integer ИндексЕслиIdОтСерврераУЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getColumnIndex("id");
                                        // TODO: 02.03.2022
                                        Integer IdотСервераУТЕкущейЗадачи = Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getInt(ИндексЕслиIdОтСерврераУЗадачи);
                                        switch (IdотСервераУТЕкущейЗадачи){
                                            case 0:
                                                card.setCheckedIconTint(ColorStateList.valueOf(Color.GRAY));
                                                break;

                                            default:
                                                card.setCheckedIconTint(ColorStateList.valueOf(Color.BLACK));
                                                break;
                                        }



                                        card.setDragged(true);
                                        break;

                                    case 1:

                                        // TODO: 25.03.2022
                                        ИндексПпрочитанные = R.drawable.icon_dsu1_for_tasks_desible_task;//TODO 1 СТАТУСА ЗАДАЧИ ОТКАЗ ОТ ВЫПОЛЕНИЯ

                                        // TODO: 30.03.2022
                                        card.setCheckedIconTint(ColorStateList.valueOf(Color.RED));

                                        // TODO: 30.03.2022
                                        break;

                                    case 2:
                                        // TODO: 30.03.2022
                                        ИндексПпрочитанные = R.drawable.icon_dsu1_message_add_contact;//TODO 2 СТАТУСА ЗАДАЧА УСПЕШНО ВЫПОЛНЕНА

                                        // TODO: 30.03.2022
                                        card.setCheckedIconTint(ColorStateList.valueOf(Color.parseColor("#00CED1")));
                                        break;

                                    // TODO: 30.03.2022

                                    case 3:
                                        // TODO: 07.06.2022
                                        // TODO: 25.03.2022
                                        ИндексПпрочитанные = R.drawable.icon_dsu1_for_task_creating;//TODO 3 ЗАДАЧА БЫЛА ОТМЕНЕНАН САМИМ СОЗДАТЕЕЛМ
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
                                drawableПпрочитанные
                                        = getContext().getDrawable(ИндексПпрочитанные);
                                // TODO: 13.03.2022
                                card.setCheckedIcon(drawableПпрочитанные);
                                // TODO: 13.03.2022
                                card.setCheckedIconResource(ИндексПпрочитанные);



                                // TODO: 13.03.2022
                            }else {


                                ИндексПпрочитанные = R.drawable.icon_dsu1_message_add_contact;//TODO 2 СТАТУСА ЗАДАЧА УСПЕШНО ВЫПОЛНЕНА
                                drawableПпрочитанные
                                        = getContext().getDrawable(ИндексПпрочитанные);
                                // TODO: 13.03.2022
                                card.setCheckedIcon(drawableПпрочитанные);
                                // TODO: 13.03.2022
                                card.setCheckedIconResource(ИндексПпрочитанные);
                                // TODO: 30.03.2022
                                card.setCheckedIconTint(ColorStateList.valueOf(Color.BLACK));
                            }

                            // TODO: 20.07.2022
                            // TODO: 13.03.2022
                            card.setSelected(true);
                            // TODO: 30.03.2022
                            card.requestFocus();
                            // TODO: 30.03.2022
                            card.requestLayout();
                            // TODO: 30.03.2022
                            card.forceLayout();
                            // TODO: 30.03.2022
                            // TODO: 30.03.2022
                            recyclerView.requestLayout();
                            // TODO: 30.03.2022
                            recyclerView.forceLayout();


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

                return recyclerView.getAdapter().getItemId(position);//super.getItemId(position)

            }


            private  Cursor МетодПолучениеДанныхФИОаОснованииID(Integer КтоНаписалСообщениеФИОдЛПосика)   {
                Cursor Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал = null;
                try {
                    // TODO: 15.05.202
                    String Текущаятаблицы="chat_users";
                    ModuleQuety moduleQuety=new ModuleQuety(getContext());
                    Курсор_ГлавныйКурсорДляЗадач= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT D.name  FROM "+Текущаятаблицы+" AS D" +
                            "  WHERE D._id='"+КтоНаписалСообщениеФИОдЛПосика+"'   AND D.name IS NOT NULL "+
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
                // TODO: 02.03.2022
                return Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал;
            }


            @Override
            public int getItemCount() {

                // TODO: 02.03.2022
                Integer КоличествоСтрочкеЗадача2;
                ////////
                if (Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount()>0) {
                    // TODO: 18.07.2022
                    КоличествоСтрочкеЗадача2=Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри.getCount();
                } else {
                    // TODO: 18.07.2022
                    КоличествоСтрочкеЗадача2=1;
                }
                Log.d(this.getClass().getName(), "Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри " + Курсор_ДляПолученияДАнныхДляЗАДАЧTASKВнутри+ " КоличествоСтрочкеЗадача2 " +КоличествоСтрочкеЗадача2);
                // TODO: 28.02.2022
                return КоличествоСтрочкеЗадача2;
            }


        }//TODO  конец два


        // TODO: 12.03.2022


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
                        recyclerView.getAdapter().notifyItemRangeChanged(0,Курсор_ГлавныйКурсорДляЗадач.getCount());
                        //TODO
                        bottomNavigationViewДляTasks.forceLayout();
                        recyclerView.requestLayout();
                        recyclerView.forceLayout();
                        linearLayou.forceLayout();
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

                handlerTaskFragment2=  callback;

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 02.08.2022

        // TODO: 02.08.2022  metod launch bing async

    }   // TODO: 28.02.2022 конец класса бизнес логики   // TODO: 28.02.2022 конец класса бизнес логики



    // TODO: 28.02.2022 бизнес -логика    для активти


}    // TODO: 28.02.2022 конец класса






























