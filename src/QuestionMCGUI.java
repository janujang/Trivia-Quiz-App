import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
/*
 * Author: Janujan Gathieswaran
 * Date: January 24 2017
 * Description: Multiple choice GUI to display the questions for multiple choice questions
 * 
 * Method List:
 * 		Constructors
 * 			uestionMCGUI(QuestionMC mcQ)  //default constructor to pass in a multiple choice object and run GUI
 * 
 * 		Functions
 * 			setTimerTitle (String txt) //method to set the timer title
 *  		setQuestionNum (String txt) //method to set the question number
 * 			void updateProgressBar (int maxValue, int currentValue) //method to update the progress bar by passing 
 * 																	in the maximum value and the value that should be displayed
 * 			public void viewAnswer () //method to display the correct answer
 *
 * 		Getters
 * 			boolean getCloseState ()
 * 			boolean getBtnPressed ()
 * 			boolean isInTimer () 
 * 
 * 		Self-Testing Main
 * 			static void main(String[] args) //create an object of the class
 */

public class QuestionMCGUI extends JFrame implements ActionListener{


	//---[Variable Declaration]------
	//new colours for green and red
	private Color green = new Color (0,178,51);
	private Color red = new Color (178,0,0);
	
	//array of buttons for options
	private JButton [] btnOptions; 

	//close state boolean to check if window should be closed in Quiz GUI
	private boolean closeState = false; 

	//inTimer boolean to check if timer is performing an action 
	private boolean inTimer = false; 

	//incorrect boolean to check if answer is incorrect
	private boolean incorrect = false; 

	//btnPressed boolean to check if any button is pressed
	private boolean btnPressed = false; 

	//multiple choice object for global access throughout the program
	private QuestionMC mcQ; 

	//variable for timer
	private Timer timer;

	//variable for the answer
	private String answer = "";

	//label for count down timer, question title and question number
	private JLabel lblTimer, lblTitle, lblQuestionNum;

	//progress bar to show countdown graphically
	private JProgressBar progress;

	//panel for multiple choice buttons
	private JPanel p;

	//variables for counter limit, delay for swing timer and option size
	private int counter = 2; 
	private int delay = 500; 
	private int optionSize = 0;

	//-----------------------------------

	public QuestionMCGUI(QuestionMC mcQ) throws InterruptedException {
		setSize(500,700);
		setResizable(false);
		getContentPane().setBackground(new Color(201,77,63));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//set the multiple choice object in this GUI to the object passed in 
		this.mcQ = mcQ;

		//Reference for cross platform look
		//https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		try {
			UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		//Reference to make a swing timer with a particular action listener
		//http://stackoverflow.com/questions/13503788/confusion-with-the-java-swing-timer
		ActionListener action = new ActionListener()
		{   
			public void actionPerformed(ActionEvent event)
			{
				//set in timer boolean to true
				inTimer = true;

				//if counter is less than 0, check if the answer is incorrect and display the correct answer
				if(counter < 0)
				{
					if (incorrect) {
						viewAnswer();
						timer.start();
						incorrect = false;
					}
					else {
						timer.stop();
						closeState = true; //window should not be closed
						//System.out.println("Done");
					}
				}
				else
				{
					counter--; //count down
					closeState = false; //window should not be closed
					//System.out.println("Hello");
				}
			}
		};
		timer = new Timer(delay, action); //create a new timer with the delay and particular action
		timer.setInitialDelay(0); 

		//set options size to the options of the multiple choice object
		optionSize = mcQ.getOptions().size();

		//Reference for JProgressBar 
		//https://docs.oracle.com/javase/8/docs/api/javax/swing/JProgressBar.html
		//create progress bar to show count down timer
		progress = new JProgressBar();
		progress.setBounds(150,5,200,30);
		getContentPane().add(progress);

		//create timer label for count down and center text
		lblTimer = new JLabel("");
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTimer.setForeground(Color.WHITE);
		lblTimer.setBounds(218, 40, 61, 20);
		getContentPane().add(lblTimer);

		//create question number label
		lblQuestionNum = new JLabel ("Results");
		lblQuestionNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestionNum.setForeground(Color.WHITE);
		lblQuestionNum.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblQuestionNum.setBounds(0, 60, 500, 50);
		getContentPane().add(lblQuestionNum);

		//create question title and center text
		lblTitle = new JLabel("Question Title");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTitle.setBounds(32, 86, 443, 139);
		lblTitle.setText("<html>" + mcQ.getQuestion()  + "</html>"); //set the text to the title of the question and text wrapping
		getContentPane().add(lblTitle);

		//create a JPanel to evenly space out buttons depending on number of buttons
		p = new JPanel();
		p.setLayout(new GridLayout (optionSize,1,5,5)); 
		p.setBounds(28, 237, 443, 393);
		p.setBackground(new Color(201,77,63));
		getContentPane().add(p);

		//create an array of buttons
		btnOptions = new JButton [5] ;

		//create each button
		btnOptions[0] = new JButton("New 1");
		p.add(btnOptions[0]);

		btnOptions[1] = new JButton("New 2");
		p.add(btnOptions[1]);

		btnOptions[2] = new JButton("New 3");
		p.add(btnOptions[2]);

		//add fourth or fifth button is option size is higher than 3
		if (optionSize == 4) {
			btnOptions[3] = new JButton("New 4");
			p.add(btnOptions[3]);
		}
		else if (optionSize == 5) {
			btnOptions[3] = new JButton("New 4");
			p.add(btnOptions[3]);

			btnOptions[4] = new JButton("New 5");
			p.add(btnOptions[4]);
		}

		//loop through buttons and set the text to the different options and add an action listener
		for (int i = 0; i<optionSize; i++) {
			btnOptions[i].setText(mcQ.getOptions().get(i));
			btnOptions[i].addActionListener(this);
		}

		setVisible(true);
	}
	//method to set the timer title
	public void setTimerTitle (String txt) { 
		lblTimer.setText(txt);
	}

	//method to set the question number
	public void setQuestionNum (int num) { 
		lblQuestionNum.setText("Question " + num);
	}

	//method to update the progress bar by passing in the maximum value and the value that should be displayed
	public void updateProgressBar (int maxValue, int currentValue) {
		progress.setMaximum(maxValue);
		progress.setValue(currentValue);
	}

	//method to check answer
	public void viewAnswer () {
		//set the answer to the answer obtained from the multiple choice question
		answer = mcQ.getAnswer();

		//loop through the buttons and check if the text of a certain button is the same as the answer
		//and make it green
		for (int i = 0; i < optionSize; i++) {
			btnOptions[i].setEnabled(false);
			if (btnOptions[i].getText().equals(answer)) {
				btnOptions[i].setBackground(green);
			}
		}
	}

	//getters for the close state, button pressed and in timer booleans
	public boolean getCloseState () {
		return closeState;
	}
	public boolean getBtnPressed () {
		return btnPressed;
	}
	public boolean isInTimer () {
		return inTimer;
	}

	//method to check what button pressed 
	public void actionPerformed(ActionEvent e) {
		//set button pressed boolean to true as action performed was entered
		btnPressed = true;

		//loop through buttons and check which one pressed
		for (int i = 0; i < optionSize; i++) {
			if (e.getSource()==btnOptions[i]) {

				//if correct answer chosen, set incorrect to false and make the specific button green
				if (mcQ.checkAnswer(btnOptions[i].getText())) {
					//System.out.println("Correct answer");
					incorrect = false;
					Data.correct++;//add to correct variable in data class
					btnOptions[i].setBackground(green);
					timer.start(); //delay to see chosen answer

					//enhanced if statement (if timer is greater than 15 seconds add 10 points, else 5 points
					Data.addPoints(Integer.parseInt(lblTimer.getText())>15? 10: 5);

					//add the time by subtracting the time limit by the current time
					Data.addTime(Data.timeLimit - Integer.parseInt(lblTimer.getText()));
				}
				else { 
					//if wrong answer, set incorrect to true and make the specific button red
					//System.out.println("Wrong answer");
					incorrect = true;
					Data.incorrect++;//add to incorrect variable in data class
					btnOptions[i].setBackground(red);
					timer.start(); //delay to see chosen answer
				}
			}
		}
	}
	//self-testing main
	public static void main(String[] args) throws InterruptedException {
		//create an array of options and create a new question multiple choice object
		ArrayList<String> options = new ArrayList(Arrays.asList("Brandon", "Kevin", "Jeffrey", "Campos"));
		QuestionMC q = new QuestionMC("Hello", options, "Kevin");

		//create an object of the GUI and pass in the multiple choice object
		QuestionMCGUI qGUI = new QuestionMCGUI(q);
	}
}
