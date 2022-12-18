import java.util.*;

public class BookSorting{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		List<Book> l = new ArrayList<>();
		while(sc.hasNextLine()){
			l.add(new Book(sc.nextLine()));
		}
		Collections.sort(l);
		l.forEach(System.out::println);
	}
}



class Book implements Comparable<Book>{
	private String title, author, genre;
	public Book(String line){
		String[] arr = line.split("\\*");
		title = arr[0];
		author = arr[1];
		genre = arr[2];
		}
		public int compareTo(Book a){
			int compare = author.compareTo(a.author);
			if(compare == 0){
				compare = genre.compareTo(a.genre);
			}
			return compare;
		}
		public String toString(){
			return title+"*"+author+"*"+genre;
		}
	}

