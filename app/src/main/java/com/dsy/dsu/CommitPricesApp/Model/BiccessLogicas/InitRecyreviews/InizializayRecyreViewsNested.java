package com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.InitRecyreviews;

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.DizaynRecyreView.LeftDividerItemDecoratorNested;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

public class InizializayRecyreViewsNested {

    RecyclerView recycleview_comminingppricesNesteds;
    private Context context;
    private Animation animationДляСогласования;

    public InizializayRecyreViewsNested(RecyclerView recycleview_comminingppricesNesteds, Context context ) {
        this.recycleview_comminingppricesNesteds = recycleview_comminingppricesNesteds;
        this.context = context;
    }

    public void startInitRecyreview() {
        try {
         //   animationДляСогласования= AnimationUtils.loadAnimation(context, R.anim.slide_in_row);//R.anim.layout_animal_commit
        /*    DividerItemDecoration itemDecoration =
                    new DividerItemDecoration(recycleview_comminingppricesNesteds.getContext(), DividerItemDecoration.HORIZONTAL);
            GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{0xfff7f7f7, 0xfff7f7f7});
            drawable.setSize(1,1);*/

            recycleview_comminingppricesNesteds.addItemDecoration(new LeftDividerItemDecoratorNested(context));
              GridLayoutManager   layoutManager = new GridLayoutManager(context, 1,GridLayoutManager.VERTICAL,false);
            layoutManager.setSpanSizeLookup(new GridLayoutManager.DefaultSpanSizeLookup());



            recycleview_comminingppricesNesteds.setHasFixedSize(true);
           // LinearLayoutManager  layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            layoutManager.setItemPrefetchEnabled(true);
            layoutManager.setSmoothScrollbarEnabled(true);
            recycleview_comminingppricesNesteds.setLayoutManager( layoutManager);
            recycleview_comminingppricesNesteds.requestLayout();
            recycleview_comminingppricesNesteds.refreshDrawableState();
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
