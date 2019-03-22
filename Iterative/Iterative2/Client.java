import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        Socket clientSocket=null;
        Scanner in = new Scanner(System.in);
        System.out.println("Server connection host:port");
        String[] server = in.nextLine().split(":");
        System.out.println("Please fill me with two numbers");
        String numberz = in.nextLine();
        System.out.println("Input: " + numberz);
        in.close();

        try {
            /*Start a new connection with a server*/
            clientSocket = new  Socket(server[0], Integer.parseInt(server[1]));
            System.out.println("Client is connecting to server " + clientSocket.getInetAddress()+":"+clientSocket.getPort());
            /* Prepare the output stream */
            DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
            /* Send the numbers */
            toServer.writeBytes(numberz+"\n");

            /* Prepare the reader of response */
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            int result = Integer.parseInt(br.readLine());
            System.out.println("Result: " + result);

        } catch (IOException e) {
            System.out.println("Error talking with server");
        }finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing socket");
            }
        }

    }
}
