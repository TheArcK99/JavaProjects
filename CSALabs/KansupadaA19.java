import java.util.*;

public class KansupadaA19
{
	public static void main(String args[])
	{
		Scanner numbers = new Scanner(System.in);
		String message = CodeWordHelper.getMessage();
		System.out.println("Enter an integer");
		int n=numbers.nextInt();
		String[][] array = toArray(message,n);
		display(array);
		code(array);
		display(array);
		code(array);
		display(array);

	}
	public static String[][] toArray(String m, int n)
	{
	double round = (m.length()/n)+1;
	int rowN2 = (int)round;
	int colN = n;

	String [][] data = new String[rowN2][colN];

	int pos = 0;
	for(int row = 0; row < data.length; row++){
		for(int col = 0; col < data[0].length; col++){
			if(pos <= m.length()-1){
			data[row][col] = m.substring(pos,pos+1);
			}else{
			data[row][col] = " ";
			}
			pos++;
		}
	}
	return data;
	}

	public static void display(String[][] arr)
	{
	for(int row = 0; row < arr.length; row++){
		for(int col = 0; col < arr[0].length; col++){
			System.out.print(arr[row][col]);
		}
		System.out.println();
	}


	}
	public static void code(String[][] arr)
	{
		System.out.println("\n--------------\nSwap\n\n");
		for(int row = 0; row < arr.length; row++){
			for(int col = 0; col < arr[0].length-1; col +=2){
			String temp = arr[row][col];
			arr[row][col] = arr[row][col+1];
			arr[row][col+1] = temp;
			}
		}
	}

}
