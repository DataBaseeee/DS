import java.io.*;
import java.net.*;
import java.util.*;

 class ClockClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8080);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(() -> {
            while (true) {
                try {
                    long time = System.currentTimeMillis() + (long)(Math.random() * 1000 - 500);
                    out.println(time);
                    System.out.println("Sent time: " + time);
                    Thread.sleep(5000);
                } catch (Exception e) { break; }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    long syncTime = Long.parseLong(in.readLine());
                    System.out.println("Synchronized Time: " + syncTime);
                } catch (Exception e) { break; }
            }
        }).start();
    }
}
