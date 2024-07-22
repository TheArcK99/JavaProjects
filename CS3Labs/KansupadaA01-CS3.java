import java.util.*;
import java.io.*;

public class KansupadaA01 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("binary.txt"));
        ArrayList<String> s = new ArrayList<String>();
        ArrayList<Integer> o = new ArrayList<Integer>();
        while(input.hasNext()){
            s.add(input.nextLine());
        }


        for(int i = 0; i < s.size(); i++){
            int m = 1;
            int l = s.get(i).length();
            String s1 = s.get(i);
            int temp10 = 0;
            for(int y = 0; y < l; y++){
                int temp2 = Integer.valueOf(s1.substring(s1.length()-1));
                s1 = s1.substring(0,s1.length()-1);
                temp10 += m*(temp2);
                m *= 2;
            }
            o.add(temp10);
        }

        for(int c = 0; c < s.size(); c++){
            System.out.println(s.get(c)+" in base 2 equals " +o.get(c)+" in base 10.");
        }
    }
}
