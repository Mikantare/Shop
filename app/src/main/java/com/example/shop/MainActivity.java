package com.example.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shop.Adapter.BrandPartAdapter;
import com.example.shop.Adapter.DialogToBasket;
import com.example.shop.Adapter.PartAdapter;
import com.example.shop.Adapter.PartsToBasketAdapter;
import com.example.shop.Data.BrandPart;
import com.example.shop.Data.MainViewModel;
import com.example.shop.Data.Part;
import com.example.shop.Data.PartsToBasket;
import com.example.shop.Utils.JSONUtils;
import com.example.shop.Utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogToBasket.DialogToBasketListener{

    private RecyclerView recyclerViewResultSearch;
    private RecyclerView recyclerViewResultPartSearch;
    private EditText editTextPartNumber;
    private Button buttonSearch;
    private BrandPartAdapter brandPartAdapter;
    private String partNumber;
    private MainViewModel viewModel;
    private Part partToBasket;
    private int coincidence = 2;

    @Override
    public void onFinishEditDialog(String quantity) {
        checkOnCoincidence(partToBasket.getPartId());
        switch (coincidence) {
            case 1:
                viewModel.insertPartToBasket(new PartsToBasket(partToBasket,Integer.parseInt(quantity)));
                break;
            case 0:
                Toast.makeText(this, R.string.warning_item_to_basket, Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this,BasketActivity.class);
        startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPartNumber = findViewById(R.id.editTextPartNumber);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        buttonSearch = findViewById(R.id.buttonOrder);
        recyclerViewResultPartSearch = findViewById(R.id.recyclerViewResultPartSearch);
        recyclerViewResultSearch = findViewById(R.id.recyclerViewToBasket);
        brandPartAdapter = new BrandPartAdapter();
        brandPartAdapter.setOnPartClickListener(new BrandPartAdapter.OnPartClickListener() {
            @Override
            public void OnpartClick(int position) {
                recyclerViewResultSearch.setVisibility(View.INVISIBLE);
                recyclerViewResultPartSearch.setVisibility(View.VISIBLE);
                BrandPart brandPart = brandPartAdapter.getBrandParts().get(position);
                final PartAdapter partAdapter = new PartAdapter();
                String brand = brandPart.getBrand();
                String partNumber = brandPart.getPartNumber();
                downLoadPartsList(partNumber, brand);
                final LiveData<List<Part>> partFromDb = viewModel.getParts();
                partFromDb.observe(MainActivity.this, new Observer<List<Part>>() {
                    @Override
                    public void onChanged(List<Part> parts) {
                        recyclerViewResultPartSearch.setAdapter(partAdapter);
                        partAdapter.setParts((ArrayList<Part>) parts);
                    }
                });
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        FragmentManager manager = getSupportFragmentManager();
                        DialogToBasket dialogToBasket = new DialogToBasket();
                        dialogToBasket.show(manager,"myDialog");
                        partToBasket = partAdapter.getParts().get(viewHolder.getAdapterPosition());
                    }
                });
                itemTouchHelper.attachToRecyclerView(recyclerViewResultPartSearch);
            }
        });


    }

    public void downLoadPartsList(String partNumber, String brand) {
        recyclerViewResultPartSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        JSONObject jsonObject = NetworkUtils.getPartJSONFromNetwork(partNumber, brand);
        if (jsonObject != null) {
            viewModel.deleteAllParts();
            viewModel.insertPart((List<Part>) JSONUtils.getPartFromJSON(jsonObject));
        }
    }


    public void search(View view) {
        recyclerViewResultSearch.setVisibility(View.VISIBLE);
        recyclerViewResultPartSearch.setVisibility(View.INVISIBLE);
        recyclerViewResultSearch.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewResultSearch.setAdapter(brandPartAdapter);
        partNumber = editTextPartNumber.getText().toString().trim();
        JSONObject jsonObject = NetworkUtils.getBrandJSONFromNetwork(partNumber);
        ArrayList<BrandPart> brandParts = JSONUtils.getBrandFromJSON(jsonObject);
        brandPartAdapter.setBrandParts(brandParts);
    }


    public void checkOnCoincidence (final int id) {
        final LiveData <List<PartsToBasket>> partsBasketFromDB = viewModel.getPartsToBasket();
        partsBasketFromDB.observe(this, new Observer<List<PartsToBasket>>() {
            @Override
            public void onChanged(List<PartsToBasket> partsToBaskets) {
                for (PartsToBasket parts: partsToBaskets) {
                    if (id == parts.getPartId() || parts.getPartId() != 0){
                        coincidence = 0;
                    } else {
                        coincidence = 1;
                    }
                }
            }
        });
    }

}
