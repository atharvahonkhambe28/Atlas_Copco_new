package com.example.atlas_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.atlas_project.customViews.InitializeGraph;
import com.example.atlas_project.customViews.InitializeMap;
import com.example.atlas_project.customViews.Location;
import com.example.atlas_project.jobdata.FetchItemList;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Scan extends Activity {

    Button start ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitializeMap.getInstance();
        Location location = Location.getInstance("53 AA1" , "53 AA");
        InitializeGraph.getInstance(5 ,11) ;
        FetchItemList.getInstance(getString(R.string.url)) ;
        setContentView(R.layout.activity_scan);
        start =( Button) findViewById(R.id.start) ;

        Toast.makeText(Scan.this, "you are entered", Toast.LENGTH_SHORT).show();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ProgressDialog dialog = ProgressDialog.show(Scan.this, "",
//                        "Loading. Please wait...", true);
//                FetchItemList.getInstance(null).fetch_item_list(dialog , Scan.this ,"01S03S1001270222021123030");


                IntentIntegrator intentIntegrator = new IntentIntegrator(Scan.this);
                intentIntegrator.setPrompt("Scan a barcode or QR Code");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then
        // toast a message as "cancelled"

        if (intentResult.getContents() != null) {
            ProgressDialog dialog = ProgressDialog.show(Scan.this, "",
                    "Loading. Please wait...", true);
            FetchItemList.getInstance(null).fetch_item_list(dialog , Scan.this ,intentResult.getContents());

        }else {
            Toast.makeText(this, "Scan Again", Toast.LENGTH_SHORT).show();
        }
    }
}