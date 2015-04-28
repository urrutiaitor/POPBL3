package editor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import robot.Point;

public class CreateMap extends JDialog implements ActionListener, Runnable{

	private final int undefined = 0;
	private final int unexisting = 1;
	private final int obstacle = 2;
	private final int free = 3;
	
	ArrayList<ArrayList<Point>> map;
	JPanel screen;
	JButton save;
	
	JButton northButton, westButton, southButton, eastButton;
	
	File file;
	
	public CreateMap(){
		map = new ArrayList<ArrayList<Point>>();
	}
	
	private Container createPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(screenPanel(), BorderLayout.CENTER);
		panel.add(savePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private Component screenPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(screen = new CreateScreen(map, 50, 50), BorderLayout.CENTER);
		panel.add(northButton = new JButton("North"), BorderLayout.NORTH);
		northButton.addActionListener(this);
		panel.add(westButton = new JButton("West"), BorderLayout.WEST);
		westButton.addActionListener(this);
		panel.add(southButton = new JButton("South"), BorderLayout.SOUTH);
		southButton.addActionListener(this);
		panel.add(eastButton = new JButton("East"), BorderLayout.EAST);
		eastButton.addActionListener(this);
		
		return panel;
	}
	
	private Component savePanel() {
		JPanel panel = new JPanel();
		save = new JButton("Save");
		save.addActionListener(this);
		panel.add(save);
		return panel;
	}
	
	private void saveMap() throws IOException{
		FileWriter writer = null;
		String address = JOptionPane.showInputDialog(this, "Folder local address:", JOptionPane.QUESTION_MESSAGE);
	    writer = new FileWriter("Maps/" + address + ".txt");

		for(int y = 0; y < map.get(0).size(); y++){
			for(int x = 0; x < map.size(); x++){
				if(map.get(x).get(y).getValue() == undefined) writer.write("u");
				if(map.get(x).get(y).getValue() == unexisting) writer.write("x");
				if(map.get(x).get(y).getValue() == free) writer.write("f");
				if(map.get(x).get(y).getValue() == obstacle) writer.write("o");
			}
			writer.write("\n");
		}
		writer.close();
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./Maps/directFicherosU.txt", true), "UTF8")); 
		
		out.newLine();
		out.write(address);
		
		out.close();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		
		if(button == save){
			try {
				saveMap();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this, "It has been an error during the saving proccess", "Saving failed", JOptionPane.WARNING_MESSAGE);
			}
			this.dispose();
		}
	}

	@Override
	public void run() {
		this.setLocation(300, 300);
		//this.setSize(1000, 1000);
		this.setContentPane(createPane());
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

}
