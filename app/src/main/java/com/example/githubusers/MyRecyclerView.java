package com.example.githubusers;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Chudofom on 01.08.16.
 */
public class MyRecyclerView extends RecyclerView {
    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
        int lastVisibleView = linearLayoutManager.findLastVisibleItemPosition();
        int firstVisibleView = linearLayoutManager.findFirstVisibleItemPosition();
        View firstView = linearLayoutManager.findViewByPosition(firstVisibleView);
        View lastView = linearLayoutManager.findViewByPosition(lastVisibleView);
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        int topMargin = (screenHeight - lastView.getHeight()) / 2;
        int bottomMargin = (screenHeight - firstView.getHeight()) / 2 + firstView.getHeight();
        int topEdge = lastView.getTop();
        int bottomtEdge = firstView.getBottom();
        int scrollDistanceTop = topEdge - topMargin;
        int scrollDistanceBottom = bottomMargin - bottomtEdge;

        if (velocityY > 0) smoothScrollBy(0, scrollDistanceTop);
        else smoothScrollBy(0, -scrollDistanceBottom);

        return true;
    }
}
