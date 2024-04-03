package app.entities;

public class Orderline {

    private String topping_name;
    private String bottom_name;
    private double orderline_price;

    public Orderline(String topping_name, String bottom_name, double orderline_price) {
        this.topping_name = topping_name;
        this.bottom_name = bottom_name;
        this.orderline_price = orderline_price;
    }

    public String toString()
    {
        return "Topping: " + topping_name + " Bottom: " + bottom_name + " Price: " + orderline_price;
    }
}
