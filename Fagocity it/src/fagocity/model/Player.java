package fagocity.model;

import java.awt.Color;

import fagocity.controller.PlayerController;

public class Player extends Actor {
		
	public Player(int x, int y, double velX, double velY, int radius, Color color) {
		super(x, y, velX, velY, radius, color);
		actorController = new PlayerController(this);
	}	
}
