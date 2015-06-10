package fagocity.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import fagocity.model.GameStatus;
import fagocity.model.GameStatus.STATUS;
import fagocity.view.Interfaces.ViewSingleton;

public class MenuView implements ViewSingleton {
	
	private static MenuView menuView = null;
	
	public static MenuView getInstance() {
		if (menuView == null)
			menuView = new MenuView();
		return menuView;
	}
	
	private MenuView (){
		
	}
	
	public void render (Graphics g){
		if (GameStatus.status == STATUS.Menu){
			Font fnt = new Font ("arial", 1, 50);
			
			g.setFont (fnt);
			g.setColor(Color.DARK_GRAY);
			g.drawString ("Fagocity It!", GameView.getScreenWidth()/2 - 120, 120);
					
			/*botoes do menu*/
			g.drawRect (82, 200, 190, 65);
			g.drawString ("Play", 125, 252);
			
			g.drawRect (82, 429 , 190, 65);
			g.drawString ("Help", 125, 477);
		
			g.drawRect (82, 632, 190, 65);
			g.drawString ("Quit", 125, 683);
			
		}
		
		/*janela help*/
		else if (GameStatus.status == STATUS.Help){	
			
			String text1 = "- Mouse move o personagem";
			String text2 = "- Bolinhas maiores que voce te fagocitam";
			String text3 = "- Bolinhas menores que voce serao fagocitadas";
			String text4 = "- Fagocitar bolinhas com cor diferente da sua tira sua vida";
			String text5 = "- Cada sequencia de fagocitacoes faz o personagem voltar a ser pequeno";

			Font font = new Font ("arial", 1, 30);
			g.setFont (font);
			g.setColor(Color.DARK_GRAY);

			g.drawString (text1, 40, 250);
			g.drawString (text2, 40, 340);
			g.drawString (text3, 40, 430);
			g.drawString (text4, 40, 520);
			g.drawString (text5, 40, 610);
			
			Font font2 = new Font ("arial", 1, 50);
			g.setFont ( font2 );
			g.setColor(Color.DARK_GRAY);
			
			g.drawString ("Help", 900, 120);
			
			/*botao back*/
			g.drawRect (864, 650, 190, 65);
			g.drawString ("Back", 900, 700);
		}
	}
}
