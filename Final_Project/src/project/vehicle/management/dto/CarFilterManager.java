package project.vehicle.management.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Jia
 *
 */
//
// TODO make the priceList changeable according to the choice in other filters
// TODO sort the choice in each filter(yearFilter has done)
// TODO add counts(nice to have)

public class CarFilterManager {

	/*
	 * CarFilterManager -> 1. Conditions 2. Years, 3. Makes, 4. Models, 5.
	 * Types, 6. PriceRanges, counts (nice to have)
	 */

	/*
	 * carInfo 0. id 1. webId 2. category(condition) 3. year 4. make 5. model 6.
	 * trim 7. type 8. price
	 */

	private ArrayList<Car> originalCarList;
	private ArrayList<Car> tempCarList;
	private String conditionSelected = "Select";
	private String yearSelected = "Select";
	private String makeSelected = "Select";
	private String modelSelected = "Select";
	private String typeSelected = "Select";
	private String priceSelected = "Select";
	private double minPrice = Double.MIN_VALUE;
	private double maxPrice = Double.MAX_VALUE;
	boolean a, b, c, d, e, f, g;

	public CarFilterManager(String dealerName) {
		CarFileManager cfm = new CarFileManager();
		this.originalCarList = cfm.readCars(
				"/Users/fandonghan/desktop/16SpringProject/Final_Project/src/project/vehicle/data/" + dealerName);
		this.tempCarList = new ArrayList<Car>(originalCarList);
	}

	static List<String> priceListRange = Arrays.asList("0~10000", "10001~20000", "20001~30000", "30001~40000",
			"40001~50000", "50001~60000", "60001~100000");

	public List<String> getConditionChoice() {
		int i = 0;
		List<String> conditionList = new ArrayList<String>();
		conditionList.add("Select");
		Car tempCar;
		while (i < tempCarList.size()) {
			// add conditions to conditionList
			tempCar = tempCarList.get(i);
			if (!conditionList.contains(tempCar.getCondition()))
				conditionList.add(tempCar.getCondition());
			i++;
		}
		return conditionList;
	}

	public void conditionFilter(String n) {
		conditionSelected = n;
		int i = 0;
		Car tempCar;
		while (i < tempCarList.size()) {
			tempCar = tempCarList.get(i);
			// remove all the car which doesn't qualify select condition;
			if (!(tempCar.getCondition().equals(conditionSelected)))
				tempCarList.remove(tempCar);
			else
				i++;
		}

		// add cars that qualify the condition n from originalCarList to
		// tempCarList ;

		for (Car originalCar : originalCarList) {
			a = originalCar.getCondition().equals(conditionSelected);
			if (conditionSelected.equals("Select"))
				a = true;

			b = (String.valueOf(originalCar.getYear())).equals(yearSelected);
			if (yearSelected.equals("Select"))
				b = true;

			c = originalCar.getMake().equals(makeSelected);
			if (makeSelected.equals("Select"))
				c = true;

			d = originalCar.getModel().equals(modelSelected);
			if (modelSelected.equals("Select"))
				d = true;

			e = originalCar.getType().equals(typeSelected);
			if (typeSelected.equals("Select"))
				e = true;

			f = (originalCar.getPrice() >= minPrice) && (originalCar.getPrice() < maxPrice);
			if (priceSelected.equals("Select"))
				f = true;

			g = !tempCarList.contains(originalCar);

			if (a && b && c && d && e && f && g) {
				tempCarList.add(originalCar);
			}
		}

	}

	public List<String> getYearChoice() {
		int i = 0;
		List<String> yearList = new ArrayList<String>();
		yearList.add("Select");
		Car tempCar;
		while (i < tempCarList.size()) {
			// add years to yearsList
			tempCar = tempCarList.get(i);
			if (!yearList.contains(String.valueOf(tempCar.getYear())))
				yearList.add(String.valueOf(tempCar.getYear()));
			i++;
		}
		// sort the yearList in descending order.
		Collections.sort(yearList);
		Collections.reverse(yearList);
		return yearList;
	}

	public void yearFilter(String n) {
		yearSelected = n;
		int i = 0;
		Car tempCar;
		while (i < tempCarList.size()) {
			tempCar = tempCarList.get(i);
			// remove all the car which doesn't qualify select year;
			if (!String.valueOf(tempCar.getYear()).equals(yearSelected))
				tempCarList.remove(tempCar);
			else
				i++;
		}

		// add cars that qualify all the filter from originalCarList to
		// tempCarList ;
		for (Car originalCar : originalCarList) {
			a = originalCar.getCondition().equals(conditionSelected);
			if (conditionSelected.equals("Select"))
				a = true;

			b = (String.valueOf(originalCar.getYear())).equals(yearSelected);
			if (yearSelected.equals("Select"))
				b = true;

			c = originalCar.getMake().equals(makeSelected);
			if (makeSelected.equals("Select"))
				c = true;

			d = originalCar.getModel().equals(modelSelected);
			if (modelSelected.equals("Select"))
				d = true;

			e = originalCar.getType().equals(typeSelected);
			if (typeSelected.equals("Select"))
				e = true;

			f = (originalCar.getPrice() >= minPrice) && (originalCar.getPrice() < maxPrice);
			if (priceSelected.equals("Select"))
				f = true;

			g = !tempCarList.contains(originalCar);

			if (a && b && c && d && e && f && g) {
				tempCarList.add(originalCar);
			}
		}
	}

	public List<String> getMakeChoice() {
		int i = 0;
		List<String> makeList = new ArrayList<String>();
		makeList.add("Select");
		Car tempCar;
		while (i < tempCarList.size()) {
			// add makes to makeList
			tempCar = tempCarList.get(i);
			if (!makeList.contains(tempCar.getMake()))
				makeList.add(tempCar.getMake());
			i++;
		}
		return makeList;
	}

	public void makeFilter(String n) {
		makeSelected = n;
		int i = 0;
		Car tempCar;
		while (i < tempCarList.size()) {
			tempCar = tempCarList.get(i);
			// remove all the car which doesn't qualify select make;
			if (!tempCar.getMake().equals(makeSelected))
				tempCarList.remove(tempCar);
			else
				i++;
		}

		// add cars that qualify all the filter from originalCarList to
		// tempCarList ;
		for (Car originalCar : originalCarList) {
			a = originalCar.getCondition().equals(conditionSelected);
			if (conditionSelected.equals("Select"))
				a = true;

			b = (String.valueOf(originalCar.getYear())).equals(yearSelected);
			if (yearSelected.equals("Select"))
				b = true;

			c = originalCar.getMake().equals(makeSelected);
			if (makeSelected.equals("Select"))
				c = true;

			d = originalCar.getModel().equals(modelSelected);
			if (modelSelected.equals("Select"))
				d = true;

			e = originalCar.getType().equals(typeSelected);
			if (typeSelected.equals("Select"))
				e = true;

			f = (originalCar.getPrice() >= minPrice) && (originalCar.getPrice() < maxPrice);
			if (priceSelected.equals("Select"))
				f = true;

			g = !tempCarList.contains(originalCar);

			if (a && b && c && d && e && f && g) {
				tempCarList.add(originalCar);
			}
		}
	}

	public List<String> getModelChoice() {
		int i = 0;
		List<String> modelList = new ArrayList<String>();
		modelList.add("Select");
		Car tempCar;
		while (i < tempCarList.size()) {
			// add models to modelList
			tempCar = tempCarList.get(i);
			if (!modelList.contains(tempCar.getModel()))
				modelList.add(tempCar.getModel());
			i++;
		}
		return modelList;
	}

	public void modelFilter(String n) {
		modelSelected = n;
		int i = 0;
		Car tempCar;
		while (i < tempCarList.size()) {
			tempCar = tempCarList.get(i);
			// remove all the car which doesn't qualify select model;
			if (!tempCar.getModel().equals(modelSelected))
				tempCarList.remove(tempCar);
			else
				i++;
		}

		// add cars that qualify all the filter from originalCarList to
		// tempCarList ;
		for (Car originalCar : originalCarList) {
			a = originalCar.getCondition().equals(conditionSelected);
			if (conditionSelected.equals("Select"))
				a = true;

			b = (String.valueOf(originalCar.getYear())).equals(yearSelected);
			if (yearSelected.equals("Select"))
				b = true;

			c = originalCar.getMake().equals(makeSelected);
			if (makeSelected.equals("Select"))
				c = true;

			d = originalCar.getModel().equals(modelSelected);
			if (modelSelected.equals("Select"))
				d = true;

			e = originalCar.getType().equals(typeSelected);
			if (typeSelected.equals("Select"))
				e = true;

			f = (originalCar.getPrice() >= minPrice) && (originalCar.getPrice() < maxPrice);
			if (priceSelected.equals("Select"))
				f = true;

			g = !tempCarList.contains(originalCar);

			if (a && b && c && d && e && f && g) {
				tempCarList.add(originalCar);
			}
		}
	}

	public List<String> getTypeChoice() {
		int i = 0;
		List<String> typeList = new ArrayList<String>();
		typeList.add("Select");
		Car tempCar;
		while (i < tempCarList.size()) {
			// add types to typeList
			tempCar = tempCarList.get(i);
			if (!typeList.contains(tempCar.getType()))
				typeList.add(tempCar.getType());
			i++;
		}
		return typeList;
	}

	public void typeFilter(String n) {
		typeSelected = n;
		int i = 0;
		Car tempCar;
		while (i < tempCarList.size()) {
			tempCar = tempCarList.get(i);
			// remove all the car which doesn't qualify select type;
			if (!tempCar.getType().equals(typeSelected))
				tempCarList.remove(tempCar);
			else
				i++;
		}

		// add cars that qualify all the filter from originalCarList to
		// tempCarList ;
		for (Car originalCar : originalCarList) {
			a = originalCar.getCondition().equals(conditionSelected);
			if (conditionSelected.equals("Select"))
				a = true;

			b = (String.valueOf(originalCar.getYear())).equals(yearSelected);
			if (yearSelected.equals("Select"))
				b = true;

			c = originalCar.getMake().equals(makeSelected);
			if (makeSelected.equals("Select"))
				c = true;

			d = originalCar.getModel().equals(modelSelected);
			if (modelSelected.equals("Select"))
				d = true;

			e = originalCar.getType().equals(typeSelected);
			if (typeSelected.equals("Select"))
				e = true;

			f = (originalCar.getPrice() >= minPrice) && (originalCar.getPrice() < maxPrice);
			if (priceSelected.equals("Selected"))
				f = true;

			g = !tempCarList.contains(originalCar);

			if (a && b && c && d && e && f && g) {
				tempCarList.add(originalCar);
			}
		}
	}

	public List<String> getPriceChoice() {
		List<String> priceList = new ArrayList<String>();
		priceList.add("Select");
		// TODO make the priceList changeable according to the other filter
		priceList.addAll(priceListRange);
		return priceList;
	}

	public void priceFilter(String n) {
		priceSelected = n;
		if (n.equals("Select")) {
			return;
		}
		String[] priceRange = priceSelected.split("~");
		minPrice = Double.valueOf(priceRange[0]);
		maxPrice = Double.valueOf(priceRange[1]);

		int i = 0;
		Car tempCar;
		while (i < tempCarList.size()) {
			tempCar = tempCarList.get(i);
			// remove all the car which doesn't qualify select price range;
			if (tempCar.getPrice() < minPrice || tempCar.getPrice() >= maxPrice)
				tempCarList.remove(tempCar);
			else
				i++;
		}

		// add cars that qualify all the filter from originalCarList to
		// tempCarList ;
		for (Car originalCar : originalCarList) {
			a = originalCar.getCondition().equals(conditionSelected);
			if (conditionSelected.equals("Select"))
				a = true;

			b = (String.valueOf(originalCar.getYear())).equals(yearSelected);
			if (yearSelected.equals("Select"))
				b = true;

			c = originalCar.getMake().equals(makeSelected);
			if (makeSelected.equals("Select"))
				c = true;

			d = originalCar.getModel().equals(modelSelected);
			if (modelSelected.equals("Select"))
				d = true;

			e = originalCar.getType().equals(typeSelected);
			if (typeSelected.equals("Select"))
				e = true;

			f = (originalCar.getPrice() >= minPrice) && (originalCar.getPrice() < maxPrice);
			if (priceSelected.equals("Select"))
				f = true;

			g = !tempCarList.contains(originalCar);

			if (a && b && c && d && e && f && g) {
				tempCarList.add(originalCar);
			}
		}
	}

	public List<Car> getFilteredCarList() {
		return tempCarList;
	}

}