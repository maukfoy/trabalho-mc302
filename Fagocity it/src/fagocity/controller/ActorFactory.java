package fagocity.controller;

import java.awt.Color;
import java.util.EmptyStackException;

import fagocity.model.Actor;
import fagocity.model.Enemy;
import fagocity.model.Player;

public class ActorFactory {
	
	/* Cria um Actor */
	public static Actor createActor(int x, int y, double velX, double velY, int radius, Color color, String type) {
		
		/* Cria um Player */
		if( type.equals("player")) {
			Player player = new Player(x, y, velX, velY, radius, color);
			/* Adiciona o player à lista de actors */
			GameController.addToActorsList(player);
			return player;
		}
		
		/* Cria um Enemy */
		else if( type.equals("enemy")) {
			Enemy enemy = new Enemy(x, y, velX, velY, radius, color);
			/* Adiciona o enemy à lista de actors */
			GameController.addToActorsList(enemy);
			return enemy;
		}
		
		/* O objeto pedido para ser criado não é conhecido */
		else
			return null;
	}
}
