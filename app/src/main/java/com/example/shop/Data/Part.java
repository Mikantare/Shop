package com.example.shop.Data;

public class Part {

    private String partNumber;
    private String brand;
    private String partName;
    private String stock;
    private String deliveryDays;
    private String minQuantity;
    private String price;
    private String currency;
    private String partId;
    private String sname;
    private String sflag;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(String deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    public String getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getSflag() {
        return sflag;
    }

    public void setSflag(String sflag) {
        this.sflag = sflag;
    }

    public Part(String partNumber, String brand, String partName, String stock, String deliveryDays, String minQuantity, String price, String currency, String partId, String sname, String sflag) {
        this.partNumber = partNumber;
        this.brand = brand;
        this.partName = partName;
        this.stock = stock;
        this.deliveryDays = deliveryDays;
        this.minQuantity = minQuantity;
        this.price = price;
        this.currency = currency;
        this.partId = partId;
        this.sname = sname;
        this.sflag = sflag;


    }
}
