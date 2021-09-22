package com.example.atlas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atlas_project.customViews.DrawLayoutBitmap;
import com.example.atlas_project.customViews.Location;
import com.example.atlas_project.jobdata.FetchItemList;

import java.util.HashMap;

public class Start extends Activity {

    private Button next;
    private TextView quatityTextview , itemnumberTextview;
    private int current ;
    private Location location ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        HashMap<String , Integer> images = new HashMap<>();
        images.put( "type1_1" , R.id.imagetype1_1); images.put("type1_2" , R.id.imagetype1_2); images.put("type1_3" ,R.id.imagetype1_3) ;
        location = Location.getInstance(null , null) ;
        ImageView imageView = (ImageView) findViewById(images.get(location.getType() + "_" + location.getShelf()));
        imageView.setVisibility(View.VISIBLE);

        quatityTextview = findViewById(R.id.start_quantity);
        itemnumberTextview = findViewById(R.id.start_itemNumber);

        current = location.getCurrent_point() ;
        if(current >= location.getLocations().size())
            current = location.getLocations().size()  ;
        try {
            quatityTextview.setText("Quantity : " + FetchItemList.getInstance(null).getItemList().getItems().get(current).getQuantity());
            itemnumberTextview.setText("ItemNumber : " + FetchItemList.getInstance(null).getItemList().getItems().get(current).getItemNo());
        }
        catch (IndexOutOfBoundsException e){
            Log.d("efef" , e.getMessage()) ;
        }



        next = (Button) findViewById(R.id.next) ;

        DrawLayoutBitmap d = DrawLayoutBitmap.get() ;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Location.done){
                    Log.d("ds" , "dfsf");



                    //setContentView(R.layout.activity_main);


                    Intent intent = new Intent(Start.this , item_details.class) ;
                    intent.putExtra("current_point" , current ) ;
                    startActivityForResult(intent ,3);
                    //d.redraw();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 3){
            if(data.getBooleanExtra("isValid" , false)){
                Log.d("isValid"  , "true");
                try {
                    location.setPoints();
                    FetchItemList.getInstance(null).getItemList().getItems().get(current).setQuantity_picked(String.valueOf(current));
                }
                catch (IndexOutOfBoundsException e){
                    Log.d("efef" , e.getMessage()) ;
                }
                Intent intent = new Intent() ;
                intent.putExtra("message" , "next") ;
                setResult(2 ,intent);
                finish();
            }
            else Log.d("isValid"  , "false");

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent() ;
        intent.putExtra("message" , "back") ;
        setResult(2 ,intent);
        finish();
    }
}