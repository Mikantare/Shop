package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.shop.Data.BrandPart;
import com.example.shop.Utils.JSONUtils;
import com.example.shop.Utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork("2825");
        ArrayList <BrandPart> brandParts = JSONUtils.getBrandFromJSON(jsonObject);
        for (int i = 0; i<brandParts.size(); i++) {
            Log.i("RESULT",brandParts.get(i).getPartName());

        }
    }
}
