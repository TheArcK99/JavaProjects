//I did not realize that top of your program meant the actual code. There is also a paragraph on my
//introductory screen. This simulation was based off voltaic/galvanic cells, which is a concept I learned in
//my AP Chemistry class. Voltaic cells form the basis of modern batteries, and rely on the movement
//of electrons as they are knocked off ions and solids, or as chemists call it, redox reactions.
//This model of a battery involves two beakers with water, floating ions, and two metals sticks connected with wires.
//In the model depicted, the two gray sticks with a bunch of floating ions are metal sticks with Ag and Ni respectively.
//Whenever you spawn a A- ion in the left beaker, it collides and accumulates in the metal stick(cathode). As this Ag- ion turns
//into a Ag solid with no charge, that extra electron goes through the wire and completes a circuit(shown by the lightbuulb).
//This electron then goes into the other metal stick(anode) and turns the solid Ni into an Ni- ion, which floats freely in the
//solutuion. There is also a salt bridge that connects the two beakers and maintains ionic neutrality by adding ions to each solution.
//Overall this reaction is "thermodynamically favorable," meaning that it will natrually happen without the addition of energy.
//A miniscule version of this reaction happens every day in millions of batteries across the world, and when batteries die, it is
//because there are no ions there to drive the reaction. However, rechargable batteries can replenish this source, which is how
//electronics such as laptops and phones are able to be recharged at night.

import java.awt.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.applet.*;
import java.applet.Applet;
import java.awt.event.*;

public class KansupadaA99 extends Panel
{
	private Image offScreenImage;private Dimension offScreenSize;private Graphics offScreenGraphics;final boolean RESIZEABLE = false;
	int width = 1280-10;int height = 1024-10;Image virtualMem;Graphics2D gBuffer;

	public void resizeWindow(){if(RESIZEABLE){if(getHeight() != height + 10 || getWidth() != width + 10){height = getHeight() - 10;width = getWidth() - 10;virtualMem = createImage(width+20,height+20);
		gBuffer = (Graphics2D)virtualMem.getGraphics();gBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);}}else setSize(width+10,height+10);}
	public void update(Graphics g)
	{	Dimension d = size();if((offScreenImage == null) || (d.width != offScreenSize.width) || (d.height != offScreenSize.height)) {offScreenImage = createImage(d.width, d.height);
		 offScreenSize = d; offScreenGraphics = offScreenImage.getGraphics();}offScreenGraphics.clearRect(0, 0, d.width, d.height);paint(offScreenGraphics);g.drawImage(offScreenImage, 0, 0, null);}
	public static void main(String[] args)
	{
  		Frame f = new Frame();f.addWindowListener(new java.awt.event.WindowAdapter() {public void windowClosing(java.awt.event.WindowEvent e) {System.exit(0);};});
		KansupadaA99 window = new KansupadaA99();
  		window.setSize(1065,800);f.add(window);f.pack();window.init();
  		f.setSize(1065,800);
		f.show();
	}


	//Atom f = new Atom(10,10,70,"Ag",-2);
	DisplayScreen a = new DisplayScreen(1065,800);
	static Keyboard kevin = new Keyboard();
	static Mouse momo = new Mouse();
	double volts = 0;
	boolean pastPage = false;
	boolean circuit1 = false;
	boolean circuit2 = false;
	boolean ionB = false;
	private ArrayList<Atom> ag = silverAtoms(200,15,105,170,325,615);
	private ArrayList<Atom> ni = nickelAtoms(200,15,105+765,170+765,325,615);
	private ArrayList<Atom> agion = new ArrayList<Atom>();
	private ArrayList<Atom> niion = new ArrayList<Atom>();
	//private ArrayList<Atom> salts = createSalt(400,210);
	private ArrayList<Electron> electrons = new ArrayList<Electron>();

	public void init()
	{
	addKeyListener(kevin);
	this.addMouseListener(momo);
	requestFocus();
	}


	public void paint(Graphics g)
	{
		//System.out.print(ionB);
		a.createPage(g, pastPage, circuit1, circuit2, volts);
		if(momo.isPopupTrigger()){
		pastPage = true;
		}
		if(momo.createIon()){
			ionB = true;
		}
		if(pastPage){
		for(int e = 0 ; e < ag.size(); e++){
			ag.get(e).draw(g, Color.white);
			ag.get(e).walls(100,165,315,610);
			ag.get(e).move();
			ni.get(e).draw(g, Color.orange);
			ni.get(e).walls(100+765,165+765,315,610);
			ni.get(e).move();
		}

		if(ionB){
			agion.add(new Atom(momo.rx(),momo.ry(),15,"Ag",-1));
			ionB = false;
		}

		for(int e = 0 ; e < agion.size(); e++){
			agion.get(e).draw(g,Color.magenta);
			agion.get(e).move();
			agion.get(e).walls(75,360,400,745);
			if(agion.get(e).within(105,165,315,610)){
				agion.remove(e);
				electrons.add(new Electron(130,350));
				e--;
			}
		}

		//System.out.print(circuit);

		for(int e = 0 ; e < electrons.size(); e++){
			electrons.get(e).draw(g, Color.yellow);
			electrons.get(e).electronMove();
			if(electrons.get(e).done()){
				niion.add(new Atom(820,450, 15, "Ni",-1));
				electrons.remove(e);
				e--;
			}
		}

		for(int e = 0 ; e < niion.size(); e++){
			niion.get(e).draw(g,Color.red);
			niion.get(e).move();
			niion.get(e).walls(395,680,745,850,620,960);
			//niion.get(e).walls(680,850,395,745);
		}

		int x = 0;
		for(int e = 0 ; e < electrons.size(); e++){
			if(electrons.get(e).withinC1()){
				x++;
			}
		}

		if(x>0){
			circuit1 = true;
		}else{
			circuit1 = false;
		}

		int y = 0;
		for(int e = 0 ; e < electrons.size(); e++){
			if(electrons.get(e).withinC2()){
				y++;
			}
		}

		if(y>0){
			circuit2 = true;
		}else{
			circuit2 = false;
		}

		for(int e = 0 ; e < electrons.size(); e++){
			if(electrons.get(e).getCharge()){
			  volts += Math.random();
			}
		}

		a.drawConc(g,agion.size(),niion.size());


		//for(int e = 0 ; e < salts.size(); e++){
			//salts.get(e).draw(g,Color.red);
		//}

		}

		try{Thread.sleep(20);} catch(Exception e){}
		repaint();
	}

	public ArrayList<Atom> silverAtoms(int x, int size, int x1,int x2, int y1, int y2){
		ArrayList<Atom> bob = new ArrayList<Atom>();
		for(int i = 0; i < x; i++){
			int xA = (int)((Math.random()*(x2-x1+1))+x1);
			int yA = (int)((Math.random()*(y2-y1+1))+y1);
			bob.add(new Atom(xA,yA,size,"Ag",0));
		}
		return bob;
	}

	public ArrayList<Atom> nickelAtoms(int x, int size, int x1,int x2, int y1, int y2){
		ArrayList<Atom> bob = new ArrayList<Atom>();
		for(int i = 0; i < x; i++){
			int xA = (int)((Math.random()*(x2-x1+1))+x1);
			int yA = (int)((Math.random()*(y2-y1+1))+y1);
			bob.add(new Atom(xA,yA,size,"Ni",0));
		}
		return bob;
	}

	public ArrayList<Atom> createSalt(int x, int y){
		ArrayList<Atom> bob = new ArrayList<Atom>();
		bob.add(new Atom(x,y,15,"Na",1));
		bob.add(new Atom(x+12,y,30,"Cl",-1));
		return bob;
	}

}
abstract class Particle
{
	private int x, y;
	private double yvel,xvel;
	public Particle(int xpos, int ypos)
	{
		x = xpos;
		y = ypos;
		if(Math.random() < 0.5){
			yvel = -1;
		}else{
			yvel = 1;
		}
		if(Math.random() < 0.5){
			xvel = -1;
		}else{
			xvel = 1;
		}

	}
	public abstract void draw(Graphics g, Color c);

	public void move()
	{
		x += xvel;
		y += yvel;
	}

	public void move(int a, int b)
	{
		x += a;
		y += b;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void cVelX(){
		xvel *= -1;
	}
	public void cVelY(){
		yvel *= -1;
	}

}

class Electron extends Atom
{
	private int xvelo;
	private int yvelo;

	public Electron(int x, int y){
		super(x, y, 10,"e",-1);
		xvelo = 0;
		yvelo = 0;
	}

	public void electronMove(){
		if(super.getY()>50){
			yvelo = -1;
		}else{
			yvelo = 0;
		}
		if(super.getX()<895 && super.getY()<51){
			xvelo = 1;
		}else{
			xvelo = 0;
		}
		if(super.getY() < 350 && super.getX() >=895){
			yvelo = 1;
		}
		super.move(xvelo,yvelo);
	}

	public boolean withinC1(){
		if(super.getX() >= 450 && super.getX() <= 500){
			if(super.getY() >= 25 && super.getY() <= 75){
			return true;
		}
		}
		return false;
	}

	public boolean withinC2(){
		if(super.getX() >= 600 && super.getX() <= 650){
			if(super.getY() >= 25 && super.getY() <= 75){
			return true;
		}
		}
		return false;
	}

	public boolean getCharge(){
		if(super.getY()<135 && super.getY()>133){
			return true;
		}
		return false;
	}

	public boolean done(){
		if(super.getY() >= 350 && super.getX() >= 895){
			return true;
		}
		return false;
	}
}

class Atom extends Particle
{
	private int size;
	private String letters;
	private int charge;
	public Atom(int x, int y, int s, String name, int c){
		super(x,y);
		size = s;
		letters = name;
		charge = c;
	}
	public void draw(Graphics g, Color c){
		int x = super.getX();
		int y = super.getY();
		g.setColor(c);
		g.fillOval(x,y,size,size);
		int fSize = (int)(size*(2.0/5));
		int osX = (int)(size*(2.0/11));
		int osY = (int)(size*(2.0/3.2));
		g.setColor(Color.black);
		g.setFont(new Font("SansSerif", Font.PLAIN, fSize));
		g.drawString(letters,x+osX,y+osY);
		int fSize2 = (int)(size*(2.0/8));
		int osX2 = (int)(size*(2.0/3.5));
		int osY2 = (int)(size*(2.0/5.3));
		g.setColor(Color.black);
		g.setFont(new Font("SansSerif", Font.PLAIN, fSize2));
		String s = "";
		s+=charge;
		g.setColor(Color.black);
		g.drawString(s,x+osX2,y+osY2);
	}

	public void walls(int a, int b, int c, int d){
		if(super.getX() < a){
			super.cVelX();
		}
		if(super.getX() > b){
			super.cVelX();
		}
		if(super.getY() < c){
		 	super.cVelY();
		}
		if(super.getY() > d){
			super.cVelY();
		}
	}

	public void walls(int a, int b, int c, int d, int e, int f){
		if(super.getY() < a){
			super.cVelY();
		}
		if(super.getY() > c){
			super.cVelY();
		}
		if(super.getX() < b){
			super.cVelX();
		}
		if(super.getX() > d && super.getY() < e-10){
			super.cVelX();
		}
		if(super.getX() > d && super.getY() > e-10){
			super.cVelY();
		}
		if(super.getX() > f && super.getY() > e){
			super.cVelX();
		}

	}

	public boolean within(int a, int b, int c, int d){
		if(super.getX() < a){
			return false;
		}
		if(super.getX() > b){
			return false;
		}
		if(super.getY() < c){
		 	return false;
		}
		if(super.getY() > d){
			return false;
		}
		return true;
	}

}


class DisplayScreen
{
	private int width, height, charge1, charge2;
	private String chem1;
	private String chem2;
	public DisplayScreen(int w, int h){
		width = w;
		height = h;
	}

	public void createPage(Graphics g, boolean b, boolean c, boolean d, double x){
		if(!b){
		g.setColor(Color.lightGray);
		g.fillRect(0,0,width,height);
		drawBase(g);
		drawText(g);
		}else{
		g.setColor(Color.lightGray);
		g.fillRect(0,0,width,height);
		if(c){
		g.setColor(Color.yellow);
		}else{
		g.setColor(Color.white);
		}
		g.fillArc(450,59,50,100,180,360);
		if(d){
		g.setColor(Color.yellow);
		}else{
		g.setColor(Color.white);
		}
		g.fillArc(600,59,50,100,180,360);
		drawExpirement(g, x);
		drawInstruct(g);


		//ArrayList<Atom> agIons = new
	}

	}


	public void drawBase(Graphics g){
		g.setFont(new Font("SansSerif", Font.PLAIN, 60));
		g.setColor(Color.black);
		g.drawString("Electrochem Voltaic Cell Simulation",50, 55);
		g.setFont(new Font("SansSerif", Font.PLAIN, 40));
		g.drawString("By Aarav Kansupada - Period 4 AP CSA",150, 105);
		g.setColor(Color.red);
		g.fillRect(200,150,570,300);
	}

	public void drawConc(Graphics g, int con, int con2){
		g.setFont(new Font("MONOSPACED", Font.BOLD, 30));
		g.setColor(Color.gray);
		g.fillRect(15,195,115,60);
		g.setColor(Color.black);
		double i = con/1.37;
		i = (double) Math.round(i * 100.0) / 100;
		g.drawString(i+"M",20,230);

		g.setFont(new Font("MONOSPACED", Font.BOLD, 30));
		g.setColor(Color.gray);
		g.fillRect(905,195,115,60);
		g.setColor(Color.black);
		double e = con2/1.62;
		e = (double) Math.round(e * 100.0) / 100;
		g.drawString(e+"M",910,230);

	}


	public void drawText(Graphics g){
		g.setColor(Color.black);
		g.setFont(new Font("SansSerif", Font.PLAIN, 150));
		g.drawString("BEGIN",250, 350);
		g.setFont(new Font("SansSerif", Font.PLAIN, 30));
		g.drawString("This project incorporates processes taught in my AP Chem class. This is a",10, 500);
		g.drawString("voltaic cell, which uses a redox reaction to create a electrical current. As ",10, 540);
		g.drawString("ions in one beaker lose electrons turned solid, these electrons are moved",10, 580);
		g.drawString("into a seperate beaker where they turn solids into ions. The simulation shows  ",10, 620);
		g.drawString("the voltage of the electrical current along with the molarity(concentration)",10, 660);
		g.drawString("of each of the two solutions. Click on the left most solution to create an ion",10, 700);
		g.drawString("and start the reaction. Please proceed to the next page and watch this process.",10, 740);
	}

	public void drawExpirement(Graphics g, double i){
		g.setColor(new Color(220,240,239));
		g.fillRect(75,300,300,500);
		g.fillRect(675,300,300,500);
		g.setColor(Color.cyan);
		g.fillRect(75,400,300,400);
		g.fillRect(675,400,300,400);
		g.setColor(Color.gray);
		g.fillRect(100,320,80,305);
		g.fillRect(865,320,80,305);
		g.setFont(new Font("MONOSPACED", Font.BOLD, 30));
		g.fillRect(15,80,115,60);
		g.setColor(Color.red);
		i = (double) Math.round(i * 100) / 100;
		g.drawString(i+"V",20,120);
		g.setColor(Color.gray);
		g.fillRect(130,50,10,300);
		g.fillRect(895,50,10,300);
		g.fillRect(130,50,770,10);
		g.drawLine(300,450,300,200);
		g.drawLine(350,450,350,250);
		g.drawLine(300,200,750,200);
		g.drawLine(350,250,700,250);
		g.drawLine(750,200,750,450);
		g.drawLine(700,250,700,450);
		g.fillRect(450,25,50,50);
		g.fillRect(600,25,50,50);
		g.setColor(Color.white);
		g.fillArc(370,237,90,25,0,180);
		g.fillArc(495,237,90,25,0,180);
		g.fillArc(610,237,60,25,0,180);
	}

	public void drawInstruct(Graphics g){
		g.setColor(Color.yellow);
		int[] xP = {400,525,650};
		int[] yP = {500,300,500};
		g.fillPolygon(xP,yP,3);
		g.setColor(Color.black);
		g.fillOval(505,455,40,40);
		g.fillOval(505,340,40,100);
		g.fillRect(518,500,15,300);
		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
		g.drawString("Click here to",380,730);
		g.drawString("spawn Ag Ions",380,750);
		g.setFont(new Font("SansSerif", Font.PLAIN, 30));
		//g.drawString("Watch the Electrons flow",30,200);
		g.setFont(new Font("SansSerif", Font.PLAIN, 60));
		//g.drawString("Chemistry",150,120);
		//g.drawString("AP Labs",665,120);
		g.setColor(Color.black);
		g.drawLine(390,600,460,710);
		g.drawLine(390,600,390,620);
		g.drawLine(390,600,410,605);
		g.drawLine(1050-390,600,1050-460,710);
		g.drawLine(1050-390,600,1050-390,620);
		g.drawLine(1050-390,600,1050-410,605);
		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
		g.drawString("Ni Ions will",550,730);
		g.drawString("appear here",550,750);
		g.drawString("Watch the electrons",280,170);
		g.drawString("flow through the wire",280,190);
		g.drawLine(390-190,600-530,460-190,710-530);
		g.drawLine(390-190,600-530,390-190,620-530);
		g.drawLine(390-190,600-530,410-190,605-530);
	}

}


class Mouse extends MouseAdapter
{

	private int x;
	private int y;
	private int dubeX;
	private int dubeY;

	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		x = mx;
		y = my;

		System.out.println(mx + " " + my);
	}

	public void mouseReleased(MouseEvent e)
	{

	}

	public boolean isPopupTrigger(){
		if(x >= 200 && x <= 770){
			if(y >= 159 && y <= 450){
				return true;
			}
		}
		return false;
	}

	public boolean createIon(){
		if(x >= 75 && x <= 375){
			if(y >= 400 && y <= 800){
				dubeX = x;
				dubeY = y;
				x = 0;
				y = 0;
				return true;
			}
		}
		return false;
	}

	public int rx(){
		return dubeX;
	}

	public int ry(){
		return dubeY;
	}


}





