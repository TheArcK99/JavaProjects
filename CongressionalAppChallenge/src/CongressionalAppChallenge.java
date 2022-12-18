/**
 * @(#)CongressionalAppChallenge.java
 *
 * CongressionalAppChallenge application
 *
 * @author
 * @version 1.00 2022/10/18
 */


package net.codejava.swing;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import java.util.*;
import java.util.Arrays;
import java.io.File;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import java.io.File;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CongressionalAppChallenge extends PApplet {

	boolean overScan = false;
	boolean overWiki = false;
	boolean overAbout = false;
	boolean overSetting = false;
	boolean overExit = false;
	boolean overUpload = false;
	boolean overDownload = false;

	boolean press = false;
	boolean loaded = false;

	boolean overBlack = false;
	boolean overBlue = true;
	boolean overWhite = false;
	boolean overGrey = true;
	boolean overBlackText = true;
	boolean overMagenta = false;

	boolean onPage = false;

	int backGroundR = 30;
	int backGroundG = 37;
	int backGroundB = 126;

	int accentR = 224;
	int accentG = 224;
	int accentB = 224;

	int textR = 0;
	int textG = 0;
	int textB = 0;

	String filename;
	String text = "";
	String path = "";

	ArrayList<String> phrases = new ArrayList<String>();

	TestJFilePicker reader = new TestJFilePicker();
	File input;

    public static void main(String[] args)  {
    	ArrayList<String> phrases = new ArrayList<String>();
    	String[] processingArgs= {"CongressionalAppChallenge"};
    	CongressionalAppChallenge congressionalAppChallenge = new CongressionalAppChallenge();
    	PApplet.runSketch(processingArgs, congressionalAppChallenge);

    }

    public void settings(){
    	size(600,800);
    }

    public void draw(){
       background(backGroundR,backGroundG, backGroundB);
       baseMenu();
       if(!onPage){
       overBaseMenu();
       }
       	if(overScan){
       	scan();
       	press = false;
        }else if(overWiki){
        wiki();
        press = false;
        }else if(overAbout){
        about();
        press = false;
        }else if(overSetting){
        set();
        press = false;
        }else if(overExit){
        System.exit(0);
        }
    }

    public void baseMenu(){
    	PFont space = createFont("SpaceGrotesk-Regular.ttf", 32);
    	textFont(space);

    	PImage setting = loadImage(dataPath("gray.png"));
    	PImage exit = loadImage(dataPath("exit.png"));
    	PImage search = loadImage(dataPath("search.png"));
    	PImage book = loadImage(dataPath("book.png"));
    	PImage info = loadImage(dataPath("info.png"));

    	setting.resize(90,90);
    	exit.resize(80,80);
    	search.resize(50,50);
    	book.resize(50,50);
    	info.resize(50,50);

    	strokeWeight(0);
    	fill(224,224,224);
    	rect(100,200,400,480,25);
    	fill(153,186,221);
    	rect(150,225,300,125, 25);
    	fill(75,104,184);
    	rect(150,375,300,125, 25);
    	fill(153,186,221);
    	rect(150,525,300,125, 25);

    	image(setting, 10, 700);
    	fill(accentR,accentG,accentB);
    	rect(500,690,90,100, 25);
    	image(exit, 500, 700);

    	image(search, 175, 265);
    	image(book, 180, 410);
    	image(info, 170, 565);

		fill(textR, textG, textB);
    	textSize(70);
    	textAlign(CENTER);
    	text("Scan",320,310);
    	text("Wiki",310,460);
    	textSize(60);
		text("About",320,610);

		fill(accentR,accentG,accentB);
		textSize(105);
		text("Contract P.I", 295, 160);
		triangle(0,0,200,0,0,75);
		triangle(600,0,400,0,600,75);
    }

    public void about(){
    onPage = true;
	PImage arrow = loadImage(dataPath("backarrow.png"));
	PImage about = loadImage(dataPath("aboutInfo.png"));
	background(backGroundR,backGroundG, backGroundB);
	arrow.resize(90,90);
	fill(224,224,224);
    rect(100,200,400,480,25);
    fill(accentR,accentG,accentB);
    rect(500,690,90,90, 25);
    image(arrow,500, 690);

    fill(accentR,accentG,accentB);
	textSize(105);
	text("Contract P.I", 295, 160);
	triangle(0,0,200,0,0,75);
	triangle(600,0,400,0,600,75);

	rectMode(CENTER);
	image(about, 125,215);
	rectMode(CORNER);

    overAboutMenu();
    if(overExit){
    	onPage = false;
    	overAbout = false;
    	overExit = false;
    	press = false;
    }

    }

    public void wiki(){
	onPage = true;
	PImage arrow = loadImage(dataPath("backarrow.png"));
	PImage wiki = loadImage(dataPath("wikiInfo.png"));
	background(backGroundR,backGroundG, backGroundB);
	arrow.resize(90,90);
	fill(224,224,224);
    rect(100,200,400,480,25);
    fill(accentR,accentG,accentB);
    rect(500,690,90,90, 25);
    image(arrow,500, 690);

    fill(accentR,accentG,accentB);
	textSize(105);
	text("Contract P.I", 295, 160);
	triangle(0,0,200,0,0,75);
	triangle(600,0,400,0,600,75);

	rectMode(CENTER);
	image(wiki, 125,215);
	rectMode(CORNER);

	/*fill(255);
	textSize(50);
	text("More info in",300,725);
	text("MorereadMe.txt",300,775);*/

    overWikiMenu();
    if(overExit){
    	onPage = false;
    	overWiki = false;
    	overExit = false;
    	press = false;
    }

    }

    public void scan (){
	onPage = true;
	String filenameW = "words.txt";
	if(!loaded){
		try {
      		File myObjW = new File(dataPath(filenameW));
      		Scanner myReaderW = new Scanner(myObjW);
      	while (myReaderW.hasNextLine()) {
        	String data = myReaderW.nextLine();
        	phrases.add(data);
      	}
      		myReaderW.close();
    	} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    		}
    }
    loaded = true;

    PImage arrow = loadImage(dataPath("backarrow.png"));
    PImage upload = loadImage(dataPath("upload.png"));
	background(backGroundR,backGroundG, backGroundB);
	arrow.resize(90,90);
	upload.resize(70,70);

	fill(224,224,224);
    rect(100,200,400,480,25);
    fill(accentR,accentG,accentB);
    rect(500,690,90,90, 25);
    image(arrow,500, 690);
    fill(153,186,221);
    rect(150,375,300,125, 25);
  /*  rect(150,525,300,125, 25);
*/
    fill(textR, textG, textB);
    textSize(60);
    textAlign(CENTER);
    text("Upload",340,460);
    image(upload, 160, 405);
    /*textSize(55);
    text("Download",305,610);*/

    fill(accentR,accentG,accentB);
	textSize(105);
	text("Contract P.I", 295, 160);
	triangle(0,0,200,0,0,75);
	triangle(600,0,400,0,600,75);

	fill(textR, textG, textB);
	textSize(30);
	text("Press the upload button.", 300,250);
	text("Your text will be scanned,", 300,290);
	text("and a new file will be made.", 300,330);
	textSize(35);
	text("The file will deliver as a",300,570);
	textSize(40);
	text("output.txt",300,620);

    overScanMenu();
    if(overUpload){
    	filename = reader.pick();
    	path = filename.substring(0,filename.lastIndexOf("."));
    	overUpload = false;
    	press = false;
    	if(filename != null ){
			try {
      			File myObj = new File(filename);
      			Scanner myReader = new Scanner(myObj);
      		while (myReader.hasNextLine()) {
        		String data = myReader.nextLine();
        		text += "\n";
        		text += data;
        		System.out.println(data);
      		}
      			myReader.close();
    		} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
      		fill(textR, textG, textB);
      		textSize(55);
      		text("File Upload", 300,280);
    		text("Failed", 300,335);
    		}

			KMP_String_Matching j = new KMP_String_Matching();
    		for(int i = 0; i < phrases.size(); i++){
    		String arc = j.KMPSearch(phrases.get(i), text, path);
    		text = arc;
    		}
    		j.printText(text, path);
       		}
    }

    if(overExit){
    	overScan = false;
    	overExit = false;
    	press = false;
    	overUpload = false;
    	loaded = false;
    	onPage = false;
    }
    }

    public void set(){
    onPage = true;
	PImage arrow = loadImage(dataPath("backarrow.png"));
	background(backGroundR,backGroundG, backGroundB);
	arrow.resize(90,90);
	fill(224,224,224);
    rect(100,200,400,480,25);
    fill(accentR,accentG,accentB);
    rect(500,690,90,90, 25);
    image(arrow,500, 690);

	fill(accentR,accentG,accentB);
	textSize(105);
	text("Contract P.I", 295, 160);
	triangle(0,0,200,0,0,75);
	triangle(600,0,400,0,600,75);

	fill(textR, textG, textB);
	textSize(50);
	text("Background", 300,250);
	text("Color", 300,300);
	fill(0);
	rect(150, 275, 75, 50);
	fill(30, 37, 126);
	rect(375, 275, 75, 50);

	fill(textR, textG, textB);
	textSize(50);
	text("Accent", 300,400);
	text("Color", 300,450);
	strokeWeight(5);
	fill(255);
	rect(150, 425, 75, 50);
	fill(224,224,224);
	rect(375, 425, 75, 50);

	fill(textR, textG, textB);
	textSize(50);
	text("Text", 300,550);
	text("Color", 300,600);
	strokeWeight(5);
	fill(0);
	rect(150, 575, 75, 50);
	fill(139,0,139);
	rect(375, 575, 75, 50);


    overSetMenu();

    if(overBlack){
    backGroundR = 0;
	backGroundG = 0;
	backGroundB = 0;
    }
    if(overBlue){
    backGroundR = 30;
	backGroundG = 37;
	backGroundB = 126;
    }
    if(overWhite){
    accentR = 255;
	accentG = 255;
	accentB = 255;
    }
    if(overGrey){
    accentR = 224;
	accentG = 224;
	accentB = 224;
    }
    if(overBlackText){
    textR = 0;
    textG = 0;
    textB = 0;
    }
    if(overMagenta){
	textR = 139;
    textG = 0;
    textB = 139;
    }


    if(overExit){
    	overSetting = false;
    	overExit = false;
    	press = false;
    	onPage = false;

    }


    }

    public void mouseClicked(){
    	press = true;
    }

    public void overBaseMenu(){
    	if(!overScan || !overWiki || !overAbout || !overSetting){
    	if(mouseX >= 150 && mouseX <= 450 && mouseY >= 220 && mouseY <= 350 && press == true){
       	overScan = true;
        }else if(mouseX >= 150 && mouseX <= 450 && mouseY >= 370 && mouseY <= 500 && press == true){
        overWiki = true;
        }else if(mouseX >= 150 && mouseX <= 450 && mouseY >= 520 && mouseY <= 650 && press == true){
        overAbout = true;
        }else if(mouseX >= 10 && mouseX <= 100 && mouseY >= 700  && mouseY <= 770 && press == true){
        overSetting = true;
        }else if(mouseX >= 500 && mouseX <= 600 && mouseY >= 690 && mouseY <= 790 && press == true){
        overExit = true;
        }
    	}

    }

    public void overScanMenu(){
    	if(mouseX >= 500 && mouseX <= 600 && mouseY >= 690 && mouseY <= 790 && press == true){
        overExit = true;
        }
        if(mouseX >= 150 && mouseX <= 450 && mouseY >= 370 && mouseY <= 500 && press == true){
        overUpload = true;
        }
        if(mouseX >= 150 && mouseX <= 450 && mouseY >= 520 && mouseY <= 650 && press == true){
        overDownload = true;
        }
    }

    public void overWikiMenu(){
    	if(mouseX >= 500 && mouseX <= 600 && mouseY >= 690 && mouseY <= 790 && press == true){
        overExit = true;
        }

    }

    public void overAboutMenu(){
    	if(mouseX >= 500 && mouseX <= 600 && mouseY >= 690 && mouseY <= 790 && press == true){
        overExit = true;
        }

    }

    public void overSetMenu(){
    	if(mouseX >= 500 && mouseX <= 600 && mouseY >= 690 && mouseY <= 790 && press == true){
        overExit = true;
        }
        if(mouseX >= 150 && mouseX <= 225 && mouseY >= 275 && mouseY <= 325 && press == true){
        overBlack = true;
        overBlue = false;
        }
        if(mouseX >= 375 && mouseX <= 450 && mouseY >= 275 && mouseY <= 325 && press == true){
        overBlue = true;
        overBlack = false;
        }
        if(mouseX >= 150 && mouseX <= 225 && mouseY >= 425 && mouseY <= 475 && press == true){
        overWhite = true;
        overGrey = false;
        }
        if(mouseX >= 375 && mouseX <= 450 && mouseY >= 425 && mouseY <= 475 && press == true){
        overGrey = true;
        overWhite = false;
        }
        if(mouseX >= 150 && mouseX <= 225 && mouseY >= 575 && mouseY <= 625 && press == true){
        overBlackText = true;
		overMagenta = false;
        }
        if(mouseX >= 375 && mouseX <= 450 && mouseY >= 575 && mouseY <= 625 && press == true){
        overBlackText = false;
		overMagenta = true;
        }
    }


}





















class TestJFilePicker extends JFrame {

    public TestJFilePicker() {
        super("Test using JFilePicker");

        setLayout(new FlowLayout());

        // set up a file picker component
        JFilePicker filePicker = new JFilePicker("Pick a file", "Browse...");
        filePicker.setMode(JFilePicker.MODE_SAVE);
        filePicker.addFileTypeFilter(".jpg", "JPEG Images");
        filePicker.addFileTypeFilter(".mp4", "MPEG-4 Videos");

        // access JFileChooser class directly
        JFileChooser fileChooser = filePicker.getFileChooser();
        fileChooser.setCurrentDirectory(new File("D:/"));

        // add the component to the frame
        add(filePicker);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 100);
        setLocationRelativeTo(null);    // center on screen
    }

    public String pick() {
    	JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
    	System.out.println("Selected file: " + selectedFile.getAbsolutePath());
    	return selectedFile.getAbsolutePath();
		}
		return null;
    }

}

class FileTypeFilter extends FileFilter {

    private String extension;
    private String description;

    public FileTypeFilter(String extension, String description) {
        this.extension = extension;
        this.description = description;
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        return file.getName().toLowerCase().endsWith(extension);
    }

    public String getDescription() {
        return description + String.format(" (*%s)", extension);
    }
}

class JFilePicker extends JPanel {
    private String textFieldLabel;
    private String buttonLabel;

    private JLabel label;
    private JTextField textField;
    private JButton button;

    private JFileChooser fileChooser;

    private int mode;
    public static final int MODE_OPEN = 1;
    public static final int MODE_SAVE = 2;

    public JFilePicker(String textFieldLabel, String buttonLabel) {
        this.textFieldLabel = textFieldLabel;
        this.buttonLabel = buttonLabel;

        fileChooser = new JFileChooser();

        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // creates the GUI
        label = new JLabel(textFieldLabel);

        textField = new JTextField(30);
        button = new JButton(buttonLabel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        add(label);
        add(textField);
        add(button);

    }

    private void buttonActionPerformed(ActionEvent evt) {
        if (mode == MODE_OPEN) {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (mode == MODE_SAVE) {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    }

    public void addFileTypeFilter(String extension, String description) {
        FileTypeFilter filter = new FileTypeFilter(extension, description);
        fileChooser.addChoosableFileFilter(filter);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getSelectedFilePath() {
        return textField.getText();
    }

    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }
}

class KMP_String_Matching {
    public String KMPSearch(String pat, String txt, String path)
    {
        int M = pat.length();
        int N = txt.length();
        int[] indexes= new int[0];

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);

        int i = 0; // index for txt[]
        while ((N - i) >= (M - j)) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern "
                                + "at index " + (i - j));
                indexes = add(indexes, (i-j));
                j = lps[j - 1];
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return toString(indexes, txt, pat, path);
    }

    public static int[] add(int[] a, int c){
	int[] new2 = new int[a.length+1];
	for(int i = 0; i < a.length; i++){
		new2[i] = a[i];
	}
	new2[a.length] = c;
	return new2;
	}

	public static String toString(int[] a, String t, String p, String path){
		int h = 0;
		String result = "";
		for(int i = 0; i < a. length; i++){
			System.out.print(t.substring(h,a[i]));
			result += t.substring(h,a[i]);
			for(int g = 0; g < p.length(); g++){
			System.out.print("0");
			}
			result += "*";
			result += "*";
			result += "*";
			result += p.toUpperCase();
			result += "*";
			result += "*";
			result += "*";
			h = a[i]+p.length();
		}
		System.out.print(t.substring(h, t.length()));
		result += t.substring(h, t.length());
		System.out.println();
		return result;
	}

	public void printText(String result, String path){
		boolean uploaded = false;
		int i = 0;
		while(!uploaded){
		try{
			File r = new File(path+"-output"+"("+i+")"+".txt");
			if(r.createNewFile()){
				System.out.println("File created: " + r.getName());
				uploaded = true;
				break;
			}else {
				System.out.println("File already exists.");
			}
		} catch(IOException e){
			System.out.println("An error occured.");
			e.printStackTrace();
		}
		i++;
		}

		try{
			FileWriter myWriter = new FileWriter(path+"-output"+"("+i+")"+".txt");
			myWriter.write(result);
			myWriter.close();
			System.out.println("Successfully wrote to file.");
		}catch(IOException e){
			System.out.println("An error occured.");
			e.printStackTrace();
		}
	}

    void computeLPSArray(String pat, int M, int lps[])
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
}
