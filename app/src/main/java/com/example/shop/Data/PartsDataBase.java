package com.example.shop.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Part.class,PartsToBasket.class}, version = 3, exportSchema = false)
public abstract class PartsDataBase extends RoomDatabase {
    private static PartsDataBase dataBase;
    private static final String DB_NAME = "part.db";
    private static final Object LOCK = new Object();


    public static PartsDataBase getInstance(Context context) {
        synchronized (LOCK) {
            if (dataBase == null) {
                dataBase = Room.databaseBuilder(context, PartsDataBase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return dataBase;
    }

    public abstract PartsDao partsDao();

}
