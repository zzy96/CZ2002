package control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import boundary.MovieGoerUI;
import entity.Cinema;
import entity.History;
import entity.Movie;
import entity.MovieGoer;
import entity.ShowingTime;
import entity.Ticket;

public class MovieGoerController {
	public static void movieGoerRegistration() throws Exception {

		boolean loop = true;
		MovieGoer goer;
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("database/MovieGoer"));
		int num = objectInputStream.readInt();
		MovieGoer[] exist = new MovieGoer[num];
		for (int i = 0; i < num; i++) {
			exist[i] = (MovieGoer) objectInputStream.readObject();
		}
		objectInputStream.close();

		do {
			loop = false;
			goer = new MovieGoer();
			for (int i = 0; i < num; i++) {
				if (exist[i].getName() == goer.getName()) {
					loop = true;
				}
			}
			if (!loop) {
				num++;
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(
						new FileOutputStream("database/MovieGoer"));
				objectOutputStream.writeInt(num);
				for (int i = 0; i < num - 1; i++) {
					objectOutputStream.writeObject(exist[i]);
				}
				objectOutputStream.writeObject(goer);
				objectInputStream.close();
			} else {
				System.out.println("User already exist!");
			}
		} while (loop);
		MovieGoerUI.movieGoerMain(goer);
	}

	public static void movieGoerLogin() throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("input name:");
		String name = input.nextLine();
		MovieGoer check;

		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("database/MovieGoer"));
		int num = objectInputStream.readInt();
		for (int i = 0; i < num; i++) {
			check = (MovieGoer) objectInputStream.readObject();
			if (check.getName().equals(name)) {
				MovieGoerUI.movieGoerMain(check);
				break;
			}
		}
		System.out.println("User doesn't exist!");
		objectInputStream.close();
	}

	private static void movieGoerMain(MovieGoer goer) throws Exception {
		Scanner input = new Scanner(System.in);
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

			switch (option) {
			case 1:
				ChooseMovie.listMovie();
				break;
			case 2:
				ChooseMovie.chooseMovie().printInfo();
				break;
			case 3:
				buyTicket(goer);
				updateMovieGoer(goer);
				break;
			case 4:
				goer.showHistory();
				break;
			case 5:
				goer.makeReview();
				updateMovieGoer(goer);
				break;
			case 6:
				goer.showReview();
				break;
			case 7:
				listTopMovie();
				break;
			case 8:
				loop = false;
			default:
				break;
			}
		}

	}

	public static void updateMovieGoer(MovieGoer movie) throws Exception {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("database/MovieGoer"));
		int num = objectInputStream.readInt();
		MovieGoer temp;
		ArrayList<MovieGoer> goers = new ArrayList<MovieGoer>();
		for (int i = 0; i < num; i++) {
			temp = (MovieGoer) objectInputStream.readObject();
			if (temp.getName().equals(movie.getName())) {
				goers.add(movie);
			} else {
				goers.add(temp);
			}
		}
		objectInputStream.close();

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("database/MovieGoer"));
		objectOutputStream.writeInt(num);
		for (int i = 0; i < num; i++) {
			objectOutputStream.writeObject(goers.get(i));
		}
		objectOutputStream.close();
	}

	public static void listTopMovie() throws Exception {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("database/Movie"));
		int num = objectInputStream.readInt();
		Movie movies[] = new Movie[num];
		for (int i = 0; i < num; i++) {
			movies[i] = (Movie) objectInputStream.readObject();
		}
		objectInputStream.close();

		Movie temp;
		int count = num > 5 ? 5 : num;
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < num; j++) {
				if (movies[i].getRating() < movies[j].getRating()) {
					temp = movies[i];
					movies[i] = movies[j];
					movies[j] = temp;
				}
			}
		}

		System.out.println("Top Five Movie by Rating:");
		for (int i = 0; i < 5; i++) {
			if (i < count) {
				System.out.println((i + 1) + ". " + movies[i].getTitle() + " ----- " + movies[i].getRating());
			} else {
				System.out.println((i + 1) + ". Not Available");
			}
		}
		System.out.println("");
	}

	public static void buyTicket(MovieGoer goer) throws Exception {

		Scanner input = new Scanner(System.in);
		Cinema c = ChooseCinema.chooseCinema();
		c.listShowingTime();
		System.out.println("Select a showingtime");
		ShowingTime s = c.selectShowingTime(input.nextInt());
		s.printAllTicket();

		System.out.println("Choose row number");
		int i = input.nextInt();
		System.out.println("Choose column number");
		int j = input.nextInt();
		Ticket t = s.getTicket(i, j);

		if (goer.getAge() <= 12) {
			System.out.println("Child Price: " + t.getPrice() * TicketPriceController.getDiscount());
		} else if (goer.getAge() >= 60) {
			System.out.println("Senior Price: " + t.getPrice() * TicketPriceController.getDiscount());
		} else {
			System.out.println("Standard Price: " + t.getPrice());
		}

		System.out.println("Confirm Booking?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		if (input.nextInt() == 1) {
			t.setBooked(true);
			ChooseCinema.updateCinema(c);
			System.out.println("booking successful");

			History h = new History(goer.getName(), s.getMovie().getTitle(), t.getTID());
			goer.addHistory(h);
		}
	}
}
