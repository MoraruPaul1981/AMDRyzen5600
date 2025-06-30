package com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.LiveData.CallBacksLiveData;
import com.dsy.dsu.CommitPricesApp.Model.LiveDataPrices.GetLiveDataForrecyreViewPrices;
import com.dsy.dsu.CommitPricesApp.View.MyRecycleViewIsNull.MyRecycleViewIsNullAdaptersCommintPrices;
import com.dsy.dsu.CommitPricesApp.ViewModel.ModelComminingPrisesByte;
import com.dsy.dsu.CommitPricesApp.ViewModel.ModelComminingPrisesString;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import javax.inject.Inject;


public class BLFragmentCommintingPrices {

  private   Activity activity;
    private  Context context;
    private  ObjectMapper objectMapper;
    private ModelComminingPrisesString modelComminingPrisesString;
    private ModelComminingPrisesByte modelComminingPrisesByte;

    private MyRecycleViewIsNullAdaptersCommintPrices myRecycleViewIsNullAdaptersCommintPrices;


    private RecyclerView recycleview_comminingpprices;

    private ProgressBar prograessbar_commintingprices ;
    private  LifecycleOwner lifecycleOwner;

    private androidx.appcompat.widget.SearchView searchview_commintingprices;

    private  EventsBackAndAsyncAndSearchCommintPrices eventsBackAndAsyncAndSearchCommintPrices;

    private  String getHiltCommintgPrices;
    private  LiveData<Bundle>     liveData1;
    private  LiveData<Bundle>     liveData2;
    private Integer getHiltPublicId;
    private GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices;
    private MutableLiveData<Intent> getHiltMutableLiveDataPay;

    BottomNavigationItemView bottomNavigationSearch;
    public @Inject BLFragmentCommintingPrices(@NotNull  Activity activity,
                                              @NotNull  Context context,
                                              @NotNull   ObjectMapper objectMapper,
                                              @NotNull   ModelComminingPrisesString modelComminingPrisesString,
                                              @NotNull    ModelComminingPrisesByte modelComminingPrisesByte,
                                              @NotNull   RecyclerView recycleview_comminingpprices,
                                              @NotNull    ProgressBar prograessbar_commintingprices,
                                              @NotNull  LifecycleOwner lifecycleOwner ,
                                              @NotNull androidx.appcompat.widget.SearchView searchview_commintingprices,
                                              @NotNull   EventsBackAndAsyncAndSearchCommintPrices eventsBackAndAsyncAndSearchCommintPrices,
                                              @NotNull Integer getHiltPublicId,
                                              @NotNull String getHiltCommintgPrices,
                                              @NotNull GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices,
                                              @NotNull MutableLiveData<Intent> getHiltMutableLiveDataPay,
                                              @NotNull BottomNavigationItemView bottomNavigationSearch) {
        this.activity = activity;
        this.context = context;
        this.objectMapper = objectMapper;
        this.modelComminingPrisesString = modelComminingPrisesString;
        this.modelComminingPrisesByte = modelComminingPrisesByte;
        this.recycleview_comminingpprices = recycleview_comminingpprices;
        this.prograessbar_commintingprices = prograessbar_commintingprices;
        this. lifecycleOwner = lifecycleOwner;
        this. searchview_commintingprices = searchview_commintingprices;
        this. eventsBackAndAsyncAndSearchCommintPrices = eventsBackAndAsyncAndSearchCommintPrices;
        this. getHiltPublicId = getHiltPublicId;
        this. getHiltCommintgPrices = getHiltCommintgPrices;
        this. getLiveDataForrecyreViewPrices = getLiveDataForrecyreViewPrices;
        this. getHiltMutableLiveDataPay = getHiltMutableLiveDataPay;
        this. bottomNavigationSearch = bottomNavigationSearch;
    }

    // TODO: 26.12.2023 получение данных в виде String
 



    // TODO: 26.12.2023 получение данных в виде String
 
    public void getmodelByte() {
        try{

            // TODO: 25.12.2023  предварительный код  получение данныз от 1с
            // TODO: 25.12.2023 запуска callback
             getLiveDataCallBacks(lifecycleOwner);






            // TODO: 25.12.2023  запускаем получение Данных RELIS RELIS

            modelComminingPrisesByte.livedatastartSetJsonByte(getHiltCommintgPrices,objectMapper,prograessbar_commintingprices );

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




    public void getLiveDataCallBacks(@NotNull  LifecycleOwner lifecycleOwner){
        try{
                liveData1 = modelComminingPrisesByte.livedatastartGetJsonByte();
            // TODO: 30.12.2023 has тогда выключаем обсервер
            if (! liveData1.hasObservers()) {
                Log.d(this.getClass().getName(),"\n"
                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() +  "  liveData1.hasObservers()  " + liveData1.hasObservers());

                liveData1.observe(lifecycleOwner, new Observer<Bundle>() {
                    @Override
                    public void onChanged(Bundle bundle) {
                        try{

                            new CallBacksLiveData(context,prograessbar_commintingprices,
                                    recycleview_comminingpprices, myRecycleViewIsNullAdaptersCommintPrices,
                                    objectMapper,   eventsBackAndAsyncAndSearchCommintPrices,
                                    getHiltPublicId,getHiltCommintgPrices,getLiveDataForrecyreViewPrices
                                    ,getHiltMutableLiveDataPay,lifecycleOwner,bottomNavigationSearch).callbackLiveData(bundle);


                            // TODO: 09.01.2024

                            prograessbar_commintingprices.setVisibility(View.GONE);

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


                });
            }
            // TODO: 30.12.2023
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


    public void getLiveDataCallBacksEventBus(@NotNull  LifecycleOwner lifecycleOwner){
        try{
            liveData2 = modelComminingPrisesByte.livedatastartGetJsonByteEventBus();
            // TODO: 30.12.2023 has тогда выключаем обсервер
            if (! liveData2.hasObservers()) {
                Log.d(this.getClass().getName(),"\n"
                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() +  "  liveData1.hasObservers()  " + liveData1.hasObservers());

                liveData2.observe(lifecycleOwner, new Observer<Bundle>() {
                    @Override
                    public void onChanged(Bundle bundle) {
                        try{

                            JsonNode     jsonNode1сСогласованиеЦен=    new GeneratorBundleForJsonNode().
                                    generatorBunbleForJsonNode(bundle,context,objectMapper);

                            Log.d(this.getClass().getName(),"\n"
                                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                                    " jsonNode1сСогласованиеЦен " +jsonNode1сСогласованиеЦен);

// TODO: 24.01.2024 Update Screeen

                            new CallBacksLiveData(context,prograessbar_commintingprices,
                                    recycleview_comminingpprices, myRecycleViewIsNullAdaptersCommintPrices,
                                    objectMapper,   eventsBackAndAsyncAndSearchCommintPrices,getHiltPublicId,
                                    getHiltCommintgPrices,getLiveDataForrecyreViewPrices,getHiltMutableLiveDataPay
                                    ,lifecycleOwner,bottomNavigationSearch)
                                    .completeRecyreView(jsonNode1сСогласованиеЦен);


                            // TODO: 09.01.2024

                            prograessbar_commintingprices.setVisibility(View.GONE);

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
                });
            }
            // TODO: 30.12.2023
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }




// TODO: 26.12.2023 download one NULL data from 1c  Согласование Цен

 
    public void startIsNullRecyreView( ) {
        try {
            if (myRecycleViewIsNullAdaptersCommintPrices ==null) {
                ArrayList<Boolean> arrayListIsNull1cData = new ArrayList<>();
                arrayListIsNull1cData.add(true);
                myRecycleViewIsNullAdaptersCommintPrices = new MyRecycleViewIsNullAdaptersCommintPrices(arrayListIsNull1cData, context  );
                myRecycleViewIsNullAdaptersCommintPrices.notifyDataSetChanged();
                recycleview_comminingpprices.setAdapter(myRecycleViewIsNullAdaptersCommintPrices);
                recycleview_comminingpprices.getAdapter().notifyDataSetChanged();
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "myRecycleViewIsNullAdaptersCommintPrices  "
                        + myRecycleViewIsNullAdaptersCommintPrices);
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



    public void getSearchView( ) {
        try {

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "myRecycleViewIsNullAdaptersCommintPrices  "
                        + myRecycleViewIsNullAdaptersCommintPrices);


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


 

}///TODO  class BiznesLogicainnerFragment