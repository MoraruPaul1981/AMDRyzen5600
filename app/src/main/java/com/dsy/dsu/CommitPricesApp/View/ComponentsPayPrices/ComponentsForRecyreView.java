package com.dsy.dsu.CommitPricesApp.View.ComponentsPayPrices;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.setAndStartingRecyreViewNestedClickArrow;
import com.dsy.dsu.CommitPricesApp.Model.LiveDataPrices.GetLiveDataForrecyreViewPrices;
import com.dsy.dsu.CommitPricesApp.View.MyRecycleView.MyRecycleViewIsAdaptersCommintPrices;
import com.dsy.dsu.CommitPricesApp.View.MyRecycleView.MyViewHoldersCommintPrices;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.jakewharton.rxbinding4.view.RxView;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import kotlin.Unit;

public class ComponentsForRecyreView {

    private MyViewHoldersCommintPrices holderPrices;
    private Context context;

    private  int position;
    private MaterialTextView mTV_commitingprices_value,mTV_NumberDOc_value;
    private  MaterialButton  arrow_nested_receriview;
    private  ObjectMapper objectMapper;
    private Integer getHiltPublicId;
    private  String getHiltCommintgPrices;
    private  GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices;
    private  MutableLiveData<Intent> getHiltMutableLiveDataPay;
    private LifecycleOwner lifecycleOwner;
    private  MyRecycleViewIsAdaptersCommintPrices myRecycleViewIsAdaptersCommintPrices;
    public ComponentsForRecyreView(@NotNull MyViewHoldersCommintPrices holderPrices,
                                   @NotNull  Context context,
                                   @NotNull  int position,
                                   @NotNull ObjectMapper objectMapper,
                                   @NotNull Integer getHiltPublicId,
                                   @NotNull String getHiltCommintgPrices,
                                   @NotNull GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices,
                                   @NotNull MutableLiveData<Intent> getHiltMutableLiveDataPay,
                                   @NotNull  LifecycleOwner lifecycleOwner,
                                   @NotNull MyRecycleViewIsAdaptersCommintPrices myRecycleViewIsAdaptersCommintPrices) {
        try{
        this.holderPrices = holderPrices;
        this.context = context;
        this.position = position;
        this.objectMapper = objectMapper;
        this.getHiltPublicId = getHiltPublicId;
        this.getHiltCommintgPrices = getHiltCommintgPrices;
        this.getLiveDataForrecyreViewPrices = getLiveDataForrecyreViewPrices;
        this.getHiltMutableLiveDataPay = getHiltMutableLiveDataPay;
        this.lifecycleOwner = lifecycleOwner;
        this.myRecycleViewIsAdaptersCommintPrices = myRecycleViewIsAdaptersCommintPrices;

        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " position " +position+
                " myRecycleViewIsAdaptersCommintPrices " +myRecycleViewIsAdaptersCommintPrices);

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }




    public MaterialTextView getmTV_commitingprices_value() {
        try{
            mTV_commitingprices_value=    holderPrices.itemView.findViewById(R.id.mTV_commitingprices_value) ;
            JsonNode jsonNode=    holderPrices.jsonNode.deepCopy();
            TextNode textNodeNameZFO=( TextNode)   jsonNode.findValue("CFO").deepCopy();
            mTV_commitingprices_value.setText(textNodeNameZFO.asText().trim());
           // mTV_commitingprices_value.setText(textNodeNameZFO.asText().trim()+new Date().toLocaleString().toString());

            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + " mTV_commitingprices_value " +mTV_commitingprices_value + " textNodeNameZFO " +textNodeNameZFO);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return mTV_commitingprices_value;
    }



    public MaterialTextView getmTV_NumberDOc_value( ) {
        try{
            mTV_NumberDOc_value=    holderPrices.itemView.findViewById(R.id.mTV_NumberDOc_value) ;
            JsonNode jsonNode=    holderPrices.jsonNode.deepCopy();
                TextNode textNodeNDoc=( TextNode)   jsonNode.findValue("NDoc").deepCopy();
                // TODO: 30.12.2023 номер документа
                if (! textNodeNDoc.asText().isEmpty()) {
                    Integer integerНомерДокумента=Integer.parseInt(textNodeNDoc.asText().trim());
                   /// mTV_NumberDOc_value.setText(textNodeNDoc.asText().trim());
                    mTV_NumberDOc_value.setText(integerНомерДокумента.toString());
                }

            mTV_NumberDOc_value.requestLayout();
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
        return mTV_NumberDOc_value;
    }










    public void getproccesingNestedRcyreViewClickMaterialButton(@NonNull MaterialButton materialButtonStartingNestedRecyreView) {
        try{
        // TODO: 25.03.2024 Кнопка запуска внутргего recyreview
        RxView.clicks(materialButtonStartingNestedRecyreView)
                .throttleFirst(250, TimeUnit.MILLISECONDS)
                .filter(s -> !s.toString().isEmpty())
                .map(new Function<Unit, MaterialButton>() {
                    @Override
                    public MaterialButton apply(Unit unit) throws Throwable {
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        return materialButtonStartingNestedRecyreView;
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
                .subscribe( Getarrow_nested_receriview-> {

                    ///todo отрыкавем или скрываем дополнительнае данные в Соглосование Цен
                    clikningArrowSettingNestedRecyreView(  Getarrow_nested_receriview);



                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            +" Getarrow_nested_receriview " +Getarrow_nested_receriview  );

                });

            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + " arrow_nested_receriview " +arrow_nested_receriview);

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

}

// TODO: 25.03.2024



    public void getproccesingNestedRcyreViewClickMaterialTextView(@NonNull MaterialButton arrow_nested_receriview,
            @NonNull MaterialTextView mTV_commitingprices_value) {
        try{
            // TODO: 25.03.2024 Кнопка запуска внутргего recyreview
            RxView.clicks(mTV_commitingprices_value)
                    .throttleFirst(250, TimeUnit.MILLISECONDS)
                    .filter(s -> !s.toString().isEmpty())
                    .map(new Function<Unit, MaterialTextView>() {
                        @Override
                        public MaterialTextView apply(Unit unit) throws Throwable {
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                            return mTV_commitingprices_value;
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
                    .subscribe( Getarrow_nested_receriview-> {


                        arrow_nested_receriview.callOnClick();
                        ///todo отрыкавем или скрываем дополнительнае данные в Соглосование Цен
                       /// clikningArrowSettingNestedRecyreView(  arrow_nested_receriview);



                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                +" Getarrow_nested_receriview " +Getarrow_nested_receriview  );

                    });

            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + " arrow_nested_receriview " +arrow_nested_receriview);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }




    private void clikningArrowSettingNestedRecyreView(MaterialButton materialButtonStaringNestedRecyreView) {
        try{
// TODO: 29.12.2023  Класс Обоработки Нажатие на Кноппку Стрелочка
            // TODO: 29.12.2023  запускаем бизес лошику нажатие на Кнопку Arrow
            setAndStartingRecyreViewNestedClickArrow setAndStartingRecyreViewNestedClickArrow =
                    new setAndStartingRecyreViewNestedClickArrow(materialButtonStaringNestedRecyreView,
                    holderPrices,
                    context,position,
                            objectMapper,
                            getHiltPublicId,
                            getHiltCommintgPrices,
                    getLiveDataForrecyreViewPrices,
                            getHiltMutableLiveDataPay,
                            lifecycleOwner,
                            myRecycleViewIsAdaptersCommintPrices) ;

// TODO: 30.12.2023 запускам при нажатии на Кнопку Arrow  внутрнеий



            setAndStartingRecyreViewNestedClickArrow.chnageStatusArrowData();

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










}






