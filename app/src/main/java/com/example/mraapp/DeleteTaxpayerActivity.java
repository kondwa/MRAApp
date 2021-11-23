package com.example.mraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteTaxpayerActivity extends AppCompatActivity {
    private int position;
    private Taxpayer taxpayer;
    private String authorization;
    private EditText etTpin;
    private Button btnCancel;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_taxpayer);
        authorize();
        etTpin = findViewById(R.id.etTpin);
        btnCancel = findViewById(R.id.btnDelete);
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTaxpayer();
            }
        });
        Intent intent = getIntent();
        position = intent.getIntExtra("position",-1);
        if(position != -1){
            getTaxpayer();
        }
    }

    private void deleteTaxpayer() {
        DeleteRequest deleteRequest = new DeleteRequest(etTpin.getText().toString());
        Api.getService().delete(deleteRequest,authorization).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(DeleteTaxpayerActivity.this,TaxpayerListActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(DeleteTaxpayerActivity.this,response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(DeleteTaxpayerActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTaxpayer() {
        Api.getService().getAll(authorization).enqueue(new Callback<List<Taxpayer>>() {
            @Override
            public void onResponse(Call<List<Taxpayer>> call, Response<List<Taxpayer>> response) {
                if(response.isSuccessful()){
                    taxpayer = response.body().get(position);
                    etTpin.setText(taxpayer.getTpin());
                }else{
                    Toast.makeText(DeleteTaxpayerActivity.this,response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Taxpayer>> call, Throwable t) {
                Toast.makeText(DeleteTaxpayerActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
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
}