//THIS CLASS IS COMPLETED BUT IS NOT USED IN ANY PLACE. MUST CALLED TO IT FROM MAP OR ROBOT

package robot;

public class ObstacleDetector {
	
	private final double minimum = 3;
	private final double robotWidth = 2;
	private final double robotHeight = 3;
	
	public void detectAll(Map map){
		resizeMap(map);
		if(map.robot.direction.equals("N")) detectNorth(map);
		if(map.robot.direction.equals("S")) detectSouth(map);
		if(map.robot.direction.equals("E")) detectEast(map);
		if(map.robot.direction.equals("W")) detectWest(map);
	}

	private void resizeMap(Map map) {
		while(map.map.get(0).get((int) (map.robot.position.getPosY() - robotHeight - minimum)) == null){
			map.expandMapNorth(1);
		}
		while(map.map.get((int) (map.robot.position.getPosX() - robotHeight - minimum)) == null){
			map.expandMapWest(1);
		}
		while(map.map.get(0).get((int) (map.robot.position.getPosY() + robotHeight + minimum)) == null){
			map.expandMapSouth(1);
		}
		while(map.map.get((int) (map.robot.position.getPosX() + robotWidth + minimum)) == null){
			map.expandMapEast(1);
		}
		
	}
	
	private void detectNorth(Map map) {
		double distance = 0;
		
		distance = map.robot.sensors.getNorthLeftSensor();
		if(distance > minimum){
			map.map.get(map.robot.position.getPosX()).get((int) (map.robot.position.getPosY() - distance)).setValue(2);
		}
		
		distance = map.robot.sensors.getNorthCenterSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() + (robotWidth/2))).get((int) (map.robot.position.getPosY() - distance)).setValue(2);
		}
		
		distance = map.robot.sensors.getNorthRightSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() + robotWidth)).get((int) (map.robot.position.getPosY() - distance)).setValue(2);
		}
		
		distance = map.robot.sensors.getEastFrontSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() + robotWidth + distance)).get((int) (map.robot.position.getPosY())).setValue(2);
		}
		
		distance = map.robot.sensors.getEastBackSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() + robotWidth + distance)).get((int) (map.robot.position.getPosY() + robotHeight)).setValue(2);
		}
		
		distance = map.robot.sensors.getWestFrontSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() - distance)).get((int) (map.robot.position.getPosY())).setValue(2);
		}
		
	}

	private void detectSouth(Map map) {
		double distance = 0;
		
		distance = map.robot.sensors.getNorthLeftSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() + robotWidth)).get((int) (map.robot.position.getPosY() + robotHeight + distance)).setValue(2);
		}
		
		distance = map.robot.sensors.getNorthCenterSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() + (robotWidth/2))).get((int) (map.robot.position.getPosY() + robotHeight + distance)).setValue(2);
		}
		
		distance = map.robot.sensors.getNorthRightSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX())).get((int) (map.robot.position.getPosY() + robotHeight + distance)).setValue(2);
		}
		
		distance = map.robot.sensors.getEastFrontSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() - distance)).get((int) (map.robot.position.getPosY() + robotHeight)).setValue(2);
		}
		
		distance = map.robot.sensors.getEastBackSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() - distance)).get((int) (map.robot.position.getPosY())).setValue(2);
		}
		
		distance = map.robot.sensors.getWestFrontSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() + robotWidth + distance)).get((int) (map.robot.position.getPosY() + robotHeight)).setValue(2);
		}
		
	}

	private void detectEast(Map map) {
		double distance = 0;
		
		distance = map.robot.sensors.getNorthLeftSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() + robotHeight + distance)).get((int) (map.robot.position.getPosY())).setValue(2);
		}
		
		distance = map.robot.sensors.getNorthCenterSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() + robotHeight + distance)).get((int) (map.robot.position.getPosY() + (robotWidth/2))).setValue(2);
		}
		
		distance = map.robot.sensors.getNorthRightSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() + robotHeight + distance)).get((int) (map.robot.position.getPosY() + robotWidth)).setValue(2);
		}
		
		distance = map.robot.sensors.getEastFrontSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() + robotHeight)).get((int) (map.robot.position.getPosY() + robotWidth + distance)).setValue(2);
		}
		
		distance = map.robot.sensors.getEastBackSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX())).get((int) (map.robot.position.getPosY() + robotWidth + distance)).setValue(2);
		}
		
		distance = map.robot.sensors.getWestFrontSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() + robotHeight)).get((int) (map.robot.position.getPosY() - distance)).setValue(2);
		}
		
	}

	private void detectWest(Map map) {
		double distance = 0;
		
		distance = map.robot.sensors.getNorthLeftSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() - distance)).get((int) (map.robot.position.getPosY() + robotWidth)).setValue(2);
		}
		
		distance = map.robot.sensors.getNorthCenterSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() - distance)).get((int) (map.robot.position.getPosY() + (robotWidth/2))).setValue(2);
		}
		
		distance = map.robot.sensors.getNorthRightSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() - distance)).get((int) (map.robot.position.getPosY())).setValue(2);
		}
		
		distance = map.robot.sensors.getEastFrontSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX())).get((int) (map.robot.position.getPosY() - distance)).setValue(2);
		}
		
		distance = map.robot.sensors.getEastBackSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX() + robotHeight)).get((int) (map.robot.position.getPosY() - distance)).setValue(2);
		}
		
		distance = map.robot.sensors.getWestFrontSensor();
		if(distance > minimum){
			map.map.get((int) (map.robot.position.getPosX())).get((int) (map.robot.position.getPosY() + robotWidth + distance)).setValue(2);
		}
		
	}
}
