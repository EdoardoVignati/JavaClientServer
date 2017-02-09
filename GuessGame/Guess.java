import java.util.*;

public class Guess{

	int [] players;
	int nowPlays;
	int maxPlayers=10;
	int randomToGuess;
	

	public Guess(){
		players = new int [maxPlayers];
		for(int i: players)
			i=0;	
		randomToGuess = new Random().nextInt(100);
		nowPlays=0;
	}

	public int getRandom(){
		return randomToGuess;
	}

	public synchronized int addPlayer(int p){
			
			for(int i = 0; i<maxPlayers; i++){

				if(players[i]==0){
					players[i] = p;
					System.out.println("\nAdded new player " + p + "\n");
					return 1;
				}
			}
			
		
		System.out.println("\nCan't add another player, maximum size reached.\n");
		return 0;
	}

	public synchronized int nextPlayer(){

		return players[nowPlays];

	}

	public synchronized int nextIndexPlayer(){

		for(int i = nowPlays; i<maxPlayers; i++){
				if(players[(i+1)%maxPlayers]!=0)
					return (i+1)%maxPlayers;
		}
	return 0;
	}

	public synchronized void removePlayer(int delPlayer){
		
		for(int i = 0; i<maxPlayers; i++){
			if(players[i]==delPlayer)
			{
			
				for(int j = i; j<maxPlayers-1; j++)
					players[j] = players[j+1];

				players[maxPlayers-1]=0;
				nowPlays = this.nextIndexPlayer();
				this.startRound();
			
				break;
			}
								
			
		}
		
		/*for(int i = 0; i<maxPlayers; i++)
			System.out.println(players[i] + " ");
								
			
		
		if(delPlayer==players[nowPlays])
			{
				
			}*/
			

		System.out.println("Removed player " + delPlayer + "\nNext is " + nowPlays);
	}

	public synchronized int tryToGuess(int number){

		int result;

		if(randomToGuess==number)
			result = 1;
		else result = 0;
		
		nowPlays = this.nextIndexPlayer();
		
		return result;
	}

	

	public synchronized void startRound(){

		try{
			notifyAll();
		}catch(Exception e){
			System.out.println("Exception handled");
			e.printStackTrace();
		}
	}

	public synchronized void endRound(){

		try{			
			wait();

		}catch(Exception e){
			System.out.println("Exception handled");
			e.printStackTrace();
		}
	}
	
}
