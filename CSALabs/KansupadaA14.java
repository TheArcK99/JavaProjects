import java.util.*;
import java.util.Arrays;

public class KansupadaA14 {
	static ArrayList<PlayingCard> deck;
    public static void main(String args[]) {
     	System.out.println("Original Deck");
     	createDeck();
     	System.out.println("Shuffled Deck");
     	shuffleDeck();
		System.out.println("Sorted Deck");
     	sortedDeck();
		}

public static void createDeck(){
	deck = new ArrayList<PlayingCard>();
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
	for(int i = 0; i < deck.size(); i++){
			deck.get(i).alignName();
			deck.get(i).alignPts();
	}
	for(int i = 0; i < deck.size(); i++){
		System.out.print(deck.get(i).printCard()+ " ");
	}
	System.out.println();
}

public static void shuffleDeck(){
	for(int i = deck.size()-1; i > 0; i--){
		int point = (int)(Math.random()*i);
		PlayingCard t = deck.get(i);
		deck.set(i,deck.get(point));
		deck.set(point, t);
	}
	for(int i = 0; i < deck.size(); i++){
		System.out.print(deck.get(i).printCard()+ " ");
	}
	System.out.println();
}

public static void sortedDeck(){
	int time = 0;
	for(int h = 0; h < deck.size(); h++){
       PlayingCard temp;
       String min = "\3";
       int pos = 0;
       for(int i = time; i< deck.size(); i++){
       	if(deck.get(i).returnSuit().compareTo(min) >= 0){
       		min = deck.get(i).returnSuit();
       		pos = i;
       	}
       }
       temp = deck.get(time);
       deck.set(time, deck.get(pos));
       deck.set(pos, temp);
       time++;
	   }
	for(int i = deck.size()-1; i >= 0; i--){
		System.out.print(deck.get(i).printCard()+ " ");
	}
	System.out.println();
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

