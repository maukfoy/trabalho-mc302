package fagocity.model;

import fagocity.model.interfaces.IActor;

public class Enemy extends Actor implements IActor {
	
	public Enemy(int x, int y) {
		super(x, y, "enemy");
	}

}
