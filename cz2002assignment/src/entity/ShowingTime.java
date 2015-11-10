package entity;

import java.io.Serializable;
import java.util.Scanner;

import boundary.ChooseMovie;
import control.TicketPriceController;

public class ShowingTime implements Serializable {
	private String cinemaCode;
	private Movie movie;
	private String date;
	private String time;
	private Ticket[][] ticket;

	public ShowingTime(Cinema cinema) throws Exception {
		Scanner input = new Scanner(System.in);

		this.cinemaCode = cinema.getCode();
		this.movie = ChooseMovie.chooseMovie();
		System.out.print("input date(dd/mm/yyyy):");
		this.date = input.nextLine();
		System.out.print("Enter the time(hh:mm):");
		this.time = input.nextLine();

		int size = cinema.getCinemaClass().equals("Large") ? 20 : 10;
		double price = TicketPriceController.getPrice(movie.getTypeNum(), cinema.getCinemaClass(), date);

		ticket = new Ticket[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				ticket[i][j] = new Ticket(i, j, price, cinemaCode + date.substring(0, 4) + date.substring(5, 7)
						+ date.substring(8, 10) + time.substring(0, 2) + time.substring(3, 5));
			}
		}
	}

	public Movie getMovie() {
		return this.movie;
	}

	public String getDate() {
		return this.date;
	}

	public String getTime() {
		return this.time;
	}

	public Ticket[] getTicket(boolean available) {
		int size = ticket.length;
		Ticket[] checkTicket = new Ticket[size * size];
		int index = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (ticket[i][j].getBooked() != available) {
					checkTicket[index] = ticket[i][j];
					index++;
				}

			}
		}
		return checkTicket;
	}

	public String toString() {
		return "MOVIE:" + movie.getTitle() + " DATE: " + date + " TIME: " + time;
	}

	public void printAllTicket() {
		int size = ticket.length;

		System.out.print("  ");
		for (int k = 0; k < size; k++)
			System.out.print(k + " ");
		System.out.println();

		for (int i = 0; i < size; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < size; j++) {
				if (ticket[i][j].getBooked())
					System.out.print("X ");
				else
					System.out.print("O ");
			}
			System.out.println();
		}
	}
}