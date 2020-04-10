package com.example.shop.Adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Data.PartsToBasket;
import com.example.shop.R;

public class PartsToBasketAdapter {

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
