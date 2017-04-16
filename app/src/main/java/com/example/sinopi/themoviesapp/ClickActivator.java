package com.example.sinopi.themoviesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by sinopi on 4/15/17.
 */

public class ClickActivator implements RecyclerView.OnItemTouchListener{

    private OnItemClickListener mListener;
    private GestureDetector mGestureDetector;
    public ClickActivator(Context context, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(
                context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        }
        );
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent e) {

        View child = recyclerView.findChildViewUnder(e.getX(),
                e.getY());
        if (child != null && mListener != null &mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(child, recyclerView.
                    getChildAdapterPosition(child));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
}


