package fagocity.controller;

import java.awt.Color;

import fagocity.controller.Interfaces.ControllerSingleton;
import fagocity.model.Actor;
import fagocity.model.Enemy;
import fagocity.model.GameModel;
import fagocity.model.Player;

public class ActorFactory implements ControllerSingleton{
	
	private GameModel model;
	
	private static ActorFactory actorFactory = null;
	
	public static ActorFactory getInstance() {
		if (actorFactory == null)
			actorFactory = new ActorFactory();
		return actorFactory;
	}
	
	private ActorFactory () {
		this.model = GameModel.getInstance();
	}
	
	/* Cria um Actor */
	public Actor createActor(int x, int y, double velX, double velY, int radius, Color color, String type) {
		
		/* Cria um Player */
		if( type.equals("player")) {
			Player player = new Player(x, y, velX, velY, radius, color, "player");
			/* Adiciona o player à lista de actors */
			model.addToActorsList(player);
			return player;
		}
		
		/* Cria um Enemy */
		else if( type.equals("enemy")) {
			Enemy enemy = new Enemy(x, y, velX, velY, radius, color, "enemy");
			/* Adiciona o enemy à lista de actors */
			model.addToActorsList(enemy);
			return enemy;
		}
		
		/* O objeto pedido para ser criado não é conhecido */
		else
			return null;
	}
}
