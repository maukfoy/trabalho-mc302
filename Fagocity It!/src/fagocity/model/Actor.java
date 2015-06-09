package fagocity.model;

import java.awt.Color;

import fagocity.controller.BoundsController;
import fagocity.controller.CameraController;
import fagocity.controller.GameController;
import fagocity.controller.Interfaces.IActorController;
import fagocity.view.GameView;

public abstract class Actor {
	protected int x, y, radius;
	protected double velX;
	protected double velY;
	protected Color color;
	protected IActorController actorController;
	protected int growingRadius = 0;
	protected GameModel model;
	protected GameView view;
	
	public Actor(int x, int y, double velX, double velY, int radius, Color color,
			GameView view, GameModel model, GameController controller) {
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
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setVelX(double x) {
		this.velX = x;
	}
	public void setVelY(double y) {
		this.velY = y;
	}
	public void setRadius(int radius){
		this.radius = radius;
	}
	public void setGrowingRadius(int growingRadius){
		this.growingRadius = growingRadius;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public double getVelX() {
		return this.velX;
	}
	public double getVelY() {
		return this.velY;
	}
	public int getRadius() {
		return this.radius;
	}
	public int getGrowingRadius(){
		return this.growingRadius;
	}
	public Color getColor() {
		return this.color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
}
