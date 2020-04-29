package com.example.shop.Adapter;

import android.text.Editable;
import android.text.TextWatcher;
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
    private String[] mDataset;

//    public PartsToBasketAdapter(String[] mDataset) {
//        this.mDataset = mDataset;
//    }

    public void setPartsToBaskets(ArrayList<PartsToBasket> partsToBaskets) {
        this.partsToBaskets = partsToBaskets;
        Log.i("Result","" + partsToBaskets.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PartBasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.part_item_basket,parent,false);
        return new PartBasketViewHolder(view, new MyCustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(@NonNull PartBasketViewHolder holder, int position) {
        PartsToBasket partsToBasket = partsToBaskets.get(position);
        holder.textViewPartNumber.setText(partsToBasket.getPartNumber());
        holder.textViewBrand.setText(partsToBasket.getBrand());
        holder.textViewName.setText(partsToBasket.getPartName());
        holder.textViewPrice.setText(Integer.toString(partsToBasket.getPrice()));
//        holder.textViewSum.setText(Integer.toString(partsToBasket.getPrice()*partsToBasket.getQuantity()));
        holder.textViewQuantity.setText(Integer.toString(partsToBasket.getQuantity()));
        holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());
        try {
            holder.textViewSum.setText(mDataset[holder.getAdapterPosition()]);
//            holder.editTextQuantity.setText(mDataset[holder.getAdapterPosition()]);
            holder.editTextQuantity.setText(Integer.toString(partsToBasket.getQuantity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return partsToBaskets.size();
    }


    class PartBasketViewHolder extends RecyclerView.ViewHolder {
    public MyCustomEditTextListener myCustomEditTextListener;
    private TextView textViewName;
    private TextView textViewBrand;
    private TextView textViewPartNumber;
    private TextView textViewPrice;
    private TextView textViewSum;
    private TextView textViewQuantity;
    private EditText editTextComment;
    private EditText editTextQuantity;

    public PartBasketViewHolder(@NonNull View itemView, MyCustomEditTextListener myCustomEditTextListener) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewBrand = itemView.findViewById(R.id.textViewBrand);
        textViewPartNumber = itemView.findViewById(R.id.textViewPartNumber);
        textViewPrice = itemView.findViewById(R.id.textViewPrice);
        textViewSum = itemView.findViewById(R.id.textViewSum);
        editTextComment = itemView.findViewById(R.id.editTextComment);
        editTextQuantity = itemView.findViewById(R.id.editTextQuantity);
        textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
        this.myCustomEditTextListener = myCustomEditTextListener;
    }
}

    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            mDataset[position] = charSequence.toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }



}
