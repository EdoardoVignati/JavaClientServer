import java.io.*;
import java.net.*;

public class MultiThreadServer{

	public static void main(String [] args){

		
		int port = 55000;
		ServerSocket s =null;
		
		Socket toCln=null;

		Store mag = new Store();


		try{

		s = new ServerSocket(port);
		

			while(true){

				toCln = s.accept();	

					ServiceThread sThread = new ServiceThread(toCln, mag);
					new Thread(sThread).start();
				
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
