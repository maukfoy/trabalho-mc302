package fagocity.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class EndView {
	
	public void render (Graphics g){
	
		Font fnt = new Font ("arial", 1, 50);
		
		g.setFont (fnt);
		g.setColor(Color.DARK_GRAY);
		g.drawString ("Fagocity It!", GameView.getScreenWidth()/2 - 120, 120);
		
		/*botoes do menu*/
		g.drawRect (82, 270, 190, 65);
		g.drawString ("Reset", 125, 320);
		
		g.drawRect (82, 489 , 190, 65);
		g.drawString ("Help", 125, 538);
	
		g.drawRect (82, 702, 190, 65);
		g.drawString ("Quit", 125, 752);
	}
}
