/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aptekab;

/**
 *
 * @author User
 */
public class Medicament {
    
private String name;
    private double price;
    private boolean stock;

    Medicament(String name, double price, boolean stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(boolean stock) {
        stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean setStock() {
        return stock;
    }


}
