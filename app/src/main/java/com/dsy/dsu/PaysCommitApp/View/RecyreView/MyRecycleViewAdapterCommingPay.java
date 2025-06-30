package com.dsy.dsu.PaysCommitApp.View.RecyreView;

// TODO: 28.02.2022 ViewHolder

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView.Bl_CommintigPay;
import com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView.FileFrom1CCommitPay;
import com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView.FindJson.FindFromJsonNode;
import com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView.LiveData.GetLiveDataForrecyreViewPay;
import com.dsy.dsu.R;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForApprove;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.jakewharton.rxbinding4.view.RxView;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import kotlin.Unit;

@SuppressLint("SuspiciousIndentation")
public class MyRecycleViewAdapterCommingPay extends RecyclerView.Adapter<MyViewHolderPayCommingPay> {


  public   JsonNode jsonNode1сСогласованияAfterSearchView;
    JsonNode  arrayNodeJsonRow;

    Context context;

    MyViewHolderPayCommingPay myViewHolderPayCommingPay;

    Bl_CommintigPay bl_commintigPay;

    ServiceForApprove.LocalBinderДляСогласования binderСогласования1C;
    Animation animation1;
    Integer ПубличныйidPay;
    ObjectMapper objectMapper;
    BottomNavigationView bottomNavigationViewParent;
    RecyclerView recycleviewcommitpays;

    // TODO: 17.01.2024 nested
    String getHiltCommintgPays;
    Animation animationДляСогласовани;
    JsonNode jsonNode1сСогласованияAll;




    LifecycleOwner lifecycleOwner;

    GetLiveDataForrecyreViewPay getLiveDataForrecyreViewPay;

    MutableLiveData<Intent> getHiltMutableLiveDataPayForRecyreView;
    androidx.appcompat.widget.SearchView searchview_commitpay;
    Activity activity;
    public MyRecycleViewAdapterCommingPay(@NotNull JsonNode jsonNode1сСогласования,
                                          @NonNull Context context,
                                          @NonNull ServiceForApprove.LocalBinderДляСогласования binderСогласования1C,
                                          @NotNull Animation animation1,
                                          @NonNull Integer ПубличныйidPay,
                                          @NonNull ObjectMapper objectMapper,
                                          @NonNull BottomNavigationView bottomNavigationViewParent,
                                          @NonNull RecyclerView recycleviewcommitpays,
                                          @NonNull String getHiltCommintgPays,
                                          @NonNull Bl_CommintigPay bl_commintigPay,
                                          @NotNull JsonNode jsonNode1сСогласованияAll,
                                          @NonNull LifecycleOwner lifecycleOwner,
                                          @NonNull GetLiveDataForrecyreViewPay getLiveDataForrecyreViewPay,
                                          @NonNull MutableLiveData<Intent> getHiltMutableLiveDataPayForRecyreView,
                                          @NonNull androidx.appcompat.widget.SearchView searchview_commitpay,
                                          @NonNull Activity activity) {
        try {
            this.jsonNode1сСогласованияAfterSearchView = jsonNode1сСогласования;
            this.context = context;
            this.binderСогласования1C = binderСогласования1C;
            this.animation1 = animation1;
            this.ПубличныйidPay = ПубличныйidPay;
            this.objectMapper = objectMapper;
            this.bottomNavigationViewParent = bottomNavigationViewParent;
            this.recycleviewcommitpays = recycleviewcommitpays;
            this.getHiltCommintgPays = getHiltCommintgPays;
            this.bl_commintigPay = bl_commintigPay;
            this.jsonNode1сСогласованияAll = jsonNode1сСогласованияAll;
            this.lifecycleOwner = lifecycleOwner;
            this.getLiveDataForrecyreViewPay = getLiveDataForrecyreViewPay;
            this.getHiltMutableLiveDataPayForRecyreView = getHiltMutableLiveDataPayForRecyreView;
            this.searchview_commitpay = searchview_commitpay;
            this.activity = activity;

            animationДляСогласовани = AnimationUtils.loadAnimation(context,  R.anim.slide_in_scrolls);//R.anim.layout_animal_commit
           // Animation  animationvibr1 = AnimationUtils.loadAnimation(context, R.anim.slide_in_row9);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 30.03.2022
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolderPayCommingPay holder, @NonNull int position, @NonNull List<Object> payloads) {
        Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position);
        try {
            // TODO: 19.01.2024  данные
            arrayNodeJsonRow = holder.jsonNode1сСогласования .get(position); // Here's

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "arrayNodeJsonRow "
                    + arrayNodeJsonRow + " position " + position);


            super.onBindViewHolder(holder, position, payloads);


                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "jsonNode1сСогласования "
                        + jsonNode1сСогласованияAfterSearchView + " position " + position);



            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "jsonNode1сСогласования "
                    + jsonNode1сСогласованияAfterSearchView + " position " + position);
            // TODO: 30.03.2022
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }




    // TODO: 18.01.2024  заполенеия Bungle
    private void setBungleCardView(@NonNull MyViewHolderPayCommingPay holder, @NonNull JsonNode jsonNode1сСогласованияSingleRow,@NonNull int position) {
        try{
            if ( holder.cardview_commingpay.getTag()==null) {
                Bundle bundleCacdView=new Bundle();

                bundleCacdView.putString("Ndoc",jsonNode1сСогласованияSingleRow.get("Ndoc").asText());
                bundleCacdView.putString("Ddoc",jsonNode1сСогласованияSingleRow.get("Ddoc").asText());
                bundleCacdView.putString("CFO",jsonNode1сСогласованияSingleRow.get("CFO").asText());
                bundleCacdView.putString("NomerScheta",String.valueOf(jsonNode1сСогласованияSingleRow.get("NomerScheta").asInt()));
                bundleCacdView.putString("Otvetstvenniy",jsonNode1сСогласованияSingleRow.get("Otvetstvenniy").asText());
                bundleCacdView.putString("articleDDS",jsonNode1сСогласованияSingleRow.get("articleDDS").asText());
                bundleCacdView.putInt("position",position);

                holder.cardview_commingpay.setTag(bundleCacdView);
                holder.cardview_commingpay.forceLayout();
            }


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "jsonNode1сСогласования "
                + jsonNode1сСогласованияAfterSearchView );
        // TODO: 30.03.2022
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    private void setеVisibleTablerow_nesters(MyViewHolderPayCommingPay holder ) {
        try{
            holder.progressbar_commingpay.setVisibility(View.VISIBLE);

                    TableRow tableRowFisrt=    holder.    tableRowFisrt;

                    TableRow  tablerowsecond = holder. tablerowsecond;


                       if(tableRowFisrt.getVisibility()==View.VISIBLE){

                        procceseringHideorVisibleSecornd(  holder);

                         //procceseringButtonVisibleSecornd(  holder);

                    }

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "tablerow_nesters_commininggpay.getVisibility() ");


                    holder.progressbar_commingpay.setVisibility(View.GONE);
                   

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

    private  void procceseringHideorVisibleSecornd(@NonNull MyViewHolderPayCommingPay holder) {

        try{
        if (holder.tablerowsecond.getVisibility()==View.GONE) {
            holder.tablerowsecond.setVisibility(View.VISIBLE);
            Drawable drawableup=context.getDrawable(R.drawable.icon_for_commingpricesup);
            holder. arrowpay_nested_receriview_commitingpay.setIcon(drawableup);
            ViewGroup.LayoutParams params = holder.tablerowsecond.getLayoutParams();
            params.height = 400;
            holder.tablerowsecond.setLayoutParams(params);
        }else {
            Drawable drawabledown=context.getDrawable(R.drawable.icon_for_commingpricesdown2);
            holder. arrowpay_nested_receriview_commitingpay.setIcon(drawabledown);
            holder.tablerowsecond.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = holder.tablerowsecond.getLayoutParams();
            params.height = 0;
            holder.tablerowsecond.setLayoutParams(params);


        }

            holder.tablerowsecond.requestLayout();
            holder.tablerowsecond.refreshDrawableState();
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


    private   void defalultsetVisibleSecondTableRow(@NonNull MyViewHolderPayCommingPay holder  ) {
        try {
            // TODO: 26.01.2024 данные для послддующего скрытия
            // TODO: 26.01.2024 данные для послддующего скрытия
            TableRow       tablerowsecond=  holder.    tablerowsecond;
            tablerowsecond.setVisibility(View.GONE);
            tablerowsecond.setEnabled(false);
            ViewGroup.LayoutParams params =    tablerowsecond.getLayoutParams();
            params.height = 0;
            tablerowsecond.setLayoutParams(params);
            tablerowsecond.requestLayout();
            tablerowsecond.refreshDrawableState();
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




    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolderPayCommingPay holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull MyViewHolderPayCommingPay holder) {
        // TODO: 03.11.2023 Parent
        return super.onFailedToRecycleView(holder);

    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolderPayCommingPay holder) {
        super.onViewAttachedToWindow(holder);

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolderPayCommingPay holder) {
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
            // TODO: 30.03.2022
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " position" + position);
            // TODO: 30.03.2022
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return super.getItemViewType(position);
    }









    @NonNull
    @Override
    public MyViewHolderPayCommingPay onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewГлавныйВидДляRecyclleViewДляСогласования = null;
        try {


                             /*   viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.context)
                                        .inflate(R.layout.simple_for_commitpay_cardview1, parent, false);//todo old simple_for_takst_cardview1*/
                             /*   viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.context)
                                        .inflate(R.layout.simple_for_commitpay_cardview_file, parent, false);//todo old simple_for_takst_cardview1*/
                              /*  viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.context)
                                        .inflate(R.layout.simple_for_commitpay_cardview_grid_file, parent, false);//todo old simple_for_takst_cardview1*/
                       /*         viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.context)
                                        .inflate(R.layout.simple_for_commitpay_cardview_grid_file, parent, false);//todo old simple_for_takst_cardview1*/

            if (jsonNode1сСогласованияAfterSearchView.size()>0) {
                viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.simple_for_commitpay_cardview_withnested3, parent, false);//todo old simple_for_takst_cardview1
            }else {

                viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.simple_for_commitpay_dont_jsonot1c, parent, false);
            }


            // TODO: 22.03.2022
            myViewHolderPayCommingPay = new MyViewHolderPayCommingPay(viewГлавныйВидДляRecyclleViewДляСогласования, context, jsonNode1сСогласованияAfterSearchView);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return myViewHolderPayCommingPay;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderPayCommingPay holder, int position) {
        try {

            if (arrayNodeJsonRow!=null && arrayNodeJsonRow.size()>0) {

                // TODO: 12.01.2024 Заполняем Данными Компоненты

                МетодБиндингаСозданиеНомерДокумента(holder  );

                МетодБиндингаСозданиеСФО(holder   );

                МетодБиндингаСозданиеКонтрагент(holder );

                МетодБиндингаСозданиеОрганизация(holder );

                МетодБиндингаСозданиеНомелклатура(holder );


                МетодБиндингаСозданиеСумма(holder  );

                МетодБиндингаСозданиеДДС(holder );


                // TODO: 12.01.2024 кнопки
                МетодКпопкаОтказаСогласования(holder );

                МетодКпопкаСогласованияУспешное(holder, ПубличныйidPay, getHiltCommintgPays );

                // TODO: 12.01.2024 обработка видимости Prograssbar
                МетодForPrograBarInner(holder);


                // TODO: 03.11.2023 дополнитешльный механизм добаляем файлы с 1С
                AddFileOt1c(holder, arrayNodeJsonRow, objectMapper);


                // TODO: 17.01.2024  КЛИК ПО ОТКРЫТИЕ NESTED REcyrecireView
                ClickvisibleOrHideNestedRecyreView(holder);


                // TODO: 17.01.2024 методы для заполения NESTD RECYREVIEW


                metodNestdobiyectRaskhoda(holder  );
                metodnestedAvans(holder) ;
                metodNestdPoDogovoruSMP(holder );
                metodNestdPredelnayaData(holder   );
                metodNestdNomerScheta(holder   );
                metodNestdDataScheta(holder);
                metodNestdOtvetstvenniy(holder );
                metodNestdCommentariy(holder  );


                // TODO: 17.01.2024 вставляем данные

                setBungleCardView(holder, arrayNodeJsonRow,position);


                // TODO: 17.01.2024 показать или скрыть
/*                defalultsetVisibleFirstTableRow(holder);
                defalultsetVisiblebuttonsTableRow(holder);*/

                defalultsetVisibleSecondTableRow(holder);


                // TODO: 26.01.2024 end

                myViewHolderPayCommingPay.setIsRecyclable(false);

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+"position " + position );

            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 03.11.2023  метод загрузки файлов с 1с
    void AddFileOt1c(@NonNull MyViewHolderPayCommingPay holder, @NotNull JsonNode jsonNode1сСогласованияSingleRow, @NonNull ObjectMapper objectMapper) {
        try {
            // TODO: 03.11.2023 advanset code Downloaf Image rom Commit Pay


                // TODO: 03.11.2023  запускаем получние отображения File from 1c
                FileFrom1CCommitPay fileFrom1CCommitPay1 = new FileFrom1CCommitPay(holder, context, binderСогласования1C);
                // TODO: 08.11.2023 строчка добавление файлов FILE  от 1С
                fileFrom1CCommitPay1.startFileFrom1CCommitPay(jsonNode1сСогласованияSingleRow, holder,   ПубличныйidPay,  getHiltCommintgPays);



            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "   holder.tableLayoutcommitpayfiles "
                    + " jsonNode1сСогласованияSingleRow " + jsonNode1сСогласованияSingleRow);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


    ///todo первый метод #1

    private void МетодБиндингаСозданиеНомерДокумента(@NonNull MyViewHolderPayCommingPay holder ) {
        try {
            if (  holder.tx_nomer != null) {///"Ndoc"

                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();

                String ПерваяСтрочкаЗначения = jsonNodeCurrent.get("Ndoc").asText().trim();

                Integer Ndoc=Integer.parseInt(ПерваяСтрочкаЗначения);

                holder.tx_nomer.setText(Ndoc.toString());
                holder.butt_successcommit.setTag(ПерваяСтрочкаЗначения);

                holder.butt_cancel.setTag(ПерваяСтрочкаЗначения);
                holder.tx_nomer.setTag(ПерваяСтрочкаЗначения);

                holder.tx_nomer.requestLayout();
                holder.tx_nomer.refreshDrawableState();

                Log.i(this.getClass().getName(), "  Ndoc   holder.butt_successcommit " + holder.butt_successcommit.getTag() +
                        "     holder.butt_cancel " + holder.butt_cancel.getTag());

            }
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    ///todo первый метод #2

    private void МетодБиндингаСозданиеДДС(@NonNull MyViewHolderPayCommingPay holder ) {

        try {
            // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

            if ( holder.tx_statiy != null) {

                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();

                String ПерваяСтрочкаЗначения = jsonNodeCurrent.get("articleDDS").asText();
                // TODO: 02.03.2022
                Log.i(this.getClass().getName(), "  articleDDS ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                // TODO: 28.02.2022
                holder.tx_statiy.setText(ПерваяСтрочкаЗначения);
                holder.tx_statiy.setTag(ПерваяСтрочкаЗначения);

                holder.tx_statiy.requestLayout();
                holder.tx_statiy.refreshDrawableState();

            }
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            // TODO: 28.02.2022*/
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }
    ///todo первый метод #3

    private void МетодБиндингаСозданиеКонтрагент(@NonNull MyViewHolderPayCommingPay holder ) {

        try {
            // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

            if (  holder.tx_kontragent != null) {

                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();
                //TODO
                String ПерваяСтрочкаЗначения = jsonNodeCurrent.get("counterparty").asText();
                // TODO: 02.03.2022

                // TODO: 02.03.2022
                Log.i(this.getClass().getName(), " organization ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                // TODO: 28.02.2022
                holder.tx_kontragent.setText(ПерваяСтрочкаЗначения);
                holder.tx_kontragent.setTag(ПерваяСтрочкаЗначения);

                holder.tx_kontragent.requestLayout();
                holder.tx_kontragent.refreshDrawableState();

            }
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            // TODO: 28.02.2022*/
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }

    }


    ///todo первый метод #4

    private void МетодБиндингаСозданиеСФО(@NonNull MyViewHolderPayCommingPay holder ) {

        try {
            // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
            if (holder.tx_zfo != null  ) {
                //todo
                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();

                String ПерваяСтрочкаЗначения = jsonNodeCurrent.get("CFO").asText();
                // TODO: 02.03.2022

                Log.i(this.getClass().getName(), " CFO ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                // TODO: 28.02.2022
                //TODo
                holder.tx_zfo.setText(ПерваяСтрочкаЗначения);

                holder.tx_zfo.requestLayout();
                holder.tx_zfo.refreshDrawableState();

            }
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            // TODO: 28.02.2022*/
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }

    }


    ///todo первый метод #7

    private void МетодБиндингаСозданиеНомелклатура(@NonNull MyViewHolderPayCommingPay holder ) {
        try {
            // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

                StringBuffer stringBufferArrayNamelk = new StringBuffer();
                final Integer[] ИНдексТекущий = {1};
                // String ПерваяСтрочкаЗначения = jsonNode1сСогласованияSingleRow.get("nomenclature").asText()
            JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();

            jsonNodeCurrent.get("nomenclature")
                            .elements()
                            .forEachRemaining(new Consumer<JsonNode>() {
                                @Override
                                public void accept(JsonNode jsonNode) {
                                    JsonNode jsonNodesArrayНамелклатура = jsonNode.deepCopy();


                                    jsonNodesArrayНамелклатура.elements().forEachRemaining(new Consumer<JsonNode>() {
                                        @Override
                                        public void accept(JsonNode jsonNode) {
                                            TextNode textNode = jsonNode.deepCopy();
                                            if (!textNode.isNull()) {
                                                stringBufferArrayNamelk.append(textNode.asText());
                                                if (ИНдексТекущий[0] < jsonNodeCurrent.get("nomenclature").size()) {
                                                    // TODO: 24.11.2023
                                                    ИНдексТекущий[0]++;
                                                    stringBufferArrayNamelk.append("\n");
                                                }

                                                Log.d(context.getClass().getName(), "\n"
                                                        + " время: " + new Date() + "\n+" +
                                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                                            }
                                        }
                                    });


                                }
                            });


                // TODO: 24.11.2023 слашателя
                holder.tx_namelklatura.setText(stringBufferArrayNamelk);
                holder.tx_namelklatura.setTag(stringBufferArrayNamelk);

                holder.tx_namelklatura.requestLayout();
                holder.tx_namelklatura.refreshDrawableState();

                Log.d(context.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        "  nomenclature stringBufferArrayNamelk " + stringBufferArrayNamelk);




        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


    // TODO: 14.03.2022  Успешное Согласования

    private void МетодКпопкаСогласованияУспешное(@NonNull MyViewHolderPayCommingPay holder,
                                                 @NonNull Integer ПубличныйidPay,
                                                 @NonNull  String getHiltCommintgPays)
            throws ExecutionException, InterruptedException {
        try {
            Log.d(this.getClass().getName(), "   КнопкаУспешноеСогласования    Успехх Согласования 2 ");

            final Handler[] handler = {null};

            MyRecycleViewAdapterCommingPay myRecycleViewAdapterCommingPay =this;

            // TODO: 10.11.2023 клик по файлов
            RxView.clicks(holder.butt_successcommit)
                    .throttleFirst(10, TimeUnit.SECONDS)
                    .filter(s -> !s.toString().isEmpty())
                    .map(new Function<Unit, Object>() {
                        @Override
                        public Object apply(Unit unit) throws Throwable {

                            // TODO: 30.12.2023 вибрация
                            Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                            v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                            handler[0] = holder.butt_successcommit.getHandler();

                            // TODO: 25.03.2024
                            cardViewRotacfions(holder,5);

                            return holder.butt_successcommit;
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
                    .subscribe(Getbutt_successcommit -> {

                        // TODO: 18.01.2024  метод успешного соглавования
                                    proccerClickSucceesPay(holder, handler, holder.cardview_commingpay,
                                            ПубличныйidPay,getHiltCommintgPays,   myRecycleViewAdapterCommingPay);

                        // TODO: 25.03.2024
                        cardViewRotacfions(holder,-5);

                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


                    });


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 12.01.2024  метод срабоатывает когда нажали Согласовать Succeeess
    private void proccerClickSucceesPay(@NonNull MyViewHolderPayCommingPay holder,
                                        @NonNull  Handler[] handler,
                                        @NonNull MaterialCardView cardview_commingpay,
                                        @NonNull Integer ПубличныйidPay,
                                        @NonNull  String getHiltCommintgPays,
                                        @NonNull MyRecycleViewAdapterCommingPay myRecycleViewAdapterCommingPay) {
        try {
            handler[0].postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                     Bundle bundlegetCardViewPay=(Bundle)   cardview_commingpay.getTag();


                      String  NumberDoc =bundlegetCardViewPay.getString("Ndoc").trim();
                      String  DataDoc =bundlegetCardViewPay.getString("Ddoc").trim();
                      int  position =bundlegetCardViewPay.getInt("position");
                        // TODO: 06.02.2024



                 /*       if (searchview_commitpay.getVisibility()==View.VISIBLE && searchview_commitpay.getQuery().toString().length()>0 ) {*/
                        Integer    positionDeleteJsonNodeSeachView = new FindFromJsonNode(context).startPostionJsonNode(jsonNode1сСогласованияAfterSearchView,NumberDoc);

                        Integer     positionDeleteJsonNodeAll = new FindFromJsonNode(context).startPostionJsonNode(jsonNode1сСогласованияAll,NumberDoc);


                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +" positionDeleteJsonNodeAll " +positionDeleteJsonNodeAll);






                        Intent intentзаданиеНаВыполениеSuccess = new Intent();
                        intentзаданиеНаВыполениеSuccess.setAction("ЗапускаемСогласованиеОтказИлилУспешное");
                        Bundle bundleДляПередачиВСлужбуСогласования = new Bundle();
                        bundleДляПередачиВСлужбуСогласования.putInt("PROCESS_IDСогласования", Integer.parseInt("28"));
                        bundleДляПередачиВСлужбуСогласования.putString("NumberDoc", NumberDoc);
                        bundleДляПередачиВСлужбуСогласования.putString("DataDoc", DataDoc);
                        bundleДляПередачиВСлужбуСогласования.putInt("ПередаемСтатусСогласования", 2);///TODO выполнил Согласования
                        bundleДляПередачиВСлужбуСогласования.putInt("ПубличныйidPay", ПубличныйidPay);///TODO выполнил Согласования
                        intentзаданиеНаВыполениеSuccess.putExtras(bundleДляПередачиВСлужбуСогласования);



                        // TODO: 24.01.2024 сама операиция подтверждения отправляем ее

                        // TODO: 30.01.2024  слушатель на дейстия при СУПЕШНО
                        getLiveDataForrecyreViewPay.setObservableLiveDataRecyreViewPays(lifecycleOwner,
                                context,
                                getHiltMutableLiveDataPayForRecyreView,
                                myRecycleViewAdapterCommingPay,
                                bl_commintigPay,
                                jsonNode1сСогласованияAfterSearchView
                                ,holder,cardview_commingpay,position,recycleviewcommitpays,
                                intentзаданиеНаВыполениеSuccess,
                                getHiltCommintgPays,
                                binderСогласования1C,
                                searchview_commitpay,
                                activity,
                                positionDeleteJsonNodeAll,
                                positionDeleteJsonNodeSeachView);




// TODO: 30.01.2024  отпраявеам событие в Mutable
                        sendLiveDataRecyreViewEventCallBacl1c( );




                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " intentзаданиеНаВыполениеSuccess " +intentзаданиеНаВыполениеSuccess);


                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }


            }, 100);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    private void cardViewRotacfions(@NonNull MyViewHolderPayCommingPay holder,@NonNull int rot) {
        holder.cardview_commingpay.animate().rotationXBy(rot);
    }

















    public void notifynotifyDataSetChanged(@NonNull MyViewHolderPayCommingPay holder,
                                           @NonNull int position) {
        try{
            // TODO: 23.01.2024 анимация
            holder.itemView.animate().withStartAction(new Runnable() {
                @Override
                public void run() {
                    // TODO: 11.01.2024 перегрузка данных
                    notifyItemRemoved(position );
                    notifyItemMoved(position,holder.jsonNode1сСогласования.size());
                }
            }).setDuration(50).start();

            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " jsonNode1сСогласования " +jsonNode1сСогласованияAfterSearchView);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    public JsonNode remoteSingleJsonSeravhView(@NonNull int intStreamFindPosiontion) {
        try{
            int sum=0;
            // TODO: 23.01.2024 анимация
            Iterator<JsonNode> elementsSearchView;
                elementsSearchView = jsonNode1сСогласованияAfterSearchView.iterator();
                while (elementsSearchView.hasNext()) {
                    elementsSearchView.next();
                    if (sum == intStreamFindPosiontion) {
                        elementsSearchView.remove();
                        break;
                    }
                    sum++;

                }

            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " jsonNode1сСогласования " +jsonNode1сСогласованияAfterSearchView);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  jsonNode1сСогласованияAfterSearchView;
    }


    public JsonNode remoteSingleJsonFromAll(@NonNull int intStreamFindPosiontion) {
        try{
            int sum=0;
            // TODO: 23.01.2024 анимация
            Iterator<JsonNode> elementsAll;
            elementsAll = jsonNode1сСогласованияAll.iterator();
            while (elementsAll.hasNext()) {
                elementsAll.next();
                if (sum == intStreamFindPosiontion) {
                    elementsAll.remove();
                    break;
                }
                sum++;
            }

            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " jsonNode1сСогласованияAll " +jsonNode1сСогласованияAll);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  jsonNode1сСогласованияAll;
    }





    // TODO: 12.01.2024 Кнопка РАскрытие Скрытого recyreview


    private void МетодForPrograBarInner(@NonNull MyViewHolderPayCommingPay holder) {
        try {
            holder.progressbar_commingpay.setVisibility(View.INVISIBLE);

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
    // TODO: 01.08.2022


    //TODO вторая кнопка
    // TODO: 14.03.2022  отказа Согласования
    private void МетодКпопкаОтказаСогласования(@NonNull MyViewHolderPayCommingPay holder )
            throws ExecutionException, InterruptedException {
        try {
            // TODO: 02.03.2022
            Log.d(this.getClass().getName(), "  КнопкаСогласованиеОтказ    отказ 1  ");


            final Handler[] handler = {null};

            MyRecycleViewAdapterCommingPay myRecycleViewAdapterCommingPay =this;

            RxView.clicks(holder.butt_cancel)
                    .throttleFirst(10, TimeUnit.SECONDS)
                    .filter(s -> !s.toString().isEmpty())
                    .map(new Function<Unit, Object>() {
                        @Override
                        public Object apply(Unit unit) throws Throwable {
                            // TODO: 30.12.2023 вибрация
                            Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                            v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            handler[0] = holder.butt_cancel.getHandler();
                            // TODO: 25.03.2024 rota
                            cardViewRotacfions(holder,5);
                            return holder.butt_cancel;
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
                    .subscribe(Getbutt_cancel -> {
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


                        procceringCancelButtonClick(holder, handler[0], holder.cardview_commingpay,  myRecycleViewAdapterCommingPay);

                      // TODO: 25.03.2024 rotafions defalurtl
                        cardViewRotacfions(holder,-5);


                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                    });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 12.01.2024  Дейставия по кнопек ОТКАЗ
    private void procceringCancelButtonClick(@NonNull MyViewHolderPayCommingPay holder,
                                             @NonNull  Handler handler,
                                            @NonNull MaterialCardView cardview_commingpay,
                                             @NonNull MyRecycleViewAdapterCommingPay myRecycleViewAdapterCommingPay) {
        try {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bundle bundlegetCardViewPay=(Bundle)   cardview_commingpay.getTag();

                        String  NumberDoc =bundlegetCardViewPay.getString("Ndoc");
                        String  DataDoc =bundlegetCardViewPay.getString("Ddoc");
                        int  position =bundlegetCardViewPay.getInt("position");


                        // TODO: 06.02.2024
                        /*       if (searchview_commitpay.getVisibility()==View.VISIBLE && searchview_commitpay.getQuery().toString().length()>0 ) {*/
                        Integer    positionDeleteJsonNodeSeachView = new FindFromJsonNode(context).startPostionJsonNode(jsonNode1сСогласованияAfterSearchView,NumberDoc);

                        Integer     positionDeleteJsonNodeAll = new FindFromJsonNode(context).startPostionJsonNode(jsonNode1сСогласованияAll,NumberDoc);


                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +" positionDeleteJsonNodeAll " +positionDeleteJsonNodeAll);



                        Intent intentзаданиеНаВыполениеCancel = new Intent();
                        intentзаданиеНаВыполениеCancel.setAction("ЗапускаемСогласованиеОтказИлилУспешное");
                        Bundle bundleДляПередачиВСлужбуCancel = new Bundle();
                        bundleДляПередачиВСлужбуCancel.putInt("PROCESS_IDСогласования", Integer.parseInt("28"));
                        bundleДляПередачиВСлужбуCancel.putString("NumberDoc", NumberDoc);
                        bundleДляПередачиВСлужбуCancel.putString("DataDoc", DataDoc);
                        bundleДляПередачиВСлужбуCancel.putInt("ПередаемСтатусСогласования", 1);///TODO выполнил ОТКАЗ
                        bundleДляПередачиВСлужбуCancel.putInt("ПубличныйidPay", ПубличныйidPay);

                        intentзаданиеНаВыполениеCancel.putExtras(bundleДляПередачиВСлужбуCancel);
                        ///TODO выполнил ОТКАЗ


                        // TODO: 24.01.2024 сама операиция подтверждения отправляем ее

                        // TODO: 30.01.2024  слушатель на дейстия при СУПЕШНО
                        getLiveDataForrecyreViewPay.setObservableLiveDataRecyreViewPays(lifecycleOwner,
                                context,
                                getHiltMutableLiveDataPayForRecyreView,
                                myRecycleViewAdapterCommingPay,bl_commintigPay,jsonNode1сСогласованияAfterSearchView
                                ,holder,cardview_commingpay,position,recycleviewcommitpays,
                                intentзаданиеНаВыполениеCancel,
                                getHiltCommintgPays,
                                binderСогласования1C,
                                searchview_commitpay,
                                activity,
                                positionDeleteJsonNodeAll,
                                positionDeleteJsonNodeSeachView);




// TODO: 30.01.2024  отпраявеам событие в Mutable
                        sendLiveDataRecyreViewEventCallBacl1c( );
                        
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
            }, 100);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void sendLiveDataRecyreViewEventCallBacl1c(  ) {

        try{
            Intent intentCallBackRcyreCiew1cPayEvent=new Intent("CallBackRecyreViewPays");
            getHiltMutableLiveDataPayForRecyreView.postValue(intentCallBackRcyreCiew1cPayEvent);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNode1сСогласования " +jsonNode1сСогласованияAfterSearchView);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    ///todo первый метод #3

    private void МетодБиндингаСозданиеОрганизация(@NonNull MyViewHolderPayCommingPay holder ) {

        try {
            // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

            if (  holder.tx_organizations != null) {
                //TODO
                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();

                String ПерваяСтрочкаЗначения = jsonNodeCurrent.get("organization").asText();
                // TODO: 02.03.2022

                // TODO: 02.03.2022
                Log.i(this.getClass().getName(), " organization ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                // TODO: 28.02.2022
                holder.tx_organizations.setText(ПерваяСтрочкаЗначения);
                holder.tx_organizations.setTag(ПерваяСтрочкаЗначения);

                holder.tx_organizations.requestLayout();
                holder.tx_organizations.refreshDrawableState();
            }

            // TODO: 28.02.2022*/
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }

    }

    ///todo первый метод #5


    private void МетодБиндингаСозданиеСумма(@NonNull MyViewHolderPayCommingPay holder ) {

        try {
            // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

            if (  holder.tx_sum != null) {
                //TODO
                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();

                String ПерваяСтрочкаЗначения = jsonNodeCurrent.get("sum").asText();
                // TODO: 02.03.2022

                // TODO: 02.03.2022
                Log.i(this.getClass().getName(), "  sum ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                // TODO: 28.02.2022
                holder.tx_sum.setText(ПерваяСтрочкаЗначения.toString() + " руб");
                holder.tx_sum.setTag(ПерваяСтрочкаЗначения);

                holder.tx_sum.requestLayout();
                holder.tx_sum.refreshDrawableState();

            }

            // TODO: 28.02.2022*/
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }

    }




    public void ClickvisibleOrHideNestedRecyreView(@NonNull MyViewHolderPayCommingPay holder  ) {
        try {
            Log.d(this.getClass().getName(), "   КнопкаУспешноеСогласования    Успехх Согласования 2 " );

            final Handler[] handler = {null};

            // TODO: 10.11.2023 клик по файлов
            RxView.clicks(   holder. arrowpay_nested_receriview_commitingpay)
                    .throttleFirst(250, TimeUnit.MILLISECONDS)
                    .filter(s -> !s.toString().isEmpty())
                    .filter(jsonnester->arrayNodeJsonRow.has("obiyectRaskhoda")==true)
                    .map(new Function<Unit, Object>() {
                        @Override
                        public Object apply(Unit unit) throws Throwable {
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                            handler[0] =holder. arrowpay_nested_receriview_commitingpay.getHandler();
                            return    holder. arrowpay_nested_receriview_commitingpay;
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
                    .subscribe( Getarrow_nested_receriview_commitingpay-> {
// TODO: 18.01.2024 код показываем или скрываем Nested RecyreView

                       setеVisibleTablerow_nesters(holder );
                        
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Getarrow_nested_receriview_commitingpay "
                                +Getarrow_nested_receriview_commitingpay);


                    });



        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }



    }









// TODO: 17.01.2024 методы Hide Nested RecyreView Or Visible
    ///todo первый метод #1
    private void metodNestdobiyectRaskhoda(@NonNull MyViewHolderPayCommingPay holder ) {
        try {
            if ( holder.obiyectRaskhoda!=null ) {///"Ndoc"

                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();

                String    obiyectRaskhoda=    jsonNodeCurrent.get("obiyectRaskhoda").asText().trim();

                if (!obiyectRaskhoda.isEmpty()) {
                    holder.obiyectRaskhoda.setText(obiyectRaskhoda);
                    holder.obiyectRaskhoda.setTag(obiyectRaskhoda);


                    holder.obiyectRaskhoda.requestLayout();
                    holder.obiyectRaskhoda.refreshDrawableState();
                }

                Log.i(this.getClass().getName(), "  mTV_1value" +  holder.obiyectRaskhoda.getTag()+
                        "     holder.butt_cancel " +    holder.obiyectRaskhoda.getTag() );

            }
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

    ///todo первый метод #2


    ///todo первый метод #3






    ///todo первый метод #4

    private void metodnestedAvans(@NonNull MyViewHolderPayCommingPay holder  ) {

        try {
            // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
            if (holder.Avans !=null   ) {
                //todo
                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();
                String Avans = jsonNodeCurrent.get("Avans").asText();
                // TODO: 02.03.2022

                Log.i(this.getClass().getName(), "   Avans " + Avans);
                if (!Avans.isEmpty()) {
                    // TODO: 28.02.2022
                    //TODo
                    holder.Avans.setText(Avans);
                    holder.Avans.setTag(Avans);

                    holder.Avans.requestLayout();
                    holder.Avans.refreshDrawableState();
                }

            }
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            // TODO: 28.02.2022*/
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }

    }


    private void metodNestdPoDogovoruSMP(@NonNull MyViewHolderPayCommingPay holder ) {

        try {
            // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

            if (  holder.PoDogovoruSMP !=null ) {
                //TODO
                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();
                String PoDogovoruSMP = jsonNodeCurrent.get("PoDogovoruSMP").asText();
                // TODO: 02.03.2022
                if (!PoDogovoruSMP.isEmpty()) {
                    holder.PoDogovoruSMP.setText(PoDogovoruSMP);
                    holder.PoDogovoruSMP.setTag(PoDogovoruSMP);

                    holder.PoDogovoruSMP.requestLayout();
                    holder.PoDogovoruSMP.refreshDrawableState();
                }

            }
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            // TODO: 28.02.2022*/
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }

    }




    ///todo первый метод #3
    private void metodNestdPredelnayaData(@NonNull MyViewHolderPayCommingPay holder ) {

        try {
            // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

            if (   holder.PredelnayaData !=null ) {
                //TODO
                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();

                String PredelnayaData = jsonNodeCurrent.get("PredelnayaData").asText();
                // TODO: 02.03.2022
                if (!PredelnayaData.isEmpty()) {
                    holder.PredelnayaData.setText(PredelnayaData);
                    holder.PredelnayaData.setTag(PredelnayaData);

                    holder.PredelnayaData.requestLayout();
                    holder.PredelnayaData.refreshDrawableState();
                }

                // TODO: 02.03.2022
                Log.i(this.getClass().getName(), " organization PredelnayaData " + PredelnayaData);
            }

            // TODO: 28.02.2022*/
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }

    }

    ///todo первый метод #5

    ///todo первый метод #7

    private void metodNestdNomerScheta(@NonNull MyViewHolderPayCommingPay holder ) {
        try {
            // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
            if (  holder.NomerScheta !=null ) {
                //TODO
                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();
                Integer NomerScheta = jsonNodeCurrent.get("NomerScheta").asInt();
                // TODO: 02.03.2022
                if (NomerScheta>0) {
                    holder.NomerScheta.setText(NomerScheta.toString());
                    holder.NomerScheta.setTag(NomerScheta.toString());

                    holder.NomerScheta.requestLayout();
                    holder.NomerScheta.refreshDrawableState();
                }

                // TODO: 02.03.2022
                Log.i(this.getClass().getName(), " organization NomerScheta " + NomerScheta);
            }

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







    private void metodNestdDataScheta( @NonNull MyViewHolderPayCommingPay holder ) {

        try {
            // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
            if (   holder.DataScheta !=null ) {
                //TODO
                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();

                String DataScheta = jsonNodeCurrent.get("DataScheta").asText();
                // TODO: 02.03.2022
                // TODO: 28.02.2022
                if (!DataScheta.isEmpty()) {
                    holder.DataScheta.setText(DataScheta );
                    holder.DataScheta.setTag(DataScheta);

                    holder.DataScheta.requestLayout();
                    holder.DataScheta.refreshDrawableState();
                }

                // TODO: 02.03.2022
                Log.i(this.getClass().getName(), "  sum DataScheta " + DataScheta);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }

    }


    private void metodNestdOtvetstvenniy(@NonNull MyViewHolderPayCommingPay holder  ) {

        try {
            // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

            if (   holder.Otvetstvenniy !=null ) {
                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();
                String Otvetstvenniy = jsonNodeCurrent.get("Otvetstvenniy").asText();
                // TODO: 02.03.2022

                if (!Otvetstvenniy.isEmpty()) {
                    // TODO: 28.02.2022
                    holder.Otvetstvenniy.setText(Otvetstvenniy);
                    holder.Otvetstvenniy.setTag(Otvetstvenniy);

                    holder.Otvetstvenniy.requestLayout();
                    holder.Otvetstvenniy.refreshDrawableState();
                }
                Log.i(this.getClass().getName(), "  articleDDS Otvetstvenniy " + Otvetstvenniy);
            }
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            // TODO: 28.02.2022*/
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }



    private void metodNestdCommentariy(@NonNull MyViewHolderPayCommingPay holder ) {
        try {
            if (  holder.Commentariy !=null ) {

                JsonNode jsonNodeCurrent=     arrayNodeJsonRow.deepCopy();
                String Commentariy = jsonNodeCurrent.get("Commentariy").asText();
                // TODO: 02.03.2022

                // TODO: 28.02.2022
                if (!Commentariy.isEmpty()) {
                    holder.Commentariy.setText(Commentariy);
                    holder.Commentariy.setTag(Commentariy);

                    holder.Commentariy.requestLayout();
                    holder.Commentariy.refreshDrawableState();
                }
                Log.i(this.getClass().getName(), "  articleDDS Commentariy " + Commentariy);
            }
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            // TODO: 28.02.2022*/
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

















    // TODO: 29.03.2022  слутаеть смены статуса


    @Override
    public long getItemId(int position) {
        // TODO: 04.03.2022

        Log.i(this.getClass().getName(), "     getItemId holder.position " + position);

        return super.getItemId(position);

    }



    @Override
    public int getItemCount() {
        // TODO: 02.03.2022
        int КоличесвоСтрок = jsonNode1сСогласованияAfterSearchView.size();
        // TODO: 06.02.2024
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                +"jsonNode1сСогласования.size() " + jsonNode1сСогласованияAfterSearchView.size() + " КоличесвоСтрок " + КоличесвоСтрок);
        // TODO: 28.02.2022
        return КоличесвоСтрок;
    }

}//TODO  END MyRecycleAdapter