package entity;

public class History {
	private String custName;
	private String movieName;
	private String trID;

	public History(String name, String mName, String TID) {
		custName = name;
		movieName = mName;
		trID = TID;
	}

	public String getCustName() {
		return custName;
	}

	public String getMovieName() {
		return movieName;
	}
}
