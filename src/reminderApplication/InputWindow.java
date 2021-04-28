package reminderApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public abstract class InputWindow {
	
	public void configUIComponents() {
		JFrame frame = getFrame();
		
		configTitleLabel(frame);
		configTitleInput(frame, getTitleInput());
		configDateLabel(frame);
		configDateInput(frame, getDateInput());
		configCloseBtn(frame, getCloseBtn());
		configInputWindowFrame(frame);
	}
	
	private void configTitleLabel(JFrame frame) {
		JLabel labelTitle = new JLabel("Title");
		labelTitle.setBounds(0,0,30,20);
		frame.add(labelTitle);
	}
	
	private void configTitleInput(JFrame frame, JTextField titleInputField) {
		titleInputField.setBounds(40,0,200,20);
		frame.add(titleInputField);
	}
	
	private void configDateLabel(JFrame frame) {
		JLabel labelDate = new JLabel("Date");
		labelDate.setBounds(0,40,50,20);
		frame.add(labelDate);
	}
	
	private void configDateInput(JFrame frame, JTextField dateInputField) {
		dateInputField.setBounds(40,40,200,20);
		frame.add(dateInputField);
	}
	
	private void configCloseBtn(JFrame frame, JButton closeBtn) {
		closeBtn.setBounds(80, 100, 80, 20);
		
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				closeButtonBehavior(); 
			}
		});
		
		frame.add(closeBtn);	
	}
	
	private void configInputWindowFrame(JFrame frame) {
		frame.setSize(240, 150);
		frame.setLayout(null);
	}

	public abstract JFrame  getFrame();
	public abstract JTextField getTitleInput();
	public abstract JTextField getDateInput();
	public abstract JButton getCloseBtn();
	
	public abstract void updateUIComponents();
	public abstract void closeButtonBehavior();
	public abstract void updateReminderList(Integer id, String title, String description, LocalDate date);
	public abstract void openWindow();
	public abstract void closeWindow();
}
