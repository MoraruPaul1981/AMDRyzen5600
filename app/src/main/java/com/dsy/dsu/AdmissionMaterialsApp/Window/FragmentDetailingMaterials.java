package com.dsy.dsu.AdmissionMaterialsApp.Window;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForAdminissionMaterial;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.rxbinding4.view.RxView;


import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import kotlin.Unit;


// TODO: 29.09.2022 фрагмент для получение материалов
public class FragmentDetailingMaterials extends Fragment {
    private Integer ПубличныйIDДляФрагмента;
    private RecyclerView recyclerView;
    private LinearLayout linearLayou;
    private Fragment fragment_ТекущийФрагментСогласованиеСписок;
    private BottomNavigationView bottomNavigationView;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemViewвыход;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemView2создать;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemView3обновить;
    private ProgressBar progressBarСканирование;
    private LayoutAnimationController layoutAnimationController;
    private Animation animation;
    private  Handler handler;
    private MyRecycleViewAdapterDetalingMaterial myRecycleViewAdapterDetalingMaterial;
    private MyViewHolder myViewHolder;
    private  ServiceForAdminissionMaterial.LocalBinderДляПолучениеМатериалов binderДляПолучениеМатериалов;
    private Integer ТекущаяЦФО=0;
    private Integer НомерВыбраногоМатериала =0;
    private Integer Количество =0;
    private String ВыбранныйМатериал=new String();
    private String Материал =new String();
    private  ViewGroup container;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private AsyncTaskLoader<Cursor> asyncTaskLoaderДетализация;
    private    Bundle data;
    long start;
    long startДляОбноразвовной;
   private Cursor  cursorДетализацияМатериала;

    // TODO: 27.09.2022 Фрагмент Получение Материалов
    public FragmentDetailingMaterials() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            МетодHandlerCallBack();
             data=      getArguments();
            if (data!=null) {
                binderДляПолучениеМатериалов=  (ServiceForAdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");
                ТекущаяЦФО= data.getInt("Цфо");
                НомерВыбраногоМатериала = data.getInt("НомерВыбраногоМатериала");
                Материал =data.getString("Материал");
                Количество =data.getInt("Количество");
                ВыбранныйМатериал =data.getString("ВыбранныйМатериал");

            }
            start=     Calendar.getInstance().getTimeInMillis();
            startДляОбноразвовной=     Calendar.getInstance().getTimeInMillis();

            Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  binderДляПолучениеМатериалов  "+binderДляПолучениеМатериалов+
                    " ТекущаяЦФО " +ТекущаяЦФО+ " НомерВыбраногоМатериала "+ НомерВыбраногоМатериала +
                    "ВыбранныйМатериал "+ВыбранныйМатериал+"Количество "+ Количество);
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
            view= inflater.inflate(R.layout.fragment_admission_materials_detelizaziy, container, false);
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
    @SuppressLint({"RestrictedApi", "WrongConstant"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);
            recyclerView = view.findViewById(R.id.RecyclerView);

            recyclerView.setHorizontalScrollBarEnabled(true);
            recyclerView.setHorizontalFadingEdgeEnabled(true);

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
            МетодЗаполенияRecycleViewДляЗадач();//todo заполения recycreview
            МетодИнициализацииRecycreView();
            // TODO: 01.07.2023  ДАннеы


            МетодВыходНаAppBack();

            metodCreateNewMaretila();

            metodStaringDataMarerialv();


            МетодКпопкиЗначков(cursorДетализацияМатериала);

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

            // TODO: 10.11.2022
            if(binderДляПолучениеМатериалов!=null){
                методGetCursorForDetalizaa();
            }

            // TODO: 17.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorДетализацияМатериала " + cursorДетализацияМатериала);
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
    public void onResume() {
        super.onResume();
      try{
        if (asyncTaskLoaderДетализация!=null && asyncTaskLoaderДетализация.isAbandoned()) {
            методRebootRecyreViewonStop();
            МетодКпопкиЗначков(cursorДетализацияМатериала);
            МетодСоздаенияСлушателяДляПолучениеМатериалаWorkMAnager();
            МетодСлушательКурсора();
            МетодСлушательRecycleView();//todo создаем слушатель для recycreview для получение материалов
            // TODO: 17.07.2023
            МетодДизайнПрограссБара();
        }
          МетодПерегрузкаRecyceView();
        // TODO: 17.04.2023
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorДетализацияМатериала " + cursorДетализацияМатериала);
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
            // TODO: 20.11.2023 стави курсор на Posstion First
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorДетализацияМатериала " + cursorДетализацияМатериала);
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
            if (cursorДетализацияМатериала!=null) {
                myRecycleViewAdapterDetalingMaterial.cursor=cursorДетализацияМатериала;
                myRecycleViewAdapterDetalingMaterial.notifyDataSetChanged();
                RecyclerView.Adapter recyclerViewОбновление=         recyclerView.getAdapter();
                recyclerView.swapAdapter(recyclerViewОбновление,true);
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            recyclerView.refreshDrawableState();
            recyclerView.requestLayout();
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " cursorДетализацияМатериала " +cursorДетализацияМатериала);
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


    // TODO: 18.07.2023  метод получение GET CURSOR
    private void методGetCursorForDetalizaa() {
            try{
                asyncTaskLoaderДетализация=new AsyncTaskLoader(getActivity()) {
                    @Nullable
                    @Override
                    public Object loadInBackground() {

                        cursorДетализацияМатериала=
                                МетодПолучениеДанныхДЛяПолучениеМатериалов(
                                        "ПолучениеНомерМатериалаДетализация"
                                        ,ТекущаяЦФО, НомерВыбраногоМатериала);
                        Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  binderДляПолучениеМатериалов  "+binderДляПолучениеМатериалов+
                                " ТекущаяЦФО " +ТекущаяЦФО+ " НомерВыбраногоМатериала "+ НомерВыбраногоМатериала +
                                "ВыбранныйМатериал "+ВыбранныйМатериал+"Количество "+ Количество + " cursorДетализацияМатериала " +cursorДетализацияМатериала);
                        return cursorДетализацияМатериала;
                    }
                };
                asyncTaskLoaderДетализация.startLoading();
                asyncTaskLoaderДетализация.forceLoad();
                asyncTaskLoaderДетализация.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener<Cursor>() {
                    @Override
                    public void onLoadComplete(@NonNull Loader<Cursor> loader, @Nullable Cursor data) {
                        try{
                            if(data!=null){
                                cursorДетализацияМатериала=data;
                                // TODO: 01.10.2024
                                setingCursorFirstMove();

                                asyncTaskLoaderДетализация.abandon();
                                // TODO: 08.08.2023
                             onResume();

                            }
                            Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  binderДляПолучениеМатериалов  "+binderДляПолучениеМатериалов+
                                    " ТекущаяЦФО " +ТекущаяЦФО+ " НомерВыбраногоМатериала "+ НомерВыбраногоМатериала +
                                    "ВыбранныйМатериал "+ВыбранныйМатериал+"Количество "+ Количество + " cursorДетализацияМатериала " +cursorДетализацияМатериала);
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

    private void setingCursorFirstMove() {
        if(!cursorДетализацияМатериала.isFirst()){
            cursorДетализацияМатериала.moveToFirst();
        }
    }


    // TODO: 04.03.2022 прозвомжность Заполения RecycleView
    void МетодЗаполенияRecycleViewДляЗадач() {
        try {
            myRecycleViewAdapterDetalingMaterial = new MyRecycleViewAdapterDetalingMaterial(cursorДетализацияМатериала);
            myRecycleViewAdapterDetalingMaterial.notifyDataSetChanged();
            recyclerView.setAdapter(myRecycleViewAdapterDetalingMaterial);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " recyclerView  " + recyclerView);
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



            RxView.clicks(  bottomNavigationItemViewвыход)
                    .throttleFirst(3, TimeUnit.SECONDS)
                    .filter(s -> !s.toString().isEmpty())
                    .map(new Function<Unit, BottomNavigationItemView>() {
                        @Override
                        public BottomNavigationItemView apply(Unit unit) throws Throwable {
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                            return    bottomNavigationItemViewвыход;
                        }
                    })
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    })
                    .subscribe( GetNameSingleAsync1c-> {
                        ///todo revboot
                        МетодЗапускаАнимацииКнопок(GetNameSingleAsync1c);//todo только анимауия
                        Fragment      fragmentПолученыеМатериалов = new FragmentAdmissionMaterials();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle data=new Bundle();
                        data.putBinder("binder",binderДляПолучениеМатериалов);
                        fragmentПолученыеМатериалов.setArguments(data);
                        // fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragmentПолученыеМатериалов).commit();//.layout.activity_for_fragemtb_history_tasks
                        fragmentTransaction.show(fragmentПолученыеМатериалов);

                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

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

    private void metodStaringDataMarerialv() {



        RxView.clicks(  bottomNavigationItemView3обновить)
                .throttleFirst(3, TimeUnit.SECONDS)
                .filter(s -> !s.toString().isEmpty())
                .map(new Function<Unit, BottomNavigationItemView>() {
                    @Override
                    public BottomNavigationItemView apply(Unit unit) throws Throwable {
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        return    bottomNavigationItemView3обновить;
                    }
                })
                .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                })
                .onErrorComplete(new Predicate<Throwable>() {
                    @Override
                    public boolean test(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                        return false;
                    }
                })
                .subscribe( GetNameSingleAsync1c-> {
                    ///todo revboot

                    МетодЗапускаАнимацииКнопок(GetNameSingleAsync1c);
                    handler.postDelayed(()->{
                                Integer ПубличныйIDДляФрагмента =   new GetttingPublicID().getttingPublicID(getContext());

                            },
                            500);
                    Log.d(this.getClass().getName(), " GetNameSingleAsync1c  " + GetNameSingleAsync1c);
                    Log.d(this.getClass().getName(), " GetNameSingleAsync1c  " +GetNameSingleAsync1c);
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

                });



    }

    private void metodCreateNewMaretila() {


        RxView.clicks(  bottomNavigationItemView2создать)
                .throttleFirst(3, TimeUnit.SECONDS)
                .filter(s -> !s.toString().isEmpty())
                .map(new Function<Unit, BottomNavigationItemView>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public BottomNavigationItemView apply(Unit unit) throws Throwable {
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        return    bottomNavigationItemView2создать;
                    }
                })
                .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                })
                .onErrorComplete(new Predicate<Throwable>() {
                    @Override
                    public boolean test(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                        return false;
                    }
                })
                .subscribe( GetNameSingleAsync1c-> {
                    ///todo revboot

                    МетодЗапускаАнимацииКнопок(GetNameSingleAsync1c);
                    handler.postDelayed(()->{ МетодЗапускСозданиНовгоМатериалов();},500);

                    Log.d(this.getClass().getName(), " GetNameSingleAsync1c  " +GetNameSingleAsync1c);
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

                });




    }


    protected void МетодЗапускСозданиНовгоМатериалов() {
        try{
            fragmentTransaction = fragmentManager.beginTransaction();
            Fragment    fragment_СозданиеНовогоМатериалов = new FragmentMaretialNew();
            String FragmentNewImageName=   fragment_СозданиеНовогоМатериалов.getClass().getName();
            fragmentTransaction.addToBackStack(FragmentNewImageName);
            Bundle data=new Bundle();
            data.putBinder("binder",binderДляПолучениеМатериалов);
            fragment_СозданиеНовогоМатериалов.setArguments(data);
            fragmentTransaction.setCustomAnimations( android.R.anim.slide_in_left,android.R.anim.slide_out_right);
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
        handler .postDelayed(()->{
            v.animate().rotationX(0);
        },300);
    }
    private void МетодКпопкиЗначков(@NonNull Cursor cursor)
            throws ExecutionException, InterruptedException {
        try {
            if (cursor!=null) {
                if (  cursor.getCount()> 0) {
                        Log.d(this.getClass().getName(), "  cursor" + cursor.getCount());
                       bottomNavigationView.getOrCreateBadge(R.id.id_lback).setNumber( cursor.getCount());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                        bottomNavigationView.getOrCreateBadge(R.id.id_lback).setBackgroundColor(Color.parseColor("#15958A"));
                    } else {
                        bottomNavigationView.getOrCreateBadge(R.id.id_lback).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                        bottomNavigationView.getOrCreateBadge(R.id.id_lback).setBackgroundColor(Color.RED);
                    }
            }else {
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
        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                try {
                    Log.d(this.getClass().getName(), " msg  " + msg);
                    Bundle bundle = msg.getData();
                    Log.d(this.getClass().getName(), " bundle  " + bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return true;
            }
        });
    }
    // TODO: 10.03.2022 БИЗНЕС-КОД для ФРАГМЕНТА ПОСТУПЛЕНИЯ МАТЕРИАЛА

    void МетодСлушательRecycleView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
        try {
            if (myRecycleViewAdapterDetalingMaterial !=null) {
                myRecycleViewAdapterDetalingMaterial.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
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
    , @NonNull Integer НомерВыбраногоМатериала ){
        Cursor cursorДетализацияМатериала = null;
        try{
            ПубличныйIDДляФрагмента     =     new GetttingPublicID().getttingPublicID(getContext());

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
            bundleДляПЕредачи.putInt("НомерВыбраногоМатериала",НомерВыбраногоМатериала);
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

    private void МетодДизайнПрограссБара() {
        progressBarСканирование.setVisibility(View.INVISIBLE);
    }

    private void МетодСлушательКурсора() {
        // TODO: 15.10.2022  слушатиель для курсора
        try {
            if (cursorДетализацияМатериала !=null) {
                cursorДетализацияМатериала.registerDataSetObserver(new DataSetObserver() {
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
                cursorДетализацияМатериала.registerContentObserver(new ContentObserver(handler) {
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
        private TableLayout tableLayout_material_detalizaziy;
        private MaterialCardView materialcardView_for_detalizaziy;
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
                tableLayout_material_detalizaziy = itemView.findViewById(R.id.tableLayout_material_detalizaziy);
                // TODO: 29.06.2023
                materialcardView_for_detalizaziy = itemView.findViewById(R.id.materialcardView_for_detalizaziy);
                Log.d(this.getClass().getName(), " cardViewМатериал   " + materialcardView_for_detalizaziy);
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

    class MyRecycleViewAdapterDetalingMaterial extends RecyclerView.Adapter<MyViewHolder> {
        private Cursor  cursor ;
        public MyRecycleViewAdapterDetalingMaterial(@NotNull Cursor cursorДетализацияМатериала) {
            this.cursor = cursorДетализацияМатериала;
            // TODO: 18.04.2023  Simple Adapter Кдик по Элементы
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " cursor " +cursor);

        }
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            try {
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position +
                        " cursor "+cursor);
                if(cursor!=null){
                    МетодЗаполняемДаннымиПолучениеМАтериалов(holder,cursor);
                }
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position +
                        " cursor "+cursor);
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
                if( cursor==null){
                    viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_load_actimavmaretialovdetalizasia, parent, false);//todo old simple_for_takst_cardview1
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов);

                }else {
                    if (cursor.getCount() > 0 ) {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_assionamaterial_detalizay, parent, false);//todo old  simple_for_assionamaterial
                        Log.i(this.getClass().getName(), "   viewПолучениеМатериалов" + viewПолучениеМатериалов+ "  cursorДетализацияМатериала.getCount()  " + cursor.getCount());
                    } else   {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_isnull_actimavmaretisldetalizasia, parent, false);//todo old simple_for_takst_cardview1
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов+ "  cursorДетализацияМатериала.getCount()  " + cursor.getCount() );
                    }
                }
                // TODO: 13.10.2022  добавляем новый компонент в Нащ RecycreView
                myViewHolder = new MyViewHolder(viewПолучениеМатериалов);
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


        ///todo первый метод #1
        private void МетодЗаполняемДаннымиПолучениеМАтериалов(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
            try {
                    // TODO: 18.10.2022 заполеняем данныими
                    МетодДобавленеиЕлементоввRecycreView(holder.tableLayout_material_detalizaziy);
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


        private void МетодДобавленеиЕлементоввRecycreView(@NonNull TableLayout tableLayoutРодительская) {
            try {
                // TODO: 07.11.2022   ВТОРОЙ ЭТАП ПОЛУЧАЕМ НОМЕР ЦФО
                Log.i(this.getClass().getName(), "  ТекущаяЦФО " + ТекущаяЦФО + " cursor " + cursor + " ТекущаяЦФО " +ТекущаяЦФО);
                    // TODO: 18.10.2022 Добавяем Названием ЦФО
                    if (tableLayoutРодительская.getChildCount()==0) {
                        МетодНазваниеЦФОДетализация(tableLayoutРодительская);
                        // TODO: 18.10.2022 дял линии
                        МетодДанныеЛинияДетализации(tableLayoutРодительская);
                        // TODO: 18.10.2022 Добавяем Названием Столбиков
                        МетодНазваниеСтолбиковДетализация(tableLayoutРодительская);
                        // TODO: 18.10.2022 Добавяем Данные
                        МетодДанныеМатериалДетализация(tableLayoutРодительская );
                    }


                МетодПерегрузкаRecyceView();
                // TODO: 17.04.2023
                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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





        // TODO: 08.11.2022 метод Удаление материала
        private void МетодаКликаУдаленияМатериалаПоtableRow(TableRow rowПервыеДанные) {
            try{
                rowПервыеДанные.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        progressBarСканирование.setVisibility(View.VISIBLE);
                        v.animate().rotationX(-60l);
                        handler .postDelayed(()->{
                            v.animate().rotationX(0);
                            // TODO: 01.07.2023 удаление
                            Bundle bundleПереходУдалениеМатериала=(Bundle) v.getTag();
                            Log.d(this.getClass().getName(), "МетодаКликаУдаленияМатериалаПоtableRow v  " + v+ " bundleПереходУдалениеМатериала "
                                    +bundleПереходУдалениеМатериала);
                            if (bundleПереходУдалениеМатериала != null) {
                                Long UUIDДляУдаления= bundleПереходУдалениеМатериала.getLong("UUIDВыбраныйМатериал",0l);
                                String Материал= bundleПереходУдалениеМатериала.getString("Материал","");
                                Integer Количество= bundleПереходУдалениеМатериала.getInt("Количество",0);
                                bundleПереходУдалениеМатериала.putString("selection","uuid=?");
                                Log.d(this.getClass().getName(), "  v  " + v+ " UUIDДляУдаления " +UUIDДляУдаления);
                                Snackbar snackbar = Snackbar.make(v, "Text to display", Snackbar.LENGTH_LONG);
                                View view = snackbar .getView();
                                TextView textView = (TextView) view.findViewById(R.id.snackbar_text);
                                textView.setTextColor(Color.parseColor("#FF4500"));
                                textView.setText(Материал+" : "+Количество+"");
                                snackbar
                                        .setAction("Удалить ? ", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                try{

                                                    Intent intentДляУдалениеМатериалов=new Intent("УдалениеВыбранныеМатериалыДетализации");
                                                    intentДляУдалениеМатериалов.putExtras(bundleПереходУдалениеМатериала);
                                                    Log.d(this.getClass().getName(), "  v  " + v+ " UUIDДляУдаления " +UUIDДляУдаления);
                                                    Integer РезультатСменыСтатусаНАУдалнной=    binderДляПолучениеМатериалов.getService().МетодCлужбыУдалениеМатериалов(getContext(),intentДляУдалениеМатериалов);
                                                    Log.d(this.getClass().getName(), "  РезультатСменыСтатусаНАУдалнной  " + РезультатСменыСтатусаНАУдалнной);
                                                    if(РезультатСменыСтатусаНАУдалнной>0){
                                                        // TODO: 01.07.2023  метод после удланеи детализации
                                                        методПослеУдаленияЗаписиДетализации();
                                                    }
                                                    Log.d(getContext().getClass().getName(), "\n"
                                                            + " время: " + new Date() + "\n+" +
                                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " view " +view);
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
                                        }).setActionTextColor(Color.WHITE)
                                        .setTextColor(Color.GRAY)
                                        .setDuration(3000)
                                        .show();
                            }
                            progressBarСканирование.setVisibility(View.INVISIBLE);
                        },150);

                        return true;
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

        private void МетодаКликаGetFotoМатериалаПоtableRow(TableRow rowПервыеДанные) {
            try{
                rowПервыеДанные.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBarСканирование.setVisibility(View.VISIBLE);
                        v.animate().rotationX(-60l);
                        handler .postDelayed(()->{
                            v.animate().rotationX(0);
                            // TODO: 01.07.2023 удаление
                            Bundle bundleПереходGetImagesFormaterial=(Bundle) v.getTag();

                            методForfardForImages(v, bundleПереходGetImagesFormaterial);

                            // TODO: 18.04.2023  Simple Adapter Кдик по Элементы
                            Log.d(this.getClass().getName(),"\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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

        // TODO: 18.07.2023  метод  перехода на Image Binary  для материала
        private void методForfardForImages(@NonNull  View v, @NonNull Bundle bundleПереходGEtImages) {
            try{
            if (bundleПереходGEtImages != null) {
                Long UUIDДляУдаления= bundleПереходGEtImages.getLong("UUIDВыбраныйМатериал",0l);
                Log.d(this.getClass().getName(), "  v  " + v + " UUIDДляУдаления " +UUIDДляУдаления);
                // TODO: 18.07.2023 пололнительные параменты
                bundleПереходGEtImages.putInt("Цфо",  ТекущаяЦФО);
                bundleПереходGEtImages.putInt("НомерВыбраногоМатериала", НомерВыбраногоМатериала);
                bundleПереходGEtImages.putString("Материал", Материал);
                bundleПереходGEtImages.putInt("Количество", Количество);
                bundleПереходGEtImages.putString("ВыбранныйМатериал",ВыбранныйМатериал);
                bundleПереходGEtImages.putBinder("binder",binderДляПолучениеМатериалов);
                // TODO: 18.07.2023 переходят на Фрагмент Рисунков Binary Image
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentManager.restoreBackStack(null);
              //  fragmentTransaction.setCustomAnimations( android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                Fragment              fragmentImagesMaterials = new FragmentImagesMaterials();
                fragmentImagesMaterials.setArguments(bundleПереходGEtImages);
                fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragmentImagesMaterials);//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.commit();
                fragmentTransaction.show(fragmentImagesMaterials);
                // TODO: 18.07.2023
                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " UUIDДляУдаления " +UUIDДляУдаления);
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


        private void методПослеУдаленияЗаписиДетализации() {
            // TODO: 01.07.2023  метод после удаление
            try{

                методGetCursorForDetalizaa();

                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " cursor " +cursor);
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

        // TODO: 08.11.2022 метод редактирование


        private void МетодНазваниеЦФОДетализация(@NonNull TableLayout tableLayoutРодительская) {
            try {
                TableRow RowName_ForColunt = методGetCFOTableRow();
                // TODO: 29.06.2023 тим Детализации
                TextView textview_dest_nameCFO=  RowName_ForColunt.findViewById(R.id.textviewname_detalis_cfo);
                // TODO: 10.11.2022  данные для название ЦФО
                @SuppressLint("Range") String НазваниеЦФОДляДетализации= Optional.ofNullable(cursor.getString(cursor.
                        getColumnIndex("name_cfo"))).orElse("");
                textview_dest_nameCFO.setText(НазваниеЦФОДляДетализации.replace("\"", "")
                        .replace("\\n", "").trim());

                // TODO: 18.10.2022 добавляем  Линию
                МетодДобаленияНовыхСтрокДанных(RowName_ForColunt, tableLayoutРодительская);
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
        private void МетодНазваниеСтолбиковДетализация(@NonNull TableLayout tableLayoutРодительская) {
            try {

                TableRow RowName_ForColunt = методGetNameTableRow();
                // TODO: 18.10.2022 добавляем  Линию
                МетодДобаленияНовыхСтрокДанных(RowName_ForColunt, tableLayoutРодительская);
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

        private void МетодДанныеЛинияДетализации(@NonNull TableLayout tableLayoutРодительская) {
            try {
                TableRow RowName_ForLineNames = методGetLineTableRow();
                // TODO: 18.10.2022 добавляем  Линию
                МетодДобаленияНовыхСтрокДанных(RowName_ForLineNames, tableLayoutРодительская);

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
        @SuppressLint("Range")
        private void МетодДанныеМатериалДетализация(@NonNull TableLayout tableLayoutРодительская ) {
            try{

                Flowable.range(0,cursor.getCount()).onBackpressureBuffer().blockingForEach(row->{
                    // TODO: 01.10.2024 row

                    TableRow RowData_for_detalisaziy = методGetDataTableRow();
                    // TODO: 29.06.2023 тим Детализации
                    TextView textview_det_type=  RowData_for_detalisaziy.findViewById(R.id.textview_data_det_type);
                    // TODO: 10.11.2022  данные для название ЦФО
                  String типДеталиазции= Optional.ofNullable(cursor.getString(cursor.
                            getColumnIndex("typematerial"))).orElse("");
                    textview_det_type.setText(типДеталиазции.trim());
                    // TODO: 29.06.2023 Материалоа Детализации
                    TextView textview_det_material=  RowData_for_detalisaziy.findViewById(R.id.textview_data_det_material);
                    // TODO: 10.11.2022  данные для название ЦФО
                    @SuppressLint("Range") String nomenvesovДетадизации= Optional.ofNullable(cursor.getString(cursor.
                            getColumnIndex("nomenvesov"))).orElse("");
                    textview_det_material.setText(nomenvesovДетадизации.trim());
                    // TODO: 29.06.2023 Материалоа Детализации
                    TextView textview_det_kilichestvo=  RowData_for_detalisaziy.findViewById(R.id.textview_data_det_kilichestvo);
                    // TODO: 10.11.2022  данные для название ЦФО
                    @SuppressLint("Range") Integer КоличествоДетадизации= Optional.ofNullable(cursor.getInt(cursor.
                            getColumnIndex("count"))).orElse(0);
                    textview_det_kilichestvo.setText(КоличествоДетадизации.toString() );


                    методSetRowBungle(cursor, RowData_for_detalisaziy, типДеталиазции, nomenvesovДетадизации, КоличествоДетадизации);


                    // TODO: 16.11.2022  слушатель Удаление строк
                    МетодаКликаУдаленияМатериалаПоtableRow(RowData_for_detalisaziy);
                    // TODO: 19.10.2022 Клик получение ФОтографии
                    МетодаКликаGetFotoМатериалаПоtableRow(RowData_for_detalisaziy);
                    // TODO: 18.10.2022 добавляем  Линию
                    МетодДобаленияНовыхСтрокДанных(RowData_for_detalisaziy, tableLayoutРодительская);

                   //TODO
                    cursor.moveToNext();

                });

                setingCursorFirstMove();
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






        private void МетодДобаленияНовыхСтрокДанных(TableRow rowПервыеДанные, @NonNull TableLayout tableLayoutРодительская) {
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
            Integer getCountRow=1;
            try {
                Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  ");
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



    private void методSetRowBungle(@NonNull Cursor cursorДетализацияМатериала, TableRow RowData_for_detalisaziy,
                                   String типДеталиазции, String nomenvesovДетадизации, Integer КоличествоДетадизации) {
        try {
        // TODO: 29.06.2023 Данные ДЛя Текущего Row Bungle
        @SuppressLint("Range") Long UUIDВыбраныйМатериал= Optional.ofNullable(cursorДетализацияМатериала.getLong(cursorДетализацияМатериала.
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
    private TableRow методGetCFOTableRow() {
        TableRow RowData_for_detalisaziy = null;
        try{
            TableLayout  tableLayoutДеталицация= (TableLayout) LayoutInflater.from(getContext())
                    .inflate(R.layout.simple_for_assionamaterial_detelizaziy_row,null);//todo old  simple_for_assionamaterial
            RowData_for_detalisaziy = (TableRow)   tableLayoutДеталицация.findViewById(R.id.tablerowCFO_detals);
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