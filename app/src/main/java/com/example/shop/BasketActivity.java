package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import com.example.shop.Adapter.PartsToBasketAdapter;
import com.example.shop.Data.MainViewModel;
import com.example.shop.Data.Part;
import com.example.shop.Data.PartsToBasket;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {
    private RecyclerView recyclerViewToBasket;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        recyclerViewToBasket = findViewById(R.id.recyclerViewToBasket);
        final PartsToBasketAdapter adapter = new PartsToBasketAdapter();
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        final LiveData<List<PartsToBasket>> partsToBasket = viewModel.getPartsToBasket();
        partsToBasket.observe(BasketActivity.this, new Observer<List<PartsToBasket>>() {
            @Override
            public void onChanged(List<PartsToBasket> partsToBaskets) {
            recyclerViewToBasket.setAdapter(adapter);
            adapter.setPartsToBaskets((ArrayList<PartsToBasket>) partsToBasket);
            }
        });
    }
}
