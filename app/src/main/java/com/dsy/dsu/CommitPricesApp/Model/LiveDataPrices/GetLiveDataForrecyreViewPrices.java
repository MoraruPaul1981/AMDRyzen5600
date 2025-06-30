package com.dsy.dsu.CommitPricesApp.Model.LiveDataPrices;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitPricesApp.Model.SendDataTo1C.CommintPricesSendJsonTo1C;
import com.dsy.dsu.CommitPricesApp.Model.SendDataTo1C.ProcceroingResultatOtveta1CPost;
import com.dsy.dsu.CommitPricesApp.View.MyRecycleViewNested.MyRecycleViewIsAdaptersNestedCommintPrices;
import com.dsy.dsu.CommitPricesApp.View.MyRecycleViewNested.MyViewHoldersNestedCommintPrices;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@QualifierGetLiveDataForrecyreViewPrices
@Module
@InstallIn(SingletonComponent.class)
public class GetLiveDataForrecyreViewPrices {

    public @Inject GetLiveDataForrecyreViewPrices() {
        System.out.println("GetLiveDataForrecyreViewPrices");
    }

    public void setObservableLiveDataRecyreViewCommitPrices(@NonNull  LifecycleOwner lifecycleOwner ,
                                                            @NonNull Context context,
                                                            @NonNull  MutableLiveData<Intent> getHilLiveDataRecyreVirewCommitPrices,
                                                            @NonNull     byte[] ByteFor1CCommintPrices,
                                                            @NonNull Integer getHiltPublicId,
                                                            @NonNull String getHiltCommintgPrices,
                                                            @NonNull    String UUID,
                                                            @NonNull MaterialTextView mTV_commitingprices_count,
                                                            @NonNull MyRecycleViewIsAdaptersNestedCommintPrices myRecycleViewIsAdaptersNestedCommintPrices,
                                                            @NonNull  int position,
                                                            @NonNull MaterialCardView cardview_commingprices_neasted,
                                                            @NonNull JsonNode jsonNodeNested,
                                                            @NonNull MyViewHoldersNestedCommintPrices holderNested,
                                                            @NonNull RecyclerView   recycleview_comminingppricesNesteds) {
        try{
            if (!getHilLiveDataRecyreVirewCommitPrices.hasObservers()) {
                // TODO: 16.01.2024
                getHilLiveDataRecyreVirewCommitPrices.observe(lifecycleOwner, new Observer<Intent>() {
                    @Override
                    public void onChanged(Intent intent) {
                        try{
                            switch (intent.getAction())  {
                                case           "CallBackRecyreViewPrices":
                                    // TODO: 16.01.2024  пришли данные


                                    // TODO: 10.01.2024 Отправка данных Price на сервер 1с
                                    CommintPricesSendJsonTo1C generatorJsonForPostComminhgPrices=new CommintPricesSendJsonTo1C();

                                    // TODO: 30.01.2024 Отправляем задаени на 1с
                                    StringBuffer  BufferOt1cCommintPricePost=   generatorJsonForPostComminhgPrices.SendJsonForPostComminhgPrices(context,
                                            ByteFor1CCommintPrices,getHiltPublicId,getHiltCommintgPrices, UUID);

                          /*   StringBuffer  BufferOt1cCommintPricePost=new StringBuffer("Согласование внесено в базу!");*/


                                    // TODO: 10.01.2024 Скрываем Текущий Платеж По Которому был Клик http://192.168.254.218/dds_copy/hs/jsonto1ccena/listofdocuments
                                    // TODO: 11.01.2024 терперь третьй вариант пользователюю  прячем указвнный текущий Плитку с соглдосваниием
                                    ProcceroingResultatOtveta1CPost procceroingResultatOtveta1CPost=new ProcceroingResultatOtveta1CPost(context);
                                    procceroingResultatOtveta1CPost.startingResultatOtveta1CPost(  BufferOt1cCommintPricePost,
                                            mTV_commitingprices_count,
                                            myRecycleViewIsAdaptersNestedCommintPrices,
                                            position
                                            ,cardview_commingprices_neasted,jsonNodeNested,holderNested,
                                            recycleview_comminingppricesNesteds);


                                    // TODO: 07.02.2024 выключаем обзервер
                                    getHilLiveDataRecyreVirewCommitPrices.removeObservers(lifecycleOwner);

                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    break;

                            }

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
                });


            }

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




}
