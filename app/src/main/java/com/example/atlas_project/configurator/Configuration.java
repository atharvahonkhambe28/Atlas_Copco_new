package com.example.atlas_project.configurator;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.atlas_project.MainActivity;
import com.example.atlas_project.customViews.InitializeGraph;
import com.example.atlas_project.customViews.InitializeMap;
import com.example.atlas_project.customViews.Location;
import com.example.atlas_project.retro.Jobapi;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Configuration {
    @SerializedName("values")
    private HashMap<String , String> rack_data ;

    @SerializedName("number_of_segments")
    private int number_of_segments;

    @SerializedName("no_of_racks")
    private int no_of_racks;

    public Configuration(HashMap<String, String> rack_data, int number_of_segments, int no_of_racks) {
        this.rack_data = rack_data;
        this.number_of_segments = number_of_segments;
        this.no_of_racks = no_of_racks;
    }

    public HashMap<String, String> getRack_data() {
        return rack_data;
    }

    public void setRack_data(HashMap<String, String> rack_data) {
        this.rack_data = rack_data;
    }

    public int getNumber_of_segments() {
        return number_of_segments;
    }

    public void setNumber_of_segments(int number_of_segments) {
        this.number_of_segments = number_of_segments;
    }

    public int getNo_of_racks() {
        return no_of_racks;
    }

    public void setNo_of_racks(int no_of_racks) {
        this.no_of_racks = no_of_racks;
    }

    public static void fetch_config(Context context){
       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.146:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       Jobapi jobapi =retrofit.create(Jobapi.class);

       Call<Configuration> configurationCall = jobapi.getLayout();

       configurationCall.enqueue(new Callback<Configuration>() {
           @Override
           public void onResponse(Call<Configuration> call, Response<Configuration> response) {
               Log.d( "*******data *******" , response.body().getRack_data().toString()) ;

               InitializeMap.getInstance(response.body().rack_data);
             //  Location location = Location.getInstance("53 AA1" , "53 AA");
               InitializeGraph.getInstance(5 ,11) ;
               Toast.makeText(context ,"Initialized" ,Toast.LENGTH_LONG).show();
           }

           @Override
           public void onFailure(Call<Configuration> call, Throwable t) {
               Log.d("FAIL--------" , t.getMessage()) ;
           }
       });

    }
}
