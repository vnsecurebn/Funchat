
import javax.swing.JFrame;


public class Sentry_box extends Thread implements Runnable {

	Main_interface mi;
	Listener l;
	public Sentry_box() {
		mi = new Main_interface();
		mi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l = new Listener(mi);
	}
	
	public void run(){
		l.start();
	}
	
	public static void main(String[] args) {
		Sentry_box Sb = new Sentry_box();
		Sb.start();
	}

}
