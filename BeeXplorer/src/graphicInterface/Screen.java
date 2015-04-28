package graphicInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

import robot.Point;

public class Screen extends JPanel {
	
	ArrayList<ArrayList<Point>> map;
	
	private final int undefined = 0;
	private final int unexisting = 1;
	private final int obstacle = 2;
	private final int free = 3;
	
	public Screen (ArrayList<ArrayList<Point>> map){
		this.map = map;
		if(map == null){
			this.setPreferredSize(new Dimension(501, 501));
		}
		else{
			this.setPreferredSize(new Dimension(map.size()*10 + 1, map.get(0).size()*10 + 1));
		}
		repaint();
	}

	public void paint(Graphics g){
		super.paint(g);
		
		if(map != null){
			
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, map.size() * 10 + 1, map.get(0).size() * 10 + 1);
			
			for(int x = 0; x <= map.size(); x++){
				g.setColor(Color.BLACK);
				g.fillRect(x*10, 0, 1, map.get(0).size() * 10);
			}
			
			for(int y = 0; y <= map.get(0).size(); y++){
				g.setColor(Color.BLACK);
				g.fillRect(0, (y*10) - 1, map.size() * 10, 1);
			}
			
			System.out.println(map.size() + ", " + map.get(0).size());
		
			for(int y = 0; y < map.size(); y++){
				for(int x = 0; x < map.get(0).size(); x++){
					System.out.println("Printing arrayList: " + x + ", " + y);
					if(map.get(x).get(y).getValue() == obstacle){
						g.setColor(Color.DARK_GRAY);
						g.fillRect(x*10, y*10, 10, 10);
					}
				}
			}
		}
		else{
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 501, 501);
		}
	}
	
}
