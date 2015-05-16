package com.fagocity.main;

public class InimigoSeguidor extends Inimigo {
	
	private Objeto jogador; 

	public InimigoSeguidor(int x, int y, ID id, Handler handler)
	{
		super(x, y, id, handler);
		
		/*percorre os objetos e define "jogador" como o jogador já existente*/
		for (int i = 0; i < handler.obj.size(); i++)
		{
			if (handler.obj.get(i).getID() == ID.Jogador)
				jogador = handler.obj.get(i);
		}
	}
	
	public void tick ()
	{
		super.tick();
		
		/*algoritmo que permite que o inimigo siga o jogador, com base na distancia entre os dois*/
		float difX = x - jogador.getX() - 8;
		float difY = y - jogador.getY() - 8;
		float distancia = (float) Math.sqrt((x-jogador.getX()) * (x-jogador.getX()) +
		(y-jogador.getY()) * (y-jogador.getY()));
		
		velX = (float) ((-2.0/distancia) * difX);
		velY = (float) ((-2.0/distancia) * difY);		
	}	

}