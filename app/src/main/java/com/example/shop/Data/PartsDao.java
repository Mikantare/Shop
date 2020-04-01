package com.example.shop.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PartsDao {
    @Query("SELECT * FROM parts ORDER BY price")
    LiveData<List<Part>> getAllPart();

    @Query("DELETE FROM parts")
    void deleteAllParts();

    @Query("SELECT * FROM parts WHERE partId ==:partId")
    Part getPartFromID(String partId);

    @Insert
    void insertParts(List<Part> parts);

    @Query("SELECT * FROM part_to_basket")
    LiveData<List<PartsToBasket>> getAllPartToBasket();

    @Query("DELETE FROM part_to_basket")
    void deleteAllPartToBasket();

    @Query("SELECT * FROM part_to_basket WHERE partId ==:partId")
    Part getPartToBasketFromID(String partId);

    @Insert
    void insertPartToBasket(PartsToBasket partsToBasket);

    @Delete
    void deletePartToBasket (PartsToBasket partsToBasket);


}
