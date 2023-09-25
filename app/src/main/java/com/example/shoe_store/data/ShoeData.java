package com.example.shoe_store.data;

public class ShoeData {
    public ShoeData(String name, int price, float rate, String brand, int brandImage, int shoeImage) {
        Name = name;
        Price = price;
        Rate = rate;
        Brand = brand;
        BrandImage = brandImage;
        ShoeImage = shoeImage;
    }

    @Override
    public String toString() {
        return "ShoeData{" +
                "Name='" + Name + '\'' +
                ", Price='" + Integer.toString(Price) + '\'' +
                ", Rate='" + Float.toString(Rate) + '\'' +
                ", Brand='" + Brand + '\'' +
                ", BrandImage='" + BrandImage + '\'' +
                ", ShoeImage='" + ShoeImage + '\'' +
                '}';
    }

    private String Name;
    private int Price;
    private float Rate;
    private String Brand;
    private int BrandImage;
    private int ShoeImage;
    public int getBrandImage() {
        return BrandImage;
    }

    public void setBrandImage(int brandImage) {
        BrandImage = brandImage;
    }

    public int getShoeImage() {
        return ShoeImage;
    }

    public void setShoeImage(int shoeImage) {
        ShoeImage = shoeImage;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public float getRate() {
        return Rate;
    }

    public void setRate(float rate) {
        Rate = rate;
    }

}
