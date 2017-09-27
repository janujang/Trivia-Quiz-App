/*
 * Author: Lily Liu
 * Date: Monday, January 9, 2016
 * Description: Generic question class.
 * 
 * Method List:
 * 		Constructors
 * 			Question() // default constructor
 * 			Question(String q, int t) // constructor for question
 * 		Functions
 * 			String toString() // convert question info to String for saving in file
 * 		Getters
 * 			int getType()
 * 			String getQuestion()
 * 			double getTime()
 * 		Setters
 * 			void setType(int t)
 * 			void setQuestion(String q)
 * 			void setTime(double t)
 * 		Self-Testing Main
 * 			static void main(String[] args)
 */

public class Question {
	
	/*
	 * ==============================
	 * Variables
	 * ==============================
	 */
	
	/*
	 * Types:
	 * 0 = undefined type
	 * 1 = true or false question
	 * 2 = multiple-choice question
	 * 3 = check box question
	 */
	
	private int type; // type of question
	private String question; // question
	private double time; // length of time spent on question
	
	/*
	 * ==============================
	 * Constructors
	 * ==============================
	 */
	
	// default constructor
	public Question() {
		type = 0;
		question = "";
		time = 0;
	}
	
	// constructor for question
	public Question(String q, int t) {
		type = t;
		question = q;
		time = 0;
	}
	
	/*
	 * ==============================
	 * Functions
	 * ==============================
	 */
	
	// convert question info to String for saving in file
	public String toString() {
		return question;
	}

	/*
	 * ==============================
	 * Getters
	 * ==============================
	 */
	
	public int getType() {
		return type;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public double getTime() {
		return time;
	}
	
	/*
	 * ==============================
	 * Setters
	 * ==============================
	 */
	
	public void setType(int t) {
		this.type = t;
	}
	
	public void setQuestion(String q) {
		this.question = q;
	}
	
	public void setTime(double t) {
		this.time = t;
	}
	
	/*
	 * ==============================
	 * Self-Testing Main
	 * ==============================
	 */
	
	public static void main(String[] args) {
		Question q = new Question("Is George Washington the first POTUS?", 0);
		System.out.println("Question 1: " + q.getQuestion());
		
		q.setQuestion("Is John Adams the second POTUS?");
		System.out.println("Question 2: " + q.getQuestion());
	}

}
