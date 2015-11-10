package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Movie implements Serializable {

	private String title;
	private int movieType;
	// 3D = 3 , Animation = 2, Normal = 1;
	private int showingStatus;
	// coming soon = 1 , preview = 2, now showing = 3;
	private String synopsis;
	private String director;
	private ArrayList<String> cast = new ArrayList<String>();
	private double rating;
	private ArrayList<Review> reviews = new ArrayList<Review>();

	public Movie() {
		Scanner input = new Scanner(System.in);
		System.out.println("input movie title:");
		this.title = input.nextLine();
		System.out.println("input movie type:");
		System.out.println("1. Normal");
		System.out.println("2. Animation");
		System.out.println("3. 3D");
		this.movieType = input.nextInt();
		input.nextLine();
		System.out.println("input showingStatus:");
		System.out.println("1. coming soon");
		System.out.println("2. preview");
		System.out.println("3. now showing");
		this.showingStatus = input.nextInt();
		input.nextLine();
		System.out.println("input synopsis:");
		this.synopsis = input.nextLine();
		System.out.println("input director:");
		this.director = input.nextLine();
		System.out.println("input number of casts:");
		int castNum = input.nextInt();
		input.nextLine();
		for (int i = 0; i < castNum; i++) {
			System.out.println("input cast " + (i + 1) + " :");
			cast.add(input.nextLine());
		}
		this.rating = 0;
	}

	public void addReview(Review newReview) {
		reviews.add(newReview);
		rating = (double) (rating * (reviews.size() - 1) + newReview.getRating()) / reviews.size();
	}

	public String getTitle() {
		return title;
	}

	public double getRating() {
		return rating;
	}

	public void printInfo() {

		System.out.println("=====INFORMATION=====");
		System.out.println("title: " + title);
		System.out.println("showing status: " + getStatus());
		System.out.println("movie type: " + getType());
		System.out.println("director: " + director);
		System.out.println("synopsis: " + synopsis);
		System.out.println("cast information:");
		for (int i = 0; i < cast.size(); i++) {
			System.out.println("-- " + cast.get(i));
		}
		System.out.println("overall rating: " + rating);
		System.out.println("past reviews:");
		for (int i = 0; i < reviews.size(); i++) {
			System.out.println("-- " + reviews.get(i).toString());
		}
		System.out.println("=====================");
	}

	public int getTypeNum() {
		return movieType;
	}

	public String getType() {
		switch (movieType) {
		case 1:
			return "Normal";
		case 2:
			return "Animation";
		case 3:
			return "3D";
		default:
			return "N.A.";
		}
	}

	public void updateStatus() {
		Scanner input = new Scanner(System.in);
		System.out.println("input new showingStatus:");
		System.out.println("1. coming soon");
		System.out.println("2. preview");
		System.out.println("3. now showing");
		this.showingStatus = input.nextInt();
		input.nextLine();
	}

	public String getStatus() {
		switch (showingStatus) {
		case 1:
			return "coming soon";
		case 2:
			return "preview";
		case 3:
			return "now showing";
		default:
			return "N.A.";
		}
	}

}
