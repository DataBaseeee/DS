import java.rmi.*;
import java.rmi.server.*;

class DS_1Impl extends UnicastRemoteObject implements DS_1Intf  {
public DS_1Impl() throws RemoteException{

}
    public double addition(double n1 , double n2) throws RemoteException{
        return n1+n2;
    }
    public double substraction(double n1 , double n2) throws RemoteException{
        return n1-n2;
    }
    public double multiplication(double n1 , double n2) throws RemoteException{
        return n1*n2;
    }
    public double division(double n1 , double n2) throws RemoteException{
         if(n2 != 0){
            return n1/n2;
         }else{
            System.out.println("Unexpected inputs");
         }
        
        return n1/n2;
    }

}
