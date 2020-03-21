package com.example.shop.Utils;

import android.util.Log;

import com.example.shop.Data.BrandPart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONUtils {
    private static final String KEY_RESULT = "result";

    public static ArrayList <BrandPart> getBrandFromJSON (JSONObject jsonObject) {
        ArrayList <BrandPart> result = new ArrayList<>();
        if (jsonObject == null) {
            return result;
        }
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_RESULT);
            for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objectBrandPart = jsonArray.getJSONObject(i);
                String partNumber = objectBrandPart.getString("nr");
                String brand = objectBrandPart.getString("brand");
                String partName = objectBrandPart.getString("name");
                BrandPart brandPart = new BrandPart(partNumber,brand,partName);
                result.add(brandPart);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


}
