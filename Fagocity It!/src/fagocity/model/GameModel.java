package fagocity.model;

import java.util.ArrayList;

import fagocity.controller.ActorFactory;
import fagocity.controller.CameraController;
import fagocity.controller.HUDController;
import fagocity.controller.SpawnController;

public class GameModel {
	/* Lista de Actors */
	private ArrayList<Actor> ActorsList;
	HUDModel hudModel;
	
	public GameModel() {

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
