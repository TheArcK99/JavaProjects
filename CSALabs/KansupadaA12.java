import java.util.*;
import java.util.Arrays;

public class KansupadaA12 {
    public static void main(String args[]) {
     	Scanner inp = new Scanner(System.in);
		playGame();

		}

public static ArrayList<PlayingCard> createDeck(){
	ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
	int s = 1;
	for(int i = 0; i < 13; i++){
		deck.add(new PlayingCard("\3",s,String.valueOf(s)));
		s++;
	}
	int g = 1;
	for(int e = 0 ; e < 13; e++){
		deck.add(new PlayingCard("\4",g,String.valueOf(g)));
		g++;
	}
	int j = 1;
	for(int c = 0; c < 13; c++){
		deck.add(new PlayingCard("\5",j,String.valueOf(j)));
		j++;
	}
	int v = 1;
	for(int d = 0 ; d < 13; d++){
		deck.add(new PlayingCard("\6",v,String.valueOf(v)));
		v++;
	}
	return deck;
}

public static void menu(){
	System.out.println("MENU:");
	System.out.println("1) Display the cards");
	System.out.println("2) Reset the deck");
	System.out.println("3) Sort the deck");
	System.out.println("4) Delete the suit");
	System.out.println("5) Exit");
	System.out.print("Pick an option (1 - 5) ");
}

public static void playGame(){
	ArrayList<PlayingCard> e = createDeck();
	ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
	for(int i = 0; i < e.size(); i++){
			e.get(i).alignName();
			e.get(i).alignPts();
	}
	Scanner input = new Scanner(System.in);
	boolean b = false;
	while(!b){
	menu();
	int in = input.nextInt();
	if(in == 1){
		System.out.println("The deck has "+hand.size()+" cards: ");
		for(int i = 0; i<hand.size();i++){
			System.out.print(hand.get(i).printCard()+" ");
		}
	System.out.println();
	if(hand.size()>0){
	System.out.println();
	}
	}else if(in == 2){
	int o = 0;
	boolean j = true;
	while(j){
	System.out.print("How many cards do you want to add to the deck? ");
	o = input.nextInt();
	if(o >= 0 && o <= 52){
	j = false;
	}
	}
	hand = new ArrayList<PlayingCard>();
	for(int i = 0; i < o; i++){
		int k = (int)(Math.random()*(e.size()));
		hand.add(e.get(k));
		e.remove(k);
	}
	e = createDeck();
	for(int i = 0; i < e.size(); i++){
			e.get(i).alignName();
			e.get(i).alignPts();
	}
	System.out.println();
	}else if(in == 3){
		int time = 0;
	for(int h = 0; h < hand.size(); h++){
       PlayingCard temp;
       int min = 15;
       int pos = 0;
       for(int i = time; i< hand.size(); i++){
       	if(hand.get(i).returnPV() < min){
       		min = hand.get(i).returnPV();
       		pos = i;
       	}
       }
       temp = hand.get(time);
       hand.set(time, hand.get(pos));
       hand.set(pos, temp);
       time++;
	   }
	   System.out.println();
	}else if(in == 4){
		int k = 0;
		System.out.println();
		System.out.println("1) Hearts \3");
		System.out.println("2) Diamonds \4");
		System.out.println("3) Clubs \5");
		System.out.println("4) Spades \6");
		System.out.print("Which suit do you want to delete? ");
		k = input.nextInt();
		String s = "";
		if(k == 1){
		s = "\3";
		}else if(k == 2){
		s = "\4";
		}else if(k == 3){
		s = "\5";
		}else if(k == 4){
		s = "\6";
		}
		System.out.println(s);
		for(int i = 0; i < hand.size(); i++){
			if(hand.get(i).returnSuit().equals(s)){
				hand.remove(i);
				i = 0;
			}
		}
		if(hand.size()==1 && hand.get(0).returnSuit().equals(s)){
			hand.remove(0);
		}
		System.out.println();
	}else if(in == 5){
	System.exit(0);
	}
	}
}

}



class PlayingCard{

	private String suit;
	private int pV;
	private String name;

	public PlayingCard(String s, int i, String d){
		suit = s;
		pV = i;
		name = d;
	}

	public void alignName(){
		if(name.equals("1")){
			name = "A";
		}else if(name.equals("11")){
			name = "J";
		}else if(name.equals("12")){
			name = "Q";
		}else if(name.equals("13")){
			name = "K";
		}
	}

	public void alignPts(){
		if(name.equals("A")){
			pV = 11;
		}
		if(name.equals("J")){
			pV = 10;
		}
		if(name.equals("Q")){
			pV = 10;
		}
		if(name.equals("K")){
			pV = 10;
		}
	}


	public String printCard(){
		return name+suit;
	}

	public int returnPV(){
		return pV;
	}

	public String returnSuit(){
		return suit;
	}
}

