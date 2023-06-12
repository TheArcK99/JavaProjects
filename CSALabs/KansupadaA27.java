import java.util.*;
import java.net.*;
import java.io.*;
public class KansupadaA27
{


	public static void main(String[] args)
	{
		ArrayList<String> linesOfText = new ArrayList<String>();
		linesOfText=readText(linesOfText);
		linesOfText=formatText(linesOfText);
		linesOfText = removeSpaceCaps(linesOfText);
		linesOfText = ind(linesOfText);
		countWords(linesOfText);
		findWords(linesOfText);

	}
	/*Reads a .txt file from the internet and stores each line as
	 *a String in an ArrayList.
	 *If there is a problem reading the file from the internet:
	 *	1.) comment out the line with the first "String website"
	 *	2.) take the comments off of the line after that
	 *  3.) have the pride.txt file in the same folder as the .java file
	*/
	public static ArrayList<String> readText(ArrayList<String> text)
	{
		String website = "http://archive.org/stream/TheEpicofGilgamesh_201606/eog_djvu.txt";
		//String website = "";
		try{URL url = new URL(website);Scanner s = new Scanner(url.openStream());while(s.hasNext())text.add(s.nextLine());}
		catch(Exception e){try{Scanner s = new Scanner(new File("pride.txt"));while(s.hasNext())text.add(s.nextLine());}catch(Exception ex){}}
		return text;
	}
	/*Removes the extra html formatting associated with the file.
	 *Removes everything except for letters and spaces from the text.
	*/
	public static ArrayList<String> formatText(ArrayList<String> text)
	{	for(int line =0;line<text.size();line++)
		{	String s = text.get(line);
			if(s.contains("The Epic Of Gilgamesh")&&!s.contains("<"))
			{	for(int a=line-1;a>=0;a--)text.remove(a);break;}}
		for(int line =0;line<text.size();line++)
		{	String s = text.get(line);
			text.set(line,text.get(line).replaceAll("[^a-zA-Z ]",""));
			if(s.contains("</pre>"))for(int a=line-5;a<text.size();text.remove(a));}
		return text;
	}

	public static ArrayList<String> ind(ArrayList<String> text){
		ArrayList<String> second = new ArrayList<String>();
		for(int i = 0; i < text.size(); i++){
			String[] temp = text.get(i).split(" ");
			for(int r = 0; r < temp.length; r++){
			second.add(temp[r].toLowerCase());
			}
		}
		return second;
	}

	public static ArrayList<String> removeSpaceCaps(ArrayList<String> text){
		for(int i = 0; i < text.size(); i++){
			if(text.get(i).equals(" ")){
				text.remove(i);
				i--;
			}
			text.get(i).toLowerCase();

		}
		return text;

		}

	public static void countWords(ArrayList<String> text){
		double totLet = 0;
		for(int i = 0; i < text.size(); i++){
			totLet += text.get(i).length();
		}

		int uniqueWords = 0;
		ArrayList<String> uniqueW = new ArrayList<String>();
		for(int i = 0; i < text.size()-1; i++){
			boolean isU = true;
			for(int x = i+1 ; x < text.size(); x++){
				if(text.get(i).equals(text.get(x))){
					isU = false;
				}
			}
		if(isU){
			uniqueWords ++;
			uniqueW.add(text.get(i));
		}
	}
	ArrayList<Integer> bruh = new ArrayList<Integer>();
	ArrayList<Integer> bruh2 = new ArrayList<Integer>();
	ArrayList<String> bruhW = new ArrayList<String>();
	String[] arrW = new String[15];
	int cIndex = 0;
	for(int i = 0; i < uniqueW.size(); i++){
		int num = 0;
		for(int x = 0; x < text.size(); x++){
			if(text.get(x).equals(uniqueW.get(i))){
				num++;
			}

	}
	bruh.add(num);
	}

	for(int i = 0; i < 15; i++){
	int max = bruh.get(0);
	int pos = 0;
	for(int j = 1; j < bruh.size(); j++){
		if(bruh.get(j)>max){
			max = bruh.get(j);
			pos = j;
		}
	}
	bruh2.add(max);
	bruhW.add(uniqueW.get(pos));
	bruh.remove(pos);
	uniqueW.remove(pos);
	}




	System.out.println("This text: ");
	System.out.println("Contains "+(text.size()-1)+ " words and "+uniqueWords+" unique words.");
	System.out.printf("Each word has an average word length of %.2f letters.", totLet/(text.size()-1));
	System.out.println();
	System.out.println("The top 15 more common words are: ");
	for(int i = 0; i < 15; i++){
		System.out.print(bruhW.get(i)+" : ");
		System.out.println(bruh2.get(i));
	}
	}

	public static void findWords(ArrayList<String> text){
		Scanner e = new Scanner(System.in);
		System.out.println();
		System.out.print("Which word would you like to find: ");
		String t = e.nextLine();
		int x = 0;
		for(int i = 0; i < text.size(); i++){
			if(t.equals(text.get(i))){
				x++;
			}
		}
		System.out.println();
		System.out.println("The word \""+t+"\" was found exactly "+x+" times!");
	}

}
