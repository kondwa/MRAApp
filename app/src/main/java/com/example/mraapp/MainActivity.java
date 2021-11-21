package com.example.mraapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private LoginResponse loginResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        SessionManager.setContext(this);
        buttonLogin.setOnClickListener(v -> {
            doLogin();
            Toast.makeText(MainActivity.this,editTextEmail.getText().toString()+" logging in...",Toast.LENGTH_LONG).show();
        });
    }

    private void doLogin() {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Logging in. Please wait...");
        progressDialog.show();
        LoginRequest loginRequest = new LoginRequest(editTextEmail.getText().toString(),editTextPassword.getText().toString());

        Api.getService().login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginResponse = response.body();
                if(loginResponse != null && loginResponse.isAuthenticated()) {
                    progressDialog.dismiss();
                    SessionManager.setAuthorization("Bearer "+loginResponse.getToken().getValue());
                    SessionManager.setUsername(loginResponse.getUserDetails().getUsername());
                    ShowTaxpayerList();
                }else{
                    progressDialog.dismiss();
                    if(loginResponse!= null) {
                        Toast.makeText(MainActivity.this, loginResponse.getRemark(), Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this,"Login failed.",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void ShowTaxpayerList() {
        Intent intent = new Intent(this,TaxpayerListActivity.class);
        startActivity(intent);
    }
}