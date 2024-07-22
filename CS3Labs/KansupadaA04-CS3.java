import java.util.*;
import java.util.regex.*;
import java.io.*;
public class KansupadaA04
{
	public static void main(String[] args)throws IOException
	{

		Scanner keyboard = new Scanner(System.in);
		Scanner s = new Scanner(new File("calculations.txt"));
		while(s.hasNext())
		{

			System.out.println(postFix(s.nextLine()));
		}
	}
	public static String postFix(String text)
	{
		String result = "";
		Scanner line = new Scanner(text);
		Stack<Character> st = new Stack<Character>();
		st.push('(');


		while(line.hasNext())
		{
			String token = line.next();

			for(int i = 0; i < token.length(); i++) {

				if (token.substring(i,i+1).matches("\\d")) {
					int x = 1;
					int tempi = i;
					result += token.substring(i, i + 1);
					while(tempi+x < token.length() && (token.substring(tempi+x,tempi+x+1).matches("\\d"))){
						result += token.substring(tempi+x, tempi + 1+x);
						x++;
						i++;
					}
					result += " ";

				}

				if (token.substring(i,i+1).matches("[+/*^-]")){
					String oppie = token.substring(i, i+1);
					Character oppie2 = oppie.charAt(0);
					while (!st.peek().equals('(') && ((precedence(st.peek()) > precedence(oppie2)) || ((precedence(st.peek()) == precedence(oppie2) && (!oppie.equals("^")))))) {
						result += st.pop() + " ";
					}
					st.push(oppie2);
				}

				if (token.substring(i, i+1).equals("(")) {
					st.push('(');
				}

				if (token.substring(i, i+1).equals(")")) {
					while (!st.peek().equals('(')) {
						result += st.pop()+ " ";
					}
					if (st.peek().equals('(')) {
						st.pop();
					}
					//if (!st.isEmpty() && !st.peek().equals('(')) {
					//	result += st.pop();
					//}
				}


			}
		}
		while(!st.peek().equals('(')){
			result += st.pop()+ " ";
		}

		return result;
	}
	public static int precedence(char c)
	{
		if(c == '+' || c == '-') 	return 1;
		if(c == '*' || c == '/') 	return 2;
		if(c == '^')				return 3;
		return 0;
	}
}