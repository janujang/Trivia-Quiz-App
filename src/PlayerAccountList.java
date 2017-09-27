import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
/*
 * Author: Kevin Subhash
 * Date: January 2017
 * Description: This class allows us to insert, delete, and change player accounts as well as search for different player info
 * through the list of accounts. This class also contains sort methods, check if login works, and is able to read from files 
 * and write to files.
 * 
 * Method List:
 * public PlayerAccountList() 
 * public PlayerAccountList(int maxSize)
 * public void loadFile (String file) throws IOException
 * public void writeFile (String file) throws IOException
 * public boolean insert(Player record)
 * public boolean delete(Player record)
 * public boolean checkUserName(String userName)
 * public Statistics getStats(String userName)
 * public boolean checkLogin(String userName, String password)
 * public String getName(String userName)
 * public String getPic(String userName)
 * public void insertSort ()
 * public int binarySearch(String searchKey)
 * public boolean change(Player oldR, Player newR)
 * public int getSize()
 * public Player[] getList()
 */

public class PlayerAccountList {

	private int maxSize;   // creating private variables
	private int size;
	private Player list [];

	public PlayerAccountList() {
		this.maxSize = 100; //initializing variables
		size = 0; 
		list = new Player [maxSize];
	}

	public PlayerAccountList(int maxSize) { //overloading
		this.maxSize = maxSize; //initializing variables
		size = 0; 
		list = new Player [maxSize];
	}

	public void loadFile (String file) throws IOException{ // method to read a file
		FileInputStream ln = new FileInputStream(file);
		FileInputStream ln2 = new FileInputStream(file);
		LineNumberReader inputFile2 = new LineNumberReader (new InputStreamReader(ln2));//inputting file
		BufferedReader inputFile = new BufferedReader (new InputStreamReader(ln)); 
		int size = 0;

		while (inputFile2.readLine () != null) // finds size of array in file
		{
			size++;
		}  
		String fileInfo[] = new String [size]; //declare and create 

		for (int j = 0 ; j < fileInfo.length ; j++)
		{
			fileInfo [j] = inputFile.readLine ();
			Player accountsInfo = new Player();
			accountsInfo.updatePlayerData(fileInfo[j]);
			insert(accountsInfo);
		}
		inputFile.close (); // closes inputFile
	}

	public void writeFile (String file) throws IOException{ // method to write a file

		PrintWriter output = new PrintWriter (new FileWriter(file));

		for (int i = 0; i < size; i++) { // Reads each line of the text file and stores it in arrays
			output.println(list[i]);
		}

		output.close (); // Closes file2
	}

	public boolean insert(Player record){ // method used to insert new record
		if (size < maxSize){ // if size is less than maxSize then it inserts record
			size++;
			list[size-1] = record;
			return true;
		}
		return false;
	}	

	public boolean delete(Player record){ // method used to delete a record
		int  location;

		location = binarySearch(record.toString()); // calls binary search method

		if(location >= -1) // if statement for delete
		{
			list[location] = list[size-1]; // deletes record and reduces size of record by 1
			size--;
			return true;
		}
		return false;
	}

	public boolean checkUserName(String userName){ // method to check if username is available
		for(int i = 0; i < size; i++){
			if(userName.equalsIgnoreCase(list[i].getUserName())){ // compares usernames
				return false;
			}
		}
		return true; 
	}
	
	public Statistics getStats(String userName){ // method to check if username is available
		for(int i = 0; i < size; i++){
			if(userName.equalsIgnoreCase(list[i].getUserName())){ // compares usernames
				return list[i].getStats();
			}
		}
		return null;
	}

	public boolean checkLogin(String userName, String password){ // method to check if username and password meet
		for(int i = 0; i < size; i++){
			if(userName.equalsIgnoreCase(list[i].getUserName()) && password.equals(list[i].getPassword())){ // compares username and compares password
				return true;
			}
		}
		return false; 
	}

	public String getName(String userName){ // Method used to get name with userName
		for(int i = 0; i < size; i++){
			if(userName.equalsIgnoreCase(list[i].getUserName())){ // compares userName
				return list[i].getName();
			}
		}
		return null; // returns -1 if not found
	}

	public String getPic(String userName){ // Method searches for the inputted userName and gets the username's pic name
		for(int i = 0; i < size; i++){
			if(userName.equalsIgnoreCase(list[i].getUserName())){ // compares userNames
				return list[i].getAccountpic();
			}
		}
		return null; // returns -1 if not found
	}

	public void insertSort (){ // An insertion sort method that sorts the player's name
		for (int top = 1; top < size; top++){
			Player item = list[top];
			int i = top-1;
			while (i >= 0){
				if(item.getName().compareToIgnoreCase(list[i].getName())>0){ // compares the inputed name with names in current account
					break;
				}
				list[i+1] = list[i];
				i--;
			}
			list[i+1] = item;
		}
	}

	public int binarySearch(String searchKey){ // binary search method that searches for account name
		int low = 0;
		int high = size - 1;
		int middle;
		insertSort();
		while(low <= high){
			middle = (high + low)/2; // divides array in two
			if(searchKey.equalsIgnoreCase(list[middle].toString())){  // returns middle when found
				return middle;
			}
			else if(searchKey.compareToIgnoreCase(list[middle].toString())<0){ 
				high = middle - 1;
			}
			else{
				low = middle + 1;
			}
		}
		return -2;
	}

	public boolean change(Player oldR, Player newR){ // method used to change an account
		if(delete(oldR)){ // deletes old account and inserts new account
			insert(newR); 
			return true;
		}
		return false;
	}

	public int getSize() { // getter method to get the size
		return size;
	}

	public Player[] getList() { // method to return list
		return list;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)throws IOException {
		PlayerAccountList accounts = new PlayerAccountList(); 
		String[] button = {"Insert","Print","Delete","Change","File Input","Save","insertion sort","Login","Quit"}; // string array of buttons

		while(true){

			char command = (char) JOptionPane.showOptionDialog(null, "What Would You Like To Do With The Player Accounts?","Player Accounts",
					JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,button,button[0]);// Asks user some options with buttons

			if(button[command].charAt(0) == 'Q'){ // starting switch case for each button
				break;
			}
			switch (button[command].charAt(0)){
			case 'I':{ // inserts player info manually 
				String info = JOptionPane.showInputDialog(null,"Enter <name>,<userName>,<password>,<picName>",
						"Kevin Subhash;KevinSub99;123456;p1.jpg;3|20|18|90.0|3.5|3|[American History, European History, World History]");
				Player playerInfo = new Player(info);
				if(accounts.checkUserName(playerInfo.getUserName())){

					if (!accounts.insert(playerInfo)){ // account is not added if not valid
						JOptionPane.showMessageDialog(null, "Maximum Number of Accounts reached.");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "UserName is Taken.");
				}
				System.out.println("Name: " + accounts.getName(playerInfo.getUserName()));
				System.out.println("Pic Name: " + accounts.getPic(playerInfo.getUserName()));
				System.out.println("Stats: " + accounts.getStats(playerInfo.getUserName()));
				break;
			}
			case 'F':{ // inserts all player info from txt file
				String file = JOptionPane.showInputDialog(null, "Enter File Name", "Players.txt"); // prompts user for file name

				accounts.loadFile(file); // calls method to load file

				break;
			}
			case 'S':{ // Writes to a file
				String fileName = JOptionPane.showInputDialog(null, "Enter File Name ", "Players.txt"); // prompt user for file
				accounts.writeFile(fileName); // calls method to write file
				break;
			}
			case 'P':{ // prints all records
				for (int i = 0; i < accounts.getSize(); i++) { // loops until all record are printed
					System.out.println("Account #" + (i+1) + " " + accounts.getList()[i]);
				}
				break;
			}
			case 'D':{ // deletes a player account
				Player playerInfo = new Player();
				String record = JOptionPane.showInputDialog(null,"Enter <name>,<userName>,<password>,<picName>", //prompt user for player info to delete account
						"Kevin Subhash;KevinSub99;123456;p1.jpg;3|20|18|90.0|3.5|3|[American History, European History, World History]");

				playerInfo.updatePlayerData(record); // processes inputed string

				if(!accounts.delete(playerInfo)){ // performs when record cannot be found
					JOptionPane.showMessageDialog(null, "Record Not Found.");
					break;
				}
				accounts.insertSort();
				break;
			}
			case 'C':{ // changes a player account into another
				String oldRecord = JOptionPane.showInputDialog(null, "Enter Account to Change", "Kevin Subhash;KevinSub99;123456;p1.jpg;3|20|18|90.0|3.5|3|[American History, European History, World History]"); // prompts user for old account info
				Player oldInfo = new Player();
				oldInfo.updatePlayerData(oldRecord); // processes old account string

				String newRecord = JOptionPane.showInputDialog(null, "Enter Account to Change to", "Janujan G.;JG100;789456;p2.png;3|10|13|99.9|3.5|3|[American History, European History, World History]"); // prompts user for new account info
				Player newInfo = new Player();
				newInfo.updatePlayerData(newRecord); // processes new string info

				if(accounts.change(oldInfo,newInfo)){ // calls method to change the old record to new record
					accounts.insertSort();
					JOptionPane.showMessageDialog(null,"Success");
				}
				else{
					JOptionPane.showMessageDialog(null, "Fail");

					break;
				}
				break;
			}
			case 'i':{ // performs insertion sort
				accounts.insertSort();
				break;
			}
			case 'L':{ // performs ripple sort
				String userName = JOptionPane.showInputDialog(null,"Enter userName",
						"KevinSub99");
				String password = JOptionPane.showInputDialog(null,"Enter password",
						"123456");
				if(accounts.checkLogin(userName, password)){
					JOptionPane.showMessageDialog(null,"Login Success");
				}
				else{
					JOptionPane.showMessageDialog(null,"Login Failed");
				}
				break;
			}
			} // end of while loop
		} // end of main method

	}

}
