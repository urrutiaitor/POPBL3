package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Manual extends JDialog implements ActionListener, KeyListener, Runnable{
	
	SerialPort port;
	JButton exitButton;
	JPanel labelPanel;
	String text1, text2, text3;
	
	private Container content() {
		text1 = " · You have started the manual control";
		text2 = " · Use the KEYS or WASD to control the robot movements";
		text3 = " · Push Exit button to EXIT the manual control";
		JPanel panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 300));
		panel.addKeyListener(this);
		panel.setFocusable(true);
		panel.add(labelPanel = new JPanel(new GridLayout(3, 1, 5, 5)), BorderLayout.CENTER);
		labelPanel.add(new JLabel(text1));
		labelPanel.add(new JLabel(text2));
		labelPanel.add(new JLabel(text3));
		panel.add(exitButton = new JButton("Exit"), BorderLayout.SOUTH);
		exitButton.addActionListener(this);
		return panel;
	}
	
	private void stop(){
		try {
		 	port.setParams(9600, 8, 1, 0);
		 	port.writeInt(0);
		} catch (SerialPortException e) {
			JOptionPane.showMessageDialog(this, "Connect the serial line", "Connection failed", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void forward(){
		try {
		 	port.setParams(9600, 8, 1, 0);
		 	port.writeInt(1);
		} catch (SerialPortException e) {
			JOptionPane.showMessageDialog(this, "Connect the serial line", "Connection failed", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void right(){
		try {
		 	port.setParams(9600, 8, 1, 0);
		 	port.writeInt(2);
		} catch (SerialPortException e) {
			JOptionPane.showMessageDialog(this, "Connect the serial line", "Connection failed", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void left(){
		try {
		 	port.setParams(9600, 8, 1, 0);
		 	port.writeInt(3);
		} catch (SerialPortException e) {
			JOptionPane.showMessageDialog(this, "Connect the serial line", "Connection failed", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void back(){
		try {
		 	port.setParams(9600, 8, 1, 0);
		 	port.writeInt(4);
		} catch (SerialPortException e) {
			JOptionPane.showMessageDialog(this, "Connect the serial line", "Connection failed", JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void keyPressed(KeyEvent k) {
		switch(k.getKeyCode()){
		case 87:
		case 38: forward();		break;
		case 65:
		case 37: left();		break;
		case 83:
		case 40: back();		break;
		case 68:
		case 39: right();	break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		stop();
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void run() {
		try {
			port = new SerialPort("/dev/tty.usbserial-FTFOM49A");
			port.openPort();
		} catch (SerialPortException e) {
			JOptionPane.showMessageDialog(this, "Connect the serial line", "Connection failed", JOptionPane.WARNING_MESSAGE);
		}
	
		this.setLocation(100,100);
		this.setContentPane(content());
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitButton) this.dispose();
		
	}
}
