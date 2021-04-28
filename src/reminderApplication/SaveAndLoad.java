package reminderApplication;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.*;
import java.util.ArrayList;

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
		    		//get title
		    		String title = myReader.nextLine();

		    		//get description
		    		String description = myReader.nextLine();

		    		//get date
		    		String dateString = myReader.nextLine();

		    		//convert date
		    		LocalDate localDateString = LocalDate.parse(dateString);
		    		
		    		String tag = myReader.nextLine();

		    		Reminder currentReminder = new Reminder(i, title, description, localDateString, tag);
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
	public static void saveToFile(ReminderList listToSave) {
		//code outline obtained from https://www.w3schools.com/java/java_files_create.asp
		int numberOfReminders = listToSave.getSize();
		ArrayList<Reminder> actualList = listToSave.getList();
		 try {
			 //this -should- simply overwrite the contents of the file
		      FileWriter saveWriter = new FileWriter("SavedReminders.txt");





		      for(int i=0; i<numberOfReminders; ++i) {
		    	  //write 1 reminder to file
		    	  //write title
		    	  saveWriter.write(actualList.get(i).getTitle());

		    	  //write description
		    	  saveWriter.write(actualList.get(i).getDescription());

		    	  //write tag (when implemented)
		    	  //saveWriter.write(actualList.get(i).getTag());

		    	  //write date
		    	  saveWriter.write(actualList.get(i).getDueDate().toString());
		    	  
		    	  saveWriter.write(actualList.get(i).getTag());

		      }



		      saveWriter.close();
		      //System.out.println("Successfully wrote to the file.");
		 }
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}

		return;
	}	
//	not actually going to use this one as I would have to have the filewriter as a parameter
//	public void writeReminderToFile() {
//		
//		return;
//	}
}