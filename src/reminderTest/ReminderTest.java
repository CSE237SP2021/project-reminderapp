package reminderTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import reminderApplication.Reminder;

import org.junit.jupiter.api.Test;

class ReminderTest {
	LocalDate dueDate = LocalDate.parse("2021-12-03");
	Reminder r = new Reminder(1,"title 1", "description 1", dueDate);
	
	
}
