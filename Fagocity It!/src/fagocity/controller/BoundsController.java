package fagocity.controller;

import java.util.ArrayList;

import fagocity.controller.Interfaces.ControllerSingleton;
import fagocity.model.Actor;
import fagocity.model.Enemy;
import fagocity.model.GameModel;
import fagocity.view.GameView;

public class BoundsController implements ControllerSingleton {
	private GameView view;
	private GameModel model;
	private ArrayList<Actor> actors;
	
	private static BoundsController boundsController = null;
	
	public static BoundsController getInstance() {
		if (boundsController == null)
			boundsController = new BoundsController();
		return boundsController;
	}
	
	private BoundsController()
	{
		this. model = GameModel.getInstance();
		this. view = GameView.getInstance();
		actors = model.getActorsList();
	}

	
	public void update()
	{
		seekBounders();		
	}
	
	/*delete todos os inimigos fora do mapa e fora da visao do jogador*/
	private void seekBounders ()
	{		
		for (int i =0; i < actors.size(); i++)
		{
			if (outOfBounds(actors.get(i)) && actors.get(i) instanceof Enemy)
			{
				actors.remove(i);
			}
		}
	}
	
	/*retorna true se o inimigo esiver fora dos limites do mapa e da visao do jogador*/
	private boolean outOfBounds (Actor enemy)
	{
		int leftX = enemy.getX();
		int rightX = enemy.getX() + enemy.getRadius();
		int upperY = enemy.getY();
		int lowerY = enemy.getY() + enemy.getRadius();
		
		if (rightX < view.getMinXBounds() - GameView.getScreenWidth()/2)
			return true;
		if (leftX > view.getMaxXBounds() + GameView.getScreenWidth()/2)
			return true;
		if (lowerY < view.getMinYBounds() - GameView.getScreenHeight()/2)
			return true;
		if (upperY > view.getMaxYBounds() + GameView.getScreenHeight())
			return true;
		else
			return false;
	}
	
	/*nega a passagem do player para fora dos limites do mapa*/
	public int playerRepositioning (int pos, int min, int max)
	{
		if (pos >= max)
			return max;
		else if (pos <= min)
			return min;
		else
			return pos;
	}

}
