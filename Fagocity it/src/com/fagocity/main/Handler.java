package com.fagocity.main;

import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {
	
	LinkedList<ObjetoJogo> obj = new LinkedList<ObjetoJogo>();
	
	/* Método que percorre todos objetos e atualiza suas informações */
	public void tick() {
		int i;
		for( i = 0; i < obj.size(); i++ ) {
			ObjetoJogo tempObj= obj.get(i);
			tempObj.tick();
		}
	}
	
	/* Método que percorre todos objetos, renderiza e os coloca na tela */
	public void render(Graphics g) {
		int i;
		for( i = 0; i < obj.size(); i++ ) {
			ObjetoJogo tempObj= obj.get(i);
			tempObj.render(g);
		}
	}
	
	/* Adiciona um objeto à lista ligada de objetos do jogo */
	public void addObjeto(ObjetoJogo objeto) {
		this.obj.add(objeto);
	}
	
	/* Remove um objeto da lista ligada de objetos do jogo */
	public void delObjeto(ObjetoJogo objeto) {
		this.obj.remove(objeto);
	}

}
