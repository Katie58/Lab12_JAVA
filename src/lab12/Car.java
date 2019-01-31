package lab12;

import java.text.DecimalFormat;

public class Car {

	protected String make;
	protected String model;
	protected int year;
	protected double price;
	
	public Car() {
	}
	
	public Car(String make, String model, int year, double price) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.price = price;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getMake() {
		return make;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		DecimalFormat currency = new DecimalFormat("0.00");
		return make + " " + model + " " + year + " $" + currency.format(price);
	}
	
}
