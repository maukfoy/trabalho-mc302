package fagocity.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import fagocity.model.GameStatus;
import fagocity.model.GameStatus.STATUS;

public class MenuView {
	
	public static void render (Graphics g)
	{
		if (GameStatus.status == STATUS.Menu)
		{
		Font fnt = new Font ("arial", 1, 50);
		
		g.setFont (fnt);
		g.setColor(Color.DARK_GRAY);
		g.drawString ("Fagocity It!", 820, 120);
				
		/*botoes do menu*/
		g.drawRect (82, 270, 190, 65);
		g.drawString ("Play", 125, 320);
		
		g.drawRect (82, 489 , 190, 65);
		g.drawString ("Help", 125, 538);
	
		g.drawRect (82, 702, 190, 65);
		g.drawString ("Quit", 125, 752);
		}
		
		/*janela help*/
		else if (GameStatus.status == STATUS.Help)
		{	
			String text1 = "- Mouse move o personagem";
			String text2 = "- Bolinhas maiores que voc\u00EA te fagocitam";
			String text3 = "- Bolinhas menores que voc\u00EA s\u00E3o fagocitadas";
			String text4 = "- Fagocitar bolinhas com cor diferente da sua, tira sua vida";
			String text5 = "- Deve-se completar uma sequ\u00EAncia de fagocita\u00E7\u00F5es para voltar a ser pequeno";


			Font font = new Font ("arial", 1, 40);
			g.setFont ( font );
			g.setColor(Color.DARK_GRAY);

			g.drawString ( text1 ,  40 , 330) ;
			g.drawString ( text2 , 40, 420 );
			g.drawString ( text3, 40 , 510 );
			g.drawString ( text4 , 40, 600 );
			g.drawString ( text5 , 40 , 690 );
			
			Font font2 = new Font ("arial", 1, 50);
			g.setFont ( font2 );
			g.setColor(Color.DARK_GRAY);
			
			g.drawString ("Help", 900, 120);
			
			/*botao back*/
			g.drawRect (864, 900, 190, 65);
			g.drawString ("Back", 900, 950);
			
			
		}

	}

}
