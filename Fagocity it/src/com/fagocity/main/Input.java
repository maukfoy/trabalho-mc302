package com.fagocity.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {
	
	private  Handler handler;
	
	/* Construtor */
	public Input (Handler handler) {
		this.handler = handler;
	}
	
	/* Realiza a��o quando um bot�o for apertado */
	public void keyPressed(KeyEvent k) {
		int key, i;
		key = k.getKeyCode();
		
		/* Percorre todos objetos do jogo na busca do jogador */
		for( i = 0; i < handler.obj.size(); i++) {
			ObjetoJogo tempObj = handler.obj.get(i);
			
			/* Verifica se o objeto � o jogador. Se for, move o jogador */
			if(tempObj.getID() == ID.Player) {
				if(key == KeyEvent.VK_W)
					tempObj.setVelY(-7);
				if(key == KeyEvent.VK_S)
					tempObj.setVelY(7);
				if(key == KeyEvent.VK_A)
					tempObj.setVelX(-7);
				if(key == KeyEvent.VK_D)
					tempObj.setVelX(7);
			}
			if (key == KeyEvent.VK_ESCAPE)
				System.exit(0);
		}
	}
	
	/* Realiza uma a��o quando um bot�o n�o estiver apertado */
	public void keyReleased(KeyEvent k) {
		int key, i;
		key = k.getKeyCode();
		
		/* Percorre todos objetos do jogo na busca do jogador */
		for( i = 0; i < handler.obj.size(); i++) {
			ObjetoJogo tempObj = handler.obj.get(i);
			
			/* Verifica se o objeto � o jogador. Se for, move o jogador */
			if(tempObj.getID() == ID.Player) {
				if( key == KeyEvent.VK_W)
					tempObj.setVelY(0);
				if( key == KeyEvent.VK_S)
					tempObj.setVelY(0);
				if( key == KeyEvent.VK_A)
					tempObj.setVelX(0);
				if( key == KeyEvent.VK_D)
					tempObj.setVelX(0);
			}
		}	
	}
}
