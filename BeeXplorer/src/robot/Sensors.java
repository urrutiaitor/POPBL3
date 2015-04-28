package robot;

public class Sensors {
	int eastFrontSensor;
	int eastBackSensor;
	int westFrontSensor;
	int northRightSensor;
	int northLeftSensor;
	int northCenterSensor;
	
	public Sensors(int eastFrontSensor, int eastBackSensor,
			int westFrontSensor, int northRightSensor,
			int northLeftSensor, int northCenterSensor) {
		super();
		this.eastFrontSensor = eastFrontSensor;
		this.eastBackSensor = eastBackSensor;
		this.westFrontSensor = westFrontSensor;
		this.northRightSensor = northRightSensor;
		this.northLeftSensor = northLeftSensor;
		this.northCenterSensor = northCenterSensor;
	}
	
	public void interpretateData(){
		
	}

	public int getEastFrontSensor() {
		return eastFrontSensor;
	}

	public void setEastFrontSensor(int eastFrontSensor) {
		this.eastFrontSensor = eastFrontSensor;
	}

	public int getEastBackSensor() {
		return eastBackSensor;
	}

	public void setEastBackSensor(int eastBackSensor) {
		this.eastBackSensor = eastBackSensor;
	}

	public int getWestFrontSensor() {
		return westFrontSensor;
	}

	public void setWestFrontSensor(int westFrontSensor) {
		this.westFrontSensor = westFrontSensor;
	}

	public int getNorthRightSensor() {
		return northRightSensor;
	}

	public void setNorthRightSensor(int northRightSensor) {
		this.northRightSensor = northRightSensor;
	}

	public int getNorthLeftSensor() {
		return northLeftSensor;
	}

	public void setNorthLeftSensor(int northLeftSensor) {
		this.northLeftSensor = northLeftSensor;
	}

	public int getNorthCenterSensor() {
		return northCenterSensor;
	}

	public void setNorthCenterSensor(int northCenterSensor) {
		this.northCenterSensor = northCenterSensor;
	}
	
	
}
