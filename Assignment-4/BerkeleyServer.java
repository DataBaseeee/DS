import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;

class ClockServer {
    static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Server started...");

        new Thread(() -> {
            while (true) {
                try {
                    Socket clientSocket = server.accept();
                    ClientHandler client = new ClientHandler(clientSocket);
                    clients.add(client);
                    new Thread(client).start();
                } catch (Exception e) { e.printStackTrace(); }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000); // every 10 sec
                    syncClocks();
                } catch (Exception e) { e.printStackTrace(); }
            }
        }).start();
    }

    static void syncClocks() {
        if (clients.isEmpty()) return;

        long serverTime = System.currentTimeMillis();
        long total = serverTime;
        for (ClientHandler c : clients) {
            total += c.clientTime;
        }
        long avg = total / (clients.size() + 1);

        for (ClientHandler c : clients) {
            long offset = avg - c.clientTime;
            try {
                c.out.println(avg);
            } catch (Exception e) { e.printStackTrace(); }
        }

        System.out.println("Server Time: " + serverTime);
        System.out.println("Average Time: " + avg);
    }

    static class ClientHandler implements Runnable {
        Socket socket;
        BufferedReader in;
        PrintWriter out;
        long clientTime;

        ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }

        public void run() {
            while (true) {
                try {
                    clientTime = Long.parseLong(in.readLine());
                    System.out.println("Received time: " + clientTime);
                } catch (Exception e) { break; }
            }
        }
    }
}
