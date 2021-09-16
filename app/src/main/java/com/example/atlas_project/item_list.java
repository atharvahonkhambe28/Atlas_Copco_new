package com.example.atlas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.atlas_project.jobdata.FetchItemList;
import com.example.atlas_project.jobdata.Item;
import com.example.atlas_project.jobdata.ItemList;

import java.util.ArrayList;

public class item_list extends AppCompatActivity {

    ArrayList<String> items ;
    TextView picklistno , kittername , total_items ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        ItemList itemList= FetchItemList.getInstance().getItemList() ;
        items = new ArrayList<>() ;
        for(Item i: itemList.getItems()){
            items.add( i.getItemNo() + "---" +i.getLocation() ) ;
        }
        picklistno = (TextView) findViewById(R.id.picklistno) ;
        kittername = (TextView) findViewById(R.id.kittername) ;
        total_items= (TextView) findViewById(R.id.totalitems) ;


        picklistno.setText(itemList.getPicklistnumber());
        kittername.setText(itemList.getKitter());
        total_items.setText(String.valueOf(itemList.getItems().size()));
//        FetchItemList fetchItemList = FetchItemList.getInstance() ;
//        ItemList i = fetchItemList.getItemList() ;
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.item_list_text, items);



        ListView listView = (ListView) findViewById(R.id.item_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                // Item item= (Item) adapter.getItem(position);
                Intent intent=new Intent(item_list.this, Start.class);
                startActivity(intent);
            }
        });

    }
}