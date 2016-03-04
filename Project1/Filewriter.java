import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
//Sources
//http://www.tutorialspoint.com/java/stringbuffer_append.htm
//http://www.tutorialspoint.com/java/lang/stringbuilder_append_string.htm
//http://stackoverflow.com/questions/14534767/how-to-append-a-newline-to-stringbuilder
//http://www.coderanch.com/t/391312/java/java/StringBuffer-File
//http://tutorials.jenkov.com/java-howto/replace-strings-in-streams-arrays-files.html
public class Filewriter {
	//string buffer to edit text in file
	static StringBuffer sb = new StringBuffer();
	//sets file name to null will be changed later
	static String filename = null;
	//scanner for user editing of file
	static Scanner myscanner = new Scanner(System.in);
	
	public static void main(String[] args){
		//method to read the file with user entered name.
		boolean read = readfile();
		//if the file was found successfully 
			if(read) {
				replace();//method to find text to replace, and replace String buffer.
				write();//writes the edited file
			}
			//exits when program is complete
			System.exit(0);
	}
	private static void replace() {
		//Asks user for the line of the file they would like to edit
		System.out.println("\nPlease enter the contents of a line you would like to edit:");
		//reads the line to edit
		String lineedit = myscanner.nextLine();
		//Asks the user what they want to replace the line with
		System.out.println("Please enter the replacement text: ");
		//reads the the line entered
		String replacementline = myscanner.nextLine();
		//from Stackoverflow
		//gets the start point of the line to be edited
		int startindex = sb.indexOf(lineedit);
		//Adds the start index of the text with the text length to get to the end index.
		int endindex = startindex + lineedit.length();
		//From Stackoverflow
		//This is where the text replacement happens
		sb.replace(startindex, endindex, replacementline);
		//prints the edited file
		System.out.println("Here is the new edited text: \n" + sb);
		}
	
	private static boolean readfile(){
		//user enters the file path here
		System.out.println("Please enter your files path:");
		//replaces the filename with the entered path
		filename = myscanner.nextLine();
		Scanner fileToRead = null;
		try {
			fileToRead = new Scanner(new File(filename)); //scanner method to file
			//to check if the file has another line, checks if it is not null
			for (String line; fileToRead.hasNextLine() && (line = fileToRead.nextLine()) != null; ){
				//Prints the file to the console as its read
				System.out.print(line);
					//from StackOverflow
					//Appends the text read from the file to a string buffer, which will be used 
					//to edit the file \r\n makes a new line
					sb.append(line).append("\r\n");
			}
			//Scanner stops reading file
			fileToRead.close();
			return true;
			//catch statement if the path entered does not exist or cannot be found prints exception
			
		} catch (FileNotFoundException excep){
			System.out.println("The file " + filename + " could not be found " + excep.getMessage());
			return false;
			//closes the file if an error occurs
		} finally {
			fileToRead.close();
			return true;
			}
		}
	//From Coderanch
	//This method uses edited string buffer to write to the file
	private static void write(){
		try {
			//new filewriter for the edited text
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			//Writes the edited string buffer to the new file.
			writer.write(sb.toString());
			//closes the file
			writer.close();
		
		} catch (Exception e){//if for some reason the file was not able to write.
			System.out.println("Error occured while attempting to write to file: " + e.getMessage());
		}
	}
	
	}

