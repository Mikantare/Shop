package com.example.shop.Adapter;

import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Data.BrandPart;
import com.example.shop.Pojo.BrandPojo;
import com.example.shop.Pojo.BrandsPojo;
import com.example.shop.R;

import java.util.ArrayList;
import java.util.List;

public class BrandPartAdapter extends RecyclerView.Adapter <BrandPartAdapter.BrandPartsViewHolder> {
    private ArrayList <BrandPart> brandParts;
    private OnPartClickListener onPartClickListener;
    private List<BrandPojo> brandPojos;

    public BrandPartAdapter() {
    }

    public void setOnPartClickListener(OnPartClickListener onPartClickListener) {
        this.onPartClickListener = onPartClickListener;
    }

    public interface OnPartClickListener {
        void OnpartClick(int position);
    }

    public ArrayList <BrandPart> getBrandParts() {
        return brandParts;
    }

    public void setBrandParts(ArrayList <BrandPart> brandParts) {
        this.brandParts = brandParts;
        notifyDataSetChanged();
    }

    // Для Pojo

    public BrandPartAdapter(List <BrandPojo> brandPojos) {
        this.brandPojos = brandPojos;
    }

    public void setBrandPojos(List <BrandPojo> brandPojos) {
        this.brandPojos = brandPojos;
        notifyDataSetChanged();
    }

    // Для Pojo

    @NonNull
    @Override
    public BrandPartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_part_item, parent, false);
        return new BrandPartsViewHolder(view);
    }
    //Для Pojo

    @Override
    public void onBindViewHolder(@NonNull BrandPartsViewHolder holder, int position) {

////       BrandPart brandPart = brandParts.get(position);
//        holder.textViewPartNumber.setText(brandPart.getPartNumber());
//        holder.textViewBrand.setText(brandPart.getBrand());
//        holder.textViewName.setText(brandPart.getPartName());

        //Для Pojo
        BrandPojo brandPart = brandPojos.get(position);

        holder.textViewPartNumber.setText(brandPart.getNr());
        holder.textViewBrand.setText(brandPart.getBrand());
        holder.textViewName.setText(brandPart.getName());
    }

    @Override
    public int getItemCount() {
        return brandPojos.size();
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onPartClickListener != null) {
                        onPartClickListener.OnpartClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
