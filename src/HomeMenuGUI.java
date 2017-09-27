/*
 * Author: Janujan Gathieswaran
 * Date: January 24 2017
 * Description: The home GUI which allows the user to choose quizzes, access settings and create a new quiz. 
 * 
 * Method List:
 * 		Constructors
 * 			HomeMenuGUI() //default constructor to run GUI

 * 		Functions
 * 			void actionPerformed (ActionEvent e) //method to check what buttons pressed
 * 
 * 		Self-Testing Main
 * 			static void main(String[] args) //create an object of the class
 */

import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.Color;

public class HomeMenuGUI extends JFrame implements ActionListener, MouseListener{

	//----[Variable Declaration]------
	//private JTextField txtSearch;
	//labels for settings, create, logout, statistics and picture
	private JLabel lblSettings, lblCreate, lblLogout, lblStatistics, lblPic;
	JButton [] btnQuiz;

	QuizGUI q;
	//-----------------------

	public HomeMenuGUI() throws IOException {
		Data.accounts.loadFile("Players.txt");

		setContentPane (new JLabel(new ImageIcon ("Images/HomeMenuGUI.png")));

		setSize(500,700);
		//setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		//create logout label
		lblLogout = new JLabel("Logout");
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogout.setBounds(24, 494, 78, 16);
		getContentPane().add(lblLogout);
		lblLogout.addMouseListener(this);

		//create statistics label
		lblStatistics = new JLabel("Statistics");
		lblStatistics.setForeground(Color.WHITE);
		lblStatistics.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatistics.setBounds(24, 395, 78, 16);
		getContentPane().add(lblStatistics);
		lblStatistics.addMouseListener(this);

		//create a picture label

		lblPic = new JLabel(new ImageIcon(Data.accounts.getPic(Data.userName)));

		lblPic.setHorizontalAlignment(SwingConstants.CENTER);
		lblPic.setBounds(30, 30, 60, 60);
		getContentPane().add(lblPic);

		//create a create label
		lblCreate = new JLabel("Create");
		lblCreate.setForeground(Color.WHITE);
		lblCreate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreate.setBounds(24, 344, 78, 16);
		getContentPane().add(lblCreate);
		lblCreate.addMouseListener(this);

		//create a settings label
		lblSettings = new JLabel("Settings");	
		lblSettings.setForeground(Color.WHITE);
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setBounds(24, 444, 78, 16);
		getContentPane().add(lblSettings);
		lblSettings.addMouseListener(this);

		//create a new quizzes label
		JLabel lblNewQuizzes = new JLabel("New Quizzes: ");
		lblNewQuizzes.setBounds(149, 95, 80, 16);
		getContentPane().add(lblNewQuizzes);

		//create a best quizzes label
		JLabel lblBestQuizzes = new JLabel("Best Quizzes: ");
		lblBestQuizzes.setBounds(149, 378, 92, 16);
		getContentPane().add(lblBestQuizzes);

		//create an array of quiz option buttons
		btnQuiz = new JButton[12];

		//width and height dimensions of buttons
		int width = 90, height = 90;

		//initial x and y positions
		int x = 149, y = 123;

		//loop through and place 12 buttons 
		for (int i = 0; i < 12; i++) {
			if (i > 8) {
				if (i == 9) {
					x = 149;
				}
				y = 524;
				btnQuiz [i] = new JButton ();
				btnQuiz [i].setBounds(x, y, width, height);
				getContentPane().add(btnQuiz [i]);
				x += 112;
			}
			else if (i > 5) {
				if (i == 6) {
					x = 149;
				}
				y = 415;
				btnQuiz [i] = new JButton ();
				btnQuiz [i].setBounds(x, y, width, height);
				getContentPane().add(btnQuiz [i]);
				x += 112;
			} 
			else if (i > 2) {
				if (i == 3) {
					x = 149;
				}
				y = 232;
				btnQuiz [i] = new JButton ();
				btnQuiz [i].setBounds(x, y, width, height);
				getContentPane().add(btnQuiz [i]);
				x += 112;
			} 
			else {
				btnQuiz [i] = new JButton ();
				btnQuiz [i].setBounds(x, y, width, height);
				getContentPane().add(btnQuiz [i]);
				x += 112;
			}
		}

		//set the text of the buttons
		for (int i = 0; i < 6; i++) {
			btnQuiz[i].setText("<html>" + "American History" + "<html>");
			btnQuiz[i].setFont(new Font("Lucida Grande", Font.PLAIN, 10));
			btnQuiz[i].addActionListener(this);
		}
		for (int i = 6; i < 12; i++) {
			//btnQuiz[i].setText(bestQuizNames[i]);
			btnQuiz[i].setFont(new Font("Lucida Grande", Font.PLAIN, 10));
			btnQuiz[i].addActionListener(this);
		}
		System.out.print(btnQuiz.length);


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	//mouse press listener for when a label is pressed
	public void mousePressed(MouseEvent e) {

		if(e.getSource() == lblSettings){
			try {
				new Settings();
				dispose();
			} catch (IOException e1) {
			}
		}

		else if(e.getSource() == lblLogout){
			try {
				new AccountLogin();
			} catch (IOException e1) {
			}
			dispose();
		}
		else if(e.getSource() == lblCreate){

			new TriviaCreationGUI();
			dispose();
		}
		else if(e.getSource() == lblStatistics){
			try {
				new StatisticsGUI();
			} catch (IOException e1) {
			}
			dispose();
		}
	}
	//method to listen to button clicks
	public void actionPerformed (ActionEvent e) {

		//depending on which quiz button pressed, a QuizGUI is called and a quiz can be run
		for (int i = 0; i < btnQuiz.length; i++) {
			if (e.getSource() == btnQuiz[i]) {
				String fileName = btnQuiz[i].getText().substring(6,btnQuiz[i].getText().length()-6) + ".txt";

				//cannot call Quiz GUI because it is using thread.sleep
				try {
					new QuizGUI (fileName);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) throws IOException {
		//create an object of the class
		HomeMenuGUI homeMenu = new HomeMenuGUI ();
	}


}