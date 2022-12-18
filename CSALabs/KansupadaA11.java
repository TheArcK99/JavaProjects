import java.util.*;
import java.util.Arrays;

public class KansupadaA11 {
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
	System.out.println("3) Exit");
	System.out.print("Pick an option (1 - 3) ");
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
}

