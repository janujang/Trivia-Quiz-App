import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;

/*
 * Author: Janujan Gathieswaran
 * Date: January 24 2017
 * Description: Check box GUI to display the questions for check box questions
 * 
 * Method List:
 * 		Constructors
 * 			QuestionCBGUI(QuestionCB cbQ)  //default constructor to pass in a check box object and run GUI
 * 
 * 		Functions
 * 			setTimerTitle (String txt) //method to set the timer title
 * 			setQuestionNum (String txt) //method to set the question number
 * 			void updateProgressBar (int maxValue, int currentValue) //method to update the progress bar by passing 
 * 																	in the maximum value and the value that should be displayed
 * 			public void viewAnswer () //method to display the correct answer
 *
 * 		Getters
 * 			boolean getCloseState ()
 * 			boolean getBtnPressed ()
 * 			boolean isInTimer () 
 * 			int getInputSize () //method to get the input array size which has the items that are selected
 * 
 * 		Self-Testing Main
 * 			static void main(String[] args) //create an object of the class
 */

public class QuestionCBGUI extends JFrame implements ActionListener{

	//---[Variable Declaration]------
	//ok button to check selected check boxes
	private JButton btnOK;

	//new colours for green and red
	private Color green = new Color (0,178,51);
	private Color red = new Color (178,0,0);
	
	//close state boolean to check if window should be closed in Quiz GUI
	private boolean closeState = false; 

	//inTimer boolean to check if timer is performing an action 
	private boolean inTimer = false; 

	//incorrect boolean to check if answer is incorrect
	private boolean incorrect = false; 

	//btnPressed boolean to check if any button is pressed
	private boolean btnPressed = false; 

	//array list for selected check boxes
	private ArrayList<String> input;

	//variable for size of options
	private int optionSize;

	//check box question object for global access throughout the program
	private QuestionCB cbQ;

	//array of check boxes
	private JCheckBox [] checkBox;

	//panel for check box items
	private JPanel p;

	//variable for timer
	private Timer timer;

	//label for count down timer, question title and question number
	private JLabel lblTimer, lblTitle, lblQuestionNum;

	//progress bar to show countdown graphically
	private JProgressBar progress;

	//variables for counter limit and delay for swing timer
	private int counter = 2; 
	private int delay = 1000; 
	//------------------------

	//default constructor to run GUI
	public QuestionCBGUI(QuestionCB cbQ) throws InterruptedException {
		setSize(500,700);
		getContentPane().setBackground(new Color(201,77,63));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//set the checkbox choice object in this GUI to the object passed in 
		this.cbQ = cbQ;

		//Reference for cross platform look
		//https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		try {
			UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
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

		//Reference for JProgressBar 
		//https://docs.oracle.com/javase/8/docs/api/javax/swing/JProgressBar.html
		progress = new JProgressBar();
		progress.setBounds(150,5,200,30);
		add(progress);

		//set the option size to the size of the options of the check box object
		optionSize = cbQ.getOptions().size();
		//System.out.println("Option Size: " + optionSize);

		//create a JPanel with a grid layout
		p = new JPanel();
		p.setLayout(new GridLayout (optionSize,1,5,5));
		p.setBounds(28, 200, 443, 250);
		p.setBackground(new Color(201,77,63));
		getContentPane().add(p);

		//create an array of check boxes with the option size 
		checkBox = new JCheckBox [optionSize];

		//add each check box to the panel and set the text to the options in order
		checkBox[0] = new JCheckBox(cbQ.getOptions().get(0));
		p.add(checkBox[0]);
		checkBox[0].addActionListener(this);

		checkBox[1] = new JCheckBox(cbQ.getOptions().get(1));
		p.add(checkBox[1]);
		checkBox[1].addActionListener(this);

		checkBox[2] = new JCheckBox(cbQ.getOptions().get(2));
		p.add(checkBox[2]);
		checkBox[2].addActionListener(this);

		//add a fourth or fifth check box if required
		if(optionSize == 4){
			checkBox[3] =  new JCheckBox(cbQ.getOptions().get(3));
			p.add(checkBox[3]);
			checkBox[3].addActionListener(this);
		}
		else if(optionSize == 5){
			checkBox[3] =  new JCheckBox(cbQ.getOptions().get(3));
			p.add(checkBox[3]);
			checkBox[3].addActionListener(this);

			checkBox[4] =  new JCheckBox(cbQ.getOptions().get(4));
			p.add(checkBox[4]);
			checkBox[4].addActionListener(this);
		}

		//create question number label
		lblQuestionNum = new JLabel ("Results");
		lblQuestionNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestionNum.setForeground(Color.WHITE);
		lblQuestionNum.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblQuestionNum.setBounds(0, 60, 500, 50);
		getContentPane().add(lblQuestionNum);

		//create question title and center text
		lblTitle = new JLabel("");
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTitle.setBounds(32, 86, 443, 139);
		getContentPane().add(lblTitle);
		lblTitle.setText("<html>" + cbQ.getQuestion() + "</html>"); //set the text to the title of the question and text wrapping

		//create an ok button 
		btnOK = new JButton ("OK");
		btnOK.addActionListener(this);
		btnOK.setBounds(200,600,100,50);
		add(btnOK);

		//create timer label for count down and center text
		lblTimer = new JLabel("");
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTimer.setForeground(Color.WHITE);
		lblTimer.setBounds(218, 40, 61, 20);
		getContentPane().add(lblTimer);

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
	//method to display the correct answer
	public void viewAnswer () {
		for (int i = 0; i < optionSize; i++) {
			checkBox[i].setEnabled(false);
			String option = checkBox[i].getText();

			if (cbQ.checkSelectedItem(option)) {
				checkBox[i].setBackground(green);
			}
		}
	}
	//getters for the size of input array, close state, button pressed and in timer booleans
	public int getInputSize () {
		return input.size();
	}
	public boolean getCloseState () {
		return closeState;
	}
	public boolean getBtnPressed () {
		return btnPressed;
	}
	public boolean isInTimer () {
		return inTimer;
	}

	//method to display the correct answer
	public void actionPerformed(ActionEvent e) {
		//set button pressed boolean to true as action performed was entered
		btnPressed = true;

		//create an input array to store items selected
		input = new ArrayList<String>();

		//if ok button is pressed, check the items selected
		if (e.getSource() == btnOK) {
			for(int i = 0; i < optionSize; i++){
				if(checkBox[i].isSelected()){
					//add items selected to input array 
					input.add(cbQ.getOptions().get(i));
				}
			}
			//if the option selected are correct, set incorrect to false and
			if (cbQ.checkAnswer(input))
			{
				//enhanced if statement (if timer is greater than 15 seconds add 10 points, else 5 points
				Data.addPoints(Integer.parseInt(lblTimer.getText())>15? 10: 5);
				
				//add the time by subtracting the time limit by the current time
				Data.addTime(Data.timeLimit - Integer.parseInt(lblTimer.getText()));

				incorrect = false;
				
				Data.correct++;//add to correct variable in data class

				//loop through selected options and make the correct options green
				for (int i = 0; i < input.size(); i++) {
					String selected = input.get(i);

					for (int j = 0; j < optionSize; j++) {
						if (selected.equals(checkBox[j].getText())) {
							checkBox[j].setBackground(green);
						}
					}
				}
				timer.start(); //delay to see chosen answers
			}
			else //answers are wrong, set incorrect to true and
			{
				incorrect = true;
				
				Data.incorrect++;//add to incorrect variable in data class
				
				//loop through selected options and display the correct and incorrect answers chosen
				for (int i = 0; i < input.size(); i++) {
					String selected = input.get(i);

					if (cbQ.checkSelectedItem(selected)) {
						for (int j = 0; j < optionSize; j++) {
							if (selected.equals(checkBox[j].getText())) {
								checkBox[j].setBackground(green);
							}
						}
					}
					else
					{
						for (int j = 0; j < optionSize; j++) {
							if (selected.equals(checkBox[j].getText())) {
								checkBox[j].setBackground(red);
							}
						}

					}
					timer.start(); //delay to see chosen answers
				}
			}
			//			//enhanced for loop for print out the items of the input array
			//			for (String f: input) {
			//				System.out.print(f + ", ");
			//			}

			//Reference for enhanced if statement
			//https://www.youtube.com/watch?v=w41D0V-BnKQ&index=31&list=PLFE2CE09D83EE3E28
			//System.out.println(cbQ.checkAnswer(input)?"Correct":"Wrong");

		}
	}
	//self-testing main
	public static void main(String[] args) throws InterruptedException {
		//create options and answers array lists and create a check box question
		ArrayList<String> options = new ArrayList(Arrays.asList("Red","Orange","Caloon","Babarotu","Green"));
		ArrayList<String> answers = new ArrayList(Arrays.asList("Red","Orange","Green"));
		QuestionCB cbQ = new QuestionCB ("Which of the follow are colours?", options, answers);

		//create an object of the class and pass in the check box object
		QuestionCBGUI cbGUI = new QuestionCBGUI(cbQ);
	}
}
