package fr.univ_smb.cheapsprint.models;

public class Product {
    private String name;
    private double price;

    private Product(){}
    public Product(String name){
        this.name = name;
    }
    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }
}
