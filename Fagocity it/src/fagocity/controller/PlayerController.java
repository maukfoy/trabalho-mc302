package fagocity.controller;

import java.util.ArrayList;

import fagocity.controller.Interfaces.IActorController;
import fagocity.model.Actor;
import fagocity.model.GameModel;
import fagocity.model.Player;
import fagocity.view.Display;

public class PlayerController implements IActorController {
	
	private Player p;
	
	public PlayerController (Player player){
		this.p = player;
	}
	
	public void update() {
		atualizaVelocidade();
		atualizarPosicao();
	}
	
	private void atualizarPosicao() {
		p.setX(p.getX() + (int)p.getVelX());
		p.setY(p.getY() + (int)p.getVelY());
	}
	
	private void atualizaVelocidade() {
		double velTotal = Player.defaultSpeed;
		int mouseX = MouseController.getMouseX();
		int mouseY = MouseController.getMouseY();
		
		/* Distance between mouse and player */
		double distance = Math.sqrt( (mouseY - p.getY())*(mouseY - p.getY()) +  (mouseX - p.getX())*(mouseX - p.getX()) );
		
		/* 5 é a tolerância de erro. Ele é necessário pois evita uma divisão por 0 e evita o flickering do player */
		if(distance > 5) {
			p.setVelX ( (velTotal/distance) * (mouseX - p.getX()) );
			p.setVelY ( (velTotal/distance) * (mouseY - p.getY()) );
			Display.showCursor();
		}
		else {
			Display.hideCursor();
			p.setVelX (0);
			p.setVelY (0);
		}
	}
	
	public static Player getPlayer() {
		ArrayList<Actor> list = GameModel.getActorsList();
		Player player = null;
		
		for(int i = 0; i < list.size(); i++) {
			Actor actor = list.get(i);
			if( actor instanceof Player )
				player = (Player) actor;
		}
		return player;
	}
}
