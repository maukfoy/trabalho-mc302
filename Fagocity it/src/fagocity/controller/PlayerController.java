package fagocity.controller;

import fagocity.model.Player;

public class PlayerController {
	
	/* Cria um Player */
	public static  Player createPlayer(int x, int y, int radius, double mass) {
		Player player = (Player) ActorFactory.createActor(x, y, radius, mass, "player");
		return player;
	}
	
}