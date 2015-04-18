package com.fagocity.main;

import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {
	
	LinkedList<ObjetoJogo> obj = new LinkedList<ObjetoJogo>();
	
	/* M�todo que percorre todos objetos e atualiza suas informa��es */
	public void tick() {
		int i;
		for( i = 0; i < obj.size(); i++ ) {
			ObjetoJogo tempObj= obj.get(i);
			tempObj.tick();
		}
	}
	
	/* M�todo que percorre todos objetos, renderiza e os coloca na tela */
	public void render(Graphics g) {
		int i;
		for( i = 0; i < obj.size(); i++ ) {
			ObjetoJogo tempObj= obj.get(i);
			tempObj.render(g);
		}
	}
	
	/* Adiciona um objeto � lista ligada de objetos do jogo */
	public void addObjeto(ObjetoJogo objeto) {
		this.obj.add(objeto);
	}
	
	/* Remove um objeto da lista ligada de objetos do jogo */
	public void delObjeto(ObjetoJogo objeto) {
		this.obj.remove(objeto);
	}

}
