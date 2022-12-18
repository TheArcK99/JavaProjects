import java.util.*;
import java.util.Arrays;

public class KansupadaA17 {

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int a = 1;
		while(a >= 1){
		System.out.print("Enter a number: ");
		a = s.nextInt();
		factor(a);

		}

	}


public static void factor(int a){
	for(int x = 2; x < a-1;x++){
		if(a % x == 0){
			System.out.print(x+" ");
			factor(a/x);
			return;
		}
	}
	System.out.println(a);
}

}