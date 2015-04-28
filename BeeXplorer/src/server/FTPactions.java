package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


import java.util.Observable;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


public class FTPactions{
	String dirIP="172.17.17.25";
	String nomFS;
	JOptionPane op;
	public boolean subirArchivo(String nomfichero) {
		if(nomfichero!="DirectFicheros"){
		try {
			escribirFichero("DirectFicheros",nomfichero);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		}
	    try {
	 
	        FTPClient ftpClient = new FTPClient();
	        ftpClient.connect(InetAddress.getByName(dirIP));
	        
	        ftpClient.login("usuario1", "");
	 
	        ftpClient.changeWorkingDirectory("/Nueva carpeta");
	        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	        BufferedInputStream buffIn=null;
	        buffIn=new BufferedInputStream(new FileInputStream("./Maps/"+nomfichero+".txt"));
	        ftpClient.enterLocalPassiveMode();
	        ftpClient.storeFile(nomfichero+".txt", buffIn);
	        buffIn.close();
	        ftpClient.logout();
	        ftpClient.disconnect();
	        //System.out.println("Archivo subido!!!");
	        if(nomfichero!="DirectFicheros"){
	        	JOptionPane.showMessageDialog(null, "The file has been uploaded succesfully", "", JOptionPane.PLAIN_MESSAGE);
	        	return true;
	        }
	       
	    } catch (Exception e){
	    	//System.out.println("Consola, Ups!");
	    	if(nomfichero!="DirectFicheros"){
	    		JOptionPane.showMessageDialog(null, "The file could not been uploaded succesfully", "Warning", JOptionPane.ERROR_MESSAGE);
	    		return false;
	    	}
	    }
	    return true;
	}

	public String getNomFS() {
		return nomFS;
	}

	public boolean bajarArchivo(String nomfichero) {
		
		
		if(nomfichero!="DirectFicheros"&&nomfichero!="DirectFicherosU"){
			try {
				escribirFichero("DirectFicherosU",nomfichero);
			} catch (IOException e) {
				return false;
			}
		}
	 
	    FTPClient ftpClient = new FTPClient();
        try {
			ftpClient.connect(InetAddress.getByName(dirIP));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			ftpClient.login("usuario1", "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        try {
			ftpClient.changeWorkingDirectory("/Nueva carpeta");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BufferedOutputStream buffOut=null;
        try {
			buffOut=new BufferedOutputStream(new FileOutputStream("./Maps/"+nomfichero+".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ftpClient.enterLocalPassiveMode();
        try {
			ftpClient.retrieveFile("./"+nomfichero+".txt", buffOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //ftpClient.storeFile("ficheroprueba.txt", buffOut);
        try {
			buffOut.close();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
        try {
        ftpClient.logout();
		} catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			ftpClient.disconnect();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
        //System.out.println("Archivo descargado!!!");
	    if(nomfichero!="DirectFicheros"){
	        JOptionPane.showMessageDialog(null, "The file has been downloaded succesfully", "", JOptionPane.PLAIN_MESSAGE);
	        return true;
	    }
	   	if(nomfichero!="DirectFicheros"&&nomfichero!="DirectFicherosU"){
	    	JOptionPane.showMessageDialog(null, "The file could not been downloaded succesfully", "Warning", JOptionPane.ERROR_MESSAGE);
	    	return false;
	   	}else{
	   		System.out.println(nomfichero);
			//JOptionPane.showMessageDialog(null, "It is not possible to accede the server", "Warning", JOptionPane.ERROR_MESSAGE);
			return true;
	   	}
	    
	 
	}
	
	public void escribirFichero(String nom,String dat) throws IOException{
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./Maps/"+nom+".txt", true), "UTF8")); 
			
			out.newLine();
			out.write(dat);
			
			out.close();
			if(nom=="DirectFicheros"){
			subirArchivo("DirectFicheros");
			}
			
	}
	public void leerFichero(String nom,int i) throws IOException{
		
		int x=0;
		String nombres[]=new String[100];
		
		BufferedReader in = new BufferedReader(new FileReader("./Maps/"+nom+".txt"));
		
       String linea;
       
       while((linea=in.readLine())!=null){
       	nombres[x]=linea;
       	x++;
       	//System.out.println(linea);
       
       }
       in.close();
       if(i==1){
    	   DownloadFile EFD=new DownloadFile(nombres);
       }
       else{
    	   UploadFile EFS=new UploadFile(nombres);
       }
       
    		   
	}
}
