package com.example.roong;

public class CartItem {
    private String productName;
    private String price;
    private int quantity;
    private int imageResource;
    private double unitPrice;

    public CartItem(String productName, double unitPrice, int quantity, int imageResource) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.imageResource = imageResource;
        this.price = "Rs : " + String.format("%.2f", unitPrice * quantity);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void updatePrice() {
        this.price = "Rs : " + String.format("%.2f", unitPrice * quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updatePrice();
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }
}
