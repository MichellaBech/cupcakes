package app.entities;

public class Cupcake {

   private int user_number;
   private int topping_number;
    private String topping_name;
    private double orderline_price;
    private int quantity;
    private int bottom_number;
    private String bottom_name;

    private double topping_price;
    private double bottom_price;

    private double totalPrice;

    public Cupcake(int user_number, int topping_number, String topping_name, int bottom_number, String bottom_name, int quantity, double orderline_price) {
        this.topping_number = topping_number;
        this.topping_name = topping_name;
        this.bottom_number = bottom_number;
        this.bottom_name = bottom_name;
        this.quantity = quantity;
        this.orderline_price = orderline_price;
    }

    public double getTotalPrice() {
        return ((topping_price + bottom_price) * quantity);
    }

    public String getTopping_name() {
        return topping_name;
    }

    public String getBottom_name() {
        return bottom_name;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setTopping_name(String topping_name) {
        this.topping_name = topping_name;
    }

    public void setBottom_name(String bottom_name) {
        this.bottom_name = bottom_name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public Cupcake(String bottom_name, String topping_name, int quantity, double totalPrice){
        this.bottom_name = bottom_name;
        this.topping_name = topping_name;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public double getOrderline_price()
    {
        Topping topping = new Topping();
        Bottom bottom = new Bottom();
        Double orderPrice = (topping.getTopping_price() + bottom.getBottom_price()) * quantity;
        return orderPrice;
    }

    public String toString()
    {
        return "Topping: " + topping_name + " Bottom: " + bottom_name + " Quantity: " + quantity;
    }
}
