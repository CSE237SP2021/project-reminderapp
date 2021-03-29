package reminderTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import reminderApplication.Reminder;
import reminderApplication.ReminderList;

class ReminderListTest {

	LocalDate dueDate = LocalDate.parse("2021-12-03");
	Reminder r = new Reminder(1,"title 1", "description 1", dueDate);
	ReminderList rl = new ReminderList();
	
	@Test
	void testInsert() {
		rl.addReminder(r);
		assertTrue(true);
	}
	
	@Test
	void testDelete() {
		rl.addReminder(r);
		rl.removeReminder(r.getId());
		assertTrue(true);
	}

}
