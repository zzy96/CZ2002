package control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Cinema;

public class ChooseCinema {

	private static ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
	private static int num;

	public static void listCinema() throws Exception {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("database/Cinema"));

		num = objectInputStream.readInt();
		cinemas.clear();
		for (int i = 0; i < num; i++) {
			cinemas.add((Cinema) objectInputStream.readObject());
			System.out.println((i + 1) + ". " + cinemas.get(i).toString());
		}

		objectInputStream.close();
	}

	public static Cinema chooseCinema() throws Exception {
		Scanner input = new Scanner(System.in);

		System.out.println("All cinemas:");
		listCinema();
		System.out.println("input your choice:");

		Cinema choice = cinemas.get(input.nextInt() - 1);
		input.nextLine();

		return choice;
	}

	public static void updateCinema(Cinema cinema) throws Exception {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("database/Cinema"));
		num = objectInputStream.readInt();
		Cinema temp;
		cinemas.clear();
		for (int i = 0; i < num; i++) {
			temp = (Cinema) objectInputStream.readObject();
			if (temp.getCode().equals(cinema.getCode())) {
				cinemas.add(cinema);
			} else {
				cinemas.add(temp);
			}
		}
		objectInputStream.close();

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("database/Cinema"));
		objectOutputStream.writeInt(num);
		for (int i = 0; i < num; i++) {
			objectOutputStream.writeObject(cinemas.get(i));
		}
		objectOutputStream.close();
	}
}
