package com.example.atlas_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atlas_project.customViews.Location;
import com.example.atlas_project.jobdata.FetchItemList;
import com.example.atlas_project.jobdata.Item;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class item_details extends AppCompatActivity {
    Button scan;
    String result;
    TextView tv_quantity , tv_desc , tv_number , tv_location ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        String point = getIntent().getStringExtra("current_point") ;
        scan=(Button)findViewById(R.id.scan);
        Item item = FetchItemList.getInstance(null).getItemList().getItems().get(Integer.parseInt(point));
        tv_quantity = findViewById(R.id.qty) ;
        tv_desc = findViewById(R.id.item_des) ;
        tv_number = findViewById(R.id.item_no) ;
        tv_location = findViewById(R.id.location) ;

        tv_location.setText("Location : " + item.getLocation());
        tv_number.setText("Item Number : " + item.getItemNo());
        tv_desc.setText("Item Description : " + item.getItemDescription());
        tv_quantity.setText("Quantity : " + item.getQuantity());

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator intentIntegrator = new IntentIntegrator(item_details.this);
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

            result=intentResult.getContents();

            Toast.makeText(this,result, Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Scan Again", Toast.LENGTH_SHORT).show();
        }
    }
}