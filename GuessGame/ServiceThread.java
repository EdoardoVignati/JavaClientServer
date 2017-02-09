import java.io.*;
import java.net.*;

public class ServiceThread extends Thread{

	Socket toCln;
	int playerNumber;
	Guess g;

	public ServiceThread(Socket toCln, Guess g, int playerNumber){
		this.toCln = toCln;
		this.g = g;
		this.playerNumber = playerNumber;
	}

	public void run(){
	
		String mexFromCln;
		byte [] buffer = new byte [1000];
		int mexFromClnLength;
		String mexToCln;
		int nextP=0;

		try{

			InputStream in; 
			OutputStream out;
			
				in =  toCln.getInputStream();
				out = toCln.getOutputStream();

				mexToCln = "Hello player # " + playerNumber
					  + "\nWrite the number to guess (between 0 and 100)."
					  + "\nGood Luck!";
		
				out.write(mexToCln.getBytes(),0,mexToCln.length());

			
					while(true){

						if(playerNumber==g.nextPlayer()){

							out.write("turn".getBytes(),0,"turn".length());


							nextP=g.nextPlayer();
							g.startRound();
							mexFromClnLength = in.read(buffer);

							if(mexFromClnLength<=0)
								break;

							mexFromCln = new String(buffer, 0, mexFromClnLength);

							if(mexFromCln.compareTo("exit")==0)
								break;
														
							System.out.println("Player " + playerNumber + " tries : " + mexFromCln);
							
							if(g.tryToGuess(Integer.parseInt(mexFromCln))==1){
								System.out.println("Player " + playerNumber + " wins!");
								mexToCln="You win!";
							}else{
								System.out.println("Player doesn't guess.");
								mexToCln="\nSorry, try again!\nNow wait, next players are playing...\n";
							}

							g.startRound();

							System.out.println("Next player is: " + g.nextPlayer() + "\n");

							out.write(mexToCln.getBytes(),0,mexToCln.length());

							if(playerNumber!=g.nextPlayer())
							g.endRound();
							
						}else {
						
							out.write("wait".getBytes(),0,"wait".length());
							g.endRound();
						}
							
					}

				g.removePlayer(playerNumber);
				System.out.println("\nClient closed connection");
				System.out.println("Next is:" + g.nextPlayer());
				toCln.close();


		}catch(Exception e){
			System.out.println("Exception handled");
			e.printStackTrace();
		}finally{
			try{
				toCln.close();
			}catch(Exception e){
				System.out.println("Exception handled");
				e.printStackTrace();
			}
		}
			

	}



}
