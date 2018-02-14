import java.util.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

public class Client {

	public static void main(String[] args) {
		// -c -h <server hostname> -p <server port> -t <time>
		String hostname = args[0];
		int portNumber = 0;
		long time = 0;
		byte blocks[] = new byte[1000];
			
		
		for(int i = 1; i<args.length;i++) {
			
			switch(args[i]) {
			
			case "-c": continue;
			
			case "-t":
				time = Integer.parseInt(args[i+1]);
				break;
				
			}
		}
		
		if(args.length!=8) {
			System.out.println("Error: missing or additional arguments");
			System.exit(0);
		}
		
		if(portNumber < 1024 || portNumber > 65535) {
			System.out.println("Error: port number must be in the range 1024 to 65535");
			System.exit(0);
		
		}
		
		try(	
				Socket clientSocket = new Socket(hostname,portNumber);
				OutputStream ClientSocket = clientSocket.getOutputStream();	
		 ) {
			
			long start = System.currentTimeMillis();
			long end = start + time*1000;
			int num_bytes =0;
			
			while(System.currentTimeMillis() < end) {
				ClientSocket.write(blocks, 0, blocks.length);
				num_bytes++;		
			}
			int data = num_bytes*blocks.length;
			clientSocket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
	}
