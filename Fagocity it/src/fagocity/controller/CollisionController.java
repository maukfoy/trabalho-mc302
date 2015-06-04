package fagocity.controller;

import java.awt.Color;
import java.util.ArrayList;

import fagocity.model.Actor;
import fagocity.model.GameModel;

public class CollisionController {
	
	public static void update(){
		collision ();
	}
	
	public static void collision(){
		ArrayList<Actor> lista = GameModel.getActorsList();
		Actor obj1, obj2;
		
		/*compara todos os Actors do jogo*/
		for(int i = 0; i < lista.size(); i++) {
			for(int j = 0; j < lista.size(); j++) {
				obj1 = lista.get(i);
				obj2 = lista.get(j);
				
				/*se distintos objetos, com distintos tamanhos, tiverem condicao de intersecao
				 *propícia a fagocitacao, remove-se o menor e aumenta-se o maior*/
				if ( (j != i) && (obj1.getRadius() != obj2.getRadius()) && (intersection(obj1, obj2))){
					greatestObject(obj1, obj2).setRadius(newRadius(obj1, obj2));			
					lista.remove(smallestObject (obj1, obj2));
				}
			}
		}
	}
	
	/*calcula a dinstancia entre dois pontos das coordenadas cartesianas*/
	public static int distanceBetweenPoints (double x1, double x2, double y1, double y2)
	{
		int d;
		
		d = (int) (Math.sqrt ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
		
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
	public static boolean intersection (Actor obj1, Actor obj2){
		/*se a distancia entre os objetos for menor ou igual ao raio do maior objeto*/
		if (distanceBetweenPoints (obj1.getX(), obj2.getX(), obj1.getY(), obj2.getY()) <= greatestObject(obj1, obj2).getRadius()/2)
			return true;
		else
			return false;
	}
	
	/*retorna o novo raio do objeto maior após a fagocitacao*/
	public static double newRadius (Actor obj1, Actor obj2){
		double r;
		
		r = Math.sqrt(obj1.getRadius() * obj1.getRadius() + obj1.getRadius() * obj1.getRadius());
		return r;
	}
	
}
