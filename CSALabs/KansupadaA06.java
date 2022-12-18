public class KansupadaA06 {
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
	private char letter;

	public Student(){
		id = (int)(Math.random()*9000)+1000;
		t1 = (int)(Math.random()*41)+60;
		t2 = (int)(Math.random()*41)+60;
		t3 = (int)(Math.random()*41)+60;
		avg =(int)Math.round((t1+t2+t3)/3.0);
		if(avg >= 90){letter='A';}
		else if(avg >= 80 && avg <= 89){letter='B';}
		else if(avg >= 75 && avg <= 79){letter='C';}
		else if(avg >= 70 && avg <= 74){letter='D';}
		else if(avg < 70){letter='F';}
	}

	public void ec(){
		t3 = t3 + (int)(Math.random()*5)+1;
		avg = (t1+t2+t3)/3;
		if(avg > 90){letter='A';}
		else if(avg >= 80 && avg <= 89){letter='B';}
		else if(avg >= 75 && avg <= 79){letter='C';}
		else if(avg >= 70 && avg <= 74){letter='D';}
		else if(avg < 70){letter='F';}
	}

	public void printS(){System.out.println(id+" : Test 1: "+t1+"  Test 2: "+t2+"  Test 3: "+t3+"  Avg: "+avg+"\t"+letter);}
}