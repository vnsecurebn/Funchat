import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.Socket;

import javax.swing.JFrame;


public class Session extends Thread implements Runnable{

	Center_receiver center_r;
	Center_sender center_s;
	Chat_interface ci;
	Chat_receiver cr;
	PipedInputStream pi1,pi2,fi1,fi2;
	PipedOutputStream po1,po2,fo1,fo2;
	
	public Session(Main_interface m,Socket s,String str ) {
		 pi1 = new PipedInputStream();
		 pi2 = new PipedInputStream();
		 po1 = new PipedOutputStream();
		 po2 = new PipedOutputStream();
		 fi1 = new PipedInputStream();
		 fi2 = new PipedInputStream();
		 fo1 = new PipedOutputStream();
		 fo2 = new PipedOutputStream();
		try {
			pi1.connect(po1);
			pi2.connect(po2);
			fi1.connect(fo1);
			fi2.connect(fo2);
		} catch (IOException e) {
			System.out.println("Error when connect pipe : "+e);
		}
		center_s = new Center_sender(s,pi2,fi1,this);
		center_r = new Center_receiver(s,po1,fo2,this);
		ci= new Chat_interface(po2,str,this);
		cr= new Chat_receiver(ci,pi1);
	}
	
	public void run(){
		center_r.start();
		center_s.start();
		cr.start();
		ci.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public int setState(int i){
		center_r.setState(i);
		center_s.setState(i);
		return i;
	}
	
	public void Display(String s){
		ci.Display(s);
	}
	
	public void Sendfile(){
		setState(2);
		Send_file sf= new Send_file(fi2,fo1,this);
		getstate();
		Display("Send file :::::::::::::::::::::::::::::::::");
	}
	
	public void Receive(String s){
		setState(2);
		Receive_file rf= new Receive_file(fi2,fo1,this,s);
		getstate();
		Display("Receive file :::::::::::::::::::::::::::::::::");
	}
	
	public void getstate(){
		center_r.get_State();
		center_s.get_State();
	}
}

