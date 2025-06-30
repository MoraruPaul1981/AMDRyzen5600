









package com.dsy.dsu.PaysCommitApp.View.RecyreViewIsNull;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;

import java.util.ArrayList;
import java.util.List;

// TODO: 09.11.2023 ВТОРОЯ Rereview
public class MyRecycleViewIsNullAdapterPay extends RecyclerView.Adapter<  MyViewHolderNULLPay> {
  public ArrayList<Boolean> arrayListIsNull1cData=new ArrayList<>();
    Context context;


    MyViewHolderNULLPay myViewHolderNULLPay;

    public MyRecycleViewIsNullAdapterPay(ArrayList<Boolean> arrayListIsNull1cData, Context context ) {
        this.arrayListIsNull1cData = arrayListIsNull1cData;
        this.context = context;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolderNULLPay holder, @NonNull int position, @NonNull List<Object> payloads) {
        Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position);
        try {
            // TODO: 15.01.2024  отправляем для  получени данных

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"arrayListIsNull1cData "
                    + arrayListIsNull1cData + " position " +position);
            // TODO: 30.03.2022
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        super.onBindViewHolder(holder, position, payloads);
    }







    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolderNULLPay holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull MyViewHolderNULLPay holder) {
        // TODO: 03.11.2023 Parent
        return super.onFailedToRecycleView(holder);

    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolderNULLPay holder) {
        super.onViewAttachedToWindow(holder);

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolderNULLPay holder) {
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
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }

        return super.getItemViewType(position);
    }


    @NonNull
    @Override
    public MyViewHolderNULLPay onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewГлавныйВидДляRecyclleViewДляСогласованияISNull = null;
        try {
            Boolean СтатусЗагрузки=    arrayListIsNull1cData.stream().findFirst().get();

            if (СтатусЗагрузки ) {
                viewГлавныйВидДляRecyclleViewДляСогласованияISNull = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.simple_for_commitpay_cardview1empty_in_prossering, parent, false);

            }else {
                viewГлавныйВидДляRecyclleViewДляСогласованияISNull = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.simple_for_commitpay_dont_jsonot1c, parent, false);

            }


            myViewHolderNULLPay = new  MyViewHolderNULLPay(viewГлавныйВидДляRecyclleViewДляСогласованияISNull,context);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "   viewГлавныйВидДляRecyclleViewДляСогласованияISNull" + viewГлавныйВидДляRecyclleViewДляСогласованияISNull);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros( context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return myViewHolderNULLPay;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderNULLPay holder, int position) {
        try {

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
        int КоличесвоСтрок;
        if (arrayListIsNull1cData.size()>0) {
            КоличесвоСтрок = arrayListIsNull1cData.size();
            Log.d(this.getClass().getName(), "arrayListIsNull1cData.size() " + arrayListIsNull1cData.size() + " КоличесвоСтрок " +КоличесвоСтрок);
        } else {
            КоличесвоСтрок=1;
            Log.d(this.getClass().getName(), "arrayListIsNull1cData.size() " + arrayListIsNull1cData.size() + " холостой ход КоличесвоСтрок " +КоличесвоСтрок);
        }
        Log.d(this.getClass().getName(), "arrayListIsNull1cData.size() " + arrayListIsNull1cData.size() + " КоличесвоСтрок " +КоличесвоСтрок);

        // TODO: 28.02.2022
        return КоличесвоСтрок ;
    }
}//TODO  конец два22
