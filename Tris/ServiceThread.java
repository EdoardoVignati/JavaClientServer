import java.io.*;
import java.net.*;

public class ServiceThread extends Thread{

	Socket toCln;
	int player=0;
	Tris table;

	public ServiceThread(Socket s, int num, Tris t){
	
		toCln = s;
		table = t;
		player=num;
	}

	public void run(){

		InputStream in;
		OutputStream out;

		byte[] buffer = new byte [1000];
		int length;
		int items;
		String mex []= new String[9];
		String symbol;
		String mexToCln;
		Tris t=null;

		try{
			in = toCln.getInputStream();
			out = toCln.getOutputStream();

			while(true){

				length = in.read(buffer);

				if(length<=0)
					break;

				mex[0] = new String(buffer, 0, length);

		
				if(mex[0].compareTo("exit")==0)
					break;

				mex = mex[0].split(" ");


				switch (mex[0]){

				/*case "play" :
				    	 out.write("Game table created. Use 'help' to continue.".getBytes());
					 break;*/

				case "set" : 
					 table.setSymbol(mex[1],Integer.parseInt(mex[2]),Integer.parseInt(mex[3]));
					 out.write("Set the symbol".getBytes());
					 table.startRound();
					 table.endRound();
					 break;

				case "?" : 	
					 mexToCln = "Current table : \n" + table.getTable();
					 out.write(mexToCln.getBytes());
					 break;

				default:
					mexToCln = "Use 'set sym x y' to set your symbol on table or '?' to get the table";
					out.write(mexToCln.getBytes());				
					break;

				}				
			}

			toCln.close();
			System.out.println("Client socket closed");
			
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
