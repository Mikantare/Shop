package com.example.shop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Data.PartsToBasket;
import com.example.shop.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class PartsToBasketAdapter extends RecyclerView.Adapter<PartsToBasketAdapter.PartsToBasketViewHolder> {
            private ArrayList <PartsToBasket> partsToBaskets;

    public void setPartsToBaskets(ArrayList<PartsToBasket> partsToBaskets) {
        this.partsToBaskets = partsToBaskets;
        notifyDataSetChanged();
    }

    public PartsToBasketAdapter(ArrayList<PartsToBasket> partsToBaskets) {
        this.partsToBaskets = partsToBaskets;
    }

    @NonNull
    @Override
    public PartsToBasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.part_item_basket, parent,false);
        return new PartsToBasketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartsToBasketViewHolder holder, int position) {
        PartsToBasket partsToBasket = partsToBaskets.get(position);
        holder.textViewPartNumber.setText(partsToBasket.getPartNumber());
        holder.textViewBrand.setText(partsToBasket.getBrand());
        holder.textViewName.setText(partsToBasket.getPartName());
        holder.editTextQuantity.setText(partsToBasket.getQuantity());
    }

    @Override
    public int getItemCount() {
        return partsToBaskets.size();
    }

    class PartsToBasketViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName, textQuantityLable, textViewSum;
        private TextView textViewBrand;
        private TextView textViewPartNumber;
        private TextView textViewPrice;
        private EditText editTextQuantity, editTextComment;


        public PartsToBasketViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPartNumber = itemView.findViewById(R.id.textViewPartNumber);
            textViewBrand = itemView.findViewById(R.id.textViewBrand);
            textViewName = itemView.findViewById(R.id.textViewName);
            textQuantityLable = itemView.findViewById(R.id.textQuantityLable);
            textViewSum = itemView.findViewById(R.id.textViewSum);
            editTextQuantity = itemView.findViewById(R.id.editTextQuantity);
            editTextComment = itemView.findViewById(R.id.editTextComment);
        }
    }
}
