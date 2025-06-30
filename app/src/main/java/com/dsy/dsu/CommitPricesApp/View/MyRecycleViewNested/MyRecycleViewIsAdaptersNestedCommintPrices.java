package com.dsy.dsu.CommitPricesApp.View.MyRecycleViewNested;// TODO: 27.12.2023 Recyreview is null

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitPricesApp.Model.LiveDataPrices.GetLiveDataForrecyreViewPrices;
import com.dsy.dsu.CommitPricesApp.View.ComponentsPayPrices.ComponentsForRecyreViewNesteds;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

// TODO: 09.11.2023 ВТОРОЯ Rereview

public  class MyRecycleViewIsAdaptersNestedCommintPrices extends RecyclerView.Adapter<MyViewHoldersNestedCommintPrices> {

    public Context context;
    public View itemViewholderPrices;
     public  JsonNode jsonNodeNested;
     public  JsonNode jsonNodeNestedRow;
    public  int getAbsoluteAdapterPosition;
    private MyViewHoldersNestedCommintPrices viewHoldersNested;



    private ObjectMapper objectMapper;

    private Integer getHiltPublicId;
    private String getHiltCommintgPrices;

    private GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices;


    private MutableLiveData<Intent> getHiltMutableLiveDataPrices;
    private LifecycleOwner lifecycleOwner;

    private RecyclerView recycleview_comminingppricesNesteds;

    public MyRecycleViewIsAdaptersNestedCommintPrices(@NonNull View itemViewholderPrices,
                                                      @NotNull Context context,
                                                      @NotNull JsonNode jsonNodeNested,
                                                      @NotNull int getAbsoluteAdapterPosition,
                                                      @NotNull ObjectMapper objectMapper,
                                                      @NotNull Integer getHiltPublicId,
                                                      @NotNull String getHiltCommintgPrices,
                                                      @NonNull GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices,
                                                      @NotNull MutableLiveData<Intent> getHiltMutableLiveDataPrices,
                                                      @NonNull LifecycleOwner lifecycleOwner,
                                                      @NonNull RecyclerView recycleview_comminingppricesNesteds) {
        // super();
        try{
            this.itemViewholderPrices=itemViewholderPrices;
            this.context=context;
            this.getAbsoluteAdapterPosition=getAbsoluteAdapterPosition;
            this.objectMapper=objectMapper;
            this.getHiltPublicId=getHiltPublicId;
            this.getHiltCommintgPrices=getHiltCommintgPrices;
            this.getLiveDataForrecyreViewPrices=getLiveDataForrecyreViewPrices;
            this.getHiltMutableLiveDataPrices = getHiltMutableLiveDataPrices;
            this.lifecycleOwner = lifecycleOwner;
            this.recycleview_comminingppricesNesteds = recycleview_comminingppricesNesteds;
            this.jsonNodeNested = jsonNodeNested;




        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                " jsonNodeNested.size() " + jsonNodeNested.size());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }


}


    @Override
    public void onBindViewHolder(@NonNull MyViewHoldersNestedCommintPrices holder, @NonNull int position, @NonNull List<Object> payloads) {
        try {
            // TODO: 28.12.2023 получаем позицию в recyreview

         Iterator<JsonNode> jsonNodeIterator= jsonNodeNested.elements();
            JsonNode jsonNodeNestedAfterIterator=    jsonNodeIterator.next();
           jsonNodeNestedRow= (JsonNode) jsonNodeNestedAfterIterator.get(position);


            super.onBindViewHolder(holder, position, payloads);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"jsonNodeNested "
                    + jsonNodeNested+ " position " +position);
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
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public void onViewRecycled(@NonNull MyViewHoldersNestedCommintPrices holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull MyViewHoldersNestedCommintPrices holder) {
        // TODO: 03.11.2023 Parent
        return super.onFailedToRecycleView(holder);

    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHoldersNestedCommintPrices holder) {
        super.onViewAttachedToWindow(holder);

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHoldersNestedCommintPrices holder) {
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
            Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
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
    public MyViewHoldersNestedCommintPrices onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewComminigPrices = null;
        try {

            if (jsonNodeNested.size()>0) {
       /*         viewComminigPrices = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.simple_for_commintgprices1, parent, false);*/
                /*viewComminigPrices = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.simple_for_complitingprices_grid1, parent, false);*/
          /*      viewComminigPrices = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.simple_for_complitingprices_grid3, parent, false);*/
                viewComminigPrices = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.simple_for_complitingprices_grid5, parent, false);
            } else {
                viewComminigPrices = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.simple_for_dont_commingprices, parent, false);
            }
            // TODO: 28.12.2023 получаем позицию в recyreview
            int  getPostionViewHolder=  getPostions();
            // TODO: 22.03.2022
            viewHoldersNested = new MyViewHoldersNestedCommintPrices(viewComminigPrices,context,getPostionViewHolder,jsonNodeNested);
            // TODO: 27.12.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "   viewComminigPrices" + viewComminigPrices
                    + " getPostionViewHolder " +getPostionViewHolder+" jsonNodeNestedRow " +jsonNodeNestedRow);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return viewHoldersNested;

    }

    private int getPostions() {
        int getPostionViewHolder = 0;
        try{
        if (viewHoldersNested!=null) {
            getPostionViewHolder=   viewHoldersNested.getAbsoluteAdapterPosition();
        }
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                + "   getPostionViewHolder" + getPostionViewHolder);

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  getPostionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoldersNestedCommintPrices holderNested, int position) {
        try {
            MaterialCardView cardview_commingprices_neasted=holderNested.itemView.findViewById(R.id.cardview_commingprices_neasted);

            ComponentsForRecyreViewNesteds componentsForRecyreViewNesteds=
                    new ComponentsForRecyreViewNesteds(holderNested,context,
                            position,cardview_commingprices_neasted,objectMapper,getHiltPublicId,
                            this,jsonNodeNested,getHiltCommintgPrices,
                            getLiveDataForrecyreViewPrices,
                            getHiltMutableLiveDataPrices,lifecycleOwner,recycleview_comminingppricesNesteds);
// TODO: 30.12.2023  запуск метода Сверху Сумма согласования цены
            if (jsonNodeNestedRow.size()>0) {

                componentsForRecyreViewNesteds.getmTV_commitingprices_count(jsonNodeNestedRow);

                // TODO: 30.12.2023 кнопка подтверждения  согласования цены указонной
                componentsForRecyreViewNesteds.getArrow_nested_receriview(position,jsonNodeNestedRow);

                // TODO: 30.12.2023 кнопка Номелклатура
                componentsForRecyreViewNesteds.getmTV_Nomenklatura(jsonNodeNestedRow);

                // TODO: 30.12.2023 кнопка ДДС
                componentsForRecyreViewNesteds.getmTV_StatyaDDS_value(jsonNodeNestedRow);

                // TODO: 30.12.2023 кнопка Единица измериния
                componentsForRecyreViewNesteds.getmTV_EdIzm_value(jsonNodeNestedRow);

                // TODO: 30.12.2023 кнопка Дата
                componentsForRecyreViewNesteds.getmTV_Data_value(jsonNodeNestedRow);

                // TODO: 30.12.2023 кнопка Количество
                componentsForRecyreViewNesteds.getmTV_Kolichestvo_value(jsonNodeNestedRow);

                // TODO: 30.12.2023 кнопка ЦФО Расчет
                componentsForRecyreViewNesteds.getmTV_CFORaskhoda_value(jsonNodeNestedRow);


                // TODO: 31.01.2024 3 New Poly
                componentsForRecyreViewNesteds. getmTV_Kontragent_value(jsonNodeNestedRow);

                componentsForRecyreViewNesteds.  getmTV_NumberRow_value(jsonNodeNestedRow);


                // TODO: 31.01.2024 Last Metod  !!!!!
                // TODO: 30.12.2023  rebbot Sxreen Recyreview
                componentsForRecyreViewNesteds.setagMaterialCardViewNestad(jsonNodeNestedRow);
                // TODO: 23.01.2024 кожа нет данных

                // TODO: 29.01.2024 мсобытие вент басс
                // TODO: 26.01.2024 end

                holderNested.setIsRecyclable(false);
            }

            Log.d(this.getClass().getName(), "\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " position " +position);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    public long getItemId(int position) {
        // TODO: 04.03.2022

        Log.i(this.getClass().getName(), "     getItemId holder.position " + position);

        return super.getItemId(position);

    }




    @Override
    public int getItemCount() {
        // TODO: 02.03.2022
        int КоличесвоСтрок = 0;
        try{
        if (jsonNodeNested!=null && jsonNodeNested.size()>0) {
            Iterator<JsonNode> jsonNodeIterator= jsonNodeNested.elements();
            JsonNode jsonNodeNestedAfterIterator=    jsonNodeIterator.next();
           КоличесвоСтрок = jsonNodeNestedAfterIterator.size();
            Log.d(this.getClass().getName(), "jsonNodeNested.size() " + jsonNodeNested.size() + " КоличесвоСтрок " +КоличесвоСтрок);
        }
        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                " jsonNodeNested.size() " + jsonNodeNested.size());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        // TODO: 28.02.2022
        return КоличесвоСтрок ;
    }
}//TODO  конец два22