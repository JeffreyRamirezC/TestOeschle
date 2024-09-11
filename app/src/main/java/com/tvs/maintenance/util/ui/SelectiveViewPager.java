package com.tvs.maintenance.util.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class SelectiveViewPager extends ViewPager {
    private boolean paging = true;
    private int min_distance = 100;
    private float downX, downY, upX, upY;

    public SelectiveViewPager(Context context) {
        super(context);
    }

    public SelectiveViewPager(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!paging) {
            return false;
        }  else  return super.onTouchEvent(ev);

        //return true;
    }
    private boolean isSwipe(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_UP: {
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                //HORIZONTAL SCROLL
                if(Math.abs(deltaX) > Math.abs(deltaY))
                {
                    if(Math.abs(deltaX) > min_distance){
                        // left or right
                        return false;
                    }
                    else {
                        //not long enough swipe...
                        return true;
                    }
                }
                //VERTICAL SCROLL
                else
                {
                    if(Math.abs(deltaY) > min_distance){
                        return true;
                    }
                    else {
                        //not long enough swipe...
                        return true;
                    }
                }


            }
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (!paging) {
            return super.onInterceptTouchEvent(e);
        }else return false;

        //return true;
    }

    public void setPaging(boolean p){ paging = p; }

}

