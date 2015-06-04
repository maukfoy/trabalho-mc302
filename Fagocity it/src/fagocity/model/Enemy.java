package fagocity.model;

import java.awt.Color;

import fagocity.model.interfaces.IActor;

public class Enemy extends Actor implements IActor {
	
	public Enemy(int x, int y, double velX, double velY, int radius, Color color) {
		super(x, y, velX, velY, radius, color);
	}

	@Override
	public void update() {
		atualizarPosicao();
	}
	
	private void atualizarPosicao() {
		x += velX;
		y += velY;
	}

}
