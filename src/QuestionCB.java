/*
 * Author: Lily Liu
 * Date: Monday, January 9, 2016
 * Description: Checkbox question class.
 * 
 * Method List:
 * 		Constructors
 * 			QuestionCB() // default constructor
 * 			QuestionCB(String q) // constructor for new question
 * 			QuestionCB(String q, ArrayList<String> o, ArrayList<String> a) // constructor for existing question
 * 			QuestionCB(String[] info) // constructor for reading from file
 * 		Functions
 * 			boolean addOption(String o) // add option to question
 * 			boolean removeOption(String o) // remove option from question
 * 			boolean addAnswer(String a) // add answer to question
 * 			boolean removeAnswer(String a) // remove answer from question
 * 			boolean checkAnswer(ArrayList<String> input) // verify answers
 * 			String toString() // convert question info to String for saving in file
 * 		Getters
 * 			ArrayList<String> getOptions()
 * 			ArrayList<String> getAnswers()
 * 			int getOSize()
 * 			int getMinSize()
 * 			int getMaxSize()
 * 		Setters
 * 			void setOptions(ArrayList<String> o)
 * 			void setAnswer(ArrayList<String> a)
 * 			void setOSize(int s)
 * 			void setASize(int s)
 * 		Self-Testing Main
 * 			static void main(String[] args)
 */

import java.util.ArrayList;

public class QuestionCB extends Question {

	/*
	 * ==
	 * Variables
	 * ==
	 */

	private static final int type = 3; // 3 = check box question
	private static final int minSize = 3; // minimum number of options
	private static final int maxSize = 5; // maximum number of options

	private ArrayList<String> options; // all possible options
	private ArrayList<String> answers; // correct answers
	private int oSize; // number of options
	private int aSize; // number of answers

	/*
	 * ==
	 * Constructors
	 * ==
	 */

	// default constructor
	public QuestionCB() {
		super();
		options = new ArrayList<String>();
		answers = new ArrayList<String>();
		oSize = 0;
		aSize = 0;
	}

	// constructor for new question
	public QuestionCB(String q) {
		super(q, type);
		options = new ArrayList<String>();
		answers = new ArrayList<String>();
		oSize = 0;
		aSize = 0;
	}

	// constructor for existing question
	public QuestionCB(String q, ArrayList<String> o, ArrayList<String> a) {
		super(q, type);
		options = o;
		answers = a;
		oSize = o.size();
		aSize = a.size();
	}

	// constructor for reading from file
	public QuestionCB(String[] info) {
		/*
		 * example: 3|What are the branches of government?|[Executive,Legislative,Judicial,Municipal]|[Executive,Legislative,Judicial]
		 * [0] - type
		 * [1] - question
		 * [2] - options
		 * [3] - answers
		 */

		super(info[1], type);
		options = new ArrayList<String>();
		answers = new ArrayList<String>();

		// add options to array list
		String[] option = info[2].substring(1, info[2].length() - 1).split(","); // get array of options
		oSize = option.length;

		for (int i = 0; i < oSize; i++) {
			options.add(option[i]); // add each option to array list
		}

		// add answers to array list
		String str = info[3].substring(1, info[3].length() - 1); // remove first and last character
		String[] answer = str.split(","); // get array of answers
		aSize = answer.length;

		for (int i = 0; i < aSize; i++) {
			answers.add(answer[i]); // add each answer to array list
		}
	}

	/*
	 * ==
	 * Functions
	 * ==
	 */
	
	// add option to question
	public boolean addOption(String o) {
		if (oSize < maxSize) {
			options.add(o);
			oSize++;
			return true;
		}

		return false;
	}
	
	// remove option from question
	public boolean removeOption(String o) {
		if (oSize > minSize) {
			options.remove(o);
			oSize--;
			return true;
		}

		return false;
	}

	// add answer to question
	public boolean addAnswer(String a) {
		if (aSize < maxSize) {
			answers.add(a);
			aSize++;
			return true;
		}

		return false;
	}
	// remove answer from question
	public boolean removeAnswer(String a) {
		if (aSize > minSize) {
			answers.remove(a);
			aSize--;
			return true;
		}
		
		return false;
	}
	// verify answers
	public boolean checkAnswer(ArrayList<String> input) {
		int numCorrect = 0;
		int size = input.size();

		for (int i = 0; i < aSize; i++) {
			for (int j = 0; j < size; j++) {
				if (answers.get(i).equals(input.get(j))) {
					numCorrect++;
					break;
				}
			}
			// break will take you here
		}

		return numCorrect == answers.size();
	}
	public boolean checkSelectedItem(String input) {
		for (int i = 0; i < aSize; i++) {
			if (answers.get(i).equals(input)) {
				return true;
			}
		}
		return false;
	}


	// convert question info to String for saving in file
	public String toString() {
		// example: 3|What are the branches of government?|[Executive,Legislative,Judicial,Municipal]|[Executive,Legislative,Judicial]

		String s = type + "|" + getQuestion() + "|[" + options.get(0);

		for (int i = 1; i < oSize; i++) {
			s += "," + options.get(i);
		}

		s += "]|[" + answers.get(0);

		for (int i = 1; i < aSize; i++) {
			s += "," + answers.get(i);
		}

		return s += "]";
	}

	/*
	 * ==
	 * Getters
	 * ==
	 */

	public ArrayList<String> getOptions() {
		return options;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public int getOSize() {
		return oSize;
	}

	public int getMinSize() {
		return minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	/*
	 * ==
	 * Setters
	 * ==
	 */

	public void setOptions(ArrayList<String> o) {
		options = o;
	}

	public void setAnswer(ArrayList<String> a) {
		answers = a;
	}

	public void setOSize(int s) {
		oSize = s;
	}	
	public void setASize(int s) {
		aSize = s;
	}
	

	/*
	 * ==
	 * Self-Testing Main
	 * ==
	 */

	public static void main(String[] args) {
		QuestionCB q = new QuestionCB("What are the branches of government?");
		System.out.println(q.getQuestion());

		// add options
		String[] options = {"Executive", "Legislative", "Judicial", "Municipal"};
		int oSize = options.length;
		for (int i = 0; i < oSize; i++) {
			q.addOption(options[i]);
		}
		System.out.println("\nOptions: " + q.getOptions());

		/*
		// remove option
		System.out.println("    remove municipal: " + q.removeOption("Municipal"));
		System.out.println("    remove federal: " + q.removeOption("Federal"));
		System.out.println("Options: " + q.getOptions());
		 */

		// add answers
		String[] answers = {"Executive", "Legislative", "Judicial"};
		int aSize = answers.length;
		for (int i = 0; i < aSize; i++) {
			q.addAnswer(answers[i]);
		}
		System.out.println("\nAnswers: " + q.getAnswers());

		// user input
		ArrayList<String> input = new ArrayList<String>();
		input.add("Executive");
		input.add("Legislative");
		input.add("Judicial");
		System.out.println("Input: " + input);

		System.out.println("\nIs correct: " + q.checkAnswer(input));
		System.out.println("To string: " + q.toString());
	}

}
