import java.io.*;
import java.net.*;

public class ConnectionLessClient{

	public static void main(String [] args){

		DatagramSocket s;
		int length=1000;
		byte [] bufferOut = new byte[length];
		byte [] bufferIn = new byte[length];
		String mex;
		

		
		if(args.length!=2){
			throw new IllegalArgumentException("Start me with address and port of server");
		}


		try{
			s = new DatagramSocket();

			for(int i = 0; i<10; i++){

				if(i==0){
					System.out.println("Write something to send to server: ");
					BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
					bufferOut = in.readLine().getBytes();
					System.out.println("");
					
					System.out.println("Client: " + s.getLocalAddress() + ":" + s.getLocalPort());
					System.out.println("Server: " + args[0] + ":" + args[1]);
				}


				System.out.println("I'm sending your message in 4 sec... " + (10 - i) + " times more ");
				
				final DatagramPacket dataPackOut = new DatagramPacket(bufferOut, bufferOut.length);

				InetSocketAddress server = new InetSocketAddress(args[0],Integer.parseInt(args[1]));
				dataPackOut.setSocketAddress(server);
				
				Thread.sleep(4000);
				s.send(dataPackOut);

				DatagramPacket dataPackIn = new DatagramPacket(bufferIn, bufferIn.length);
				s.receive(dataPackIn);

				mex = new String(bufferIn,0,dataPackIn.getLength());
				System.out.println("Seems server received : " + mex + "\n");

			}			
			
			s.close();
			
		}catch(Exception e){
			System.out.println("Exception handled");
			e.printStackTrace();
		}

	}


}
