package fagocity.model;

import java.util.ArrayList;

public class GameModel {
	/* Lista de Actors */
	private static ArrayList<Actor> ActorsList;
	
	public GameModel() {
		ActorsList = new ArrayList<Actor>();
	}
	
	public void addToActorsList(Actor actor) {
		ActorsList.add(actor);
	}
	
	public static void removeFromActorsList(Actor actor) {
		ActorsList.remove(actor);
	}
	
	/* Getter */
	public static ArrayList<Actor> getActorsList() {
		return ActorsList;
	}	
}
