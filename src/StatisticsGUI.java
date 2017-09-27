import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * Author: Kevin Subhash and Janujan
 * Date: January 2017
 * Description: This class is the GUI for the the player Statistics. It displays
 * the player's quizzes attempted, total questions attempted, correct answers,
 * overall percentage, and average time.
 * 
 * Method List:
 * public void actionPerformed(ActionEvent e) 
 * public StatisticsGUI() throws IOException
 */
public class StatisticsGUI extends JFrame implements ActionListener {

	private JLabel lblPicture, lblPlayerName, userName, picLogo; //Declaring JLabels
	private JTable table; // declaring a JTable
	private JButton btnBack; // declaring JButton
	private JScrollPane scrollPane; // Declaring JScrollPane

	public StatisticsGUI() throws IOException {

		Data.accounts.loadFile("Players.txt"); // loads Players.txt

		setContentPane (new JLabel(new ImageIcon ("Images/stats.png")));

		setSize(350,500); // set size of window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		getContentPane().setLayout(null); // setting layout to null
		setLocationRelativeTo(null);

		String[] columns = {"Quizzes", "Total\nQuestions", "Correct Answers", "Overall %", "Average Time"}; // creating columns for JTable
		Object [][] data = {{Data.accounts.getStats(Data.userName).getNumQuizzes(), Data.accounts.getStats(Data.userName).getNumTotal(), Data.accounts.getStats(Data.userName).getNumCorrect(),
			Data.accounts.getStats(Data.userName).getOverallPercentage(), Data.accounts.getStats(Data.userName).getAverageTime()}}; // Creating and declaring 2D Arrays which contain data for the JTable

		table = new JTable(data, columns); // creating JTable with data and columns
		table.setToolTipText("Quizzes, Total Questions Attempted, Correct Answers, Overall %, Average Time"); // setting tool tip for table
		table.setEnabled(false); // setEnabled is set to false for table
		table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null)); // setting border for table
		table.setFillsViewportHeight(true); // set fill viewport height to true
		table.setPreferredScrollableViewportSize(new Dimension(500, 50)); // set preferred scroallable viewport size to 500,50

		scrollPane = new JScrollPane(table); // created JScrollPane with table
		scrollPane.setEnabled(false); // setEnabled false for scrollPane

		btnBack = new JButton("Back"); // created JButton
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 15)); // setting font for back button

		lblPicture = new JLabel(new ImageIcon(Data.accounts.getPic(Data.userName))); // creating JLabel with player's profile picture

		lblPlayerName = new JLabel("<html>" + Data.accounts.getName(Data.userName) + "'s Statistics" + "<html>"); // created JLabel with player's name

		picLogo = new JLabel(new ImageIcon("tree.png"));	 // created label with picture of tree

		userName = new JLabel("<html>" + "UserName: " + Data.userName + "<html>"); // created label for player's username
		userName.setFont(new Font("Comic Sans MS", Font.PLAIN, 11)); // setting font for userName
		lblPlayerName.setFont(new Font("Comic Sans MS", Font.PLAIN, 20)); // setting font for playername
		userName.setHorizontalAlignment(SwingConstants.CENTER); // centering userName
		lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER); // centering PlayerName

		lblPicture.setBounds(138, 79, 60, 60); // setting bounds for button, scrollpane, and labels
		btnBack.setBounds(10, 75, 89, 23);
		lblPlayerName.setBounds(10, 150, 324, 85);
		scrollPane.setBounds(10, 278, 324, 44);
		userName.setBounds(10, 232, 314, 14);
		picLogo.setBounds(99, 333, 138, 127);

		getContentPane().add(lblPicture); // adding scrollbar, button, and labels to frame
		getContentPane().add(lblPlayerName);
		getContentPane().add(userName);
		getContentPane().add(scrollPane);
		getContentPane().add(btnBack);
		getContentPane().add(picLogo);

		btnBack.addActionListener(this); // adding actionlistener to back button


		setVisible(true); // setting the visibility to true
		setResizable(false); // cannot resize window
	}

	public void actionPerformed(ActionEvent e) { // actionPerformed method
		if(e.getSource() == btnBack){ // when back button is pressed, it opens HomeMenuGUI and disposes this gui
			try {
				new HomeMenuGUI();
			} catch (IOException e1) {
			}
			dispose();
		}
	}

	public static void main(String[] args) throws IOException {
		StatisticsGUI statistics = new StatisticsGUI();
	}
}