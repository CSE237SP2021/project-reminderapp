package reminderTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.ArrayList;

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
		int size = rl.getSize();
		rl.addReminder(r);
		rl.removeReminder(r.getId());
		assertTrue(size == rl.getSize());
	}
	
	@Test
	void testSort(){
		LocalDate dueDate1 = LocalDate.parse("2021-12-03");
		Reminder r1 = new Reminder(1,"title 1", "description 1", dueDate1);
		rl.addReminder(r1);
		LocalDate dueDate2 = LocalDate.parse("2020-12-01");
		Reminder r2 = new Reminder(1,"title 1", "description 1", dueDate2);
		rl.addReminder(r2);
		LocalDate dueDate3 = LocalDate.parse("2021-03-03");
		Reminder r3 = new Reminder(1,"title 1", "description 1", dueDate3);
		rl.addReminder(r3);
		rl.dateSort();
		ArrayList<Reminder> al =rl.getList();//sorted list
		ArrayList<Reminder> tl = new ArrayList<Reminder>();//true list
		tl.add(r2);
		tl.add(r3);
		tl.add(r1);
		boolean flag = true;
		int i = 0;
		while (i < 3 && flag == true) {
			System.out.println(al.get(i).getDueDate());
			if(al.get(i).getDueDate().isEqual(tl.get(i).getDueDate()) != true) {
				flag = false;
			}
			i++;
		}
		assertTrue(flag);
	}
	
	
//	@Test
//	void testSort2(){
//		LocalDate dueDate1 = LocalDate.parse("2021-12-03");
//		Reminder r1 = new Reminder(1,"title 1", "description 1", dueDate1);
//		rl.addReminder(r1);
//		LocalDate dueDate2 = LocalDate.parse("2020-12-01");
//		Reminder r2 = new Reminder(1,"title 1", "description 1", dueDate2);
//		rl.addReminder(r2);
//		LocalDate dueDate3 = LocalDate.parse("2020-03-03");
//		Reminder r3 = new Reminder(1,"title 1", "description 1", dueDate3);
//		rl.addReminder(r3);
//		LocalDate dueDate4 = LocalDate.parse("2018-12-03");
//		Reminder r4 = new Reminder(1,"title 1", "description 1", dueDate4);
//		rl.addReminder(r4);
//		LocalDate dueDate5 = LocalDate.parse("2022-12-03");
//		Reminder r5 = new Reminder(1,"title 1", "description 1", dueDate5);
//		rl.addReminder(r5);
//		rl.dateSort();
//		ArrayList<Reminder> al =rl.getList();//sorted list
//		ArrayList<Reminder> tl = new ArrayList<Reminder>();//true list
//		tl.add(r4);
//		tl.add(r3);
//		tl.add(r2);
//		tl.add(r1);
//		tl.add(r5);
//		boolean flag = true;
//		int i = 0;
//		while (i < 5 && flag == true) {
//			System.out.print(al.get(i).getDueDate());
//			System.out.print(" ");
//			System.out.println(tl.get(i).getDueDate());
//			if(al.get(i).getDueDate().isEqual(tl.get(i).getDueDate()) != true) {
//				flag = false;
//			}
//			i++;
//		}
//		assertTrue(flag);
//	}

}
