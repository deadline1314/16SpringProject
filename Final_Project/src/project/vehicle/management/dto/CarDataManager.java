package project.vehicle.management.dto;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * 
 *
 */

public class CarDataManager
{
    private ArrayList<Car> myCars;

    public CarDataManager(ArrayList<Car> myCars)
    {
        this.myCars = myCars;
    }

    public void addCar(Car newCar)
    {
        myCars.add(newCar);
        //return myCars;  
    }

    public void deleteCarByVIN(String VIN)
    {
        Iterator<Car> i = myCars.iterator();
        Car c;
        while (i.hasNext())
        {
            c = i.next();
            if (c.getVIN().equals(VIN))
            {
                i.remove();
            }
        }
       // return myCars;
    }

    public Car getCarByVIN(String VIN)
    {
        for (Car c : myCars)
        {
            if (c.getVIN().equals(VIN))
            {
                return c;
            }
        }
        return null;
    }

    public void modifyCar(Car aCar)
    {
        int index = myCars.indexOf(getCarByVIN(aCar.getVIN()));
        myCars.remove(index);
        myCars.add(index, aCar);
        //return myCars;
    }

}
