package reminderApplication;

import java.awt.event.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;

public class Application {
	// Window Fields
	private static JFrame mainWindow = new JFrame();
	private static JFrame addWindow = new JFrame();
	private static JFrame editWindow = new JFrame();
	
	// List Fields
	private static ReminderList reminderList = new ReminderList();
	private static JList<String> textList = new JList<>();
	private static DefaultListModel<String> textListModel = new DefaultListModel<>();
	
	// Edit Fields
	private static JTextField editTitleInput = null;
	private static JTextField editDateInput = null;
	private static JButton editCloseButton = null;
	
	// Methods
	
	// Template for Adding and Editing Reminder Windows
	public static JTextField[] reminderWindowPreprocess(JFrame frame) {
		JLabel label_title = new JLabel("Title");
		label_title.setBounds(0,0,30,20);
		JTextField textInput_title = new JTextField("");
		textInput_title.setBounds(40,0,200,20);
		
		JLabel label_date = new JLabel("Date");
		label_date.setBounds(0,40,50,20);
		JTextField textInput_date = new JTextField("YYYY-MM-DD");
		textInput_date.setBounds(40,40,200,20);
		
		frame.add(label_title);
		frame.add(textInput_title);
		frame.add(label_date);
		frame.add(textInput_date);
		
		frame.setSize(240, 150);
		frame.setLayout(null);
		frame.setVisible(true);
		
		return new JTextField[]{textInput_title, textInput_date};
	}
	
	
	// New Reminder
	public static void initNewReminderWindow() {
		JTextField[] textInputs = reminderWindowPreprocess(addWindow);
		
		JButton close_btn = new JButton("Finished");
		close_btn.setBounds(80, 100, 80, 20);
		close_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedNewReminder(null, textInputs[0].getText(), null, LocalDate.parse(textInputs[1].getText()));
				
				textInputs[0].setText("");
				textInputs[1].setText("YYYY-MM-DD");
				
				addWindow.setVisible(false);
				mainWindow.setVisible(true);
			}
		});
		
		addWindow.add(close_btn);
		addWindow.setVisible(false);
	}
	
	public static void showAddReminderWindow() {
		mainWindow.setVisible(false);
		addWindow.setVisible(true);
	}
	
	public static void finishedNewReminder(Integer id, String title, String description, LocalDate date) {
		Reminder r =  new Reminder(id, title, description, date);
		
		reminderList.addReminder(r);
		updateList();
	}
	
	
	// Edit Reminder
	public static void initEditReminderWindow() {
		JTextField[] textInputs = reminderWindowPreprocess(editWindow);
		JButton close_btn = new JButton("Edit");
		close_btn.setBounds(80, 100, 80, 20);
		
		editTitleInput = textInputs[0];
		editDateInput = textInputs[1];
		editCloseButton = close_btn;
		
		editWindow.add(close_btn);
		editWindow.setVisible(false);
	}
	
	public static void updateEditWindowInputTextFields(Reminder reminder) {
		editTitleInput.setText(reminder.getTitle());
		editDateInput.setText(reminder.getDueDate().toString());
	}
	
	public static void updateEditWindowInputButton(Reminder reminder, JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedEditingReminder(reminder, null, editTitleInput.getText(), null,
						LocalDate.parse(editDateInput.getText()));

				editWindow.setVisible(false);
				mainWindow.setVisible(true);
			}
		});
	}
	
	public static void showEditReminderWindow(Reminder reminder) {
		updateEditWindowInputTextFields(reminder);
		updateEditWindowInputButton(reminder, editCloseButton);
		
		mainWindow.setVisible(false);
		editWindow.setVisible(true);
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

	
	// Main Window
	public static void initMainWindow() {
		JButton addBtn = new JButton("+");
		addBtn.setBounds(0, 0, 20, 20);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setVisible(false);
				showAddReminderWindow();
			}
		});
		
		JButton editBtn = new JButton("Edit");
		editBtn.setBounds(320, 0, 30, 20);
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = textList.getSelectedIndex();
				mainWindow.setVisible(false);
				showEditReminderWindow(reminderList.getList().get(index));
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

	public static void main(String[] args) {
		initMainWindow();
		initNewReminderWindow();
		initEditReminderWindow();
	}
}
