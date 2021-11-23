package com.example.mraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
{
    "TPIN": "20203030",
    "BusinessCertificateNumber": "MBRS123456",
    "TradingName": "Sample Trader",
    "BusinessRegistrationDate": "2021/11/02",
    "MobileNumber": "0995900000",
    "Email": "bmangisoni@gmail.com",
    "PhysicalLocation": "Namiwawa",
    "Username": "bmangisoni"
}
 */
public class AddTaxpayerActivity extends AppCompatActivity {
    private String authorization;
    private EditText etTpin;
    private EditText etBusinessCertificateNumber;
    private EditText etTradingName;
    private EditText etBusinessRegistrationDate;
    private EditText etMobileNumber;
    private EditText etEmail;
    private EditText etPhysicalLocation;
    private Button btnCancel;
    private Button btnAddTaxpayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_taxpayer);
        etTpin = findViewById(R.id.etTpin);
        etBusinessCertificateNumber = findViewById(R.id.etBusinessCertificateNumber);
        etTradingName = findViewById(R.id.etTradingName);
        etBusinessRegistrationDate = findViewById(R.id.etBusinessRegistrationDate);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        etEmail = findViewById(R.id.etEmail);
        etPhysicalLocation = findViewById(R.id.etPhysicalLocation);
        btnCancel = findViewById(R.id.btnCancel);
        btnAddTaxpayer = findViewById(R.id.btnAddTaxpayer);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAddTaxpayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTaxpayer();
            }
        });

        authorize();
    }

    @Override
    protected void onStart() {
        super.onStart();
        authorize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        authorize();
    }

    private void addTaxpayer() {
        Taxpayer taxpayer = new Taxpayer();
        taxpayer.setTpin(etTpin.getText().toString());
        taxpayer.setBusinessCertificateNumber(etBusinessCertificateNumber.getText().toString());
        taxpayer.setTradingName(etTradingName.getText().toString());
        taxpayer.setBusinessRegistrationDate(etBusinessRegistrationDate.getText().toString());
        taxpayer.setMobileNumber(etMobileNumber.getText().toString());
        taxpayer.setEmail(etEmail.getText().toString());
        taxpayer.setPhysicalLocation(etPhysicalLocation.getText().toString());
        taxpayer.setDeleted(false);
        taxpayer.setUsername(SessionManager.getUsername());
        Api.getService().add(taxpayer,authorization).enqueue(new Callback<Taxpayer>() {
            @Override
            public void onResponse(Call<Taxpayer> call, Response<Taxpayer> response) {
                Taxpayer tp = response.body();
                if(tp != null){
                    Intent intent = new Intent(AddTaxpayerActivity.this,TaxpayerListActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(AddTaxpayerActivity.this,"Adding taxpayer failed.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Taxpayer> call, Throwable t) {
                Toast.makeText(AddTaxpayerActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void authorize() {
        SessionManager.setContext(this);
        authorization = SessionManager.getAuthorization();
        if(authorization == null){
            showLoginForm();
        }
    }
    private void showLoginForm() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}