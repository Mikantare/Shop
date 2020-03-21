package com.example.shop.Data;

public class BrandPart {

    private String partNumber;
    private String brand;
    private String partName;

    public BrandPart(String partNumber, String brand, String partName) {
        this.partNumber = partNumber;
        this.brand = brand;
        this.partName = partName;
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
}
