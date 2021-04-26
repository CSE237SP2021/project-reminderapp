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
	
	// Add Fields
	private JTextField addTitleInput;
	private JTextField addDateInput;
	private JButton addCloseButton;
	
	// Edit Fields
	private JTextField editTitleInput;
	private JTextField editDateInput;
	private JButton editCloseButton;
	
	// Home Fields
	private JButton homeUpButton;
	private JButton homeDownButton;
	private JButton homeEditButton;
	private JButton homeDeleteButton;
	
	
	// Constructor
	public Application() {
		mainWindow = new JFrame();
		addWindow = new JFrame();
		editWindow = new JFrame();
		
		reminderList = new ReminderList();
		//integrate "load from file" method here
		//Should only load if a save file exists
		if(SaveAndLoad.checkIfSaveExists()==true) {
			reminderList=SaveAndLoad.loadFromFile();
		}
		
		textList = new JList<>();
		textListModel = new DefaultListModel<>();
		
		addTitleInput = null;
		addDateInput = null;
		addCloseButton = null;
		
		editTitleInput = null;
		editDateInput = null;
		editCloseButton = null;
		
		homeUpButton = new JButton("^");
		homeDownButton = new JButton("v");
		homeEditButton = new JButton("Edit");
		homeDeleteButton = new JButton("Delete");
	}
	
	// Methods
	
	//Getters
	public ReminderList getReminderList() {
		return reminderList;
	}
	
	public JTextField getAddTitleInput() {
		return addTitleInput;
	}
	
	public JTextField getAddDateInput() {
		return addDateInput;
	}
	
	public JButton getAddCloseButton() {
		return addCloseButton;
	}
	
	public JTextField getEditTitleInput() {
		return editTitleInput;
	}
	
	public JTextField getEditDateInput() {
		return editDateInput;
	}
	
	public JButton getEditCloseButton() {
		return editCloseButton;
	}

	// Template for Adding and Editing Reminder Windows
	public JTextField[] reminderWindowPreprocess(JFrame frame) {
		JLabel labelTitle = new JLabel("Title");
		labelTitle.setBounds(0,0,30,20);
		JTextField textInputTitle = new JTextField("");
		textInputTitle.setBounds(40,0,200,20);
		
		JLabel labelDate = new JLabel("Date");
		labelDate.setBounds(0,40,50,20);
		JTextField textInputDate = new JTextField("YYYY-MM-DD");
		textInputDate.setBounds(40,40,200,20);
		
		frame.add(labelTitle);
		frame.add(textInputTitle);
		frame.add(labelDate);
		frame.add(textInputDate);
		
		frame.setSize(240, 150);
		frame.setLayout(null);
		frame.setVisible(true);
		
		return new JTextField[]{textInputTitle, textInputDate};
	}
	
	
	// Add Reminder
	
	//create add reminder window
	public void initAddReminderWindow() {
		JTextField[] textInputs = reminderWindowPreprocess(addWindow);
		
		addTitleInput = textInputs[0];
		addDateInput = textInputs[1];
		addCloseButton = createAddCloseButton(textInputs);
		
		addWindow.setVisible(false);
	}
	
	public JButton createAddCloseButton(JTextField[] textInputs) {
		JButton closeBtn = new JButton("Finished");
		closeBtn.setBounds(80, 100, 80, 20);
		
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedNewReminder(null, textInputs[0].getText(), null, LocalDate.parse(textInputs[1].getText()));
				
				resetInputFields(textInputs);
				
				addWindow.setVisible(false);
				mainWindow.setVisible(true);
			}
		});
		
		addWindow.add(closeBtn);
		return closeBtn;
	}
	
	public void resetInputFields(JTextField[] textInputs) {
		textInputs[0].setText("");
		textInputs[1].setText("YYYY-MM-DD");
	}
	
	public void showAddReminderWindow() {
		mainWindow.setVisible(false);
		addWindow.setVisible(true);
	}
	
	// update list to show new reminder
	public void finishedNewReminder(Integer id, String title, String description, LocalDate date) {
		Reminder r =  new Reminder(id, title, description, date);
		
		reminderList.addReminder(r);
		
		updateList();
	}
	
	
	// Edit Reminder
	
	// create edit reminder window
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
	
	public JButton createEditCloseButton() {
		JButton closeBtn = new JButton("Edit");
		closeBtn.setBounds(80, 100, 80, 20);
		editWindow.add(closeBtn);
		
		return closeBtn;
	}
	
	public void updateEditWindowInputTextFields(Reminder reminder) {
		editTitleInput.setText(reminder.getTitle());
		editDateInput.setText(reminder.getDueDate().toString());
	}
	
	public void updateEditWindowInputButton(Reminder reminder) {
		editCloseButton.addActionListener(new ActionListener() {
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
		updateEditWindowInputButton(reminder);
		
		mainWindow.setVisible(false);
		editWindow.setVisible(true);
	}
	
	// update list to show edited reminder
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
	
	// setting up home buttons
	public void initMainWindow() {
		JButton addBtn = new JButton("+");
		addBtn.setBounds(0, 0, 20, 20);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setVisible(false);
				showAddReminderWindow();
			}
		});
		
		homeUpButton.setBounds(280,0,20,20);
		homeUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = textList.getSelectedIndex();
				reminderList.swapUpReminder(index);
				updateList();
				
				disableHomeButtons();
			}
		});
		
		homeDownButton.setBounds(300,0,20,20);
		homeDownButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = textList.getSelectedIndex();
				reminderList.swapDownReminder(index);
				updateList();
				
				disableHomeButtons();
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
		mainWindow.add(homeUpButton);
		mainWindow.add(homeDownButton);
		mainWindow.add(homeEditButton);
		mainWindow.add(homeDeleteButton);
		mainWindow.add(textList);
		
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
		homeUpButton.setEnabled(true);
		homeDownButton.setEnabled(true);
		homeEditButton.setEnabled(true);
		homeDeleteButton.setEnabled(true);
	}
	
	public void disableHomeButtons() {
		homeUpButton.setEnabled(false);
		homeDownButton.setEnabled(false);
		homeEditButton.setEnabled(false);
		homeDeleteButton.setEnabled(false);
	}

	public void exitSetting() {
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void run() {
		initMainWindow();
		initAddReminderWindow();
		initEditReminderWindow();
		exitSetting();
	}
}