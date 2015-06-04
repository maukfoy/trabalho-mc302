package fagocity.controller;


import java.awt.Color;

import fagocity.model.Enemy;

public class EnemyController {
	
	/* Cria um Enemy */
	public static Enemy createEnemy(int x, int y, double velX, double velY, int radius, Color color) {
		Enemy enemy = (Enemy) ActorFactory.createActor(x, y, velX, velY, radius, color, "enemy");
		return enemy;
	}

}
