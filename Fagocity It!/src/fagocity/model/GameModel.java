package fagocity.model;

import java.util.ArrayList;

public class GameModel {
	/* Lista de Actors */
	private ArrayList<Actor> ActorsList;
	HUDModel hudModel;
	
	private static GameModel gameModel = null;
	
	public static GameModel getInstance() {
		if (gameModel == null)
			gameModel = new GameModel();
		return gameModel;
	}
	
	private GameModel() {

		ActorsList = new ArrayList<Actor>();
		hudModel = new HUDModel();
		
		/* Manda o HUD se iniciar */
		try {
			hudModel.loadHUD();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void addToActorsList(Actor actor) {
		ActorsList.add(actor);
	}
	
	public void removeFromActorsList(Actor actor) {
		ActorsList.remove(actor);
	}
	
	/* Getter */
	public ArrayList<Actor> getActorsList() {
		return ActorsList;
	}
	
	public HUDModel getHudModel (){
		return hudModel;
	}
}
