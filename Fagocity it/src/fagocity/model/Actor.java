package fagocity.model;

public class Actor {
	protected int x, y;
	protected double velX, velY;
	
	public Actor(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/* Getters and setters */
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
}