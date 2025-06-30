package com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitPricesApp.View.MyRecycleViewNested.MyRecycleViewIsAdaptersNestedCommintPrices;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.databind.JsonNode;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;



@Module
@InstallIn(SingletonComponent.class)
public class RebootRecyreViewNested {

Context context;

    public  @Inject RebootRecyreViewNested(@ApplicationContext Context context) {
        //TODO сомо имя json
        this.context=context;
    }

    public void методRebootRecyreViewComminPrices(@NonNull JsonNode jsonNode1сСогласованияЦен
            ,@NonNull MyRecycleViewIsAdaptersNestedCommintPrices myRecycleViewIsAdaptersNestedCommintPrices,
                                                  @NotNull RecyclerView recycleview_comminingppricesNesteds) {

        try{
            myRecycleViewIsAdaptersNestedCommintPrices.jsonNodeNested=jsonNode1сСогласованияЦен;
            myRecycleViewIsAdaptersNestedCommintPrices.notifyDataSetChanged();
            RecyclerView.Adapter recyclerViewОбновление=         recycleview_comminingppricesNesteds.getAdapter();
            recycleview_comminingppricesNesteds.swapAdapter(recyclerViewОбновление,true);
            recycleview_comminingppricesNesteds.getAdapter().notifyDataSetChanged();
            // TODO: 24.01.2024
            recycleview_comminingppricesNesteds.requestLayout();
            recycleview_comminingppricesNesteds .refreshDrawableState();
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " jsonNode1сСогласованияЦен " +jsonNode1сСогласованияЦен);
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
