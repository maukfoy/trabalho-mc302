package fagocity.controller;

import fagocity.model.Player;
import fagocity.view.GameView;

public class GameController {
	
	Player player;
	public GameController() {
		/* Cria o player */
		player = PlayerController.createPlayer((double)GameView.WIDTH/2, (double)GameView.HEIGHT/2, ActorID.Player);
	}
	
}
