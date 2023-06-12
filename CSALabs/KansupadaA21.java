import java.util.*;
import java.util.Arrays;

public class KansupadaA21 {
	public static void main(String args[]) {
		char[] up = {'A','E','I','O','U'};
		char[] low = {'a','e','i','o','u'};
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a line of text here: \t");
		String b = input.nextLine();

		for(int i = 0; i < b.length(); i++){
			String half1 = b.substring(0, i);
			String half2 = b.substring(i+1);
			if(v(b.charAt(i))){
				if((int)b.charAt(i) < 97){
					char e = b.charAt(i);
					while(e == b.charAt(i) ){
					int x = (int)(Math.random()*5);
					e = up[x];
					}
					b = half1+e+half2;
				}else{
					char e = b.charAt(i);
					while(e == b.charAt(i) ){
					int x = (int)(Math.random()*5);
					e = low[x];
				}
				b = half1+e+half2;
			}
		}
	}
	System.out.println();
	System.out.println("\t\t\t\t"+b);
	System.out.println();
	}
	public static boolean v(char a){
		if(a=='A')
			return true;
		if(a=='E')
			return true;
		if(a=='I')
			return true;
		if(a=='O')
			return true;
		if(a=='U')
			return true;
		if(a=='a')
			return true;
		if(a=='e')
			return true;
		if(a=='i')
			return true;
		if(a=='o')
			return true;
		if(a=='u')
			return true;
		return false;
	}
}


