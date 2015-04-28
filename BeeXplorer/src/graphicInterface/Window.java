package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class Window extends JFrame implements Runnable{
	
	JMenuBar bar;
	
	JMenu robotBar;
	JMenu computerBar;
	JMenu serverBar;
	JMenu exitBar;
	
	JMenuItem menuOption;
	
	AbstractAction accCreate, accEdit, accShow, accShare, accManual, accDelete, accExit;

	public Window(){
		super("BeeXplorer");
	}
	
	public void createActions(){
		accCreate = new MyAction ("Create map", new ImageIcon(""), "Create map", KeyEvent.VK_C);
		accEdit = new MyAction ("Edit map", new ImageIcon(""), "Edit Map", KeyEvent.VK_C);
		accShow = new MyAction ("Show", new ImageIcon(""), "Show map", KeyEvent.VK_C);
		accShare = new MyAction ("Share", new ImageIcon(""), "Share map", KeyEvent.VK_C);
		accManual = new MyAction ("Manual control", new ImageIcon(""), "Control the robot manualy", KeyEvent.VK_C);
		accDelete = new MyAction ("Delete", new ImageIcon("icons/delete.png"), "Delete map", KeyEvent.VK_C);
		accExit = new MyAction ("Exit", new ImageIcon("icons/exit.png"), "Exit the Program", KeyEvent.VK_C);
	}

	private Container createWindowPane() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 20, 20));
		panel.add(createRobotPanel());
		panel.add(createComputerServerPanel());
		return panel;
	}

	private Component createRobotPanel() {
		JPanel subPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		subPanel.add(new JButton(accManual));
		subPanel.add(new JButton(accEdit));
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.add(new JButton(accCreate));
		panel.add(subPanel);
		return panel;
	}

	private Component createComputerServerPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 20, 20));
		panel.add(createComputerPanel());
		panel.add(createServerPanel());
		panel.add(createExitPanel());
		return panel;
	}

	private Component createComputerPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 20));
		panel.add(new JButton(accShow));
		panel.add(new JButton(accDelete));
		return panel;
	}

	private Component createServerPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JButton(accShare), BorderLayout.CENTER);
		return panel;
	}
	
	private Component createExitPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JButton(accExit), BorderLayout.CENTER);
		return panel;
	}

	private JMenuBar createBar() {
		bar = new JMenuBar();
		bar.add(createRobotBar());
		bar.add(createComputerBar());
		bar.add(createServerBar());
		bar.add(Box.createHorizontalGlue());
		bar.add(createExitBar());
		
		return bar;
	}

	private JMenu createRobotBar() {
		robotBar = new JMenu ("Robot");
		
		menuOption = robotBar.add(accCreate);
		menuOption = robotBar.add(accManual);
		menuOption = robotBar.add(accEdit);
		
		robotBar.add(menuOption);
		
		return robotBar;
	}

	private JMenu createComputerBar() {
		computerBar = new JMenu ("Computer");
		
		menuOption = computerBar.add(accDelete);
		menuOption = computerBar.add(accShow);
		
		computerBar.add(menuOption);
		
		return computerBar;
	}
	
	private JMenu createServerBar() {
		serverBar = new JMenu ("Server");
		
		menuOption = serverBar.add(accShare);
		
		serverBar.add(menuOption);
		
		return serverBar;
	}
	
	private JMenu createExitBar() {
		exitBar = new JMenu ("Exit Bar");
		menuOption = exitBar.add(accExit);
		exitBar.add(menuOption);
		return exitBar;
	}

	@Override
	public void run() {
		this.createActions();
		this.setJMenuBar(createBar());
		this.setLocation(200, 200);
		this.setSize(640, 480);
		this.setIconImage(new ImageIcon("logo.jpg").getImage());
		this.getContentPane().add(createWindowPane());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


}