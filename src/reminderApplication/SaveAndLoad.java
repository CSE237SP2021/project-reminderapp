package reminderApplication;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.*;

public class SaveAndLoad {

	
	public static boolean checkIfSaveExists() {
		File saveFile = new File("SavedReminders.txt"); 
		return saveFile.exists();
	}
	
	
	
	
	
	public static ReminderList loadFromFile() {
		//outline from w3schools https://www.w3schools.com/java/java_files_read.asp
		//this is super messy and I need to clean it up later but for now I need to get something down
		
		ReminderList loadedReminderList= new ReminderList();
		    try {
		    	
		    	File myObj = new File("SavedReminders.txt");
		    	Scanner myReader = new Scanner(myObj);
		    	
//		    	while (myReader.hasNextLine()) {
//		    		String nextReminder = myReader.nextLine();
		    	
		    	
		    	//first line of save will always be number of reminders
		    	String numberOfRemindersString = myReader.nextLine();
		    	int numberOfRemindersInt=Integer.parseInt(numberOfRemindersString);
		    	
		    	
		    	for(int i=0; i<numberOfRemindersInt; ++i) {
		    		//come back and put this process in it's own method
		    		
		    		//parse lines from SavedReminders.txt into a new reminder, and add it to the list.
		    		String title = myReader.nextLine();
		    		String description = myReader.nextLine();
		    		String dateString = myReader.nextLine();
		    		LocalDate dateLD = LocalDate.parse(dateString);
		    				    		
		    		Reminder currentReminder = new Reminder(i, title, description, dateLD);
		    		loadedReminderList.addReminder(currentReminder);
		    		
		    	}
		    	
		    	
		        
		        
		        
		      
		      myReader.close();
		      return loadedReminderList;
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return null;
	}
	
	
	
	
	
	//TODO Finish this
	public void saveToFile() {
		//code outline obtained from https://www.w3schools.com/java/java_files_create.asp
		 try {
		      FileWriter myWriter = new FileWriter("SavedReminders.txt");
		      
		      
		      
		      
		      for(int i=0; i<4; ++i) {
		    	  //write 1 reminder to file
		      }
		      
		      
		      
		      myWriter.close();
		      //System.out.println("Successfully wrote to the file.");
		 }
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		return;
	}	
	
	public void writeReminderToFile() {
		
		return;
	}
}
