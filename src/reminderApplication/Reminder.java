package reminderApplication;

import java.time.*;

public class Reminder {
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    //private int priority;

    public Reminder(Integer i, String t, String d, LocalDate dd) {
        id = (i != null) ? i : -1;
        title = (t != null) ? t : "";
        description = (d != null) ? d : "";
        dueDate = (dd != null) ? dd : LocalDate.parse("0000-01-01");
       // priority = (p != null) ? p : 0;

    }

    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public void setId(int val) {
        id = val;
    }

    public void setTitle(String t) {
    	title = t;
    }

    public void setDescription(String d) {
    	description = d;
    }

    public void setDueDate(LocalDate dd) {
    	dueDate = dd;
    }
    
    public void updateReminder(Integer id, String title, String description, LocalDate date) {   	
		if(id != null) setId(id);
		if(title != null) setTitle(title);
		if(description != null) setDescription(description);
		if(date != null) setDueDate(date);
    }

    //These methods are the setter/getters for the priority which describes how each task ranks in importance with respect to eachother
//    public int getPriority() {
//    	return priority;
//    }
//    
//    public void setPriority(int val) {
//    	priority = val;
//    }
}