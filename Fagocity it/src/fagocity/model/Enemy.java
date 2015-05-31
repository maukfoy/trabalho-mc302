package fagocity.model;

import fagocity.model.interfaces.IActor;

public class Enemy extends Actor implements IActor {
	
	public Enemy(int x, int y, int radius, double mass) {
		super(x, y, radius, mass);
	}

}
