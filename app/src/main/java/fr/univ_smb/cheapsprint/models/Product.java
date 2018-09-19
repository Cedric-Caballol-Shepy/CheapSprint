package fr.univ_smb.cheapsprint.models;

public class Product {
    private String name;
    private double price;

    public Product(String name){
        this.name = name;
    }
    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public Product findProduct(){
        return null;
    }
}
