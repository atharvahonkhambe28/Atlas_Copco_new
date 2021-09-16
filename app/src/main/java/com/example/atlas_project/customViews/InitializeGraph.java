package com.example.atlas_project.customViews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitializeGraph {
    private static InitializeGraph initializeGraph= null ;
    private static List<List<Node>> adj ;

    private static DrawLayout customBitmap ;

    public static InitializeGraph getInstance(int no_of_aisle , int racks_per_aisle ){
        if(initializeGraph == null){
            initializeGraph = new InitializeGraph(no_of_aisle , racks_per_aisle) ;
        }
        return initializeGraph ;
    }

    public static  List<List<Node>> getGraph(){
        return adj ;
    }
    public static DrawLayout getLayout(){ return customBitmap ;}


    private InitializeGraph(int no_of_aisle , int racks_per_aisle ){




//        customBitmap = new DrawLayout() ;
//        customBitmap.setDestination(Location.getSudo_destination());
//        customBitmap.setSource(Location.getSudo_source());
//        customBitmap.setNo_of_segments(5);
//        customBitmap.setNumber_of_racks(11);

        adj = new ArrayList<>();
        int no_of_points_row = no_of_aisle * 3;
        int no_of_points_between_top_bottom_point = no_of_aisle*racks_per_aisle + no_of_points_row;
        int total_points = no_of_aisle * racks_per_aisle + no_of_points_row *2  ;


        //Loop to initialize the adj list
        for (int i = 0; i < total_points; i++) {
            List<Node> item = new ArrayList<>();
            adj.add(item);
        }

        for(int i = 0; i< no_of_aisle * 3 -1; i++){
            adj.get(i).add(new Node(i+1 , 1));
            adj.get(i+1).add(new Node(i , 1));

            adj.get(i+ no_of_points_between_top_bottom_point).add(new Node(i+ no_of_points_between_top_bottom_point +1  , 1));
            adj.get(i+ no_of_points_between_top_bottom_point + 1).add(new Node(i +no_of_points_between_top_bottom_point , 1));
        }

        int a = no_of_points_row ;
        for(int i = 1, j = 0; i< no_of_aisle * 3; i+=3 ,j++){
            adj.get(i).add(new Node( a+ j , 1));
            adj.get(a+j).add(new Node(i , 1));
        }
        a = total_points - no_of_points_row - 1 ;
        for(int i = total_points -2, j = 0; i>=total_points - no_of_points_row ; i-=3 ,j++){
            adj.get(i).add(new Node( a -j  , 1));
            adj.get(a-j).add(new Node(i , 1));
        }



        for(int i= no_of_points_row ; i<no_of_points_row + no_of_aisle ; i++){
            for(int j=i , x =0; x <racks_per_aisle -1 ;j+=no_of_aisle ,x++){
                adj.get(j).add(new Node(j + no_of_aisle ,1));
                adj.get(j + no_of_aisle).add(new Node(j ,1));
            }
        }


    }
}
