package boundary;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class CreateDatabase {

	public static void main(String[] args) throws Exception {

		FileOutputStream fos = new FileOutputStream("database/MovieGoer");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeInt(0);
		oos.close();

	}

}
