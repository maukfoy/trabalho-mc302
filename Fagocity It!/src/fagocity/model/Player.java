package fagocity.model;

import java.awt.Color;

import fagocity.controller.PlayerController;
import fagocity.controller.Interfaces.IActorController;

public class Player extends Actor {
	
	//private static Player player = null;
	
	private final int defaultRadius = 110;
	private final double defaultSpeed = 8;
	private int lifes = 3;
	private long lastDeathTime = 0;
	private long deathTimeDelay = 1000; // em milisegundos
		
	public Player(int x, int y, double velX, double velY, int radius, Color color,
			String type) {
		super(x, y, velX, velY, radius, color, type);
		actorController = new PlayerController(this);
	}
	
	/*
	public static Player getInstance(int x, int y, double velX, double velY, int radius, Color color,
			GameView view, GameModel model, GameController controller) {
		if (player == null)
			player = new Player(x, y, velX, velY, radius, color, view, model, controller);
		return player;
	}
	
	public static Player getInstance() {
		return player;
	}*/
	
	public long getLastDeathTime() {
		return lastDeathTime;
	}
	
	public long getDeathTimeDelay() {
		return deathTimeDelay;
	}
	
	public void setLastDeathTime(long lastDeathTime) {
		this.lastDeathTime = lastDeathTime;
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
