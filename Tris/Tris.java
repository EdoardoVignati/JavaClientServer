import java.util.*;

public class Tris{

	String [][] table;

	public Tris(){
		table = new String [3][3];
		
		for (String[] row: table)
    			Arrays.fill(row, "-");
	} 

	public void setSymbol(String sym, int x, int y){
	
		table[x][y]=sym;
	}

	public String getTable(){
		
		return (table[0][0] + " | " + table[1][0] + " | " + table[2][0] + "\n" +
			table[0][1] + " | " + table[1][1] + " | " + table[2][1] + "\n" +
			table[0][2] + " | " + table[1][2] + " | " + table[2][2]
		);
	}
	public boolean isWinner(){
		return false;	
	}
	public synchronized void startRound(){
			notify();	
	}

	public synchronized void endRound(){

		try{
			wait();	
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
