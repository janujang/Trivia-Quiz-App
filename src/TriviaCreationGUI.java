//Name: Pavneet Gill 
//Date: 2017-01-23
//Description: This is a triviaCreationGUI which creates the quiz and sets the quiz name 
//       void actionPerformed //listens to button clicks 
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;


public class TriviaCreationGUI extends JFrame implements ActionListener {
	
	//declaring variables for txt fields, options, labels 
	private JTextField txtLabel;
	
	private JTextField txtQuizName;
	
	private JButton btnBack, btnCreate;
	
	private String options [] = {"","5","6","7","8","9","10"}; //options for comboBox
	
	private JComboBox comboBox = new JComboBox(options); //creating comboBox with number of questions
	
	private JTextField txtCategoryName;
	
	private JLabel lblError; 
	
	private JLabel lblCategoryError; 
	
	private JLabel lblErrorUnselected;
	
	private JLabel lblCategory = new JLabel("Category");
	
	private JLabel lblNumberOfQuestions; 
	
	private JLabel lblTriviaName; 

	public TriviaCreationGUI() {
		
		getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 11)); //setting font 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(500,420);
		
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(null);
		
		//creating jlabels and textfield 
		lblError = new JLabel("");
		
		lblCategoryError = new JLabel("");
		
		lblErrorUnselected = new JLabel(""); 
		
		txtLabel = new JTextField();
		
		//setting label to non editable 
		txtLabel.setEditable(false);
		
		txtLabel.setBackground(new Color(255, 255, 255));//setting background of label 
		
		txtLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 16)); //setting font of label 
		
		txtLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtLabel.setText("Trivia Creation");
		
		txtLabel.setBounds(0, 0, 484, 42); //setting bounds 
		
		getContentPane().add(txtLabel);
		
		txtLabel.setColumns(10);
		
		//creating jlabel for triviaName and setting bounds and adding to frame 
		lblTriviaName = new JLabel("Trivia Name");
		lblTriviaName.setFont(new Font("Segoe UI Historic", Font.PLAIN, 15));
		lblTriviaName.setHorizontalAlignment(SwingConstants.CENTER);
		lblTriviaName.setBounds(10, 87, 115, 20);
		getContentPane().add(lblTriviaName);
		
		//creating text field and setting bounds and adding to frame
		txtQuizName = new JTextField();
		txtQuizName.setBounds(35, 118, 400, 32);
		getContentPane().add(txtQuizName);
		txtQuizName.setColumns(10);
		
		//creating button and setting bounds and adding to frame
		btnBack = new JButton("Back");
		btnBack.setBounds(10, 53, 89, 23);
		getContentPane().add(btnBack);
		btnBack.addActionListener(this); //adding action listener 
		
		//creating jlabel for number of questions and setting bounds and adding to frame 
		lblNumberOfQuestions = new JLabel("Number of Question(s):");
		lblNumberOfQuestions.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNumberOfQuestions.setBounds(113, 268, 133, 14);
		getContentPane().add(lblNumberOfQuestions);
		
		//adding comboBox to frame and setting bounds 
		comboBox.setBounds(256, 265, 50, 20);
		getContentPane().add(comboBox);
		comboBox.setActionCommand("Question #");
		
		//creating btnCreate and adding it to frame and adding actionListener 
		btnCreate = new JButton("Create");
		btnCreate.setBounds(167, 333, 139, 36);
		getContentPane().add(btnCreate);
		btnCreate.addActionListener(this);
		
		//creating a category label and adding it to frame, setting bounds and text and adding actionListener 
		lblCategory = new JLabel("Category"); 
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategory.setFont(new Font("Segoe UI Historic", Font.PLAIN, 15));
		lblCategory.setBounds(0, 161, 115, 20);
		getContentPane().add(lblCategory);
		
		//creating textField and setting bounds and adding to frame
		txtCategoryName = new JTextField();
		txtCategoryName.setColumns(10);
		txtCategoryName.setBounds(35, 192, 400, 32);
		getContentPane().add(txtCategoryName);
		
		//setting color of error text and adding to frame
		lblError.setForeground(Color.RED);
		getContentPane().add(lblError);
		
		lblCategoryError.setForeground(Color.RED);
		getContentPane().add(lblCategoryError);
		
		lblErrorUnselected.setForeground(Color.RED);
		getContentPane().add(lblErrorUnselected);
		
		setVisible (true); 
	}
	
	public void actionPerformed(ActionEvent e) { //listens to button clicked 
		if(e.getSource() == btnBack){
			
			try {
			new HomeMenuGUI();
			} catch (Exception f) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			dispose();
		}
		else if(e.getSource() == btnCreate){
			
			String selectedItem = (String) comboBox.getSelectedItem();//getting selected item on comboBox box 
			
			//getting catergoryName fromTextField
			String categoryName = txtCategoryName.getText(); 
			//
			//creating variable for max length 
			int maxlength = 20; 
			
			//getting quizName for textField 
			String quizName = txtQuizName.getText(); 
			
			//checking for erros and long names than ruin gui design 
			if ((categoryName.length() > maxlength) || (quizName.length() > maxlength) || selectedItem == "" || (categoryName.isEmpty()) || (quizName.isEmpty()))
			{
				if (quizName.length() > maxlength)
				{
					lblError.setText("Shorten QuizName to 20 characters");
					
					lblError.setBounds(167, 104, 268, 14);
				}
				//checking if textfield is empty 
				if (categoryName.isEmpty())
				{
					lblCategoryError.setText("Empty Category Name");
					
					lblCategoryError.setBounds(280, 178, 155, 14);
				}
				
				if (quizName.isEmpty())
				{
					lblError.setText("EmptyQuizName");
					
					lblError.setBounds(320, 104, 115, 14);
				}
				
				if (categoryName.length() > maxlength)
				{
					lblCategoryError.setText("Shorten CategoryName to 20 characters");
					
					lblCategoryError.setBounds(181, 178, 254, 14);
				}
				
				if (selectedItem.equalsIgnoreCase (""))
				{
					lblErrorUnselected.setText("Unselected Questions");
					
					lblErrorUnselected.setBounds(220, 293, 158, 14);
				}
			}
			else  
			{
				//data class and clearing questions 
				Data.q.getQuestions().clear();

				//getting quizName 
				Data.q.setQuizName(txtQuizName.getText());
				
				//getting category 
				Data.q.setCategory(txtCategoryName.getText());
				
				Data.totalQuestions = Integer.parseInt(selectedItem); //getting total num of questions 
				
				new QuestionCreation();
				
				dispose();
			}
		}
	}

	public static void main(String[] args) {
		TriviaCreationGUI tcGUI = new TriviaCreationGUI();
	}
}
