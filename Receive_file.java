import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Receive_file extends Thread{

	ReceiveFile_interface rfi;
	DataInputStream is;
	DataOutputStream os;
	FileOutputStream fo;
	String fn;
	Session ss;
	public Receive_file(InputStream i,OutputStream o,Session se,String s) {
		ss=se;
		is=new DataInputStream(i);
		os= new DataOutputStream(o);
		fn=null;
		fo=null;
		rfi = new ReceiveFile_interface(this);
		rfi.setfn(s);
		System.out.println("Filename start receive 1");
	}
	
	public void setfilename(String s){
		fn=s;
	}
	
	public void Sendfn(String s){
		try {
			os.write(3);
			os.writeInt(s.length());
			os.write(s.getBytes());
		} catch (IOException e) {
			ss.Display("ERROR : "+e);
		}
		System.out.println("Have replied ");
	}
	public void run(){
		int type = 0;
		int len;
		byte[] buf;
		System.out.println("Receive data ");	
		try {
			fo=new FileOutputStream(fn);
			while(true){
				try {
					type=is.read();
					len=is.readInt();
					buf= new byte[len];
					is.read(buf);
					if(type==4){
						fo.close();
						break;
					}
					if(type==2){
						fo.write(buf);
					}
				} catch (IOException e) {
					ss.Display("Receiving ERROR : "+e);
				}
			}
		} catch (FileNotFoundException e1) {
			ss.Display("ERROR : "+e1);
		}	
	}

}
