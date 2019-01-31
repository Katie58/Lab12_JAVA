package lab12;
import java.text.NumberFormat;

public class UsedCar extends Car {

	protected double mileage;
	
	public UsedCar() {
	}
	
	public UsedCar(String make, String model, int year, double price, double mileage) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.price = price;
		this.mileage = mileage;
	}
	
	@Override
	public String toString() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		NumberFormat comma = NumberFormat.getInstance();
		if (!CarApp.cars.containsValue(this)) {
			return make + " " + model + " " + year + " " + currency.format(price) + " " + comma.format(mileage);
		} else {
			int makeL = CarApp.padding("make") + 4 - make.length();
			String padMake = CarApp.printMulti(makeL, ' ');
			int modelL = CarApp.padding("model") + 4 - model.length();
			String padModel = CarApp.printMulti(modelL, ' ');
			int priceL = CarApp.padding("price") + 4 - Double.toString(price).length();
			String padPrice = CarApp.printMulti(priceL, ' ');
			int mileL = CarApp.padding("mileage") + 4 - Double.toString(mileage).length();
			String padMile = CarApp.printMulti(mileL, ' ');

			return this.make + padMake + this.model + padModel + this.year + padPrice + " " + currency.format(this.price) + padMile + comma.format(this.mileage);
		}
	}	
	
}
