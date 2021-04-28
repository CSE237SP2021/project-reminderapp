package reminderApplication;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.*;
import java.util.ArrayList;

public class SaveAndLoad {

	public static boolean checkIfSaveExists(String fileToCheck) {
		File saveFile = new File(fileToCheck); 
		return saveFile.exists();
	}
	
	public static ReminderList loadFromFile(String fileToLoadFrom) {
		//outline from w3schools https://www.w3schools.com/java/java_files_read.asp
		
		ReminderList loadedReminderList= new ReminderList();
		    try {
		    	
		    	File myObj = new File(fileToLoadFrom);
		    	Scanner myReader = new Scanner(myObj);
		    	
		    	//first line of save will always be number of reminders
		    	String numberOfRemindersString = myReader.nextLine();
		    	int numberOfRemindersInt=Integer.parseInt(numberOfRemindersString);
		    	
		    	
		    	for(int i=0; i<numberOfRemindersInt; ++i) {

		    		String title = myReader.nextLine();
		    		
		    		String description = myReader.nextLine();
		    		
		    		String tag = myReader.nextLine();
		    		
		    		String dateString = myReader.nextLine();
		    		
		    		LocalDate localDateString = LocalDate.parse(dateString);
		    				    		
		    		Reminder currentReminder = new Reminder(i, title, description, localDateString, tag);
		    		loadedReminderList.addReminder(currentReminder);
		    		
		    	}

		      myReader.close();
		      return loadedReminderList;
		    } 
		    catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return null;
	}


	public static void saveToFile(ReminderList listToSave, String fileToSaveTo) {
		//code outline obtained from https://www.w3schools.com/java/java_files_create.asp
		ArrayList<Reminder> actualList = listToSave.getList();
		int numberOfReminders = listToSave.getSize();

		if(!checkIfSaveExists(fileToSaveTo)) {
			//code outline from w3schools
			// https://www.w3schools.com/java/java_files_create.asp
			 try {
			      File myObj = new File(fileToSaveTo);
			      if (myObj.createNewFile()) {
			        System.out.println("File created: " + myObj.getName());
			      } else {
			        System.out.println("File already exists.");
			      }
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}
		
		 try {
			 //If the file exists, the file will be overwritten
		      FileWriter saveWriter = new FileWriter(fileToSaveTo);
		      saveWriter.write(numberOfReminders + System.getProperty( "line.separator" ));
		      
		      for(int i=0; i<numberOfReminders; ++i) {
		    	  saveWriter.write(actualList.get(i).getTitle() + System.getProperty( "line.separator" ));
		    	  
		    	  saveWriter.write(actualList.get(i).getDescription() + System.getProperty( "line.separator" ));
		    	  
		    	  saveWriter.write(actualList.get(i).getTag() + System.getProperty( "line.separator" ));
		    	  
		    	  saveWriter.write(actualList.get(i).getDueDate().toString() + System.getProperty( "line.separator" ));
		    	  
		      }
		      
		      saveWriter.close();
		 }
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		return;
	}	
}