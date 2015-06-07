package fagocity.controller;

import java.util.ArrayList;

import fagocity.model.Actor;
import fagocity.model.Enemy;
import fagocity.model.GameModel;
import fagocity.view.GameView;

public class BoundsController {
	
	private static ArrayList<Actor> actors = GameModel.getActorsList();

	
	public static void update()
	{
		seekBounders();		
	}
	
	/*delete todos os inimigos fora do mapa e fora da visao do jogador*/
	private static void seekBounders ()
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
	private static boolean outOfBounds (Actor enemy)
	{
		int leftX = enemy.getX();
		int rightX = enemy.getX() + enemy.getRadius();
		int upperY = enemy.getY();
		int lowerY = enemy.getY() + enemy.getRadius();
		
		if (rightX < GameView.getMinXBounds() - GameView.getScreenWidth())
			return true;
		if (leftX > GameView.getMaxXBounds() + GameView.getScreenWidth())
			return true;
		if (lowerY < GameView.getMinYBounds() - GameView.getScreenHeight())
			return true;
		if (upperY > GameView.getMaxYBounds() + GameView.getScreenHeight())
			return true;
		else
			return false;
	}
	
	/*nega a passagem do player para fora dos limites do mapa*/
	public static int playerRepositioning (int pos, int min, int max)
	{
		if (pos >= max)
			return max;
		else if (pos <= min)
			return min;
		else
			return pos;
	}

}
