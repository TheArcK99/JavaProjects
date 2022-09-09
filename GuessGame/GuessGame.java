import java.util.*;

public class GuessGame{
	public static void main(String args[]){
		int number = (int)(Math.random()*100+1);
		Scanner inp = new Scanner(System.in);
		System.out.println("Welcome to the Java Guessing Game. Our advanced computer had genereated a random number for you to guess in this game.");
		System.out.println("The number is between 1 and 100.");
		int g1 = 0;
		int count = 0;

		while(g1 != number){
		System.out.print("Please state your guess: ");
		g1 = inp.nextInt();

		if(g1 > number){
		System.out.println("That's too high. Try again!");
		count++;
		}

		if(g1 < number){
		System.out.println("That's too low. Try again!");
		count++;
		}
		}

		if(g1 == number){
		System.out.println("You guessed the number! It took you "+count+" tries!");
		}
	}
}