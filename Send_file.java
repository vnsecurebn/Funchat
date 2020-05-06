import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Send_file extends Thread {

	SendFile_interface sfi;
	DataInputStream is;
	DataOutputStream os;
	FileInputStream fi;
	String fn;
	Session ss;
	
	public Send_file(InputStream i,OutputStream o,Session se) {
		ss=se;
		is=new DataInputStream(i);
		os= new DataOutputStream(o);
		fn=null;
		fi=null;
		sfi= new SendFile_interface(this);
	}
	
	public String setFilename(String s){
		fn=s;
		return s;
	}
	
	public void run(){
		String temp=fn.substring(51, fn.length());
		
		try {
			os.write(1);
			os.writeInt(temp.length());
			os.write(temp.getBytes());
			System.out.println(" have sent finame ");
		} catch (IOException e) {
			ss.Display("Send filename ERROR : "+e);
		}
		
		System.out.println(" Waiting for reply ");
		
		int type = 0;
		int len;
		byte[] buf;
		while(true){
			try {
				if(is.available()>0){
					type=is.read();
					System.out.println("type"+type );
					len=is.readInt();
					System.out.println("len"+len);
					buf=new byte[len];
					is.read(buf);
					System.out.println("String"+new String(buf));
					if(type==3){
						if(new String(buf).compareToIgnoreCase("ok")==0){
							break;
						}else{
							ss.Display("Deny");
						}
					}
				}
				
				System.out.println("...........................");
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				sleep(600);
			} catch (InterruptedException e) {
				ss.Display("Sleep ERROR "+e);
			}
		}
		
		System.out.println(" open file ");
		
		try {
			fi=new FileInputStream(fn);
		} catch (FileNotFoundException e) {
			ss.Display("can't read file : "+e);
		}
		
		System.out.println(" send data  ");
		
		byte[] buff;
		try {
			while(true){
				if(fi.available()>5000)
					buff=new byte[5000];
				else if(fi.available()>0)
						buff= new byte[fi.available()];
				else {
					os.write(4);
					break;
				}
				
				fi.read(buff);
				try{
					os.write(2);
					os.writeInt(buff.length);
					os.write(buff);
				}catch(IOException e){
					ss.Display("Sending ERROR : "+e);
				}
				try {
					sleep(800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					ss.Display("Sleep ERROR "+e);
				}
			}
		} catch (IOException e) {
			ss.Display("Reading file is ERROR : "+e);
		}
		
		System.out.println(" In the end ");
		
		ss.setState(3);
	}

}
