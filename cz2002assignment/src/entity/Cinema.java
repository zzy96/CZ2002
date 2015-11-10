package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable {

	private String code;
	private String cineplex;
	private int cinemaClass;
	private ArrayList<ShowingTime> showingTime;

	public Cinema(String code, String cineplex, int cinemaClass) {
		this.code = code;
		this.cineplex = cineplex;
		this.cinemaClass = cinemaClass;
		showingTime = new ArrayList<ShowingTime>();
	}

	@Override
	public String toString() {
		return "Cinema " + code + " [cineplex:" + cineplex + ", cinemaClass:" + getCinemaClass() + "]";
	}

	public String getCode() {
		return code;
	}

	public String getCineplex() {
		return cineplex;
	}

	public String getCinemaClass() {
		if (cinemaClass == 1) {
			return "Large";
		} else {
			return "Small";
		}
	}

	public void addShowingTime() throws Exception {
		showingTime.add(new ShowingTime(this));

	}

	public void removeShowingTime(int index) {
		showingTime.remove(index);
	}

	public void listShowingTime() {
		System.out.println("Showing list:");
		for (int i = 0; i < showingTime.size(); i++) {
			System.out.println((i + 1) + ". " + showingTime.get(i).toString());
		}
		System.out.println("");
	}
}
