package control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Cinema;
import entity.Movie;

public class AdminController {
	public static void removeMovie() throws Exception {
		Scanner input = new Scanner(System.in);
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
		for (i = 0; i < num + 1; i++) {
			if (!(sel == i + 1)) {
				oos2.writeObject(mList[i]);
			}
		}

		oos2.close();

	}

	public static void updateMovieStatus() throws Exception {

		Movie movie = ChooseMovie.chooseMovie();
		movie.updateStatus();
		ChooseMovie.updateMovie(movie);

	}

	public static void createMovie() throws Exception {
		Scanner input = new Scanner(System.in);
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

	public static void createShowingTime() throws Exception {
		Cinema cinema = ChooseCinema.chooseCinema();
		cinema.addShowingTime();
		ChooseCinema.updateCinema(cinema);
	}

	public static void removeShowingTime() throws Exception {
		Scanner input = new Scanner(System.in);
		Cinema cinema = ChooseCinema.chooseCinema();
		cinema.listShowingTime();
		System.out.println("input choice:");
		cinema.removeShowingTime(input.nextInt() - 1);
		ChooseCinema.updateCinema(cinema);
	}
}
