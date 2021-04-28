package reminderApplication;

import java.time.*;

public class Reminder {
    //fields
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String tag;

    // constructor
    public Reminder(Integer i, String t, String d, LocalDate dd, String ta) {
        id = (i != null) ? i : -1;
        title = (t != null) ? t : "";
        description = (d != null) ? d : "";
        dueDate = (dd != null) ? dd : LocalDate.parse("2007-12-03");
        tag = (ta != null) ? ta : "";
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
    
    //this method is used to return a tag, which is useful for getting all reminders with the tag 
    public String getTag() {
    	return tag;
    }
    
    //This method is for setting a new tag
    public void setTag(String t) {
    	tag = t;
    }
}