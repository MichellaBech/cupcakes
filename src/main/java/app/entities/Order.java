package app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {

    private int order_id;
    private int order_number;
    private int user_number;
    private double order_price;
    private LocalDate order_date;
    private LocalDateTime order_pickuptime;

    public Order(int order_number, int user_number, double order_price, LocalDate order_date, LocalDateTime order_pickuptime) {
        this.order_number = order_number;
        this.user_number = user_number;
        this.order_price = order_price;
        this.order_date = order_date;
        this.order_pickuptime = order_pickuptime;
    }

    public String toString() {
        return "Order number: " + order_number +
                " User number: " + user_number +
                " Price: " + order_price +
                " Order date: " + order_date +
                " Pickup Time: " + order_pickuptime;
    }
}
