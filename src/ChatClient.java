import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("🟢 Connected to server!");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        Thread sendThread = new Thread(() -> {
            try {
                String msg;
                while ((msg = console.readLine()) != null) {
                    out.println(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread receiveThread = new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println("Server: " + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        sendThread.start();
        receiveThread.start();
    }
}
