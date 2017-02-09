import java.io.*;
import java.net.*;

public class Client {

	public static void main (String [] args){

		Socket s=new Socket();
		
		int port = 55000;
		InetAddress local;
		InetSocketAddress server;

		byte [] buffer = new byte[1000];
		int length;
		String mexFromSrv;

		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		String mex;

		try{
			local = InetAddress.getLocalHost();
			server = new InetSocketAddress(local,port);
			s.connect(server);
			OutputStream out = s.getOutputStream();
			InputStream in = s.getInputStream();


			do{
				
				while(true){
					length = in.read(buffer);
					mexFromSrv = new String(buffer,0,length);
					

					if(mexFromSrv.compareTo("wait")==0){
						System.out.println("\nWait...\n");
						length = in.read(buffer);
						mexFromSrv = new String(buffer,0,length);
							if(mexFromSrv.compareTo("turn")!=0)
								System.out.println("\nError\n");
							else System.out.print("\nMy turn :\n>  ");	

							break;									
					}else if(mexFromSrv.compareTo("turn")==0){
						System.out.print("\nMy turn :\n>  ");	
						break;	
					}else System.out.println(mexFromSrv);
					
				}
			
				
				mex=keyboard.readLine();
				out.write(mex.getBytes(),0,mex.length());

				length = in.read(buffer);
				mexFromSrv = new String(buffer,0,length);
				System.out.print(mexFromSrv);

			}while(mex.compareTo("exit")!=0);

			
			s.close();
		
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
