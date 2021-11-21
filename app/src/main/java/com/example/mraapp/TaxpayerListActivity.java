package com.example.mraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaxpayerListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Taxpayer> taxpayerList;
    String token;
    String authorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxpayer_list);
        recyclerView = findViewById(R.id.recyclerView);
        SessionManager.setContext(this);
        authorization = SessionManager.getAuthorization();
        if(authorization != null) {
            getTaxpayerList();
        }else {
            finish();
        }
    }

    private void getTaxpayerList() {
        Api.getService().getAll(authorization).enqueue(new Callback<List<Taxpayer>>() {
            @Override
            public void onResponse(Call<List<Taxpayer>> call, Response<List<Taxpayer>> response) {
                taxpayerList = response.body();
                setDataInRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Taxpayer>> call, Throwable t) {
                Log.e("Get All",t.toString());
                //Toast.makeText(,t.toString(),Toast.LENGTH_LONG);
            }
        });
    }

    private void setDataInRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        TaxpayersAdapter taxpayersAdapter = new TaxpayersAdapter(this,taxpayerList);
        recyclerView.setAdapter(taxpayersAdapter);
    }
}