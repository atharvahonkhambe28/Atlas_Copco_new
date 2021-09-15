package com.example.atlas_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView register;
    Button login;
    private TextView editTextTextEmail;
    private TextView editTextTextPass;
    private OkHttpClient okHttpClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register=(TextView)findViewById(R.id.textview2);
        login=(Button)findViewById(R.id.login);
        editTextTextEmail=findViewById(R.id.Email);
        editTextTextPass=findViewById(R.id.Pass);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ActivityRegister.class);
                startActivity(intent);
            }
        });
        OkHttpClient client = new OkHttpClient();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody formBody2 = new FormBody.Builder()
                        .add("Email", editTextTextEmail.getText().toString())
                        .add("Password", editTextTextPass.getText().toString())
                        .build();
                Log.i("formBody2", formBody2.toString());
                Request request = new Request.Builder().url("http://10.0.2.2:5000/login").post(formBody2).build();
                client.newCall(request).enqueue(new Callback() {

                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.e("onfailure", e.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                                editTextTextEmail.setText("");
                                editTextTextPass.setText("");


                            }
                        });




                    }

                    @Override

                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Log.i("hello guys", response.toString());
                        if (response.body().string().equals("User exist")){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this , "you are entered", Toast.LENGTH_SHORT).show();
                                    IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                                    intentIntegrator.setPrompt("Scan a barcode or QR Code");
                                    intentIntegrator.setBeepEnabled(true);
                                    intentIntegrator.setOrientationLocked(true);
                                    intentIntegrator.setCaptureActivity(Capture.class);
                                    intentIntegrator.initiateScan();
                                }
                            });
                        }
                        else {
                            //Log.i("errrrrrorr", "Your account is not valid");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this , "Your credentials are invalid", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }

                    }
                });







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

                Intent intent=new Intent(MainActivity.this, item_list.class);
                startActivity(intent);

            }else {
                Toast.makeText(this, "Scan Again", Toast.LENGTH_SHORT).show();
            }
    }

}