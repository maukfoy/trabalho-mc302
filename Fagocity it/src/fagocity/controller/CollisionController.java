package fagocity.controller;

import java.io.IOException;
import java.util.ArrayList;

import fagocity.model.Actor;
import fagocity.model.GameModel;
import fagocity.model.HUDModel;
import fagocity.model.Player;

public class CollisionController {
	
	public static void update()
	{
		collision ();
	}
	
	public static void collision()
	{
		ArrayList<Actor> list = GameModel.getActorsList();
		ArrayList<Actor> toBeDeleted = new ArrayList<Actor>();
		Actor obj1, obj2;
		
		/*compara todos os Actors do jogo*/
		for(int i = 0; i < list.size(); i++)
		{
			for(int j = 0; j < list.size(); j++)
			{
				obj1 = list.get(i);
				obj2 = list.get(j);
				
				/*se distintos objetos, com distintos tamanhos, tiverem condicao de intersecao
				 *propicia a fagocitacao, guarda-se o menor (p/ remove-lo posteriormente) e 
				 *aumenta-se o maior*/
				if ( (j != i) && (obj1.getRadius() != obj2.getRadius()) && (intersection(obj1, obj2)))
				{	
					/* Se for o player, aumenta o seu raio */
					if ( greatestObject(obj1, obj2) instanceof Player )
						greatestObject(obj1, obj2).setRadius(newRadius(obj1, obj2));
					toBeDeleted.add(smallestObject(obj1,obj2));
					Actor dead = smallestObject (obj1, obj2);
					/* Se o Actor morto for o player, salvar o highscore */
					if(dead instanceof Player) {
						try {
							HUDModel.saveHighscore();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		/*remove todos os objetos que foram fagocitados*/
		for (int i = 0; i < toBeDeleted.size(); i++) {
			Actor dead = toBeDeleted.get(i);
			/* Se for o player, diminui uma vida */
			if(dead instanceof Player) {
				long currentTime = System.currentTimeMillis();
				/* Faz com que o deathTimeDelay seja respeitado, ou seja
				 * s� deixa o player morrer se ja tiver se passado 
				 * o tempo de DeathTimeDaley desde sua ultima morte */
				if( Player.getLastDeathTime() == 0 || 
						(currentTime - Player.getLastDeathTime() ) > Player.getDeathTimeDelay() ) {
						Player.lives--;
						Player.setLastDeathTime( System.currentTimeMillis() );
				}
				/* Se o player n�o tiver mais vidas, ele morre pra sempre */
				if(Player.lives < 0)
					list.remove(dead);
			}
			else {
				list.remove(dead);
			}
			
		}
	}
	
	/*calcula a dinstancia entre dois pontos das coordenadas cartesianas*/
	public static int distanceBetweenPoints (double x1, double x2, double y1, double y2)
	{
		int d;
		
		d = (int)(Math.sqrt ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
		
		return d;
	}
	
	/*retorna o objeto com raio menor*/
	public static Actor smallestObject (Actor obj1, Actor obj2)
	{
		if (obj1.getRadius() > obj2.getRadius())
			return obj2;
		else
			return obj1;
	}
	
	/*retorna o objeto com raio maior*/
	public static Actor greatestObject (Actor obj1, Actor obj2)
	{
		if (obj1.getRadius() > obj2.getRadius())
			return obj1;
		else
			return obj2;
	}
	
	/*retorna true se a intersecao entre dois objetos for suficiente para ocorrer a fagocitacao*/
	public static boolean intersection (Actor obj1, Actor obj2)
	{
		
		double x1center = obj1.getX() + obj1.getRadius()/2;
		double x2center = obj2.getX()+ obj2.getRadius()/2;
		double y1center = obj1.getY()+ obj1.getRadius()/2;
		double y2center = obj2.getY()+ obj2.getRadius()/2;
		/*se a distancia entre os objetos for menor ou igual ao raio do maior objeto*/
		if (distanceBetweenPoints (x1center, x2center , y1center , y2center)
				<= greatestObject(obj1, obj2).getRadius()/2)
			return true;
		else
			return false;
	}
	
	/*retorna o novo raio do objeto maior após a fagocitacao*/
	public static int newRadius (Actor obj1, Actor obj2)
	{
		double r;
		
		r = Math.sqrt(obj1.getRadius() * obj1.getRadius() + obj2.getRadius() * obj2.getRadius());
	
		return (int)r;
	}
	
}
