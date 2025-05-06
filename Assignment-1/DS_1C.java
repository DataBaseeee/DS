import java.rmi.*;
import java.util.*;


public class DS_1C{

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        try{
           String serverURL = "rmi://localhost/server";
           DS_1Intf serverIntf = (DS_1Intf)Naming.lookup(serverURL);

           System.out.print("Enter first no :");
           double n1 =sc.nextDouble();
           System.out.print("Enter second no :");
           double n2 =sc.nextDouble();

           System.out.println("First no is "+n1);
           System.out.println("Second no is "+n2);

           System.out.println("--------Result----------");
           System.out.println("Addition is: "+serverIntf.addition(n1,n2));
           System.out.println("Substraction is: "+serverIntf.substraction(n1,n2));
           System.out.println("Multiplication is: "+serverIntf.multiplication(n1,n2));
           System.out.println("Division is: "+serverIntf.division(n1,n2));

           
        }catch(Exception e){
            System.out.println("Exception Occured"+e.getMessage());
        }
    sc.close();

    }
   
}