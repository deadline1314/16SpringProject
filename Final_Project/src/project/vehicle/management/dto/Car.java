package project.vehicle.management.dto;

/**
 * 
 * @author Lei Zhou
 *
 */

public class Car
{
    enum Condition
    {
        NEW, USED, CERTIFIED
    }

    private String VIN;
    private String dealerName;
    private int year;
    private String make;
    private Condition condition;
    private String model;
    private String trim;
    private String type;
    private double price;

    public Car(String VIN, String dealerName, int year, String make, Condition condition, String model, String trim,
            String type, double price)
    {
        this.VIN = VIN;
        this.dealerName = dealerName;
        this.year = year;
        this.make = make;
        this.condition = condition;
        this.model = model;
        this.trim = trim;
        this.type = type;
        this.price = price;
    }

    public String getVIN()
    {
        return VIN;
    }

    public void setVIN(String VIN)
    {
        this.VIN = VIN;
    }

    public String getDealerName()
    {
        return dealerName;
    }

    public void setDealerName(String dealerName)
    {
        this.dealerName = dealerName;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public Condition getCondition()
    {
        return condition;
    }

    public void setCondition(Condition condition)
    {
        this.condition = condition;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getTrim()
    {
        return trim;
    }

    public void setTrim(String trim)
    {
        this.trim = trim;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

}
