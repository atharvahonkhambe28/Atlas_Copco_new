package com.example.atlas_project.customViews;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class DrawLayout {

        private int x , y ;
        private Bitmap bitmap ;
        private Canvas canvas  ;
        private Rect rect ;
        private Paint rack_color  ;
        private Paint text_color;
        private String source ;
        private String destination ;
        private int [][] rect_cord ;
        private int no_of_segments ;
        private int number_of_racks ;
        private ArrayList<Integer> points ;
        private Path path ;
        private int dest_x ;
        private int dest_y ;

        private int rack_Start_Top ;
        private int rack_Start_Left  ;
        private int rack_width ;
        private int rack_height ;
        private int gap ;
        private int path_Start_Top ;
        private int path_Start_Left;
        private int circle_radius = 15 ;
        private int horizontal_dist_between_circle ;
        private int width_of_one_segment ;
        private  Paint paintcircle ;

        void setSource(String source){
            this.source = source ;
        }
        void setDestination(String destination){
            this.destination = destination ;
        }
        void setNo_of_segments(int x){
            this.no_of_segments = x ;
        }
        void setNumber_of_racks(int x){
            this.number_of_racks = x ;
        }
        void setPoints(){
            List<List<Node>> adj = InitializeGraph.getGraph();
            Path path = new Path(85) ;
            points = path.getPath(destination , parseInt(source.split("-")[0]) , adj) ;
            Log.d("points----" , points.toString());

        }
        public void setBitmap(){
            Integer p =0 ;
            // Paint paint = new Paint() ;
            // paint.setColor(Color.BLUE);


            setPoints();
            draw_path_in_row(  path_Start_Top- rack_height ,100 + rack_width/2, 0);

            draw_path_in_row(  path_Start_Top + (rack_height+5)*11 ,100 + rack_width/2, 70);



            for(int i=0 ;i<no_of_segments;i++){
                draw_column_of_racks(rack_Start_Top , rack_Start_Left + i*width_of_one_segment , rack_height ,rack_width  , i*2);

                draw_path_in_column(path_Start_Left +i*width_of_one_segment, path_Start_Top , circle_radius , number_of_racks ,true  ,i*2);

                draw_column_of_racks(rack_Start_Top , rack_Start_Left +rack_width +gap  + i*width_of_one_segment, rack_height ,rack_width , i*2 + 1);
            }

            draw_row_of_racks((rack_Start_Top+rack_height*(number_of_racks+1))+rack_height/2, rack_Start_Left,rack_height,rack_width,15);


//            for(int i=0;i<no_of_segments-1;i++){
//                setRect(path_Start_Top , path_Start_Left +i*horizontal_dist_between_circle, 5 ,horizontal_dist_between_circle) ;
//                canvas.drawRect(rect , paintcircle);
//                setRect(path_Start_Top + rack_height*(number_of_racks+1) + 60, path_Start_Left + i*horizontal_dist_between_circle , 5 ,horizontal_dist_between_circle) ;
//                canvas.drawRect(rect , paintcircle);
//
//            }
            draw_walking_path(dest_x,dest_y ) ;
        }
        private void setConstraints(){
            rack_Start_Top = 100;
            rack_Start_Left = 100 ;
            rack_width = 180 ;
            rack_height = 100 ;
            gap = rack_width +10;
            path_Start_Top = 100 + rack_height/2;
            path_Start_Left = rack_Start_Top + rack_width + gap/2;
            circle_radius = 15 ;
            horizontal_dist_between_circle = gap + 2*rack_width + 5 ;
            width_of_one_segment = rack_width*2 + gap + 5;
            paintcircle = new Paint() ;
            paintcircle.setColor(Color.GRAY);
        }
        boolean search(Integer s){
            int l =0 , r=points.size() ;
            while(l<r){
                if(points.get(l) == s)
                    return true;
                l++;
            }

            return false;
        }
        DrawLayout() {
            bitmap = Bitmap.createBitmap(2880 ,1800 , Bitmap.Config.ARGB_8888) ;
            canvas  = new Canvas(bitmap) ;
            rect = new Rect() ;
            rack_color = new Paint() ;
            text_color = new Paint() ;
            rack_color.setColor(Color.BLUE);
            text_color.setTextSize(35);
            text_color.setColor(Color.WHITE);
            rect_cord = new int[31][4] ;
            points = new ArrayList<>() ;
            setConstraints();
            path = new Path(85) ;
        }

        private Rect setRect(int top , int left , int height , int width) {

            rect.left = left ;
            rect.top = top ;
            rect.bottom = rect.top + height ;
            rect.right = rect.left + width;
            return  rect ;
        }
        private void draw_column_of_racks(int top , int left, int height , int width  ,int segment_number) {
            int add =0 , space_between_two_racks = 5;
            char A = 'A' ;
            int circle_number;
            String rack_name ;
            for(int i=0;i<number_of_racks ;i++){
                circle_number = segment_number/2 + (i+1)*no_of_segments +10 ;
                rack_name = circle_number + "-" + segment_number ;
                setRect(top + add , left , height , width) ;

                if(rack_name.equals(destination))
                    rack_color.setColor(Color.GREEN);
                else if (rack_name.equals(source))
                    rack_color.setColor(Color.RED);
                canvas.drawRect(rect , rack_color);
                rack_color.setColor(Color.BLUE);

                canvas.drawText(InitializeMap.get_rack_name(rack_name).substring(0,5),left + 2  ,top + height/2 +add ,text_color );
                add+= height + space_between_two_racks ;
            }
        }
    private void draw_row_of_racks(int top,int left,int height,int width,int no_of_rect){
        int add=0;
       // String rack_name;


        for(int i=0;i<no_of_rect;i++){
             int x = 70 + i ;
             String rack_name = x + "-" + "x";
            setRect(top,left+add,height,width);
            if(rack_name.equals(source)){
                rack_color.setColor(Color.RED);
            }
            else if (rack_name.equals(destination))
                rack_color.setColor(Color.GREEN);
            else rack_color.setColor(Color.BLUE);
            canvas.drawRect(rect,rack_color);

//            setRect(top,left+add,height,0);
//            rack_color.setColor(Color.WHITE);
//            canvas.drawRect(rect,rack_color);

            canvas.drawText(InitializeMap.get_rack_name(rack_name).substring(0,5),left + add  ,top + height/2 ,text_color );
            rack_color.setColor(Color.BLUE);
            //canvas.drawText(rack_name,left + 5  ,top + height/2 +add ,text_color );
            //v+=1;
            add+=width+5;
           // Log.d(String.valueOf(DEBUG_TAG), String.valueOf(v));
        }
    }
        private void draw_walking_path(int cx,int cy){
           rack_color.setColor(Color.GREEN);
            for(int i =0 ; i< points.size()-1;i++){
                //go_up

                if( (points.get(i) - points.get(i+1))  <= no_of_segments*-1 ){

                    setRect(cy ,cx ,rack_height ,10);
                    canvas.drawRect(rect , rack_color);
                    cy+=rack_height+5 ;
                }
                else if( (points.get(i) - points.get(i+1)) >= no_of_segments ){
                    cy-=rack_height +5 ;
                    setRect(cy ,cx ,rack_height ,10);
                    canvas.drawRect(rect , rack_color);
                }
                else if(points.get(i) - points.get(i+1) == -1 ){
                    setRect(cy,cx,10,180 /2 + 190 /2);
                    canvas.drawRect(rect , rack_color);
                    cx+= 180 /2 + 190 /2;
                }
                else if(points.get(i) - points.get(i+1) == 1 ){
                    cx-=180 /2 + 190 /2;
                    setRect(cy,cx,10,180 /2 + 190 /2);
                    canvas.drawRect(rect , rack_color);
                }

            }
            rack_color.setColor(Color.BLUE);
        }
        private void draw_path_in_column(int cx , int cy , int radius , int number_circles , boolean with_path  ,int segment_number){
            int add =0;
            Paint circle_color = new Paint() ;
            circle_color.setColor(Color.GRAY);
            int circle_number ;
            int path_width = 5;

            for(int i=0;i<number_circles;i++){
                circle_number = segment_number/2 + (i+1)*no_of_segments + 10;
              //  int destination_circle_number = parseInt(destination.split("-")[0]) ;
                int source_circle_number = parseInt(source.split("-")[0]) ;
               // Log.d("dest" , String.valueOf(destination_circle_number)) ;
              //  Log.d("rack_name" , String.valueOf(circle_number)) ;
                if (source_circle_number == circle_number)
                {
                    dest_x = cx ;
                    dest_y =cy +add;
                }
                if(circle_number == source_circle_number) {
                    circle_color.setColor(Color.RED);
                    canvas.drawCircle(  cx ,  cy +add, radius+5, circle_color);
                }
                else if(search(circle_number)){
                    circle_color.setColor(Color.GREEN);

                    canvas.drawCircle(  cx ,  cy +add, radius+5, circle_color);
                }
                else
                    canvas.drawCircle(  cx ,  cy +add, radius, circle_color);
                circle_color.setColor(Color.GRAY);
                if(with_path && i+1 != number_circles) {
                    setRect(cy + radius  +add, cx, rack_height, path_width);
                    canvas.drawRect(rect, circle_color);
                }
                circle_color.setColor(Color.GRAY);

                add+=rack_height + 5;

            }
        }
        private void draw_path_in_row(int top, int left, int start_circle){
            int add = 0;
            int start = start_circle + 1 ;
            int shift = 0 ;
            for(int i =start_circle ; i<start_circle+15;i++){
                int source_circle_number = parseInt(source.split("-")[0]) ;
                boolean in_points = search(i) ;
                if(in_points) circle_radius = 20 ;
                else circle_radius =15 ;
                if(i == source_circle_number){
                    paintcircle.setColor(Color.RED);
                    dest_x = left +add ;
                    dest_y = top ;
                }
                else if(in_points)
                    paintcircle.setColor(Color.GREEN);
                else paintcircle.setColor(Color.GRAY);
                canvas.drawCircle( left+add , top ,circle_radius ,paintcircle);
                paintcircle.setColor(Color.GRAY);
                if(i!=start_circle+14){
                    if( (start + shift) == i) {
                        //top row path
                        if(start_circle < 14)
                            setRect(top ,left+add, rack_height, 5);
                        else if (start_circle>=70)
                            setRect(top -rack_height ,left+add, rack_height, 5);
                            canvas.drawRect(rect ,paintcircle);
                        shift+=3;
                    }
                    setRect(top ,left+add, 5, rack_width + 5);
                    canvas.drawRect(rect, paintcircle);
                }


                circle_radius =15;
                add+=rack_width+5;
            }
        }
        public Bitmap getBitmap() {
            return bitmap;
        }
}

