package com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.LiveData.CallBacksLiveDataNested;
import com.dsy.dsu.CommitPricesApp.Model.LiveDataPrices.GetLiveDataForrecyreViewPrices;
import com.dsy.dsu.CommitPricesApp.View.MyRecycleView.MyRecycleViewIsAdaptersCommintPrices;
import com.dsy.dsu.CommitPricesApp.View.MyRecycleView.MyViewHoldersCommintPrices;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;

public  class setAndStartingRecyreViewNestedClickArrow {

    private   RecyclerView recycleview_nesters_comminingpprices;
    private    MaterialButton materialButton;
    private   ProgressBar  progressbar_comminingprices;
    private MyViewHoldersCommintPrices holderPrices;
    private  Context context;

    private  Integer position;
    private  Handler handlerProgbar;
    private   Handler handlerBut;
    private   ObjectMapper objectMapper;

    private  Integer getHiltPublicId;
     private  String getHiltCommintgPrices;

    private GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices;

    private  MutableLiveData<Intent> getHiltMutableLiveDataPay;
    private LifecycleOwner lifecycleOwner;

    private  MyRecycleViewIsAdaptersCommintPrices myRecycleViewIsAdaptersCommintPrices;


    public setAndStartingRecyreViewNestedClickArrow(@NonNull MaterialButton materialButton,
                                                    @NonNull MyViewHoldersCommintPrices holderPrices,
                                                    @NonNull  Context context,
                                                    @NonNull Integer position,
                                                    @NotNull ObjectMapper objectMapper,
                                                    @NotNull Integer getHiltPublicId,
                                                    @NotNull String getHiltCommintgPrices,
                                                    @NonNull GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices,
                                                    @NotNull MutableLiveData<Intent> getHiltMutableLiveDataPay,
                                                    @NonNull    LifecycleOwner lifecycleOwner,
                                                    @NotNull MyRecycleViewIsAdaptersCommintPrices myRecycleViewIsAdaptersCommintPrices)
    {
        try{
        this.materialButton = materialButton;
        this.holderPrices = holderPrices;
        this.context = context;
        this.position = position;
        this.objectMapper = objectMapper;
        this.getHiltPublicId = getHiltPublicId;
        this.getHiltCommintgPrices = getHiltCommintgPrices;
        this.getLiveDataForrecyreViewPrices = getLiveDataForrecyreViewPrices;
        this.getHiltMutableLiveDataPay = getHiltMutableLiveDataPay;
        this.lifecycleOwner = lifecycleOwner;

            // TODO: 19.03.2024 init
         recycleview_nesters_comminingpprices=
                    holderPrices.itemView.findViewById(R.id.recycleview_nesters_comminingpprices) ;
          progressbar_comminingprices= holderPrices.itemView.findViewById(R.id.progressbar_comminingprices) ;

            // TODO: 10.01.2024
            handlerBut=  materialButton.getHandler();
            handlerProgbar=  progressbar_comminingprices.getHandler();

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }


   public void startProccerotNested(){
        try{
            Completable.fromSupplier(new Supplier<Object>() {
                @Override
                public Object get() throws Throwable {
                    // TODO: 30.12.2023
                    handlerBut.post(new Runnable() {
                        @Override
                        public void run() {
                            try{
                            // TODO: 29.12.2023  включаем вложеный RecyreView
                                initNedsterRecyreView();
                                // TODO: 30.12.2023 запус каем ккод заполнения  NESTED
                                Log.d(this.getClass().getName(), "\n"
                                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }

                        }



                    });

                    return handlerBut;
                }







                    })
                    .subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                            // TODO: 30.12.20
                            Log.d(this.getClass().getName(),"\n"
                                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }

                        @Override
                        public void onComplete() {
                            // TODO: 30.12.2023
                            // TODO: 29.12.2023
                            handlerProgbar.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressbar_comminingprices.setVisibility(View.GONE);
                                }
                            },1000);

                            Log.d(this.getClass().getName(),"\n"
                                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e
                                    + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    });

            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    private void initNedsterRecyreView() {
        try{
            CallBacksLiveDataNested callBacksLiveDataNested =
                    new CallBacksLiveDataNested(holderPrices, context, recycleview_nesters_comminingpprices,
                            holderPrices.jsonNode, position,objectMapper,
                            getHiltPublicId,getHiltCommintgPrices,
                            getLiveDataForrecyreViewPrices,
                            getHiltMutableLiveDataPay,
                            lifecycleOwner);

            // TODO: 30.12.2023 запукскаем
            callBacksLiveDataNested.callbackLiveDataNested();

            Log.d(this.getClass().getName(), "\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }







    public  Integer chnageStatusArrowData(){
        try{

            handlerBut.post(new Runnable() {
                @Override
                public void run() {
                    try{

                    if (recycleview_nesters_comminingpprices.getVisibility()==View.VISIBLE) {
                        // TODO: 29.12.2023
                        Drawable drawabledown=context.getDrawable(R.drawable.icon_for_commingpricesdown2);
                        materialButton.setIcon(drawabledown);
                        // TODO: 29.12.2023

                        recycleview_nesters_comminingpprices.setVisibility(View.GONE);
                        // TODO: 29.12.2023
                        progressbar_comminingprices.setVisibility(View.GONE);




                        Log.d(this.getClass().getName(),"\n"
                                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    } else {

                        Drawable drawableup=context.getDrawable(R.drawable.icon_for_commingpricesup);
                        materialButton.setIcon(drawableup);

                        recycleview_nesters_comminingpprices.setVisibility(View.VISIBLE);
                        // TODO: 29.12.2023
                        progressbar_comminingprices.setVisibility(View.VISIBLE);


                        // TODO: 30.12.2023  метод смой загрузки данных
                      startProccerotNested();




                        // TODO: 29.12.2023
                        Log.d(this.getClass().getName(),"\n"
                                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    // TODO: 29.12.2023
                    materialButton.requestLayout();
                    materialButton.refreshDrawableState();
                    // TODO: 29.12.2023
                    recycleview_nesters_comminingpprices.requestLayout();
                    recycleview_nesters_comminingpprices.refreshDrawableState();


                    progressbar_comminingprices.requestLayout();
                    progressbar_comminingprices.refreshDrawableState();


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }
            } );

            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return 0;
    }

}
