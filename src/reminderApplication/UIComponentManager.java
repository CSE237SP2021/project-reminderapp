package reminderApplication;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public abstract class UIComponentManager {
	public static void updateListUI(MainWindow mainWindow) {
		ArrayList<Reminder> reminders = mainWindow.getReminderList().getList();
		mainWindow.setTextListModel(new DefaultListModel<>());
		
		for(Reminder reminder : reminders) {
			String title = reminder.getTitle();
			String date = reminder.getDueDate().toString();
			
			mainWindow.getTextListModel().addElement(title + "  |  " + date);
		}
		
		mainWindow.getFrame().remove(mainWindow.getTextList());
		mainWindow.getFrame().validate();
		mainWindow.getFrame().repaint();
		
		mainWindow.setTextList(new JList<>(mainWindow.getTextListModel()));
		mainWindow.textList.setBounds(0, 30, 200, 380);
		mainWindow.textList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mainWindow.enableHomeButtons();
			}
		});
		
		
		mainWindow.getFrame().add(mainWindow.getTextList());
	}
}
