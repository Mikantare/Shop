package com.example.shop.Utils;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.UserHandle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class NetworkUtils {

    private static final String BASE_BRABD_URL_SEARCH_BRAND = "http://portal.moskvorechie.ru/portal.api?l=%s&p=%s&act=brand_by_nr&nr=%s&oe&name&cs=utf8";
    private static final String BASE_PART_URL_SEARCH_BRAND = "http://portal.moskvorechie.ru/portal.api?l=%s&p=%s&act=price_by_nr_firm&v=1&nr=%s&f=%s&cs=utf8%s%s";
    private static final String USER_NAME = "plotnikov";
    private static final String API_KEY = "U66xkswgBstTKNAWvmPFQNqqpJy2kPn1mwiiEBORqMVM8lSyg9CfphEOPpvs6pkE";

    //    При использовании данного параметра, в результатах данной процедуру будут также выводиться бренды на которые есть аналоги на нашем складе. "" для отключения
    private static final String API_ANALOG_ON = "&alt";
    //    При использовании данного параметра, из результатов скрываются все предложения со сторонних складов (позиции под заказ). Будет отображаться только наличие с локальных складов. "" для отключения
    private static final String API_EXISTENS = "";

    public static URL buildBrandURL(String partNumber) {
        URL searchBrandURL = null;
        Uri uri = Uri.parse(String.format(BASE_BRABD_URL_SEARCH_BRAND, USER_NAME, API_KEY, partNumber));
        try {

            searchBrandURL = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return searchBrandURL;
    }

    public static URL buildPartURL(String partNumber, String brand) {
        URL searchBrandURL = null;
        Uri uri = Uri.parse(String.format(BASE_PART_URL_SEARCH_BRAND, USER_NAME, API_KEY, partNumber, brand,API_ANALOG_ON,API_EXISTENS));
        try {
            searchBrandURL = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return searchBrandURL;
    }

    public static JSONObject getPartJSONFromNetwork(String partNumber, String brand) {
        JSONObject result = null;
        URL url = buildPartURL(partNumber, brand);
        try {
            result = new JSONLoadTask().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (result != null) {
        }
        return result;
    }


    public static JSONObject getBrandJSONFromNetwork(String partNumber) {
        JSONObject result = null;
        URL url = buildBrandURL(partNumber);
        try {
            result = new JSONLoadTask().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (result != null) {
        }
        return result;
    }

    private static class JSONLoadTask extends AsyncTask<URL, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(URL... urls) {
            JSONObject result = null;
            if (urls == null || urls.length == 0) {
                return result;
            }
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) urls[0].openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder builder = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    builder.append(line);
                    line = reader.readLine();

                }
                result = new JSONObject(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return result;
        }
    }


}

