package entity;

import java.io.Serializable;
import java.util.Scanner;

public class Review implements Serializable {
	private String name;
	private String content;
	private int rating;

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

	public int getRating() {
		return rating;
	}

	public Review(MovieGoer goer) {
		Scanner input = new Scanner(System.in);

		name = goer.getName();
		System.out.println("Review Content:");
		content = input.nextLine();
		do {
			System.out.println("Rating(1/2/3/4/5):");
			rating = input.nextInt();
		} while (rating < 0 || rating > 5);
	}

	public String toString() {
		return "Reviewer:" + name + " | Content:" + content + " | Rating:" + rating;
	}

}
