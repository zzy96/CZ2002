package control;

import java.util.ArrayList;
import java.util.Scanner;

public class TicketPriceController {
	private static ArrayList<String> holiday = new ArrayList<String>();
	private static double[] price = new double[3];
	private static double discount;
	private static double cinemaMultiplier, holidayMultiplier;

	static {
		holiday.add("01/01");
		holiday.add("25/12");
		holiday.add("09/08");
		price[0] = 5;
		price[1] = 7;
		price[2] = 10;
		discount = 0.5;
		cinemaMultiplier = 1.5;
		holidayMultiplier = 1.2;
	}

	public static double getDiscount() {
		return discount;
	}

	public static void editPrice() {
		System.out.println("=====Price Information=====");
		System.out.println("Normal: " + price[0] + "SGD");
		System.out.println("Animation: " + price[1] + "SGD");
		System.out.println("3D: " + price[2] + "SGD");
		System.out.println("Discount Rate: " + discount);
		System.out.println("Multiplier for Small Cinema Class: " + cinemaMultiplier);
		System.out.println("Multiplier for Holiday: " + holidayMultiplier);
		listHoliday();
		System.out.println("===========================");
		System.out.println("Want to make change?(1 for Y)");

		Scanner input = new Scanner(System.in);
		if (input.nextInt() == 1) {
			System.out.println("input price for Normal:");
			price[0] = input.nextDouble();
			System.out.println("input price for Animation:");
			price[1] = input.nextDouble();
			System.out.println("input price for 3D:");
			price[2] = input.nextDouble();
			System.out.println("input discount rate:");
			discount = input.nextDouble();
			System.out.println("input cinema multiplier:");
			cinemaMultiplier = input.nextDouble();
			System.out.println("input holiday multiplier:");
			holidayMultiplier = input.nextDouble();
		}
		System.out.println("Want to add holiday?(1 for Y)");
		if (input.nextInt() == 1) {
			input.nextLine();
			System.out.println("input date(dd/mm/yyyy):");
			addHoliday(input.nextLine());
		}
	}

	public static void listHoliday() {
		System.out.println("Holiday List:");
		for (int i = 0; i < holiday.size(); i++) {
			System.out.println(holiday.get(i));
		}
	}

	public static void addHoliday(String date) {
		holiday.add(date);
	}

	public static double getPrice(int movieType, String cinemaClass, String date) {
		boolean isHoliday = false, isSmall = false;
		for (int i = 0; i < holiday.size(); i++) {
			if (date.contains(holiday.get(i))) {
				isHoliday = true;
				break;
			}
		}
		if (cinemaClass.equals("small")) {
			isSmall = true;
		}

		switch (movieType) {
		case 1:
			if (isHoliday) {
				if (isSmall) {
					return cinemaMultiplier * holidayMultiplier * price[0];
				} else {
					return holidayMultiplier * price[0];
				}
			} else {
				if (isSmall) {
					return holidayMultiplier * price[0];
				} else {
					return price[0];
				}
			}
		case 2:
			if (isHoliday) {
				if (isSmall) {
					return cinemaMultiplier * holidayMultiplier * price[1];
				} else {
					return holidayMultiplier * price[1];
				}
			} else {
				if (isSmall) {
					return holidayMultiplier * price[1];
				} else {
					return price[1];
				}
			}
		case 3:
			if (isHoliday) {
				if (isSmall) {
					return cinemaMultiplier * holidayMultiplier * price[2];
				} else {
					return holidayMultiplier * price[2];
				}
			} else {
				if (isSmall) {
					return holidayMultiplier * price[2];
				} else {
					return price[2];
				}
			}
		default:
			return -1;
		}
	}
}
