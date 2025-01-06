import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author vregi, 1/4/2025
 */

public class Client {
    public static void main(String[] args) {
        if (args.length != 2)
            System.err.println("Usage: java Client <host> <port>");

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try (
                Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                Scanner scanner = new Scanner(System.in)
        ){

            String input;
            while ((input = scanner.nextLine()) != null){
                out.println(input);
                System.out.println("Server returned: " + in.readLine());
            }
        }
        catch (UnknownHostException e) {
            System.err.println("Unknown host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Unable to get I/O connection to " + host);
            System.exit(1);
        }
    }
}
