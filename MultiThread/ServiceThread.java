import java.io.*;
import java.net.*;

public class ServiceThread extends Thread{

	Socket toCln;
	Store mag;

	public ServiceThread(Socket s,Store m){
	
		toCln = s;
		mag = m;
	}

	public void run(){

		InputStream in;
		OutputStream out;

		byte[] buffer = new byte [1000];
		int length;
		int items;
		String mex;
		String mexToCln;

		try{
			in = toCln.getInputStream();
			out = toCln.getOutputStream();

			while(true){

				length = in.read(buffer);

				if(length<=0)
					break;

				mex = new String(buffer, 0, length);

		
				if(mex.compareTo("exit")==0)
					break;



				switch (mex){

				case "+" : 	
				    	 mag.add();
				    	out.write("Item added".getBytes());
					 break;

				case "-" : 	
					 mag.remove();
					 out.write("Item removed".getBytes());
					 break;

				case "?" : 	
					 items = mag.getStore();
					 mexToCln = "The store contains " + items + " items";
					 out.write(mexToCln.getBytes(),0,mexToCln.length());
					 break;

				default:
					out.write("Use '+' to add items, '-' to remove items or '?' to check store".getBytes());				
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
