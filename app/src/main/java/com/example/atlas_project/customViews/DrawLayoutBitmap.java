package com.example.atlas_project.customViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;



import static java.lang.Integer.parseInt;


public class DrawLayoutBitmap extends View  {


    private float startx , starty  , endx , endy , diffx, diffy   ;
    private ScaleGestureDetector mScaleDetector;
    private int x,y ;
    private enum STATE{pan , zoom ,none} ;
    private STATE state = STATE.none;
    private float prediffx =0 ;
    private float prediffy =0 ;
    private DrawLayout customBitmap ;
    private Location location ;
    public static DrawLayoutBitmap d =null ;
    public static DrawLayoutBitmap get(){
        return d;
    }
    public DrawLayoutBitmap(Context context) {
        super(context);
        init(null ,context);
    }

    public DrawLayoutBitmap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs ,context);
    }

    public DrawLayoutBitmap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs ,context);
    }
    public void redraw(){
        invalidate();
        requestLayout();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int scaledwidth = Math.round(2880 * mScaleFactor);
        int scaledheight = Math.round(1440 * mScaleFactor);

        setMeasuredDimension(
                Math.min(scaledwidth ,width) ,
                Math.min(scaledheight , height)
        );
    }
    private Bitmap bitmap ;
    private float mScaleFactor = 1.f;
    public void init(@Nullable AttributeSet at , Context context) {
        InitializeGraph.getInstance(5 ,11) ;
        startx = 0;
        starty = 0;
        endx = 0;
        endy =0 ;
        diffx =0 ;
        diffy =0 ;
        x=0;
        y=0;
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        location = Location.getInstance("53 AA1" , "53 AA");
        customBitmap = new DrawLayout() ;
        customBitmap.setDestination(Location.getSudo_destination());
        customBitmap.setSource(Location.getSudo_source());
        customBitmap.setNo_of_segments(5);
        customBitmap.setNumber_of_racks(11);
        customBitmap.setBitmap();
        bitmap = customBitmap.getBitmap() ;
        d =this ;



    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.5f, Math.min(mScaleFactor, 3.0f));


            return true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {





        Paint paint = new Paint() ;
        paint.setColor(Color.BLUE);

        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor );
//        if( (diffx * -1) < 0 ){
//            diffx = 0;
//        }
//
//        else if( (diffx * -1) > (2880 * mScaleFactor)- getWidth() ){
//            diffx = ((2880 * mScaleFactor)- getWidth())*-1;
//        }
//        if( (diffy * -1) < 0 ){
//            diffy = 0;
//        }
//        else if( (diffy * -1) > (1400 * mScaleFactor)- getHeight() ){
//            diffy = ((1400 * mScaleFactor)- getHeight())*-1;
//        }
        canvas.translate(diffx / mScaleFactor , diffy / mScaleFactor);
        canvas.drawRect(100 ,100 ,200 ,200 ,paint);
        canvas.drawBitmap(bitmap , 0 , 0 , paint);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

            mScaleDetector.onTouchEvent(event);
        switch(action & MotionEvent.ACTION_MASK) {
            case (MotionEvent.ACTION_DOWN) :
             //   Log.d(DEBUG_TAG,"Action was DOWN");
             //   Log.d(DEBUG_TAG , "On-touch    " + event.getX() + "   " +event.getY() ) ;
                startx = event.getX() - prediffx ;
                starty = event.getY() - prediffy;
                state = STATE.pan ;
                break;
            case (MotionEvent.ACTION_MOVE) :
               // Log.d(DEBUG_TAG,"Action was MOVE");
              //  Log.d(DEBUG_TAG , "On-touch   " + event.getX() + "   " +event.getY() ) ;
                endx = event.getX() ;
                endy = event.getY() ;

                diffx = (endx -startx) ;
                diffy  = (endy -starty);

                break ;
            case (MotionEvent.ACTION_UP) :
                prediffx = diffx ;
                prediffy = diffy ;
                state = STATE.none ;
                break ;
            case (MotionEvent.ACTION_POINTER_DOWN) :
                state = STATE.zoom ;
                break;
        }
    if( (state == STATE.pan && mScaleFactor != 0.1f ) || state == STATE.zoom )
    {
        invalidate();
       // requestLayout();
    }
    return true ;
    }


}
