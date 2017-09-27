
/**
 * Author: Pavneet Gill
 * Date: January 24, 2017
 * Description: Static data for easy access from GUI classes
 *
 */
public class Data {

	//variables for point quiz, filename, question number, total questions, time an array of player accounts and user name
	public static int points = 0; 
	public static Quiz q = new Quiz("Hello", "Hello");
	public static String filename;
	public static int questionNum = 0; 
	public static int totalQuestions =0; 
	public static double time = 0;
	public static PlayerAccountList accounts = new PlayerAccountList();
	public static String userName;
	public static int timeLimit = 0;
	public static int correct = 0;
	public static int incorrect = 0;
	

	//methods to get points and add points
	public static int getPoints () {
		return points; 
	}
	public static void addPoints (int newPoints) {
		points += newPoints;
		//System.out.println(points);
	}
	
	//method to get average time
	public static double getAverageTime () {
		return time/totalQuestions; 
	}
	
	//method to add time
	public static void addTime (int newTime) {
		time += newTime;
		//System.out.println(points);
	}

}
