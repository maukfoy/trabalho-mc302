package fagocity.controller;

import fagocity.model.Player;

public class PlayerController {
	
	/* Cria um Player */
	public static Player createPlayer(int x, int y, ActorID id) {
		Player player = (Player) ActorFactory.createActor(x, y, id);
		return player;
	}
}
