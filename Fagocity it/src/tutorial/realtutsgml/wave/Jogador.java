package tutorial.realtutsgml.wave;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Jogador extends Objeto {
		
	public Jogador (float x, float y, ID id, Handler handler)
	{
		super(x, y, id, handler);	
	}
	
	public Rectangle getBounds() 
	{
		return new Rectangle ((int) x, (int) y, 32, 32);
	}
	
	private void colisao () {
		/*percorre os objetos do jogo*/
		for (int i = 0; i <handler.obj.size(); i++)
		{
			Objeto temp = handler.obj.get(i);
		
			/*se o objeto for um inimigo e colidir com o jogador*/
			if ( (temp.getID() == ID.Inimigo) && ( (this.getBounds()).intersects( temp.getBounds() ) ) )
			{
					/*vida diminui*/
					HUD.vida -= 1;
					
					/*o inimigo desaparece*/
					handler.delObjeto(temp);
			}
		}
	}
	
	/* Método que atualiza as informações do objeto */
	public void tick() 
	{
		x += velX;
		y += velY;
		
		/*limita a liberdade do jogador para dentro dos limites da tela*/
		x = Fagocity.limita (x, 0, Fagocity.LARGURA - 37);
		y = Fagocity.limita (y, 0, Fagocity.ALTURA - 60);
		
		colisao ();

	}
	
	/* Renderiza o objeto */
	public void render(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, 32, 32);
	}
	
}


