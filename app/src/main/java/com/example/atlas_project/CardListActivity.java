package com.example.atlas_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.TaskExecutor;

import com.example.atlas_project.customViews.Location;
import com.example.atlas_project.jobdata.FetchItemList;
import com.example.atlas_project.jobdata.Item;
import com.example.atlas_project.jobdata.ItemList;

import java.util.ArrayList;

public class CardListActivity extends Activity {

    private static final String TAG = "CardListActivity";
    private CardArrayAdapter cardArrayAdapter;
    private ListView listView;
    private TextView kittername , kitterno , uniqueno , station , picklistno  ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        ArrayList<Item> items= (ArrayList<Item>) FetchItemList.getInstance(null).getItemList().getItems() ;
        ItemList  itemList =  FetchItemList.getInstance(null).getItemList() ;

        kittername = findViewById(R.id.kittername) ;
        station = findViewById(R.id.station) ;
        kitterno = findViewById(R.id.kitno) ;
        uniqueno = findViewById(R.id.unique_no) ;
        picklistno = findViewById(R.id.picklist_no) ;

        kitterno.setText("Kit No :- " + itemList.getKitnumber());
        station.setText("station :-" + itemList.getStation());
        uniqueno.setText("Unique No : " + itemList.getUniquenumber());
        picklistno.setText("Picklist No : " + itemList.getUniquenumber());
        kittername.setText("Kitter : "  + itemList.getKitter());

        listView = (ListView) findViewById(R.id.card_listView);

        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));
        Log.d("dejkdasjf" , "start");

        cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.list_item_card);

        for (Item item : items) {
            Card card = new Card( "ItemNumber : " + item.getItemNo(), "Description : " + item.getItemDescription(), "Location : " + item.getLocation() , "Quantity : " + item.getQuantity());
            cardArrayAdapter.add(card);
        }


        listView.setAdapter(cardArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Item item= (Item) adapter.getItem(position);
                    Intent intent=new Intent(CardListActivity.this, Start.class);
                    startActivityForResult(intent , 2);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String message = data.getStringExtra("message") ;
        if(message.equals("back")) return ;
        Location location = Location.getInstance(null ,null) ;
       // Log.d("dejkdasjf" , "done picking all locations");
        if(location.getCurrent_point() <= location.getLocations().size()){
            Intent intent=new Intent(CardListActivity.this, Start.class);
            startActivityForResult(intent ,2);
        }
        else {

            Log.d("dejkdasjf" , "done picking all locations");
            FetchItemList.getInstance(null).post_item_list();
            finish();
        }
    }
}