import java.util.Scanner;
public class Resistors //class Resistors, by Elia Sulimanov, 14/10/2018.
{ //program that calculate the total resistance of 3 parallal resistors
    public static void main (String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println("Please enter 3 integers:");
        System.out.println("Please enter r1:");
        int r1 = scan.nextInt(); //save input from the user to an int type var.
        System.out.println("Please enter r2:");
        int r2 = scan.nextInt();
        System.out.println("Please enter r3:");
        int r3 = scan.nextInt();
        
        double totalResistance = 1 / ((1/(double)r1) + (1/(double)r2) 
                                 + (1/(double)r3)); //calculation of the total resistance
        
        System.out.println("The total resistance of resistors " + r1 
        + ", " + r2 + ", and " + r3 + " connected in parallel is: " 
        + totalResistance); //print total resistance value
    } //end of nethod main
} //end of class Resistors
