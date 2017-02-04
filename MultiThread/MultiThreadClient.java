import java.io.*;
import java.net.*;

public class MultiThreadClient{

	public static void main(String [] args){

		
		int port = 55000;
		Socket s = new Socket();

		BufferedReader keyboard = new BufferedReader( new InputStreamReader (System.in) );
		String mex;

		byte [] buffer = new byte [1000];
		String fromSrv;
		int length;

		try{
		
			InetAddress local = InetAddress.getLocalHost();
			InetSocketAddress serverAddress = new InetSocketAddress(local, port);	
			s.connect(serverAddress);

			OutputStream out = s.getOutputStream();
			InputStream in = s.getInputStream();
			
			System.out.println("Send something to server or write 'exit'");
			while((mex=keyboard.readLine()).compareTo("exit")!=0){

				out.write(mex.getBytes(), 0, mex.length());
				length = in.read(buffer);
				fromSrv = new String (buffer, 0, length);
				System.out.println("Server > " + fromSrv);

			}

		}catch(Exception e){
			System.out.println("Exception handled");
			e.printStackTrace();

		}finally{
			try{
				s.close();
			}catch(Exception e){
				System.out.println("Exception handled");
				e.printStackTrace();
			}
		}


	}	



}
