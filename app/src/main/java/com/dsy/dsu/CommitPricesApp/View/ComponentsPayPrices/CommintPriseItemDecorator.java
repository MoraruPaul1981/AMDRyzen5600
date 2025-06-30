package com.dsy.dsu.CommitPricesApp.View.ComponentsPayPrices;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;

public class CommintPriseItemDecorator extends RecyclerView.ItemDecoration {
    private final Drawable mDivider;
    private final Rect mBounds = new Rect();
    private final Context mContext;

    public CommintPriseItemDecorator(Context context) {
        mContext = context;
        mDivider = context.getResources().getDrawable(R.drawable.car_ui_recyclerview_divider);
    }

    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() != null && mDivider != null) {
            // TODO: 25.03.2024
            drawDivider(c, parent);

        }
    }

    private void drawDivider(Canvas canvas, RecyclerView parent) {
        try{
        canvas.save();

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; ++i) {
            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);

            int childAdapterPosition = parent.getChildAdapterPosition(child);


            if (mDivider == null) {
                // Draw left vertical divider
                mDivider.setBounds(
                        0,
                        0,
                        0,
                        0);
                canvas.drawColor(Color.WHITE);
            } else {
                // Draw left vertical divider
                mDivider.setBounds(
                        0,
                        5,
                        0,
                        15);
                canvas.drawColor(Color.parseColor("#B8D8EE"));
            }

          //  canvas.drawColor(Color.WHITE);
            mDivider.draw(canvas);
        }

        canvas.restore();



    } catch (Exception e) {
        e.printStackTrace();
        Log.e(parent.getContext().getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(parent.getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }


    }



}