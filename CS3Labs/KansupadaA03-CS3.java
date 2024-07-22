import java.io.File;
import java.util.*;
import java.io.IOException;
import java.util.Scanner;

public class KansupadaA03 {

    public static void main(String[] args) throws IOException {
        Scanner inp = new Scanner(new File("javaStatements.txt"));

        while (inp.hasNext()) {
            String temp = inp.nextLine();
            String keep = temp;
            temp = parse(temp);
            Stack<String> syntax = new Stack<String>();
            boolean correct = true;
            boolean quote2 = true;

            for (int i = 0; i < temp.length() ; i++) {
                if (opener(temp.substring(i, i + 1))) {
                    syntax.push(temp.substring(i, i + 1));
                }else if(temp.substring(i, i + 1).equals("\"")){
                    if(i >= temp.length()-1){
                        syntax.push(temp.substring(i, i + 1));
                        correct = false;
                    }else {
                        quote2 = false;
                        for (int y = i+1; y < temp.length(); y++) {
                            if (temp.substring(y, y + 1).equals("\"")) {
                                quote2 = true;
                                i = y;
                                break;
                            } else {
                                correct = false;
                            }
                        }
                        if (!quote2) {
                            syntax.push(temp.substring(i, i + 1));
                        }
                    }
                }

                if (closer(temp.substring(i, i + 1))) {
                    if(syntax.isEmpty()){
                        correct= false;
                    }else if (syntax.peek().equals("(") && temp.substring(i, i + 1).equals(")")) {
                        syntax.pop();
                    }else if (syntax.peek().equals("{") && temp.substring(i, i + 1).equals("}")) {
                        syntax.pop();
                    }else if (syntax.peek().equals("[") && temp.substring(i, i + 1).equals("]")) {
                        syntax.pop();
                    }else if (syntax.peek().equals("<") && temp.substring(i, i + 1).equals(">")) {
                        syntax.pop();
                    }else{
                        correct = false;
                    }
                }

            }

            if(!syntax.isEmpty()){
                correct = false;
            }else{
                correct = true;
            }

            if(correct) {
                System.out.println("Good :\t"+keep+"   Stack: "+syntax);
            }else{
                System.out.println("Bad :\t"+keep+"   Stack: "+syntax);
            }


        }

    }

    public static String parse(String sentence){
        String[] openClose = {"(",")","[","]","{","}","<",">","\""};
        String ret = "";
        for(int i = 0; i < sentence.length();i++){
            for(int e = 0; e < openClose.length ; e++){
                if(sentence.substring(i,i+1).equals(openClose[e])){
                    ret += sentence.substring(i,i+1);
                }
            }
        }
        return ret;
    }

    public static boolean opener(String s){
        String[] open = {"(","[","{","<"};
        for(int i = 0; i < open.length; i++){
            if(s.equals(open[i])){
                return true;
            }
        }
        return false;
    }

    public static boolean closer(String s){
        String[] close = {")","]","}",">"};
        for(int i = 0; i < close.length; i++){
            if(s.equals(close[i])){
                return true;
            }
        }
        return false;
    }
}


