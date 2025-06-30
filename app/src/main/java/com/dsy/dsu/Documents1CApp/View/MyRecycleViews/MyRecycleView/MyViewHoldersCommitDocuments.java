package com.dsy.dsu.Documents1CApp.View.MyRecycleViews.MyRecycleView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.Documents1CApp.View.ComponentsUI.InitiativeComponentCommitDocumentsUI;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationBarView;

import org.jetbrains.annotations.NotNull;

// TODO: 28.02.2022 начало  MyViewHolderДляЧата
public class MyViewHoldersCommitDocuments extends RecyclerView.ViewHolder {// TODO: 28.02.2022 начало  MyViewHolderДляЧата
    // TODO: 28.02.2022

    public Context context;
    public View itemView;
    public   JsonNode jsonNode;
    public   int getAbsoluteAdapterPosition;


    protected RecyclerView recycleview_commitdocument;
    protected ProgressBar prograessbar_commitdocument;
    protected androidx.appcompat.widget.SearchView searchview_commitdocument;
    protected BottomNavigationView bottomnavigationw_commitdocument;
    protected BottomNavigationItemView bottomNavigationBack;
    protected BottomNavigationItemView bottomNavigationAsync;
    protected BottomNavigationItemView bottomNavigationSearch;
    protected MaterialCardView fragment_materialmardview_commit_documents;
    protected InitiativeComponentCommitDocumentsUI initiativeComponentCommitDocumentsUI;




    // TODO: 02.03.2022
    public MyViewHoldersCommitDocuments(@NonNull View itemView,
                                        @NotNull Context context,
                                        @NotNull int getAbsoluteAdapterPosition,
                                        @NotNull InitiativeComponentCommitDocumentsUI initiativeComponentCommitDocumentsUI) {
        super(itemView);
        try{
            this.itemView=itemView;
            this.context=context;
            this.getAbsoluteAdapterPosition=getAbsoluteAdapterPosition;
            this.initiativeComponentCommitDocumentsUI=initiativeComponentCommitDocumentsUI;

            // TODO: 07.03.2024  метод Инициализации Главных компоненом RecyreView Document Comming
            initMainComponetRecyreViewDocComming(itemView);


            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+ " jsonNode " +jsonNode);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @SuppressLint("RestrictedApi")
    private void initMainComponetRecyreViewDocComming(@NonNull View itemView) {
        try{
        fragment_materialmardview_commit_documents = itemView.findViewById(R.id.fragment_materialmardview_commit_documents);
        recycleview_commitdocument = itemView.findViewById(R.id.recycleview_commitdocument);
        prograessbar_commitdocument = itemView.findViewById(R.id.prograessbar_commitdocument);
        searchview_commitdocument =   itemView.findViewById(R.id.searchview_commitdocument);


        bottomnavigationw_commitdocument = itemView.findViewById(R.id.bottomnavigationw_commitdocument);
        bottomnavigationw_commitdocument.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_UNLABELED);
        bottomNavigationBack = bottomnavigationw_commitdocument.findViewById(R.id.bottomNavigationBack);
        bottomNavigationBack.setTitle("Выйти");
        bottomNavigationAsync = bottomnavigationw_commitdocument.findViewById(R.id.bottomNavigationAsync);
        bottomNavigationAsync.setTitle("Обновить");
        bottomNavigationSearch = bottomnavigationw_commitdocument.findViewById(R.id.bottomNavigationSearch);
        bottomNavigationSearch.setTitle("Поиск");

        bottomNavigationSearch.setEnabled(false);
        bottomNavigationSearch.setClickable(false);
            // TODO: 07.03.2024
        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+ " jsonNode " +jsonNode);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

} // TODO: 28.02.2022 конец  MyViewHolderДляЧата