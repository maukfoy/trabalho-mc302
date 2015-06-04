package fagocity.controller;

import fagocity.controller.Interfaces.ActorController;
import fagocity.model.Enemy;
import fagocity.model.Player;

public class EnemyController implements ActorController {
	
	private Enemy e;
	
	public EnemyController (Enemy enemy){
		this.e = enemy;
	}
	
	public void update() {
		atualizarPosicao();
	}
	
	private void atualizarPosicao() {
		e.setX(e.getX() + e.getVelX());
		e.setY(e.getY() + e.getVelY());
	}

}
