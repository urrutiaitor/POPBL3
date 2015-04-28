package main;

import graphicInterface.Window;

public class Main {

	public static void main(String[] args) {
		Window windowThread = new Window();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(windowThread).start();
		
	}

}
