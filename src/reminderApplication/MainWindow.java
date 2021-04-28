package reminderApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

public class MainWindow {
	private JFrame frame;
	private AddReminderWindow addWindow;
	private EditReminderWindow editWindow;
	
	public ReminderList reminderList;
	public JList<String> textList;
	public DefaultListModel<String> textListModel;
	
	private JButton addBtn;
	private JButton upBtn;
	private JButton downBtn;
	private JButton editBtn;
	private JButton delBtn;

	public MainWindow() {
		frame = new JFrame();
		addWindow = new AddReminderWindow(this);
		editWindow = new EditReminderWindow(this);
		
		reminderList = new ReminderList();
		textList = new JList<>();
		textListModel = new DefaultListModel<>();
		
		addBtn = new JButton("+");
		upBtn = new JButton("^");
		downBtn = new JButton("v");
		editBtn = new JButton("Edit");
		delBtn = new JButton("Delete");
		
		createUIComponents();
	}
	
	
	public JFrame getFrame() {
		return frame;
	}
	
	public AddReminderWindow getAddWindow() {
		return addWindow;
	}
	
	public EditReminderWindow getEditWindow() {
		return editWindow;
	}
	
	public ReminderList getReminderList() {
		return reminderList;
	}
	
	public JList<String> getTextList() {
		return textList;
	}
	
	public DefaultListModel<String> getTextListModel() {
		return textListModel;
	}
	
	public void setTextList(JList<String> newTextList) {
		textList = newTextList;
	}
	
	public void setTextListModel(DefaultListModel<String> newTextListModel) {
		textListModel = newTextListModel;
	}
	
	
	public void createUIComponents() {
		configUIComponents();
		addUIComponentsToFrame();
		disableHomeButtons();
	}
	
	private void configUIComponents() {
		configAddButton();
		configUpButton();
		configDownButton();
		configEditButton();
		configDeleteButton();
		configFrame();
	}
	
	private void configAddButton() {
		addBtn.setBounds(0, 0, 20, 20);
		
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addWindow.openWindow();
			}
		});
	}
	
	private void configUpButton() {
		upBtn.setBounds(280,0,20,20);
		upBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reminderReorganizationBtnBehavior(true);
			}
		});
	}
	
	private void configDownButton() {
		downBtn.setBounds(300,0,20,20);
		downBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reminderReorganizationBtnBehavior(false);
			}
		});
	}
	
	private void reminderReorganizationBtnBehavior(boolean isUpBtn) {
		int index = textList.getSelectedIndex();
		
		if(isUpBtn) { reminderList.swapUpReminder(index); }
		else { reminderList.swapDownReminder(index); }
		
		UIComponentManager.updateListUI(this);
		disableHomeButtons();
	}
	
	private void configEditButton() {
		editBtn.setBounds(320, 0, 30, 20);
		editBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = textList.getSelectedIndex();
				
				editWindow.setReminder(reminderList.getList().get(index));
				editWindow.updateUIComponents();
				editWindow.openWindow();
				
				disableHomeButtons();
			}
		});
	}
	
	private void configDeleteButton() {
		delBtn.setBounds(350, 0, 50, 20);
		delBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = textList.getSelectedIndex();
				
				reminderList.removeReminder(index);
				textListModel.remove(index);
				
				disableHomeButtons();
			}
		});
	}

	private void configFrame() {
		frame.setSize(400, 500);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	private void addUIComponentsToFrame() {
		frame.add(addBtn);
		frame.add(upBtn);
		frame.add(downBtn);
		frame.add(editBtn);
		frame.add(delBtn);
		frame.add(textList);
	}
	
	
	public void enableHomeButtons() {
		upBtn.setEnabled(true);
		downBtn.setEnabled(true);
		editBtn.setEnabled(true);
		delBtn.setEnabled(true);
	}
	
	private void disableHomeButtons() {
		upBtn.setEnabled(false);
		downBtn.setEnabled(false);
		editBtn.setEnabled(false);
		delBtn.setEnabled(false);
	}
	
	public void exitSetting() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
