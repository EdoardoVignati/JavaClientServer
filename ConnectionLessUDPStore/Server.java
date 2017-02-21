import java.net.*;

public class Server {
	public static void main(String [] args) {

		DatagramSocket s;
		int length=100;
		byte [] buffer = new byte[length];
		byte [] bufferOut = new byte[length];
		String mex; 
		int many;
		String mexToCln;
		Store mag = new Store();
		int notEnought=0;
		
		try {
			s = new DatagramSocket(0);
		
			InetAddress sCln;
	
			InetAddress ia = s.getLocalAddress();
			int localPort = s.getLocalPort();
			System.out.println("Server " + ia.getHostAddress() + ":" + localPort);
			DatagramPacket in;
			DatagramPacket out;
			InetSocketAddress client;
			while(true){
				
			

				in = new DatagramPacket(buffer,length);
				s.receive(in);
				mex = new String(buffer,0,in.getLength());

				sCln = in.getAddress();
	
 				client = new InetSocketAddress(sCln.getHostAddress(),in.getPort());

				if(buffer[0]=='.')
					System.out.println("A client closed connection");
				else if(buffer[1]=='?'){
						mag.getStore();
				}
				else if(buffer[0]=='p'){

					mex = new String(buffer,0,in.getLength());
					mex = mex.replace("p","");

					try{
						mag.add(Integer.parseInt(mex));
							
						if(Integer.parseInt(mex)<0)
							System.out.println("Items must be > 0");
						else 
							System.out.println(mex + " items added to store");
					}catch(Exception e){
						System.out.println("User input not valid");
						mex="0";
					}
						
					System.out.println(mag.getStore() + " items remain");

				}else if(buffer[0]=='c'){

					mex = new String(buffer,0,in.getLength());
					mex = mex.replace("c","");
					
					try{
							if(mag.getStore()<Integer.parseInt(mex))
								notEnought=1;
							else{
									mag.remove(Integer.parseInt(mex));
								if(Integer.parseInt(mex)<0)
									System.out.println("Items must be > 0");
								else 	System.out.println("Client buy " + mex + " from store");
								
							}	
					}catch(Exception e){
								System.out.println("User input not valid");
								mex="0";
							}
						
					System.out.println(mag.getStore() + " items remain");

						
				}
				
				if(notEnought==1)
					mexToCln=+mag.getStore() + "\nSorry, you don't buy anything.\nCurrent store is just "+mag.getStore();
				else mexToCln=""+mag.getStore();

				notEnought=0;
				bufferOut=mexToCln.getBytes();

				out = new DatagramPacket(bufferOut,bufferOut.length);
				out.setSocketAddress(client);
				s.send(out);

			
			}
		} catch (Exception e) {
			System.out.println("Exception handled");
			e.printStackTrace();

		}

	}

}

