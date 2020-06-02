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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shop.Adapter.BrandPartAdapter;
import com.example.shop.Adapter.DialogToBasket;
import com.example.shop.Adapter.PartAdapter;
import com.example.shop.Api.ApiFactory;
import com.example.shop.Api.ApiService;
import com.example.shop.Data.BrandPart;
import com.example.shop.Data.MainViewModel;
import com.example.shop.Data.Part;
import com.example.shop.Data.PartsToBasket;
import com.example.shop.Pojo.BrandPojo;
import com.example.shop.Pojo.BrandsPojo;
import com.example.shop.Utils.JSONUtils;
import com.example.shop.Utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements DialogToBasket.DialogToBasketListener {

    private RecyclerView recyclerViewResultSearch;
    private RecyclerView recyclerViewResultPartSearch;
    private EditText editTextPartNumber;
    private Button buttonSearch;
    private BrandPartAdapter brandPartAdapter;
    private String partNumber;
    private MainViewModel viewModel;
    private Part partToBasket;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itemMain:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.itemBasket:
                Intent intentToFavourite = new Intent(this, BasketActivity.class);
                startActivity(intentToFavourite);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFinishEditDialog(String quantity, int buttonID) {
        PartsToBasket parts = null;
        try {
            parts = viewModel.getPartsToBasketToID(partToBasket.getPartId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (parts != null) {
            Toast.makeText(this, R.string.warning_item_to_basket, Toast.LENGTH_SHORT).show();
        } else {
            viewModel.insertPartToBasket(new PartsToBasket(partToBasket, Integer.parseInt(quantity)));
            if (buttonID == 0) {
                Intent intent = new Intent(this, BasketActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPartNumber = findViewById(R.id.editTextPartNumber);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        buttonSearch = findViewById(R.id.buttonClearBasket);
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
                final LiveData <List <Part>> partFromDb = viewModel.getParts();
                partFromDb.observe(MainActivity.this, new Observer <List <Part>>() {
                    @Override
                    public void onChanged(List <Part> parts) {
                        recyclerViewResultPartSearch.setAdapter(partAdapter);
                        partAdapter.setParts((ArrayList <Part>) parts);
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
                        dialogToBasket.show(manager, "myDialog");
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
            viewModel.insertPart((List <Part>) JSONUtils.getPartFromJSON(jsonObject));
        }
    }


    public void search(View view) {

        searchToRetrofit();
//        InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(buttonSearch.getWindowToken(),
//                InputMethodManager.HIDE_NOT_ALWAYS);
//        recyclerViewResultSearch.setVisibility(View.VISIBLE);
//        recyclerViewResultPartSearch.setVisibility(View.INVISIBLE);
//        recyclerViewResultSearch.setLayoutManager(new LinearLayoutManager(this));
//        recyclerViewResultSearch.setAdapter(brandPartAdapter);
//        partNumber = editTextPartNumber.getText().toString().trim();
//        JSONObject jsonObject = NetworkUtils.getBrandJSONFromNetwork(partNumber);
//        ArrayList<BrandPart> brandParts = JSONUtils.getBrandFromJSON(jsonObject);
//        brandPartAdapter.setBrandParts(brandParts);
    }

    public void searchToRetrofit() {
        ApiFactory apiFactory = ApiFactory.getInstance();
        final ApiService apiService = apiFactory.getApiservice();
        apiService.getPojoParts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer <BrandsPojo>() {
                    @Override
                    public void accept(BrandsPojo brandsPojo) throws Exception {
                        recyclerViewResultSearch.setVisibility(View.VISIBLE);
                        recyclerViewResultPartSearch.setVisibility(View.INVISIBLE);
                        recyclerViewResultSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerViewResultSearch.setAdapter(brandPartAdapter);
                        brandPartAdapter.setBrandPojos(brandsPojo.getParts());
                    }
                }, new Consumer <Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
