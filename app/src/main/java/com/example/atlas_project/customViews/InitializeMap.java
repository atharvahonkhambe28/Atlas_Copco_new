package com.example.atlas_project.customViews;

import android.util.Log;

import com.example.atlas_project.configurator.Configuration;

import java.util.HashMap;
import java.util.Map;

public class InitializeMap {
    private static HashMap<String,String> original_rack_name;
    private static HashMap<String,String> sudo_rack_name;
    private static InitializeMap object = null ;
    private InitializeMap(HashMap<String ,String> rack_data){
        original_rack_name =new HashMap<String,String>();
        sudo_rack_name =new HashMap<String, String>();
        initializeMAP(rack_data);
    }

    public static InitializeMap getInstance(HashMap<String ,String> rack_data){
        if (object == null)
                object = new InitializeMap(rack_data) ;
        return object ;
    }
    public static String get_rack_name( String key){
        if(original_rack_name.containsKey(key))
            return original_rack_name.get(key);
        Log.d("*****NOKEY*****" ,  "`key`") ;
        return key+"2";
    }
    public static String get_sudo_rack_name( String key){
        return sudo_rack_name.get(key);
    }
    private static  void initializeMAP(HashMap<String ,String> rack_data){
            original_rack_name = rack_data ;
//        char a='A';
//        // First Segment
//        int Coloum_number=51;
//        for(int i=15;i<=65;i+=5){
//            original_rack_name.put(i+"-0", Coloum_number+" A"+a); 1-0 : 51 AA
//            original_rack_name.put(i+"-1", Coloum_number+1+" A"+a);
//            a++;
//        }
//        a='A';
//
//        // Second Segment
//        Coloum_number=53;
//        for(int i=16;i<=66;i+=5){
//            original_rack_name.put(i+"-2", Coloum_number+" A"+a);
//            original_rack_name.put(i+"-3", Coloum_number+1+" A"+a);
//            a++;
//        }
//
//        a='A';
//
//        // THIRD Segment
//        Coloum_number=41;
//        for(int i=17;i<=67;i+=5){
//            original_rack_name.put(i+"-4", Coloum_number+" A"+a);
//            original_rack_name.put(i+"-5", Coloum_number+1+" A"+a);
//            a++;
//        }
//
//        a='A';
//
//        // Forth Segment
//        Coloum_number=31;
//        for(int i=18;i<=68;i+=5){
//            original_rack_name.put(i+"-6", Coloum_number+" A"+a);
//            original_rack_name.put(i+"-7", Coloum_number+1+" A"+a);
//            a++;
//        }
//
//        a='A';
//
//        // FIFTh Segment
//        Coloum_number=33;
//        for(int i=19;i<=69;i+=5){
//            original_rack_name.put(i+"-8", Coloum_number+" A"+a);
//            //h.put(i+"-9", Coloum_number+1+" A"+a);
//            a++;
//        }
//
//        original_rack_name.replace("59-8", "43 AA");
//        original_rack_name.replace("64-8", "43 AB");
//        original_rack_name.replace("69-8", "43 AC");
//
//
//// LAST COLOUM
//        original_rack_name.put("19-9",  "35 AA");
//        original_rack_name.put("24-9", "35 AB");
//        original_rack_name.put("29-9", "34 AA");
//        original_rack_name.put("34-9", "34 AB");
//        original_rack_name.put("39-9", "34 AC");
//        original_rack_name.put("44-9", "34 AD");
//        original_rack_name.put("49-9", "34 AE");
//        original_rack_name.put("54-9", "44 AA");
//        original_rack_name.put("59-9", "44 AB");
//        original_rack_name.put("64-9", "44 AC");
//        original_rack_name.put("69-9", "42 AO");
//
//        Coloum_number = 70 ;
//        for(int i=70 ;i<=84;i++){
//            original_rack_name.put(i+"-x", Coloum_number+" AZ");
//            Coloum_number++ ;
//        }




        for(Map.Entry m : original_rack_name.entrySet()){
            sudo_rack_name.put(m.getValue().toString(),m.getKey().toString());
//            if(c == 5){
//                c=0;
//                done_5 = !done_5 ;
//            }
            original_rack_name.put(m.getKey().toString() , m.getValue().toString() + "-" + "type1" ) ;
//            if(done_5){
//                h.put(m.getKey().toString() , m.getValue().toString() + "-" + "type-1" ) ;
//            }
//            else h.put(m.getKey().toString() , m.getValue().toString() + "-" + "type-2" ) ;
//            c++;
        }

    }
}
