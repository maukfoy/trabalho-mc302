package fagocity.controller;

import fagocity.model.Player;

public class PlayerController {
	
	/* Cria um Player */
	public static  Player createPlayer(int x, int y) {
		Player player = (Player) ActorFactory.createActor(x, y, "player");
		return player;
	}
}