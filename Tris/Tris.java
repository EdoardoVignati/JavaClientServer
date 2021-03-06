import java.util.*;

public class Tris{

	String [][] table;

	public Tris(){
		table = new String [3][3];
		
		for (String[] row: table)
    			Arrays.fill(row, "-");
	} 

	public synchronized void setSymbol(String sym, int x, int y){
	
		table[x][y]=sym;
	}

	public String getTable(){
		
		return (table[0][0] + " | " + table[1][0] + " | " + table[2][0] + "\n" +
			table[0][1] + " | " + table[1][1] + " | " + table[2][1] + "\n" +
			table[0][2] + " | " + table[1][2] + " | " + table[2][2]
		);
	}

	public synchronized void startRound(){

		try{
			notify();	
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public synchronized void endRound(){

		try{
			wait();	
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
