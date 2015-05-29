package fagocity.model;

import fagocity.model.interfaces.IActor;

public class Player extends Actor implements IActor {
	
	public Player(int x, int y) {
		super(x, y, "player");
	}
}
