package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shop.Adapter.BrandPartAdapter;
import com.example.shop.Adapter.PartAdapter;
import com.example.shop.Data.BrandPart;
import com.example.shop.Data.MainViewModel;
import com.example.shop.Data.Part;
import com.example.shop.Data.PartsDataBase;
import com.example.shop.Utils.JSONUtils;
import com.example.shop.Utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewResultSearch;
    private RecyclerView recyclerViewResultPartSearch;
    private EditText editTextPartNumber;
    private Button buttonSearch;
    private BrandPartAdapter brandPartAdapter;
    private String partNumber;
    private ArrayList<Part> parts = new ArrayList<>();

    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPartNumber = findViewById(R.id.editTextPartNumber);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
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
                final PartAdapter partAdapter = new PartAdapter();
                String brand = brandPart.getBrand();
                String partNumber = brandPart.getPartNumber();
                recyclerViewResultPartSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewResultPartSearch.setAdapter(partAdapter);
                JSONObject jsonObject = NetworkUtils.getPartJSONFromNetwork(partNumber, brand);
                viewModel.insertPart((List<Part>) JSONUtils.getPartFromJSON(jsonObject));
                LiveData<List<Part>> partFromDb = viewModel.getParts();
                partFromDb.observe(MainActivity.this, new Observer<List<Part>>() {
                    @Override
                    public void onChanged(List<Part> parts) {
                        partAdapter.setParts((ArrayList<Part>) parts);
                    }
                });
            }
        });


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


}
