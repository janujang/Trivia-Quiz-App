import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
/*
 * Author: Janujan Gathieswaran
 * Date: January 24 2017
 * Description: Quiz GUI to play a game with different kinds of questions: multiple choice, true or false and check box. 
 * 				The GUI displays if the answer chosen is correct and displays the correct answer if incorrect. 
 * 
 * Method List:
 * 		Constructors
 * 			QuizGUI(String filename) //default constructor to run GUI

 * 		Functions
 * 			void actionPerformed (ActionEvent e) //method to check what buttons pressed
 * 			public int readSecondsLimitFromFile () //method to read seconds limit from file
 * 
 * 		Self-Testing Main
 * 			static void main(String[] args) //create an object of the class
 */
public class QuizGUI extends JFrame implements ActionListener {

	//--[Variable Declaration]--------
	//create a quiz object
	//private Quiz q = new Quiz();
	private Quiz q;

	//variable for type of question
	private int type = 0; 

	//button to go back home
	private JButton btnHome; 

	//label for title of quiz, category, results, correct and wrong answers, points and points number and average time
	private JLabel lblTitle, lblCategory, lblResults, lblCorrect, lblWrong, lblPoints, lblPointsNum, lblAverageTime, lblMoreInfo;

	//variable for current seconds passed
	private int currentSeconds = 0;

	//variable for time limit
	private int timeLimit = readSecondsLimitFromFile();

	//create object of true or false, multiple choice and check box GUIs
	private QuestionTFGUI tF;
	private QuestionMCGUI mC;
	private QuestionCBGUI cB;

	//variable for size of questions
	private int sizeOfQuestions; 

	//----------------------------------
	//constructor with file name as a parameter to run GUI
	public QuizGUI(String fileName) throws InterruptedException {
		//create a new quiz
		q = new Quiz();
		
		getContentPane().setBackground(new Color(201,77,63));

		setSize(500,700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo (null);
		getContentPane().setLayout(null);

		//Reference to set cross platform look in order for Mac UI to look similar to Windows
		//https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		try {
			UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		//read the quiz from a file 
		q.readFromFile(fileName);

		//create a quiz title label with the quiz name of the quiz object as an argument
		lblTitle = new JLabel(q.getQuizName());
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblTitle.setBounds(65, 304, 370, 131);
		getContentPane().add(lblTitle);

		//create a quiz title label with the quiz name of the quiz object as an argument
		lblCategory = new JLabel(q.getCategory());
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategory.setFont(new Font("Lucida Grande", Font.BOLD, 35));
		lblCategory.setBounds(65, 223, 370, 131);
		getContentPane().add(lblCategory);

		//create home button 
		btnHome = new JButton ();
		btnHome.setText("Home");
		btnHome.setBounds(195, 605, 110, 55);
		btnHome.addActionListener(this);
		btnHome.setVisible(false);
		getContentPane().add(btnHome);

		//create results label
		lblResults = new JLabel ("Results");
		lblResults.setForeground(Color.WHITE);
		lblResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblResults.setFont(new Font("Arial Black", Font.BOLD, 27));
		lblResults.setBounds(60, 6, 379, 78);
		lblResults.setVisible(false);
		getContentPane().add(lblResults);

		//create more information label
		lblMoreInfo = new JLabel ("More Information");
		lblMoreInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoreInfo.setForeground(Color.WHITE);
		lblMoreInfo.setFont(new Font("Lucida Grande", Font.BOLD, 23));
		lblMoreInfo.setBounds(46, 273, 408, 55);
		lblMoreInfo.setVisible(false);
		getContentPane().add(lblMoreInfo);

		//create correct answers label
		lblCorrect = new JLabel ("Correct Answers");
		lblCorrect.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorrect.setForeground(Color.WHITE);
		lblCorrect.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblCorrect.setBounds(46, 340, 408, 55);
		lblCorrect.setVisible(false);
		getContentPane().add(lblCorrect);

		//create wrong answers label
		lblWrong = new JLabel ("Wrong Answers");
		lblWrong.setHorizontalAlignment(SwingConstants.CENTER);
		lblWrong.setForeground(Color.WHITE);
		lblWrong.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblWrong.setBounds(50, 407, 399, 78);
		lblWrong.setVisible(false);
		getContentPane().add(lblWrong);

		//create points label title
		lblPoints = new JLabel ("Points");
		lblPoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoints.setForeground(Color.WHITE);
		lblPoints.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		lblPoints.setBounds(98, 175, 304, 86);
		lblPoints.setVisible(false);
		getContentPane().add(lblPoints);

		//create pointsNum label to display number
		lblPointsNum = new JLabel ("45");
		lblPointsNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointsNum.setForeground(Color.WHITE);
		lblPointsNum.setFont(new Font("Lucida Grande", Font.PLAIN, 78));
		lblPointsNum.setBounds(98, 96, 304, 115);
		lblPointsNum.setVisible(false);
		getContentPane().add(lblPointsNum);

		lblAverageTime = new JLabel("Average Time");
		lblAverageTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblAverageTime.setForeground(Color.WHITE);
		lblAverageTime.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblAverageTime.setBounds(60, 475, 379, 86);
		lblAverageTime.setVisible(false);
		getContentPane().add(lblAverageTime);

		//display the GUI
		setVisible (true);

		//delay of 15 seconds to display the tile of the quiz and picture
		Thread.sleep(15000);
		
		//set the size of the questions to the size of the questions array of the quiz object
		sizeOfQuestions = q.getQuestions().size();

		//set total questions in data class to the value of the total number of questions
		Data.totalQuestions = sizeOfQuestions;

		//set the time limit of the data class to the time limit specified in this class
		Data.timeLimit = timeLimit;

		//loop through the questions of the quiz
		for (int i = 0; i < sizeOfQuestions; i++) {

			//get the type of question
			type = q.getQuestions().get(i).getType();

			//switch case to check type of question and perform an action accordingly
			switch(type) {

			//true or false question
			case 1: {

				//call the true and false GUI and pass in the particular true or false question
				tF = new QuestionTFGUI ((QuestionTF) q.getQuestions().get(i));

				//set the question number on the window
				tF.setQuestionNum (i+1);

				//hide the window only for the first iteration
				if (i == 0) 
					setVisible(false);

				//reset current seconds to the time limit 
				currentSeconds = timeLimit;

				//loop to count down from specified time limit
				while (currentSeconds > 0)
				{
					//update the progress bar and the timer title to show a count down
					tF.updateProgressBar(timeLimit, currentSeconds);
					tF.setTimerTitle(Integer.toString(currentSeconds));

					//delay of 1 second
					Thread.sleep(1000);

					/*
					 * if the close boolean in true or false GUI is true, move to the next question
					 * move to the next question when an option is selected
					 */
					if (tF.getCloseState()) {
						break;
					}
					//stop counting down when an answer is chosen and the correct answer is being displayed 
					else if (!tF.getCloseState() && tF.isInTimer()) {
					}
					//count down 
					else 
					{
						currentSeconds--;
					}
				}
				//if no button is pressed in the true or false GUI, display the correct answer with a delay of 2 seconds
				if (!tF.getBtnPressed()) {
					tF.viewAnswer();
					Thread.sleep(2000);
				}
				tF.dispose(); 
				break;
			}
			//multiple choice question
			case 2: {
				//call the multiple choice GUI and pass in the particular multiple choice question
				mC = new QuestionMCGUI ((QuestionMC) q.getQuestions().get(i));

				//set the question number on the window
				mC.setQuestionNum (i+1);

				//hide the window only for the first iteration
				if (i == 0) 
					setVisible(false);

				//reset current seconds to the time limit 
				currentSeconds = timeLimit;

				//loop to count down from specified time limit
				while (currentSeconds > 0)
				{
					//update the progress bar and the timer title to show a count down
					mC.updateProgressBar(timeLimit, currentSeconds);
					mC.setTimerTitle(Integer.toString(currentSeconds));

					//delay of 1 second
					Thread.sleep(1000);

					/*
					 * if the close boolean in the multiple choice GUI is true, move to the next question
					 * move to the next question when an option is selected
					 */
					if (mC.getCloseState()) {
						break;
					}
					//stop counting down when an answer is chosen and the correct answer is being displayed 
					else if (!mC.getCloseState() && mC.isInTimer()) {
					}
					else 
					{
						currentSeconds--;//count down 
					}
				}
				//if no button is pressed in the multiple choice GUI, display the correct answer with a delay of 2 seconds
				if (!mC.getBtnPressed()) {
					mC.viewAnswer();
					Thread.sleep(2000);
				}
				mC.dispose(); 
				break;

			}
			//check box question
			case 3: {
				//call the checkbox GUI and pass in the particular checkbox question
				cB = new QuestionCBGUI ((QuestionCB) q.getQuestions().get(i));

				//set the question number on the window
				cB.setQuestionNum (i+1);

				//hide the window only for the first iteration
				if (i == 0) 
					setVisible(false);

				//reset current seconds to the time limit 
				currentSeconds = timeLimit;

				//loop to count down from specified time limit
				while (currentSeconds > 0)
				{
					//update the progress bar and the timer title to show a count down
					cB.updateProgressBar(timeLimit, currentSeconds);
					cB.setTimerTitle(Integer.toString(currentSeconds));

					//delay of 1 second
					Thread.sleep(1000);

					/*
					 * if the close boolean in the check box GUI is true, move to the next question
					 * move to the next question when an option is selected
					 */
					if (cB.getCloseState()) {
						break;
					}
					//stop counting down when an answer is chosen and the correct answer is being displayed 
					else if (!cB.getCloseState() && cB.isInTimer()) {
					}
					else 
					{
						currentSeconds--;//count down 
					}
				}
				//if no button is pressed in the check box GUI, display the correct answer with a delay of 2 seconds
				if (!cB.getBtnPressed()) {
					cB.viewAnswer();
					Thread.sleep(2000);
				}
				cB.dispose(); 
				break;
			}
			}
		}
		setVisible(true);

		//hide the title and category and display all the results information
		lblTitle.setVisible(false);
		lblCategory.setVisible(false);
		
		lblCorrect.setText("Correct Answers: " + Data.correct);
		lblWrong.setText("Wrong Answers: " + Data.incorrect);
		lblAverageTime.setText("Average Time: " + Data.getAverageTime() + " secs");
		lblPointsNum.setText(Integer.toString(Data.getPoints()));

		lblResults.setVisible(true);
		lblPoints.setVisible(true);
		lblWrong.setVisible(true);
		lblCorrect.setVisible(true);
		lblAverageTime.setVisible(true);
		lblPointsNum.setVisible(true);
		lblMoreInfo.setVisible(true);
		btnHome.setVisible(true);

		//JOptionPane.showMessageDialog(null, "Total Points: " + Data.getPoints());
	}
	//method to read seconds limit from file
	public int readSecondsLimitFromFile ()  {
		try {
			BufferedReader f = new BufferedReader (new FileReader ("time.txt"));
			return Integer.parseInt(f.readLine());
		}
		catch (IOException e) {
			return 0;
		}
	}
	//method to check what buttons pressed
	public void actionPerformed (ActionEvent e) { 
		try {
			new HomeMenuGUI ();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	//create an object of the quiz GUI and pass in American test file to test if it works
	public static void main(String[] args) throws InterruptedException {
		QuizGUI qGUI = new QuizGUI ("American History.txt");
	}
}
