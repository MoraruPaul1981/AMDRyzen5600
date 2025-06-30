package com.dsy.dsu.AdmissionMaterialsApp.Window;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.dsy.dsu.AdmissionMaterialsApp.bl_admissonmaterils.PesssionCameta;
import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.bl_launchFragmentSettingsandDashbord.LaunchActivityDashboard;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForAdminissionMaterial;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceUpdatesPO;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.card.MaterialCardView;


import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;


// TODO: 29.09.2022 фрагмент для получение материалов
@SuppressLint("RestrictedApi")
public class FragmentAdmissionMaterials extends Fragment {
    private Integer ПубличныйIDДляФрагмента;
    private RecyclerView recyclerView;
    private LinearLayout linearLayou;
    private Fragment fragment_ТекущийФрагментСогласованиеСписок;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationItemView bottomNavigationItemViewвыход;
    private BottomNavigationItemView bottomNavigationItemView2создать;
    private BottomNavigationItemView bottomNavigationItemView3обновить;
    private ProgressBar progressBarСканирование;
    private LayoutAnimationController layoutAnimationController;
    private Animation animation1;
    private Animation animation2;
    private Message message;

    private  Cursor cursorНомерМатериала;
    private  Cursor cursorСамиДанныеGroupBy;
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private MyViewHolder myViewHolder;
    private Integer ТекущаяЦифраЦФО =0;
    private Integer ТекущаяНомерМатериала=0;
    private String ТекущаяИмяЦФО=new String();
    private  ViewGroup container;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private  TextView   textViewНазваниеФрагмента;
    long start;
    long startДляОбноразвовной;
    private  ServiceForAdminissionMaterial.LocalBinderДляПолучениеМатериалов binderДляПолучениеМатериалов;
    private String ИмяСлужбыСинхронизациОдноразовая="WorkManager Synchronizasiy_Data Disposable";
    private String ИмяСлужбыСинхронизацииОбщая="WorkManager Synchronizasiy_Data";
    private  LifecycleOwner lifecycleOwner =this ;
    private LifecycleOwner lifecycleOwnerОбщая =this ;
    private  Cursor cursorНомерЦФО;

    private  AsyncTaskLoader<Cursor> asyncTaskLoader;
    private ServiceUpdatesPO.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ

    // TODO: 27.09.2022 Фрагмент Получение Материалов
    public FragmentAdmissionMaterials() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            // TODO: 27.09.2022  запускаем фрагмент получение материалов
            МетодHandlerCallBack();
            Bundle bundle=(Bundle)        getArguments();
            if (bundle!=null) {
                binderДляПолучениеМатериалов=  (ServiceForAdminissionMaterial.LocalBinderДляПолучениеМатериалов)        bundle.getBinder("binder" );
            }



             lifecycleOwner =this ;
            lifecycleOwnerОбщая =this ;
            fragmentManager = getActivity().getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

            методCallBackOnActivitymaterail();
            метоГлавныйПолучениеДанныхНаFragmetnMatrial();
            МетодСоздаенияСлушателяДляПолучениеМатериалаWorkMAnager();
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorНомерЦФО " + cursorНомерЦФО);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View    view=null;
        try {
            view= inflater.inflate(R.layout.fragment_admission_materials, container, false);
            this.container=container;
            Log.d(this.getClass().getName(), " onCreateView FragmentAdmissionMaterials" + view);
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

    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);
            recyclerView = view.findViewById(R.id.RecyclerView);
            textViewНазваниеФрагмента = view.findViewById(R.id.TextView);
            textViewНазваниеФрагмента.setText("Поступление материалов".toUpperCase());
            linearLayou = view.findViewById(R.id.fragmentadmissionmaterias);
            bottomNavigationView = view.findViewById(R.id.BottomNavigationView);
            bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationItemViewвыход = bottomNavigationView.findViewById(R.id.id_lback);
            bottomNavigationItemViewвыход.setIconSize(50);
            bottomNavigationItemView2создать = bottomNavigationView.findViewById(R.id.id_create);
            bottomNavigationItemView2создать.setIconSize(70);
            bottomNavigationItemView3обновить = bottomNavigationView.findViewById(R.id.id_async);
            bottomNavigationItemView3обновить.setIconSize(50);
           // animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_vibrator1);
           // animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_tabellist);
            progressBarСканирование=  view.findViewById(R.id.ProgressBar);
            progressBarСканирование.setVisibility(View.VISIBLE);
            animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_tabellist);
            animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_scrolls);
            //todo запуск методов в фрагменте
            МетодЗаполенияRecycleViewДляЗадач();
            МетодИнициализацииRecycreView();
            МетодВыходНаAppBack();
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentAdmissionMaterials  recyclerView  "+recyclerView+
                    " linearLayou "+linearLayou+"  fragmentManager "+fragmentManager);
       start=     Calendar.getInstance().getTimeInMillis();
       startДляОбноразвовной=     Calendar.getInstance().getTimeInMillis();

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
                if (asyncTaskLoader!=null && asyncTaskLoader.isAbandoned()) {
                        try {
                    // TODO: 07.08.2023 методы 
                    методRebootRecyreViewonStop();
                    // TODO: 07.08.2023  методы слушатели
                    МетодСлушательRecycleView();//todo создаем слушатель для recycreview для получение материалов
                    МетодСлушательКурсора();
                    МетодКпопкиЗначков();
                        // TODO: 17.04.2023
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorНомерЦФО " + cursorНомерЦФО);
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

    }

    private void метоГлавныйПолучениеДанныхНаFragmetnMatrial() {
        try{
            if(binderДляПолучениеМатериалов!=null){
                    методAsyncTask();
            }
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorНомерЦФО " + cursorНомерЦФО  +  "binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов);
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
















    // TODO: 04.03.2022 прозвомжность Заполения RecycleView
    void МетодЗаполенияRecycleViewДляЗадач() {
        try {
            myRecycleViewAdapter = new MyRecycleViewAdapter(cursorНомерЦФО);
            myRecycleViewAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(myRecycleViewAdapter);
            recyclerView.getAdapter().notifyDataSetChanged();

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
    public void onStop() {
        super.onStop();
        try{

                metodBackPostionFiestCursors();
            методRebootRecyreViewonStop();
                МетодПерегрузкаRecyceView();
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void методRebootRecyreViewonStop() {

        try{
            if (cursorНомерЦФО!=null) {
                myRecycleViewAdapter.cursor=cursorНомерЦФО;
            }
            myRecycleViewAdapter.notifyDataSetChanged();
            RecyclerView.Adapter recyclerViewОбновление=         recyclerView.getAdapter();
            recyclerView.swapAdapter(recyclerViewОбновление,true);
            recyclerView.getAdapter().notifyDataSetChanged();
            Log.d(this.getClass().getName(), "\n" + " class " +
                Thread.currentThread().getStackTrace()[2].getClassName()
                + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " cursorНомерЦФО " +cursorНомерЦФО);
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

    public void onDestroyView() {
        super.onDestroyView();
        try{
            recyclerView.removeAllViewsInLayout();
            ///WorkManager.getInstance(getContext()).cancelUniqueWork(ИмяСлужбыСинхронизациОдноразовая);
            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая).removeObservers(lifecycleOwnerОбщая);
            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).removeObservers(lifecycleOwner);
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


    // TODO: 06.08.2023  метод слущатель от Активти Материлы
    void методCallBackOnActivitymaterail(){

        fragmentManager.setFragmentResultListener("BackFromActivityBinder", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                // TODO: 03.11.2022  ПОСЛЕ ПОЛУЧЕННЫХ ДАННЫХ
                try{
                binderДляПолучениеМатериалов=
                        (ServiceForAdminissionMaterial.LocalBinderДляПолучениеМатериалов)
                                result.getBinder("binderДляПолучениеМатериалов");

                методAsyncTask();

                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов  );
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
    }



    private void методAsyncTask() {
        message.getTarget().post(()-> {
            asyncTaskLoader = new AsyncTaskLoader<Cursor>(getActivity()) {
                @Nullable
                @Override
                public Cursor loadInBackground() {
                    try {
                        cursorНомерЦФО = методGetCFOCursorFirst("ПолучениеЦФО", 0);

                        Log.d(this.getClass().getName(), "\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " binderДляПолучениеМатериалов " + binderДляПолучениеМатериалов + " cursorНомерЦФО " + cursorНомерЦФО);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return cursorНомерЦФО;
                }
            };
            asyncTaskLoader.startLoading();
            asyncTaskLoader.forceLoad();
            asyncTaskLoader.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener<Cursor>() {
                @Override
                public void onLoadComplete(@NonNull Loader<Cursor> loader, @Nullable Cursor data) {
                    try {
                        if (data != null) {
                            try {
                                cursorНомерЦФО = data;
                                asyncTaskLoader.abandon();
                                onStart();
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorНомерЦФО " + cursorНомерЦФО);
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
        });
    }
    //TODO метод делает callback с ответом на экран
    private void МетодПерегрузкаRecyceView() {
        try {
            bottomNavigationView.requestLayout();
            bottomNavigationView.refreshDrawableState();
            recyclerView.requestLayout();
            recyclerView.refreshDrawableState();
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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


    private void МетодИнициализацииRecycreView() {
        try{
            Log.d(this.getClass().getName(), " recyclerView  "+recyclerView);
            /*DividerItemDecoration dividerItemDecorationVer=
                    new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL);
            /// dividerItemDecorationVer.setDrawable(getContext().getDrawable(R.drawable.divider_for_order_transport1));///R.dimen.activity_horizontal_margin
            recyclerView.addItemDecoration(dividerItemDecorationVer);*/

            DividerItemDecoration dividerItemDecorationHor=
                    new DividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL);
/*            dividerItemDecorationHor.setDrawable(getContext().getDrawable(R.drawable.divider_for_order_transport1));///R.dimen.activity_horizontal_margin*/
            recyclerView.addItemDecoration(dividerItemDecorationHor);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.startAnimation(animation1);



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




    private void МетодВыходНаAppBack() {
        try {
            bottomNavigationItemViewвыход.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        МетодЗапускаАнимацииКнопок(v);//todo только анимауия

                        LaunchActivityDashboard launchActivityDashboard=new LaunchActivityDashboard( fragmentManager,getContext());
                        // TODO: 27.03.2024 в зависомсти кто вызвает
                        launchActivityDashboard.     launchADashboardFragment();


                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
            });
            bottomNavigationItemView2создать.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        v.animate().rotationX(-40l);
                    message.getTarget() .postDelayed(()->{
                            try{
                            v.animate().rotationX(0);
                            // TODO: 28.07.2023
                            МетодЗапускСозданиНовгоМатериалов();
                            Log.d(this.getClass().getName(), "  v  " + v);
                        } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                        },150);


                }
            });
            bottomNavigationItemView3обновить.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    message.getTarget().post(()->{
                            try {
                                progressBarСканирование.setVisibility(View.VISIBLE);
                                МетодЗапускаАнимацииКнопок(v);
                                Integer getPublicIdForError =   new GetttingPublicID().getttingPublicID(getContext());

                                // TODO: 16.11.2022  запуск синхронизации однорозовая
                                Log.d(this.getClass().getName(), "  v  " + v);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(getContext().getClass().getName(),
                                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }

                        });
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
    protected void МетодЗапускСозданиНовгоМатериалов() {
        try{
            //fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            Fragment  fragment_СозданиеНовогоМатериалов = new FragmentMaretialNew();
            Bundle data=new Bundle();
            data.putBinder("binder",binderДляПолучениеМатериалов);
            String FragmentNewImageName=   fragment_СозданиеНовогоМатериалов.getClass().getName();
            fragmentTransaction.addToBackStack(FragmentNewImageName);
            fragment_СозданиеНовогоМатериалов.setArguments(data);

   Fragment    FragmentУжеЕСтьИлиНЕт=     fragmentManager.findFragmentByTag(FragmentNewImageName);
            if (FragmentУжеЕСтьИлиНЕт==null) {
                fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragment_СозданиеНовогоМатериалов).commit();//.layout.activity_for_fragemtb_history_task
                fragmentTransaction.show(fragment_СозданиеНовогоМатериалов);
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов);
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
    // TODO: 02.08.2022











    private void МетодЗапускаАнимацииКнопок(View v) {
        v.animate().rotationX(-40l);
        message.getTarget() .postDelayed(()->{
            v.animate().rotationX(0);
        },150);
    }
    private void МетодКпопкиЗначков( ) {
        try {
                if (   cursorНомерЦФО!=null  && cursorНомерЦФО.getCount()> 0 ) {
                    Log.d(this.getClass().getName(), "  cursorНомерЦФО" + cursorНомерЦФО.getCount());
                    bottomNavigationView.getOrCreateBadge(R.id.id_async).setNumber( cursorНомерЦФО.getCount());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    bottomNavigationView.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.parseColor("#15958A"));
                } else {
                    bottomNavigationView.getOrCreateBadge(R.id.id_async).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    bottomNavigationView.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.RED);
                }
            //TODO
            bottomNavigationView.refreshDrawableState();
            bottomNavigationView.requestLayout();
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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

    void МетодHandlerCallBack() {
        message=Message.obtain(new Handler(Looper.myLooper()),()->{
            try{
            Bundle bundle=   message.getData();
            Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString() + " message " +message );
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
        });
    }
    // TODO: 10.03.2022 БИЗНЕС-КОД для ФРАГМЕНТА ПОСТУПЛЕНИЯ МАТЕРИАЛА

    void МетодСлушательRecycleView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
        try {
                myRecycleViewAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        try {
                            Log.d(this.getClass().getName(), "onChanged ");
                            //TODO
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
                    public void onItemRangeChanged(int positionStart, int itemCount) {
                        super.onItemRangeChanged(positionStart, itemCount);
                        // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeChanged ");
                            //TODO
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
                    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                        super.onItemRangeChanged(positionStart, itemCount, payload);
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeChanged ");
                            //TODO
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
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        super.onItemRangeInserted(positionStart, itemCount);
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeInserted ");
                            //TODO
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
                    public void onItemRangeRemoved(int positionStart, int itemCount) {
                        super.onItemRangeRemoved(positionStart, itemCount);
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeRemoved ");
                            //TODO
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
                    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                        try {
                            Log.d(this.getClass().getName(), "     onItemRangeMoved ");
         /*                   recyclerView.getRecycledViewPool().clear();
                            recyclerView.getAdapter().notifyDataSetChanged();
                            recyclerView.refreshDrawableState();*/
                            //TODO
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
            //TODO
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

    // TODO: 18.10.2021  СИНХРОНИАЗЦИЯ ЧАТА ПО РАСПИСАНИЮ ЧАТ
    @SuppressLint("FragmentLiveDataObserve")
    void МетодСоздаенияСлушателяДляПолучениеМатериалаWorkMAnager()   {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
        try {
            lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
                @Override
                public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                    source.getLifecycle().getCurrentState();
                    event.getTargetState().name();
                }
            });
            lifecycleOwnerОбщая.getLifecycle().addObserver(new LifecycleEventObserver() {
                @Override
                public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                    source.getLifecycle().getCurrentState();
                    event.getTargetState().name();
                }
            });

            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).observe(lifecycleOwner, new Observer<List<WorkInfo>>() {
                @Override
                public void onChanged(List<WorkInfo> workInfos) {
                    workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                                try {
                           if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0)         {
                               Integer  ReturnWorkManager = СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getInt("ReturnSingleAsyncWork", 0);
                               long end = Calendar.getInstance().getTimeInMillis();
                               long РазницаВоврмени=end-startДляОбноразвовной;
                               if (РазницаВоврмени>20000) {
                                   if (ReturnWorkManager>0) {
                                       if (myRecycleViewAdapter.cursor!=null) {
                                           методRebootRecyreViewAfterAsync();
                                           // TODO: 18.04.2023
                                       }
                                      // onStart();
                                       // TODO: 21.11.2022  запускаем удаление
                                       WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).removeObservers(lifecycleOwner);
                                   }
                               }
                                    }
                                    if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.RUNNING) != 0) {
                                        // TODO: 23.05.2023  програсс бар
                                        progressBarСканирование.setVisibility(View.INVISIBLE);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            });
                }
            });
            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая).observe(lifecycleOwnerОбщая, new Observer<List<WorkInfo>>() {
                @Override
                public void onChanged(List<WorkInfo> workInfos) {
                    workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                                try {
                                    if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.ENQUEUED) == 0
                                    ||СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0 ) {
                                        long end = Calendar.getInstance().getTimeInMillis();
                                        Integer  ReturnWorkManager = СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getInt("ReturnSingleAsyncWork", 0);
                                        long РазницаВоврмени=end-start;
                                        if (РазницаВоврмени>20000) {
                                            if (myRecycleViewAdapter.cursor!=null) {
                                                методRebootRecyreViewAfterAsync();
                                            }
                                        }
                                    }
                                       // WorkManager.getInstance(getContext()).cancelAllWorkByTag(ИмяСлужбыСинхронизациОдноразовая).getResult();
                                    if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.RUNNING) != 0) {
                                        // TODO: 23.05.2023  програсс бар
                                        progressBarСканирование.setVisibility(View.INVISIBLE);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            });
                }
            });
            // TODO: 29.09.2021  конец синхрониазции по раписанию
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void методRebootRecyreViewAfterAsync() {
        try{

                Cursor cursorПослеОбновления=       myRecycleViewAdapter.cursor;
                cursorПослеОбновления=  методGetCFOCursorFirst("ПолучениеЦФО",0);

                myRecycleViewAdapter.cursor=cursorПослеОбновления;
                myRecycleViewAdapter.notifyDataSetChanged();
                RecyclerView.Adapter recyclerViewОбновление=         recyclerView.getAdapter();
                recyclerView.swapAdapter(recyclerViewОбновление,true);
                recyclerView.getAdapter().notifyDataSetChanged();
                МетодПерегрузкаRecyceView();
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    // TODO: 02.08.2022
    protected   Cursor методGetCFOCursorFirst(@NonNull String  ФлагКакиеДанныеНужныПолучениеМатериалов, @NonNull Integer ТекущаяЦФО ){
        try{
            ПубличныйIDДляФрагмента      = new GetttingPublicID().getttingPublicID(getContext());

            Log.d(getContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=new Bundle();
            switch (ФлагКакиеДанныеНужныПолучениеМатериалов){
                case "ПолучениеЦФО":
                    bundleДляПЕредачи.putString("Таблица","view_taterials");
                    break;
                case "ПолучениеСгрупированныеСамиДанные":
                case "ПолучениеНомерМатериала":
                    bundleДляПЕредачи.putString("Таблица","view_taterials_group");//TODO сами данные
                    break;
            }
            bundleДляПЕредачи.putInt("ПубличныйIDДляФрагмента",ПубличныйIDДляФрагмента);
            bundleДляПЕредачи.putInt("ТекущаяЦифраЦФО",ТекущаяЦФО);
            bundleДляПЕредачи.putInt("ТекущаяНомерМатериала",ТекущаяНомерМатериала);
            bundleДляПЕредачи.putString("ФлагКакиеДанныеНужныПолучениеМатериалов",ФлагКакиеДанныеНужныПолучениеМатериалов);
            Intent intentПолучениеМатериалов = new Intent(getContext(), ServiceForAdminissionMaterial.class);
            intentПолучениеМатериалов.setAction(ФлагКакиеДанныеНужныПолучениеМатериалов);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            Log.d(this.getClass().getName(), "   ПубличныйIDДляФрагмента "+ ПубличныйIDДляФрагмента);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            //TODO получение данных от Службы ДЛя Получение Материалов
            switch (ФлагКакиеДанныеНужныПолучениеМатериалов){
                case "ПолучениеЦФО":
                    cursorНомерЦФО = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursorНомерЦФО " + cursorНомерЦФО);
                    if (cursorНомерЦФО.getCount() > 0) {
                        cursorНомерЦФО.moveToFirst();
                        // TODO: 15.10.2022
                        Log.d(this.getClass().getName(), "   cursorНомерЦФО " + cursorНомерЦФО);
                    }
                    break;
                case "ПолучениеНомерМатериала":
                    cursorНомерМатериала = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursorНомерМатериалаДляGroupBy " + cursorНомерМатериала);
                    if (cursorНомерМатериала.getCount() > 0) {
                        cursorНомерМатериала.moveToFirst();
                        Log.d(this.getClass().getName(), "   cursorНомерМатериалаДляGroupBy " + cursorНомерМатериала);
                    }
                    break;
                case "ПолучениеСгрупированныеСамиДанные":
                    cursorСамиДанныеGroupBy = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursorСамиДанныеFace " + cursorСамиДанныеGroupBy);
                    if (cursorСамиДанныеGroupBy.getCount() > 0) {
                        cursorСамиДанныеGroupBy.moveToFirst();
                        Log.d(this.getClass().getName(), "   cursorСамиДанныеFace " + cursorСамиДанныеGroupBy);
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return   cursorНомерЦФО;
    }



    private void МетодСлушательКурсора() {
        // TODO: 15.10.2022  слушатиель для курсора
        try {
            if (cursorНомерЦФО !=null) {
                cursorНомерЦФО.registerDataSetObserver(new DataSetObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                    }

                    @Override
                    public void onInvalidated() {
                        super.onInvalidated();
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                    }
                });
                // TODO: 15.10.2022
                cursorНомерЦФО.registerContentObserver(new ContentObserver(  message.getTarget()) {
                    @Override
                    public boolean deliverSelfNotifications() {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        return super.deliverSelfNotifications();
                    }

                    @Override
                    public void onChange(boolean selfChange) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        super.onChange(selfChange);
                    }

                    @Override
                    public void onChange(boolean selfChange, @Nullable Uri uri) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        super.onChange(selfChange, uri);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 28.02.2022 начало  MyViewHolderДляЧата
    protected class MyViewHolder extends RecyclerView.ViewHolder {
        private TableLayout tableLayoutМатериалРодительная;
        private MaterialCardView cardViewМатериалРодительная;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                МетодИнициализацииКомпонетовЗаданияCardView(itemView);
                Log.d(this.getClass().getName(), "   itemView   " + itemView);
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
        private void МетодИнициализацииКомпонетовЗаданияCardView(@NonNull View itemView) {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);
                tableLayoutМатериалРодительная = itemView.findViewById(R.id.TableLayoutAdmissionLayoutInflater);
                cardViewМатериалРодительная = itemView.findViewById(R.id.CardviewassibAmaterial);
                Log.d(this.getClass().getName(), " cardViewМатериал   " + cardViewМатериалРодительная);
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
    }



    class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private Cursor cursor;
        public MyRecycleViewAdapter(@NotNull Cursor cursorMaterials) {
            this.cursor = cursorMaterials;
            if ( cursor!=null) {
                if (cursor.getCount() > 0 ) {
                    Log.i(this.getClass().getName(), " cursorMaterials  " + cursor.getCount());
                }
            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
            try {
                ///todo ЩЕЛКАЕМ КАЖДУЮ СТРОЧКУ ОТДЕЛЬНО
                    if (cursor!=null && cursor.getCount() > 0  ) {
                        cursor.moveToPosition(position);
                        // TODO: 29.06.2023
                        ТекущаяЦифраЦФО =        МетодВытаскиваемЦифраТекущийЦФО(cursor);

                        ТекущаяИмяЦФО =             МетодВытаскиваемНазваниеТекущийЦФО(cursor);
                        Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position +
                                " cursor " + cursor  + " ТекущаяЦифраЦФО " +ТекущаяЦифраЦФО + " ТекущаяИмяЦФО " +ТекущаяИмяЦФО);
                    }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
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
                Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View viewПолучениеМатериалов = null;
            try {
                if( asyncTaskLoader ==null ||  !asyncTaskLoader.isAbandoned()){
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_load_actimavmaretialov, parent, false);//todo old simple_for_takst_cardview1
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов);
                }else {
                    if (cursor.getCount() > 0) {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_assionamaterial, parent, false);//todo old  simple_for_assionamaterial
                        Log.i(this.getClass().getName(), "   viewПолучениеМатериалов" + viewПолучениеМатериалов+ "  sqLiteCursor.getCount()  "
                                + cursor.getCount());
                    } else {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_isnull_dm_materials, parent, false);//todo old simple_for_takst_cardview1
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов+
                                "  cursor.getCount()  " + cursor.getCount() );
                    }

                    progressBarСканирование.setVisibility(View.INVISIBLE);
                    Log.d(this.getClass().getName(),"\n" + " class " +
                            Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов  );
                }
                // TODO: 13.10.2022  добавляем новый компонент в Нащ RecycreView
                myViewHolder = new MyViewHolder(viewПолучениеМатериалов);
                // TODO: 08.08.2023
                МетодПерегрузкаRecyceView();
                Log.i(this.getClass().getName(), "   myViewHolder" + myViewHolder + "  binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return myViewHolder;
        }

        private Integer МетодВытаскиваемЦифраТекущийЦФО(@NonNull Cursor cursor) {
            try{
                Integer индексТекущаяЦФО =cursor.getColumnIndex("cfo");
                ТекущаяЦифраЦФО =cursor.getInt(индексТекущаяЦФО);
                if(ТекущаяЦифраЦФО==null){
                    ТекущаяЦифраЦФО=0;
                }
                Log.i(this.getClass().getName(),  "  ТекущаяЦифраЦФО " + ТекущаяЦифраЦФО  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return ТекущаяЦифраЦФО;
        }

        private String МетодВытаскиваемНазваниеТекущийЦФО(@NonNull Cursor cursor) {
            try{
                // TODO: 19.10.2022 название ЦФО
                Integer индексТекущаяНазваниеЦФО =cursor.getColumnIndex("name_cfo");
                ТекущаяИмяЦФО =cursor.getString(индексТекущаяНазваниеЦФО);
                if(ТекущаяИмяЦФО==null){
                    ТекущаяИмяЦФО="цфо закрыто !!! ";
                }
                Log.i(this.getClass().getName(),    " ТекущаяИмяЦФО " +ТекущаяИмяЦФО);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return ТекущаяИмяЦФО;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            try {
                  if(cursor!=null &&        ТекущаяЦифраЦФО >0 && ТекущаяИмяЦФО!=null)  {
                        МетодЗаполняемДаннымиПолучениеМАтериалов(holder, cursor);
                        Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " cursor " + cursor.getCount());
                    }


                // TODO: 17.04.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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

        ///todo первый метод #1
        private void МетодЗаполняемДаннымиПолучениеМАтериалов(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
            try {
                    // TODO: 18.10.2022 заполеняем данныими
                if (holder.tableLayoutМатериалРодительная.getChildCount()==0) {
                    МетодДобавленеиЕлементоввRecycreView(holder.tableLayoutМатериалРодительная,holder);

                }

                МетодПерегрузкаRecyceView();
                // TODO: 17.04.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                        + "\n" + " holder.tableLayoutМатериалРодительная " +holder.tableLayoutМатериалРодительная);
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


        private void МетодДобавленеиЕлементоввRecycreView(@NonNull TableLayout tableLayoutРодительская,@NonNull MyViewHolder holder) {
            try {
                // TODO: 07.11.2022   ВТОРОЙ ЭТАП ПОЛУЧАЕМ НОМЕР ЦФО
                    методGetCFOCursorFirst("ПолучениеНомерМатериала", ТекущаяЦифраЦФО);
                // TODO: 18.10.2022 название ЦФО
                МетодДанныеНазваниеЦФО(tableLayoutРодительская);
                // TODO: 18.10.2022 дял линии
                МетодДанныеЛиния(tableLayoutРодительская);
                // TODO: 18.10.2022 Добавяем Названием Столбиков
                МетодДанныеНазваниеСтолбиков(tableLayoutРодительская);
                // TODO: 07.11.2022 сами данные
                if (cursorНомерМатериала.getCount()>0) {
                    do{
                        // TODO: 07.11.2022  ТРЕТИЙ ЭТАП ПОЛУЧАЕМ  НОМЕР ДОКУМЕНТА
                         Integer ИндексМатериала=  cursorНомерМатериала.getColumnIndex("nomenvesov_zifra");
                        ТекущаяНомерМатериала=      cursorНомерМатериала.getInt(ИндексМатериала);

                        методGetCFOCursorFirst("ПолучениеСгрупированныеСамиДанные", ТекущаяЦифраЦФО);


                        МетодДанныеПолучениеМатериалов(tableLayoutРодительская,cursorСамиДанныеGroupBy);

                        // TODO: 17.04.2023
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                "  ТекущаяЦифраЦФО " + ТекущаяЦифраЦФО + " cursorНомерЦФО " + cursorНомерЦФО
                                + " ТекущаяЦифраЦФО " + ТекущаяЦифраЦФО + " ТекущаяНомерМатериала " +ТекущаяНомерМатериала);

                    }while (cursorНомерМатериала.moveToNext());
                }
                cursorНомерМатериала.close();
                // TODO: 17.04.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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

        private void МетодДанныеПолучениеМатериалов(@NonNull TableLayout tableLayoutРодительская , @NonNull Cursor cursorСамиДанныеGroupBy) {
            // TODO: 18.10.2022  ДАННЫЕ
            try {
                TableLayout tableLayoutДочернная=new TableLayout(getContext());
                            МетодВнутриСпинера(tableLayoutДочернная,tableLayoutРодительская,cursorСамиДанныеGroupBy);
                // TODO: 06.11.2022 данные цикл
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
        @SuppressLint("Range")
        private void МетодВнутриСпинера(@NonNull TableLayout tableLayout ,@NonNull TableLayout tableLayoutРодительская, @NonNull Cursor cursorСамиДанныеGroupBy) {
            try{
                Log.d(this.getClass().getName(), "  выходим из задания МетодКпопкаВозвращениеНазадИзСогласованиии" + " cursorСамиДанныеGroupBy " +cursorСамиДанныеGroupBy);
                tableLayout= (TableLayout) LayoutInflater.from(getContext()).inflate(R.layout.simple_for_assionamaterial_row,null);//todo old  simple_for_assionamaterial
               // tableLayout.setAnimation(animation);
                TableRow rowПервыеДанные = (TableRow)   tableLayout.findViewById(R.id.TableData);
                TextView textView=  rowПервыеДанные.findViewById(R.id.textview1);
                String Материал= Optional.ofNullable(cursorСамиДанныеGroupBy.getString(cursorСамиДанныеGroupBy.getColumnIndex("typematerial"))).orElse("");
                textView.setText(Материал.trim());
                TextView textView2=  rowПервыеДанные.findViewById(R.id.textview2);
                String Весовая= Optional.ofNullable(cursorСамиДанныеGroupBy.getString(cursorСамиДанныеGroupBy.getColumnIndex("nomenvesov"))).orElse("");
                textView2.setText(Весовая.trim());
                Integer Количество= Optional.ofNullable(cursorСамиДанныеGroupBy.getInt(cursorСамиДанныеGroupBy.getColumnIndex("moneys"))).orElse(0);
                TextView textView3=  rowПервыеДанные.findViewById(R.id.textview3);
                textView3.setText(Количество.toString());
                // TODO: 08.11.2022  заполеним данными Строчку ДляДальнейшего Использование
                Bundle data=new Bundle();
                data.putString("Материал",Материал);
                Integer Цфо= Optional.ofNullable(cursorСамиДанныеGroupBy.getInt(cursorСамиДанныеGroupBy.getColumnIndex("cfo"))).orElse(0);
                data.putInt("Цфо",Цфо);
               Integer НомерВыбраногоМатериала=
                        Optional.ofNullable(cursorСамиДанныеGroupBy.getInt(cursorСамиДанныеGroupBy.getColumnIndex("nomenvesov_zifra"))).orElse(0);
                data.putInt("НомерВыбраногоМатериала",НомерВыбраногоМатериала);
                String ВыбранныйМатериал=
                        Optional.ofNullable(cursorСамиДанныеGroupBy.getString(cursorСамиДанныеGroupBy.getColumnIndex("nomenvesov"))).orElse("");
                data.putString("ВыбранныйМатериал",ВыбранныйМатериал);
                data.putInt("Количество",Количество);
                rowПервыеДанные.setTag(data);

                // TODO: 06.11.2022 удаление
                tableLayout.recomputeViewAttributes(rowПервыеДанные);
                tableLayout.removeViewInLayout(rowПервыеДанные);
                tableLayout.removeView(rowПервыеДанные);
                rowПервыеДанные.setId(new Random().nextInt());
                tableLayout.recomputeViewAttributes(rowПервыеДанные);
                rowПервыеДанные.startAnimation(animation1);
                // TODO: 18.10.2022 добавляем  сами данные
                МетодДобаленияНовыхСтрокДанных(rowПервыеДанные, tableLayoutРодительская);
                // TODO: 19.10.2022
                // TODO: 08.11.2022 метод клика по строке Row
                Log.d(this.getClass().getName(), "МетодаКликаПоtableRow cursorСамиДанныеGroupBy  "+cursorСамиДанныеGroupBy );
                МетодаКликаПоtableRow(rowПервыеДанные);
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

        // TODO: 08.11.2022 метод перехода на дитализацию
        private void МетодаКликаПоtableRow(TableRow rowПервыеДанные) {
            try{
            rowПервыеДанные.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PesssionCameta pesssionCameta = new PesssionCameta(getActivity());
                    Boolean EnableCamera = pesssionCameta.checkPesssionCameta();
                    Boolean EnableFile = pesssionCameta.checkPesssionFile();


                    if (   EnableCamera && EnableFile) {

                    v.animate().rotationX(-40l);
                    message.getTarget() .postDelayed(()->{
                        v.animate().rotationX(0);


                        Bundle bundleПереходДетализацию=(Bundle) v.getTag();
                        Log.d(this.getClass().getName(), "МетодаКликаПоtableRow v  " + v+ " bundleПереходДетализацию "+bundleПереходДетализацию);
                        if (bundleПереходДетализацию != null) {

                            // TODO: 09.11.2022  переходим на детализацию Полученихы Материалов
                            fragmentTransaction = fragmentManager.beginTransaction();
                          //  fragmentTransaction.setCustomAnimations( android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                            Fragment              fragmentAdmissionMaterialsDetailing = new FragmentDetailingMaterials();
                            bundleПереходДетализацию.putBinder("binder",binderДляПолучениеМатериалов);
                            fragmentAdmissionMaterialsDetailing.setArguments(bundleПереходДетализацию);
                            fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragmentAdmissionMaterialsDetailing);//.layout.activity_for_fragemtb_history_tasks
                            fragmentTransaction.commit();
                            fragmentTransaction.show(fragmentAdmissionMaterialsDetailing);
                            Log.d(this.getClass().getName(), " fragmentAdmissionMaterialsDetailing " + fragmentAdmissionMaterialsDetailing);
                            Log.d(this.getClass().getName(), "  v  " + v);

                        }
                    },150);

                    } else {
                        // TODO: 30.09.2024

                        Toast.makeText(getActivity(), "Нет разрешения на Камеру !!!", Toast.LENGTH_SHORT).show();
                        Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  binderДляПолучениеМатериалов  "
                                + binderДляПолучениеМатериалов );

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

        private void МетодДанныеНазваниеСтолбиков(@NonNull TableLayout tableLayoutРодительская) {
            try {
                TableLayout  tableLayoutНазваниеСтобликов= (TableLayout) LayoutInflater.from(getContext()).inflate(R.layout.simple_for_assionamaterial_row,null);
                TableRow rowПервыеНазваниеСтобликов = (TableRow)   tableLayoutНазваниеСтобликов.findViewById(R.id.TableHendlers);
                tableLayoutНазваниеСтобликов.recomputeViewAttributes(rowПервыеНазваниеСтобликов);
                tableLayoutНазваниеСтобликов.removeViewInLayout(rowПервыеНазваниеСтобликов);
                tableLayoutНазваниеСтобликов.removeView(rowПервыеНазваниеСтобликов);
                rowПервыеНазваниеСтобликов.setId(new Random().nextInt());
                // TODO: 18.10.2022 добавляем  Линию
                МетодДобаленияНовыхСтрокДанных(rowПервыеНазваниеСтобликов, tableLayoutРодительская);
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

        private void МетодДанныеЛиния(@NonNull TableLayout tableLayoutРодительская) {
            try {
                TableLayout  tableLayoutЛиния= (TableLayout) LayoutInflater.from(getContext())
                        .inflate(R.layout.simple_for_assionamaterial_row,null);
                TableRow rowПервыеЛиния = (TableRow)   tableLayoutЛиния.findViewById(R.id.TableRowLine);
                tableLayoutЛиния.recomputeViewAttributes(rowПервыеЛиния);
                tableLayoutЛиния.removeViewInLayout(rowПервыеЛиния);
                tableLayoutЛиния.removeView(rowПервыеЛиния);
                rowПервыеЛиния.setId(new Random().nextInt());
                // TODO: 18.10.2022 добавляем  Линию
                МетодДобаленияНовыхСтрокДанных(rowПервыеЛиния, tableLayoutРодительская);
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

        private void МетодДанныеНазваниеЦФО(@NonNull TableLayout tableLayoutРодительская) {
            try{
                TableLayout  tableLayoutНазваниеЦФО= (TableLayout) LayoutInflater.from(getContext())
                        .inflate(R.layout.simple_for_assionamaterial_row,null);//todo old  simple_for_assionamaterial
                TableRow rowПервыеНазваниеЦФО = (TableRow)   tableLayoutНазваниеЦФО.findViewById(R.id.TableRowNameCFO);
                TextView textViewНазваниеЦФО=  rowПервыеНазваниеЦФО.findViewById(R.id.textviewnameCFO);
                if(ТекущаяИмяЦФО!=null && ТекущаяИмяЦФО.length()>0){
                    textViewНазваниеЦФО.setText(ТекущаяИмяЦФО.trim() );
                }else{
                    textViewНазваниеЦФО.setText("Цфо закрыто !!!" );
                }
                tableLayoutНазваниеЦФО.recomputeViewAttributes(rowПервыеНазваниеЦФО);
                tableLayoutНазваниеЦФО.removeViewInLayout(rowПервыеНазваниеЦФО);
                tableLayoutНазваниеЦФО.removeView(rowПервыеНазваниеЦФО);
                rowПервыеНазваниеЦФО.setId(new Random().nextInt());
                // TODO: 18.10.2022 добавляем  название ЦФО
                МетодДобаленияНовыхСтрокДанных(rowПервыеНазваниеЦФО, tableLayoutРодительская);
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

        private void МетодДобаленияНовыхСтрокДанных(TableRow rowПервыеДанные, @NonNull TableLayout tableLayoutРодительская) {
            try {
                tableLayoutРодительская.removeView(rowПервыеДанные);
                tableLayoutРодительская.removeViewInLayout(rowПервыеДанные);
                tableLayoutРодительская.addView(rowПервыеДанные);
                tableLayoutРодительская.requestLayout();
                tableLayoutРодительская.refreshDrawableState();
                tableLayoutРодительская.forceLayout();
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
        public long getItemId(int position) {
            // TODO: 04.03.2022
            Log.i(this.getClass().getName(), "     getItemId holder.position " + position);
            return super.getItemId(position);
        }
        @Override
        public int getItemCount() {
            int КоличесвоСтрок =1;
            try {
                    if ( cursorНомерЦФО!=null  && cursorНомерЦФО.getCount() > 0) {
                        КоличесвоСтрок = cursorНомерЦФО.getCount();
                        Log.d(this.getClass().getName(), "cursorНомерЦФО.getCount()  " + cursorНомерЦФО.getCount());
                    }
                Log.d(this.getClass().getName(), "КоличесвоСтрок  " + КоличесвоСтрок);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return КоличесвоСтрок;
        }
    }




    private void metodBackPostionFiestCursors() {
        try{
            if (cursorНомерЦФО!=null) {
                if (!cursorНомерЦФО.isFirst()) {
                }
                    if (!cursorНомерЦФО.isClosed()) {
                        cursorНомерЦФО.moveToFirst();
                    }




                if (cursorНомерМатериала!=null) {
                    if (!cursorНомерМатериала.isClosed()) {
                            cursorНомерМатериала.moveToFirst();
                        }
                }


                if (cursorСамиДанныеGroupBy!=null) {
                    if (!cursorСамиДанныеGroupBy.isClosed()) {
                            cursorСамиДанныеGroupBy.moveToFirst();
                        }
                }
                // TODO: 17.04.2023 LOG
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                            + "\n" + " cursorНомерЦФО " +cursorНомерЦФО);
                }

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


}