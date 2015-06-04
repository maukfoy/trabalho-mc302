package fagocity.model;

import java.awt.Color;

import fagocity.model.interfaces.IActor;

public abstract class Actor implements IActor {
	public int x, y, radius;
	protected double velX;
	protected double velY;
	protected Color color;
	
	public Actor(int x, int y, double velX, double velY, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.radius = radius;
		this.color = color;
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
	public Color getColor() {
		return this.color;
	}
}