/*
 * Author: Administrator
 * Created: Sunday, January 27, 2008 11:45:55 PM
 * Modified: Sunday, January 27, 2008 11:45:55 PM
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.Dimension;
public class Chat_interface extends JFrame implements KeyListener,ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea jta1,jta2;
	JScrollPane js1,js2;
	JPanel p1,p2,p3,p4;
	static JMenuBar jmnb;
	private DataOutputStream os;
	Session ss;
	public Chat_interface(OutputStream pos,String title,Session se)
	{
		super(title);
		initialize();
		ss=se;
		os = new DataOutputStream(pos);
	    jmnb=new JMenuBar();
		JMenu menu=new JMenu("menu");
		JMenuItem sendfile=new JMenuItem("send file");
		JMenuItem exit=new JMenuItem("Exit");
		jmnb.setForeground(Color.blue);
		menu.setForeground(Color.blue);
		sendfile.setForeground(Color.blue);
		exit.setForeground(Color.blue);
		sendfile.addActionListener((ActionListener) this);
		menu.add(sendfile);
		menu.addSeparator();
		menu.add(exit);
		jmnb.add(menu);
		
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		jta1=new JTextArea(7,20);
		jta1.setFocusable(false);
		jta2=new JTextArea(5,20);
		jta2.addKeyListener(this);
		jta2.setFocusable(true);
		jta1.setLineWrap(true);
        jta1.setWrapStyleWord(true);
		jta2.setLineWrap(true);
        jta2.setWrapStyleWord(true);
		js1=new JScrollPane(jta1);
		js2=new JScrollPane(jta2);
		js1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		js2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());
		p3.setLayout(new BorderLayout());
        p4.setLayout(new BorderLayout());		
		p1.setMinimumSize(new Dimension(400, 50));
		p2.setMinimumSize(new Dimension(400, 20));
		p1.setPreferredSize(new Dimension(400, 280));
		p2.setPreferredSize(new Dimension(400, 50));
		p1.add(js1);
		p2.add(js2);
		p1.setBorder(BorderFactory.createTitledBorder("Display"));
		p2.setBorder(BorderFactory.createTitledBorder("Type"));
		Container container =getContentPane();
		container.setLayout(new BorderLayout());
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
       // splitPane.setSize(10, 10);//ResizeWeight(0.5);
		splitPane.add(p1);
		splitPane.add(p2);
		p3.add(splitPane,BorderLayout.CENTER);
		p3.setBorder(BorderFactory.createEmptyBorder(10,15,20,25));
		//p3.setBorder(BorderFactory.createTitledBorder("panel 3"));
		p4.add(p3);
		//p4.setBorder(BorderFactory.createTitledBorder("panel 4"));
		
		container.add(p4);
		container.setSize(450,400);
		//container.add(p2);
		this.setJMenuBar(jmnb);
		jmnb.setVisible(true);
		
		
	}
	
	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setVisible(true);
        this.setSize(new Dimension(501, 322));
			
	}

	public void keyPressed(KeyEvent arg0) {
		
	}
	public void keyReleased(KeyEvent arg0) {
		
	}
	public void keyTyped(KeyEvent arg0) {
		String temp;
		if(arg0.getKeyChar()=='\n'){
			temp = jta2.getText();
			temp = new String(temp.substring(0, temp.length()-1));
			try {
				os.write(2);
				os.writeInt(temp.length());
				os.write(temp.getBytes());
			} catch (IOException e) {
				Display("Send Error :"+e);
			}
			Display("A :"+temp);
			jta2.setText(null);
			if(temp.compareToIgnoreCase("quit")==0){
				System.exit(0);
			}	
		}
		
	}
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("send file"))
		{
			ss.Sendfile();
	     }
		if(arg0.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
    }
	
	public void Display(String s){
		jta1.append(s+"\n");
	}
}  //  @jve:decl-index=0:visual-constraint="71,5"