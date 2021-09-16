package com.example.atlas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.atlas_project.customViews.DrawLayoutBitmap;
import com.example.atlas_project.customViews.Location;

import java.util.HashMap;

public class Start extends AppCompatActivity {

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        HashMap<String , Integer> images = new HashMap<>();
        images.put( "type1_1" , R.id.imagetype1_1); images.put("type1_2" , R.id.imagetype1_2); images.put("type1_3" ,R.id.imagetype1_3) ;
        Location location = Location.getInstance(null , null) ;
        ImageView imageView = (ImageView) findViewById(images.get(location.getType() + "_" + location.getShelf()));
        imageView.setVisibility(View.VISIBLE);





        next = (Button) findViewById(R.id.next) ;

        DrawLayoutBitmap d = DrawLayoutBitmap.get() ;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Location.done){
                    Log.d("ds" , "dfsf");
                    location.setPoints();
                    //setContentView(R.layout.activity_main);
                    Intent n = new Intent(Start.this , Start.class) ;
                    startActivity(n);
                    //d.redraw();
                }
            }
        });
    }
}