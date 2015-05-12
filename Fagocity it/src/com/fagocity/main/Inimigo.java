package com.fagocity.main;

import java.awt.Color;
import java.awt.Graphics;

public class Inimigo extends ObjetoJogo {

	public Inimigo(int x, int y, ID id) {
		super(x, y, id);
		
		velX = 5;
		velY = 5;
	}

	public void tick() {
		x += velX;
		y += velY;
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect (x, y, 16, 16);
	}

}
