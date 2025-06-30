package com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas.DizaynRecyreView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.R;

public class LeftDividerItemDecorator extends RecyclerView.ItemDecoration {
    private final Drawable mDivider;
    private final Rect mBounds = new Rect();
    private final Context mContext;

    public LeftDividerItemDecorator(Context context) {
        mContext = context;
        mDivider = context.getResources().getDrawable(R.drawable.car_ui_recyclerview_divider);
    }

    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() != null && mDivider != null) {
            // TODO: 25.03.2024
            drawLeftDivider(c, parent);
            
        }
    }

    private void drawLeftDivider(Canvas canvas, RecyclerView parent) {
        canvas.save();

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; ++i) {
            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);

            int childAdapterPosition = parent.getChildAdapterPosition(child);

            int left = parent.getPaddingLeft();

            // Solid size according to divider.xml width
            //int right = left + (mDivider.getIntrinsicWidth());

            // Dynamic size according to divider.xml width multiplied by child number
            int right = left + (mDivider.getIntrinsicWidth() * (childAdapterPosition + 1));

            int top = child.getTop();
            int bottom = child.getBottom();

            // Draw left vertical divider
            mDivider.setBounds(
                    left,
                    top,
                    right,
                    bottom
            );
            canvas.drawColor(Color.WHITE);
            mDivider.draw(canvas);
        }

        canvas.restore();
    }

    // Handles dividers width - move current views to right
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (mDivider == null) {
            outRect.set(0, 0, 0, 0);
        } else {
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            outRect.set(  0, 5, 0, 15);
        }


    }

}