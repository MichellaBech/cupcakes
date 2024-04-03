package app.entities;

public class Bottom {

    private int bottom_id;

    private String bottom_name;

    private int bottom_number;

    private double bottom_price;

    public Bottom(String bottom_name, double bottom_price) {
        this.bottom_name = bottom_name;
        this.bottom_number = bottom_number;
        this.bottom_price = bottom_price;
    }

    public String getBottom_name() {
        return bottom_name;
    }

    public int getBottom_number() {
        return bottom_number;
    }

    public double getBottom_price() {
        return bottom_price;
    }

    public String toString()
    {
        return bottom_name + " " + bottom_price;
    }
}
