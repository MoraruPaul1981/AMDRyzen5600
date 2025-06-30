package com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.LiveData;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.InitRecyreviews.InizializayRecyreViewsNested;
import com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.RebootRecyreViewNested;
import com.dsy.dsu.CommitPricesApp.Model.LiveDataPrices.GetLiveDataForrecyreViewPrices;
import com.dsy.dsu.CommitPricesApp.View.MyRecycleView.MyViewHoldersCommintPrices;
import com.dsy.dsu.CommitPricesApp.View.MyRecycleViewNested.MyRecycleViewIsAdaptersNestedCommintPrices;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class CallBacksLiveDataNested {

 private Context context;





    private  JsonNode  jsonNodeNested;

    private MyRecycleViewIsAdaptersNestedCommintPrices myRecycleViewIsAdaptersNestedCommintPrices;
    private RecyclerView recycleview_nesters_comminingpprices;
    private  MyViewHoldersCommintPrices holderPrices;




    private  int postion;
    private ObjectMapper objectMapper;

    private Integer getHiltPublicId;
    private    String getHiltCommintgPrices;

    private GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices;

    private MutableLiveData<Intent> getHiltMutableLiveDataPay;

    private LifecycleOwner lifecycleOwner;
    public CallBacksLiveDataNested(@NotNull  MyViewHoldersCommintPrices holderPrices,
                                   @NotNull  Context context,
                                   @NotNull   RecyclerView  recycleview_nesters_comminingpprices,
                                   @NotNull JsonNode  jsonNodeNested,
                                   @NotNull int postion,
                                   @NotNull ObjectMapper objectMapper,
                                   @NotNull Integer getHiltPublicId,
                                   @NotNull   String getHiltCommintgPrices,
                                   @NotNull GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices,
                                   @NotNull MutableLiveData<Intent> getHiltMutableLiveDataPay,
                                   @NotNull LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.recycleview_nesters_comminingpprices = recycleview_nesters_comminingpprices;
        this.jsonNodeNested = jsonNodeNested;
        this.holderPrices = holderPrices;
        this.postion = postion;
        this.objectMapper = objectMapper;
        this.getHiltPublicId = getHiltPublicId;
        this.getHiltCommintgPrices = getHiltCommintgPrices;
        this. getLiveDataForrecyreViewPrices = getLiveDataForrecyreViewPrices;
        this. getHiltMutableLiveDataPay = getHiltMutableLiveDataPay;
        this. lifecycleOwner = lifecycleOwner;

        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+ " jsonNodeNested " + jsonNodeNested);

    }

    public  void callbackLiveDataNested( ) {
        Completable.complete().subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                // TODO: 25.12.2023
                // TODO: 26.12.2023  когда данные пришли от 1с согласования цен

                if (jsonNodeNested!=null && jsonNodeNested.size()>0){

                    // TODO: 28.12.2023 инизилащитция recyreview nesters
                    new InizializayRecyreViewsNested(recycleview_nesters_comminingpprices,context).startInitRecyreview();

                    // TODO: 28.12.2023 TODO: 28.12.2023 инизилащитция AdaptersNested
                    if (myRecycleViewIsAdaptersNestedCommintPrices ==null) {

                        setRecycleViewIsAdaptersNested(   );

                    }else{
                        // TODO: 26.12.2023 нет  байты
                        RebootRecyreViewNested rebootRecyreViewNested=new RebootRecyreViewNested(context);

                        rebootRecyreViewNested.методRebootRecyreViewComminPrices(jsonNodeNested,
                                myRecycleViewIsAdaptersNestedCommintPrices,
                                recycleview_nesters_comminingpprices);
                    }



                    Log.d(this.getClass().getName(),"\n"
                            + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+ " jsonNodeNested " + jsonNodeNested);
                }
            }

            @Override
            public void onComplete() {
                // TODO: 28.12.2023 prograsbar

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


    // TODO: 28.12.2023 метод запуска уже настоящегно rcyreview когда первый is null прошел ага

    public void setRecycleViewIsAdaptersNested(  ) {
        try {


                myRecycleViewIsAdaptersNestedCommintPrices = new MyRecycleViewIsAdaptersNestedCommintPrices(holderPrices.itemView, context
                        ,jsonNodeNested, postion,objectMapper,getHiltPublicId,getHiltCommintgPrices
                        ,getLiveDataForrecyreViewPrices,  getHiltMutableLiveDataPay,lifecycleOwner,recycleview_nesters_comminingpprices);
            // TODO: 30.01.2024
                myRecycleViewIsAdaptersNestedCommintPrices.notifyDataSetChanged();
                recycleview_nesters_comminingpprices.setAdapter(myRecycleViewIsAdaptersNestedCommintPrices);
            recycleview_nesters_comminingpprices.getAdapter().notifyDataSetChanged();
                

            // TODO: 10.01.2024  reebott dizayn recyreview nested

            rebbotdizaynRecyreViewNested();

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "myRecycleViewIsAdaptersNestedCommintPrices  "
                        + myRecycleViewIsAdaptersNestedCommintPrices + " jsonNodeNested " +jsonNodeNested);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }









    private void rebbotdizaynRecyreViewNested() {
        recycleview_nesters_comminingpprices.requestLayout();
        recycleview_nesters_comminingpprices.refreshDrawableState();
    }


}
