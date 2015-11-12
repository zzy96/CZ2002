package boundary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Cinema;

/**
 * 
 * Create cinema. Store cinema information into the Cinema database
 *
 */
public class CreateCinema {

	private static ArrayList<Cinema> cinemas = new ArrayList<Cinema>();

	public static void main(String[] args) throws Exception {

		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("database/Cinema"));
		Scanner input = new Scanner(System.in);

		int num = objectInputStream.readInt();

		for (int i = 0; i < num; i++) {
			cinemas.add((Cinema) objectInputStream.readObject());
		}
		objectInputStream.close();

		System.out.println("=====Add new cinema=====");
		System.out.println("input cinema code(XXX):");
		String code = input.nextLine();
		System.out.println("input cinema cineplex:");
		String cineplex = input.nextLine();
		System.out.println("input cinema class(1?large:small):");
		int cinemaClass = input.nextInt();
		cinemas.add(new Cinema(code, cineplex, cinemaClass));
		num++;

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database/Cinema"));
		oos.writeInt(num);
		for (int i = 0; i < num; i++) {
			oos.writeObject(cinemas.get(i));
		}
		oos.close();

		System.out.println("new cinema added");

		input.close();
	}
}
