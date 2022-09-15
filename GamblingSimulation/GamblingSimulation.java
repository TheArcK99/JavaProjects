import java.util.*;

public class GamblingSimulation{
	public static void main(String args[]){
	Scanner scan = new Scanner(System.in);
	System.out.print("Hello to the Virtual Java Casino! What is your name? ");
	String pName = scan.nextLine();
	System.out.println("Hey "+pName+"! Welcome!");

	System.out.print("Please make a intial desposit between $20 and $1000. Type in the deposit amount: ");
	int money = scan.nextInt();
	while(money <= 20 || money >= 1000){
	  System.out.print("The amount has to be between $20 and $1000, inclusive, please type in a different amount: ");
	  money = scan.nextInt();
	}

	int ch;
		do {
			System.out.println("Here is a list of all the loaded games. Please type in the number of the game you would like to play");
			System.out.println("1.Slot Machine");
			System.out.println("2.Blackjack");
			System.out.println("3.Exit");
			ch = scan.nextInt();

			switch(ch){
				case 1:
					System.out.print("How much would you like to bet on the Slot Machines? ");
					int betSM = scan.nextInt();
					while(betSM > money){
						System.out.print("You dont have enough money? Please desosit more. Enter the amount here: ");
						money += scan.nextInt();
					}
					slotMachine a = new slotMachine(betSM, money);
					a.spin();
					String wL = "won";
					if(a.balance()<money){
						wL = "lost";
					}
					System.out.println("Your current account balance is "+a.balance()+" dollars. You "+wL+" "+a.deposit()+" dollars.");
					money = a.balance();
					break;
                case 2:
					System.out.print("Welcome to BlackJack. This is game where you play against the computer and roll dice to get the highest possible score under 21. How much would you like to bet on the Slot Machines? ");
					int betSM2 = scan.nextInt();
					while(betSM2 > money){
						System.out.print("You dont have enough money? Please desosit more. Enter the amount here: ");
						money += scan.nextInt();
					}
					blackjack b = new blackjack(betSM2, money);
					b.run();
					String wL2 = "won";
					if(b.balance()<money){
						wL2 = "lost";
					}
					System.out.println("Your current account balance is "+b.balance()+" dollars. You "+wL2+" "+b.dep()+" dollars.");
					money = b.balance();
                   	break;
                case 3:
                	System.out.println("Thanks for playing!");
                	System.exit(0);
                   	break;

			}
		}
		while(ch!=3);
	}

}

class slotMachine{

	private int deposit;
	private int balance;
	ArrayList<String> words;

	public slotMachine(int i, int a){
		deposit = i;
		balance = a;
		words = new ArrayList<String>();
		words.add("Cherries");
		words.add("Oranges");
		words.add("Plums");
		words.add("Apples");
		words.add("Potatoes");
		words.add("Grapes");
	}

	public void spin(){
		ArrayList<String> choices = new ArrayList<String>();
		for(int i = 0; i<3;i++){
			int choice = (int)(Math.random()*5);
			String e = words.get(choice);
			choices.add(e);
	}
	if(choices.get(0).equals(choices.get(1))&&choices.get(1).equals(choices.get(2))){
		balance += (deposit*3);
		System.out.println(choices.get(0));
		System.out.println(choices.get(1));
		System.out.println(choices.get(2));
		System.out.println("All three words match! Congratulation on winning "+(deposit*3)+" dollars!");
		deposit *= 3;
	}else if(choices.get(0).equals(choices.get(1))||choices.get(1).equals(choices.get(2))||choices.get(1).equals(choices.get(2))){
		balance += (deposit*2);
		System.out.println(choices.get(0));
		System.out.println(choices.get(1));
		System.out.println(choices.get(2));
		System.out.println("Two words match! Congratulation on winning "+(deposit*2)+" dollars!");
		deposit *= 2;
	}else{
		balance -= (deposit);
		System.out.println(choices.get(0));
		System.out.println(choices.get(1));
		System.out.println(choices.get(2));
		System.out.println("No words match! You lost your deposit!");
	}
	}

	public int balance(){
		return balance;
	}

	public int deposit(){
		return deposit;
	}
}

	 class blackjack{
		int bSum;
		int balance;
		Scanner inp;
		int pScore;
		int cScore;

		public blackjack(int i, int a){
		bSum = i;
		balance = a;
		inp = new Scanner(System.in);
		pScore = 0;
		cScore = 0;
		}
		public void run(){
		while(pScore <= 21){
			System.out.print("Let us begin the game. Would you like to roll a pair of dice? Type Y or N.");
			String cho = inp.nextLine();
			if(cho.equals("Y")){
			  for(int e = 0; e < 2; e++){
			  	cScore += (int)(Math.random()*5+1);
			  	pScore += (int)(Math.random()*5+1);
			  }
			}else{
				break;
			}
		}
		if(pScore>21){
			System.out.println("You went over 21. You lost your deposit.");
			balance -= bSum;
		}else if(cScore>pScore){
			System.out.println("The computer won. The computer had a score of "+cScore+" and you had a score of "+pScore+" . You lost your deposit.");
			balance -= bSum;
		}else if(pScore > cScore){
			System.out.println("You won! The computer had a score of "+cScore+" and you had a score of "+pScore+" . You just won "+(bSum*2)+" dollars.");
			balance += (bSum*2);
			bSum *= 2;
		}
		}

		public int balance(){
			return balance;
		}

		public int dep(){
			return bSum;
		}

	}
