package reminderApplication;

import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class AddReminderWindow extends InputWindow {
	private JFrame frame;
	private JTextField titleTextField;
	private	JTextField dateTextField;
	private JButton closeBtn;
	
	private MainWindow mainWindow;
	
	public AddReminderWindow(MainWindow pMainWindow) {
		frame = new JFrame();
		mainWindow = pMainWindow;
		
		titleTextField = new JTextField("");
		dateTextField = new JTextField("YYYY-MM-DD");
		closeBtn = new JButton("Finished");
		
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

	@Override
	public JButton getCloseBtn() {
		return closeBtn;
	}
	
	
	@Override
	public void updateUIComponents() {
		titleTextField.setText("");
		dateTextField.setText("YYYY-MM-DD");
	}
	
	@Override
	public void closeButtonBehavior() {
		updateReminderList(null, titleTextField.getText(), null, LocalDate.parse(dateTextField.getText()));
		updateUIComponents();
		closeWindow();
	}
	
	@Override
	public void updateReminderList(Integer id, String title, String description, LocalDate date) {
		mainWindow.getReminderList().addReminder(new Reminder(id, title, description, date));
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
