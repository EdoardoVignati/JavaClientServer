import java.io.*;
import java.net.*;

public class Server {

	public static void main(String [] args){

		ServerSocket s = null;
		Socket toCln = null;

		Guess g = new Guess();

		int playerNumber = 0;
		

		try{

			s = new ServerSocket(55000);

			System.out.println("Number to guess is: " + g.getRandom());
			

			while(true){

				toCln = s.accept();
				
				playerNumber++;
				g.addPlayer(playerNumber);
				ServiceThread sThread = new ServiceThread(toCln, g, playerNumber);
				sThread.start();
			}

		}catch(Exception e){
			System.out.println("Exception handled");
			e.printStackTrace();
		}finally{
			try{
				toCln.close();
				s.close();
			}catch(Exception e){
				System.out.println("Exception handled");
				e.printStackTrace();
			}
		}


	}

}
