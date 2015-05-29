package fagocity.controller;

import java.util.ArrayList;

import fagocity.model.Actor;
import fagocity.model.Player;
import fagocity.view.GameView;

public class GameController {
	/* Lista de Actors */
	static ArrayList<Actor> ActorsList;
	
	Player player;
	public GameController() {
		/* Cria o player */
		ActorsList = new ArrayList<Actor>();
		System.out.println("antes: " +ActorsList.isEmpty());
		player = PlayerController.createPlayer(GameView.WIDTH/2, GameView.HEIGHT/2, ActorID.Player);
		System.out.println("depois: " +ActorsList.get(0).getX());
	}
	
	
	
}
