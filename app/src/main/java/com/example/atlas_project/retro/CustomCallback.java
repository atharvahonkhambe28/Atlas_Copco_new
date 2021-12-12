package com.example.atlas_project.retro;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.atlas_project.CardListActivity;
import com.example.atlas_project.Scan;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomCallback<T> implements Callback<T> {
    @SuppressWarnings("unused")
    private static final String TAG = "RetrofitCallback";
    private ProgressDialog dialog ;
    private final Callback<T> mCallback;
    private Context mContext ;
    public CustomCallback(ProgressDialog dialog, Callback<T> callback , Context context) {
        this.dialog = dialog;
        this.mCallback = callback;
        this.mContext = context ;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
       // if(response.body() == null) return;
        mCallback.onResponse(call , response);
        this.dialog.dismiss();
        Intent n =new Intent(mContext, CardListActivity.class) ;
        mContext.startActivity(n);

    }
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.d("FAILUTE------" , t.getMessage()) ;
    }
}
