import java.io.*;
import java.net.*;

public class ConnectionLessServer{

	public static synchronized void main(String [] args){

		DatagramSocket s;
		int length=1000;
		byte [] buffer = new byte[length];
		String mex;


		try{
			s = new DatagramSocket();
			DatagramPacket dataPackIn;
			for(int i = 0; i<20; i++){

				System.out.println("Server: " + s.getLocalAddress() + ":" + s.getLocalPort());
			
				dataPackIn = new DatagramPacket(buffer, length);
				s.receive(dataPackIn);

				InetAddress client = dataPackIn.getAddress();
				System.out.println("Client: " + client.getHostAddress() + ":" + dataPackIn.getPort());

				mex = new String(buffer,0,dataPackIn.getLength());
	
				System.out.println("Client > " + mex + "\n");

				DatagramPacket dataPackOut = new DatagramPacket(buffer, buffer.length);
				InetSocketAddress clientAddress = new InetSocketAddress(client.getHostAddress(),dataPackIn.getPort());
				dataPackOut.setSocketAddress(clientAddress);
				s.send(dataPackOut);

			}

			s.close();		
			
		}catch(Exception e){
			System.out.println("Exception handled");
			e.printStackTrace();
		}
	}
}
