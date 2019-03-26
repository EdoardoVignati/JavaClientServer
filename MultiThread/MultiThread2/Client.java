import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket clientSocket = null;
        Scanner in = new Scanner(System.in);
        System.out.println("Please define the server connection host:port ...");
        String[] server = in.nextLine().split(":");

        try {
            /*Start a new connection with a server*/
            clientSocket = new Socket(server[0], Integer.parseInt(server[1]));
            System.out.println("Client is connecting to server " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
            /* Prepare the output stream */
            DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
            /* Prepare the reader of response */
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Waiting for commands from server...");


            do {
                System.out.println(br.readLine());
                String value = in.nextLine();
                System.out.println("Sending... : " + value);
                toServer.writeBytes(value+"\n");
                toServer.flush();

            } while (!clientSocket.isClosed());

        } catch (IOException e) {
            System.out.println("Error talking with server");
        } finally {
            try {
                clientSocket.close();
                in.close();
            } catch (IOException e) {
                System.out.println("Error closing socket");
            }
        }

    }
}
