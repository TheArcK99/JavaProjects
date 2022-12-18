import java.util.*;
public class KansupadaA15
{
	/*Do not alter any existing code*/
	static ArrayList<Quote> quoteList = new ArrayList<Quote>();
	public static void populateList()
	{
		quoteList.add(new Quote("Grace Hopper","To me programming is more than an important practical art. It is also a gigantic undertaking in the foundations of knowledge."));
		quoteList.add(new Quote("Edsger Dysktra","The question of whether a computer can think is no more interesting than the question of whether a submarine can swim."));
		quoteList.add(new Quote("Ada Lovelace","I never am really satisfied that I understand anything; because, understand it well as I may, my comprehension can only be an infinitesimal fraction of all I want to understand about the many connections and relations which occur to me, how the matter in question was first thought of or arrived at?"));
		quoteList.add(new Quote("Alan Turing","One day [people] will take their computers for walks in the park and tell each other, \"My little computer said such a funny thing this morning\"."));
	}
	public static void main(String args[])
	{
		populateList();
		menu();
	}
	/*Complete the menu method*/
	public static void menu()
	{
		int choice=0;
		while(choice!=3)
		{
			System.out.println("1) Display all quotes");
			System.out.println("2) Display a random quote");
			System.out.println("3) Exit");
			System.out.print("Select an option\t");
			Scanner input = new Scanner(System.in);
			choice=input.nextInt();
			if(choice==1)
			{
			System.out.println();
			for(int i = 0; i < quoteList.size(); i++){
				quoteList.get(i).printQ();
			}
			}
			if(choice==2)
			{
				System.out.println();
				quoteList.get((int)((Math.random()*4))).printQ();
			}


		}
	}


}
class Quote
{
	String person;
	String quote;

	public Quote(String s, String s1){
		person = s;
		quote = s1;
	}

	public void printQ(){
		for(int i = 0; i < 60; i++){
			System.out.print("=");
		}
		System.out.println();
		String[] words = quote.split(" ");
		int wordPos = 0;
		for(int i = 0; i < Math.ceil(quote.length()/56.0); i++){
			System.out.print("| ");
			int currentIndex = 0;
			while(currentIndex < 56){
				for(int x = wordPos; x < words.length; x++){
					if(currentIndex + words[x].length() < 56){
					System.out.print(words[x]);
					System.out.print(" ");
					currentIndex += words[x].length()+1;
					wordPos++;
					}else{
						break;
					}
				}
				for(int c = currentIndex; c <= 55; c++){
				System.out.print(" ");
				currentIndex ++;
				}
			}
			System.out.println(" |");
		}

		System.out.print("| ");
		for(int i = 0; i < 54-person.length(); i++){
			System.out.print(" ");
		}
		System.out.println("- "+person+" |");

		for(int i = 0; i < 60; i++){
			System.out.print("=");
		}
		System.out.println();
		System.out.println();
	}

}