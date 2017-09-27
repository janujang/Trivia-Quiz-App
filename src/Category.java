/*
 * Authors: Lily Liu and Pavneet Gill
 * Date: Monday, January 9, 2017
 * Description: This class separates the quizzes into different categories 
 * 				with the option to add delete and change them.
 * 
 * Method List:
 * 		Constructors
 * 			Category(String c) // constructor for category
 * 		Functions
 * 			String add(String title) // add quiz to category
 * 			boolean change(String title, String newTitle) // change name of quiz
 * 			boolean remove(String title) // remove quiz from category list
 * 			int search(String title) // search quiz based on name and return index
 * 			String toString() // convert category info to String for saving in file
 * 		Reading and Writing
 * 			boolean readFromFile(String fileName) // read and load info from file into Category object
 * 			boolean writeToFile(String fileName, String contents, boolean append) // save info to file
 * 		Getters
 * 			ArrayList<String> getList()
 * 			String getCategory()
 * 			int getSize()
 * 		Self-Testing Main
 * 			static void main(String[] args) throws IOException
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Category {
	
	/*
	 * ==============================
	 * Variables
	 * ==============================
	 */

	ArrayList<String> list; // titles of quizzes
	String category; // subject
	int size; // number of quizzes
	
	/*
	 * ==============================
	 * Constructors
	 * ==============================
	 */
	
	// constructor for category
	public Category(String c) {
		list = new ArrayList<String>();
		category = c;
		size = 0;
	}
	
	/*
	 * ==============================
	 * Functions
	 * ==============================
	 */
	
	// add quiz to category
	public String add(String title) {
		String newTitle = title;
		
		/*
		**** while duplicate title, change title ****
		while (search(title) > -1) { // duplicate title
			// change title
		}
		*/
		
		list.add(newTitle);
		size++;
		writeToFile(category + ".txt", newTitle + "\n", true);
		
		return title;
	}
	
	// change name of quiz
	public boolean change(String title, String newTitle) {
		int index = search(title);
		
		if (index > -1) { // found
			list.set(index, newTitle); // update list
			writeToFile(getCategory() + ".txt", toString(), false); // update file
			
			return true;
		}
		
		return false;
	}
	
	// remove quiz from category list
	public boolean remove(String title) {
		int index = search(title);
		
		if (index > -1) { // found
			list.remove(index); // remove item
			size--;
			writeToFile(getCategory() + ".txt", toString(), false); // update file
			
			return true;
		}
		
		return false;
	}
	
	// search quiz based on name and return index
	public int search(String title) {
		for (int i = 0; i < size; i++) {
			if (list.get(i).equals(title)) {
				return i; // found
			}
		}
		
		return -1; // not found
	}
	
	// convert category info to String for saving in file
	public String toString() {
		String str = list.get(0);
		
		for (int i = 1; i < size; i++) {
			str += "\n" + list.get(i);
		}
		
		return str;
	}
	
	/*
	 * ==============================
	 * Reading and Writing
	 * ==============================
	 */
	
	// read and load info from file into Category object
	public boolean readFromFile(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
		    String line = br.readLine();

		    while (line != null) {
		        list.add(line); // add title to list
		        size++; // increase size
		        line = br.readLine();
		    }
		    
		    br.close();
		    
		    return true;
		}
		catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			
			return false;
		}
	}
	
	// save info to file
	public boolean writeToFile(String fileName, String contents, boolean append) {
		try {
		    FileWriter fw = new FileWriter(fileName, append); // true tells to append data
		    BufferedWriter bw = new BufferedWriter(fw);
		    bw.write(contents);
		    bw.close();
		    
		    return true;
		}
		catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			
			return false;
		}
	}
	
	/*
	 * ==============================
	 * Getters
	 * ==============================
	 */
	
	public ArrayList<String> getList() {
		return list;
	}

	public String getCategory() {
		return category;
	}

	public int getSize() {
		return size;
	}
	
	/*
	 * ==============================
	 * Self-Testing Main
	 * ==============================
	 */
	
	public static void main(String[] args) throws IOException {
		/*
		Category c = new Category("Science"); // add category and write to file
		String title = "Astronomy";
		System.out.println(c.add(title));
		*/
		
		Category c = new Category("History"); // read and load data from file
		c.readFromFile(c.getCategory() + ".txt");
		System.out.println(c.getList());
		
		c.change("Canadian History", "World History"); // edit title and update file
		System.out.println(c.getList());
		
		c.remove("British History"); // remove title and update file
		System.out.println(c.getList());
	}

}