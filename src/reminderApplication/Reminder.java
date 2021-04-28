package reminderApplication;

import java.time.*;

public class Reminder {
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
    

    //this method is used to return a tag, which is useful for getting all reminders with the tag 
    public String getTag() {
    	return tag;
    }
    
    //This method is for setting a new tag
    public void setTag(String t) {
    	tag = t;
    }

    public void updateReminder(Integer id, String title, String description, LocalDate date, String tag){  	
		if(id != null) setId(id);
		if(title != null) setTitle(title);
		if(description != null) setDescription(description);
		if(date != null) setDueDate(date);
		if(tag != null) setTag(tag);
    }



}