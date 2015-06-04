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
		p.setX(p.getX() + p.getVelX());
		p.setY(p.getY() + p.getVelY());
	}
	
	private void atualizaVelocidade() {
		double velTotal = 8;
		double mouseX = MouseController.getMouseX();
		double mouseY = MouseController.getMouseY();
		
		/* Se houver distância entre x do mouse e x da bola, mover a bola no eixo x */
		if( (mouseX - p.getX()) != 0.0 ) {
			double oldVelX = p.getVelX();
			p.setVelX (velTotal * ( (mouseX - p.getX()) / Math.sqrt( (mouseY - p.getY())*(mouseY - p.getY()) +  (mouseX - p.getX())*(mouseX - p.getX()) )) );
			if( oldVelX > 0 && p.getVelX() < 0 ||  oldVelX < 0 && p.getVelX() > 0)
				p.setVelX(0);
			//System.out.println(velX);
		}
		/* Se houver distância entre y do mouse e y da bola, mover a bola no eixo y */
		if( (mouseY - p.getY()) != 0.0 ) {
			double oldVelY = p.getVelY();
			p.setVelY(velTotal * ( (mouseY - p.getY()) / Math.sqrt( (mouseY - p.getY())*(mouseY - p.getY()) +  (mouseX - p.getX())*(mouseX - p.getX()) )));
			if( oldVelY > 0 && p.getVelY() < 0 ||  oldVelY < 0 && p.getVelY() > 0)
				p.setVelY(0);
			//System.out.println(velY);
		}
	}
}
