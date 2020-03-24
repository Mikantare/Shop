package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shop.Adapter.BrandPartAdapter;
import com.example.shop.Adapter.PartAdapter;
import com.example.shop.Data.BrandPart;
import com.example.shop.Data.Part;
import com.example.shop.Data.PartsDataBase;
import com.example.shop.Utils.JSONUtils;
import com.example.shop.Utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewResultSearch;
    private RecyclerView recyclerViewResultPartSearch;
    private EditText editTextPartNumber;
    private Button buttonSearch;
    private BrandPartAdapter brandPartAdapter;
    private String partNumber;
    private ArrayList <Part> parts;

    private PartsDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = PartsDataBase.getInstance(this);
        editTextPartNumber = findViewById(R.id.editTextPartNumber);
        buttonSearch = findViewById(R.id.buttonSearch);
        recyclerViewResultPartSearch = findViewById(R.id.recyclerViewResultPartSearch);
        recyclerViewResultSearch = findViewById(R.id.recyclerViewResultBrandSearch);
        brandPartAdapter = new BrandPartAdapter();
        brandPartAdapter.setOnPartClickListener(new BrandPartAdapter.OnPartClickListener() {
            @Override
            public void OnpartClick(int position) {
                recyclerViewResultSearch.setVisibility(View.INVISIBLE);
                recyclerViewResultPartSearch.setVisibility(View.VISIBLE);
                BrandPart brandPart = brandPartAdapter.getBrandParts().get(position);
                PartAdapter partAdapter = new PartAdapter();
                String brand = brandPart.getBrand();
                String partNumber = brandPart.getPartNumber();
                recyclerViewResultPartSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewResultPartSearch.setAdapter(partAdapter);
                JSONObject jsonObject = NetworkUtils.getPartJSONFromNetwork(partNumber, brand);
                parts = JSONUtils.getPartFromJSON(jsonObject);
                partAdapter.setParts(parts);

            }
        });


    }

    private void getData () {
        ArrayList <Part> partsFromDB = dataBase.partsDao().getAllPart();
        parts.clear();
        parts.addAll(partsFromDB);
    }

    public void search(View view) {
        recyclerViewResultSearch.setVisibility(View.VISIBLE);
        recyclerViewResultPartSearch.setVisibility(View.INVISIBLE);
        recyclerViewResultSearch.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewResultSearch.setAdapter(brandPartAdapter);
        partNumber = editTextPartNumber.getText().toString().trim();
        JSONObject jsonObject = NetworkUtils.getBrandJSONFromNetwork(partNumber);
        ArrayList <BrandPart> brandParts = JSONUtils.getBrandFromJSON(jsonObject);
        brandPartAdapter.setBrandParts(brandParts);
    }




}
