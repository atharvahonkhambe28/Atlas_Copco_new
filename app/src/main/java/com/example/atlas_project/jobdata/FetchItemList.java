package com.example.atlas_project.jobdata;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.example.atlas_project.R;
import com.example.atlas_project.customViews.Location;
import com.example.atlas_project.retro.CustomCallback;
import com.example.atlas_project.retro.Jobapi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchItemList {
    private static ItemList itemList ;
    private static FetchItemList fetchItemList = null ;
    private FetchItemList(){

    }
    public static FetchItemList getInstance(){
        if(fetchItemList == null)
            fetchItemList = new FetchItemList();
        return fetchItemList ;
    }
    public void fetch_item_list(ProgressDialog dialog , Context context , String filename){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Jobapi jobapi =retrofit.create(Jobapi.class);
        Call<List<ItemList>> call = jobapi.getJob(filename);
        Location location =  Location.getInstance(null ,null) ;

         Callback<List<ItemList>> itemListCallback = new Callback<List<ItemList>>() {
            @Override
            public void onResponse(Call<List<ItemList>> call, Response<List<ItemList>> response) {
                //loaded = true
                if(response.body() == null)
                    return;
                setItemList(response.body().get(0));
                ArrayList<String> locations = new ArrayList<>();
                location.setCurrent_point(0);
                location.setDestination("53 AA1");
                location.setSource("53 AA");

                //loading
                for(Item i : response.body().get(0).getItems()){
                    locations.add(i.getLocation()) ;
                }
                location.setLocations(locations);
                Location.done = true ;

            }

            @Override
            public void onFailure(Call<List<ItemList>> call, Throwable t) {
                Log.d("FAILUTE------" , t.getMessage()) ;
            }
        };

         call.enqueue(new CustomCallback<>(dialog, itemListCallback ,context));

    }

    public ItemList getItemList() {
        return itemList;
    }

    public void setItemList(ItemList itemList) {

        this.itemList = itemList;
    }
}