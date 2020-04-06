package com.example.shop.Data;


import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "part_to_basket")
public class PartsToBasket extends Part {

    public PartsToBasket(Integer partId, String partNumber, String brand, String partName, String stock, String deliveryDays, String minQuantity, int price, String currency, String sname, String sflag) {
        super(partId, partNumber, brand, partName, stock, deliveryDays, minQuantity, price, currency, sname, sflag);
    }

    @Ignore
    public PartsToBasket(Part part) {
        super(part.getPartId(), part.getPartNumber(),part.getBrand(),part.getPartName(),part.getStock(),part.getDeliveryDays(),part.getMinQuantity(), part.getPrice(),part.getCurrency(),part.getSname(),part.getSflag());
    }
}
