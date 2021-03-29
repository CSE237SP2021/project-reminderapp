package reminderApplication;

import java.util.ArrayList;

public class ReminderList {
    //fields
    ArrayList<Reminder> reminders;

    //constructor
    public ReminderList() {
        reminders = new ArrayList<>();
    }
    
    public ArrayList<Reminder> getList() {
    	return reminders;
    }

    //methods
    public void addReminder(Reminder r) {
        r.setId(reminders.size());
        reminders.add(r);
    }
    
    public void removeReminder(int id) {
    	reminders.remove(id);
    }
}
