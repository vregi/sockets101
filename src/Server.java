import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author vregi, 1/3/2025
 */
public class Server {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        try (
                ServerSocket serverSocket =
                        new ServerSocket(Integer.parseInt(args[0]));
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            System.err.println("Unable to get I/O connection on port " + port + " or connection");
            System.exit(1);
        }

    }
}
