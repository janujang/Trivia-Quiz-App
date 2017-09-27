import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * 
 */

/**Name: Pavneet Gill & Kevin Subhash
	 * Date: December 2016
	 * Description: This program allow the creation of a plauer 
	 */
public class Player {

	//declaring variables for name, user name, password and Stats class 
	private String name; 
	
	private String userName; 
	
	private String password;
	
	private String picName;
	
	private Statistics stats; 
	
	public Player() {
		this.name = ""; 
		this.userName = ""; 
		this.stats = null;
		this.picName = ""; 
		this.password = ""; 
	}
	//Overloads Constructor initializes the player data
	public Player(String name, String userName, String password, String picName, Statistics stats)
	{
			this.name= name; 
			this.userName = userName; 
			this.password = password; 
			this.picName = picName;
			this.stats = new Statistics();
	}
	
	//Overloads Constructor initializes the player data in different way 
	public Player (String info)
	{
		String word [];
		word = info.split(";");
		this.name = word[0];
		this.userName = word[1];
		this.password = word[2];
		this.picName = word[3];
		String str = word[4];
		String[] data = str.split("\\|");
		this.stats = new Statistics(data);
	}
	
	public void updatePlayerData (String info)
	{
		String word [];
		word = info.split(";");
		this.name = word[0];
		this.userName = word[1];
		this.password = word[2];
		this.picName = word[3];
		String str = word[4];
		String[] data = str.split("\\|");
		this.stats = new Statistics(data);
	}
	
	//Setter and getter methods 
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public Statistics getStats() 
	{
		return stats;
	}

	public void setAccountpic(String picName) 
	{
		this.picName = picName;
	}
	
	public String getAccountpic() 
	{
		return this.picName;
	}

	public String getPassword ()
	{
	     return password; 
	}
	
//	public Statistics setStats(String info) 
//	{
//		this.stats = new Statistics(info);
//	}
	
	public String toString(){ // method to change inputed variable to full form and return a string record
		return (getName() + ";" + getUserName() + ";" + getPassword() + ";" + getAccountpic() + ";" + getStats());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//self testing
				//-----------------------------------------------------------------------------------
				String playerInfo = ""; //creating variable for playerInfo 


				playerInfo = JOptionPane.showInputDialog(null, "Enter:"+ "<name>,<userName>,<password>","Kevin Subhash;KevinSub99;123456;p1.jpg;3|20|18|90.0|3.5|3|[American History, European History, World History]");

				//process Record
				Player playerRecord = new Player(playerInfo); //creating a Customer constructor 

				System.out.println("Name: "+ playerRecord.getName()); //getting name
				
				System.out.println("UserName: " + playerRecord.getUserName()); //getting userName
				
				System.out.println("Password: " + playerRecord.getPassword()); //getting passowrd
				
				System.out.println("Stats: " + playerRecord.getStats().toString()); //getting statistics 
				
				System.out.println(playerRecord.toString());  //comverting playerrecord to string 
	}

}
