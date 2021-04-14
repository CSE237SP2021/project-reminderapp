package reminderApplication;

import java.time.*;

public class Reminder {
    //fields
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    //private int priority;

    // constructor
    public Reminder(Integer i, String t, String d, LocalDate dd) {
        id = (i != null) ? i : -1;
        title = (t != null) ? t : "";
        description = (d != null) ? d : "";
        dueDate = (dd != null) ? dd : LocalDate.parse("2007-12-03");
       // priority = (p != null) ? p : 0;
    }

    // methods
    public int getId(){
        return id;
    }
    public void setId(int val) {
        id = val;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String t) {
    	title = t;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String d) {
    	description = d;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dd) {
    	dueDate = dd;
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