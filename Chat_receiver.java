

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Chat_receiver extends Thread implements Runnable{
	
	Chat_interface ci;
	DataInputStream is;
	public Chat_receiver(Chat_interface c,InputStream p){
		ci=c;
		is=new DataInputStream(p);
	}
	
	public void run(){
		byte[] temp;
		int type=0;
		int len=5;
		while(true){
			try {
				while(is.available()!=0){
					try{
					type=is.read();
					len=is.readInt();
					}catch(IOException e){
						ci.Display("Receive Header ERROR : "+e);
					}
					try{
						temp = new byte[len];
						is.read(temp);
						}catch(IOException e){
							ci.Display("Receive String ERROR : "+e);
							temp="ERROR".getBytes();
						}
					if(type==2){
						ci.Display("B : "+new String(temp));
						if(new String(temp).compareToIgnoreCase("quit")==0){
							System.exit(0);
						}
					}
				}
				sleep(800);
			} catch (IOException e) {
				ci.Display("Error : "+e);
			} catch (InterruptedException e) {
				ci.Display("Error : "+e);
			}
		}
	}

}
