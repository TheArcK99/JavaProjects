import java.util.*;
import java.util.Arrays;

public class KansupadaA18 {

	private static double realMoney;

	public static void main(String args[]) {
			Scanner bob = new Scanner(System.in);
			System.out.print("How much money?\t $");
			String money = bob.nextLine();

			realMoney = Double.parseDouble(money);

			System.out.println("Bills");
			System.out.println("----------");

			checkChange(100, realMoney, "Hundreds:");
			checkChange(50, realMoney, "Fifties:");
			checkChange(20, realMoney, "Twenties:");
			checkChange(10, realMoney, "Tens:\t");
			checkChange(5, realMoney, "Fives:\t");
			checkChange(1, realMoney, "Ones:\t");

			System.out.println();
			System.out.println("Coins");
			System.out.println("----------");
			checkChange(0.25, realMoney, "Quarters:");
			checkChange(0.1, realMoney, "Dimes:\t");
			checkChange(0.05, realMoney, "Nickels:");
			checkChange(0.01, realMoney, "Pennies:");

		}

	public static void checkChange(double x, double realM, String word){
				System.out.println(word+" \t" + ((int)(realM/x)) );
				int tot = (int)(realM/x);
				realMoney = realM-(tot*x);
				realMoney = Math.round(realMoney * 100.0) / 100.0;
				//System.out.println(word+" \t" + 0);
	}

	}

