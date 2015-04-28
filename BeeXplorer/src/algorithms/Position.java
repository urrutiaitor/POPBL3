package algorithms;

public class Position {
	int posX;
	int posY;
	
	public Position(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posX;
		result = prime * result + posY;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (posX != other.posX)
			return false;
		if (posY != other.posY)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Position [posX=" + posX + ", posY=" + posY + "]";
	}	
	
	public int incrementPosX(int value) {
		return this.posX = this.posX + value;
	}
	
	public int incrementPosY(int value) {
		return this.posY = this.posY + value;
	}
}
