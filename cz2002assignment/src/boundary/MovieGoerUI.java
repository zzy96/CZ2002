package boundary;

import java.util.Scanner;

import control.ChooseMovie;
import control.MovieGoerController;
import entity.MovieGoer;

public class MovieGoerUI {
	private static Scanner input = new Scanner(System.in);

	public static void movieGoerChooseLogin() throws Exception {
		System.out.println("1. Existing movie goer");
		System.out.println("2. New movie goer");
		System.out.println("3. Quit");
		switch (input.nextInt()) {
		case 1:
			input.nextLine();
			MovieGoerController.movieGoerLogin();
			break;
		case 2:
			input.nextLine();
			MovieGoerController.movieGoerRegistration();
			break;
		default:
			break;
		}
	}

	public static void movieGoerMain(MovieGoer goer) throws Exception {

		boolean loop = true;
		while (loop) {
			System.out.println("Movie-goer Option:");
			System.out.println("1. List all movies");
			System.out.println("2. View movie details");
			System.out.println("3. Buy a ticket");
			System.out.println("4. View my booking history");
			System.out.println("5. Add review on a movie");
			System.out.println("6. View my reviewing history");
			System.out.println("7. List the Top 5 ranking");
			System.out.println("8. Exit");
			int option = input.nextInt();
			if (option == 8)
				break;
			switch (option) {
			case 1:
				ChooseMovie.listMovie();
				break;
			case 2:
				ChooseMovie.chooseMovie().printInfo();
				break;
			case 3:
				MovieGoerController.buyTicket();
				break;
			case 4:

				break;
			case 5:
				goer.makeReview();
				MovieGoerController.updateMovieGoer(goer);
				break;
			case 6:
				goer.showReview();
				break;
			case 7:
				MovieGoerController.listTopMovie();
				break;
			case 8:
				loop = false;
			default:
				break;
			}
		}

	}
}
