package fagocity.controller;

import fagocity.controller.Interfaces.ActorController;
import fagocity.model.Enemy;

public class EnemyController implements ActorController {
	
	private Enemy e;
	
	public EnemyController (Enemy enemy){
		this.e = enemy;
	}
	
	public void update() {
		atualizarPosicao();
	}
	
	private void atualizarPosicao() {
		e.setX(e.getX() + (int)e.getVelX());
		e.setY(e.getY() + (int)e.getVelY());
	}

}
