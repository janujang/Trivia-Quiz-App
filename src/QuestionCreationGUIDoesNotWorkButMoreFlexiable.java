// Name: Pavneet Gill and Janujan 
// Data: 2017-01-22
// Description: This program creates the question for the quiz 
// Method List: 
//         actionPerformed () //checks if button is clicked 
//
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class QuestionCreationGUIDoesNotWorkButMoreFlexiable extends JFrame implements ActionListener {

	//creating private variables for buttons, options, textfields, radiobuttons and array lists 
	private JPanel contentPane; 

	//creating and declaring radio buttons for true and false, 
	private JRadioButton trueButton = new JRadioButton ("true");

	private JRadioButton falseButton = new JRadioButton ("false");

	private JLabel lblOptions = new JLabel("Options");

	private JLabel lblAnswer = new JLabel("Answer");

	private Border blackline = BorderFactory.createLineBorder(Color.BLACK); 

	private Border redline = BorderFactory.createLineBorder(Color.RED,10); 

	private JButton btnConfirm = new JButton ("Confirm"); 

	//creating textfields for the different options 
	private JTextField textField= new JTextField();
	private JTextField textField_1= new JTextField();;
	private JTextField textField_2= new JTextField();
	private JTextField textField_3=new JTextField();
	private JTextField textField_4 = new JTextField (); 

	//creating checkboxes for the different options
	private JCheckBox checkBox = new JCheckBox("");
	private JCheckBox checkBox_1 = new JCheckBox("");
	private JCheckBox checkBox_2 = new JCheckBox("");
	private JCheckBox checkBox_3 = new JCheckBox("");
	private JCheckBox checkBox_4 = new JCheckBox (""); 

	//creating radiobuttons for the different options 
	private JRadioButton radioButton = new JRadioButton (); 
	private JRadioButton radioButton_1 = new JRadioButton (); 
	private JRadioButton radioButton_2 = new JRadioButton (); 
	private JRadioButton radioButton_3 = new JRadioButton (); 
	private JRadioButton radioButton_4 = new JRadioButton (); 

	//creating button groups 
	private ButtonGroup checkBoxes = new ButtonGroup (); 
	private ButtonGroup trueAndFalse = new ButtonGroup (); 
	
	//creating questionTitle textArea
	private JTextArea txtQuestionTitle = new JTextArea();

	//creating array of options for comboBox
	private String [] numOfQuestions = {"3", "4", "5"}; 

	//creating an instance of button 
	private JButton btnNext = new JButton("Next");

	private JScrollPane scrollBar;

	//creating variable to check selected question
	private String selectedItem;

	//creating a variable for question type
	private int questionType = 0;

	private int size = 10;

	//creationg a variable for questionTitle
	private String questionTitle;

	private String answer;

	//creating and declaring arraylists 
	private ArrayList<String> optionslist = new ArrayList<String>();

	private ArrayList<String> answersCB;

	private ArrayList<String> checkBoxAnswer = new ArrayList<String>(); 
	
	private ArrayList<String> options = new ArrayList<String>(); 
	
	//creating array for options with box
	private String boxOptions [] = {"Select", "True or False", "Multiple Choice", "Check Box"}; 

	//creating comboBox
	private JComboBox cBQuestionType= new JComboBox(boxOptions);

	//creating JLabel for question type 
	private JLabel lblQuestionType = new JLabel("Question Type");

	//creating comboBox for num of options 
	private JComboBox cBNumQuestions = new JComboBox(numOfQuestions);
	
	//creating JLabel for number of options 
	private JLabel lblNumOptions = new JLabel("# of Options");
	
	//declaring string num of options 
	private String numOfOptions; 

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public QuestionCreationGUIDoesNotWorkButMoreFlexiable() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize (500,700);

		getContentPane().setLayout(null);
		
		//adding buttons to group 
		checkBoxes.add(checkBox);
		checkBoxes.add(checkBox_1);
		checkBoxes.add(checkBox_2);
		checkBoxes.add(checkBox_3);
		checkBoxes.add(checkBox_4); 

		cBNumQuestions.setVisible(false);
		
		cBNumQuestions.setActionCommand("hi");
		
		//adding buttons to group 
		trueAndFalse.add(trueButton);
		trueAndFalse.add(falseButton);

		
		cBQuestionType.setActionCommand("hello");//action command for comboBox
		cBQuestionType.setSelectedIndex (0); //setting default selected
		getContentPane().add(cBQuestionType);
		
		cBNumQuestions.setSelectedItem("3"); //setting default selection
		
		//creating scroll bar and adding to quesitonTitle
		scrollBar = new JScrollPane(txtQuestionTitle);

		scrollBar.setVerticalScrollBarPolicy( JScrollPane. VERTICAL_SCROLLBAR_ALWAYS); //setting default scroll bar 

		scrollBar.setBorder(blackline); //setting border of scroll bar 

		//setting the x, y, width, height 
		lblAnswer.setBounds(30, 238, 47, 20);
		lblOptions.setBounds(31, 209, 86, 20);
		cBNumQuestions.setBounds(350, 178, 56, 20);
		lblQuestionType.setBounds(30, 178, 86, 20);
		cBQuestionType.setBounds(126, 178, 112, 20);

		txtQuestionTitle.setWrapStyleWord(true); 
		txtQuestionTitle.setLineWrap(true);
		txtQuestionTitle.setOpaque(false);//Blending with background
		
		txtQuestionTitle.setText("Untitled Question");
		
		scrollBar.setBounds(22, 81, 437, 64);

		if (Data.questionNum == Data.totalQuestions-1) { //checking if questionNum is equal to total questions

			btnNext.setEnabled(false);

			btnNext.setBounds(0, 0, 0, 0);

			btnConfirm.setBounds (241, 382, 89, 23); 
		}
		

		//adding things to frame 
		getContentPane().add(lblOptions);

		getContentPane().add(scrollBar);

		getContentPane().add (trueButton); 

		getContentPane().add(lblAnswer);

		getContentPane().add (falseButton);

		getContentPane().add(textField);

		getContentPane().add(textField_1);

		getContentPane().add(textField_2);

		getContentPane().add(textField_3);

		getContentPane().add(checkBox);

		getContentPane().add(checkBox_1);

		getContentPane().add(checkBox_2);

		getContentPane().add(checkBox_3);

		getContentPane().add(radioButton); 

		getContentPane().add(radioButton_1); 

		getContentPane().add(radioButton_2); 

		getContentPane().add(radioButton_3); 

		getContentPane().add(btnNext);

		getContentPane().add (btnConfirm);
		
		getContentPane().add(cBNumQuestions);
		
		getContentPane().add(lblNumOptions);
		
		getContentPane().add (radioButton_4); 
		
		getContentPane().add (textField_4);
		
		getContentPane().add (checkBox_4); 
		
		getContentPane().add(lblQuestionType); 
		
		//adding actionListeners 
		btnNext.addActionListener(this);
		
		btnConfirm.addActionListener(this);
		
		cBNumQuestions.addActionListener(this);
		
		cBQuestionType.addActionListener(this);

		setLocationRelativeTo (null);
		
		setVisible (true); 
	}

	public void actionPerformed(ActionEvent e) { //every time a button is clicked 
		
		if (e.getActionCommand().equals("hello") || e.getActionCommand().equals("hi"))
		{
			//getting selected items on combox box 
			selectedItem = (String) cBQuestionType.getSelectedItem();
			
			numOfOptions = (String) cBNumQuestions.getSelectedItem();  

			//switching depending on selection item
			switch (selectedItem)
			{
			case "True or False":
			{
				cBNumQuestions.setVisible(false);
				
				questionType = 1; //setting question Type
				
				lblNumOptions.setBounds(0, 0, 0, 0);

				//setting bounds of new items or moving old items off screen 
				lblOptions.setBounds(1000,1000,1000,1000);
				
				btnNext.setBounds(370, 31, 89, 23);

				lblAnswer.setBounds(31, 209, 86, 20);

				trueButton.setBounds(126, 208, 50, 23);

				falseButton.setBounds (178, 208, 60, 23);
				
				trueButton.setSelected(true);

				textField.setBounds(0,0,0,0);

				textField_1.setBounds(0,0,0,0);

				textField_2.setBounds(0,0,0,0);

				textField_3.setBounds(0,0,0,0);
				
				textField_4.setBounds(0,0,0,0);

				checkBox.setBounds(0,0,0,0);

				checkBox_1.setBounds(0,0,0,0);

				checkBox_2.setBounds(0,0,0,0);

				checkBox_3.setBounds(0,0,0,0);
				
				checkBox_4.setBounds(0, 0, 0, 0);

				radioButton.setBounds(0,0,0,0);

				radioButton_1.setBounds(0,0,0,0);

				radioButton_2.setBounds(0,0,0,0);

				radioButton_3.setBounds(0,0,0,0);
				
				radioButton_4.setBounds(0, 0, 0, 0);

				break; 
			}
			case "Multiple Choice":
			{
				questionType = 2; 
				
				//setting bounds of new items or moving old items off screen 
				cBNumQuestions.setVisible(true);
				
				btnNext.setBounds(370, 31, 89, 23);
				
				lblNumOptions.setBounds(257, 178, 68, 20);

				lblAnswer.setBounds(324, 209, 47, 20);

				lblOptions.setBounds(31, 209, 86, 20);

				trueButton.setBounds(0,0,0,0);

				falseButton.setBounds (0,0,0,0);

				radioButton.setBounds(0,0,0,0);

				radioButton_1.setBounds(0,0,0,0);

				radioButton_2.setBounds(0,0,0,0);

				radioButton_3.setBounds(0,0,0,0);
				
				radioButton_4.setBounds(0,0,0,0);
				
				//Changing based on selection of number of questions 
				if (numOfOptions.equalsIgnoreCase("4")) 
				{
					textField.setBounds(126, 238, 186, 20);

					textField_1.setBounds(126, 281, 186, 20);

					textField_2.setBounds(126, 326, 186, 20);
					
					checkBox_1.setBounds(324, 278, 26, 23);

					checkBox_2.setBounds(324, 322, 26, 23);
					
					checkBox.setBounds(324, 235, 26, 23);
					
					textField_3.setBounds(126, 371, 186, 20);
					
					checkBox_3.setBounds(324, 368, 26, 23);
					
					textField_4.setBounds (0,0,0,0);
					
					checkBox_4.setBounds(0,0,0,0);
				}
				else if (numOfOptions.equalsIgnoreCase("3"))
				{
					textField.setBounds(126, 238, 186, 20);

					textField_1.setBounds(126, 281, 186, 20);

					textField_2.setBounds(126, 326, 186, 20);
					
					checkBox_1.setBounds(324, 278, 26, 23);

					checkBox_2.setBounds(324, 322, 26, 23);
					
					checkBox.setBounds(324, 235, 26, 23);
					
					textField_3.setBounds(0,0,0,0);
					
					checkBox_3.setBounds(0,0,0,0);
					
					textField_4.setBounds (0,0,0,0);
					
					checkBox_4.setBounds(0,0,0,0);
					
				}
				else if (numOfOptions == "5")
				{
					textField.setBounds(126, 238, 186, 20);

					textField_1.setBounds(126, 281, 186, 20);

					textField_2.setBounds(126, 326, 186, 20);
					
					checkBox_1.setBounds(324, 278, 26, 23);

					checkBox_2.setBounds(324, 322, 26, 23);
					
					checkBox.setBounds(324, 235, 26, 23);
					
					textField_3.setBounds(126, 371, 186, 20);
					
					checkBox_3.setBounds(324, 368, 26, 23);
					
					textField_4.setBounds (126, 415, 186,20);
					
					checkBox_4.setBounds(324, 412, 26, 23);
				}

				break; 
			}
			case "Check Box": {
				questionType = 3;

				//setting bounds of new items or moving old items off screen 
				cBNumQuestions.setVisible(true);
				
				checkBox.setBounds(0,0,0,0);
				
				btnNext.setBounds(370, 31, 89, 23);
				
				lblNumOptions.setBounds(257, 178, 68, 20);
				
				checkBox_1.setBounds(0, 0, 0, 0);

				checkBox_2.setBounds(0,0,0,0);

				checkBox_3.setBounds(0,0,0,0);
				
				checkBox_4.setBounds(0,0,0,0);

				radioButton.setBounds(324, 206, 26, 23);

				radioButton_1.setBounds(324, 244, 26, 23);

				radioButton_2.setBounds(324, 287, 26, 23);

				radioButton_3.setBounds(324, 333, 26, 23);
				
				radioButton_4.setBounds(324, 379, 26, 23);

				lblAnswer.setBounds(324, 209, 47, 20);

				lblOptions.setBounds(31, 209, 86, 20);

				//Changing based on selection of number of questions 
				if (numOfOptions == "4")
				{
					textField.setBounds(126, 238, 186, 20);

					textField_1.setBounds(126, 281, 186, 20);

					textField_2.setBounds(126, 326, 186, 20);
					
					radioButton_1.setBounds(324, 278, 26, 23);

				    radioButton_2.setBounds(324, 322, 26, 23);
					
					radioButton.setBounds(324, 235, 26, 23);
					
					textField_3.setBounds(126, 371, 186, 20);
					
					radioButton_3.setBounds(324, 368, 26, 23);
					
					textField_4.setBounds (0,0,0,0);
					
					radioButton_4.setBounds(0,0,0,0);
				}
				else if (numOfOptions =="3")
				{
					textField.setBounds(126, 238, 186, 20);

					textField_1.setBounds(126, 281, 186, 20);

					textField_2.setBounds(126, 326, 186, 20);
					
					radioButton_1.setBounds(324, 278, 26, 23);

				    radioButton_2.setBounds(324, 322, 26, 23);
					
					radioButton.setBounds(324, 235, 26, 23);
					
					textField_3.setBounds(0,0,0,0);
					
					radioButton_3.setBounds(0,0,0,0);
					
					textField_4.setBounds (0,0,0,0);
					
					radioButton_4.setBounds(0,0,0,0);
					
				}
				else if (numOfOptions == "5")
				{
					textField.setBounds(126, 238, 186, 20);

					textField_1.setBounds(126, 281, 186, 20);

					textField_2.setBounds(126, 326, 186, 20);
					
					radioButton_1.setBounds(324, 278, 26, 23);

				    radioButton_2.setBounds(324, 322, 26, 23);
					
					radioButton.setBounds(324, 235, 26, 23);
					
					textField_3.setBounds(126, 371, 186, 20);
					
					radioButton_3.setBounds(324, 368, 26, 23);
					
					textField_4.setBounds (126, 415, 186,20);
					
					radioButton_4.setBounds(324, 412, 26, 23);
				}

				trueButton.setBounds(0,0,0,0);

				falseButton.setBounds (0,0,0,0);

				break; 

			}
			}
		}
		else if (e.getSource().equals(btnNext))
		{

			Data.questionNum++; //adding to total questions

			questionTitle = txtQuestionTitle.getText();//getting questionTitle 
			
			if (questionTitle.equalsIgnoreCase("Untitled Question")) 
			{
				txtQuestionTitle.setBorder(redline); //make border red if question is unselected
			}
			else 
			{
				if (selectedItem.equals("True or False")) {

					//checking which button is selected and setting answer
					if (falseButton.isSelected())
					{
						answer = "True";
					}
					else if (trueButton.isSelected ())
					{
						answer = "False"; 
					}

						//create new true false question
						QuestionTF tf = new QuestionTF(questionTitle, Boolean.parseBoolean(answer));

						//add question to quiz
						Data.q.addQuestion(tf);

				}
				else if (selectedItem.equals("Multiple Choice"))
				{
					//getting inputs of textFields for different options 
					String optionMC [] = {textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText()}; 

					//ArrayList<String> options = new ArrayList<String>(); // all possible options

					//adding options to list 
					for (int i=0; i< optionMC.length; i++)
					{
						if (!optionMC[i].isEmpty()) //to check if field is empty
						{
							optionslist.add(optionMC[i]);
						}
					}
					
					//checking which check box is selected and setting answer based on it 
					if (checkBox.isSelected())
					{
						answer = optionMC[0]; 
					}
					else if (checkBox_1.isSelected())
					{
						answer = optionMC[1]; 
					}
					else if (checkBox_2.isSelected())
					{
						answer = optionMC[2]; 
					}
					else if (checkBox_3.isSelected())
					{
						answer = optionMC[3]; 
					}					
					else if(checkBox_4.isSelected())
					{
						answer = optionMC[4]; 
					}
					
					QuestionMC mc = new QuestionMC (questionTitle, optionslist, answer); //creating new multiple choice question

					Data.q.addQuestion(mc); //adding multiple choice question to quiz 

				}
				else if (selectedItem.equals("Check Box"));
				{
					//declaring and assigning  array of options 
					String optionCB [] = {textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText()}; 
					
					//for loop to check if selected is empty 
					for (int i=0; i< optionCB.length; i++)
					{
						if (!optionCB[i].isEmpty())
						{
							options.add(optionCB[i]);
						}
					}

					//reads selected radio button and add answer to array list based on selection(s)
					if (radioButton.isSelected())
					{
						checkBoxAnswer.add(textField.getText());
					}

					if (radioButton_1.isSelected()); 
					{
						checkBoxAnswer.add(textField_1.getText());
					}

					if (radioButton_2.isSelected())
					{
						checkBoxAnswer.add(textField_2.getText());
					}

					if (radioButton_3.isSelected())
					{
						checkBoxAnswer.add(textField_3.getText());
					}
					
					if(radioButton_4.isSelected())
					{
						checkBoxAnswer.add(textField_4.getText());
					}

					QuestionCB cb = new QuestionCB(questionTitle, options, checkBoxAnswer); //creates check box question

					Data.q.addQuestion(cb); //adding question to list 


				}
				//closing previous window and calling this calls again 
				super.dispose();
				main(null);	
				//}
			}
		}
		else if (e.getSource() == btnConfirm)
		{
			questionTitle = txtQuestionTitle.getText();//getting questionTitle 
			
			if (selectedItem.equals("True or False")) {

				//checking which button is selected and setting answer
				if (falseButton.isSelected())
				{
					answer = "True";
				}
				else if (trueButton.isSelected ())
				{
					answer = "False"; 
				}

					//create new true false question
					QuestionTF tf = new QuestionTF(questionTitle, Boolean.parseBoolean(answer));

					//add question to quiz
					Data.q.addQuestion(tf);
			}
			else if (selectedItem.equals("Multiple Choice"))
			{
				//getting inputs of textFields for different options 
				String optionMC [] = {textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText()}; 

				//ArrayList<String> options = new ArrayList<String>(); // all possible options

				//adding options to list 
				for (int i=0; i< optionMC.length; i++)
				{
					if (!optionMC[i].isEmpty()) //to check if field is empty
					{
						optionslist.add(optionMC[i]);
					}
				}
				
				//checking which check box is selected and setting answer based on it 
				if (checkBox.isSelected())
				{
					answer = optionMC[0]; 
				}
				else if (checkBox_1.isSelected())
				{
					answer = optionMC[1]; 
				}
				else if (checkBox_2.isSelected())
				{
					answer = optionMC[2]; 
				}
				else if (checkBox_3.isSelected())
				{
					answer = optionMC[3]; 
				}					
				else if(checkBox_4.isSelected())
				{
					answer = optionMC[4]; 
				}
				
				QuestionMC mc = new QuestionMC (questionTitle, optionslist, answer); //creating new multiple choice question

				Data.q.addQuestion(mc); //adding multiple choice question to quiz 
			}
			else if (selectedItem.equals("Check Box"))
			{
				//declaring and assigning  array of options 
				String optionCB [] = {textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText()}; 
				
				//for loop to check if selected is empty 
				for (int i=0; i< optionCB.length; i++)
				{
					if (!optionCB[i].isEmpty())
					{
						options.add(optionCB[i]);
					}
				}

				//reads selected radio button and add answer to array list based on selection(s)
				if (radioButton.isSelected())
				{
					checkBoxAnswer.add(textField.getText());
				}

				if (radioButton_1.isSelected()); 
				{
					checkBoxAnswer.add(textField_1.getText());
				}

				if (radioButton_2.isSelected())
				{
					checkBoxAnswer.add(textField_2.getText());
				}

				if (radioButton_3.isSelected())
				{
					checkBoxAnswer.add(textField_3.getText());
				}
				
				if(radioButton_4.isSelected())
				{
					checkBoxAnswer.add(textField_4.getText());
				}

				QuestionCB cb = new QuestionCB(questionTitle, options, checkBoxAnswer); //creates check box question

				Data.q.addQuestion(cb); //adding question to list 
			}
		
			Data.q.writeToFile(Data.q.getQuizName(), Data.q.toString(), false);
			
			try {
				new HomeMenuGUI ();
			} catch (IOException e1) {
				
			} 
		}

	}

	public static void main(String[] args) {//main 
		QuestionCreationGUIDoesNotWorkButMoreFlexiable question = new QuestionCreationGUIDoesNotWorkButMoreFlexiable (); //cration questionCreation object
	}

}