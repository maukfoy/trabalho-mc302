package fagocity.model;

import java.awt.Color;

import fagocity.controller.EnemyController;

public class Enemy extends Actor {
		
	public Enemy(double x, double y, double velX, double velY, double radius, Color color) {
		super(x, y, velX, velY, radius, color);
		actorController = new EnemyController(this);
	}
}
