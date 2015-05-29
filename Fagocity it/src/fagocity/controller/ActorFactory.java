package fagocity.controller;

import fagocity.model.Enemy;
import fagocity.model.Player;
import fagocity.model.interfaces.IActor;

public class ActorFactory {
	
	/* Cria um Actor */
	public static IActor createActor(int x, int y, String type) {
		
		/* Cria um Player */
		if( type.equals("player")) {
			Player player = new Player(x, y);
			/* Adiciona o player à lista de actors */
			GameController.addToActorsList(player);
			return player;
		}
		
		/* Cria um Enemy */
		else if( type.equals("enemy")) {
			Enemy enemy = new Enemy(x, y);
			/* Adiciona o enemy à lista de actors */
			GameController.addToActorsList(enemy);
			return enemy;
		}
		
		/* O objeto pedido para ser criado não é conhecido */
		return null;
	}
}
