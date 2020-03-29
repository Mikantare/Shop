package com.example.shop.Utils;

import android.util.Log;
import android.util.Pair;

import com.example.shop.Data.BrandPart;
import com.example.shop.Data.Part;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {
    private static final String KEY_RESULT = "result";

    public static ArrayList<BrandPart> getBrandFromJSON(JSONObject jsonObject) {
        ArrayList<BrandPart> result = new ArrayList<>();
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
                BrandPart brandPart = new BrandPart(partNumber, brand, partName);
                result.add(brandPart);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Part> getPartFromJSON(JSONObject jsonObject) {
        ArrayList<Part> result = new ArrayList<>();
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
                String stock = objectBrandPart.getString("stock");
                String deliveryDays = objectBrandPart.getString("ddays");
                String minQuantity = objectBrandPart.getString("minq");
                int price = objectBrandPart.getInt("price");
                String currency = objectBrandPart.getString("currency");
                int partId = objectBrandPart.getInt("gid");
                String sname = objectBrandPart.getString("sname");
                String sflag = objectBrandPart.getString("sflag");
                Log.i("Result", partName);
                if (sname.equals("Владимир") || sname.equals("Центр")) {
                    switch (sname) {
                        case "Владимир":
                            Part part = new Part(partId, partNumber, brand, partName, stock, deliveryDays, minQuantity, price, currency, sname, sflag);
                            result.add(part);
                            break;
                        default:
                            for (int j = 0; j < result.size(); j++) {
                                try {
                                    if (!partNumber.equals(result.get(j).getPartNumber())) {
                                        Log.i("Result", "" + j);
                                        part = new Part(partId, partNumber, brand, partName, stock, deliveryDays, minQuantity, price, currency, sname, sflag);
                                        result.add(part);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                    }


                }
            }
            for (int i = 0; i < result.size() - 1; i++) {
                if (result.get(i).getPartNumber() == result.get(i + 1).getPartNumber()) {
                    result.remove(i);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


}
