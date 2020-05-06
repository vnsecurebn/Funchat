import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * Example AWT Frame with some common components on it
 */
public class Main_interface extends JFrame implements ActionListener,KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel Panel_connect = null;
	private JLabel Label_IP = null;
	private JTextField TextField_IP = null;
	private JButton Button_connect = null;
	private JTextArea TextArea_console = null;
	

	public Main_interface() {
		super();
		initialize();
	}

	/**
	 * Initialize the class.
	 */
	private void initialize() {
		this.setName("Frame1");
		this.setLayout(new BorderLayout());
		this.setBounds(45, 25, 534, 409);
		this.setTitle("The Main Interface ");
		this.setVisible(true);
		this.add(getPanel_connect(), BorderLayout.NORTH);
		this.add(getTextArea_console(), BorderLayout.CENTER);
		Button_connect.addActionListener(this);
		TextField_IP.addKeyListener(this);
	}

	/**
	 * This method initializes Panel_connect	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanel_connect() {
		if (Panel_connect == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(15);
			flowLayout.setVgap(15);
			Label_IP = new JLabel();
			Label_IP.setText("Enter host IP");
			Label_IP.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			Panel_connect = new JPanel();
			Panel_connect.setBackground(new Color(111, 233, 107));
			Panel_connect.setLayout(flowLayout);
			Panel_connect.setPreferredSize(new Dimension(80, 70));
			Panel_connect.add(Label_IP, null);
			Panel_connect.add(getTextField_IP(), null);
			Panel_connect.add(getButton_connect(), null);
		}
		return Panel_connect;
	}

	/**
	 * This method initializes TextField_IP	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField_IP() {
		if (TextField_IP == null) {
			TextField_IP = new JTextField();
			TextField_IP.setColumns(25);
			TextField_IP.setText("127.0.0.1");
			TextField_IP.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return TextField_IP;
	}

	/**
	 * This method initializes Button_connect	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButton_connect() {
		if (Button_connect == null) {
			Button_connect = new JButton();
			Button_connect.setText("Connect");
			Button_connect.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		}
		return Button_connect;
	}

	/**
	 * This method initializes TextArea_console	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTextArea_console() {
		if (TextArea_console == null) {
			TextArea_console = new JTextArea();
			TextArea_console.setColumns(40);
			TextArea_console.setText("");
			TextArea_console.setRows(40);
		}
		return TextArea_console;
	}

	public void actionPerformed(ActionEvent arg0) {
		String s = TextField_IP.getText();
		try {
			Socket so = new Socket(s,4321);
			Session se = new Session(this,so,"Main_interface call ");
			se.start();
		} catch (UnknownHostException e) {
			TextArea_console.append("Error : "+e);
		} catch (IOException e) {
			TextArea_console.append("Error : "+e);
		} 
	}

	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyChar()=='\n'){
			String s = TextField_IP.getText();
			try {
				Socket so = new Socket(s,4321);
				Session se = new Session(this,so,"Main_interface call");
				se.start();
			} catch (UnknownHostException e) {
				TextArea_console.append("Error : "+e);
			} catch (IOException e) {
				TextArea_console.append("Error : "+e);
			} 
		}
	}

	public void keyReleased(KeyEvent arg0) {
		
	}

	public void keyTyped(KeyEvent arg0) {
		
	}
	
	public void display(String s){
		TextArea_console.append(s+'\n');
	}
}  //  @jve:decl-index=0:visual-constraint="66,10"
