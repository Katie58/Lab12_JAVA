package lab12;
import java.time.Year;
import java.util.Scanner;
import java.util.TreeMap;

public class CarApp {
	static Scanner scnr = new Scanner(System.in);
	static TreeMap<Integer, Car> cars = new TreeMap<>();
	
	public static void main(String[] args) {
		boolean retry = true;
		
		while(retry) {
			greeting();
			userInputCar();
			//menu();
			printInventory();
			retry = retry("clear list and start again");
		}
		exit();		
	}	
	
	public static void greeting() {
		System.out.println("Welcome to the Grand Circus Motors admin console!\n");
	}
	
	public static void userInputCar() {
		boolean retry = true;
		while(retry) {
			int count = cars.size() + 1;
			String make;
			String model;
			int year;
			double price;
			
			System.out.println("Please enter car " + count);
			System.out.print("Make: ");
			make = validateString();
			System.out.print("Model: ");
			model = validateString();
			System.out.print("Year: ");
			year = validateYear(1885, Year.now().getValue() + 1);
			System.out.print("Price: ");
			price = validatePrice();
			
			Car car = new Car(make, model, year, price);
			System.out.println("For car " + count + ", you entered:");
			System.out.println(car);
			if (retry("save car")) {
				cars.put(count, car);
			}
			retry = retry("enter another car");
		}
	}
	
	public static void menu() {
		
	}
	
	public static String validateString() {
		String input = "";
		boolean valid = false;
		while(!valid) {
			input = scnr.nextLine().trim();
			if (input.length() == 0) {
				System.out.println("That's not very descriptive, try again...");
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
			if (scnr.hasNextInt()) {
				input = scnr.nextInt();
				scnr.nextLine();
				if (Integer.toString(input).length() == 4) {
					if (input >= min && input <= max) {
						return input;
					} else {
						if (input < min) {
							System.out.println("The Flinstone's car is not a motorized vehicle, try again...");
							continue;
						} else {
							System.out.println("Take your car back to the future or try again...");
							continue;
						}
					}
				} else {
					System.out.println("Looking for 4 numbers to be precise, try again...");
					continue;
				}
			} else {
				System.out.println("Looking for numbers here, try again...");
				continue;
			}
		}
		return input;
	}
	
	public static double validatePrice() {
		double price = 0.00;
		boolean valid = false;
		while(!valid) {
			if (scnr.hasNextDouble()) {
				price = scnr.nextDouble();
				scnr.nextLine();
				if (price > 0) {
					return price;
				} else {
					System.out.println("Obviously your car is in need of tlc, but surely it has some value, try again...");
					continue;
				}
			} else {
				System.out.println("Not sure what kind of currency you are trying to enter, try again...");
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
	
	public static void printInventory() {
		System.out.println("\nCurrent Inventory:");
		for (int i = 1; i <= cars.size(); i++) {
			System.out.println(cars.get(i));
		}	
	}
	
	public static void exit() {
		System.out.println("Drive safe!");
	}
	
}
