import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/*
 * Author: Kevin Subhash
 * Date: January 2017
 * Description: This class allows the user to change his/her information whether it is
 * their name, username, password, or picture. The user can also change the seconds for
 * each quiz.
 */
public class Settings extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnChange1, btnChange2, btnChange3, btnChange4,btnConfirm,btnBack,btnVerifyLogin, btnCancel; // declared variables as JButtons
	private JLabel lblChange1, lblChange2, lblChange3, lblChange4, label, label_1, label_2, label_3, lblUserName, lblPassword, lblStatus, lblSeconds, greeting; // declared variables as JLabels
	private JTextField confirmPassField, universalField; // declared variables as JTextField
	private JRadioButton radioButton, radioButton_1, radioButton_2, radioButton_3; // declared variables as JRadioButton
	private String name, userName, password, picName, stats; // declared variables as String
	private JPasswordField passwordField; // declared variable as JPasswordField
	private int key,time; // decalred variables as int, it is a key for btnConfirm and time for questions
	private JComboBox comboBox; // created a comboBox
	private ButtonGroup bg = new ButtonGroup();
	Player newInfo, oldInfo; // declared variables as Player
	PlayerAccountList accounts = new PlayerAccountList(); // Created PlayerAccountList object

	public Settings() throws IOException {
		super("Settings");  // title for the frame

		accounts.loadFile("Players.txt");

		setContentPane (new JLabel(new ImageIcon ("Images/settings.png")));

		setSize(350,500); // set size of window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		/*
		 * Creted JButtons with Names
		 */
		btnChange1 = new JButton("Change Picture");
		btnChange2 = new JButton("Change Name");
		btnChange3 = new JButton("Change UserName");
		btnChange4 = new JButton("Change Password");
		btnConfirm = new JButton("Confirm");
		btnCancel = new JButton("Cancel");
		btnVerifyLogin = new JButton("Verify Login");
		btnBack = new JButton("Back");
		/*
		 * Added fonts to JButtons
		 */
		btnChange1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnChange2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnChange3.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnChange4.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnConfirm.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnCancel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnVerifyLogin.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		/*
		 * SetBounds for each JButton
		 */
		btnChange1.setBounds(10, 251, 184, 38);
		btnChange2.setBounds(10, 300, 184, 38);
		btnChange3.setBounds(10, 349, 184, 38);
		btnChange4.setBounds(10, 398, 184, 38);
		btnConfirm.setBounds(10, 349, 184, 38);
		btnCancel.setBounds(10, 398, 184, 38);
		btnVerifyLogin.setBounds(76, 269, 184, 38);
		btnBack.setBounds(10, 75, 89, 23);
		/*
		 * Added frame for each JButton
		 */
		getContentPane().add(btnChange1);
		getContentPane().add(btnChange2);
		getContentPane().add(btnChange3);
		getContentPane().add(btnChange4);
		getContentPane().add(btnConfirm);
		getContentPane().add(btnCancel);
		getContentPane().add(btnVerifyLogin);
		getContentPane().add(btnBack);
		/*
		 * Set visibility of certain JButtons to false
		 */
		btnChange1.setVisible(false);
		btnChange2.setVisible(false);
		btnChange3.setVisible(false);
		btnChange4.setVisible(false);
		btnConfirm.setVisible(false);
		btnCancel.setVisible(false);
		btnBack.setVisible(false);
		/*
		 * Added actionlistener to all JButtons
		 */
		btnChange1.addActionListener(this);	
		btnChange2.addActionListener(this);	
		btnChange3.addActionListener(this);	
		btnChange4.addActionListener(this);
		btnConfirm.addActionListener(this);
		btnCancel.addActionListener(this);
		btnVerifyLogin.addActionListener(this);
		btnBack.addActionListener(this);
		/*
		 * Creted JRadioButtons 
		 */
		radioButton = new JRadioButton();
		radioButton_1 = new JRadioButton();
		radioButton_2 = new JRadioButton();
		radioButton_3 = new JRadioButton();
		/*
		 * SetBounds for each radiobutton
		 */
		radioButton.setBounds(56, 250, 28, 23);
		radioButton_1.setBounds(127, 250, 28, 23);
		radioButton_2.setBounds(198, 250, 28, 23);
		radioButton_3.setBounds(266, 250, 28, 23);
		/*
		 * Set frame for each radiobutton
		 */
		getContentPane().add(radioButton);
		getContentPane().add(radioButton_1);
		getContentPane().add(radioButton_2);
		getContentPane().add(radioButton_3);
		/*
		 * Added radioButtons to ButtonGroup
		 */
		bg.add(radioButton);
		bg.add(radioButton_1);
		bg.add(radioButton_2);
		bg.add(radioButton_3);
		/*
		 * Set visibility of radioButtons to false
		 */
		radioButton.setVisible(false);
		radioButton_1.setVisible(false);
		radioButton_2.setVisible(false);
		radioButton_3.setVisible(false);	
		/*
		 * Creted JTextFields with 10 columns 
		 */
		universalField = new JTextField(10);
		confirmPassField = new JTextField(10);
		passwordField = new JPasswordField(10);
		/*
		 * SetBounds for textfields
		 */
		confirmPassField.setBounds(10, 318, 184, 20);
		universalField.setBounds(91, 165, 153, 20);
		passwordField.setBounds(91, 220, 153, 20);
		/*
		 * Added textfield to frame
		 */
		getContentPane().add(confirmPassField);
		getContentPane().add(passwordField);
		getContentPane().add(universalField);
		/*
		 * Set visibility to certain textfields to false
		 */
		confirmPassField.setVisible(false);
		/*
		 * Creted JLabels with text
		 */
		lblChange1 = new JLabel("Enter New Name");
		lblChange2 = new JLabel("Enter New UserName");
		lblChange3 = new JLabel("Enter New Password");
		lblChange4 = new JLabel("Confirm Password");

		label = new JLabel(new ImageIcon("p1.jpg"));
		label_1 = new JLabel(new ImageIcon("p4.png"));
		label_2 = new JLabel(new ImageIcon("p3.jpg"));
		label_3 = new JLabel(new ImageIcon("p2.png"));

		lblStatus = new JLabel("Invalid UserName/Password");
		lblUserName = new JLabel("User Name");
		lblPassword = new JLabel("Password");
		lblSeconds = new JLabel("Seconds:");
		greeting = new JLabel("<html>" + "Hello, " + Data.accounts.getName(Data.userName) + "<html>");
		greeting.setHorizontalAlignment(SwingConstants.CENTER);
		greeting.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		/*
		 * SetBounds for labels
		 */
		lblStatus.setBounds(91, 120, 243, 20);
		lblChange1.setBounds(10, 300, 153, 14);
		lblChange2.setBounds(10, 300, 153, 14);
		lblChange3.setBounds(10, 256, 132, 14);
		lblChange4.setBounds(10, 300, 121, 14);
		label.setBounds(37, 280, 60, 60);
		label_1.setBounds(107, 280, 60, 60);
		label_2.setBounds(177, 280, 60, 60);
		label_3.setBounds(246, 280, 60, 60);
		lblUserName.setBounds(91, 149, 83, 14);
		lblPassword.setBounds(91, 205, 83, 14);
		lblSeconds.setBounds(246, 352, 86, 14);
		greeting.setBounds(17, 120, 317, 120);
		/*
		 * Added labels to frame
		 */
		getContentPane().add(lblChange1);
		getContentPane().add(lblChange2);
		getContentPane().add(lblChange3);
		getContentPane().add(lblChange4);
		getContentPane().add(label);
		getContentPane().add(label_1);
		getContentPane().add(label_2);
		getContentPane().add(label_3);
		getContentPane().add(lblUserName);
		getContentPane().add(lblPassword);
		getContentPane().add(lblStatus);
		getContentPane().add(lblSeconds);
		getContentPane().add(greeting);
		/*
		 * Creating comboBox and setting
		 * it up
		 */
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"15", "20", "25", "30", "35", "40", "45"}));
		comboBox.setActionCommand("access");
		comboBox.addActionListener(this);
		comboBox.setBounds(246, 367, 48, 20);
		getContentPane().add(comboBox);
		/*
		 * set visibility for certain labels to false
		 */
		lblChange1.setVisible(false);
		lblChange2.setVisible(false);
		lblChange3.setVisible(false);	
		lblChange4.setVisible(false);		
		label.setVisible(false);	
		label_1.setVisible(false);	
		label_2.setVisible(false);
		label_3.setVisible(false);
		lblStatus.setVisible(false);
		lblSeconds.setVisible(false);
		greeting.setVisible(false);
		lblStatus.setForeground(Color.DARK_GRAY);
		comboBox.setVisible(false); // setting visibility for comboBox


		setVisible(true);
		setResizable(false); // cannot resize window
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBack){
			try{
				if(accounts.change(oldInfo, newInfo)){ // when the information is changed, it saves to the file
					accounts.writeFile("Players.txt");
				}
				new HomeMenuGUI();
			} catch(IOException e2){
			}
			dispose(); // closes settings gui
		}
		if(e.getSource() == btnVerifyLogin){ // performs when Verify login is pressed is pressed
			if(Data.userName.equalsIgnoreCase(universalField.getText()) && accounts.checkLogin(universalField.getText(), passwordField.getText())){
				userName = universalField.getText(); // stores userName of player
				password = passwordField.getText(); // stores password of player
				picName = Data.accounts.getPic(userName); // stores picture of player
				stats = Data.accounts.getStats(userName).toString(); // stores stats of player
				name = Data.accounts.getName(userName); // stores name of player
				oldInfo = new Player(name + ";" + userName + ";" + password + ";" + picName + ";" + stats); // adds information to oldInfo Player Object
				/*
				 * Setting visibility of buttons, labels, and textfields 
				 * to either true or false
				 */
				lblStatus.setVisible(false);
				btnBack.setVisible(true);
				btnChange1.setVisible(true);
				btnChange2.setVisible(true);
				btnChange3.setVisible(true);
				btnChange4.setVisible(true);
				comboBox.setVisible(true);
				lblSeconds.setVisible(true);
				universalField.setVisible(false);
				passwordField.setVisible(false);
				lblUserName.setVisible(false);
				lblPassword.setVisible(false);
				btnVerifyLogin.setVisible(false);
				greeting.setVisible(true);
			}
			else{
				lblStatus.setText("Invalid UserName/Password"); // notifies user if login has failed
				lblStatus.setVisible(true);
			}
		}
		if (e.getActionCommand().equals("access"))
		{
			//switching depending on selection item
			switch ((String) comboBox.getSelectedItem())
			{
			case "15":
			{
				time = 15;
				break;
			}
			case "20":
			{
				time = 20;
				break;
			}
			case "25":
			{
				time = 25;
				break;
			}
			case "30":
			{
				time = 30;
				break;
			}
			case "35":
			{
				time = 35;
				break;
			}
			case "40":
			{
				time = 40;
				break;
			}
			case "45":
			{
				time = 45;
				break;
			}
			}
			try {
				PrintWriter pw = new PrintWriter(new FileWriter("time.txt")); // writes the time to a file
				pw.println(time);
				pw.close();
			} catch (IOException e1) {
			}
		}
		if (e.getSource() == btnChange1) { // performs when btnChange1 is pressed
			/*
			 * Setting visibility of buttons, labels, and textfields 
			 * to either true or false
			 */
			key = 1;
			btnChange1.setVisible(false);
			btnChange2.setVisible(false);
			btnChange3.setVisible(false);
			btnChange4.setVisible(false);
			btnBack.setVisible(false);
			radioButton.setVisible(true);
			radioButton_1.setVisible(true);
			radioButton_2.setVisible(true);
			radioButton_3.setVisible(true);
			label.setVisible(true);
			label_1.setVisible(true);
			label_2.setVisible(true);
			label_3.setVisible(true);
			radioButton.setSelected(true);
			radioButton_1.setSelected(false);
			radioButton_2.setSelected(false);
			radioButton_3.setSelected(false);
			btnConfirm.setVisible(true);
			btnCancel.setVisible(true);

		}
		if (e.getSource() == btnChange2) { // performs when btnChange2 is pressed
			/*
			 * Setting visibility of buttons, labels, and textfields 
			 * to either true or false
			 */
			key = 2;
			universalField.setText("");
			universalField.setBounds(10, 318, 184, 20);
			universalField.setVisible(true);
			btnChange1.setVisible(false);
			btnChange2.setVisible(false);
			btnChange3.setVisible(false);
			btnChange4.setVisible(false);
			btnBack.setVisible(false);
			lblChange1.setVisible(true);
			btnConfirm.setVisible(true);
			btnCancel.setVisible(true);
		}
		if (e.getSource() == btnChange3) { // performs when btnChange3 is pressed
			/*
			 * Setting visibility of buttons, labels, and textfields 
			 * to either true or false
			 */
			key = 3;
			universalField.setText("");
			universalField.setBounds(10, 318, 184, 20);
			universalField.setVisible(true);
			btnChange1.setVisible(false);
			btnChange2.setVisible(false);
			btnChange3.setVisible(false);
			btnChange4.setVisible(false);
			btnBack.setVisible(false);
			lblChange2.setVisible(true);
			btnConfirm.setVisible(true);
			btnCancel.setVisible(true);
		}
		if (e.getSource() == btnChange4) { // performs when btnChange4 is pressed
			/*
			 * Setting visibility of buttons, labels, and textfields 
			 * to either true or false
			 */ 
			key = 4;
			universalField.setText("");
			universalField.setBounds(10, 269, 184, 20);	
			universalField.setVisible(true);
			btnChange1.setVisible(false);
			btnChange2.setVisible(false);
			btnChange3.setVisible(false);
			btnChange4.setVisible(false);
			btnBack.setVisible(false);
			confirmPassField.setVisible(true);
			lblChange3.setVisible(true);
			lblChange4.setVisible(true);
			btnConfirm.setVisible(true);
			btnCancel.setVisible(true);
		}
		if (e.getSource() == btnCancel) { // performs when btnCancel is pressed
			/*
			 * Setting visibility of buttons, labels, and textfields 
			 * to either true or false
			 */
			universalField.setVisible(false);
			lblStatus.setVisible(false);
			btnChange1.setVisible(true); 
			btnChange2.setVisible(true);
			btnChange3.setVisible(true);
			btnChange4.setVisible(true);	
			radioButton.setVisible(false);
			radioButton_1.setVisible(false);
			radioButton_2.setVisible(false);
			radioButton_3.setVisible(false);
			label.setVisible(false);
			label_1.setVisible(false);
			label_2.setVisible(false);
			label_3.setVisible(false);
			btnConfirm.setVisible(false);
			lblChange1.setVisible(false);
			lblChange2.setVisible(false);
			lblChange3.setVisible(false);
			lblChange4.setVisible(false);
			confirmPassField.setVisible(false);
			btnBack.setVisible(true);
			btnCancel.setVisible(false);

		}
		if (e.getSource() == btnConfirm) { // Performs when btnConfirm is pressed
			if (key == 1){ // performs when key is 1
				/*
				 * When a specific radiobutton is selcted, it will
				 * set the picName to the picture's name
				 */
				if(radioButton.isSelected()){
					picName = "p1.jpg";
				}
				else if(radioButton_1.isSelected()){
					picName = "p2.png";
				}
				else if(radioButton_2.isSelected()){
					picName = "p3.jpg";
				}
				else if(radioButton_3.isSelected()){
					picName = "p4.png";
				}
				else{
					picName = "p1,jpg";
				}
				/*
				 * Setting visibility of buttons, labels, and textfields 
				 * to either true or false
				 */
				btnChange1.setVisible(true);
				btnChange2.setVisible(true);
				btnChange3.setVisible(true);
				btnChange4.setVisible(true);
				btnBack.setVisible(true);
				radioButton.setVisible(false);
				radioButton_1.setVisible(false);
				radioButton_2.setVisible(false);
				radioButton_3.setVisible(false);
				label.setVisible(false);
				label_1.setVisible(false);
				label_2.setVisible(false);
				label_3.setVisible(false);
				btnConfirm.setVisible(false);
			}
			else if(key == 2){ // performs when key is 2
				if(universalField.getText().equals("")){ // when textfield is empty, it notifies the user
					lblStatus.setBounds(50, 281, 161, 14);
					lblStatus.setText("Fill in the Field(s).");			
					lblStatus.setVisible(true);
				}
				else{
					name = universalField.getText();
					/*
					 * Setting visibility of buttons, labels, and textfields 
					 * to either true or false
					 */
					universalField.setVisible(false);
					lblStatus.setVisible(false);
					btnChange1.setVisible(true);
					btnChange2.setVisible(true);
					btnChange3.setVisible(true);
					btnChange4.setVisible(true);
					btnBack.setVisible(true);
					lblChange1.setVisible(false);
					btnConfirm.setVisible(false);
				}
			}
			else if(key == 3){ // performs when key is 3
				if(universalField.getText().equals("")){ // checking if textfield is empty
					lblStatus.setBounds(50, 281, 161, 14);
					lblStatus.setText("Fill in the Field(s).");			
					lblStatus.setVisible(true);
				}
				else if(!accounts.checkUserName(universalField.getText())){ // checking if username exists already
					lblStatus.setBounds(37, 275, 189, 14);
					lblStatus.setText("UserName Taken Already");			
					lblStatus.setVisible(true);
				}
				else{
					userName = universalField.getText(); // userName hold text in textField4
					/*
					 * Setting visibility of buttons, labels, and textfields 
					 * to either true or false
					 */
					universalField.setVisible(false);
					lblStatus.setVisible(false);
					btnChange1.setVisible(true);
					btnChange2.setVisible(true);
					btnChange3.setVisible(true);
					btnChange4.setVisible(true);
					btnBack.setVisible(true);
					lblChange2.setVisible(false);
					btnConfirm.setVisible(false);
				}
			}
			else if (key == 4){ // performs when key is 4
				if(universalField.getText().equals("") || confirmPassField.getText().equals("")){ // checking if textfields are filled
					lblStatus.setBounds(50, 241, 161, 14);
					lblStatus.setText("Fill in the Field(s).");			
					lblStatus.setVisible(true);
				}
				else if(universalField.getText().equals(confirmPassField.getText()) == false){ // checking if text in both textfields are not the same
					lblStatus.setBounds(25, 235, 161, 14);
					lblStatus.setText("Passwords DO NOT Match.");			
					lblStatus.setVisible(true);
				}
				else{
					password = universalField.getText(); // password hold the text in textField2
					/*
					 * Setting visibility of buttons, labels, and textfields 
					 * to either true or false
					 */
					universalField.setVisible(false);
					lblStatus.setVisible(false);
					btnChange1.setVisible(true);
					btnChange2.setVisible(true);
					btnChange3.setVisible(true);
					btnChange4.setVisible(true);
					btnBack.setVisible(true);
					confirmPassField.setVisible(false);
					lblChange3.setVisible(false);
					lblChange4.setVisible(false);
					btnConfirm.setVisible(false);
				}
			}	
		}
		newInfo = new Player(name + ";" + userName + ";" + password + ";" + picName + ";" +  stats); // creating Player object called newInfo with new information
	}
	public static void main(String[] args) throws IOException {
		Settings settings = new Settings();
	}
}
