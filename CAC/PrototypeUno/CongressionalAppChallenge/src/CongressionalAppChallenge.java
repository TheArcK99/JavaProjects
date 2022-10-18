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
import java.util.*;
import java.util.Arrays;
import java.io.File;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import java.io.File;

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

    public static void main(String[] args) {
    	String[] processingArgs= {"CongressionalAppChallenge"};
    	CongressionalAppChallenge congressionalAppChallenge = new CongressionalAppChallenge();
    	PApplet.runSketch(processingArgs, congressionalAppChallenge);
    	TestJFilePicker h = new TestJFilePicker();
    	h.pick();
    }

    public void settings(){
    	size(500,500);

    }

    public void draw(){
    	ellipse(mouseX, mouseY, 50, 50);
    }

    public void mousePressed(){
    	background(64);
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

    public void pick() {
    	JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
    	System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		}
        /*SwingUtilities.i
         *nvokeLater(new Runnable() {
            @Override
            public void run() {
                new TestJFilePicker().setVisible(true);
            }
        });*/
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



class KMPalg {
    public static void run() {
    		Scanner e = new Scanner(System.in);
    		String txt = e.nextLine();
        	String pat = "bill";
        	new KMP_String_Matching().KMPSearch(pat, txt);
    	}
}

class KMP_String_Matching {
    void KMPSearch(String pat, String txt)
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
        rePrint(indexes, txt, pat);
    }

    public static int[] add(int[] a, int c){
	int[] new2 = new int[a.length+1];
	for(int i = 0; i < a.length; i++){
		new2[i] = a[i];
	}
	new2[a.length] = c;
	return new2;
	}

	public static void rePrint(int[] a, String t, String p){
		int h = 0;
		for(int i = 0; i < a. length; i++){
			System.out.print(t.substring(h,a[i]));
			for(int g = 0; g < p.length(); g++){
			System.out.print("0");
			}
			h = a[i]+p.length();
		}
		System.out.print(t.substring(h, t.length()));
		System.out.println();
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


