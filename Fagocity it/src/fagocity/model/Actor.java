package fagocity.model;

import fagocity.model.interfaces.IActor;

public abstract class Actor implements IActor {
	public int x, y, radius;
	private double velX, velY, mass;
	
	public Actor(int x, int y, int radius, double mass) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.mass = mass;
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
	public int getRadius() {
		return this.radius;
	}
}