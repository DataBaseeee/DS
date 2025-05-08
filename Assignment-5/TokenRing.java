// import java.util.*;

// public class TokenRing{

//     public static void main(String[]args){
//         Scanner sc = new Scanner(System.in);

//         System.out.println("Enter the no of Nodes :");
//         int n = sc.nextInt();

//         System.out.println("----------Ring Formed------------");

//         for(int i=0; i<n; i++){
//             System.out.print(i+"->");
//         }
//         System.out.println("0");
         
//         int choice = 0;
//         do{
//          System.out.println("Enter Sender");
//          int sender = sc.nextInt();

//          System.out.println("Enter Receiver");
//          int receiver = sc.nextInt();

//          System.out.println("Enter Data");
//          int Data = sc.nextInt();

//          int token = 0;
//             System.out.println("----------Token Passing------------");
//          for(int i=token ; i<sender; i++){
//             System.out.print(i+"->");
//          }
//          System.out.print(sender+"\n");
//          System.out.println("Sender "+sender+" Sending Data "+Data);

//          for(int i=sender;i!=receiver;i=(i+1)%n){
//             System.out.println("Data : "+Data+" Forwarded to next by : "+i);
//          }
//          System.out.println("Receiver : "+receiver+ " Received the data : "+Data );

//          token = sender;
//          System.out.println("Token Released by Sender: ");
//          System.out.println("Do you want to continue if yes press 1 otherwise press 0");
//          choice = sc.nextInt();
//         }while(choice==1);
//     }
// }

import java.util.*;
public class TokenRing{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the no of nodes in Ring : ");
        int n = sc.nextInt();
         System.out.println("--------Below is Ring--------");
        for(int i=0; i<n; i++){
            System.out.print(i+"->");
        }
        System.out.println("0");
         int choice = 0;
        do{
            System.out.println("Enter the Sender : ");
            int sender = sc.nextInt();
            System.out.println("Enter the Receiver : ");
            int receiver = sc.nextInt();
            System.out.println("Enter the Data : ");
            int data = sc.nextInt();
           

            int token = 0;
           System.out.println("Token is passing towards to the Sender ");
            for(int i=token; i<sender; i++){
                System.out.print(i+"->");
            }
            System.out.println(""+sender);
         
            System.out.println("Data is getting forwarded ");

            for(int i=sender; i!=receiver; i=(i+1)%n){
                System.out.println("Data "+data+" is forwarded by "+i);
            }
            System.out.println("Receiver "+receiver+" received the Data "+data);
            token = sender;
            System.out.println("Token is released from the sender");

            System.out.println("Do you want to continue if yes press 1 otherwise 0 : ");
            choice = sc.nextInt();

        }while(choice==1);
    }
}