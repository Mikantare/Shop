package com.example.shop.Data;


import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "part_to_basket")
public class PartsToBasket extends Part {
    private int quantity;


    public PartsToBasket(Integer partId, String partNumber, String brand, String partName, String stock, String deliveryDays, String minQuantity, int price, String currency, String sname, String sflag, int quantity) {
        super(partId, partNumber, brand, partName, stock, deliveryDays, minQuantity, price, currency, sname, sflag);
        this.quantity = quantity;
    }

    @Ignore
    public PartsToBasket(Part part, int quantity) {
        super(part.getPartId(), part.getPartNumber(),part.getBrand(),part.getPartName(),part.getStock(),part.getDeliveryDays(),part.getMinQuantity(), part.getPrice(),part.getCurrency(),part.getSname(),part.getSflag());
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
