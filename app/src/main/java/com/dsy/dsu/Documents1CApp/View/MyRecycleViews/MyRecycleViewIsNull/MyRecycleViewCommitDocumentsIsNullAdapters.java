package com.dsy.dsu.Documents1CApp.View.MyRecycleViews.MyRecycleViewIsNull;// TODO: 27.12.2023 Recyreview is null

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.Documents1CApp.View.ComponentsUI.InitiativeComponentCommitDocumentsUI;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

// TODO: 09.11.2023 ВТОРОЯ Rereview
public  class MyRecycleViewCommitDocumentsIsNullAdapters extends RecyclerView.Adapter<MyViewHoldersCommitDocumentsIsNull> {
    public ArrayList<Boolean> arrayListIsNull1cData=new ArrayList<>();
  private   Context context;
  private MyViewHoldersCommitDocumentsIsNull viewHoldersCommitDocumentsIsNull;
  private  InitiativeComponentCommitDocumentsUI initiativeComponentCommitDocumentsUI;
  private  LiveData<Bundle> liveDataCommitDoc;

    @SuppressLint("SuspiciousIndentation")
    public MyRecycleViewCommitDocumentsIsNullAdapters(@NotNull ArrayList<Boolean>arrayListIsNull1cData,
                                                      @NotNull Context context,
                                                      @NonNull InitiativeComponentCommitDocumentsUI  initiativeComponentCommitDocumentsUI,
                                                      @NonNull LiveData<Bundle> liveDataCommitDoc) {
        // super();
        try{
        this.arrayListIsNull1cData = arrayListIsNull1cData;
        this.context = context;
        this.initiativeComponentCommitDocumentsUI = initiativeComponentCommitDocumentsUI;
        this.liveDataCommitDoc = liveDataCommitDoc;
            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " arrayListIsNull1cData.size() " + arrayListIsNull1cData.size());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHoldersCommitDocumentsIsNull holder, @NonNull int position, @NonNull List<Object> payloads) {
        try {

            // TODO: 07.03.2024  запускаем получение настоящего запуск получение данных и RecyreView заполенный



            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"arrayListIsNull1cData "
                    + arrayListIsNull1cData + " position " +position
                    + " initiativeComponentCommitDocumentsUI "+ initiativeComponentCommitDocumentsUI);
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
    public void onViewRecycled(@NonNull MyViewHoldersCommitDocumentsIsNull holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull MyViewHoldersCommitDocumentsIsNull holder) {
        // TODO: 03.11.2023 Parent
        return super.onFailedToRecycleView(holder);

    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHoldersCommitDocumentsIsNull holder) {
        super.onViewAttachedToWindow(holder);

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHoldersCommitDocumentsIsNull holder) {
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


    @SuppressLint("SuspiciousIndentation")
    @NonNull
    @Override
    public MyViewHoldersCommitDocumentsIsNull onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewRecyreViewCommitDocumentsISNull = null;
        try {
// TODO: 27.12.2023 загрузка когда нет данных
        Boolean СтатусЗагрузкиCommitDocuments=    arrayListIsNull1cData.stream().findFirst().get();

          if (СтатусЗагрузкиCommitDocuments ) {
            viewRecyreViewCommitDocumentsISNull = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.simple_for_commitdocuments_cardview1empty_in_prossering, parent, false);

              Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                      + "   viewRecyreViewCommitDocumentsISNull" + viewRecyreViewCommitDocumentsISNull);
        }else {

            viewRecyreViewCommitDocumentsISNull = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.simple_for_commitdocument_dont_jsonot1c, parent, false);

              Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                      + "   viewRecyreViewCommitDocumentsISNull" + viewRecyreViewCommitDocumentsISNull +
                       " СтатусЗагрузкиCommitDocuments " +СтатусЗагрузкиCommitDocuments);
            }
            // TODO: 22.03.2022  is nullrecyreview Commting Documnens
            viewHoldersCommitDocumentsIsNull = new MyViewHoldersCommitDocumentsIsNull(viewRecyreViewCommitDocumentsISNull,context,
                    initiativeComponentCommitDocumentsUI);


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "   viewRecyreViewCommitDocumentsISNull" + viewRecyreViewCommitDocumentsISNull);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return viewHoldersCommitDocumentsIsNull;

    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyViewHoldersCommitDocumentsIsNull holder, int position) {
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
        int КоличесвоСтрок = 0;
        try{
        if (arrayListIsNull1cData.size()>0) {
            КоличесвоСтрок = arrayListIsNull1cData.size();
            Log.d(this.getClass().getName(), "arrayListIsNull1cData.size() " + arrayListIsNull1cData.size() + " КоличесвоСтрок " +КоличесвоСтрок);
        }
        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                " КоличесвоСтрок " + КоличесвоСтрок);
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