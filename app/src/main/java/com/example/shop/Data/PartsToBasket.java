package com.example.shop.Data;


import androidx.room.Entity;

@Entity(tableName = "part_to_basket")
public class PartsToBasket extends Part {
    public PartsToBasket(int partId, String partNumber, String brand, String partName, String stock, String deliveryDays, String minQuantity, int price, String currency, String sname, String sflag) {
        super(partId, partNumber, brand, partName, stock, deliveryDays, minQuantity, price, currency, sname, sflag);
    }
}
