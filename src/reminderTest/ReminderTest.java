package reminderTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import reminderApplication.Reminder;

import org.junit.jupiter.api.Test;

class ReminderTest {
	
	LocalDate dueDate = LocalDate.parse("2021-12-03");
	Reminder r = new Reminder(1,"title 1", "description 1", dueDate);
	
	
	@Test
	void testSetTitle() {
		String newTitle = "title 2";
		r.setTitle(newTitle);
		assertTrue(r.getTitle().equals(newTitle));
	}

	@Test
	void testSetDescription() {
		String newDes = "des 2";
		r.setDescription(newDes);
		assertTrue(r.getDescription().equals(newDes));
	}
	
	@Test
	void testSetDueDate() {
		LocalDate newDueDate = LocalDate.parse("2021-11-11");
		r.setDueDate(newDueDate);
		assertTrue(r.getDueDate().equals(newDueDate));
	}
	
	@Test
	void testSetId() {
		int newId = 2;
		r.setId(newId);
		assertTrue(r.getId() == newId);
	}
	
	
}
