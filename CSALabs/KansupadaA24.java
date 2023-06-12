import java.util.*;

public class KansupadaA24
{

	public static void main(String args[])
	{
		Scanner tree = new Scanner(System.in);
		System.out.print("Enter a sentence:\t");
		String monkey = tree.nextLine();
		String[] vowels = new String[10];

		vowels[0] = "a";
		vowels[1] = "e";
		vowels[2] = "i";
		vowels[3] = "o";
		vowels[4] = "u";

		vowels[5] = "A";
		vowels[6] = "E";
		vowels[7] = "I";
		vowels[8] = "O";
		vowels[9] = "U";


		for(int i = 0; i < vowels.length; i++){
		String vowel = vowels[i];
		monkey = monkey.replaceAll(vowel, vowel+vowel+vowel);
		}


		String[] words = monkey.split(" ");
		for(int i = 0; i < words.length; i++){
			boolean isVowel = false;
			for(int j = 0; j < vowels.length; j++){
			if(words[i].substring(0,1).equals(vowels[j]))
				isVowel = true;
			}
			if(isVowel){
				words[i] += "wook";
			}else{
				words[i] = words[i].substring(1)+words[i].substring(0,1)+"eek";
			}
		}

		monkey = "";
		for(int i = 0; i < words.length; i++){
			monkey += words[i];
			monkey += " ";
		}

		monkey = monkey.toLowerCase();
		monkey = monkey.replaceAll("[^a-z ]","");

		System.out.print("The monkey says: ' " + monkey + "' ");
	}




}