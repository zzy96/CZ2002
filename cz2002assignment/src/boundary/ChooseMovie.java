package boundary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Movie;

public class ChooseMovie {
	private static ArrayList<Movie> movies = new ArrayList<Movie>();

	public static void listMovie() throws Exception {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("database/Movie"));

		int num = objectInputStream.readInt();
		movies.clear();
		for (int i = 0; i < num; i++) {
			movies.add((Movie) objectInputStream.readObject());
			System.out.println("Movie " + (i + 1) + " : " + movies.get(i).getTitle());
		}

		objectInputStream.close();
	}

	public static Movie chooseMovie() throws Exception {
		Scanner input = new Scanner(System.in);

		System.out.println("All movies:");
		listMovie();
		System.out.println("input your choice:");

		Movie choice = movies.get(input.nextInt() - 1);
		input.nextLine();

		return choice;
	}

	public static void updateMovie(Movie movie) throws Exception {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("database/Movie"));
		int num = objectInputStream.readInt();
		Movie temp;
		movies.clear();
		for (int i = 0; i < num; i++) {
			temp = (Movie) objectInputStream.readObject();
			if (temp.getTitle().equals(movie.getTitle())) {
				movies.add(movie);
			} else {
				movies.add(temp);
			}
		}
		objectInputStream.close();

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("database/Movie"));
		objectOutputStream.writeInt(num);
		for (int i = 0; i < num; i++) {
			objectOutputStream.writeObject(movies.get(i));
		}
		objectOutputStream.close();
	}
}
