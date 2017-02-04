import java.io.*;
import java.net.*;

public class IterativeClient{

	public static void main(String [] args){

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String mexToSrv;

		Socket s = new Socket();

		try{

			InetAddress local = InetAddress.getLocalHost();
			int port = 55000;
			InetSocketAddress toServer = new InetSocketAddress(local,port);
			 
			 s.connect(toServer);
		
			OutputStream out = s.getOutputStream();
			System.out.println("Send something to server or write 'exit'");

			while ( (mexToSrv=in.readLine()).compareTo("exit")!=0)
			{
				out.write(mexToSrv.getBytes(), 0, mexToSrv.length());
			}

		}catch(Exception e){
			System.out.println("Exception handled");
			e.printStackTrace();
		}finally{
			try{
				s.close();
			}catch(Exception e){
				System.out.println("Exception handled");
			}
			
		}
		

	}

}
