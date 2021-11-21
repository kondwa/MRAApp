package com.example.mraapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaxpayersAdapter extends RecyclerView.Adapter<TaxpayersAdapter.TaxpayersViewHolder> {
    Context context;
    List<Taxpayer> taxpayerList;

    public TaxpayersAdapter(Context context, List<Taxpayer> taxpayerList) {
        this.context = context;
        this.taxpayerList = taxpayerList;
    }

    @NonNull
    @Override
    public TaxpayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.taxpayer_list_item,null);
        TaxpayersViewHolder taxpayersViewHolder = new TaxpayersViewHolder(view);
        return taxpayersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaxpayersViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tradingName.setText(taxpayerList.get(position).getTradingName());
        holder.tpin.setText("TPIN: "+taxpayerList.get(position).getTpin());
        holder.businessCertificateNumber.setText("Business Certificate Number: "+taxpayerList.get(position).getBusinessCertificateNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,taxpayerList.get(position).getTradingName()+" clicked!",Toast.LENGTH_SHORT);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,taxpayerList.get(position).getTradingName()+" long clicked!",Toast.LENGTH_LONG);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return taxpayerList.size();
    }

    class TaxpayersViewHolder extends RecyclerView.ViewHolder{
        TextView tradingName,tpin,businessCertificateNumber;
        public TaxpayersViewHolder(@NonNull View itemView) {
            super(itemView);
            tradingName = itemView.findViewById(R.id.trading_name);
            tpin = itemView.findViewById(R.id.tpin);
            businessCertificateNumber = itemView.findViewById(R.id.business_certificate_number);
        }
    }
}
