
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class Center_receiver extends Thread implements Runnable {

	DataInputStream is ;
	OutputStream po1;
	OutputStream po2;
	Session ss;
	int state;
	
	public Center_receiver(Socket s,OutputStream p1,OutputStream p2,Session se) {
		ss=se;
		state=1;
		try {
			is=new DataInputStream(s.getInputStream());
			po1=p1;
			po2=p2;
		} catch (IOException e) {
			ss.Display("Error : "+e);
		}
	}
	
	public int setState(int i){
		state=i;
		return i;
	}
	
	public void get_State(){
		System.out.println("Receiver's state : "+state);	
	}
	
	public void run(){
		int i;
		byte[] buf ;
		while(true){
			try {
				while((i = is.read())!=-1){
					buf = new byte[is.readInt()];
					is.read(buf);
					if(i == 1 ){
						po1.write(buf);
					}else {
						if(state==1){
							byte[] temp=new byte[buf.length - 5];
							for(int it=0;it<temp.length;it++)
								temp[it]=buf[it+5];
							ss.Receive(new String(temp));
						}else{
							po2.write(buf);
							System.out.println("FTP packet was received 000");
						}
						System.out.println("FTP packet was received ");
					}
				}	
				sleep(2000);
			} catch (IOException e) {
				ss.Display("Error : "+e);
			} catch (InterruptedException e) {
				ss.Display("Error : "+e);
			}
			
		}
	}

}
