import java.util.*;

public class AKansupada02 {
    public static void main(String args[]) {
       Scanner myObj = new Scanner(System.in);  
       System.out.print("How many were right in Part A?\t\t\t\t ");
       double a = myObj.nextDouble(); 
       System.out.print("How many points were avaliable in Part A?\t ");
       double atot = myObj.nextDouble();
       System.out.print("How many were right in Part B?\t\t\t\t ");
       double b = myObj.nextDouble();
       System.out.print("How many points were avaliable in Part B?\t ");
       double btot = myObj.nextDouble();
       System.out.print("How many were right in Part C?\t\t\t\t ");
       double c = myObj.nextDouble();
       System.out.print("How many points were avaliable in Part C?\t ");
       double ctot = myObj.nextDouble();
       System.out.println();
       System.out.println("--------");
       System.out.println();
       int pa =(int)((a/atot*100)+0.5);
       int pb =(int)((b/btot*100)+0.5);
       int pc =(int)((c/ctot*100)+0.5);
       int pf = (int)(((pa+pb+pc)/3)+0.5);
       System.out.println("Part A \t\t\t\t\t : "+pa);
       System.out.println("Part B \t\t\t\t\t : "+pb);
       System.out.println("Part C \t\t\t\t\t : "+pc);
       System.out.println();
       System.out.println("Final Grade\t\t\t\t : "+pf);
    }
}