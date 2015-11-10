package control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import boundary.ChooseCinema;
import boundary.ChooseMovie;
import entity.Cinema;
import entity.Movie;

public class AdminController {

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
				createMovie();
				break;
			case 3:
				updateMovieStatus();
				break;
			case 4:
				removeMovie();
				break;
			case 5:
				ChooseCinema.chooseCinema().listShowingTime();
				break;
			case 6:
				createShowingTime();
				break;
			case 7:
				removeShowingTime();
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

	private static void removeMovie() throws Exception {
		int sel, i;
		FileInputStream fis2 = new FileInputStream("database/Movie");
		ObjectInputStream ois2 = new ObjectInputStream(fis2);

		int num = ois2.readInt();
		Movie[] mList = new Movie[num];

		System.out.println("delete from below:");
		for (i = 0; i < num; i++) {
			mList[i] = (Movie) ois2.readObject();
			System.out.println("Movie " + (i + 1) + ":");
			mList[i].printInfo();
		}
		ois2.close();
		sel = input.nextInt();
		input.nextLine();

		// update number of movie
		num--;

		FileOutputStream fos2 = new FileOutputStream("database/Movie");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
		oos2.writeInt(num);
		for (i = 0; i < num; i++) {
			if (!(sel == i + 1)) {
				oos2.writeObject(mList[i]);
			}
		}

		oos2.close();

	}

	private static void updateMovieStatus() throws Exception {

		Movie movie = ChooseMovie.chooseMovie();
		movie.updateStatus();
		ChooseMovie.updateMovie(movie);

	}

	private static void createMovie() throws Exception {
		input.nextLine();
		FileInputStream fis = new FileInputStream("database/Movie");
		ObjectInputStream ois = new ObjectInputStream(fis);
		int num = ois.readInt();
		num++;
		ArrayList<Movie> mList = new ArrayList<Movie>();

		// read all Movie objects file
		for (int i = 0; i < num - 1; i++) {
			mList.add((Movie) ois.readObject());
		}
		ois.close();

		// append Movie
		mList.add(new Movie());

		// rewrite database
		FileOutputStream fos2 = new FileOutputStream("database/Movie");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
		oos2.writeInt(num);
		for (Movie i : mList) {
			oos2.writeObject(i);
		}
		oos2.close();
	}

	private static void createShowingTime() throws Exception {
		Cinema cinema = ChooseCinema.chooseCinema();
		cinema.addShowingTime();
		ChooseCinema.updateCinema(cinema);
	}

	private static void removeShowingTime() throws Exception {
		Cinema cinema = ChooseCinema.chooseCinema();
		cinema.listShowingTime();
		System.out.println("input choice:");
		cinema.removeShowingTime(input.nextInt() - 1);
		ChooseCinema.updateCinema(cinema);
	}
}
