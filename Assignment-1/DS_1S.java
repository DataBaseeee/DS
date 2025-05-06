
import java.rmi.*;
public class DS_1S{

    public static void main(String[]args){
        try{
            DS_1Impl obj1 = new DS_1Impl();
            Naming.rebind("server",obj1);

        }catch(Exception e){
            System.out.println("Exception Occured"+e.getMessage());
        }
    }
}