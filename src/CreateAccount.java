import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 *Author: Kevin Subhash
 *Date: January 2017
 *Description: This class allows the user to create an account. After inputing the information, 
 *it uses the information to create a new player object and stores the valid information in a 
 *textfile
 */

public class CreateAccount extends JFrame implements ActionListener{

	private JButton btnClear1, btnClear2, btnClear3, btnClear4, btnCreateAccount, btnBack; // Declaring JButtons
	private JRadioButton radioButton, radioButton_1, radioButton_2, radioButton_3; // Declaring JRadioButtons
	private JTextField passwordField,userNameField,confirmPassField,nameField; // Declaring JTextFields
	private JLabel lblPickAPicture,lblEnterAUsername,lblEnterYourName,lblEnterPassword,lblComfirmPassword, // Declaring JLabels
	label,label_1,label_2,label_3,lblMatch,lblTaken,lblPicWarning,lblEmpty; 
	private ButtonGroup bg = new ButtonGroup();

	public CreateAccount() throws IOException {

		super("Create Account");  // title for the frame
		Data.accounts.loadFile("Players.txt"); // loads the players.txt file

		setContentPane (new JLabel(new ImageIcon ("Images/createaccount.png")));

		setSize(350,500); // set size of window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		/*
		 * Created JTextFields with 10 columns
		 */
		passwordField = new JTextField(10);
		userNameField = new JTextField(10);
		confirmPassField = new JTextField(10);
		nameField = new JTextField(10);
		/*
		 * Specific clear button appears when typed
		 * in a textfield
		 */
		passwordField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				btnClear3.setVisible(true);
			}
		});

		userNameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				btnClear1.setVisible(true);
			}
		});

		confirmPassField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				btnClear4.setVisible(true);
			}
		});

		nameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				btnClear2.setVisible(true);
			}
		});
		/*
		 * SetBounds for each textfield
		 */
		passwordField.setBounds(71, 191, 193, 20);
		userNameField.setBounds(71, 124, 193, 20);
		confirmPassField.setBounds(71, 259, 193, 20);
		nameField.setBounds(71, 63, 193, 20);
		/*
		 * Added textfields to frame
		 */
		getContentPane().add(passwordField);
		getContentPane().add(userNameField);
		getContentPane().add(confirmPassField);
		getContentPane().add(nameField);	
		/*
		 * Created JLabels with text
		 */
		lblEnterAUsername = new JLabel("Enter a UserName");
		lblEnterYourName = new JLabel("Enter Your Name");
		lblEnterPassword = new JLabel("Enter Password");
		lblComfirmPassword = new JLabel("Comfirm Password");
		lblMatch = new JLabel("Passwords Do NOT Match.");
		lblTaken = new JLabel("UserName Already Taken.");
		lblPickAPicture = new JLabel("Select A Picture");

		label = new JLabel(new ImageIcon("p1.jpg"));
		label_1 = new JLabel(new ImageIcon("p4.png"));
		label_2 = new JLabel(new ImageIcon("p3.jpg"));
		label_3 = new JLabel(new ImageIcon("p2.png"));

		lblEmpty = new JLabel("Fill in the Field(s).");
		lblPicWarning = new JLabel("You Need To Pick A Picture.");
		/*
		 * SetBounds for each JLabel
		 */
		lblEnterAUsername.setBounds(71, 110, 193, 14);
		lblEnterYourName.setBounds(71, 50, 193, 14);
		lblEnterPassword.setBounds(71, 177, 193, 14);
		lblComfirmPassword.setBounds(71, 244, 193, 14);
		lblMatch.setBounds(96, 155, 153, 14);
		lblTaken.setBounds(103, 94, 183, 14);
		lblPickAPicture.setBounds(133, 290, 104, 14);
		label.setBounds(37, 337, 60, 60);
		label_1.setBounds(107, 337, 60, 60);
		label_2.setBounds(177, 337, 60, 60);
		label_3.setBounds(246, 337, 60, 60);
		lblEmpty.setBounds(127, 25, 122, 14);
		lblPicWarning.setBounds(140, 290, 166, 14);
		/*
		 * Added labels to frame
		 */
		getContentPane().add(lblEnterAUsername);
		getContentPane().add(lblEnterYourName);
		getContentPane().add(lblEnterPassword);
		getContentPane().add(lblComfirmPassword);
		getContentPane().add(lblMatch);
		getContentPane().add(lblTaken);
		getContentPane().add(lblPickAPicture);
		getContentPane().add(label);
		getContentPane().add(label_1);
		getContentPane().add(label_2);
		getContentPane().add(label_3);
		getContentPane().add(lblEmpty);
		getContentPane().add(lblPicWarning);
		/*
		 * Set visible to false for lblMatch and lblTaken
		 */
		lblMatch.setVisible(false);
		lblTaken.setVisible(false);
		lblPicWarning.setVisible(false);
		lblEmpty.setVisible(false);
		/*
		 * Created JButtons with text
		 */
		btnCreateAccount = new JButton("Create Account!");
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 15)); // Setting font for back button
		btnClear1 = new JButton("X");
		btnClear2 = new JButton("X");
		btnClear3 = new JButton("X");
		btnClear4 = new JButton("X");
		/*
		 * SetBounds for each JButton
		 */
		btnCreateAccount.setBounds(96, 419, 144, 41);
		btnBack.setBounds(10, 11, 89, 23);
		btnClear1.setBounds(284, 123, 50, 23);
		btnClear2.setBounds(284, 62, 50, 23);
		btnClear3.setBounds(284, 190, 50, 23);
		btnClear4.setBounds(284, 258, 50, 23);
		/*
		 * Added buttons to the frame
		 */
		getContentPane().add(btnCreateAccount);
		getContentPane().add(btnClear1);
		getContentPane().add(btnClear2);
		getContentPane().add(btnClear3);
		getContentPane().add(btnClear4);
		getContentPane().add(btnBack);
		/*
		 * Set visibility of several buttons to false
		 */
		btnClear1.setVisible(false);	
		btnClear2.setVisible(false);
		btnClear3.setVisible(false);
		btnClear4.setVisible(false);
		/*
		 * Added actionlistener to all buttons
		 */
		btnCreateAccount.addActionListener(this);
		btnClear1.addActionListener(this);
		btnClear2.addActionListener(this);
		btnClear3.addActionListener(this);
		btnClear4.addActionListener(this);
		btnBack.addActionListener(this);
		/*
		 * Created JRadioButtons
		 */
		radioButton = new JRadioButton();
		radioButton.setSelected(true); // setSelected set to true
		radioButton_1 = new JRadioButton();
		radioButton_2 = new JRadioButton();
		radioButton_3 = new JRadioButton();
		/*
		 * SetBounds for each radiobutton
		 */
		radioButton.setBounds(56, 308, 21, 23);
		radioButton_1.setBounds(127, 308, 21, 23);
		radioButton_2.setBounds(198, 308, 21, 23);
		radioButton_3.setBounds(266, 308, 21, 23);
		/*
		 * Added radiobuttons to frame
		 */
		getContentPane().add(radioButton);
		getContentPane().add(radioButton_1);
		getContentPane().add(radioButton_2);
		getContentPane().add(radioButton_3);
		/*
		 * Added radiobuttons to buttongroup
		 */
		bg.add(radioButton);
		bg.add(radioButton_1);
		bg.add(radioButton_2);
		bg.add(radioButton_3);

		setVisible(true);
		setResizable(false); // cannot resize the window
	}

	public void actionPerformed (ActionEvent e){
		if (e.getSource() == btnClear1) { // clears userNameField
			userNameField.setText("");
			btnClear1.setVisible(false);
		}
		if (e.getSource() == btnClear4) { // clears comfirmPaddField
			confirmPassField.setText("");
			btnClear4.setVisible(false);
		}
		if (e.getSource() == btnClear3) { // clears passwordField
			passwordField.setText("");
			btnClear3.setVisible(false);
		}
		if (e.getSource() == btnClear2) { // clears nameField
			nameField.setText("");
			btnClear2.setVisible(false);
		}
		if (e.getSource() == btnBack) { // clears nameField
			try {
				new AccountLogin();
			} catch (IOException e1) {
			}
			dispose();
		}
		if (e.getSource() == btnCreateAccount) {
			String info, password = null, pic = null, name = nameField.getText(), userName = userNameField.getText(); // created string variables with values from textfields

			if(passwordField.getText().equals(confirmPassField.getText())){
				password = passwordField.getText(); // sets password to  passwordField text once it matches the comfirmPassField text
			}
			/*
			 * pic will be given a value depending on which radiobutton
			 * is selected
			 */
			if(radioButton.isSelected()){
				pic = "p1.jpg";
			}
			else if (radioButton_1.isSelected()){
				pic = "p4.png";
			}
			else if (radioButton_2.isSelected()){
				pic = "p3.jpg";
			}
			else if (radioButton_3.isSelected()){
				pic = "p2.png";
			}

			if(nameField.getText().equals("")||userNameField.getText().equals("")||passwordField.getText().equals("")||confirmPassField.getText().equals("") ){ // If any textField is empty, the user will be notified 
				lblMatch.setVisible(false);
				lblTaken.setVisible(false);
				lblPicWarning.setVisible(false);
				lblEmpty.setVisible(true);
			}
			else if(!Data.accounts.checkUserName(userNameField.getText())){ // If UserName is taken, the user will be notified
				lblMatch.setVisible(false);
				lblTaken.setVisible(true);
				lblPicWarning.setVisible(false);
				lblEmpty.setVisible(false);
			}
			else if(pic == null){ // If picture is not picked, the user will be notified
				lblMatch.setVisible(false);
				lblTaken.setVisible(false);
				lblPicWarning.setVisible(true);
				lblEmpty.setVisible(false);
				lblPickAPicture.setVisible(false);
			}
			else if(passwordField.getText().equals(confirmPassField.getText()) == false){ // If the passwords don't match, the user will be notified
				lblMatch.setVisible(true);
				lblTaken.setVisible(false);
				lblPicWarning.setVisible(false);
				lblEmpty.setVisible(false);
			}
			else{
				String t = name + ";" + userName + ";" + password + ";" + pic + ";0|0|0|0.0|0.0|1|[empty]"; // storing t with new Player Information
				Player playerInfo = new Player(t); // creates new Player object
				Data.accounts.insert(playerInfo); // inserts new player info 
				try {
					Data.accounts.writeFile("Players.txt"); // saves the file
				} catch (IOException e1) {
				}
				try {
					new AccountLogin();
				} catch (IOException e1) {
				} // Opens AccountLogin GUI
				dispose(); // Closes CreateAccount GUI
			}
		}
	}


	public static void main(String[] args) throws IOException {
		CreateAccount createAccount = new CreateAccount();

	}
}
