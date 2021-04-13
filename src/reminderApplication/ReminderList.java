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
    
    public void removeReminder(int index) {
    	reminders.remove(index);
    }
    
    public void swapUpReminder(int index) {
    	if(index == 0) return;
    	Reminder tmp = reminders.get(index);
    	reminders.set(index, reminders.get(index - 1));
    	reminders.set(index - 1, tmp);
    }
    
    public void swapDownReminder(int index) {
    	if(index == reminders.size() - 1) return;
    	Reminder tmp = reminders.get(index);
    	reminders.set(index, reminders.get(index + 1));
    	reminders.set(index + 1, tmp);
    }
}
