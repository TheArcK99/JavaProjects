import java.util.*;
import java.io.*;

public class KansupadaA18 {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("emails.txt"));
        while(input.hasNextLine()){
            String t = input.nextLine();
            if(valid(t))
            {
                System.out.println(t+" is valid");
            }else{
                System.out.println(t+" is invalid");
            }
        }
    }

    public static boolean valid(String email){

        if(email.matches("[^.][\\w.]+[^.]@[\\w]+\\.+[\\w.]+[^.]")){
            return true;
        }
        return false;

    }

}
