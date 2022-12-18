public class KansupadaA05 {
    public static void main(String args[]) {
      Student one = new Student();
      Student two = new Student();
      Student three = new Student();

      one.printS();
      two.printS();
      three.printS();
      System.out.println();
      System.out.println("Grades after extra credit:");
      one.ec();
      two.ec();
      three.ec();
      one.printS();
      two.printS();
      three.printS();
    }
}

class Student{
	private int id;
	private int t1;
	private int t2;
	private int t3;
	private int avg;

	public Student(){
		id = (int)(Math.random()*9000)+1000;
		t1 = (int)(Math.random()*41)+60;
		t2 = (int)(Math.random()*41)+60;
		t3 = (int)(Math.random()*41)+60;
		avg =(int)Math.round((t1+t2+t3)/3.0);
	}

	public void ec(){
		t3 = t3 + (int)(Math.random()*5)+1;
		avg = (t1+t2+t3)/3;
	}

	public void printS(){System.out.println(id+" : Test 1: "+t1+"  Test 2: "+t2+"  Test 3: "+t3+"  Avg: "+avg);}
}