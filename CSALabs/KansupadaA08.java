import java.util.*;
import java.util.Arrays;

public class KansupadaA08 {
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
	System.out.println("1) Display the player's points");
	System.out.println("2) Pull a card from the deck");
	System.out.println("3) Reset");
	System.out.println("4) Exit");
	System.out.print("Pick an option (1 - 4) ");
}

public static void playGame(){
	int pPoint = 0;
	PlayingCard[] e;
	e = createDeck();
	for(int i = 0; i < e.length; i++){
			e[i].alignName();
			e[i].alignPts();
	}
	Scanner input = new Scanner(System.in);
	boolean b = false;
	while(!b){
	menu();
	int in = input.nextInt();
	if(in == 1){
		System.out.println("\nTotal Points : "+pPoint);
	}else if(in == 2){
		int c = (int)(Math.random()*(e.length));
		pPoint += e[c].returnPV();
		System.out.println("\nCard Pulled : "+ e[c].printCard());
		e = remove(e,c);
	}else if(in == 3){
		pPoint = 0;
		e = createDeck();
		for(int i = 0; i < e.length; i++){
			e[i].alignName();
			e[i].alignPts();
		}
		System.out.println("\nDeck Reset!");
	}else if(in == 4){
	 	System.exit(0);
	}
	System.out.println();
	}
}

public static PlayingCard[] remove(PlayingCard []e, int c){
	PlayingCard[] new2 = new PlayingCard[e.length-1];
	for(int i = 0; i < c; i++){
		new2[i]=e[i];
	}for(int i = c+1; i < e.length; i++){
		new2[i-1]=e[i];
	}
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
		}else if(name.equals("J")){
			pV = 10;
		}else if(name.equals("Q")){
			pV = 10;
		}else if(name.equals("K")){
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


