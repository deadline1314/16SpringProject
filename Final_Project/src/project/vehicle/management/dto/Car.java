package project.vehicle.management.dto;

/**
 * 
 * @author Lei Zhou
 *
 */

public class Car {
	private String VIN;
	private String dealerName;
	private Integer year;
	private String make;
	private Condition condition;
	private String model;
	private String trim;
	private String type;
	private float price;
	
	

}

enum Condition {
	NEW, USED, CERTIFIED
}


