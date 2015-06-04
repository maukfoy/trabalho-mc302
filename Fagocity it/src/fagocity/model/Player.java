package fagocity.model;

import java.awt.Color;

import fagocity.controller.PlayerController;

public class Player extends Actor {
		
	public Player(double x, double y, double velX, double velY, double radius, Color color) {
		super(x, y, velX, velY, radius, color);
		actorController = new PlayerController(this);
	}	
}
