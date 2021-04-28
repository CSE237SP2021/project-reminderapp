package reminderApplication;

import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class EditReminderWindow extends InputWindow {
	private JFrame frame;
	private JTextField titleTextField;
	private	JTextField dateTextField;
	private JTextField tagTextField;
	private JButton closeBtn;
	
	private Reminder reminder;
	
	private MainWindow mainWindow;
	
	public EditReminderWindow(MainWindow pMainWindow){
		frame = new JFrame();
		mainWindow = pMainWindow;
			
		titleTextField = new JTextField("");
		dateTextField = new JTextField("YYYY-MM-DD");
		tagTextField = new JTextField("");
		closeBtn = new JButton("Edit");
		reminder = null;
			
		configUIComponents();
	}

	
	@Override
	public JFrame getFrame() {
		return frame;
	}

	@Override
	public JTextField getTitleInput() {
		return titleTextField;
	}

	@Override
	public JTextField getDateInput() {
		return dateTextField;
	}
	
	public JTextField getTagInput() {
		return tagTextField;
	}

	@Override
	public JButton getCloseBtn() {
		return closeBtn;
	}
	
	public void setReminder(Reminder newReminder) {
		reminder = newReminder;
	}
	
	
	@Override
	public void updateUIComponents() {
		titleTextField.setText(reminder.getTitle());
		dateTextField.setText(reminder.getDueDate().toString());
		tagTextField.setText(reminder.getTag());
	}
	
	@Override
	public void closeButtonBehavior() {
		updateReminderList(null, titleTextField.getText(), null, LocalDate.parse(dateTextField.getText()), tagTextField.getText());
		closeWindow();
	}
	
	@Override
	public void updateReminderList(Integer id, String title, String description, LocalDate date, String tag) {
		reminder.updateReminder(id, title, description, date, tag);
		UIComponentManager.updateListUI(mainWindow);
	}

	@Override
	public void openWindow() {
		frame.setVisible(true);
		mainWindow.getFrame().setVisible(false);
	}

	@Override
	public void closeWindow() {
		frame.setVisible(false);
		mainWindow.getFrame().setVisible(true);
	}

	
}
