import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String [] args){


        Scanner scanner = new Scanner(System.in);
        System.out.println("Listening port:");
        int port = scanner.nextInt();
        scanner.close();
        ServerSocket server=null;
        BufferedReader br=null;

            try{
                server = new ServerSocket(port);
                System.out.println("Listening in localhost on port " + port + "...");

                while(true) {
                    /*Accept single socket from client and stay in listening*/
                    Socket fromClient = server.accept();
                    /* Print information of client */
                    System.out.println("Connection from: " + fromClient.getInetAddress() + ":" +fromClient.getPort());

                    /* Get the input stream of the socket */
                    br = new BufferedReader(new InputStreamReader(fromClient.getInputStream()));
                    /* Read a line from the input stream */
                    String[] numbers = br.readLine().split(" ");
                    System.out.println("Input from client:" + numbers[0] + " " + numbers[1]);
                    /* Do the sum */
                    int sum = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);

                    /* Prepare the output channel for the socket */
                    DataOutputStream outToClient = new DataOutputStream(fromClient.getOutputStream());
                    /* Send sum throw socket connection */
                    outToClient.writeBytes(sum+"\n");
                    System.out.println("Message to client:" + sum);
                }
            }catch (IOException e){
                System.out.println("Error during connection");
                System.exit(1);

        }


    }
}
