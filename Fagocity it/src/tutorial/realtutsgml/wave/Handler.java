package tutorial.realtutsgml.wave;

import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {
	
	/*cria lista contendo todos os objetos*/
	LinkedList<Objeto> obj = new LinkedList<Objeto>();
	
	/* Método que percorre todos objetos e atualiza suas informações */
	public void tick() 
	{
		int i;
		for( i = 0; i < obj.size(); i++ )
		{
			Objeto tempObj= obj.get(i);
			tempObj.tick();
		}
	}
	
	/* Método que percorre todos objetos, renderiza e os coloca na tela */
	public void render(Graphics g)
	{
		int i;
		for( i = 0; i < obj.size(); i++ )
		{
			Objeto tempObj= obj.get(i);
			tempObj.render(g);
		}
	}
	
	/* Adiciona um objeto à lista ligada de objetos do jogo */
	public void addObjeto(Objeto objeto) 
	{
		this.obj.add(objeto);
	}
	
	/* Remove um objeto da lista ligada de objetos do jogo */
	public void delObjeto(Objeto objeto)
	{
		this.obj.remove(objeto);
	}

}
