import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: Pavneet Gill
 * Date: January 15, 2016 
 * Description: Writes information to file 
 * MethodList
 *              saveFile (String fileName, String newPhrases[]) saves encrypted and decrypted words into file
 */
public class Writer 
{

    private String fileName;
    private String text[];
    
    public Writer (String fileName) throws IOException
    {
      saveFile (fileName, text); 
    }
 
    public void saveFile (String fileName, String newText[]) throws IOException
    {

	// open file for writing
	PrintWriter fw = new PrintWriter (new FileWriter (fileName));

	fw.println (newText.length); // writing length of file

	for (int i = 0 ; i < newText.length ; i++) // loop to write 
	{
	    fw.println (newText [i]); // Writing to file
	}

	fw.close (); // closes file
    }
    public static void main (String[] args) throws IOException
    {

	//Testing
	//-------------------------------------------------------------------------------------
	String fileName = "testing";

	String phrases[] = new String [5];  //declaring array of phrases with 5 elements

	Writer saver = new Writer (fileName); //creating TheFileSaveClass object

	for (int k = 0 ; k < phrases.length ; k++) //loop to fill array with phrase
	{
	    phrases [k] = "I love ICS";
	}

	saver.saveFile (fileName, phrases); //calling save file with FileName and phrases
	//------------------------------------------------------------------------------------
    }
}
