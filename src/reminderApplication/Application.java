package reminderApplication;

import java.awt.event.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Application {
	// Window Fields
	private JFrame mainWindow;
	private JFrame addWindow;
	private JFrame editWindow;
	
	// List Fields
	private ReminderList reminderList;
	private JList<String> textList;
	private DefaultListModel<String> textListModel;
	
	// Edit Fields
	private JTextField editTitleInput;
	private JTextField editDateInput;
	private JButton editCloseButton;
	
	// Home Fields
	private JButton homeEditButton;
	private JButton homeDeleteButton;
	
	
	// Constructor
	public Application() {
		mainWindow = new JFrame();
		addWindow = new JFrame();
		editWindow = new JFrame();
		
		reminderList = new ReminderList();
		textList = new JList<>();
		textListModel = new DefaultListModel<>();
		
		editTitleInput = null;
		editDateInput = null;
		editCloseButton = null;

		homeEditButton = new JButton("Edit");
		homeDeleteButton = new JButton("Delete");
	}
	
	// Methods
	
	// Template for Adding and Editing Reminder Windows
	public JTextField[] reminderWindowPreprocess(JFrame frame) {
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
	public void initNewReminderWindow() {
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
	
	public void showAddReminderWindow() {
		mainWindow.setVisible(false);
		addWindow.setVisible(true);
	}
	
	public void finishedNewReminder(Integer id, String title, String description, LocalDate date) {
		Reminder r =  new Reminder(id, title, description, date);
		
		reminderList.addReminder(r);
		updateList();
	}
	
	
	// Edit Reminder
	public void initEditReminderWindow() {
		JTextField[] textInputs = reminderWindowPreprocess(editWindow);
		JButton close_btn = new JButton("Edit");
		close_btn.setBounds(80, 100, 80, 20);
		
		editTitleInput = textInputs[0];
		editDateInput = textInputs[1];
		editCloseButton = close_btn;
		
		editWindow.add(close_btn);
		editWindow.setVisible(false);
	}
	
	public void updateEditWindowInputTextFields(Reminder reminder) {
		editTitleInput.setText(reminder.getTitle());
		editDateInput.setText(reminder.getDueDate().toString());
	}
	
	public void updateEditWindowInputButton(Reminder reminder, JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedEditingReminder(reminder, null, editTitleInput.getText(), null,
						LocalDate.parse(editDateInput.getText()));

				editWindow.setVisible(false);
				mainWindow.setVisible(true);
			}
		});
	}
	
	public void showEditReminderWindow(Reminder reminder) {
		updateEditWindowInputTextFields(reminder);
		updateEditWindowInputButton(reminder, editCloseButton);
		
		mainWindow.setVisible(false);
		editWindow.setVisible(true);
	}
	
	public void finishedEditingReminder(Reminder reminder, Integer id, String title, String description, LocalDate date) {
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
	public void initMainWindow() {
		JButton addBtn = new JButton("+");
		addBtn.setBounds(0, 0, 20, 20);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setVisible(false);
				showAddReminderWindow();
			}
		});
		
		homeEditButton.setBounds(320, 0, 30, 20);
		homeEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = textList.getSelectedIndex();
				mainWindow.setVisible(false);
				showEditReminderWindow(reminderList.getList().get(index));
				
				disableHomeButtons();
			}
		});
		
		homeDeleteButton.setBounds(350, 0, 50, 20);
		homeDeleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = textList.getSelectedIndex();
				reminderList.removeReminder(index);
				textListModel.remove(index);
				
				disableHomeButtons();
			}
		});	

		mainWindow.add(addBtn);
		mainWindow.add(homeEditButton);
		mainWindow.add(homeDeleteButton);
		mainWindow.add(textList);
		
		// Setting edit and delete buttons during initial start up
		disableHomeButtons();
		
		mainWindow.setSize(400, 500);
		mainWindow.setLayout(null);
		mainWindow.setVisible(true);
	}
	
	public void updateList() {
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
		textList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				enableHomeButtons();
			}
		});
		
		
		mainWindow.add(textList);
	}
	
	public void enableHomeButtons() {
		homeEditButton.setEnabled(true);
		homeDeleteButton.setEnabled(true);
	}
	
	public void disableHomeButtons() {
		homeEditButton.setEnabled(false);
		homeDeleteButton.setEnabled(false);
	}

	public void run() {
		initMainWindow();
		initNewReminderWindow();
		initEditReminderWindow();
	}
}
