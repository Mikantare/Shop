package com.example.shop.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
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


}
