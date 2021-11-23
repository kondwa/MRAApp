package com.example.mraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowTaxpayerActivity extends AppCompatActivity {
    private String authorization;
    private Taxpayer taxpayer;
    private int position;
    private TextView tvTpin;
    private TextView tvBusinessCertificateNumber;
    private TextView tvTradingName;
    private TextView tvBusinessRegistrationDate;
    private TextView tvMobileNumber;
    private TextView tvEmail;
    private TextView tvPhysicalLocation;
    private Button btnCancel;
    private Button btnEdit;
    private Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_taxpayer);
        tvTpin = findViewById(R.id.tvTpin);
        tvBusinessCertificateNumber = findViewById(R.id.tvBusinessCertificateNumber);
        tvTradingName = findViewById(R.id.tvTradingName);
        tvBusinessRegistrationDate = findViewById(R.id.tvBusinessRegistrationDate);
        tvMobileNumber = findViewById(R.id.tvMobileNumber);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhysicalLocation = findViewById(R.id.tvPhysicalLocation);
        btnCancel = findViewById(R.id.btnCancel);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete= findViewById(R.id.btnDelete);
        authorize();
        Intent intent = getIntent();
        position = intent.getIntExtra("position",-1);
        if(position != -1){
            getTaxpayer();
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(ShowTaxpayerActivity.this,EditTaxpayerActivity.class);
                editIntent.putExtra("position",position);
                startActivity(editIntent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteIntent = new Intent(ShowTaxpayerActivity.this,DeleteTaxpayerActivity.class);
                deleteIntent.putExtra("position",position);
                startActivity(deleteIntent);
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
        Intent intent = new Intent(this, ShowTaxpayerActivity.class);
        startActivity(intent);
    }
    private void getTaxpayer() {
        Api.getService().getAll(authorization).enqueue(new Callback<List<Taxpayer>>() {
            @Override
            public void onResponse(Call<List<Taxpayer>> call, Response<List<Taxpayer>> response) {
               if(response.isSuccessful()){
                   taxpayer = response.body().get(position);
                   showTaxpayer();
               }else{
                   Toast.makeText(ShowTaxpayerActivity.this,response.message(),Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onFailure(Call<List<Taxpayer>> call, Throwable t) {
                Toast.makeText(ShowTaxpayerActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showTaxpayer(){
        if(taxpayer != null) {
            tvTpin.setText(taxpayer.getTpin());
            tvBusinessCertificateNumber.setText(taxpayer.getBusinessCertificateNumber());
            tvTradingName.setText(taxpayer.getTradingName());
            tvBusinessRegistrationDate.setText(taxpayer.getBusinessRegistrationDate());
            tvMobileNumber.setText(taxpayer.getMobileNumber());
            tvEmail.setText(taxpayer.getEmail());
            tvPhysicalLocation.setText(taxpayer.getPhysicalLocation());
        }else{
            Toast.makeText(ShowTaxpayerActivity.this,"Nothing to show",Toast.LENGTH_SHORT).show();
        }
    }

}