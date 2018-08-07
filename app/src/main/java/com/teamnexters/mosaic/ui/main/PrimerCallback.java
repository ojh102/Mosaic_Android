package com.teamnexters.mosaic.ui.main;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.teamnexters.mosaic.ui.main.CardConfig.MAX_SHOW_COUNT;
import static com.teamnexters.mosaic.ui.main.CardConfig.SCALE_GAP;
import static com.teamnexters.mosaic.ui.main.CardConfig.TRANS_Y_GAP;

public class PrimerCallback extends CardCallback {
    private static final int MAX_ROTATION = 15;

    private int mVerticalDeviation;

    private boolean isTopSwipe;

    private List prevDatas = new ArrayList();

    public PrimerCallback(RecyclerView rv, RecyclerView.Adapter adapter, List datas) {
        super(rv, adapter, datas);
        mVerticalDeviation = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, mRv.getContext().getResources().getDisplayMetrics());
    }

    public PrimerCallback(int dragDirs, int swipeDirs, RecyclerView rv, RecyclerView.Adapter adapter, List datas) {
        super(dragDirs, swipeDirs, rv, adapter, datas);
    }

    public int getHorizontalDeviation() {
        return mVerticalDeviation;
    }

    public PrimerCallback setHorizontalDeviation(int horizontalDeviation) {
        mVerticalDeviation = horizontalDeviation;
        return this;
    }

    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        if (isTopViewCenterInVertical(viewHolder.itemView)) {
            return Float.MAX_VALUE;
        }
        return super.getSwipeThreshold(viewHolder);
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        View topView = mRv.getChildAt(mRv.getChildCount() - 1);
        if (isTopViewCenterInVertical(topView)) {
            return Float.MAX_VALUE;
        }
        return super.getSwipeEscapeVelocity(defaultValue);
    }

    @Override
    public float getSwipeVelocityThreshold(float defaultValue) {
        View topView = mRv.getChildAt(mRv.getChildCount() - 1);
        if (isTopViewCenterInVertical(topView)) {
            return Float.MAX_VALUE;
        }
        return super.getSwipeVelocityThreshold(defaultValue);
    }

    public boolean isTopViewCenterInVertical(View topView) {
        Log.d("TAG", "getSwipeThreshold() called with: viewHolder.itemView.getY() = [" + topView.getY() + "]");
        Log.d("TAG", "getSwipeThreshold() called with:  viewHolder.itemView.getHeight() / 2  = [" + topView.getHeight() / 2 + "]");
        Log.d("TAG", "getSwipeThreshold() called with:  mRv.getY() = [" + mRv.getY() + "]");
        Log.d("TAG", "getSwipeThreshold() called with:  mRv.getHeight() / 2 = [" + mRv.getHeight() / 2 + "]");

        return Math.abs(mRv.getHeight() / 2 - topView.getY() - (topView.getHeight() / 2)) < mVerticalDeviation;
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (isTopSwipe) {
            Object data = mDatas.remove(viewHolder.getLayoutPosition());
            prevDatas.add(data);
            ((CardAdapter) mAdapter).removeAt(viewHolder.getLayoutPosition());

            Toast.makeText(mRv.getContext(), "top", Toast.LENGTH_SHORT).show();
        } else {
            super.onSwiped(viewHolder, direction);

            Toast.makeText(mRv.getContext(), "bottom", Toast.LENGTH_SHORT).show();
        }

        viewHolder.itemView.setRotation(0);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        Log.e("swipecard", "onChildDraw()  viewHolder = [" + viewHolder + "], dX = [" + dX + "], dY = [" + dY + "], actionState = [" + actionState + "], isCurrentlyActive = [" + isCurrentlyActive + "]");

        double swipeValue = Math.sqrt(dX * dX + dY * dY);
        double fraction = swipeValue / getThreshold(viewHolder);

        if (fraction > 1) {
            fraction = 1;
        }

        int childCount = recyclerView.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = recyclerView.getChildAt(i);

            int level = childCount - i - 1;
            if (level > 0) {
                child.setScaleX((float) (1 - SCALE_GAP * level + fraction * SCALE_GAP));

                if (level < MAX_SHOW_COUNT - 1) {
                    child.setScaleY((float) (1 - SCALE_GAP * level + fraction * SCALE_GAP));
                    child.setTranslationY((float) (TRANS_Y_GAP * level - fraction * TRANS_Y_GAP));
                } else {

                }

            } else {
                float xFraction = dX / getThreshold(viewHolder);

                if (xFraction > 1) {
                    xFraction = 1;
                } else if (xFraction < -1) {
                    xFraction = -1;
                }

                child.setRotation(xFraction * MAX_ROTATION);
            }
        }

        float v = mRv.getHeight() / 2 - viewHolder.itemView.getY() - (viewHolder.itemView.getHeight() / 2);

        if (v > 0) {
            isTopSwipe = true;
        } else if (v < 0) {
            isTopSwipe = false;
        }
    }
}