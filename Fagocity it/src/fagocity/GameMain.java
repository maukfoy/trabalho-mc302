package fagocity;

import javax.swing.SwingUtilities;

import fagocity.model.GameModel;
import fagocity.view.GameFrame;

public class GameMain implements Runnable {
	
	GameFrame frame;
	GameModel model;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new GameMain());
	}
	
	public void run() {
		model = new GameModel();
		frame = new GameFrame(model);
		frame.setVisible(true);
	}
	
}
