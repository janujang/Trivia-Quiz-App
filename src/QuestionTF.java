/*
 * Author: Lily Liu
 * Date: Monday, January 9, 2016
 * Description: True or false question class.
 * 
 * Method List:
 * 		Constructors
 * 			QuestionTF() // default constructor
 * 			QuestionTF(String q) // constructor for new question
 * 			QuestionTF(String q, boolean a) // constructor for existing question
 * 			QuestionTF(String[] info) // constructor for reading from file
 * 		Functions
 * 			boolean checkAnswer(boolean a) // verify answer
 * 			String toString() // convert question info to String for saving in file
 * 		Getters
 * 			boolean getAnswer()
 * 		Setters
 * 			void setAnswer(boolean a)
 * 		Self-Testing Main
 * 			static void main(String[] args)
 */

public class QuestionTF extends Question {

	/*
	 * ==============================
	 * Variables
	 * ==============================
	 */
	
	private static final int type = 1; // 1 = true or false question
	
	private boolean answer; // correct answer

	/*
	 * ==============================
	 * Constructors
	 * ==============================
	 */
	
	// default constructor
	public QuestionTF() {
		super();
		answer = false;
	}

	// constructor for new question
	public QuestionTF(String q) {
		super(q, type);
		answer = false;
	}

	// constructor for existing question
	public QuestionTF(String q, boolean a) {
		super(q, type);
		answer = a;
	}

	// constructor for reading from file
	public QuestionTF(String[] info) {
		/*
		 * example: 1|George Washington is the first POTUS.|true
		 * [0] - type
		 * [1] - question
		 * [2] - answer
		 */

		super(info[1], type);
		answer = java.lang.Boolean.parseBoolean(info[2]);
	}

	/*
	 * ==============================
	 * Functions
	 * ==============================
	 */

	// verify answer
	public boolean checkAnswer(boolean a) {
		return answer == a;
	}

	// convert question info to String for saving in file
	public String toString() {
		// example: 1|George Washington is the first POTUS.|true

		return type + "|" + getQuestion() + "|" + answer;
	}

	/*
	 * ==============================
	 * Getters
	 * ==============================
	 */

	public boolean getAnswer() {
		return answer;
	}

	/*
	 * ==============================
	 * Setters
	 * ==============================
	 */

	public void setAnswer(boolean a) {
		answer = a;
	}

	/*
	 * ==============================
	 * Self-Testing Main
	 * ==============================
	 */

	public static void main(String[] args) {
		QuestionTF q = new QuestionTF("Barack Obama is the 44th POTUS.");
		System.out.println("Question: " + q.getQuestion());

		q.setAnswer(true); // correct answer
		System.out.println("\nAnswer: " + q.getAnswer());

		boolean input = true; // user input
		System.out.println("Input: " + input);

		System.out.println("\nIs correct: " + q.checkAnswer(input));
		System.out.println("To string: " + q.toString());
	}

}
