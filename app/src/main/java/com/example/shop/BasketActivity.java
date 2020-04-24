package com.example.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;

import com.example.shop.Adapter.PartsToBasketAdapter;
import com.example.shop.Data.MainViewModel;
import com.example.shop.Data.Part;
import com.example.shop.Data.PartsToBasket;
import com.example.shop.Utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {
    private RecyclerView recyclerViewToBasket;
    private MainViewModel viewModel;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itemMain:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.itemBasket:
                Intent intentToFavourite = new Intent(this, BasketActivity.class);
                startActivity(intentToFavourite);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        Intent intent = getIntent();
        recyclerViewToBasket = findViewById(R.id.recyclerViewToBasket);
        recyclerViewToBasket.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        final PartsToBasketAdapter adapter = new PartsToBasketAdapter();
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        final LiveData<List<PartsToBasket>> partsToBasket = viewModel.getPartsToBasket();
        partsToBasket.observe(this, new Observer<List<PartsToBasket>>() {
            @Override
            public void onChanged(List<PartsToBasket> partsToBaskets) {
                recyclerViewToBasket.setAdapter(adapter);
                for (PartsToBasket parts : partsToBaskets) {
                }
                adapter.setPartsToBaskets((ArrayList<PartsToBasket>) partsToBaskets);
            }
        });
    }

    public void Cleare(View view) {
        viewModel.deleteAllPartsToBasket();
    }

    public void CreateOrder(View view) {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        final LiveData<List<PartsToBasket>> partsToBasket = viewModel.getPartsToBasket();
        partsToBasket.observe(this, new Observer<List<PartsToBasket>>() {
            @Override
            public void onChanged(List<PartsToBasket> partsToBaskets) {
                for (PartsToBasket parts : partsToBaskets) {
                    NetworkUtils.partToBasket(parts.getPartId(),parts.getQuantity());
                }
                viewModel.deleteAllPartsToBasket();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

    }
}