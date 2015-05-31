package fagocity.model;

import fagocity.model.interfaces.IActor;

public class Player extends Actor implements IActor {
	
	public Player(int x, int y, int radius, double mass) {
		super(x, y, radius, mass);
	}
}
