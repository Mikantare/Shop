package com.example.shop.Adapter;

import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Data.BrandPart;
import com.example.shop.R;

import java.util.ArrayList;

public class BrandPartAdapter extends RecyclerView.Adapter<BrandPartAdapter.BrandPartsViewHolder> {
    private ArrayList <BrandPart> brandParts;

    @NonNull
    @Override
    public BrandPartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_part_item, parent,false);
        return new BrandPartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandPartsViewHolder holder, int position) {
        BrandPart brandPart = brandParts.get(position);
        holder.textViewPartNumber.setText(brandPart.getPartNumber());
        holder.textViewBrand.setText(brandPart.getBrand());
        holder.textViewName.setText(brandPart.getPartName());
    }

    @Override
    public int getItemCount() {
        return brandParts.size();
    }

    class BrandPartsViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPartNumber;
        private TextView textViewBrand;
        private TextView textViewName;

        public BrandPartsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPartNumber = itemView.findViewById(R.id.textViewPartNumber);
            textViewBrand = itemView.findViewById(R.id.textViewBrand);
            textViewName = itemView.findViewById(R.id.textViewName);
        }
    }
}
