package fagocity.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fagocity.model.GameModel;

@SuppressWarnings("serial")
public class GameView extends JFrame {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private GameModel model;
	private JPanel screen;
	private boolean visible;

	public GameView(GameModel model) {
		this.model = model;
		this.visible = false;
	}
	
	/* Cria a janela principal */
	public void CriaJanelaPrincipal() {
		if(visible == false) {
			screen = new JPanel();
			screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
			screen.setBackground(Color.BLUE);
			add(screen);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			pack();
			setLocationRelativeTo(null);
			this.visible = true;
			this.setVisible(true);
		}
	}
	
	
}
