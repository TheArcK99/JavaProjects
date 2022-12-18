import java.util.*;

public class KansupadaA07 {
    public static void main(String args[]) {
    	System.out.print("How many dollars do you have? ");
     	Scanner inp = new Scanner(System.in);
     	playGame(inp.nextInt());
    }


public static boolean simRoll(){
		int d1 = (int)(Math.random()*6+1);
		int d2 = (int)(Math.random()*6+1);
		if((d1+d2)==7){
			return true;
		}
			return false;
}

public static void playGame(int i){
		int m = i;
		int c = 0;
		int turn = 0;
		int max = i;
		while(m>0){
			c++;
			if(simRoll()){
				m+=4;
			}else{
				m--;
			}

			if(m > max){
			 	turn = c;
			 	max = m;
			}
		}
		System.out.println("You are broke after "+c+" rolls.");
		System.out.println("You should have quit after "+turn+" rolls when you had "+max+" dollars.");
}
}

