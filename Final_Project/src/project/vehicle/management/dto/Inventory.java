package project.vehicle.management.dto;

import java.util.ArrayList;


/**
 * 
 * @author Lei Zhou
 *
 */


public class Inventory
{

    private ArrayList<Car> myCars;

    private CarDataManager cdm;
    private CarSearchManager csm;

    public Inventory()
    {
        myCars = new ArrayList<Car>();
        cdm = new CarDataManager(myCars);
        csm = new CarSearchManager(myCars);
    }

    public CarDataManager getCarDataManager()
    {
        return cdm;
    }

    public CarSearchManager getCarSearchManager()
    {
        return csm;
    }
}

