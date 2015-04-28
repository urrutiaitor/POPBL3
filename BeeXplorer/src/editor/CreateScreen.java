package editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import robot.Point;

public class CreateScreen extends JPanel implements MouseListener{
	
	private final int undefined = 0;
	private final int unexisting = 1;
	private final int obstacle = 2;
	private final int free = 3;
	
	int startPosX, startPosY;
	int endPosX, endPosY;
	
	int row;
	int col;
	
	ArrayList<ArrayList<Point>> map;
	
	public CreateScreen(ArrayList<ArrayList<Point>> map, int row, int col) {
		super();
		this.map = map;
		
		for(int x = 0; x < col; x++){
			map.add(x, new ArrayList<Point>());
			for(int y = 0; y < row; y++){
				map.get(x).add(y, new Point(free));
			}
		}
		this.col = col;
		this.row = row;
		this.setPreferredSize(new Dimension(col*10 + 1, row*10 + 1));
		this.setVisible(true);
		this.addMouseListener(this);
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	

	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getCol() * 10, this.getRow() * 10);
		
		for(int x = 0; x <= col; x++){
			g.setColor(Color.BLACK);
			g.fillRect(x*10, 0, 1, this.getRow() * 10);
		}
		
		for(int y = 0; y <= row; y++){
			g.setColor(Color.BLACK);
			g.fillRect(0, (y*10) - 1, this.getCol() * 10, 1);
		}
		
		for(int i = 0; i < col; i++){
			for(int z = 0; z < row; z++){
				if(map.get(i).get(z).getValue() == obstacle){
					g.setColor(Color.DARK_GRAY);
					g.fillRect(i*10, z*10, 10, 10);
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startPosX = (int) (e.getPoint().getX()/10);
		startPosY = (int) (e.getPoint().getY()/10);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		endPosX = (int) (e.getPoint().getX()/10);
		endPosY = (int) (e.getPoint().getY()/10);
		
		for(int i = startPosX; i <= endPosX; i++){
			for(int z = startPosY; z <= endPosY; z++){
				if(map.get(i).get(z).getValue() != obstacle) map.get(i).get(z).setValue(obstacle);
				else map.get(i).get(z).setValue(free);
			}
		}
		
		this.repaint();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
