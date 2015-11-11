package boundary;

import java.util.Scanner;

import control.AdminController;
import control.ChooseCinema;
import control.ChooseMovie;
import control.TicketPriceController;

public class AdminUI {
	private static Scanner input = new Scanner(System.in);

	public static boolean checkLogin() throws Exception {
		System.out.println("input admin password:");

		if (input.nextLine().equals("admin")) {
			adminMain();
			return true;
		}
		{
			System.out.println("wrong password!");
			return false;
		}
	}

	private static void adminMain() throws Exception {

		boolean loop = true;
		while (loop) {
			System.out.println("Administrator Option:");
			System.out.println("1. List movies");
			System.out.println("2. Create new movie to the list");
			System.out.println("3. Update movie status");
			System.out.println("4. Remove movie in the list");
			System.out.println("5. List showing time");
			System.out.println("6. Create new showing time for a cinema");
			System.out.println("7. Remove showing time for a cinema");
			System.out.println("8. Configure other settings");
			System.out.println("9. Quit");

			switch (input.nextInt()) {
			case 1:
				ChooseMovie.listMovie();
				break;
			case 2:
				AdminController.createMovie();
				break;
			case 3:
				AdminController.updateMovieStatus();
				break;
			case 4:
				AdminController.removeMovie();
				break;
			case 5:
				ChooseCinema.chooseCinema().listShowingTime();
				break;
			case 6:
				AdminController.createShowingTime();
				break;
			case 7:
				AdminController.removeShowingTime();
				break;
			case 8:
				TicketPriceController.editPrice();
				break;
			case 9:
				loop = false;
			default:
				break;
			}
		}

	}
}
