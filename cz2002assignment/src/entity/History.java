package entity;

import java.io.Serializable;

public class History implements Serializable {
	private String custName;
	private String movieName;
	private String TID;

	public History(String name, String mName, String TID) {
		custName = name;
		movieName = mName;
		this.TID = TID;
	}

	public String toString() {
		return custName + " " + movieName + " " + TID;
	}
}
