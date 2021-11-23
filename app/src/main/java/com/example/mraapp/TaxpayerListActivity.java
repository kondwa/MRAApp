package com.example.mraapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaxpayerListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Taxpayer> taxpayerList;
    String authorization;
    FloatingActionButton fab;

    @Override
    protected void onResume() {
        super.onResume();
        authorize();
    }

    @Override
    protected void onStart() {
        super.onStart();
        authorize();
    }

    private void authorize() {
        SessionManager.setContext(this);
        authorization = SessionManager.getAuthorization();
        if(authorization == null){
            showLoginForm();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxpayer_list);
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaxpayerListActivity.this,AddTaxpayerActivity.class);
                startActivity(intent);
            }
        });
        SessionManager.setContext(this);
        authorization = SessionManager.getAuthorization();
        if(authorization != null) {
            getTaxpayerList();
        }else {
            showLoginForm();
        }
    }

    private void showLoginForm() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_action:
                doLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void doLogout() {
        LogoutRequest logoutRequest = new LogoutRequest(SessionManager.getUsername());
        Api.getService().logout(logoutRequest,SessionManager.getAuthorization()).enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                LogoutResponse logoutResponse = response.body();
                if(logoutResponse!= null && logoutResponse.getResultCode() == 1){
                    SessionManager.setAuthorization(null);
                    SessionManager.setUsername(null);
                    showLoginForm();
                }else {
                    if(logoutResponse!= null){
                        Toast.makeText(TaxpayerListActivity.this,logoutResponse.getRemark(),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(TaxpayerListActivity.this,"Logout failed.",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                Toast.makeText(TaxpayerListActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
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
                Toast.makeText(TaxpayerListActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
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