package fagocity.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import fagocity.model.GameStatus;
import fagocity.model.GameStatus.STATUS;

public class MenuView {
	private static int width = GameView.getScreenWidth();
	private static int height = GameView.getScreenHeight();
	
	public static void render (Graphics g)
	{
		if (GameStatus.status == STATUS.Menu)
		{
		Font fnt = new Font ("arial", 1, 50);
		
		g.setFont (fnt);
		g.setColor (Color.white);
		g.drawString ("Fagocity It!", 20*width/50, height/10);
		
		/*botoes do menu*/
		g.drawRect (width/23, 5*height/20, width/10, height/17);
		g.drawString ("Play", width/15, 59*height/200);
		
		g.drawRect (width/23, 9*height/20 , width/10, height/17);
		g.drawString ("Help", width/15, 189*height/380);
	
		g.drawRect (width/23, 13*height/20, width/10, height/17);
		g.drawString ("Quit", width/15, 153*height/220);
		}
		
		/*janela help*/
		else if (GameStatus.status == STATUS.Help)
		{
			Font fnt = new Font ("arial", 1, 50);
			Font fnt2 = new Font ("arial", 1, 30);
			
			g.setFont (fnt);
			g.setColor (Color.white);
			g.drawString ("Help", 23*width/50, height/10);
			
			g.drawRect (22*width/50, 40*height/50, width/10, height/17);
			g.drawString ("Back", 23*width/50, 169*height/200);
			
			g.setFont (fnt2);
			g.drawString ("Mouse move o personagem", 25*width/64, 4*height/9);
		}

	}

}
