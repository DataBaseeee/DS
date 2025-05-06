import java.rmi.*;


 interface DS_1Intf extends Remote{

    public double addition(double n1 , double n2) throws RemoteException;
    public double substraction(double n1 , double n2) throws RemoteException;
    public double multiplication(double n1 , double n2) throws RemoteException;
    public double division(double n1 , double n2) throws RemoteException;

} 