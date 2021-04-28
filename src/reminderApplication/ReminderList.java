package reminderApplication;

import java.util.ArrayList;

public class ReminderList {
    ArrayList<Reminder> reminders;

    public ReminderList() {
        reminders = new ArrayList<>();
    }
    
    public ArrayList<Reminder> getList() {
    	return reminders;
    }

    public void addReminder(Reminder r) {
        r.setId(reminders.size());
        reminders.add(r);
    }

    public int getSize() {
    	return reminders.size();
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
    
    //This method is used for getting all of the reminders that are tagged with the same tag. EX: would return a list of all reminders tagged "Homework"
    //This is case sensitive
    public ArrayList<Reminder> getTaggedRem(String tag){
    	ArrayList<Reminder> rl = new ArrayList<Reminder>();
    	
    	for(int i = 0; i < reminders.size(); i++) {
    		if(reminders.get(i).getTag().equals(tag)) {
    			rl.add(reminders.get(i));
    		}
    	}
    	
    	return rl;
    }
    
    
    //uses a merge sort to sort the Reminders by due dates, the earliest duedate is in the front 
    public void dateSort() {    	
    	reminders = dateDivide(reminders);
    }
    
    
    public ArrayList<Reminder> dateDivide(ArrayList<Reminder> rl) {
        ArrayList<Reminder> left = new ArrayList<Reminder>();
        ArrayList<Reminder> right = new ArrayList<Reminder>();
        int center;
     
        if (rl.size() == 1) {    
            return rl;
        } else {
            center = rl.size()/2;
           
            for (int i=0; i<center; i++) {
                    left.add(rl.get(i));
            }
     
            
            for (int i=center; i<rl.size(); i++) {
                    right.add(rl.get(i));
            }
     
            
            left  = dateDivide(left);
            right = dateDivide(right);
     
            
            dateMerge( rl, left, right);
        }
        return rl;
    }
    
    
    private void dateMerge( ArrayList<Reminder> rl, ArrayList<Reminder> left, ArrayList<Reminder> right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int rlIndex = 0;
     
        
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ( (left.get(leftIndex).getDueDate().isBefore(right.get(rightIndex).getDueDate()))) {
                rl.set(rlIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                rl.set(rlIndex, right.get(rightIndex));
                rightIndex++;
            }
            rlIndex++;
        }
     
        ArrayList<Reminder> remaining;
        int remainingIndex;
        if (leftIndex >= left.size()) {
            
            remaining = right;
            remainingIndex = rightIndex;
        } else {
           
            remaining = left;
            remainingIndex = leftIndex;
        }
     
       
        for (int i= remainingIndex; i<remaining.size(); i++) {
            rl.set(rlIndex, remaining.get(i));
            rlIndex++;
        }
    }
}
