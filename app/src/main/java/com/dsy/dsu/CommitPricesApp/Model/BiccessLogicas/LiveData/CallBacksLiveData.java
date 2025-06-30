package com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.LiveData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.EventsBackAndAsyncAndSearchCommintPrices;
import com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.GeneratorBundleForJsonNode;
import com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.InitRecyreviews.InizializayRecyreViews;
import com.dsy.dsu.CommitPricesApp.Model.LiveDataPrices.GetLiveDataForrecyreViewPrices;
import com.dsy.dsu.CommitPricesApp.View.MyRecycleView.MyRecycleViewIsAdaptersCommintPrices;
import com.dsy.dsu.CommitPricesApp.View.MyRecycleViewIsNull.MyRecycleViewIsNullAdaptersCommintPrices;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class CallBacksLiveData {

 private Context context;
    private ProgressBar prograessbar_commintingprices ;
    private RecyclerView recycleview_comminingpprices;

    private MyRecycleViewIsNullAdaptersCommintPrices myRecycleViewIsNullAdaptersCommintPrices;
    private MyRecycleViewIsAdaptersCommintPrices myRecycleViewIsAdaptersCommintPrices;
    private  ObjectMapper objectMapper;
    private EventsBackAndAsyncAndSearchCommintPrices eventsBackAndAsyncAndSearchCommintPrices;

    private Integer getHiltPublicId;
    private  String getHiltCommintgPrices;
    GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices;
    MutableLiveData<Intent> getHiltMutableLiveDataPay;
    private LifecycleOwner lifecycleOwner;
   public BottomNavigationItemView bottomNavigationSearch;
    public CallBacksLiveData( @NotNull  Context context,
                              @NotNull   ProgressBar prograessbar_commintingprices,
                              @NotNull   RecyclerView  recycleview_comminingpprices,
                              @NotNull MyRecycleViewIsNullAdaptersCommintPrices myRecycleViewIsNullAdaptersCommintPrices,
                              @NotNull ObjectMapper objectMapper,
                              @NotNull      EventsBackAndAsyncAndSearchCommintPrices eventsBackAndAsyncAndSearchCommintPrices,
                              @NotNull Integer getHiltPublicId,
                              @NotNull String getHiltCommintgPrices,
                              @NotNull GetLiveDataForrecyreViewPrices  getLiveDataForrecyreViewPrices,
                              @NotNull MutableLiveData<Intent> getHiltMutableLiveDataPay ,
                              @NotNull  LifecycleOwner lifecycleOwner,
                              @NotNull  BottomNavigationItemView bottomNavigationSearch) {

        this.context = context;
        this.prograessbar_commintingprices = prograessbar_commintingprices;
        this.recycleview_comminingpprices = recycleview_comminingpprices;
        this.myRecycleViewIsNullAdaptersCommintPrices = myRecycleViewIsNullAdaptersCommintPrices;
        this.objectMapper = objectMapper;
        this.eventsBackAndAsyncAndSearchCommintPrices = eventsBackAndAsyncAndSearchCommintPrices;
        this.getHiltPublicId = getHiltPublicId;
        this.getHiltCommintgPrices = getHiltCommintgPrices;
        this.getLiveDataForrecyreViewPrices = getLiveDataForrecyreViewPrices;
        this.getHiltMutableLiveDataPay = getHiltMutableLiveDataPay;
        this.lifecycleOwner = lifecycleOwner;
        this.bottomNavigationSearch = bottomNavigationSearch;
    }

    public  void callbackLiveData(Bundle bundle) {
        Completable.complete().subscribe(new CompletableObserver() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                // TODO: 25.12.2023
                Bundle validadress=   Objects.requireNonNullElse(bundle,new Bundle());

               // TODO: 25.12.2023  пришел ответ в livedata от VieModel


                JsonNode     jsonNode1сСогласованиеЦен=    new GeneratorBundleForJsonNode().
                        generatorBunbleForJsonNode(validadress,context,objectMapper);

                // TODO: 26.12.2023  когда данные пришли от 1с согласования цен
                if (jsonNode1сСогласованиеЦен!=null && jsonNode1сСогласованиеЦен.size()>0){

                    // TODO: 26.12.2023 пришли байты  из байт в обьект  json node

                    if (jsonNode1сСогласованиеЦен.isArray()&& jsonNode1сСогласованиеЦен.size()>0) {


                        // TODO: 28.12.2023 инизилащитция recyreview

                        new InizializayRecyreViews(recycleview_comminingpprices,context).startInitRecyreview();


                        // TODO: 28.12.2023 Запускам настрощий recyreview при получение ииз байт обьект JsonNode
                        startGetRecyreView( jsonNode1сСогласованиеЦен );



// TODO: 30.12.2023 запускаем первоночальную оценку количество записей
                        eventsBackAndAsyncAndSearchCommintPrices.new EventsAsync().eventsSearchsetNumber(jsonNode1сСогласованиеЦен.size());

                      // TODO: 31.01.2024 делаем SearchView активиным
                         changeVisibleEnableSeachView();

                        // TODO: 26.12.2023 На текущего пользователя нет данных  !!!!!
                    } else {
                        // TODO: 26.12.2023 На текущего пользователя нет данных  !!!!!
                        completeIsNullRecyreView();

                        Log.d(this.getClass().getName(),"\n"
                                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+ " jsonNode1сСогласованиеЦен " + jsonNode1сСогласованиеЦен);
                    }


                    Log.d(this.getClass().getName(),"\n"
                            + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+ " jsonNode1сСогласованиеЦен " + jsonNode1сСогласованиеЦен);
                }else {

                    // TODO: 26.12.2023 нет  байты
                    completeIsNullRecyreView();

                    Log.d(this.getClass().getName(),"\n"
                            + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+ " jsonNode1сСогласованиеЦен " + jsonNode1сСогласованиеЦен);
                }
            }

            @Override
            public void onComplete() {
                // TODO: 28.12.2023 prograsbar

                dontvissiblePrograssBar();

                Log.d(this.getClass().getName(),"\n"
                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        });
    }


    // TODO: 31.01.2024 длаем SearchView botton активным
    @SuppressLint("RestrictedApi")
    private void changeVisibleEnableSeachView() {
        try {
        bottomNavigationSearch.setEnabled(true);
        bottomNavigationSearch.setClickable(true);
        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
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

    public void completeIsNullRecyreView() {
        try{
            if (myRecycleViewIsNullAdaptersCommintPrices !=null) {
                ArrayList<Boolean> arrayListIsNull1cData=new ArrayList<>();
                arrayListIsNull1cData.add(false);
                myRecycleViewIsNullAdaptersCommintPrices.arrayListIsNull1cData=arrayListIsNull1cData;
                myRecycleViewIsNullAdaptersCommintPrices.notifyDataSetChanged();
                RecyclerView.Adapter recyclerViewAdapter=         recycleview_comminingpprices.getAdapter();
                recycleview_comminingpprices.swapAdapter(recyclerViewAdapter,true);
                recycleview_comminingpprices.getAdapter().notifyDataSetChanged();

                // TODO: 09.01.2024
                recyreViewReboot();
            }

            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " myRecycleViewIsNullAdaptersCommintPrices " + myRecycleViewIsNullAdaptersCommintPrices);
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
    public void dontvissiblePrograssBar() {
        // TODO: 27.12.2023
        try{
            prograessbar_commintingprices.setVisibility(View.INVISIBLE);
            prograessbar_commintingprices.requestLayout();
            prograessbar_commintingprices.refreshDrawableState();

            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " myRecycleViewIsNullAdaptersCommintPrices " + myRecycleViewIsNullAdaptersCommintPrices);
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


    // TODO: 28.12.2023 метод запуска уже настоящегно rcyreview когда первый is null прошел ага

    public void startGetRecyreView( JsonNode     jsonNode1сСогласованиеЦен ) {
        try {
            if (myRecycleViewIsAdaptersCommintPrices ==null) {
                myRecycleViewIsAdaptersCommintPrices =
                        new MyRecycleViewIsAdaptersCommintPrices(jsonNode1сСогласованиеЦен, 
                                context,objectMapper 
                                ,getHiltPublicId,
                                getHiltCommintgPrices,getLiveDataForrecyreViewPrices,getHiltMutableLiveDataPay ,
                                lifecycleOwner);
                // TODO: 30.01.2024
                myRecycleViewIsAdaptersCommintPrices.notifyDataSetChanged();
                recycleview_comminingpprices.setAdapter(myRecycleViewIsAdaptersCommintPrices);
                recycleview_comminingpprices.getAdapter().notifyDataSetChanged();
                // TODO: 09.01.2024
                recyreViewReboot();

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "myRecycleViewIsAdapters  "
                        + myRecycleViewIsAdaptersCommintPrices + " jsonNode1сСогласованиеЦен " +jsonNode1сСогласованиеЦен);
            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"recycleview_comminingpprices  "
                    + recycleview_comminingpprices);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void recyreViewReboot() {
        recycleview_comminingpprices.requestLayout();
        recycleview_comminingpprices.refreshDrawableState();
    }


    public void completeRecyreView(@NotNull  JsonNode     jsonNode1сСогласованиеЦен) {
        try{
            if (myRecycleViewIsAdaptersCommintPrices !=null) {
                myRecycleViewIsAdaptersCommintPrices.jsonNodeParent=jsonNode1сСогласованиеЦен;
                myRecycleViewIsAdaptersCommintPrices.notifyDataSetChanged();
                RecyclerView.Adapter recyclerViewAdapter=         recycleview_comminingpprices.getAdapter();
                recycleview_comminingpprices.swapAdapter(recyclerViewAdapter,true);
                recycleview_comminingpprices.getAdapter().notifyDataSetChanged();

                // TODO: 09.01.2024
                recyreViewReboot();
            }

            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " jsonNode1сСогласованиеЦен " +jsonNode1сСогласованиеЦен);
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





}
