package reminderApplication;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public abstract class UIComponentManager {
	public static void updateListUI(MainWindow mainWindow) {
		ArrayList<Reminder> reminders = mainWindow.getReminderList().getList();
		
		updateTextListModel(reminders, mainWindow);
		updateMainWindowUI(mainWindow);
		configureMainWindowUI(mainWindow);
		updateTextList(mainWindow);
	}
	
	public static void updateListUI(MainWindow mainWindow, String tags) {
		ArrayList<Reminder> reminders = mainWindow.getReminderList().getTaggedRem(tags);
		
		updateTextListModel(reminders, mainWindow);
		updateMainWindowUI(mainWindow);
		configureMainWindowUI(mainWindow);
		updateTextList(mainWindow);
	}
	
	private static void updateTextListModel(ArrayList<Reminder> reminders, MainWindow mainWindow) {
		
		mainWindow.setTextListModel(new DefaultListModel<>());
		
		for(Reminder reminder : reminders) {
			String title = reminder.getTitle();
			String date = reminder.getDueDate().toString();
			String tag = reminder.getTag();
			
			mainWindow.getTextListModel().addElement(title + "  |  " + date + " | " + tag);
		}
	}
	
	private static void updateMainWindowUI(MainWindow mainWindow) {
		mainWindow.getFrame().remove(mainWindow.getTextList());
		mainWindow.getFrame().validate();
		mainWindow.getFrame().repaint();
	}
	
	private static void configureMainWindowUI(MainWindow mainWindow) {
		mainWindow.setTextList(new JList<>(mainWindow.getTextListModel()));
		mainWindow.textList.setBounds(0, 50, 200, 380);
		mainWindow.textList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mainWindow.enableHomeButtons();
			}
		});
	}

	private static void updateTextList(MainWindow mainWindow) {
		mainWindow.getFrame().add(mainWindow.getTextList());
	}
}
