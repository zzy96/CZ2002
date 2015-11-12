package boundary;

import java.util.Scanner;

/**
 * Movie Booking and Listing Management Application
 * 
 * @author CZ2002/BCG2
 * @author Fu Qiang, Lei Ming, Lu Chenghao, Pan Jiangdong, Zhou Zhiyao
 * 
 */
public class MainUI {

	/**
	 * Main entry of the program
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		int sel = 0;
		System.out.println("=====Welcome=====");

		while (sel != 3) {
			System.out.println("1. Login as administrator");
			System.out.println("2. Login as movie goer");
			System.out.println("3. Quit");
			System.out.println("input your choice:");
			sel = input.nextInt();

			switch (sel) {
			case 1:
				input.nextLine();
				AdminUI.checkLogin();
				break;
			case 2:
				MovieGoerUI.movieGoerChooseLogin();
				break;
			default:
				break;
			}
		}

		input.close();

	}
}
