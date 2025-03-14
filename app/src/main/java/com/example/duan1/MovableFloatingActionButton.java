package com.example.duan1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MovableFloatingActionButton extends FloatingActionButton implements View.OnTouchListener {

    private final static float CLICK_DRAG_TOLERANCE = 10;
    private static final int BOTTOM_LIMIT_DP = 550; // 550dp limit from the bottom

    private float downRawX, downRawY;
    private float dX, dY;

    public MovableFloatingActionButton(Context context) {
        super(context);
        init();
    }

    public MovableFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MovableFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

        int action = motionEvent.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            downRawX = motionEvent.getRawX();
            downRawY = motionEvent.getRawY();
            dX = view.getX() - downRawX;
            dY = view.getY() - downRawY;
            return true; // Consumed
        } else if (action == MotionEvent.ACTION_MOVE) {
            int viewWidth = view.getWidth();
            int viewHeight = view.getHeight();

            View viewParent = (View) view.getParent();
            int parentWidth = viewParent.getWidth();
            int parentHeight = viewParent.getHeight();

            float newX = motionEvent.getRawX() + dX;
            newX = Math.max(layoutParams.leftMargin, newX);
            newX = Math.min(parentWidth - viewWidth - layoutParams.rightMargin, newX);

            float newY = motionEvent.getRawY() + dY;
            newY = Math.max(layoutParams.topMargin, newY);

            // Convert BOTTOM_LIMIT_DP to pixels and apply the bottom limit constraint
            int bottomLimitPx = (int) (BOTTOM_LIMIT_DP * getResources().getDisplayMetrics().density);
            newY = Math.min(parentHeight - viewHeight - layoutParams.bottomMargin - bottomLimitPx, newY);

            // Ensure the view stays within the parent boundaries
            newY = Math.min(parentHeight - viewHeight, newY);
            newY = Math.max(0, newY);

            view.animate()
                    .x(newX)
                    .y(newY)
                    .setDuration(0)
                    .start();

            return true; // Consumed
        } else if (action == MotionEvent.ACTION_UP) {
            float upRawX = motionEvent.getRawX();
            float upRawY = motionEvent.getRawY();

            float upDX = upRawX - downRawX;
            float upDY = upRawY - downRawY;

            if (Math.abs(upDX) < CLICK_DRAG_TOLERANCE && Math.abs(upDY) < CLICK_DRAG_TOLERANCE) {
                return performClick(); // A click
            } else {
                return true; // A drag
            }
        } else {
            return super.onTouchEvent(motionEvent);
        }
    }
}
