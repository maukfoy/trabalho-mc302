package fagocity.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import fagocity.model.GameMode;
import fagocity.model.GameMode.STATUS;

public class MenuView {
	
	private static int width = GameView.getScreenWidth();
	private static int height = GameView.getScreenHeight();
	
	public static void render (Graphics g)
	{
		if (GameMode.status == STATUS.Menu)
		{
		Font f = null;
		try {
			f = Font.createFont (Font.TRUETYPE_FONT, new FileInputStream(new File ("Sansation-Regular.ttf"))).deriveFont(Font.PLAIN, 500);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		g.setFont (f);
		g.setColor (Color.white);
		g.drawString ("Menu", width/2 - (width/25), height/10);
		
		/*botoes do menu*/
		g.drawRect (width/23, 190, 200, 64);
		g.drawString ("Play", width/15, 240);
		
		g.drawRect (width/23, 320, 200, 64);
		g.drawString ("Help", width/15, 370);
	
		g.drawRect (width/23, 450, 200, 64);
		g.drawString ("Quit", width/15, 500);
		}
		/*janela help*/
		else if (GameMode.status == STATUS.Help)
		{
			Font fnt = new Font ("arial", 1, 50);
			Font fnt2 = new Font ("arial", 1, 30);
			
			g.setFont (fnt);
			g.setColor (Color.white);
			g.drawString ("Menu", 335, 70);
			
			g.drawRect (300, 450, 200, 64);
			g.drawString ("Back", 340, 500);
			
			g.setFont (fnt2);
			g.drawString ("Mouse move o personagem", 200, 250);
		}

	}

}
