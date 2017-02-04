import java.io.*;
import java.net.*;

public class IterativeServer{

	public static void main(String [] args){

		ServerSocket s=null;

		Socket toCln;

		byte [] bufferIn = new byte [1000];
		String mexFromCln;
		int mexFromClnLength;

		try{
 			s = new ServerSocket(55000);
			toCln = new Socket();
			InputStream in; 

 			
			while(true){

				toCln = s.accept();
				in = toCln.getInputStream();
				while(true){


					mexFromClnLength = in.read(bufferIn);


					if(mexFromClnLength<=0)
					{
						System.out.println("Client closed connection");
						break;
					}

					mexFromCln = new String(bufferIn,0,mexFromClnLength);
					if(mexFromCln.compareTo("exit")==0)	
					{
						System.out.println("Bye Client");
						break;
					}
					System.out.println(mexFromCln);

				}
				toCln.close();
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
