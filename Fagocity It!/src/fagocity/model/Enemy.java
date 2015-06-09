package fagocity.model;

import java.awt.Color;

import fagocity.controller.BoundsController;
import fagocity.controller.CameraController;
import fagocity.controller.EnemyController;
import fagocity.controller.GameController;
import fagocity.view.GameView;

public class Enemy extends Actor {
		
	public Enemy(int x, int y, double velX, double velY, int radius, Color color,
			String type, GameView view, GameModel model,	GameController controller) {
		super(x, y, velX, velY, radius, color, type, view, model, controller);
		actorController = new EnemyController(this);
	}
	
}
