package fagocity.model;

public class Actor {
	protected double x, y;
	protected double velX, velY;
	
	public Actor(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/* Getters and setters */
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}