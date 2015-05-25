package fagocity.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameView extends JFrame {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private JPanel screen;
	private boolean visible;

	public GameView() {
		this.visible = false;
	}
	
	/* Cria a janela principal */
	public void createMainWindow() {
		if(this.visible == false) {
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
