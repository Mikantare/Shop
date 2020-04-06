package com.example.shop.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Data.Part;
import com.example.shop.MainActivity;
import com.example.shop.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.shop.R.color.grenn;
import static com.example.shop.R.color.material_blue_grey_800;
import static com.example.shop.R.color.orange;

public class PartAdapter extends RecyclerView.Adapter <PartAdapter.PartViewHolder> {
    private ArrayList <Part> parts;



    public ArrayList<Part> getParts() {
        return parts;
    }

    public void setParts(ArrayList<Part> parts) {
        this.parts = parts;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public PartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.part_item,parent,false);
        PartViewHolder viewHolder = new PartViewHolder(view);
        return viewHolder;


    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull PartViewHolder holder, int position) {
        Part part = parts.get(position);
        holder.textViewName.setText(part.getPartName());
        holder.textViewBrand.setText(part.getBrand());
        holder.textViewPartNumber.setText(part.getPartNumber());
        holder.textViewPrice.setText(part.getStringPrice());
        switch (part.getDeliveryDaysToInt()) {
            case 0:
                holder.textViewPrice.setBackgroundColor(holder.itemView.getResources().getColor(grenn));
                break;
            case 1:
                holder.textViewPrice.setBackgroundColor(holder.itemView.getResources().getColor(orange));
                break;
                default:
                    holder.textViewPrice.setBackgroundColor(holder.itemView.getResources().getColor(android.R.color.holo_red_light));
        }
    }

    @Override
    public int getItemCount() {
        return parts.size();
    }

    class PartViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewBrand;
        private TextView textViewPartNumber;
        private TextView textViewPrice;


        public PartViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewBrand = itemView.findViewById(R.id.textViewBrand);
            textViewPartNumber = itemView.findViewById(R.id.textViewPartNumber);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
