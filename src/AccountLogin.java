import java.awt.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

/*
 * Author: Janujan and Kevin
 * Date: January 24 2017
 * Description: Account login GUI with functionality to login into home menu
 * 
 * Method List:
 * 		Constructors
 * 			AccountLogin() //default constructor
 * 
 * 		Functions
 * 			void actionPerformed (ActionEvent e) //method to determine what buttons pressed

 * 		Self-Testing Main
 * 			static void main(String[] args) //create an object of the class
 */

public class AccountLogin extends JFrame implements ActionListener {

	//---[Variable Declaration]------------
	//buttons for login and clear
	private JButton btnLogin, btnClear1, btnClear2; 

	//text field for user name
	private JTextField txtUserName; 

	//labels for user name, password, status, picture and create account
	private JLabel lblUserName, lblPassword, lblStatus, picture, lblCreateAccount;

	//password field for user to enter password
	private JPasswordField passwordField;

	//variable for number of tries for login
	private int tries = 0; 

	//variables for entered password and user name
	private String password, userName;

	//create a PlayerAccountList object
	private PlayerAccountList accounts = new PlayerAccountList();

	//----------------------------------

	public AccountLogin() throws IOException { //constructor for GUI
		//load the different players from a file
		accounts.loadFile("Players.txt");

		setContentPane (new JLabel(new ImageIcon ("Images/accountlogin.png")));

		setSize(350,500); // set size of window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		/*
		 * create text user name text field add a key listener 
		 * to check if anything entered and display clear button
		 */
		txtUserName = new JTextField("");
		txtUserName.setBounds(70,230,200,25);
		getContentPane().add(txtUserName);
		txtUserName.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				btnClear2.setVisible(true);
			}
		});

		//create password field and add a key listener to check if anything entered
		passwordField= new JPasswordField(10);
		passwordField.setBounds(70,290,200,25);
		passwordField.addActionListener(this);
		getContentPane().add(passwordField);
		passwordField.addKeyListener(new KeyAdapter() {

			//if enter key is pressed, check if password and user name are correct
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == KeyEvent.VK_ENTER) { 
					if(access()==true && tries < 5) {
						//JOptionPane.showMessageDialog(null,"Success");
						lblStatus.setText("");
					}
					else{
						txtUserName.setText("");
						passwordField.setText("");
						lblStatus.setText("Invalid username or password");
						tries ++;
						btnClear1.setVisible(false);
						btnClear2.setVisible(false);

						if (tries >=3) {
							lblStatus.setText("Too many attempts");
							btnLogin.setEnabled(false);
						}
					}
				}
			}
			//if a key is pressed, display the clear button
			public void keyTyped(KeyEvent arg0) {
				btnClear1.setVisible(true);
			}
		});

		//create a login button
		btnLogin = new JButton("Login");
		btnLogin.setBounds(131,340,75,40);
		getContentPane().add(btnLogin);
		btnLogin.addActionListener(this);

		//create a username label 
		lblUserName = new JLabel("User Name");
		lblUserName.setBounds(70, 213, 84, 14);
		getContentPane().add(lblUserName);

		//create a password label
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(70, 276, 84, 14);
		getContentPane().add(lblPassword);

		//create a status label
		lblStatus = new JLabel("");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(58, 186, 217, 14);
		getContentPane().add(lblStatus);

		//create a create account label with a mouse listener to check if pressed
		lblCreateAccount = new JLabel("Create Account");
		lblCreateAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateAccount.setBounds(53, 419, 217, 14);
		getContentPane().add(lblCreateAccount);
		lblCreateAccount.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				try {
					new CreateAccount();
				} catch (IOException e) {
				}
				dispose();
			}
		});

		//create two clear buttons
		btnClear1 = new JButton("X");
		btnClear1.setBounds(280, 291, 44, 23);
		getContentPane().add(btnClear1);
		btnClear1.setVisible(false);
		btnClear1.addActionListener(this);

		btnClear2 = new JButton("X");
		btnClear2.setBounds(280, 231, 44, 23);
		getContentPane().add(btnClear2);
		btnClear2.setVisible(false);
		btnClear2.addActionListener(this);

		setVisible(true);

	}
	public boolean access(){
		if(txtUserName.getText().equalsIgnoreCase(userName) && passwordField.getText().equalsIgnoreCase(password)){
			return true;
		}
		return false;
	}
	//method for actions events
	public void actionPerformed (ActionEvent e){
		//if second clear button pressed, hide the second clear button and empty the user name field
		if (e.getSource() == btnClear2) {
			txtUserName.setText("");
			btnClear2.setVisible(false);
		}
		//if first clear button pressed, hide the first clear button and empty the password field 
		if (e.getSource() == btnClear1) {
			passwordField.setText("");
			btnClear1.setVisible(false);
		}
		//if login button pressed, check if the entered information is valid and open a home menu GUI
		if(e.getSource()==btnLogin){
			if(accounts.checkLogin(txtUserName.getText(), passwordField.getText()) && tries < 5) {

				lblStatus.setText("");
				Data.userName = txtUserName.getText();
				System.out.println(Data.accounts.getPic(Data.userName));
				try {
					new HomeMenuGUI();
				} catch (IOException e1) {
				}
				dispose();
			}
			else{
				//if information is wrong, display invalid user name and password
				txtUserName.setText("");
				passwordField.setText("");
				lblStatus.setText("Invalid username or password");
				tries ++;
				btnClear1.setVisible(false);
				btnClear2.setVisible(false);

				//if more than 5 tries, display too many attemptes
				if (tries >=5) {
					lblStatus.setText("Too many attempts");
					btnLogin.setEnabled(false);
				}
			}
		}
	}

	//create an account login object to run GUI
	public static void main(String[] args) throws IOException {
		AccountLogin accountLogin = new AccountLogin();
	}
}
