package robot;

import java.util.ArrayList;
import algorithms.Position;

public class Map implements Runnable{

	ArrayList<ArrayList<Point>> map;
	int width = 1, height = 1;
	Robot robot;
	private final double space = 1;
	
	private final int undefined = 0;
	private final int unexisting = 1;
	private final int obstacle = 2;
	private final int free = 3;
	
	private final int ROBOTWIDTH = 2;
	private final int ROBOTHEIGHT = 3;
	
	public Map (int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public Map (){
		
	}
	
	public Position expandMap (int deltaWidth, int deltaHeight, Position robotPosition) {
		if(!expandMapWidth(deltaWidth)) System.out.println("Error expanding map width");
		if(!expandMapHeight(deltaHeight)) System.out.println("Error expanding map height");
		robotPosition.incrementPosX(-deltaWidth);
		robotPosition.incrementPosY(-deltaHeight);
		return robotPosition;
	}
	
	public boolean expandMapWidth (int deltaWidth) {
		if(deltaWidth > 0) {
			if(expandMapEast(deltaWidth)) return true;
		}
		if(deltaWidth < 0) {
			if(expandMapWest(Math.abs(deltaWidth))) return true;
		}
		return false;
	}
	
	public boolean expandMapHeight (int deltaHeight) {
		if(deltaHeight > 0) {
			if(expandMapEast(deltaHeight)) return true;
		}
		if(deltaHeight < 0) {
			if(expandMapWest(Math.abs(deltaHeight))) return true;
		}
		return false;
	}
	
	public boolean expandMapEast (int value) {
		int width = map.size();
		map.add(new ArrayList<Point>());
		for(int i = 0; i < map.get(0).size(); i++){
			map.get(width).add(new Point(0));
		}
		return true;
	}
	
	public boolean expandMapWest (int value) {
		map.add(0, new ArrayList<Point>());
		for(int i = 0; i < map.get(1).size(); i++){
			map.get(0).add(new Point(0));
		}
		return true;
	}
	
	public boolean expandMapNorth (int value) {
		int width = map.size();
		for(int i = 0; i < width; i++){
			for(int z = 0; z < value; z++){
				map.get(i).add(0, new Point(0));
			}
		}
		return true;
	}
	
	public boolean expandMapSouth (int value) {
		int width = map.size();
		for(int i = 0; i < width; i++){
			for(int z = 0; z < value; z++){
				map.get(i).add(new Point(0));
			}
		}
		return true;
	}

	public void exploreMap(){
		Position position;
		while((position = completed()) == null){
	
			if(perimeterDone()){
				goToPosition(position);
			}
			else{
				makePerimeter();
			}
			explorePoint(position);
		}
		return;
	}

	private void makePerimeter() {
		while(!findWall()){
			System.out.println("Could not find the wall");
		}
		Position robotFountWallPosition = robot.position;
		while(continueRightWall() != robotFountWallPosition){
			System.out.println("Making perimeter");
		}
		
		
	}
	
	private Position continueRightWall(){
		if(robot.sensors.getNorthRightSensor() < space){
			robot.rotateLeft();
			paintMapPoints();
		}
		if(robot.sensors.getEastFrontSensor() <= space){
			robot.goStraight();
			paintMapPoints();
		}
		if(robot.sensors.getEastFrontSensor() > space){
			while(robot.sensors.getEastBackSensor() <= space){
				robot.goStraight();
				paintMapPoints();
			}
		}

		return robot.position;
	}
	
	private boolean findWall(){
		while((robot.sensors.getNorthLeftSensor() > space)&&(robot.sensors.getNorthCenterSensor() > space)&&(robot.sensors.getNorthRightSensor() > space)){
			robot.goStraight();
			paintMapPoints();
		}
		if(robot.sensors.getNorthCenterSensor() < space){
			robot.rotateLeft();
			paintMapPoints();
			while(robot.sensors.getEastFrontSensor() > space){ //while ordez IF erabili beharko zen
				robot.goBack();
				paintMapPoints();
			}
		}
		if(robot.sensors.getNorthLeftSensor() < space){
			robot.rotateLeft();
			paintMapPoints();
			return true;
		}
		if(robot.sensors.getNorthRightSensor() < space){
			robot.rotateLeft();
			paintMapPoints();
			while(robot.sensors.getEastFrontSensor() > space){ //while ordez IF erabili beharko zen
				robot.goBack();
				paintMapPoints();
			}
			return true;
		}
		
		return false;
	}

	private boolean perimeterDone() {
		Position firstPosition = null;
		Position actualPosition = null;
		String direction = "west";
		int counter = 0;
		
		for(int y = 0; y < map.get(0).size(); y++){
			for(int x = 0; x < map.size(); x++){
				if(getPointValue(x, y) == free){
					firstPosition = new Position(x, y);
					actualPosition = new Position(x, y);
				}
			}
		}
		
		do{
			while(direction.compareTo("west") == 0){
				if(getPointValue(actualPosition.getPosX(), actualPosition.getPosY() - 1) == free){
					actualPosition.setPosY(actualPosition.getPosY() - 1);
					direction = "north";
				}
				else{
					if(getPointValue(actualPosition.getPosX() - 1, actualPosition.getPosY()) == free){
						actualPosition.setPosX(actualPosition.getPosX() - 1);
						direction = "west";
					}
					else{
						if(getPointValue(actualPosition.getPosX(), actualPosition.getPosY() + 1) == free){
							actualPosition.setPosY(actualPosition.getPosY() + 1);
							direction = "south";
						}
						else{
							if(getPointValue(actualPosition.getPosX() + 1, actualPosition.getPosY()) == free){
								actualPosition.setPosX(actualPosition.getPosX() + 1);
								direction = "east";
							}
						}
					}
				}
			}
			
			while(direction.compareTo("south") == 0){
				if(getPointValue(actualPosition.getPosX() - 1, actualPosition.getPosY()) == free){
					actualPosition.setPosX(actualPosition.getPosX() - 1);
					direction = "west";
				}
				else{
					if(getPointValue(actualPosition.getPosX(), actualPosition.getPosY() + 1) == free){
						actualPosition.setPosY(actualPosition.getPosY() + 1);
						direction = "south";
					}
					else{
						if(getPointValue(actualPosition.getPosX() + 1, actualPosition.getPosY()) == free){
							actualPosition.setPosX(actualPosition.getPosX() + 1);
							direction = "east";
						}
						else{
							if(getPointValue(actualPosition.getPosX(), actualPosition.getPosY() - 1) == free){
								actualPosition.setPosY(actualPosition.getPosY() - 1);
								direction = "north";
							}
						}	
					}
				}
			}
			
			while(direction.compareTo("east") == 0){
				
				if(getPointValue(actualPosition.getPosX(), actualPosition.getPosY() + 1) == free){
					actualPosition.setPosY(actualPosition.getPosY() + 1);
					direction = "south";
				}
				else{
					if(getPointValue(actualPosition.getPosX() + 1, actualPosition.getPosY()) == free){
						actualPosition.setPosX(actualPosition.getPosX() + 1);
						direction = "east";
					}
					else{
						if(getPointValue(actualPosition.getPosX(), actualPosition.getPosY() - 1) == free){
							actualPosition.setPosY(actualPosition.getPosY() - 1);
							direction = "north";
						}
						else{
							if(getPointValue(actualPosition.getPosX() - 1, actualPosition.getPosY()) == free){
								actualPosition.setPosX(actualPosition.getPosX() - 1);
								direction = "west";
							}
						}
					}
				}
			}
			
			while(direction.compareTo("north") == 0){
				
				if(getPointValue(actualPosition.getPosX() + 1, actualPosition.getPosY()) == free){
					actualPosition.setPosX(actualPosition.getPosX() + 1);
					direction = "east";
				}
				else{
					if(getPointValue(actualPosition.getPosX(), actualPosition.getPosY() - 1) == free){
						actualPosition.setPosY(actualPosition.getPosY() - 1);
						direction = "north";
					}
					else{
						if(getPointValue(actualPosition.getPosX() - 1, actualPosition.getPosY()) == free){
							actualPosition.setPosX(actualPosition.getPosX() - 1);
							direction = "west";
						}
						else{
							if(getPointValue(actualPosition.getPosX(), actualPosition.getPosY() + 1) == free){
								actualPosition.setPosY(actualPosition.getPosY() + 1);
								direction = "south";
							}
						}
					}
				}
			}
		
			if(counter > 50000) return false;
			
			counter++;
			
		}while(actualPosition != firstPosition);
		return true;
	}
	
	private int getPointValue(int x, int y){
		return map.get(x).get(y).getValue();
	}

	private Position completed() {
		int firstX = -1, lastX = -1, firstY = -1, lastY = -1;
		
		for(int y = 0; y < map.get(0).size(); y++){
			for(int x = 0; x < map.size(); x++){
				if(getPointValue(x, y) == undefined){
					for(int auxX = 0; auxX < map.size(); x++){
						if(getPointValue(auxX, y) == obstacle){
							if(firstX == -1) firstX = auxX;
							else{
								lastX = auxX;
								if((firstX < x)&&(x < lastX)) break;
								else{
									firstX = lastX;
									lastX = -1;
								}
							}
						}
					}
					if(lastX != -1){
						for(int auxY = firstX; auxY < lastX; x++){
							if(getPointValue(x, auxY) == obstacle){
								if(firstY == -1) firstY = auxY;
								else{
									lastY = auxY;
									if((firstY < y)&&(y < lastY)) return new Position(x, y);
									else{
										firstY = lastY;
										lastY = -1;
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	//MAKE IT!!
	private void explorePoint(Position position){
		
	}
	
	@Override
	public void run() {
		map = new ArrayList<ArrayList<Point>>();
		robot = new Robot(new Position(0, 0), "N", new Sensors(0, 0, 0, 0, 0, 0));
		for(int x = 0; x < width; x++){
			map.add(new ArrayList<Point>());
			for(int y = 0; y < height; y++){
				map.get(0).add(new Point(0));
			}
		}
		
	}
	
	private void goToPosition(Position position) {
		// TODO Auto-generated method stub
		
	}
	
	private String recursivePoint(Position goalPosition, Position actualPosition, int counter){
		if(map.get(goalPosition.getPosX()).get(goalPosition.getPosY()).getValue() != undefined) return "";
		
		for(int x = actualPosition.getPosX() - 5; x < actualPosition.getPosX() + 5; x++){
			for(int y = actualPosition.getPosY() - 5; y < actualPosition.getPosY() + 5; y++){
				if(map.get(x).get(y) != null){
					if(map.get(x).get(y).getValue() == obstacle) return "invalid";
				}
			}
		}
		String movements;
		movements = recursivePoint(goalPosition, new Position(actualPosition.getPosX(), actualPosition.getPosY() - 1), counter + 1);
		if(movements.equals("invalid")){
			return movements;
		}
		else{
			return "n" + movements;
		}
		
	}
	
	private void paintMapPoints(){
		if(robot.direction.equals("north")){
			if(robot.sensors.getWestFrontSensor() <= space + 1){
				while(map.get(robot.position.getPosX() - robot.sensors.getWestFrontSensor()) == null) expandMapWest(1);
				map.get(robot.position.getPosX() - robot.sensors.getWestFrontSensor()).get(robot.position.getPosY()).setValue(obstacle);
			}
			if(robot.sensors.getNorthLeftSensor() <= space + 1){
				while(map.get(robot.position.getPosX()).get(robot.position.getPosY() - robot.sensors.getNorthLeftSensor()) == null) expandMapNorth(1);
				map.get(robot.position.getPosX()).get(robot.position.getPosY() - robot.sensors.getNorthLeftSensor()).setValue(obstacle);
			}
			if(robot.sensors.getNorthCenterSensor() <= space + 1){
				while(map.get(robot.position.getPosX() + 1).get(robot.position.getPosY() - robot.sensors.getNorthCenterSensor()) == null) expandMapNorth(1);
				map.get(robot.position.getPosX() + 1).get(robot.position.getPosY() - robot.sensors.getNorthCenterSensor()).setValue(obstacle);
			}
			if(robot.sensors.getNorthRightSensor() <= space + 1){
				while(map.get(robot.position.getPosX() + 2).get(robot.position.getPosY() - robot.sensors.getNorthRightSensor()) == null) expandMapNorth(1);
				map.get(robot.position.getPosX() + 2).get(robot.position.getPosY() - robot.sensors.getNorthRightSensor()).setValue(obstacle);
			}
			if(robot.sensors.getEastFrontSensor() <= space + 1){
				while(map.get(robot.position.getPosX() + 2 + robot.sensors.getEastFrontSensor()) == null) expandMapEast(1);
				map.get(robot.position.getPosX() + 2 + robot.sensors.getEastFrontSensor()).get(robot.position.getPosY()).setValue(obstacle);
			}
			if(robot.sensors.getEastBackSensor() <= space + 1){
				while(map.get(robot.position.getPosX() + 2 + robot.sensors.getEastBackSensor()) == null) expandMapEast(1);
				while(map.get(robot.position.getPosX() + 2 + robot.sensors.getEastBackSensor()).get(robot.position.getPosY() + ROBOTHEIGHT) == null) expandMapSouth(1);
				map.get(robot.position.getPosX() + 2 + robot.sensors.getWestFrontSensor()).get(robot.position.getPosY()).setValue(obstacle);
			}
		}
		if(robot.direction.equals("west")){
			if(robot.sensors.getWestFrontSensor() <= space + 1){
				while(map.get(robot.position.getPosX()).get(robot.position.getPosY() + robot.sensors.getWestFrontSensor()) == null) expandMapSouth(1);
				map.get(robot.position.getPosX()).get(robot.position.getPosY() + robot.sensors.getWestFrontSensor()).setValue(obstacle);
			}
			if(robot.sensors.getNorthLeftSensor() <= space + 1){
				while(map.get(robot.position.getPosX() - robot.sensors.getNorthLeftSensor()) == null) expandMapWest(1);
				map.get(robot.position.getPosX() - robot.sensors.getNorthLeftSensor()).get(robot.position.getPosY()).setValue(obstacle);
			}
			if(robot.sensors.getNorthCenterSensor() <= space + 1){
				while(map.get(robot.position.getPosX() - robot.sensors.getNorthCenterSensor()) == null) expandMapWest(1);
				while(map.get(robot.position.getPosX() - robot.sensors.getNorthCenterSensor()).get(robot.position.getPosY() - 1) == null) expandMapNorth(1);
				map.get(robot.position.getPosX() - robot.sensors.getNorthCenterSensor()).get(robot.position.getPosY() - 1).setValue(obstacle);
			}
			if(robot.sensors.getNorthRightSensor() <= space + 1){
				while(map.get(robot.position.getPosX() - robot.sensors.getNorthRightSensor()) == null) expandMapWest(1);
				while(map.get(robot.position.getPosX() - robot.sensors.getNorthCenterSensor()).get(robot.position.getPosY() - 2) == null) expandMapNorth(1);
				map.get(robot.position.getPosX() - robot.sensors.getNorthRightSensor()).get(robot.position.getPosY() - 2).setValue(obstacle);
			}
			if(robot.sensors.getEastFrontSensor() <= space + 1){
				while(map.get(robot.position.getPosX()).get(robot.position.getPosY() - 2 - robot.sensors.getEastFrontSensor()) == null) expandMapNorth(1);
				map.get(robot.position.getPosX()).get(robot.position.getPosY() - 2 - robot.sensors.getEastFrontSensor()).setValue(obstacle);
			}
			if(robot.sensors.getEastBackSensor() <= space + 1){
				while(map.get(robot.position.getPosX() + ROBOTHEIGHT) == null) expandMapEast(1);
				while(map.get(robot.position.getPosX() + ROBOTHEIGHT).get(robot.position.getPosY() - 2 - robot.sensors.getEastBackSensor()) == null) expandMapNorth(1);
				map.get(robot.position.getPosX() + ROBOTHEIGHT).get(robot.position.getPosY() - 2 - robot.sensors.getEastBackSensor()).setValue(obstacle);
			}
		}
		if(robot.direction.equals("south")){
			if(robot.sensors.getWestFrontSensor() <= space + 1){
				while(map.get(robot.position.getPosX() + robot.sensors.getWestFrontSensor()) == null) expandMapEast(1);
				map.get(robot.position.getPosX() + robot.sensors.getWestFrontSensor()).get(robot.position.getPosY()).setValue(obstacle);
			}
			if(robot.sensors.getNorthLeftSensor() <= space + 1){
				while(map.get(robot.position.getPosX()).get(robot.position.getPosY() + robot.sensors.getNorthLeftSensor()) == null) expandMapSouth(1);
				map.get(robot.position.getPosX()).get(robot.position.getPosY() + robot.sensors.getNorthLeftSensor()).setValue(obstacle);
			}
			if(robot.sensors.getNorthCenterSensor() <= space + 1){
				while(map.get(robot.position.getPosX() - 1).get(robot.position.getPosY() + robot.sensors.getNorthCenterSensor()) == null) expandMapSouth(1);
				map.get(robot.position.getPosX() - 1).get(robot.position.getPosY() + robot.sensors.getNorthCenterSensor()).setValue(obstacle);
			}
			if(robot.sensors.getNorthRightSensor() <= space + 1){
				while(map.get(robot.position.getPosX() - 2).get(robot.position.getPosY() + robot.sensors.getNorthRightSensor()) == null) expandMapSouth(1);
				map.get(robot.position.getPosX() - 2).get(robot.position.getPosY() + robot.sensors.getNorthRightSensor()).setValue(obstacle);
			}
			if(robot.sensors.getEastFrontSensor() <= space + 1){
				while(map.get(robot.position.getPosX() - 2 - robot.sensors.getEastFrontSensor()) == null) expandMapWest(1);
				map.get(robot.position.getPosX() - 2 - robot.sensors.getWestFrontSensor()).get(robot.position.getPosY()).setValue(obstacle);
			}
			if(robot.sensors.getEastBackSensor() <= space + 1){
				while(map.get(robot.position.getPosX() - 2 - robot.sensors.getEastBackSensor()) == null) expandMapWest(1);
				while(map.get(robot.position.getPosX() - 2 - robot.sensors.getEastBackSensor()).get(robot.position.getPosY() - ROBOTHEIGHT) == null) expandMapNorth(1);
				map.get(robot.position.getPosX() - 2 - robot.sensors.getWestFrontSensor()).get(robot.position.getPosY() - ROBOTHEIGHT).setValue(obstacle);
			}
		}
		if(robot.direction.equals("east")){
			if(robot.sensors.getWestFrontSensor() <= space + 1){
				while(map.get(robot.position.getPosX()).get(robot.position.getPosY() - robot.sensors.getWestFrontSensor()) == null) expandMapNorth(1);
				map.get(robot.position.getPosX()).get(robot.position.getPosY() - robot.sensors.getWestFrontSensor()).setValue(obstacle);
			}
			if(robot.sensors.getNorthLeftSensor() <= space + 1){
				while(map.get(robot.position.getPosX() + robot.sensors.getNorthLeftSensor()) == null) expandMapEast(1);
				map.get(robot.position.getPosX() + robot.sensors.getNorthLeftSensor()).get(robot.position.getPosY()).setValue(obstacle);
			}
			if(robot.sensors.getNorthCenterSensor() <= space + 1){
				while(map.get(robot.position.getPosX() + robot.sensors.getNorthCenterSensor()) == null) expandMapEast(1);
				while(map.get(robot.position.getPosX() + robot.sensors.getNorthCenterSensor()).get(robot.position.getPosY() + 1) == null) expandMapNorth(1);
				map.get(robot.position.getPosX() + robot.sensors.getNorthCenterSensor()).get(robot.position.getPosY() + 1).setValue(obstacle);
			}
			if(robot.sensors.getNorthRightSensor() <= space + 1){
				while(map.get(robot.position.getPosX() + robot.sensors.getNorthRightSensor()) == null) expandMapEast(1);
				while(map.get(robot.position.getPosX() + robot.sensors.getNorthCenterSensor()).get(robot.position.getPosY() + 2) == null) expandMapNorth(1);
				map.get(robot.position.getPosX() + robot.sensors.getNorthRightSensor()).get(robot.position.getPosY() + 2).setValue(obstacle);
			}
			if(robot.sensors.getEastFrontSensor() <= space + 1){
				while(map.get(robot.position.getPosX()).get(robot.position.getPosY() + 2 + robot.sensors.getEastFrontSensor()) == null) expandMapSouth(1);
				map.get(robot.position.getPosX()).get(robot.position.getPosY() + 2 + robot.sensors.getEastFrontSensor()).setValue(obstacle);
			}
			if(robot.sensors.getEastBackSensor() <= space + 1){
				while(map.get(robot.position.getPosX() - ROBOTHEIGHT) == null) expandMapWest(1);
				while(map.get(robot.position.getPosX() - ROBOTHEIGHT).get(robot.position.getPosY() + 2 + robot.sensors.getEastBackSensor()) == null) expandMapSouth(1);
				map.get(robot.position.getPosX() + ROBOTHEIGHT).get(robot.position.getPosY() + 2 + robot.sensors.getEastBackSensor()).setValue(obstacle);
			}
		}
	}
	
}
