/*
 * Author: Lily Liu
 * Date: Monday, January 9, 2016
 * Description: Multiple-choice question class.
 * 
 * Method List:
 * 		Constructors
 * 			QuestionMC() // default constructor
 * 			QuestionMC(String q) // constructor for new question
 * 			QuestionMC(String q, ArrayList<String> o, String a) // constructor for existing question
 * 			QuestionMC(String[] info) // constructor for reading from file
 * 		Functions
 * 			boolean addOption(String o) // add option to question
 * 			boolean removeOption(String o) // remove option from question
 * 			boolean checkAnswer(String a) // verify answer
 * 			int searchOptions(String o) // search through options and return index
 * 			String toString() // convert question info to String for saving in file
 * 		Getters
 * 			ArrayList<String> getOptions()
 * 			String getAnswer()
 * 			int getSize()
 * 			int getMinSize()
 * 			int getMaxSize()
 * 		Setters
 * 			void setOptions(ArrayList<String> o)
 * 			void setAnswer(String a)
 * 			void setSize(int s)
 * 		Self-Testing Main
 * 			static void main(String[] args)
 */

import java.util.ArrayList;

public class QuestionMC extends Question {
	
	/*
	 * ==============================
	 * Variables
	 * ==============================
	 */

	private static final int type = 2; // 2 = multiple-choice question

	private static final int minSize = 3; // minimum number of options
	private static final int maxSize = 5; // maximum number of options
	
	private ArrayList<String> options; // all possible options
	private String answer; // correct answer
	private int size; // number of options
	
	/*
	 * ==============================
	 * Constructors
	 * ==============================
	 */
	
	// default constructor
	public QuestionMC() {
		super();
		options = new ArrayList<String>();
		answer = "";
		size = 0;
	}
	
	// constructor for new question
	public QuestionMC(String q) {
		super(q, type);
		options = new ArrayList<String>();
		answer = "";
		size = 0;
	}
	
	// constructor for existing question
	public QuestionMC(String q, ArrayList<String> o, String a) {
		super(q, type);
		options = o;
		answer = a;
		size = o.size();
	}
	
	// constructor for reading from file
	public QuestionMC(String[] info) {
		/*
		 * example: 2|In what year was the Declaration of Independence signed?|[1776,1789,1800]|1776
		 * [0] - type
		 * [1] - question
		 * [2] - options
		 * [3] - answer
		 */
			
		super(info[1], type);
		options = new ArrayList<String>();
		answer = info[3];
		
		// add options to array list
		String str = info[2].substring(1, info[2].length() - 1); // remove first and last character
		String[] option = str.split(","); // get array of options
		size = option.length;
		
		for (int i = 0; i < size; i++) {
			options.add(option[i]); // add each option to array list
		}
	}
	
	/*
	 * ==============================
	 * Functions
	 * ==============================
	 */
	
	// add option to question
	public boolean addOption(String o) {
		if (size < maxSize) {
			options.add(o);
			size++;
			return true;
		}
		
		return false;
	}
	
	// remove option from question
	public boolean removeOption(String o) {
		int index = searchOptions(o);
		
		if (index > -1 && size > minSize) {
			options.remove(index);
			size--;
			return true;
		}
		
		return false;
	}
	
	// verify answer
	public boolean checkAnswer(String a) {
		return answer.equals(a);
	}
	
	// search through options and return index
	public int searchOptions(String o) {
		for (int i = 0; i < size; i++) {
			if (options.get(i).equals(o)) {
				return i; // found
			}
		}
		
		return -1; // not found
	}
	
	// convert question info to String for saving in file
	public String toString() {
		// example: 2|In what year was the Declaration of Independence signed?|[1776,1789,1800]|1776
		
		String s = type + "|" + getQuestion() + "|[" + options.get(0);
		
		for (int i = 1; i < size; i++) {
			s += "," + options.get(i);
		}
		
		return s += "]|" + answer;
	}
	
	/*
	 * ==============================
	 * Getters
	 * ==============================
	 */
	
	public ArrayList<String> getOptions() {
		return options;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getMinSize() {
		return minSize;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	
	/*
	 * ==============================
	 * Setters
	 * ==============================
	 */
	
	public void setOptions(ArrayList<String> o) {
		options = o;
	}
	
	public void setAnswer(String a) {
		answer = a;
	}
	
	public void setSize(int s) {
		size = s;
	}
	
	/*
	 * ==============================
	 * Self-Testing Main
	 * ==============================
	 */
	
	public static void main(String[] args) {
		QuestionMC q = new QuestionMC("What is the length of term for the POTUS?");
		System.out.println("Question: " + q.getQuestion());
		
		String[] options = {"3 years", "4 years", "5 years", "8 years"};
		int oSize = options.length;
		for (int i = 0; i < oSize; i++) {
			q.addOption(options[i]);
		}
		System.out.println("\nOptions: " + q.getOptions());

		System.out.println("    remove 2 years: " + q.removeOption("2 years"));
		System.out.println("    remove 8 years: " + q.removeOption("8 years"));
		System.out.println("Options: " + q.getOptions());
		
		q.setAnswer("4 years"); // correct answer
		System.out.println("\nAnswer: " + q.getAnswer());
		
		String input = "5 years"; // user input
		System.out.println("Input: " + input);
		
		System.out.println("\nIs correct: " + q.checkAnswer(input));
		System.out.println("To string: " + q.toString());
	}

}
