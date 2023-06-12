import java.util.*;
import java.util.Arrays;

public class KansupadaA20 {

	public static void main(String args[]) {
	ArrayList<String> timmy = construct();
	display(timmy);
	}

	public static ArrayList<String> construct(){
		ArrayList<String> data = new ArrayList<String>();
		int numS = (int)((Math.random()*21)+10);
		for(int i = 0; i < numS; i++){
			String bob = "";
			int numC = (int)((Math.random()*21)+5);
			for(int a = 0; a < numC; a++){
				int billy = (int)((Math.random()*94)+33);
				char jilly = (char) (billy+'0');
				bob += jilly;
			}
			data.add(bob);
		}
		return data;
	}

	public static void display(ArrayList<String> john){
		int numOS = john.size();
		int mPos = findMax(john);
		int maxSpace = john.get(mPos).length();

		for(int x = 0; x < maxSpace+4; x++){
			System.out.print("*");
		}
		System.out.println();

		System.out.print("*");
		for(int x = 0; x < maxSpace+2; x++){
			System.out.print(" ");
		}
		System.out.println("*");

		for(int i = 0; i < numOS; i++){
			int currentPos = 0;
			System.out.print("*");
			System.out.print(" ");
			if(john.get(i).length()<maxSpace){
			for(int c = 0; c < (maxSpace/2.0)-(john.get(i).length()/2.0); c++){
				System.out.print(" ");
				currentPos++;
			}
			System.out.print(john.get(i));
			currentPos += john.get(i).length();
			for(int e = 0; e < maxSpace-currentPos; e++){
				System.out.print(" ");
			}
			}else if(john.get(i).length() == maxSpace){
			System.out.print(john.get(i));
			}
			System.out.print(" ");
			System.out.println("*");
		}



		System.out.print("*");
		for(int x = 0; x < maxSpace+2; x++){
			System.out.print(" ");
		}
		System.out.println("*");

		for(int x = 0; x < maxSpace+4; x++){
			System.out.print("*");
		}
		System.out.println();

	}

	public static int findMax(ArrayList<String> jane){
		int max = 0;
		int pos = 0;
		for(int i = 0; i < jane.size(); i++){
			if(jane.get(i).length()>max){
				max = jane.get(i).length();
				pos = i;
			}
		}

		return pos;
	}

}