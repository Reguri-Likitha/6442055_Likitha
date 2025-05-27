import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Connected to server.");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        String inputLine, outputLine;
        while (true) {
            System.out.print("You: ");
            outputLine = keyboard.readLine();
            out.println(outputLine);
            inputLine = in.readLine();
            if (inputLine == null) break;
            System.out.println("Server: " + inputLine);
        }

        socket.close();
    }
}
