package com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView;

//TODO класс SerachView поски по Соглоаваниям

import android.app.Activity;
import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.PaysCommitApp.View.RecyreView.MyRecycleViewAdapterCommingPay;
import com.fasterxml.jackson.databind.JsonNode;

import org.jetbrains.annotations.NotNull;

class AdapterSerachViewPay{
    private     androidx.appcompat.widget.SearchView searchview_commitpay;

    Context context;
    Bl_CommintigPay bl_commintigPay;
    JsonNode jsonNode1сСогласованияAllRows;
    Activity activity;
    MyRecycleViewAdapterCommingPay myRecycleViewAdapter;
    RecyclerView recyclerViewСогласование1С;

    public AdapterSerachViewPay(@NotNull
                                androidx.appcompat.widget.SearchView searchview_commitpay,
                                @NotNull  Context context,
                                @NotNull JsonNode jsonNode1сСогласованияAllRows,
                                @NonNull Activity activity,
                                @NonNull MyRecycleViewAdapterCommingPay myRecycleViewAdapter,
                                @NonNull RecyclerView recyclerViewСогласование1С,
                                @NonNull Bl_CommintigPay bl_commintigPay) {
        this.searchview_commitpay = searchview_commitpay;
        this.context = context;
        this.jsonNode1сСогласованияAllRows = jsonNode1сСогласованияAllRows;
        this.activity = activity;
        this.recyclerViewСогласование1С = recyclerViewСогласование1С;
        this.myRecycleViewAdapter = myRecycleViewAdapter;
        this.bl_commintigPay = bl_commintigPay;

    }


    void  setAdapterSerachViewPay (){
        // TODO: 21.11.2023 Посик
        try{
            if(searchview_commitpay.isEnabled()){
                searchview_commitpay.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        try{
                        if (hasFocus==true) {
                            ((androidx.appcompat.widget.SearchView) v).setQueryHint("Поиск...");
                        }else {
                            ((androidx.appcompat.widget.SearchView) v).setQueryHint(null);

                         // TODO: 24.11.2023
                            bl_commintigPay.  методЗакрываемКлавитатуру(   activity);

                           // bl_commintigPay.методRebootDisaynRecyreViewFromSearchView(jsonNode1сСогласованияAllRows );


                        }
                        // TODO: 26.12.2022  конец основгого кода
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                " searchview_commitpay " + searchview_commitpay);
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
// TODO: 21.11.2023
                searchview_commitpay.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        try{
                            // TODO: 24.11.2023
                            Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                            v2.vibrate(VibrationEffect.createOneShot(60, VibrationEffect.EFFECT_HEAVY_CLICK));

                           SearchViewPayCommiting searchViewPayCommiting =
                                   new SearchViewPayCommiting( query,context,searchview_commitpay
                                           ,myRecycleViewAdapter,recyclerViewСогласование1С,bl_commintigPay);

                            searchViewPayCommiting.   registerBroadCastRexiver ( );

                            searchViewPayCommiting.startrunningSearchView(jsonNode1сСогласованияAllRows);



                            // TODO: 26.12.2022  конец основгого кода
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                    " searchview_commitpay " + searchview_commitpay);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(context.getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        try{
                            if (newText!=null && newText.length()>0) {
                                onQueryTextSubmit(newText);
                                // TODO: 26.12.2022  конец основгого кода
                                Log.d(this.getClass().getName(), "\n" + " class " +
                                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                        " searchview_commitpay " + searchview_commitpay);
                                return true;
                            }else {


                                // TODO: 26.12.2022 когда выходим изSerchView  и переопледеем Recyreview
                                AfterSerachingOverridRecyreView();
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                        " searchview_commitpay " + searchview_commitpay);
                                return false;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(context.getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return false;
                    }
                });

            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " searchview_commitpay " + searchview_commitpay);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }






    }//TODO end onSubscribe

    private void AfterSerachingOverridRecyreView() {
        try{
        if (jsonNode1сСогласованияAllRows.size()>0) {
            bl_commintigPay.myRecycleViewAdapterReebotgetAdapter(jsonNode1сСогласованияAllRows );
        } else {
            bl_commintigPay.методRebootRecyreviewDontJsonNULL();
        }

        bl_commintigPay.  методЗакрываемКлавитатуру(  activity);
        // TODO: 06.02.2024
        bl_commintigPay.successNavigatorButtonIconRow(jsonNode1сСогласованияAllRows );

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " searchview_commitpay " + searchview_commitpay);
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