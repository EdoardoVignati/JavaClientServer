import java.io.*;
import java.net.*;

public class Produttore {

	public static void main(String [] args) {

		DatagramSocket s;
		int length=100;
		byte buffer[] = new byte[length];
		byte bufferIn[] = new byte[length];
		String mex;
		String mexToSrv;
		String mexFromSrv;
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			if(args.length!=2){
				throw new IllegalArgumentException();
			}
			
			InetSocketAddress server = new InetSocketAddress(args[0],Integer.parseInt(args[1]));
			s = new DatagramSocket();
			
			DatagramPacket out;
			DatagramPacket in;

			while((mex=keyboard.readLine()).compareTo(".")!=0){

				mexToSrv = "p" + mex;
				buffer =  mexToSrv.getBytes();
				out = new DatagramPacket(buffer,buffer.length);
				
				out.setSocketAddress(server);
				s.send(out);
		
				in = new DatagramPacket(bufferIn,bufferIn.length);
				s.receive(in);
				mexFromSrv = new String(bufferIn,0,in.getLength());
				System.out.println("Items stored: " + mexFromSrv);
	
				
			}
				buffer = ".".getBytes();
				out = new DatagramPacket(buffer,buffer.length);
				out.setSocketAddress(server);
				s.send(out);
			
			s.close();	

		} catch (Exception e) {
			System.out.println("Exception handled");
			e.printStackTrace();

		}
		
	}

}

