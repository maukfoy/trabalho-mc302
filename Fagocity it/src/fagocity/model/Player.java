package fagocity.model;

import java.awt.Color;

import fagocity.controller.MouseController;
import fagocity.model.interfaces.IActor;

public class Player extends Actor implements IActor {
	
	public Player(int x, int y, double velX, double velY, int radius, Color color) {
		super(x, y, velX, velY, radius, color);
	}
	
	public void update() {
		AtualizaVelocidade();
		atualizarPosicao();
	}
	
	private void atualizarPosicao() {
		x += velX;
		y += velY;
	}
	
	private void AtualizaVelocidade() {
		double velTotal = 8;
		int mouseX = MouseController.getMouseX();
		int mouseY = MouseController.getMouseY();
		
		/* Se houver distância entre x do mouse e x da bola, mover a bola no eixo x */
		if( (mouseX - x) != 0.0 ) {
			double oldVelX = velX;
			velX = velTotal * ( (mouseX - x) / Math.sqrt( (mouseY - y)*(mouseY - y) +  (mouseX - x)*(mouseX - x) ) );
			if( oldVelX > 0 && velX < 0 ||  oldVelX < 0 && velX > 0) {
				velX = 0;
				// x = mouseX; talvez seja util - atualmente da bug, nao usar
			}
			System.out.println(velX);
		}
		/* Se houver distância entre y do mouse e y da bola, mover a bola no eixo y */
		if( (mouseY - y) != 0.0 ) {
			double oldVelY = velY;
			velY = velTotal * ( (mouseY - y) / Math.sqrt( (mouseY - y)*(mouseY - y) +  (mouseX - x)*(mouseX - x) ) );
			if( oldVelY > 0 && velY < 0 ||  oldVelY < 0 && velY > 0) {
				velY = 0;
				// y = mouseY; talvez seja util - atualmente da bug, nao usar
			}
			//System.out.println(velY);
		}
	}
	
}
