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
		
		mainWindow.getFrame().remove(mainWindow.getTextList());
		mainWindow.getFrame().validate();
		mainWindow.getFrame().repaint();
		
		mainWindow.setTextList(new JList<>(mainWindow.getTextListModel()));
		mainWindow.textList.setBounds(0, 50, 200, 380);
		mainWindow.textList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mainWindow.enableHomeButtons();
			}
		});
		
		
		mainWindow.getFrame().add(mainWindow.getTextList());
	}
	
	public static void updateListUI(MainWindow mainWindow, String tags) {
		ArrayList<Reminder> reminders = mainWindow.getReminderList().getTaggedRem(tags);
		
		mainWindow.setTextListModel(new DefaultListModel<>());
		
		for(Reminder reminder : reminders) {
			String title = reminder.getTitle();
			String date = reminder.getDueDate().toString();
			String tag = reminder.getTag();
			
			mainWindow.getTextListModel().addElement(title + "  |  " + date + " | " + tag);
		}
		
		mainWindow.getFrame().remove(mainWindow.getTextList());
		mainWindow.getFrame().validate();
		mainWindow.getFrame().repaint();
		
		mainWindow.setTextList(new JList<>(mainWindow.getTextListModel()));
		mainWindow.textList.setBounds(0, 50, 200, 380);
		mainWindow.textList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mainWindow.enableHomeButtons();
			}
		});
		
		mainWindow.getFrame().add(mainWindow.getTextList());
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
	private static void updateListUI() {
		
	}
	
}
