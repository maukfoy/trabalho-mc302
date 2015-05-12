package com.fagocity.main;

import java.awt.Color;
import java.awt.Graphics;

public class Inimigo extends ObjetoJogo {

	public Inimigo(int x, int y, ID id) {
		super(x, y, id);
		
		/*velocidade do inimigo*/
		velX = 5;
		velY = 5;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		/*faz inimigo voltar quando bater na parede (limites da tela)*/
		if (y <= 0 || y >= Fagocity.ALTURA - 32) velY *= -1;
		if (x <= 0 || x >= Fagocity.LARGURA - 16) velX *= -1;		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect (x, y, 16, 16);
	}

}
