package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import robot.Point;

public class ShowDialog extends JDialog implements ActionListener, Runnable{
	
	private final int undefined = 0;
	private final int unexisting = 1;
	private final int obstacle = 2;
	private final int free = 3;
	
	JButton deleteButton;
	JButton openButton;
	JFileChooser fc = new JFileChooser();
	String fileName;
	
	Screen screen;
	ArrayList<ArrayList<Point>> map = null;
	
	private Container createPane() {
		JSplitPane panel = new JSplitPane();
		panel.setDividerLocation(100);
		panel.setLeftComponent(createLeftSide());
		panel.setRightComponent(screen = new Screen(map));
		return panel;
	}

	private Component createLeftSide() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 20, 20));
		panel.add(createFileChooser());
		panel.add(new JPanel());
		panel.add(createSouthSide());
		return panel;
	}

	private Component createFileChooser() {
		JPanel panel = new JPanel();
		openButton = new JButton("Open");
		panel.add(openButton);
		openButton.addActionListener(this);
		return panel;
	}

	private Component createSouthSide() {
		JPanel panel = new JPanel(new BorderLayout());
		deleteButton = new JButton("Delete");
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(this);
		panel.add(deleteButton, BorderLayout.CENTER);
		return panel;
	}
	
	private boolean openFile(){
		int row = 0, col = 0;
		int character;
		
		JFileChooser j= new JFileChooser();
        j.showOpenDialog(j);
        fileName = j.getSelectedFile().getAbsolutePath();
		
        try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((character = br.read()) != -1){
				System.out.println(col + ", " + row + ": " + character);
				
				if('x' == character){
					if(row == 0) map.add(new ArrayList<Point>());
					map.get(col).add(new Point(unexisting));
					col++;
            	}
            	if('u' == character){
            		if(row == 0) map.add(new ArrayList<Point>());
            		map.get(col).add(new Point(undefined));
            		col++;
            	}
            	if('f' == character){
            		if(row == 0) map.add(new ArrayList<Point>());
            		map.get(col).add(new Point(free));
            		col++;
            	}
            	if('o' == character){
            		if(row == 0) map.add(new ArrayList<Point>());
            		map.get(col).add(new Point(obstacle));
            		col++;
            	}
            	if('\n' == character){
            		col = 0;
            		row++;
            	}
			}
			br.close();
		} catch (IOException e3) {
			JOptionPane.showMessageDialog(this, "It has been an error during the reading", "Reading failed", JOptionPane.WARNING_MESSAGE);

			return false;
		}
        return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Delete":
			File file = new File(fileName);
			file.delete();
			this.dispose();
			break;
		case "Open":
			map = new ArrayList<ArrayList<Point>>();
			
			if(openFile()){
				deleteButton.setEnabled(true);
				screen.map = map;
				screen.repaint();
				this.setVisible(true);
			}
			
			break;
		}
		
	}

	@Override
	public void run() {
		this.setLocation(300, 100);
		this.setContentPane(createPane());
		this.pack();
		this.setVisible(true);
		this.setModal(false); 
	}

}
