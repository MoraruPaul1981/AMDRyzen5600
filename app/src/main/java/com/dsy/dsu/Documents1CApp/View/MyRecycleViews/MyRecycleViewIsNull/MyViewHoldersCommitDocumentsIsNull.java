package com.dsy.dsu.Documents1CApp.View.MyRecycleViews.MyRecycleViewIsNull;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.Documents1CApp.View.ComponentsUI.InitiativeComponentCommitDocumentsUI;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import org.jetbrains.annotations.NotNull;

// TODO: 28.02.2022 начало  MyViewHolderДляЧата
public class MyViewHoldersCommitDocumentsIsNull extends RecyclerView.ViewHolder {// TODO: 28.02.2022 начало  MyViewHolderДляЧата
    // TODO: 28.02.2022
    protected  Context context;

    protected  InitiativeComponentCommitDocumentsUI initiativeComponentCommitDocumentsUI;
    // TODO: 02.03.2022
    @SuppressLint("RestrictedApi")
    public MyViewHoldersCommitDocumentsIsNull(@NonNull View itemView,
                                              @NotNull Context context,
                                              @NonNull InitiativeComponentCommitDocumentsUI initiativeComponentCommitDocumentsUI) {
        super(itemView);
        try{
            this.context=context;
            this.initiativeComponentCommitDocumentsUI=initiativeComponentCommitDocumentsUI;
            // TODO: 02.03.2022
            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " initiativeComponentCommitDocumentsUI " +initiativeComponentCommitDocumentsUI);
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