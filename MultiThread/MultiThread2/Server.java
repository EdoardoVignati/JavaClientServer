import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {

        ServerSocket server;
        Scanner in = new Scanner(System.in);
        System.out.println("Please put port for server:");
        int port = in.nextInt();

        try {
            server = new ServerSocket(port);

            while (true) {
                System.out.println("Server listening on port " + server.getLocalPort() + "...   ");
                Socket s = server.accept();
                System.out.println("A new channel has been created with " + s.getInetAddress());
                Calc newCalc = new Calc(s);
                newCalc.start();
                if(newCalc.isAlive())
                    System.out.println("New thread started");
                else
                    System.out.println("Ops, an error has occurred spawning a new thread.");
            }
        } catch (IOException e) {
            System.out.println("Server cannot start.");
        }

    }
}
