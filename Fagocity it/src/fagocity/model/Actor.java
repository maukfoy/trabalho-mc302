package fagocity.model;

import java.awt.Color;

import fagocity.controller.Interfaces.ActorController;

public abstract class Actor {
	protected double x, y, radius;
	protected double velX;
	protected double velY;
	protected Color color;
	protected ActorController actorController;
	
	public Actor(double x, double y, double velX, double velY, double radius, Color color) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.radius = radius;
		this.color = color;
	}
	
	public void update (){
		actorController.update();
	}
	
	/* Getters and setters */
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setVelX(double x) {
		this.velX = x;
	}
	public void setVelY(double y) {
		this.velY = y;
	}
	public void setRadius(double radius){
		this.radius = radius;
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public double getVelX() {
		return this.velX;
	}
	public double getVelY() {
		return this.velY;
	}
	public double getRadius() {
		return this.radius;
	}
	public Color getColor() {
		return this.color;
	}
}
