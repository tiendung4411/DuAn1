package com.example.duan1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DraggableFloatingActionButton extends FloatingActionButton implements View.OnTouchListener {

    private final static float CLICK_DRAG_TOLERANCE = 10;

    private float downRawX, downRawY;
    private float dX, dY;

    public DraggableFloatingActionButton(Context context) {
        super(context);
        init();
    }

    public DraggableFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DraggableFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int viewWidth = view.getWidth();
        float newX = 0; // Declare newX here

        int action = motionEvent.getAction();
        if (action == MotionEvent.ACTION_DOWN) {

            downRawX = motionEvent.getRawX();
            downRawY = motionEvent.getRawY();
            dX = view.getX() - downRawX;
            dY = view.getY() - downRawY;

            return false;

        } else if (action == MotionEvent.ACTION_MOVE) {

            newX = motionEvent.getRawX() + dX; // Assign value to newX
            float newY = motionEvent.getRawY() + dY;

            view.animate()
                    .x(newX)
                    .y(newY)
                    .setDuration(0)
                    .start();

            return true;

        } else if (action == MotionEvent.ACTION_UP) {

            float upRawX = motionEvent.getRawX();
            float upRawY = motionEvent.getRawY();

            float upDX = upRawX - downRawX;
            float upDY = upRawY - downRawY;

            View viewParent = (View) view.getParent();
            int parentWidth = viewParent.getWidth();

            if (upRawX > (parentWidth - viewWidth) / 2) {
                newX = parentWidth - viewWidth - layoutParams.rightMargin; // Update value of newX
            } else {
                newX = layoutParams.leftMargin; // Update value of newX
            }

            view.animate()
                    .x(newX)
                    .setInterpolator(new OvershootInterpolator())
                    .setDuration(300)
                    .start();

            if (Math.abs(upDX) < CLICK_DRAG_TOLERANCE && Math.abs(upDY) < CLICK_DRAG_TOLERANCE) {
                return false; // Not consumed (considered a click)
            } else {
                return true; // Consumed (considered a drag)
            }

        } else {
            return super.onTouchEvent(motionEvent);
        }
    }
}
