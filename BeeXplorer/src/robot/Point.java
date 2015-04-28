package robot;

public class Point {
	int value;
	private final int undefined = 0;
	private final int unexisting = 1;
	private final int obstacle = 2;
	private final int free = 3;
	
	
	public Point(int value) {
		super();
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
}
