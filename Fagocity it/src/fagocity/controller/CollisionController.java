package fagocity.controller;

import java.awt.Color;
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
					Actor greatest = greatestObject(obj1, obj2);
					Actor smallest = smallestObject(obj1, obj2);
					
					greatest.setRadius(newRadius(obj1, obj2));
					/* Se o player consegue fagocitar */
					if( greatest instanceof Player  ) {
						/* Tira o menor, no caso o inimigo, do jogo */
						toBeDeleted.add(smallest);
												
						/* Se as cores não forem iguais, perde vida */
						if( equalColors(obj1.getColor(), obj2.getColor()) == false ) {
							PlayerController.reducePlayerLife(greatest, toBeDeleted);
						}
						
						/* Muda a cor do player */
						greatest.setColor( SpawnController.generateRandomColor() );
 					}
					
					/* Se o player não consegue fagocitar, tira ele do jogo */
					else {
						toBeDeleted.add(smallest);
						/* Salva o highscore */
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
			list.remove(dead);
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
	
	/*retorna o novo raio do objeto maior apÃ³s a fagocitacao*/
	public static int newRadius (Actor obj1, Actor obj2)
	{
		double r;
		
		r = Math.sqrt(obj1.getRadius() * obj1.getRadius() + obj2.getRadius() * obj2.getRadius());
	
		return (int)r;
	}
	
	private static boolean equalColors(Color color1, Color color2) {
		if(color1 == color2)
			return true;
		else
			return false;
	}

}
