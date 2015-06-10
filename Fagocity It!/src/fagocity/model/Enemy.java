package fagocity.model;

import java.awt.Color;

import fagocity.controller.EnemyController;


public class Enemy extends Actor {
		
	public Enemy(int x, int y, double velX, double velY, int radius, Color color,
			String type) {
		super(x, y, velX, velY, radius, color, type);
		actorController = new EnemyController(this);
	}
	
}
