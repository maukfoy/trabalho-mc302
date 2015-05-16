package com.fagocity.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {
	
	private  Handler handler;
	private boolean[] apertado = new boolean[4]; //4 booleanos (false commo default)
	
	/* Construtor */
	public Input (Handler handler) {
		this.handler = handler;
	}
	
	/* Realiza ação quando um botão for apertado */
	public void keyPressed(KeyEvent k)
	{
		int key = k.getKeyCode();
		
		/* Percorre todos objetos do jogo na busca do jogador */
		for( int i = 0; i < handler.obj.size(); i++)
		{
			Objeto tempObj = handler.obj.get(i);
			
			/* Verifica se o objeto é o jogador. Se for, move o jogador */
			if(tempObj.getID() == ID.Jogador)
			{
				if( key == KeyEvent.VK_W)
				{
					tempObj.setVelY(-5);
					apertado[0] = true;
				}
				if( key == KeyEvent.VK_S)
				{
					tempObj.setVelY(5);
					apertado[1] = true;
				}
				if( key == KeyEvent.VK_A)
				{
					tempObj.setVelX(-5);
					apertado[2] = true;
				}
				if( key == KeyEvent.VK_D)
				{
					tempObj.setVelX(5);
					apertado[3] = true;
				}
			}
		}
	}
	
	/* Realiza uma ação quando um botão não estiver apertado */
	public void keyReleased(KeyEvent k)
	{
		int key = k.getKeyCode();
		
		/* Percorre todos objetos do jogo na busca do jogador */
		for( int i = 0; i < handler.obj.size(); i++) 
		{
			Objeto tempObj = handler.obj.get(i);
			
			/* Verifica se o objeto é o jogador. Se for, move o jogador */
			if(tempObj.getID() == ID.Jogador) {
				if( key == KeyEvent.VK_W)
				{
					apertado[0] = false;
				}
				if( key == KeyEvent.VK_S)
				{
					apertado[1] = false;
				}				
				if( key == KeyEvent.VK_A)
				{
					apertado[2] = false;
				}				
				if( key == KeyEvent.VK_D)
				{
					apertado[3] = false;
				}
				
				/*movimento vertical*/
				if (!apertado[0] && !apertado[1])
					tempObj.setVelY(0);
				/*movimento horizontal*/
				if (!apertado[2] && !apertado[3])
					tempObj.setVelX(0);
			}
		}	
	}
}
