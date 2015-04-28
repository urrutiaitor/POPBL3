package server;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class DownloadFile  {
	JDialog ventana;
	String nomFich;
	int numFich;
	JList<String> lista;
	ListSelectionModel model;
	//String dirIP="172.17.17.25";
	String nombres[];
	ArrayList<String> ficheros;

	public DownloadFile(String noms[]){
		ventana=new JDialog();
		nombres=noms;
		ventana.dispose();
		ventana.setSize(300,400);
		ventana.setLocation(300,100);
		ventana.setContentPane(crearPanelDialogo());
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	private Container crearPanelDialogo() {
		JPanel panel=new JPanel(new BorderLayout());
		
		panel.add(crearPanel1(),BorderLayout.CENTER);	
		panel.add(crearPanel2(),BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanel2() {
		JPanel panel2=new JPanel(new GridLayout(1,2,10,0));
		panel2.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		JButton b1=new JButton("Download Map");
		b1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FTPactions Ftp=new FTPactions();
				numFich=lista.getSelectedIndex();
				nomFich=nombres[numFich];
				Ftp.bajarArchivo(nomFich);
			ventana.dispose();
				
			}
			
		});
		JButton b2=new JButton("Cancel");
		b2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ventana.dispose();
			}
			
		});
		panel2.add(b1);
		panel2.add(b2);
		return panel2;
	}

	private Component crearPanel1() {
		JPanel panel1=new JPanel(new BorderLayout());
		//JScrollPane panel1 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel1.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		
		
		lista=new JList<String>(nombres);
		
		lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); 
		 
	
		
		/*JScrollPane barraDesplazamiento = new JScrollPane(lista); 
		barraDesplazamiento.setBounds(10,30,200,110); 
		lista.addListSelectionListener(this);*/
		
		panel1.add(lista);
		 
		return panel1;
	}


	
}
