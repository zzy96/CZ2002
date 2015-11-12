package boundary;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * Create empty database start with the number of objects in the database. Now
 * the total number is initialized to 0
 *
 */
public class CreateDatabase {

	public static void main(String[] args) throws Exception {

		FileOutputStream fos = new FileOutputStream("database/MovieGoer");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeInt(0);
		oos.close();

	}

}
