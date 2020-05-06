
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Listener extends Thread implements Runnable{
	ServerSocket ss;
	Main_interface mi;
	public Listener(Main_interface m) {
		mi = m;
		try {
			ss = new ServerSocket(4321);
		} catch (IOException e) {
			mi.display("Error :" + e);
		}
	}
	public void run(){
		mi.display("I'm listenning ...... "+'\n');
		while(true){
			try {
				Socket so = ss.accept();
				mi.display("connect ......");
				Session se = new Session(mi,so,"listener call");
				se.start();
				sleep(200);
			} catch (IOException e) {
				mi.display("Error :" + e);
			} catch (InterruptedException e) {
				mi.display("Error :" + e);
			}	
		}
	}
}
