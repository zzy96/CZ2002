package entity;

import java.io.Serializable;

public class Ticket implements Serializable {

	private int row;
	private int column;
	private double price;
	private boolean isBooked;
	private String TID;

	public Ticket(int r, int c, double p, String TicketID) {
		row = r;
		column = c;
		price = p;
		isBooked = false;
		TID = TicketID;
	}

	public String getTID() {
		return TID;
	}

	public boolean getBooked() {
		return isBooked;
	}

	public void setBooked(Boolean s) {
		isBooked = s;
	}

	public double getPrice() {
		return price;
	}

	public int getSeatRow() {
		return row + 1;
	}

	public int getSeatColumn() {
		return column + 1;
	}

}
