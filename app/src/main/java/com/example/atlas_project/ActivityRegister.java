package com.example.atlas_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ActivityRegister extends AppCompatActivity {
    TextView login;
    Button register;
    private TextView editTextTextPersonNameTextView;
    private TextView editTextTextPersonName2TextView;
    private TextView editTextTextEmailAddress2TextView;
    private TextView editTextTextPassword2TextView;
    private TextView ConfirmPasswordView;

    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        login=(TextView)findViewById(R.id.textview);
        editTextTextPersonNameTextView = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2TextView = findViewById(R.id.editTextTextPersonName2);
        editTextTextEmailAddress2TextView = findViewById(R.id.editTextTextEmailAddress2);
        editTextTextPassword2TextView = findViewById(R.id.editTextTextPassword2);
        ConfirmPasswordView = findViewById(R.id.ConfirmPassword);
        register=findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityRegister.this, MainActivity.class);
                startActivity(intent);
            }
        });

        OkHttpClient client = new OkHttpClient();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextTextPersonNameTextView.length()==0){
                    Toast.makeText(ActivityRegister.this, "Enter first name", Toast.LENGTH_SHORT).show();
                }
                else if(!editTextTextPersonNameTextView.getText().toString().matches("[a-z,A-Z, ]*")){
                    editTextTextPersonNameTextView.setError("Enter character only");
                }
                else if(editTextTextPersonName2TextView.length()==0){
                    Toast.makeText(ActivityRegister.this, "Enter last name", Toast.LENGTH_SHORT).show();
                }
                else if(!editTextTextPersonName2TextView.getText().toString().matches("[a-z,A-Z, ]*")){
                    editTextTextPersonName2TextView.setError("Enter character only");
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(editTextTextEmailAddress2TextView.getText().toString()).matches()){
                    editTextTextEmailAddress2TextView.setError("Enter valid Email ID");
                }
                else if(editTextTextPassword2TextView.length()==0){
                    Toast.makeText(ActivityRegister.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                else if(ConfirmPasswordView.length()==0){
                    Toast.makeText(ActivityRegister.this, "Enter password to confirm", Toast.LENGTH_SHORT).show();
                }
                else if(!ConfirmPasswordView.getText().toString().equals(ConfirmPasswordView.getText().toString())) {
                    ConfirmPasswordView.setError("Password does not match reenter the password");
                }
                else {
                    RequestBody formBody = new FormBody.Builder()
                            .add("Name", editTextTextPersonNameTextView.getText().toString())
                            .add("Surname", editTextTextPersonName2TextView.getText().toString())
                            .add("Email", editTextTextEmailAddress2TextView.getText().toString())
                            .add("Password", editTextTextPassword2TextView.getText().toString())
                            .add("ConfirmPassword", ConfirmPasswordView.getText().toString())
                            .build();
                    Log.i("formBody", formBody.toString());
                    String string = getString(R.string.url);
                    Request request = new Request.Builder().url(string+"/register").post(formBody).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.e("onfailure", e.toString());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ActivityRegister.this, "", Toast.LENGTH_SHORT).show();
                                    editTextTextPersonNameTextView.setText("");
                                    editTextTextPersonName2TextView.setText("");
                                    editTextTextEmailAddress2TextView.setText("");
                                    editTextTextPassword2TextView.setText("");
                                    ConfirmPasswordView.setText("");

                                }
                            });
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            if (response.body().string().equals("great success")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ActivityRegister.this, "data received", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                    Intent in = new Intent(ActivityRegister.this, MainActivity.class);
                    startActivity(in);
                }
            }
        });
    }
}
