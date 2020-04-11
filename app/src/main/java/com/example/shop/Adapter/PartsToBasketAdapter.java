package com.example.shop.Adapter;

import android.util.Log;
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

public class PartsToBasketAdapter extends RecyclerView.Adapter<PartsToBasketAdapter.PartBasketViewHolder> {
    ArrayList<PartsToBasket> partsToBaskets;

    public void setPartsToBaskets(ArrayList<PartsToBasket> partsToBaskets) {
        this.partsToBaskets = partsToBaskets;
        Log.i("Result","" + partsToBaskets.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PartBasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.part_item_basket,parent,false);
        return new PartBasketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartBasketViewHolder holder, int position) {
        PartsToBasket partsToBasket = partsToBaskets.get(position);
        holder.textViewPartNumber.setText(partsToBasket.getPartNumber());
        holder.textViewBrand.setText(partsToBasket.getBrand());
        holder.textViewName.setText(partsToBasket.getPartName());
//        holder.editTextQuantity.setText(partsToBasket.getQuantity());

    }

    @Override
    public int getItemCount() {
        return partsToBaskets.size();
    }

    class PartBasketViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewName;
    private TextView textViewBrand;
    private TextView textViewPartNumber;
    private TextView textViewPrice;
    private TextView textViewSum;
    private EditText editTextComment;
    private EditText editTextQuantity;

    public PartBasketViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewBrand = itemView.findViewById(R.id.textViewBrand);
        textViewPartNumber = itemView.findViewById(R.id.textViewPartNumber);
        textViewPrice = itemView.findViewById(R.id.textViewPrice);
        textViewSum = itemView.findViewById(R.id.textViewSum);
        editTextComment = itemView.findViewById(R.id.editTextComment);
        editTextQuantity = itemView.findViewById(R.id.editTextQuantity);
    }
}
}
