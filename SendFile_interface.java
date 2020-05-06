import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendFile_interface extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JLabel Label_url = null;
	private JTextField TextField_filename = null;
	private JButton Button_send = null;
	private JButton Button_cancel = null;
	Send_file sf;

	public SendFile_interface(Send_file s) {
		super();
		initialize();
		sf=s;
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
		this.setBounds(45, 25, 522, 114);
		this.setTitle("Send_file");
		this.setVisible(true);
		this.add(Label_url, null);
		this.add(getTextField_filename(), null);
		this.add(getButton_send(), null);
		this.add(getButton_cancel(), null);
		Button_cancel.addActionListener(this);
		Button_send.addActionListener(this);
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
			TextField_filename.setText("C:\\Documents and Settings\\QUANG TRUNG\\Desktop\\send\\");
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
		if (Button_send == null) {
			Button_send = new JButton();
			Button_send.setText("Send");
		}
		return Button_send;
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

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==Button_send){
			sf.setFilename(TextField_filename.getText());
			sf.start();
			TextField_filename.setEditable(false);
			Button_send.setVisible(false);
		}else{
			System.exit(0);
		}
	}
}  //  @jve:decl-index=0:visual-constraint="34,25"
