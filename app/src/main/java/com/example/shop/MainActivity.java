package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.shop.Adapter.BrandPartAdapter;
import com.example.shop.Data.BrandPart;
import com.example.shop.Utils.JSONUtils;
import com.example.shop.Utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewResultSearch;
    private BrandPartAdapter brandPartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewResultSearch = findViewById(R.id.recyclerViewResultSearch);
        brandPartAdapter = new BrandPartAdapter();
        recyclerViewResultSearch.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewResultSearch.setAdapter(brandPartAdapter);

        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork("2825");
        ArrayList <BrandPart> brandParts = JSONUtils.getBrandFromJSON(jsonObject);

    }
}
