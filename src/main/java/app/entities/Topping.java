package app.entities;

public class Topping {

private int topping_id;

private String topping_name;

private int topping_number;

private double topping_price;

    public Topping(String topping_name, double topping_price) {
        this.topping_name = topping_name;
        this.topping_price = topping_price;
    }

    public double getTopping_price() {
        return topping_price;
    }

    public String getTopping_name() {
        return topping_name;
    }

    public int getTopping_number() {
        return topping_number;
    }

    public String toString()
    {
        return topping_name + " " + topping_price;
    }
}
