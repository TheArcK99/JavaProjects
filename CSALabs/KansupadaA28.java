import java.util.*;
import java.net.*;
import java.io.*;
public class KansupadaA28
{


	public static void main(String[] args)
	{
		ArrayList<String> linesOfText = new ArrayList<String>();
		linesOfText=readText(linesOfText);
		linesOfText=formatText(linesOfText);
		linesOfText = sortText(linesOfText);
		System.out.print(linesOfText);

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

	public static ArrayList<String> sortText(ArrayList<String> text){
		ArrayList<String> second = new ArrayList<String>();
		for(int i = 0; i < text.size(); i++){
			String[] temp = text.get(i).split(" ");
			for(int r = 0; r < temp.length; r++){
			second.add(temp[r].toLowerCase());
			}
		}
		text = second;
		int n = text.size();
        for (int i = 1; i < n; ++i) {
            String key = text.get(i);
            int j = i - 1;
            while (j >= 0 && text.get(j).compareTo(key) > 0) {
                text.set(j+1,text.get(j));
                j = j - 1;
            }
            text.set(j + 1, key);
        }

        for(int i = 0; i < text.size(); i++){
        	if(text.get(i).equals("")){
        		text.remove(i);
        		i--;
        	}
        }

        return text;
	}

}