import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ReceiveFile_interface extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel Label_url = null;
	private JTextField TextField_filename = null;
	private JButton Button_receive = null;
	private JButton Button_cancel = null;
	Receive_file rf;

	public ReceiveFile_interface(Receive_file r) {
		super();
		initialize();
		rf=r;
	}

	/**
	 * Initialize the class.
	 */
	private void initialize() {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(10);
		Label_url = new JLabel();
		Label_url.setText("URL");
		Label_url.setFont(new Font("Dialog", Font.BOLD, 14));
		this.setLayout(flowLayout);
		this.setName("Frame1");
		this.setResizable(false);
		this.setBounds(45, 25, 522, 114);
		this.setTitle("Receive_file");
		this.setVisible(true);
		this.add(Label_url, null);
		this.add(getTextField_filename(), null);
		this.add(getButton_send(), null);
		this.add(getButton_cancel(), null);
		Button_cancel.addActionListener(this);
		Button_receive.addActionListener(this);
	}

	/**
	 * This method initializes TextField_filename	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField_filename() {
		if (TextField_filename == null) {
			TextField_filename = new JTextField();
			TextField_filename.setColumns(40);
			TextField_filename.setText("C:\\Documents and Settings\\QUANG TRUNG\\Desktop\\receive\\");
			TextField_filename.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return TextField_filename;
	}

	/**
	 * This method initializes Button_send	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButton_send() {
		if (Button_receive == null) {
			Button_receive = new JButton();
			Button_receive.setText("Receive");
		}
		return Button_receive;
	}

	/**
	 * This method initializes Button_cancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButton_cancel() {
		if (Button_cancel == null) {
			Button_cancel = new JButton();
			Button_cancel.setText("Cancel");
		}
		return Button_cancel;
	}

	public void setfn(String s) {
		TextField_filename.setText(TextField_filename.getText()+s);
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==Button_receive){
			rf.Sendfn("ok");
			rf.setfilename(TextField_filename.getText());
			rf.start();
			TextField_filename.setEditable(false);
			Button_receive.setVisible(false);
		}else if(arg0.getSource()==Button_cancel){
			rf.Sendfn("ok");
			System.exit(0);
		}
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="34,25"
