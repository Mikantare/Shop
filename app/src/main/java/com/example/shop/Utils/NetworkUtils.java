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

public  class NetworkUtils {

    private static final String BASE_URL_SEARCH_BRAND = "http://portal.moskvorechie.ru/portal.api?l=%s&p=%s&act=brand_by_nr&nr=%s&oe&name";
    private static final String USER_NAME = "plotnikov";
    private static final String API_KEY = "U66xkswgBstTKNAWvmPFQNqqpJy2kPn1mwiiEBORqMVM8lSyg9CfphEOPpvs6pkE";


    public static URL buildBrandURL(String partNumber) {
        URL searchBrandURL = null;
        Uri uri = Uri.parse(String.format(BASE_URL_SEARCH_BRAND, USER_NAME, API_KEY, partNumber));
        try {
            searchBrandURL = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return searchBrandURL;
    }

    public static JSONObject getJSONFromNetwork(String partNumber) {
        JSONObject result = null;
        URL url = buildBrandURL(partNumber);
        try {
            result =new JSONLoadTask().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static class JSONLoadTask extends AsyncTask<URL, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(URL... urls) {
            JSONObject result = null;
            if (urls == null || urls.length == 0) {
                return result;
            }
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection) urls[0].openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }
                result = new JSONObject(bufferedReader.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
            return result;
        }
    }


}

