package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shop.Adapter.BrandPartAdapter;
import com.example.shop.Data.BrandPart;
import com.example.shop.Utils.JSONUtils;
import com.example.shop.Utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewResultSearch;
    private EditText editTextPartNumber;
    private Button buttonSearch;
    private BrandPartAdapter brandPartAdapter;
    private String partNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPartNumber = findViewById(R.id.editTextPartNumber);
        buttonSearch = findViewById(R.id.buttonSearch);
        recyclerViewResultSearch = findViewById(R.id.recyclerViewResultSearch);
        brandPartAdapter = new BrandPartAdapter();
        brandPartAdapter.setOnPartClickListener(new BrandPartAdapter.OnPartClickListener() {
            @Override
            public void OnpartClick(int position) {
//                String partNumber = brandPartAdapter.getBrandParts().get(position);

            }
        });


    }

    public void search(View view) {
        recyclerViewResultSearch.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewResultSearch.setAdapter(brandPartAdapter);
        partNumber = editTextPartNumber.getText().toString().trim();
        JSONObject jsonObject = NetworkUtils.getBrandJSONFromNetwork(partNumber);
        ArrayList <BrandPart> brandParts = JSONUtils.getBrandFromJSON(jsonObject);
        brandPartAdapter.setBrandParts(brandParts);
    }




}
