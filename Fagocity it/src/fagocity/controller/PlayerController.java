package fagocity.controller;


import java.awt.Color;

import fagocity.model.Player;

public class PlayerController {
	
	/* Cria um Player */
	public static  Player createPlayer(int x, int y, double velX, double velY, int radius, Color color) {
		Player player = (Player) ActorFactory.createActor(x, y, velX, velY, radius, color, "player");
		return player;
	}
	
}