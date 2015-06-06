package fagocity.model;

import java.awt.Color;

import fagocity.controller.PlayerController;

public class Player extends Actor {
	public static final int defaultRadius = 50;
	public static final double defaultSpeed = 8;
	public static int lives = 3;
	private static long lastDeathTime = 0;
	private static long deathTimeDelay = 2000; // em milisegundos
		
	public Player(int x, int y, double velX, double velY, int radius, Color color) {
		super(x, y, velX, velY, radius, color);
		actorController = new PlayerController(this);
	}
	
	public static long getLastDeathTime() {
		return lastDeathTime;
	}
	
	public static long getDeathTimeDelay() {
		return Player.deathTimeDelay;
	}
	
	public static void setLastDeathTime(long lastDeathTime) {
		Player.lastDeathTime = lastDeathTime;
	}
	
}
