package fagocity.controller;

import fagocity.model.Player;

public class PlayerController {
	
	/* Cria um Player */
	public static Player createPlayer(double x, double y, ActorID id) {
		Player player = (Player) ActorFactory.createActor(x, y, id);
		return player;
	}
}
