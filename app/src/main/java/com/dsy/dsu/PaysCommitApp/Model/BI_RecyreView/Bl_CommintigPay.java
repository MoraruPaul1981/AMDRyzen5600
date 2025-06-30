package com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.BusinessLogicForApps.Class_Get_Json_1C;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView.LiveData.GetLiveDataForrecyreViewPay;
import com.dsy.dsu.PaysCommitApp.Model.LeftDividerItemDecoratorCommitPay;
import com.dsy.dsu.PaysCommitApp.View.RecyreView.MyRecycleViewAdapterCommingPay;
import com.dsy.dsu.PaysCommitApp.View.RecyreViewIsNull.MyRecycleViewIsNullAdapterPay;
import com.dsy.dsu.R;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForApprove;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textview.MaterialTextView;
import com.jakewharton.rxbinding4.view.RxView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import kotlin.Unit;

// TODO: 10.03.2022 БИЗНЕС-КОД ПЕРЕНЕСЕН ИЗ АКТИВТИ

 
public class Bl_CommintigPay {
    Activity activity;
    View viewCore;
    Integer ПубличныйidPay;
    ObjectMapper objectMapper;
    LifecycleOwner lifecycleOwner;
    Context context;
    ProgressBar progressBarCommitPay;
    RecyclerView recycleviewcommitpays;
    MyRecycleViewIsNullAdapterPay myRecycleViewIsNullAdapter;
    Animation animation;
    MaterialTextView textViewТекущаяЗадача;
      JsonNode jsonNode1сСогласованияRow;

      BottomNavigationView bottomNavigationViewParentCommitingPay;

     BottomNavigationItemView bottomNavigationBackCommitingPay;

      BottomNavigationItemView bottomNavigationAsyncCommitingPay;
     BottomNavigationItemView bottomNavigationSearchCommitingPay;
    MyRecycleViewAdapterCommingPay myRecycleViewAdapter;

    MutableLiveData<Intent> jsonNodeMutableLiveDataPayCommintg;
    androidx.appcompat.widget.SearchView searchview_commitpay;
    String getHiltCommintgPays;
    private   Animation    animationДляСогласования;


    GetLiveDataForrecyreViewPay getLiveDataForrecyreViewPay;

    MutableLiveData<Intent> getHiltMutableLiveDataPayForRecyreView;
    @SuppressLint("SuspiciousIndentation")
    public    Bl_CommintigPay(@NonNull  Activity activity,
                              @NonNull  Context context ,
                              @NotNull View viewCore,
                              @NonNull ObjectMapper objectMapper,
                              @NonNull Integer ПубличныйidPay,
                              @NonNull LifecycleOwner lifecycleOwner,
                              @NonNull RecyclerView recycleviewcommitpays,
                              @NonNull     MyRecycleViewIsNullAdapterPay myRecycleViewIsNullAdapter,
                              @NonNull Animation animation,
                              @NonNull MaterialTextView textViewТекущаяЗадача,
                              @NonNull JsonNode jsonNode1сСогласованияRow,

                              @NonNull BottomNavigationView bottomNavigationViewParentCommitingPay,
                              @NonNull BottomNavigationItemView bottomNavigationBackCommitingPay,
                              @NonNull BottomNavigationItemView bottomNavigationAsyncCommitingPay,
                              @NonNull BottomNavigationItemView bottomNavigationSearchCommitingPay,
                              @NonNull MutableLiveData<Intent> jsonNodeMutableLiveDataPayCommintg,
                              @NonNull androidx.appcompat.widget.SearchView searchview_commitpay,
                              @NonNull String getHiltCommintgPays,
                              @NonNull GetLiveDataForrecyreViewPay getLiveDataForrecyreViewPay,
                              @NonNull  MutableLiveData<Intent> getHiltMutableLiveDataPayForRecyreView) {
        try{
        this.activity = activity;
        this.viewCore = viewCore;
        this.objectMapper = objectMapper;
        this.ПубличныйidPay = ПубличныйidPay;
        this.lifecycleOwner = lifecycleOwner;
        this.context = context;
        this.recycleviewcommitpays = recycleviewcommitpays;
        this.myRecycleViewIsNullAdapter = myRecycleViewIsNullAdapter;
        this.animation = animation;
        this.textViewТекущаяЗадача = textViewТекущаяЗадача;
        this.jsonNode1сСогласованияRow = jsonNode1сСогласованияRow;

        this.bottomNavigationViewParentCommitingPay = bottomNavigationViewParentCommitingPay;
        this.bottomNavigationBackCommitingPay = bottomNavigationBackCommitingPay;

        this.bottomNavigationAsyncCommitingPay = bottomNavigationAsyncCommitingPay;
        this.bottomNavigationSearchCommitingPay = bottomNavigationSearchCommitingPay;

        this.myRecycleViewAdapter = myRecycleViewAdapter;
        this.jsonNodeMutableLiveDataPayCommintg = jsonNodeMutableLiveDataPayCommintg;
        this.searchview_commitpay = searchview_commitpay;
        this.getHiltCommintgPays = getHiltCommintgPays;
        this.getLiveDataForrecyreViewPay = getLiveDataForrecyreViewPay;
        this.  getHiltMutableLiveDataPayForRecyreView = getHiltMutableLiveDataPayForRecyreView;

            // TODO: 15.01.2024 init...

        progressBarCommitPay = viewCore.findViewById(R.id.prograessbarcommitpaydown); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА

   // animationДляСогласования= AnimationUtils.loadAnimation(context, R.anim.slide_in_row);//R.anim.layout_animal_commit
    animationДляСогласования= AnimationUtils.loadAnimation(context,  R.anim.slide_in_scrolls);//R.anim.layout_animal_commit

        Log.d(activity.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                " recyclerViewСогласование1С " + recycleviewcommitpays);

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    public void МетодСлушательObserverДляRecycleView(@NonNull MyRecycleViewAdapterCommingPay myRecycleViewAdapterCommingPay) {  // TODO: 04.03.2022  класс в котором находяться слушатели
        try {
            if (myRecycleViewAdapterCommingPay !=null) {
                myRecycleViewAdapterCommingPay.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        try {
                            Log.d(context.getClass().getName(), "\n"
                                    + " время: " + new Date()+"\n+" +
                                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        } catch (Exception e) {
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount) {
                        super.onItemRangeChanged(positionStart, itemCount);
                        // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
                        try {
                            Log.d(context.getClass().getName(), "\n"
                                    + " время: " + new Date()+"\n+" +
                                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                        super.onItemRangeChanged(positionStart, itemCount, payload);
                        try{
                            Log.d(context.getClass().getName(), "\n"
                                    + " время: " + new Date()+"\n+" +
                                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        super.onItemRangeInserted(positionStart, itemCount);
                        try{
                            Log.d(context.getClass().getName(), "\n"
                                    + " время: " + new Date()+"\n+" +
                                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onItemRangeRemoved(int positionStart, int itemCount) {
                        super.onItemRangeRemoved(positionStart, itemCount);
                        try{
                            Log.d(context.getClass().getName(), "\n"
                                    + " время: " + new Date()+"\n+" +
                                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                        try{
                            Log.d(context.getClass().getName(), "\n"
                                    + " время: " + new Date()+"\n+" +
                                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 02.03.2022 выход

    public void eventBackCommmitPays( )
            throws ExecutionException, InterruptedException {
        try {

            RxView.clicks(  bottomNavigationBackCommitingPay)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .filter(s -> !s.toString().isEmpty())
                    .map(new Function<Unit, Object>() {
                        @Override
                        public Object apply(Unit unit) throws Throwable {
                            // TODO: 30.12.2023
                            Log.d(this.getClass().getName(), "\n" + " class "
                                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                            progressBarCommitPay.setVisibility(View.VISIBLE);
                            return    bottomNavigationBackCommitingPay;
                        }
                    })
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(context.getClass().getName(),
                                    "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(context.getClass().getName(),
                                    "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    })
                    .subscribe( getbottomNavigationSearch-> {
                        ///todo revboot
                        try{


                            subcriBackFromCommitPay();



              /*          // TODO Запусукаем Фргамент НАстройки  dashbord
                        DashboardFragmentSettings dashboardFragmentSettings = DashboardFragmentSettings.newInstance();
                        Bundle data=new Bundle();
                        dashboardFragmentSettings.setArguments(data);
                        fragmentTransaction.remove(dashboardFragmentSettings);
                        String fragmentNewImageNameaddToBackStack=   dashboardFragmentSettings.getClass().getName();
                        fragmentTransaction.addToBackStack(fragmentNewImageNameaddToBackStack);
                        Fragment FragmentУжеЕСтьИлиНЕт=     fragmentManager.findFragmentByTag(fragmentNewImageNameaddToBackStack);
                        if (FragmentУжеЕСтьИлиНЕт==null) {
                            dashboardFragmentSettings.show(fragmentManager, "DashboardFragmentSettings");
                            // TODO: 01.08.2023

                        }*/








                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " getbottomNavigationSearch " +getbottomNavigationSearch );
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            ///
                        }

                    });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void subcriBackFromCommitPay() {
        try{

           /* LaunchActivityDashboard launchActivityDashboard=new LaunchActivityDashboard( fragmentManager,getContext());
            // TODO: 27.03.2024 в зависомсти кто вызвает
            launchActivityDashboard.     launchADashboardFragment();*/


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    // TODO: 02.03.2022  принудительный обмен с 1с
    public void eVentsAsycBottunCommintgPay( )
            throws ExecutionException, InterruptedException {
        try {
            // TODO: 02.03.2022
            Log.d(this.getClass().getName(), "  принудительный обмен  МетодКпопкаВозвращениеНазадИзСогласованиии" );
            RxView.clicks(  bottomNavigationAsyncCommitingPay)
                    .throttleFirst(2, TimeUnit.SECONDS)
                    .filter(s -> !s.toString().isEmpty())
                    .map(new Function<Unit, Object>() {
                        @Override
                        public Object apply(Unit unit) throws Throwable {
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                            progressBarCommitPay.setVisibility(View.VISIBLE);
                            return    bottomNavigationAsyncCommitingPay;
                        }
                    })
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(context.getClass().getName(),
                                    "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(context.getClass().getName(),
                                    "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    })
                    .subscribe( GetAsyncCommitPays-> {
                        ///todo revboot

                        startingAsyncAdvansed( );


                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " GetAsyncCommitPays " +GetAsyncCommitPays );

                    });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    public void eVentSearchViewCommingPay(@NonNull JsonNode jsonNode1сСогласованияAllRows)
            throws ExecutionException, InterruptedException {
        // TODO: 02.03.2022
        try {


            RxView.clicks(  bottomNavigationSearchCommitingPay)
                    .throttleFirst(1,TimeUnit.SECONDS)
                    .filter(s -> !s.toString().isEmpty())
                    .map(new Function<Unit, Object>() {
                        @Override
                        public Object apply(Unit unit) throws Throwable {
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                            return    bottomNavigationSearchCommitingPay;
                        }
                    })
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(context.getClass().getName(),
                                    "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(context.getClass().getName(),
                                    "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    })
                    .subscribe( GetbottomNavigationSearchCommitingPay-> {

                        // TODO: 16.01.2024
                        subcSerchViewPay(jsonNode1сСогласованияAllRows);


                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                + "\n"+ " GetbottomNavigationSearchCommitingPay " +GetbottomNavigationSearchCommitingPay );

                    });
            // TODO: 09.03.2022
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void subcSerchViewPay(@NonNull JsonNode jsonNode1сСогласованияAllRows) {
        try{
        if (jsonNode1сСогласованияAllRows !=null) {
            if (searchview_commitpay.getVisibility()==View.VISIBLE) {
                searchview_commitpay.setVisibility(View.GONE);
                searchview_commitpay.setEnabled(false);
                ViewGroup.LayoutParams params= searchview_commitpay.getLayoutParams();
                params.height=0;
                searchview_commitpay.setLayoutParams(params);

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            }else {
                searchview_commitpay.setVisibility(View.VISIBLE);
                searchview_commitpay.setEnabled(true);
                ViewGroup.LayoutParams params= searchview_commitpay.getLayoutParams();
                params.height=80;
                searchview_commitpay.setLayoutParams(params);

                // TODO: 21.11.2023 Enadble Filter



              new AdapterSerachViewPay(searchview_commitpay,context, jsonNode1сСогласованияAllRows,activity,
                      myRecycleViewAdapter, recycleviewcommitpays,this).setAdapterSerachViewPay();

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            }

            searchview_commitpay.startAnimation(animation);
            searchview_commitpay.requestLayout();
            searchview_commitpay.refreshDrawableState();

            recycleviewcommitpays.requestLayout();
            recycleviewcommitpays.refreshDrawableState();


        }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "jsonNode1сСогласованияAllRows " + jsonNode1сСогласованияAllRows);
        // TODO: 09.03.2022
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    // TODO: 04.03.2022 прозвомжность Заполения RecycleView
  public   void InitMyAdapterRecyreViewWorker(@NonNull JsonNode jsonNode1сСогласованияAllRows,
                                              @NonNull   ServiceForApprove.LocalBinderДляСогласования binderСогласования1C,
                                              @NonNull  String getHiltCommintgPays,@NonNull LifecycleOwner lifecycleOwner) {
        try {
            if (myRecycleViewAdapter==null) {
                myRecycleViewAdapter = new MyRecycleViewAdapterCommingPay(jsonNode1сСогласованияAllRows,context
                        ,binderСогласования1C,animation,ПубличныйidPay,objectMapper,
                        bottomNavigationViewParentCommitingPay,recycleviewcommitpays,
                        getHiltCommintgPays,this,jsonNode1сСогласованияAllRows,lifecycleOwner,
                        getLiveDataForrecyreViewPay,getHiltMutableLiveDataPayForRecyreView,searchview_commitpay,activity);
                // TODO: 24.01.2024
                myRecycleViewAdapter.notifyDataSetChanged();
                recycleviewcommitpays.setAdapter(myRecycleViewAdapter);
                recycleviewcommitpays.getAdapter().notifyDataSetChanged();



            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"jsonNode1сСогласованияAllRows  "
                    + jsonNode1сСогласованияAllRows);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    public   void InitMyAdapterRecyreViewIsNull( @NonNull Boolean РЕжимЗагрузки) {
        try {// TODO: 06.02.2024 TRUE это показыаем кран что идт зашгрузка данных
                ArrayList<Boolean> arrayListIsNull1cData=new ArrayList<>();
                arrayListIsNull1cData.add(РЕжимЗагрузки);
                myRecycleViewIsNullAdapter = new MyRecycleViewIsNullAdapterPay(arrayListIsNull1cData,activity  );
                myRecycleViewIsNullAdapter.notifyDataSetChanged();
                recycleviewcommitpays.setAdapter(myRecycleViewIsNullAdapter);
                recycleviewcommitpays.getAdapter().notifyDataSetChanged();




            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"asyncTaskLoader  ");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    // TODO: 04.03.2022 прозвомжность инициализации RecycleView
    public void InitRecyreReview( ) {
        try {


            recycleviewcommitpays.setHasFixedSize(true);
            recycleviewcommitpays.addItemDecoration(new LeftDividerItemDecoratorCommitPay(context));
            GridLayoutManager layoutManager = new GridLayoutManager(context, 1,GridLayoutManager.VERTICAL,false);
            layoutManager.setSpanSizeLookup(new GridLayoutManager.DefaultSpanSizeLookup());




              /*  DividerItemDecoration dividerItemDecorationHor=
                        new DividerItemDecoration(context, LinearLayoutManager.VERTICAL);*/
         /*   DividerItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL);
            GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{0xfff7f7f7, 0xfff7f7f7});
            drawable.setSize(1,1);
            itemDecoration.setDrawable(drawable);
            *//*            dividerItemDecorationHor.setDrawable(context.getDrawable(R.drawable.divider_for_order_transport1));///R.dimen.activity_horizontal_margin*//*
            recyclerViewСогласование1С.addItemDecoration(itemDecoration);

            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);*/
          /*  linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);*/
            recycleviewcommitpays.setLayoutManager(layoutManager);
            //TODO new LinearLayoutManager(context) // TODO: 28.02.2022 создаем наш первый RecyclerView recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recycleviewcommitpays.startAnimation(animation);
            // TODO: 28.02.2022
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " animation " +animation);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }



    // TODO: 14.03.2022

    public void successNavigatorButtonIconRow(@NonNull JsonNode jsonNode1сСогласованияRow ) {
        try {
            // TODO: 09.03.2022
            if (jsonNode1сСогласованияRow!=null) {
                if (  jsonNode1сСогласованияRow.size()>0) {
                    bottomNavigationViewParentCommitingPay.getOrCreateBadge(R.id.bottomNavigationAsync).setBackgroundColor(Color.parseColor("#008080"));
                    bottomNavigationViewParentCommitingPay.getOrCreateBadge(R.id.bottomNavigationAsync).setNumber(jsonNode1сСогласованияRow.size());
                    // TODO: 25.03.2024 after commint
                    Animation animationДляСогласованияОплаты= AnimationUtils.loadAnimation(context, R.anim.slide_in_scrolls);//R.anim.layout_animal_commit
                    bottomNavigationViewParentCommitingPay.startAnimation(animationДляСогласованияОплаты);

                    //.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                } else {
                    bottomNavigationViewParentCommitingPay.getOrCreateBadge(R.id.bottomNavigationAsync).setBackgroundColor(Color.RED)        ;
                    bottomNavigationViewParentCommitingPay.getOrCreateBadge(R.id.bottomNavigationAsync).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                }
            }else {
                bottomNavigationViewParentCommitingPay.getOrCreateBadge(R.id.bottomNavigationAsync).setBackgroundColor(Color.RED)        ;
                bottomNavigationViewParentCommitingPay.getOrCreateBadge(R.id.bottomNavigationAsync).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
            }
            bottomNavigationViewParentCommitingPay.requestLayout();
            bottomNavigationViewParentCommitingPay.refreshDrawableState();

            // TODO: 15.01.2024
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    public void navigatorDontbuttonIconRow(@NonNull JsonNode jsonNode1сСогласованияRow ) {
        try {
            // TODO: 09.03.2022
            if (jsonNode1сСогласованияRow!=null) {
                if (  jsonNode1сСогласованияRow.size()>0) {
                    bottomNavigationViewParentCommitingPay.getOrCreateBadge(R.id.bottomNavigationAsync).setBackgroundColor(Color.parseColor("#008080"));
                    bottomNavigationViewParentCommitingPay.getOrCreateBadge(R.id.bottomNavigationAsync).setNumber(jsonNode1сСогласованияRow.size());
                } else {
                    bottomNavigationViewParentCommitingPay.getOrCreateBadge(R.id.bottomNavigationAsync).setBackgroundColor(Color.RED)        ;
                    bottomNavigationViewParentCommitingPay.getOrCreateBadge(R.id.bottomNavigationAsync).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                }
            }else {
                bottomNavigationViewParentCommitingPay.getOrCreateBadge(R.id.bottomNavigationAsync).setBackgroundColor(Color.RED)        ;
                bottomNavigationViewParentCommitingPay.getOrCreateBadge(R.id.bottomNavigationAsync).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
            }
            bottomNavigationViewParentCommitingPay.requestLayout();
            bottomNavigationViewParentCommitingPay.refreshDrawableState();

            // TODO: 15.01.2024
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    public void rebootDizaynRecyreView() {
        try{
            progressBarCommitPay.requestLayout();
            progressBarCommitPay.refreshDrawableState();

            recycleviewcommitpays.requestLayout();
            recycleviewcommitpays.refreshDrawableState();

            bottomNavigationViewParentCommitingPay.requestLayout();
            bottomNavigationViewParentCommitingPay.refreshDrawableState();


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }







    @SuppressLint("RestrictedApi")
    public void setEnableSearchMechi(@NonNull JsonNode jsonNode1сСогласованияRow,@NonNull BottomNavigationView bottomNavigationViewParent )
    {
        try {
            // TODO: 09.03.2022
            if (jsonNode1сСогласованияRow.size()>0) {

                BottomNavigationItemView    bottomNavigationSearch = bottomNavigationViewParent.findViewById(R.id.bottomNavigationSearch);

                bottomNavigationSearch.setEnabled(true);
                bottomNavigationSearch.setClickable(true);
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





























    // TODO: 12.07.2022  метод получение данных от 1С для согласования
    public void metodGetDataOt1cCommitPay() {
        try {
            AsyncTaskLoader <JsonNode>    asyncTaskLoader=new AsyncTaskLoader<JsonNode>(context) {
                @Override
                protected void onStartLoading() {
                    super.onStartLoading();
                    try{
                        progressBarCommitPay.setVisibility(View.VISIBLE);
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        // TODO: 28.02.2022*/
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }

                @Nullable
                @Override
                public JsonNode loadInBackground() {
                    JsonNode jsonNode1сСогласования = null;
                    try{
                        //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С DEBUG
                        jsonNode1сСогласования =
                                new Class_Get_Json_1C(context ,getHiltCommintgPays)
                                        .МетодПингаИПОлучениеДанныхОт1сДляСогласования(context,ПубличныйidPay,objectMapper);


                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласования " +jsonNode1сСогласования);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return jsonNode1сСогласования;
                }
            };
            asyncTaskLoader.startLoading();
            asyncTaskLoader.forceLoad();
            asyncTaskLoader.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener<JsonNode>() {
                @Override
                public void onLoadComplete(@NonNull Loader<JsonNode> loader, @Nullable JsonNode jsonNode1сСогласования) {
                    try{

                        // TODO: 16.01.2024  результата при получение певого раза  данных
                        resultatFisrtПолучениеДанных((Serializable) jsonNode1сСогласования);


                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласования " +jsonNode1сСогласования);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }


            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


// TODO: 16.01.2024 FIRST

    private void resultatFisrtПолучениеДанных(Serializable jsonNode1сСогласования) {

        try{
        Intent intentCallBackAfter1cData=new Intent("CallBackAflerGetData1cCommitPay");
        Bundle bundle=new Bundle();
        bundle.putSerializable("jsonNode1сСогласования", jsonNode1сСогласования);
        intentCallBackAfter1cData.putExtras(bundle);
        jsonNodeMutableLiveDataPayCommintg.postValue(intentCallBackAfter1cData);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласования " +jsonNode1сСогласования);

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    // TODO: 16.01.2024 Second
    private void resultatSecondПолучениеДанных(Serializable jsonNode1сСогласования) {

        try{
            Intent intentCallBackAfter1cData=new Intent("SecondCallBackAflerGetData1c");
            Bundle bundle=new Bundle();
            bundle.putSerializable("jsonNode1сСогласования", jsonNode1сСогласования);
            intentCallBackAfter1cData.putExtras(bundle);
            jsonNodeMutableLiveDataPayCommintg.postValue(intentCallBackAfter1cData);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласования " +jsonNode1сСогласования);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    // TODO: 16.01.2024 Second Принудительная
    private void resultatSecondPrinyditelnayПолучениеДанных(Serializable jsonNode1сСогласования) {

        try{
            Intent intentCallBackAfter1cData=new Intent("PrinyditelnaySecondCallBackAflerGetData1c");
            Bundle bundle=new Bundle();
            bundle.putSerializable("jsonNode1сСогласования", jsonNode1сСогласования);
            intentCallBackAfter1cData.putExtras(bundle);
            jsonNodeMutableLiveDataPayCommintg.postValue(intentCallBackAfter1cData);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласования " +jsonNode1сСогласования);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    // TODO: 12.07.2022  метод получение данных от 1С для согласования
    public void startingAsyncAdvansed() {
        try {
            AsyncTaskLoader <JsonNode>    asyncTaskLoader=new AsyncTaskLoader<JsonNode>(context) {
                @Override
                protected void onStartLoading() {
                    super.onStartLoading();
                    try{
                        progressBarCommitPay.setVisibility(View.VISIBLE);
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        // TODO: 28.02.2022*/
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }

                @Nullable
                @Override
                public JsonNode loadInBackground() {
                    JsonNode jsonNode1сСогласования = null;
                    try{

                        //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С RELIS
                        jsonNode1сСогласования =
                                new Class_Get_Json_1C(context ,getHiltCommintgPays)
                                        .МетодПингаИПОлучениеДанныхОт1сДляСогласования(context,ПубличныйidPay,objectMapper);
/*
[{"Ndoc":"000021992","CFO":"База (Управление ул. Проездная, 18/27)","organization":"СОЮЗ АВТОДОР ООО","counterparty":"СИТИЛИНК ООО","sum":6,"articleDDS":"2.2.04. Оргтехника","nomenclature":[{"nomen":"Тест 1"},{"nomen":"Тест 2"},{"nomen":"Тест 3"}],"filenames":[{"ВinNameFile":"Текстовый документ","expansion":"txt"}]},
                            {"Ndoc":"000021993","CFO":"База (Управление ул. Проездная, 18/27)","organization":"СОЮЗ АВТОДОР ООО","counterparty":"ИП Пряслов Алексей Александрович","sum":50,"articleDDS":"2.2.05. Прочие (инвестиционная деятельность)","nomenclature":[{"nomen":"Тест картридж"}],"filenames":[{"ВinNameFile":"Справочник","expansion":"xlsx"}]}]*/


                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  jsonNode1сСогласования  " + jsonNode1сСогласования);




                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласования " +jsonNode1сСогласования);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return jsonNode1сСогласования;
                }
            };
            asyncTaskLoader.startLoading();
            asyncTaskLoader.forceLoad();
            asyncTaskLoader.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener<JsonNode>() {
                @Override
                public void onLoadComplete(@NonNull Loader<JsonNode> loader, @Nullable JsonNode jsonNode1сСогласования) {
                    try{

                        // TODO: 16.01.2024  результата при получение певого раза  данных  SECOND !!!!!!!

                        if (myRecycleViewAdapter!=null) {
                            resultatSecondПолучениеДанных((Serializable) jsonNode1сСогласования);


                            // TODO: 24.01.2024 перрерисовка дизайна
                            startAnimationsRecyreView();

                            // TODO: 16.01.2024  результата при получение певого раза  данных  SECOND Принудительная !!!!!!!
                        } else {

                            resultatSecondPrinyditelnayПолучениеДанных((Serializable) jsonNode1сСогласования);
                        }

                        successNavigatorButtonIconRow(jsonNode1сСогласования );

                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласования " +jsonNode1сСогласования);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }


            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    private void startAnimationsRecyreView() {
        // TODO: 24.01.2024
        try {
        Animation      animationscroll = AnimationUtils.loadAnimation(context, R.anim.slide_in_row_vibrator1);

        recycleviewcommitpays.startAnimation(animationscroll);

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    public void методЗакрываемКлавитатуру(@NonNull Activity activity) {
        try{
       Window w =activity.getWindow();

            if (w!=null) {
                w.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    public void методЗакрываемSearchView(@NonNull androidx.appcompat.widget.SearchView searchview_commitpay) {
        try{

            if (searchview_commitpay.getVisibility()==View.VISIBLE) {
                searchview_commitpay.clearFocus();
                searchview_commitpay.setQuery("",true);
                searchview_commitpay.cancelLongPress();

                searchview_commitpay.setVisibility(View.GONE);
                searchview_commitpay.requestLayout();
                searchview_commitpay.refreshDrawableState();
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    public void myRecycleViewAdapterReebotgetAdapter(@NonNull JsonNode jsonNode1сСогласованияAllRows) {
        try{
                if (myRecycleViewAdapter!=null) {
                    myRecycleViewAdapter.jsonNode1сСогласованияAfterSearchView=jsonNode1сСогласованияAllRows;
                    myRecycleViewAdapter.notifyDataSetChanged();
                    RecyclerView.Adapter recyclerViewAdapter=         recycleviewcommitpays.getAdapter();
                    recycleviewcommitpays.swapAdapter(recyclerViewAdapter,true);
                    recycleviewcommitpays.getAdapter().notifyDataSetChanged();


                    // TODO: 24.01.2024
                    recycleviewcommitpays.requestLayout();
                    recycleviewcommitpays .refreshDrawableState();

                }
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " jsonNode1сСогласованияAllRows " +jsonNode1сСогласованияAllRows);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }



    public void методClearRecyreView(  @NonNull RecyclerView recyclerViewСогласование1С) {

        try{
            if (recyclerViewСогласование1С!=null) {
                recyclerViewСогласование1С.getRecycledViewPool().clear();
                recyclerViewСогласование1С.removeAllViews();
            }
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " recyclerViewСогласование1С " +recyclerViewСогласование1С);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


    public void методRebootRecyreviewDontJsonNULL() {
        try{
            if (myRecycleViewIsNullAdapter!=null) {
                ArrayList<Boolean> arrayListIsNull1cData=new ArrayList<>();
                arrayListIsNull1cData.add(false);
                myRecycleViewIsNullAdapter.arrayListIsNull1cData=arrayListIsNull1cData;
                myRecycleViewIsNullAdapter.notifyDataSetChanged();
                RecyclerView.Adapter recyclerViewAdapter=         recycleviewcommitpays.getAdapter();
                recycleviewcommitpays.swapAdapter(recyclerViewAdapter,true);
                recycleviewcommitpays.getAdapter().notifyDataSetChanged();


            }
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " recyclerViewСогласование1С " + recycleviewcommitpays);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }







    public void metodSetNameCommitHeaders( ) {
        try{
            textViewТекущаяЗадача.setText("Согласования".toUpperCase());
            textViewТекущаяЗадача.startAnimation(animation);
            textViewТекущаяЗадача.requestLayout();
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



















































}