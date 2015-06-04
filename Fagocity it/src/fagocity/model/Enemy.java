package fagocity.model;

import java.awt.Color;

import fagocity.controller.EnemyController;

public class Enemy extends Actor {
		
	public Enemy(int x, int y, double velX, double velY, int radius, Color color) {
		super(x, y, velX, velY, radius, color);
		actorController = new EnemyController(this);
	}
}
