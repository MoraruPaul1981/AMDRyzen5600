package com.dsy.dsu.Documents1CApp.View.ComponentsUI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.dsy.dsu.Documents1CApp.View.MyRecycleViews.MyRecycleViewIsNull.MyRecycleViewCommitDocumentsIsNullAdapters;
import com.dsy.dsu.Documents1CApp.View.Window.FragmentCommitDocuments;
import com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.DizaynRecyreView.LeftDividerItemDecorator;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;


/**
 * Компоненты Фрагмента Согласование Документов
 */
public class InitiativeComponentCommitDocumentsUI extends FragmentCommitDocuments   {
  protected View view;
  protected  Activity activity;
  protected Context context;


  protected ObjectMapper getHiltJaksonObjectMapper;

  protected LifecycleOwner lifecycleOwner;
  // TODO: 07.03.2024 компонеты согсалованеи документов
  protected RecyclerView recycleview_commitdocument;
  protected MyRecycleViewCommitDocumentsIsNullAdapters myViewHoldersCommitDocumentsIsNull;
  protected  InitiativeComponentCommitDocumentsUI initiativeComponentCommitDocumentsUI;
  protected LiveData<Bundle> liveDataCommitDoc;


  public InitiativeComponentCommitDocumentsUI(@NonNull View view,
                                              @NonNull Activity activity,
                                              @NonNull Context context,
                                              @NonNull ObjectMapper getHiltJaksonObjectMapper,
                                              @NonNull LifecycleOwner lifecycleOwner,
                                              @NonNull LiveData<Bundle> liveDataCommitDoc) {
    this.view = view;
    this.activity = activity;
    this.context = context;
    this.getHiltJaksonObjectMapper = getHiltJaksonObjectMapper;
    this.lifecycleOwner = lifecycleOwner;
    this. initiativeComponentCommitDocumentsUI=this;
    this. liveDataCommitDoc=liveDataCommitDoc;
    Log.d(this.getClass().getName(),"\n"
            + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
  }


  @SuppressLint("RestrictedApi")
  public void inizualyzarWorkerUI(){
try{
  recycleview_commitdocument = view.findViewById(R.id.recycleview_commitdocument);

  // TODO: 07.03.2024   init Recyreviews
  new InizializayRecyreViews().startInitRecyreview();





  // TODO: 07.03.2024  запускаем NULL RecyreView первым
  InterfaceComponentDocumentsUI interfaceComponentDocumentsUI;
  if (myViewHoldersCommitDocumentsIsNull==null) {
    interfaceComponentDocumentsUI=new StartingIsNullRecyreViewDocumentsCommingOne();

  } else {
    // TODO: 07.03.2024 второй  раз Обновляем isnull recyreview
    interfaceComponentDocumentsUI=new StartingIsNullRecyreViewDocumentsCommingTwo();

  }
  interfaceComponentDocumentsUI.startIsNullRecyreView(myViewHoldersCommitDocumentsIsNull);

  Log.d(this.getClass().getName(),"\n"
            + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
  } catch (Exception e) {
    e.printStackTrace();
    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
  }
  }





  class   InizializayRecyreViews {
    public void startInitRecyreview() {
      try {
        recycleview_commitdocument.setHasFixedSize(true);
        recycleview_commitdocument.addItemDecoration(new LeftDividerItemDecorator(context));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview_commitdocument.setLayoutManager(linearLayoutManager);
        recycleview_commitdocument.requestLayout();
        recycleview_commitdocument.refreshDrawableState();
        // TODO: 28.02.2022
        Log.d(this.getClass().getName(), "\n" + " class "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

      } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
      }

    }
  }








// TODO: 07.03.2024

class  StartingIsNullRecyreViewDocumentsCommingOne implements InterfaceComponentDocumentsUI{


  @NonNull
  @Override
  public void startIsNullRecyreView(@NonNull MyRecycleViewCommitDocumentsIsNullAdapters myViewHoldersCommitDocumentsIsNull) {
    try {
      ArrayList<Boolean> arrayListIsNull1cData = new ArrayList<>();
      arrayListIsNull1cData.add(true);
      myViewHoldersCommitDocumentsIsNull = new MyRecycleViewCommitDocumentsIsNullAdapters(arrayListIsNull1cData,
              context ,initiativeComponentCommitDocumentsUI,  liveDataCommitDoc );
      myViewHoldersCommitDocumentsIsNull.notifyDataSetChanged();
      recycleview_commitdocument.setAdapter(myViewHoldersCommitDocumentsIsNull);
      recycleview_commitdocument.getAdapter().notifyDataSetChanged();

      Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
              " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
              " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "MyRecycleViewCommitDocumentsIsNullAdapters  "
              + myViewHoldersCommitDocumentsIsNull );
    } catch (Exception e) {
      e.printStackTrace();
      Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
              " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
      new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
              Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
  }
}


  class  StartingIsNullRecyreViewDocumentsCommingTwo implements InterfaceComponentDocumentsUI{


    @NonNull
    @Override
    public void startIsNullRecyreView(@NonNull MyRecycleViewCommitDocumentsIsNullAdapters myViewHoldersCommitDocumentsIsNull) {
      try {
        ArrayList<Boolean> arrayListIsNull1cData = new ArrayList<>();
        arrayListIsNull1cData.add(false);
        myViewHoldersCommitDocumentsIsNull.arrayListIsNull1cData=arrayListIsNull1cData;
        myViewHoldersCommitDocumentsIsNull.notifyDataSetChanged();
        RecyclerView.Adapter recyclerViewОбновление=         recycleview_commitdocument.getAdapter();
        recycleview_commitdocument.swapAdapter(recyclerViewОбновление,true);
        recycleview_commitdocument.getAdapter().notifyDataSetChanged();

        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "MyRecycleViewCommitDocumentsIsNullAdapters  "
                + myViewHoldersCommitDocumentsIsNull );
      } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
      }
    }
  }





}
