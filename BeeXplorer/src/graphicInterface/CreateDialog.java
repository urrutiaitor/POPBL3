package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import robot.Map;

public class CreateDialog extends JDialog implements ActionListener, Runnable {

	JButton cancelButton;
	JButton closeButton;
	Map map;


	private Container createPane() {
		JSplitPane panel = new JSplitPane();
		panel.setDividerLocation(300);
		panel.setLeftComponent(createLeftSide());
		panel.setRightComponent(createScreen());
		return panel;
	}


	private Component createLeftSide() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 20, 20));
		panel.add(createNorthSide());
		panel.add(new JPanel());
		panel.add(createSouthSide());
		return panel;
	}


	private Component createNorthSide() {
		JPanel panel = new JPanel();
		return panel;
	}


	private Component createSouthSide() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 20, 20));
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setEnabled(true);
		closeButton = new JButton("Close");
		closeButton.addActionListener(this);
		closeButton.setEnabled(false);
		panel.add(cancelButton);
		panel.add(closeButton);
		return panel;
	}


	private Component createScreen() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("Map"), BorderLayout.CENTER);
		return panel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Cancel":
			System.out.println("Cancel the robot's inmersion");
			break;

		default:
			break;
		}
	}


	@Override
	public void run() {
		this.setLocation(100, 100);
		this.setSize(900, 600);
		this.setContentPane(createPane());
		this.setVisible(true);
		this.setModal(false);
		
		map = new Map();
		map.run();
		
	}
	
}