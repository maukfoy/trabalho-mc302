package fagocity.controller;

import java.awt.Color;
import java.util.EmptyStackException;

import fagocity.model.Enemy;
import fagocity.model.Player;

public class ActorFactory {
	
	/* Cria um Actor */
	public static void createActor(double x, double y, double velX, double velY, double radius, Color color, String type) {
		
		/* Cria um Player */
		if( type.equals("player")) {
			Player player = new Player(x, y, velX, velY, radius, color);
			/* Adiciona o player à lista de actors */
			GameController.addToActorsList(player);
		}
		
		/* Cria um Enemy */
		else if( type.equals("enemy")) {
			Enemy enemy = new Enemy(x, y, velX, velY, radius, color);
			/* Adiciona o enemy à lista de actors */
			GameController.addToActorsList(enemy);
		}
		
		/* O objeto pedido para ser criado não é conhecido */
		else
			throw new EmptyStackException();
	}
}
