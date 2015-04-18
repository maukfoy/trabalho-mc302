package com.fagocity.main;

import java.awt.Color;
import java.awt.Graphics;

public class Jogador extends ObjetoJogo {
	
	/* Construtor */
	public Jogador(int x, int y, ID id) {
		/* Seta a posição inicial do jogador */
		super(x, y, id);
	}
	
	/* Método que atualiza as informações do objeto */
	public void tick() {
		x += velX;
		y += velY;
	}
	
	/* Renderiza o objeto */
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}
}
