package reminderTest;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import reminderApplication.Reminder;
import reminderApplication.ReminderList;
import org.junit.jupiter.api.Test;

class ReminderListTest {

	LocalDate dueDate1 = LocalDate.parse("2021-12-03");
	Reminder r1 = new Reminder(1,"1", "description 1", dueDate1);
	
	LocalDate dueDate2 = LocalDate.parse("2021-12-03");
	Reminder r2 = new Reminder(1,"2", "description 2", dueDate2);
	
	ReminderList rl = new ReminderList();
	
	
	// Testing if swapUp works
	@Test
	void testSwapUpNormal() {
		rl.addReminder(r1);
		rl.addReminder(r2);
		
		rl.swapUpReminder(1);
		
		Reminder first = rl.getList().get(0);
		Reminder second = rl.getList().get(1);
		
		// testing that a swap has occurred and that swap works correctly
		assertTrue(rl.getList().size() == 2 && first != second && first == r2);
	}

	// used to test what would happen if we tried swapping up from the 0th index
	@Test
	void testSwapUpNormalEdgeCase() {
		rl.addReminder(r1);
		rl.addReminder(r2);
		
		rl.swapUpReminder(0);
		
		Reminder first = rl.getList().get(0);
		Reminder second = rl.getList().get(1);
		
		// testing that a swap has occurred
		// after a bad swap, r1 should be in its original position
		assertTrue(rl.getList().size() == 2 && first != second && first == r1);
	}
	
	// Testing if swapDown works
	@Test
	void testSwapDownNormal() {
		rl.addReminder(r1);
		rl.addReminder(r2);
		
		rl.swapDownReminder(0);
		
		Reminder first = rl.getList().get(0);
		Reminder second = rl.getList().get(1);
		
		// testing that a swap has occurred and swap is correct
		assertTrue(rl.getList().size() == 2 && first != second && second == r1);
	}

	// used to test what would happen if we tried swapping down from the last possible index
	@Test
	void testSwapDownEdgeCase() {
		rl.addReminder(r1);
		rl.addReminder(r2);
		
		rl.swapDownReminder(1);
		
		Reminder first = rl.getList().get(0);
		Reminder second = rl.getList().get(1);
		
		// testing that a swap has occurred
		// after a bad swap, r2 should be in its original position
		assertTrue(rl.getList().size() == 2 && first != second && second == r2);
	}
}
