package fagocity.controller;

import fagocity.controller.inter.IActorFactory;
import fagocity.model.Actor;
import fagocity.model.Enemy;
import fagocity.model.Player;

public class ActorFactory implements IActorFactory {
	
	/* Cria um Actor */
	public static Actor createActor(int x, int y, ActorID id) {
		
		if( id == ActorID.Player) {
			Player player = new Player(x, y);
			/* Adiciona o player � lista de actors */
			GameController.ActorsList.add(player);
			return player;
		}
		else if( id == ActorID.Enemy) {
			Enemy enemy = new Enemy(x, y);
			/* Adiciona o enemy � lista de actors */
			GameController.ActorsList.add(enemy);
			return enemy;
		}
		
		return null; /* N�o retorna nada pois o ID n�o � conhecido */
	}
}
