package com.example.atlas_project.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.ScaleGestureDetectorCompat;
import androidx.core.view.ViewCompat;

public class zoom extends View {

    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private ScaleGestureDetector.OnScaleGestureListener mScaleGestureListener;
    private void init (){
        mScaleGestureListener
                = new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            /**
             * This is the active focal point in terms of the viewport. Could be a local
             * variable but kept here to minimize per-frame allocations.
             */
            private PointF viewportFocus = new PointF();
            private float lastSpanX;
            private float lastSpanY;

            // Detects that new pointers are going down.
            @Override
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                lastSpanX = scaleGestureDetector.
                        getCurrentSpanX();
                lastSpanY = scaleGestureDetector.
                        getCurrentSpanY();
                return true;
            }

            @Override
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {

                float spanX = scaleGestureDetector.getCurrentSpanX();
                float spanY = scaleGestureDetector.
                        getCurrentSpanY();

                float newWidth = lastSpanX / spanX * mCurrentViewport.width();
                float newHeight = lastSpanY / spanY * mCurrentViewport.height();

                float focusX = scaleGestureDetector.getFocusX();
                float focusY = scaleGestureDetector.getFocusY();
                // Makes sure that the chart point is within the chart region.
                // See the sample for the implementation of hitTest().
//                hitTest(scaleGestureDetector.getFocusX(),
//                        scaleGestureDetector.getFocusY(),
//                        viewportFocus);

                mCurrentViewport.set(
                        viewportFocus.x
                                - newWidth * (focusX - mContentRect.left)
                                / mContentRect.width(),
                        viewportFocus.y
                                - newHeight * (mContentRect.bottom - focusY)
                                / mContentRect.height(),
                        0,
                        0);
                mCurrentViewport.right = mCurrentViewport.left + newWidth;
                mCurrentViewport.bottom = mCurrentViewport.top + newHeight;

                // Invalidates the View to update the display.


                ViewCompat.postInvalidateOnAnimation(zoom.this);

                lastSpanX = spanX;
                lastSpanY = spanY;
                return true;
            }
        };
    }
    public zoom(Context context) {
        super(context);
init();
    }

    public zoom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    init();
    }

    public zoom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
init();
    }

    public zoom(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
init();
    }


    private RectF mCurrentViewport =
            new RectF(100, 100, 300, 300);
    private Rect mContentRect;
    private ScaleGestureDetector mScaleGestureDetector;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean retVal = mScaleGestureDetector.onTouchEvent(event);
      //  retVal = mGestureDetector.onTouchEvent(event) || retVal;
        return retVal || super.onTouchEvent(event);
    }

    /**
     * The scale listener, used for handling multi-finger scale gestures.
     */


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect(100,100,100+200,100+200) ;
        Paint paint = new Paint() ;
        paint.setColor(Color.GREEN);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        canvas.drawRect(rect ,paint);
        canvas.restore();
    }

}
