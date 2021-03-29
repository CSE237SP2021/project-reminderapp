package reminderApplication;

import java.awt.event.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;

public class Application {
	private static JFrame mainWindow = new JFrame();
	private static ReminderList reminderList = new ReminderList();
	private static JList<String> textList = new JList<>();
	private static DefaultListModel<String> textListModel = new DefaultListModel<>();
	
	//methods
	public static void finishedNewReminder(Integer id, String title, String description, LocalDate date) {
		Reminder r =  new Reminder(id, title, description, date);
		
		reminderList.addReminder(r);
		updateList();
	}
	
	public static void finishedEditingReminder(Reminder reminder, Integer id, String title, String description, LocalDate date) {
		if(id != null) {
			reminder.setId(id);
		}
		if(title != null) {
			reminder.setTitle(title);
		}
		if(description != null) {
			reminder.setDescription(description);
		}
		if(date != null) {
			reminder.setDueDate(date);
		}
		
		updateList();
	}
	
	//TO DO: ONLY CHANGE THE TEXTLISTMODEL, DO NOT HAVE TO RENDER THE ENTIRE LIST AGAIN
	public static void updateList() {
		ArrayList<Reminder> reminders = reminderList.getList();
		textListModel = new DefaultListModel<>();
		
		for(Reminder reminder : reminders) {
			String title = reminder.getTitle();
			String date = reminder.getDueDate().toString();
			
			textListModel.addElement(title + "  |  " + date);
		}
		
		mainWindow.remove(textList);
		mainWindow.validate();
		mainWindow.repaint();
		
		textList = new JList<>(textListModel);
		textList.setBounds(0, 30, 200, 380);
		
		mainWindow.add(textList);
	}
	
	//TO DO: MUST BE FIXED SO THAT ONLY ONE INSTANCE EXISTS (instead of created every single time)
	public static void newReminder() {
		JFrame f = new JFrame();
		
		JLabel l1 = new JLabel("Title");
		l1.setBounds(0,0,30,20);
		JTextField t1 = new JTextField("");
		t1.setBounds(40,0,200,20);
		
		JLabel l2 = new JLabel("Date");
		l2.setBounds(0,40,50,20);
		JTextField t2 = new JTextField("YYYY-MM-DD");
		t2.setBounds(40,40,200,20);
		
		JButton close =  new JButton("Finished");
		close.setBounds(80, 100, 80, 20);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedNewReminder(null, t1.getText(), null, LocalDate.parse(t2.getText()));
				
				f.dispose();
				mainWindow.setVisible(true);
			}
		});
		
		// LAZINESS
		f.add(l1);
		f.add(t1);
		f.add(l2);
		f.add(t2);
		f.add(close);
		
		f.setSize(240, 150);
		f.setLayout(null);
		f.setVisible(true);
	}
	
	//Can combine with previous code into only one method instead of 2
	//TO DO: MUST BE FIXED SO THAT ONLY ONE INSTANCE EXISTS (instead of created every single time)
	public static void editReminder(Reminder reminder) {
		JFrame f = new JFrame();
		
		JLabel l1 = new JLabel("Title");
		l1.setBounds(0,0,30,20);
		JTextField t1 = new JTextField(reminder.getTitle());
		t1.setBounds(40,0,200,20);
		
		JLabel l2 = new JLabel("Date");
		l2.setBounds(0,40,50,20);
		JTextField t2 = new JTextField(reminder.getDueDate().toString());
		t2.setBounds(40,40,200,20);
		
		JButton close =  new JButton("Edit");
		close.setBounds(80, 100, 80, 20);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedEditingReminder(reminder, null, t1.getText(), null, LocalDate.parse(t2.getText()));
				
				f.dispose();
				mainWindow.setVisible(true);
			}
		});
		
		// LAZINESS
		f.add(l1);
		f.add(t1);
		f.add(l2);
		f.add(t2);
		f.add(close);
		
		f.setSize(240, 150);
		f.setLayout(null);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		
		JButton addBtn = new JButton("+");
		addBtn.setBounds(0, 0, 20, 20);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.dispose();
				
				newReminder();
			}
		});
		
		JButton editBtn = new JButton("Edit");
		editBtn.setBounds(320, 0, 30, 20);
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = textList.getSelectedIndex();
				
				
				mainWindow.dispose();
				
				editReminder(reminderList.getList().get(index));
			}
		});
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(350, 0, 50, 20);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = textList.getSelectedIndex();
				reminderList.removeReminder(index);
				textListModel.remove(index);
			}
		});	
		
		// TO DO: WRITE CODE THAT WILL ENABLE AND DISABLE THE EDIT AND DELETE BUTTONS

		mainWindow.add(addBtn);
		mainWindow.add(editBtn);
		mainWindow.add(deleteBtn);
		mainWindow.add(textList);
		
		mainWindow.setSize(400, 500);
		mainWindow.setLayout(null);
		mainWindow.setVisible(true);
	}
	
	
}
