package com.example.atlas_project.jobdata;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

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
    private static Retrofit retrofit ;
    private static Jobapi jobapi;
    private FetchItemList(String url){
             retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jobapi =retrofit.create(Jobapi.class);
    }
    public static FetchItemList getInstance(@Nullable String url){
        if(fetchItemList == null)
            fetchItemList = new FetchItemList(url);
        return fetchItemList ;
    }
    public void post_item_list(){
        List<ItemList> itemLists = new ArrayList<>() ;
        itemLists.add(itemList) ;
        Call<String> call = jobapi.setItems(itemLists);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String answer = response.body() ;
                if(answer.equals("done")) {
                    Log.d("print-------", answer);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("print fail-------" , t.getMessage() ) ;
            }
        });
    }
    public void fetch_item_list(ProgressDialog dialog , Context context , String filename){



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

                location.setDestination( response.body().get(0).getItems().get(0).getLocation());
                location.setSource("53 AA");

                //loading
                for(int i=1  ; i<response.body().get(0).getItems().size() ;i++ ){
                    locations.add(response.body().get(0).getItems().get(i).getLocation()) ;
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