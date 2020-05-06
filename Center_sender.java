

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.io.DataOutputStream;


public class Center_sender extends Thread implements Runnable{
	
	DataOutputStream os;
	Session ss;
	InputStream pi1;
	InputStream pi2;
	int state;
	
	public Center_sender(Socket s,InputStream p1,InputStream p2,Session se) {
		ss=se;
		state =1;
		try {
			os = new DataOutputStream(s.getOutputStream());
			pi1=p1;
			pi2=p2;
		} catch (IOException e) {
			ss.Display("Error : "+e);
		}
	}
	
	public int setState(int i){
		state=i;
		return i;
	}
	
	public void get_State(){
		System.out.println("Sender's state : "+state);	
	}
	
	public void run(){
		byte[] buf,buf1;
		while(true){
			try {
				while(pi1.available()> 0){
					buf=new byte[pi1.available()];
					pi1.read(buf);
					os.write(1);
					os.writeInt(buf.length);
					os.write(buf);
				}
				if(state!=1){
					while(pi2.available() > 0 ){
						buf1=new byte[pi2.available()];
						pi2.read(buf1);
						os.write(2);
						os.writeInt(buf1.length);
						os.write(buf1);
						System.out.println("FTP packet was sent ");
					}
					if(state ==3){
						state=1;
					}
				}	
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					ss.Display("Error : "+e);
				}
			} catch (IOException e) {
				ss.Display("Error : "+e);
			}
		}
	}

}
