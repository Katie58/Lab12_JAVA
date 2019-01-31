package lab12;
import java.time.Year;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;

public class CarApp {
	static Scanner scnr = new Scanner(System.in);
	static TreeMap<Integer, Car> cars = new TreeMap<>();
	static ArrayList<Integer> usedKeys = new ArrayList<>();
		
	public static void main(String[] args) {
		boolean retry = true;
		
		while(retry) {
			greeting();
			menu();
			printInventory();
			retry = retry("clear list and start again");
		}
		exit();		
	}	
	
	public static void greeting() {
		System.out.println("Welcome to the Grand Circus Motors admin console!\n");
	}
	
	public static void userInputCar(boolean used) {
		boolean retry = true;
		while(retry) {
			String make;
			String model;
			int year;
			double price;
			double mileage;
			
			System.out.println("Please enter car " + (cars.size() + 1));
			System.out.print("Make: ");
			make = validateString();
			System.out.print("Model: ");
			model = validateString();
			System.out.print("Year: ");
			year = validateYear(1885, Year.now().getValue() + 1);
			System.out.print("Price: ");
			price = validatePrice();
			if (used) {
				System.out.print("Mileage: ");
				mileage = validatePrice();	
				UsedCar car = new UsedCar(make, model, year, price, mileage);
				System.out.println("\nFor car " + (cars.size() + 1) + ", you entered:");
				System.out.println(car);
				if (retry("save car")) {
					cars.put(cars.size() + 1, car);
					usedKeys.add(cars.size());
				}
				retry = retry("enter another used car");
			} else {
				Car car = new Car(make, model, year, price);
				System.out.println("\nFor car " + (cars.size() + 1) + ", you entered:");
				System.out.println(car);
				if (retry("save car")) {
					cars.put(cars.size() + 1, car);
				}		
				retry = retry("enter another new car");
			}		
		}
	}
	
	public static void menu() {
		boolean retry = true;
		while(retry) {
			System.out.println("1. Enter a new car to sell.\n2. Enter a used car to sell.\n3. Edit inventory.\n4. Find a car to buy.");
			System.out.print("\nPlease select an option: (1-4) ");
			int select = validateMenu(4);
			switch(select) {
			case 1: userInputCar(false);
			break;
			case 2: userInputCar(true);
			break;
			case 3: editInventory(); refreshInventory();
			break;
			case 4: inventory();
			break;
			}	
			retry = retry("return to the main menu");
		}
	}
	
	public static void inventory() {
		System.out.println("Sorry, too lazy to make up a bunch of cars atm :/");
	}
	
	public static int validateMenu(int menuItems) {
		int input = 0;
		boolean valid = false;
		while(!valid) {
			String in = scnr.nextLine().trim();
			if (in.matches("[0-9]")) {
				input = Integer.parseInt(in);
				if (input >= 1 && input <= menuItems) {
					return input;
				} else {
					System.out.print("Sorry, " + input + " is not a menu option, try again... ");
					continue;
				}
			} else if (in.isEmpty()) {
				System.out.print("Perhaps check your numlock and try again... ");
				continue;					
			} else {
				System.out.print("Looking for numbers, try again... ");
				continue;
			}
		}
		return input;
	}
	
	public static String validateString() {
		String input = "";
		boolean valid = false;
		while(!valid) {
			input = scnr.nextLine().trim();
			if (input.length() == 0) {
				System.out.print("That's not very descriptive, try again... ");
				continue;
			} else {
				String output = "";
				String[] inputs = input.split(" ");
				for (String in : inputs) {
					if (in.length() <= 3) {
						output += in.toUpperCase() + " ";
					} else {
						output += in.substring(0, 1).toUpperCase() + in.substring(1).toLowerCase() + " ";
					}
				}	
				return output.trim();
			}
		}
		return input;
	}

	public static int validateYear(int min, int max) {
		int input = 0;
		boolean valid = false;
		while(!valid) {
			String in = scnr.nextLine();
			if (!in.isEmpty() || !in.matches("[0-9]*")) {
				input = (int) Integer.parseInt(in);
				if (Integer.toString(input).length() == 4) {
					if (input >= min && input <= max) {
						return input;
					} else {
						if (input < min) {
							System.out.print("The Flinstone's car is not a motorized vehicle, try again... ");
							continue;
						} else {
							System.out.print("Take your car back to the future or try again... ");
							continue;
						}
					}
				} else {
					System.out.print("Looking for 4 numbers to be precise, try again... ");
					continue;
				}
			} else {
				System.out.print("Looking for numbers here, try again... ");
				continue;
			}
		}
		return input;
	}
	
	public static double validatePrice() {//editing
		double price = 0.00;
		boolean valid = false;
		while(!valid) {
			String in = scnr.nextLine().trim();
			if (!in.isEmpty() || !in.matches("[$0-9,.]*")) {
				if (in.charAt(0) == '$') {
					in = in.substring(1);
				}	
				price = (double) Double.parseDouble(in);
				if (price > 0) {
					int priceTemp = (int) (price * 100);
					price = ((double) priceTemp) / 100;
					return price;
				} else {
					System.out.print("Obviously your car is in need of tlc, but surely it has some value, try again... ");
					continue;
				}
			} else {
				System.out.print("Not sure what kind of currency you are trying to enter, try again... ");
				continue;
			}
		}
		return price;
	}
	
 	public static boolean retry(String question) {
		System.out.print("\nWould you like to " + question + "? (y/n) ");
		return validateYesNo(scnr.nextLine().charAt(0));
	}
	
	public static boolean validateYesNo(char input) {//validate yes/no user input
		while (input != 'y' && input != 'Y' && input != 'n' && input != 'N') {
			System.out.print("This is a simple yes or no question, try again...");
			input = scnr.nextLine().charAt(0);
		}
		return (input == 'y' || input == 'Y');
	}
	
	public static void editInventory() {
		int removed = 0;
		boolean retry = true;
		while(retry) {
			if (cars.size() > 0) {
				int select = 0;
				printInventory();
				System.out.print("Select a car to remove: (1-" + cars.size() + ") ");
				select = validateMenu(cars.size());
				System.out.println("You selected: ");
				System.out.println(cars.get(select));
				System.out.print("Are you sure you want to remove the " + cars.get(select).make + " " + cars.get(select).model + "? ");
				if (validateYesNo(scnr.nextLine().charAt(0))) {
					cars.replace(select, new Car("REMOVED", " ", 0000, 0.00));
					usedKeys.remove(select);
					removed++;
				}	
				if (removed < cars.size()) {
					retry = retry("remove another car");
				} else {
					System.out.println("Your inventory is now empty...");
					retry = false;
				}					
			} else {
				System.out.println("Your inventory is empty...");
				retry = false;
			}
		
		}
	}
	
	public static void refreshInventory() {
		for (int i = cars.size(); i >= 1; i--) {
			System.out.println("car index = " + i);///////////////////////////////
			if (cars.get(i).make.equalsIgnoreCase("REMOVED")) {
				System.out.println("car index removed = " + i);///////////////////////////
				cars.remove(i);
				for (int j = i + 1; j <= cars.size(); j++) {
					System.out.println("further car index removed = " + i);///////////////
					Car carHolder = cars.get(i);
					cars.remove(i);
					System.out.println("car index added = " + (i - 1));////////////////
					cars.put(i - 1, carHolder);
					if (usedKeys.contains(i)) {
						System.out.println("used key removed = " + i);/////////////
						usedKeys.remove(i);
						System.out.println("used key added = " + (i - 1));////////////
						usedKeys.add(i - 1);
					}					
				}
			}
		}
	}
	
	public static void printInventory() {
		System.out.println("\nYour Inventory:");
		
		int makeL = CarApp.padding("make");
		String padMake = CarApp.printMulti(makeL, ' ');
		int modelL = CarApp.padding("model") - 1;
		String padModel = CarApp.printMulti(modelL, ' ');
		int priceL = CarApp.padding("price") + 3;
		String padPrice = CarApp.printMulti(priceL, ' ');
		int mileL = CarApp.padding("mileage") - 5;
		String padMile = CarApp.printMulti(mileL, ' ');
		System.out.println(printMulti(3, ' ') + "MAKE" + padMake + "MODEL" + padModel + "YEAR" + padPrice + "PRICE" + padMile + "MILEAGE");
		System.out.println(printMulti(makeL + modelL + priceL + mileL + 28, '-'));
		
		for (int i = 1; i <= cars.size(); i++) {
			System.out.print(i + ". ");
			if (cars.get(i).make.equalsIgnoreCase("REMOVED")) {
				System.out.println("REMOVED");
			} else {
				if (usedKeys.contains(i)) {
					System.out.println((UsedCar) cars.get(i));
				} else {
					System.out.println(cars.get(i));
				}				
			}		
		}	
	}
	
	public static int padding(String variable) {
		int length = 0;
		switch(variable) {
		case "make": for (int i = 1; i <= cars.size(); i++) {
			String make = cars.get(i).make;
			if (make.length() > length) {
				length = make.length();
			}
		}
		break;
		case "model": for (int i = 1; i <= cars.size(); i++) {
			String make = cars.get(i).model;
			if (make.length() > length) {
				length = make.length();
			}
		}
		break;
		case "price": for (int i = 1; i <= cars.size(); i++) {
			String make = Double.toString(cars.get(i).price);
			if (make.length() > length) {
				length = make.length();
			}
		}
		break;
		case "mileage": for (int i = 1; i <= cars.size(); i++) {
			if (usedKeys.contains(i)) {
				UsedCar car = (UsedCar)cars.get(i);
				String make = Double.toString(car.mileage);
				if (make.length() > length) {
					length = make.length();
				}				
			}
		}
		break;
		}
		return length;
	}
	
	public static String printMulti(int multi, char character) {
		String multiples = "";
		for (int i = 1; i <= multi; i++) {
			multiples += character;
		}
		return multiples;
	}
	
	public static void exit() {
		System.out.println("Drive safe!");
	}
	
}
