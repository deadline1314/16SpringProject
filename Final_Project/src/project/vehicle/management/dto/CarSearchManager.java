package project.vehicle.management.dto;

import java.util.ArrayList;

/**
 * 
 * @author Lei Zhou
 *
 */

public class CarSearchManager
{
    private ArrayList<Car> myCars;

    public CarSearchManager(ArrayList<Car> myCars)
    {
        this.myCars = myCars;
    }

    public ArrayList<Car> listCarsByDealer(String dealerName)
    {
        ArrayList<Car> dealerCars = new ArrayList<Car>();
        for (Car c : myCars)
        {
            if (c.getDealerName().equals(dealerName))
            {
                dealerCars.add(c);
            }
        }
        return dealerCars;
    }

    public ArrayList<Car> sort(String dealerName, String type)
    {
        ArrayList<Car> dealerCars = listCarsByDealer(dealerName);
        if (dealerCars.size() == 0)
        {
            return dealerCars;
        }

        sort(dealerCars, 0, dealerCars.size() - 1, type);
        return dealerCars;
    }

    private void sort(ArrayList<Car> dealerCars, int lo, int hi, String type)
    {
        if (hi <= lo)
        {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(dealerCars, lo, mid, type);
        sort(dealerCars, mid + 1, hi, type);
        merge(dealerCars, lo, mid, hi, type);
    }

    private void merge(ArrayList<Car> dealerCars, int lo, int mid, int hi, String type)
    {
        ArrayList<Car> tempDealerCars = (ArrayList<Car>) dealerCars.clone();
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++)
        {
            if (i > mid)
            {
                dealerCars.remove(k);
                dealerCars.add(k, tempDealerCars.get(j++));
            }
            else if (j > hi)
            {
                dealerCars.remove(k);
                dealerCars.add(k, tempDealerCars.get(i++));
            }
            else if (less(tempDealerCars.get(j), tempDealerCars.get(i), type))
            {
                dealerCars.remove(k);
                dealerCars.add(k, tempDealerCars.get(j++));
            }
            else
            {
                dealerCars.remove(k);
                dealerCars.add(k, tempDealerCars.get(i++));
            }
        }
    }

    private boolean less(Car car1, Car car2, String type)
    {
        if (type.equals("VIN"))
        {
            if (car1.getVIN().compareTo(car2.getVIN()) < 0)
            {
                return true;
            }
        }
        else if (type.equals("dealerName"))
        {
            if (car1.getDealerName().compareTo(car2.getDealerName()) < 0)
            {
                return true;
            }
        }
        else if (type.equals("year"))
        {
            if (car1.getYear() < car2.getYear())
            {
                return true;
            }
        }
        else if (type.equals("make"))
        {
            if (car1.getMake().compareTo(car2.getMake()) < 0)
            {
                return true;
            }
        }
        else if (type.equals("model"))
        {
            if (car1.getModel().compareTo(car2.getModel()) < 0)
            {
                return true;
            }
        }
        else if (type.equals("trim"))
        {
            if (car1.getTrim().compareTo(car2.getTrim()) < 0)
            {
                return true;
            }
        }
        else if (type.equals("type"))
        {
            if (car1.getType().compareTo(car2.getType()) < 0)
            {
                return true;
            }
        }
        else if (type.equals("price"))
        {
            if (car1.getPrice() < car2.getPrice())
            {
                return true;
            }
        }
        else if (type.equals(""))
        {
            if (car1.getCondition().compareTo(car2.getCondition()) < 0)
            {
                return true;
            }
        }
        return false;
    }
}
