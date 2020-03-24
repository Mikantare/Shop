package com.example.shop.Data;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
public interface PartsDao {
    @Query("SELECT * FROM parts ORDER BY price")
    ArrayList <Part> getAllPart();

    @Query("DELETE FROM parts")
    void deleteAllParts();

    @Query("SELECT * FROM parts WHERE partId ==:partId")
    Part getPartFromID (String partId);
}
