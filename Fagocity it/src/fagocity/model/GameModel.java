package fagocity.model;

import java.util.ArrayList;
import fagocity.model.interfaces.IActor;

public class GameModel {
	/* Lista de Actors */
	private static ArrayList<IActor> ActorsList;
	
	public GameModel() {
		ActorsList = new ArrayList<IActor>();
	}
	
	public void addToActorsList(IActor actor) {
		ActorsList.add(actor);
	}
	
	/* Getters e setters */
	public static ArrayList<IActor> getActorsList() {
		return ActorsList;
	}	
}
