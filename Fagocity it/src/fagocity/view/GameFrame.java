package fagocity.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fagocity.model.GameModel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	private GameModel model;
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = WIDTH / 20 * 9;
	
	private JPanel screen;
	
	public GameFrame(GameModel model) {
		this.model = model;
		
		screen = new JPanel();
		screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		screen.setBackground(Color.BLUE);
		add(screen);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}
	
}
