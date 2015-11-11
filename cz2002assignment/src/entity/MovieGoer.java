package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import control.ChooseMovie;

public class MovieGoer implements Serializable {
	private String name;
	private long mobileNo;
	private String email;
	private int age;
	private ArrayList<Review> reviews;
	private ArrayList<History> history;

	public MovieGoer() {
		Scanner input = new Scanner(System.in);
		System.out.println("input name:");
		name = input.nextLine();
		System.out.println("input mobile number:");
		mobileNo = input.nextLong();
		input.nextLine();
		System.out.println("input email:");
		email = input.nextLine();
		System.out.println("input age:");
		age = input.nextInt();
		reviews = new ArrayList<Review>();
	}

	public String getName() {
		return name;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public int getAge() {
		return age;
	}

	public void makeReview() throws Exception {
		Movie movie = ChooseMovie.chooseMovie();
		Review newReview = new Review(this);
		movie.addReview(newReview);
		ChooseMovie.updateMovie(movie);
		reviews.add(newReview);
	}
	
	public void showReview() {
		System.out.println("All " + this.getName() + "'s Reviews:");
		for (Review i : reviews) {
			System.out.println(i.toString());
		}

	}
	public void addHistory(History his){
		this.history.add(his);
	}
	public void showHistory(){
		System.out.println(this.getName()+"'s booking history:");
		for(History i: history){
			System.out.println(i.toString());
		}
	}
	// public void addHistory(History his){
	// this.history.add(his);
	// }

}
