package fagocity.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fagocity.model.GameModel;

@SuppressWarnings("serial")
public class GameView extends JFrame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = WIDTH / 4 * 3; // Força aspect ratio 4:3 baseado no WIDTH
	
	private JPanel screen;
	private GameModel model;
	
	public GameView(GameModel model) {
		this.model = model;
		SetMainWindow();
	}
	
	/* Cria a janela principal */
	public void SetMainWindow() {
		setTitle("Fagocity It!");
		screen = new JPanel();
		screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		screen.setBackground(Color.RED);
		add(screen);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}
}
