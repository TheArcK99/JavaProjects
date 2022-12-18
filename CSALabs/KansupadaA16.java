import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
public class KansupadaA16 extends Canvas
{
	TestTaker[][] grid;

	JFrame frame = new JFrame("Lab 16");
	static Graphics g;
	public void windowSetup()
	{
		Dimension size = new Dimension(1200,800);
		frame.setPreferredSize(size);
		frame.setMinimumSize(size);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
	}
	public void draw()
	{
		BufferStrategy buffer = this.getBufferStrategy();
		if(buffer==null){this.createBufferStrategy(3);return;}
		g = buffer.getDrawGraphics();
		drawDesks();
		g.dispose();
		buffer.show();
	}
	public void render(Graphics g)
	{

	}
	/*Constructor for this class
	 *Change the name of the constructor to match your file name*/
	public KansupadaA16()
	{
		fillDesks();
		windowSetup();
		while(true)
			draw();
	}
	/*Only change the constructor invocation to match your file name*/
	public static void main(String[] args)
	{
		new KansupadaA16();
	}
	/*Display the seating chart */
	public void drawDesks()
	{
		/*Changes the Font to 30 point Arial*/
		g.setFont(new Font("Courier New",0,30));
		/*Displays a single rectangle on the screen with the following parameters
		 *(x start, y start, width, height)*/
		for(int r =0;r<grid.length;r++)
			for(int c=0;c<grid[0].length;c++)
		 	{
		 		g.setColor(Color.black);
		 		g.drawRect(c*200+50,r*100+50,150,75);
				/*Displays a String on the screen with the following parameters
				 *(String to display, x value, y value)*/
				 if(grid[r][c]!=null)
				 {
				 	g.drawString(""+grid[r][c].getNumber()+" :"+grid[r][c].getID(),c*200+58,r*100+85);
						g.drawString(""+grid[r][c].getAvg(),c*200+110,r*100+120);
				 }
		 	}
	}
	 /*Populate the seating chart with TestTaker objects
	 *Each TestTaker is assigned a number, beginning at 1
	 *Post-condition : Every index of the 2D array will have a TestTaker object with a unique number*/
	public void fillDesks()
	{
		grid = new TestTaker[6][5];
		int number = 1;
		for(int i = 0; i < grid.length*grid[0].length; i++){
			boolean placed = false;
			while(!placed){
			int r = (int)((Math.random()*6));
			int c = (int)((Math.random()*5));
			if(grid[r][c] == null){
				grid[r][c] = new TestTaker(number);
				placed = true;
			}
			}
			number++;
		}
		/*for(int r = 0; r < grid.length; r++)
		{
			for(int c = 0; c < grid[0].length; c++)
			{
			grid[r][c] = new TestTaker(number);
			number++;
			}
		}*/
	}
}
/* You will have to change the Student class name below to your Student class name (Ex: SmithStudent)*/
class TestTaker extends Student
{
	private int number;
	public TestTaker(int num)
	{
		super();
		number=num;
	}
	public int getNumber()
	{
		return number;
	}
}
/*You will need to paste your Student class here
 *and name it appropriately (Student).
 *The class may need to be altered to be able to use
 *the getAvg() and getID() methods.*/
class Student{
	private int id;
	private int t1;
	private int t2;
	private int t3;
	private int avg;

	public Student()
	{
		id = (int)(Math.random()*9000)+1000;
		t1 = (int)(Math.random()*41)+60;
		t2 = (int)(Math.random()*41)+60;
		t3 = (int)(Math.random()*41)+60;
		avg =(int)Math.round((t1+t2+t3)/3.0);
	}

	public int getAvg()
	{
		return avg;
	}

	public int getID()
	{
		return id;
	}

	public void ec()
	{
		t3 = t3 + (int)(Math.random()*5)+1;
		avg = (t1+t2+t3)/3;
	}

	public void printS(){System.out.println(id+" : Test 1: "+t1+"  Test 2: "+t2+"  Test 3: "+t3+"  Avg: "+avg);}
}

