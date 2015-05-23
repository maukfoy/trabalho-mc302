package fagocity.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fagocity.model.GameModel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private GameModel model;
	private JPanel screen;
	
	public GameFrame(GameModel model) {
		this.model = model;
		
		// Cria a janela principal
		screen = new JPanel();
		screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		screen.setBackground(Color.BLUE);
		add(screen);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}
	
}
