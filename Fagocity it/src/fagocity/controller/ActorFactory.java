package fagocity.controller;

import fagocity.controller.inter.IActorFactory;
import fagocity.model.Actor;
import fagocity.model.Enemy;
import fagocity.model.Player;

public class ActorFactory implements IActorFactory {
	
	/* Cria um Actor */
	public static Actor createActor(double x, double y, ActorID id) {
		
		if( id == ActorID.Player) {
			Player player = new Player(x, y);
			return player;
		}
		else if( id == ActorID.Enemy) {
			Enemy enemy = new Enemy(x, y);
			return enemy;
		}
		
		return null; /* Não retorna nada pois o ID não é conhecido */
	}
}
