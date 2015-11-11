package control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import boundary.MovieGoerUI;
import entity.Movie;
import entity.MovieGoer;

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

	public static void buyTicket() {
		// TODO Auto-generated method stub

	}
}
