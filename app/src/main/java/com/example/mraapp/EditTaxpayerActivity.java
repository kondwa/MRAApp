package com.example.mraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTaxpayerActivity extends AppCompatActivity {
    private List<Taxpayer> taxpayerList;
    private String authorization;
    private EditText etTpin;
    private EditText etBusinessCertificateNumber;
    private EditText etTradingName;
    private EditText etBusinessRegistrationDate;
    private EditText etMobileNumber;
    private EditText etEmail;
    private EditText etPhysicalLocation;
    private Button btnCancel;
    private Button btnEditTaxpayer;
    private Taxpayer taxpayer;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_taxpayer);
        etTpin = findViewById(R.id.etTpin);
        etBusinessCertificateNumber = findViewById(R.id.etBusinessCertificateNumber);
        etTradingName = findViewById(R.id.etTradingName);
        etBusinessRegistrationDate = findViewById(R.id.etBusinessRegistrationDate);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        etEmail = findViewById(R.id.etEmail);
        etPhysicalLocation = findViewById(R.id.etPhysicalLocation);
        btnCancel = findViewById(R.id.btnCancel);
        btnEditTaxpayer = findViewById(R.id.btnEditTaxpayer);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnEditTaxpayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTaxpayer();
            }
        });
        authorize();
        Intent intent = getIntent();
        position = intent.getIntExtra("position",-1);
        if(position != -1) {
            getTaxpayer(position);
        }
    }

    private void populateTaxpayerForm(Taxpayer tp) {
        if(tp != null) {
            etTpin.setText(tp.getTpin());
            etBusinessCertificateNumber.setText(tp.getBusinessCertificateNumber());
            etTradingName.setText(tp.getTradingName());
            etBusinessRegistrationDate.setText(tp.getBusinessRegistrationDate());
            etMobileNumber.setText(tp.getMobileNumber());
            etEmail.setText(tp.getEmail());
            etPhysicalLocation.setText(tp.getPhysicalLocation());
        }
    }

    private void getTaxpayer(int position) {
        Api.getService().getAll(authorization).enqueue(new Callback<List<Taxpayer>>() {
            @Override
            public void onResponse(Call<List<Taxpayer>> call, Response<List<Taxpayer>> response) {
                if(response.isSuccessful()){
                    taxpayer = response.body().get(position);
                    populateTaxpayerForm(taxpayer);
                }else{
                    Toast.makeText(EditTaxpayerActivity.this,response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Taxpayer>> call, Throwable t) {
                Toast.makeText(EditTaxpayerActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
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

    private void editTaxpayer() {
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
        Api.getService().edit(taxpayer,authorization).enqueue(new Callback<Taxpayer>() {
            @Override
            public void onResponse(Call<Taxpayer> call, Response<Taxpayer> response) {
                Taxpayer tp = response.body();
                if(tp != null){
                    Intent intent = new Intent(EditTaxpayerActivity.this,TaxpayerListActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(EditTaxpayerActivity.this, "Editing taxpayer failed.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Taxpayer> call, Throwable t) {
                Toast.makeText(EditTaxpayerActivity.this, t.toString(),Toast.LENGTH_SHORT).show();
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