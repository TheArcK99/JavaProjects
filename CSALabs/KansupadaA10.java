import java.util.*;
import java.util.Arrays;

public class KansupadaA10 {
    public static void main(String args[]) {
     	Scanner inp = new Scanner(System.in);
		playGame();

		}

public static PlayingCard[] createDeck(){
	PlayingCard[] deck = new PlayingCard[52];
	int s = 1;
	for(int i = 0; i < deck.length-39; i++){
		deck[i] = new PlayingCard("\3",s,String.valueOf(s));
		s++;
	}
	int g = 1;
	for(int e = deck.length-39 ; e < deck.length-26; e++){
		deck[e] = new PlayingCard("\4",g,String.valueOf(g));
		g++;
	}
	int j = 1;
	for(int c = deck.length-26; c < deck.length-13; c++){
		deck[c] = new PlayingCard("\5",j,String.valueOf(j));
		j++;
	}
	int v = 1;
	for(int d = deck.length-13 ; d < deck.length; d++){
		deck[d] = new PlayingCard("\6",v,String.valueOf(v));
		v++;
	}
	return deck;
}

public static void menu(){
	System.out.println("MENU:");
	System.out.println("1) Display the cards");
	System.out.println("2) Put the cards in order");
	System.out.println("3) Are they in order?");
	System.out.println("4) Add a new card");
	System.out.println("5) Exit");
	System.out.print("Pick an option (1 - 5) ");
}

public static void playGame(){
	int pPoint = 0;
	boolean grow = false;
	PlayingCard[] e;
	e = createDeck();
	for(int i = 0; i < e.length; i++){
			e[i].alignName();
			e[i].alignPts();
	}
	PlayingCard[] hand = new PlayingCard[5];
	for(int i = 0; i < 5; i++){
		int c = (int)(Math.random()*(e.length));
		hand[i] = e[c];
	}
	Scanner input = new Scanner(System.in);
	boolean b = false;
	while(!b){
	menu();
	int in = input.nextInt();
	if(in == 1){

		for(int i = 0; i < hand.length; i++){
			System.out.print(hand[i].printCard()+" ");
	}
	System.out.println();
	}else if(in == 2){
	   int time = 0;
	for(int h = 0; h < hand.length; h++){
       PlayingCard[] temp = new PlayingCard[1];
       int min = 15;
       int pos = 0;
       for(int i = time; i< hand.length; i++){
       	if(hand[i].returnPV() < min){
       		min = hand[i].returnPV();
       		pos = i;
       	}
       }
       System.out.println(hand[pos].printCard()+" is the smallest");
       temp[0] = hand[time];
       hand[time] = hand[pos];
       hand[pos] = temp[0];
       for(int i = 0; i < hand.length; i++){
			System.out.print(hand[i].printCard()+" ");
	   }
	   System.out.println();
       time++;
	   }
	}else if(in == 3){
		grow = true;
		for(int i = 0; i < hand.length-1; i++){
		   if(hand[i].returnPV() > hand[i+1].returnPV()){
		   	grow = false;
		   }
		}
		if(grow){
		  System.out.print("The cards are in order: ");
		  for(int i = 0; i < hand.length; i++){
			System.out.print(hand[i].printCard()+" ");
	   	}
		}else{
		System.out.print("The cards are not in order: ");
		  for(int i = 0; i < hand.length; i++){
			System.out.print(hand[i].printCard()+" ");
	   	}
		}
		System.out.println();
	}else if(in == 4){
		grow = true;
		for(int i = 1; i < hand.length; i++){
		   if(hand[i-1].returnPV() > hand[i].returnPV()){
		   	grow = false;
		   }
		}
		System.out.println(grow);
		int x = (int)(Math.random()*(e.length));
		if(grow){
		hand = add(hand, e[x],true);
		}else if(grow == false){
		hand = add(hand, e[x],false);
		}
	}else if(in == 5){
	 	System.exit(0);
	}
	System.out.println();
	}
}

public static PlayingCard[] add(PlayingCard[] a, PlayingCard c, boolean k){
	PlayingCard[] new2 = new PlayingCard[a.length+1];
	int place = 0;
	if(k){
	for(int i = 0; i < a.length; i++){
	   if(c.returnPV() >= a[i].returnPV()){
	   	place = i+1;
	   }
	}
	for(int i = 0; i < place; i++){
		new2[i] = a[i];
	}
	new2[place] = c;
	for(int i = place+1; i < new2.length; i++){
		new2[i] = a[i-1];
	}
	System.out.println(place);
	}else if(k==false){
	new2[0] = c;
	for(int i = 1; i < new2.length; i++){
		new2[i] = a[i-1];
	}
	}
	System.out.println(c.printCard()+" was added to index " +place);
	place = 0;
	return new2;
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
}


