package lab12;
import java.text.NumberFormat;

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
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		if (!CarApp.cars.containsValue(this)) {
			return make + " " + model + " " + year + " " + currency.format(price);
		} else {
			int makeL = CarApp.padding("make") + 4 - make.length();
			String padMake = CarApp.printMulti(makeL, ' ');
			int modelL = CarApp.padding("model") + 4 - model.length();
			String padModel = CarApp.printMulti(modelL, ' ');
			int priceL = CarApp.padding("price") + 4 - Double.toString(price).length();
			String padPrice = CarApp.printMulti(priceL, ' ');

			return this.make + padMake + this.model + padModel + this.year + padPrice + " " + currency.format(this.price);
		}
	}	
}
