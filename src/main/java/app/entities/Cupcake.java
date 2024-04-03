package app.entities;

public class Cupcake {

   private int user_number;
   private int topping_number;
    private String topping_name;
    private int bottom_number;
    private String bottom_name;

    private int quantity;
    private double orderline_price;


    public Cupcake(int user_number, int topping_number, String topping_name, int bottom_number, String bottom_name, int quantity, double orderline_price) {
        this.topping_number = topping_number;
        this.topping_name = topping_name;
        this.bottom_number = bottom_number;
        this.bottom_name = bottom_name;
        this.quantity = quantity;
        this.orderline_price = orderline_price;
    }

    public String toString()
    {
        return "Topping: " + topping_name + " Bottom: " + bottom_name + " Quantity: " + quantity;
    }
}
