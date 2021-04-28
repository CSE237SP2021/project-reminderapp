package reminderTest;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import reminderApplication.Reminder;
import reminderApplication.ReminderList;
import reminderApplication.SaveAndLoad;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.*;
import java.util.ArrayList;

public class SaveLoadTests {
	
	LocalDate dueDate1 = LocalDate.parse("2021-12-03");
	Reminder r1 = new Reminder(1,"1", "description 1", dueDate1, "tag1");
	
	LocalDate dueDate2 = LocalDate.parse("2021-12-03");
	Reminder r2 = new Reminder(1,"2", "description 2", dueDate2, "tag2");
	
	LocalDate dueDate3 = LocalDate.parse("2021-12-03");
	Reminder r3 = new Reminder(1,"2", "description 2", dueDate2, "tag1");
	
	ReminderList rl = new ReminderList();
	
	//set up save test
	@Test
	void testSaveAndLoad() {
		SaveAndLoad.saveToFile(rl, "testReminderSave.txt");
		ReminderList loadedList = SaveAndLoad.loadFromFile("testReminderSave.txt");
		
		for(int i = 0; i < rl.getSize(); ++i) {
			if(!loadedList.getList().get(i).getTitle().equals(rl.getList().get(i).getTitle()) || 
			   !loadedList.getList().get(i).getTag().equals(rl.getList().get(i).getTag()) || 
			   !loadedList.getList().get(i).getDueDate().toString().equals(rl.getList().get(i).getDueDate().toString())
			) {
				assertTrue(false);
				return;
			}	
		}
		assertTrue(true);
	}
	
	
}
