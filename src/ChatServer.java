import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main (String [] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started. Waiting for client ..");

        Socket socket = serverSocket.accept();
        System.out.println("client connected ! ");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        String inputLine;
        while ((inputLine = in.readLine()) != null){
            System.out.println("Client :" + inputLine);
            System.out.print("You : ");
            String response = console.readLine();
            out.println(response);
        }
        socket.close();
        serverSocket.close();
    }
}
