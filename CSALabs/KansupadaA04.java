import java.util.*;

public class KansupadaA04 {
    public static void main(String args[]) {
       System.out.println("Aarav Kansupada");
       System.out.println("Lab 04");
       System.out.println();
       Scanner myObj = new Scanner(System.in);
       System.out.print("Enter the radius: \t");
       double r = myObj.nextDouble();
       System.out.print("Enter the height: \t");
       double h = myObj.nextDouble();


       System.out.println();
       sa_cylinder(r,h);
       sa_cone(r,h);
       System.out.println();
       vol_cylinder(r,h);
       vol_cone(r,h);
    }

    public static double area(double r){
      return(Math.PI*r*r);
    }
    public static void sa_cylinder(double r, double h){
      System.out.printf("The surface area of the cylinder is %.2f\n", 2*area(r)+(2*Math.PI*r*h));
    }
    public static void sa_cone(double r, double h){
      double l = Math.pow(Math.pow(r,2)+Math.pow(h,2),0.5);
      System.out.printf("The surface area of the cone is %.2f\n", area(r)+(Math.PI*r*l));
    }
    public static void vol_cylinder(double r, double h){
      System.out.printf("The volume of the cylinder is %.2f\n",area(r)*h);
    }
    public static void vol_cone(double r, double h){
      System.out.printf("The volume of the cone is %.2f\n",area(r)*h*(1.0/3));
    }


}