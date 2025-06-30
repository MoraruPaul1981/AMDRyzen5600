package com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView.LiveData;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView.Bl_CommintigPay;
import com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView.ProccesingCancelOrOKPay;
import com.dsy.dsu.PaysCommitApp.View.RecyreView.MyRecycleViewAdapterCommingPay;
import com.dsy.dsu.PaysCommitApp.View.RecyreView.MyViewHolderPayCommingPay;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForApprove;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.android.material.card.MaterialCardView;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@QualifierGetLiveDataForrecyreViewPay
@Module
@InstallIn(SingletonComponent.class)
public class GetLiveDataForrecyreViewPay {

    public @Inject GetLiveDataForrecyreViewPay() {
    }

     public void setObservableLiveDataRecyreViewPays(@NonNull  LifecycleOwner lifecycleOwner ,
                                                     @NonNull Context context,
                                                     @NonNull  MutableLiveData<Intent> getHiltMutableLiveDataPayForRecyreViewPays,
                                                     @NonNull MyRecycleViewAdapterCommingPay myRecycleViewAdapterCommingPay,
                                                     @NonNull Bl_CommintigPay bl_commintigPay,
                                                     @NonNull JsonNode jsonNode1сСогласования,
                                                     @NonNull MyViewHolderPayCommingPay holder,
                                                     @NonNull MaterialCardView cardview_commingpay,
                                                     @NonNull int position,
                                                     @NonNull RecyclerView recycleviewcommitpays,
                                                     @NonNull  Intent intentзаданиеНаВыполениеSuccess,
                                                     @NonNull String getHiltCommintgPays,
                                                     @NonNull ServiceForApprove.LocalBinderДляСогласования binderСогласования1C,
                                                     @NonNull    androidx.appcompat.widget.SearchView searchview_commitpay,
                                                     @NonNull Activity activity,
                                                     @NonNull   Integer positionDeleteJsonNodeAll,
                                                     @NonNull Integer positionDeleteJsonNodeSeachView) {
        try{
            if (!getHiltMutableLiveDataPayForRecyreViewPays.hasObservers()) {
                // TODO: 16.01.2024
                getHiltMutableLiveDataPayForRecyreViewPays.observe(lifecycleOwner, new Observer<Intent>() {
                    @Override
                    public void onChanged(Intent intent) {
                        try{
                            switch (intent.getAction())  {
                                case           "CallBackRecyreViewPays":
                                    // TODO: 16.01.2024  пришли данные


                                    ProccesingCancelOrOKPay proccesingCancelOrOKPay = new ProccesingCancelOrOKPay(context,binderСогласования1C);

                                    StringBuffer ОТветОт1СОперациисДаннымиSuccees=
                                            proccesingCancelOrOKPay.proccerCancelOrOKPay(context,  intentзаданиеНаВыполениеSuccess,getHiltCommintgPays);

                                    ////StringBuffer ОТветОт1СОперациисДаннымиSuccees=new StringBuffer("Операция успешна");


// TODO: 30.01.2024  метод ответа от 1с pay согласовано или нет
                                    callBackOt1cPays( ОТветОт1СОперациисДаннымиSuccees,
                                            myRecycleViewAdapterCommingPay,
                                            holder,
                                            position,
                                            context,
                                            bl_commintigPay,
                                            searchview_commitpay,
                                            activity,
                                            positionDeleteJsonNodeAll,
                                            positionDeleteJsonNodeSeachView);

                                    // TODO: 07.02.2024  выключаем
                                    getHiltMutableLiveDataPayForRecyreViewPays.removeObservers(lifecycleOwner);

                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                            + "\n"+ " recycleviewcommitpays "+recycleviewcommitpays);
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

    @SuppressLint("SuspiciousIndentation")
    void callBackOt1cPays(@NonNull StringBuffer ОТветОт1СОперациисДанными,
                          @NonNull MyRecycleViewAdapterCommingPay myRecycleViewAdapterCommingPay,
                          @NonNull MyViewHolderPayCommingPay holder,
                          @NonNull int position,
                          @NonNull Context context,
                          @NonNull Bl_CommintigPay bl_commintigPay,
                          @NonNull    androidx.appcompat.widget.SearchView searchview_commitpay,
                          @NonNull Activity activity,
                          @NonNull Integer positionDeleteJsonNodeAll,
                          @NonNull Integer positionDeleteJsonNodeSeachView) {

        try {


        if (ОТветОт1СОперациисДанными.toString().trim().matches("(.*)Операция успешна(.*)")) {
            // TODO: 23.01.2024  удаление строчки

            // TODO: 23.01.2024  notify переоплеедения даных
            myRecycleViewAdapterCommingPay. notifynotifyDataSetChanged(  holder,positionDeleteJsonNodeSeachView );
            JsonNode  jsonNode1сСогласованияПослеУдалениеиПосика;


            if (searchview_commitpay.getVisibility()== View.VISIBLE && searchview_commitpay.getQuery().toString().length()>0 ) {
                // TODO: 23.01.2024  удаление строки из Json Row
              jsonNode1сСогласованияПослеУдалениеиПосика=  myRecycleViewAdapterCommingPay. remoteSingleJsonSeravhView(   positionDeleteJsonNodeSeachView );
                myRecycleViewAdapterCommingPay. remoteSingleJsonFromAll(   positionDeleteJsonNodeAll );
            }else {
                // TODO: 23.01.2024  удаление строки из Json Row
                 jsonNode1сСогласованияПослеУдалениеиПосика=  myRecycleViewAdapterCommingPay. remoteSingleJsonFromAll(   positionDeleteJsonNodeAll );
            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    +" jsonNode1сСогласованияПослеУдалениеиПосика " +jsonNode1сСогласованияПослеУдалениеиПосика);









            // TODO: 24.01.2024  tod УДАЛЕНИЕ Переопределяем RecyreView
            chengeDatasInertRecyreVieAfterCommitng(bl_commintigPay, searchview_commitpay, activity, jsonNode1сСогласованияПослеУдалениеиПосика,context);


            // TODO: 16.01.2024 перегрузка дизайна
            bl_commintigPay.   rebootDizaynRecyreView();



            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ОТветОт1СОперациисДанными " +ОТветОт1СОперациисДанными+
                    " jsonNode1сСогласованияПослеУдалениеиПосика " +jsonNode1сСогласованияПослеУдалениеиПосика);

        }else {
            Toast.makeText(context, "Операция  не прошла !!! ", Toast.LENGTH_SHORT).show();
            Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));

        }

        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                +" ОТветОт1СОперациисДанными " +ОТветОт1СОперациисДанными);

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

    private   void chengeDatasInertRecyreVieAfterCommitng(@NonNull Bl_CommintigPay bl_commintigPay,
                                                          @NonNull SearchView searchview_commitpay,
                                                          @NonNull Activity activity,
                                                          @NonNull JsonNode jsonNode1сСогласованияПослеУдалениеиПосика,
                                                          @NonNull Context context) {
        try{
        if (jsonNode1сСогласованияПослеУдалениеиПосика.size()>0) {
            // TODO: 07.02.2024  закрываем клавиатуру
            bl_commintigPay.методЗакрываемКлавитатуру(activity);

            // TODO: 24.01.2024 переодредляем количество строчек внизу
            bl_commintigPay.successNavigatorButtonIconRow(jsonNode1сСогласованияПослеУдалениеиПосика);
            // TODO: 25.03.2024 переропределяем recyreView
            bl_commintigPay.myRecycleViewAdapterReebotgetAdapter(jsonNode1сСогласованияПослеУдалениеиПосика);

        }else {
            bl_commintigPay. методЗакрываемSearchView(searchview_commitpay);

            // TODO: 24.01.2024 переодредляем количество строчек внизу
            bl_commintigPay.navigatorDontbuttonIconRow(jsonNode1сСогласованияПослеУдалениеиПосика);

            bl_commintigPay.InitMyAdapterRecyreViewIsNull(false);
           // bl_commintigPay.методRebootRecyreviewDontJsonNULL();

        }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    +" jsonNode1сСогласованияПослеУдалениеиПосика.size() " +jsonNode1сСогласованияПослеУдалениеиПосика.size());

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
