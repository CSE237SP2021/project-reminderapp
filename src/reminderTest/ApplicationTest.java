package reminderTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import reminderApplication.Application;
import reminderApplication.Reminder;

public class ApplicationTest {
	Application app = new Application();
	
	@Test
	void testAddNewReminder() {	
		LocalDate dueDate = LocalDate.parse("2021-12-03");
		app.finishedNewReminder(null, "title 1", null, dueDate);
		
		Reminder r = app.getReminderList().getList().get(0);
		
		assertTrue(r.getTitle().equals("title 1") && r.getDueDate().toString().equals("2021-12-03"));
	}
	
	@Test
	void testEditExistingReminder() {
		LocalDate dueDate = LocalDate.parse("2021-12-03");
		app.finishedNewReminder(null, "title 1", null, dueDate);
		
		Reminder r = app.getReminderList().getList().get(0);
		LocalDate newDueDate = LocalDate.parse("1111-11-11");
		
		app.finishedEditingReminder(r, null, "new title", null, newDueDate);
		assertTrue(r.getTitle().equals("new title") && r.getDueDate().toString().equals("1111-11-11"));
	}
	
	// the user can input any type of string for the date input.
	// From the code, we can see that the parse method fails if a faulty input is given
	// This is also reflective in our current program
	@Test
	void testFaultyDateInput() {
		LocalDate dueDate = LocalDate.parse("hello, world");
		app.finishedNewReminder(null, "title 1", null, dueDate);
		
		Reminder r = app.getReminderList().getList().get(0);
		
		assertTrue(r.getTitle().equals("title 1") && r.getDueDate().toString().equals("hello, world"));
	}
	
	void testDeleteReminder() {
		// this test has been omitted because deleteReminder ultimately calls remove
		// on a arraylist of reminders. And thus, we know that it works.
	}
	
	
	// HomeUpButton and HomeDownButton has been tested
	// within the ReminderListTest class
}
