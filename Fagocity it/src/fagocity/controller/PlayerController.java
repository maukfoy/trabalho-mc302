package fagocity.controller;

import fagocity.controller.Interfaces.ActorController;
import fagocity.model.Player;

public class PlayerController implements ActorController {
	
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
		double velTotal = 8;
		int mouseX = MouseController.getMouseX();
		int mouseY = MouseController.getMouseY();
		
		/* Distance between mouse and player */
		double distance = Math.sqrt( (mouseY - p.getY())*(mouseY - p.getY()) +  (mouseX - p.getX())*(mouseX - p.getX()) );
		
		/* 5 é a tolerância de erro. Ele é necessário pois evita uma divisão por 0 e evita o flickering do player */
		if(distance > 5) {
			p.setVelX ( (velTotal/distance) * (mouseX - p.getX()) );
			p.setVelY ( (velTotal/distance) * (mouseY - p.getY()) );
		}
		else {
			p.setVelX (0);
			p.setVelY (0);
		}
		System.out.println("distance:" +distance);
	}
}
