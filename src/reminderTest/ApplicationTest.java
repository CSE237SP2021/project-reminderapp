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
		app.finishedNewReminder(null, "title 1", null, dueDate, null);
		
		Reminder r = app.getReminderList().getList().get(0);
		
		assertTrue(r.getTitle().equals("title 1") && r.getDueDate().toString().equals("2021-12-03"));
	}
	
	@Test
	void testEditExistingReminder() {
		LocalDate dueDate = LocalDate.parse("2021-12-03");
		app.finishedNewReminder(null, "title 1", null, dueDate, null);
		
		Reminder r = app.getReminderList().getList().get(0);
		LocalDate newDueDate = LocalDate.parse("1111-11-11");
		
		app.finishedEditingReminder(r, null, "new title", null, newDueDate, "tag");
		assertTrue(r.getTitle().equals("new title") && r.getDueDate().toString().equals("1111-11-11"));
	}

	@Test
	void testFaultyDateInput() {
		LocalDate dueDate = LocalDate.parse("hello, world");
		app.finishedNewReminder(null, "title 1", null, dueDate, null);
		
		Reminder r = app.getReminderList().getList().get(0);
		
		assertTrue(r.getTitle().equals("title 1") && r.getDueDate().toString().equals("hello, world"));
	}
}
