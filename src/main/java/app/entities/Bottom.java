package app.entities;

public class Bottom {

    private String bottom_name;

    private int bottom_number;

    private double bottom_price;


    public Bottom() {}

    public String getBottom_name() {
        return bottom_name;
    }


    public double getBottom_price() {
        return bottom_price;
    }

    public String toString()
    {
        return bottom_name + " " + bottom_price;
    }
}
