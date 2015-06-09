package fagocity.model;

import java.awt.Color;

import fagocity.controller.BoundsController;
import fagocity.controller.CameraController;
import fagocity.controller.EnemyController;
import fagocity.controller.GameController;
import fagocity.controller.PlayerController;
import fagocity.controller.Interfaces.IActorController;
import fagocity.view.GameView;

public class Player extends Actor {
	private final int defaultRadius = 110;
	private final double defaultSpeed = 8;
	private int lifes = 3;
	private long lastDeathTime = 0;
	private long deathTimeDelay = 1000; // em milisegundos
		
	public Player(int x, int y, double velX, double velY, int radius, Color color,
			GameView view, GameModel model, GameController controller) {
		super(x, y, velX, velY, radius, color, view, model, controller);
		actorController = new PlayerController(this, view, model, controller);
	}
	
	public long getLastDeathTime() {
		return lastDeathTime;
	}
	
	public long getDeathTimeDelay() {
		return deathTimeDelay;
	}
	
	public void setLastDeathTime(long lastDeathTime) {
		lastDeathTime = lastDeathTime;
	}
	
	public int getLifes (){
		return lifes;
	}
	
	public double getDefaultSpeed (){
		return defaultSpeed;
	}
	
	public void setLifes (int lifes){
		this.lifes = lifes;
	}
	
	public IActorController getPlayerController(){
		return actorController;
	}
	
	public int getDefaultRadius (){
		return defaultRadius;
	}
	
}
