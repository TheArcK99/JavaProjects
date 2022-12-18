import java.util.*;


public class KansupadaA03 {
    public static void main(String args[]) {
       Scanner myObj = new Scanner(System.in);  
       System.out.print("Enter the first leg of the triangle: ");
       double a = myObj.nextDouble(); 
       System.out.print("Enter the second leg of the triangle: ");
       double b = myObj.nextDouble();
       double c = Math.pow(Math.pow(a, 2) + Math.pow(b, 2), 0.5);
       System.out.printf("The length of the hypotenuse is %.2f",c);
       
       System.out.println();
       System.out.println();
       System.out.print("Enter the radius of the circle: ");
       double r = myObj.nextDouble(); 
       double dia = 2*r*Math.PI;
       double are = Math.pow(r,2)*Math.PI;
       double vol = (Math.pow(r,3))*Math.PI*(4.0/3);
       System.out.printf("The circumference of the circle is %.2f\n",dia);
       System.out.printf("The area of the circle is %.2f\n",are);
       System.out.printf("The volume of the sphere is %.2f\n",vol);
       System.out.println("------");
       System.out.println("Extra Credit");
       System.out.println();
       double e = Math.max(Math.max(a,b),r);
       System.out.print("The largest number entered was "+e);
       
    }
}