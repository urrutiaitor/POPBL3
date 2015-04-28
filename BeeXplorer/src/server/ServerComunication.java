package server;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ServerComunication extends JDialog implements Runnable{
	String sig;
	
	private Container createPanel() {
		JPanel panel=new JPanel(new GridLayout(1,2));
		JButton b1=new JButton("Download a file");
		b1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FTPactions Ftp=new FTPactions();
				try {
					if(Ftp.bajarArchivo("DirectFicheros")) Ftp.leerFichero("DirectFicheros", 1);
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		JButton b2=new JButton("Upload a file");
		b2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FTPactions Ftp=new FTPactions();
				try {
					if(Ftp.bajarArchivo("DirectFicheros")) Ftp.leerFichero("DirectFicherosU", 0);
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		panel.add(b1);
		panel.add(b2);
		return panel;
	}
	
	@Override
	public void run() {
		this.setSize(300,100);
		this.setLocation(300,100);
		this.setContentPane(createPanel());
		this.setVisible(true);
	}


}
