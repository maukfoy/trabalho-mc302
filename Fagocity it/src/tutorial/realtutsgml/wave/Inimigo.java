package tutorial.realtutsgml.wave;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Inimigo extends Objeto {
	
	public Inimigo (float x, float y, ID id, Handler handler)
	{
		super(x, y, id, handler);
		
		/*velocidade do inimigo*/
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle ((int) x, (int) y, 16, 16);
	}

	public void tick()
	{
		x += velX;
		y += velY;
		
		/*faz inimigo voltar quando bater na parede (limites da tela)*/
		if (y <= 0 || y >= Fagocity.ALTURA - 32) 
			velY *= -1;
		if (x <= 0 || x >= Fagocity.LARGURA - 16)
			velX *= -1;	
	}

	public void render(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect ((int) x, (int) y, 16, 16);
	}

}
