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
    //returns the size of the list
    public int getSize() {
    	return reminders.size();
    }
    
    public void removeReminder(int id) {
    	reminders.remove(id);
    }
    
    //uses a merge sort to sort the Reminders by due dates, the earliest duedate is in the front 
    public void dateSort() {
    	ArrayList<Reminder> rl = new ArrayList<Reminder>();
    	for (int i =0; i < reminders.size(); i++) {
    		rl.add(i, reminders.get(i));
    	}
    	dateDivide(reminders);
    	//reminders = rl;
    }
    
    
    //Starts the divide process of the merge sort
    public void dateDivide(ArrayList<Reminder> rl) {
    	ArrayList <Reminder> left = new ArrayList<Reminder>();
    	ArrayList<Reminder> right = new ArrayList<Reminder>();
    	if(rl.size()> 1){
    		int mid = rl.size()/2;
    		
    		for (int i = 0; i < mid; i ++) {
    			left.add(rl.get(i));
    			
    		}
    		
    		for (int j = mid; j < rl.size(); j++) {
    			right.add(rl.get(j));
    		}
    		
    		dateDivide(left);
    		dateDivide(right);
    		dateMerge(rl, left, right);
    	}
  
    	
    }
    
    
    //merges the arrays back together
    public void dateMerge(ArrayList<Reminder> rl, ArrayList<Reminder> left, ArrayList<Reminder> right) {
    	//ArrayList<Reminder> temp = new ArrayList<Reminder>();
    	int l = 0;
    	int r = 0;
    	int index = 0;
    	while (l < left.size() && r < right.size()) {
    		if(left.get(l).getDueDate().isBefore(right.get(r).getDueDate())){
    			reminders.set(index,left.get(l));
    			l++;
    		}
    		else {
    			reminders.set(index,right.get(r));
    			r++;
    		}
    		index++;
    	}
    	
    	if(l >= left.size()) {
    		for(int i = r; i < right.size(); i++) {
    			reminders.set(index, right.get(i));
    			index++;
    		}
    	}
    	
    	if(r >= right.size()) {
    		for(int i = l; i < left.size(); i++) {
    			reminders.add(index, left.get(i));
    			index++;
    		}
    	}
    	
    	
    }
}
