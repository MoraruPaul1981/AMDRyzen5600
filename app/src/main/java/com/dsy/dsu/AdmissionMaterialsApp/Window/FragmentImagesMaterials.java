package com.dsy.dsu.AdmissionMaterialsApp.Window;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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

import com.dsy.dsu.AdmissionMaterialsApp.bl_admissonmaterils.PesssionCameta;
import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForAdminissionMaterial;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutionException;


// TODO: 29.09.2022 фрагмент для получение материалов
public class FragmentImagesMaterials extends Fragment {
    private Integer ПубличныйIDДляФрагмента;
    private RecyclerView recyclerView;
    private LinearLayout linearLayou;
    private Fragment fragment_ТекущийФрагментСогласованиеСписок;
    private BottomNavigationView bottomNavigationView;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemViewвыход;
    private BottomNavigationItemView bottomNavigationItemView2создать;
    private BottomNavigationItemView bottomNavigationItemView3обновить;
    private ProgressBar progressBarСканирование;
    private LayoutAnimationController layoutAnimationController;
    private Animation animation;
    private  Message message;
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private MyViewHolder myViewHolder;
    private  ServiceForAdminissionMaterial.LocalBinderДляПолучениеМатериалов binderДляПолучениеМатериалов;
    private  ViewGroup container;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    private AsyncTaskLoader<Cursor> asyncTaskLoaderДетализация;
    private    Bundle bundleForImages;
    long start;
    long startДляОбноразвовной;
    private Cursor cursorImageForSelectMaterail;

    // TODO: 27.09.2022 Фрагмент Получение Материалов
    public FragmentImagesMaterials() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            // TODO: 01.07.2023  ДАннеы
            МетодHandlerCallBack();
            bundleForImages=      getArguments();
            if (bundleForImages!=null) {
                binderДляПолучениеМатериалов=  (ServiceForAdminissionMaterial.LocalBinderДляПолучениеМатериалов) bundleForImages.getBinder("binder");
                // TODO: 10.11.2022
                if(binderДляПолучениеМатериалов!=null){
                    // TODO: 18.07.2023 получение Image для выбраного Материала
                    методGetCursorImageForRow(bundleForImages.getLong("UUIDВыбраныйМатериал"));
                }
            }
            start=     Calendar.getInstance().getTimeInMillis();
            startДляОбноразвовной=     Calendar.getInstance().getTimeInMillis();
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  binderДляПолучениеМатериалов  "
                    +binderДляПолучениеМатериалов  + " cursorImageForSelectMaterail "+ cursorImageForSelectMaterail);
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
            view= inflater.inflate(R.layout.fragment_admission_materials_images, container, false);
            this.container=container;
            Log.d(this.getClass().getName(), " onCreateView FragmentDetailingMaterials" + view);
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
    @SuppressLint({"WrongConstant", "RestrictedApi"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);
            recyclerView = view.findViewById(R.id.RecyclerView);
            fragmentManager = getActivity().getSupportFragmentManager();
            linearLayou = view.findViewById(R.id.fragmentadmissionmaterias);
            bottomNavigationView = view.findViewById(R.id.BottomNavigationView);
            bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationItemViewвыход = bottomNavigationView.findViewById(R.id.id_lback);
            bottomNavigationItemViewвыход.setIconSize(60);
            bottomNavigationItemView2создать = bottomNavigationView.findViewById(R.id.id_create);
            bottomNavigationItemView2создать.setIconSize(50);
            bottomNavigationItemView3обновить = bottomNavigationView.findViewById(R.id.id_async);
            bottomNavigationItemView3обновить.setIconSize(50);
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_tabellist);
            progressBarСканирование=  view.findViewById(R.id.ProgressBar);
            progressBarСканирование.setVisibility(View.VISIBLE);
            bottomNavigationItemView2создать.setVisibility(View.GONE);
            bottomNavigationItemView3обновить.setVisibility(View.GONE);
            //todo запуск методов в фрагменте
            МетодИнициализацииRecycreView();
            МетодЗаполенияRecycleViewДляЗадач();//todo заполения recycreview
            МетодВыходНаAppBack();
            МетодКпопкиЗначков(cursorImageForSelectMaterail);
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  recyclerView  "+recyclerView+
                    " linearLayou "+linearLayou+"  fragmentManager "+fragmentManager);
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
        try{// TODO: 03.11.2022  после получение данных перересует Экран
            if (asyncTaskLoaderДетализация !=null && asyncTaskLoaderДетализация.isAbandoned()  ) {
                методRebbotRecyreViews();
                МетодКпопкиЗначков(cursorImageForSelectMaterail);
                МетодСоздаенияСлушателяДляПолучениеМатериалаWorkMAnager();
                МетодСлушательКурсора();
                МетодСлушательRecycleView();//todo создаем слушатель для recycreview для получение материалов
                МетодПерегрузкаRecyceView();
            }
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  recyclerView  "+recyclerView+
                    " linearLayou "+linearLayou+"  cursorImageForSelectMaterail "+cursorImageForSelectMaterail);
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

    private void методRebbotRecyreViews() {
        try{
        recyclerView.removeAllViewsInLayout();
            if (    myRecycleViewAdapter.cursorImageForSelectMaterail==null) {
                myRecycleViewAdapter.cursorImageForSelectMaterail=cursorImageForSelectMaterail;
            } else {
                myRecycleViewAdapter.cursorImageForSelectMaterail.requery();
            }
        myRecycleViewAdapter.notifyDataSetChanged();
        RecyclerView.Adapter recyclerViewОбновление=         recyclerView.getAdapter();
        recyclerViewОбновление.notifyDataSetChanged();
        recyclerView.swapAdapter(recyclerViewОбновление,true);
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  recyclerView  "+recyclerView+
                    " linearLayou "+linearLayou+"  fragmentManager "+fragmentManager);
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
            // TODO: 18.07.2023
            metodBackPostionCursorFirst();
            методRebbotRecyreViews();
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

    private void metodBackPostionCursorFirst() {
        try{
            if (!cursorImageForSelectMaterail.isFirst()
                    && !cursorImageForSelectMaterail.isClosed()) {
                cursorImageForSelectMaterail.moveToFirst();
            }
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
    @NonNull
    private void методGetCursorImageForRow(Long UUIDДляУдаления) {
        message.getTarget().post(()->{
            asyncTaskLoaderДетализация=new AsyncTaskLoader(getActivity()) {
                @Nullable
                @Override
                public Object loadInBackground() {

                    try{
                        cursorImageForSelectMaterail =     МетодПолучениеДанныхФотографииImageДляМатериа (UUIDДляУдаления);
                        // TODO: 17.07.2023
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " UUIDДляУдаления " +UUIDДляУдаления +
                                " cursorImageForSelectMaterail " + cursorImageForSelectMaterail);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return cursorImageForSelectMaterail;
                }
            };
            asyncTaskLoaderДетализация.startLoading();
            asyncTaskLoaderДетализация.forceLoad();
            asyncTaskLoaderДетализация.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener<Cursor>() {
                @Override
                public void onLoadComplete(@NonNull Loader<Cursor> loader, @Nullable Cursor data) {
                    try{
                    if (data !=null) {
                        asyncTaskLoaderДетализация.abandon();
                        cursorImageForSelectMaterail=data;
                        onStart();
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " cursorImageForSelectMaterail " +cursorImageForSelectMaterail);
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
            bottomNavigationView.refreshDrawableState();
            bottomNavigationView.requestLayout();
            bottomNavigationView.forceLayout();
            recyclerView.refreshDrawableState();
            recyclerView.requestLayout();
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
            if(cursorImageForSelectMaterail !=null && cursorImageForSelectMaterail.getCount()>0){
                cursorImageForSelectMaterail.moveToFirst();
            }
            myRecycleViewAdapter = new MyRecycleViewAdapter(cursorImageForSelectMaterail);
            myRecycleViewAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(myRecycleViewAdapter);
            Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
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

            DividerItemDecoration dividerItemDecorationHor=
                    new DividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL);
            /*            dividerItemDecorationHor.setDrawable(getContext().getDrawable(R.drawable.divider_for_order_transport1));///R.dimen.activity_horizontal_margin*/
            recyclerView.addItemDecoration(dividerItemDecorationHor);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
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

                        PesssionCameta pesssionCameta = new PesssionCameta(getActivity());
                        Boolean EnableCamera = pesssionCameta.checkPesssionCameta();
                        Boolean EnableFile = pesssionCameta.checkPesssionFile();


                        if (   EnableCamera && EnableFile) {

                            // TODO: 30.09.2024

                            МетодЗапускаАнимацииКнопок(v);//todo только анимауия
                            Fragment fragmentBAcK = new FragmentDetailingMaterials();
                            fragmentTransaction = fragmentManager.beginTransaction();
                            bundleForImages.putBinder("binder", binderДляПолучениеМатериалов);
                            fragmentBAcK.setArguments(bundleForImages);
                            /*       fragmentTransaction.setCustomAnimations( android.R.anim.slide_in_left,android.R.anim.slide_out_right);*/
                            fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragmentBAcK).commit();//.layout.activity_for_fragemtb_history_tasks
                            fragmentTransaction.show(fragmentBAcK);
                            Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  binderДляПолучениеМатериалов  "
                                    + binderДляПолучениеМатериалов +
                                    " bundleForImages " + bundleForImages);


                        } else {
                            // TODO: 30.09.2024

                            Toast.makeText(getActivity(), "Нет разрешения на Камеру !!!", Toast.LENGTH_SHORT).show();
                            Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  binderДляПолучениеМатериалов  "
                                    + binderДляПолучениеМатериалов +
                                    " bundleForImages " + bundleForImages);

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
            bottomNavigationItemView2создать.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        v.animate().rotationX(-40l);
                        message.getTarget() .postDelayed(()->{
                            try {
                            v.animate().rotationX(0);
                                // TODO: 28.07.2023  Запускаем Новый Материал
                            МетодЗапускСозданиНовгоМатериалов();
                                Log.d(getContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        },300);
                 
                }
            });
            bottomNavigationItemView3обновить.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        progressBarСканирование.setVisibility(View.VISIBLE);
                        МетодЗапускаАнимацииКнопок(v);
                        message.getTarget().postDelayed(()->{
                                    Integer ПубличныйIDДляФрагмента =     new GetttingPublicID().getttingPublicID(getContext());

                                    // TODO: 16.11.2022  запуск синхронизации однорозовая
                                    },
                                500);
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
    // TODO: 02.08.2022

    protected void МетодЗапускСозданиНовгоМатериалов() {
        try{
            fragmentTransaction = fragmentManager.beginTransaction();
            Fragment     fragment_СозданиеНовогоМатериалов = new FragmentMaretialNew();
            Bundle data=new Bundle();
            data.putBinder("binder",binderДляПолучениеМатериалов);
             fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left);
            fragment_СозданиеНовогоМатериалов.setArguments(data);
            fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragment_СозданиеНовогоМатериалов).commit();//.layout.activity_for_fragemtb_history_task
            fragmentTransaction.show(fragment_СозданиеНовогоМатериалов);
            Log.d(this.getClass().getName(), " fragment_СозданиеНовогоМатериалов " + fragment_СозданиеНовогоМатериалов);
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

    private void МетодЗапускаАнимацииКнопок(View v) {
        v.animate().rotationX(-40l);
        message.getTarget() .postDelayed(()->{
            v.animate().rotationX(0);
        },300);
    }
    private void МетодКпопкиЗначков(@NonNull Cursor cursor)
            throws ExecutionException, InterruptedException {
        try {
            if (cursor!=null && cursor.getCount()> 0) {
                    Log.d(this.getClass().getName(), "  cursor" + cursor.getCount());
                   bottomNavigationView.getOrCreateBadge(R.id.id_lback).setNumber( cursor.getCount());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    bottomNavigationView.getOrCreateBadge(R.id.id_lback).setBackgroundColor(Color.parseColor("#15958A"));
                } else {
                    bottomNavigationView.getOrCreateBadge(R.id.id_lback).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    bottomNavigationView.getOrCreateBadge(R.id.id_lback).setBackgroundColor(Color.RED);
                }
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

    void МетодHandlerCallBack() {
        message=Message.obtain(new Handler(Looper.myLooper()),()->{
            try{
                Bundle bundle=   message.getData();
                Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                        Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " время " +new Date().toLocaleString() + " binderДляПолучениеМатериалов ");
                Log.i(this.getClass().getName(), "bundle " +bundle);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        });
    }
    // TODO: 10.03.2022 БИЗНЕС-КОД для ФРАГМЕНТА ПОСТУПЛЕНИЯ МАТЕРИАЛА

    void МетодСлушательRecycleView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
        try {
            if (myRecycleViewAdapter!=null) {
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
                recyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
                    @Override
                    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
                        Log.d(this.getClass().getName(), "     holder "+holder);
                        //   ContentProviderOperation.Builder builder = null;
                    }
                });
                // TODO: 15.10.2022  дополнительные слушатели

                recyclerView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        return null;
                    }
                });
                // TODO: 17.10.2022 метод внешний вид нижних строчек
                recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                    @Override
                    public void onChildViewAttachedToWindow(@NonNull View view) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        try {

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
                    public void onChildViewDetachedFromWindow(@NonNull View view) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        try {
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
    void МетодСоздаенияСлушателяДляПолучениеМатериалаWorkMAnager() throws ExecutionException, InterruptedException {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
        try {
            LifecycleOwner lifecycleOwner =this ;;
            lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
                @Override
                public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                    source.getLifecycle().getCurrentState();
                    event.getTargetState().name();
                }
            });
            String ИмяСлужбыСинхронизациОдноразовая="WorkManager Synchronizasiy_Data Disposable";
            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).observe(lifecycleOwner, new Observer<List<WorkInfo>>() {
                @Override
                public void onChanged(List<WorkInfo> workInfos) {
                    workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                            // TODO: 03.08.2022 методы работы work manager
                        if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0)         {
                            Long CallBaskОтWorkManagerОдноразового =
                                    СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая",
                                            0l);
                            if (CallBaskОтWorkManagerОдноразового>0) {
                                long end = Calendar.getInstance().getTimeInMillis();
                                long РазницаВоврмени=end-startДляОбноразвовной;
                                if (РазницаВоврмени>2000) {
                                    onStart();
                                    onResume();
                                }
                                // TODO: 21.11.2022  запускаем удаление
                            }

                        }
                        if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0
                        || СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.FAILED) == 0) {
                            МетодДизайнПрограссБара();
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

    // TODO: 02.08.2022
    protected   Cursor МетодПолучениеДанныхДЛяПолучениеМатериалов(@NonNull String  ФлагКакиеДанныеНужныПолучениеМатериалов
            ,@NonNull Integer ТекущаяЦФО
    , @NonNull Integer ТекущаяНомерМатериала ){
        Cursor cursorДетализацияМатериала = null;
        try{
            ПубличныйIDДляФрагмента     =    new GetttingPublicID().getttingPublicID(getContext());

            Log.d(getContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=new Bundle();
            switch (ФлагКакиеДанныеНужныПолучениеМатериалов){
                case "ПолучениеНомерМатериалаДетализация":
                    bundleДляПЕредачи.putString("Таблица","view_taterials");//TODO сами данные
                    break;
            }
            bundleДляПЕредачи.putInt("ПубличныйIDДляФрагмента",ПубличныйIDДляФрагмента);
            bundleДляПЕредачи.putInt("ТекущаяЦФО",ТекущаяЦФО);
            bundleДляПЕредачи.putInt("ТекущаяНомерМатериала",ТекущаяНомерМатериала);
            bundleДляПЕредачи.putString("ФлагКакиеДанныеНужныПолучениеМатериалов",ФлагКакиеДанныеНужныПолучениеМатериалов);
            Intent intentПолучениеМатериалов = new Intent(getContext(), ServiceForAdminissionMaterial.class);
            intentПолучениеМатериалов.setAction(ФлагКакиеДанныеНужныПолучениеМатериалов);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            Log.d(this.getClass().getName(), "   ПубличныйIDДляФрагмента "+ ПубличныйIDДляФрагмента);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            //TODO получение данных от Службы ДЛя Получение Материалов
                    cursorДетализацияМатериала = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursorНомерМатериала " + cursorДетализацияМатериала);
                    if (cursorДетализацияМатериала.getCount() > 0) {
                        cursorДетализацияМатериала.moveToFirst();
                        Log.d(this.getClass().getName(), "   cursorНомерМатериала " + cursorДетализацияМатериала);
                    }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  cursorДетализацияМатериала;
    }

    // TODO: 02.08.2022
    protected   Cursor МетодПолучениеДанныхФотографииImageДляМатериа(@NonNull Long  Paren_Image_UUID){
        Cursor cursorGetIamges= null;
        try{
            Bundle bundleДляПЕредачи=new Bundle();
            bundleДляПЕредачи.putString("Таблица","materials_databinary");//TODO сами данные
            bundleДляПЕредачи.putLong("Paren_Image_UUID",Paren_Image_UUID);
            Intent intentПолучениеМатериалов = new Intent(getContext(), ServiceForAdminissionMaterial.class);
            intentПолучениеМатериалов.setAction("GetImageFormaterial");
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            Log.d(this.getClass().getName(), "   ПубличныйIDДляФрагмента "+ ПубличныйIDДляФрагмента);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            //TODO получение данных от Службы ДЛя Получение Материалов
            if (Paren_Image_UUID!=null) {
                cursorGetIamges = (Cursor) binderДляПолучениеМатериалов.getService()
                        .new SubClassGetDataAdmissionMaterial_Данные_ДляНовогоПоиска()
                        .МетодПолучениеДанныхForImage(getContext(), intentПолучениеМатериалов);
            }
            Log.d(this.getClass().getName(), "   cursorImageForSelectMaterail " + cursorGetIamges  + " Paren_Image_UUID " +Paren_Image_UUID);
            if (cursorGetIamges.getCount() > 0) {
                cursorGetIamges.moveToFirst();
                Log.d(this.getClass().getName(), "   cursorImageForSelectMaterail " + cursorGetIamges);
            }
            // TODO: 18.04.2023  Simple Adapter Кдик по Элементы
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " cursorImageForSelectMaterail " +cursorGetIamges);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  cursorGetIamges;
    }
    private void МетодДизайнПрограссБара() {
        progressBarСканирование.postDelayed(()->{
            progressBarСканирование.setVisibility(View.INVISIBLE);
            progressBarСканирование.setIndeterminate(true);
        },1000);
    }

    private void МетодСлушательКурсора() {
        // TODO: 15.10.2022  слушатиель для курсора
        try {
            if (cursorImageForSelectMaterail !=null) {
                cursorImageForSelectMaterail.registerDataSetObserver(new DataSetObserver() {
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
                cursorImageForSelectMaterail.registerContentObserver(new ContentObserver(message.getTarget()) {
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
        private TableLayout tableLayout_material_image;
        private MaterialCardView materialcardView_for_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                if(cursorImageForSelectMaterail !=null  && cursorImageForSelectMaterail.getCount()>0 ) {
                    МетодИнициализацииКомпонетовЗаданияCardView(itemView);
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"   itemView   " + itemView);
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
                tableLayout_material_image = itemView.findViewById(R.id.tableLayout_material_image);
                // TODO: 29.06.2023
                materialcardView_for_image = itemView.findViewById(R.id.materialcardView_for_image);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  +
                         " tableLayout_material_image " +tableLayout_material_image
                        + " materialcardView_for_image " +materialcardView_for_image );

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
        private Cursor cursorImageForSelectMaterail;
        public MyRecycleViewAdapter(@NotNull Cursor cursorImageForSelectMaterail) {
            this.cursorImageForSelectMaterail = cursorImageForSelectMaterail;

        }
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            try {
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position +
                        " cursorImageForSelectMaterail "+ cursorImageForSelectMaterail);
                if(cursorImageForSelectMaterail !=null  && cursorImageForSelectMaterail.getCount()>0 ){
                    // TODO: 18.07.2023 POSITION
                    cursorImageForSelectMaterail.moveToPosition(position);
                    // TODO: 18.07.2023  Данные Заполенияем новые  Image
                    МетодЗаполняемДаннымиПолучениеМАтериалов(holder, cursorImageForSelectMaterail);
                }
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position +
                        " cursorImageForSelectMaterail "+ cursorImageForSelectMaterail  + "  position " +position);
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
                if(asyncTaskLoaderДетализация ==null || !asyncTaskLoaderДетализация.isAbandoned() ){
                    viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_load_actimavmaretial_image, parent, false);//todo old simple_for_takst_cardview1
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов);
               /*     // TODO: 18.07.2023 получение Image для выбраного Материала
                    методGetImageForRow(bundleForImages.getLong("UUIDВыбраныйМатериал"));*/

                }else {
                    if (cursorImageForSelectMaterail.getCount() > 0 ) {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_assionamaterial_for_images, parent, false);//todo old  simple_for_assionamaterial
                        Log.i(this.getClass().getName(), "   viewПолучениеМатериалов" + viewПолучениеМатериалов+ "  cursorImageForSelectMaterail.getCount()  " + cursorImageForSelectMaterail.getCount());
                    } else {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_isnull_actimavmaretial_image, parent, false);//todo old simple_for_takst_cardview1
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов+ "  cursorImageForSelectMaterail.getCount()  "
                                + cursorImageForSelectMaterail.getCount() );
                    }

                    МетодДизайнПрограссБара();
                }
                // TODO: 13.10.2022  добавляем новый компонент в Нащ RecycreView
                myViewHolder = new MyViewHolder(viewПолучениеМатериалов);
                // TODO: 17.07.2023
                МетодПерегрузкаRecyceView();

                // TODO: 18.04.2023  Simple Adapter Кдик по Элементы
                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " myViewHolder " +myViewHolder +
                        " cursorImageForSelectMaterail " + cursorImageForSelectMaterail);
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


        ///todo первый метод #1
        private void МетодЗаполняемДаннымиПолучениеМАтериалов(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
            try {
                    // TODO: 18.10.2022 заполеняем данныими
                if (holder.tableLayout_material_image.getChildCount()==0) {
                    МетодДобавленеиЕлементоввRecycreView(holder.tableLayout_material_image);
                }


                МетодПерегрузкаRecyceView();

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


        private void МетодДобавленеиЕлементоввRecycreView(@NonNull TableLayout tableLayoutРодительскаяImage) {
            try {
                // TODO: 18.10.2022 Image Binary
                if (tableLayoutРодительскаяImage!=null) {
                    // TODO: 18.07.2023  заполение  Image для Выбраного Материала
                    методЗаполениеImages(tableLayoutРодительскаяImage);
                }
                // TODO: 17.04.2023
                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorImageForSelectMaterail " +cursorImageForSelectMaterail);
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


        private void МетодаКликаGetImageМатериалаПоtableRow(TableRow rowПервыеДанные) {
            try{
                rowПервыеДанные.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBarСканирование.setVisibility(View.VISIBLE);
                        v.animate().rotationX(-60l);
                        message.getTarget() .postDelayed(()->{
                            v.animate().rotationX(0);
                            // TODO: 01.07.2023 удаление
                            Bundle bundleПереходУдалениеМатериала=(Bundle) v.getTag();
                            Log.d(this.getClass().getName(), "МетодаКликаУдаленияМатериалаПоtableRow v  " + v+ " bundleПереходУдалениеМатериала "
                                    +bundleПереходУдалениеМатериала);

                            progressBarСканирование.setVisibility(View.INVISIBLE);
                        },250);
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


        private void методЗаполениеImages(@NonNull TableLayout tableLayoutРодительскаяImage) {
            try {
                TableRow RowName_ForImage = методGetImageTableRow();


                // TODO: 29.06.2023 Заполянем ДАту ФОтографии
                методЗаполенияДатыImage(RowName_ForImage);

                // TODO: 18.07.2023 Заполения сомой Image
               ImageView imageViewImage= методЗаполенияItemImage(RowName_ForImage);


                // TODO: 18.07.2023 click Image
                методClickItemImage(RowName_ForImage);

                // TODO: 17.04.2023 Заполением Данными
                МетодДобаленияНовыхСтрокДанных(RowName_ForImage, tableLayoutРодительскаяImage);





                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " RowName_ForImage " +RowName_ForImage);
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
        private ImageView методЗаполенияItemImage(@NonNull  TableRow RowName_ForImage) {
            ImageView imageViewImage = null;
            try{
                imageViewImage=  RowName_ForImage.findViewById(R.id.image_binary);
                Long UUIDТекущаяФотографии=      cursorImageForSelectMaterail.getLong(cursorImageForSelectMaterail.getColumnIndex("uuid"));
                String patchFileName="SousAvtoFile/AppMaterial/Photos";
                String NameNewPhotosCamerax=UUIDТекущаяФотографии.toString()+".jpg";
                File fileNewPhotoFromCameraX = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        +File.separator+patchFileName + File.separator+NameNewPhotosCamerax);

                File fileNewPhotoDirectory= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        +File.separator+patchFileName  );

                // TODO: 27.09.2023  BUFFER
                ByteArrayOutputStream bytedataNewImage;

                if (fileNewPhotoDirectory.isDirectory() && fileNewPhotoFromCameraX.exists()) {
                    Uri address=     FileProvider.getUriForFile(getContext(),"com.dsy.dsu.provider" ,fileNewPhotoFromCameraX );
                    final InputStream imageStream = getContext().getContentResolver().openInputStream(address);

                    Bitmap     bitmapselectPhoto = BitmapFactory.decodeStream(imageStream);


                    if (bitmapselectPhoto!=null) {
                        bytedataNewImage = new ByteArrayOutputStream(2048);////4096
                        bitmapselectPhoto.compress(Bitmap.CompressFormat.JPEG, 80, bytedataNewImage);
                        //   bitmapselectPhoto.compress(Bitmap.CompressFormat.JPEG, 100, bytedataNewImage);
                        byte[]   ByteDecodePhotoCompress = bytedataNewImage.toByteArray();
                        Bitmap   bitmapImage= BitmapFactory.decodeByteArray(ByteDecodePhotoCompress, 0, ByteDecodePhotoCompress.length);
                        bitmapImage = Bitmap.createScaledBitmap(bitmapImage,   700, 1280, false);//scale the bitmap
                        // TODO: 11.09.2023
                        if (bitmapImage!=null) {
                            imageViewImage.setImageBitmap(bitmapImage);
                        }
                        // TODO: 14.09.2023  clear exit

                        bytedataNewImage.flush();
                        bytedataNewImage.close();
                        imageStream.close();
                    }

                }else {
                    byte[] byteImageBlob=      cursorImageForSelectMaterail.getBlob(cursorImageForSelectMaterail.getColumnIndex("image"));
                    // TODO: 11.09.2023
                    if (byteImageBlob!=null) {
                        Bitmap   bitmapImageBlob= BitmapFactory.decodeByteArray(byteImageBlob, 0, byteImageBlob.length);
                        bytedataNewImage = new ByteArrayOutputStream(2048);////4096
                        bitmapImageBlob.compress(Bitmap.CompressFormat.JPEG, 100, bytedataNewImage);
                        bitmapImageBlob = Bitmap.createScaledBitmap(bitmapImageBlob, 700, 1280, false);//scale the bitmap
                        if (bitmapImageBlob!=null) {
                            // TODO: 11.09.2023
                            imageViewImage.setImageBitmap(bitmapImageBlob);
                        }
                        // TODO: 14.09.2023 clear exit
                        bytedataNewImage.flush();
                        bytedataNewImage.close();
                    }
                }
                // TODO: 08.08.2023 ДАтаФОто
                String ДатаImage=     cursorImageForSelectMaterail.getString(cursorImageForSelectMaterail.getColumnIndex("uuid"));
                imageViewImage.setTooltipText("фото :"+ДатаImage);
            // TODO: 17.07.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " RowName_ForImage " +RowName_ForImage + "UUIDТекущаяФотографии " +UUIDТекущаяФотографии);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
            return  imageViewImage;
        }
        private void методClickItemImage(@NonNull       TableRow RowName_ForImage) {
            try{
                RowName_ForImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.animate().rotationX(+1l);
                    message.getTarget() .postDelayed(()-> {
                        try{
                                v.animate().rotationX(0);

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
                            },250);

                }
            });
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






        @SuppressLint("Range")
        private void методЗаполенияДатыImage(@NonNull  TableRow RowName_ForImage) {
            try{
            TextView textViewNameImage=  RowName_ForImage.findViewById(R.id.textview_imagename);
          //  String ДатаImage=     cursorImageForSelectMaterail.getString(cursorImageForSelectMaterail.getColumnIndex("date_update"));
         String ДатаImage=     cursorImageForSelectMaterail.getString(cursorImageForSelectMaterail.getColumnIndex("date_update"));
            textViewNameImage.setText(ДатаImage.trim());
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " RowName_ForImage " +RowName_ForImage);
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


        private void МетодДобаленияНовыхСтрокДанных(@NonNull TableRow rowПервыеДанные, @NonNull TableLayout tableLayoutРодительская) {
            try {
                if (tableLayoutРодительская!=null) {
                    tableLayoutРодительская.removeView(rowПервыеДанные);
                    tableLayoutРодительская.removeViewInLayout(rowПервыеДанные);
                    tableLayoutРодительская.addView(rowПервыеДанные);
                    tableLayoutРодительская.requestLayout();
                    tableLayoutРодительская.refreshDrawableState();
                    tableLayoutРодительская.forceLayout();
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

        @Override
        public long getItemId(int position) {
            // TODO: 04.03.2022
            Log.i(this.getClass().getName(), "     getItemId holder.position " + position);
            return super.getItemId(position);
        }
        @Override
        public int getItemCount() {
            Integer getCountRow=0;
            try {
                if (cursorImageForSelectMaterail!=null && cursorImageForSelectMaterail.getCount()>0) {
                    getCountRow = cursorImageForSelectMaterail.getCount();
                }else {
                    getCountRow=1  ;
                }
                Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  getCountRow  "+getCountRow);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return getCountRow;
        }
    }

    private void методSetRowBungle(@NonNull Cursor cursorДетализацияМатериала, TableRow RowData_for_detalisaziy, String типДеталиазции, String nomenvesovДетадизации, Integer КоличествоДетадизации) {
        try {
        // TODO: 29.06.2023 Данные ДЛя Текущего Row Bungle
        Long UUIDВыбраныйМатериал= Optional.ofNullable(cursorДетализацияМатериала.getLong(cursorДетализацияМатериала.
                getColumnIndex("uuid"))).orElse(0l);
        // TODO: 10.11.2022
        Bundle bundleДанныеДляСтрочки=new Bundle();
        bundleДанныеДляСтрочки.putLong("UUIDВыбраныйМатериал",UUIDВыбраныйМатериал);
        bundleДанныеДляСтрочки.putString("Материал", nomenvesovДетадизации);
        bundleДанныеДляСтрочки.putString("Тип", типДеталиазции);
        bundleДанныеДляСтрочки.putInt("Количество", КоличествоДетадизации);
        RowData_for_detalisaziy.setTag(bundleДанныеДляСтрочки);
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


    // TODO: 29.06.2023  метод получение GET Row
    @NonNull
    private TableRow методGetDataTableRow() {
        TableRow RowData_for_detalisaziy = null;
        try{
        TableLayout  tableLayoutДеталицация= (TableLayout) LayoutInflater.from(getContext())
                .inflate(R.layout.simple_for_assionamaterial_detelizaziy_row,null);//todo old  simple_for_assionamaterial
          RowData_for_detalisaziy = (TableRow)   tableLayoutДеталицация.findViewById(R.id.tableData_for_detalisaziy);
        // TODO: 29.06.2023  удаление данных из PARENT
        tableLayoutДеталицация.recomputeViewAttributes(RowData_for_detalisaziy);
        tableLayoutДеталицация.removeViewInLayout(RowData_for_detalisaziy);
        tableLayoutДеталицация.removeView(RowData_for_detalisaziy);
        RowData_for_detalisaziy.setId(new Random().nextInt());
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " RowData_for_detalisaziy " +RowData_for_detalisaziy);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return RowData_for_detalisaziy;
    }
    // TODO: 29.06.2023  метод получение GET Row CFO
    @NonNull
    private TableRow методGetImageTableRow() {
        TableRow tablerow_image = null;
        try{
            TableLayout  tableLayoutRowImage= (TableLayout) LayoutInflater.from(getContext())
                    .inflate(R.layout.simple_for_assiona_material_image_row,null);//todo old  simple_for_assionamaterial
            tablerow_image = (TableRow)   tableLayoutRowImage.findViewById(R.id.tablerow_image);
            // TODO: 29.06.2023  удаление данных из PARENT
            tableLayoutRowImage.recomputeViewAttributes(tablerow_image);
            tableLayoutRowImage.removeViewInLayout(tablerow_image);
            tableLayoutRowImage.removeView(tablerow_image);
            tablerow_image.setId(new Random().nextInt());
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " tablerow_image " +tablerow_image);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return tablerow_image;
    }
    // TODO: 29.06.2023  метод получение GET Row NAME
    @NonNull
    private TableRow методGetNameTableRow() {
        TableRow RowData_for_detalisaziy = null;
        try{
            TableLayout  tableLayoutДеталицация= (TableLayout) LayoutInflater.from(getContext())
                    .inflate(R.layout.simple_for_assionamaterial_detelizaziy_row,null);//todo old  simple_for_assionamaterial
            RowData_for_detalisaziy = (TableRow)   tableLayoutДеталицация.findViewById(R.id.tableName_for_detalisaziy);
            // TODO: 29.06.2023  удаление данных из PARENT
            tableLayoutДеталицация.recomputeViewAttributes(RowData_for_detalisaziy);
            tableLayoutДеталицация.removeViewInLayout(RowData_for_detalisaziy);
            tableLayoutДеталицация.removeView(RowData_for_detalisaziy);
            RowData_for_detalisaziy.setId(new Random().nextInt());
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " RowData_for_detalisaziy " +RowData_for_detalisaziy);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return RowData_for_detalisaziy;
    }
    // TODO: 29.06.2023  метод получение GET Row Line
    @NonNull
    private TableRow методGetLineTableRow() {
        TableRow RowData_for_detalisaziy = null;
        try{
            TableLayout  tableLayoutДеталицация= (TableLayout) LayoutInflater.from(getContext())
                    .inflate(R.layout.simple_for_assionamaterial_detelizaziy_row,null);//todo old  simple_for_assionamaterial
            RowData_for_detalisaziy = (TableRow)   tableLayoutДеталицация.findViewById(R.id.tablerowline_detaliz);
            // TODO: 29.06.2023  удаление данных из PARENT
            tableLayoutДеталицация.recomputeViewAttributes(RowData_for_detalisaziy);
            tableLayoutДеталицация.removeViewInLayout(RowData_for_detalisaziy);
            tableLayoutДеталицация.removeView(RowData_for_detalisaziy);
            RowData_for_detalisaziy.setId(new Random().nextInt());
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " RowData_for_detalisaziy " +RowData_for_detalisaziy);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return RowData_for_detalisaziy;
    }
}